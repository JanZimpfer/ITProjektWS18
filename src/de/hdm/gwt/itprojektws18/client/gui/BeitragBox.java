package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;

import java.sql.Timestamp;
import java.util.Vector;

public class BeitragBox extends VerticalPanel {

	// Panels fuer die Darstellung der BeitragBox
	private VerticalPanel beitragPanel = new VerticalPanel();
	private HorizontalPanel NickTimePanel = new HorizontalPanel();
	private VerticalPanel InhaltPanel = new VerticalPanel();
	private FlowPanel InfoPanel = new FlowPanel();
	private HorizontalPanel ButtonPanel = new HorizontalPanel();
	// hier EK Panel hinzuf�gen
	private KommentarBox KBox = new KommentarBox();

	// benoetigte Label
	private Label beitragInhalt = new Label();
	private Label nickname = new Label();
	private Label erstellZeitpunkt = new Label();
	private Label kommentarAnzahlText = new Label("30 Kommentare");
	private Label likeAnzahlText = new Label("85 Likes");

	// benoetigte Buttons
	private Button beitragBearbeitenButton = new Button("Beitrag bearbeiten");
	private Button likeButton = new Button("Gefaellt mir!");
	private Button likesAnzeigenBtn= new Button("Likes anzeigen");
	private Button loeschenBtn = new Button("Loeschen");
	

	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	
	
	Pinnwand pinnwand = new Pinnwand();
	
	String Text;
	
	public BeitragBox() {
		
	}
	
	
	public BeitragBox(Pinnwand p) {

		this.pinnwand = p;
		
		
		InhaltPanel.setSpacing(4);
		ButtonPanel.setSpacing(5);
		NickTimePanel.setSpacing(2);
		
	}
	
	public void befuelleNicklabel (String nicknameString) {
		
		this.nickname.setText(nicknameString);
		
	}
	
	public void befuelleErstellzeitpunkt (String erstellzeitpunkt) {
		
		this.erstellZeitpunkt.setText(erstellzeitpunkt);
		
	}
	
	public void befuelleInhalt (String inhalt) {
		
		this.beitragInhalt.setText(inhalt);
	}

	public void onLoad() {


		
		// StyleName für das Styling aller BeitragBoxen mit CSS
		this.addStyleName("beitragBox");
		
		this.add(beitragPanel);
		
		
		// Panels hinzufügen 
		beitragPanel.add(InhaltPanel);
		beitragPanel.add(InfoPanel);
		beitragPanel.add(ButtonPanel);
		// hier Ek einf�gen
		beitragPanel.add(KBox);

		
		//NickTimePanel gestalten
		NickTimePanel.add(nickname);
		NickTimePanel.add(erstellZeitpunkt);
		
		// InhaltPanel gestalten
		InhaltPanel.add(NickTimePanel);
		InhaltPanel.add(beitragInhalt);
		InhaltPanel.add(ButtonPanel);
		
		// ButtonPanel gestalten
		ButtonPanel.add(likeButton);
		ButtonPanel.add(likeAnzahlText);
		ButtonPanel.add(likesAnzeigenBtn);
		ButtonPanel.add(beitragBearbeitenButton);
		ButtonPanel.add(loeschenBtn);
		ButtonPanel.add(kommentarAnzahlText);
		

		// StyleNames für das Styling mit CSS hinzufügen
		
		beitragBearbeitenButton.addStyleName("beitragBearbeitenButton");
		likeButton.addStyleName("likeButton");
		InfoPanel.addStyleName("infoPanel");
		InhaltPanel.addStyleName("inhaltPanel");
		beitragInhalt.addStyleName("beitragInhalt");
		nickname.addStyleName("nickname");
		erstellZeitpunkt.addStyleName("erstellZeitpunkt");
		ButtonPanel.addStyleName("ButtonPanel");
		loeschenBtn.addStyleName("loeschenBtn");
		
		
		RootPanel.get("InhaltDiv").add(beitragPanel);
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

