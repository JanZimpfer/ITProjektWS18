package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.client.ITProjektWS18;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class HeaderBox extends HorizontalPanel {

	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	private ProfilBox profilBox = new ProfilBox();
	private Suchleiste suchLeiste = new Suchleiste();
	private VerticalPanel logoutEditPanel = new VerticalPanel();

	private Button logoutButton = new Button("Logout");
	private Button profilEditButton = new Button("Profil bearbeiten");

	private Label deleteCheckLbl = new Label("Sind Sie sich sicher, dass Sie Ihr Nutzerprofil und" + "\n"
			+ "alle damit in Zusammenhang stehende Daten unwiderruflich löschen wollen?");

	private Button openDeleteFormButton = new Button("Profil löschen");
	private Button deleteButton = new Button("Nutzerdaten endgültig löschen");
	private Button abortDeletionButton = new Button("Abbruch");

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

		logoutEditPanel.addStyleName("logoutEditPanel");
		logoutButton.addStyleName("headerBtn");
		profilEditButton.addStyleName("headerBtn");
		openDeleteFormButton.addStyleName("headerBtn");

		profilEditButton.addClickHandler(new ChangeClickHandler());

		super.onLoad();
	}

	public class ChangeClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			final Nutzer n = new Nutzer();
			// n.setId(Integer.parseInt(Cookies.getCookie("id")));
			n.setId(3);
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

	// Schablone der DialogBox, die das Löschen des Profils ermöglicht
	private class DeleteForm extends DialogBox {
		private VerticalPanel deletePanel = new VerticalPanel();

		public DeleteForm() {

			deleteButton.addClickHandler(new DeleteProfilClickHandler());
			abortDeletionButton.addClickHandler(new AbortDeletionClickHandler());

			deletePanel.add(deleteCheckLbl);
			deletePanel.add(deleteButton);
			deletePanel.add(abortDeletionButton);
			this.add(deletePanel);

		}
	}

	// ClickHandler, der das Öffnen der DeleteForm zum Löschen des Profils
	// ermöglicht
	class OpenDeleteFormClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
//			dlgBox.hide();
			deleteBox.center();

		}

	}

	// ClickHandler, der die Löschung des Profils des eingeloggten Nutzers
	// durchführt
	class DeleteProfilClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			// Später Cookie Id anstatt Id = 3
			Nutzer n = new Nutzer();
			// n.setId(Integer.parseInt(Cookies.getCookie("id")));
			n.setId(3);
			pinnwandVerwaltung.loeschen(n, new DeleteNutzerCallback());

		}

	}

	public class DeleteNutzerCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Löschen des Profils" + caught.getMessage());

		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("Ihr Profil wurde erfolgreich gelöscht!");
			// Laden der Login-Seie ausstehend

		}

	}

	class AbortDeletionClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			deleteBox.hide();

		}

	}

}
