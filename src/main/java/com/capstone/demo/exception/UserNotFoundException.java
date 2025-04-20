package com.capstone.demo.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {

	}

	public UserNotFoundException(String str) {
		super(str);
	}
}
