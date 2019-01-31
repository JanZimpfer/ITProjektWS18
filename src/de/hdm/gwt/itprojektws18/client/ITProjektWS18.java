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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
@SuppressWarnings("unused")
public class ITProjektWS18 implements EntryPoint {

	/**
	 * Erzeugen eines PinnwandVerwaltung-Objekts um eine Applikationsverwaltung zu
	 * initialisieren.
	 */
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	private LoginInfo loginInfo = null;
	Nutzer n = new Nutzer();

	private Label vornameLabel = new Label("Vorname");
	private Label nachnameLabel = new Label("Nachname");
	private Label nicknameLabel = new Label("Nickname");

	private TextBox vornameEingabe = new TextBox();
	private TextBox nachnameEingabe = new TextBox();
	private TextBox nicknameEingabe = new TextBox();

	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginGreet = new Label("Willkommen im HdM Network");
	private Label loginMsg = new Label("Bitte loggen Sie sich mit Ihrem Google Account ein.");
	private Anchor signInLink = new Anchor("Einloggen");
	private Anchor signOutLink = new Anchor("Ausloggen");
	private Button loginBtn = new Button("Login");

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL() + "ITProjektWS18.html", new AsyncCallback<LoginInfo>() {

			@Override
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new HTML(caught.toString()));
			}

			@Override
			public void onSuccess(LoginInfo result) {
				loginInfo = result;

				if (loginInfo.isLoggedIn()) {
					loadNutzerAbruf(result);
				} else {
					loadLogin();
				}
			}
		});
	}

	/**
	 * Die Metode loadLogin() wird aufgerufen wenn festgestellt wird, dass der User
	 * nicht eingeloggt ist (Ergebnis aus LoginInfo)
	 * 
	 */
	private void loadLogin() {

		loginBtn.addClickHandler(new LoginButtonClickHandler());
		loginBtn.setStylePrimaryName("loginButton");
		loginGreet.setStylePrimaryName("loginBegruessung");
		loginPanel.setStylePrimaryName("loginPanel");
		loginMsg.setStylePrimaryName("loginAufforderung");
		loginPanel.add(loginGreet);
		loginPanel.add(loginMsg);
		loginPanel.add(loginBtn);

		RootPanel.get("InhaltDiv").clear();
		RootPanel.get("InhaltDiv").add(loginPanel);
	}

	/**
	 * <b>Nested Class für den Login-Button</b>
	 * 
	 * Implementiert den entsprechenden ClickHandler. Verweis auf die Google Site
	 * zum Einloggen.
	 *
	 */
	class LoginButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			signInLink.setHref(loginInfo.getLoginUrl());
			Window.open(signInLink.getHref(), "_self", "");

		}

	}

	/**
	 * Die Methode loadNutzerAbruf() wird aufgerufen, wenn festgestellt wird dass
	 * der User eingeloggt ist (Ergebnis aus LoginInfo)
	 * 
	 * Hier wird überprüft ob der eingeloggte Nutzer bereits im Pinnwandsystem
	 * vorhanden ist.
	 * 
	 * @param loginInfo
	 */
	private void loadNutzerAbruf(LoginInfo loginInfo) {
		pinnwandVerwaltung.checkEmail(loginInfo.getEmailAddress(), new NutzerAbrufCallback());
	}

	/**
	 * Die Methode loadPinnwandVerwaltung() wird aufgerufen, wenn der User bereits
	 * vorhanden ist und eingeloggt ist.
	 */
	private void loadPinnwandVerwaltung() {

		RootPanel.get("Header").clear();
		RootPanel.get("InhaltDiv").clear();
		RootPanel.get("AboDiv").clear();
		/**
		 * Anlegen der Boxen die zum RootPanel hinzugefügt werden sollen
		 */
		HeaderBox hBox = new HeaderBox();
		PinnwandBox pBox = new PinnwandBox();
		AboBox aBox = new AboBox();

		/**
		 * Hinzufügen zum RootPanel
		 */
		RootPanel.get("Header").add(hBox);
		RootPanel.get("InhaltDiv").add(pBox);
		RootPanel.get("AboDiv").add(aBox);

		signOutLink.setHref(loginInfo.getLogoutUrl());
	}

	/**
	 * <b>Nested Class fuer den AsyncCallback checkEmail</b>
	 * 
	 * Ist der Nutzer vorhanden: Setzen der Cookies zur späteren
	 * Nutzeridentifikation und laden der PinnwandVerwaltung.
	 * 
	 * Ist der Nutzer nicht vorhanden: Starten einer Registrierungs-Abfrage
	 */
	class NutzerAbrufCallback implements AsyncCallback<Nutzer> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Login: " + caught.getMessage());
		}

		@Override
		public void onSuccess(Nutzer result) {

			if (result != null) {
				Cookies.setCookie("email", result.getEmail());
				Cookies.setCookie("id", result.getId() + "");
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
	 * Abfrage ob der User sich registrieren möchte
	 */
	class RegistrierungsformDialogBox extends DialogBox {

		/**
		 * Instantiierung der notwendigen GUI Objekte
		 */
		private Label abfrage = new Label("Sie sind noch nicht registriert."
				+ "Wenn Sie einen Nutzer anlegen möchten, füllen Sie bitte folgendes Formular aus.");
		private Button jaBtn = new Button("Registrieren");
		private Button neinBtn = new Button("Abbrechen");
		private VerticalPanel vPanel = new VerticalPanel();
		private HorizontalPanel btnPanel = new HorizontalPanel();

		/**
		 * Ein String der die E-Mail Adresse speichert
		 */
		private String googleMail;

		/**
		 * Aufruf des Konstruktors
		 * 
		 * @param mail
		 */
		public RegistrierungsformDialogBox(String mail) {

			googleMail = mail;
			
			this.setText("Registrierung @tellIT");
			this.setGlassEnabled(true);
			this.setAnimationEnabled(true);
			this.setAutoHideEnabled(true);
			
			this.setStylePrimaryName("customDialogbox");

			jaBtn.addClickHandler(new NutzerAnlegenClickHandler(this));
			neinBtn.addClickHandler(new NutzerNichtAnlegenClickHandler(this));

			vPanel.add(abfrage);
			vPanel.add(vornameLabel);
			vPanel.add(vornameEingabe);
			vPanel.add(nachnameLabel);
			vPanel.add(nachnameEingabe);
			vPanel.add(nicknameLabel);
			vPanel.add(nicknameEingabe);
			btnPanel.add(jaBtn);
			btnPanel.add(neinBtn);
			vPanel.add(btnPanel);

			this.add(vPanel);
		}

		/**
		 * <b>Nested Class in der <class>RegistrierungsformDialogBox</class></b>
		 * 
		 * implementiert den entsprechenden ClickHandler, falls ein Nutzer angelegt
		 * werden soll.
		 */
		class NutzerAnlegenClickHandler implements ClickHandler {
			RegistrierungsformDialogBox regForm;

			public NutzerAnlegenClickHandler(RegistrierungsformDialogBox registrierungsformDialogBox) {
				this.regForm = registrierungsformDialogBox;
			}

			@Override
			public void onClick(ClickEvent event) {
				pinnwandVerwaltung.erstelleNutzer(vornameEingabe.getText(), nachnameEingabe.getText(),
						nicknameEingabe.getText(), loginInfo.getEmailAddress(), new NutzerAnlegenCallback(regForm));

			}
		}

		/**
		 * <b>Nested Class für die Registrierungsform</b>
		 * 
		 * Callback Aufruf um einen Nutzer anzulegen
		 */
		class NutzerAnlegenCallback implements AsyncCallback<Nutzer> {
			RegistrierungsformDialogBox regForm;

			public NutzerAnlegenCallback(RegistrierungsformDialogBox registrierungsformDialogBox) {
				this.regForm = registrierungsformDialogBox;
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Ihr User konnte nicht erstellt werden" + caught.getMessage());
			}

			@Override
			public void onSuccess(Nutzer result) {

				if (result == null) {
					Window.alert("Nickname bereits vergeben");
				} else {

					/**
					 * Setzen der Cookies zur späteren Nutzeridentifikation
					 */
					Cookies.setCookie("email", result.getEmail());
					Cookies.setCookie("id", result.getId() + "");
					Cookies.setCookie("vorname", result.getVorname());
					Cookies.setCookie("nachname", result.getNachname());
					Cookies.setCookie("nickname", result.getNickname());

					this.regForm.hide();

					pinnwandVerwaltung.erstellePinnwand(result, new PinnwandAnlegenCallback());
				}

			}

		}

		class PinnwandAnlegenCallback implements AsyncCallback<Pinnwand> {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Ihre Pinnwand konnte nicht erstellt werden" + caught.getMessage());

			}

			@Override
			public void onSuccess(Pinnwand result) {
				loadPinnwandVerwaltung();
			}

		}

		/**
		 * <b>Nested Class in der <class>RegistrierungsformDialogBox</class></b>
		 * 
		 * implementiert den entsprechenden ClickHandler, falls kein User angelegt
		 * werden soll.
		 */
		class NutzerNichtAnlegenClickHandler implements ClickHandler {
			RegistrierungsformDialogBox regForm;

			public NutzerNichtAnlegenClickHandler(RegistrierungsformDialogBox registrierungsformDialogBox) {
				this.regForm = registrierungsformDialogBox;
			}

			@Override
			public void onClick(ClickEvent event) {
				this.regForm.hide();
			}

		}
	}

}
