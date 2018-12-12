package de.hdm.gwt.itprojektws18.shared.bo;

public class Beitrag extends Textbeitrag{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Fremdschluesselbeziehung zur Pinnwand
	 */
	
	private int pinnwandFK;

	/**
	 * Auslesen des Fremdschluessels zur Pinnwand
	 * @return 
	 */
	public int getPinnwandFK() {
		return pinnwandFK;
	}

	/**
	 * Setzen des Fremdschluessels zur Pinnwand
	 * @param 
	 */
	public void setPinnwandFK(int pinnwandFK) {
		this.pinnwandFK = pinnwandFK;
	}
	
	/**
	 * Fremdschluesselbeziehung zum Nutzer
	 */
	public int nutzerFK;

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
	 * String-Repräsentation einer Beitragsinstanz
	 */
	public String toString() {
		
		return super.toString() + "Pinnwand: " + this.getPinnwandFK() + "Nutzer: " + this.getNutzerFK();
	}
	
	
}
