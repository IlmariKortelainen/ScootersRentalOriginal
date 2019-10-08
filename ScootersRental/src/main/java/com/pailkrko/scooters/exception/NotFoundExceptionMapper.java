package com.pailkrko.scooters.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.pailkrko.scooters.model.ErrorMessage;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
	
	@Override
	public Response toResponse(NotFoundException ex) {
		
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, "Not implemented");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

}
