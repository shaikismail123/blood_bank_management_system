package com.capstone.demo.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.service.BloodAvailabilityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/blood")
@RestController
@CrossOrigin(value = "*")
public class BloodAvailabilityController {

	private Logger logger = LoggerFactory.getLogger(BloodAvailabilityController.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	DefaultValues defaultValues;

	@Autowired
	private BloodAvailabilityServiceImpl bloodAvailabilityServiceImpl;

	// Get all blood details and divided based on blood group or city

	@GetMapping(value = "/getBloodDetailsBasedOnCity/{city}")
	public ResponseEntity<Map<String, Long>> getBloodDetailsBasedOnCity(@PathVariable String city) {
		try {
			logger.info("Blood group controller method invoce   " + city);
			Map<String, Long> bloodDetailsBasedOnCity = bloodAvailabilityServiceImpl.getBloodDetailsBasedOnCity(city);
			if (bloodDetailsBasedOnCity != null && bloodDetailsBasedOnCity.size() > 0) {
				return ResponseEntity.status(HttpStatus.OK).body(bloodDetailsBasedOnCity);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@GetMapping(value = "/getBloodDetailsBasedOnBloodGroup/{blood}")
	public ResponseEntity<Map<String, Long>> getBloodDetailsBasedOnBloodGroup(@PathVariable String blood) {
		try {
			logger.info("Blood group controller method invoce   " + blood);
			Map<String, Long> bloodDetailsBasedOnCity = bloodAvailabilityServiceImpl
					.getBloodDetailsBasedOnBloodGroup(blood);
			if (bloodDetailsBasedOnCity != null && bloodDetailsBasedOnCity.size() > 0) {
				return ResponseEntity.status(HttpStatus.OK).body(bloodDetailsBasedOnCity);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@GetMapping(value = "/getBloodDetails")
	public ResponseEntity<Map<String, Long>> getBloodDetails(@RequestParam Map<String, String> allParams) {
		try {
			logger.info("Blood group controller method invoce   " + mapper.writeValueAsString(allParams));
			Map<String, Long> bloodDetailsBasedOnCity = bloodAvailabilityServiceImpl
					.getBloodDetails(allParams.get("city"), allParams.get("blood"));
			if (bloodDetailsBasedOnCity != null && bloodDetailsBasedOnCity.size() > 0) {
				return ResponseEntity.status(HttpStatus.OK).body(bloodDetailsBasedOnCity);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	// based on blood group and city we need to increase the blood count

	@GetMapping(value = "/addingBloodCountFromDonars")
	public ResponseEntity<String> addingBloodCountFromDonars() {
		try {
			logger.info("Controller addingBloodCountFromDonars invoked ..!");
			boolean addingBloodCountFromDonars = bloodAvailabilityServiceImpl.addingBloodCountFromDonars();
			return addingBloodCountFromDonars
					? ResponseEntity.status(HttpStatus.OK).body(defaultValues.getMessage().get(AppConstants.SUCCESS))
					: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	
	
}
