package com.capstone.demo.service;

import java.util.List;

import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.entity.RequesterDetails;
import com.capstone.demo.exception.RequesterNotFoundException;

public interface RequesterDetailsService {

	public boolean saveRequesterDetails(RequesterDetailsDto requesterDetailsDto);

	public boolean deleteRequestById(Long id) throws RequesterNotFoundException;

	public RequesterDetails getReqeustDetailsById(Long id) throws RequesterNotFoundException;

	public List<RequesterDetailsDto> getAllRequestOfDonarForApproving(Long donarId) throws RequesterNotFoundException;

}
