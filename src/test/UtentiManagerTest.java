package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utentipackage.Amministratore;
import utentipackage.Utente;
import utentipackage.UtentiManager;

public class UtentiManagerTest {

	private UtentiManager model;
	private Utente utenteEsistente;
	private Utente utenteNonEsistente;
	private Amministratore amministratoreEsistente;
	private Amministratore amministratoreNonEsistente;
	
	@Before
	public void setUp() throws Exception {
		
		

		model = new UtentiManager();
		Date date = new Date(90,0,15);
		utenteEsistente = new Utente("carmelo", "sottile", "carmelosottile@libero.it", 
				"crmlstt993re138h", "roma", "salerno", "sa", "via libertas", "82034", 
				"carmelosottile", "pinko", 24, date);
		utenteNonEsistente = new Utente("marco", "sottile", "parco@mail.it", 
				"pqmlstt993re138h", "roma", "salerno", "sa", "via marzo", "82034", 
				"lollo870", "panicom", 24, date);
		amministratoreNonEsistente = new Amministratore("ale@gmail.com","alead","pablo");
		amministratoreEsistente = new Amministratore("siani@outlook.it","pinko","pippo");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoginUtente() throws SQLException {
		Utente u;
		//test su un account non presente nel database
		u = model.loginUtente(utenteNonEsistente.getUsername(), utenteNonEsistente.getPassword());
		assertNull(u.getUsername());
		//test su account esistente
		u = model.loginUtente(utenteEsistente.getUsername(), utenteEsistente.getPassword());
		assertEquals(utenteEsistente, u);
	}

	

	@Test
	public void testModificaUtente() throws SQLException {
		boolean flag;
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "luca", "nome");
		assertEquals(true,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "luca4", "nome");
		assertEquals(false,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "lucaqwerqwerqwerqwerqwerqwerqwersdfsdf", "nome");
		assertEquals(false,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "rispoli", "cognome");
		assertEquals(true,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "rispoli4", "cognome");
		assertEquals(false,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "rispoli4qwerqwerqwerqwerqwerqwerqwersdfsdf", "cognome");
		assertEquals(false,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "qwerqwerqw1rqwer", "cf");
		assertEquals(true,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "qwerqwerqwerqwer17", "cf");
		assertEquals(false,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "qwe,qwer.werqwer", "cf");
		assertEquals(false,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "Cava de' Tirreni", "citt‡N");
		assertEquals(true,flag);
		

		flag = model.ModificaUtente(utenteEsistente.getUsername(), "Cava de45 Tirreni", "citt‡N");
		assertEquals(false,flag);
		

		flag = model.ModificaUtente(utenteEsistente.getUsername(), 
				"Cava de' Tirreni Cava de' Tirreni Cava de' Tirreni Cava de' Tirreni Cava de' Tirreni",
				"citt‡N");
		assertEquals(false,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "Cava de' Tirreni", "citt‡R");
		assertEquals(true,flag);
		

		flag = model.ModificaUtente(utenteEsistente.getUsername(), "Cava de45 Tirreni", "citt‡R");
		assertEquals(false,flag);
		

		flag = model.ModificaUtente(utenteEsistente.getUsername(), 
				"Cava de' Tirreni Cava de' Tirreni Cava de' Tirreni Cava de' Tirreni Cava de' Tirreni",
				"citt‡R");
		assertEquals(false,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "84013", "cap");
		assertEquals(true,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "84A13", "cap");
		assertEquals(false,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "8401344", "cap");
		assertEquals(false,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "luca@gmail.com", "eMail");
		assertEquals(true,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "lucagmail.com", "eMail");
		assertEquals(false,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "lucasdrubalevincentemariointerforeverciaomondohellowordl40caratteri@gmail.com", "eMail");
		assertEquals(false,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "sa", "provinca");
		assertEquals(true,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "salerno", "provinca");
		assertEquals(false,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "s1", "provinca");
		assertEquals(false,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "a. salsano", "via");
		assertEquals(true,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "aniello salsano vicino cava de' tirrenni a pregiato dopo la statale", "via");
		assertEquals(false,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "aniello @salsano*", "via");
		assertEquals(false,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "18", "civico");
		assertEquals(true,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "1b", "civico");
		assertEquals(false,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "luca@12", "password");
		assertEquals(true,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "luca@12ablababfaganiqwjrngfriejnfidonvosdkmcoskmcokmokodskcmsok", "password");
		assertEquals(false,flag);
		
		flag = model.ModificaUtente(utenteEsistente.getUsername(), "1990-03-03", "data");
		assertEquals(true,flag);
		
		
		
		
		
	}

	@Test
	public void testRegistrazioneUtente() throws SQLException {
		boolean flag ;
		//registrazione con username gi‡ esistente
		flag = model.registrazioneUtente(utenteEsistente);
		assertEquals(false,flag);
		
		//registrazione con username non esistente
		flag = model.registrazioneUtente(utenteNonEsistente);
		assertEquals(true,flag);
		
	}

	@Test
	public void testEliminaUtente() throws SQLException {
		boolean flag;
		//test su account non presente nel databse
		flag = model.eliminaUtente(utenteNonEsistente.getUsername());
		assertEquals(false,flag);
		//test su account presente nel database
		flag = model.eliminaUtente(utenteEsistente.getUsername());
		assertEquals(true,flag);
	}
	
	@Test
	public void testLoginAmministratore() throws SQLException {
		Amministratore a;
		//test su un account non presente nel database
		a = model.loginAmministratore(amministratoreNonEsistente.getUsername(), amministratoreNonEsistente.getPassword());
		assertNull(a.getUsername());
		//test su account esistente
		a = model.loginAmministratore(amministratoreEsistente.getUsername(), amministratoreEsistente.getPassword());
		assertEquals(amministratoreEsistente, a);
	}

}
