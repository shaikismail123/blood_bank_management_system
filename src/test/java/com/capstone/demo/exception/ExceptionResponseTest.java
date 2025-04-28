package com.capstone.demo.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ExceptionResponseTest {
    
    @Test
    public void testExceptionResponseConstructorAndGettersSetters() {
        // Arrange
        String message = "An error occurred";
        String timeAndDate = "2025-04-25T08:05:01";
        String exceptionCode = "ERR001";
        
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage(message);
        exceptionResponse.setTimeAnDate(timeAndDate);
        exceptionResponse.setExceptionCode(exceptionCode);
        
        // Act & Assert
        assertEquals(message, exceptionResponse.getMessage());
        assertEquals(timeAndDate, exceptionResponse.getTimeAnDate());
        assertEquals(exceptionCode, exceptionResponse.getExceptionCode());
    }
    
    @Test
    public void testAllArgsConstructor() {
        // Arrange
        String message = "An error occurred";
        String timeAndDate = "2025-04-25T08:05:01";
        String exceptionCode = "ERR001";
        
        // Act
        ExceptionResponse exceptionResponse = new ExceptionResponse(message, timeAndDate, exceptionCode);
        
        // Assert
        assertEquals(message, exceptionResponse.getMessage());
        assertEquals(timeAndDate, exceptionResponse.getTimeAnDate());
        assertEquals(exceptionCode, exceptionResponse.getExceptionCode());
    }
    
    @Test
    public void testDefaultConstructor() {
        // Act
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        
        // Assert
        assertNull(exceptionResponse.getMessage());
        assertNull(exceptionResponse.getTimeAnDate());
        assertNull(exceptionResponse.getExceptionCode());
    }
}