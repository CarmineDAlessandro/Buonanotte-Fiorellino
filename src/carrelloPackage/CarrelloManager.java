package carrelloPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		String selectSQL = "select * from prodotticarrello, prodotto where utenteCarrello = ? "
				+ "prodotticarrello.numeroProdotto = prodotto.idProdotto";
		Carrello car = new Carrello();

		try {
			conn = ds.getConnection();
			preparedStatement1 = conn.prepareStatement(selectSQL);
			preparedStatement1.setString(1, username);

			ResultSet rs = preparedStatement1.executeQuery();
			int i = 0;
			while (rs.next()) {
				car.aggiungiProdotto(
						new Prodotto(rs.getInt("idProdotto"), rs.getString("nome"), rs.getString("urlImmagine"),
								rs.getString("descrizione "), rs.getInt("quantitaCarrello"), rs.getDouble("prezzo")));

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
		return car;
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
	public void eliminaProdottoCarrello (String username, int idProdotto) throws SQLException  {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null,preparedStatement2 = null;
		
		String SQL1 ="select * from prodottiCarrello where username = ? and idProdotto = ? ";
		String SQL2 = "update into prodottiCarrello  set quantitaCarrello = ? where username = ? and idProdotto = ?";
		String SQL3 = "delete from prodotticarrello where utenteCarrello = ? and idProdotto = ?";
		
		try {
			conn = ds.getConnection();
			preparedStatement1 = conn.prepareStatement(SQL1);
			preparedStatement1.setString(1, username);
			preparedStatement1.setInt(2, idProdotto);
			ResultSet rs = preparedStatement1.executeQuery();
			while(rs.next()) {
				if(1 == (rs.getInt("quantitaCarrello"))){
					preparedStatement2 = conn.prepareStatement(SQL3);
					preparedStatement2.setString(1, username);
					preparedStatement2.setInt(2, idProdotto);
					preparedStatement2.executeUpdate();
				}else{
					preparedStatement2 = conn.prepareStatement(SQL2);
					preparedStatement2.setInt(1,(rs.getInt("quantitaCarrello")-1) );
					preparedStatement2.setString(2, username);
					preparedStatement2.setInt(3, idProdotto);
					preparedStatement2.executeUpdate();
				}
			}
		} finally {
			try {
				if (preparedStatement1 != null &&preparedStatement2 != null) {
					preparedStatement2.close();
					preparedStatement1.close();
				}
			} finally {
				if (conn != null)
					conn.close();
			}
		}
	}
}
