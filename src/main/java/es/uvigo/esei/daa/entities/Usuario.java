package es.uvigo.esei.daa.entities;

//Clase Person de DAA Example modificado para contener a los usuarios
public class Usuario {
	private int id;
	private String login;
	private String password;
	
	public Usuario() {
	}
	
	public Usuario(int id, String login, String pass) {
		this.id = id;
		this.login = login;
		this.password = pass;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
