package com.capstone.demo.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.dto.UsersDto;
import com.capstone.demo.entity.MyUserDetails;
import com.capstone.demo.entity.RequesterDetails;
import com.capstone.demo.exception.RequesterNotFoundException;
import com.capstone.demo.exception.UserNotFoundException;
import com.capstone.demo.repository.MyUserDetailsRepository;
import com.capstone.demo.service.MyUserDetailsServiceImpl;
import com.capstone.demo.service.RequesterDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/requester")
//@CrossOrigin(value = "*")
public class RequesterDetailsController {

	private Logger logger = LoggerFactory.getLogger(RequesterDetailsController.class);
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private RequesterDetailsServiceImpl requesterDetailsServiceImpl;

	@Autowired
	DefaultValues defaultValues;

	@Autowired
	MyUserDetailsServiceImpl myUserDetailsServiceImpl;

	@PostMapping(value = "/saveRequesterDetails")
	public ResponseEntity<String> saveRequesterDetails(@RequestBody RequesterDetailsDto requesterDetailsDto) {
		try {
			logger.info("Cusor enter in to save Requester details method inside "
					+ mapper.writeValueAsString(requesterDetailsDto));
			boolean saveRequesterDetails = requesterDetailsServiceImpl.saveRequesterDetails(requesterDetailsDto);
			return saveRequesterDetails
					? ResponseEntity.status(HttpStatus.OK).body(defaultValues.getMessage().get(AppConstants.REQUESTER))
					: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		} catch (Exception ex) {
			logger.error("error ",ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@DeleteMapping(value = "/deleteReqeusterById/{id}")
	public ResponseEntity<String> deleteReqeusterById(@PathVariable Long id) {
		try {
			logger.info("Cusor enter in to deleteReqeusterById controller " + id);
			boolean deleteRequestById = requesterDetailsServiceImpl.deleteRequestById(id);
			return deleteRequestById
					? ResponseEntity.status(HttpStatus.OK).body(defaultValues.getMessage().get(AppConstants.DELETE))
					: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (Exception ex) {
			logger.error("error ",ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	// get requester request details from DB requester_details not form uesr_details
	// DB
	@GetMapping(value = "/getReqeusterRequestDetailsById/{id}")
	public ResponseEntity<RequesterDetails> getReqeustDetailsById(@PathVariable Long id)
			throws RequesterNotFoundException {

		logger.info("Cusor enter in to getReqeustDetailsById controller " + id);
		RequesterDetails reqeustDetailsById = requesterDetailsServiceImpl.getReqeustDetailsById(id);
		return reqeustDetailsById != null ? ResponseEntity.status(HttpStatus.OK).body(reqeustDetailsById)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

	}


	// get all the donars for making reqeust from the requester
	@GetMapping(value = "/getAlldonarsForMakingRequest")
	public ResponseEntity<List<MyUserDetails>> getAlldonarsForMakingRequest() throws UserNotFoundException {
		logger.info("Cursor enter into getAlldonarsForMakingRequest ");
		List<MyUserDetails> alldonarsForMakingRequest = myUserDetailsServiceImpl.getAlldonarsForMakingRequest();
		return alldonarsForMakingRequest != null ? ResponseEntity.status(HttpStatus.OK).body(alldonarsForMakingRequest)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

	}

}
