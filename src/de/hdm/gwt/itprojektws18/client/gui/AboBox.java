package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;

import java.util.Vector;



public class AboBox extends VerticalPanel{
	
	
	private VerticalPanel pinnwandPanel = new VerticalPanel();
	private Vector<Pinnwand> nutzerAbos = new Vector<Pinnwand>();
	
	public AboBox() {
		
	}
	
	public void onLoad() {
		
		
		
		
	}
	
	private class PinnwandAnzeigen implements ClickHandler{

		
		public void onClick(ClickEvent event) {
			
			
		}
		
	}
}
