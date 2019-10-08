package com.pailkrko.scooters.resources;

import com.pailkrko.scooters.service.AuthenticationService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.glassfish.jersey.internal.util.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;


@Provider
public class TokenFilter implements ContainerRequestFilter{

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Token ";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// jos endpoint on token, niin ei tarvi autentikoida
		if(requestContext.getUriInfo().getPath().contains("token")) {
			return;
		}

		// autentikoidaan kaikki muut endpointit tokenilla
		// jos halutaan käyttää basic authia, niin kaikki kommentteihin
		/*
		List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		if (authHeader != null && authHeader.size() > 0) {
			String authToken = authHeader.get(0);
			authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");

			String token = authToken;
			try {
				Algorithm algorithm = Algorithm.HMAC256(AuthenticationService.SECRET);
				JWTVerifier verifier = JWT.require(algorithm)
						//.withIssuer("auth0")
						.build(); //Reusable verifier instance
				DecodedJWT jwt = verifier.verify(token);
				return;

			} catch (JWTVerificationException exception){
				//Invalid signature/claims
			}
		}
		Response unauthorizedStatus = Response
				.status(Response.Status.UNAUTHORIZED)
				.entity("User cannot access the resource.")
				.build();
		requestContext.abortWith(unauthorizedStatus);
		*/
	}
}
