package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.shared.bo.Kommentar;

public class KommentarBox extends VerticalPanel {

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
		
		kommentarLoeschenBtn.addStyleName("kommentarBtn");
		kommentarBearbeitenBtn.addStyleName("kommentarBtn");

		kommentarPanel.add(nickname);
		kommentarPanel.add(erstellZeitpunkt);
		kommentarPanel.add(kommentarText);

		kommentarBearbeitenBtn.addClickHandler(new KommentarBearbeitenClickHandler());
		kommentarLoeschenBtn.addClickHandler(new KommentarLoeschenClickHandler());

		kommentarBtnPanel.add(kommentarBearbeitenBtn);
		kommentarBtnPanel.add(kommentarLoeschenBtn);

		this.add(kommentarPanel);
		this.add(kommentarBtnPanel);

		super.onLoad();

	}

	/**
	 * Methode zum Setzen des Autors eines Kommentars
	 * @param name
	 */
	public void befuelleNicklabel(String name) {

		this.nickname.setText(name);

	}
	
	/**
	 * Methode zum Setzen des Erstellzeitpunkts eines Kommentars
	 * @param erstellzeitpunkt
	 */
	public void befuelleErstellzeitpunkt(String erstellzeitpunkt) {

		this.erstellZeitpunkt.setText(erstellzeitpunkt);

	}

	/**
	 * Methode zum Setzen des Kommentarinhalts
	 * @param inhalt
	 */
	public void befuelleInhalt(String inhalt) {

		this.kommentarText.setText(inhalt);
	}

	/**
	 * <b>Nested Class für den Bearbeiten-Button</b>
	 * implementiert den entsprechenden ClickHandler
	 * 
	 * Sobald ein ClickEvent empfangen wird
	 * öffnet sich eine KommentarBearbeitenDialogBox
	 */
	class KommentarBearbeitenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			KommentarBearbeitenDialogBox dlgbox= new KommentarBearbeitenDialogBox(kommentar);
			dlgbox.center();

		}

	}

	/**
	 * <b>Nested Class für den Löschen-Button</b>
	 * implementiert den entsprechenden ClickHandler
	 * 
	 * Sobald ein ClickEvent empfangen wird
	 * öffnet sich eine KommentarLoeschenDialogBox
	 */
	class KommentarLoeschenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			KommentarLoeschenDialogBox loeschenBox = new KommentarLoeschenDialogBox(kommentar);
			loeschenBox.center();
		}
	}
}
