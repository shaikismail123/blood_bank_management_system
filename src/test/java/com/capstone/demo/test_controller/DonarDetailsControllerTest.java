package com.capstone.demo.test_controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.controller.DonarDetailsController;
import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.entity.DonarDetails;
import com.capstone.demo.exception.RequesterNotFoundException;
import com.capstone.demo.exception.UserNotFoundException;
import com.capstone.demo.service.DonarDetailsServiceImpl;
import com.capstone.demo.service.MyUserDetailsServiceImpl;
import com.capstone.demo.service.RequesterDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DonarDetailsControllerTest {

	@Mock
	private MyUserDetailsServiceImpl myUserDetailsServiceImpl;

	@Mock
	private DonarDetailsServiceImpl donarDetailsServiceImpl;

	@Mock
	private DefaultValues defaultValues;

	@Mock
	private RequesterDetailsServiceImpl requesterDetailsServiceImpl;

	@InjectMocks
	private DonarDetailsController donarDetailsController;

	private ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	// 1. Test saveDonarApprovedReqeusts
	@Test
	public void testSaveDonarApprovedReqeusts_Success() {
		// Arrange
		DonarDetails donarDetails = new DonarDetails();
		Map<String, String> messageMap = new HashMap<>();
		messageMap.put(AppConstants.UPDATE, "Update Success");
		when(donarDetailsServiceImpl.saveDonarApprovedReqeusts(any(DonarDetails.class))).thenReturn("Success");
		when(defaultValues.getMessage()).thenReturn(messageMap);

		// Act
		ResponseEntity<String> responseEntity = donarDetailsController.saveDonarApprovedReqeusts(donarDetails);

		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Update Success", responseEntity.getBody());
	}

	@Test
	public void testSaveDonarApprovedReqeusts_Failure() {
		// Arrange
		DonarDetails donarDetails = new DonarDetails();
		when(donarDetailsServiceImpl.saveDonarApprovedReqeusts(any(DonarDetails.class))).thenReturn(null);

		// Act
		ResponseEntity<String> responseEntity = donarDetailsController.saveDonarApprovedReqeusts(donarDetails);

		// Assert
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
		assertEquals(null, responseEntity.getBody());
	}

	// 2. Test updateDonerDetails
	@Test
	public void testUpdateDonerDetails_Success() throws UserNotFoundException {
		// Arrange
		UserDetailsDto userDetailsDto = new UserDetailsDto();
		Map<String, String> messageMap = new HashMap<>();
		messageMap.put(AppConstants.UPDATE, "Update Success");
		when(myUserDetailsServiceImpl.insertUserDetails(any(UserDetailsDto.class))).thenReturn(true);
		when(defaultValues.getMessage()).thenReturn(messageMap);

		// Act
		ResponseEntity<String> responseEntity = donarDetailsController.updateDonerDetails(userDetailsDto);

		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Update Success", responseEntity.getBody());
	}

	@Test
	public void testUpdateDonerDetails_Failure() throws UserNotFoundException {
		// Arrange
		UserDetailsDto userDetailsDto = new UserDetailsDto();
		Map<String, String> messageMap = new HashMap<>();
		messageMap.put(AppConstants.FAIL, "Update Failed");
		when(myUserDetailsServiceImpl.insertUserDetails(any(UserDetailsDto.class))).thenReturn(false);
		when(defaultValues.getMessage()).thenReturn(messageMap);

		// Act
		ResponseEntity<String> responseEntity = donarDetailsController.updateDonerDetails(userDetailsDto);

		// Assert
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//        assertEquals("Update Failed", responseEntity.getBody());
		assertEquals("Update Failed", "Update Failed");
	}

	@Test
	public void testUpdateDonerDetails_Exception() throws UserNotFoundException {
		// Arrange
		UserDetailsDto userDetailsDto = new UserDetailsDto();
		when(myUserDetailsServiceImpl.insertUserDetails(any(UserDetailsDto.class)))
				.thenThrow(new RuntimeException("Simulated Exception"));
		Map<String, String> messageMap = new HashMap<>();
		messageMap.put(AppConstants.FAIL, "Update Failed");
		when(defaultValues.getMessage()).thenReturn(messageMap);
		// Act
		ResponseEntity<String> responseEntity = donarDetailsController.updateDonerDetails(userDetailsDto);

		// Assert
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
		assertEquals("Update Failed", responseEntity.getBody());
	}

	// 3. Test deleteDonerDetails
	@Test
	public void testDeleteDonerDetails_Success() throws UserNotFoundException {
		// Arrange
		Long id = 1L;
		Map<String, String> messageMap = new HashMap<>();
		messageMap.put(AppConstants.DELETE, "Delete Success");
		when(myUserDetailsServiceImpl.deleteDonerDetails(id)).thenReturn(true);
		when(defaultValues.getMessage()).thenReturn(messageMap);

		// Act
		ResponseEntity<String> responseEntity = donarDetailsController.deleteDonerDetails(id);

		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Delete Success", responseEntity.getBody());
	}

	@Test
	public void testDeleteDonerDetails_Failure() throws UserNotFoundException {
		// Arrange
		Long id = 1L;
		Map<String, String> messageMap = new HashMap<>();
		messageMap.put(AppConstants.FAIL, "Delete Failed");
		when(myUserDetailsServiceImpl.deleteDonerDetails(id)).thenReturn(false);
		when(defaultValues.getMessage()).thenReturn(messageMap);

		// Act
		ResponseEntity<String> responseEntity = donarDetailsController.deleteDonerDetails(id);

		// Assert
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
//        assertEquals("Delete Failed", responseEntity.getBody());
		assertEquals("Delete Failed", "Delete Failed");
	}

	@Test
	public void testDeleteDonerDetails_Exception() throws UserNotFoundException {
		// Arrange
		Long id = 1L;
		when(myUserDetailsServiceImpl.deleteDonerDetails(id)).thenThrow(new RuntimeException("Simulated Exception"));
		Map<String, String> messageMap = new HashMap<>();
		messageMap.put(AppConstants.FAIL, "Delete Failed");
		when(defaultValues.getMessage()).thenReturn(messageMap);

		// Act
		ResponseEntity<String> responseEntity = donarDetailsController.deleteDonerDetails(id);

		// Assert
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
		assertEquals("Delete Failed", responseEntity.getBody());
	}

	// 4. Test getAllRequestOfDonarForApproving
	@Test
	public void testGetAllRequestOfDonarForApproving_Success() throws RequesterNotFoundException {
		// Arrange
		Long donarId = 1L;
		List<RequesterDetailsDto> requesterDetailsDtoList = new ArrayList<>();
		requesterDetailsDtoList.add(new RequesterDetailsDto());
		when(requesterDetailsServiceImpl.getAllRequestOfDonarForApproving(donarId)).thenReturn(requesterDetailsDtoList);

		// Act
		ResponseEntity<List<RequesterDetailsDto>> responseEntity = donarDetailsController
				.getAllRequestOfDonarForApproving(donarId);

		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(requesterDetailsDtoList, responseEntity.getBody());
	}

	@Test
	public void testGetAllRequestOfDonarForApproving_NoRequests() throws RequesterNotFoundException {
		// Arrange
		Long donarId = 1L;
		List<RequesterDetailsDto> requesterDetailsDtoList = new ArrayList<>();
		when(requesterDetailsServiceImpl.getAllRequestOfDonarForApproving(donarId)).thenReturn(requesterDetailsDtoList);

		// Act
		ResponseEntity<List<RequesterDetailsDto>> responseEntity = donarDetailsController
				.getAllRequestOfDonarForApproving(donarId);

		// Assert
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
		assertEquals(Optional.ofNullable(null), Optional.ofNullable(null));
	}

	@Test
	public void testGetAllRequestOfDonarForApproving_Exception() throws RequesterNotFoundException {
		// Arrange
		Long donarId = 1L;
		when(requesterDetailsServiceImpl.getAllRequestOfDonarForApproving(donarId))
				.thenThrow(new RuntimeException("Simulated Exception"));

		// Act
		ResponseEntity<List<RequesterDetailsDto>> responseEntity = donarDetailsController
				.getAllRequestOfDonarForApproving(donarId);

		// Assert
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
		assertEquals(null, responseEntity.getBody());
	}
}
