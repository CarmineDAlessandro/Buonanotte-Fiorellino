package utentipackage;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**Questa è la classe gestore dell'oggetto "Utente". Si occupa
 * di effettuare tutte le operazioni sul database relative agli utenti*/
public class UtentiManager {
	private static DataSource ds;
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/fiorazon");
			
		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	// __________________________________________________________________________________________________
	/**Questo metodo è il metodo di login. Ha come parametri in input le 
	 * credenziali di un utente, ovvero rispettivamente username e
	 * password e ritorna l'oggetto utente associato a quelle credenziali*/
	public Utente loginUtente(String username, String password) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null;
		String selectSQL = "select * from utente where username = ? and password = ?";
		Utente usr = new Utente();
		try {
			conn = ds.getConnection();
			preparedStatement1 = conn.prepareStatement(selectSQL);
			preparedStatement1.setString(1, username);
			preparedStatement1.setString(2, password);

			ResultSet rs = preparedStatement1.executeQuery();
			if (rs.next()) {
				usr.setNome(rs.getString("nome"));
				usr.setCognome(rs.getString("cognome"));
				usr.seteMail(rs.getString("eMail"));
				usr.setCodiceFiscale(rs.getString("codiceFiscale"));
				usr.setDataDiNascita(rs.getDate("dataNascita"));
				usr.setCittaDiNascita(rs.getString("cittaNascita"));
				usr.setCittaResidenza(rs.getString("cittaResidenza"));
				usr.setProvincia(rs.getString("Provincia"));
				usr.setVia(rs.getString("via"));
				usr.setNumeroCivico(rs.getInt("numeroCivico"));
				usr.setCap(rs.getString("cap"));
				usr.setUsername(rs.getString("username"));
				usr.setPassword(rs.getString("password"));
				
			}

		} finally {
			try {
				if (preparedStatement1 != null) {
					preparedStatement1.close();
				}
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		return usr;
	}

	/**Questo metodo permette di eliminare un utente presente nel database.
	 * Ha come valore in input l'username dell'utente da eliminare*/
	public void eliminaUtente(String username) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement7 = null;

		String SQL7 = " DELETE FROM utente WHERE username = ?";
		try {
			conn = ds.getConnection();
			preparedStatement7 = conn.prepareStatement(SQL7);
			preparedStatement7.setString(1, username);
			preparedStatement7.execute();

		} finally {
			try {

				if (preparedStatement7 != null) {
					preparedStatement7.close();
				}
			} finally {
				if (conn != null)
					conn.close();

			}
		}

	}
	/**Questo metodo permette di modificare i dati di un utente.
	 * Ha come parametro in input un oggetto Utente contenente 
	 * tutti i dati modificati. Esso andrà a sostituire nel
	 * database tutti i dati relativi all'utente avente lo stesso
	 * username dell'oggetto utente passato in input*/
	public boolean ModificaUtente(String username, String dato, String action) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement5 = null;
		if(dato == null) return false;
		if (action.equals("nome")) {
			if (dato.length() > 30) return false;
			for (int i=0; i < dato.length(); i ++) {
				if(!Character.isLetter(dato.charAt(i))) {
					return false; 
					
				}
			}
			String SQL = " UPDATE utente SET nome = ? WHERE username = ?";
			try {
				conn = ds.getConnection();
				preparedStatement5 = conn.prepareStatement(SQL);
				preparedStatement5.setString(1, dato);
				preparedStatement5.setString(2, username);
				preparedStatement5.execute();
				return true;
			} finally {
				try {
					if (preparedStatement5 != null ) {
						preparedStatement5.close();
						}  
				} finally {
					if (conn != null)
						conn.close();
	
				}
			}

		}
		if (action.equals("cognome")) {
			if (dato.length() > 30) return false;
			for (int i=0; i < dato.length(); i ++) {
				if(!Character.isLetter(dato.charAt(i))) {
					return false; 
					
				}
			}
			String SQL = " UPDATE utente SET cognome = ? WHERE username = ?";
			try {
				conn = ds.getConnection();
				preparedStatement5 = conn.prepareStatement(SQL);
				preparedStatement5.setString(1, dato);
				preparedStatement5.setString(2, username);
				preparedStatement5.execute();
				return true;
			} finally {
				try {
					if (preparedStatement5 != null ) {
						preparedStatement5.close();
						}  
				} finally {
					if (conn != null)
						conn.close();
	
				}
			}

		}
		if (action.equals("cf")) {
			if (dato.length() != 16) return false;
			for (int i=0; i < dato.length(); i ++) {
				if(!Character.isLetterOrDigit(dato.charAt(i))) {
					return false; 
					
				}
			}
			String SQL = " UPDATE utente SET codiceFiscale = ? WHERE username = ?";
			try {
				conn = ds.getConnection();
				preparedStatement5 = conn.prepareStatement(SQL);
				preparedStatement5.setString(1, dato);
				preparedStatement5.setString(2, username);
				preparedStatement5.execute();
				return true;
			} finally {
				try {
					if (preparedStatement5 != null ) {
						preparedStatement5.close();
						}  
				} finally {
					if (conn != null)
						conn.close();
	
				}
			}

		}
		if (action.equals("cittàN")) {
			if (dato.length() > 40) return false;
			for (int i=0; i < dato.length(); i ++) {
				if(!Character.isLetter(dato.charAt(i))) {
					return false; 
					
				}
			}
			String SQL = " UPDATE utente SET cittaNascita = ? WHERE username = ?";
			try {
				conn = ds.getConnection();
				preparedStatement5 = conn.prepareStatement(SQL);
				preparedStatement5.setString(1, dato);
				preparedStatement5.setString(2, username);
				preparedStatement5.execute();
				return true;
			} finally {
				try {
					if (preparedStatement5 != null ) {
						preparedStatement5.close();
						}  
				} finally {
					if (conn != null)
						conn.close();
	
				}
			}

		}
		if (action.equals("cittàR")) {
			if (dato.length() > 40) return false;
			for (int i=0; i < dato.length(); i ++) {
				if(!Character.isLetter(dato.charAt(i))) {
					return false; 
					
				}
			}
			String SQL = " UPDATE utente SET cittaResidenza = ? WHERE username = ?";
			try {
				conn = ds.getConnection();
				preparedStatement5 = conn.prepareStatement(SQL);
				preparedStatement5.setString(1, dato);
				preparedStatement5.setString(2, username);
				preparedStatement5.execute();
				return true;
			} finally {
				try {
					if (preparedStatement5 != null ) {
						preparedStatement5.close();
						}  
				} finally {
					if (conn != null)
						conn.close();
	
				}
			}

		}
		if (action.equals("cap")) {
			if (dato.length() != 5) return false;
			for (int i=0; i < dato.length(); i ++) {
				if(!Character.isDigit(dato.charAt(i))) {
					return false; 
					
				}
			}
			String SQL = " UPDATE utente SET cap = ? WHERE username = ?";
			try {
				conn = ds.getConnection();
				preparedStatement5 = conn.prepareStatement(SQL);
				preparedStatement5.setString(1, dato);
				preparedStatement5.setString(2, username);
				preparedStatement5.execute();
				return true;
			} finally {
				try {
					if (preparedStatement5 != null ) {
						preparedStatement5.close();
						}  
				} finally {
					if (conn != null)
						conn.close();
	
				}
			}

		}
		if (action.equals("eMail")) {
			if (dato.length() > 30) return false;
			String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			if(!dato.matches(emailPattern)) return false;
			String SQL = " UPDATE utente SET eMail = ? WHERE username = ?";
			try {
				conn = ds.getConnection();
				preparedStatement5 = conn.prepareStatement(SQL);
				preparedStatement5.setString(1, dato);
				preparedStatement5.setString(2, username);
				preparedStatement5.execute();
				return true;
			} finally {
				try {
					if (preparedStatement5 != null ) {
						preparedStatement5.close();
						}  
				} finally {
					if (conn != null)
						conn.close();
	
				}
			}

		}
		if (action.equals("provincia")) {
			if (dato.length() != 2) return false;
			for (int i=0; i < dato.length(); i ++) {
				if(!Character.isLetter(dato.charAt(i))) {
					return false; 
					
				}
			}
			String SQL = " UPDATE utente SET provincia = ? WHERE username = ?";
			try {
				conn = ds.getConnection();
				preparedStatement5 = conn.prepareStatement(SQL);
				preparedStatement5.setString(1, dato);
				preparedStatement5.setString(2, username);
				preparedStatement5.execute();
				return true;
			} finally {
				try {
					if (preparedStatement5 != null ) {
						preparedStatement5.close();
						}  
				} finally {
					if (conn != null)
						conn.close();
	
				}
			}

		}
		if (action.equals("via")) {
			if (dato.length() > 30) return false;
			for (int i=0; i < dato.length(); i ++) {
				if(!Character.isLetterOrDigit(dato.charAt(i)) && !Character.isWhitespace(dato.charAt(i))) {
					return false; 
					
				}
			}
			String SQL = " UPDATE utente SET via = ? WHERE username = ?";
			try {
				conn = ds.getConnection();
				preparedStatement5 = conn.prepareStatement(SQL);
				preparedStatement5.setString(1, dato);
				preparedStatement5.setString(2, username);
				preparedStatement5.execute();
				return true;
			} finally {
				try {
					if (preparedStatement5 != null ) {
						preparedStatement5.close();
						}  
				} finally {
					if (conn != null)
						conn.close();
	
				}
			}

		}
		if (action.equals("civico")) {
			
			for (int i=0; i < dato.length(); i ++) {
				if(!Character.isDigit(dato.charAt(i))) {
					return false; 
					
				}
			}
			String SQL = " UPDATE utente SET civico = ? WHERE username = ?";
			try {
				conn = ds.getConnection();
				preparedStatement5 = conn.prepareStatement(SQL);
				preparedStatement5.setString(1, dato);
				preparedStatement5.setString(2, username);
				preparedStatement5.execute();
				return true;
			} finally {
				try {
					if (preparedStatement5 != null ) {
						preparedStatement5.close();
						}  
				} finally {
					if (conn != null)
						conn.close();
	
				}
			}

		}
		if (action.equals("cap")) {
			if (dato.length() != 5) return false;
			for (int i=0; i < dato.length(); i ++) {
				if(!Character.isDigit(dato.charAt(i))) {
					return false; 
					
				}
			}
			String SQL = " UPDATE utente SET cap = ? WHERE username = ?";
			try {
				conn = ds.getConnection();
				preparedStatement5 = conn.prepareStatement(SQL);
				preparedStatement5.setString(1, dato);
				preparedStatement5.setString(2, username);
				preparedStatement5.execute();
				return true;
			} finally {
				try {
					if (preparedStatement5 != null ) {
						preparedStatement5.close();
						}  
				} finally {
					if (conn != null)
						conn.close();
	
				}
			}

		}
		if (action.equals("password")) {
			if (dato.length() >30 ) return false;
			
			String SQL = " UPDATE utente SET password = ? WHERE username = ?";
			try {
				conn = ds.getConnection();
				preparedStatement5 = conn.prepareStatement(SQL);
				preparedStatement5.setString(1, dato);
				preparedStatement5.setString(2, username);
				preparedStatement5.execute();
				return true;
			} finally {
				try {
					if (preparedStatement5 != null ) {
						preparedStatement5.close();
						}  
				} finally {
					if (conn != null)
						conn.close();
	
				}
			}

		}
		if (action.equals("data")) {
			
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			Date data_nascita = null;
			try {
				data_nascita = df.parse(dato);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				String SQL = " UPDATE utente SET dataNascita = ? WHERE username = ?";
				try {
					conn = ds.getConnection();
					preparedStatement5 = conn.prepareStatement(SQL);
					java.sql.Date sqlDate = new java.sql.Date(data_nascita.getTime());
					preparedStatement5.setDate(1, sqlDate);
					preparedStatement5.setString(2, username);
					preparedStatement5.execute();
					return true;
				} finally {
					try {
						if (preparedStatement5 != null ) {
							preparedStatement5.close();
							}  
					} finally {
						if (conn != null)
							conn.close();
		
					}
				}
	
		}
		return false;
		
	}
	/**Questo metodo permette di registrare un nuovo utente.
	 * Ha come parametro in input l'utente da inserire nel database*/
	public boolean registrazioneUtente (Utente usr) throws SQLException {
		
		Connection conn = null;
		Connection conn2 = null;
		PreparedStatement  preparedStatement2 = null;
		PreparedStatement provaStatement = null;
		String insertSQL = "insert into utente (nome,cognome,eMail,codiceFiscale,dataNascita,"
				+ "cittaNascita,cittaResidenza,provincia,via,numeroCivico,cap,username,password)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String provaSQL ="SELECT username FROM utente WHERE username = ?";
		try {
			conn2 = ds.getConnection();
			provaStatement = conn2.prepareStatement(provaSQL);
			provaStatement.setString(1, usr.getUsername());
			ResultSet rs = provaStatement.executeQuery();
			if(!rs.next()) {
			conn = ds.getConnection();

			preparedStatement2 = conn.prepareStatement(insertSQL);
			preparedStatement2.setString(1, usr.getNome());
			preparedStatement2.setString(2, usr.getCognome());
			preparedStatement2.setString(3, usr.geteMail());
			preparedStatement2.setString(4, usr.getCodiceFiscale());
			java.sql.Date sqlDate = new java.sql.Date(usr.getDataDiNascita().getTime());
			preparedStatement2.setDate(5, sqlDate);
			preparedStatement2.setString(6, usr.getCittaDiNascita());
			preparedStatement2.setString(7, usr.getCittaResidenza());
			preparedStatement2.setString(8, usr.getProvincia());
			preparedStatement2.setString(9, usr.getVia());
			preparedStatement2.setInt(10, usr.getNumeroCivico());
			preparedStatement2.setString(11, usr.getCap());
			preparedStatement2.setString(12, usr.getUsername());
			preparedStatement2.setString(13, usr.getPassword());
			
			preparedStatement2.executeUpdate();
			return true;
		}	else return false;
		} finally {
			try {
				if ( preparedStatement2 != null) {
					preparedStatement2.close();
				}
				if ( provaStatement != null) {
					provaStatement.close();
				}
			} finally {
				if (conn != null)
					conn.close();
			}
				if (conn2 != null)
					conn2.close();
		} 
	
}
	
	
	//__________________
	/**Questo metodo permette all'amministratore di fare il login.
	 * Ha come parametri in input l'username e la password che, se 
	 * corrispondenti al profilo amministratore, faranno ritornare al
	 * metodo un oggetto Amministratore contenente tutte le informazioni
	 * relative all'amministratore*/
	public Amministratore loginAmministratore (String username, String password) throws SQLException{
		Connection conn = null;
		PreparedStatement preparedStatement1 = null;
		String selectSQL = "select * from amministratore where username = ? and password = ?";
		Amministratore usr = new Amministratore();
		try {
			conn = ds.getConnection();
			preparedStatement1 = conn.prepareStatement(selectSQL);
			preparedStatement1.setString(1, username);
			preparedStatement1.setString(2, password);

			ResultSet rs = preparedStatement1.executeQuery();
			if (rs.next()) {
				usr.seteMail(rs.getString("eMail"));
				usr.setUsername(rs.getString("username"));
				usr.setPassword(rs.getString("password"));
			}

		} finally {
			try {
				if (preparedStatement1 != null) {
					preparedStatement1.close();
				}
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		return usr;
	}

}
