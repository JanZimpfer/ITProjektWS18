package de.hdm.gwt.itprojektws18.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
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

	private Button beitragBearbeitenBtn = new Button("Beitrag bearbeiten");
	private Button beitragLoeschenBtn = new Button("Beitrag löschen");
	private Button likeBtn = new Button("Gefällt mir!");
	private Button likesAnzeigenBtn = new Button("Likes anzeigen");

	private DateTimeFormat dtf = DateTimeFormat.getFormat("dd.MM.yyyy 'um' hh:mm");

	private KommentarBox kommentarBox = new KommentarBox();

	private Beitrag beitrag = new Beitrag();

	public BeitragBox() {

	}

	public BeitragBox(final Beitrag b) {

		this.beitrag = b;

		// Hinzufügen der StyleNames
		this.addStyleName("beitragBox");
		beitragInhalt.setStylePrimaryName("beitragInhalt");
		nickname.addStyleName("nickname");
		erstellzeitpunkt.addStyleName("erstellzeitpunkt");
		kommentarAnzahl.addStyleName("kommentarAnzahl");
		likeAnzahl.addStyleName("likeAnzahl");
		beitragBearbeitenBtn.addStyleName("beitragBearbeitenButton");
		beitragLoeschenBtn.addStyleName("loeschenBtn");
		likesAnzeigenBtn.addStyleName("likesAnzeigenBtn");
		likeBtn.addStyleName("likeButton");

		inhaltPanel.addStyleName("inhaltPanel");
		buttonPanel.addStyleName("ButtonPanel");
		erstelleKommentarPanel.addStyleName("erstelleKommentarPanel");
		kommentarPanel.addStyleName("kommentarPanel");

		ErstelleKommentarBox erstelleKommentarBox = new ErstelleKommentarBox(b);

		/**
		 * Die TextArea soll nur den Text des Beitrags anzeigen und keine Texteingabe
		 * ermöglichen. Zusätzlich: Festlegung der Größe der TextArea.
		 */
		beitragInhalt.setReadOnly(true);
		beitragInhalt.setSize("150px", "30px");

		inhaltPanel.add(nickname);
		inhaltPanel.add(erstellzeitpunkt);
		inhaltPanel.add(beitragInhalt);

		buttonPanel.add(likeAnzahl);
		buttonPanel.add(kommentarAnzahl);
		buttonPanel.add(likeBtn);
		buttonPanel.add(likesAnzeigenBtn);
		buttonPanel.add(beitragBearbeitenBtn);
		buttonPanel.add(beitragLoeschenBtn);

		likeBtn.addClickHandler(new LikesErstellenClickHandler());
		likesAnzeigenBtn.addClickHandler(new LikesAnzeigenClickHandler());
		beitragBearbeitenBtn.addClickHandler(new BeitragBearbeitenClickHandler());
		beitragLoeschenBtn.addClickHandler(new BeitragLoeschenClickHandler());

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

	class BeitragBearbeitenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			BeitragBearbeitenDialogBox dlgBox = new BeitragBearbeitenDialogBox(beitrag);
			dlgBox.center();

		}

	}

	class BeitragLoeschenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			pinnwandVerwaltung.loeschen(beitrag, new BeitragLoeschenCallback());

		}
	}

	class BeitragLoeschenCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Löschen des Beitrags: " + caught.getMessage());

		}

		@Override
		public void onSuccess(Void result) {

			PinnwandBox pBox = new PinnwandBox();

			Window.alert("Beitrag erfolgreich gelöscht");
			RootPanel.get("InhaltDiv").clear();
			RootPanel.get("InhaltDiv").add(pBox);

		}

	}

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

	class LikesErstellenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			Nutzer n = new Nutzer();
//			n.setId(Integer.parseInt((Cookies.getCookie("id"))));
			n.setId(3);
			pinnwandVerwaltung.getLikeFor(beitrag.getId(), n.getId(), new LikeInfoCallback());
		}
	}
	
	class LikeInfoCallback implements AsyncCallback<Like> {

		@Override
		public void onFailure(Throwable caught) {
			

			
		}

		@Override
		public void onSuccess(Like result) {
			
			if (result == null){
				Nutzer n = new Nutzer();
//				n.setId(Integer.parseInt(Cookies.getCookie("id")));
				n.setId(3);
				
				pinnwandVerwaltung.erstelleLike(beitrag, n, new LikeErstellenCallback());
			} else {
				pinnwandVerwaltung.loeschen(result, new LikeLoeschenCallback());
			}
			
		}
		
	}

	class LikeErstellenCallback implements AsyncCallback<Like> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Like erstellen : " + caught.getMessage());

		}

		@Override
		public void onSuccess(Like result) {

				PinnwandBox pBox = new PinnwandBox();

				RootPanel.get("InhaltDiv").clear();
				RootPanel.get("InhaltDiv").add(pBox);
			
		}
	}
	
	class LikeLoeschenCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Entfernen der Gefällt-mir Angabe: " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Void result) {
			PinnwandBox pBox = new PinnwandBox();
			RootPanel.get("InhaltDiv").clear();
			RootPanel.get("InhaltDiv").add(pBox);
			
		}
		
	}

	class LikesAnzeigenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			LikesAnzeigenDialogBox likesAnzeigenBox = new LikesAnzeigenDialogBox(beitrag);
			likesAnzeigenBox.center();

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