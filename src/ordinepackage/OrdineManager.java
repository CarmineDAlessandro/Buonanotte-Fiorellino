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

/**Questa classe Ë il gestore degli oggetti "Ordini".
 * Si occupa di effettuare tutte le operazioni sul database che 
 * coinvolgono gli ordini*/
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
	// username ritorna ordini associati amministratore
	/**Questo metodo ha come valore di ritorno una lista contenente 
	 * ogni ordine presente nel database*/
	public ArrayList<Ordine> returnOrdiniAmministratore() throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null, preparedStatement2 = null;
		ArrayList<Ordine> listaOrdine = new ArrayList<Ordine>();
		ArrayList<Prodotto> listaProdotto = new ArrayList<Prodotto>();

		String SQL1 = "select * from Ordine";
		String SQL2 = "select idProdottoOrdine,quantit‡ProdottoOrdine,prezzo,nomeProdottiOrdine from prodottiordine where idOrdine = ?";
		

		try {
			conn = ds.getConnection();

			preparedStatement1 = conn.prepareStatement(SQL1);
			ResultSet rs = preparedStatement1.executeQuery();

			while (rs.next()) {
				Ordine ord = new Ordine();
				ord.setId(rs.getInt("id"));
				ord.setUtenteOrdine(rs.getString("utenteOrdine"));
				ord.setPrezzoTotale(rs.getDouble("prezzoTotale"));
				ord.setStato(rs.getString("stato"));
				ord.setIban(rs.getString("iban"));
				listaOrdine.add(ord);
			}
				for (Ordine or : listaOrdine) {
					preparedStatement2 = conn.prepareStatement(SQL2);
					preparedStatement2.setInt(1, or.getId());
					ResultSet rst = preparedStatement2.executeQuery();
					
					while (rst.next()) {
						Prodotto prd = new Prodotto();
						prd.setIdProdotto(rst.getInt("idProdottoOrdine"));
						prd.setQuantita(rst.getInt("quantit‡ProdottoOrdine"));
						prd.setPrezzo(rst.getDouble("prezzo"));
						prd.setNome(rst.getString("nomeProdottiOrdine"));
						
						

						// aggiunge prodotto all' array
						
						listaProdotto.add(prd);
					}
					// aggiunge prodotto all' ordine
					or.setProdotto(listaProdotto);
					listaProdotto = new ArrayList<Prodotto>();
				}

			} finally {
				try {
					if (preparedStatement1 != null && preparedStatement2 != null ) {
						
						preparedStatement2.close();
						preparedStatement1.close();
					}
				} finally {
					if (conn != null)
						conn.close();
				}
			}
			return listaOrdine;
		}
	// __________________________________________________________________________________________________
	// username ritorna ordini associati Utente
	/**Questo metodo ha come parametro un username riferito ad un utente
	 * e ha come valore di ritorno una lista di ordini riferiti all'username
	 * passato come parametro*/
	public ArrayList<Ordine> returnOrdiniUtente(String username) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null, preparedStatement2 = null;
		ArrayList<Ordine> listaOrdine = new ArrayList<Ordine>();
		ArrayList<Prodotto> listaProdotto = new ArrayList<Prodotto>();
	
		String SQL1 = "select * from Ordine where utenteOrdine = ?";
		String SQL2 = "select idProdottoOrdine,quantit‡ProdottoOrdine,prezzo,nomeProdottiOrdine from prodottiordine where idOrdine = ?";
		

		try {
			conn = ds.getConnection();

			preparedStatement1 = conn.prepareStatement(SQL1);
			preparedStatement1.setString(1, username);
			ResultSet rs = preparedStatement1.executeQuery();

			while (rs.next()) {
				Ordine ord = new Ordine();
				ord.setId(rs.getInt("id"));
				ord.setUtenteOrdine(rs.getString("utenteOrdine"));
				ord.setPrezzoTotale(rs.getDouble("prezzoTotale"));
				ord.setStato(rs.getString("stato"));
				ord.setIban(rs.getString("iban"));
				listaOrdine.add(ord);
			}
			
			for (Ordine or : listaOrdine) {
				preparedStatement2 = conn.prepareStatement(SQL2);
				preparedStatement2.setInt(1, or.getId());
				ResultSet rst = preparedStatement2.executeQuery();

				while (rst.next()) {
					Prodotto prd = new Prodotto();
					prd.setIdProdotto(rst.getInt("idProdottoOrdine"));
					prd.setQuantita(rst.getInt("quantit‡ProdottoOrdine"));
					prd.setPrezzo(rst.getDouble("prezzo"));
					prd.setNome(rst.getString("nomeProdottiOrdine"));
					
					

					// aggiunge prodotto all' array
					
					listaProdotto.add(prd);
				}
				// aggiunge prodotto all' ordine
				or.setProdotto(listaProdotto);
				listaProdotto = new ArrayList<Prodotto>();
			}

		} finally {
			try {
				if (preparedStatement1 != null && preparedStatement2 != null ) {
				
					preparedStatement2.close();
					preparedStatement1.close();
				}
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		return listaOrdine;
	}

	// __________________________________________________________________________________________________
	// avanza stato
	/**Questo metodo cambia lo stato di spedizione di un ordine. Ha 
	 * come parametri il nuovo stato e l'id dell'ordine da modificare*/
	public void avanzaStato(String Stato, int idOrdine) throws SQLException {
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
				if (preparedStatement1 != null && preparedStatement1 != null) {

					preparedStatement1.close();
				}
			} finally {
				if (conn != null)
					conn.close();
			}
		}
	}

	// __________________________________________________________________________________________________
	// iserici iban
	/**Questo metodo permette di associare un codice iban all'ordine.
	 * Ha come parametri il nuovo codice iban da associare all'ordine
	 * e l'id dell'oridne da modificare*/
	public void inserisciIban(String iban, int idOrdine) throws SQLException {
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
				if (preparedStatement1 != null && preparedStatement1 != null) {

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
