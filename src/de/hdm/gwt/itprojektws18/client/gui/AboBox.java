package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Abonnement;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;

import java.util.Vector;



public class AboBox extends VerticalPanel{
	
	/**
	 * Erzeugen eines PinnwandVerwaltung-Objekts um eine Applikationsverwaltung zu initialisieren.
	 */
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	ClientsideSettings clientSettings = new ClientsideSettings();
	
	/**
	 * Instanziierung der GUI Elemente
	 */
	
	private Vector<AboPinnwandBox> nutzerAbos = new Vector<AboPinnwandBox>();
	
	private Vector<Abonnement> abocount = new Vector<Abonnement>();
	
	private Nutzer n = null;
	
	public AboBox() {
		
	}
	

	public void onLoad() {
		
		this.addStyleName("aboBox");
		
		
//		//for(int i = 0; i < pinnwandVerwaltung.getAllAbosFor(n, null); i++) {
//			AboPinnwandBox tempAboPinnwandBox = new AboPinnwandBox(i);
//			nutzerAbos.add(i, tempAboPinnwandBox);
//		}
		
		for(int i = 0; i < nutzerAbos.size(); i++) {
			this.add(nutzerAbos.elementAt(i));
		}
		
		
	}
	
	//Implementierung: findAllAbosBy, die eine Liste an Abonnementbeziehungen des angemeldetenNutzers liefern,
	// Id Abgleich dieser Liste, ob jeweilige Pinnwand ID bereits dem VP geaddet wurde (if-Abfrage) 
	//Nutzen der Methode: getAllAbosByNutzer
	
	
}
