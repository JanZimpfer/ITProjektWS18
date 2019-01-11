package de.hdm.gwt.itprojektws18.client.gui;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class UebersichtBox extends VerticalPanel {
	
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	
	private ErstelleBeitragBox erstelleBeitragBox = new ErstelleBeitragBox();
	private VerticalPanel beitragPanel = new VerticalPanel(); 

	public UebersichtBox(int nutzerId) {
		
		this.add(erstelleBeitragBox);
		
		Nutzer n = new Nutzer();
		n.setId(nutzerId);
		
		pinnwandVerwaltung.getAllBeitraegeByNutzer(n, new BeitraegeAnzeigenCallback());
		
		super.onLoad();
	}
	
	
	
	public class BeitraegeAnzeigenCallback implements AsyncCallback<Vector<Beitrag>> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onSuccess(Vector<Beitrag> result) {

			
			
			for (int i = 0; i < result.size(); i++) {

				
				Nutzer nutzer = new Nutzer();
				nutzer.setId(result.elementAt(result.size()-1-i).getNutzerFK());

				BeitragBox bBox = new BeitragBox(result.get(i));

				String nicknameString = "@ " + nutzer.getNickname();
				String erstellZP = "" + result.elementAt(result.size()-1-i).getErstellZeitpunkt() + "";
				String inhalt = result.elementAt(result.size()-1-i).getText();

				bBox.befuelleNicklabel(nicknameString);
				bBox.befuelleErstellzeitpunkt(erstellZP);
				bBox.befuelleInhalt(inhalt);

				beitragPanel.add(bBox);
//				result.clear();

				
			}


		}

	}
}
