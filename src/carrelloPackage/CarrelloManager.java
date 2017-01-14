package carrelloPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import prodottipackage.Prodotto;

/**Questa classe Ë il gestore degli oggetti "Carrello".
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
	
	private static Connection getConnection () throws SQLException {
		if (ds != null)
			return ds.getConnection();
		else if (ds == null) {
			String URL ="jdbc:mysql://localhost:3306";
			String database ="fiorazon";
				String driver = "com.mysql.jdbc.Driver";
			String user ="root";
			String password = "root";
			
			
			try {
				Class.forName(driver);
				return  DriverManager.getConnection(URL + "/"+database,user,password);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		}
		return null;
		
	}

	// __________________________________________________________________________________________________

	/**
	 * Questo metodo ritorna un oggetto Carrello nel caso in cui l'utente abbia
	 * associato un carrello ritorna null se non ha associato nessun carrello
	 * 
	 * 
	 * pi˘ in dettaglio. Ritorna null se non sono presenti coppie
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
			conn = getConnection();
			preparedStatement1 = conn.prepareStatement(selectSQL);
			preparedStatement1.setString(1, username);

			ResultSet rs = preparedStatement1.executeQuery();
			int i = 0;
			while (rs.next()) {
				carrello.setId(rs.getInt("numeroCarrello"));
				
				car.add(
						new Prodotto(rs.getInt("idProdotto"), rs.getString("nome"), rs.getString("urlImmagine"),
								rs.getString("descrizione"), rs.getInt("quantit‡Carrello"), rs.getDouble("prezzo")));

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


	
	/**Questo metodo aggiunge un prodotto al carrello.
	 * Vuole come parametri l'id del prodotto da aggiungere e l'username
	 * dell'utente relativo al carrello*/
	public boolean aggiungiProdottoCarrello (String username, int idProdotto, int quantit‡) throws SQLException {
		int numeroCarrello = 0;
		boolean flag = true;
		Connection conn = null;
		PreparedStatement preparedStatement1 = null,preparedStatement2 = null,
				preparedStatement3 = null, preparedStatement4 = null, ps5=null ;
		String SQL1 = "select * from carrello where usernameCarrello = ?";
		String SQL2 = "insert into carrello(usernameCarrello) values(?)";
		String SQL3 = "select * from prodotticarrello where numeroCarrello = ? "
				+ "and idProdottoCarrello = ?";
		String SQL4 = "insert into prodotticarrello(numeroCarrello,idProdottoCarrello,"
				+ "quantit‡Carrello) values(?,?,?)";
		try { 
			conn = getConnection();
			preparedStatement1 = conn.prepareStatement(SQL1);
			preparedStatement1.setString(1, username);
			ResultSet rs1 =preparedStatement1.executeQuery();
			if(rs1.next()) {
				numeroCarrello = rs1.getInt("numeroCarrello");
			}
			else { //se non c'Ënel db, lo aggiungo
				preparedStatement2 = conn.prepareStatement(SQL2);
				preparedStatement2.setString(1, username);
				preparedStatement2.executeUpdate();
				preparedStatement3 = conn.prepareStatement(SQL1);
				preparedStatement3.setString(1, username);
				ResultSet rs2 = preparedStatement3.executeQuery();
				if(rs2.next()) {
					numeroCarrello = rs2.getInt("numeroCarrello");
					}
			
			}
			preparedStatement4 = conn.prepareStatement(SQL3);
			preparedStatement4.setInt(1, numeroCarrello);
			preparedStatement4.setInt(2, idProdotto);
			ResultSet rs3 = preparedStatement4.executeQuery();
			if(rs3.next()) { 
				
				String idProdottoS = ""+idProdotto;
				flag = cambiaQuantit‡Carrello(numeroCarrello, quantit‡, idProdottoS);
				
				if(!flag) return false; // se la quantit‡ Ë eccessiva, non fa l'operazione
				
			}
			else {
				ps5 = conn.prepareStatement(SQL4);
				ps5.setInt(1, numeroCarrello);
				ps5.setInt(2, idProdotto);
				ps5.setInt(3, quantit‡);
				ps5.executeUpdate();
			}
			
			
		}finally {
			try {
				if (preparedStatement1 != null  && preparedStatement4 != null) {
					preparedStatement4.close();
					
					preparedStatement1.close();
				}
				if (preparedStatement2 != null && preparedStatement3 != null) {
					preparedStatement3.close();
					preparedStatement2.close();
				}
				if(ps5 != null) {
					ps5.close();
				}
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		
		return flag;
	}
	
	/**Questo metodo elimina un prodotto dal carrello.
	 * Ha come parametri l'username dell'utente a cui si riferisce il carrello
	 * e l'id del prodotto da rimuovere*/
	public boolean eliminaProdottoCarrello (int idCarrello, String idProdotto) throws SQLException  {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null,preparedStatement2 = null;
		int idProdottoInt = Integer.parseInt(idProdotto);
		String prova = "select * from prodotticarrello where numeroCarrello = ? and idProdottoCarrello = ?";
		String SQL = "delete from prodotticarrello where numeroCarrello = ? and idProdottoCarrello = ?";
		
		try {
			conn = getConnection();
			preparedStatement2 = conn.prepareStatement(prova);
			preparedStatement2.setInt(1, idCarrello);
			preparedStatement2.setInt(2, idProdottoInt);
			ResultSet rs = preparedStatement2.executeQuery();
			if(!rs.next()) return false;
			preparedStatement1 = conn.prepareStatement(SQL);
			preparedStatement1.setInt(1, idCarrello);
			preparedStatement1.setInt(2, idProdottoInt);
			preparedStatement1.executeUpdate();
		
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
		return true;
	}
	/**
	 * Questo metodo cambia la quantit‡ di un prodotto dal carrello.
	 * @param idCarrello Ë l'id del carrello
	 * @param quantit‡Change Ë la variazione di quantit‡ del prodotto
	 * @param idProdotto l'id del prodotto
	 * @return restituisce l'esito dell'operazione
	 * @throws SQLException
	 */
	public boolean cambiaQuantit‡Carrello(int idCarrello, int quantit‡Change, String idProdotto) throws SQLException {
		boolean flag;
		Connection conn = null;
		PreparedStatement ps1 = null,ps2 = null;
		PreparedStatement ps3 = null;
		int idProdottoInt = Integer.parseInt(idProdotto);
		
		String SQL1=("select quantita from prodotto where idProdotto = ?");
		String SQL2=("select quantit‡Carrello from prodottiCarrello "
				+ "where idProdottoCarrello = ? and numeroCarrello = ?");
		String SQL3=("update prodottiCarrello  set quantit‡Carrello = ? "
				+ "where numeroCarrello = ? and idProdottoCarrello = ?");
		try {
			conn = getConnection();
			ps1 = conn.prepareStatement(SQL1);
			ps1.setInt(1, idProdottoInt);
			int quantit‡Disponibile=0;
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next()) {
			
			quantit‡Disponibile = rs1.getInt("quantita");
			}
			ps2 = conn.prepareStatement(SQL2);
			ps2.setInt(1, idProdottoInt);
			ps2.setInt(2, idCarrello);
			ResultSet rs2 = ps2.executeQuery();
			int quantit‡NelCarrello = 0;
			if(rs2.next()) {
			quantit‡NelCarrello = rs2.getInt("quantit‡Carrello");
			}
			if(quantit‡Disponibile < (quantit‡NelCarrello + quantit‡Change)) {
				
				flag = false;
			}
			else if((quantit‡NelCarrello + quantit‡Change) <= 0) {
				String SQL4 = "delete from prodotticarrello "
						+ "where numeroCarrello = ? and idProdottoCarrello = ?";
				ps3 = conn.prepareStatement(SQL4);
				ps3.setInt(1, idCarrello);
				ps3.setInt(2, idProdottoInt);
				ps3.executeUpdate();
				flag = true;
			}
			else {
				ps3 = conn.prepareStatement(SQL3);
				ps3.setInt(1, (quantit‡NelCarrello + quantit‡Change));
				ps3.setInt(2, idCarrello);
				ps3.setInt(3, idProdottoInt);
				ps3.executeUpdate();
				flag = true;
			}
		
		} finally {
			try {
				if (ps1 != null && ps2 != null && ps3 != null) {
					ps3.close();
					ps2.close();
					ps1.close();
				}
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		return flag;
	}
}
