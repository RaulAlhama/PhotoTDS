package um.tds.phototds.dominio;

import java.util.HashMap;

import um.tds.phototds.persistencia.DAOException;
import um.tds.phototds.persistencia.FactoriaDAO;

public class RepoFotos {

	private static RepoFotos unicaInstancia;
	
	private FactoriaDAO factoria;
	
	private HashMap<Integer, Usuario> asistentesPorID;
	private HashMap<String, Usuario> asistentesPorLogin;
	
	public static RepoFotos getUnicaInstancia() {
		if(!(unicaInstancia == null)) return unicaInstancia;
		unicaInstancia = new RepoFotos();
		return unicaInstancia;
	}
	
	private RepoFotos() {
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
}
