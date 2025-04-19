package com.capstone.demo.service;

import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.entity.RequesterDetails;

public interface RequesterDetailsService {

	public boolean saveRequesterDetails(RequesterDetailsDto requesterDetailsDto, Long requeserId);

	public boolean deleteRequestById(Long id);

	public RequesterDetails getReqeustDetailsById(Long id);

}
