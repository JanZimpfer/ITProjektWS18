package de.hdm.gwt.itprojektws18.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Abonnement;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class ProfilBox extends VerticalPanel {

	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private HorizontalPanel labelPanel = new HorizontalPanel();

	private Button profilbildButton = new Button("Profilbild");
	private Button profilButton = new Button("Startseite");
	private Button reportButton = new Button("Report");

	private Label beitraege = new Label();
	private Label abonniert = new Label();

	Nutzer nutzer = new Nutzer();

	public ProfilBox() {

//	
//		

		Nutzer n = new Nutzer();
//		nutzer.setId(Integer.parseInt(Cookies.getCookie("id")));
		n.setId(3);

		this.addStyleName("profilBox");

		buttonPanel.add(profilbildButton);
		buttonPanel.add(profilButton);
		buttonPanel.add(reportButton);

		labelPanel.add(beitraege);
		labelPanel.add(abonniert);

		profilbildButton.addStyleName("profilBtn");
		profilButton.addStyleName("profilBtn");
		reportButton.addStyleName("profilBtn");

		profilButton.addClickHandler(new eigenesProfilAnzeigen());

		pinnwandVerwaltung.getAllAbosFor(n, new anzahlAbosCallback());
		pinnwandVerwaltung.getAllBeitraegeByNutzer(n, new anzahlBeitragCallback());

		this.add(buttonPanel);
		this.add(labelPanel);

		/**
		 * ClickHandler den entsprechenden Buttons hinzufügen:
		 */

		reportButton.addClickHandler(new ReportClickHandler());

		super.onLoad();

	}

	class eigenesProfilAnzeigen implements ClickHandler {

		public void onClick(ClickEvent event) {

			Window.Location.assign("http://127.0.0.1:8888/ITProjektWS18.html");

		}
	}

	class anzahlAbosCallback implements AsyncCallback<Vector<Abonnement>> {

		@Override
		public void onFailure(Throwable caught) {

			Window.alert("Fehler beim zählen der Abos: " + caught.getMessage());
		}

		@Override
		public void onSuccess(Vector<Abonnement> result) {

			String anzahlAbos = "Abonniert: " + result.size() + "";

			abonniert.setText(anzahlAbos);

		}

	}

	class anzahlBeitragCallback implements AsyncCallback<Vector<Beitrag>> {

		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim zählen der Beitraege: " + caught.getMessage());

		}

		public void onSuccess(Vector<Beitrag> result) {

			String anzahlBeitraege = "Anzahl der Beitraege: " + result.size() + "";

			beitraege.setText(anzahlBeitraege);

		}

	}

	class ReportClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			Window.Location.assign("http://127.0.0.1:8888/ITProjektWS18Report.html");

		}

	}

}
