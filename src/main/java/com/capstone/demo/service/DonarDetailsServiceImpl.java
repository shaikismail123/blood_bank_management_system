package com.capstone.demo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.dto.UsersDto;
import com.capstone.demo.entity.DonarDetails;
import com.capstone.demo.entity.MyUserDetails;
import com.capstone.demo.exception.UserNotFoundException;
import com.capstone.demo.repository.DonarDetailsRepository;
import com.capstone.demo.repository.MyUserDetailsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DonarDetailsServiceImpl implements DonarDetailsService {

	private Logger logger = LoggerFactory.getLogger(DonarDetailsServiceImpl.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	MyUserDetailsRepository myUserDetailsRepository;

	@Autowired
	private DonarDetailsRepository donarDetailsRepository;

	@Autowired
	DefaultValues defaultValues;

	@Override
	public String saveDonarApprovedReqeusts(DonarDetails donarDetails) {
		try {
			return donarDetailsRepository.save(donarDetails).getId() != null
					? defaultValues.getMessage().get(AppConstants.SUCCESS)
					: defaultValues.getMessage().get(AppConstants.FAIL);
		} catch (Exception ex) {
			logger.error("error ", ex.getMessage());
		}
		return null;
	}

	@Override
	public boolean updateDonarDetails(UsersDto usersDto) throws UserNotFoundException {
		logger.info("Service method is invoking for update the Requester  ");
		Optional<MyUserDetails> myUserDetails = myUserDetailsRepository.findById(usersDto.getUserId());
		if (myUserDetails.isPresent()) {
			// here we are converting the values from DTO class to entity class
			MyUserDetails myUserDetails2 = myUserDetails.get();
			myUserDetails2.setFirstName(usersDto.getFirstName());
			myUserDetails2.setLastName(usersDto.getLastName());
			myUserDetails2.setCity(usersDto.getCity());
			myUserDetails2.setBloodGroup(usersDto.getBloodGroup());
			myUserDetails2.setPhoneNumber(usersDto.getPhoneNumber());
			// if the user will save it will return true other wise false
			return myUserDetailsRepository.save(myUserDetails2).getUserId() != null;
		} else {
			throw new UserNotFoundException(
					"Id is not availble for updating the Donar please check the User Id " + usersDto.getUserId());
		}

	}

}
