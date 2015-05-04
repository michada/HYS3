package es.uvigo.esei.daa.rest;

import static es.uvigo.esei.daa.TestUtils.assertBadRequestStatus;
import static es.uvigo.esei.daa.TestUtils.assertOkStatus;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import es.uvigo.esei.daa.TestUtils;
import es.uvigo.esei.daa.entities.Evento;

public class EventosTest extends JerseyTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.createFakeContext();
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		TestUtils.initTestDatabase();
	}
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
		
		TestUtils.clearTestDatabase();
	}

	@Override
	protected Application configure() {
		return new ResourceConfig(EventosResource.class)
			.register(JacksonJsonProvider.class)
			.property("com.sun.jersey.api.json.POJOMappingFeature", Boolean.TRUE);
	}

	@Override
	protected void configureClient(ClientConfig config) {
		super.configureClient(config);
		
		config.register(JacksonJsonProvider.class);
		config.property("com.sun.jersey.api.json.POJOMappingFeature", Boolean.TRUE);
	}
	
	@Test
	public void testListOrdenado() throws IOException {
		final Response response = target("eventos").request().get();
		assertOkStatus(response);

		final List<Evento> even = response.readEntity(new GenericType<List<Evento>>(){});
		assertEquals(3, even.size());
		
		assertEquals(3, even.size());

		assertEquals(3, even.get(0).getIdEvento());
		assertEquals(10, even.get(0).getMaxAsistentes());

		assertEquals(1, even.get(1).getIdEvento());
		assertEquals(3, even.get(1).getMaxAsistentes());

		assertEquals(2, even.get(2).getIdEvento());
		assertEquals(2, even.get(2).getMaxAsistentes());
	}

	@Test
	public void testGet() throws IOException {
		final Response response = target("eventos/1").request().get();
		assertOkStatus(response);
		
		final Evento evento = response.readEntity(Evento.class);
		assertEquals(1, evento.getIdEvento());
		assertEquals("Evento numero 1", evento.getTitulo());
		assertEquals(0, evento.getUsuario());
		assertEquals(3, evento.getMaxAsistentes());
		assertEquals("03/05/2015", evento.getinicio());
		assertEquals("09/05/2015", evento.getfin());
		assertEquals("Pontevedra", event.getLocalidad());
		assertEquals("descripcion corta1", event.getDescripcion);
		assertEquals("descripcion larga1", event.getDescripcionDetallada());
		assertEquals("Libros", event.getCategoria());
		assertEquals("Local 1", event.getLocal());
	}

	@Test
	public void testGetInvalidId() throws IOException {
		assertBadRequestStatus(target("eventos/100").request().get());
	}

	@Test
	public void testAdd() throws IOException {
		final Form form = new Form();
		form.param("titulo", "Evento añadido");
		form.param("usuario", "2");
		form.param("inicio", "02/05/2015");
		form.param("fin", "10/05/2015");
		form.param("maxAsistentes", "4");
		form.param("localidad", "Madrid");
		form.param("descripcion", "descripcion corta4");
		form.param("descripcionDetallada", "descripcion larga4");
		form.param("categoria", "Peliculas");
		form.param("local", "Local 4");
		
		final Response response = target("eventos")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		assertOkStatus(response);
		
		final Evento evento = response.readEntity(Evento.class);
		assertEquals(4, evento.getIdEvento());
		assertEquals("Evento añadido", evento.getTitulo());
		assertEquals(2, evento.getUsuario());
		assertEquals("02/05/2015", evento.getinicio());
		assertEquals("10/05/2015", evento.getfin());
		assertEquals("Madrid", evento.getLocalidad());
		assertEquals("descripcion corta4", evento.getDescripcion);
		assertEquals("descripcion larga4", evento.getDescripcionDetallada());
		assertEquals("Peliculas", evento.getCategoria());
		assertEquals("Local 4", evento.getLocal());
	}

	@Test
	public void testAddMissingTitulo() throws IOException {
		final Form form = new Form();
		form.param("usuario", "UsuarioPrueba1");
		form.param("inicio", "02/05/2015");
		form.param("fin", "10/05/2015");
		form.param("localidad", "Madrid");
		form.param("descripcion", "descripcion corta4");
		form.param("descripcionDetallada", "descripcion larga4");
		form.param("categoria", "Peliculas");
		form.param("local", "Local 4");
		
		final Response response = target("eventos")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		
		assertBadRequestStatus(response);
	}

	@Test
	public void testModify() throws IOException {
		final Form form = new Form();
		form.param("idEvento", "1");
		form.param("titulo", "Evento modificado");
		form.param("usuario", "1");
		form.param("maxAsistentes", "2");
		form.param("inicio", "02/05/2015");
		form.param("fin", "10/05/2015");
		form.param("localidad", "Madrid");
		form.param("descripcion", "descripcion corta modificada");
		form.param("descripcionDetallada", "descripcion larga modificada");
		form.param("categoria", "Series");
		form.param("local", "Local 5");
		
		final Response response = target("eventos/1")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		assertOkStatus(response);
		
		final Evento evento = response.readEntity(Evento.class);
		assertEquals(1, evento.getIdEvento());
		assertEquals("Evento modificado", evento.getTitulo());
		assertEquals(1, evento.getUsuario());
		assertEquals("02/05/2015", evento.getinicio());
		assertEquals("10/05/2015", evento.getfin());
		assertEquals("Madrid", evento.getLocalidad());
		assertEquals("descripcion corta modificada4", evento.getDescripcion);
		assertEquals("descripcion larga modificada", evento.getDescripcionDetallada());
		assertEquals("Series", evento.getCategoria());
		assertEquals("Local 5", evento.getLocal());
	}

	@Test
	public void testModifyInvalidId() throws IOException {
		final Form form = new Form();
		form.param("titulo", "Evento modificado");
		form.param("usuario", "UsuarioPrueba1");
		form.param("inicio", "02/05/2015");
		form.param("fin", "10/05/2015");
		form.param("localidad", "Madrid");
		form.param("descripcion", "descripcion corta modificada");
		form.param("descripcionDetallada", "descripcion larga modificada");
		form.param("categoria", "Series");
		form.param("local", "Local 5");
		
		final Response response = target("eventos/100")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

		assertBadRequestStatus(response);
	}

	@Test
	public void testDelete() throws IOException {
		final Response response = target("eventos/2").request().delete();
		assertOkStatus(response);
		
		assertEquals(2, (int) response.readEntity(Integer.class));
	}

	@Test
	public void testDeleteInvalidId() throws IOException {
		assertBadRequestStatus(target("eventos/100").request().delete());
	}
	
	
	@Test
	public void testBuscar() throws IOException{
		final Form form = new Form();
		form.param("titulo", "Evento de prueba");
		form.param("usuario", "2");
		form.param("inicio", "02/05/2015");
		form.param("fin", "10/05/2015");
		form.param("maxAsistentes", "10");
		form.param("localidad", "Madrid");
		form.param("descripcion", "descripcion corta4");
		form.param("descripcionDetallada", "descripcion larga4");
		form.param("categoria", "Peliculas");
		form.param("local", "Local 4");
		
		final Response response = target("eventos")
			.request(MediaType.APPLICATION_JSON_TYPE)
			.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		assertOkStatus(response);
		
		final Response response2 = target("eventos/Evento").request().get();
		assertOkStatus(response2);
		
		final List<Evento> evento = response2.readEntity(new GenericType<List<Evento>>(){});
		assertEquals(3, evento.size());
		
		assertEquals("Evento de prueba", evento.get(0).getTitulo());
		assertEquals("Evento numero 1", evento.get(1).getTitulo());
		assertEquals("Evento numero 2", evento.get(2).getTitulo());
	}
}
