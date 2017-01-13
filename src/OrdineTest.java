import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ordinepackage.Ordine;
import prodottipackage.Prodotto;

public class OrdineTest {
private Ordine ord;
private ArrayList<Prodotto> lista1 = new ArrayList<Prodotto>();
private Prodotto p1= new Prodotto(134 ,"ribicus" ,"./Immagine/test1","questo è un test 1...",12,3.2);
private Prodotto p2= new Prodotto(25 ,"rosa" ,"./Immagine/test2","questo è un test 2...",12,3.2);
private ArrayList<Prodotto> lista2 = new ArrayList<Prodotto>();
private Prodotto p3= new Prodotto(47 ,"viola" ,"./Immagine/test3","questo è un test 3...",12,3.2);
private Prodotto p4= new Prodotto(98 ,"melissa" ,"./Immagine/test4","questo è un test 4...",12,3.2);



	@Before
	public void setUp() throws Exception {
		ord = new Ordine("mario","cccc","arrivato", 20.4 , 35);
		lista1.add(p1);
		lista1.add(p2);
		ord.setProdotto(lista1);
	}
	
	@After
	public void tearDown() throws Exception {
		ord = null;
	}
	
	//test get
	@Test
	public void testGetUtenteOrdine() {
		String t = ord.getUtenteOrdine();
		assertEquals("mario",t);
	}
	
	@Test
	public void testGetStato() {
		String t = ord.getStato();
		assertEquals("arrivato",t);
	}
	
	@Test
	public void testGetPrezzoTotale() {
		Double v = 20.4;
		Double t = ord.getPrezzoTotale();
		assertEquals(v,t);
	}
	
	@Test
	public void testGetId() {
		int t = ord.getId();
		assertEquals(35,t);
	}
	
	
	@Test
	public void testGetIban() {
		String t = ord.getIban();
		assertEquals("cccc",t);
	}
	
	@Test
	public void testGetProdotto() {
		ArrayList<Prodotto> l1 = (ArrayList<Prodotto>) lista1;
		ArrayList<Prodotto> l2 = (ArrayList<Prodotto>) ord.getProdotto();
		assertArrayEquals(l1.toArray(), l2.toArray());
	}
	
	//test set
		@Test
		public void testSetUtenteOrdine() {
			ord.setUtenteOrdine("trio");
			String t = ord.getUtenteOrdine();
			assertEquals("trio",t);
		}
		
		@Test
		public void testSetStato() {
			ord.setStato("spedito");
			String t = ord.getStato();
			assertEquals("spedito",t);
		}
		
		@Test
		public void testSetPrezzoTotale() {
			Double v = (14.5);
			ord.setPrezzoTotale(14.5);
			Double t = ord.getPrezzoTotale();
			assertEquals(v,t);
		}
		
		@Test
		public void testSetId() {
			ord.setId(44);
			int t = ord.getId();
			assertEquals(44,t);
		}
		
		
		@Test
		public void testSetIban() {
			ord.setIban("ffcc234");
			String t = ord.getIban();
			assertEquals("ffcc234",t);
		}
		@Test
		public void testSetProdotto() {
			lista2.add(p3);
			lista2.add(p4);
			ord.setProdotto(lista2);
			ArrayList<Prodotto> l1 = (ArrayList<Prodotto>) lista2;
			ArrayList<Prodotto> l2 = (ArrayList<Prodotto>) ord.getProdotto();
			assertArrayEquals(l1.toArray(), l2.toArray());
		}
	

}
