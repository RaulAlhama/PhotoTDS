package um.tds.phototds.persistencia;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import um.tds.phototds.dominio.Photo;
import um.tds.phototds.dominio.Usuario;

public final class TDSUsuarioDAO implements UsuarioDAO {

	private static final String USUARIO = "Usuario";
	private static final String NOMBRE = "nombre";
	private static final String EMAIL = "email";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";
	private static final String PREMIUM = "premium";
	private static final String IMAGEN_PATH = "imagen";
	private static final String PRESENTACION = "presentacion";
	private static final String FOTOS = "fotos";

	private ServicioPersistencia servPersistencia;
	private TDSPublicacionDAO persistenciaPublicaciones;

	public TDSUsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		persistenciaPublicaciones = new TDSPublicacionDAO();
	}

	private Usuario entidadToUsuario(Entidad eUsuario) throws ParseException {
		List<Photo> fotos;
		String idsFotos = "";
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		String login = servPersistencia.recuperarPropiedadEntidad(eUsuario, LOGIN);
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
		String fechaNacimiento = servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
		String premium = servPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM);
		String imagenPath = servPersistencia.recuperarPropiedadEntidad(eUsuario, IMAGEN_PATH);
		String presentacion = servPersistencia.recuperarPropiedadEntidad(eUsuario, PRESENTACION);
		idsFotos = servPersistencia.recuperarPropiedadEntidad(eUsuario, FOTOS);
		System.out.println(idsFotos);
		if (idsFotos == null) {
			fotos = new ArrayList<Photo>();
		} else
			fotos = obtenerFotosDeCodigos(idsFotos);
		Usuario usuario = new Usuario(nombre, email, login, password, obtenerFecha(fechaNacimiento), imagenPath,
				presentacion);
		usuario.setId(eUsuario.getId());
		usuario.setPremium(Boolean.valueOf(premium));
		usuario.setFotos(fotos);

		return usuario;
	}

	private List<Photo> obtenerFotosDeCodigos(String ids) {
		List<Photo> resultado = new LinkedList<Photo>();
		StringTokenizer strTok = new StringTokenizer(ids, " ");
		while (strTok.hasMoreTokens()) {
			resultado.add((Photo) persistenciaPublicaciones.get(Integer.valueOf(strTok.nextToken())));
		}
		return resultado;
	}

	private Date obtenerFecha(String date) {
		String[] dateSplitted = date.split(" ");
		int year = Integer.parseInt(dateSplitted[5]) - 1900;
		int day = Integer.parseInt(dateSplitted[2]);
		int month;
		switch (dateSplitted[1]) {
		case "Jan":
			month = 0;
			break;
		case "Feb":
			month = 1;
			break;
		case "Mar":
			month = 2;
			break;
		case "Apr":
			month = 3;
			break;
		case "May":
			month = 4;
			break;
		case "Jun":
			month = 5;
			break;
		case "Jul":
			month = 6;
			break;
		case "Aug":
			month = 7;
			break;
		case "Sep":
			month = 8;
			break;
		case "Oct":
			month = 9;
			break;
		case "Nov":
			month = 10;
			break;
		case "Dec":
			month = 11;
			break;
		default:
			return null;
		}
		@SuppressWarnings("deprecation")
		Date parsedDate = new Date(year, month, day);
		return parsedDate;
	}

	private Entidad usuarioToEntidad(Usuario usuario) {
		Entidad eUsuario = new Entidad();
		eUsuario.setNombre(USUARIO);
		System.out.println(usuario.getFechaNacimiento().toString());
		String idsFotos = obtenerCodigos(usuario.getFotos());
		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(NOMBRE, usuario.getNombre()),
				new Propiedad(EMAIL, usuario.getEmail()), new Propiedad(LOGIN, usuario.getUsername()),
				new Propiedad(PASSWORD, usuario.getClave()),
				new Propiedad(FECHA_NACIMIENTO, usuario.getFechaNacimiento().toString()),
				new Propiedad(IMAGEN_PATH, usuario.getImagenPath()),
				new Propiedad(PRESENTACION, usuario.getPresentacion()),
				new Propiedad(PREMIUM, Boolean.toString(usuario.isPremium())), new Propiedad(FOTOS, idsFotos))));
		return eUsuario;
	}

	public String obtenerCodigos(List<Photo> photos) {
		String resultado = "";
		for (Photo f : photos)
			resultado += f.getId() + " ";
		return resultado;
	}

	public void create(Usuario usuario) {
		Entidad eUsuario = this.usuarioToEntidad(usuario);
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());

	}

	public boolean delete(Usuario usuario) {
		// Borramos todas las fotos primero
		for (Photo p : usuario.getFotos()) {
			persistenciaPublicaciones.delete(p);
		}
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		return servPersistencia.borrarEntidad(eUsuario);
	}

	public void update(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals(PASSWORD)) {
				prop.setValor(usuario.getClave());
			} else if (prop.getNombre().equals(EMAIL)) {
				prop.setValor(usuario.getEmail());
			} else if (prop.getNombre().equals(NOMBRE)) {
				prop.setValor(usuario.getNombre());
			} else if (prop.getNombre().equals(LOGIN)) {
				prop.setValor(usuario.getUsername());
			} else if (prop.getNombre().equals(IMAGEN_PATH)) {
				prop.setValor(usuario.getImagenPath());
			} else if (prop.getNombre().equals(PRESENTACION)) {
				prop.setValor(usuario.getPresentacion());
			} else if (prop.getNombre().equals(PREMIUM)) {
				prop.setValor(Boolean.toString(usuario.isPremium()));
			} else if (prop.getNombre().equals(FOTOS)) {
				for (Photo f : usuario.getFotos()) {
					if (f.getId() == Photo.IDERROR) {
						persistenciaPublicaciones.create(f);
					}
					else {
						persistenciaPublicaciones.update(f);
					}
				} prop.setValor(obtenerCodigos(usuario.getFotos()));
			}
			servPersistencia.modificarPropiedad(prop);
		}

	}

	public Usuario get(int id) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);

		try {
			return entidadToUsuario(eUsuario);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Usuario> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);
		if(entidades.isEmpty()) return new ArrayList<Usuario>();
		List<Usuario> usuarios = entidades.stream().map(e -> get(e.getId())).collect(Collectors.toList());
		return usuarios;
	}

}
