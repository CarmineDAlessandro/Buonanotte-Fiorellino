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

import bean.Prodotto;

public class ProdottiModel {
	
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
	
	//_________________________________________________________________________________________________
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
	
	public synchronized void InsertProdotto(Prodotto usr) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null;
		String SQL1 = "insert into prodotto (urlImmagine,nome,quantita,descrizione,prezzo)values (?,?,?,?,?)";
		try {
			conn = ds.getConnection();
			
			preparedStatement1 = conn.prepareStatement(SQL1);
				
			preparedStatement1.setString(1, usr.getUrlImmagine());
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
				//if (conn != null)
					conn.close();
			}
		} 
	}
	//________________________________________________________________________________________________
	
	public synchronized boolean eliminaProdotto (String idprodotto) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null;
		String selectSQL = "DELETE FROM Prodotto WHERE idProdotto = ?";
		boolean eliminazione = false;
		try {
			conn = ds.getConnection();
			preparedStatement1 = conn.prepareStatement(selectSQL);
			preparedStatement1.setString(1, idprodotto);
				preparedStatement1.executeUpdate();
				eliminazione =true;
		}catch (SQLException e){
				eliminazione = false;
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
		return eliminazione; 
	}
	//_________________________________________________________________________________________________
			//modifica immagine
	public synchronized void modificaImmagine (int idprodotto,String Url) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null;
		String selectSQL = "UPDATE prodotto SET urlImmagine = ? WHERE idProdotto = ?";
		
		try {
			conn = ds.getConnection();
			preparedStatement1 = conn.prepareStatement(selectSQL);
			preparedStatement1.setString(1, Url);
			preparedStatement1.setInt(2, idprodotto);
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
	//modifica immagine
public synchronized void modificaProdotto (int idprodotto,Prodotto prd) throws SQLException {
	Connection conn = null;
	PreparedStatement preparedStatement1 = null;
	String selectSQL = "UPDATE prodotto SET nome = ?,quantita = ?,descrizione = ?,prezzo = ? WHERE idProdotto = ?";
	
	try {
		conn = ds.getConnection();
		preparedStatement1 = conn.prepareStatement(selectSQL);
		preparedStatement1.setString(1, prd.getNome());
		preparedStatement1.setInt(2, prd.getQuantita());
		preparedStatement1.setString(3, prd.getDescrizione());
		preparedStatement1.setDouble(4, prd.getPrezzo());
		preparedStatement1.setInt(5, idprodotto);
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
//modifica immagine
	public synchronized Prodotto returnInfo (int idprodotto) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null;
		String selectSQL = "select * from prodotto where idProdotto = ?";
		Prodotto prd = new Prodotto();
		try {
			conn = ds.getConnection();
			preparedStatement1 = conn.prepareStatement(selectSQL);
			preparedStatement1.setInt(1, idprodotto);
		ResultSet rs = preparedStatement1.executeQuery();
			if(rs.next()) {	
				
				prd.setIdProdotto(rs.getInt("idProdotto"));
				prd.setNome(rs.getString("nome"));
				prd.setPrezzo(rs.getDouble("prezzo"));
				prd.setQuantita(rs.getInt("quantita"));
				prd.setDescrizione(rs.getString("descrizione"));
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
		return prd;
	}
	//_________________________________________________________________________________________________
}
