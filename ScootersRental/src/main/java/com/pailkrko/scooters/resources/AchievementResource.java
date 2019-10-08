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
import javax.ws.rs.core.MediaType;

import com.pailkrko.scooters.model.Achievement;
import com.pailkrko.scooters.service.AchievementService;

@Path("/users/{userName}/achievements/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AchievementResource {
	
	private AchievementService achievementService = new AchievementService();
	
	@GET
	public List<Achievement> getAllAchievements(@PathParam("userName") String userName){
		return achievementService.getAllAchievements(userName);
	}
	
	@POST
	public Achievement addAchievement(@PathParam("userName") String userName, Achievement achievement){
		return achievementService.addAchievement(userName, achievement);
	}
	
	@PUT
	@Path("/{achievementId}")
	public Achievement updateUser(@PathParam("userName") String userName, @PathParam("achievementId") long id, Achievement achievement){
		achievement.setId(id);
		return achievementService.updateAchievement(userName, achievement);
	}
	
	@DELETE
	@Path("/{achievementId}")
	public void deleteAchievement(@PathParam("userName") String userName, @PathParam("achievementId") long achievementId){
		achievementService.removeAchievement(userName, achievementId);
	}
	
	@GET
	@Path("/{achievementId}")
	public Achievement getAchievement(@PathParam("userName") String userName, @PathParam("achievementId") long achievementId){
		return achievementService.getAchievement(userName, achievementId);
	}
	
}
