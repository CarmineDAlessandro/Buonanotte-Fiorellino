package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import prodottipackage.ProdottiManager;
import prodottipackage.Prodotto;

public class ProdottiManagerTest {
	ProdottiManager model;
	Prodotto p;
	Prodotto p2;
	Prodotto pNuovo;
	Prodotto pNonEsiste;
	
	@Before
	public void setUp() throws Exception {
		model = new ProdottiManager();
		p = new Prodotto(3, "viola", "./Immagini/viola.jpg", "la viola  ......", 140, 1.00);
		p2 = new Prodotto(9, "tulipano", "./Immagini/tulipano.jpg","la tulipano  ......",140,0.55);
		pNuovo = new Prodotto(4,"viola45","./Immagini/viola.jpg"," ......", 140, 1.00);
		pNonEsiste = new Prodotto(174,"viola45","./Immagini/viola.jpg"," ......", 140, 1.00);
	}

	@After
	public void tearDown() throws Exception {
		model = null;
		p = null;
		p2 = null;
		pNuovo = null;
		pNonEsiste = null;
	}

	@Test
	public void testReturnProdotti() throws SQLException {
		ArrayList<Prodotto> lista = model.returnProdotti();
		assertNotNull(lista);
	}

	@Test
	public void testAggiungiProdotto() throws SQLException {
		boolean flag;
		flag = model.aggiungiProdotto(p2); //provo ad aggiungere un prodotto già esistente
		assertEquals(false, flag);
		//aggiungo un nuovo prodotto
		flag = model.aggiungiProdotto(pNuovo);
		assertEquals(true, flag);
	}



	@Test
	public void testModificaProdotto() throws SQLException {
		boolean flag;
		flag = model.modificaProdotto(p.getIdProdotto(), "fiore", "nome");
		assertEquals(true,flag);
		
		flag = model.modificaProdotto(p.getIdProdotto(), "margherita", "nome");
		assertEquals(false,flag);
		
		flag = model.modificaProdotto(p.getIdProdotto(), "fioremargheritawewewqwerqwerqwerqwerqwerqwerqwersdfsdf", "nome");
		assertEquals(false,flag);
		
		flag = model.modificaProdotto(p.getIdProdotto(), "fiore.", "nome");
		assertEquals(false,flag);
		
		flag = model.modificaProdotto(p.getIdProdotto(), "descrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteridescrizione maggiore di 300 caratteri",
				"descrizione");
		assertEquals(false,flag);
		
		flag = model.modificaProdotto(p.getIdProdotto(),"descrizione minore di 300 caratteri", "descrizione");
		assertEquals(true,flag);
		
		flag = model.modificaProdotto(p.getIdProdotto(), "4","prezzo");
		assertEquals(true,flag);
		
		flag = model.modificaProdotto(p.getIdProdotto(), "-4","prezzo");
		assertEquals(false,flag);
		
		flag = model.modificaProdotto(p.getIdProdotto(), "12", "quantità");
		assertEquals(true,flag);
		
		flag = model.modificaProdotto(p.getIdProdotto(), "0", "quantità");
		assertEquals(false, flag);
		
		flag = model.modificaProdotto(p.getIdProdotto(), "./Immagini/viola.jpg", "img");
		assertEquals(true,flag);
		
	}
	
	@Test
	public void testEliminaProdotto() throws SQLException {
		boolean flag;
		//test con un prodotto non esistente
		flag = model.eliminaProdotto(pNonEsiste.getIdProdotto());
		assertEquals(false, flag);
		//test con prodotto esistente
		flag = model.eliminaProdotto(p.getIdProdotto());
		assertEquals(true,flag);
	}

	@Test
	public void testRicercaNumeroMin() throws SQLException {
		ArrayList<Prodotto> lista;
		lista = model.ricercaNumeroMin(10);
		assertNotNull(lista);
	}

	@Test
	public void testRicercaNumeroMax() throws SQLException {
		ArrayList<Prodotto> lista;
		lista = model.ricercaNumeroMax(10);
		assertNotNull(lista);
	}

	@Test
	public void testRicercaNome() throws SQLException {
		ArrayList<Prodotto> lista;
		lista = model.ricercaNome("lilium");
		assertNotNull(lista);
	}

}
