package com.capstone.demo.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BloodNotAvailabilityExceptionTest {
    
    @Test
    public void testExceptionMessage() {
        // Arrange
        String message = "Blood not available";
        BloodNotAvailabilityException exception = new BloodNotAvailabilityException(message);
        
        // Act & Assert
        assertEquals(message, exception.getMessage());
    }
    
    @Test
    public void testDefaultConstructor() {
        // Arrange
        BloodNotAvailabilityException exception = new BloodNotAvailabilityException();
        
        // Act & Assert
        assertNull(exception.getMessage());
    }
    
    @Test
    public void testAllArgsConstructor() {
        // Arrange
        String message = "Blood not available";
        BloodNotAvailabilityException exception = new BloodNotAvailabilityException(message);
        
        // Act & Assert
        assertEquals(message, exception.getMessage());
    }
}