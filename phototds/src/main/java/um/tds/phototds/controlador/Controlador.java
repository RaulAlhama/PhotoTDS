package um.tds.phototds.controlador;


import um.tds.phototds.dominio.RepoUsuarios;
import um.tds.phototds.dominio.Usuario;
import um.tds.phototds.persistencia.DAOException;
import um.tds.phototds.persistencia.FactoriaDAO;
import um.tds.phototds.persistencia.UsuarioDAO;

public class Controlador {
	private Usuario usuarioActual;
	private static Controlador unicaInstancia;
	private FactoriaDAO factoria;

	private Controlador() {
		usuarioActual = null;
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public static Controlador getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new Controlador();
		return unicaInstancia;
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
			String fechaNacimiento) {

		if (esUsuarioRegistrado(login))
			return false;
		Usuario usuario = new Usuario(nombre, email, login, password, fechaNacimiento);

		UsuarioDAO usuarioDAO = factoria.getUsuarioDAO(); /* Adaptador DAO para almacenar el nuevo Usuario en la BD */
		usuarioDAO.create(usuario);

		RepoUsuarios.getUnicaInstancia().addUsuario(usuario);
		return true;
	}

	public boolean borrarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getUsername()))
			return false;

		UsuarioDAO usuarioDAO = factoria.getUsuarioDAO(); /* Adaptador DAO para borrar el Usuario de la BD */
		usuarioDAO.delete(usuario);

		RepoUsuarios.getUnicaInstancia().removeUsuario(usuario);
		return true;
	}
	
	public void hacerPremium() {
		usuarioActual.setPremium(true);
	}
}
