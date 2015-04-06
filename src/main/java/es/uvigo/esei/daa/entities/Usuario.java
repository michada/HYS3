package es.uvigo.esei.daa.entities;

//Clase Person de DAA Example modificado para contener a los usuarios
public class Usuario {
	private int idUsuario;
	private String login;
	private String password;
	private String nombre;
	
	public Usuario() {
	}
	
	public Usuario(int idUsuario, String login, String pass, String nombre) {
		this.idUsuario = idUsuario;
		this.login = login;
		this.password = pass;
		this.nombre = nombre;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
