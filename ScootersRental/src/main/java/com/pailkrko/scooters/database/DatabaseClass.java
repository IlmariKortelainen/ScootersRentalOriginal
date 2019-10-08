package com.pailkrko.scooters.database;

import java.util.HashMap;
import java.util.Map;


import com.pailkrko.scooters.model.Scooter;
import com.pailkrko.scooters.model.User;

public class DatabaseClass {
	
	private static Map<Long, Scooter> scooters = new HashMap<>();
	private static Map<String, User> users = new HashMap<>();
	//private static Map<String, LoginUser> loginUsers = new HashMap<>();
	
	public static Map<Long, Scooter> getScooters() {
		return scooters;
	}

	public static Map<String, User> getUsers() {
		return users;
	}
	/*
	public static Map<String, LoginUser> getLoginUsers() {
		return loginUsers;
	}*/
}
