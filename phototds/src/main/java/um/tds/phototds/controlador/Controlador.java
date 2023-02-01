package um.tds.phototds.controlador;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.EventObject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import um.tds.phototds.dominio.RepoPublicaciones;
import um.tds.phototds.dominio.RepoUsuarios;
import um.tds.phototds.dominio.Usuario;
import um.tds.phototds.dominio.Comentario;
import um.tds.phototds.dominio.Descuento;
import um.tds.phototds.dominio.DescuentoEdad;
import um.tds.phototds.dominio.DescuentoPopularidad;
import um.tds.phototds.dominio.Photo;
import um.tds.phototds.persistencia.DAOException;
import um.tds.phototds.persistencia.FactoriaDAO;
import um.tds.phototds.persistencia.PublicacionDAO;
import um.tds.phototds.persistencia.UsuarioDAO;
import umu.tds.fotos.CargadorFotos;
import umu.tds.fotos.Fotos;
import umu.tds.fotos.FotosEvent;
import umu.tds.fotos.FotosListener;

public class Controlador implements FotosListener {
	private Usuario usuarioActual;
	private static Controlador unicaInstancia;

	private FactoriaDAO factoria;
	private PublicacionDAO publicacionDAO;
	private UsuarioDAO usuarioDAO;

	private CargadorFotos cargadorFotos;
	private Fotos nuevasFotos;

	private RepoUsuarios repoUsuario;
	private RepoPublicaciones repoPublicaciones;
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
		repoUsuario = RepoUsuarios.getUnicaInstancia();
		repoPublicaciones = RepoPublicaciones.getUnicaInstancia();
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

	public void actualizarUsuario(Usuario usuario) {
		usuarioDAO.update(usuario);
	}

	public void hacerPremium() {
		usuarioActual.setPremium(true);
		actualizarUsuario(usuarioActual);
	}

	public void compartirFoto(String texto, String path, List<String> hashtags) {
		usuarioActual.addFoto(texto, path, hashtags);
		actualizarUsuario(usuarioActual);
	}

	public void setFotosFile(String path) {
		cargadorFotos.setArchivoFotos(path);
	}

	@Override
	public void nuevasFotos(EventObject arg0) {
		if (arg0 instanceof FotosListener) {
			nuevasFotos = ((FotosEvent) arg0).getNuevasFotos();
		}

	}

	public int obtenerNumeroPubls() {
		return usuarioActual.getFotos().size();
	}

	public int obtenerNumeroPubls(Usuario usu) {
		return usu.getFotos().size();
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

	public void addMeGusta(Photo f) {
		f.setMeGustas(f.getMeGusta() + 1);
		publicacionDAO.update(f);
	}

	public void addComentario(Photo f, String comentario) {
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

}
