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
				"10/05/2015");

		final Evento event = this.dao.get(1);

		assertEquals(1, event.getIdEvento());
		assertEquals("Evento modificado", event.getTitulo());
		assertEquals(0, event.getUsuario());
		assertEquals(50, event.getMaxAsistentes());
		assertEquals("02/05/2015", event.getinicio());
		assertEquals("10/05/2015", event.getfin());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyInvalidId() throws DAOException {
		this.dao.modify(100, "Evento modificado", 0, 50, "02/05/2015",
				"10/05/2015");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullTitulo() throws DAOException {
		this.dao.modify(1, null, 0, 50, "02/05/2015", "10/05/2015");
	}

	// @Test(expected = IllegalArgumentException.class)
	// public void testModifyNullUsuario() throws DAOException {
	// this.dao.modify(1, "Evento modificado", null, 50, "02/05/2015",
	// "10/05/2015");
	// }

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullAsistentes() throws DAOException {
		this.dao.modify(1, "Evento modificado", 0, 0, "02/05/2015",
				"10/05/2015");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullFechaInicio() throws DAOException {
		this.dao.modify(1, "Evento modificado", 0, 50, null, "10/05/2015");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullFechaFin() throws DAOException {
		this.dao.modify(1, "Evento modificado", 0, 50, "02/05/2015", null);
	}

	@Test
	public void testAdd() throws DAOException {
		final Evento event = this.dao.add("Evento añadido", 2, 1, "05/05/2015",
				"15/05/2015");

		assertEquals("Evento añadido", event.getTitulo());
		assertEquals(2, event.getUsuario());
		assertEquals("05/05/2015", event.getinicio());
		assertEquals("15/05/2015", event.getfin());

		final Evento eventGet = this.dao.get(event.getIdEvento());

		assertEquals(event.getIdEvento(), eventGet.getIdEvento());
		assertEquals("Evento añadido", eventGet.getTitulo());
		assertEquals(2, eventGet.getUsuario());
		assertEquals("05/05/2015", eventGet.getinicio());
		assertEquals("15/05/2015", eventGet.getfin());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullTitulo() throws DAOException {
		this.dao.add(null, 0, 1, "02/05/2015", "10/05/2015");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullAsistentes() throws DAOException {
		this.dao.add("Evento modificado", 0, 0, "02/05/2015", "10/05/2015");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullFechaInicio() throws DAOException {
		this.dao.add("Evento modificado", 0, 1, null, "10/05/2015");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullFechaFin() throws DAOException {
		this.dao.add("Evento modificado", 0, 1, "02/05/2015", null);
	}

	@Test
	public void testOrdenarEventos() throws DAOException {
		this.dao.add("Evento añadido", 1, 50, "05/05/2015", "15/05/2015");

		assertEquals(4, this.dao.list().size());// Comprueba si se ha añadido
												// correctamente

		List<Evento> even = this.dao.ordenar();

		assertEquals(4, even.get(0).getIdEvento());
		assertEquals(50, even.get(0).getMaxAsistentes());

		assertEquals(1, even.get(1).getIdEvento());
		assertEquals(3, even.get(1).getMaxAsistentes());

		assertEquals(2, even.get(2).getIdEvento());
		assertEquals(2, even.get(2).getMaxAsistentes());

		assertEquals(3, even.get(3).getIdEvento());
		assertEquals(1, even.get(3).getMaxAsistentes());

	}
}
