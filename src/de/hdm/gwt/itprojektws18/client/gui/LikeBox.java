package de.hdm.gwt.itprojektws18.client.gui;

import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Kommentar;
import de.hdm.gwt.itprojektws18.shared.bo.Like;

public class LikeBox extends ScrollPanel{
	
	/**
	 * Erzeugen eines PinnwandVerwaltung-Objekts um eine Applikationsverwaltung zu initialisieren.
	 */
	private static PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
	
	/**
	 * Instanziierung der GUI Elemente
	 */
	private Label nickname = new Label();
	
	
	/**
	 * Deklarierung der BO, die verwendet werden
	 */
	private Kommentar kommentar = null;
	private Beitrag beitrag = null;
	
	

	public LikeBox(Beitrag b) {
		
		//überarbeitung, da beitrag global deklariert wurde = kein direkter zusammenhang
		this.beitrag = b;
		pinnwandVerwaltung.getAllLikesByBeitrag(b, new LikeAnzeigeCallBack());
		
	}
	
	public void onLoad() {
		
		this.addStyleName("likebox");
		
		nickname.addStyleName("nicknameLikeBox");
		
		this.add(nickname);
	}
	
	public class LikeAnzeigeCallBack implements AsyncCallback<Vector<Like>> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler bei der Anzeige der Likes: " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Vector<Like> result) {
			// Like Vector auslesen und jeweils dem ScrollPanel adden -> for Schleife
			
		}
		
	}
}
