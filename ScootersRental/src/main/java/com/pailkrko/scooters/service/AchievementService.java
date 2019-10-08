package com.pailkrko.scooters.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pailkrko.scooters.database.DatabaseClass;
import com.pailkrko.scooters.model.Achievement;
import com.pailkrko.scooters.model.User;

public class AchievementService {
	
	private Map<String, User> users = DatabaseClass.getUsers();
	
	public List<Achievement> getAllAchievements(String userName) {
		Map<Long, Achievement> achievements = users.get(userName).getAchievements();
		return new ArrayList<Achievement>(achievements.values());
	}
	
	public Achievement getAchievement(String userName, long achievementId) {
		Map<Long, Achievement> achievements = users.get(userName).getAchievements();
		return achievements.get(achievementId);
	}
	
	public Achievement addAchievement(String userName, Achievement achievement) {
		Map<Long, Achievement> achievements = users.get(userName).getAchievements();
		achievement.setId(achievements.size() + 1);
		achievements.put(achievement.getId(), achievement);
		return achievement;
	}
	
	public Achievement updateAchievement(String userName, Achievement achievement) {
		Map<Long, Achievement> achievements = users.get(userName).getAchievements();
		if (achievement.getId() <= 0) {
			return null;
		}
		achievements.put(achievement.getId(), achievement);
		return achievement;
	}
	
	public Achievement removeAchievement(String userName, long achievementId) {
		Map<Long, Achievement> achievements = users.get(userName).getAchievements();
		return achievements.remove(achievementId);
	}
}
