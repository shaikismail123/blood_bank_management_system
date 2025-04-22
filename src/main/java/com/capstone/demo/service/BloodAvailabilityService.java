package com.capstone.demo.service;

import java.util.List;

import com.capstone.demo.entity.BloodAvailability;
import com.capstone.demo.exception.BloodNotAvailabilityException;

public interface BloodAvailabilityService {

	public List<BloodAvailability> getBloodDetails();

	public List<BloodAvailability> getBloodDetailsBasedOnCity(String city) throws BloodNotAvailabilityException;

	public List<BloodAvailability> getBloodDetailsBasedOnBloodGroup(String bloodGroup)
			throws BloodNotAvailabilityException;

	public List<BloodAvailability> getBloodDetailsBasedOnCityAndBloodGroup(String city, String bloodGroup)
			throws BloodNotAvailabilityException;

}
