package utentipackage;

/**Questa è la classe bean dell'amministratore. Contiene al suo interno
 * tutte le informazioni relative all'amministratore.*/
public class Amministratore {
	/**Questo attributo è l'e-mail dell'amministratore.
	 * è reso accessibile tramite metodi get e set*/
	private String eMail;
	/**Questo attributo è l'username dell'amministratore.
	 * è reso accessibile tramite metodi get e set*/
	private String username;
	/**Questo attributo è la password dell'amministratore.
	 * è reso accessibile tramite metodi get e set*/
	private String password;
	
	/**Questo costruttore vuole in input i valori delle 3 variaibli
	 * dell'oggetto Amministratore*/
	public Amministratore(String eMail, String username, String password) {
		super();
		this.eMail = eMail;
		this.username = username;
		this.password = password;
	}
	/**Questo è un costruttore vuoto*/
	public Amministratore() {
		super();
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
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
}
