package com.capstone.demo.service;

import java.util.List;

import com.capstone.demo.entity.BloodAvailability;

public interface BloodAvailabilityService {

	public List<BloodAvailability> getBloodDetails();

	public List<BloodAvailability> getBloodDetailsBasedOnCity(String city);

	public List<BloodAvailability> getBloodDetailsBasedOnBloodGroup(String bloodGroup);

	public List<BloodAvailability> getBloodDetailsBasedOnCityAndBloodGroup(String city, String bloodGroup);

}
