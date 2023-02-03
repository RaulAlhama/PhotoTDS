package um.tds.phototds.dominio;

import java.util.ArrayList;
import java.util.List;

public class Album extends Publicacion {
	private List<Photo> fotos;
	private String titulo;
	public Album(String titulo,String fecha, String descripcion, List<String> hashtags) {
		super(fecha, descripcion, hashtags);
		this.titulo = titulo;
		this.fotos = new ArrayList<Photo>();
	}

	//Lista de fotos
	
	
	

	public List<Photo> getFotos() {
		return fotos;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setFotos(List<Photo> fotos) {
		this.fotos = fotos;
	}
	
	public void setFoto(Photo foto) {
		this.fotos.add(foto);
	}

	
	
	
}
