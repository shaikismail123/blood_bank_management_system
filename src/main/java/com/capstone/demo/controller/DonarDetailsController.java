package com.capstone.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.service.MyUserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "donar")
@CrossOrigin(value = "*")
public class DonarDetailsController {

	private Logger logger = LoggerFactory.getLogger(DonarDetailsController.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MyUserDetailsServiceImpl myUserDetailsServiceImpl;
	@Autowired
	private DefaultValues defaultValues;

	// for updating the donar data
	@PutMapping("/updatedoner")
	public ResponseEntity<String> updateDonerDetails(@RequestBody UserDetailsDto userDetailsDto) {
		try {
			logger.info("Cursor enter in to Doner updation method inside controller");
			boolean insertUserDetails = myUserDetailsServiceImpl.insertUserDetails(userDetailsDto);
			return insertUserDetails
					? ResponseEntity.status(HttpStatus.OK).body(defaultValues.getMessage().get(AppConstants.UPDATE))
					: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (Exception e) {
			logger.info("something went wrong while updating the doner" + e.getMessage());
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(defaultValues.getMessage().get(AppConstants.FAIL));
	}

	// for deleting the doner data by id
	@PutMapping("/deletedoner/{id}")
	public ResponseEntity<String> deleteDonerDetails(@PathVariable Long id) {
		try {
			logger.info("Cursor enter in to Doner updation method inside controller");
			boolean deleteDonerDetails = myUserDetailsServiceImpl.deleteDonerDetails(id);
			return deleteDonerDetails
					? ResponseEntity.status(HttpStatus.OK).body(defaultValues.getMessage().get(AppConstants.DELETE))
					: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		} catch (Exception e) {
			logger.info("something went wrong while updating the doner" + e.getMessage());
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(defaultValues.getMessage().get(AppConstants.FAIL));
	}

}
