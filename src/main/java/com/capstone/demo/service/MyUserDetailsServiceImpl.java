package com.capstone.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

import jakarta.transaction.Transactional;

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
			userDetailsDto.setUserType(userDetailsDto.getUserType().toUpperCase());
			userDetailsDto.setCity(userDetailsDto.getCity().toUpperCase());
			userDetailsDto.setBloodGroup(userDetailsDto.getBloodGroup().toUpperCase());
			String encodedPwd = pwdEncoder.encode(userDetailsDto.getPasswordHash());
			userDetailsDto.setPasswordHash(encodedPwd);
			if (userDetailsDto.getUserType().equalsIgnoreCase("DONAR")) {
				logger.info("<================= blood packets ading method is invoke  ============>");
				// This method is for adding blood count from the donor
				bloodAvailabilityServiceImpl.addingBloodCountFromDonars(userDetailsDto);
			}
			// here we are converting the values from DTO class to entity class
			MyUserDetails details = mapper.convertValue(userDetailsDto, MyUserDetails.class);
			// if the user will save it will return true other wise false
			return userDetailsRepository.save(details).getUserId() != null;
		} catch (Exception ex) {
			logger.error("error ", ex.getMessage());
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
	@Transactional
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

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		MyUserDetails c = userDetailsRepository.findByEmail(username);
//
//		return new User(c.getEmail(), c.getPasswordHash(), Collections.emptyList());
//
//	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		MyUserDetails c = userDetailsRepository.findByEmail(username);
		if (c == null) {
			throw new UsernameNotFoundException("User not found");
		}
//		return new User(c.getEmail(), c.getPasswordHash(), Collections.emptyList());
		List<GrantedAuthority> authorities = new ArrayList<>();
		switch (c.getUserType()) {
		case "DONAR":
			authorities.add(new SimpleGrantedAuthority("DONAR"));
			break;
		case "REQUESTER":
			authorities.add(new SimpleGrantedAuthority("REQUESTER"));
			break;
		case "ADMIN":
			authorities.add(new SimpleGrantedAuthority("ADMIN"));
			break;
		}
		return new User(c.getEmail(), c.getPasswordHash(), authorities);

	}

	public List<MyUserDetails> getAlldonarsForMakingRequest() throws UserNotFoundException {
		logger.info("Cursor Enter in to getAlldonarsForMakingRequest method inside service ====>  ");
		List<MyUserDetails> alldonarsForMakingRequest = userDetailsRepository.getAlldonarsForMakingRequest();
		if (alldonarsForMakingRequest == null) {
			throw new UsernameNotFoundException("Donar are not available in side the DB .....!");
		} else {
			return alldonarsForMakingRequest;
		}

	}

}
