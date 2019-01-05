package de.hdm.gwt.itprojektws18.client.gui;

import java.sql.Timestamp;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;

public class ErstelleBeitragBox extends HorizontalPanel {

	private HorizontalPanel PostingPanel = new HorizontalPanel();
	private TextArea erstelleBeitragFeld = new TextArea();
	private Button postingButton = new Button("Posten");

	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	ClientsideSettings clientSettings = new ClientsideSettings();
	
	private Pinnwand pinnwand = new Pinnwand();

	public ErstelleBeitragBox(Pinnwand p) {

		this.pinnwand = p;
		PostingPanel.setSpacing(2);
	}

	public void onLoad() {
		this.addStyleName("erstelleBeitragBox");
		this.add(PostingPanel);

		PostingPanel.add(erstelleBeitragFeld);
		PostingPanel.add(postingButton);

		erstelleBeitragFeld.addStyleName("erstelleBeitragFeld");
		postingButton.addStyleName("submitButton");

		RootPanel.get("BeitragDiv").add(PostingPanel);
	}

	class postingButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			Nutzer nutzer = new Nutzer();
			nutzer.setId(Integer.parseInt(Cookies.getCookie("id")));

			Pinnwand pinnwand = new Pinnwand();
			pinnwand.setNutzerFK(nutzer.getId());
			Timestamp erstellzeitpunkt = null;

			String text = erstelleBeitragFeld.getText();

			pinnwandVerwaltung.erstelleBeitrag(pinnwand, text, erstellzeitpunkt, nutzer, new BeitragAnlegenCallback());
		}

		public class BeitragAnlegenCallback implements AsyncCallback<Beitrag> {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim Anlegen des Beitrags: " + caught.getMessage());

			}

			@Override
			public void onSuccess(Beitrag result) {
				Window.alert("Der Beitrag wurde erfolgreich angelegt.");
				BeitragBox bBox = new BeitragBox(pinnwand);
			}

		}

	}
	
}
