package um.tds.phototds.persistencia;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
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

	private ServicioPersistencia servPersistencia;

	public TDSUsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	private Usuario entidadToUsuario(Entidad eUsuario) throws ParseException {

		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		String login = servPersistencia.recuperarPropiedadEntidad(eUsuario, LOGIN);
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
		String fechaNacimiento = servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
		String premium = servPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM);
		String imagenPath = servPersistencia.recuperarPropiedadEntidad(eUsuario, IMAGEN_PATH);
		String presentacion = servPersistencia.recuperarPropiedadEntidad(eUsuario, PRESENTACION);
		Usuario usuario = new Usuario(nombre, email, login, password, obtenerFecha(fechaNacimiento), imagenPath, presentacion);
		usuario.setId(eUsuario.getId());
		usuario.setPremium(Boolean.valueOf(premium));

		return usuario;
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

		eUsuario.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad(NOMBRE, usuario.getNombre()), new Propiedad(EMAIL, usuario.getEmail()),
						new Propiedad(LOGIN, usuario.getUsername()), new Propiedad(PASSWORD, usuario.getClave()),
						new Propiedad(FECHA_NACIMIENTO, usuario.getFechaNacimiento().toString()),
						new Propiedad(IMAGEN_PATH, usuario.getImagenPath()),
						new Propiedad(PRESENTACION, usuario.getPresentacion()))));
		return eUsuario;
	}

	public void create(Usuario usuario) {
		Entidad eUsuario = this.usuarioToEntidad(usuario);
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());

	}

	public boolean delete(Usuario usuario) {
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

		List<Usuario> usuarios = new LinkedList<Usuario>();
		for (Entidad eUsuario : entidades) {
			usuarios.add(get(eUsuario.getId()));
		}

		return usuarios;
	}

}
