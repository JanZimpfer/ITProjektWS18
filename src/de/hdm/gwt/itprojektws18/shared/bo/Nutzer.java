package de.hdm.gwt.itprojektws18.shared.bo;


public class Nutzer extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * selbstgewähltes Pseudonym des Nutzers
	 */
	private String nickname = "";
	
	/**
	 * Vorname des Nutzers
	 */
	private String vorname = "";
	
	/**
	 * Nachname des Nutzers
	 */
	private String nachname = "";

	/**
	 * Email-Adresse des Nutzers
	 */
	private String email = "";
	
	/**
	 * Das Pseudoynm wird ausgelesen
	 * @return
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Das Pseudonym wird gesetzt
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Der Vorname wird ausgelesen
	 * @return
	 */
	public String getVorname() {
		return vorname;
	}

	/**
	 * Der Vorname wird gesetzt
	 * @param vorname
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * Der Nachname wird ausgelesen
	 * @return
	 */
	public String getNachname() {
		return nachname;
	}

	/**
	 * Der Nachname wird gesetzt
	 * @param nachname
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	
	/**
	 * String-Repräsentation einer Nutzerinstanz
	 */
	public String toString() {
		
		return super.toString() + " Vorname: " + this.getVorname() + " Nachname:" + this.getNachname() + " Nickname:" + this.getNickname();
	}

	/**
	 * Email-Adresse wird ausgelesen
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Email-Adresse wird gesetzt
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
