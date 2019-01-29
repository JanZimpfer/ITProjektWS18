package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

/**
 * Klasse zur Erstellung einer ProfilBox.
 * @author Jan Zimpfer
 *
 */

public class ProfilBox extends VerticalPanel {
	
	/**
	 * Erzeugen eines PinnwandVerwaltung-Objekts um eine Applikationsverwaltung zu
	 * initialisieren.
	 */

	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	
	/**
	 * Instanziierung der GUI Elemente
	 */

	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private VerticalPanel imgPanel = new VerticalPanel();

	Image logo = new Image();

	private Button profilButton = new Button("Startseite");
	private Button reportButton = new Button("Report");
	
	/**
	 * Deklarierung des Business Object das verwendet wird
	 */

	Nutzer nutzer = new Nutzer();

	public ProfilBox() {

		this.addStyleName("profilBox");

		logo.setUrl("/images/tellIT_Logo.png");
		imgPanel.add(logo);

		buttonPanel.add(profilButton);
		buttonPanel.add(reportButton);

		profilButton.addStyleName("profilBtn");
		reportButton.addStyleName("profilBtn");

		reportButton.addClickHandler(new ReportClickHandler());
		profilButton.addClickHandler(new eigenesProfilAnzeigen());

		this.add(buttonPanel);
		this.add(imgPanel);

		super.onLoad();

	}

	class eigenesProfilAnzeigen implements ClickHandler {

		public void onClick(ClickEvent event) {

			Window.Location.assign("/");

		}
	}

	class ReportClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			Window.Location.assign("/ITProjektWS18Report.html");

		}

	}

}
