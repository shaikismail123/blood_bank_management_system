package com.capstone.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.entity.UserDetails;
import com.capstone.demo.repository.UserDetailsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserDetailsServiceImpl {

	private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public boolean insertUserDetails(UserDetailsDto userDetailsDto) {
		try {
			logger.info("Service method is invoking " + mapper.writeValueAsString(userDetailsDto));
			UserDetails details = mapper.convertValue(userDetailsDto, UserDetails.class);
			return userDetailsRepository.save(details).getUserId() != null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

}
