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
	
	private VerticalPanel abobox = new VerticalPanel();
	
	private VerticalPanel aboniertePW = new VerticalPanel();
	
	private HorizontalPanel profilbereich = new HorizontalPanel();
	
	private HorizontalPanel aboPinnwandInfos = new HorizontalPanel();
	
	private Label profil = new Label("Profil: ");
	
	private Label anzeigePW = new Label("Abonnierte Pinnwaende: ");
	
	private Button aboErstellBtn = new Button("Abo hinzufuegen");
	
	private Button deAboBtn = new Button("Abo entfernen");
	
	private Label nutzerNameLabel = new Label("Jan");
	
	private Label nickNameLabel = new Label("flizzy");

	
	private Nutzer n = null;
	
	public AboBox() {
		
		profilbereich.setSpacing(2);
		aboniertePW.setSpacing(2);
		aboPinnwandInfos.setSpacing(2);
		
	}
	

	public void onLoad() {
		
		this.addStyleName("abobox");
		this.add(abobox);
		
		abobox.add(profilbereich);
		abobox.add(aboniertePW);
		
		profilbereich.add(profil);
		profilbereich.add(aboErstellBtn);
		profilbereich.add(deAboBtn);
		
		aboniertePW.add(anzeigePW);
		aboniertePW.add(aboPinnwandInfos);
		
		aboPinnwandInfos.add(nutzerNameLabel);
		aboPinnwandInfos.add(nickNameLabel);
		
		profil.addStyleName("profil");
		anzeigePW.addStyleName("anzeigePW");
		aboErstellBtn.addStyleName("aboErstellBtn");
		deAboBtn.addStyleName("deAboBtn");
		aboniertePW.addStyleName("aboniertePW");
		aboPinnwandInfos.addStyleName("aboPinnwandInfos");
		nutzerNameLabel.addStyleName("nutzerNameLabel");
		nickNameLabel.addStyleName("nickNameLabel");
		
		RootPanel.get("AboBereich").add(abobox);
		
		
//		//for(int i = 0; i < pinnwandVerwaltung.getAllAbosFor(n, null); i++) {
//			AboPinnwandBox tempAboPinnwandBox = new AboPinnwandBox(i);
//			nutzerAbos.add(i, tempAboPinnwandBox);
//		}
		
//		for(int i = 0; i < nutzerAbos.size(); i++) {
//			this.add(nutzerAbos.elementAt(i));
//		}
		
		
	}
	
	//Implementierung: findAllAbosBy, die eine Liste an Abonnementbeziehungen des angemeldetenNutzers liefern,
	// Id Abgleich dieser Liste, ob jeweilige Pinnwand ID bereits dem VP geaddet wurde (if-Abfrage) 
	//Nutzen der Methode: getAllAbosByNutzer
	
	
}
