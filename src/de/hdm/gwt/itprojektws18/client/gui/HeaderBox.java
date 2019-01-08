package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class HeaderBox extends HorizontalPanel {

	private HorizontalPanel headerBox = new HorizontalPanel();
	private HorizontalPanel inhalte = new HorizontalPanel();
	private ProfilBox ProfilBox = new ProfilBox();
	private Suchleiste suchLeiste = new Suchleiste();
	private VerticalPanel LogoutEditPanel = new VerticalPanel();

	private Button LogoutButton = new Button("Logout");
	private Button ProfilEditButton = new Button("Profil bearbeiten");

	Label nicknameLbl = new Label("Neuer Nickname: ");
	Label vornameLbl = new Label("Neuer Vorname: ");
	Label nachnameLbl = new Label("Neuer Nachname: ");

	TextBox nicknameTextbox = new TextBox();
	TextBox vornameTextbox = new TextBox();
	TextBox nachnameTextbox = new TextBox();

	Button changeButton = new Button("Aenderungen speichern");
	Button closeButton = new Button("Schließen");

	// Panels hinzufügen
//	HorizontalPanel EditPanel = new HorizontalPanel();

	EditForm edtForm = new EditForm();

	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	ClientsideSettings clientSettings = new ClientsideSettings();

	public HeaderBox() {

	}

	public void onLoad() {

		this.add(headerBox);
		this.addStyleName("headerBox");

//		headerBox.add(EditPanel);
		headerBox.add(inhalte);

		inhalte.add(ProfilBox);
		inhalte.add(suchLeiste);
		inhalte.add(LogoutEditPanel);

//		EditPanel.add(nicknameLbl);
//		EditPanel.add(nicknameEdit);
//		EditPanel.add(vornameLbl);
//		EditPanel.add(vornameEdit);
//		EditPanel.add(nachnameLbl);
//		EditPanel.add(nachnameEdit);
//		EditPanel.add(changeButton);

		LogoutEditPanel.add(LogoutButton);
		LogoutEditPanel.add(ProfilEditButton);

		LogoutEditPanel.addStyleName("logoutEditPanel");
		LogoutButton.addStyleName("logoutButton");
//		ProfilEditButton.addStyleName("profilEditButton");

		ProfilEditButton.addClickHandler(new ChangeClickHandler());

		RootPanel.get("SuchProfilLogout").add(headerBox);

	}

	public class ChangeClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			EditForm dlgBox = new EditForm();
			dlgBox.center();
			dlgBox.show();
//			dlgBox.hide();

		}

	}

	private class EditForm extends DialogBox {
		private VerticalPanel EditPanel = new VerticalPanel();

		public EditForm() {

			changeButton.addClickHandler(new ProfilEditClickHandler());
//			closeButton.addClickHandler(new CloseClickHandler());
			EditPanel.add(nicknameLbl);
			EditPanel.add(nicknameTextbox);
			EditPanel.add(vornameLbl);
			EditPanel.add(vornameTextbox);
			EditPanel.add(nachnameLbl);
			EditPanel.add(nachnameTextbox);

			EditPanel.add(changeButton);
			EditPanel.add(closeButton);
			this.add(EditPanel);

		}

	}

	class ProfilEditClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			Nutzer n = new Nutzer();
			n.setId(1);
			n.setNickname( "'" + nicknameTextbox.getText() + "'");
			n.setVorname( "'" + vornameTextbox.getText() + "'");
			n.setNachname( "'" + nachnameTextbox.getText() + "'");
			pinnwandVerwaltung.speichern(n, new NutzerEditCallback());

		}
	}

	public class NutzerEditCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSuccess(Void result) {
			Window.alert("LULULU");


		}

	}

//	class CloseClickHandler implements ClickHandler {
//
//
//		public void onClick(ClickEvent event) {
//			EditForm.this.hide();
//			
//		}
//		
//	}
}
