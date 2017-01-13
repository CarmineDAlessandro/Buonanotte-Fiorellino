import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ordinepackage.Ordine;

public class OrdineTest {
private Ordine ord;

	@Before
	public void setUp() throws Exception {
		ord = new Ordine("mario","cccc","arrivato", 20.4 , 35);
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
	

}
