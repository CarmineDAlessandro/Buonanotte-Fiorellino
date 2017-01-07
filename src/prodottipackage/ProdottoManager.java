package prodottipackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public class ProdottoManager {

	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/fiorazonis");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
//_________________________________________________________________________________________
	public synchronized ArrayList<Prodotto> returnProdotti() throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null;
		ArrayList<Prodotto> lista = new ArrayList<Prodotto>();
		
		String SQL1 = "select * from prodotto";
		
		try {
			conn = ds.getConnection();
			preparedStatement1 = conn.prepareStatement(SQL1);
			
			ResultSet rs = preparedStatement1.executeQuery();
			while(rs.next()) {
				Prodotto pr = new Prodotto();
				pr.setIdProdotto(rs.getInt("idProdotto"));
				pr.setUrlImmagine(rs.getString("urlImmagine"));
				pr.setNome(rs.getString("nome"));
				pr.setQuantita(rs.getInt("quantita"));
				pr.setDescrizione(rs.getString("descrizione"));
				pr.setPrezzo(rs.getDouble("prezzo"));
				lista.add(pr);
			}

		} finally {
			try {
				if (preparedStatement1 != null && preparedStatement1 != null) {
					preparedStatement1.close();
				}
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		return lista;
	}
//________________________________________________________________________________________________
	public synchronized void aggiungiProdotto(Prodotto usr) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null;
		
		String SQL1 = "insert into prodotto (urlImmagine,nome,quantita,descrizione,prezzo)values (?,?,?,?,?,?)";
		
		//manca metodo per prendere immagine
		String url="./Immagini/";
		try {
			conn = ds.getConnection();
			
			preparedStatement1 = conn.prepareStatement(SQL1);
			preparedStatement1.setString(1, url);
			preparedStatement1.setString(2, usr.getNome());
			preparedStatement1.setInt(3, usr.getQuantita());
			preparedStatement1.setString(4, usr.getDescrizione());
			preparedStatement1.setDouble(5, usr.getPrezzo());
				
			preparedStatement1.executeUpdate();
			

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
//________________________________________________________________________________________________
	
	public void eliminaProdotto (int idprodotto) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null;
		String selectSQL = "DELETE FROM Prodotto WHERE idProdotto = ?";
		try {
			conn = ds.getConnection();
			preparedStatement1 = conn.prepareStatement(selectSQL);
			preparedStatement1.setInt(1, idprodotto);
				preparedStatement1.executeUpdate();		
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
	//_________________________________________________________________________________________________
	
	public void ModificaProdotto(Prodotto usr) throws SQLException {
		Connection conn = null;

		PreparedStatement preparedStatement1 = null;
		
		String SQL1 = " UPDATE prodotto SET urlImmagine = ?, nome = ?, quantita = ?, descrizione = ?,"
				+ " prezzo = ? WHERE idProdotto = ?";
		
		
		try {
			conn = ds.getConnection();
									
									
								preparedStatement1 = conn.prepareStatement(SQL1);
								preparedStatement1.setString(1, usr.getUrlImmagine());
								preparedStatement1.setString(2, usr.getNome());
								preparedStatement1.setInt(3, usr.getQuantita());
								preparedStatement1.setString(4, usr.getDescrizione());
								preparedStatement1.setDouble(5, usr.getPrezzo());
								preparedStatement1.setInt(6, usr.getIdProdotto());
								
									preparedStatement1.executeUpdate();
									
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
//_________________________________________________________________________________________________
	//ricerca prezzo minore uguale
	public ArrayList<Prodotto> ricercaNumeroMin(double prezzo) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null;
		String SQL1 = " select * from prodotto where prezzo <=  ?";
		ArrayList<Prodotto> lista = new ArrayList<Prodotto>();
		try {
			conn = ds.getConnection();
			
								preparedStatement1 = conn.prepareStatement(SQL1);
								preparedStatement1.setDouble(1, prezzo);
								ResultSet rs = preparedStatement1.executeQuery();
								while(rs.next()) {
									Prodotto pr = new Prodotto();
									pr.setIdProdotto(rs.getInt("idProdotto"));
									pr.setUrlImmagine(rs.getString("urlImmagine"));
									pr.setNome(rs.getString("nome"));
									pr.setQuantita(rs.getInt("quantita"));
									pr.setDescrizione(rs.getString("descrizione"));
									pr.setPrezzo(rs.getDouble("prezzo"));
									lista.add(pr);
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
		return lista;
	}
//_________________________________________________________________________________________________
	//ricerca prezzo maggiore uguale
		public ArrayList<Prodotto> ricercaNumeroMax(double prezzo) throws SQLException {
			Connection conn = null;
			PreparedStatement preparedStatement1 = null;
			String SQL1 = " select * from prodotto where prezzo >=  ?";
			ArrayList<Prodotto> lista = new ArrayList<Prodotto>();
			try {
				conn = ds.getConnection();
				
									preparedStatement1 = conn.prepareStatement(SQL1);
									preparedStatement1.setDouble(1, prezzo);
									ResultSet rs = preparedStatement1.executeQuery();
									while(rs.next()) {
										Prodotto pr = new Prodotto();
										pr.setIdProdotto(rs.getInt("idProdotto"));
										pr.setUrlImmagine(rs.getString("urlImmagine"));
										pr.setNome(rs.getString("nome"));
										pr.setQuantita(rs.getInt("quantita"));
										pr.setDescrizione(rs.getString("descrizione"));
										pr.setPrezzo(rs.getDouble("prezzo"));
										lista.add(pr);
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
			return lista;
		}
//_________________________________________________________________________________________________
	
	//ricerca per nome (ritorna una lista)
	
		public ArrayList<Prodotto> ricercaNome(String nome) throws SQLException {
			Connection conn = null;
			PreparedStatement preparedStatement1 = null;
			String SQL1 = " select * from prodotto where nome >=  ?";
			ArrayList<Prodotto> lista = new ArrayList<Prodotto>();
			try {
				conn = ds.getConnection();
				
									preparedStatement1 = conn.prepareStatement(SQL1);
									preparedStatement1.setString(1, nome);
									ResultSet rs = preparedStatement1.executeQuery();
									while(rs.next()) {
										Prodotto pr = new Prodotto();
										pr.setIdProdotto(rs.getInt("idProdotto"));
										pr.setUrlImmagine(rs.getString("urlImmagine"));
										pr.setNome(rs.getString("nome"));
										pr.setQuantita(rs.getInt("quantita"));
										pr.setDescrizione(rs.getString("descrizione"));
										pr.setPrezzo(rs.getDouble("prezzo"));
										lista.add(pr);
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
			return lista;
		}
//_________________________________________________________________________________________________
	
}
