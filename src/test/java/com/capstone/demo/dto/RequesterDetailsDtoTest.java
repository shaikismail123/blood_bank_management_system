package com.capstone.demo.dto;

import com.capstone.demo.entity.MyUserDetails;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequesterDetailsDtoTest {
    
    @Test
    public void testConstructorAndGetters() {
        MyUserDetails requester = new MyUserDetails(); // Create a mock or real instance if needed
        MyUserDetails donor = new MyUserDetails(); // Create a mock or real instance if needed
        RequesterDetailsDto dto = new RequesterDetailsDto(1L, requester, donor, "John Doe", "A+", "New York",
                "Dr. Smith", "City Hospital", "123 Main St", "2025-04-25", "Jane Doe", "1234567890",
                "jane@example.com", "Need blood urgently", "PENDING");
        
        assertEquals(1L, dto.getId());
        assertEquals(requester, dto.getRequesterId());
        assertEquals(donor, dto.getDonarId());
        assertEquals("John Doe", dto.getPatientName());
        assertEquals("A+", dto.getRequiredBloodGroup());
        assertEquals("New York", dto.getCity());
        assertEquals("Dr. Smith", dto.getDoctorName());
        assertEquals("City Hospital", dto.getHospitalName());
        assertEquals("123 Main St", dto.getHospitalAddress());
        assertEquals("2025-04-25", dto.getRequiredDate());
        assertEquals("Jane Doe", dto.getContactName());
        assertEquals("1234567890", dto.getContactNumber());
        assertEquals("jane@example.com", dto.getContactEmail());
        assertEquals("Need blood urgently", dto.getMessage());
        assertEquals("PENDING", dto.getStatus());
    }
    
    @Test
    public void testSetters() {
        RequesterDetailsDto dto = new RequesterDetailsDto();
        
        dto.setId(1L);
        assertEquals(1L, dto.getId());
        
        MyUserDetails requester = new MyUserDetails(); // Create a mock or real instance if needed
        dto.setRequesterId(requester);
        assertEquals(requester, dto.getRequesterId());
        
        MyUserDetails donor = new MyUserDetails(); // Create a mock or real instance if needed
        dto.setDonarId(donor);
        assertEquals(donor, dto.getDonarId());
        
        dto.setPatientName("John Doe");
        assertEquals("John Doe", dto.getPatientName());
        
        dto.setRequiredBloodGroup("A+");
        assertEquals("A+", dto.getRequiredBloodGroup());
        
        dto.setCity("New York");
        assertEquals("New York", dto.getCity());
        
        dto.setDoctorName("Dr. Smith");
        assertEquals("Dr. Smith", dto.getDoctorName());
        
        dto.setHospitalName("City Hospital");
        assertEquals("City Hospital", dto.getHospitalName());
        
        dto.setHospitalAddress("123 Main St");
        assertEquals("123 Main St", dto.getHospitalAddress());
        
        dto.setRequiredDate("2025-04-25");
        assertEquals("2025-04-25", dto.getRequiredDate());
        
        dto.setContactName("Jane Doe");
        assertEquals("Jane Doe", dto.getContactName());
        
        dto.setContactNumber("1234567890");
        assertEquals("1234567890", dto.getContactNumber());
        
        dto.setContactEmail("jane@example.com");
        assertEquals("jane@example.com", dto.getContactEmail());
        
        dto.setMessage("Need blood urgently");
        assertEquals("Need blood urgently", dto.getMessage());
        
        dto.setStatus("PENDING");
        assertEquals("PENDING", dto.getStatus());
    }
    
    @Test
    public void testToString() {
        MyUserDetails requester = new MyUserDetails(); // Create a mock or real instance if needed
        MyUserDetails donor = new MyUserDetails(); // Create a mock or real instance if needed
        RequesterDetailsDto dto = new RequesterDetailsDto(1L, requester, donor, "John Doe", "A+", "New York",
                "Dr. Smith", "City Hospital", "123 Main St", "2025-04-25", "Jane Doe", "1234567890",
                "jane@example.com", "Need blood urgently", "PENDING");
        
        String expectedString = "RequesterDetailsDto [id=1, requesterId=" + requester + ", donarId=" + donor
                + ", patientName=John Doe, requiredBloodGroup=A+, city=New York, doctorName=Dr. Smith, "
                + "hospitalName=City Hospital, hospitalAddress=123 Main St, requiredDate=2025-04-25, "
                + "contactName=Jane Doe, contactNumber=1234567890, contactEmail=jane@example.com, "
                + "message=Need blood urgently, status=PENDING]";
        
        assertEquals(expectedString, dto.toString());
    }
}