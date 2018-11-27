package de.hdm.gwt.itprojektws18.shared.bo;

public class Beitrag extends Textbeitrag{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Fremdschluesselbeziehung zur Pinnwand
	 */
	
	private int zielId;

	/**
	 * Auslesen des Fremdschluessels zur Pinnwand
	 * @return zielId
	 */
	public int getZielId() {
		return zielId;
	}

	/**
	 * Setzen des Fremdschluessels zur Pinnwand
	 * @param zielId
	 */
	public void setZielId(int zielId) {
		this.zielId = zielId;
	}
	
}
