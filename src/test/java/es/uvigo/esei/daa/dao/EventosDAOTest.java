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
		assertEquals("descripcion corta2", event.getDescripcion());
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
	public void testFiltrarLocalidad() throws DAOException {
		this.dao.add("Evento a�adido", "usuario", 5, "05/05/2015", "15/05/2015",
				"Pontevedra", "descripcion corta5", "descripcion larga5",
				"Libros", "Local 5");

		assertEquals(4, this.dao.list().size());// Comprueba si se ha a�adido
												// correctamente

		List<Evento> even = this.dao.filtrarLocalidad("Pontevedra");
		
		assertEquals(3, even.size());

		assertEquals(4, even.get(0).getIdEvento());
		assertEquals(1, even.get(1).getIdEvento());
		assertEquals(2, even.get(2).getIdEvento());
	}	
	
	@Test
	public void testFiltrarCategoria() throws DAOException {
		this.dao.add("Evento a�adido", "usuario", 5, "05/05/2015", "15/05/2015",
				"Pontevedra", "descripcion corta5", "descripcion larga5",
				"Libros", "Local 5");

		assertEquals(4, this.dao.list().size());// Comprueba si se ha a�adido
												// correctamente

		List<Evento> even = this.dao.filtrarLocalidad("Libros");
		
		assertEquals(2, even.size());

		assertEquals(4, even.get(0).getIdEvento());
		assertEquals(1, even.get(1).getIdEvento());
	}
	

	@Test
	public void buscarEventos() throws DAOException {
		List<Evento> even = this.dao.buscar("Evento");

		assertEquals(3, even.size());

		assertEquals("Evento numero 1", even.get(0).getTitulo());
		assertEquals("Evento numero 2", even.get(1).getTitulo());
		assertEquals("Evento numero 3", even.get(2).getTitulo());

		this.dao.add("Prueba", "usuario", 1, "05/05/2015", "15/05/2015", "Barcelona",
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
