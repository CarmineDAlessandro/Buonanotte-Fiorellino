package bean;

public class Prodotto {

	//costruttori
	public Prodotto() {
	}
	
	public Prodotto(int idProdotto ,String nome ,String urlImmagine,String descrizione,int quantita,int prezzo) {
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
	public void setIdProdotto(int string) {
		this.idProdotto = string;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void setUrlImmagine(String urlImmagine) {
		this.urlImmagine = urlImmagine;
	}
	
	private String nome ,descrizione,urlImmagine ;
	private int idProdotto ,quantita ;
	private double prezzo;
}
