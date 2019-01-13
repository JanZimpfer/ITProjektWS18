package de.hdm.gwt.itprojektws18.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.thirdparty.javascript.jscomp.Result;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.client.gui.BeitragBox.AlleLikesCallback;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Kommentar;
import de.hdm.gwt.itprojektws18.shared.bo.Like;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class BeitragBox extends VerticalPanel {

	private static PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	/**
	 * Erstellung verschiedener Panels
	 */
	private VerticalPanel inhaltPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private VerticalPanel erstelleKommentarPanel = new VerticalPanel();
	private VerticalPanel kommentarPanel = new VerticalPanel();

	/**
	 * Erstellung benötigter GUI-Elemente
	 */
	private TextBox beitragInhalt = new TextBox();

	private Label nickname = new Label();
	private Label erstellzeitpunkt = new Label();
	private Label kommentarAnzahl = new Label();
	private Label likeAnzahl = new Label();

	private String nicknameFiller = new String();

	private Button beitragBearbeitenBtn = new Button("Beitrag bearbeiten");
	private Button beitragLoeschenBtn = new Button("Beitrag löschen");
	private Button likeBtn = new Button("Gefällt mir!");
	private Button likesAnzeigenBtn = new Button("Likes anzeigen");

	private TextBox beitragBearbeitenInhalt = new TextBox();
	private Button speichernBtn = new Button("Speichern");
	private Button schliessenBtn = new Button("Schließen");

	// Test-Vektor - OBACHT!
	private Vector<Like> alleLikesVonBeitrag = new Vector<Like>();

//	private ErstelleKommentarBox erstelleKommentarBox = new ErstelleKommentarBox(b);
	private KommentarBox kommentarBox = new KommentarBox();

	private Vector<Kommentar> kommentarVector = null;
	private Nutzer kommentarNutzer = null;
	public BeitragBox() {

	}

	public BeitragBox(final Beitrag b) {

//		// Hinzufügen der StyleNames
		this.addStyleName("beitragBox");
		beitragInhalt.addStyleName("beitragInhalt");
//		nickname.addStyleName("nickname");
//		erstellzeitpunkt.addStyleName("erstellzeitpunkt");
//		kommentarAnzahl.addStyleName("kommentarAnzahl");
//		likeAnzahl.addStyleName("likeAnzahl");
//		beitragBearbeitenBtn.addStyleName("beitragBearbeitenButton");
//		beitragLoeschenBtn.addStyleName("loeschenBtn");
//		likesAnzeigenBtn.addStyleName("likesAnzeigenBtn");
//		likeBtn.addStyleName("likeButton");
//
//		inhaltPanel.addStyleName("inhaltPanel");
//		buttonPanel.addStyleName("ButtonPanel");
//		erstelleKommentarPanel.addStyleName("erstelleKommentarPanel");
//		kommentarPanel.addStyleName("kommentarPanel");

		ErstelleKommentarBox erstelleKommentarBox = new ErstelleKommentarBox(b);

		/**
		 * Die TextArea soll nur den Text des Beitrags anzeigen und keine Texteingabe
		 * ermöglichen. Zusätzlich: Festlegung der Größe der TextArea.
		 */
		beitragInhalt.setReadOnly(true);
		beitragInhalt.setSize("470px", "30px");
		beitragInhalt.addStyleName("beitragInhalt");

		inhaltPanel.add(nickname);
		inhaltPanel.add(erstellzeitpunkt);
		inhaltPanel.add(beitragInhalt);

		buttonPanel.add(likeAnzahl);
		buttonPanel.add(kommentarAnzahl);
		buttonPanel.add(likeBtn);
		buttonPanel.add(likesAnzeigenBtn);
		buttonPanel.add(beitragBearbeitenBtn);
		buttonPanel.add(beitragLoeschenBtn);
		
		
		likeBtn.addClickHandler(new LikesErstellenClickHandler ());
		likesAnzeigenBtn.addClickHandler(new LikesAnzeigenClickHandler());
		beitragBearbeitenBtn.addClickHandler(new BeitragBearbeitenClickHandler());

		
		
		erstelleKommentarPanel.add(erstelleKommentarBox);

		kommentarPanel.add(kommentarBox);

		pinnwandVerwaltung.getAllKommentareByBeitrag(b, new KommentareAnzeigenCallback());
		pinnwandVerwaltung.getAllLikesByBeitrag(b, new AlleLikesCallback());

		this.add(inhaltPanel);
		this.add(buttonPanel);
		this.add(erstelleKommentarPanel);
		this.add(kommentarPanel);

		super.onLoad();

	}

	class BeitragBearbeitenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			BeitragBearbeitenForm beitragBearbeitenForm = new BeitragBearbeitenForm();
			beitragBearbeitenForm.center();
			beitragBearbeitenForm.show();

		}

	}

	class BeitragBearbeitenForm extends DialogBox {

		private VerticalPanel editPanel = new VerticalPanel();

		public BeitragBearbeitenForm() {

			speichernBtn.addClickHandler(new BeitragSpeichernClickHandler());
			schliessenBtn.addClickHandler(new SchliessenClickHandler());

			editPanel.add(beitragBearbeitenInhalt);
			editPanel.add(speichernBtn);
			editPanel.add(schliessenBtn);

			this.add(editPanel);

		}

	}

	class BeitragAbrufCallback implements AsyncCallback<Beitrag> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Abrufen des Beitraginhalts: " + caught.getMessage());

		}

		@Override
		public void onSuccess(Beitrag result) {
			// TODO Auto-generated method stub

		}

	}

	class BeitragSpeichernClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			Beitrag b = new Beitrag();

			b.setId(1);
			b.setText(beitragBearbeitenInhalt.getText());

			pinnwandVerwaltung.speichern(b, new BeitragSpeichernCallback());

		}

	}

	class BeitragSpeichernCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Bearbeiten des Beitrags: " + caught.getMessage());

		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Beitrag erfolgreich bearbeitet.");

		}

	}

	class SchliessenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			Window.Location.assign("http://127.0.0.1:8888/ITProjektWS18.html");

		}

	}

	class AlleLikesCallback implements AsyncCallback<Vector<Like>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Auslesen aller Likes: " + caught.getMessage());

		}

		@Override
		public void onSuccess(Vector<Like> result) {

			for (int i = 0; i < result.size(); i++) {

				alleLikesVonBeitrag.add(result.elementAt(i));
				
				String lA = "Likes: "+ result.size() + "";
				
				likeAnzahl.setText(lA);
				
			}

		}
	}
	
	
	

	class KommentareAnzeigenCallback implements AsyncCallback<Vector<Kommentar>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Auslesen der Kommentare: " + caught.getMessage());

		}

		@Override
		public void onSuccess(Vector<Kommentar> result) {
			
			
			for (final Kommentar kommentar : result) {
				final KommentarBox kBox = new KommentarBox(kommentar);
				
				pinnwandVerwaltung.getNutzerbyID(kommentar.getNutzerFK(), new AsyncCallback<Nutzer>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Nutzer result) {
						String nameString = "@" + result.getNickname() + "," + result.getVorname() + " " +  result.getNachname();
						final String erstellZP = kommentar.getErstellZeitpunkt().toString();
						final String inhalt = kommentar.getText();
						kBox.befuelleNicklabel(nameString);
						kBox.befuelleErstellzeitpunkt(erstellZP);
						kBox.befuelleInhalt(inhalt);

						kommentarPanel.add(kBox);
					}
				});
			}
		}


	}
	
	class LikesErstellenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			Like l = new Like ();
			
			
			Beitrag b = new Beitrag ();
			b.setId(1);
			
