package com.pailkrko.scooters.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.pailkrko.scooters.model.ErrorMessage;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Throwable>{
	
	@Override
	public Response toResponse(Throwable ex) {
		
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 500, "Not implemented");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage)
				.build();
				
	}

}
