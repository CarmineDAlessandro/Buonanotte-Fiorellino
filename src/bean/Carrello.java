package bean;

import java.util.ArrayList;

public class Carrello {
	
	//costruttori
	public Carrello() {
	}
	
	public Carrello(String utenteCarrello ,int idCarrello ,ArrayList<Prodotto> prodotto) {
		this.utenteCarrello = utenteCarrello;
		this.idCarrello = idCarrello;
		this.prodotto = prodotto;
	}
	
	//metodi get 
	public String getUtenteCarrello() {
		return utenteCarrello;
	}
	public ArrayList<Prodotto> getProdotto() {
		return prodotto;
	}
	public int getIdCarrello() {
		return idCarrello;
	}
	
	//metodi set
	public void setUtenteCarrello(String utenteCarrello) {
		this.utenteCarrello = utenteCarrello;
	}
	public void setProdotto(ArrayList<Prodotto> prodotto) {
		this.prodotto = prodotto;
	}
	public void setIdCarrello(int idCarrello) {
		this.idCarrello = idCarrello;
	}
	
	private String utenteCarrello ;
	private int idCarrello ;
	private ArrayList<Prodotto> prodotto;
}
