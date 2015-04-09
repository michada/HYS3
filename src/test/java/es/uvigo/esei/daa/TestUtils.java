package es.uvigo.esei.daa;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.core.Response;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;


public final class TestUtils {
	private TestUtils() {}

	//Arreglar
	public static void createFakeContext() throws NamingException {
		final SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
		builder.bind("java:/comp/env/jdbc/haveyouseen_db", createTestingDataSource());
		builder.activate();
	}
	
	//Arrelgar
	private static BasicDataSource createTestingDataSource() {
		final BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/haveyouseen_db?allowMultiQueries=true");
		ds.setUsername("Admin");
		ds.setPassword("admin");
		ds.setMaxActive(100);
		ds.setMaxIdle(30);
		ds.setMaxWait(10000);
		return ds;
	}
	
	public static void clearTestDatabase() throws SQLException {
		final String queries = new StringBuilder()
			.append("DELETE FROM `eventos`;")
			.append("DELETE FROM `usuarios`;")
			//.append("DELETE FROM `asistentes`;")
		.toString();

		final DataSource ds = createTestingDataSource();
		try (Connection connection = ds.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				statement.execute(queries);
			}
		}
	}
	
	public static void initTestDatabase() throws SQLException {
		final String queries = new StringBuilder()
			.append("ALTER TABLE `Usuarios` AUTO_INCREMENT = 1;")
			.append("ALTER TABLE `Eventos` AUTO_INCREMENT = 1;")
			.append("INSERT INTO `usuarios` (`idUsuario`,`login`,`password`,`nombre`) VALUES ('0', 'UsuarioPrueba1','prueba1', 'Pepe');")
			.append("INSERT INTO `usuarios` (`idUsuario`,`login`,`password`,`nombre`) VALUES ('0', 'UsuarioPrueba2','prueba2', 'Maria');")
			.append("INSERT INTO `usuarios` (`idUsuario`,`login`,`password`,`nombre`) VALUES ('0', 'UsuarioPrueba3','prueba3', 'Jose');")
			.append("INSERT INTO `eventos` (`idEvento`,`titulo`,`usuario`,`maxAsistentes`,`inicio`,`fin`) VALUES (0, 'Evento numero 1', 0, '3', '03/05/2015', '09/05/2015');")
			.append("INSERT INTO `eventos` (`idEvento`,`titulo`,`usuario`,`maxAsistentes`,`inicio`,`fin`) VALUES (0, 'Evento numero 2', 1, '2', '03/05/2015', '09/05/2015');")
			.append("INSERT INTO `eventos` (`idEvento`,`titulo`,`usuario`,`maxAsistentes`,`inicio`,`fin`) VALUES (0, 'Evento numero 3', 2, '1', '03/05/2015', '09/05/2015');")
//			.append("INSERT INTO `asistentes` (`usuario`,`evento`) VALUES ('UsuarioPrueba1','Evento numero 1');")
//			.append("INSERT INTO `asistentes` (`usuario`,`evento`) VALUES ('UsuarioPrueba2','Evento numero 1');")
//			.append("INSERT INTO `asistentes` (`usuario`,`evento`) VALUES ('UsuarioPrueba3','Evento numero 1');")
//			.append("INSERT INTO `asistentes` (`usuario`,`evento`) VALUES ('UsuarioPrueba1','Evento numero 2');")
//			.append("INSERT INTO `asistentes` (`usuario`,`evento`) VALUES ('UsuarioPrueba2','Evento numero 2');")
//			.append("INSERT INTO `asistentes` (`usuario`,`evento`) VALUES ('UsuarioPrueba3','Evento numero 3');")
		.toString();

		final DataSource ds = createTestingDataSource();
		try (Connection connection = ds.getConnection()) {
			try (Statement statement = connection.createStatement()) {
				statement.execute(queries);
			}
		}
	}

	public static void assertOkStatus(final Response response) {
		assertEquals("Unexpected status code", Response.Status.OK.getStatusCode(), response.getStatus());
	}

	public static void assertBadRequestStatus(final Response response) {
		assertEquals("Unexpected status code", Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}
}
