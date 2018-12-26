package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Abonnement;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;

public class AboPinnwandBox extends FlowPanel{

	
	/**
	 * Erzeugen eines PinnwandVerwaltung-Objekts um eine Applikationsverwaltung zu initialisieren.
	 */
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	ClientsideSettings clientSettings = new ClientsideSettings();
	
	/**
	 * Instanziierung der GUI Elemente
	 */
	private Label nutzerNameLabel = new Label();
	
	private Label nickNameLabel = new Label();
	
	
	/**
	 * Deklarierung der BO, die verwendet werden
	 */
	private Nutzer nutzer = null;
	private Abonnement abo = null;
	private Pinnwand pinnwand = null;
	
	public AboPinnwandBox() {
		
	}
	
	
	public void onLoad() {
		
		
		
		nutzerNameLabel.addStyleName("vornameAbobox");
		nickNameLabel.addStyleName("nicknameAboBox");
		this.addStyleName("aboPinnwandBox");
		
		
		nutzerNameLabel.addClickHandler(new PinnwandAnzeigen());
		nickNameLabel.addClickHandler(new PinnwandAnzeigen());
		
		this.add(nutzerNameLabel);
		this.add(nickNameLabel);
	}
	
	public void updateNutzerNameLabel() {
		Nutzer n = new Nutzer();
		this.nutzerNameLabel.setText(n.getVorname());
		
		//methode bis jetzt falsch, implementierung einer methode getVornameByID() in pinnwandverwaltungimpl 
	}
	
	public void updateNickNameLabel() {
		
	}
	
	
	
	class PinnwandAnzeigen implements ClickHandler{
	
			
			public void onClick(ClickEvent event) {
				
				
			}
			
		}
	
	//Hinzufügen von Methoden, die Methoden der Impl verwenden, um Nick- und Nutzername den jeweilgien Labels hinzuzufügen
	//Id Abgleich in Konstruktor, ob ID(bzw Pinnwand)bereits verwendet wurde bzw. ob Abonnementbeziehung bereits in Abobox angezeigt wird
}
