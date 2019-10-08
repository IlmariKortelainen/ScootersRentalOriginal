package com.pailkrko.scooters.resources;

import com.pailkrko.scooters.service.AuthenticationService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("token")
public class TokenResource {

	
	// haetaan token
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getToken() {
        return AuthenticationService.getJwt();
    }
}
