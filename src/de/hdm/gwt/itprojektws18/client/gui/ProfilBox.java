package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;

public class ProfilBox extends VerticalPanel {
	
	
	private VerticalPanel profilbox = new VerticalPanel ();
	private HorizontalPanel buttonPanel  = new HorizontalPanel ();	
	
	
	private HorizontalPanel labelPanel  = new HorizontalPanel ();
	
	
	private Button  profilbildButton = new Button ("Profilbild");
	private Button 	profilButton = new Button ("Profil");
	
	private Label 	beitraege = new Label ("Beitraege: ");
	private Label 	abonniert = new Label ("Abonniert: ");
	private Label 	abonnenten = new Label ("Abonnenten: ");
	
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	ClientsideSettings clientSettings = new ClientsideSettings();
	
	public ProfilBox () {
		buttonPanel.setSpacing(2);
		labelPanel.setSpacing(3);
	}
	
	public void onLoad() {
	
		this.addStyleName("profilBox");
		this.add(profilbox);
		
		profilbox.add(buttonPanel);
		profilbox.add(labelPanel);
		
		buttonPanel.add(profilbildButton);
		buttonPanel.add(profilButton);
		
		
		labelPanel.add(beitraege);
		labelPanel.add(abonniert);
		labelPanel.add(abonnenten);
		
		profilbildButton.addStyleName("profilbildButton");
		profilButton.addStyleName("profilButton");
		
		RootPanel.get("header").add(profilbox);
		
		
		
	
	
	
	
	 
	 profilButton.addStyleName("profilButton");
	 
	 
	profilbildButton.addClickHandler(new eigenesProfilAnzeigen());
	profilButton.addClickHandler(new eigenesProfilAnzeigen ());
	 
	
		
	}
	
	class eigenesProfilAnzeigen implements ClickHandler {

		
		public void onClick(ClickEvent event) {
			
		Window.Location.assign("http://127.0.0.1:8888/ITProjektWS18.html");
		

		}
	}
 

}
