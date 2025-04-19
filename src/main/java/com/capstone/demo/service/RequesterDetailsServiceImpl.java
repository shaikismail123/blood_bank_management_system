package com.capstone.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.entity.RequesterDetails;
import com.capstone.demo.repository.MyUserDetailsRepository;
import com.capstone.demo.repository.RequesterDetailsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RequesterDetailsServiceImpl implements RequesterDetailsService {

	private Logger logger = LoggerFactory.getLogger(RequesterDetailsServiceImpl.class);
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private RequesterDetailsRepository requesterDetailsRepository;

	@Autowired
	private MyUserDetailsRepository myUserDetailsRepository;

	@Autowired
	DefaultValues defaultValues;

	@Override
	public boolean saveRequesterDetails(RequesterDetailsDto requesterDetailsDto, Long requesterId, Long donarId) {
		try {
			logger.info("Curser enter in to Save Request detaisl method inside service ");

			requesterDetailsDto.setRequesterId(requesterId);
			requesterDetailsDto.setDonarId(donarId);
			requesterDetailsDto.setRequiredDate(String.valueOf(LocalDate.now()));
			RequesterDetails details = mapper.convertValue(requesterDetailsDto, RequesterDetails.class);
			return requesterDetailsRepository.save(details).getRequestId() != null;

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("something went wrong while adding reqeuster details ", ex.getMessage());
		}

		return false;
	}

	@Override
	public boolean deleteRequestById(Long id) {
		try {
			logger.info("Cursor Enter in to Delete Reqeust by id method inside service ====>  " + id);
			requesterDetailsRepository.deleteById(id);
			logger.info(defaultValues.getMessage().get(AppConstants.DELETE));
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public RequesterDetails getReqeustDetailsById(Long id) {
		try {
			logger.info("Cursor Enter in to DgetReqeustDetailsById inside service ====>  " + id);
			Optional<RequesterDetails> byId = requesterDetailsRepository.findById(id);
			if (byId.isPresent()) {
				return byId.get();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// if donar want to see how can requests he got for blood donation approval
	@Override
	public List<RequesterDetailsDto> getAllRequestOfDonarForApproving(Long donarId) {
		try {
			List<RequesterDetails> byDonarId = requesterDetailsRepository.findByDonarId(donarId);
			List<RequesterDetailsDto> donarDetails = new ArrayList<>();
			byDonarId.stream().forEach(each -> {
				donarDetails.add(mapper.convertValue(each, RequesterDetailsDto.class));
			});
			return donarDetails != null ? donarDetails : null;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

}
