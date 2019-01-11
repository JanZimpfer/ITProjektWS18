package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;


//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.user.client.Cookies;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.*;
//
//import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
//import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
//import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
//import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
//import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;
//
//import java.sql.Timestamp;
//import java.util.Vector;

public class BeitragBox extends VerticalPanel {
	
	private static PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	
	/**
	 * Erstellung verschiedener Panels
	 */
	private VerticalPanel inhaltPanel = new VerticalPanel();
	private VerticalPanel buttonPanel = new VerticalPanel();
	private VerticalPanel erstelleKommentarPanel = new VerticalPanel();
	private VerticalPanel kommentarPanel = new VerticalPanel();
	
	/**
	 * Erstellung benötigter GUI-Elemente
	 */
	private TextArea beitragInhalt = new TextArea();
	
	private Label vorname = new Label();
	private Label nachname = new Label();
	private Label nickname = new Label();
	private Label erstellzeitpunkt = new Label();
	private Label kommentarAnzahl = new Label();
	private Label likeAnzahl = new Label();
	
	private Button beitragBearbeitenBtn = new Button("Beitrag bearbeiten");
	private Button beitragLoeschenBtn = new Button("Beitrag löschen");
	private Button kommentierBtn = new Button("Kommentieren");
	private Button likeBtn = new Button("Gefällt mir!");
	private Button likesAnzeigenBtn = new Button("Likes anzeigen");
	
	
	private ErstelleKommentarBox erstelleKommentarBox = new ErstelleKommentarBox();
	private KommentarBox kommentarBox = new KommentarBox();
	
	public BeitragBox(final Beitrag b) {
		
		/**
		 * T1
		 */
		inhaltPanel.add(nickname);
		inhaltPanel.add(vorname);
		inhaltPanel.add(nachname);
		inhaltPanel.add(erstellzeitpunkt);
		inhaltPanel.add(beitragInhalt);
		
		buttonPanel.add(likeAnzahl);
		buttonPanel.add(kommentarAnzahl);
		buttonPanel.add(beitragBearbeitenBtn);
		buttonPanel.add(beitragLoeschenBtn);
		buttonPanel.add(kommentierBtn);
		buttonPanel.add(likeBtn);
		buttonPanel.add(likesAnzeigenBtn);
		
		erstelleKommentarPanel.add(erstelleKommentarBox);
		
		kommentarPanel.add(kommentarBox);
		
		this.add(inhaltPanel);
		this.add(buttonPanel);
		this.add(erstelleKommentarPanel);
		this.add(kommentarPanel);
		
		super.onLoad();
		
		
		/**
		 * T2
		 */
		
		Nutzer n = new Nutzer();
		n.setId(3);
		 
		pinnwandVerwaltung.getNutzerbyID(n.getId(), new NutzerInfosCallback());
		
		this.add(kommentierBtn);
		this.add(beitragInhalt);
		
		super.onLoad();
		
		RootPanel.get("InhaltDiv").add(kommentierBtn);
	}
	
	/**
	 * Nested Class fuer den Callback Aufruf um Nutzerinformationen zu erhalten
	 */
	class NutzerInfosCallback implements AsyncCallback<Nutzer> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Auslesen der Nutzerinformationen: "+caught.getMessage());
			
		}

		@Override
		public void onSuccess(Nutzer result) {
			
		}
		
	}
}

