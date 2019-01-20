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
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
//import de.hdm.gwt.itprojektws18.client.gui.SuchergebnisBox.ErgebnisCallback;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltung;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class Suchleiste extends HorizontalPanel {

	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	private HorizontalPanel suchleiste = new HorizontalPanel();
	private Button sucheButton = new Button("Pinnwand anzeigen");
//	private TextBox txtBox = new TextBox();
	
	MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	private SuggestBox txtBox = new SuggestBox(oracle);

	

//	private DialogBox suchErgebnisBox = new DialogBox();

	public Suchleiste() {

		this.add(txtBox);
		txtBox.setText("Nicknamen eingeben...");

		this.addStyleName("suchleiste");
		this.add(suchleiste);
		
		suchleiste.add(sucheButton);
		pinnwandVerwaltung.getAllNutzer(new SearchCallback());

		sucheButton.addStyleName("suchButton");

		sucheButton.addClickHandler(new ShowPinnwandClickHandler());

		super.onLoad();
	}
		
	class SearchCallback implements AsyncCallback<Vector<Nutzer>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Ausführen der Suche: " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Vector<Nutzer> result) {

			String searchResultString = new String();
			Label searchLbl = new Label();
			for(int i=0; i<result.size(); i++) {
				
				searchResultString = "" + result.elementAt(i).getNickname();
				oracle.add(searchResultString);
				
				
			}
			
			
		}
		
		
	}
	
	class ShowPinnwandClickHandler implements ClickHandler{

		
		public void onClick(ClickEvent event) {
			
		pinnwandVerwaltung.getNutzerByNickname(txtBox.getText(), new ShowPinnwandCallback());
		
		txtBox.refreshSuggestionList();
		}	
	}
	class ShowPinnwandCallback implements AsyncCallback<Nutzer>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Anzeigen der Pinnwand" + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Nutzer result) {
			PinnwandBox pB = new PinnwandBox(result.getId());
			RootPanel.get("InhaltDiv").clear();
			RootPanel.get("InhaltDiv").add(pB);
			
		}
		
	}
	
	}
	
//	class SuchClickHandler implements ClickHandler {
//
//		public void onClick(ClickEvent event) {
//
//		final	String suchEingabe = txtBox.getText();
//
//			pinnwandVerwaltung.searchNutzer(suchEingabe, new AsyncCallback<Vector<Nutzer>>() {
//
//				@Override
//				public void onFailure(Throwable caught) {
//					// TODO Auto-generated method stub
//
//				}
//
//				@Override
//				public void onSuccess(Vector<Nutzer> result) {
//
//					for (int i = 0; i < result.size(); i++) {
//
//						if (result == null) {}
//
//
//						else {
//							SuchergebnisBox sb = new SuchergebnisBox(suchEingabe);
//							sb.center();
//						}
//					}
//
//				}
//
//			});
//
//		}
//
//	}

