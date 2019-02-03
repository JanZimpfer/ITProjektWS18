package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class ProfilBearbeitenDialogBox extends DialogBox {

	/**
	 * Instanziierung eines PinnwandVerwaltung-Objekts um eine
	 * Applikationsverwaltung zu initialisieren
	 */
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	Nutzer nutzer = new Nutzer();

	/**
	 * Deklarierung der benötigten Widgets
	 */
	private VerticalPanel vPanel = new VerticalPanel();
	private VerticalPanel editPanel = new VerticalPanel();
	private HorizontalPanel btnPanel = new HorizontalPanel();

	private Label vornameLbl = new Label("Neuer Vorname: ");
	private Label nachnameLbl = new Label("Neuer Nachname: ");
	private Label meldungLbl = new Label();

	private TextBox vornameTextbox = new TextBox();
	private TextBox nachnameTextbox = new TextBox();

	private Button changeButton = new Button("Änderungen speichern");
	private Button closeButton = new Button("Schließen");

	public ProfilBearbeitenDialogBox() {

	}

	public ProfilBearbeitenDialogBox(Nutzer n) {
		this.nutzer = n;

		this.setText("Profil bearbeiten");
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.setAutoHideEnabled(true);

		this.setStylePrimaryName("customDialogbox");

		nutzer.setId(Integer.parseInt(Cookies.getCookie("id")));


		vornameTextbox.setText(n.getVorname());
		nachnameTextbox.setText(n.getNachname());

		changeButton.addClickHandler(new ProfilEditClickHandler());
		closeButton.addClickHandler(new CloseClickHandler());

		editPanel.add(meldungLbl);
		editPanel.add(vornameLbl);
		editPanel.add(vornameTextbox);
		editPanel.add(nachnameLbl);
		editPanel.add(nachnameTextbox);
		btnPanel.add(changeButton);
		btnPanel.add(closeButton);

		vPanel.add(editPanel);
		vPanel.add(btnPanel);
		this.add(vPanel);
	}

	/**
	 * <b>Nested Class für den Speichern-Button</b> implementiert den ClickHandler
	 * zum Speichern des bearbeiteten Nutzers
	 *
	 */
	class ProfilEditClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			nutzer.setVorname(vornameTextbox.getText());
			nutzer.setNachname(nachnameTextbox.getText());

			pinnwandVerwaltung.speichern(nutzer, new NutzerEditCallback());

		}

		/**
		 * <b>Nested Class für den Speichern-Button</b> Callback Aufruf zum Speichern
		 * des bearbeiteten Nutzers
		 *
		 */
		public class NutzerEditCallback implements AsyncCallback<Nutzer> {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim Bearbeiten des Nutzers: " + caught.getMessage());

			}

			@Override
			public void onSuccess(Nutzer result) {

					hide();
					PinnwandBox pBox = new PinnwandBox();
					RootPanel.get("InhaltDiv").clear();
					RootPanel.get("InhaltDiv").add(pBox);

			}

		}

	}

	/**
	 * <b>Nested Class für den Schließen-Button</b> implementiert den ClickHandler
	 * zum Schließen der DialogBox
	 * 
	 */
	class CloseClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			hide();
		}

	}
}
