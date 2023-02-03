package um.tds.phototds.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

public class Usuario {
	private static final int EDADJOVENMAX = 23;
	private static final int EDADJOVENMIN = 18;
	private static final int POPULAR = 100;

	private int id;
	private String username, clave, nombre, email;
	private boolean premium;
	private Date fechaNacimiento;
	private List<Album> albumnes;
	private List<Photo> fotos;
	private String imagenPath;
	private String presentacion;
	private List<Notificacion> notificaciones;
	private List<Usuario> seguidores;

	public Usuario(String nombre, String email, String login, String password, Date fechaNacimiento, String imagenPath,
			String presentacion) {
		this.id = 0;
		this.nombre = nombre;
		this.email = email;
		this.username = login;
		this.clave = password;
		this.fechaNacimiento = fechaNacimiento;
		this.albumnes = new ArrayList<Album>();
		this.fotos = new LinkedList<Photo>();
		this.premium = false;
		this.imagenPath = imagenPath;
		this.presentacion = presentacion;
		this.notificaciones = new ArrayList<Notificacion>();
		this.seguidores = new ArrayList<Usuario>();
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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<Album> getAlbumnes() {
		return new ArrayList<Album>(albumnes);
	}

	public void setAlbumnes(List<Album> albumnes) {
		this.albumnes = albumnes;
	}

	public List<Photo> getFotos() {
		return new LinkedList<Photo>(fotos);
	}

	public void setFotos(List<Photo> fotos) {
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
	
	public List<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}
	

	public List<Usuario> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(List<Usuario> seguidores) {
		this.seguidores = seguidores;
	}

	public void addFoto(String texto, String path,List<String> hashtags) {
		Photo foto = new Photo(LocalDate.now().toString(), texto, hashtags, path);
		this.fotos.add(foto);
	}
	
	public void addAlbum(String titulo, String texto, String path,List<String> hashtags) {
		Photo foto = new Photo(LocalDate.now().toString(), texto, hashtags, path);
		Album album = new Album(titulo,LocalDate.now().toString(), texto, hashtags);
		album.setFoto(foto);
		this.albumnes.add(album);
	}
	
	public void borrarFoto(Photo foto) {
		this.fotos.remove(foto);
	}
	
	public void borrarAlbum(Album album) {
		this.albumnes.remove(album);
	}
	

	public boolean isJoven() {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europa/Madrid"));
		cal.setTime(fechaNacimiento);
		int añoNac = cal.get(Calendar.YEAR); // año de nacimiento
		return ((LocalDate.now().getYear() - añoNac) <= EDADJOVENMAX
				&& (LocalDate.now().getYear() - añoNac) >= EDADJOVENMIN);
	}

	public boolean esPopular() {
		return getFotos().stream().anyMatch(f -> f.getMeGusta() > POPULAR);

	}

}
