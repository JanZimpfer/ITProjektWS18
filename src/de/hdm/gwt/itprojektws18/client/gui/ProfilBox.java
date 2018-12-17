package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class ProfilBox extends HorizontalPanel {
	
	private HorizontalPanel ProfilBox = new HorizontalPanel ();
		
	private Button  profilbildButton = new Button ("Mein Profilbild");
	private Button 	profilButton = new Button ("Mein Profil");
	private Label 	beitraege = new Label ();
	private Label 	abonniert = new Label ();
	private Label 	abonnenten = new Label ();
	
	
	public ProfilBox() {
	}
	
	
	
	public void onLoad() {
		
	 this.add(ProfilBox);
	 
	 ProfilBox.add(beitraege);
	 ProfilBox.add(abonniert);
	 ProfilBox.add(abonnenten);
	 
	 ProfilBox.add(profilButton);
	 ProfilBox.add(profilbildButton);
	 
	 profilButton.addClickHandler(new ClickHandler ()  {
		 
		 public void onClick(ClickEvent event) {
			 
		 }
	 }	 
			 );
	 
	 profilbildButton.addClickHandler(new ClickHandler() {
		 
		 public void onClick(ClickEvent event) {
			 
			 
		 		}
	 	}
			 
	);
	 
	 
	 
	
		
	}

}