//
//	// Panels fuer die Darstellung der BeitragBox
//	private VerticalPanel beitragPanel = new VerticalPanel();
//	private HorizontalPanel nickTimePanel = new HorizontalPanel();
//	private VerticalPanel inhaltPanel = new VerticalPanel();
//	private FlowPanel infoPanel = new FlowPanel();
//	private HorizontalPanel buttonPanel = new HorizontalPanel();
//	// hier EK Panel hinzuf�gen
//	private KommentarBox kBox = new KommentarBox();
//
//	// benoetigte Label
//	private TextArea beitragInhalt = new TextArea();
//	private Label nickname = new Label();
//	private Label erstellZeitpunkt = new Label();
//	private Label kommentarAnzahlText = new Label("30 Kommentare");
//	private Label likeAnzahlText = new Label("85 Likes");
//
//	// benoetigte Buttons
//	private Button beitragBearbeitenButton = new Button("Beitrag bearbeiten");
//	private Button likeButton = new Button("Gefaellt mir!");
//	private Button likesAnzeigenBtn= new Button("Likes anzeigen");
//	private Button loeschenBtn = new Button("Loeschen");
//	
//
//	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
//	
//	
//	Pinnwand pinnwand = new Pinnwand();
//	
//	String Text;
//	
//	public BeitragBox() {
//		
//	}
//	
//	
//	public BeitragBox(Pinnwand p) {
//
//		this.pinnwand = p;
//		
//		
//		inhaltPanel.setSpacing(4);
//		buttonPanel.setSpacing(5);
//		nickTimePanel.setSpacing(2);
//		
//	}
//	
//	public void befuelleNicklabel (String nicknameString) {
//		
//		this.nickname.setText(nicknameString);
//		
//	}
//	
//	public void befuelleErstellzeitpunkt (String erstellzeitpunkt) {
//		
//		this.erstellZeitpunkt.setText(erstellzeitpunkt);
//		
//	}
//	
//	public void befuelleInhalt (String inhalt) {
//		
//		this.beitragInhalt.setText(inhalt);
//	}
//
//	public void onLoad() {
//
//
//		
//		// StyleName für das Styling aller BeitragBoxen mit CSS
//		this.addStyleName("beitragBox");
//		
//		
//	
//
//		
//		//NickTimePanel gestalten
//		nickTimePanel.add(nickname);
//		nickTimePanel.add(erstellZeitpunkt);
//		
//		// InhaltPanel gestalten
//		inhaltPanel.add(nickTimePanel);
//		inhaltPanel.add(beitragInhalt);
//		inhaltPanel.add(buttonPanel);
//		
//		// ButtonPanel gestalten
//		buttonPanel.add(likeButton);
//		buttonPanel.add(likeAnzahlText);
//		buttonPanel.add(likesAnzeigenBtn);
//		buttonPanel.add(beitragBearbeitenButton);
//		buttonPanel.add(loeschenBtn);
//		buttonPanel.add(kommentarAnzahlText);
//		
//
//		// StyleNames für das Styling mit CSS hinzufügen
//		
//		beitragBearbeitenButton.addStyleName("beitragBearbeitenButton");
//		likeButton.addStyleName("likeButton");
//		//infoPanel.addStyleName("infoPanel");
//		inhaltPanel.addStyleName("inhaltPanel");
//		beitragInhalt.addStyleName("beitragInhalt");
//		nickname.addStyleName("nickname");
//		erstellZeitpunkt.addStyleName("erstellZeitpunkt");
//		buttonPanel.addStyleName("ButtonPanel");
//		loeschenBtn.addStyleName("loeschenBtn");
//		
//		// Panels hinzufügen 
//		beitragPanel.add(inhaltPanel);
//		//beitragPanel.add(infoPanel);
//		beitragPanel.add(buttonPanel);
//		// hier Ek einf�gen
//		beitragPanel.add(kBox);
//		
//		this.add(beitragPanel);
//		RootPanel.get("InhaltDiv").add(beitragPanel);
//	}
//
//	/**
//	 * Hier werden im Folgenden die inneren Klassen implementiert, die das Interface
//	 * Clickhandler implementieren, um entsprechenden erstellten Buttons
//	 * <code>ClickEvents</code> zuzuweisen.
//	 * 
//	 * @author florian
//	 */
//
//	class LikesAnzeigen implements ClickHandler {
//
//		public void onClick(ClickEvent event) {
//
//		}
//
//	}
//
//	class LikeErstellen implements ClickHandler {
//
//		public void onClick(ClickEvent event) {
//
//		}
//
//	}
//
//	class KommentareAnzeigen implements ClickHandler {
//
//		public void onClick(ClickEvent event) {
//
//		}
//
//	}
//
//	class KommentarErstellen implements ClickHandler {
//
//		public void onClick(ClickEvent event) {
//
//		}
//
//	}
//				
//			
//		
//	}
//
