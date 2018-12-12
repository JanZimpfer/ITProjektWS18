package de.hdm.gwt.itprojektws18.shared.bo;

public class Textbeitrag extends BusinessObject{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Inhalt des Textbeitrages
	 */
	private String text;

	/**
	 * Inhalt des Textbeitrages wird ausgelesen
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * Inhalt des Textbeitrages wird gesetzt
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * String-Repräsentation einer Textbeitraginstanz
	 */
	public String toString() {
		
		return super.toString() + "Text: " + this.getText();
	}

}
