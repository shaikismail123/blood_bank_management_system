package com.capstone.demo.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserNotFoundExceptionTest {
    
    @Test
    public void testDefaultConstructor() {
        // Arrange
        UserNotFoundException exception = new UserNotFoundException();
        
        // Act & Assert
        assertNull(exception.getMessage());
    }
    
    @Test
    public void testMessageConstructor() {
        // Arrange
        String message = "User not found";
        UserNotFoundException exception = new UserNotFoundException(message);
        
        // Act & Assert
        assertEquals(message, exception.getMessage());
    }
}