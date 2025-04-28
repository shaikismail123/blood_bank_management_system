package com.capstone.demo.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequesterDetailsTest {
    
    @Test
    public void testRequesterDetailsConstructorAndGettersSetters() {
        // Arrange
        MyUserDetails requester = new MyUserDetails(); // Assuming you have a way to create this
        MyUserDetails donor = new MyUserDetails(); // Assuming you have a way to create this
        
        RequesterDetails requesterDetails = new RequesterDetails();
        requesterDetails.setId(1L);
        requesterDetails.setRequesterId(requester);
        requesterDetails.setDonarId(donor);
        requesterDetails.setPatientName("John Doe");
        requesterDetails.setRequiredBloodGroup("A+");
        requesterDetails.setCity("New York");
        requesterDetails.setDoctorName("Dr. Smith");
        requesterDetails.setHospitalName("General Hospital");
        requesterDetails.setHospitalAddress("123 Main St");
        requesterDetails.setRequiredDate("2025-04-30");
        requesterDetails.setContactName("Jane Doe");
        requesterDetails.setContactNumber("1234567890");
        requesterDetails.setContactEmail("jane.doe@example.com");
        requesterDetails.setMessage("Need blood urgently");
        requesterDetails.setStatus("PENDING");
        
        // Act & Assert
        assertEquals(1L, requesterDetails.getId());
        assertEquals(requester, requesterDetails.getRequesterId());
        assertEquals(donor, requesterDetails.getDonarId());
        assertEquals("John Doe", requesterDetails.getPatientName());
        assertEquals("A+", requesterDetails.getRequiredBloodGroup());
        assertEquals("New York", requesterDetails.getCity());
        assertEquals("Dr. Smith", requesterDetails.getDoctorName());
        assertEquals("General Hospital", requesterDetails.getHospitalName());
        assertEquals("123 Main St", requesterDetails.getHospitalAddress());
        assertEquals("2025-04-30", requesterDetails.getRequiredDate());
        assertEquals("Jane Doe", requesterDetails.getContactName());
        assertEquals("1234567890", requesterDetails.getContactNumber());
        assertEquals("jane.doe@example.com", requesterDetails.getContactEmail());
        assertEquals("Need blood urgently", requesterDetails.getMessage());
        assertEquals("PENDING", requesterDetails.getStatus());
    }
    
    @Test
    public void testRequesterDetailsAllArgsConstructor() {
        // Arrange
        MyUserDetails requester = new MyUserDetails(); // Assuming you have a way to create this
        MyUserDetails donor = new MyUserDetails(); // Assuming you have a way to create this
        
        RequesterDetails requesterDetails = new RequesterDetails(1L, requester, donor, "John Doe", "A+", "New York",
                "Dr. Smith", "General Hospital", "123 Main St", "2025-04-30", "Jane Doe", "1234567890",
                "jane.doe@example.com", "Need blood urgently", "PENDING");
        
        // Act & Assert
        assertEquals(1L, requesterDetails.getId());
        assertEquals(requester, requesterDetails.getRequesterId());
        assertEquals(donor, requesterDetails.getDonarId());
        assertEquals("John Doe", requesterDetails.getPatientName());
        assertEquals("A+", requesterDetails.getRequiredBloodGroup());
        assertEquals("New York", requesterDetails.getCity());
        assertEquals("Dr. Smith", requesterDetails.getDoctorName());
        assertEquals("General Hospital", requesterDetails.getHospitalName());
        assertEquals("123 Main St", requesterDetails.getHospitalAddress());
        assertEquals("2025-04-30", requesterDetails.getRequiredDate());
        assertEquals("Jane Doe", requesterDetails.getContactName());
        assertEquals("1234567890", requesterDetails.getContactNumber());
        assertEquals("jane.doe@example.com", requesterDetails.getContactEmail());
        assertEquals("Need blood urgently", requesterDetails.getMessage());
        assertEquals("PENDING", requesterDetails.getStatus());
    }
}