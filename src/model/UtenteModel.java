package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.Utente;

public class UtenteModel {
	
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
//__________________________________________________________________________________________________
	public synchronized Utente returnInfo(String username) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		Utente usr = new Utente();
		
		String selectSQL = "select * from utente where username = ?";
		
		try {
			conn = ds.getConnection();
			preparedStatement = conn.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				usr.setRuolo(rs.getString("ruolo"));
				usr.setNome(rs.getString("nome"));
				usr.setCognome(rs.getString("cognome"));
				usr.seteMail(rs.getString("eMail"));
				usr.setCodiceFiscale(rs.getString("codiceFiscale"));
				usr.setDataNascita(rs.getString("dataNascita"));
				usr.setCittaNascita(rs.getString("cittaNascita"));
				usr.setCittaResidenza(rs.getString("cittaResidenza"));
				usr.setVia(rs.getString("via"));
				usr.setNumeroCivico(rs.getInt("numeroCivico"));
				usr.setCap(rs.getInt("cap"));
				usr.setUsername(rs.getString("username"));
				usr.setPassword(rs.getString("password"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		return usr;
	}
	//__________________________________________________________________________________________
	public synchronized boolean controllUser(String username) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null;
		String selectSQL = "select username from utente where username = ?";
		
		try {
			conn = ds.getConnection();
			preparedStatement1 = conn.prepareStatement(selectSQL);
			preparedStatement1.setString(1, username);
			
			ResultSet rs = preparedStatement1.executeQuery();
			if(rs.next() == true) {
				return true;
			}else{
				return false;
			}
			
			

		} finally {
			try {
				if (preparedStatement1 != null ) {
					preparedStatement1.close();
				}
			} finally {
				if (conn != null)
					conn.close();
			}
		} 
	}
//______________________________________________________________________________________
	public synchronized void ModificaUtente(Utente usr,String username) throws SQLException {
		Connection conn = null;

		PreparedStatement preparedStatement5 = null;
		
		String SQL5 = " UPDATE utente SET nome = ?, cognome = ?, eMail = ?, codiceFiscale = ?,"
				+ " dataNascita = ?, cittaNascita = ?, cittaResidenza = ?, via = ?, numeroCivico = ?,"
				+ " cap = ?, password = ? WHERE username = ?";
		
		
		try {
			conn = ds.getConnection();
									
									
								preparedStatement5 = conn.prepareStatement(SQL5);
								preparedStatement5.setString(1, usr.getNome());
								preparedStatement5.setString(2, usr.getCognome());
								preparedStatement5.setString(3, usr.geteMail());
								preparedStatement5.setString(4, usr.getCodiceFiscale());
								preparedStatement5.setString(5, usr.getDataNascita());
								preparedStatement5.setString(6, usr.getCittaNascita());
								preparedStatement5.setString(7, usr.getCittaResidenza());
								preparedStatement5.setString(8, usr.getVia());
								preparedStatement5.setInt(9, usr.getNumeroCivico());
								preparedStatement5.setInt(10, usr.getCap());
								preparedStatement5.setString(11, usr.getPassword());
								preparedStatement5.setString(12, username);
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
//_________________________________________________________________________________________________
	public synchronized boolean eliminaUtente(String username) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;
		PreparedStatement preparedStatement4 = null;
		PreparedStatement preparedStatement5 = null;
		PreparedStatement preparedStatement6 = null;
		PreparedStatement preparedStatement7 = null;
		String idOrdine = null ,idCarrello = null;
		boolean eliminazione= false;
		
		String SQL1 = " select id from ordine where utenteOrdine = ? ";
		String SQL2 = " DELETE from prodottiOrdine where idOrdine = ? ";
		String SQL3 = " DELETE FROM ordine WHERE utenteOrdine = ? and id = ?";
		
		String SQL4 = " select idCarrello from carrello where utenteCarrello = ? ";
		String SQL5 = " DELETE FROM  prodottiCarrello where numeroCarrello = ? ";
		String SQL6 = " DELETE from carrello where idCarrello = ? ";
		
		String SQL7 = " DELETE FROM utente WHERE username = ? and ruolo = ?";
		
		
	
		
		try {
			conn = ds.getConnection();
			
			preparedStatement1 = conn.prepareStatement(SQL1);
			preparedStatement1.setString(1, username);
				preparedStatement1.execute();
				ResultSet rs = preparedStatement1.executeQuery();
				while(rs.next()) {
					idOrdine = rs.getString("id");
						preparedStatement2 = conn.prepareStatement(SQL2);
						preparedStatement2.setString(1, idOrdine);
							preparedStatement2.execute();
							
								preparedStatement3 = conn.prepareStatement(SQL3);
								preparedStatement3.setString(1, username);
								preparedStatement3.setString(2, idOrdine);
									preparedStatement3.execute();
									
				}
				
				
			preparedStatement4 = conn.prepareStatement(SQL4);
			preparedStatement4.setString(1, username);
				preparedStatement4.execute(); 
				ResultSet rsl = preparedStatement4.executeQuery();
				//if
				while(rsl.next()) {
					idCarrello = rsl.getString("idCarrello");
						preparedStatement5 = conn.prepareStatement(SQL5);
						preparedStatement5.setString(1, idCarrello);
							preparedStatement5.execute();
							
				}

			preparedStatement6 = conn.prepareStatement(SQL6);
			preparedStatement6.setString(1, idCarrello);
				preparedStatement6.execute();
				
				
				
			preparedStatement7 = conn.prepareStatement(SQL7);
			preparedStatement7.setString(1, username);
			preparedStatement7.setString(2, "utente");
				preparedStatement7.execute();
				
				
				
				
				eliminazione= true;
				
		}catch (SQLException e){
			eliminazione = false;
			System.out.println("Info sulla SQLException:\n");
			while (e != null) {
			System.out.println("Message:" + e.getMessage ());
			System.out.println("SQLState:" + e.getSQLState ());
			System.out.println("ErrorCode:" + e.getErrorCode ());
			e = e.getNextException();
				}
			} finally {
			try {
				if (preparedStatement1 != null ) {
					preparedStatement1.close();
				}else{
					if (preparedStatement2 != null ) {
						preparedStatement2.close();
					}else{
						if (preparedStatement3 != null ) {
							preparedStatement3.close();
						}else{
							if (preparedStatement4 != null ) {
								preparedStatement4.close();
							}else{
								if (preparedStatement5 != null ) {
									preparedStatement5.close();
								}else{
									if (preparedStatement6 != null ) {
										preparedStatement6.close();
									}else{
										if (preparedStatement7 != null ) {
											preparedStatement7.close();
										}
									}
								}
							}
						}
					}
				}
			} finally {
				if (conn != null)
					conn.close();
				
			}
		} 
		
		return eliminazione;
	}
//__________________________________________________________________________________________________
	public synchronized void ModificaAccount(Utente usr,String username) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement0 = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;
		PreparedStatement preparedStatement4 = null;
		PreparedStatement preparedStatement5 = null;
		PreparedStatement preparedStatement6 = null;
		PreparedStatement preparedStatement7 = null;
		String idOrdine= null,idCarrello = null,amministratore = null;
		ArrayList <Integer> listaOrdini = new ArrayList<Integer>();
		ArrayList <Integer> listacarrello = new ArrayList<Integer>();
		
		
		
		String SQL0 ="select username from utente where ruolo = ?";
		String SQL1 ="select id from ordine where utenteOrdine = ?";
		String SQL2 = "UPDATE ordine SET utenteOrdine = ?   WHERE id = ?";
		
		String SQL3 ="select idCarrello from carrello where utenteCarrello = ?";
		String SQL4 = "UPDATE carrello SET utenteCarrello = ?   WHERE idCarrello = ? ";
		
		String SQL5 = " UPDATE utente SET nome = ?, cognome = ?, eMail = ?, codiceFiscale = ?,"
				+ " dataNascita = ?, cittaNascita = ?, cittaResidenza = ?, via = ?, numeroCivico = ?,"
				+ " cap = ?, username = ?, password = ? WHERE username = ?";
		
		String SQL6 ="UPDATE ordine SET utenteOrdine = ?  where utenteOrdine = ? and id = ?";
		String SQL7 ="UPDATE carrello SET utenteCarrello = ?  where  idCarrello = ?";
		
		try {
			conn = ds.getConnection();
			
			//prende username amministratore
			preparedStatement0 = conn.prepareStatement(SQL0);
			preparedStatement0.setString(1, "amministratore");
				preparedStatement0.execute();
				ResultSet rsa = preparedStatement0.executeQuery();
				if(rsa.next()) {
					amministratore = rsa.getString("username");
					
				}
				
				//ordini su amministratore e tiene traccia di essi
			preparedStatement1 = conn.prepareStatement(SQL1);
			preparedStatement1.setString(1, username);
				preparedStatement1.execute();
				ResultSet rs = preparedStatement1.executeQuery();
				while(rs.next()) {
					idOrdine = rs.getString("id");
					listaOrdini.add(Integer.parseInt(idOrdine));
								preparedStatement2 = conn.prepareStatement(SQL2);
								preparedStatement2.setString(1, amministratore);
								preparedStatement2.setString(2, idOrdine);
									preparedStatement2.execute();
									
								}
				
				//ordini su amministratore e tiene traccia di essi
				preparedStatement3 = conn.prepareStatement(SQL3);
				preparedStatement3.setString(1, username);
					preparedStatement3.execute();
					ResultSet rsl = preparedStatement3.executeQuery();
					while(rsl.next()) {
						idCarrello = rsl.getString("idCarrello");
						listacarrello.add(Integer.parseInt(idCarrello));
								preparedStatement4 = conn.prepareStatement(SQL4);
								preparedStatement4.setString(1, amministratore);
								preparedStatement4.setString(2, idCarrello);
									preparedStatement4.execute();
									
					}
							//modifica di utente
								preparedStatement5 = conn.prepareStatement(SQL5);
								preparedStatement5.setString(1, usr.getNome());
								preparedStatement5.setString(2, usr.getCognome());
								preparedStatement5.setString(3, usr.geteMail());
								preparedStatement5.setString(4, usr.getCodiceFiscale());
								preparedStatement5.setString(5, usr.getDataNascita());
								preparedStatement5.setString(6, usr.getCittaNascita());
								preparedStatement5.setString(7, usr.getCittaResidenza());
								preparedStatement5.setString(8, usr.getVia());
								preparedStatement5.setInt(9, usr.getNumeroCivico());
								preparedStatement5.setInt(10, usr.getCap());
								preparedStatement5.setString(11, usr.getUsername());
								preparedStatement5.setString(12, usr.getPassword());
								preparedStatement5.setString(13, username);
									preparedStatement5.execute();
									
									
									//ordini su utente
									for(int numero1 : listaOrdini)
									{
										preparedStatement6 = conn.prepareStatement(SQL6);
										preparedStatement6.setString(1, usr.getUsername());
										preparedStatement6.setString(2, amministratore);
										preparedStatement6.setInt(3, numero1);
											preparedStatement6.execute();
											
									}
									
									
									//carrello  su utente
									//""UPDATE carrello SET utenteCarrello = ?  where  idCarrello = ?";
									for(int numero2 : listacarrello)
									{
										preparedStatement7 = conn.prepareStatement(SQL7);
										preparedStatement7.setString(1, usr.getUsername());
										preparedStatement7.setInt(2, numero2);
											preparedStatement7.execute();
											
									}		
									
		}catch (SQLException e){
			System.out.println("Info sulla SQLException:\n");
			while (e != null) {
			System.out.println("Message:" + e.getMessage ());
			System.out.println("SQLState:" + e.getSQLState ());
			System.out.println("ErrorCode:" + e.getErrorCode ());
			e = e.getNextException();
				}
			} finally {
			try {
				if (preparedStatement0 != null ) {
					preparedStatement0.close();
				}else{
				if (preparedStatement1 != null ) {
					preparedStatement1.close();
				}else{
					if (preparedStatement2 != null ) {
						preparedStatement2.close();
					}else{
						if (preparedStatement3 != null ) {
							preparedStatement3.close();
						}else{
							if (preparedStatement4 != null ) {
								preparedStatement4.close();
							}else{
								if (preparedStatement5 != null ) {
									preparedStatement5.close();
									}else{
										if (preparedStatement6 != null ) {
											preparedStatement6.close();
											}else{
												if (preparedStatement7 != null ) {
													preparedStatement7.close();
													}
											}
									}
								}
							}
						}
					}
				}
			} finally {
				if (conn != null)
					conn.close();
				
			}
		} 
	}
	//_________________________________________________________________________________________________
	public synchronized String verifyAccess(String username, String password) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		
		//variabili
		String ruolo = null;
		
		String selectSQL = "select ruolo from utente where username = ? and password = ? ";
		
		try {
			conn = ds.getConnection();
			preparedStatement = conn.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				ruolo = rs.getString("ruolo");
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		return ruolo;
	}
	//_________________________________________________________________________________________________
	public synchronized void InsertUser(Utente usr) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null, preparedStatement2 = null;
		
		String insertSQL = "insert into utente (ruolo,nome,cognome,eMail,codiceFiscale,dataNascita,"
				+ "cittaNascita,cittaResidenza,via,numeroCivico,cap,username,password)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String selectSQL = "select username from utente where username = ?";
		
		try {
			conn = ds.getConnection();
			preparedStatement1 = conn.prepareStatement(selectSQL);
			preparedStatement1.setString(1, usr.getUsername());
			
			ResultSet rs = preparedStatement1.executeQuery();
			if(rs.next() == true) {
				throw new SQLException();
				
			}

			preparedStatement2 = conn.prepareStatement(insertSQL);
			preparedStatement2.setString(1, usr.getRuolo());
			preparedStatement2.setString(2, usr.getNome());
			preparedStatement2.setString(3, usr.getCognome());
			preparedStatement2.setString(4, usr.geteMail());
			preparedStatement2.setString(5, usr.getCodiceFiscale());
			preparedStatement2.setString(6, usr.getDataNascita());
			preparedStatement2.setString(7, usr.getCittaNascita());
			preparedStatement2.setString(8, usr.getCittaResidenza());
			preparedStatement2.setString(9, usr.getVia());
			preparedStatement2.setInt(10, usr.getNumeroCivico());
			preparedStatement2.setInt(11, usr.getCap());
			preparedStatement2.setString(12, usr.getUsername());
			preparedStatement2.setString(13, usr.getPassword());
			
			preparedStatement2.executeUpdate();

		} finally {
			try {
				if (preparedStatement1 != null && preparedStatement2 != null) {
					preparedStatement2.close();
					preparedStatement1.close();
				}
			} finally {
				if (conn != null)
					conn.close();
			}
		} 
	}
	//__________________________________________________________________________________________
}
