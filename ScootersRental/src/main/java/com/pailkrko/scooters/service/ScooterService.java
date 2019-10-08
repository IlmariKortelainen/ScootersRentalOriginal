package com.pailkrko.scooters.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.pailkrko.scooters.database.DatabaseClass;
import com.pailkrko.scooters.exception.NotFoundException;
import com.pailkrko.scooters.model.Scooter;

public class ScooterService {
	
	private Map<Long, Scooter> scooters = DatabaseClass.getScooters();
	
	public ScooterService() {

	}
	
	public List<Scooter> getAllScooters() {
		return new ArrayList<Scooter>(scooters.values());
	}
	
	public List<Scooter> getAllScootersForYear(int year) {
		List<Scooter> scootersForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Scooter scooter : scooters.values()) {
			cal.setTime(scooter.getManufactured());
			if (cal.get(Calendar.YEAR) == year) {
				scootersForYear.add(scooter);
			}
		}
		return scootersForYear;
	}
	
	public List<Scooter> getAllScootersPaginated(int start, int size) {
		ArrayList<Scooter> list = new ArrayList<Scooter>(scooters.values());
		if (start + size > list.size()) return new ArrayList<Scooter>();
		return list.subList(start,  start + size);
	}
	
	public Scooter getScooter(long id) {
		Scooter scooter = scooters.get(id);
		if (scooter == null) {
			throw new NotFoundException("Scooter with id " + id + " not found");
		}
		return scooters.get(id);
	}
	
	public Scooter addScooter(Scooter scooter) {
		scooter.setId(scooters.size() + 1);
		scooters.put(scooter.getId(), scooter);
		return scooter;
	}
	
	public Scooter updateScooter(Scooter scooter) {
		if (scooter.getId() <= 0) {
			return null;
		}
		scooters.put(scooter.getId(), scooter);
		return scooter;
	}
	
	public Scooter removeScooter(long id) {
		return scooters.remove(id);
	}
}
