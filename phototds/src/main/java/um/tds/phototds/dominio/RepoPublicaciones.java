package um.tds.phototds.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	
	public static RepoPublicaciones getUnicaInstancia() {
		if(unicaInstancia == null) unicaInstancia = new RepoPublicaciones();
		return unicaInstancia;
	}
	
	private RepoPublicaciones() {
		try {
			factoria = FactoriaDAO.getInstancia();
			adaptadorPublicacion = factoria.getPublicacionDAO();
			publicacionesID = new HashMap<Integer, Publicacion>();
			this.cargarRepo();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public void cargarRepo() {
		List<Publicacion> publicacionesBD = adaptadorPublicacion.getAll();
		 for(Publicacion pub : publicacionesBD) {
			 //adaptadorPublicacion.delete(pub);
			 publicacionesID.put(pub.getId(), pub);
		 }
			
	}
	
	public void addPublicacion(Publicacion publicacion) {
		publicacionesID.put(publicacion.getId(), publicacion);
	}
	
	public void removePublicacion (Publicacion publicacion) {
		publicacionesID.remove(publicacion.getId());
	}
	/*
	public List<Publicacion> findPublicacion(String hashtag) {
		ArrayList<Publicacion> todasPub = (ArrayList<Publicacion>) factoria.getPublicacionDAO().getAll();
		ArrayList<Publicacion> publiEncontradas = (ArrayList<Publicacion>) todasPub.stream()
											.filter(pu -> pu.getHashtags().equals(hashtag))
											.collect(Collectors.toList());
		return publiEncontradas;
	
											
	}*/
	
	
	
	public List<Publicacion> obtenerFotos(Fotos fotos){
		ArrayList<Publicacion> pubs = new ArrayList<Publicacion>();
		for(Foto foto : fotos.getFoto()) {
			String path = foto.getPath();
			String descripcion = foto.getPath();
			ArrayList<String> hastags = (ArrayList<String>) hashtagToString(foto.getHashTags());
			Photo photo = new Photo(LocalDate.now().toString(), descripcion, hastags, path);
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
