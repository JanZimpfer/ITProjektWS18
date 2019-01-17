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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.client.gui.BeitragBox.BeitragBearbeitenClickHandler;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Kommentar;

public class KommentarBox extends VerticalPanel {

	private static PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	/*
	 * Elemente zur Darstellung der KommentarBox
	 */

	private VerticalPanel kommentarPanel = new VerticalPanel();
	private HorizontalPanel kommentarBtnPanel = new HorizontalPanel();

	private TextBox kommentarText = new TextBox();
	private Label nickname = new Label();
	private Label erstellZeitpunkt = new Label();

	private Button kommentarLoeschenBtn = new Button("Kommentar löschen");

	private Button kommentarBearbeitenBtn = new Button("Kommentar bearbeiten");

	private Kommentar kommentar = new Kommentar();

	public KommentarBox() {

	}

	public KommentarBox(final Kommentar k) {

		this.kommentar = k;
		
		this.addStyleName("kommentarBox");
//		kommentarText.addStyleName("kommentarText");

		kommentarText.setStylePrimaryName("beitragInhalt");
		kommentarText.setReadOnly(true);

		kommentarPanel.add(nickname);
		kommentarPanel.add(erstellZeitpunkt);
		kommentarPanel.add(kommentarText);

		kommentarBearbeitenBtn.addClickHandler(new KommentarBearbeitenClickHandler());

		kommentarBtnPanel.add(kommentarBearbeitenBtn);
		kommentarBtnPanel.add(kommentarLoeschenBtn);

		this.add(kommentarPanel);
		this.add(kommentarBtnPanel);

		super.onLoad();

	}

	public void befuelleNicklabel(String nicknameString) {

		this.nickname.setText(nicknameString);

	}

	public void befuelleErstellzeitpunkt(String erstellzeitpunkt) {

		this.erstellZeitpunkt.setText(erstellzeitpunkt);

	}

	public void befuelleInhalt(String inhalt) {

		this.kommentarText.setText(inhalt);
	}

	class KommentarBearbeitenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			KommentarBearbeitenDialogBox dlgbox= new KommentarBearbeitenDialogBox(kommentar);
			dlgbox.center();

		}

	}


	/**
	 * <b>Nested Class zum deleteKommentar-Button</b> implementiert den
	 * entsprechenden ClickHandler
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
	 * </b>Nested Class einer KommentarLoeschenForm</b> Abfrage ob der User den
	 * Kommentar wirklich löschen möchte
	 */
	class KommentarLoeschenForm extends DialogBox {
		/**
		 * Instantiierung der notwendigen GUI-Elemente
		 */
		private Label abfrage = new Label("Möchten Sie diesen Kommentar wirklich löschen?");
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
			// Wie kommt der Kommentar hier rein?
			// Vergabe der ID?
//			pinnwandVerwaltung.loeschen(k, new KommentarLoeschenCallback());
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
	 * <b>Nested Class fuer den delete-Button</b> Callback Aufruf um Kommentar zu
	 * loeschen
	 */
	class KommentarLoeschenCallback implements AsyncCallback<Void> {

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
	 * <b>Nested Class zum editKommentar-Button</b> implementiert den entsprechenden
	 * ClickHandler
	 */
	class editBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

//			Kommentar k = new Kommentar();

//			pinnwandVerwaltung.speichern (k, new KommentarBearbeitenCallback());

		}

	}
}
