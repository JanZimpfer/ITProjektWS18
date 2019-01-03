package de.hdm.gwt.itprojektws18.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.ListDataProvider;
import com.ibm.icu.text.SimpleDateFormat;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.client.NutzerStatistikCallback;
import de.hdm.gwt.itprojektws18.shared.ReportGeneratorAsync;
import de.hdm.gwt.itprojektws18.client.BeitragStatistikCallback;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;



public class BeitragStatistikForm extends HorizontalPanel{
	
	ReportGeneratorAsync reportVerwaltung = ClientsideSettings.getReportGenerator();
	
	
	private Button reportStart= new Button("Report Starten");
	private Label startDatum = new Label("Startdatum");
	private DateBox dateBox = new DateBox();
	private Label endDatum = new Label("Enddatum");
	private DateBox endDateBox = new DateBox();
	private FlexTable ft = new FlexTable();
	
	private VerticalPanel vpanel = new VerticalPanel();
	
	public BeitragStatistikForm() {
		
		Nutzer n = new Nutzer();
		n.setId(Integer.parseInt(Cookies.getCookie("id")));
		dateBox.getDatePicker().setYearArrowsVisible(true);
		
		ft.setWidget(0, 0, startDatum);
		ft.setWidget(0, 1, dateBox);
		ft.setWidget(1, 0, endDatum);
		ft.setWidget(1, 1, endDateBox);
		ft.setWidget(2, 0, reportStart);
		
		reportStart.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				vpanel.clear();
				vpanel.add(new BeitragStatistikCallback());
				RootPanel.get("contentReport").add(vpanel);
				
			}
			
			
			
		});
		
		this.add(ft);
	}
		
		
	}
	
	
	
	
	
	
