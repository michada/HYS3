package es.uvigo.esei.daa.entities;

//Clase Person de DAA Example modificado para contener a los eventos
public class Evento {
	private int idEvento;
	private String titulo;
	private String usuario;
	private int maxAsistentes;
	private String inicio;
	private String fin;
	
	public Evento(){
		
	}
	
	public Evento(int idEvento,String titulo, String usuario, int maxAsistentes, String inicio, String fin){
		
		this.idEvento = idEvento;
		this.titulo = titulo;
		this.usuario = usuario;
		this.maxAsistentes = maxAsistentes;
		this.inicio = inicio;
		this.fin = fin;
		
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
	public String getinicio() {
		return inicio;
	}
	public void setinicio(String inicio) {
		this.inicio = inicio;
	}
	public String getfin() {
		return fin;
	}
	public void setfin(String fin) {
		this.fin = fin;
	}
	
	
	
	
}
