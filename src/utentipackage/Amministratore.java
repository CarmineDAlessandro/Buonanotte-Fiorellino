package utentipackage;

/**Questa � la classe bean dell'amministratore. Contiene al suo interno
 * tutte le informazioni relative all'amministratore.*/
public class Amministratore {
	/**Questo attributo � l'e-mail dell'amministratore.
	 * � reso accessibile tramite metodi get e set*/
	private String eMail;
	/**Questo attributo � l'username dell'amministratore.
	 * � reso accessibile tramite metodi get e set*/
	private String username;
	/**Questo attributo � la password dell'amministratore.
	 * � reso accessibile tramite metodi get e set*/
	private String password;
	
	/**Questo costruttore vuole in input i valori delle 3 variaibli
	 * dell'oggetto Amministratore*/
	public Amministratore(String eMail, String username, String password) {
		super();
		this.eMail = eMail;
		this.username = username;
		this.password = password;
	}
	/**Questo � un costruttore vuoto*/
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
