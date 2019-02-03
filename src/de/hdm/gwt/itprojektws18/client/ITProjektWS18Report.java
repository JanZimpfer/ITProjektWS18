package de.hdm.gwt.itprojektws18.client;

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

import de.hdm.gwt.itprojektws18.client.gui.report.ReportSelectMenu;
import de.hdm.gwt.itprojektws18.shared.LoginService;
import de.hdm.gwt.itprojektws18.shared.LoginServiceAsync;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.ReportGeneratorAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;

/**
 * Entry Point Klasse für den Report des Projekts tellIT - ITProjektWS18
 *
 */
@SuppressWarnings("unused")
public class ITProjektWS18Report implements EntryPoint{
	
	/**
	 * Erzeugen eines Reportgenerator-Objekts um eine Reportverwaltung zu
	 * initialisieren.
	 */
	ReportGeneratorAsync reportVerwaltung = ClientsideSettings.getReportGenerator();
	
	private LoginInfo loginInfo = null;
	
	Nutzer n = new Nutzer();

	private Label vornameLabel = new Label("Vorname");
	private Label nachnameLabel = new Label("Nachname");
	private Label nicknameLabel = new Label("Nickname");

	private TextBox vornameEingabe = new TextBox();
	private TextBox nachnameEingabe = new TextBox();
	private TextBox nicknameEingabe = new TextBox();

	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginGreet = new Label("Willkommen bei tellIT");
	private Label loginMsg = new Label("Bitte loggen Sie sich mit Ihrem Google Account ein.");
	private Anchor signInLink = new Anchor("Einloggen");
	private Anchor signOutLink = new Anchor("Ausloggen");
	private Button loginBtn = new Button("Login");

	
	@Override
	public void onModuleLoad() {
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL() + "ITProjektWS18Report.html", new AsyncCallback<LoginInfo>() {

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

		RootPanel.get("contentReport").clear();
		RootPanel.get("contentReport").add(loginPanel);
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
		reportVerwaltung.findNutzerByEmail(loginInfo.getEmailAddress(), new NutzerAbrufCallback());
	}
	
	private void loadReportgenerator() {

		ReportSelectMenu reportMenu = new ReportSelectMenu();
		RootPanel.get("header").add(reportMenu);
	}
	
	/**
	 * <b>Nested Class fuer den AsyncCallback findNutzerByEmail</b>
	 * 
	 * Ist der Nutzer vorhanden: Setzen der Cookies zur späteren
	 * Nutzeridentifikation und laden der PinnwandVerwaltung.
	 * 
	 * Ist der Nutzer nicht vorhanden, so erscheint eine Fehlermeldung
	 * mit der Bitte um vorherige Registrierung im System
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
				RootPanel.get("contentReport").clear();
				loadReportgenerator();
			}

			else {
				Window.alert("Bitte registrieren Sie sich zuerst bei tellIT.");
				signOutLink.setHref(loginInfo.getLogoutUrl());
				Window.open(signOutLink.getHref(), "_self", "");
			}
		}

	}
	
}
