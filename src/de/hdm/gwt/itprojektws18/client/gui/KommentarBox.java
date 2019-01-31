package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.shared.bo.Kommentar;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class KommentarBox extends VerticalPanel {

	/*
	 * Elemente zur Darstellung der KommentarBox
	 */

	private VerticalPanel kommentarPanel = new VerticalPanel();
	private HorizontalPanel kommentarBtnPanel = new HorizontalPanel();
	private HorizontalPanel nutzerZeitPanel = new HorizontalPanel();

	private TextArea kommentarText = new TextArea();
	private Label nickname = new Label();
	private Label erstellZeitpunkt = new Label();

	private Button kommentarLoeschenBtn = new Button("Kommentar löschen");

	private Button kommentarBearbeitenBtn = new Button("Kommentar bearbeiten");

	private Kommentar kommentar = new Kommentar();

	public KommentarBox() {

	}

	public KommentarBox(final Kommentar k) {

		this.kommentar = k;
		Nutzer n = new Nutzer();
		n.setId(Integer.parseInt(Cookies.getCookie("id")));

		this.addStyleName("kommentarBox");
//		kommentarText.addStyleName("kommentarText");

		kommentarText.setStylePrimaryName("beitragInhalt");
		kommentarText.setReadOnly(true);

		kommentarLoeschenBtn.addStyleName("kommentarBtn");
		kommentarBearbeitenBtn.addStyleName("kommentarBtn");

		kommentarPanel.add(nutzerZeitPanel);
		kommentarPanel.add(kommentarText);

		nutzerZeitPanel.add(nickname);
		nutzerZeitPanel.add(erstellZeitpunkt);

		if (n.getId() == k.getNutzerFK()) {
			kommentarBtnPanel.add(kommentarBearbeitenBtn);
			kommentarBtnPanel.add(kommentarLoeschenBtn);
			kommentarBearbeitenBtn.addClickHandler(new KommentarBearbeitenClickHandler());
			kommentarLoeschenBtn.addClickHandler(new KommentarLoeschenClickHandler());
		}

		this.add(kommentarPanel);
		this.add(kommentarBtnPanel);

		super.onLoad();

	}

	/**
	 * Methode zum Setzen des Autors eines Kommentars
	 * 
	 * @param name
	 */
	public void befuelleNicklabel(String name) {

		this.nickname.setText(name);

	}

	/**
	 * Methode zum Setzen des Erstellzeitpunkts eines Kommentars
	 * 
	 * @param erstellzeitpunkt
	 */
	public void befuelleErstellzeitpunkt(String erstellzeitpunkt) {

		this.erstellZeitpunkt.setText(erstellzeitpunkt);

	}

	/**
	 * Methode zum Setzen des Kommentarinhalts
	 * 
	 * @param inhalt
	 */
	public void befuelleInhalt(String inhalt) {

		this.kommentarText.setText(inhalt);
	}

	/**
	 * <b>Nested Class für den Bearbeiten-Button</b> implementiert den
	 * entsprechenden ClickHandler
	 * 
	 * Sobald ein ClickEvent empfangen wird öffnet sich eine
	 * KommentarBearbeitenDialogBox
	 */
	class KommentarBearbeitenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			KommentarBearbeitenDialogBox dlgbox = new KommentarBearbeitenDialogBox(kommentar);
			dlgbox.center();

		}

	}

	/**
	 * <b>Nested Class für den Löschen-Button</b> implementiert den entsprechenden
	 * ClickHandler
	 * 
	 * Sobald ein ClickEvent empfangen wird öffnet sich eine
	 * KommentarLoeschenDialogBox
	 */
	class KommentarLoeschenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			KommentarLoeschenDialogBox loeschenBox = new KommentarLoeschenDialogBox(kommentar);
			loeschenBox.center();
		}
	}
}
