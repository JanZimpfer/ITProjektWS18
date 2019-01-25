package de.hdm.gwt.itprojektws18.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Abonnement;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;

public class UebersichtBox extends VerticalPanel {

	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	private ErstelleBeitragBox erstelleBeitragBox = new ErstelleBeitragBox();
	private VerticalPanel beitragPanel = new VerticalPanel();
	private HorizontalPanel abonnierenPanel = new HorizontalPanel();

	private Label profilInfos = new Label();
	private Button aboErstellBtn = new Button();

	private DateTimeFormat dtf = DateTimeFormat.getFormat("dd.MM.yyyy 'um' k:mm");

	private Pinnwand p = new Pinnwand();

	public UebersichtBox() {

		this.add(erstelleBeitragBox);

		Nutzer n = new Nutzer();
		n.setId(Integer.parseInt(Cookies.getCookie("id")));

		pinnwandVerwaltung.getAllBeitraegeByNutzer(n, new BeitraegeAnzeigenCallback());

		this.add(beitragPanel);

		super.onLoad();
	}

	public UebersichtBox(int nutzerId) {
		
		Nutzer n = new Nutzer();
		n.setId(Integer.parseInt(Cookies.getCookie("id")));
		
		aboErstellBtn.addStyleName("uebersichtBtn");

		p.setId(nutzerId);

		abonnierenPanel.add(profilInfos);
		abonnierenPanel.add(aboErstellBtn);


		aboErstellBtn.addClickHandler(new AboErstellClickhandler());

		pinnwandVerwaltung.getAboFor(nutzerId, n.getId() , new AboPruefenCallback());
		
		
		pinnwandVerwaltung.getNutzerbyID(nutzerId, new NutzerInformationenCallback());

		this.add(abonnierenPanel);

		pinnwandVerwaltung.getAllBeitraegeByPinnwand(p, new BeitraegeAnzeigenCallback());

		this.add(beitragPanel);

		super.onLoad();
	}

	class AboErstellClickhandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {

			Nutzer n = new Nutzer();
			n.setId(Integer.parseInt(Cookies.getCookie("id")));

			pinnwandVerwaltung.getAboFor(p.getId(), n.getId(), new AboInfoCallback());
		}

	}
	
	class AboInfoCallback implements AsyncCallback<Abonnement> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Abruf der Abo-Informationen: " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Abonnement result) {
			
			if (result == null) {
				Nutzer n = new Nutzer();
				n.setId(Integer.parseInt(Cookies.getCookie("id")));
				
				pinnwandVerwaltung.erstelleAbonnement(p, n, new AboErstellenCallback());
			} else {
				pinnwandVerwaltung.loeschen(result, new AboLoeschenCallback());
			}
			
		}
		
	}

	class AboErstellenCallback implements AsyncCallback<Abonnement> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Anlegen des Abonnements: " + caught.getMessage());

		}

		@Override
		public void onSuccess(Abonnement result) {
				
				aboErstellBtn.setText("Deabonnieren");
				
				AboBox aboBox = new AboBox();

				RootPanel.get("AboDiv").clear();
				RootPanel.get("AboDiv").add(aboBox);
			}

		}
	
	class AboLoeschenCallback implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Löschen des Abonnements. " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Void result) {
			
			aboErstellBtn.setText("Abonnieren");
			
			AboBox aboBox = new AboBox();

			RootPanel.get("AboDiv").clear();
			RootPanel.get("AboDiv").add(aboBox);
			
		}
		
	}
	
	class AboPruefenCallback implements AsyncCallback<Abonnement>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Abfragen des Abonnements " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Abonnement result) {
			if(result == null) {
				aboErstellBtn.setText("Abonnieren");
			} else {
				aboErstellBtn.setText("Deabonnieren");
			}
			
		}
		
	}

	class NutzerInformationenCallback implements AsyncCallback<Nutzer> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Auslesen des Nutzers: " + caught.getMessage());

		}

		@Override
		public void onSuccess(Nutzer result) {
			String profilInfosString = "Profil: " + result.getVorname() + " " + result.getNachname() + "";
			profilInfos.setText(profilInfosString);
		}

	}

	public class BeitraegeAnzeigenCallback implements AsyncCallback<Vector<Beitrag>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Auslesen der Beitragsinformationen: " + caught.getMessage());

		}

		@Override
		public void onSuccess(Vector<Beitrag> result) {

			for (final Beitrag beitrag : result) {
				final BeitragBox bBox = new BeitragBox(beitrag);
				beitragPanel.add(bBox);

				pinnwandVerwaltung.getNutzerbyID(beitrag.getNutzerFK(), new AsyncCallback<Nutzer>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Fehler beim Auslesen der Nutzerinformationen: " + caught.getMessage());

					}

					@Override
					public void onSuccess(Nutzer result) {
						String nameString = "@" + result.getNickname() + "," + result.getVorname() + " "
								+ result.getNachname();
						final String erstellZP = dtf.format(beitrag.getErstellZeitpunkt());
						final String inhalt = beitrag.getText();

						bBox.befuelleName(nameString);
						bBox.befuelleErstellzeitpunkt(erstellZP);
						bBox.befuelleInhalt(inhalt);

					}

				});
			}
		}
	}
}
