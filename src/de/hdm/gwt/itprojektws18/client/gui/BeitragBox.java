package de.hdm.gwt.itprojektws18.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Kommentar;
import de.hdm.gwt.itprojektws18.shared.bo.Like;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class BeitragBox extends VerticalPanel {

	/**
	 * Instanziierung eines PinnwandVerwaltung-Objekts um eine
	 * Applikationsverwaltung zu initialisieren
	 */
	private static PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	/**
	 * Erstellung verschiedener Panels
	 */
	private VerticalPanel inhaltPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private VerticalPanel erstelleKommentarPanel = new VerticalPanel();
	private VerticalPanel kommentarPanel = new VerticalPanel();
	private HorizontalPanel nutzerZeitPanel = new HorizontalPanel ();

	/**
	 * Erstellung benötigter GUI-Elemente
	 */
	private TextBox beitragInhalt = new TextBox();

	private Label nickname = new Label();
	private Label erstellzeitpunkt = new Label();
	private Label kommentarAnzahl = new Label();
	private Label likeAnzahl = new Label();

	private Button beitragBearbeitenBtn = new Button("Beitrag bearbeiten");
	private Button beitragLoeschenBtn = new Button("Beitrag löschen");
	private Button likeBtn = new Button();
	private Button likesAnzeigenBtn = new Button("Likes anzeigen");

	private DateTimeFormat dtf = DateTimeFormat.getFormat("dd.MM.yyyy k:mm");

	private KommentarBox kommentarBox = new KommentarBox();

	private Beitrag beitrag = new Beitrag();

	public BeitragBox() {

	
	}

	public BeitragBox(final Beitrag b) {

		this.beitrag = b;
		Nutzer n = new Nutzer();
		n.setId(Integer.parseInt(Cookies.getCookie("id")));

		this.addStyleName("beitragBox");
		beitragInhalt.setStylePrimaryName("beitragInhalt");
		nickname.addStyleName("nickname");
		erstellzeitpunkt.addStyleName("erstellzeitpunkt");
		kommentarAnzahl.addStyleName("kommentarAnzahl");
		likeAnzahl.addStyleName("likeAnzahl");
		beitragBearbeitenBtn.addStyleName("beitragBtn");
		beitragLoeschenBtn.addStyleName("beitragBtn");
		likesAnzeigenBtn.addStyleName("beitragBtn");
		likeBtn.addStyleName("beitragBtn");

		inhaltPanel.addStyleName("inhaltPanel");
		buttonPanel.addStyleName("ButtonPanel");
		erstelleKommentarPanel.addStyleName("erstelleKommentarPanel");
		kommentarPanel.addStyleName("kommentarPanel");

		nutzerZeitPanel.addStyleName("nutzerZeitPanel");

		pinnwandVerwaltung.getLikeFor(b.getId(), n.getId(), new LikeCheckCallback());
		
		ErstelleKommentarBox erstelleKommentarBox = new ErstelleKommentarBox(b);

		beitragInhalt.setReadOnly(true);
		beitragInhalt.setSize("150px", "30px");

		inhaltPanel.add(nutzerZeitPanel);
		inhaltPanel.add(beitragInhalt);
		
		nutzerZeitPanel.add(nickname);
		nutzerZeitPanel.add(erstellzeitpunkt);

		buttonPanel.add(likeAnzahl);
		buttonPanel.add(kommentarAnzahl);
		buttonPanel.add(likeBtn);
		buttonPanel.add(likesAnzeigenBtn);
		
		if (n.getId() == b.getNutzerFK()) {
			buttonPanel.add(beitragBearbeitenBtn);
			buttonPanel.add(beitragLoeschenBtn);
			beitragBearbeitenBtn.addClickHandler(new BeitragBearbeitenClickHandler());
			beitragLoeschenBtn.addClickHandler(new BeitragLoeschenClickHandler());
		}

		likeBtn.addClickHandler(new LikesErstellenClickHandler());
		likesAnzeigenBtn.addClickHandler(new LikesAnzeigenClickHandler());

		erstelleKommentarPanel.add(erstelleKommentarBox);

		pinnwandVerwaltung.getAllKommentareByBeitrag(b, new KommentareAnzeigenCallback());
		pinnwandVerwaltung.getAllLikesByBeitrag(b, new AlleLikesCallback());

		kommentarPanel.add(kommentarBox);

		this.add(inhaltPanel);
		this.add(buttonPanel);
		this.add(erstelleKommentarPanel);
		this.add(kommentarPanel);

		super.onLoad();

	}
	
	class LikeCheckCallback implements AsyncCallback<Like>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Abfragen des Likes " +caught.getMessage());
			
		}

		@Override
		public void onSuccess(Like result) {
			if(result == null) {
				likeBtn.setText("Gefällt mir!");
			} else {
				likeBtn.setText("Gefällt mir nicht mehr!");
			}
			
		}
		
		
	}

	/**
	 * <b>Nested Class für den Bearbeiten-Button</b> implementiert den
	 * entsprechenden ClickHandler
	 * 
	 * Sobald ein ClickEvent empfangen wird öffnet sich eine
	 * BeitragBearbeitenDialogBox
	 */
	class BeitragBearbeitenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			BeitragBearbeitenDialogBox dlgBox = new BeitragBearbeitenDialogBox(beitrag);
			dlgBox.center();

		}

	}

	/**
	 * <b>Nested Class für den Löschen-Button</b> implementiert den entsprechenden
	 * ClickHandler
	 * 
	 * Sobald ein ClickEvent empfangen wird öffnet sich eine
	 * BeitragLoeschenDialogBox
	 */
	class BeitragLoeschenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			BeitragLoeschenDialogBox loeschenBox = new BeitragLoeschenDialogBox(beitrag);
			loeschenBox.center();

		}
	}

	/**
	 * <b>Nested Class zur Like-Abfrage eines Beitrags</b> Callback Aufruf zur
	 * Ausgabe aller Likes
	 * 
	 * Setzt die Größe des Ergebnisvektors als Anzahl aller Likes für einen Beitrag
	 *
	 */
	class AlleLikesCallback implements AsyncCallback<Vector<Like>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Auslesen aller Likes: " + caught.getMessage());

		}

		@Override
		public void onSuccess(Vector<Like> result) {


			String lA = "Likes: " + result.size() + "";
			likeAnzahl.setText(lA);

		}
	}

	/**
	 * <b>Nested Class zur Kommentar-Abfrage eines Beitrags</b> Callback Aufruf zur
	 * Ausgabe aller Kommentare.
	 * 
	 * Legt für jedes Kommentar-Objekt eine entsprechende KommentarBox an und
	 * befüllt diese mit dem Inhalt des Kommentars
	 *
	 */
	class KommentareAnzeigenCallback implements AsyncCallback<Vector<Kommentar>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Auslesen der Kommentare: " + caught.getMessage());

		}

		@Override
		public void onSuccess(Vector<Kommentar> result) {

			String aK = "Kommentare: " + result.size() + "";
			kommentarAnzahl.setText(aK);

			for (final Kommentar kommentar : result) {
				final KommentarBox kBox = new KommentarBox(kommentar);
				kommentarPanel.add(kBox);

				pinnwandVerwaltung.getNutzerbyID(kommentar.getNutzerFK(), new AsyncCallback<Nutzer>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler beim Auslesen der Nutzerinformationen: " + caught.getMessage());

					}

					@Override
					public void onSuccess(Nutzer result) {
						String nameString = "@" + result.getNickname();
						final String erstellZP = dtf.format(kommentar.getErstellZeitpunkt());
						final String inhalt = kommentar.getText();
						kBox.befuelleNicklabel(nameString);
						kBox.befuelleErstellzeitpunkt(erstellZP);
						kBox.befuelleInhalt(inhalt);

					}
				});
			}
		}
	}

	/**
	 * <b>Nested Class für den Like-Button</b> implementiert den entsprechenden
	 * ClickHandler
	 *
	 */
	class LikesErstellenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			Nutzer n = new Nutzer();
			n.setId(Integer.parseInt((Cookies.getCookie("id"))));
			
			pinnwandVerwaltung.getLikeFor(beitrag.getId(), n.getId(), new LikeInfoCallback());

		}
	}

	/**
	 * <b>Nested Class für den Like-Button</b> Callback Aufruf zur Überprüfung ob
	 * Like bereits vorhanden ist
	 *
	 */
	class LikeInfoCallback implements AsyncCallback<Like> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Abrufen der Gefällt-mir Angaben: " + caught.getMessage());
		}

		@Override
		public void onSuccess(Like result) {

			if (result == null) {
				Nutzer n = new Nutzer();
				n.setId(Integer.parseInt(Cookies.getCookie("id")));

				pinnwandVerwaltung.erstelleLike(beitrag, n, new LikeErstellenCallback());

			} else {
				pinnwandVerwaltung.loeschen(result, new LikeLoeschenCallback());
			}
		}
	}

	/**
	 * <b>Nested Class für den like-Button</b> Callback Aufruf zum Erstellen eines
	 * Likes
	 *
	 */
	class LikeErstellenCallback implements AsyncCallback<Like> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Like erstellen : " + caught.getMessage());

		}

		@Override
		public void onSuccess(Like result) {

			PinnwandBox pBox = new PinnwandBox(beitrag.getNutzerFK());
			
			RootPanel.get("InhaltDiv").clear();
			RootPanel.get("InhaltDiv").add(pBox);
			
			

		}
	}

	/**
	 * <b>Nested Class für den like-Button</b> Callback Aufruf zum Entfernen eines
	 * Likes
	 *
	 */
	class LikeLoeschenCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Entfernen der Gefällt-mir Angabe: " + caught.getMessage());

		}

		@Override
		public void onSuccess(Void result) {
			PinnwandBox pBox = new PinnwandBox(beitrag.getNutzerFK());
			RootPanel.get("InhaltDiv").clear();
			RootPanel.get("InhaltDiv").add(pBox);
			
			

		}

	}

	/**
	 * <b>Nested Class für den LikesAnzeigen-Button</b> implementiert den
	 * entsprechenden ClickHandler
	 *
	 */
	class LikesAnzeigenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			LikesAnzeigenDialogBox likesAnzeigenBox = new LikesAnzeigenDialogBox(beitrag);
			likesAnzeigenBox.center();

		}

	}

	/**
	 * Methode zum Setzen des Autors eines Beitrags
	 * 
	 * @param name
	 */
	public void befuelleName(String name) {

		this.nickname.setText(name);

	}

	/**
	 * Methode zum Setzen des Erstellzeitpunkts eines Beitrags
	 * 
	 * @param erstellzeitpunkt
	 */
	public void befuelleErstellzeitpunkt(String erstellzeitpunkt) {

		this.erstellzeitpunkt.setText(erstellzeitpunkt);

	}

	/**
	 * Methode zum Setzen des Beitraginhalts
	 * 
	 * @param inhalt
	 */
	public void befuelleInhalt(String inhalt) {

		this.beitragInhalt.setText(inhalt);

	}

}