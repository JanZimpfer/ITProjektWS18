package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Kommentar;

/**
 * Klasse zur Erstellung einer DialogBox, die die Bearbeitung
 * des Texts eines Kommentars und die Speicherung des
 * bearbeiteten Texts ermöglicht.
 *
 */

public class KommentarBearbeitenDialogBox extends DialogBox {

	/**
	 * Instanziierung eines PinnwandVerwaltung-Objekts um eine
	 * Applikationsverwaltung zu initialisieren
	 */
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	/**
	 * Deklarierung des Business Object, welches verwendet wird
	 */
	Kommentar kommentar = new Kommentar();

	/**
	 * Instanziierung der GUI-Elemente
	 **/
	private VerticalPanel editPanel = new VerticalPanel();
	private TextBox kommentarText = new TextBox();
	private Button speichernBtn = new Button("Speichern");
	private Button schliessenBtn = new Button("Schliessen");

	public KommentarBearbeitenDialogBox() {

	}

	public KommentarBearbeitenDialogBox(Kommentar k) {
		this.kommentar = k;
		
		this.setText("Kommentar bearbeiten");
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.setAutoHideEnabled(true);
		
		kommentarText.setText(k.getText());

		/**
		 * Hinzufügen eines ClickHandlers auf den Button, um den bearbeiteten
		 * Kommentarinhalt zu speichern
		 */
		speichernBtn.addClickHandler(new KommentarAendernClickHandler());

		/**
		 * Hinzufügen eines ClickHandlers auf den Button, um die DialogBox für die
		 * Bearbeitung des Kommentars zu schließen
		 */
		schliessenBtn.addClickHandler(new SchliessenClickHandler());

		editPanel.add(kommentarText);
		editPanel.add(speichernBtn);
		editPanel.add(schliessenBtn);

		this.add(editPanel);
	}

	
	/**
	 * Nested Class für das Bearbeiten des Kommentartextes, implementiert das
	 * ClickHandler Interface
	 *
	 */
	class KommentarAendernClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			kommentar.setText(kommentarText.getText());

			pinnwandVerwaltung.speichern(kommentar, new KommentarSpeichernCallback());

		}
	}

	
	/**
	 * Nested Class für das Speichern des überarbeiteten Kommentars
	 *
	 */
	class KommentarSpeichernCallback implements AsyncCallback<Void> {

		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Bearbeiten der Kommentars: " + caught.getMessage());

		}

		@Override
		public void onSuccess(Void result) {
			hide();

			PinnwandBox pBox = new PinnwandBox();
			RootPanel.get("InhaltDiv").clear();
			RootPanel.get("InhaltDiv").add(pBox);

		}
	}

	
	/**
	 * Nested Class für das Schließen der DialogBox zum Bearbeiten des Kommentares,
	 * implementiert das ClickHandler Interface
	 *
	 */
	class SchliessenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			hide();

		}

	}

}
