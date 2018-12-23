package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;

public class ErstelleBeitragBox extends HorizontalPanel {
	
	private HorizontalPanel PostingPanel = new HorizontalPanel();
	private TextArea erstelleBeitragFeld = new TextArea();
	private Button postingButton = new Button("Posten");
	
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	ClientsideSettings clientSettings = new ClientsideSettings();
	
	public ErstelleBeitragBox() {
		
		PostingPanel.setSpacing(2);
	}
	
	public void onLoad(){
		this.addStyleName("erstelleBeitragBox");
		this.add(PostingPanel);
		
		PostingPanel.add(erstelleBeitragFeld);
		PostingPanel.add(postingButton);
		
		erstelleBeitragFeld.addStyleName("erstelleBeitragFeld");
		postingButton.addStyleName("submitButton");
		
		RootPanel.get("BeitragEingabe").add(PostingPanel);
	}
}

