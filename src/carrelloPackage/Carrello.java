package carrelloPackage;

import java.util.ArrayList;

import prodottipackage.Prodotto;

/**Questa è la classe bean del carrello. Il suo compito è quello
 * di contenere tutte le informazioni relative al carrello 
 * di un utente*/
public class Carrello {
	/** Questo attributo è una lista dei prodotti presenti nel carrello
	 * */
	private ArrayList <Prodotto> lista;
	/**Questo attributo è il prezzo totale del carrello*/
	private double prezzo;
	/**Questo attributo è l'id numerico del carrello*/
	private int id;
	
	/**Questo costruttore crea un carrello senza prodotti e setta il prezzo
	 * del carrello a 0*/
	public Carrello () {
		prezzo = 0;
	}
	/**
	 * Questo costruttore crea un carrrello già settato.
	 * @param unId setta l'id del carrello
	 * @param unPrezzo setta il prezzo del carrello
	 * @param unaLista setta la lista dei prodotti del carrelo
	 */
	public Carrello (int unId, double unPrezzo, ArrayList<Prodotto> unaLista) {
		id = unId;
		prezzo = unPrezzo;
		lista = unaLista;
	}



	
	/**Questo metodo setta il prezzo*/
	public void setPrezzo (double unPrezzo) {
		prezzo = unPrezzo;
	}
	/**Questo metodo ritorna il prezzo*/
	public double getPrezzo() {
		return prezzo;
	}
	
	/**Metodo che restituisce la lista di prodotti contenuti nel carrello.*/
	public ArrayList<Prodotto> getLista() {
		return lista;
	}
	/**Metodo che imposta la lista di prodotti contenuti nel carrello.*/
	public void setLista(ArrayList <Prodotto> prodotti) {
		lista=prodotti;
	}
	/**Metodo che restituisce l'id del carrello*/
	public int getId() {
		return id;
	}
	/**Metodo che imposta l'ide del carrello*/
	public void setId(int id) {
		this.id = id;
	}
}