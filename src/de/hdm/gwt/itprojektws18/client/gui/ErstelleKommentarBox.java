package de.hdm.gwt.itprojektws18.client.gui;

import java.sql.Timestamp;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Kommentar;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class ErstelleKommentarBox extends HorizontalPanel {

	
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	ClientsideSettings clientSettings = new ClientsideSettings();
	
	/**
	 * Elemente zur Darstellung der ErstelleKommentar-Box
	 */
	private TextArea eingabeFeld = new TextArea();
	private Button submitKommentarBtn = new Button("Kommentieren");
	
	private Beitrag beitrag = new Beitrag();
	
	public ErstelleKommentarBox(final Beitrag b) {
		
		eingabeFeld.addStyleName("kommentarEingabeFeld");
		submitKommentarBtn.addStyleName("submitBtn");
		
		this.addStyleName("erstelleKommentarBox");
		
		this.add(eingabeFeld);
		this.add(submitKommentarBtn);
		this.beitrag = b;
		
		
		submitKommentarBtn.addClickHandler(new submitBtnClickHandler());
		
		super.onLoad();
	}
	
	/**
	 * <b>Nested Class zum submitKommentar-Button</b>
	 * implementiert den entsprechenden ClickHandler
	 */
	class submitBtnClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			Nutzer n = new Nutzer();
			n.setId(3);
//			n.setId(Integer.parseInt((Cookies.getCookie("id"))));
			
			beitrag.getId();
			String kText = "'" +eingabeFeld.getText() + "'";
			
			pinnwandVerwaltung.erstelleKommentar(beitrag, kText, n, new KommentarAnlegenCallback());
			
			
			
		}
		
		class KommentarAnlegenCallback implements AsyncCallback<Kommentar> {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim Anlegen des Kommentars: "+caught.getMessage());
				
			}

			@Override
			public void onSuccess(Kommentar result) {
			
				PinnwandBox pBox = new PinnwandBox();
				
				RootPanel.get("InhaltDiv").clear();
				RootPanel.get("InhaltDiv").add(pBox);
				
			}
			
		}
		
	}
	
}
