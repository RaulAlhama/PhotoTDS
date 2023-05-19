package um.tds.phototds;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import um.tds.phototds.dominio.Photo;
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
			usuario1 = new Usuario("Raul Garcia", "raul.garciaa@um.es","raul" , "12", fecha, "TestPath", "TestPresentacion");
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
	
	@Test
	public void testUpdate2() {
		persistencia.create(usuario1);
		usuario1.setPremium(true);
		persistencia.update(usuario1);
		Usuario modificado = persistencia.get(usuario1.getId());
		assertEquals(modificado.isPremium(), true);
	}
	
	@Test
	public void testIsJoven() {
		assertEquals(usuario1.isJoven(), false);
	}
	
	@Test
	public void testIsPopular() {
		usuario1.addFoto("Hola a todos", "TestPath", new ArrayList<String>());
		List<Photo> fotos = usuario1.getFotos();
		fotos.get(0).setMeGustas(150);
		assertEquals(usuario1.esPopular(), true);
	}
	
	

}
