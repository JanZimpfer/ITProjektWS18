package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class KommentarBox extends ScrollPanel{
	
	/*
	 * Elemente zur Darstellung der KommentarBox
	 */
	
	private VerticalPanel kommentarPanel = new VerticalPanel();
	
	private TextArea kommentarInhalt = new TextArea();
	private Label nickname = new Label();
	private Label vorname = new Label();
	private Label nachname = new Label();
	private	Label erstellZeitpunkt = new Label();
	
	private Button submitKommentarBtn = new Button("Kommentieren");
	private Button deleteKommentarBtn = new Button("Kommentar löschen");
	
	
	
	
	
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
		kommentarPanel.add(deleteKommentarBtn);
		
		//Styling hinzufuegen
		kommentarInhalt.addStyleName("gwt-TextArea");
		submitKommentarBtn.addStyleName("submitButton");
		deleteKommentarBtn.addStyleName("submitButton");
		
		
		submitKommentarBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		deleteKommentarBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
}
