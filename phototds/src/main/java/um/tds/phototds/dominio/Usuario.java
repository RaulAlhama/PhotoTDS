package um.tds.phototds.dominio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Usuario {
	
	private int id;
	private String username, clave, nombre, email;
	private boolean premium;
	private String fechaNacimiento;
	private List<Album> albumnes;
	private List<Foto> fotos;
	private String imagenPath;
	private String presentacion;
	
	public Usuario(String nombre, String email, String login, String password,
			String fechaNacimiento,String imagenPath,String presentacion) {
		this.id = 0;
		this.nombre = nombre;
		this.email = email;
		this.username = login;
		this.clave = password;
		this.fechaNacimiento = fechaNacimiento;
		this.albumnes = new ArrayList<Album>();
		this.fotos = new LinkedList<Foto>();
		this.premium = false;
		this.imagenPath = imagenPath;
		this.presentacion = presentacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}



	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<Album> getAlbumnes() {
		return new ArrayList<Album>(albumnes);
	}

	public void setAlbumnes(List<Album> albumnes) {
		this.albumnes = albumnes;
	}

	public List<Foto> getFotos() {
		return new LinkedList<Foto>(fotos);
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	public String getImagenPath() {
		return imagenPath;
	}

	public void setImagenPath(String imangenPath) {
		this.imagenPath = imangenPath;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}
	
	
	
	
	
	
}
