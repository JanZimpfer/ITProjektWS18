package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.client.gui.AboPinnwandBox.PinnwandAnzeigen;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Abonnement;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;

import java.sql.Timestamp;
import java.util.Vector;



public class AboBox extends VerticalPanel{
	
	/**
	 * Erzeugen eines PinnwandVerwaltung-Objekts um eine Applikationsverwaltung zu initialisieren.
	 */
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	ClientsideSettings clientSettings = new ClientsideSettings();
	
	/**
	 * Instanziierung der GUI Elemente
	 */
	
	private VerticalPanel abobox = new VerticalPanel();
	
	private VerticalPanel aboniertePW = new VerticalPanel();
	
	private HorizontalPanel profilbereich = new HorizontalPanel();
	
	private HorizontalPanel aboPinnwandInfos = new HorizontalPanel();
	
	private HorizontalPanel ReportButtonPanel = new HorizontalPanel();
	
	private Label profil = new Label("Profil: PeterPan");
	
	private Label anzeigePW = new Label("Abonnierte Pinnwaende: ");
	
	private Button aboErstellBtn = new Button("Abo hinzufuegen");
	
	private Button deAboBtn = new Button("Abo entfernen");
	
	private Label nutzerNameLabel = new Label("Jan");
	
	private Label nickNameLabel = new Label("@flizzy");
	
	private Button beitragStatistikButton = new Button("BeitragStatistik");
	
	private Button nutzerStatistikButton = new Button("NutzerStatistik");

	
	private Nutzer n = null;
	
	public AboBox() {
		
		profilbereich.setSpacing(2);
		aboniertePW.setSpacing(2);
		
	}
	

	public void onLoad() {
		
		/**
		 * 
		 */
		this.addStyleName("abobox");
		this.add(abobox);
		
		abobox.add(profilbereich);
		abobox.add(aboniertePW);
		abobox.add(ReportButtonPanel);
		
		profilbereich.add(profil);
		profilbereich.add(aboErstellBtn);
		profilbereich.add(deAboBtn);
		
		aboniertePW.add(anzeigePW);
//		aboniertePW.add(aboPinnwandInfos);
		
//		aboPinnwandInfos.add(nutzerNameLabel);
//		aboPinnwandInfos.add(nickNameLabel);
		
		ReportButtonPanel.add(beitragStatistikButton);
		ReportButtonPanel.add(nutzerStatistikButton);
		
		/**
		 * 
		 */
		profil.addStyleName("profil");
		anzeigePW.addStyleName("anzeigePW");
		aboErstellBtn.addStyleName("aboErstellBtn");
		deAboBtn.addStyleName("deAboBtn");
		aboniertePW.addStyleName("aboniertePW");
		aboPinnwandInfos.addStyleName("aboPinnwandInfos");
		nutzerNameLabel.addStyleName("nutzerNameLabel");
		nickNameLabel.addStyleName("nickNameLabel");
		ReportButtonPanel.addStyleName("ReportButtonPanel");
		beitragStatistikButton.addStyleName("beitragStatistikButton");
		nutzerStatistikButton.addStyleName("nutzerStatistikButton");
		
		/**
		 * 
		 */
		aboErstellBtn.addClickHandler(new aboErstellBtnClickHandler());
		deAboBtn.addClickHandler(new deAboBtnClickHandler());
		
		nutzerNameLabel.addClickHandler(new PinnwandAnzeigen());
		nickNameLabel.addClickHandler(new PinnwandAnzeigen());
		
		beitragStatistikButton.addClickHandler(new BeitragStatistikClickHandler());
		nutzerStatistikButton.addClickHandler(new NutzerStatistikClickHandler());
		
		RootPanel.get("AboDiv").add(abobox);
		
	}
	
	class aboErstellBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			// zunächst if Abfrage: if nutzer == cookie nutzer -> button hide oder keine event, else -> abo erstellen
			
			Nutzer n = new Nutzer();
			
//			n.setId(Integer.parseInt(Cookies.getCookie("id")));
			
			n.setId(1);
			
			Pinnwand p = new Pinnwand();
			p.setNutzerFK(n.getId());
			
			Timestamp erstellzeitpunkt = null;
			
			pinnwandVerwaltung.erstelleAbonnement(p, n, erstellzeitpunkt, new AbonnementAnlegenCallBack());
			
		}
		
	}
	
	public class AbonnementAnlegenCallBack implements AsyncCallback<Abonnement> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Anlegen des Abonnements: " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Abonnement result) {
			Window.alert("Das Abonnement wurde erfolgreich angelegt.");
			Nutzer n = new Nutzer();
			
			n.setId(result.getNutzerFK());
			
			String nutzerNameString = n.getVorname();
			nutzerNameLabel.setText(nutzerNameString);
			
			String nickNameString = n.getNickname();
			nickNameLabel.setText(nickNameString);
			
			aboPinnwandInfos.add(nutzerNameLabel);
			aboPinnwandInfos.add(nickNameLabel);
			
			aboniertePW.add(aboPinnwandInfos);
			
		}
		
	}
	
	
	class deAboBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			
		} 
		
	}
	
	class PinnwandAnzeigen implements ClickHandler {
		
		
		public void onClick(ClickEvent event) {
			
			
		}
		
	}
	
	class BeitragStatistikClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class NutzerStatistikClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
