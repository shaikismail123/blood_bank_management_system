package com.capstone.demo.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/blood")
@RestController
@CrossOrigin(value = "*")
public class BloodAvailabilityController {

	private Logger logger = LoggerFactory.getLogger(BloodAvailabilityController.class);

	private ObjectMapper mapper = new ObjectMapper();
	

	// Get all blood details and divided based on blood group or city
  @GetMapping(value="/getBloodDetails")
	public ResponseEntity<Map<String, Integer>> getBloodDetails() {
		try {
         logger.info("Blood group controller method invoce");
         
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
