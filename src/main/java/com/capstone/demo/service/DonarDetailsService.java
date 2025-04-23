package com.capstone.demo.service;

import com.capstone.demo.dto.UserDetailsDto;
import com.capstone.demo.entity.DonarDetails;
import com.capstone.demo.exception.UserNotFoundException;

public interface DonarDetailsService {

	public String saveDonarApprovedReqeusts(DonarDetails donarDetails);

	boolean updateDonarDetails(UserDetailsDto userDetailsDto) throws UserNotFoundException;

}
