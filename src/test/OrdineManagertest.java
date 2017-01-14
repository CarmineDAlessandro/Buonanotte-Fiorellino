package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import carrelloPackage.Carrello;
import ordinepackage.Ordine;
import ordinepackage.OrdineManager;
import prodottipackage.Prodotto;
import utentipackage.Utente;

public class OrdineManagertest {
	OrdineManager model;
	Utente utente;
	Carrello esiste;
	Carrello nonEsiste;
	
	@Before
	public void setUp() throws Exception {
		model = new OrdineManager();
		esiste = new Carrello(1, 0, new ArrayList<Prodotto>());
		nonEsiste = new Carrello(0,0, new ArrayList<Prodotto>());
		utente = new Utente("carmelo","sottile","nnjn@mkm.it","cava","cava","cava","sa","salsano",
				"84013","carmelosottile","carmen",2,new Date(90,0,15));
	}

	@After
	public void tearDown() throws Exception {
		model = null;
		esiste = null;
		nonEsiste = null;
	}

	@Test
	public void testReturnOrdiniAmministratore() throws SQLException {
		ArrayList<Ordine> lista = model.returnOrdiniAmministratore();
		assertNotNull(lista);
	}

	@Test
	public void testReturnOrdiniUtente() throws SQLException {
		ArrayList<Ordine> lista = model.returnOrdiniUtente("roccomiele1");
		assertNotNull(lista);
	}

	@Test
	public void testAvanzaStato() throws SQLException {
		boolean flag;
		flag = model.avanzaStato("Spedito", 1);
		assertEquals(true,flag);
		flag = model.avanzaStato("Spedito", 122);
		assertEquals(false, flag);
	}

	@Test
	public void testCreaOrdine() throws SQLException {
		Ordine flag;
		flag = model.creaOrdine(esiste, utente);
		assertNotNull(flag);
		flag = model.creaOrdine(nonEsiste, utente);
		assertNull(flag);
	}

	@Test
	public void testInserisciIban() {
		fail("Not yet implemented");
	}

}
