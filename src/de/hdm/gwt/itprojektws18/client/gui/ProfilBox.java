package de.hdm.gwt.itprojektws18.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Abonnement;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class ProfilBox extends HorizontalPanel {
	
	
	private HorizontalPanel profilbox = new HorizontalPanel ();
	private HorizontalPanel buttonPanel  = new HorizontalPanel ();	
	
	
	private HorizontalPanel labelPanel  = new HorizontalPanel ();
	
	
	private Button  profilbildButton = new Button ("Profilbild");
	private Button 	profilButton = new Button ("Profil");
	
	private Label 	beitraege = new Label ("0");
	private Label 	abonniert = new Label ("0");
//	private Label 	abonnenten = new Label ("Abonnenten: ");
	
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	ClientsideSettings clientSettings = new ClientsideSettings();
	
	public ProfilBox () {
		buttonPanel.setSpacing(2);
		labelPanel.setSpacing(3);
	}
	
	
	public void befuelleBeitraege (String anzahlBeitraege) {
		
		this.beitraege.setText(anzahlBeitraege);
	}
	
public void  befuelleAbonnierte (String anzahlAbonnierte) {
		
		this.abonniert.setText(anzahlAbonnierte);
	}
	
//public void befuelleAbonnenten (String anzahlAbonnenten) {
//	
//		this.abonnenten.setText(anzahlAbonnenten);
//}
	
	public void onLoad() {
	
		this.addStyleName("profilBox");
		this.add(profilbox);
		
		profilbox.add(buttonPanel);
		profilbox.add(labelPanel);
		
		buttonPanel.add(profilbildButton);
		buttonPanel.add(profilButton);
		
		
		labelPanel.add(beitraege);
		labelPanel.add(abonniert);
//		labelPanel.add(abonnenten);
		
		profilbildButton.addStyleName("profilbildButton");
		profilButton.addStyleName("profilButton");
		
		RootPanel.get("SuchProfilLogout").add(profilbox);
		
		
		
	
	
	
	
	 
	 profilButton.addStyleName("profilButton");
	 
	 
	profilbildButton.addClickHandler(new eigenesProfilAnzeigen());
	
	
	profilButton.addClickHandler(new updateLabels ());

	
	
		
	}
	
	class eigenesProfilAnzeigen implements ClickHandler {

		
		public void onClick(ClickEvent event) {
			
		Window.Location.assign("http://127.0.0.1:8888/ITProjektWS18.html");
		

		}
	}
 
	
	class updateLabels implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
		Nutzer  n = new Nutzer ();
		
		Beitrag b = new Beitrag ();
		
//		nutzer.setId(Integer.parseInt(Cookies.getCookie("id")));
		
		n.setId(1);
		
		
		
			
		pinnwandVerwaltung.getAllAbosFor(n, new anzahlAbosCallback ());
		pinnwandVerwaltung.getAllBeitraegeByNutzer(n, new anzahlBeitragCallback ());
		
		}
		
		public class anzahlAbosCallback implements AsyncCallback <Vector<Abonnement>>{

			@Override
			public void onFailure(Throwable caught) {
				
				Window.alert("Fehler beim z�hlen der Abos: " + caught.getMessage());
			}


			@Override
			public void onSuccess(Vector<Abonnement> result) {
				
				String anzahlAbos = "Abonniert: "+ result.size() +"";
				
				abonniert.setText(anzahlAbos);
				
				
			}
			
			
		}
		
		
		public class anzahlBeitragCallback implements AsyncCallback <Vector<Beitrag>> {

			
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim z�hlen der Beitraege: " + caught.getMessage());
				
			}

		
			public void onSuccess(Vector<Beitrag> result) {
				
				String anzahlBeitraege = "Anzahl der Beitraege: " + result.size() + "";
				
				beitraege.setText(anzahlBeitraege);
				
			}
			
			
		}
		
	}
		
		
	
	

}
