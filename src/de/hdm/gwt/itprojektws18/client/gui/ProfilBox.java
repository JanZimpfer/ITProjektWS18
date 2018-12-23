package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;

public class ProfilBox extends HorizontalPanel {
	
	private HorizontalPanel profilBox = new HorizontalPanel ();
		
	private Button  profilbildButton = new Button ("Mein Profilbild");
	private Button 	profilButton = new Button ("Mein Profil");
	private Label 	beitraege = new Label ("Beitraege: ");
	private Label 	abonniert = new Label ("Abonniert: ");
	private Label 	abonnenten = new Label ("Abonnenten: ");
	
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	ClientsideSettings clientSettings = new ClientsideSettings();
	
	public ProfilBox () {
		
	}
	
	public void onLoad() {
	
		this.addStyleName("profilBox");
		this.add(profilBox);
		
		profilBox.add(profilButton);
		profilBox.add(profilbildButton);
		profilBox.add(profilButton);
		profilBox.add(beitraege);
		profilBox.add(abonniert);
		profilBox.add(abonnenten);
		
		profilbildButton.addStyleName("profilbildButton");
		profilButton.addStyleName("profilButton");
		
		RootPanel.get("header").add(profilBox);
		
		
		
	
	
	
	
	 
	 profilButton.addStyleName("profilButton");
	 
		class eigenesProfilAnzeigen implements ClickHandler {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}
		}
	 
	
		
	}

}
