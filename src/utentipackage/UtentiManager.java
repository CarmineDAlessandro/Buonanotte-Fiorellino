package utentipackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		String selectSQL = "select username from utente where username = ? and password = ?";
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
				usr.setVia(rs.getString("via"));
				usr.setCittaResidenza(rs.getString("Provincia"));
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
	public void ModificaUtente(Utente usr) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement5 = null;

		String SQL5 = " UPDATE utente SET nome = ?, cognome = ?, eMail = ?, codiceFiscale = ?,"
				+ " dataNascita = ?, cittaNascita = ?, provincia = ?,"
				+ " cittaResidenza = ?, via = ?, numeroCivico = ?,"
				+ " cap = ?, password = ? WHERE username = ?";
		
		try {
			conn = ds.getConnection();
			
							//modifica di utente
								preparedStatement5 = conn.prepareStatement(SQL5);
								preparedStatement5.setString(1, usr.getNome());
								preparedStatement5.setString(2, usr.getCognome());
								preparedStatement5.setString(3, usr.geteMail());
								preparedStatement5.setString(4, usr.getCodiceFiscale());
								preparedStatement5.setDate(5, usr.getDataDiNascita());
								preparedStatement5.setString(6, usr.getCittaDiNascita());
								preparedStatement5.setString(7, usr.getProvincia());
								preparedStatement5.setString(8, usr.getCittaResidenza());
								preparedStatement5.setString(9, usr.getVia());
								preparedStatement5.setInt(10, usr.getNumeroCivico());
								preparedStatement5.setString(11, usr.getCap());
								preparedStatement5.setString(12, usr.getPassword());
								preparedStatement5.setString(13, usr.getUsername());
									preparedStatement5.execute();
									
		
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
	/**Questo metodo permette di registrare un nuovo utente.
	 * Ha come parametro in input l'utente da inserire nel database*/
	public void registrazioneUtente (Utente usr) throws SQLException {
		
			Connection conn = null;
			PreparedStatement  preparedStatement2 = null;
			
			String insertSQL = "insert into utente (nome,cognome,eMail,codiceFiscale,dataNascita,"
					+ "cittaNascita,cittaResidenza,provincia,via,numeroCivico,cap,username,password)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			try {
				conn = ds.getConnection();

				preparedStatement2 = conn.prepareStatement(insertSQL);
				preparedStatement2.setString(1, usr.getNome());
				preparedStatement2.setString(2, usr.getCognome());
				preparedStatement2.setString(3, usr.geteMail());
				preparedStatement2.setString(4, usr.getCodiceFiscale());
				preparedStatement2.setDate(5, usr.getDataDiNascita());
				preparedStatement2.setString(6, usr.getCittaDiNascita());
				preparedStatement2.setString(7, usr.getCittaResidenza());
				preparedStatement2.setString(8, usr.getProvincia());
				preparedStatement2.setString(9, usr.getVia());
				preparedStatement2.setInt(10, usr.getNumeroCivico());
				preparedStatement2.setString(11, usr.getCap());
				preparedStatement2.setString(12, usr.getUsername());
				preparedStatement2.setString(13, usr.getPassword());
				
				preparedStatement2.executeUpdate();

			} finally {
				try {
					if ( preparedStatement2 != null) {
						preparedStatement2.close();
					}
				} finally {
					if (conn != null)
						conn.close();
				}
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
		String selectSQL = "select username from amministratore where username = ? and password = ?";
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
