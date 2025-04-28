package com.capstone.demo.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DonarDetailsTest {

	@Test
	public void testDonarDetailsConstructorAndGettersSetters() {
		// Arrange
		MyUserDetails requester = new MyUserDetails(); // Assuming you have a way to create this
		MyUserDetails donar = new MyUserDetails(); // Assuming you have a way to create this
		LocalDateTime donationDateTime = LocalDateTime.now();

		DonarDetails donarDetails = new DonarDetails();
		donarDetails.setId(1L);
		donarDetails.setRequesterId(requester);
		donarDetails.setDonarId(donar);
		donarDetails.setPatientId("P123");
		donarDetails.setDonationDateTime(String.valueOf(donationDateTime));
		donarDetails.setGlucoseLevel("5.5");
		donarDetails.setNotes("First donation");
		donarDetails.setDonarApprovedstatus("APPROVED");

		// Act & Assert
		assertEquals(1L, donarDetails.getId());
		assertEquals(requester, donarDetails.getRequesterId());
		assertEquals(donar, donarDetails.getDonarId());
		assertEquals("P123", donarDetails.getPatientId());
		assertEquals(donationDateTime, donarDetails.getDonationDateTime());
		assertEquals(5.5, donarDetails.getGlucoseLevel());
		assertEquals("First donation", donarDetails.getNotes());
		assertEquals("APPROVED", donarDetails.getDonarApprovedstatus());
	}

//	@Test
//	public void testDonarDetailsAllArgsConstructor() {
//		// Arrange
//		MyUserDetails requester = new MyUserDetails(); // Assuming you have a way to create this
//		MyUserDetails donar = new MyUserDetails(); // Assuming you have a way to create this
//		LocalDateTime donationDateTime = LocalDateTime.now();
//
//		// Act
//		DonarDetails donarDetails = new DonarDetails(1L, requester, donar, "P123", donationDateTime, "5.5",
//				"First donation", "APPROVED");
//
//		// Assert
//		assertEquals(1L, donarDetails.getId());
//		assertEquals(requester, donarDetails.getRequesterId());
//		assertEquals(donar, donarDetails.getDonarId());
//		assertEquals("P123", donarDetails.getPatientId());
//		assertEquals(donationDateTime, donarDetails.getDonationDateTime());
//		assertEquals(5.5, donarDetails.getGlucoseLevel());
//		assertEquals("First donation", donarDetails.getNotes());
//		assertEquals("APPROVED", donarDetails.getDonarApprovedstatus());
//	}
}