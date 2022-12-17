package um.tds.phototds.dominio;

import java.util.ArrayList;
import java.util.List;

public abstract class Publicacion {
	private int id;
	private String titulo;
	private String fecha;
	private String descripcion;
	private int meGusta;
	private List<String> hashtags;
	private List<Comentario> comentarios;
	
	public Publicacion(String titulo, String fecha, String descripcion, int meGusta, List<String> hashtags, List<Comentario> comentarios) {
		this.titulo = titulo;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.meGusta = meGusta;
		this.hashtags = hashtags;
		this.comentarios = comentarios;
		this.id = 0;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getFecha() {
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
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
	
	
}
