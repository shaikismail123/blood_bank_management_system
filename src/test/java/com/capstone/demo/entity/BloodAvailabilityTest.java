package com.capstone.demo.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BloodAvailabilityTest {
    
    @Test
    public void testBloodAvailabilityConstructorAndGettersSetters() {
        // Arrange
        BloodAvailability bloodAvailability = new BloodAvailability();
        bloodAvailability.setBloodId(1L);
        bloodAvailability.setBloodGroup("A+");
        bloodAvailability.setQuantity(String.valueOf(10));
        bloodAvailability.setCity("New York");
        
        // Act & Assert
        assertEquals(1L, bloodAvailability.getBloodId());
        assertEquals("A+", bloodAvailability.getBloodGroup());
        assertEquals(10, bloodAvailability.getQuantity());
        assertEquals("New York", bloodAvailability.getCity());
    }
    
    @Test
    public void testBloodAvailabilityAllArgsConstructor() {
        // Arrange
        BloodAvailability bloodAvailability = new BloodAvailability(1L, "B+", "5", "Los Angeles");
        
        // Act & Assert
        assertEquals(1L, bloodAvailability.getBloodId());
        assertEquals("B+", bloodAvailability.getBloodGroup());
        assertEquals(5, bloodAvailability.getQuantity());
        assertEquals("Los Angeles", bloodAvailability.getCity());
    }
    
    @Test
    public void testEqualsAndHashCode() {
        // Arrange
        BloodAvailability bloodAvailability1 = new BloodAvailability(1L, "O-", "15", "Chicago");
        BloodAvailability bloodAvailability2 = new BloodAvailability(1L, "O-","15", "Chicago");
        BloodAvailability bloodAvailability3 = new BloodAvailability(2L, "A+", "20", "Houston");
        
        // Act & Assert
        assertEquals(bloodAvailability1, bloodAvailability2); // Should be equal
        assertNotEquals(bloodAvailability1, bloodAvailability3); // Should not be equal
        assertEquals(bloodAvailability1.hashCode(), bloodAvailability2.hashCode()); // Should have same hash code
    }
}