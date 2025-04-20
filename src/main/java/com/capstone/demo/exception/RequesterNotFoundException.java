package com.capstone.demo.exception;

public class RequesterNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public RequesterNotFoundException() {

	}

	public RequesterNotFoundException(String msg) {
		super(msg);
	}

}
