package um.tds.phototds.dominio;

import java.time.LocalDate;
import java.util.List;

public class Album extends Publicacion {
	//Lista de fotos
	private List<Foto> fotos;
	
	public Album(String titulo, LocalDate fecha, String descripcion, int meGusta, List<String> hashtags, List<Foto> fotos) {
		super(titulo, fecha, descripcion, meGusta, hashtags);
		this.fotos = fotos;
		// TODO Auto-generated constructor stub
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	
	
	
}
