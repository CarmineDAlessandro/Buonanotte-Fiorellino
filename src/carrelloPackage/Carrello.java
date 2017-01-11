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
	/**Questo costruttore crea un carrello da una lista di prodotti*/
	public Carrello (ArrayList <Prodotto> lista) {
		aggiungiCarrello(lista);
	}
	
	/**Questo metodo aggiunge un prodotto al carrello.
	 * Se il prodotto non è ancora contenuto nel carrello, ne aggiunge
	 * un'istanza. Se il prodotto esiste, allora ne aumenta la quantità
	 * di 1*/
	public void aggiungiProdotto (Prodotto prodotto) {
		int i=0; //lista.get(i) lancia nullpointerexception! 
		while (i<lista.size() && prodotto.getIdProdotto() != lista.get(i).getIdProdotto()) {
			i++;
		}
		
		if (i == lista.size()) {
			lista.add(new Prodotto (prodotto.getIdProdotto() ,prodotto.getNome() ,
					prodotto.getUrlImmagine(), prodotto.getDescrizione(),1, prodotto.getPrezzo()));
		} else {
			lista.get(i).setQuantita(lista.get(i).getQuantita()+1);
		}
		
		prezzo += prodotto.getPrezzo();
	}
	
	/**Questo metodo rimuove un prodotto dal carrello.
	 * Se nel carrello il numero di prodotti di quel tipo è 
	 * maggiore di 1, ne diminuisce la quantità di 1. Se la quantità
	 * del prodotto è uguale a 1, lo rimuove dal carrello*/
	public void rimuoviProdotto(Prodotto prodotto) {
		int i=0;
		while (i<lista.size() && prodotto.getIdProdotto() != lista.get(i).getIdProdotto()) {
			i++;
		}
		
		if (i == lista.size()) {
			lista.get(i).setQuantita(lista.get(i).getQuantita()-1);
			if (lista.get(i).getQuantita()==0)
				lista.remove(i);
		}
		
		prezzo -= prodotto.getPrezzo();
	}
	/**Questo metodo permette di aggiungere ad un carrello, un altro carrello
	 * 
	 * Carrello carrello1 A B C  prezzo 50
	 * ArrayList carrello2 G H I L (prezzo 65)
	 * 
	 * carrello1.aggiungiCarrello(carrello2);
	 * 
	 * carrello1 A B C G H I L  prezzo 115
	 * */
	public void aggiungiCarrello (ArrayList <Prodotto> aggiunta) {
		for (int i=0; i<lista.size(); i++)
			aggiungiProdotto(aggiunta.get(i));
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