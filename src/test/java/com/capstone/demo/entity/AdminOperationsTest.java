package com.capstone.demo.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdminOperationsTest {
    
    @Test
    public void testAdminOperationsConstructorAndGettersSetters() {
        // Arrange
        RequesterDetails requesterDetails = new RequesterDetails(); // Assuming you have a constructor or a way to create this
        requesterDetails.setId(1L); // Set an ID for the requester
        
        AdminOperations adminOperations = new AdminOperations();
        adminOperations.setAdminId(1L);
        adminOperations.setRequesterDetails(requesterDetails);
        adminOperations.setApprovalStatus("Approved");
        adminOperations.setInsertableDate(LocalDate.now());
        adminOperations.setUpdatedDate(LocalDate.now());
        
        // Act & Assert
        assertEquals(1L, adminOperations.getAdminId());
        assertEquals(requesterDetails, adminOperations.getRequesterDetails());
        assertEquals("Approved", adminOperations.getApprovalStatus());
        assertNotNull(adminOperations.getInsertableDate());
        assertNotNull(adminOperations.getUpdatedDate());
    }
    
    @Test
    public void testAdminOperationsAllArgsConstructor() {
        // Arrange
        RequesterDetails requesterDetails = new RequesterDetails();
        requesterDetails.setId(1L);
        
        // Act
        AdminOperations adminOperations = new AdminOperations(1L, requesterDetails, "Approved", LocalDate.now(), LocalDate.now());
        
        // Assert
        assertEquals(1L, adminOperations.getAdminId());
        assertEquals(requesterDetails, adminOperations.getRequesterDetails());
        assertEquals("Approved", adminOperations.getApprovalStatus());
        assertNotNull(adminOperations.getInsertableDate());
        assertNotNull(adminOperations.getUpdatedDate());
    }
}