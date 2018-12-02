package de.hdm.gwt.itprojektws18.shared.bo;

public class Like extends BusinessObject{
	
	private static final long serialVersionUID = 1L;

	
	/**
	 * Fremdschluesselbeziehung zum Beitrag
	 */
	
	private int zielId;

	/**
	 * Auslesen des Fremdschluessels zum Beitrag
	 * @return zielId
	 */
	public int getZielId() {
		return zielId;
	}

	/**
	 * Setzen des Fremdschluessels zum Beitrag
	 * @param zielId
	 */
	public void setZielId(int zielId) {
		this.zielId = zielId;
	}
	

}
