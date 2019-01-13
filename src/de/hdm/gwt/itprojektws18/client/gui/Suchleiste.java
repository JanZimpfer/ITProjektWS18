package de.hdm.gwt.itprojektws18.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class Suchleiste extends HorizontalPanel {
	
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	
	private HorizontalPanel suchleiste = new HorizontalPanel ();
	private Button sucheButton = new Button ("Profil anzeigen");
	private TextBox txtBox = new TextBox();

	
	public Suchleiste () {
		
		
		this.add(txtBox);
	
		this.addStyleName("suchleiste");
		this.add(suchleiste);
		
		sucheButton.addClickHandler(new ProfilAnzeigen ());
		
		suchleiste.add(sucheButton);
		
		sucheButton.addStyleName("suchButton");
		
		
		super.onLoad();
	}
	
	
	class ProfilAnzeigen implements ClickHandler {

		
		public void onClick(ClickEvent event) {
			
			String nicknameEingabe = txtBox.getText();
			pinnwandVerwaltung.getNutzerByNickname(nicknameEingabe, new NickNameAbfrageCallback());
			

		}
	}

	
	
	class NickNameAbfrageCallback implements AsyncCallback<Nutzer> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Abfragen eines Nutzers : " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Nutzer result) {
			
			class SuchErgebnisBox extends DialogBox {
				
				public SuchErgebnisBox() {
					
				}
				
			}
			
			
			SuchErgebnisBox ergebnisBox = new SuchErgebnisBox();
			ergebnisBox.center();
			
			Label nicknameErgebnis = new Label();
			
			nicknameErgebnis.setText(result.getNickname());
			
			ergebnisBox.add(nicknameErgebnis);
			
		}
		
	}
}
