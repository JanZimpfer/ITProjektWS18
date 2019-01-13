package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
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
	
	/**
	 * Instanziierung der GUI Elemente
	 */
	
	private VerticalPanel abobox = new VerticalPanel();
	
	private VerticalPanel aboniertePW = new VerticalPanel();
	
	private HorizontalPanel profilbereich = new HorizontalPanel();
	
	private HorizontalPanel aboPinnwandInfos = new HorizontalPanel();
	
	private HorizontalPanel reportButtonPanel = new HorizontalPanel();
	
	private Label profil = new Label();
	
	private Label anzeigePW = new Label("Abonnierte Pinnwaende: ");
	
	private Button aboErstellBtn = new Button("Abo hinzufuegen");
	
	private Button deAboBtn = new Button("Abo entfernen");
	
	private Label nutzerNameLabel = new Label("Jan");
	
	private Label nickNameLabel = new Label("@flizzy");
	
	private Button beitragStatistikButton = new Button("BeitragStatistik");
	
	private Button nutzerStatistikButton = new Button("NutzerStatistik");
	
	Nutzer nutzer = new Nutzer();
	
	public AboBox() {
		
		profilbereich.setSpacing(2);
		aboniertePW.setSpacing(2);
		
		Nutzer n = new Nutzer();
		
		
//		n.setId(Integer.parseInt(Cookies.getCookie("id")));
		n.setId(3);
		
		this.addStyleName("abobox");
		this.add(abobox);
		
		abobox.add(profilbereich);
		abobox.add(aboniertePW);
		abobox.add(reportButtonPanel);
		
		profilbereich.add(profil);
		profilbereich.add(aboErstellBtn);
		profilbereich.add(deAboBtn);
		
		aboniertePW.add(anzeigePW);
		aboniertePW.add(aboPinnwandInfos);
		
		aboPinnwandInfos.add(nutzerNameLabel);
		aboPinnwandInfos.add(nickNameLabel);
		
		reportButtonPanel.add(beitragStatistikButton);
		reportButtonPanel.add(nutzerStatistikButton);
		
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
		reportButtonPanel.addStyleName("ReportButtonPanel");
		beitragStatistikButton.addStyleName("beitragStatistikButton");
		nutzerStatistikButton.addStyleName("nutzerStatistikButton");
		
		/**
		 * 
		 */
		pinnwandVerwaltung.getNutzerbyID(3, new ProfilNameCallBack());
		
		
		/**
		 * 
		 */
		aboErstellBtn.addClickHandler(new aboErstellBtnClickHandler());
		deAboBtn.addClickHandler(new deAboBtnClickHandler());
		
		nutzerNameLabel.addClickHandler(new PinnwandAnzeigen());
		nickNameLabel.addClickHandler(new PinnwandAnzeigen());
		
		beitragStatistikButton.addClickHandler(new BeitragStatistikClickHandler());
		nutzerStatistikButton.addClickHandler(new NutzerStatistikClickHandler());
		
		super.onLoad();
	}
	
	public AboBox(final Nutzer n) {
		
		profilbereich.setSpacing(2);
		aboniertePW.setSpacing(2);
		
		
		
		this.nutzer = n;
		n.setId(3);
		
		this.addStyleName("abobox");
		this.add(abobox);
		
		abobox.add(profilbereich);
		abobox.add(aboniertePW);
		abobox.add(reportButtonPanel);
		
		profilbereich.add(profil);
		profilbereich.add(aboErstellBtn);
		profilbereich.add(deAboBtn);
		
		aboniertePW.add(anzeigePW);
		aboniertePW.add(aboPinnwandInfos);
		
		aboPinnwandInfos.add(nutzerNameLabel);
		aboPinnwandInfos.add(nickNameLabel);
		
		reportButtonPanel.add(beitragStatistikButton);
		reportButtonPanel.add(nutzerStatistikButton);
		
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
		reportButtonPanel.addStyleName("ReportButtonPanel");
		beitragStatistikButton.addStyleName("beitragStatistikButton");
		nutzerStatistikButton.addStyleName("nutzerStatistikButton");
		
		/**
		 * 
		 */
		pinnwandVerwaltung.getNutzerbyID(3, new ProfilNameCallBack());
		
		/**
		 * 
		 */
		aboErstellBtn.addClickHandler(new aboErstellBtnClickHandler());
		deAboBtn.addClickHandler(new deAboBtnClickHandler());
		
		nutzerNameLabel.addClickHandler(new PinnwandAnzeigen());
		nickNameLabel.addClickHandler(new PinnwandAnzeigen());
		
		beitragStatistikButton.addClickHandler(new BeitragStatistikClickHandler());
		nutzerStatistikButton.addClickHandler(new NutzerStatistikClickHandler());
		
		super.onLoad();
		
	}
	
	class ProfilNameCallBack implements AsyncCallback<Nutzer> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler beim Auslesen des Nutzers: " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Nutzer result) {
			String profilInfos = "Profil: " + result.getVorname() + " "+ result.getNachname() + "";
			profil.setText(profilInfos);
		}
		
	}
	
	
	class aboErstellBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			// zun�chst if Abfrage: if nutzer == cookie nutzer -> button hide oder keine event, else -> abo erstellen
			
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
