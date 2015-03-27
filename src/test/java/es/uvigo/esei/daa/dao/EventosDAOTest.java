package es.uvigo.esei.daa.dao;

import static org.junit.Assert.assertEquals;

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
		
		assertEquals(2, event.getId());
		assertEquals("Evento numero 2", event.getTitulo());
		assertEquals("UsuarioPrueba2", event.getUsuario());
		assertEquals("03/05/2015", event.getFechaInicio());
		assertEquals("09/05/2015", event.getfechaFin());
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
		this.dao.modify(1, "Evento modificado", "UsuarioPrueba1", "02/05/2015", "10/05/2015");
		
		final Evento event = this.dao.get(1);
		
		assertEquals(1, event.getId());
		assertEquals("Evento modificado", event.getTitulo());
		assertEquals("UsuarioPrueba1", event.getUsuario());
		assertEquals("02/05/2015", event.getFechaInicio());
		assertEquals("10/05/2015", event.getfechaFin());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyInvalidId() throws DAOException {
		this.dao.modify(100, "Evento modificado", "UsuarioPrueba1", "02/05/2015", "10/05/2015");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullTitulo() throws DAOException {
		this.dao.modify(1, null, "UsuarioPrueba1", "02/05/2015", "10/05/2015");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullUsuario() throws DAOException {
		this.dao.modify(1, "Evento modificado", null, "02/05/2015", "10/05/2015");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullFechaInicio() throws DAOException {
		this.dao.modify(1, "Evento modificado", "UsuarioPrueba1", null, "10/05/2015");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullFechaFin() throws DAOException {
		this.dao.modify(1, "Evento modificado", "UsuarioPrueba1", "02/05/2015", null);
	}

	@Test
	public void testAdd() throws DAOException {
		final Evento event = this.dao.add("Evento añadido", "UsuarioPrueba3", "05/05/2015", "15/05/2015");
		
		assertEquals("Evento añadido", event.getTitulo());
		assertEquals("UsuarioPrueba3", event.getUsuario());
		assertEquals("05/05/2015", event.getFechaInicio());
		assertEquals("15/05/2015", event.getfechaFin());
		
		final Evento eventGet = this.dao.get(event.getId());

		assertEquals(event.getId(), eventGet.getId());
		assertEquals("Evento añadido", eventGet.getTitulo());
		assertEquals("UsuarioPrueba3", eventGet.getUsuario());
		assertEquals("05/05/2015", eventGet.getFechaInicio());
		assertEquals("15/05/2015", eventGet.getfechaFin());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullTitulo() throws DAOException {
		this.dao.add(null, "UsuarioPrueba1", "02/05/2015", "10/05/2015");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullUsuario() throws DAOException {
		this.dao.add("Evento modificado", null, "02/05/2015", "10/05/2015");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddNullFechaInicio() throws DAOException {
		this.dao.add("Evento modificado", "UsuarioPrueba1", null, "10/05/2015");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddNullFechaFin() throws DAOException {
		this.dao.add("Evento modificado", "UsuarioPrueba1", "02/05/2015", null);
	}
}
