package com.capstone.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	ExceptionResponse response = new ExceptionResponse();

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ExceptionResponse> userNotFoundExceptin(UserNotFoundException ex) {
		response.setMessage(ex.getMessage());
		response.setExceptionCode("67867");
		response.setTimeAnDate(String.valueOf(LocalDateTime.now()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(RequesterNotFoundException.class)
	public ResponseEntity<ExceptionResponse> requesterNotFoundException(RequesterNotFoundException ex) {
		response.setMessage(ex.getMessage());
		response.setExceptionCode("3455432");
		response.setTimeAnDate(String.valueOf(LocalDateTime.now()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler(BloodNotAvailabilityException.class)
	public ResponseEntity<ExceptionResponse> bloodNotAvailabilityException(BloodNotAvailabilityException ex) {
		response.setMessage(ex.getMessage());
		response.setExceptionCode("876567");
		response.setTimeAnDate(String.valueOf(LocalDateTime.now()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

}
