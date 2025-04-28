package com.capstone.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.demo.config.AppConstants;
import com.capstone.demo.config.DefaultValues;
import com.capstone.demo.dto.RequesterDetailsDto;
import com.capstone.demo.dto.UsersDto;
import com.capstone.demo.entity.DonarDetails;
import com.capstone.demo.exception.RequesterNotFoundException;
import com.capstone.demo.exception.UserNotFoundException;
import com.capstone.demo.service.DonarDetailsServiceImpl;
import com.capstone.demo.service.MyUserDetailsServiceImpl;
import com.capstone.demo.service.RequesterDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "donar")
//@CrossOrigin(value = "*")
public class DonarDetailsController {

	private Logger logger = LoggerFactory.getLogger(DonarDetailsController.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MyUserDetailsServiceImpl myUserDetailsServiceImpl;

	@Autowired
	private DonarDetailsServiceImpl donarDetailsServiceImpl;

	@Autowired
	private DefaultValues defaultValues;

	@Autowired
	private RequesterDetailsServiceImpl requesterDetailsServiceImpl;

	// save the donar approved requests

	@PostMapping(value = "/saveDonarApprovedReqeusts")
	public ResponseEntity<String> saveDonarApprovedReqeusts(@RequestBody DonarDetails donarDetails) {
		try {
			logger.info("<======= Cursor enter into saveDonarApprovedReqeusts method inside controller ======>");
			String saveDonarApprovedReqeusts = donarDetailsServiceImpl.saveDonarApprovedReqeusts(donarDetails);
			return saveDonarApprovedReqeusts != null
					? ResponseEntity.status(HttpStatus.OK).body(defaultValues.getMessage().get(AppConstants.DONAR))
					: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} catch (Exception ex) {
			logger.error("error ", ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

//	// for deleting the doner data by id
//	@DeleteMapping("/deleteDoner/{id}")
//	public ResponseEntity<String> deleteDonerDetails(@PathVariable Long id) throws UserNotFoundException {
//		logger.info("Cursor enter in to Doner updation method inside controller");
//		boolean deleteDonerDetails = myUserDetailsServiceImpl.deleteDonerDetails(id);
//		return deleteDonerDetails
//				? ResponseEntity.status(HttpStatus.OK).body(defaultValues.getMessage().get(AppConstants.DELETE))
//				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//	}

	@GetMapping(value = "/getAllRequestOfDonar/{donarId}")
	public ResponseEntity<List<RequesterDetailsDto>> getAllRequestOfDonarForApproving(@PathVariable Long donarId)
			throws RequesterNotFoundException {
		logger.info("<========= cursor enter in to getAllRequestOfDonar method inside ==========>  " + donarId);
		List<RequesterDetailsDto> allRequestOfDonarForApproving = requesterDetailsServiceImpl
				.getAllRequestOfDonarForApproving(donarId);
		return allRequestOfDonarForApproving != null
				? ResponseEntity.status(HttpStatus.OK).body(allRequestOfDonarForApproving)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

	}

}
