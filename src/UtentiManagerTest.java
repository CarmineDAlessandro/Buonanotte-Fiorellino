import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utentipackage.Utente;
import utentipackage.UtentiManager;

public class UtentiManagerTest {

	private UtentiManager model;
	private Utente utenteEsistente;
	private Utente utenteNonEsistente;
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
	public void testEliminaUtente() {
		fail("Not yet implemented");
	}

	@Test
	public void testModificaUtente() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegistrazioneUtente() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoginAmministratore() {
		fail("Not yet implemented");
	}

}
