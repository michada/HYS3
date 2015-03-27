package es.uvigo.esei.daa.entities;

//Clase Person de DAA Example modificado para contener a los eventos
public class Evento {
	private int id;
	private String titulo;
	private String usuario;
	private String fechaInicio;
	private String fechaFin;
	
	
	public Evento() {
	}
	
	public Evento(int id, String titulo, String pass, String fechaInicio, String fechaFin) {
		this.id = id;
		this.titulo = titulo;
		this.usuario = pass;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
		
	public String getfechaFin() {
		return fechaFin;
	}

	public void setfechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
}
