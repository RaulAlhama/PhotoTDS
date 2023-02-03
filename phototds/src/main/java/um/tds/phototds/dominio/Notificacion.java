package um.tds.phototds.dominio;

import java.time.LocalDate;

public class Notificacion {
	private Publicacion publicacion;
	private Usuario publicador;
	private LocalDate fecha;
	public Notificacion(Publicacion publicacion, Usuario publicador) {
		this.publicacion = publicacion;
		this.publicador = publicador;
		this.fecha = LocalDate.now();
	}
	public Publicacion getPublicacion() {
		return publicacion;
	}
	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}
	public Usuario getPublicador() {
		return publicador;
	}
	public void setPublicador(Usuario publicador) {
		this.publicador = publicador;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	
	
 
}
