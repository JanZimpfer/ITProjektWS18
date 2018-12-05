package de.hdm.gwt.itprojektws18.shared.bo;

public class Abonnement extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Fremdschlüsselbeziehung zum Bezugsprofil des Abonnements
	 */
	private int nutzerFK;

	/**
	 * Der Fremdschlüssel zum Bezugsprofil wird ausgelesen
	 * @return
	 */
	public int getNutzerFK() {
		return nutzerFK;
	}

	/**
	 * Der Fremdschlüssel zum Bezugsprofil wird gesetzt.
	 * @param nutzerId
	 */
	public void setNutzerFK(int nutzerFK) {
		this.nutzerFK = nutzerFK;
	}
	
	
	/**
	 * Fremdschlüsselbeziehung zur Pinnwand
	 */
	private int pinnwandFK;

	/**
	 * Der Fremdschlüssel zur Pinnwand wird ausgelesen
	 * @return
	 */
	public int getPinnwandFK() {
		return pinnwandFK;
	}

	/**
	 * Der Fremdschlüssel zur Pinnwand wird gesetzt
	 * @param pinnwandFK
	 */
	public void setPinnwandFK(int pinnwandFK) {
		this.pinnwandFK = pinnwandFK;
	}
	
	

}
