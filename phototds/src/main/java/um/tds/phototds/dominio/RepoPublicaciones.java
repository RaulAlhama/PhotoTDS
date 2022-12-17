package um.tds.phototds.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import um.tds.phototds.persistencia.DAOException;
import um.tds.phototds.persistencia.FactoriaDAO;

public class RepoPublicaciones {

	private static RepoPublicaciones unicaInstancia;
	
	private FactoriaDAO factoria;
	
	private HashMap<Integer, Publicacion> asistentesPorID;
	private HashMap<String, Publicacion> asistentesPorTitulo;
	
	public static RepoPublicaciones getUnicaInstancia() {
		if(!(unicaInstancia == null)) unicaInstancia = new RepoPublicaciones();
		return unicaInstancia;
	}
	
	private RepoPublicaciones() {
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public void addPublicacion(Publicacion publicacion) {
		asistentesPorID.put(publicacion.getId(), publicacion);
		asistentesPorTitulo.put(publicacion.getTitulo(), publicacion);
	}
	
	public void removePublicacion (Publicacion publicacion) {
		asistentesPorID.remove(publicacion.getId());
		asistentesPorTitulo.remove(publicacion.getTitulo());
	}
	
	public List<Publicacion> findPublicacion(String titulo) {
		ArrayList<Publicacion> todasPub = (ArrayList<Publicacion>) factoria.getPublicacionDAO().getAll();
		ArrayList<Publicacion> publiEncontradas = (ArrayList<Publicacion>) todasPub.stream()
											.filter(pu -> pu.getTitulo().equals(titulo))
											.collect(Collectors.toList());
		return publiEncontradas;
	
											
	}
	
	

}
