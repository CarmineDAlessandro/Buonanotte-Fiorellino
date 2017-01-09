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

/**
 * Questa classe è la classe gestore degli oggetti di tipo "Prodotto". Si occupa
 * di effettuare tutte le operazioni sul database che coinvolgono i prodotti
 */
public class ProdottiManager {

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

	// _________________________________________________________________________________________
	/**
	 * Questo metodi ritorna una lista di tutti i prodotti presenti nel database
	 */
	public ArrayList<Prodotto> returnProdotti() throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null;
		ArrayList<Prodotto> lista = new ArrayList<Prodotto>();

		String SQL1 = "select * from prodotto";

		try {
			conn = ds.getConnection();
			preparedStatement1 = conn.prepareStatement(SQL1);

			ResultSet rs = preparedStatement1.executeQuery();
			while (rs.next()) {
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

	// ________________________________________________________________________________________________
	/**
	 * Questo metodo permette di aggiungere un nuovo prodotto nel database. Ha
	 * come parametro il prodotto da aggiungere nel database
	 */
	public void aggiungiProdotto(Prodotto usr) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null;

		String SQL1 = "insert into prodotto (urlImmagine,nome,quantita,descrizione,prezzo)values (?,?,?,?,?,?)";

		// manca metodo per prendere immagine
		String url = "./Immagini/";
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
				if (preparedStatement1 != null) {
					preparedStatement1.close();
				}
			} finally {
				if (conn != null)
					conn.close();
			}
		}
	}

	// ________________________________________________________________________________________________
	/**
	 * Questo metodo permette di rimuovere un prodotto dal database. Ha come
	 * parametro l'id del prodotto da rimuovere
	 */
	public void eliminaProdotto(int idprodotto) throws SQLException {
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
				if (preparedStatement1 != null) {
					preparedStatement1.close();
				}
			} finally {
				if (conn != null)
					conn.close();
			}
		}

	}

	// _________________________________________________________________________________________________
	/**
	 * Questo metodo permette di modificare un prodotto nel database.
	 */
	public boolean modificaProdotto(int idProdotto, String dato, String action) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		String SQL = null;
		boolean flag = true;
		
		if (action.equals("nome")) {
			String nome = dato;
		
			if (nome.length() > 30)
				flag = false;
			for (int i = 0; i < nome.length(); i++) {
				if (!(Character.isWhitespace(nome.charAt(i))
						||Character.isLetter(nome.charAt(i)))) {
					flag = false;
				}
			}
			
			if (flag == true) {
				try {
					conn = ds.getConnection();

					SQL = "UPDATE prodotto SET nome = ? WHERE idProdotto = ?";
					
					preparedStatement = conn.prepareStatement(SQL);

					preparedStatement.setString(1, nome);
					preparedStatement.setInt(2, idProdotto);
					
					preparedStatement.executeUpdate();

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
				
				return flag;
			}
		} else if (action.equals("descrizione")) {
			String descrizione = dato;
			
			if (descrizione.length() > 300)
				flag = false;
			for (int i = 0; i <descrizione.length(); i++) {
				if (!(Character.isWhitespace(descrizione.charAt(i))
						||Character.isLetter(descrizione.charAt(i)))) {
					flag = false;
				}
			}
			
			if (flag == true) {
				try {
					conn = ds.getConnection();

					SQL = "UPDATE prodotto SET descrizione = ? WHERE idProdotto = ?";
					
					preparedStatement = conn.prepareStatement(SQL);

					preparedStatement.setString(1, descrizione);
					preparedStatement.setInt(2, idProdotto);
					
					preparedStatement.executeUpdate();

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
				
				return flag;
			}
		} else if (action.equals("prezzo")) {
			double prezzo = Double.parseDouble(dato);
			if (prezzo < 0)
				flag = false;
			
			if (flag == true) {
				try {
					conn = ds.getConnection();

					SQL = "UPDATE prodotto SET prezzo = ? WHERE idProdotto = ?";
					
					preparedStatement = conn.prepareStatement(SQL);

					preparedStatement.setDouble(1, prezzo);
					preparedStatement.setInt(2, idProdotto);
					
					preparedStatement.executeUpdate();

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
				
				return flag;
			}
		} else if (action.equals("quantità")) {
			int quantita = Integer.parseInt(dato);
			if (quantita <= 0)
				flag = false;
			if (flag == true) {
				try {
					conn = ds.getConnection();

					SQL = "UPDATE prodotto SET quantita = ? WHERE idProdotto = ?";
					
					preparedStatement = conn.prepareStatement(SQL);

					preparedStatement.setInt(1, quantita);
					preparedStatement.setInt(2, idProdotto);
					
					preparedStatement.executeUpdate();

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
				
				return flag;
			}
		}

		return false;
	}

	// _________________________________________________________________________________________________
	// ricerca prezzo minore uguale
	/**
	 * Questo metodo permette di effettuare una ricerca nel database in base al
	 * prezzo. Ha come parametro in input il prezzo secondo cui effettuare la
	 * ricerca e come valore di ritorno una lista di tutti i prodotto aventi
	 * prezzo maggiore o uguale al prezzo in input
	 */
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
			while (rs.next()) {
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
				if (preparedStatement1 != null) {
					preparedStatement1.close();
				}
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		return lista;
	}

	// _________________________________________________________________________________________________
	// ricerca prezzo maggiore uguale
	/**
	 * Questo metodo permette di effettuare una ricerca nel database in base al
	 * prezzo. Ha come parametro in input il prezzo secondo cui effettuare la
	 * ricerca e come valore di ritorno una lista di tutti i prodotto aventi
	 * prezzo minore o uguale al prezzo in input
	 */
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
			while (rs.next()) {
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
				if (preparedStatement1 != null) {
					preparedStatement1.close();
				}
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		return lista;
	}
	// _________________________________________________________________________________________________

	// ricerca per nome (ritorna una lista)
	/**
	 * Questo metodo permette di effettuare una ricerca nel database in base al
	 * nome. Ha come parametro in input il nome secondo cui effettuare la
	 * ricerca e come valore di ritorno una lista di tutti i prodotto aventi lo
	 * stesso nome del nome in input
	 */
	public ArrayList<Prodotto> ricercaNome(String nome) throws SQLException {
		Connection conn = null;
		PreparedStatement preparedStatement1 = null;
		String SQL1 = " select * from prodotto where nome =  ?";
		ArrayList<Prodotto> lista = new ArrayList<Prodotto>();
		try {
			conn = ds.getConnection();

			preparedStatement1 = conn.prepareStatement(SQL1);
			preparedStatement1.setString(1, nome);
			ResultSet rs = preparedStatement1.executeQuery();
			while (rs.next()) {
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
				if (preparedStatement1 != null) {
					preparedStatement1.close();
				}
			} finally {
				if (conn != null)
					conn.close();
			}
		}
		return lista;
	}
	// _________________________________________________________________________________________________

}
