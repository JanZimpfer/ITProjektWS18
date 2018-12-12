package de.hdm.gwt.itprojektws18.shared.bo;

public class Pinnwand extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Fremdschl�sselbeziehung zum Inhaber des Pinnwand
	 */
	private int nutzerFK;

	/**
	 * Der Fremdschl�ssel zum Pinnwandinhaber wird ausgelesen
	 * @return
	 */
	public int getNutzerFK() {
		return nutzerFK;
	}

	/**
	 * Der Fremdschl�ssel zum Pinnwandinhaber wird gesetzt.
	 * @param nutzerId
	 */
	public void setNutzerFK(int nutzerFK) {
		this.nutzerFK = nutzerFK;
	}
	
	// equals Methode implementieren?!
	
	/**
	 * String-Repr�sentation einer Pinnwandinstanz
	 */
	public String toString() {
		return super.toString() + "Nutzer: " + this.getNutzerFK();
	}

}
