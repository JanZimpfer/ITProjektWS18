package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.*;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;

public class HeaderBox extends HorizontalPanel{

	private HorizontalPanel headerBox = new HorizontalPanel();
	private ProfilBox profilBox = new ProfilBox();
	private Suchleiste suchLeiste = new Suchleiste();
	
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	ClientsideSettings clientSettings = new ClientsideSettings();
	
	public HeaderBox() {
		headerBox.setSpacing(2);
		headerBox.setCellHorizontalAlignment(profilBox, ALIGN_LEFT);
		headerBox.setCellHorizontalAlignment(suchLeiste, ALIGN_RIGHT);
	}
	
	public void onLoad() {
		
		this.addStyleName("headerBox");
		this.add(headerBox);
		
		headerBox.add(profilBox);
		headerBox.add(suchLeiste);
		
		RootPanel.get("header").add(headerBox);
		
	}
}
