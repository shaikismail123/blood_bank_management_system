package com.capstone.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.entity.AdminOperations;
import com.capstone.demo.exception.RequesterNotFoundException;
import com.capstone.demo.service.AdminDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "admin")
@CrossOrigin(value = "*")
public class AdminOperationsController {

	private Logger logger = LoggerFactory.getLogger(AdminOperationsController.class);
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private AdminDetailsServiceImpl adminDetailsServiceImpl;

	/**
	 * This save end point will execute whenever the admin will approve the
	 * requester blood request and here requester details status also change as
	 * approved based on admin
	 */
	@PostMapping(value = "/saveAdminApprovedDetails")
	public ResponseEntity<String> saveAdminDetails(@RequestBody AdminOperations adminOperations) {
		try {
			logger.info("cursor enter in to admin controller for saveAdminDetails ");
			String saveAdminDetails = adminDetailsServiceImpl.saveAdminDetails(adminOperations);
			return saveAdminDetails != null ? ResponseEntity.status(HttpStatus.OK).body(saveAdminDetails)
					: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	/**
	 * If admin want to see the all the donor's list then this end point will
	 * execute
	 */

	/**
	 * If admin want to see the all the donor's list then this end point will
	 * execute
	 * 
	 * @throws RequesterNotFoundException
	 */

	@GetMapping(value = "/getAllDonars")
	public ResponseEntity<List<UserDetailsDto>> getAllDonarsForAdmin() throws RequesterNotFoundException {
		logger.info("cursor enter in to admin controller for get all donar details ");
		List<UserDetailsDto> allDonarsForAdmin = adminDetailsServiceImpl.getAllDonarsForAdmin();
		return allDonarsForAdmin != null ? ResponseEntity.status(HttpStatus.OK).body(allDonarsForAdmin)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

	}
	
	
	@GetMapping(value = "/getAllRequesters")
	public ResponseEntity<List<UserDetailsDto>> getAllRequestersForAdmin() throws RequesterNotFoundException {
		logger.info("cursor enter in to admin controller for get all donar details ");
		List<UserDetailsDto> allDonarsForAdmin = adminDetailsServiceImpl.getAllRequestersForAdmin();
		return allDonarsForAdmin != null ? ResponseEntity.status(HttpStatus.OK).body(allDonarsForAdmin)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

	}

	/**
	 * Admin will approve the requests for that this end point it will give all the
	 * requests
	 * 
	 * @throws RequesterNotFoundException
	 */
	@GetMapping(value = "/getAllRequesterForApproval")
	public ResponseEntity<List<RequesterDetailsDto>> getAllRequesterForApproval() throws RequesterNotFoundException {
		logger.info("cursor enter in to admin controller for get all reqeuster details ");
		List<RequesterDetailsDto> allRequesterForApproval = adminDetailsServiceImpl.getAllRequesterForApproval();
		return allRequesterForApproval != null ? ResponseEntity.status(HttpStatus.OK).body(allRequesterForApproval)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

	}

	// get both requeste and donar end point

	// delete the requester or donar end point

	// for updation perpose we can reuse the same userDetails methods only (status)
}
