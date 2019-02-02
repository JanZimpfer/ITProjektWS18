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

public class BeitragLoeschenDialogBox extends DialogBox {

	/**
	 * Instanziierung eines PinnwandVerwaltung-Objekts um eine
	 * Applikationsverwaltung zu initialisieren
	 */
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	/**
	 * Deklarierung des Business Object das verwendet wird
	 */
	Beitrag beitrag = new Beitrag();

	/**
	 * Erstellung der benötigten GUI-Elemente
	 */
	private VerticalPanel vPanel = new VerticalPanel();
	private VerticalPanel abfragePanel = new VerticalPanel();
	private HorizontalPanel btnPanel = new HorizontalPanel();
	private Label abfrage = new Label("Möchten Sie diesen Beitrag wirklich löschen?");
	private Button loeschenBtn = new Button("Ja");
	private Button schliessenBtn = new Button("Nein");

	public BeitragLoeschenDialogBox() {

	}

	public BeitragLoeschenDialogBox(Beitrag b) {

		this.beitrag = b;

		this.setText("Beitrag löschen");
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.setAutoHideEnabled(true);

		this.setStylePrimaryName("customDialogbox");

		loeschenBtn.addClickHandler(new BeitragLoeschenClickHandler());
		schliessenBtn.addClickHandler(new SchliessenClickHandler());

		abfragePanel.add(abfrage);
		btnPanel.add(loeschenBtn);
		btnPanel.add(schliessenBtn);

		vPanel.add(abfragePanel);
		vPanel.add(btnPanel);
		this.add(vPanel);

	}

	/**
	 * <b>Nested Class für den loeschen-Button</b> implementiert den entsprechenden
	 * ClickHandler
	 * 
	 */
	class BeitragLoeschenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			pinnwandVerwaltung.loeschen(beitrag, new BeitragLoeschenCallback());

		}
	}

	/**
	 * <b>Nested Class für den loeschen-Button</b> Callback Aufruf zum Loeschen
	 * eines Beitrags
	 *
	 */
	class BeitragLoeschenCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Löschen des Beitrags: " + caught.getMessage());

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
	 * <b>Nested Class für den schließen-Button</b> implementiert den entsprechenden
	 * ClickHandler
	 *
	 */
	class SchliessenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			hide();

		}
	}
}