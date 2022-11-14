package um.tds.phototds.dominio;

import java.time.LocalDate;
import java.util.List;

public class Foto extends Publicacion {
	private String path; 
	
	public Foto(String titulo, LocalDate fecha, String descripcion, int meGusta, List<String> hashtags, String path) {
		super(titulo, fecha, descripcion, meGusta, hashtags);
		this.path = path;
		// TODO Auto-generated constructor stub
	}

	public String getPath() {
		return path;
	}
	
	
	
}
