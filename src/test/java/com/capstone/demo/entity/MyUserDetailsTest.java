package com.capstone.demo.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyUserDetailsTest {
    
    @Test
    public void testMyUserDetailsConstructorAndGettersSetters() {
        // Arrange
        MyUserDetails userDetails = new MyUserDetails();
        userDetails.setUserId(1L);
        userDetails.setFirstName("John");
        userDetails.setLastName("Doe");
        userDetails.setBloodGroup("A+");
        userDetails.setCity("New York");
        userDetails.setEmail("john.doe@example.com");
        userDetails.setPhoneNumber("1234567890");
        userDetails.setUserType("DONOR");
        userDetails.setPasswordHash("hashedPassword");
        
        // Act & Assert
        assertEquals(1L, userDetails.getUserId());
        assertEquals("John", userDetails.getFirstName());
        assertEquals("Doe", userDetails.getLastName());
        assertEquals("A+", userDetails.getBloodGroup());
        assertEquals("New York", userDetails.getCity());
        assertEquals("john.doe@example.com", userDetails.getEmail());
        assertEquals("1234567890", userDetails.getPhoneNumber());
        assertEquals("DONOR", userDetails.getUserType());
        assertEquals("hashedPassword", userDetails.getPasswordHash());
    }
    
    @Test
    public void testMyUserDetailsAllArgsConstructor() {
        // Arrange
        MyUserDetails userDetails = new MyUserDetails(1L, "Jane", "Doe", "B+", "Los Angeles", "jane.doe@example.com", "0987654321", "REQUESTOR", "hashedPassword");
        
        // Act & Assert
        assertEquals(1L, userDetails.getUserId());
        assertEquals("Jane", userDetails.getFirstName());
        assertEquals("Doe", userDetails.getLastName());
        assertEquals("B+", userDetails.getBloodGroup());
        assertEquals("Los Angeles", userDetails.getCity());
        assertEquals("jane.doe@example.com", userDetails.getEmail());
        assertEquals("0987654321", userDetails.getPhoneNumber());
        assertEquals("REQUESTOR", userDetails.getUserType());
        assertEquals("hashedPassword", userDetails.getPasswordHash());
    }
}