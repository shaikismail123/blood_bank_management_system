package com.capstone.demo.service;

import java.util.Collections;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.entity.MyUserDetails;
import com.capstone.demo.exception.UserNotFoundException;
import com.capstone.demo.repository.MyUserDetailsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService, UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(MyUserDetailsServiceImpl.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MyUserDetailsRepository userDetailsRepository;

	@Autowired
	private BloodAvailabilityServiceImpl bloodAvailabilityServiceImpl;
	
	@Autowired
	PasswordEncoder pwdEncoder;

	@Override
	public boolean insertUserDetails(UserDetailsDto userDetailsDto) {
		try {
			logger.info("Service method is invoking " + mapper.writeValueAsString(userDetailsDto));
			
			String encodedPwd = pwdEncoder.encode(userDetailsDto.getPasswordHash());
			userDetailsDto.setPasswordHash(encodedPwd);
			if (userDetailsDto.getUserType().equalsIgnoreCase("DONAR")) {
				logger.info("<================= blood packets ading method is invoke  ============>");
				// This method is for adding blood count form the donor
				bloodAvailabilityServiceImpl.addingBloodCountFromDonars(userDetailsDto);
			}
			// here we are converting the values from DTO class to entity class
			MyUserDetails details = mapper.convertValue(userDetailsDto, MyUserDetails.class);
			// if the user will save it will return true other wise false
			return userDetailsRepository.save(details).getUserId() != null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public UserDetailsDto userDetailsById(Long id) throws UserNotFoundException {
		logger.info("Cursor Enter in to getUserDetailsById inside service ====>  " + id);
		Optional<MyUserDetails> byId = userDetailsRepository.findById(id);

		if (byId.isPresent()) {
			UserDetailsDto dto = mapper.convertValue(byId.get(), UserDetailsDto.class);
			return dto;
		} else {
			throw new UserNotFoundException("User Not Found With this ID " + id);
		}
	}

	@Override
	public boolean deleteDonerDetails(Long id) throws UserNotFoundException {
		logger.info("Cursor Enter in to Delete Reqeust by id method inside service ====>  " + id);
		Optional<MyUserDetails> byId = userDetailsRepository.findById(id);
		if (byId.isPresent()) {
			userDetailsRepository.deleteById(id);
			return true;
		} else {
			throw new UserNotFoundException("This Donar id " + id + " is not available please check once...! ");
		}

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		MyUserDetails c = userDetailsRepository.findByEmail(username);

		return new User(c.getEmail(), c.getPasswordHash(), Collections.emptyList());

	}

}
