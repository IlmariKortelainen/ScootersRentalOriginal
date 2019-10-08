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

import com.pailkrko.scooters.model.Note;
import com.pailkrko.scooters.service.NoteService;

@Path("/scooters/{scooterId}/notes/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoteResource {
	
	private NoteService noteService = new NoteService();
	
	@GET
	public List<Note> getAllNotes(@PathParam("scooterId") long scooterId){
		return noteService.getAllNotes(scooterId);
	}
	
	@POST
	public Note addNote(@PathParam("scooterId") long scooterId, Note note){
		return noteService.addNote(scooterId, note);
	}
	
	@PUT
	@Path("/{noteId}")
	public Note updateScooter(@PathParam("scooterId") long scooterId, @PathParam("noteId") long id, Note note){
		note.setId(id);
		return noteService.updateNote(scooterId, note);
	}
	
	@DELETE
	@Path("/{noteId}")
	public void deleteNote(@PathParam("scooterId") long scooterId, @PathParam("noteId") long noteId){
		noteService.removeNote(scooterId, noteId);
	}
	
	@GET
	@Path("/{noteId}")
	public Note getNote(@PathParam("scooterId") long scooterId, @PathParam("noteId") long noteId){
		return noteService.getNote(scooterId, noteId);
	}
	
}
