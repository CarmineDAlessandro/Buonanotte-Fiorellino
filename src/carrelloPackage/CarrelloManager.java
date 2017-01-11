package carrelloPackage;

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

/**Questa classe è il gestore degli oggetti "Carrello".
 * Si occupa di effettuare tutte le operazioni che coinvolgono il carrello
 * sul database*/
public class CarrelloManager {
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

	/**
	 * Questo metodo ritorna un oggetto Carrello nel caso in cui l'utente abbia
	 * associato un carrello ritorna null se non ha associato nessun carrello
	 * 
	 * 
	 * più in dettaglio. Ritorna null se non sono presenti coppie
	 * utente-prodotto nella tabella prodotticarrello Ritorna un oggetto
	 * carrello con i prodotti indicizzati dalla tabella prodotticarrello nel
	 * caso in cui nella tabella prodotticarrello ci siano effettivamente coppie
	 * utente-prodotto
	 */
	public Carrello getCarrello(String username) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null;
		String selectSQL = "select * from prodotto, carrello, prodotticarrello "
				+ "where usernameCarrello = ? "
				+ "&& carrello.numeroCarrello=prodotticarrello.numeroCarrello "
				+ "&& idProdottoCarrello=idProdotto";
		ArrayList<Prodotto> car = new ArrayList<Prodotto>();
		Carrello carrello = new Carrello();
		try {
			conn = ds.getConnection();
			preparedStatement1 = conn.prepareStatement(selectSQL);
			preparedStatement1.setString(1, username);

			ResultSet rs = preparedStatement1.executeQuery();
			int i = 0;
			while (rs.next()) {
				carrello.setId(rs.getInt("numeroCarrello"));
				
				car.add(
						new Prodotto(rs.getInt("idProdotto"), rs.getString("nome"), rs.getString("urlImmagine"),
								rs.getString("descrizione"), rs.getInt("quantitàCarrello"), rs.getDouble("prezzo")));

				i++;
			}
			if (i == 0)
				return null;

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
		
		carrello.setLista(car);
		return carrello;
	}

	/**
	 * Ripulisce prodotticarrello di tutte le coppie che si riferiscono
	 * all'utente.
	 * 
	 * @throws SQLException
	 * 
	 */
	public void eliminaCarrello(String username) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		String query = "delete from prodotticarrello where utenteCarrello = ?";
		try {
			conn = ds.getConnection();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, username);

			preparedStatement.executeQuery();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				if (conn != null)
					conn.close();
			}
		}
	}

	/**Questo metodo aggiunge un prodotto al carrello.
	 * Vuole come parametri l'id del prodotto da aggiungere e l'username
	 * dell'utente relativo al carrello*/
	public void aggiungiProdottoCarrello (String username, int idProdotto) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null,preparedStatement2 = null,preparedStatement3 = null;
		
		String SQL1 ="select * from prodottiCarrello where username = ? and idProdotto = ? ";
		String SQL2 = "update into prodottiCarrello  set quantitaCarrello = ? where username = ? and idProdotto = ?";
		String SQL3 = " insert into prodottiCarrello  (utenteCarrello,numeroProdotto,quantitaCarrello)values(?,?,1)";
		try { 
			conn = ds.getConnection();
			preparedStatement1 = conn.prepareStatement(SQL1);
			preparedStatement1.setString(1, username);
			preparedStatement1.setInt(2, idProdotto);
			ResultSet rs =preparedStatement1.executeQuery();
			
			if(rs.next() ){
//	esegui l'update
				preparedStatement2 = conn.prepareStatement(SQL2);
				preparedStatement2.setInt(1, (rs.getInt("quantitaCarrello")+1));
				preparedStatement2.setString(2, username);
				preparedStatement2.setInt(3, idProdotto);
				preparedStatement2.executeUpdate();
				} else {
				//esegui l'inserimento 	
					preparedStatement3 = conn.prepareStatement(SQL3);
					preparedStatement3.setString(1, username);
					preparedStatement3.setInt(2, idProdotto);
					preparedStatement3.executeUpdate();
				}
			
			
		}finally {
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
	}
	
	/**Questo metodi elimina un prodotto dal carrello.
	 * Ha come parametri l'username dell'utente a cui si riferisce il carrello
	 * e l'id del prodotto da rimuovere*/
	public void eliminaProdottoCarrello (int idCarrello, String idProdotto) throws SQLException  {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null,preparedStatement2 = null;
		int idProdottoInt = Integer.parseInt(idProdotto);
		
		String SQL = "delete from prodotticarrello where numeroCarrello = ? and idProdottoCarrello = ?";
		
		try {
			conn = ds.getConnection();
			preparedStatement1 = conn.prepareStatement(SQL);
			preparedStatement1.setInt(1, idCarrello);
			preparedStatement1.setInt(2, idProdottoInt);
			System.out.println(idCarrello+" "+idProdottoInt);
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
}
