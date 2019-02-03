package de.hdm.gwt.itprojektws18.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.user.client.ui.VerticalPanel;

public class ReportSelectMenu extends HorizontalPanel {
	
	// Neues Image Objekt instanziieren für das Logo auf der Reportseite
	private Image reportlogo = new Image();
	//3 Buttons instanziieren für Nutzerstatistik; Beitragstatistik und Zurück
	private Button reportButton1 = new Button("Nutzerstatistik");
	private Button reportButton2 = new Button("Beitragstatistik");
	private Button zurueck= new Button("Zurück");

	public ReportSelectMenu() {
		
		//Logo in der GUI einpflegen
		reportlogo.setUrl("/images/tellIT-logo.png");
		reportlogo.setSize("210px", "120px");
		
		//Button Nutzerstatistik, Beitragstatistik und Zurück Button bekommen
		//das gleiche Aussehen 
		reportButton1.setStyleName("reportButton");
		reportButton2.setStyleName("reportButton");
		zurueck.setStyleName("reportButton");
		
		
		
		reportButton1.addClickHandler(new NutzerStatistikClickhandler());
		reportButton2.addClickHandler(new BeitragStatistikClickhandler());
		zurueck.addClickHandler(new ZurueckClickhandler() );
		
		
		// Widgets werden dem Panel hinzugefügt 
		this.add(reportlogo);
		this.add(reportButton1);
		this.add(reportButton2);
		this.add(zurueck);
	}

	/*
	 *  Erstellung von "Nutzerstatistik"  Button 
	 */
	
	private class NutzerStatistikClickhandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get("contentReport").clear();
			RootPanel.get("contentReport").add(new NutzerStatistikForm());
		}

	}

	/*
	 *  Erstellung von "Beitragstatistik"  Button 
	 */
	
	private class BeitragStatistikClickhandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get("contentReport").clear();
			RootPanel.get("contentReport").add(new BeitragStatistikForm());
		}
	}
	
	
	/*
	 *  Erstellung von "Zur�ck auf die Startseite"  Button, vom Report zur�ck zur Startseite 
	 */
	
	private class ZurueckClickhandler implements ClickHandler{
		
		@Override
		
		public void onClick(ClickEvent event) {
			
			Window.Location.assign("/");
			
		}
	}
	
	
	
	
}
