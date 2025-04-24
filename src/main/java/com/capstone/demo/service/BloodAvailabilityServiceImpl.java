package com.capstone.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.entity.BloodAvailability;
import com.capstone.demo.entity.MyUserDetails;
import com.capstone.demo.exception.BloodNotAvailabilityException;
import com.capstone.demo.repository.BloodAvailabilityRepository;
import com.capstone.demo.repository.MyUserDetailsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BloodAvailabilityServiceImpl implements BloodAvailabilityService {

	private Logger logger = LoggerFactory.getLogger(BloodAvailabilityService.class);
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private BloodAvailabilityRepository bloodAvailabilityRepository;

	@Autowired
	DefaultValues defaultValues;

	@Autowired
	private MyUserDetailsRepository myUserDetailsRepository;

	@Override
	public List<BloodAvailability> getBloodDetails() throws BloodNotAvailabilityException {

		List<BloodAvailability> bloodDetails = bloodAvailabilityRepository.findAll();
		if (bloodDetails != null && bloodDetails.size() > 0) {
			return bloodDetails;
		} else {
			throw new BloodNotAvailabilityException("Blood data not available ");
		}

	}

	@Override
	public List<BloodAvailability> getBloodDetailsBasedOnCityAndBloodGroup(String city, String bloodGroup)
			throws BloodNotAvailabilityException {

		logger.info("Blood Service getBloodDetails invoked ...!");
		List<BloodAvailability> bloodDetailsBasedOnCityAndGroup = bloodAvailabilityRepository.getBloodDetails(city,
				bloodGroup);

		if (bloodDetailsBasedOnCityAndGroup.size() > 0) {
			return bloodDetailsBasedOnCityAndGroup != null ? bloodDetailsBasedOnCityAndGroup : null;
		} else {
			throw new BloodNotAvailabilityException(
					"Blood not available based on this city" + city + " and blood group  " + bloodGroup);
		}

	}

	@Override
	public List<BloodAvailability> getBloodDetailsBasedOnCity(String city) throws BloodNotAvailabilityException {

		logger.info("Blood Service getBloodDetailsBasedOnCity invoked ...!");
		List<BloodAvailability> bloodDetails = bloodAvailabilityRepository.getBloodDetailsBasedOnCity(city);
		if (bloodDetails.size() > 0) {
			return bloodDetails != null ? bloodDetails : null;
		} else {
			throw new BloodNotAvailabilityException("Blood not available based on this city " + city);
		}

	}

	@Override
	public List<BloodAvailability> getBloodDetailsBasedOnBloodGroup(String bloodGroup)
			throws BloodNotAvailabilityException {

		logger.info("Blood Service getBloodDetailsBasedOnBloodGroup invoked ...!");

		List<BloodAvailability> bloodDetails = bloodAvailabilityRepository.findByBloodGroup(bloodGroup);

		if (bloodDetails.size() > 0) {
			return bloodDetails != null ? bloodDetails : null;
		} else {
			throw new BloodNotAvailabilityException("Blood not available based on this blood group " + bloodGroup);
		}

	}

	public void addingBloodCountFromDonars(UserDetailsDto userDetailsDto) {
		try {
			logger.info("Service addingBloodCountFromDonars invoked...!");
			BloodAvailability byBloodGroupAndCity = bloodAvailabilityRepository
					.findByBloodGroupAndCity(userDetailsDto.getBloodGroup(), userDetailsDto.getCity());
			logger.info("Object form the DB: " + mapper.writeValueAsString(byBloodGroupAndCity));
			if (byBloodGroupAndCity != null) {
				byBloodGroupAndCity.setQuantity(byBloodGroupAndCity.getQuantity() + 1);
				bloodAvailabilityRepository.save(byBloodGroupAndCity);
			} else {
				BloodAvailability bloodAvailability = new BloodAvailability();
				bloodAvailability.setBloodGroup(userDetailsDto.getBloodGroup());
				bloodAvailability.setCity(userDetailsDto.getCity());
				bloodAvailability.setQuantity(1);
				bloodAvailabilityRepository.save(bloodAvailability);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Exception while adding blood count: ", ex);
		}

	}

}
