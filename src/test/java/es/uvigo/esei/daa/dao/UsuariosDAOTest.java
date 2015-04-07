package es.uvigo.esei.daa.dao;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.uvigo.esei.daa.TestUtils;
import es.uvigo.esei.daa.entities.Usuario;

public class UsuariosDAOTest {
	private UsuariosDAO dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.createFakeContext();
		TestUtils.clearTestDatabase();
	}

	@Before
	public void setUp() throws Exception {
		TestUtils.initTestDatabase();
		this.dao = new UsuariosDAO();
	}

	@After
	public void tearDown() throws Exception {
		TestUtils.clearTestDatabase();
		this.dao = null;
	}

	@Test
	public void testGet() throws DAOException {
		final Usuario user = this.dao.get(2);
		
		assertEquals(2, user.getIdUsuario());
		assertEquals("UsuarioPrueba2", user.getLogin());
		assertEquals("prueba2", user.getPassword());
		assertEquals("Maria", user.getNombre());
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
		this.dao.modify(1, "LoginM", "PassM", "NombreM");
		
		final Usuario user = this.dao.get(1);
		
		assertEquals(1, user.getIdUsuario());
		assertEquals("LoginM", user.getLogin());
		assertEquals("PassM", user.getPassword());
		assertEquals("NombreM", user.getNombre());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyInvalidId() throws DAOException {
		this.dao.modify(100, "LoginM", "PassM", "NombreM");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullLogin() throws DAOException {
		this.dao.modify(1, null, "PassM", "NombreM");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullPassword() throws DAOException {
		this.dao.modify(1, "LoginM", null, "NombreM");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testModifyNullNombre() throws DAOException {
		this.dao.modify(1, "LoginM", "PassM", null);
	}

	@Test
	public void testAdd() throws DAOException {
		final Usuario user = this.dao.add("LoginA", "PassA", "NombreA");
		
		assertEquals("LoginA", user.getLogin());
		assertEquals("PassA", user.getPassword());
		
		final Usuario userGet = this.dao.get(user.getIdUsuario());

		assertEquals(user.getIdUsuario(), userGet.getIdUsuario());
		assertEquals("LoginA", userGet.getLogin());
		assertEquals("PassA", userGet.getPassword());
		assertEquals("NombreA", userGet.getNombre());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullLogin() throws DAOException {
		this.dao.add(null, "PassA", "NombreA");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNullPassword() throws DAOException {
		this.dao.add("LoginA", null, "NombreA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddNullNombre() throws DAOException {
		this.dao.add("LoginA", "PassA", null);
	}
}
