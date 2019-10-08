package com.pailkrko.scooters.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pailkrko.scooters.database.DatabaseClass;
import com.pailkrko.scooters.model.Note;
import com.pailkrko.scooters.model.Scooter;

public class NoteService {
	
	private Map<Long, Scooter> scooters = DatabaseClass.getScooters();
	
	public List<Note> getAllNotes(long scooterId) {
		Map<Long, Note> notes = scooters.get(scooterId).getNotes();
		return new ArrayList<Note>(notes.values());
	}
	
	public Note getNote(long scooterId, long noteId) {
		Map<Long, Note> notes = scooters.get(scooterId).getNotes();
		return notes.get(noteId);
	}
	
	public Note addNote(long scooterId, Note note) {
		Map<Long, Note> notes = scooters.get(scooterId).getNotes();
		note.setId(notes.size() + 1);
		notes.put(note.getId(), note);
		return note;
	}
	
	public Note updateNote(long scooterId, Note note) {
		Map<Long, Note> notes = scooters.get(scooterId).getNotes();
		if (note.getId() <= 0) {
			return null;
		}
		notes.put(note.getId(), note);
		return note;
	}
	
	public Note removeNote(long scooterId, long noteId) {
		Map<Long, Note> notes = scooters.get(scooterId).getNotes();
		return notes.remove(noteId);
	}
}
