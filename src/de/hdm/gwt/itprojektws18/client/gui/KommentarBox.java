package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class KommentarBox extends FlowPanel{
	
	/*
	 * Elemente zur Darstellung der KommentarBox
	 */
	
	private VerticalPanel kommentarPanel = new VerticalPanel();
	
	private Widget kommentarInhalt = new Widget();
	private Widget nickname = new Widget();
	private Widget vorname = new Widget();
	private Widget nachname = new Widget();
	private Widget erstellZeitpunkt = new Widget();
	
	private Button submitKommentarBtn = new Button("Kommentieren");
	
	
	
	
	
	public KommentarBox() {
	
	}

	public void onLoad() {
		
		//Stylename fuer alle KommentarBoxen
		this.addStyleName("kommentarBox");
		
		//Hinzufuegen der Widgets
		kommentarPanel.add(kommentarInhalt);
		kommentarPanel.add(nickname);
		kommentarPanel.add(vorname);
		kommentarPanel.add(nachname);
		kommentarPanel.add(erstellZeitpunkt);
		
		//Buttons hinzufuegen
		kommentarPanel.add(submitKommentarBtn);
		
		//Styling hinzufuegen
		submitKommentarBtn.addStyleName("submitButton");
		
	}
}
