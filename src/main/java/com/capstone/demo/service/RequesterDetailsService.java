package com.capstone.demo.service;

import java.util.List;

import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.dto.UsersDto;
import com.capstone.demo.entity.RequesterDetails;
import com.capstone.demo.exception.RequesterNotFoundException;
import com.capstone.demo.exception.UserNotFoundException;

public interface RequesterDetailsService {

	public boolean saveRequesterDetails(RequesterDetailsDto requesterDetailsDto);

	public boolean deleteRequestById(Long id);

	public RequesterDetails getReqeustDetailsById(Long id) throws RequesterNotFoundException;

	public List<RequesterDetailsDto> getAllRequestOfDonarForApproving(Long donarId) throws RequesterNotFoundException;

	boolean updateRequesterDetails(UsersDto usersDto) throws UserNotFoundException;

}
