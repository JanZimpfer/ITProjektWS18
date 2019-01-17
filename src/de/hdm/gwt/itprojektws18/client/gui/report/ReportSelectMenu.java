package de.hdm.gwt.itprojektws18.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ReportSelectMenu extends HorizontalPanel {

	private Button reportButton1 = new Button("Nutzerstatistik Report");
	private Button reportButton2 = new Button("Beitragstatistik Report");
	private Button zurueck= new Button("zurueck auf die Startseite");

	public ReportSelectMenu() {
		reportButton1.addClickHandler(new NutzerStatistikClickhandler());
		reportButton2.addClickHandler(new BeitragStatistikClickhandler());
		zurueck.addClickHandler(new ZurueckClickhandler() );

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
	 *  Erstellung von "Zurück auf die Startseite"  Button, vom Report zurück zur Startseite 
	 */
	
	private class ZurueckClickhandler implements ClickHandler{
		
		@Override
		
		public void onClick(ClickEvent event) {
			
			Window.Location.assign("http://127.0.0.1:8888/ITProjektWS18.html");
			
		}
	}
	
	
	
	
}
