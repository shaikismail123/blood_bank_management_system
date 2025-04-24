package com.capstone.demo.test_controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.controller.BloodAvailabilityController;
import com.capstone.demo.entity.BloodAvailability;
import com.capstone.demo.service.BloodAvailabilityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BloodAvailabilityControllerTest {

    @Mock
    private BloodAvailabilityServiceImpl bloodAvailabilityServiceImpl;

    @Mock
    private DefaultValues defaultValues;

    @InjectMocks
    private BloodAvailabilityController bloodAvailabilityController;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // 1. Test getBloodDetails
    @Test
    public void testGetBloodDetails_BloodDetailsExist() {
        // Arrange
        List<BloodAvailability> bloodDetailsList = new ArrayList<>();
        bloodDetailsList.add(new BloodAvailability());
        bloodDetailsList.add(new BloodAvailability());
        when(bloodAvailabilityServiceImpl.getBloodDetails()).thenReturn(bloodDetailsList);

        // Act
        ResponseEntity<List<BloodAvailability>> responseEntity = bloodAvailabilityController.getBloodDetails();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(bloodDetailsList, responseEntity.getBody());
    }

    @Test
    public void testGetBloodDetails_NoBloodDetails() {
        // Arrange
        List<BloodAvailability> bloodDetailsList = new ArrayList<>();
        when(bloodAvailabilityServiceImpl.getBloodDetails()).thenReturn(bloodDetailsList);

        // Act
        ResponseEntity<List<BloodAvailability>> responseEntity = bloodAvailabilityController.getBloodDetails();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    public void testGetBloodDetails_Exception() {
        // Arrange
        when(bloodAvailabilityServiceImpl.getBloodDetails()).thenThrow(new RuntimeException("Simulated Exception"));

        // Act
        ResponseEntity<List<BloodAvailability>> responseEntity = bloodAvailabilityController.getBloodDetails();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    // 2. Test getBloodDetailsBasedOnCity
    @Test
    public void testGetBloodDetailsBasedOnCity_BloodDetailsExist() {
        // Arrange
        String city = "Chennai";
        List<BloodAvailability> bloodDetailsList = new ArrayList<>();
        bloodDetailsList.add(new BloodAvailability());
        when(bloodAvailabilityServiceImpl.getBloodDetailsBasedOnCity(city)).thenReturn(bloodDetailsList);

        // Act
        ResponseEntity<List<BloodAvailability>> responseEntity = bloodAvailabilityController.getBloodDetailsBasedOnCity(city);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(bloodDetailsList, responseEntity.getBody());
    }

    @Test
    public void testGetBloodDetailsBasedOnCity_NoBloodDetails() {
        // Arrange
        String city = "Chennai";
        List<BloodAvailability> bloodDetailsList = new ArrayList<>();
        when(bloodAvailabilityServiceImpl.getBloodDetailsBasedOnCity(city)).thenReturn(bloodDetailsList);

        // Act
        ResponseEntity<List<BloodAvailability>> responseEntity = bloodAvailabilityController.getBloodDetailsBasedOnCity(city);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    public void testGetBloodDetailsBasedOnCity_Exception() {
        // Arrange
        String city = "Chennai";
        when(bloodAvailabilityServiceImpl.getBloodDetailsBasedOnCity(city)).thenThrow(new RuntimeException("Simulated Exception"));

        // Act
        ResponseEntity<List<BloodAvailability>> responseEntity = bloodAvailabilityController.getBloodDetailsBasedOnCity(city);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    // 3. Test getBloodDetailsBasedOnBloodGroup
    @Test
    public void testGetBloodDetailsBasedOnBloodGroup_BloodDetailsExist() {
        // Arrange
        String bloodGroup = "A+";
        List<BloodAvailability> bloodDetailsList = new ArrayList<>();
        bloodDetailsList.add(new BloodAvailability());
        when(bloodAvailabilityServiceImpl.getBloodDetailsBasedOnBloodGroup(bloodGroup)).thenReturn(bloodDetailsList);

        // Act
        ResponseEntity<List<BloodAvailability>> responseEntity = bloodAvailabilityController.getBloodDetailsBasedOnBloodGroup(bloodGroup);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(bloodDetailsList, responseEntity.getBody());
    }

    @Test
    public void testGetBloodDetailsBasedOnBloodGroup_NoBloodDetails() {
        // Arrange
        String bloodGroup = "A+";
        List<BloodAvailability> bloodDetailsList = new ArrayList<>();
        when(bloodAvailabilityServiceImpl.getBloodDetailsBasedOnBloodGroup(bloodGroup)).thenReturn(bloodDetailsList);

        // Act
        ResponseEntity<List<BloodAvailability>> responseEntity = bloodAvailabilityController.getBloodDetailsBasedOnBloodGroup(bloodGroup);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    public void testGetBloodDetailsBasedOnBloodGroup_Exception() {
        // Arrange
        String bloodGroup = "A+";
        when(bloodAvailabilityServiceImpl.getBloodDetailsBasedOnBloodGroup(bloodGroup)).thenThrow(new RuntimeException("Simulated Exception"));

        // Act
        ResponseEntity<List<BloodAvailability>> responseEntity = bloodAvailabilityController.getBloodDetailsBasedOnBloodGroup(bloodGroup);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    // 4. Test getBloodDetailsBasedOnCityAndBloodGroup
    @Test
    public void testGetBloodDetailsBasedOnCityAndBloodGroup_BloodDetailsExist() {
        // Arrange
        Map<String, String> params = new HashMap<>();
        params.put("city", "Chennai");
        params.put("blood", "A+");
        List<BloodAvailability> bloodDetailsList = new ArrayList<>();
        bloodDetailsList.add(new BloodAvailability());
        when(bloodAvailabilityServiceImpl.getBloodDetailsBasedOnCityAndBloodGroup("Chennai", "A+")).thenReturn(bloodDetailsList);

        // Act
        ResponseEntity<List<BloodAvailability>> responseEntity = bloodAvailabilityController.getBloodDetailsBasedOnCityAndBloodGroup(params);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(bloodDetailsList, responseEntity.getBody());
    }

    @Test
    public void testGetBloodDetailsBasedOnCityAndBloodGroup_NoBloodDetails() {
        // Arrange
        Map<String, String> params = new HashMap<>();
        params.put("city", "Chennai");
        params.put("blood", "A+");
        List<BloodAvailability> bloodDetailsList = new ArrayList<>();
        when(bloodAvailabilityServiceImpl.getBloodDetailsBasedOnCityAndBloodGroup("Chennai", "A+")).thenReturn(bloodDetailsList);

        // Act
        ResponseEntity<List<BloodAvailability>> responseEntity = bloodAvailabilityController.getBloodDetailsBasedOnCityAndBloodGroup(params);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    public void testGetBloodDetailsBasedOnCityAndBloodGroup_Exception() {
        // Arrange
        Map<String, String> params = new HashMap<>();
        params.put("city", "Chennai");
        params.put("blood", "A+");
        when(bloodAvailabilityServiceImpl.getBloodDetailsBasedOnCityAndBloodGroup("Chennai", "A+")).thenThrow(new RuntimeException("Simulated Exception"));

        // Act
        ResponseEntity<List<BloodAvailability>> responseEntity = bloodAvailabilityController.getBloodDetailsBasedOnCityAndBloodGroup(params);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }
}

