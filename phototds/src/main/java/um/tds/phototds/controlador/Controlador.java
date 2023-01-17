package um.tds.phototds.controlador;


import java.util.EventObject;

import um.tds.phototds.dominio.Publicacion;
import um.tds.phototds.dominio.RepoPublicaciones;
import um.tds.phototds.dominio.RepoUsuarios;
import um.tds.phototds.dominio.Usuario;
import um.tds.phototds.persistencia.DAOException;
import um.tds.phototds.persistencia.FactoriaDAO;
import um.tds.phototds.persistencia.PublicacionDAO;
import um.tds.phototds.persistencia.UsuarioDAO;
import umu.tds.fotos.CargadorFotos;
import umu.tds.fotos.Fotos;
import umu.tds.fotos.FotosEvent;
import umu.tds.fotos.FotosListener;

public class Controlador implements FotosListener{
	private Usuario usuarioActual;
	private static Controlador unicaInstancia;
	
	private FactoriaDAO factoria;
	private PublicacionDAO publicacionDAO;
	private UsuarioDAO usuarioDAO;
	
	private CargadorFotos cargadorFotos;
	private Fotos nuevasFotos;
	
	private RepoUsuarios repoUsuario;
	private RepoPublicaciones repoPublicaciones;
	
	//patron Sigleton
	private Controlador() {
		usuarioActual = null;
		cargadorFotos = new CargadorFotos();
		cargadorFotos.addCancionesListener(this);
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			// TODO: handle exception
		}
		inicializarAdaptadores();
	}
	//patron Singleton
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

	public boolean esUsuarioRegistrado(String login) {
		return RepoUsuarios.getUnicaInstancia().getUsuario(login) != null;
	}

	public boolean loginUsuario(String nombre, String password) {
		Usuario usuario = RepoUsuarios.getUnicaInstancia().getUsuario(nombre);
		if (usuario != null && usuario.getClave().equals(password)) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}

	public boolean registrarUsuario(String nombre, String email, String login, String password,
			String fechaNacimiento,String imagenPath,String presentacion) {

		if (esUsuarioRegistrado(login))
			return false;
		Usuario usuario = new Usuario(nombre, email, login, password, fechaNacimiento,imagenPath,presentacion);

		UsuarioDAO usuarioDAO = factoria.getUsuarioDAO(); /* Adaptador DAO para almacenar el nuevo Usuario en la BD */
		usuarioDAO.create(usuario);

		RepoUsuarios.getUnicaInstancia().addUsuario(usuario);
		return true;
	}

	public boolean borrarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getUsername()))return false;
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
	
	public void compartirFoto(String texto,String path) {
		usuarioActual.addFoto(texto,path);
	}
	
	public void setFotosFile(String path) {
		cargadorFotos.setArchivoFotos(path);
	}
	@Override
	public void nuevasFotos(EventObject arg0) {
		if(arg0 instanceof FotosListener) {
			nuevasFotos = ((FotosEvent) arg0).getNuevasFotos();
		}
		
	}
}
