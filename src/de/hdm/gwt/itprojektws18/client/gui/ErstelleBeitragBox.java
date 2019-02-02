package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;

/**
 * Klasse zur Ermöglichung der Texteingabe und Speicherung dieses Beitrags,
 * welcher dann auf der Pinnwand des jeweiligen Nutzers angezeigt wird
 *
 */

public class ErstelleBeitragBox extends HorizontalPanel {

	private static PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	/**
	 * Instanziierung der GUI-Elemente
	 */

	private TextArea erstelleBeitragFeld = new TextArea();
	private Button postingButton = new Button("Posten");

	public ErstelleBeitragBox() {

		erstelleBeitragFeld.setText("Verfasse einen Beitrag...");
		erstelleBeitragFeld.setStylePrimaryName("beitragEingabeFeld");
		postingButton.setStyleName("gwt-Button");

		this.setVerticalAlignment(ALIGN_MIDDLE);
		this.add(erstelleBeitragFeld);
		this.add(postingButton);
		
		/**
		 * Hinzufügen eines ClickHandlers auf den Button, um den angezeigten Text in der
		 * TextArea zurückzusetzen
		 */
		erstelleBeitragFeld.addClickHandler(new BeitragFeldClickHandler());

		/**
		 * Hinzufügen eines ClickHandlers auf den Button, um den Text, der in der
		 * TextArea eingegeben wurde, als Beitrag abzuspeichern
		 */
		postingButton.addClickHandler(new postingButtonClickHandler());

		super.onLoad();

	}

	/**
	 * Nested Class für das Ausführen der <code>erstelleBeitrag</code> Methode und
	 * somit der Abspeicherung des eingegebenen Beitrags in der Datenbank,
	 * implementiert das ClickHandler Interface
	 * 
	 */
	class postingButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			Nutzer nutzer = new Nutzer();
			nutzer.setId(Integer.parseInt(Cookies.getCookie("id")));

			Pinnwand pinnwand = new Pinnwand();
			pinnwand.setId(nutzer.getId());

			String text = "'" + erstelleBeitragFeld.getText() + "'";

			pinnwandVerwaltung.erstelleBeitrag(pinnwand, text, nutzer, new BeitragAnlegenCallback());
		}

	}

	/**
	 * Nested Class für das direkte Anzeigen des Beitrags auf der Pinnwand des
	 * entsprechenden Nutzers
	 *
	 */
	public class BeitragAnlegenCallback implements AsyncCallback<Beitrag> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Anlegen des Beitrags: " + caught.getMessage());

		}

		@Override
		public void onSuccess(Beitrag result) {

			PinnwandBox pBox = new PinnwandBox();

			RootPanel.get("InhaltDiv").clear();
			RootPanel.get("InhaltDiv").add(pBox);

		}

	}

	/**
	 * Nested Class für das Zurücksetzen des Texts, der in der TextArea steht,
	 * sobald die TextArea angeklickt wird
	 *
	 */
	class BeitragFeldClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			erstelleBeitragFeld.setText("");

		}

	}
}
