package com.capstone.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
	// insert user details   we can use same uri for updation also
	public ResponseEntity<String> insertUserDetails(@RequestBody UserDetailsDto userDetailsDto ){
		try {
			logger.info("<==== Cusor enter in to userController method inside ====>");
			boolean insertUserDetails = userDetailsServiceImpl.insertUserDetails(userDetailsDto);
			return null;
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	

	
}
