package de.hdm.gwt.itprojektws18.shared.bo;

public class Like extends BusinessObject{
	
	private static final long serialVersionUID = 1L;

	
	/**
	 * Fremdschluesselbeziehung zum Beitrag
	 */
	
	private int beitragFK;

	/**
	 * Auslesen des Fremdschluessels zum Beitrag
	 * @return zielId
	 */
	public int getBeitragFK() {
		return beitragFK;
	}

	/**
	 * Setzen des Fremdschluessels zum Beitrag
	 * @param zielId
	 */
	public void setBeitragFK(int beitragFK) {
		this.beitragFK = beitragFK;
	}
	
	/**
	 * Fremdschluesselbeziehung zum Nutzer
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
	 * Setzen des Fremdschluessels zum Nutzer
	 * @param nutzerFK
	 */
	public void setNutzerFK(int nutzerFK) {
		this.nutzerFK = nutzerFK;
	}
	
	/**
	 * String-Repräsentation einer Likeinstanz
	 */
	public String toString() {
		return super.toString() + "Beitrag: " + this.getBeitragFK() + "Nutzer: " + this.getNutzerFK();
	}

}
