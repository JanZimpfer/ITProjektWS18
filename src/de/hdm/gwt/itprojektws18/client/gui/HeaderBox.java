package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;

public class HeaderBox extends HorizontalPanel {

	private HorizontalPanel headerBox = new HorizontalPanel();
	private HorizontalPanel inhalte = new HorizontalPanel();
	private ProfilBox ProfilBox = new ProfilBox();
	private Suchleiste suchLeiste = new Suchleiste();
	private VerticalPanel LogoutEditPanel = new VerticalPanel();

	private Button LogoutButton = new Button("Logout");
//	private Button ProfilEditButton = new Button("Profil bearbeiten");
	
	Label nicknameLbl = new Label("Neuer Nickname: ");
	Label vornameLbl = new Label("Neuer Vorname: ");
	Label nachnameLbl = new Label("Neuer Nachname: ");

	// Textboxen hinzufügen
	TextBox nicknameEdit = new TextBox();
	TextBox vornameEdit = new TextBox();
	TextBox nachnameEdit = new TextBox();

	// Buttons hinzufügen
	Button changeButton = new Button("Aenderungen speichern");
	

	// Panels hinzufügen
	HorizontalPanel EditPanel = new HorizontalPanel();
	

	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	ClientsideSettings clientSettings = new ClientsideSettings();

	public HeaderBox() {

	}

	public void onLoad() {

		this.add(headerBox);
		this.addStyleName("headerBox");

		headerBox.add(EditPanel);
		headerBox.add(inhalte);
		
		inhalte.add(ProfilBox);
		inhalte.add(suchLeiste);
		inhalte.add(LogoutEditPanel);
		
		EditPanel.add(nicknameLbl);
		EditPanel.add(nicknameEdit);
		EditPanel.add(vornameLbl);
		EditPanel.add(vornameEdit);
		EditPanel.add(nachnameLbl);
		EditPanel.add(nachnameEdit);
		EditPanel.add(changeButton);
		

		LogoutEditPanel.add(LogoutButton);
//		LogoutEditPanel.add(ProfilEditButton);

		LogoutEditPanel.addStyleName("logoutEditPanel");
		LogoutButton.addStyleName("logoutButton");
//		ProfilEditButton.addStyleName("profilEditButton");

		

		RootPanel.get("SuchProfilLogout").add(headerBox);

	}


}
	