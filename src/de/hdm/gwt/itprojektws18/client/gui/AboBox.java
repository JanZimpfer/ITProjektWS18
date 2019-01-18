package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
//import de.hdm.gwt.itprojektws18.client.gui.AboPinnwandBox.PinnwandAnzeigen;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Abonnement;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;

import java.sql.Timestamp;
import java.util.Vector;

public class AboBox extends VerticalPanel {

	/**
	 * Erzeugen eines PinnwandVerwaltung-Objekts um eine Applikationsverwaltung zu
	 * initialisieren.
	 */
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	/**
	 * Instanziierung der GUI Elemente
	 */

	private VerticalPanel abobox = new VerticalPanel();

	private VerticalPanel aboniertePW = new VerticalPanel();

	private HorizontalPanel profilbereich = new HorizontalPanel();

	private HorizontalPanel aboPinnwandInfos = new HorizontalPanel();

	private HorizontalPanel reportButtonPanel = new HorizontalPanel();

	private Label anzeigePW = new Label("Abonnierte Pinnwaende: ");

	private Button reportButton = new Button("Report");

	Nutzer nutzer = new Nutzer();

	public AboBox() {

		profilbereich.setSpacing(2);
		aboniertePW.setSpacing(2);

		// Später Cookie-Id statt id=3 verwenden
		nutzer.setId(Integer.parseInt(Cookies.getCookie("id")));
//		nutzer.setId(3);

		pinnwandVerwaltung.getAllAbosFor(nutzer, new NeuesAboAnzeigenCallback());

		this.addStyleName("abobox");
		this.add(abobox);

		abobox.add(profilbereich);
		abobox.add(aboniertePW);
		abobox.add(reportButtonPanel);

		aboniertePW.add(anzeigePW);
		aboniertePW.add(aboPinnwandInfos);

		reportButtonPanel.add(reportButton);

		/**
		 * 
		 */
		anzeigePW.addStyleName("anzeigePW");
		aboniertePW.addStyleName("aboniertePW");
		aboPinnwandInfos.addStyleName("aboPinnwandInfos");
		reportButtonPanel.addStyleName("ReportButtonPanel");
		reportButton.addStyleName("ReportButton");


		/**
		 * ClickHandler den entsprechenden Buttons hinzufügen:
		 */

		reportButton.addClickHandler(new ReportClickHandler());

		super.onLoad();
	}

	class NeuesAboAnzeigenCallback implements AsyncCallback<Vector<Abonnement>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Anzeigen des Abonnements: " + caught.getMessage());

		}

		@Override
		public void onSuccess(Vector<Abonnement> result) {
			for (final Abonnement a : result) {
				final AboPinnwandBox apBox = new AboPinnwandBox(a);

				pinnwandVerwaltung.getNutzerbyID(a.getPinnwandFK(), new AsyncCallback<Nutzer>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler beim Auslesen der Nutzerinformationen: " + caught.getMessage());

					}

					@Override
					public void onSuccess(Nutzer result) {
						String nameString = "" + result.getVorname() + " " + result.getNachname() + "";
						String nicknameString = "@" + result.getNickname() + "";

						apBox.updateNutzerNameLabel(nameString);
						apBox.updateNickNameLabel(nicknameString);
						abobox.add(apBox);
					}

				});

			}

		}

	}

	class ReportClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			Window.Location.assign("http://127.0.0.1:8888/ITProjektWS18Report.html");

		}

	}

}
