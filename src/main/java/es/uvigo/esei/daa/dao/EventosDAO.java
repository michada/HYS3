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
								result.getString("fin"));
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
										.getString("fin")));
					}

					return eventos;
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error listando eventos", e);
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

	public Evento modify(int idEvento, String titulo, int usuario,
			int maxAsistentes, String inicio, String fin) throws DAOException,
			IllegalArgumentException {
		if (titulo == null || maxAsistentes > 0
				|| inicio == null || fin == null) {
			throw new IllegalArgumentException(
					"titulo, usuario, inicio y fin no pueden ser nulos y maxAsistentes debe ser mayor que 0");
		}

		try (final Connection conn = this.getConnection()) {
			final String query = "UPDATE Eventos SET titulo=?, usuario=?, maxAsistentes=?, inicio=?, fin=? WHERE idEvento=?";

			try (PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setString(1, titulo);
				statement.setInt(2, usuario);
				statement.setInt(3, maxAsistentes);
				statement.setString(4, inicio);
				statement.setString(5, fin);
				statement.setInt(6, idEvento);

				if (statement.executeUpdate() == 1) {
					return new Evento(idEvento, titulo, usuario, maxAsistentes,
							inicio, fin);
				} else {
					throw new IllegalArgumentException(
							"titulo, usuario, inicio y fin no pueden ser nulos y maxAsistentes debe ser mayor que 0");
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error modificando el evento", e);
			throw new DAOException();
		}
	}

	public Evento add(String titulo, int usuario, int maxAsistentes,
			String inicio, String fin) throws DAOException,
			IllegalArgumentException {
		if (titulo == null || maxAsistentes > 0
				|| inicio == null || fin == null) {
			throw new IllegalArgumentException(
					"titulo, usuario, inicio y fin no pueden ser nulos y maxAsistentes debe ser mayor que 0");
		}

		try (final Connection conn = this.getConnection()) {
			final String query = "INSERT INTO Eventos VALUES(null, ?, ?, ?, ?, ?)";

			try (PreparedStatement statement = conn.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, titulo);
				statement.setInt(2, usuario);
				statement.setInt(3, maxAsistentes);
				statement.setString(4, inicio);
				statement.setString(5, fin);

				if (statement.executeUpdate() == 1) {
					try (ResultSet resultKeys = statement.getGeneratedKeys()) {
						if (resultKeys.next()) {
							return new Evento(resultKeys.getInt(1), titulo,
									usuario, maxAsistentes, inicio, fin);
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

//	public List<Evento> ordenar() throws DAOException {
//		try (final Connection conn = this.getConnection()) {
//			try (Statement statement = conn.createStatement()) {
//				try (ResultSet result = statement
//						.executeQuery("SELECT * FROM Eventos ORDER BY maxAsistentes")) {
//					final List<Evento> eventos = new LinkedList<>();
//
//					while (result.next()) {
//						eventos.add(new Evento(result.getInt("idEvento"),
//								result.getString("titulo"), result
//										.getString("usuario"), result
//										.getInt("maxAsistentes"), result
//										.getString("inicio"), result
//										.getString("fin")));
//					}
//
//					return eventos;
//				}
//			}
//		} catch (SQLException e) {
//			LOG.log(Level.SEVERE, "Error ordenando eventos", e);
//			throw new DAOException(e);
//		}
//	}
}