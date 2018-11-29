package de.hdm.gwt.itprojektws18.shared.bo;

public class Abonnement extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Fremdschlüsselbeziehung zum Bezugsprofil des Abonnements
	 */
	private int bezugsProfilId;

	/**
	 * Der Fremdschlüssel zum Bezugsprofil wird ausgelesen
	 * @return
	 */
	public int getBezugsProfilId() {
		return bezugsProfilId;
	}

	/**
	 * Der Fremdschlüssel zum Bezugsprofil wird gesetzt.
	 * @param nutzerId
	 */
	public void setBezugsProfilId(int bezugsProfilId) {
		this.bezugsProfilId = bezugsProfilId;
	}
	

}
