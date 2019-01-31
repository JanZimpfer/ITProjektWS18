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
	 * Der Fremdschl�ssel zum Bezugsprofil wird gesetzt.
	 * @param nutzerId
	 */
	public void setNutzerFK(int nutzerFK) {
		this.nutzerFK = nutzerFK;
	}
	
	
	/**
	 * Fremdschlüsselbeziehung der zu abonnierenden Pinnwand
	 */
	private int pinnwandFK;

	/**
	 * Der Fremdschl�ssel der zu abonnierenden Pinnwand wird ausgelesen
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
	
	/**
	 * String-Repräsentation einer Aboinstanz
	 */
	public String toString() {
		return super.toString() + " abonnierter Nutzer:" + this.getNutzerFK() + " abonnierte Pinnwand:" + this.getPinnwandFK();
	}
	

}
