package com.pailkrko.scooters.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Scooter {
	
	private long id;
	private String model;
	private String color;
	private Date manufactured;
	
	private Map<Long, Note> notes = new HashMap<>();
	private List<Link> links = new ArrayList<>();
	
	public Scooter() {
		
	}
	
	public Scooter(long id, String model, String color) {
		this.id = id;
		this.model = model;
		this.color = color;
		this.manufactured = new Date();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public Date getManufactured() {
		return manufactured;
	}
	
	public void setManufactured(Date manufactured) {
		this.manufactured = manufactured;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	@XmlTransient
	public Map<Long, Note> getNotes() {
		return notes;
	}
	
	public void setNotes(Map<Long, Note> notes) {
		this.notes = notes;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public void addLink(String url, String rel) {
		
		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		links.add(link);
	}
}
