package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;

public class KommentarBox extends ScrollPanel{
	
	
	private static PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	
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
	private Button editKommentarBtn = new Button("Kommentar bearbeiten");
	
	
	
	
	
	public KommentarBox() {
	
	}

	public void onLoad() {
		
		/**
		 * Styling fuer alle KommentarBoxen
		 */
		this.addStyleName("kommentarBox");
		
		/**
		 * Hinzufuegen der Widgets
		 */
		kommentarPanel.add(kommentarInhalt);
		kommentarPanel.add(nickname);
		kommentarPanel.add(vorname);
		kommentarPanel.add(nachname);
		kommentarPanel.add(erstellZeitpunkt);

		/**
		 * Hinzufuegen der ClickHandler zu den Buttons
		 */
		submitKommentarBtn.addClickHandler(new submitBtnClickHandler());
		editKommentarBtn.addClickHandler(new editBtnClickHandler());
		deleteKommentarBtn.addClickHandler(new deleteBtnClickHandler());
		
		/**
		 * Hinzufuegen des Stylings
		 */
		kommentarInhalt.addStyleName("gwt-TextArea");
		submitKommentarBtn.addStyleName("submitButton");
		deleteKommentarBtn.addStyleName("submitButton");
		editKommentarBtn.addStyleName("submitButton");
		
		
		/**
		 * Hinzufuegen der Buttons zum Panel
		 */
		kommentarPanel.add(submitKommentarBtn);
		kommentarPanel.add(deleteKommentarBtn);
		kommentarPanel.add(editKommentarBtn);
		
		
		
	}
	
	/**
	 * <b>Nested Class zum submitKommentar-Button</b>
	 * implementiert den entsprechenden ClickHandler
	 */
	class submitBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		} 
		
	}
	
	/**
	 * <b>Nested Class zum deleteKommentar-Button</b>
	 * implementiert den entsprechenden ClickHandler
	 */
	class deleteBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
	}
	
	/**
	 * <b>Nested Class zum editKommentar-Button</b>
	 * implementiert den entsprechenden ClickHandler
	 */
	class editBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
