package de.hdm.gwt.itprojektws18.shared.bo;

public class Pinnwand extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Fremdschlüsselbeziehung zum Inhaber des Pinnwand
	 */
	private int inhaberId;

	/**
	 * Der Fremdschlüssel zum Pinnwandinhaber wird ausgelesen
	 * @return
	 */
	public int getInhaberId() {
		return inhaberId;
	}

	/**
	 * Der Fremdschlüssel zum Pinnwandinhaber wird gesetzt.
	 * @param nutzerId
	 */
	public void setInhaberId(int nutzerId) {
		this.inhaberId = nutzerId;
	}
	
	// equals Methode implementieren?!
	

}
