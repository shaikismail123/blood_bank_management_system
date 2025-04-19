package com.capstone.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.entity.DonarDetails;
import com.capstone.demo.repository.DonarDetailsRepository;

@Service
public class DonarDetailsServiceImpl implements DonarDetailsService {

	private Logger logger = LoggerFactory.getLogger(DonarDetailsServiceImpl.class);

	@Autowired
	private DonarDetailsRepository donarDetailsRepository;

	@Autowired
	DefaultValues defaultValues;

	@Override
	public String saveDonarApprovedReqeusts(DonarDetails donarDetails) {
		try {
			return donarDetailsRepository.save(donarDetails).getId() != null
					? defaultValues.getMessage().get(AppConstants.SUCCESS)
					: defaultValues.getMessage().get(AppConstants.FAIL);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
