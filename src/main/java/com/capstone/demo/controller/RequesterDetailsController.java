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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.entity.RequesterDetails;
import com.capstone.demo.service.RequesterDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/requester")
@CrossOrigin(value = "*")
public class RequesterDetailsController {

	private Logger logger = LoggerFactory.getLogger(RequesterDetailsController.class);
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private RequesterDetailsServiceImpl requesterDetailsServiceImpl;

	@Autowired
	DefaultValues defaultValues;

	@PostMapping(value = "/saveRequesterDetails")
	public ResponseEntity<String> saveRequesterDetails(@RequestBody RequesterDetailsDto requesterDetailsDto,
			@RequestParam Long requeserId) {
		try {
			logger.info("Cusor enter in to save Requester details method inside "
					+ mapper.writeValueAsString(requesterDetailsDto));
			boolean saveRequesterDetails = requesterDetailsServiceImpl.saveRequesterDetails(requesterDetailsDto,
					requeserId);
			return saveRequesterDetails
					? ResponseEntity.status(HttpStatus.OK).body(defaultValues.getMessage().get(AppConstants.SUCCESS))
					: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@DeleteMapping(value = "/deleteReqeusterById")
	public ResponseEntity<String> deleteReqeusterById(@PathVariable Long id) {
		try {
			logger.info("Cusor enter in to deleteReqeusterById controller " + id);
			boolean deleteRequestById = requesterDetailsServiceImpl.deleteRequestById(id);
			return deleteRequestById
					? ResponseEntity.status(HttpStatus.OK).body(defaultValues.getMessage().get(AppConstants.DELETE))
					: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@GetMapping(value = "/getReqeustDetailsById/{id}")
	public ResponseEntity<String> getReqeustDetailsById(@PathVariable Long id) {
		try {
			logger.info("Cusor enter in to getReqeustDetailsById controller " + id);
			RequesterDetails reqeustDetailsById = requesterDetailsServiceImpl.getReqeustDetailsById(id);
			return reqeustDetailsById != null
					? ResponseEntity.status(HttpStatus.OK).body(defaultValues.getMessage().get(AppConstants.SUCCESS))
					: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

}
