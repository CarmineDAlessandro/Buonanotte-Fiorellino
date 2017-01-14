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
	Ordine ordine;
	Ordine ordine2;
	Utente utente;
	Carrello esiste;
	Carrello esiste2;
	Carrello nonEsiste;
	
	@Before
	public void setUp() throws Exception {
		model = new OrdineManager();
		esiste = new Carrello(1, 0, new ArrayList<Prodotto>());
		esiste2 = new Carrello(3, 0, new ArrayList<Prodotto>());
		nonEsiste = new Carrello(0,0, new ArrayList<Prodotto>());
		utente = new Utente("carmelo","sottile","nnjn@mkm.it","cava","cava","cava","sa","salsano",
				"84013","carmelosottile","carmen",2,new Date(90,0,15));
		ordine = new Ordine("carmelosottile","4023000000000000","Da spedire",0,1);
		ordine2 = new Ordine("alessandrazullo1","4023000000000000","Da spedire",0,7);
	}

	@After
	public void tearDown() throws Exception {
		model = null;
		esiste = null;
		esiste2 = null;
		nonEsiste = null;
		ordine = null;
		ordine2 = null;
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
	public void testInserisciIban() throws SQLException {
		boolean flag;
		flag = model.inserisciIban(ordine, esiste, "4023000000000000");
		assertEquals(true, flag);
		flag = model.inserisciIban(ordine, esiste, "402300000000000017");
		assertEquals(false, flag);
		flag = model.inserisciIban(ordine, esiste, "402300000000000a");
		assertEquals(false, flag);
		flag = model.inserisciIban(ordine2, esiste2, "4023000000000000"); //quantità eccessiva
		assertEquals(false, flag);
		
	}

}
