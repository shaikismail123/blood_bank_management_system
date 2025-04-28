package com.capstone.demo.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersDtoTest {
    
    @Test
    public void testConstructorAndGetters() {
        UsersDto dto = new UsersDto(1L, "John", "Doe", "A+", "New York", "1234567890");
        
        assertEquals(1L, dto.getUserId());
        assertEquals("John", dto.getFirstName());
        assertEquals("Doe", dto.getLastName());
        assertEquals("A+", dto.getBloodGroup());
        assertEquals("New York", dto.getCity());
        assertEquals("1234567890", dto.getPhoneNumber());
    }
    
    @Test
    public void testSetters() {
        UsersDto dto = new UsersDto();
        
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
        
        dto.setPhoneNumber("1234567890");
        assertEquals("1234567890", dto.getPhoneNumber());
    }
}