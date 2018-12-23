package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;

public class Suchleiste extends HorizontalPanel {
	
	private HorizontalPanel suchleiste = new HorizontalPanel ();
	private	SuggestBox textfeld = new SuggestBox ();
	private Button 	sucheButton = new Button ("Suche!");
	
	
	public Suchleiste () {
		
	suchleiste.setSpacing(2);
		
	}
	
	public void onLoad() {
		
	this.addStyleName("suchleiste");
	this.add(suchleiste);
	
	suchleiste.add(textfeld);
	suchleiste.add(sucheButton);

	
	sucheButton.addStyleName("suchButton");
	textfeld.addStyleName("suchleiste");
	
	RootPanel.get("header").add(suchleiste);
		
	}

}
