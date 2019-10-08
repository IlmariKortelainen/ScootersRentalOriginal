package com.pailkrko.scooters.resources;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

import com.pailkrko.scooters.model.User;

public class MyCustomSecurityContext implements SecurityContext{
	private User user;
	private String scheme;
	
	public MyCustomSecurityContext(User user, String scheme) {
		this.user = user;
		this.scheme = scheme;
	}
	
	@Override
	public Principal getUserPrincipal() {
		return this.user;
	}
	
	@Override
	public boolean isUserInRole(String role) { 
		if (user.getRole() != null) {
			return user.getRole().contains(role);
		} return false;
	}
	
	@Override
	public boolean isSecure() {
		return "https".equals(this.scheme);
	}
	
	@Override 
	public String getAuthenticationScheme() {
		return SecurityContext.BASIC_AUTH; 
	}
}