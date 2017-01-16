package prodottipackage;

/**Questa è la classe bean del prodotto, contiene tutte le informazioni
 * relative al prodotto*/
public class Prodotto {
	
	/**Questo attributo è il nome del prodotto.
	 * è reso accessibile tramite metodi get e set*/
	private String  nome;
	/**Questo attributo è la descrizione del prodotto.
	 * è reso accessibile tramite metodi get e set*/
	private String descrizione;
	/**Questo attributo è l'url dell'immagine del prodotto.
	 * è reso accessibile tramite metodi get e set*/
	private String urlImmagine ;
	/**Questo attributo è la quantità disponibile del prodotto.
	 * è reso accessibile tramite metodi get e set*/
	private int quantita;
	/**Questo attributo è l'identificativo del prodotto.
	 * è reso accessibile tramite metodi get e set*/
	private int idProdotto;
	/**Questo attributo è il prezzo del prodotto.
	 * è reso accessibile tramite metodi get e set*/
	private double prezzo;
	//costruttori
	
	/**Il costruttore vuoto*/
	public Prodotto() {
	}
	
	/**Questo costruttore vuole come parametri un valore per ogni
	 * variabile del prodotto*/
	public Prodotto(int idProdotto ,String nome ,String urlImmagine,String descrizione,int quantita,double prezzo) {
		this.idProdotto= idProdotto;
		this.nome = nome;
		this.urlImmagine = urlImmagine;
		this.descrizione = descrizione;
		this.quantita = quantita;
		this.prezzo = prezzo;
	}
	
	//metodi get
	public int getQuantita() {
		return quantita;
	}
	public	double getPrezzo() {
		return prezzo;
	}
	public String getNome() {
		return nome;
	}
	public int getIdProdotto() {
		return idProdotto;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public String getUrlImmagine() {
		return urlImmagine;
	}
	
	//metodi set
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setIdProdotto(int idProdotto) {
		this.idProdotto = idProdotto;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void setUrlImmagine(String urlImmagine) {
		this.urlImmagine = urlImmagine;
	}
	
}