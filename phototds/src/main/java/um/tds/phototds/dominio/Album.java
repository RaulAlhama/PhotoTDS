package um.tds.phototds.dominio;

import java.util.List;

public class Album extends Publicacion {
	private List<Photo> fotos;
	public Album(String titulo, String fecha, String descripcion, List<String> hashtags, List<Photo> fotos) {
		super(titulo, fecha, descripcion, hashtags);
		this.fotos = fotos;
	}

	//Lista de fotos
	
	




	

	
	
	

	public List<Photo> getFotos() {
		return fotos;
	}

	
	
	
}
