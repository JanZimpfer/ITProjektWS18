package de.hdm.gwt.itprojektws18.shared.bo;

public class Beitrag extends Textbeitrag{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Fremdschlüsselbeziehung zur Pinnwand
	 */
	
	private int pinnwandFK;

	/**
	 * Auslesen des Fremdschlüssels zur Pinnwand
	 * @return 
	 */
	public int getPinnwandFK() {
		return pinnwandFK;
	}

	/**
	 * Setzen des Fremdschlüssels zur Pinnwand
	 * @param 
	 */
	public void setPinnwandFK(int pinnwandFK) {
		this.pinnwandFK = pinnwandFK;
	}
	
	/**
	 * Fremdschlüsselbeziehung zum Nutzer
	 */
	public int nutzerFK;

	/**
	 * Auslesen des Fremdschlüssels zum Nutzer
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
		
		return super.toString() + " Pinnwand:" + this.getPinnwandFK() + " Nutzer:" + this.getNutzerFK();
	}
	
	
}
