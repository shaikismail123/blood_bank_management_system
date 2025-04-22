package com.capstone.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.entity.AdminOperations;
import com.capstone.demo.entity.MyUserDetails;
import com.capstone.demo.entity.RequesterDetails;
import com.capstone.demo.repository.AdminDetailsRepository;
import com.capstone.demo.repository.MyUserDetailsRepository;
import com.capstone.demo.repository.RequesterDetailsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AdminDetailsServiceImpl implements AdminDetailsService {

	private Logger logger = LoggerFactory.getLogger(AdminDetailsServiceImpl.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	DefaultValues defaultValues;

	@Autowired
	private AdminDetailsRepository adminDetailsRepository;

	@Autowired
	private MyUserDetailsRepository myUserDetailsRepository;

	@Autowired
	private RequesterDetailsRepository requesterDetailsRepository;

	@Override
	public List<UserDetailsDto> getAllDonarsForAdmin() {
		try {
			List<MyUserDetails> allDonarsForAdmin = myUserDetailsRepository.getAllDonarsForAdmin();
			logger.info("Data from DB : " + mapper.writeValueAsString(allDonarsForAdmin));
			List<UserDetailsDto> listUserDto = new ArrayList<>();
			allDonarsForAdmin.stream().forEach(each -> {
				listUserDto.add(mapper.convertValue(each, UserDetailsDto.class));
			});
			return listUserDto != null && listUserDto.size() > 0 ? listUserDto : null;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("while fetching the data form DB we will get the exception ", ex.getMessage());
		}
		return null;
	}

	@Override
	public List<RequesterDetailsDto> getAllRequesterForApproval() {
		try {
			List<RequesterDetails> allRequesterForApproval = requesterDetailsRepository.getAllRequesterForApproval();
			logger.info("Data from DB : " + mapper.writeValueAsString(allRequesterForApproval));
			List<RequesterDetailsDto> allReqeusts = new ArrayList<>();
			allRequesterForApproval.stream().forEach(each -> {
				allReqeusts.add(mapper.convertValue(each, RequesterDetailsDto.class));
			});
			return allReqeusts != null ? allReqeusts : null;
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("while fetching the data form DB we will get the exception ", ex.getMessage());
		}
		return null;

	}

	@Override
	public String saveAdminDetails(AdminOperations adminOperations) {
		try {
			AdminOperations operations = adminDetailsRepository.save(adminOperations);
			if (operations != null) {
				return requesterDetailsRepository.save(adminOperations.getRequesterDetails()) != null
						? defaultValues.getMessage().get(AppConstants.SUCCESS)
						: defaultValues.getMessage().get(AppConstants.FAIL);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return defaultValues.getMessage().get(AppConstants.FAIL);
	}

}
