package de.hdm.gwt.itprojektws18.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ReportSelectMenu extends VerticalPanel {

	private Button reportButton1 = new Button("Nutzerstatistik Report");
	private Button reportButton2 = new Button("Beitragstatistik Report");

	public ReportSelectMenu() {
		reportButton1.addClickHandler(new NutzerStatistikClickhandler());
		reportButton2.addClickHandler(new BeitragStatistikClickhandler());

		this.add(reportButton1);
	}

	private class NutzerStatistikClickhandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get("contentReport").clear();
			RootPanel.get("contentReport").add(new NutzerStatistikForm());
		}

	}

	private class BeitragStatistikClickhandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			RootPanel.get("contentReport").clear();
			RootPanel.get("contentReport").add(new BeitragStatistikForm());
		}
	}
}
