package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

import de.hdm.gwt.itprojektws18.shared.bo.Abonnement;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;

public class AboPinnwandBox extends FlowPanel{

	
	/**
	 * Instanziierung der GUI Elemente
	 */
	private Widget nutzerName = new Widget();
	
	private Widget nickName = new Widget();
	
	
	/**
	 * Deklarierung der BO, die verwendet werden
	 */
	private Nutzer nutzer = null;
	private Abonnement abo = null;
	private Pinnwand pinnwand = null;
	
	public AboPinnwandBox() {
		
	}
	
	public AboPinnwandBox(int anzahlAbos) {
		//Anzahl der Abos aus Datenbank auslesen!
	}
	
	
	public void onLoad() {
		
		
		
		this.add(nutzerName);
		this.add(nickName);
	}
}
