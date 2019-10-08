package com.pailkrko.scooters.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.pailkrko.scooters.model.ErrorMessage;

@Provider
public class UnauthorizedExceptionMapper implements ExceptionMapper<UnauthorizedException> {
	
	@Override
	public Response toResponse(UnauthorizedException ex) {
		
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 401);
		return Response.status(Status.UNAUTHORIZED)
				.entity(errorMessage)
				.build();
	}

}