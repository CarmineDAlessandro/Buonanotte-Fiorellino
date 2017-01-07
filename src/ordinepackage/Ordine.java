package ordinepackage;

import java.util.ArrayList;
import prodottipackage.Prodotto;

/**Questa � la classe bean dell'ordine. Contiene al suo interno tutte
 * le informazioni relative ad un ordine*/
public class Ordine {
	/**Questo attributo � l'utente a cui si riferisce l'ordine.
	 * � reso accessibile tramite metodi get e set*/
	private String utenteOrdine;
	/**Questo attributo � lo stato dell'ordine.
	 * � reso accessibile tramite metodi get e set*/
	private String stato;
	/**Questo attributo � il codice iban relativo al pagamento
	 * dell'ordine. � reso accessibile tramite metodi get e set*/
	private String iban;
	/**Questo attributo � il prezzo totale dell'ordine.
	 * � reso accessibile tramite metodi get e set*/
	private double prezzoTotale ;
	/**Questo attributo � l'identificativo dell'ordine.
	 * � reso accessibile tramite metodi get e set*/
	private int id ;
	/**Questo attributo � la lista di tutti i prodotti dell'ordine.
	 * � reso accessibile tramite metodi get e set*/
	private ArrayList<Prodotto> prodotto;
	
	//costruttori
	public Ordine() {
	}
	
	public Ordine(String utenteOrdine,String iban,String stato , double prezzoTotale , int id ,ArrayList<Prodotto> prodotto) {
		this.utenteOrdine = utenteOrdine;
		this.iban= iban;
		this.stato = stato;
		this.prezzoTotale = prezzoTotale;
		this.id = id;
		this.prodotto = prodotto;
	}
	
	//metodi get
	public String getUtenteOrdine() {
		return utenteOrdine;
	}
	public String getStato() {
		return stato;
	}
	public double getPrezzoTotale() {
		return prezzoTotale;
	}
	public int getId() {
		return id;
	}
	public ArrayList<Prodotto> getProdotto() {
		return prodotto;
	}
	public String getIban() {
		return iban;
	}
	
	//metodi set
	public void setUtenteOrdine(String utenteOrdine) {
		this.utenteOrdine = utenteOrdine;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	public void setPrezzoTotale(double prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setProdotto(ArrayList<Prodotto> prodotto) {
		this.prodotto = prodotto;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	
	
	
}
