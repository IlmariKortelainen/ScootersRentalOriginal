package com.pailkrko.scooters.model;

import java.util.Date;

public class Achievement {
	
	private long id;
	private String title;
	private String description;
	private Date achieved;
	
	public Achievement() {
		
	}
	
	public Achievement(long id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.achieved = new Date();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
    public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Date getAchieved() {
		return achieved;
	}
	
	public void setAchieved(Date achieved) {
		this.achieved = achieved;
	}
}
