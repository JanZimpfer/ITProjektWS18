package de.hdm.gwt.itprojektws18.client;

import de.hdm.gwt.itprojektws18.client.gui.PinnwandBox;
import de.hdm.gwt.itprojektws18.shared.LoginService;
import de.hdm.gwt.itprojektws18.shared.LoginServiceAsync;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;
import de.hdm.gwt.itprojektws18.client.gui.ProfilBox;
import de.hdm.gwt.itprojektws18.client.gui.AboPinnwandBox;
import de.hdm.gwt.itprojektws18.client.gui.AboBox;
import de.hdm.gwt.itprojektws18.client.gui.HeaderBox;
import de.hdm.gwt.itprojektws18.client.gui.Suchleiste;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ITProjektWS18 implements EntryPoint {

	/**
	 * Deklaration der Klasse LoginInfo fuer die Google API
	 */
	private LoginInfo loginInfo = null;

	/**
	 * Instantiierung von GUI Objekten die fuer den Login notwendig sind
	 */
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginGreet = new Label("Hallo und willkommen im PinnwandSystem");
	private Label loginAufforderung = new Label("Bitte loggen Sie sich mit Ihrem" + "Google Account ein");
	private Anchor signInLink = new Anchor("Einloggen");
	private Anchor signOutLink = new Anchor("Ausloggen");
	private Button loginBtn = new Button("Einloggen");

	private TextBox vornameBox = new TextBox();
	private TextBox nachnameBox = new TextBox();
	private TextBox nicknameBox = new TextBox();

	/**
	 * Erzeugen eines PinnwandVerwaltung-Objekts um eine Applikationsverwaltung zu
	 * initialisieren.
	 */
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	ClientsideSettings clientSettings = new ClientsideSettings();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

//		LoginServiceAsync loginService = GWT.create(LoginService.class);
//		loginService.login(GWT.getHostPageBaseURL()+
//				"ITProjektWS18.html", new LoginCallback());
	

		// Anlegen der <code>Box</code> Objekte, welche zum RootPanel hinzugefügt
		// werden.
		HeaderBox hBox = new HeaderBox();
		PinnwandBox pBox = new PinnwandBox();
		AboBox aBox = new AboBox();

		/*
		 * Befuellen des RootPanels
		 */

		RootPanel.get("SuchProfilLogout").add(hBox);
		RootPanel.get("InhaltDiv").add(pBox);
		RootPanel.get("AboDiv").add(aBox);

	}

	private void loadPinnwandVerwaltung() {

		ProfilBox profilbox = new ProfilBox();
		Suchleiste suchleiste = new Suchleiste();
		PinnwandBox pinnwandbox = new PinnwandBox();
		AboBox abobox = new AboBox();
		AboPinnwandBox abopwbox = new AboPinnwandBox();
//			Button 	profilButton = new Button ("Mein Profil");

//			RootPanel.get("header").add(profilbox);
//			RootPanel.get("header").add(suchleiste);
//			RootPanel.get("InhaltBereich").add(pinnwandbox);
//			RootPanel.get("AboBereich").add(abobox);
//			RootPanel.get("AboBereich").add(abopwbox);

	}

		/**
		 * Methode zum Anzeigen der API
		 */
	private void loadLogin() {

		loginBtn.addClickHandler(new loginButtonClickHandler());
		loginBtn.addStyleName("submitButton");
		loginGreet.setStylePrimaryName("loginGreet");
		loginPanel.setStylePrimaryName("loginPanel");
		loginAufforderung.setStylePrimaryName("loginAufforderung");
		loginPanel.add(loginGreet);
		loginPanel.add(loginAufforderung);
		loginPanel.add(loginBtn);
		RootPanel.get("LoginBereich").add(loginPanel);

	}

		/**
		 * <b>Nested Class fuer den Login Button</b>
		 * implementiert den entsprechenden ClickHandler
		 */
		
	class loginButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			signInLink.setHref(loginInfo.getLoginUrl());
			Window.open(signInLink.getHref(), "_self", "");
		}
	}

	
		/**
		 * </b>Nested Class fuer den Login Callback</b>
		 * Zunaechst wird ueberprueft ob der User bereits eingeloggt ist.
		 * 
		 * Anschliessend wird ueberprueft ob die E-Mail Adresse bereits in
		 * der Datenbank vorhanden ist.
		 */
	class LoginCallback implements AsyncCallback<LoginInfo> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Login: " + caught.getMessage());

		}


		@Override
		public void onSuccess(LoginInfo result) {
			loginInfo = result;

			if (loginInfo.isLoggedIn()) {
				pinnwandVerwaltung.checkEmail(loginInfo.getEmailAddress(), new FindeNutzerCallback());

			}

			else {
				loadLogin();
			}
		}

	}

		
		/**
		 * <b>Nested Class fuer den AsyncCallback checkEmail</b>
		 * Ist der Nutzer bereits vorhanden werden zwei Cookies erstellt
		 * und das Pinnwandsystem wird geladen.
		 * 
		 * Ist der User nicht vorhanden startet eine Registrierungsabfrage
		 */		
	class FindeNutzerCallback implements AsyncCallback<Nutzer> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Login: " + caught.getMessage());
		}

		@Override
		public void onSuccess(Nutzer result) {

			if (result != null) {
				Cookies.setCookie("email", result.getEmail());
				Cookies.setCookie("id", result.getId() + "");
				Cookies.setCookie("ausloggen", loginInfo.getLogoutUrl());
				loadPinnwandVerwaltung();
			}

			else {
				RegistrierungsformDialogBox dlgBox = new RegistrierungsformDialogBox(loginInfo.getEmailAddress());
				dlgBox.center();
			}
		}

	}

		
		/**
		 * <b>Nested Class einer Registrierungsform</b>
		 * 
		 * Abfrage ob der User sich registrieren moechte
		 */
	class RegistrierungsformDialogBox extends DialogBox {

			/**
			 * Instantiierung der notwendigen GUI Objekte
			 */
		private Label abfrage = new Label("Sie sind noch nicht registriert."
				+ "Wenn Sie einen Nutzer anlegen m�chten, f�llen Sie bitte folgendes Formular aus.");
		private Button jaBtn = new Button("Registrieren");
		private Button neinBtn = new Button("Abbrechen");
		private VerticalPanel vPanel = new VerticalPanel();
		private HorizontalPanel btnPanel = new HorizontalPanel();

			/**
			 * Ein String der die E-Mail Adresse speichert
			 */
		private String googleMail = "";

			
			/**
			 * Aufruf des Konstruktors
			 * @param mail
			 */
		public RegistrierungsformDialogBox(String mail) {
			googleMail = mail;
			jaBtn.addClickHandler(new NutzerAnlegenClickHandler());
			neinBtn.addClickHandler(new NutzerNichtAnlegenClickHandler());
			vPanel.add(abfrage);
			vPanel.add(vornameBox);
			vPanel.add(nachnameBox);
			vPanel.add(nicknameBox);
			btnPanel.add(jaBtn);
			btnPanel.add(neinBtn);
			vPanel.add(btnPanel);
			this.add(vPanel);
		}
	}

		
		/**
		 * <b>Nested Class in der <class>RegistrierungsformDialogBox</class></b>
		 * 
		 * implementiert den entsprechenden ClickHandler
		 */
	class NutzerAnlegenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			pinnwandVerwaltung.erstelleNutzer(vornameBox.getText(), nachnameBox.getText(), nicknameBox.getText(), Cookies.getCookie("email"), new NutzerAnlegenCallback());

		}

	}

		/**
		 * <b>Nested Class in der <class>RegistrierungsformDialogBox</class></b>
		 * 
		 * implementiert den entsprechenden ClickHandler
		 */
	class NutzerNichtAnlegenClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// hide(); - funktioniert nicht
			signOutLink.setHref(loginInfo.getLogoutUrl());
			Window.open(signOutLink.getHref(), "_self", "");
		}

	}

		
		/**
		 * <b>Nested Class fuer die Registrierungsform</b>
		 * 
		 * Callback Aufruf um einen Nutzer anzulegen
		 */
	class NutzerAnlegenCallback implements AsyncCallback<Nutzer> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Ihr User konnte nicht erstellt werden" + caught.getMessage());
		}


		@Override
		public void onSuccess(Nutzer result) {
			Window.alert("Ihr Nutzer wurde angelegt");
			Cookies.setCookie("ausloggen", loginInfo.getLogoutUrl());
			Cookies.setCookie("email", result.getEmail());
			Cookies.setCookie("id", result.getId() + "");
			// hide(); - funktioniert nicht
			
			pinnwandVerwaltung.erstellePinnwand(result, new PinnwandAnlegenCallback());

		}

	}

	class PinnwandAnlegenCallback implements AsyncCallback<Pinnwand> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Ihre Pinnwand konnte nicht erstellt werden" + caught.getMessage());

		}

		@Override
		public void onSuccess(Pinnwand result) {
			Window.alert("Ihre Pinnwand wurde angelegt");
			loadPinnwandVerwaltung();
		}

	}

}
