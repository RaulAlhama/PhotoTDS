package um.tds.phototds.persistencia;

import java.util.List;

import um.tds.phototds.dominio.Foto;

public interface FotoDAO {
	void create(Foto foto);
	boolean delete(Foto foto);
	void update(Foto foto);
	Foto get(int id);
	List<Foto> getAll();
	
}
