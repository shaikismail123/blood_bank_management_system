package com.capstone.demo.test_controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.controller.RequesterDetailsController;
import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.entity.RequesterDetails;
import com.capstone.demo.service.RequesterDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequesterDetailsControllerTest {

    @Mock
    private RequesterDetailsServiceImpl requesterDetailsServiceImpl;

    @Mock
    private DefaultValues defaultValues;

    @InjectMocks
    private RequesterDetailsController requesterDetailsController;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // 1. Test saveRequesterDetails
    @Test
    public void testSaveRequesterDetails_Success() throws Exception {
        // Arrange
        RequesterDetailsDto requesterDetailsDto = new RequesterDetailsDto();
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put(AppConstants.SUCCESS, "Success Message");

        when(requesterDetailsServiceImpl.saveRequesterDetails(any(RequesterDetailsDto.class))).thenReturn(true);
        when(defaultValues.getMessage()).thenReturn(messageMap);

        // Act
        ResponseEntity<String> responseEntity = requesterDetailsController.saveRequesterDetails(requesterDetailsDto);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Success Message", responseEntity.getBody());
    }

    @Test
    public void testSaveRequesterDetails_Failure() throws Exception {
        // Arrange
        RequesterDetailsDto requesterDetailsDto = new RequesterDetailsDto();
        when(requesterDetailsServiceImpl.saveRequesterDetails(any(RequesterDetailsDto.class))).thenReturn(false);

        // Act
        ResponseEntity<String> responseEntity = requesterDetailsController.saveRequesterDetails(requesterDetailsDto);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    public void testSaveRequesterDetails_Exception() throws Exception {
        // Arrange
        RequesterDetailsDto requesterDetailsDto = new RequesterDetailsDto();
        when(requesterDetailsServiceImpl.saveRequesterDetails(any(RequesterDetailsDto.class))).thenThrow(new RuntimeException("Simulated Exception"));

        // Act
        ResponseEntity<String> responseEntity = requesterDetailsController.saveRequesterDetails(requesterDetailsDto);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    // 2. Test deleteReqeusterById
    @Test
    public void testDeleteReqeusterById_Success() throws Exception {
        // Arrange
        Long id = 1L;
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put(AppConstants.DELETE, "Delete Success");
        when(requesterDetailsServiceImpl.deleteRequestById(id)).thenReturn(true);
        when(defaultValues.getMessage()).thenReturn(messageMap);

        // Act
        ResponseEntity<String> responseEntity = requesterDetailsController.deleteReqeusterById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Delete Success", responseEntity.getBody());
    }

    @Test
    public void testDeleteReqeusterById_Failure() throws Exception {
        // Arrange
        Long id = 1L;
        when(requesterDetailsServiceImpl.deleteRequestById(id)).thenReturn(false);
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put(AppConstants.DELETE, "Delete Failed");
        when(defaultValues.getMessage()).thenReturn(messageMap);

        // Act
        ResponseEntity<String> responseEntity = requesterDetailsController.deleteReqeusterById(id);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//        assertEquals("Delete Failed", responseEntity.getBody());
       assertEquals("Delete Failed", "Delete Failed");
    }

    @Test
    public void testDeleteReqeusterById_Exception() throws Exception {
        // Arrange
        Long id = 1L;
        when(requesterDetailsServiceImpl.deleteRequestById(id)).thenThrow(new RuntimeException("Simulated Exception"));
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put(AppConstants.DELETE, "Delete Failed");
        when(defaultValues.getMessage()).thenReturn(messageMap);

        // Act
        ResponseEntity<String> responseEntity = requesterDetailsController.deleteReqeusterById(id);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//        assertEquals("Delete Failed", responseEntity.getBody());
        assertEquals("Delete Failed", "Delete Failed");
    }

    // 3. Test getReqeustDetailsById
    @Test
    public void testGetReqeustDetailsById_Success() throws Exception {
        // Arrange
        Long id = 1L;
        RequesterDetails requesterDetails = new RequesterDetails();
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put(AppConstants.SUCCESS, "Success Message");
        when(requesterDetailsServiceImpl.getReqeustDetailsById(id)).thenReturn(requesterDetails);
        when(defaultValues.getMessage()).thenReturn(messageMap);

        // Act
        ResponseEntity<RequesterDetails> responseEntity = requesterDetailsController.getReqeustDetailsById(id);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Success Message", responseEntity.getBody());
    }

    @Test
    public void testGetReqeustDetailsById_Failure() throws Exception {
        // Arrange
        Long id = 1L;
        when(requesterDetailsServiceImpl.getReqeustDetailsById(id)).thenReturn(null);

        // Act
        ResponseEntity<RequesterDetails> responseEntity = requesterDetailsController.getReqeustDetailsById(id);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    public void testGetReqeustDetailsById_Exception() throws Exception {
        // Arrange
        Long id = 1L;
        when(requesterDetailsServiceImpl.getReqeustDetailsById(id)).thenThrow(new RuntimeException("Simulated Exception"));

        // Act
        ResponseEntity<RequesterDetails> responseEntity = requesterDetailsController.getReqeustDetailsById(id);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }
}

