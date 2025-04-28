package com.capstone.demo.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.dto.UsersDto;
import com.capstone.demo.exception.UserNotFoundException;
import com.capstone.demo.service.DonarDetailsServiceImpl;
import com.capstone.demo.service.JwtService;
import com.capstone.demo.service.MyUserDetailsServiceImpl;
import com.capstone.demo.service.RequesterDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/userDetails")
@RestController
//@CrossOrigin(value = "*")
public class UserDetailsController {

	private Logger logger = LoggerFactory.getLogger(UserDetailsController.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtService jwt;

	@Autowired
	private MyUserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private DefaultValues defaultValues;

	@Autowired
	RequesterDetailsServiceImpl requesterDetailsServiceImpl;

	@Autowired
	DonarDetailsServiceImpl donarDetailsServiceImpl;

	// insert user details we can use same uri for updation also
	@PostMapping(value = "/registerUser")
	public ResponseEntity<String> insertUserDetails(@RequestBody UserDetailsDto userDetailsDto) {
		try {
			logger.info("<==== Cusor enter in to userController method inside ====>  "
					+ mapper.writeValueAsString(userDetailsDto));
			boolean insertUserDetails = userDetailsServiceImpl.insertUserDetails(userDetailsDto);
			if (insertUserDetails) {
				return ResponseEntity.status(HttpStatus.OK).body(defaultValues.getMessage().get(AppConstants.SUCCESS));
			}

		} catch (Exception ex) {
			logger.error("error ",ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginCheck(@RequestParam Map<String, String> allParams) {
		logger.info("eamil : " + allParams.get("email") + " password :" + allParams.get("password"));
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(allParams.get("email"),
				allParams.get("password"));
		try {
			Authentication authenticate = authManager.authenticate(token);
			if (authenticate.isAuthenticated()) {
				String jwtToken = jwt.generateToken(allParams.get("email"));
				return new ResponseEntity<>(jwtToken, HttpStatus.OK);
			}

		} catch (Exception ex) {
			logger.error("error ",ex.getMessage());
		}
		return new ResponseEntity<String>(defaultValues.getMessage().get(AppConstants.INVALID), HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/getUserDetailsById/{id}")
	public ResponseEntity<UserDetailsDto> getUserDetailsById(@PathVariable Long id) throws UserNotFoundException {
		logger.info("Cusor enter in to getUserDetailsById controller " + id);
		UserDetailsDto userDetails = userDetailsServiceImpl.userDetailsById(id);
		return userDetails != null ? ResponseEntity.status(HttpStatus.OK).body(userDetails)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	// for updating the donar person details donar can chage his personal details
	// only but not id , username, password
	@PutMapping("/updateRequester")
	public ResponseEntity<String> updateRequesterDetails(@RequestBody UsersDto UsersDto) throws UserNotFoundException {
		logger.info("Cursor enter in to Doner updation method inside controller");
		boolean insertUserDetails = requesterDetailsServiceImpl.updateRequesterDetails(UsersDto);
		return insertUserDetails
				? ResponseEntity.status(HttpStatus.OK).body(defaultValues.getMessage().get(AppConstants.UPDATE))
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

	}

	// for updating the donar person details donar can chage his personal details
	// only but not id , username, password
	@PutMapping("/updateDoner")
	public ResponseEntity<String> updateDonerDetails(@RequestBody UsersDto usersDto) throws UserNotFoundException {
		logger.info("Cursor enter in to Doner updation method inside controller");
		boolean insertUserDetails = donarDetailsServiceImpl.updateDonarDetails(usersDto);
		return insertUserDetails
				? ResponseEntity.status(HttpStatus.OK).body(defaultValues.getMessage().get(AppConstants.UPDATE))
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

	}

}
