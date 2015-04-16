package es.uvigo.esei.daa.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.uvigo.esei.daa.dao.DAOException;
import es.uvigo.esei.daa.dao.EventosDAO;

@Path("/eventos")
@Produces(MediaType.APPLICATION_JSON)
public class EventosResource {
	private final static Logger LOG = Logger.getLogger("EventosResource");
	
	private final EventosDAO dao;
	
	public EventosResource() {
		this.dao = new EventosDAO();
	}

	@GET
	public Response listOrdenado() {
		try {
			return Response.ok(this.dao.ordenar(), MediaType.APPLICATION_JSON).build();
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error listando eventos", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Path("/{idEvento}")
	public Response get(
		@PathParam("idEvento") int idEvento
	) {
		try {
			return Response.ok(this.dao.get(idEvento), MediaType.APPLICATION_JSON).build();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST)
				.entity(iae.getMessage()).build();
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error cogiendo un evento", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{idEvento}")
	public Response delete(
		@PathParam("idEvento") int idEvento
	) {
		try {
			this.dao.delete(idEvento);
			
			return Response.ok(idEvento).build();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST)
				.entity(iae.getMessage()).build();
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error borrando un evento", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@PUT
	@Path("/{idEvento}")
	public Response modify(
		@PathParam("idEvento") String idEvento, 
		@FormParam("titulo") String titulo, 
		@FormParam("usuario") String usuario,
		@FormParam("maxAsistentes") String maxAsistentes, 
		@FormParam("inicio") String inicio,
		@FormParam("fin") String fin
	) {
		try {
			return Response.ok(this.dao.modify(Integer.parseInt(idEvento), titulo, Integer.parseInt(usuario), Integer.parseInt(maxAsistentes), inicio, fin)).build();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST)
				.entity(iae.getMessage()).build();
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error modificando un evento", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@POST
	public Response add(
			@FormParam("titulo") String titulo, 
			@FormParam("usuario") int usuario,
			@FormParam("maxAsistentes") int maxAsistentes, 
			@FormParam("inicio") String inicio,
			@FormParam("fin") String fin
	) {
		try {
			return Response.ok(this.dao.add(titulo, usuario, maxAsistentes, inicio, fin)).build();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST)
				.entity(iae.getMessage()).build();
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error a�adiendo un evento", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
}
