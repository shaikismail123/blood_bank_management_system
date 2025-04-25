package com.capstone.demo.service;

import java.util.List;

import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.entity.AdminOperations;
import com.capstone.demo.entity.RequesterDetails;
import com.capstone.demo.exception.RequesterNotFoundException;

public interface AdminDetailsService {

	public List<UserDetailsDto> getAllDonarsForAdmin() throws RequesterNotFoundException;

	public List<RequesterDetailsDto> getAllRequesterForApproval() throws RequesterNotFoundException;

	public String saveAdminDetails(AdminOperations adminOperations);

	List<UserDetailsDto> getAllRequestersForAdmin() throws RequesterNotFoundException;

	boolean deleteRequestById(Long id) throws RequesterNotFoundException;

}
