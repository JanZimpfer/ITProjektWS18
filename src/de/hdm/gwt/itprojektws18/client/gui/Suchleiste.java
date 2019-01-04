package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;

public class Suchleiste extends HorizontalPanel {
	
	private HorizontalPanel suchleiste = new HorizontalPanel ();
	private MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	private Button sucheButton = new Button ("Profil anzeigen");
	private SuggestBox textfeld = new SuggestBox (oracle);
	

	
	public Suchleiste () {
		
	suchleiste.setSpacing(2);
	suchleiste.setCellHorizontalAlignment(suchleiste, ALIGN_LEFT);
	suchleiste.setCellHorizontalAlignment(sucheButton, ALIGN_RIGHT);
	
	
		oracle.add(" Flo ");
		oracle.add(" Nik ");
		oracle.add(" Matthias ");
		oracle.add(" Marko ");
		oracle.add(" Ayse ");
		oracle.add(" Jan ");
		
	
	
	}
	
class ProfilAnzeigen implements ClickHandler {

		
		public void onClick(ClickEvent event) {
			
		Window.Location.assign("http://127.0.0.1:8888/ITProjektWS18.html");
		

		}
	}
	
	public void onLoad() {
	
		
	this.addStyleName("suchleiste");
	this.add(suchleiste);
	
	sucheButton.addClickHandler(new ProfilAnzeigen ());
	
	suchleiste.add(textfeld);
	suchleiste.add(sucheButton);
	
	
	
	
	sucheButton.addStyleName("suchButton");
	textfeld.addStyleName("suchleiste");
	
	RootPanel.get("SuchProfilLogout").add(suchleiste);
		
	}

}
