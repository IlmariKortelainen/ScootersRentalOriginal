package com.pailkrko.scooters.model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.bind.annotation.JsonbTransient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class User implements Principal{

	private long id;
	private String userName;
	private String passWord;
	private String firstName;
	private String surName;
	private Date joined;

	private Map<Long, Achievement> achievements = new HashMap<>();
	
	private List<String> role;
	
	public User() {
		
	}
	
	public User(long id, String userName, String passWord, String firstName, String surName) {
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.firstName = firstName;
		this.surName = surName;
		this.role = new ArrayList<String>();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@JsonbTransient
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getSurName() {
		return surName;
	}
	
	public void setSurName(String surName) {
		this.surName = surName;
	}
	
	public Date getJoined() {
		return joined;
	}
	
	public void setJoined(Date joined) {
		this.joined = joined;
	}
	
	public Map<Long, Achievement> getAchievements() {
		return achievements;
	}
	
	public void setAchievements(Map<Long, Achievement> achievements) {
		this.achievements = achievements;
	}

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}
	
	@Override
	public String getName() {
		return this.firstName + " " + this.surName;
	}
}
