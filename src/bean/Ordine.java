package bean;


import java.util.ArrayList;


public class Ordine {
	
	//costruttori
	public Ordine() {
	}
	
	public Ordine(String utenteOrdine,String iban,String stato , double prezzoTotale , int id ,ArrayList<Prodotto> prodotto) {
		this.utenteOrdine = utenteOrdine;
		this.iban = iban;
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
	
	
	
	private String utenteOrdine,stato,iban;
	private double prezzoTotale ;
	private int id;
	private ArrayList<Prodotto> prodotto;
}
