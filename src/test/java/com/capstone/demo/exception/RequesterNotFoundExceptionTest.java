package com.capstone.demo.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RequesterNotFoundExceptionTest {
    
    @Test
    public void testDefaultConstructor() {
        // Arrange
        RequesterNotFoundException exception = new RequesterNotFoundException();
        
        // Act & Assert
        assertNull(exception.getMessage());
    }
    
    @Test
    public void testMessageConstructor() {
        // Arrange
        String message = "Requester not found";
        RequesterNotFoundException exception = new RequesterNotFoundException(message);
        
        // Act & Assert
        assertEquals(message, exception.getMessage());
    }
}