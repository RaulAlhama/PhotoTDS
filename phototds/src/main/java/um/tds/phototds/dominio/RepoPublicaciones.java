package um.tds.phototds.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import um.tds.phototds.persistencia.DAOException;
import um.tds.phototds.persistencia.FactoriaDAO;
import um.tds.phototds.persistencia.PublicacionDAO;
import umu.tds.fotos.Foto;
import umu.tds.fotos.Fotos;
import umu.tds.fotos.HashTag;

public class RepoPublicaciones {

	private static RepoPublicaciones unicaInstancia;
	
	private FactoriaDAO factoria;
	private PublicacionDAO adaptadorPublicacion;
	
	private HashMap<Integer, Publicacion> publicacionesID;
	private HashMap<String, Publicacion> publicacionesPorTitulo;
	
	public static RepoPublicaciones getUnicaInstancia() {
		if(!(unicaInstancia == null)) unicaInstancia = new RepoPublicaciones();
		return unicaInstancia;
	}
	
	private RepoPublicaciones() {
		try {
			factoria = FactoriaDAO.getInstancia();
			adaptadorPublicacion = factoria.getPublicacionDAO();
			publicacionesID = new HashMap<Integer, Publicacion>();
			publicacionesPorTitulo = new HashMap<String, Publicacion>();
			this.cargarRepo();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public void addPublicacion(Publicacion publicacion) {
		publicacionesID.put(publicacion.getId(), publicacion);
		publicacionesPorTitulo.put(publicacion.getTitulo(), publicacion);
	}
	
	public void removePublicacion (Publicacion publicacion) {
		publicacionesID.remove(publicacion.getId());
		publicacionesPorTitulo.remove(publicacion.getTitulo());
	}
	
	public List<Publicacion> findPublicacion(String titulo) {
		ArrayList<Publicacion> todasPub = (ArrayList<Publicacion>) factoria.getPublicacionDAO().getAll();
		ArrayList<Publicacion> publiEncontradas = (ArrayList<Publicacion>) todasPub.stream()
											.filter(pu -> pu.getTitulo().equals(titulo))
											.collect(Collectors.toList());
		return publiEncontradas;
	
											
	}
	
	public void cargarRepo() {
		List<Publicacion> publicacionesBD = adaptadorPublicacion.getAll();
		 for(Publicacion pub : publicacionesBD)
			publicacionesID.put(pub.getId(), pub);
	}
	
	public List<Publicacion> obtenerFotos(Fotos fotos){
		ArrayList<Publicacion> pubs = new ArrayList<Publicacion>();
		for(Foto foto : fotos.getFoto()) {
			String titulo = foto.getTitulo();
			String path = foto.getPath();
			String descripcion = foto.getPath();
			ArrayList<String> hastags = (ArrayList<String>) hashtagToString(foto.getHashTags());
			Photo photo = new Photo(titulo, LocalDate.now().toString(), descripcion,0, hastags, new LinkedList<Comentario>(), path);
			pubs.add(photo);
					
		}
		return pubs;
	}
	
	public List<String> hashtagToString(List<HashTag> list) {
		ArrayList<String> hashtags = new ArrayList<String>();
		for(HashTag hs : list) {
			String hashtag = "#"+hs;
			hashtags.add(hashtag);
		}
		return hashtags;
	}
	
	
	

}
