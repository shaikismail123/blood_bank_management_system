package com.capstone.demo.controller;

import java.util.List;
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
import com.capstone.demo.entity.BloodAvailability;
import com.capstone.demo.exception.BloodNotAvailabilityException;
import com.capstone.demo.service.BloodAvailabilityServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/blood")
@RestController
//@CrossOrigin(value = "*")
public class BloodAvailabilityController {

	private Logger logger = LoggerFactory.getLogger(BloodAvailabilityController.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	DefaultValues defaultValues;

	@Autowired
	private BloodAvailabilityServiceImpl bloodAvailabilityServiceImpl;

	// Get all blood details to view in front end
	@GetMapping(value = "/getBloodDetails")
	public ResponseEntity<List<BloodAvailability>> getBloodDetails() {

		logger.info("Blood group controller method invoke   ");
		List<BloodAvailability> bloodDetails = bloodAvailabilityServiceImpl.getBloodDetails();
		if (bloodDetails != null && bloodDetails.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(bloodDetails);
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	// Get all blood details and divided based on blood group or city
	@GetMapping(value = "/getBloodDetailsBasedOnCity/{city}")
	public ResponseEntity<List<BloodAvailability>> getBloodDetailsBasedOnCity(@PathVariable String city)
			throws BloodNotAvailabilityException {

		logger.info("Blood group controller method invoce   " + city);
		List<BloodAvailability> bloodDetailsBasedOnCity = bloodAvailabilityServiceImpl.getBloodDetailsBasedOnCity(city);
		if (bloodDetailsBasedOnCity != null && bloodDetailsBasedOnCity.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(bloodDetailsBasedOnCity);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

	}

	@GetMapping(value = "/getBloodDetailsBasedOnBloodGroup/{blood}")
	public ResponseEntity<List<BloodAvailability>> getBloodDetailsBasedOnBloodGroup(@PathVariable String blood) {
		logger.info("Blood group controller method invoce   " + blood);
		List<BloodAvailability> bloodDetailsBasedOngroup = bloodAvailabilityServiceImpl
				.getBloodDetailsBasedOnBloodGroup(blood);
		if (bloodDetailsBasedOngroup != null && bloodDetailsBasedOngroup.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(bloodDetailsBasedOngroup);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@GetMapping(value = "/getBloodDetailsBasedOnCityAndBloodGroup")
	public ResponseEntity<List<BloodAvailability>> getBloodDetailsBasedOnCityAndBloodGroup(
			@RequestParam Map<String, String> allParams) {

		logger.info("Blood group controller method invoce   " + allParams.get("city") + "   "
				+ allParams.get("bloodGroup"));
		List<BloodAvailability> bloodDetailsBasedOnCityAndGroup = bloodAvailabilityServiceImpl
				.getBloodDetailsBasedOnCityAndBloodGroup(allParams.get("city"), allParams.get("blood"));
		if (bloodDetailsBasedOnCityAndGroup != null && bloodDetailsBasedOnCityAndGroup.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(bloodDetailsBasedOnCityAndGroup);
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

}
