package um.tds.phototds.dominio;

public class Notificacion {
	private String idPublicacion;
	private String idPublicador;
	
	public Notificacion(String idPublicacion, String idPublicador) {
		this.idPublicacion = idPublicacion;
		this.idPublicador = idPublicador;
	}

	public String getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(String idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public String getIdPublicador() {
		return idPublicador;
	}

	public void setIdPublicador(String idPublicador) {
		this.idPublicador = idPublicador;
	}
	
}
