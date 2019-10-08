package com.pailkrko.scooters.exception;

public class NotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6328286661536343936L;
	
	public NotFoundException(String message) {
		super(message);
	}
}
