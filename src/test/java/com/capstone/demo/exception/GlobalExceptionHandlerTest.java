package com.capstone.demo.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {
    
    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;
    
    @Mock
    private UserNotFoundException userNotFoundException;
    
    @Mock
    private RequesterNotFoundException requesterNotFoundException;
    
    @Mock
    private BloodNotAvailabilityException bloodNotAvailabilityException;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testUserNotFoundException() {
        // Arrange
        String errorMessage = "User not found";
        when(userNotFoundException.getMessage()).thenReturn(errorMessage);
        
        // Act
        ResponseEntity<ExceptionResponse> response = globalExceptionHandler.userNotFoundExceptin(userNotFoundException);
        
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("67867", response.getBody().getExceptionCode());
        assertEquals(errorMessage, response.getBody().getMessage());
    }
    
    @Test
    public void testRequesterNotFoundException() {
        // Arrange
        String errorMessage = "Requester not found";
        when(requesterNotFoundException.getMessage()).thenReturn(errorMessage);
        
        // Act
        ResponseEntity<ExceptionResponse> response = globalExceptionHandler.requesterNotFoundException(requesterNotFoundException);
        
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("3455432", response.getBody().getExceptionCode());
        assertEquals(errorMessage, response.getBody().getMessage());
    }
    
    @Test
    public void testBloodNotAvailabilityException() {
        // Arrange
        String errorMessage = "Blood not available";
        when(bloodNotAvailabilityException.getMessage()).thenReturn(errorMessage);
        
        // Act
        ResponseEntity<ExceptionResponse> response = globalExceptionHandler.bloodNotAvailabilityException(bloodNotAvailabilityException);
        
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("876567", response.getBody().getExceptionCode());
        assertEquals(errorMessage, response.getBody().getMessage());
    }
}