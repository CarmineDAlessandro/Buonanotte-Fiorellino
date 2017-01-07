package ordinepackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import prodottipackage.Prodotto;

public class OrdineManager {
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
			//username ritorna ordini associati amministratore
	public synchronized ArrayList<Ordine> returnOrdini() throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null,preparedStatement2 = null,preparedStatement3 = null;
		ArrayList<Ordine> lista = new ArrayList<Ordine>();
		ArrayList<Prodotto> listaProdotto = new ArrayList<Prodotto>();
		
		String SQL1 = "select * from Ordine";
		String SQL2 = "select idProdottoLista,numeroProdotto from prodottiOrdine where idOrdine = ?";
		String SQL3 = "select * from prodotto where idProdotto = ?";
		
		try {
			conn = ds.getConnection();
			
			preparedStatement1 = conn.prepareStatement(SQL1);
			ResultSet rs = preparedStatement1.executeQuery();
					
			while(rs.next()) {
				Ordine ord = new Ordine();
				ord.setId(rs.getInt("id"));
				ord.setUtenteOrdine(rs.getString("utenteOrdine"));
				ord.setPrezzoTotale(rs.getInt("prezzoTotale"));
				ord.setStato(rs.getString("stato"));
					
				lista.add(ord);
			}
			
			for(Ordine or : lista){
				preparedStatement2 = conn.prepareStatement(SQL2);
				preparedStatement2.setInt(1, or.getId());
				ResultSet rst = preparedStatement2.executeQuery();
						
				while(rst.next()) {
					Prodotto prd = new Prodotto();
						prd.setIdProdotto(rst.getInt("idProdottoLista"));
						prd.setQuantita(rst.getInt("numeroProdotto"));
						//query per informazioni affine prodotto
						preparedStatement3 = conn.prepareStatement(SQL3);
						preparedStatement3.setInt(1, prd.getIdProdotto());
						ResultSet rsx = preparedStatement3.executeQuery();
						while(rsx.next()) {
							prd.setUrlImmagine(rsx.getString("urlImmagine"));
							prd.setNome(rsx.getString("nome"));
							prd.setDescrizione(rsx.getString("descrizione"));
							prd.setPrezzo(rsx.getInt("prezzo"));
						}
							
				//aggiunge prodotto all array
				listaProdotto.add(prd);
				}
				//aggiunge prodotto all ordine
				or.setProdotto(listaProdotto);
			}
			
		} finally {
			try {
				if (preparedStatement1 != null && preparedStatement2 != null && preparedStatement3 != null) {
					preparedStatement3.close();
					preparedStatement2.close();
					preparedStatement1.close();
				}
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		return lista;
	}
	// __________________________________________________________________________________________________
			//username ritorna ordini associati Utente
	public synchronized ArrayList<Ordine> returnOrdiniUtente(String username) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null,preparedStatement2 = null,preparedStatement3 = null;
		ArrayList<Ordine> lista = new ArrayList<Ordine>();
		ArrayList<Prodotto> listaProdotto = new ArrayList<Prodotto>();
		
		String SQL1 = "select * from Ordine where utenteOrdine = ?";
		String SQL2 = "select idProdottoLista,numeroProdotto from prodottiOrdine where idOrdine = ?";
		String SQL3 = "select * from prodotto where idProdotto = ?";
		
		try {
			conn = ds.getConnection();
			
			preparedStatement1 = conn.prepareStatement(SQL1);
			preparedStatement1.setString(1, username);
			ResultSet rs = preparedStatement1.executeQuery();
					
			while(rs.next()) {
				Ordine ord = new Ordine();
				ord.setId(rs.getInt("id"));
				ord.setUtenteOrdine(rs.getString("utenteOrdine"));
				ord.setPrezzoTotale(rs.getInt("prezzoTotale"));
				ord.setStato(rs.getString("stato"));
					
				lista.add(ord);
			}
			
			for(Ordine or : lista){
				preparedStatement2 = conn.prepareStatement(SQL2);
				preparedStatement2.setInt(1, or.getId());
				ResultSet rst = preparedStatement2.executeQuery();
						
				while(rst.next()) {
					Prodotto prd = new Prodotto();
						prd.setIdProdotto(rst.getInt("idProdottoLista"));
						prd.setQuantita(rst.getInt("numeroProdotto"));
						//query per informazioni affine prodotto
						preparedStatement3 = conn.prepareStatement(SQL3);
						preparedStatement3.setInt(1, prd.getIdProdotto());
						ResultSet rsx = preparedStatement3.executeQuery();
						while(rsx.next()) {
							prd.setUrlImmagine(rsx.getString("urlImmagine"));
							prd.setNome(rsx.getString("nome"));
							prd.setDescrizione(rsx.getString("descrizione"));
							prd.setPrezzo(rsx.getInt("prezzo"));
						}
							
				//aggiunge prodotto all array
				listaProdotto.add(prd);
				}
				//aggiunge prodotto all ordine
				or.setProdotto(listaProdotto);
			}
			
		} finally {
			try {
				if (preparedStatement1 != null && preparedStatement2 != null && preparedStatement3 != null) {
					preparedStatement3.close();
					preparedStatement2.close();
					preparedStatement1.close();
				}
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		return lista;
	}
	
	
	// __________________________________________________________________________________________________
			//avanza stato
	public  void avanzaStato(String Stato,int idOrdine) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null;
		String SQL1 = "update ordine set stato = ? where id = ? ";
		try {
			conn = ds.getConnection();
			
			preparedStatement1 = conn.prepareStatement(SQL1);
			preparedStatement1.setString(1, Stato);
			preparedStatement1.setInt(2, idOrdine);
			 preparedStatement1.executeUpdate();
	} finally {
		try {
			if (preparedStatement1 != null && preparedStatement1!= null) {
				
				preparedStatement1.close();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
	}
}
	// __________________________________________________________________________________________________
			//iserici iban
	public  void inserisciIban(String iban,int idOrdine) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null;
		String SQL1 = "update ordine set iban = ? where id = ? ";
		try {
			conn = ds.getConnection();
			
			preparedStatement1 = conn.prepareStatement(SQL1);
			preparedStatement1.setString(1, iban);
			preparedStatement1.setInt(2, idOrdine);
			 preparedStatement1.executeUpdate();
	} finally {
		try {
			if (preparedStatement1 != null && preparedStatement1!= null) {
				
				preparedStatement1.close();
			}
		} finally {
			if (conn != null)
				conn.close();
		}
	}
}
	// __________________________________________________________________________________________________
}
