package com.capstone.demo.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDetailsDtoTest {
    
    @Test
    public void testConstructorAndGetters() {
        UserDetailsDto dto = new UserDetailsDto(1L, "John", "Doe", "A+", "New York",
                "john.doe@example.com", "1234567890", "DONOR", "hashedPassword");
        
        assertEquals(1L, dto.getUserId());
        assertEquals("John", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
        assertEquals("A+", dto.getBloodGroup());
        assertEquals("New York", dto.getCity());
        assertEquals("john.doe@example.com", dto.getEmail());
        assertEquals("1234567890", dto.getPhoneNumber());
        assertEquals("DONOR", dto.getUserType());
        assertEquals("hashedPassword", dto.getPasswordHash());
    }
    
    @Test
    public void testSetters() {
        UserDetailsDto dto = new UserDetailsDto();
        
        dto.setUserId(1L);
        assertEquals(1L, dto.getUserId());
        
        dto.setFirstName("John");
        assertEquals("John", dto.getFirstName());
        
        dto.setLastName("Doe");
        assertEquals("Doe", dto.getLastName());
        
        dto.setBloodGroup("A+");
        assertEquals("A+", dto.getBloodGroup());
        
        dto.setCity("New York");
        assertEquals("New York", dto.getCity());
        
        dto.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", dto.getEmail());
        
        dto.setPhoneNumber("1234567890");
        assertEquals("1234567890", dto.getPhoneNumber());
        
        dto.setUserType("DONOR");
        assertEquals("DONOR", dto.getUserType());
        
        dto.setPasswordHash("hashedPassword");
        assertEquals("hashedPassword", dto.getPasswordHash());
    }
    
    @Test
    public void testToString() {
        UserDetailsDto dto = new UserDetailsDto(1L, "John", "Doe", "A+", "New York",
                "john.doe@example.com", "1234567890", "DONOR", "hashedPassword");
        
        String expectedString = "UserDetailsDto [userId=1, firstName=John, lastName=Doe, bloodGroup=A+, city=New York, "
                + "email=john.doe@example.com, phoneNumber=1234567890, userType=DONOR, passwordHash=hashedPassword]";
        
        assertEquals(expectedString, dto.toString());
    }
}