package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class PinnwandBox extends VerticalPanel {

	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	public PinnwandBox() {

		UebersichtBox uebersichtbox = new UebersichtBox();

		RootPanel.get("InhaltDiv").clear();
		Nutzer n = new Nutzer();
		n.setId(Integer.parseInt(Cookies.getCookie("id")));

		this.addStyleName("pinnwandBox");

		this.add(uebersichtbox);

		super.onLoad();

	}

	public PinnwandBox(int nutzerId) {

		Nutzer n = new Nutzer();
		n.setId(nutzerId);

		this.addStyleName("pinnwandBox");

		UebersichtBox uebersichtbox = new UebersichtBox(n.getId());

		this.add(uebersichtbox);

		super.onLoad();

	}
}