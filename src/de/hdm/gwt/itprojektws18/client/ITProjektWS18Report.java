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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.shared.LoginService;
import de.hdm.gwt.itprojektws18.shared.LoginServiceAsync;
import de.hdm.gwt.itprojektws18.shared.ReportGeneratorAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class ITProjektWS18Report implements EntryPoint{
	
	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private VerticalPanel vPanelBar = new VerticalPanel();
	private HorizontalPanel hPanelBar = new HorizontalPanel();
	private Anchor signInLink = new Anchor();
	private Anchor signOutLink = new Anchor("Sign Out");
	private Label welcomeMessage = new Label("Report Generator");
	private Label loginMessage = new Label("Bitte loggen Sie sich mit Ihrem Google Account ein");
	private Button logoutButton = new Button("Ausloggen");
	private Button loginButton = new Button("Login");
	private static ReportGeneratorAsync reportVerwaltung = ClientsideSettings.getReportGenerator();
	
	@Override
	public void onModuleLoad() {
		Window.alert("Hier her komme ich");
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL() + "ITProjektWS18Report.html", new LoginCallback());


	}
	/**
	 * Erstellen eines LoginCallbacks 
	 *
	 */
	class LoginCallback implements AsyncCallback<LoginInfo> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Login: " + caught.getMessage());
		}

		@Override
		public void onSuccess(LoginInfo result) {
			Window.alert("Hier her komme ich");

			loginInfo = result;
			if (loginInfo.isLoggedIn()) {
				reportVerwaltung.findNutzerByEmail(loginInfo.getEmailAddress(), new FindNutzerCallback());

			} else {
				loadLogin();
			}
		}

	}
	/**
	 * CLickHandler für den LoginButton
	 *
	 */
	class loginButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			signInLink.setHref(loginInfo.getLoginUrl());
			Window.open(signInLink.getHref(), "_self", "");
		}

	}
	/**
	 * 
	 */
	private void loadLogin() {
		Window.alert("Hier her komme ich");

		loginButton.addClickHandler(new loginButtonClickHandler());
//		loginButton.setStylePrimaryName("loginButton");
//		welcomeMessage.setStylePrimaryName("landingPageWelcomeMessage");
//		loginPanel.setStylePrimaryName("loginPanel");
//		loginMessage.setStylePrimaryName("landingPageLoginMessage");
		loginPanel.add(welcomeMessage);
		loginPanel.add(loginMessage);
		loginPanel.add(loginButton);
		RootPanel.get("contentReport").add(loginPanel);

	}
	/**
	 * Callback-Methode um Nutzer zu suchen
	 *
	 */
	class FindNutzerCallback implements AsyncCallback<Nutzer> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Login: " + caught.getMessage());
		}

		@Override
		public void onSuccess(Nutzer result) {
			if (result != null) {
				Cookies.setCookie("id", result.getId() + "");
				loadReportgenerator();
			} else {
				Window.alert("Bitte registrieren Sie sich im Pinnwand-System.");
				signOutLink.setHref(loginInfo.getLogoutUrl());
				Window.open(signOutLink.getHref(), "_self", "");
			}
		}

	}
	/**
	 * ClickHandler für den logout
	 *
	 */
	public class logoutClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			signOutLink.setHref(loginInfo.getLogoutUrl());
			Window.open(signOutLink.getHref(), "_self", "");

		}
	}
	private void loadReportgenerator() {
//		vPanelBar.add(logoutButton);
//		Cookies.setCookie("logout", loginInfo.getLogoutUrl());
//		RootPanel.get("logout").add(logoutButton);

//		ReportSelectMenu reportMenu = new ReportSelectMenu();
		RootPanel.get("contentReport").clear();

		
	
		signOutLink.setHref(loginInfo.getLogoutUrl());
	}
	
}
