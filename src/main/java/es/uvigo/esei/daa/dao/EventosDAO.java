package es.uvigo.esei.daa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.uvigo.esei.daa.entities.Evento;

//Clase EventosDAO 
public class EventosDAO extends DAO {
	private final static Logger LOG = Logger.getLogger("EventosDAO");

	public Evento get(int id) throws DAOException, IllegalArgumentException {
		try (final Connection conn = this.getConnection()) {
			final String query = "SELECT * FROM Eventos WHERE idEvento=?";

			try (PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setInt(1, id);

				try (ResultSet result = statement.executeQuery()) {
					if (result.next()) {
						return new Evento(result.getInt("idEvento"),
								result.getString("titulo"),
								result.getInt("usuario"),
								result.getInt("maxAsistentes"),
								result.getString("inicio"),
								result.getString("fin"),
								result.getString("localidad"),
								result.getString("descripcion"),
								result.getString("descripcionDetallada"),
								result.getString("categoria"),
								result.getString("local"));
					} else {
						throw new IllegalArgumentException("Invalid idEvento");
					}
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error cogiendo evento", e);
			throw new DAOException(e);
		}
	}

	public List<Evento> list() throws DAOException {
		try (final Connection conn = this.getConnection()) {
			try (Statement statement = conn.createStatement()) {
				try (ResultSet result = statement
						.executeQuery("SELECT * FROM Eventos")) {
					final List<Evento> eventos = new LinkedList<>();

					while (result.next()) {
						eventos.add(new Evento(result.getInt("idEvento"),
								result.getString("titulo"), result
										.getInt("usuario"), result
										.getInt("maxAsistentes"), result
										.getString("inicio"), result
										.getString("fin"), result
										.getString("localidad"), result
										.getString("descripcion"), result
										.getString("descripcionDetallada"),
								result.getString("categoria"), result
										.getString("local")));
					}

					return eventos;
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error listando eventos", e);
			throw new DAOException(e);
		}
	}

	public Evento add(String titulo, int usuario, int maxAsistentes,
			String inicio, String fin, String localidad, String descripcion,
			String descripcionDetallada, String categoria, String local)
			throws DAOException, IllegalArgumentException {
		if (titulo == null || inicio == null || fin == null || usuario <= 0) {
			throw new IllegalArgumentException(
					"titulo, usuario, inicio y fin no pueden ser nulos y maxAsistentes debe ser mayor que 0");
		}
		try (final Connection conn = this.getConnection()) {
			final String query = "INSERT INTO Eventos VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try (PreparedStatement statement = conn.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, titulo);
				statement.setInt(2, usuario);
				statement.setInt(3, maxAsistentes);
				statement.setString(4, inicio);
				statement.setString(5, fin);
				statement.setString(6, localidad);
				statement.setString(7, descripcion);
				statement.setString(8, descripcionDetallada);
				statement.setString(9, categoria);
				statement.setString(10, local);
				

				if (statement.executeUpdate() == 1) {
					try (ResultSet resultKeys = statement.getGeneratedKeys()) {
						if (resultKeys.next()) {
							return new Evento(resultKeys.getInt(1), titulo,
									usuario, maxAsistentes, inicio, fin,
									localidad, descripcion,
									descripcionDetallada, categoria, local);
						} else {
							LOG.log(Level.SEVERE,
									"Error retrieving inserted id");
							throw new SQLException(
									"Error retrieving inserted id");
						}
					}
				} else {
					LOG.log(Level.SEVERE, "Error inserting value");
					throw new SQLException("Error inserting value");
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error añadiendo un evento", e);
			throw new DAOException(e);
		}
	}

	public void delete(int id) throws DAOException, IllegalArgumentException {
		try (final Connection conn = this.getConnection()) {
			final String query = "DELETE FROM Eventos WHERE idEvento=?";

			try (PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setInt(1, id);

				if (statement.executeUpdate() != 1) {
					throw new IllegalArgumentException("Invalid id");
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error borrando el evento", e);
			throw new DAOException(e);
		}
	}

	public List<Evento> filtrarLocalidad(String localidad) throws DAOException {
		try (final Connection conn = this.getConnection()) {
			final String query = "SELECT * FROM Eventos WHERE localidad=? ORDER BY maxAsistentes DESC";
			
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setString(1, localidad);
				
					final List<Evento> eventos = new LinkedList<>();
					try (ResultSet result = statement.executeQuery()) {
					while (result.next()) {
						eventos.add(new Evento(result.getInt("idEvento"),
								result.getString("titulo"), result
										.getInt("usuario"), result
										.getInt("maxAsistentes"), result
										.getString("inicio"), result
										.getString("fin"), result
										.getString("localidad"), result
										.getString("descripcion"), result
										.getString("descripcionDetallada"),
								result.getString("categoria"), result
										.getString("local")));
					}

					return eventos;
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error ordenando eventos", e);
			throw new DAOException(e);
		}
	}

	public List<Evento> filtrarCategoria(String categoria) throws DAOException {
		try (final Connection conn = this.getConnection()) {
			final String query = "SELECT * FROM Eventos WHERE categoria=? ORDER BY maxAsistentes DESC";
			
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setString(1, categoria);
				
					final List<Evento> eventos = new LinkedList<>();
					try (ResultSet result = statement.executeQuery()) {
					while (result.next()) {
						eventos.add(new Evento(result.getInt("idEvento"),
								result.getString("titulo"), result
										.getInt("usuario"), result
										.getInt("maxAsistentes"), result
										.getString("inicio"), result
										.getString("fin"), result
										.getString("localidad"), result
										.getString("descripcion"), result
										.getString("descripcionDetallada"),
								result.getString("categoria"), result
										.getString("local")));
					}

					return eventos;
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error ordenando eventos", e);
			throw new DAOException(e);
		}
	}

	public List<Evento> buscar(String cadena) throws DAOException {
		try (final Connection conn = this.getConnection()) {
			final String query = "SELECT * FROM Eventos WHERE titulo=? ORDER BY maxAsistentes DESC";
			
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setString(1, cadena);
				
					final List<Evento> eventos = new LinkedList<>();
					try (ResultSet result = statement.executeQuery()) {
					while (result.next()) {
						eventos.add(new Evento(result.getInt("idEvento"),
								result.getString("titulo"), result
										.getInt("usuario"), result
										.getInt("maxAsistentes"), result
										.getString("inicio"), result
										.getString("fin"), result
										.getString("localidad"), result
										.getString("descripcion"), result
										.getString("descripcionDetallada"),
								result.getString("categoria"), result
										.getString("local")));
					}

					return eventos;
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error ordenando eventos", e);
			throw new DAOException(e);
		}
	}
}