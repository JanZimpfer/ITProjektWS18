package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

/**
 * Klasse zur Erstellung der Headerbox.
 * 
 * @author Jan Zimpfer
 */

public class HeaderBox extends HorizontalPanel {

	/**
	 * Erzeugen eines PinnwandVerwaltung-Objekts um eine Applikationsverwaltung zu
	 * initialisieren.
	 */
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	/**
	 * Instanziierung der GUI Elemente
	 */

	private ProfilBox profilBox = new ProfilBox();
	private Suchleiste suchLeiste = new Suchleiste();
	private VerticalPanel logoutEditPanel = new VerticalPanel();

	private Button logoutButton = new Button("Logout");
	private Button profilEditButton = new Button("Profil bearbeiten");

	private Label deleteCheckLbl = new Label("Sind Sie sich sicher, dass Sie Ihr Nutzerprofil und" + "\n"
			+ "alle damit in Zusammenhang stehende Daten unwiderruflich löschen wollen?");

	private Button openDeleteFormButton = new Button("Profil löschen");
	private Button deleteButton = new Button("Account löschen");
	private Button abortDeletionButton = new Button("Abbrechen");

	private DeleteForm deleteBox = new DeleteForm();

	public HeaderBox() {

		this.addStyleName("headerBox");

		this.add(profilBox);
		this.add(suchLeiste);
		this.add(logoutEditPanel);

		profilBox.setHorizontalAlignment(ALIGN_LEFT);
		suchLeiste.setHorizontalAlignment(ALIGN_CENTER);
		logoutEditPanel.setHorizontalAlignment(ALIGN_RIGHT);

		logoutEditPanel.add(logoutButton);
		logoutEditPanel.add(profilEditButton);
		logoutEditPanel.add(openDeleteFormButton);

		openDeleteFormButton.addClickHandler(new OpenDeleteFormClickHandler());

		/**
		 * Hinzufügen der StyleNamen für CSS-Styling
		 */

		logoutEditPanel.addStyleName("logoutEditPanel");
		logoutButton.addStyleName("headerBtn");
		profilEditButton.addStyleName("headerBtn");
		openDeleteFormButton.addStyleName("headerBtn");

		profilEditButton.addClickHandler(new ChangeClickHandler());
		logoutButton.addClickHandler(new LogoutClickHandler());

		super.onLoad();
	}

	/**
	 * Nested Class für das Bearbeiten des Nutzers
	 */

	public class ChangeClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			final Nutzer n = new Nutzer();
			n.setId(Integer.parseInt(Cookies.getCookie("id")));

			pinnwandVerwaltung.getNutzerbyID(n.getId(), new AsyncCallback<Nutzer>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Fehler beim Auslesen der Nutzerdaten" + caught.getMessage());

				}

				@Override
				public void onSuccess(Nutzer result) {
					ProfilBearbeitenDialogBox profilEditBox = new ProfilBearbeitenDialogBox(result);
					profilEditBox.center();
				}

			});
		}

	}

	/**
	 * Nested Class für den Logout-Button implementiert den ClickHandler zum
	 * Ausloggen aus dem System
	 *
	 */
	class LogoutClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			Anchor signOutLink = new Anchor();

			signOutLink.setHref(Cookies.getCookie("logout"));
			Window.open(signOutLink.getHref(), "_self", "");
		}

	}

	/**
	 * <b>Nested Class einer DialogBox</b>
	 * 
	 * Abfrage ob der User sein Profil löschen möchte
	 */
	private class DeleteForm extends DialogBox {
		private VerticalPanel vPanel = new VerticalPanel();
		private VerticalPanel deletePanel = new VerticalPanel();
		private HorizontalPanel btnPanel = new HorizontalPanel();

		public DeleteForm() {

			this.setText("Profil löschen");
			this.setGlassEnabled(true);
			this.setAnimationEnabled(true);
			this.setAutoHideEnabled(true);

			this.setStylePrimaryName("customDialogbox");

			deleteButton.addClickHandler(new DeleteProfilClickHandler());
			abortDeletionButton.addClickHandler(new AbortDeletionClickHandler());

			deletePanel.add(deleteCheckLbl);
			btnPanel.add(deleteButton);
			btnPanel.add(abortDeletionButton);
			vPanel.add(deletePanel);
			vPanel.add(btnPanel);

			this.add(vPanel);

		}
	}

	/**
	 * <b>Nested Class für den Löschen-Button der HeaderBox</b> implementiert den
	 * ClickHandler zum Öffnen der Sicherheitsabfrage DeleteBox
	 *
	 */
	class OpenDeleteFormClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			deleteBox.center();

		}

	}

	/**
	 * <b>Nested Class für den Löschen-Button der DeleteBox</b> implementiert den
	 * ClickHandler zum Löschen der Nutzerdaten
	 *
	 */
	class DeleteProfilClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			Nutzer n = new Nutzer();
			n.setId(Integer.parseInt(Cookies.getCookie("id")));

			pinnwandVerwaltung.loeschen(n, new DeleteNutzerCallback());

		}

	}

	/**
	 * <b>Nested Class für den Löschen-Button in der DeleteBox</b> Callback Aufruf
	 * zum Löschen der Nutzerdaten. Bei erfolgreicher Löschung werden die
	 * nutzerspezifischen Cookies entfernt
	 *
	 */
	public class DeleteNutzerCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Löschen des Profils" + caught.getMessage());

		}

		@Override
		public void onSuccess(Void result) {
			Cookies.removeCookie("id");
			Cookies.removeCookie("email");
			Window.Location.assign("/");

		}

	}

	/**
	 * <b>Nested Class für den Abbrechen-Button in der DeleteBox</b> implementiert
	 * den ClickHandler für das Schließen der DialogBox
	 *
	 */
	class AbortDeletionClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			deleteBox.hide();

		}

	}

}
