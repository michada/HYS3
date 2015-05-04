package es.uvigo.esei.daa.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uvigo.esei.daa.TestUtils;
import es.uvigo.esei.daa.entities.Evento;

public class EventosDAOTest {
	private EventosDAO dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.createFakeContext();
		TestUtils.clearTestDatabase();
	}

	@Before
	public void setUp() throws Exception {
		TestUtils.initTestDatabase();
		this.dao = new EventosDAO();
	}

	@After
	public void tearDown() throws Exception {
		TestUtils.clearTestDatabase();
		this.dao = null;
	}

	@Test
	public void testGet() throws DAOException {
		final Evento event = this.dao.get(2);

		assertEquals(2, event.getIdEvento());
		assertEquals("Evento numero 2", event.getTitulo());
		assertEquals(1, event.getUsuario());
		assertEquals(2, event.getMaxAsistentes());
		assertEquals("03/05/2015", event.getinicio());
		assertEquals("09/05/2015", event.getfin());
		assertEquals("Pontevedra", event.getLocalidad());
		assertEquals("descripcion corta2", event.getDescripcion);
		assertEquals("descripcion larga2", event.getDescripcionDetallada());
		assertEquals("Peliculas", event.getCategoria());
		assertEquals("Local 2", event.getLocal());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetInvalidId() throws DAOException {
		this.dao.get(100);
	}

	@Test
	public void testList() throws DAOException {
		assertEquals(3, this.dao.list().size());
	}

	@Test
	public void testDelete() throws DAOException {
		this.dao.delete(2);

		assertEquals(2, this.dao.list().size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteInvalidId() throws DAOException {
		this.dao.delete(100);
	}

	@Test
	public void testModify() throws DAOException {
		this.dao.modify(1, "Evento modificado", 0, 50, "02/05/2015",
				"10/05/2015", "Pontevedra", "descripcion corta modificada",
				"descripcion larga modificada", "Libros", "Local 3");

		final Evento event = this.dao.get(1);

		assertEquals(1, event.getIdEvento());
		assertEquals("Evento modificado", event.getTitulo());
		assertEquals(0, event.getUsuario());
		assertEquals(50, event.getMaxAsistentes());
		assertEquals("02/05/2015", event.getinicio());
		assertEquals("10/05/2015", event.getfin());
		assertEquals("Pontevedra", event.getLocalidad());
		assertEquals("descripcion corta modificada", event.getDescripcion);
		assertEquals("descripcion larga modificada",
				event.getDescripcionDetallada());
		assertEquals("Libros", event.getCategoria());
		assertEquals("Local 3", event.getLocal());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyInvalidId() throws DAOException {
		this.dao.modify(100, "Evento modificado", 0, 50, "02/05/2015",
				"10/05/2015", "Pontevedra", "descripcion corta modificada",
				"descripcion larga modificada", "Libros", "Local 3");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullTitulo() throws DAOException {
		this.dao.modify(1, null, 0, 50, "02/05/2015", "10/05/2015",
				"Pontevedra", "descripcion corta modificada",
				"descripcion larga modificada", "Libros", "Local 3");
	}

	// @Test(expected = IllegalArgumentException.class)
	// public void testModifyNullUsuario() throws DAOException {
	// this.dao.modify(1, "Evento modificado", null, 50, "02/05/2015",
	// "10/05/2015");
	// }

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullAsistentes() throws DAOException {
		this.dao.modify(1, "Evento modificado", 0, 0, "02/05/2015",
				"10/05/2015", "Pontevedra", "descripcion corta modificada",
				"descripcion larga modificada", "Libros", "Local 3");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullFechaInicio() throws DAOException {
		this.dao.modify(1, "Evento modificado", 0, 50, null, "10/05/2015",
				"Pontevedra", "descripcion corta modificada",
				"descripcion larga modificada", "Libros", "Local 3");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullFechaFin() throws DAOException {
		this.dao.modify(1, "Evento modificado", 0, 50, "02/05/2015", null,
				"Pontevedra", "descripcion corta modificada",
				"descripcion larga modificada", "Libros", "Local 3");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullLocalidad() throws DAOException {
		this.dao.modify(1, "Evento modificado", 0, 50, "02/05/2015",
				"10/05/2015", null, "descripcion corta modificada",
				"descripcion larga modificada", "Libros", "Local 3");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullDescripcionCorta() throws DAOException {
		this.dao.modify(1, "Evento modificado", 0, 50, "02/05/2015",
				"10/05/2015", "Pontevedra", null,
				"descripcion larga modificada", "Libros", "Local 3");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullDescripcionLarga() throws DAOException {
		this.dao.modify(1, "Evento modificado", 0, 50, "02/05/2015",
				"10/05/2015", "Pontevedra", "descripcion corta modificada",
				null, "Libros", "Local 3");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullCategoria() throws DAOException {
		this.dao.modify(1, "Evento modificado", 0, 50, "02/05/2015",
				"10/05/2015", "Pontevedra", "descripcion corta modificada",
				"descripcion larga modificada", null, "Local 3");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullLocal() throws DAOException {
		this.dao.modify(1, "Evento modificado", 0, 50, "02/05/2015",
				"10/05/2015", "Pontevedra", "descripcion corta modificada",
				"descripcion larga modificada", "Libros", null);
	}

	@Test
	public void testAdd() throws DAOException {
		final Evento event = this.dao.add("Evento añadido", 2, 1, "05/05/2015",
				"15/05/2015", "Barcelona", "descripcion corta5",
				"descripcion larga5", "Libros", "Local 5");

		assertEquals("Evento añadido", event.getTitulo());
		assertEquals(2, event.getUsuario());
		assertEquals("05/05/2015", event.getinicio());
		assertEquals("15/05/2015", event.getfin());
		assertEquals("Barcelona", event.getLocalidad());
		assertEquals("descripcion corta5", event.getDescripcion);
		assertEquals("descripcion larga5", event.getDescripcionDetallada());
		assertEquals("Libros", event.getCategoria());
		assertEquals("Local 5", event.getLocal());

		final Evento eventGet = this.dao.get(event.getIdEvento());

		assertEquals(event.getIdEvento(), eventGet.getIdEvento());
		assertEquals("Evento añadido", eventGet.getTitulo());
		assertEquals(2, eventGet.getUsuario());
		assertEquals("05/05/2015", eventGet.getinicio());
		assertEquals("15/05/2015", eventGet.getfin());
		assertEquals("Pontevedra", eventGet.getLocalidad());
		assertEquals("descripcion corta modificada", eventGet.getDescripcion);
		assertEquals("descripcion larga modificada",
				eventGet.getDescripcionDetallada());
		assertEquals("Libros", eventGet.getCategoria());
		assertEquals("Local 3", eventGet.getLocal());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullTitulo() throws DAOException {
		this.dao.add(null, 2, 1, "05/05/2015", "15/05/2015", "Barcelona",
				"descripcion corta5", "descripcion larga5", "Libros", "Local 5");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullAsistentes() throws DAOException {
		this.dao.add("Evento añadido", 2, 0, "05/05/2015", "15/05/2015",
				"Barcelona", "descripcion corta5", "descripcion larga5",
				"Libros", "Local 5");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullFechaInicio() throws DAOException {
		this.dao.add("Evento añadido", 2, 1, null, "15/05/2015", "Barcelona",
				"descripcion corta5", "descripcion larga5", "Libros", "Local 5");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullFechaFin() throws DAOException {
		this.dao.add("Evento añadido", 2, 1, "05/05/2015", null, "Barcelona",
				"descripcion corta5", "descripcion larga5", "Libros", "Local 5");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullLocalidad() throws DAOException {
		this.dao.add("Evento añadido", 2, 1, "05/05/2015", "15/05/2015", null,
				"descripcion corta5", "descripcion larga5", "Libros", "Local 5");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullDescripcionCorta() throws DAOException {
		this.dao.add("Evento añadido", 2, 1, "05/05/2015", "15/05/2015",
				"Barcelona", null, "descripcion larga5", "Libros", "Local 5");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullDescripcionLaarga() throws DAOException {
		this.dao.add("Evento añadido", 2, 1, "05/05/2015", "15/05/2015",
				"Barcelona", "descripcion corta5", null, "Libros", "Local 5");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullCategoria() throws DAOException {
		this.dao.add("Evento añadido", 2, 1, "05/05/2015", "15/05/2015",
				"Barcelona", "descripcion corta5", "descripcion larga5", null,
				"Local 5");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullLocal() throws DAOException {
		this.dao.add("Evento añadido", 2, 1, "05/05/2015", "15/05/2015",
				"Barcelona", "descripcion corta5", "descripcion larga5",
				"Libros", null);
	}

	@Test
	public void testOrdenarEventos() throws DAOException {
		this.dao.add("Evento añadido", 2, 5, "05/05/2015", "15/05/2015",
				"Pontevedra", "descripcion corta5", "descripcion larga5",
				"Libros", "Local 5");

		assertEquals(4, this.dao.list().size());// Comprueba si se ha añadido
												// correctamente

		List<Evento> even = this.dao.ordenar("Pontevedra");
		
		assertEquals(3, even.size());

		assertEquals(4, even.get(0).getIdEvento());
		assertEquals(1, even.get(1).getIdEvento());
		assertEquals(2, even.get(2).getIdEvento());
	}

	@Test
	public void buscarEventos() throws DAOException {
		List<Evento> even = this.dao.buscar("Evento");

		assertEquals(3, even.size());

		assertEquals("Evento numero 1", even.get(0).getTitulo());
		assertEquals("Evento numero 2", even.get(1).getTitulo());
		assertEquals("Evento numero 3", even.get(2).getTitulo());

		this.dao.add("Prueba", 2, 1, "05/05/2015", "15/05/2015", "Barcelona",
				"descripcion corta5", "descripcion larga5", "Libros", "Local 5");
		
		List<Evento> even2 = this.dao.buscar("Pru");

		assertEquals(1, even2.size());
		
		assertEquals("Prueba", even.get(0).getTitulo());
	}
	
	@Test void buscarEventosNullTitulo() throws DAOException{
		List<Evento> even = this.dao.buscar(null);

		assertEquals(3, even.size());
		
		List<Evento> even2 = this.dao.buscar(" ");

		assertEquals(3, even2.size());
	}
	
	@Test void buscarEventosNullResultado() throws DAOException{
		List<Evento> even = this.dao.buscar("Nunca va a encontrar esto");

		assertEquals(0, even.size());
	}
}
