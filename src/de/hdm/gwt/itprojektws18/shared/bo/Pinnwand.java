package de.hdm.gwt.itprojektws18.shared.bo;

public class Pinnwand extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Fremdschlüsselbeziehung zum Inhaber des Pinnwand
	 */
	private int nutzerFK;

	/**
	 * Der Fremdschlüssel zum Pinnwandinhaber wird ausgelesen
	 * @return
	 */
	public int getNutzerFK() {
		return nutzerFK;
	}

	/**
	 * Der Fremdschlüssel zum Pinnwandinhaber wird gesetzt.
	 * @param nutzerId
	 */
	public void setNutzerFK(int nutzerFK) {
		this.nutzerFK = nutzerFK;
	}
	
	/**
	 * String-Repräsentation einer Pinnwandinstanz
	 */
	public String toString() {
		return super.toString() + " Nutzer:" + this.getNutzerFK();
	}

}