//			n.setId(Integer.parseInt((Cookies.getCookie("id"))));
			Nutzer n = new Nutzer ();
			n.setId(3);
			
			
			pinnwandVerwaltung.erstelleLike(b, l.getErstellZeitpunkt(), n, new LikeErstelleCallback());
			
		}
		
		class LikeErstelleCallback implements AsyncCallback<Like> {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim Like erstellen : " + caught.getMessage());
				
			}

			@Override
			public void onSuccess(Like result) {
					Window.alert("Like erstellt!");
					Window.Location.assign("http://127.0.0.1:8888/ITProjektWS18.html");
				
			}
			
			
		}
		
	}
	
	

	class LikesAnzeigenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			LikeDialogBox likeBox = new LikeDialogBox();
			likeBox.center();
			
		}

	}

	class LikeDialogBox extends DialogBox {

		VerticalPanel likeElemente = new VerticalPanel();
		ScrollPanel likeListe = new ScrollPanel();

		public LikeDialogBox() {

			for (int i = 0; i < alleLikesVonBeitrag.size(); i++) {

				Label nutzerInfo = new Label();

				String text = "" + alleLikesVonBeitrag.elementAt(i).getNutzerFK() + "";

				nutzerInfo.setText(text);

				likeElemente.add(nutzerInfo);

			}

			likeListe.add(likeElemente);
			this.add(likeListe);

		}
	}

	public void befuelleName(String nicknameString) {

		this.nickname.setText(nicknameString);

	}

	public void befuelleErstellzeitpunkt(String erstellzeitpunkt) {

		this.erstellzeitpunkt.setText(erstellzeitpunkt);

	}

	public void befuelleInhalt(String inhalt) {

		this.beitragInhalt.setText(inhalt);

	}

}