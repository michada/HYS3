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
			final String query = "SELECT * FROM people WHERE id=?";
			
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setInt(1, id);
				
				try (ResultSet result = statement.executeQuery()) {
					if (result.next()) {
						return new Usuario(
							result.getInt("id"),
							result.getString("name"),
							result.getString("surname")
						);
					} else {
						throw new IllegalArgumentException("Invalid id");
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
				try (ResultSet result = statement.executeQuery("SELECT * FROM people")) {
					final List<Usuario> people = new LinkedList<>();
					
					while (result.next()) {
						people.add(new Usuario(
							result.getInt("id"),
							result.getString("name"),
							result.getString("surname")
						));
					}
					
					return people;
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error listing people", e);
			throw new DAOException(e);
		}
	}
	
	public void delete(int id)
	throws DAOException, IllegalArgumentException {
		try (final Connection conn = this.getConnection()) {
			final String query = "DELETE FROM people WHERE id=?";
			
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setInt(1, id);
				
				if (statement.executeUpdate() != 1) {
					throw new IllegalArgumentException("Invalid id");
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error deleting a person", e);
			throw new DAOException(e);
		}
	}
	
	public Usuario modify(int id, String name, String surname)
	throws DAOException, IllegalArgumentException {
		if (name == null || surname == null) {
			throw new IllegalArgumentException("name and surname can't be null");
		}
		
		try (final Connection conn = this.getConnection()) {
			final String query = "UPDATE people SET name=?, surname=? WHERE id=?";
			
			try (PreparedStatement statement = conn.prepareStatement(query)) {
				statement.setString(1, name);
				statement.setString(2, surname);
				statement.setInt(3, id);
				
				if (statement.executeUpdate() == 1) {
					return new Usuario(id, name, surname); 
				} else {
					throw new IllegalArgumentException("name and surname can't be null");
				}
			}
		} catch (SQLException e) {
			LOG.log(Level.SEVERE, "Error modifying a person", e);
			throw new DAOException();
		}
	}
	
	public Usuario add(String name, String surname)
	throws DAOException, IllegalArgumentException {
		if (name == null || surname == null) {
			throw new IllegalArgumentException("name and surname can't be null");
		}
		
		try (final Connection conn = this.getConnection()) {
			final String query = "INSERT INTO people VALUES(null, ?, ?)";
			
			try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, name);
				statement.setString(2, surname);
				
				if (statement.executeUpdate() == 1) {
					try (ResultSet resultKeys = statement.getGeneratedKeys()) {
						if (resultKeys.next()) {
							return new Usuario(resultKeys.getInt(1), name, surname);
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
			LOG.log(Level.SEVERE, "Error adding a person", e);
			throw new DAOException(e);
		}
	}
}
