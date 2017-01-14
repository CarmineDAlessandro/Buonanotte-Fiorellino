package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import prodottipackage.Prodotto;

public class ProdottoTest {
	private Prodotto prd;

	@Before
	public void setUp() throws Exception {
		prd = new Prodotto(134 ,"ribicus" ,"./Immagine/test","questo è un test",12,3.2);
	}

	@After
	public void tearDown() throws Exception {
		prd = null;
	}

	
	//metodi get
	@Test
	public void testGetQuantita() {
		int t = prd.getQuantita();
		assertEquals(12,t);
	}

	//da errore se non utilizzo variabile v
	@Test
	public void testGetPrezzo() {
		Double v = 3.2;
		Double t = prd.getPrezzo();
		assertEquals(v,t);
	}
	
	@Test
	public void testGetNome() {
		String t = prd.getNome();
		assertEquals("ribicus",t);
	}
	
	@Test
	public void testGetIdProdotto() {
		int  t = prd.getIdProdotto();
		assertEquals(134,t);
	}
	
	@Test
	public void testGetDescrizione() {
		String  t = prd.getDescrizione();
		assertEquals("questo è un test",t);
	}
	
	@Test
	public void testGetUrlImmagine() {
		String  t = prd.getUrlImmagine();
		assertEquals("./Immagine/test",t);
	}
	
	
	//metodi set
	@Test
	public void testSetQuantita() {
		prd.setQuantita(50);
		assertEquals(50,prd.getQuantita());
	}

	//da errore se non utilizzo variabile v e t
	@Test
	public void testSetPrezzo() {
		Double v = 6.8;
		 prd.setPrezzo(6.8);
		 Double t= prd.getPrezzo();
		assertEquals(v,t);
	}
	
	@Test
	public void testSetNome() {
		 prd.setNome("rose");
		assertEquals("rose",prd.getNome());
	}
	
	@Test
	public void testSetIdProdotto() {
		 prd.setIdProdotto(22);
		assertEquals(22,prd.getIdProdotto());
	}
	
	@Test
	public void testSetDescrizione() {
		prd.setDescrizione("test ...");
		assertEquals("test ...",prd.getDescrizione());
	}
	
	@Test
	public void testSetUrlImmagine() {
		prd.setUrlImmagine("./Immagine/void");
		assertEquals("./Immagine/void",prd.getUrlImmagine());
	}
	
	
}
