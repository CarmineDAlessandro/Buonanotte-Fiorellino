package bean;

public class Utente {

	//costruttori
public Utente() {
}
	
public Utente(String ruolo ,String nome,String cognome ,String eMail , String codiceFiscale, String dataNascita ,String cittaNascita ,String cittaResidenza,String via,int numeroCivico,int cap,String username ,String password) {
	this.ruolo = ruolo;
	this.nome = nome;
	this.cognome = cognome;
	this.eMail = eMail;
	this.codiceFiscale = codiceFiscale;
	this.dataNascita = dataNascita ;
	this.cittaNascita = cittaNascita;
	this.cittaResidenza = cittaResidenza;
	this.via= via;
	this.numeroCivico = numeroCivico;
	this.cap = cap;
	this.username = username;
	this.password = password;
}

	//metodi get
public int getCap() {
	return cap;
}
public String getCittaNascita() {
	return cittaNascita;
}
public String getCittaResidenza() {
	return cittaResidenza;
}
public String getCodiceFiscale() {
	return codiceFiscale;
}
public String getDataNascita() {
	return dataNascita;
}
public String getNome() {
	return nome;
}
public String getCognome() {
	return cognome;
}
public String geteMail() {
	return eMail;
}
public String getVia() {
	return via;
}
public int getNumeroCivico() {
	return numeroCivico;
}
public String getPassword() {
	return password;
}
public String getUsername() {
	return username;
}
public String getRuolo() {
	return ruolo;
}


//metodi set
public void setCap(int cap) {
	this.cap = cap;
}
public void setCittaNascita(String cittaNascita) {
	this.cittaNascita = cittaNascita;
}
public void setCittaResidenza(String cittaResidenza) {
	this.cittaResidenza = cittaResidenza;
}
public void setCodiceFiscale(String codiceFiscale) {
	this.codiceFiscale = codiceFiscale;
}
public void setCognome(String cognome) {
	this.cognome = cognome;
}
public void setDataNascita(String dataNascita) {
	this.dataNascita = dataNascita;
}
public void setRuolo(String ruolo) {
	this.ruolo = ruolo;
}
public void setNome(String nome) {
	this.nome = nome;
}
public void seteMail(String eMail) {
	this.eMail = eMail;
}
public void setPassword(String password) {
	this.password = password;
}
public void setNumeroCivico(int numeroCivico) {
	this.numeroCivico = numeroCivico;
}
public void setUsername(String username) {
	this.username = username;
}
public void setVia(String via) {
	this.via = via;
}

private String ruolo,nome,cognome ,eMail ,codiceFiscale,dataNascita ,cittaNascita ,cittaResidenza,via	,username ,password ;
private int numeroCivico,cap ;
}