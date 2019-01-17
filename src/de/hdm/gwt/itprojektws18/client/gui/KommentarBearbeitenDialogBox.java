package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Kommentar;

public class KommentarBearbeitenDialogBox extends DialogBox{
	
	PinnwandVerwaltungAsync pinnwandVerwaltung =ClientsideSettings.getPinnwandVerwaltung();
	
	
	Kommentar kommentar = new Kommentar();
	
	private VerticalPanel editPanel = new VerticalPanel();
	private TextBox kommentarText = new TextBox();
	private Button speichernBtn = new Button("Speichern");
	private Button schliessenBtn = new Button("Schliessen");
	
	public KommentarBearbeitenDialogBox() {
		
	}
	
	public KommentarBearbeitenDialogBox(Kommentar k) {
		this.kommentar = k;
		
		kommentarText.setText(k.getText());
		
		speichernBtn.addClickHandler(new KommentarAendernClickHandler());
		schliessenBtn.addClickHandler(new SchliessenClickHandler());
		
		editPanel.add(kommentarText);
		editPanel.add(speichernBtn);
		editPanel.add(schliessenBtn);
		
		this.add(editPanel);
	}
	
	class KommentarAendernClickHandler implements ClickHandler {
		
		@Override
		public void onClick(ClickEvent event) {
			kommentar.setText(kommentarText.getText());
			
			pinnwandVerwaltung.speichern(kommentar, new KommentarSpeichernCallback());
			
		}
	}
	
	class KommentarSpeichernCallback implements AsyncCallback<Void> {
		
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Bearbeiten der Kommentars: " + caught.getMessage());
			
			
		}
		
		@Override
		public void onSuccess(Void result) {
			hide();
			
			PinnwandBox pBox = new PinnwandBox();
			RootPanel.get("InhaltDiv").clear();
			RootPanel.get("InhaltDiv").add(pBox);
			
		}
	}
	
	class SchliessenClickHandler implements ClickHandler {
		
		public void onClick(ClickEvent event) {
			hide();
			
		}
		
	}


}
