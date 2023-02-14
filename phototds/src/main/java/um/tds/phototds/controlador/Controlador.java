package um.tds.phototds.controlador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import um.tds.phototds.dominio.RepoUsuarios;
import um.tds.phototds.dominio.Usuario;
import um.tds.phototds.dominio.Album;
import um.tds.phototds.dominio.Comentario;
import um.tds.phototds.dominio.Descuento;
import um.tds.phototds.dominio.DescuentoEdad;
import um.tds.phototds.dominio.DescuentoPopularidad;
import um.tds.phototds.dominio.Notificacion;
import um.tds.phototds.dominio.Photo;
import um.tds.phototds.dominio.Publicacion;
import um.tds.phototds.persistencia.DAOException;
import um.tds.phototds.persistencia.FactoriaDAO;
import um.tds.phototds.persistencia.PublicacionDAO;
import um.tds.phototds.persistencia.UsuarioDAO;
import umu.tds.fotos.CargadorFotos;
import umu.tds.fotos.FotosListener;

public class Controlador implements FotosListener {
	private Usuario usuarioActual;
	private static Controlador unicaInstancia;

	private FactoriaDAO factoria;
	private PublicacionDAO publicacionDAO;
	private UsuarioDAO usuarioDAO;

	private CargadorFotos cargadorFotos;
	// private Fotos nuevasFotos;

	private RepoUsuarios repoUsuario;
	// private RepoPublicaciones repoPublicaciones;
	private List<Descuento> descuentos;

