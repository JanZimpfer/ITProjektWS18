package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class PinnwandBox extends VerticalPanel {
	
	private VerticalPanel PinnwandPanel = new VerticalPanel();
	
	Nutzer nutzer = new Nutzer();
	
	public PinnwandBox() {
		
	}
	
	public PinnwandBox(Nutzer n) {
		this.nutzer = n;
	}
	
	public void onLoad() {
		this.add(PinnwandPanel);
		RootPanel.get("InhaltDiv").add(PinnwandPanel);
	}

}
