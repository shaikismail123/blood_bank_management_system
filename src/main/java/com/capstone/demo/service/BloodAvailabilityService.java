package com.capstone.demo.service;

import java.util.Map;

public interface BloodAvailabilityService {

	public Map<String, Long> getBloodDetailsBasedOnCity(String city);

	public Map<String, Long> getBloodDetailsBasedOnBloodGroup(String bloodGroup);

	public Map<String, Long> getBloodDetails(String city, String bloodGroup);
	
	
	
	

}