	// patron Sigleton
	private Controlador() {
		usuarioActual = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			// TODO: handle exception
		}
		cargadorFotos = new CargadorFotos();
		cargadorFotos.addCancionesListener(this);
		inicializarAdaptadores();
		inicializarRepositorios();
		inicializarDescuentos();
	}

	// patron Singleton
	public static Controlador getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new Controlador();
		return unicaInstancia;
	}

	public void inicializarAdaptadores() {
		publicacionDAO = factoria.getPublicacionDAO();
		usuarioDAO = factoria.getUsuarioDAO();
	}

	public void inicializarRepositorios() {
		// repoPublicaciones = RepoPublicaciones.getUnicaInstancia();
		repoUsuario = RepoUsuarios.getUnicaInstancia();

	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	/*
	 * public boolean esUsuarioRegistrado(String login) { return
	 * RepoUsuarios.getUnicaInstancia().getUsuario(login) != null; }
	 */

	public boolean loginUsuario(String nombre, String password) {
		Usuario usuario = repoUsuario.getUsuario(nombre);
		if (usuario != null && usuario.getClave().equals(password)) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}

	public boolean registrarUsuario(String nombre, String email, String login, String password, Date fechaNacimiento,
			String imagenPath, String presentacion) {

		/*
		 * if (esUsuarioRegistrado(login)) return false;
		 */
		Usuario usuario = new Usuario(nombre, email, login, password, fechaNacimiento, imagenPath, presentacion);
		usuarioDAO.create(usuario);
		repoUsuario.addUsuario(usuario);
		return true;
	}

	public boolean borrarUsuario(Usuario usuario) {
		/* if (!esUsuarioRegistrado(usuario.getUsername()))return false; */
		repoUsuario.removeUsuario(usuario);
		usuarioDAO.delete(usuario);
		return true;
	}

	public void borrarImagen(Photo foto) {
		usuarioActual.borrarFoto(foto);
		publicacionDAO.delete(foto);
		actualizarUsuario(usuarioActual);
	}

	public void borrarAlbum(Album album) {
		usuarioActual.borrarAlbum(album);

	}

	public void compartirFoto(String texto, String path, List<String> hashtags) {
		Photo foto = usuarioActual.addFoto(texto, path, hashtags);
		actualizarUsuario(usuarioActual);
		notificarSeguidores(foto);
		
	}

	public void compartirAlbum(String titulo, String texto, String path, List<String> hashtags) {
		usuarioActual.addAlbum(titulo, texto, path, hashtags);
		actualizarUsuario(usuarioActual);
	}

	public void addFotoToAlbum(Album album, String path) {
		usuarioActual.addFotoToAlbum(album, path);
		actualizarAlbum(album);
	}

	public boolean comprobarTitulo(String titulo) {
		return !(usuarioActual.getAlbumnes().stream().anyMatch(a -> a.getTitulo().equals(titulo)));
	}

	public void actualizarUsuario(Usuario usuario) {
		usuarioDAO.update(usuario);
	}

	public void actualizarAlbum(Album album) {
		publicacionDAO.update(album);
	}

	public void hacerPremium() {
		usuarioActual.setPremium(true);
		actualizarUsuario(usuarioActual);
	}

	/*
	 * public void compartirFoto(String texto, String path, List<String> hashtags) {
	 * usuarioActual.addFoto(texto, path, hashtags);
	 * actualizarUsuario(usuarioActual); }
	 */

	public void setFotosFile(String path) {
		cargadorFotos.setArchivoFotos(path);
	}

	@Override
	public void nuevasFotos(EventObject arg0) {
		if (arg0 instanceof FotosListener) {
			// nuevasFotos = ((FotosEvent) arg0).getNuevasFotos();
		}

	}

	public int obtenerNumeroPubls(Usuario usu) {
		return usu.getFotos().size() + usu.getAlbumnes().size();
	}

	public List<Photo> obtenerFotos() {
		return usuarioActual.getFotos();
	}

	private void inicializarDescuentos() {
		descuentos = new ArrayList<Descuento>();
		descuentos.add(new DescuentoEdad());
		descuentos.add(new DescuentoPopularidad());
	}

	public Optional<Descuento> getDescuento() {
		return descuentos.stream().filter(d -> d.isApplicable(usuarioActual))
				.sorted(Comparator.comparing(Descuento::getDescuento).reversed()).findFirst();
	}

	public void addMeGusta(Publicacion pub) {
		pub.setMeGustas(pub.getMeGusta() + 1);
		if (pub instanceof Album) {
			for (Photo photo : ((Album) pub).getFotos()) {
				addMeGusta(photo);
			}
		}
		publicacionDAO.update(pub);
	}

	public void addComentario(Publicacion f, String comentario) {
		f.setComentario(new Comentario(comentario));
		publicacionDAO.update(f);
	}

	public List<Usuario> obtenerUsuarios() {
		try {

			return repoUsuario.getUsuarios();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Entra");
		return null;
	}

	public List<Usuario> obtenerUsuariosFiltro(String filtro) {
		List<Usuario> usuarios = obtenerUsuarios().stream().filter(u -> u.getId() != usuarioActual.getId())
				.collect(Collectors.toList()); // Todos los usuarios menos el actual

		return usuarios.stream().filter(u -> u.getNombre().startsWith(filtro)).collect(Collectors.toList());
	}

	public List<Photo> obtenerTodasLasFotos() {
		List<Usuario> usuarios = obtenerUsuarios();
		List<Photo> fotos = new ArrayList<Photo>();
		usuarios.stream().forEach(u -> fotos.addAll(u.getFotos()));
		return fotos;
	}

	public void addSeguidor(Usuario usu) {
		usu.addSeguidor(Integer.toString(usuarioActual.getId()));
		actualizarUsuario(usu);
	}

	public void deleteSeguidor(Usuario usu) {
		usu.deleteSeguidor(Integer.toString(usuarioActual.getId()));
		actualizarUsuario(usu);
	}

	public String getNumSeguidores(Usuario usu) {
		return Integer.toString(usu.getSeguidores().size());
	}

	public String getSeguidos(Usuario usu) {
		int contador = 0;
		List<Usuario> usuarios = obtenerUsuarios();
		for (Usuario usuario : usuarios) {
				if (usuario.getSeguidores().contains(Integer.toString(usu.getId())))
					contador++;
			}
		return Integer.toString(contador);
	}
	
	
	public void notificarSeguidores(Publicacion pub) {
		Set<String> seguidoresIds = usuarioActual.getSeguidores();
		List<Usuario> seguidores = new ArrayList<Usuario>();
		Notificacion notificacion = crearNotificacion(pub);
		for(String id : seguidoresIds) {
			seguidores.add(usuarioDAO.get(Integer.parseInt(id)));
		}
		for (Usuario usuario : seguidores) {
			usuario.addNotificacion(notificacion);
			actualizarUsuario(usuario);
		}
		usuarioActual.addNotificacion(notificacion);
		actualizarUsuario(usuarioActual);
	}
	
	public Notificacion crearNotificacion(Publicacion pub) {
		Notificacion notificacion = new Notificacion(Integer.toString(pub.getId()), Integer.toString(usuarioActual.getId()));
		return notificacion;
	}
	
	public List<Notificacion> getNotificaciones(){
		List<Notificacion> notificaciones = usuarioActual.getNotificaciones();
		Collections.reverse(notificaciones);
		for (Notificacion notificacion : notificaciones) {
			System.out.println(notificacion);
		}
		return notificaciones;
	}
	
	public Publicacion getPubDeNotif(String id) {
		return publicacionDAO.get(Integer.parseInt(id));
	}
	
	public Usuario getUserDeNotif(String id) {
		return usuarioDAO.get(Integer.parseInt(id));
	}

}
