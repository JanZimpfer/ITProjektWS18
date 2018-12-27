package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Kommentar;

public class LikeBox extends VerticalPanel{
	
	/**
	 * Erzeugen eines PinnwandVerwaltung-Objekts um eine Applikationsverwaltung zu initialisieren.
	 */
	private static PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	
	/**
	 * Instanziierung der GUI Elemente
	 */
	private Label nickname = new Label();
	private Label vorname = new Label();
	private Label nachname = new Label();
	private	Label erstellZeitpunkt = new Label();
	
	/**
	 * Deklarierung der BO, die verwendet werden
	 */
	private Kommentar kommentar = null;
	private Beitrag beitrag = null;
	
	

	public LikeBox() {
		
	}
	
	public void onLoad() {
		
		this.addStyleName("likebox");
		
		nickname.addStyleName("nicknameLikeBox");
		vorname.addStyleName("vornameLikeBox");
		nachname.addStyleName("nachnameLikeBox");
		erstellZeitpunkt.addStyleName("erstellZeitpunktLikeBox");
		
		this.add(nickname);
		this.add(vorname);
		this.add(nachname);
		this.add(erstellZeitpunkt);
	}
}
