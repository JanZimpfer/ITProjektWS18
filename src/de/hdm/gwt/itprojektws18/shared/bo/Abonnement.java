package de.hdm.gwt.itprojektws18.shared.bo;

public class Abonnement extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Fremdschl�sselbeziehung zum Bezugsprofil des Abonnements
	 */
	private int nutzerFK;

	/**
	 * Der Fremdschl�ssel zum Bezugsprofil wird ausgelesen
	 * @return
	 */
	public int getNutzerFK() {
		return nutzerFK;
	}

	/**
	 * Der Fremdschl�ssel zum Bezugsprofil wird gesetzt.
	 * @param nutzerId
	 */
	public void setNutzerFK(int nutzerFK) {
		this.nutzerFK = nutzerFK;
	}
	
	
	/**
	 * Fremdschl�sselbeziehung zur Pinnwand
	 */
	private int pinnwandFK;

	/**
	 * Der Fremdschl�ssel zur Pinnwand wird ausgelesen
	 * @return
	 */
	public int getPinnwandFK() {
		return pinnwandFK;
	}

	/**
	 * Der Fremdschl�ssel zur Pinnwand wird gesetzt
	 * @param pinnwandFK
	 */
	public void setPinnwandFK(int pinnwandFK) {
		this.pinnwandFK = pinnwandFK;
	}
	
	

}
