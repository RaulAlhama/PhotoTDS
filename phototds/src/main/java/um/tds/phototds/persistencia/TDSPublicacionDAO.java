package um.tds.phototds.persistencia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import um.tds.phototds.dominio.Album;
import um.tds.phototds.dominio.Comentario;
import um.tds.phototds.dominio.Photo;
import um.tds.phototds.dominio.Publicacion;

public class TDSPublicacionDAO implements PublicacionDAO {

	private static final String PUBLICACION = "publicacion";
	private static final String DESCRIPCION = "descripcion";
	private static final String MGUSTAS = "meGustas";
	private static final String HASHTAGS = "hashtags";
	private static final String COMENTARIOS = "comentarios";
	private static final String FECHA = "fechaPublicacion";
	private static final String PATH = "path";
	private static final String TITULO = "titulo";
	private static final String FOTOSALBUM = "fotosAlbum";

	private ServicioPersistencia servPersistencia;

	public TDSPublicacionDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	private Entidad publicacionToEntidad(Publicacion publicacion) {
		Entidad ePubli = new Entidad();
		ePubli.setNombre(PUBLICACION);
		List<Propiedad> propiedades = new ArrayList<Propiedad>();

		propiedades.add(new Propiedad(DESCRIPCION, publicacion.getDescripcion()));
		propiedades.add(new Propiedad(MGUSTAS, String.valueOf(publicacion.getMeGusta())));
		propiedades.add(new Propiedad(HASHTAGS, guardarHashtags(publicacion.getHashtags())));
		propiedades.add(new Propiedad(COMENTARIOS, guardarComentarios(publicacion.getComentarios())));
		propiedades.add(new Propiedad(FECHA, publicacion.getFecha().toString()));
		if (publicacion instanceof Photo) {
			propiedades.add(new Propiedad(PATH, ((Photo) publicacion).getPath()));
		} else {
			propiedades.add(new Propiedad(TITULO, ((Album) publicacion).getTitulo()));
			propiedades.add(new Propiedad(FOTOSALBUM, guardarFotosAlbum(((Album) publicacion).getFotos())));
		}

		ePubli.setPropiedades(propiedades);

		return ePubli;

	}

	private String guardarHashtags(List<String> hashtags) {
		String lineas = "";
		if (hashtags.isEmpty()) {
			return lineas;
		}
		for (String h : hashtags) {
			lineas += h + " ";
		}
		return lineas.substring(0, lineas.length() - 1);
	}

	private String guardarFotosAlbum(List<Photo> fotosAlbum) {
		String lineas = "";
		for (Photo f : fotosAlbum) {
			lineas += f.getId() + " ";
		}
		return lineas;
	}

	private String guardarComentarios(List<Comentario> comentarios) {
		String lineas = "";
		if (comentarios.isEmpty()) {
			return lineas;
		}
		for (Comentario comentario : comentarios) {
			lineas += comentario.getTexto() + "\t";
		}
		return lineas;
	}

	private Publicacion entidadToPublicacion(Entidad ePubli) {
		String path = "";
		String descripcion = servPersistencia.recuperarPropiedadEntidad(ePubli, DESCRIPCION);
		int meGustas = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(ePubli, MGUSTAS));
		String hashtags = servPersistencia.recuperarPropiedadEntidad(ePubli, HASHTAGS);
		String comentarios = servPersistencia.recuperarPropiedadEntidad(ePubli, COMENTARIOS);
		String fecha = servPersistencia.recuperarPropiedadEntidad(ePubli, FECHA);
		path = servPersistencia.recuperarPropiedadEntidad(ePubli, PATH);
		String titulo = servPersistencia.recuperarPropiedadEntidad(ePubli, TITULO);
		Publicacion publicacion;
		if (path == null) {
			String fotosAlbum = servPersistencia.recuperarPropiedadEntidad(ePubli, FOTOSALBUM);
			publicacion = new Album(titulo, fecha, descripcion, recuperarHashtags(hashtags));
			((Album) publicacion).setFotos(recuperarFotos(fotosAlbum));
		} else
			publicacion = new Photo(fecha, descripcion, recuperarHashtags(hashtags), path);

