package um.tds.phototds.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Publicacion {
	private String titulo;
	private LocalDate fecha;
	private String descripcion;
	private int meGusta;
	private List<String> hashtags;
	private List<Comentario> comentarios;
	
	public Publicacion(String titulo, LocalDate fecha, String descripcion, int meGusta, List<String> hashtags) {
		this.titulo = titulo;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.meGusta = meGusta;
		this.hashtags = hashtags;
		this.comentarios = new ArrayList<Comentario>();
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getMeGusta() {
		return meGusta;
	}

	public List<String> getHashtags() {
		return hashtags;
	}

	public List<Comentario> getComentarios() {
		//Devolvemos una copia
		return new ArrayList<Comentario>(comentarios);
	}
	
	
	
	
	
	
	
}
