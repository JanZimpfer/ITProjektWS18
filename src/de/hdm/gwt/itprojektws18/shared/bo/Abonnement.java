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
	

}
