package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;

import java.util.Vector;

public class BeitragBox extends FlowPanel {

	// Panels fuer die Darstellung der BeitragBox
	private VerticalPanel InhaltPanel = new VerticalPanel();
	private VerticalPanel InfoPanel = new VerticalPanel();

	// benoetigte Label
	private Label beitragInhalt = new Label("Dieser Text ist der Inhalt");
	private Label nickname = new Label("@PeterPan");
	private Label erstellZeitpunkt = new Label("Zeitpunkt");
	private Label kommentarAnzahlText = new Label("30 Kommentare");
	private Label likeAnzahlText = new Label("85 Likes");

	// benoetigte Buttons
//	private Button beitragStatistikButton = new Button("Beitragstatistik");
	private Button beitragBearbeitenButton = new Button("Beitrag bearbeiten");
	private Button likeButton = new Button("Gefällt mir!");
	private Button kommentierButton = new Button("Kommentieren");

	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	ClientsideSettings clientSettings = new ClientsideSettings();
	
	public BeitragBox() {

	}

	public void onLoad() {

		// StyleName für das Styling aller BeitragBoxen mit CSS
		this.addStyleName("beitragBox");

		// Panels hinzufügen -> in RootPanel?
		this.add(InhaltPanel);
		this.add(InfoPanel);

		// Widgets hinzufügen
		InhaltPanel.add(nickname);
		InhaltPanel.add(beitragInhalt);
		InhaltPanel.add(erstellZeitpunkt);
		InhaltPanel.add(kommentarAnzahlText);
		InhaltPanel.add(likeAnzahlText);

		// Buttons hinzufügen
		InhaltPanel.add(beitragBearbeitenButton);
		InhaltPanel.add(likeButton);
		InhaltPanel.add(kommentierButton);

		// StyleNames für das Styling mit CSS hinzufügen
		beitragBearbeitenButton.addStyleName("beitragBearbeitenButton");
		likeButton.addStyleName("likeButton");
		kommentierButton.addStyleName("kommentierButton");

		RootPanel.get("InhaltBereich").add(InfoPanel);
		RootPanel.get("InhaltBereich").add(InhaltPanel);
	}

	/**
	 * Hier werden im Folgenden die inneren Klassen implementiert, die das Interface
	 * Clickhandler implementieren, um entsprechenden erstellten Buttons
	 * <code>ClickEvents</code> zuzuweisen.
	 * 
	 * @author florian
	 */

	class LikesAnzeigen implements ClickHandler {

		public void onClick(ClickEvent event) {

		}

	}

	class LikeErstellen implements ClickHandler {

		public void onClick(ClickEvent event) {

		}

	}

	class KommentareAnzeigen implements ClickHandler {

		public void onClick(ClickEvent event) {

		}

	}

	class KommentarErstellen implements ClickHandler {

		public void onClick(ClickEvent event) {

		}

	}

}
