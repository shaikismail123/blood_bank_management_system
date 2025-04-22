package com.capstone.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BloodNotAvailabilityException extends RuntimeException {

	private String message;

}
