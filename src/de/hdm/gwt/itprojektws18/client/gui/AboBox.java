package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Abonnement;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

import java.util.Vector;

/**
 * Klasse zur Darstellung aller Abonnement Beziehungen eines Nutzers.
 * @author NiklasFuchs
 *
 */

public class AboBox extends VerticalPanel {

	/**
	 * Erzeugen eines PinnwandVerwaltung-Objekts um eine Applikationsverwaltung zu
	 * initialisieren.
	 * @author NiklasFuchs
	 */
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	/**
	 * Instanziierung der GUI Elemente
	 * @author NiklasFuchs
	 */

	private VerticalPanel abobox = new VerticalPanel();

	private VerticalPanel aboniertePW = new VerticalPanel();

	private HorizontalPanel profilbereich = new HorizontalPanel();

	private HorizontalPanel aboPinnwandInfos = new HorizontalPanel();

	private Label anzeigePW = new Label("Abonnierte Pinnwände: ");

	/**
	 * Deklarierung des Business Object das verwendet wird
	 * @author NiklasFuchs
	 */
	Nutzer nutzer = new Nutzer();

	public AboBox() {

		profilbereich.setSpacing(2);
		aboniertePW.setSpacing(2);
		
		nutzer.setId(Integer.parseInt(Cookies.getCookie("id")));

		/**
		 * Diese Methode gibt alle Abonnements für den eingeloggten Nutzer aus
		 * @author NiklasFuchs
		 */
		pinnwandVerwaltung.getAllAbosFor(nutzer, new NeuesAboAnzeigenCallback());

		abobox.add(profilbereich);
		abobox.add(aboniertePW);

		aboniertePW.add(anzeigePW);
		aboniertePW.add(aboPinnwandInfos);
		
		this.add(abobox);

		/**
		 * Hinzufügen der StyleNamen für CSS-Styling
		 * @author NiklasFuchs
		 */
		this.addStyleName("abobox");
		anzeigePW.addStyleName("anzeigePW");
		aboniertePW.addStyleName("aboniertePW");
		aboPinnwandInfos.addStyleName("aboPinnwandInfos");

		super.onLoad();
	}

	/**
	 * Nested Class für das Anzeigen der Abonnements
	 * @author NiklasFuchs
	 *
	 */
	class NeuesAboAnzeigenCallback implements AsyncCallback<Vector<Abonnement>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Anzeigen des Abonnements: " + caught.getMessage());

		}

		@Override
		public void onSuccess(Vector<Abonnement> result) {
			for (final Abonnement a : result) {
				final AboPinnwandBox apBox = new AboPinnwandBox(a);
				abobox.add(apBox);

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
						
					}

				});

			}

		}

	}

}
