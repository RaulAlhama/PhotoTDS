package um.tds.phototds;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import um.tds.phototds.dominio.Album;
import um.tds.phototds.dominio.Photo;
import um.tds.phototds.dominio.Publicacion;

public class TestPublicacion {
	private final static String fecha = LocalDate.now().toString();
	private final static String descripcion = "Test Descripcion";
	private final static String PATH = "Test path";
	private final static String titulo = "Album1";
	private ArrayList<String> hashtags;
	
	private Publicacion photo;
	private Album album;
	
	@Before
	public void setUp() {
		hashtags = new ArrayList<String>();
		hashtags.add(new String("#TestHashtag1"));
		hashtags.add(new String("#TestHashtag2"));
		photo = new Photo(fecha, descripcion, hashtags, PATH);
		album = new Album(titulo, fecha, descripcion, hashtags);
	}
	
	@Test
	public void testConstructor() {
		//Comprobamos que el constructor genere dos publicaciones diferentes con los mismos parámetros
		//Distinto ArrayList de "Comentarios" (Foto)
		Publicacion testPhoto =  new Photo(fecha, descripcion, hashtags, PATH);
		assertNotEquals(photo, testPhoto);
	}
	
	@Test
	public void testGetIdInicial() {
		//Comprobamos el IdInicial y el método getId
		int inicial = -1;
		assertEquals(photo.getId(), inicial);
	}
	//
	@Test
	public void testIdCambiado() {
		//Comprobamos el método setId
		photo.setId(123);
		assertEquals(photo.getId(), 123);
	}
	
	@Test
	public void testGetPath() {
		assertEquals(((Photo) photo).getPath(), PATH);
	}
	
	@Test
	public void testGetHashtags() {
		List<String> listaEsperada = new ArrayList<String>();
		listaEsperada.add("#TestHashtag1");
		listaEsperada.add("#TestHashtag2");
		assertEquals(photo.getHashtags(), listaEsperada);
	}
	
	@Test
	public void testDescripcion() {
		assertEquals(photo.getDescripcion(), descripcion);
	}
	
	@Test
	public void testGetFotos() {
		List<Photo> listaEsperada = new ArrayList<Photo>();
		listaEsperada.add((Photo) photo);
		 album.setFoto((Photo) photo);
		assertEquals(album.getFotos(), listaEsperada);
	}
	
	@Test
	public void testGetTituto() {
		assertEquals(album.getTitulo(), titulo);
	}
	
	@Test
	public void testGetMeGustas() {
		assertEquals(album.getMeGusta(), 0);
	}
	
	@Test
	public void testGetFecha() {
		assertEquals(photo.getFecha(), fecha);
	}
	
	
	
}
