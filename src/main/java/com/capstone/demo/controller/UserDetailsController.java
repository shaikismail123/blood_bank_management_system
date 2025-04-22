package com.capstone.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.entity.MyUserDetails;
import com.capstone.demo.entity.RequesterDetails;
import com.capstone.demo.exception.UserNotFoundException;
import com.capstone.demo.service.MyUserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/userDetails")
@RestController
@CrossOrigin(value = "*")
public class UserDetailsController {

	private Logger logger = LoggerFactory.getLogger(UserDetailsController.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MyUserDetailsServiceImpl userDetailsServiceImpl;

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

	@GetMapping(value = "/getUserDetailsById/{id}")
	public ResponseEntity<UserDetailsDto> getUserDetailsById(@PathVariable Long id) throws UserNotFoundException {
		logger.info("Cusor enter in to getUserDetailsById controller " + id);
		UserDetailsDto userDetails = userDetailsServiceImpl.userDetailsById(id);
		return userDetails != null ? ResponseEntity.status(HttpStatus.OK).body(userDetails)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

}
