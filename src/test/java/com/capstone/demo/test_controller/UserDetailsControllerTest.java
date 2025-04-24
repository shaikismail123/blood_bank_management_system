package com.capstone.demo.test_controller;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.controller.UserDetailsController;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.service.MyUserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

public class UserDetailsControllerTest {

    @Mock
    private MyUserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private DefaultValues defaultValues;

    @InjectMocks
    private UserDetailsController userDetailsController;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // 1. Test insertUserDetails
    @Test
    public void testInsertUserDetails_Success() throws Exception {
        // Arrange
        UserDetailsDto userDetailsDto = new UserDetailsDto(); // Create a sample UserDetailsDto
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put(AppConstants.SUCCESS, "Success Message");

        when(userDetailsServiceImpl.insertUserDetails(any(UserDetailsDto.class))).thenReturn(true);
        when(defaultValues.getMessage()).thenReturn(messageMap);

        // Act
        ResponseEntity<String> responseEntity = userDetailsController.insertUserDetails(userDetailsDto);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Success Message", responseEntity.getBody());
    }

    @Test
    public void testInsertUserDetails_Failure() throws Exception {
        // Arrange
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        when(userDetailsServiceImpl.insertUserDetails(any(UserDetailsDto.class))).thenReturn(false);

        // Act
        ResponseEntity<String> responseEntity = userDetailsController.insertUserDetails(userDetailsDto);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    public void testInsertUserDetails_Exception() throws Exception {
        // Arrange
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        when(userDetailsServiceImpl.insertUserDetails(any(UserDetailsDto.class))).thenThrow(new RuntimeException("Simulated Exception"));

        // Act
        ResponseEntity<String> responseEntity = userDetailsController.insertUserDetails(userDetailsDto);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    // 2. Test getUserDetailsById
    @Test
    public void testGetUserDetailsById_UserFound() throws Exception {
        // Arrange
        Long id = 1L;
        UserDetailsDto userDetailsDto = new UserDetailsDto(); // Create a sample UserDetailsDto
        userDetailsDto.setUserId(id);
        when(userDetailsServiceImpl.userDetailsById(id)).thenReturn(userDetailsDto);

        // Act
        ResponseEntity<UserDetailsDto> responseEntity = userDetailsController.getUserDetailsById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDetailsDto, responseEntity.getBody());
    }

    @Test
    public void testGetUserDetailsById_UserNotFound() throws Exception {
        // Arrange
        Long id = 1L;
        when(userDetailsServiceImpl.userDetailsById(id)).thenReturn(null);

        // Act
        ResponseEntity<UserDetailsDto> responseEntity = userDetailsController.getUserDetailsById(id);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    public void testGetUserDetailsById_Exception() throws Exception {
        // Arrange
        Long id = 1L;
        when(userDetailsServiceImpl.userDetailsById(id)).thenThrow(new RuntimeException("Simulated Exception"));

        // Act
        ResponseEntity<UserDetailsDto> responseEntity = userDetailsController.getUserDetailsById(id);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }
}

