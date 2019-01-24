package de.hdm.gwt.itprojektws18.client.gui;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;

public class ErstelleBeitragBox extends HorizontalPanel {

	private static PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	/**
	 * Erstellung der GUI-Elemente
	 */

	private TextBox erstelleBeitragFeld = new TextBox();
	private Button postingButton = new Button("Posten");

	public ErstelleBeitragBox() {

		erstelleBeitragFeld.setText("Verfasse einen Beitrag...");
		this.add(erstelleBeitragFeld);
		this.add(postingButton);

		erstelleBeitragFeld.addClickHandler(new BeitragFeldClickHandler());
		postingButton.addClickHandler(new postingButtonClickHandler());

		super.onLoad();

	}

	public ErstelleBeitragBox(final Pinnwand p) {

		// Hinzufügen der StyleNames
		this.addStyleName("erstelleBeitragBox");

		erstelleBeitragFeld.setStylePrimaryName("erstelleBeitragFeld");

		//erstelleBeitragFeld.addStyleName("erstelleBeitragFeld");

		postingButton.addStyleName("uebersichtBtn");


		erstelleBeitragFeld.setSize("100px", "30");

		//erstelleBeitragFeld.setSize("900px", "60");

		erstelleBeitragFeld.addClickHandler(new BeitragFeldClickHandler());
		
		this.add(erstelleBeitragFeld);
		this.add(postingButton);

		postingButton.addClickHandler(new postingButtonClickHandler());

		super.onLoad();

	}

	class postingButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			Nutzer nutzer = new Nutzer();
//			nutzer.setId(Integer.parseInt(Cookies.getCookie("id")));

			nutzer.setId(3);

			Pinnwand pinnwand = new Pinnwand();
			pinnwand.setId(nutzer.getId());

			String text = "'" + erstelleBeitragFeld.getText() + "'";

			pinnwandVerwaltung.erstelleBeitrag(pinnwand, text, nutzer, new BeitragAnlegenCallback());
		}

		public class BeitragAnlegenCallback implements AsyncCallback<Beitrag> {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim Anlegen des Beitrags: " + caught.getMessage());

			}

			@Override
			public void onSuccess(Beitrag result) {
				Window.alert("Der Beitrag wurde erfolgreich angelegt.");

				PinnwandBox pBox = new PinnwandBox();

				RootPanel.get("InhaltDiv").clear();
				RootPanel.get("InhaltDiv").add(pBox);

			}

		}
	}
	
	class BeitragFeldClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			erstelleBeitragFeld.setText("");
			
		}
		
	}
}
