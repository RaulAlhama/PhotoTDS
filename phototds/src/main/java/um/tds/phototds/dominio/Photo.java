package um.tds.phototds.dominio;


import java.util.List;

public class Photo extends Publicacion {
	private String path; 
	
	public Photo(String titulo, String fecha, String descripcion, int meGusta, List<String> hashtags, List<Comentario> comentarios, String path) {
		super(titulo, fecha, descripcion, meGusta, hashtags,comentarios);
		this.path = path;
		// TODO Auto-generated constructor stub
	}

	public String getPath() {
		return path;
	}
	
	
	
}