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

import es.uvigo.esei.daa.entities.Usuario;

//Clase PeopleDAO de DAA Example a la que se le modifico el nombre para que concordara con los test
public class UsuariosDAO extends DAO {
	private final static Logger LOG = Logger.getLogger("PeopleDAO");
	
	public Usuario get(int id)
	throws DAOException, IllegalArgumentException {
		try (final Connection conn = this.getConnection()) {
			final String query = "SELECT * FROM Usuarios WHERE idUsuario=?";
			
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setInt(1, id);
				
				try (ResultSet result = statement.executeQuery()) {
					if (result.next()) {
						return new Usuario(
							result.getInt("idUsuario"),
							result.getString("login"),
							result.getString("password"),
							result.getString("nombre")
						);
					} else {
						throw new IllegalArgumentException("Invalid idUsuario");
					}
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error getting a person", e);
			throw new DAOException(e);
		}
	}
	
	public List<Usuario> list() throws DAOException {
		try (final Connection conn = this.getConnection()) {
			try (Statement statement = conn.createStatement()) {
				try (ResultSet result = statement.executeQuery("SELECT * FROM Usuarios")) {
					final List<Usuario> users = new LinkedList<>();
					
					while (result.next()) {
						users.add(new Usuario(
								result.getInt("idUsuario"),
								result.getString("login"),
								result.getString("password"),
								result.getString("nombre")
						));
					}
					
					return users;
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error listing users", e);
			throw new DAOException(e);
		}
	}
	
	public void delete(int id)
	throws DAOException, IllegalArgumentException {
		try (final Connection conn = this.getConnection()) {
			final String query = "DELETE FROM Usuarios WHERE idUsuario=?";
			
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setInt(1, id);
				
				if (statement.executeUpdate() != 1) {
					throw new IllegalArgumentException("Invalid id");
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error deleting a user", e);
			throw new DAOException(e);
		}
	}
	
	public Usuario modify(int idUsuario, String login, String password, String nombre)
	throws DAOException, IllegalArgumentException {
		if (login == null || password == null || nombre == null) {
			throw new IllegalArgumentException("login, password or nombre can't be null");
		}
		
		try (final Connection conn = this.getConnection()) {
			final String query = "UPDATE Usuarios SET login=?, password=?, nombre=? WHERE idUsuario=?";
			
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setString(1, login);
				statement.setString(2, password);
				statement.setString(3, nombre);
				statement.setInt(4, idUsuario);
				
				if (statement.executeUpdate() == 1) {
					return new Usuario(idUsuario, login, password, nombre); 
				} else {
					throw new IllegalArgumentException("login, password or nombre can't be null");
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error modifying a user", e);
			throw new DAOException();
		}
	}
	
	public Usuario add(String login, String password, String nombre)
	throws DAOException, IllegalArgumentException {
		if (login == null || password == null || nombre == null) {
			throw new IllegalArgumentException("login, password or nombre can't be null");
		}
		
		try (final Connection conn = this.getConnection()) {
			final String query = "INSERT INTO Usuarios VALUES(null, ?, ?, ?)";
			
			try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, login);
				statement.setString(2, password);
				statement.setString(3, nombre);
				
				if (statement.executeUpdate() == 1) {
					try (ResultSet resultKeys = statement.getGeneratedKeys()) {
						if (resultKeys.next()) {
							return new Usuario(resultKeys.getInt(1), login, password, nombre);
						} else {
							LOG.log(Level.SEVERE, "Error retrieving inserted id");
							throw new SQLException("Error retrieving inserted id");
						}
					}
				} else {
					LOG.log(Level.SEVERE, "Error inserting value");
					throw new SQLException("Error inserting value");
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error adding a user", e);
			throw new DAOException(e);
		}
	}
}
