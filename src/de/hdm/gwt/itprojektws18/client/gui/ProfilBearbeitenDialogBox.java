package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class ProfilBearbeitenDialogBox extends DialogBox {

	/**
	 * Instanziierung eines PinnwandVerwaltung-Objekts um eine
	 * Applikationsverwaltung zu initialisieren
	 */
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	
	Nutzer nutzer = new Nutzer();
	
	/**
	 * Deklarierung der benötigten Widgets
	 */
	private VerticalPanel editPanel = new VerticalPanel();
	
	private Label nicknameLbl = new Label("Neuer Nickname: ");
	private Label vornameLbl = new Label("Neuer Vorname: ");
	private Label nachnameLbl = new Label("Neuer Nachname: ");
	
	private TextBox nicknameTextbox = new TextBox();
	private TextBox vornameTextbox = new TextBox();
	private TextBox nachnameTextbox = new TextBox();
	
	private Button changeButton = new Button("Aenderungen speichern");
	private Button closeButton = new Button("Schließen");
	
	public ProfilBearbeitenDialogBox() {
		
	}
	
	public ProfilBearbeitenDialogBox(Nutzer n) {
		
		this.nutzer = n;
		
		this.setText("Profil bearbeiten");
		this.setGlassEnabled(true);
		this.setAnimationEnabled(true);
		this.setAutoHideEnabled(true);
		
		this.setStylePrimaryName("gwt-DialogBox");

		nicknameTextbox.setSize("200px", "25px");
		vornameTextbox.setSize("200px", "25px");
		nachnameTextbox.setSize("200px", "25px");
		
		nutzer.setId(Integer.parseInt(Cookies.getCookie("id")));

		nicknameTextbox.setText(n.getNickname());
		vornameTextbox.setText(n.getVorname());
		nachnameTextbox.setText(n.getNachname());
		
		changeButton.addClickHandler(new ProfilEditClickHandler());
		closeButton.addClickHandler(new CloseClickHandler());
		
		editPanel.add(nicknameLbl);
		editPanel.add(nicknameTextbox);
		editPanel.add(vornameLbl);
		editPanel.add(vornameTextbox);
		editPanel.add(nachnameLbl);
		editPanel.add(nachnameTextbox);
		editPanel.add(changeButton);
		editPanel.add(closeButton);
		
		this.add(editPanel);
	}
	
	class ProfilEditClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			nutzer.setVorname(vornameTextbox.getText());
			nutzer.setNachname(nachnameTextbox.getText());
			nutzer.setNickname(nicknameTextbox.getText());

			pinnwandVerwaltung.speichern(nutzer, new NutzerEditCallback());

		}
		
	public class NutzerEditCallback implements AsyncCallback<Nutzer> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Bearbeiten des Nutzers: " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Nutzer result) {
			
			
			if (result == null) {
				Window.alert("Nickname bereits vergeben");
			} else {
				
				PinnwandBox pBox = new PinnwandBox();
				RootPanel.get("InhaltDiv").clear();
				RootPanel.get("InhaltDiv").add(pBox);
			}
		
			
		}
		
	}
		
	}
	class CloseClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			hide();
		}

	}
}
