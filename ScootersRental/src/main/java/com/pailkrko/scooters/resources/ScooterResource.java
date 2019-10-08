package com.pailkrko.scooters.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pailkrko.scooters.model.Link;
import com.pailkrko.scooters.model.Scooter;
import com.pailkrko.scooters.service.ScooterService;

import javax.ws.rs.core.UriInfo;

@Path("/scooters")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ScooterResource {
	
	ScooterService scooterService = new ScooterService();
	
	@GET
	public List<Scooter> getScooters(@QueryParam("year") int year,
			                         @QueryParam("start") int start,
			                         @QueryParam("size") int size) {
		
		if (year > 0) {
			return scooterService.getAllScootersForYear(year);
		}
		if (start + size > 0) {
			return scooterService.getAllScootersPaginated(start-1, size);
		}
		return scooterService.getAllScooters();
	}
	
	@GET
	@Path("/{scooterId}")
    public Response getScooter(@PathParam("scooterId") long id, @Context UriInfo uriInfo) {
		Scooter scooter = scooterService.getScooter(id);
		List<Link> links = scooter.getLinks();
			
			if(links.size() < 2) {
				scooter.addLink(getUriForSelf(uriInfo, scooter), "Path to Scooter");
				scooter.addLink(getUriForNotes(uriInfo, scooter), "notes");
			}
		
		return Response.status(302)
				.entity(scooter)
				.build();
		// return scooterService.getScooter(id);
	}
	
	private String getUriForNotes(UriInfo uriInfo, Scooter scooter) {
		URI uri = uriInfo.getBaseUriBuilder()
				.path(ScooterResource.class)
				.path(ScooterResource.class, "getNoteResource")
				.path(NoteResource.class)
				.resolveTemplate("scooterId", scooter.getId())
				.build();
		return uri.toString();
	}
	
	private String getUriForSelf(UriInfo uriInfo, Scooter scooter) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(ScooterResource.class)
				.path(Long.toString(scooter.getId()))
				.build()
				.toString();
		return uri;
	}
	
	@POST
	public Response addScooter(Scooter scooter, @Context UriInfo uriInfo) {
		Scooter newScooter = scooterService.addScooter(scooter);
		String newId = String.valueOf(newScooter.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
				.entity(newScooter)
				.build();
		// return scooterService.addScooter(scooter);
	}
	
	@PUT
	@Path("/{scooterId}")
	public Response updateScooter(@PathParam("scooterId") long id, Scooter scooter, @Context UriInfo uriInfo) {
		
		scooter.setId(id);
		Scooter newScooter = scooterService.updateScooter(scooter);
		String scooterId = String.valueOf(newScooter.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(scooterId).build();
		return Response.created(uri)
				.entity(newScooter)
				.build();
	}
	
	@DELETE
	@Path("/{scooterId}")
	public void deleteScooter(@PathParam("scooterId") long id) {
		scooterService.removeScooter(id);
	}
	
	@Path("/{scooterId}/notes")
    public NoteResource getNoteResource() {
		
		return new NoteResource();
	}
}
