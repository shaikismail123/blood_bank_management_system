package com.capstone.demo.service;

import java.util.List;

import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.entity.RequesterDetails;

public interface RequesterDetailsService {

	public boolean saveRequesterDetails(RequesterDetailsDto requesterDetailsDto);

	public boolean deleteRequestById(Long id);

	public RequesterDetails getReqeustDetailsById(Long id);

	public List<RequesterDetailsDto> getAllRequestOfDonarForApproving(Long donarId);

}
