package um.tds.phototds.dominio;


import java.util.List;

public class Photo extends Publicacion {
	 
	private String path;

	public Photo(String fecha, String descripcion, List<String> hashtags, String path) {
		super(fecha, descripcion, hashtags);
		this.path = path;
		// TODO Auto-generated constructor stub
	}

	public String getPath() {
		return path;
	}
	
	
	
}
