package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import carrelloPackage.Carrello;
import prodottipackage.Prodotto;

public class CarrelloTest {
	Carrello carrello;
	ArrayList<Prodotto> prodotti;
	@Before
	public void setUp() throws Exception {
		prodotti = new ArrayList <Prodotto>();
		
		carrello = new Carrello (4,5,prodotti);
		
	}

	@After
	public void tearDown() throws Exception {
		carrello = null;
		prodotti = null;
	}

	@Test
	public void testSetPrezzo() {
		carrello.setPrezzo(50);
		assertEquals(50,carrello.getPrezzo(),0.001);
	}

	@Test
	public void testGetPrezzo() {
		assertEquals(5,carrello.getPrezzo(),0.01);
	}

	@Test
	public void testGetLista() {
		assertEquals(prodotti,carrello.getLista());
	}

	@Test
	public void testSetLista() {
		ArrayList <Prodotto> lista2 = new ArrayList <Prodotto> ();
		carrello.setLista(lista2);
		assertEquals(lista2,carrello.getLista());
	}

	@Test
	public void testGetId() {
		assertEquals(4,carrello.getId());
	}

	@Test
	public void testSetId() {
		carrello.setId(11);
		assertEquals(11,carrello.getId());
	}

}



