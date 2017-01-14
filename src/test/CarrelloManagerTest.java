package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import carrelloPackage.Carrello;
import carrelloPackage.CarrelloManager;


public class CarrelloManagerTest {
	CarrelloManager model;

	@Before
	public void setUp() throws Exception {
		model = new CarrelloManager();
	
	}

	@After
	public void tearDown() throws Exception {
		model = null;
	}

	@Test
	public void testGetCarrello() throws SQLException {
		Carrello c;
		c = model.getCarrello("roccomiele1");
		assertNotNull(c);
	}

	@Test
	public void testAggiungiProdottoCarrello() throws SQLException {
		boolean flag;
		flag = model.aggiungiProdottoCarrello("roccomiele1", 2, 250); //aggiungo quantit‡ eccessiva
		assertEquals(false,flag);
		flag = model.aggiungiProdottoCarrello("roccomiele1", 2, 10); //aggiungo quantit‡ eccessiva
		assertEquals(true,flag);
	}

	@Test
	public void testEliminaProdottoCarrello() throws SQLException {
		boolean flag;
		flag = model.eliminaProdottoCarrello(3, "2"); //provo a cancellare un prodotto non presente
		assertEquals(false,flag);
		flag = model.eliminaProdottoCarrello(2,"8");
		assertEquals(true,flag);
	}

	@Test
	public void testCambiaQuantit‡Carrello() throws SQLException {
		boolean flag;
		flag = model.cambiaQuantit‡Carrello(1, 200, "1");
		assertEquals(false,flag);
		flag = model.cambiaQuantit‡Carrello(1, 10, "1");
		assertEquals(true, flag);
	}

}
