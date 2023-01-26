package um.tds.phototds.dominio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import um.tds.phototds.persistencia.DAOException;
import um.tds.phototds.persistencia.FactoriaDAO;

public class RepoUsuarios {
	
	private static RepoUsuarios unicaInstancia;
	
	private FactoriaDAO factoria;

	private HashMap<Integer, Usuario> asistentesPorID;
	private HashMap<String, Usuario> asistentesPorLogin;

	public static RepoUsuarios getUnicaInstancia() {
		if (unicaInstancia == null) unicaInstancia = new RepoUsuarios();
		return unicaInstancia;
	}

	private RepoUsuarios (){
		asistentesPorID = new HashMap<Integer, Usuario>();
		asistentesPorLogin = new HashMap<String, Usuario>();
		
		try {
			factoria = FactoriaDAO.getInstancia();
			
			List<Usuario> listaAsistentes = factoria.getUsuarioDAO().getAll();
			for (Usuario usuario : listaAsistentes) {
				//factoria.getUsuarioDAO().delete(usuario);
				asistentesPorID.put(usuario.getId(), usuario);
				asistentesPorLogin.put(usuario.getUsername(), usuario);
			}
		} catch (DAOException eDAO) {
			   eDAO.printStackTrace();
		}
	}
	
	public List<Usuario> getUsuarios() throws DAOException {
		return new LinkedList<Usuario>(asistentesPorLogin.values());
	}
	
	public Usuario getUsuario(String login) {
		return asistentesPorLogin.get(login);
	}

	public Usuario getUsuario(int id) {
		return asistentesPorID.get(id);
	}
	
	public void addUsuario(Usuario usuario) {
		asistentesPorID.put(usuario.getId(), usuario);
		asistentesPorLogin.put(usuario.getUsername(), usuario);
	}
	
	public void removeUsuario(Usuario usuario) {
		asistentesPorID.remove(usuario.getId());
		asistentesPorLogin.remove(usuario.getUsername());
	}
	
}
