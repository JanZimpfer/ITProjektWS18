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

public class ProfilBox extends VerticalPanel {
	
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	
	private HorizontalPanel buttonPanel  = new HorizontalPanel ();	
	private VerticalPanel labelPanel  = new VerticalPanel ();
	
	private Button  profilbildButton = new Button ("Profilbild");
	private Button 	profilButton = new Button ("Startseite");
	
	private Label 	beitraege = new Label ();
	private Label 	abonniert = new Label ();
	private Label   abonnenten = new Label ();
	
	Nutzer nutzer = new Nutzer ();
	
	

	public ProfilBox () {
	
		
		
	
//		nutzer.setId(Integer.parseInt(Cookies.getCookie("id")));
		nutzer.setId(3);
		
		this.addStyleName("profilBox");
		
		buttonPanel.add(profilbildButton);
		buttonPanel.add(profilButton);
	
		labelPanel.add(beitraege);
		labelPanel.add(abonniert);
		labelPanel.add(abonnenten);

		profilbildButton.addStyleName("profilbildButton");
		profilButton.addStyleName("profilButton");
		profilButton.addStyleName("profilButton");
		
		profilButton.addClickHandler(new eigenesProfilAnzeigen ());
	 
		pinnwandVerwaltung.getAllAbosFor(nutzer, new anzahlAbosCallback ());
		pinnwandVerwaltung.getAllBeitraegeByNutzer(nutzer, new anzahlBeitragCallback ());

		this.add(buttonPanel);
		this.add(labelPanel);
		
		beitraege.setHorizontalAlignment(ALIGN_LEFT);
		abonniert.setHorizontalAlignment(ALIGN_LEFT);
		
		
		super.onLoad();
	
	
		
	}
	
	class eigenesProfilAnzeigen implements ClickHandler {

		
		public void onClick(ClickEvent event) {
			
		Window.Location.assign("http://127.0.0.1:8888/ITProjektWS18.html");
		

		}
	}
 
		
		class anzahlAbosCallback implements AsyncCallback <Vector<Abonnement>>{

			@Override
			public void onFailure(Throwable caught) {
				
				Window.alert("Fehler beim zählen der Abos: " + caught.getMessage());
			}


			@Override
			public void onSuccess(Vector<Abonnement> result) {
				
				String anzahlAbos = "Abonniert: "+ result.size() +"";
				
				String aZ = "Anzahl Abonnenten: ";
				
				abonnenten.setText(aZ);	
				
				Vector<Abonnement> anzahlabonnenten = new Vector <Abonnement> ();
				
				abonniert.setText(anzahlAbos);
			
				for (int i=0; i< result.size(); i++) {
					
					if (nutzer.getId() == result.elementAt(i).getPinnwandFK()) {
		
						anzahlabonnenten.addAll(result);
				
//						String aZ = "Anzahl Abonnenten: " + anzahlabonnenten.size() + "";
				
									
					
				}
				
				
			}
			
			
			}
			
		}
		
		
		class anzahlBeitragCallback implements AsyncCallback <Vector<Beitrag>> {

			
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim zählen der Beitraege: " + caught.getMessage());
				
			}

		
			public void onSuccess(Vector<Beitrag> result) {
				
				String anzahlBeitraege = "Anzahl der Beitraege: " + result.size() + "";
				
				beitraege.setText(anzahlBeitraege);
				
			}
			
			
		}
		
	}

		
		
	
	
