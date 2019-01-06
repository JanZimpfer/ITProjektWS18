package de.hdm.gwt.itprojektws18.client.gui;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class PinnwandBox extends VerticalPanel {
	
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	
	private VerticalPanel PinnwandPanel = new VerticalPanel();
	
	
	
	public PinnwandBox() {
		
	}
	
	public PinnwandBox(Nutzer n) {
		Nutzer nutzer = new Nutzer();
		nutzer = n;
		
		nutzer.setId(1);
		
		pinnwandVerwaltung.getAllBeitraegeByNutzer(nutzer, new BeitraegeAnzeigenCallback()); 
		
	}
	
	public void onLoad() {
		this.add(PinnwandPanel);
		RootPanel.get("InhaltDiv").add(PinnwandPanel);
	}
	
	public class BeitraegeAnzeigenCallback implements AsyncCallback<Vector<Beitrag>> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Beitrag> result) {
			
			for(Beitrag b : result) {
				
				BeitragBox bBox = new BeitragBox();
				
				String nicknameString = "@ "+ b.getNutzerFK();
				
				bBox.befuelleNicklabel(nicknameString);
				
				PinnwandPanel.add(bBox);
			}
			
		}
		
	}

}
