package com.capstone.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.dto.UsersDto;
import com.capstone.demo.emailSender.EmailSender;
import com.capstone.demo.entity.MyUserDetails;
import com.capstone.demo.entity.RequesterDetails;
import com.capstone.demo.exception.RequesterNotFoundException;
import com.capstone.demo.exception.UserNotFoundException;
import com.capstone.demo.repository.AdminDetailsRepository;
import com.capstone.demo.repository.MyUserDetailsRepository;
import com.capstone.demo.repository.RequesterDetailsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RequesterDetailsServiceImpl implements RequesterDetailsService {

	private Logger logger = LoggerFactory.getLogger(RequesterDetailsServiceImpl.class);
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private RequesterDetailsRepository requesterDetailsRepository;

	@Autowired
	private MyUserDetailsRepository myUserDetailsRepository;

	@Autowired
	private AdminDetailsRepository adminDetailsRepository;

	@Autowired
	DefaultValues defaultValues;

	@Value("${subject.formail}")
	private String subject;

	@Value("${body.foremail}")
	private String body;

	@Autowired
	private EmailSender emailSender;

	@Override
	public boolean saveRequesterDetails(RequesterDetailsDto requesterDetailsDto) {
		try {
			logger.info("Curser enter in to Save Request detaisl method inside service ");
			requesterDetailsDto.setRequiredDate(String.valueOf(LocalDate.now()));
			requesterDetailsDto.setStatus(requesterDetailsDto.getStatus().toUpperCase());
			RequesterDetails details = mapper.convertValue(requesterDetailsDto, RequesterDetails.class);
			RequesterDetails requesterDetails = requesterDetailsRepository.save(details);
			if (requesterDetails != null) {
				// Here sending email to admin to know the request and for approve
//				emailSender.sendEmail(myUserDetailsRepository.getAdminEmail(), subject, body);
//				logger.info("Admin mail : " + myUserDetailsRepository.getAdminEmail());
				return true;
			}
		} catch (Exception ex) {
			logger.error("error ",ex.getMessage());
			logger.error("something went wrong while adding reqeuster details ", ex.getMessage());
		}

		return false;
	}

	/***
	 * In this method we first delete the parent dependent row inside the Admin DB
	 * and delete the parent in requester
	 */
	@Override
	public boolean deleteRequestById(Long id) {
		try {
			logger.info("Cursor Enter in to Delete Reqeust by id method inside service ====>  " + id);
			adminDetailsRepository.deleteTheRequesterIdFromAdmin(id);
			requesterDetailsRepository.deleteById(id);
			logger.info(defaultValues.getMessage().get(AppConstants.DELETE));
			return true;
		} catch (Exception ex) {
			logger.error("error ",ex.getMessage());
		}
		return false;
	}

	@Override
	public RequesterDetails getReqeustDetailsById(Long id) throws RequesterNotFoundException {

		logger.info("Cursor Enter in to DgetReqeustDetailsById inside service ====>  " + id);
		Optional<RequesterDetails> byId = requesterDetailsRepository.findById(id);
		if (byId.isPresent()) {
			return byId.get();
		} else {
			throw new RequesterNotFoundException("Requster not found based on this id " + id);
		}

	}

	// if donar want to see how many requests he got for blood donation approval
	@Override
	public List<RequesterDetailsDto> getAllRequestOfDonarForApproving(Long donarId) throws RequesterNotFoundException {

		List<RequesterDetails> byDonarId = requesterDetailsRepository.getDonarDetails(donarId);
		if (byDonarId == null || byDonarId.isEmpty()) {
			throw new RequesterNotFoundException("Requsters not found to send the donar based on this id " + donarId);
		}
		List<RequesterDetailsDto> donarDetails = new ArrayList<>();
		byDonarId.stream().forEach(each -> {
			donarDetails.add(mapper.convertValue(each, RequesterDetailsDto.class));
		});
		return donarDetails;

	}

	@Override
	public boolean updateRequesterDetails(UsersDto usersDto) throws UserNotFoundException {
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
