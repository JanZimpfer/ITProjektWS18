package de.hdm.gwt.itprojektws18.client;

import de.hdm.gwt.itprojektws18.client.gui.PinnwandBox;
import de.hdm.gwt.itprojektws18.shared.FieldVerifier;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltung;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.client.gui.ProfilBox;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ITProjektWS18 implements EntryPoint {
	
	/**
	    * Erzeugen eines PinnwandVerwaltung-Objekts um eine Applikationsverwaltung zu initialisieren.
		*/
		PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
		ClientsideSettings clientSettings = new ClientsideSettings();
		
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
	
		/*
		 * Erstellung eines VerticalPanels welches fuer den Navigator genutzt wird.
		 */
		VerticalPanel navPanel = new VerticalPanel();
		HorizontalPanel ProfilBox = new HorizontalPanel ();
		
		/*
		 * Zuweisung des VerticalPanels zum DIV-Element "Navigator"
		 * in der entsprechenden HTML Datei.
		 */
		RootPanel.get("Navigator").add(navPanel);
		RootPanel.get("ProfilBox").add(ProfilBox);
		
		/**
		 * Kommentar an's Team:
		 * hier der fehlende Aufbau.
		 * 
		 * @Niklas
		 * @Florian 
		 */
		
	}
}
