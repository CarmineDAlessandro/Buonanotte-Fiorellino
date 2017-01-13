import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utentipackage.Utente;

public class UtenteTest {
	private Utente test;
	@Before
	public void setUp() throws Exception {
		Date data = new Date(System.currentTimeMillis());
		test = new Utente("mario", "rossi", "mamam@mh.it", "hgqweruhgnfhdisu", "sarno",
				"siano", "sa", "delle piazze", "84011", "marior", "marioo", 12, 
				data);
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testGetNome() {
		String t = test.getNome();
		assertEquals("mario",t);
	}
	
	@Test
	public void testGetCognome() {
		String t = test.getCognome();
		assertEquals("rossi",t);
	}
	
	@Test
	public void testGetMail() {
		String t = test.geteMail();
		assertEquals("mamam@mh.it",t);
	}
	
	@Test
	public void testGetCF() {
		String t = test.getCodiceFiscale();
		assertEquals("hgqweruhgnfhdisu",t);
	}
	
	@Test
	public void testGetCItt‡N() {
		String t = test.getCittaDiNascita();
		assertEquals("sarno",t);
	}
	
	@Test
	public void testGetCItt‡R() {
		String t = test.getCittaResidenza();
		assertEquals("siano",t);
	}
	
	@Test
	public void testGetProvincia() {
		String t = test.getProvincia();
		assertEquals("sa",t);
	}
	
	@Test
	public void testGetVia() {
		String t = test.getVia();
		assertEquals("delle piazze",t);
	}

	@Test
	public void testGetCAP() {
		String t = test.getCap();
		assertEquals("84011",t);
	}
	
	@Test
	public void testGetUsername() {
		String t = test.getUsername();
		assertEquals("marior",t);
	}
	
	@Test
	public void testGetPassword() {
		String t = test.getPassword();
		assertEquals("marioo",t);
	}
	
	@Test
	public void testGetDate() {
		Date t = test.getDataDiNascita();
		Date f = new Date(System.currentTimeMillis());
		assertEquals(f,t);
	}
	
	@Test
	public void testGetNumeroCivico() {
		int t = test.getNumeroCivico();
		assertEquals(12,t);
	}
	
	@Test
	public void testSetNome() {
		String t = "mario";
		test.setNome(t);
		assertEquals("mario",test.getNome());
	}
	
	@Test
	public void testSetCognome() {
		String t = "mario";
		test.setCognome(t);
		assertEquals("mario",test.getCognome());
	}
	
	@Test
	public void testSetMail() {
		String t = "mario";
		test.seteMail(t);
		assertEquals("mario",test.geteMail());
	}
	
	@Test
	public void testSetCF() {
		String t = "mario";
		test.setCodiceFiscale(t);
		assertEquals("mario",test.getCodiceFiscale());
	}
	
	@Test
	public void testSetCitt‡N() {
		String t = "mario";
		test.setCittaDiNascita(t);
		assertEquals("mario",test.getCittaDiNascita());
	}
	
	@Test
	public void testSetCitt‡R() {
		String t = "mario";
		test.setCittaResidenza(t);
		assertEquals("mario",test.getCittaResidenza());
	}
	
	@Test
	public void testSetProvincia() {
		String t = "mi";
		test.setProvincia(t);
		assertEquals("mi",test.getProvincia());
	}
	
	@Test
	public void testSetVia() {
		String t = "mario";
		test.setVia(t);
		assertEquals("mario",test.getVia());
	}
	
	@Test
	public void testSetCAP() {
		String t = "mario";
		test.setCap(t);
		assertEquals("mario",test.getCap());
	}
	
	@Test
	public void testSetUsername() {
		String t = "mario";
		test.setUsername(t);
		assertEquals("mario",test.getUsername());
	}
	
	@Test
	public void testSetDate() {
		Date data = new Date(System.currentTimeMillis());
		test.setDataDiNascita(data);
		assertEquals(data,test.getDataDiNascita());
	}
	
	@Test
	public void testSetPassword() {
		String t = "mario";
		test.setPassword(t);
		assertEquals("mario",test.getPassword());
	}
	
	@Test
	public void testSetNumeroCivico() {
		int t = 1;
		test.setNumeroCivico(t);
		assertEquals(1,test.getNumeroCivico());
	}
	
	
}
