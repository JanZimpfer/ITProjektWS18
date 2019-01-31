package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;

public class BeitragBearbeitenDialogBox extends DialogBox {

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
	private VerticalPanel editPanel = new VerticalPanel();
	private HorizontalPanel btnPanel = new HorizontalPanel();
	private TextBox beitragText = new TextBox();
	private Button speichernBtn = new Button("Speichern");
	private Button schliessenBtn = new Button("Schliessen");

	public BeitragBearbeitenDialogBox() {

	}

	public BeitragBearbeitenDialogBox(Beitrag b) {
		
		this.beitrag = b;
		
		this.setText("Beitrag bearbeiten");
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.setAutoHideEnabled(true);
		
		this.setStylePrimaryName("customDialogbox");

		beitragText.setText(b.getText());

		speichernBtn.addClickHandler(new BeitragAendernClickHandler());
		schliessenBtn.addClickHandler(new SchliessenClickHandler());

		editPanel.add(beitragText);
		btnPanel.add(speichernBtn);
		btnPanel.add(schliessenBtn);
		vPanel.add(editPanel);
		vPanel.add(btnPanel);

		this.add(vPanel);
	}

	/**
	 * <b>Nested Class für den speichern-Button</b> 
	 * implementiert den entsprechenden ClickHandler
	 */
	class BeitragAendernClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			beitrag.setText(beitragText.getText());

			pinnwandVerwaltung.speichern(beitrag, new BeitragSpeichernCallback());

		}
	}

	/**
	 * </b>Nested Class für den speichern-Buttton</b> 
	 * Callback Aufruf zum Speichern der Änderungen an einem Beitrag
	 */
	class BeitragSpeichernCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Bearbeiten des Beitrags: " + caught.getMessage());

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
	 * <b>Nested Class für den schliessen-Button</b> 
	 * implementiert den entsprechenden ClickHandler
	 * 
	 * Die DialogBox wird geschlossen
	 */
	class SchliessenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			hide();

		}

	}
}