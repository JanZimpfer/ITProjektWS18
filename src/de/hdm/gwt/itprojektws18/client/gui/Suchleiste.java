package de.hdm.gwt.itprojektws18.client.gui;

import java.util.Vector;



import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;

import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

/*
 * Klasse um ein NutzerProfil über die Suchleiste aufzurufen.
 * @author Jan Zimpfer
 *
 */


public class Suchleiste extends HorizontalPanel {

	/*
	 * Erzeugen eines PinnwandVerwaltung-Objekts um eine Applikationsverwaltung zu
	 * initialisieren.
	 */
	
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	
	/*
	 * Instanziierung der GUI Elemente
	 */

	private HorizontalPanel suchleiste = new HorizontalPanel();
	private Button sucheButton = new Button("Pinnwand anzeigen");

	MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	private SuggestBox txtBox = new SuggestBox(oracle);

	
	public Suchleiste() {

		this.add(suchleiste);
		this.add(txtBox);
		txtBox.setText("Nicknamen eingeben...");
		
		
		suchleiste.add(sucheButton);
		/**
		 * Diese Methode gibt den Nutzer mit dem jeweiligen Nickname zurück.
		 */
		pinnwandVerwaltung.getAllNutzer(new SearchCallback());

		/**
		 * Hinzufügen der StyleNamen für CSS-Styling
		 */
		this.addStyleName("suchleistePanel");
		sucheButton.addStyleName("suchButton");
		txtBox.setStyleName("suchBox");
		
		sucheButton.addClickHandler(new ShowPinnwandClickHandler());

		super.onLoad();
	}
	
	/**
	 * Nested Class für das Anzeigen des Nutzers
	 *
	 */

	class SearchCallback implements AsyncCallback<Vector<Nutzer>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Ausführen der Suche: " + caught.getMessage());

		}

		@Override
		public void onSuccess(Vector<Nutzer> result) {

			String searchResultString = new String();
			Label searchLbl = new Label();
			for (int i = 0; i < result.size(); i++) {

				searchResultString = "" + result.elementAt(i).getNickname();
				oracle.add(searchResultString);

			}

		}

	}
	
	/**
	 * Nested Class für das Anzeigen des Profils
	 *
	 */

	class ShowPinnwandClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			pinnwandVerwaltung.getNutzerByNickname(txtBox.getText(), new ShowPinnwandCallback());

		}
	}

	class ShowPinnwandCallback implements AsyncCallback<Nutzer> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Anzeigen der Pinnwand" + caught.getMessage());

		}

		@Override
		public void onSuccess(Nutzer result) {
			PinnwandBox pB = new PinnwandBox(result.getId());
			RootPanel.get("InhaltDiv").clear();
			RootPanel.get("InhaltDiv").add(pB);
			txtBox.refreshSuggestionList();

		}

	}

}