		if (comentarios.equals(""))
			publicacion.setComentarios(new ArrayList<Comentario>());
		else
			publicacion.setComentarios(recuperarComentarios(comentarios));
		// Asignamos el identificador de la persistencia a la publicacion
		publicacion.setId(ePubli.getId());
		publicacion.setMeGustas(meGustas);
		return publicacion;
	}

	private List<Photo> recuperarFotos(String codigosFotos) {
		List<Photo> resultado = new LinkedList<Photo>();
		if (codigosFotos == null)
			return resultado;
		StringTokenizer strTok = new StringTokenizer(codigosFotos, " ");
		while (strTok.hasMoreTokens()) {
			resultado.add((Photo) get(Integer.parseInt(strTok.nextToken())));
		}
		return resultado;
	}

	private List<String> recuperarHashtags(String hashtags) {
		// System.out.println(idsEtiquetas);
		List<String> resultado = new LinkedList<String>();
		if (hashtags == null)
			return resultado;
		StringTokenizer strTok = new StringTokenizer(hashtags, " ");
		while (strTok.hasMoreTokens()) {
			resultado.add(strTok.nextToken());
		}
		return resultado;
	}

	private List<Comentario> recuperarComentarios(String comentarios) {
		// System.out.println(idsEtiquetas);
		List<Comentario> resultado = new ArrayList<Comentario>();
		if (comentarios == null)
			return resultado;
		StringTokenizer strTok = new StringTokenizer(comentarios, "\t");
		while (strTok.hasMoreTokens()) {
			Comentario c = new Comentario(strTok.nextToken());
			resultado.add(c);
		}
		return resultado;
	}

	@Override
	public void create(Publicacion publicacion) {
		if (publicacion instanceof Album) {
			for (Photo f : ((Album) publicacion).getFotos()) {
				create(f);
			}
		}
		Entidad ePublicacion = this.publicacionToEntidad(publicacion);
		ePublicacion = servPersistencia.registrarEntidad(ePublicacion);
		publicacion.setId(ePublicacion.getId());

	}

	@Override
	public boolean delete(Publicacion publicacion) {
		System.out.println("Eliminado foto");
		Entidad ePubli = servPersistencia.recuperarEntidad(publicacion.getId());
		return servPersistencia.borrarEntidad(ePubli);
	}

	@Override
	public void update(Publicacion publicacion) {
		Entidad ePubli = servPersistencia.recuperarEntidad(publicacion.getId());
		for (Propiedad prop : ePubli.getPropiedades()) {
			if (prop.getNombre().equals(DESCRIPCION)) {
				prop.setValor(publicacion.getDescripcion());
			} else if (prop.getNombre().equals(MGUSTAS)) {
				prop.setValor(String.valueOf(publicacion.getMeGusta()));
			} else if (prop.getNombre().equals(HASHTAGS)) {
				prop.setValor(guardarHashtags(publicacion.getHashtags()));
			} else if (prop.getNombre().equals(COMENTARIOS)) {
				prop.setValor(guardarComentarios(publicacion.getComentarios()));
			} else if (prop.getNombre().equals(FOTOSALBUM)) {
				for (Photo foto : ((Album) publicacion).getFotos()) {
					if (foto.getId() == Photo.IDERROR) {
						create(foto);
					} else {
						update(foto);
					}
				}
				prop.setValor(guardarFotosAlbum(((Album) publicacion).getFotos()));
			}
			servPersistencia.modificarPropiedad(prop);
		}

	}

	@Override
	public Publicacion get(int id) {
		return entidadToPublicacion(servPersistencia.recuperarEntidad(id));
	}

	@Override
	public List<Publicacion> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(PUBLICACION);
		List<Publicacion> publicaciones = new ArrayList<Publicacion>();
		for (Entidad ePubli : entidades) {
			publicaciones.add(entidadToPublicacion(ePubli));
		}
		return publicaciones;
	}

}
