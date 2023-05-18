package um.tds.phototds;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import um.tds.phototds.dominio.Usuario;
import um.tds.phototds.persistencia.DAOException;
import um.tds.phototds.persistencia.FactoriaDAO;
import um.tds.phototds.persistencia.UsuarioDAO;

public class TestUsuario {

	private final static Date fecha = Date.from(Instant.now());
	UsuarioDAO persistencia;
	Usuario usuario1;
	
	@Before
	public void setUp() {
		try {
			persistencia = FactoriaDAO.getInstancia().getUsuarioDAO();
			usuario1 = new Usuario("Raul Garcia", "raul.garciaa@um.es","raul" , "1234", fecha, "TestPath", "TestPresentacion");
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testCrearUsuario() {
		persistencia.create(usuario1);
		Usuario recuperado = persistencia.get(usuario1.getId());
		assertEquals(usuario1.getId(), recuperado.getId());
	}
	
	@Test
	public void testEliminarUsuario() {
		persistencia.create(usuario1);
		boolean result = persistencia.delete(usuario1);
		assertTrue(result);
	}
	
	@Test
	public void testUpdate() {
		persistencia.create(usuario1);
		usuario1.setPresentacion("Buenas tardes");
		persistencia.update(usuario1);
		Usuario modificado = persistencia.get(usuario1.getId());
		assertEquals(modificado.getPresentacion(), "Buenas tardes");
	}
	
	

}
