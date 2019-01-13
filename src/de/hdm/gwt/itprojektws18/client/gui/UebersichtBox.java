package de.hdm.gwt.itprojektws18.client.gui;

import java.util.Vector;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class UebersichtBox extends VerticalPanel {

	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	private ErstelleBeitragBox erstelleBeitragBox = new ErstelleBeitragBox();
	private VerticalPanel beitragPanel = new VerticalPanel();
	
	private DateTimeFormat dtf = DateTimeFormat.getFormat("dd.MM.yyyy 'um' hh:mm");

	public UebersichtBox() {

		this.add(erstelleBeitragBox);

		Nutzer n = new Nutzer();
		n.setId(3);

		pinnwandVerwaltung.getAllBeitraegeByNutzer(n, new BeitraegeAnzeigenCallback());

		this.add(beitragPanel);

		super.onLoad();
	}

	public UebersichtBox(int nutzerId) {

		Nutzer n = new Nutzer();
		n.setId(nutzerId);

		pinnwandVerwaltung.getAllBeitraegeByNutzer(n, new BeitraegeAnzeigenCallback());

		super.onLoad();
	}

	public class BeitraegeAnzeigenCallback implements AsyncCallback<Vector<Beitrag>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Auslesen der Beitragsinformationen: " + caught.getMessage());

		}

		@Override
		public void onSuccess(Vector<Beitrag> result) {
			
			for (final Beitrag beitrag : result) {
				final BeitragBox bBox = new BeitragBox(beitrag);

				pinnwandVerwaltung.getNutzerbyID(beitrag.getNutzerFK(), new AsyncCallback<Nutzer>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler beim Auslesen der Nutzerinformationen: " + caught.getMessage());

					}

					@Override
					public void onSuccess(Nutzer result) {
						String nameString = "@" + result.getNickname() + "," + result.getVorname() + " "
								+ result.getNachname();
						final String erstellZP = dtf.format(beitrag.getErstellZeitpunkt());
						final String inhalt = beitrag.getText();

						bBox.befuelleName(nameString);
						bBox.befuelleErstellzeitpunkt(erstellZP);
						bBox.befuelleInhalt(inhalt);

						beitragPanel.add(bBox);

					}

				});
			}
		}
	}
}
