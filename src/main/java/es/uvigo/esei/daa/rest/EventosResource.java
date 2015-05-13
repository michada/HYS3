package es.uvigo.esei.daa.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

	public Response filtrarLocalidad(String localidad) {
		try {
			return Response.ok(this.dao.filtrarLocalidad(localidad), MediaType.APPLICATION_JSON).build();
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error listando eventos", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	public Response filtrarCategoria(String categoria) {
		try {
			return Response.ok(this.dao.filtrarCategoria(categoria), MediaType.APPLICATION_JSON).build();
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error listando eventos", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	public Response buscar(String cadena) {
		try {
			return Response.ok(this.dao.buscar(cadena), MediaType.APPLICATION_JSON).build();
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error listando eventos", e);
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	@GET
	public Response obtener(
			@QueryParam("cadenaBusqueda") String cadenaBusqueda,
			@QueryParam("localidad") String localidad,
			@QueryParam("categoria") String categoria
			){
		try {
			if ( cadenaBusqueda == null && categoria == null && localidad == null  ) {
				return filtrarLocalidad("Pontevedra");
			}
			else if (cadenaBusqueda == null && categoria == null){
				return filtrarLocalidad(localidad);
			}else if(cadenaBusqueda == null && localidad == null){
				return filtrarCategoria(categoria);
			}else{
				System.out.print(cadenaBusqueda);
				return Response.ok(this.dao.buscar(cadenaBusqueda), MediaType.APPLICATION_JSON).build();
			}
		} catch (DAOException e) {
			LOG.log(Level.SEVERE, "Error buscando eventos", e);
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
}
