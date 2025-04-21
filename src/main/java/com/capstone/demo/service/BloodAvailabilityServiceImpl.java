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
	public List<BloodAvailability> getBloodDetails() {
		try {
			List<BloodAvailability> bloodDetails = bloodAvailabilityRepository.findAll();
			logger.info("Data form DB : " + mapper.writeValueAsString(bloodDetails));
			if (bloodDetails != null) {
				return bloodDetails;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BloodAvailability> getBloodDetailsBasedOnCityAndBloodGroup(String city, String bloodGroup) {
		try {
			logger.info("Blood Service getBloodDetails invoked ...!");
			List<BloodAvailability> bloodDetailsBasedOnCityAndGroup = bloodAvailabilityRepository.getBloodDetails(city,
					bloodGroup);
			logger.info("Data form DB : " + mapper.writeValueAsString(bloodDetailsBasedOnCityAndGroup));
			return bloodDetailsBasedOnCityAndGroup != null ? bloodDetailsBasedOnCityAndGroup : null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BloodAvailability> getBloodDetailsBasedOnCity(String city) {
		try {
			logger.info("Blood Service getBloodDetailsBasedOnCity invoked ...!");
			List<BloodAvailability> bloodDetails = bloodAvailabilityRepository.getBloodDetailsBasedOnCity(city);
			logger.info("Data form DB : " + mapper.writeValueAsString(bloodDetails));
			return bloodDetails != null ? bloodDetails : null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BloodAvailability> getBloodDetailsBasedOnBloodGroup(String bloodGroup) {
		try {
			logger.info("Blood Service getBloodDetailsBasedOnBloodGroup invoked ...!");

			List<BloodAvailability> bloodDetails = bloodAvailabilityRepository
					.getBloodDetailsBasedOnBloodGroup(bloodGroup);
			logger.info("Data form DB : " + mapper.writeValueAsString(bloodDetails));
			return bloodDetails != null ? bloodDetails : null;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public void addingBloodCountFromDonars(UserDetailsDto userDetailsDto) {
		try {
			logger.info("Service addingBloodCountFromDonars invoked...!");
			Optional<BloodAvailability> byBloodGroupAndCity = bloodAvailabilityRepository
					.findByBloodGroupAndCity(userDetailsDto.getBloodGroup(), userDetailsDto.getCity());
			logger.info("Object form the DB: " + mapper.writeValueAsString(byBloodGroupAndCity));
			if (byBloodGroupAndCity.isPresent()) {
				byBloodGroupAndCity.get().setQuantity(byBloodGroupAndCity.get().getQuantity() + 1);
				bloodAvailabilityRepository.save(byBloodGroupAndCity.get());
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
