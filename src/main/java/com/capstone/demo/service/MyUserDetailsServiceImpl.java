package com.capstone.demo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.entity.MyUserDetails;
import com.capstone.demo.exception.UserNotFoundException;
import com.capstone.demo.repository.MyUserDetailsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {

	private Logger logger = LoggerFactory.getLogger(MyUserDetailsServiceImpl.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MyUserDetailsRepository userDetailsRepository;

	@Override
	public boolean insertUserDetails(UserDetailsDto userDetailsDto) {
		try {
			logger.info("Service method is invoking " + mapper.writeValueAsString(userDetailsDto));
			if (userDetailsDto.getUserId() == null) {
				userDetailsDto.setBloodStatusAddedOrNot("NO");
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
	public UserDetailsDto userDetailsById(Long id) {
		try {
			logger.info("Cursor Enter in to getUserDetailsById inside service ====>  " + id);
			Optional<MyUserDetails> byId = userDetailsRepository.findById(id);

			if (byId.isPresent()) {
				UserDetailsDto dto = mapper.convertValue(byId.get(), UserDetailsDto.class);
				return dto;
			} else {
				throw new UserNotFoundException("User Not Found With this ID " + id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("user not found exception ", ex.getMessage());
		}
		return null;
	}

	@Override
	public boolean deleteDonerDetails(Long id) {
		try {
			logger.info("Cursor Enter in to Delete Reqeust by id method inside service ====>  " + id);
			userDetailsRepository.deleteById(id);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

}
