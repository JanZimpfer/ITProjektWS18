package de.hdm.gwt.itprojektws18.client.gui;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;

public class PinnwandBox extends VerticalPanel {

	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	private VerticalPanel pinnwandPanel = new VerticalPanel();
	
	public PinnwandBox() {

	}

	public PinnwandBox(int nutzerId) {

		Nutzer n = new Nutzer();
		n.setId(nutzerId);
		this.add(pinnwandPanel);
		
		pinnwandVerwaltung.getAllBeitraegeByNutzer(n, new BeitraegeAnzeigenCallback());

		RootPanel.get("InhaltDiv").add(pinnwandPanel);
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

//				PinnwandBox pBox = new PinnwandBox(nutzer.getId());

				BeitragBox bBox = new BeitragBox();

				String nicknameString = "@ " + nutzer.getNickname();
				String erstellZP = "" + result.elementAt(result.size()-1-i).getErstellZeitpunkt() + "";
				String inhalt = result.elementAt(result.size()-1-i).getText();

				bBox.befuelleNicklabel(nicknameString);
				bBox.befuelleErstellzeitpunkt(erstellZP);
				bBox.befuelleInhalt(inhalt);

				pinnwandPanel.add(bBox);
//				result.clear();

				
			}


		}

	}

}
