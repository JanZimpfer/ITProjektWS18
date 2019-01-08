package de.hdm.gwt.itprojektws18.client.gui;

import java.sql.Timestamp;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Kommentar;

public class KommentarBox extends VerticalPanel{
	
	
	private static PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	
	/*
	 * Elemente zur Darstellung der KommentarBox
	 */
	
	private VerticalPanel kommentarPanel = new VerticalPanel();
	
	private TextArea kommentarEingabe = new TextArea();
	private Label kommentarText = new Label();
	private Label nickname = new Label();
	private Label vorname = new Label();
	private Label nachname = new Label();
	private	Label erstellZeitpunkt = new Label();
	private Label aufforderung = new Label("Sag etwas dazu!");
	
	private Button submitKommentarBtn = new Button("Kommentieren");
	private Button deleteKommentarBtn = new Button("Kommentar l�schen");
	private Button editKommentarBtn = new Button("Kommentar bearbeiten");
	
	BeitragBox bBox = new BeitragBox();
	
	/**
	 * Instantiierung der BusinessObjects die verwendet werden
	 */
	private Kommentar kommentar = null;
	private Beitrag beitrag = null;
	
	
	
	
	
	public KommentarBox() {
		
		}
	
	public KommentarBox(Kommentar k) {
		
	}

	public void onLoad() {
		
		/**
		 * Styling fuer alle KommentarBoxen
		 */
		this.addStyleName("kommentarBox");
		
		/**
		 * Hinzufuegen der Widgets
		 */
		kommentarPanel.add(kommentarEingabe);
		kommentarPanel.add(kommentarText);
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
		kommentarEingabe.addStyleName("gwt-TextArea");
		kommentarText.addStyleName("kommentarTextLabel");
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
			
			Nutzer nutzer = new Nutzer();
			//nutzer.setId(Integer.parseInt(Cookies.getCookie("id")));
			nutzer.setId(1);
			
			Beitrag beitrag = new Beitrag();
			Timestamp erstellzeitpunkt = null;
			//Wie kommt der Beitrag hier rein? 
			pinnwandVerwaltung.erstelleKommentar(beitrag, kommentarEingabe.getText(), erstellzeitpunkt, nutzer, new KommentarAnlegenCallback());
			
		} 
		
	}
	
	/**
	 * <b>Nested Class fuer den Submit-Button</b>
	 * 
	 * Callback Aufruf um einen Kommentar anzulegen
	 */
	public class KommentarAnlegenCallback implements AsyncCallback<Kommentar> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Anlegen des Kommentars: " + caught.getMessage());
		}

		@Override
		public void onSuccess(Kommentar result) {
			Window.alert("Kommentar erfolgreich hinzugefügt!");
			
			Nutzer n = new Nutzer();
			n.setId(result.getNutzerFK());
			
			pinnwandVerwaltung.getNutzerbyID(result.getNutzerFK(), new NutzerInfosCallback());
			kommentarText.setText(result.getText());
			String erstellZPString = ""+result.getErstellZeitpunkt()+"";
			erstellZeitpunkt.setText(erstellZPString);
			/*
			 * TO-DO: Kommentar result wird dann wo hinzugefügt?
			 */
			//TextArea leeren
			kommentarEingabe.setText("");
			
		}
		
	}
	/**
	 * <b>Nested Class fuer den KommentarAnlegenCallback</b>
	 * 
	 * Callback Aufruf um Nutzerinfos abzurufen
	 *
	 */
	class NutzerInfosCallback implements AsyncCallback<Nutzer> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Auslesen der Nutzerinformationen: " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Nutzer result) {
			String nicknameString = "@"+result.getNickname();
			String vornameString = result.getVorname();
			String nachnameString = result.getNachname();
			
			nickname.setText(nicknameString);
			vorname.setText(vornameString);
			nachname.setText(nachnameString);
			
			
		}
		
	}
	
	/**
	 * <b>Nested Class zum deleteKommentar-Button</b>
	 * implementiert den entsprechenden ClickHandler
	 * 
	 * öffnet eine DialogBox (Sicherheitsabfrage)
	 */
	class deleteBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
//			Kommentar k = new Kommentar();
//			//Wie kommt der Kommentar hier rein?
//			// Vergabe der ID?
//			pinnwandVerwaltung.loeschen(k, new KommentarLoeschenCallback());
			
			KommentarLoeschenForm dlgBox = new KommentarLoeschenForm();
			dlgBox.center();
			
		}
	}
	/**
	 * </b>Nested Class einer KommentarLoeschenForm</b>
	 * Abfrage ob der User den Kommentar wirklich löschen möchte
	 */
	 class KommentarLoeschenForm extends DialogBox {
		/**
		 * Instantiierung der notwendigen GUI-Elemente
		 */
		private Label abfrage = new Label(
				"Möchten Sie diesen Kommentar wirklich löschen?");
		private Button jaBtn = new Button("Löschen");
		private Button neinBtn = new Button("Abbrechen");
		private HorizontalPanel btnPanel = new HorizontalPanel();
		
		/**
		 * Aufruf des Konstruktors
		 */
		public KommentarLoeschenForm() {
			jaBtn.addClickHandler(new KommentarLoeschenClickHandler());
			neinBtn.addClickHandler(new KommentarNichtLoeschenClickHandler());
			btnPanel.add(jaBtn);
			btnPanel.add(neinBtn);
			this.add(abfrage);
			this.add(btnPanel);
		}
	}
	 
	 /**
	  * </b>Nested Class in der KommentarLoeschenForm</b>
	  * 
	  * implementiert den entsprechenden ClickHandler
	  */
	 class KommentarLoeschenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			Kommentar k = new Kommentar();
			//Wie kommt der Kommentar hier rein?
			// Vergabe der ID?
			pinnwandVerwaltung.loeschen(k, new KommentarLoeschenCallback());
		}
		 
	 }
	 
	 /**
	  * <b>Nested Class in der KommentarLoeschenForm</b>
	  * 
	  * implementiert den entsprechenden ClickHandler
	  */
	 class KommentarNichtLoeschenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			Window.alert("Kommentar nicht gelöscht.");
		}
		 
	 }
	
	/**
	 * <b>Nested Class fuer den delete-Button</b>
	 * Callback Aufruf um Kommentar zu loeschen
	 */
	public class KommentarLoeschenCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Löschen des Kommentars: " + caught.getMessage());
		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Kommentar erfolgreich gelöscht");
			
		}
		
	}
	
	/**
	 * <b>Nested Class zum editKommentar-Button</b>
	 * implementiert den entsprechenden ClickHandler
	 */
	class editBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			Kommentar k = new Kommentar();
			
			pinnwandVerwaltung.speichern (k, new KommentarBearbeitenCallback());
			
		}
		
	/**
	 * </b>Nested Class fuer den edit-Button</b>
	 * Callback Aufruf um Kommentar zu bearbeiten
	 */
	public class KommentarBearbeitenCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Bearbeiten des Kommentars: " + caught.getMessage());
		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Kommentar erfolgreich bearbeitet.");
			//Neu laden?
			
		}
		
	}
	}
}
