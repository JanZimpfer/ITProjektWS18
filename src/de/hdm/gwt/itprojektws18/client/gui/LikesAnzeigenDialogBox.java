package de.hdm.gwt.itprojektws18.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Like;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

/**
 * Klasse, die das Anzeigen von Nutzern ermöglicht, die einen Beitrag mit einem "Liek" versehen haben.
 * Darstellung erfolgt über eine DialogBox.
 * @author NiklasFuchs
 *
 */

public class LikesAnzeigenDialogBox extends DialogBox {

	/**
	 * Instanziierung eines PinnwandVerwaltung-Objekts um eine
	 * Applikationsverwaltung zu initialisieren
	 */
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	/**
	 * Instanziierung der benötigten GUI-Elemente
	 * @author NiklasFuchs
	 */
	private VerticalPanel uebersichtPanel = new VerticalPanel();
	private VerticalPanel likePanel = new VerticalPanel();
	private Label infoText = new Label("Folgende Nutzer haben diesen Beitrag mit Gefällt-mir markiert:");
	private Button schliessenBtn = new Button("Schließen");

	/**
	 * Deklarierung des Business Object das verwendet wird
	 * @author NiklasFuchs
	 */
	private Beitrag beitrag = new Beitrag();
	
	public LikesAnzeigenDialogBox() {

	}

	public LikesAnzeigenDialogBox(Beitrag b) {

		this.beitrag = b;
		
		this.setText("Gefällt-mir Angaben");
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.setAutoHideEnabled(true);
		
		/**
		 * Methode, alle Likes zu einem beitrag ausgibt.
		 * @author NiklasFuchs
		 */
		pinnwandVerwaltung.getAllLikesByBeitrag(beitrag, new LikesAusgebenCallback());
		
		/**
		 * Hinzufügen eines ClickHandler, der das Schließen der DialogBox bewirkt.
		 * @author NiklasFuchs
		 */
		schliessenBtn.addClickHandler(new SchliessenClickHandler());
		
		uebersichtPanel.add(infoText);
		uebersichtPanel.add(likePanel);
		uebersichtPanel.add(schliessenBtn);
		
		this.add(uebersichtPanel);

	}

	/**
	 * <b>Nested Class für den Callback Aufruf zur Ausgabe aller Likes</b>
	 * @author NiklasFuchs
	 */
	class LikesAusgebenCallback implements AsyncCallback<Vector<Like>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim abrufen der Gefällt-mir Angaben: " + caught.getMessage());

		}

		@Override
		public void onSuccess(Vector<Like> result) {

			for (final Like like : result) {
				final Label nutzername = new Label();
				likePanel.add(nutzername);
				
				pinnwandVerwaltung.getNutzerbyID(like.getNutzerFK(), new AsyncCallback<Nutzer>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler beim abrufen der Gefällt-mir Angaben: " + caught.getMessage());

					}

					@Override
					public void onSuccess(Nutzer result) {
						nutzername.setText(
								result.getVorname() + " " + result.getNachname() + ", " + "@" + result.getNickname());
						
					}

				});
			}

		}

	}

	/**
	 * <b>Nested Class für den schliessen-Button</b> implementiert den
	 * entsprechenden ClickHandler
	 * @author NiklasFuchs
	 */
	class SchliessenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			hide();

		}

	}
}
