package com.pailkrko.scooters.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import com.pailkrko.scooters.model.User;
import com.pailkrko.scooters.service.UserService;


@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	
	private UserService userService = new UserService();
	
	@Context
	private SecurityContext securityContext;
	
	@GET
	public List<User> getUsers() {
		
		return userService.getAllUsers();
	}
	
	@GET
	@Path("/{userName}")
	public User getUser(@PathParam("userName") String userName) {
		
		if (!securityContext.isUserInRole("admin")){
			System.out.println("Hän ei ole admin");
			throw new WebApplicationException("Not authorized", 401);
		}
		
		return userService.getUser(userName);
	}
	
	@POST
	public User addUser(User user) {
		
		return userService.addUser(user);
	}
	
	@PUT
	@Path("/{userName}")
	public User updateUser(@PathParam("userName") String userName, User user) {
		user.setUserName(userName);
		return userService.updateUser(user);
	}
	
	@DELETE
	@Path("/{userName}")
	public void deleteUser(@PathParam("userName") String userName){
		userService.removeUser(userName);
	}
	
	@Path("/{userName}/achievements")
    public AchievementResource getAchievementResource() {
		
		return new AchievementResource();
	}
}
