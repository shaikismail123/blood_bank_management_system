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

	public boolean addingBloodCountFromDonars() {
		try {
			logger.info("Service addingBloodCountFromDonars invoked...!");

			// Fetch existing blood group entries
			List<BloodAvailability> allBloodGroupCounts = bloodAvailabilityRepository.findAll();
			logger.info("All Blood Group Data from Blood Availability Table: "
					+ mapper.writeValueAsString(allBloodGroupCounts));

			// Fetch donors with blood group information
			List<MyUserDetails> donarsToAddBloodCount = myUserDetailsRepository.getDonarsToAddBloodCount();

			logger.info(
					"Donor details from DB to add blood count: " + mapper.writeValueAsString(donarsToAddBloodCount));

			// Count how many donors per blood group
			Map<String, Long> basedOnBloodGroupCount = donarsToAddBloodCount.stream()
					.collect(Collectors.groupingBy(MyUserDetails::getBloodGroup, Collectors.counting()));

			for (Map.Entry<String, Long> entry : basedOnBloodGroupCount.entrySet()) {
				String bloodGroup = entry.getKey();
				Long quantityToAdd = entry.getValue();

				Optional<BloodAvailability> existing = bloodAvailabilityRepository.findByBloodGroup(bloodGroup);

				if (existing.isPresent()) {
					BloodAvailability ba = existing.get();
					ba.setQuantity(ba.getQuantity() + quantityToAdd.intValue());
					bloodAvailabilityRepository.save(ba);
					logger.info("Updated quantity for blood group " + bloodGroup + " by adding " + quantityToAdd);
				} else {
					BloodAvailability newEntry = new BloodAvailability();
					newEntry.setBloodGroup(bloodGroup);
					newEntry.setQuantity(quantityToAdd.intValue());
					bloodAvailabilityRepository.save(newEntry);
					logger.info("Inserted new blood group " + bloodGroup + " with quantity " + quantityToAdd);
				}
			}

			donarsToAddBloodCount.forEach(each -> {
				each.setBloodStatusAddedOrNot("YES");
			});

			logger.info(defaultValues.getMessage().get(AppConstants.SUCCESS));
			return myUserDetailsRepository.saveAll(donarsToAddBloodCount).get(0).getUserId() != null;

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Exception while adding blood count: ", ex);
		}
		return false;
	}

}
