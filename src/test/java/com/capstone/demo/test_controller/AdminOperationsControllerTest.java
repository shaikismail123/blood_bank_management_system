package com.capstone.demo.test_controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstone.demo.controller.AdminOperationsController;
import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.entity.AdminOperations;
import com.capstone.demo.service.AdminDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AdminOperationsControllerTest {

    @Mock
    private AdminDetailsServiceImpl adminDetailsServiceImpl;

    @InjectMocks
    private AdminOperationsController adminOperationsController;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize Mockito mocks
    }

    // 1. Test saveAdminDetails
    @Test
    public void testSaveAdminDetails_ValidInput() throws Exception {
        // Arrange
        AdminOperations adminOperations = new AdminOperations(); // Create a valid AdminOperations object
        when(adminDetailsServiceImpl.saveAdminDetails(any(AdminOperations.class))).thenReturn("Success");

        // Act
        ResponseEntity<String> responseEntity = adminOperationsController.saveAdminDetails(adminOperations);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Success", responseEntity.getBody());
    }

    @Test
    public void testSaveAdminDetails_ServiceReturnsNull() throws Exception {
        // Arrange
        AdminOperations adminOperations = new AdminOperations();
        when(adminDetailsServiceImpl.saveAdminDetails(any(AdminOperations.class))).thenReturn(null);

        // Act
        ResponseEntity<String> responseEntity = adminOperationsController.saveAdminDetails(adminOperations);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    // 2. Test getAllDonarsForAdmin
    @Test
    public void testGetAllDonarsForAdmin_DonorsExist() throws Exception {
        // Arrange
        List<UserDetailsDto> donarList = new ArrayList<>();
        // Add some UserDetailsDto objects to the list
        donarList.add(new UserDetailsDto());
        donarList.add(new UserDetailsDto());
        when(adminDetailsServiceImpl.getAllDonarsForAdmin()).thenReturn(donarList);

        // Act
        ResponseEntity<List<UserDetailsDto>> responseEntity = adminOperationsController.getAllDonarsForAdmin();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(donarList, responseEntity.getBody());
    }

    @Test
    public void testGetAllDonarsForAdmin_NoDonors() throws Exception {
        // Arrange
        List<UserDetailsDto> donarList = new ArrayList<>(); // Empty list
        when(adminDetailsServiceImpl.getAllDonarsForAdmin()).thenReturn(donarList);

        // Act
        ResponseEntity<List<UserDetailsDto>> responseEntity = adminOperationsController.getAllDonarsForAdmin();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(donarList, responseEntity.getBody());
    }

    @Test
    public void testGetAllDonarsForAdmin_ServiceReturnsNull() throws Exception {
        // Arrange
        when(adminDetailsServiceImpl.getAllDonarsForAdmin()).thenReturn(null);

        // Act
        ResponseEntity<List<UserDetailsDto>> responseEntity = adminOperationsController.getAllDonarsForAdmin();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    // 3. Test getAllRequesterForApproval
    @Test
    public void testGetAllRequesterForApproval_RequestersExist() throws Exception {
        // Arrange
        List<RequesterDetailsDto> requesterList = new ArrayList<>();
        // Add some RequesterDetailsDto objects
        requesterList.add(new RequesterDetailsDto());
        requesterList.add(new RequesterDetailsDto());
        when(adminDetailsServiceImpl.getAllRequesterForApproval()).thenReturn(requesterList);

        // Act
        ResponseEntity<List<RequesterDetailsDto>> responseEntity = adminOperationsController.getAllRequesterForApproval();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(requesterList, responseEntity.getBody());
    }

    @Test
    public void testGetAllRequesterForApproval_NoRequesters() throws Exception {
        // Arrange
        List<RequesterDetailsDto> requesterList = new ArrayList<>(); // Empty list
        when(adminDetailsServiceImpl.getAllRequesterForApproval()).thenReturn(requesterList);

        // Act
        ResponseEntity<List<RequesterDetailsDto>> responseEntity = adminOperationsController.getAllRequesterForApproval();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(requesterList, responseEntity.getBody());
    }

    @Test
    public void testGetAllRequesterForApproval_ServiceReturnsNull() throws Exception {
        // Arrange
        when(adminDetailsServiceImpl.getAllRequesterForApproval()).thenReturn(null);

        // Act
        ResponseEntity<List<RequesterDetailsDto>> responseEntity = adminOperationsController.getAllRequesterForApproval();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }
}

