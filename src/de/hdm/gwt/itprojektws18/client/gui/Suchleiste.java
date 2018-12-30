package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;

public class Suchleiste extends HorizontalPanel {
	
	private HorizontalPanel suchleiste = new HorizontalPanel ();
	private MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	private Button sucheButton = new Button ("Suche");
	private SuggestBox textfeld = new SuggestBox (oracle);
	

	
	public Suchleiste () {
		
	suchleiste.setSpacing(2);
	suchleiste.setCellHorizontalAlignment(suchleiste, ALIGN_LEFT);
	suchleiste.setCellHorizontalAlignment(sucheButton, ALIGN_RIGHT);
	
	
		oracle.add("Flo");
		oracle.add("Nik");
		oracle.add("Matthias");
		oracle.add("Marko");
		oracle.add("Ayse");
		oracle.add("Jan");
		
	
	
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
