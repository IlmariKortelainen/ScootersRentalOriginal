package com.pailkrko.scooters.resources;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.pailkrko.scooters.database.DatabaseClass;
import com.pailkrko.scooters.model.User;
import com.pailkrko.scooters.service.UserService;

import org.glassfish.jersey.internal.util.Base64;

// basic auth -filtteri


@Provider
public class SecurityFilter implements ContainerRequestFilter{

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	// private static final String SECURED_URL_PREFIX = "secured";
	
	private String errorMessage = "User cannot access the resource.";
	private Map<String, User> users = DatabaseClass.getUsers();
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		UserService userService = new UserService();
		
		User user = null;

		// tämä return heittää pihalle kun käytetään JWT:tä, eli kommentoi jos käytät basic authia
		//return;
		
		
		// katsoo onko pathissa yllä muodostettu secured prefix. Me laitetaan koko roska basic authin taakse, 
		// joten tämä voi olla kommenteissa niin basic auth toimii joka endpointilla
		// if(requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {

		
			// jos halutaan käyttää JWT niin tämä kommentteihin
			
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			if (authHeader != null && authHeader.size() > 0) {
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				String decodedString = Base64.decodeAsString(authToken);
				StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
				
				// Käyttäjän antama username
				String username = tokenizer.nextToken();
				
				// Käyttäjän antama password
				String password = tokenizer.nextToken();

				if ("user".equals(username) && "password".equals(password)) {

					return;
					
				} else if ("user".equals(username) == false && "password".equals(password)) {
					
					errorMessage = "Username doesn't exist!";
					
				} else if ("user".equals(username) && "password".equals(password) == false) {
					
					errorMessage = "Bad password!";
				}
				
				if (users.size() > 0 && ("user".equals(username) == false)) {
					
					if (userService.getUser(username) != null) {
						user = userService.getUser(username);
						String password1 = user.getPassWord();
						String username1 = user.getUserName();
						
						if (username1.equals(username) && password1.equals(password)) {
							
							String scheme = requestContext.getUriInfo().getRequestUri().getScheme();
							requestContext.setSecurityContext(new MyCustomSecurityContext(user, scheme));
							
							return;
						}
						
						errorMessage = "Bad password!";
						
					   } else {
						
						errorMessage = "Username doesn't exist!";
					}
				}
			}
			Response unauthorizedStatus = Response
					.status(Response.Status.UNAUTHORIZED)
					.entity(errorMessage)
					.build();
			requestContext.abortWith(unauthorizedStatus);
			
		}
	}
