package de.hdm.gwt.itprojektws18.shared.bo;

public class Like extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Fremdschlüsselbeziehung zum Beitrag
	 */
	private int beitragFK;

	/**
	 * Auslesen des Fremdschlüssels zum Beitrag
	 * @return zielId
	 */
	public int getBeitragFK() {
		return beitragFK;
	}

	/**
	 * Setzen des Fremdschlüssels zum Beitrag
	 * @param zielId
	 */
	public void setBeitragFK(int beitragFK) {
		this.beitragFK = beitragFK;
	}
	
	/**
	 * Fremdschlüsselbeziehung zum Nutzer
	 */
	private int nutzerFK;

	
	/**
	 * Auslesen des Fremdschluessels zum Nutzer
	 * @return
	 */
	public int getNutzerFK() {
		return nutzerFK;
	}

	/**
	 * Setzen des Fremdschlüssels zum Nutzer
	 * @param nutzerFK
	 */
	public void setNutzerFK(int nutzerFK) {
		this.nutzerFK = nutzerFK;
	}
	
	/**
	 * String-Repräsentation einer Likeinstanz
	 */
	public String toString() {
		return super.toString() + " Beitrag:" + this.getBeitragFK() + " Nutzer:" + this.getNutzerFK();
	}

}
