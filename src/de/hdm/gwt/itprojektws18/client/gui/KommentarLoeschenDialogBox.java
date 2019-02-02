package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Kommentar;

public class KommentarLoeschenDialogBox extends DialogBox {
	
	/**
	 * Instanziierung eines PinnwandVerwaltung-Objekts um eine
	 * Applikationsverwaltung zu initialisieren
	 */
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	/**
	 * Deklarierung des Business Object das verwendet wird
	 */
	Kommentar kommentar = new Kommentar();

	/**
	 * Erstellung der benötigten GUI-Elemente
	 */
	private VerticalPanel vPanel = new VerticalPanel();
	private VerticalPanel abfragePanel = new VerticalPanel();
	private HorizontalPanel btnPanel = new HorizontalPanel();
	private Label abfrage = new Label("Möchten Sie diesen Kommentar wirklich löschen?");
	private Button loeschenBtn = new Button("Ja");
	private Button schliessenBtn = new Button("Nein");

	public KommentarLoeschenDialogBox() {

	}

	public KommentarLoeschenDialogBox(Kommentar k) {

		this.kommentar = k;
		
		this.setText("Kommentar löschen");
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.setAutoHideEnabled(true);

		this.setStylePrimaryName("customDialogbox");
		
		loeschenBtn.addClickHandler(new KommentarLoeschenClickHandler());
		schliessenBtn.addClickHandler(new SchliessenClickHandler());

		abfragePanel.add(abfrage);
		btnPanel.add(loeschenBtn);
		btnPanel.add(schliessenBtn);

		vPanel.add(abfragePanel);
		vPanel.add(btnPanel);
		this.add(vPanel);
		
		super.onLoad();

	}

	/**
	 * <b>Nested Class für den loeschen-Button</b>
	 * implementiert den entsprechenden ClickHandler
	 * 
	 */
	class KommentarLoeschenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			pinnwandVerwaltung.loeschen(kommentar, new KommentarLoeschenCallback());

		}
	}

	/**
	 * <b>Nested Class für den loeschen-Button</b>
	 * Callback Aufruf zum Loeschen eines Kommentars
	 *
	 */
	class KommentarLoeschenCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Löschen des Kommentars: " + caught.getMessage());

		}

		@Override
		public void onSuccess(Void result) {
			hide();
			
			pinnwandVerwaltung.getBeitragByID(kommentar.getBeitragFK(), new AsyncCallback<Beitrag>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Fehler beim Abruf der Beitragsinformationen: " + caught.getMessage());
					
				}

				@Override
				public void onSuccess(Beitrag result) {
					
					PinnwandBox pBox = new PinnwandBox(result.getNutzerFK());

					RootPanel.get("InhaltDiv").clear();
					RootPanel.get("InhaltDiv").add(pBox);
					
				}
				
			});
			

		}
	}

	/**
	 * <b>Nested Class für den schließen-Button</b>
	 * implementiert den entsprechenden ClickHandler
	 * 
	 * Die DialogBox wird geschlossen
	 *
	 */
	class SchliessenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			hide();

		}
	}
}
