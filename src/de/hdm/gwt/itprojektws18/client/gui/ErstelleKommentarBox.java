package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class ErstelleKommentarBox extends HorizontalPanel {

	
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	ClientsideSettings clientSettings = new ClientsideSettings();
	
	/**
	 * Elemente zur Darstellung der ErstelleKommentar-Box
	 */
	private HorizontalPanel erstelleKommentarPanel = new HorizontalPanel();
	private TextArea eingabeFeld = new TextArea();
	
	private Button submitKommentarBtn = new Button("Kommentieren");
	
	
	
	public ErstelleKommentarBox() {
		
	}
	
	public void onLoad() {
		/**
		 * Styling fuer alle ErstelleKommentar-Boxen
		 */
		this.addStyleName("erstelleKommentarBox");
		
		/**
		 * Widgets hinzufuegen
		 */
		this.add(eingabeFeld);
		
		/**
		 * ClickHandler zum submitKommentar-Button hinzufuegen
		 */
		submitKommentarBtn.addClickHandler(new submitBtnClickHandler());
		
		/**
		 * Button zur ErstelleKommentar-Box hinzufuegen
		 */
		this.add(submitKommentarBtn);
		
		/**
		 * Hinzufuegen zum RootPanel
		 */
		RootPanel.get("BeitragDiv").add(this);
	}
	
	
	/**
	 * <b>Nested Class zum submitKommentar-Button</b>
	 * implementiert den entsprechenden ClickHandler
	 */
	class submitBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			Nutzer n = new Nutzer();
			n.setId(Integer.parseInt((Cookies.getCookie("id"))));
			
			
			
			
		}
		
	}
	
}
