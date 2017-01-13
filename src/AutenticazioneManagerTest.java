package it.ubmplatform.autenticazione;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.ubmplatform.account.Account;
import it.ubmplatform.autenticazione.AutenticazioneManager;
import it.ubmplatform.profilo.Profilo;

public class AutenticazioneManagerTest {
	private Account existingAccountR, existingAccountIOk, existingAccountINonOk, existingAccountB, existingAccountA, notExistingAccount;
	private Profilo profiloCompleto, profiloNonCompleto;
	private AutenticazioneManager manager;

	@Before
	public void setUp() throws Exception {
		manager = new AutenticazioneManager();
		existingAccountR = new Account("sara5@studenti.unisa.it", "Password1", "R");
		existingAccountIOk = new Account("francesca@studenti.unisa.it", "Password2", "I");
		existingAccountIOk.setDataInvalidazione(new Date (2016, 12, 20));
		existingAccountINonOk = new Account("paolo@studenti.unisa.it", "Password4", "I");
		existingAccountINonOk.setDataInvalidazione(new Date (2017, 01, 05));
		existingAccountB = new Account("maria@studenti.unisa.it", "Password3", "B");
		existingAccountA = new Account("admin@studenti.unisa.it", "Passadmin1", null);
		notExistingAccount = new Account("nonesisto@studenti.unisa.it", "nonesiste", null);
		profiloNonCompleto = new Profilo("maddalena@studenti.unisa.it", "null", "null", null, null, null, null, new java.util.Date(1995, 6, 06));
		profiloCompleto = new Profilo("sara15@studenti.unisa.it", "Sara", "Unisa", "Avellino", "9876543210", "Musica", null, new java.util.Date(1995, 5, 15));
	}

	@After
	public void tearDown() throws Exception {
		manager = null;
		existingAccountR = null;
		notExistingAccount = null;
	}

	@Test
	public void testQueryLogin()
	{
		int test;
		
		//test su un account non presente nel database
		test = manager.queryLogin(notExistingAccount.getEmail(), notExistingAccount.getPassword());
		assertEquals(-1, test);
		
		//test su un account esistente di un amministratore
		test = manager.queryLogin(existingAccountA.getEmail(), existingAccountA.getPassword());
		assertEquals(0, test);
		
		//test su un account esistente di un utente Regolare
		test = manager.queryLogin(existingAccountR.getEmail(), existingAccountR.getPassword());
		assertEquals(1, test);
		
		//test su un account esistente di un utente Invalidato
		test = manager.queryLogin(existingAccountIOk.getEmail(), existingAccountIOk.getPassword());
		assertEquals(2, test);
		
		//test su un account esistente di un utente Bannato
		test = manager.queryLogin(existingAccountB.getEmail(), existingAccountB.getPassword());
		assertEquals(3, test);
		
	}

	@Test
	public void testQueryRecuperaPassword()
	{
			String test;
			
			//test su un'e-mail non presente nel database
			test = manager.queryRecuperaPassword(notExistingAccount.getEmail());
			assertEquals(null, test);
			
			//test su un'e-mail presente nel database
			test = manager.queryRecuperaPassword(existingAccountR.getEmail());
			assertEquals("Password1", test);
	}

	@Test
	public void testQueryEstraiNome()
	{
		String test;
		
		//test su un account che non ha inserito il nome nel proprio profilo
		test = manager.queryEstraiNome(profiloNonCompleto.getEmail());
		assertEquals("null", test);
		
		//test su un account che ha inserito il nome nel proprio profilo
		test = manager.queryEstraiNome(profiloCompleto.getEmail());
		assertEquals("Sara", test);
		
	}

	@Test
	public void testQueryControllaData()
	{	
		long dataMill = System.currentTimeMillis();
		int test;
		
		//test su un account che non può ancora effettuare l'accesso
		test = manager.queryControllaData(dataMill, existingAccountINonOk.getEmail());
		assertEquals(4, test);
		
		//test su un account che può effettuare l'accesso
		test = manager.queryControllaData(dataMill, existingAccountIOk.getEmail());
		assertEquals(0, test);
	}

}
