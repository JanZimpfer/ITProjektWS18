package de.hdm.gwt.itprojektws18.client.gui;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;

public class PinnwandBox extends VerticalPanel {

	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	

	public PinnwandBox() {

		UebersichtBox uebersichtbox = new UebersichtBox();
		
		RootPanel.get("InhaltDiv").clear();
		Nutzer n = new Nutzer();
		n.setId(3);
		
		this.addStyleName("pinnwandBox");

		this.add(uebersichtbox);

		super.onLoad();

	}

	public PinnwandBox(int nutzerId) {
		
		RootPanel.get("InhaltDiv").clear();
		Nutzer n = new Nutzer();
		n.setId(nutzerId);
		
		this.addStyleName("pinnwandBox");
		
		UebersichtBox uebersichtbox = new UebersichtBox(n.getId());

		this.add(uebersichtbox);

		super.onLoad();

	}
}