package um.tds.phototds.persistencia;

import java.util.List;

import um.tds.phototds.dominio.Usuario;


public interface UsuarioDAO {
	void create(Usuario asistente);
	boolean delete(Usuario asistente);
	void update(Usuario asistente);
	Usuario get(int id);
	List<Usuario> getAll();
}
