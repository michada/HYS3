package es.uvigo.esei.daa.entities;

//Clase Person de DAA Example modificado para contener a los eventos
public class Evento {
	private int idEvento;
	private String titulo;
	private int usuario;
	private int maxAsistentes;
	private String inicio;
	private String fin;
	private String localidad;
	private String descripcion;
	private String descripcionDetallada;
	private String categoria;
	private String local;
	
	public Evento(){
		
	}
	
	public Evento(int idEvento,String titulo, int usuario, int maxAsistentes, String inicio, String fin, String localidad
			, String descripcion, String descripcionDetallada, String categoria, String local){
		
		this.idEvento = idEvento;
		this.titulo = titulo;
		this.usuario = usuario;
		this.maxAsistentes = maxAsistentes;
		this.inicio = inicio;
		this.fin = fin;
		this.setLocalidad(localidad);
		this.setDescripcion(descripcion);
		this.setDescripcionDetallada(descripcionDetallada);
		this.setCategoria(categoria);
		this.setLocal(local);
		
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
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
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

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcionDetallada() {
		return descripcionDetallada;
	}

	public void setDescripcionDetallada(String descripcionDetallada) {
		this.descripcionDetallada = descripcionDetallada;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}	
}