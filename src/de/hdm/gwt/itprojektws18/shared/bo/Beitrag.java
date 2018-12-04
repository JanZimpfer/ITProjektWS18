package de.hdm.gwt.itprojektws18.shared.bo;

public class Beitrag extends Textbeitrag{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Fremdschluesselbeziehung zur Pinnwand
	 */
	
	private int pinnwandFK;

	/**
	 * Auslesen des Fremdschluessels zur Pinnwand
	 * @return zielId
	 */
	public int getPinnwandFK() {
		return pinnwandFK;
	}

	/**
	 * Setzen des Fremdschluessels zur Pinnwand
	 * @param zielId
	 */
	public void setPinnwandFK(int pinnwandFK) {
		this.pinnwandFK = pinnwandFK;
	}
	
}
