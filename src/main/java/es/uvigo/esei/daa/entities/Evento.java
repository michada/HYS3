package es.uvigo.esei.daa.entities;

//Clase Person de DAA Example modificado para contener a los eventos
public class Evento {
	private int idEvento;
	private String titulo;
	private String usuario;
	private int maxAsistentes;
	private String fechaInicio;
	private String fechaFin;
	
	public Evento(){
		
	}
	
	public Evento(int idEvento,String titulo, String usuario, int maxAsistentes, String fechaInicio, String fechaFin){
		
		this.idEvento = idEvento;
		this.titulo = titulo;
		this.usuario = usuario;
		this.maxAsistentes = maxAsistentes;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		
	}

	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
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
	public int getMaxAsistentes() {
		return maxAsistentes;
	}
	public void setMaxAsistentes(int maxAsistentes) {
		this.maxAsistentes = maxAsistentes;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
	
	
}
