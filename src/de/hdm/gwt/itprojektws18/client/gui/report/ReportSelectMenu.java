package de.hdm.gwt.itprojektws18.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ReportSelectMenu extends VerticalPanel{
	
	private Button reportButton1 = new Button("Report 1");
	private VerticalPanel vPanel = new VerticalPanel();
	
	public ReportSelectMenu() {
		reportButton1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("contentReport").clear();
				RootPanel.get("contentReport").add(new NutzerStatistikForm());
			}
		});
		this.add(reportButton1);
	}

}
