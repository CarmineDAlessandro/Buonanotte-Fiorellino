package utentipackage;

import java.sql.Date;

/**Questa è la classe bean dell'utente. Contiene al suo interno tutte
 * le informazioni relative all'utente*/
public class Utente {
	/**Questo attributo è il nome dell'utente.
	 * è reso accessibile tramite metodi get e set*/
	private String nome;
	/**Questo attributo è il cognome dell'utente.
	 * è reso accessibile tramite metodi get e set*/
	private String cognome;
	/**Questo attributo è l'e-mail dell'utente.
	 * è reso accessibile tramite metodi get e set*/
	private String eMail;
	/**Questo attributo è il codice fiscale dell'utente.
	 * è reso accessibile tramite metodi get e set*/
	private String codiceFiscale;
	/**Questo attributo è il codice di avviamento postale dell'utente.
	 * è reso accessibile tramite metodi get e set*/
	private String cap;
	/**Questo attributo è la città di nascita dell'utente.
	 * è reso accessibile tramite metodi get e set*/
	private String cittaDiNascita;
	/**Questo attributo è la città di residenza dell'utente.
	 * è reso accessibile tramite metodi get e set*/
	private String cittaResidenza;
	/**Questo attributo è la provincia di residenza dell'utente.
	 * è reso accessibile tramite metodi get e set*/
	private String provincia;
	/**Questo attributo è la via di residenza dell'utente.
	 * è reso accessibile tramite metodi get e set*/
	private String via;
	/**Questo attributo è l'username dell'utente.
	 * è reso accessibile tramite metodi get e set*/
	private String username;
	/**Questo attributo è la password dell'utente.
	 * è reso accessibile tramite metodi get e set*/
	private String password;
	/**Questo attributo è il numero civico dell'utente.
	 * è reso accessibile tramite metodi get e set*/
	private int numeroCivico;
	/**Questo attributo è la data di nascita dell'utente.
	 * è reso accessibile tramite metodi get e set*/
	private Date dataDiNascita;
	
	/**Il costruttore vuoto*/
	public Utente(){};
	
	
	/**Questo costruttore ha come parametri campi per tutte le variabili
	 * dell'oggetto utente*/
	public Utente(String nome, String cognome, String eMail, String codiceFiscale, String cittaDiNascita,
			String cittaResidenza, String provincia, String via,String cap, String username, String password, int numeroCivico,
			Date dataDiNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.eMail = eMail;
		this.codiceFiscale = codiceFiscale;
		this.cittaDiNascita = cittaDiNascita;
		this.cittaResidenza = cittaResidenza;
		this.provincia = provincia;
		this.via = via;
		this.username = username;
		this.password = password;
		this.numeroCivico = numeroCivico;
		this.dataDiNascita = dataDiNascita;
		this.cap = cap;
	}
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getCittaDiNascita() {
		return cittaDiNascita;
	}
	public void setCittaDiNascita(String cittaDiNascita) {
		this.cittaDiNascita = cittaDiNascita;
	}
	public String getCittaResidenza() {
		return cittaResidenza;
	}
	public void setCittaResidenza(String cittaResidenza) {
		this.cittaResidenza = cittaResidenza;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getNumeroCivico() {
		return numeroCivico;
	}
	public void setNumeroCivico(int numeroCivico) {
		this.numeroCivico = numeroCivico;
	}
	public Date getDataDiNascita() {
		return dataDiNascita;
	}
	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}
	
	
	
}