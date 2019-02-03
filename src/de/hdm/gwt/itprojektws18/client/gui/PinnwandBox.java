package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

/**
 * Klasse zur Darstellung der Pinnwand innerhalb der ÜbersichtBox.
 *
 */
public class PinnwandBox extends VerticalPanel {

	/**
	 * Instanziierung eines PinnwandVerwaltung-Objekts um eine
	 * Applikationsverwaltung zu initialisieren
	 */
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	/**
	 * Konstruktor für die PinnwandBox des eingeloggten Users
	 */
	public PinnwandBox() {

		UebersichtBox uebersichtbox = new UebersichtBox();

		RootPanel.get("InhaltDiv").clear();
		Nutzer n = new Nutzer();
		n.setId(Integer.parseInt(Cookies.getCookie("id")));

		this.addStyleName("pinnwandBox");

		this.add(uebersichtbox);

		super.onLoad();

	}
	/**
	 * Konstruktor für die PinnwandBox des ausgewählten Users
	 */
	public PinnwandBox(int nutzerId) {

		Nutzer n = new Nutzer();
		n.setId(nutzerId);

		this.addStyleName("pinnwandBox");

		UebersichtBox uebersichtbox = new UebersichtBox(n.getId());

		this.add(uebersichtbox);

		super.onLoad();

	}
}