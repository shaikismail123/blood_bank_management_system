package com.capstone.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/userDetails")
@RestController
public class UserDetailsController {

	private Logger logger = LoggerFactory.getLogger(UserDetailsController.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private DefaultValues defaultValues;

	// insert user details we can use same uri for updation also
	@PostMapping(value = "/insertUserDetails")
	public ResponseEntity<String> insertUserDetails(@RequestBody UserDetailsDto userDetailsDto) {
		try {
			logger.info("<==== Cusor enter in to userController method inside ====>  "
					+ mapper.writeValueAsString(userDetailsDto));
			boolean insertUserDetails = userDetailsServiceImpl.insertUserDetails(userDetailsDto);
			if (insertUserDetails) {
				return ResponseEntity.status(HttpStatus.OK).body(defaultValues.getMessage().get(AppConstants.SUCCESS));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

}
