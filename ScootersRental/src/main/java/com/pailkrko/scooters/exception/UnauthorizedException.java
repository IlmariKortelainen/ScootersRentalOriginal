package com.pailkrko.scooters.exception;

public class UnauthorizedException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6328286661536343936L;
	
	public UnauthorizedException(String message) {
		super(message);
	}
}
