package um.tds.phototds.dominio;

import java.util.List;

public class Album extends Publicacion {
	//Lista de fotos
	private List<Photo> fotos;
	
	public Album(String titulo, String fecha, String descripcion, int meGusta, List<String> hashtags,
			List<Comentario> comentarios,List<Photo> fotos) {
		super(titulo, fecha, descripcion, meGusta, hashtags, comentarios);
		this.fotos = fotos;
	}




	

	
	
	

	public List<Photo> getFotos() {
		return fotos;
	}

	
	
	
}
