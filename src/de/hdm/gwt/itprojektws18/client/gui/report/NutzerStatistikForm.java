package de.hdm.gwt.itprojektws18.client.gui.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.view.client.ListDataProvider;
import com.ibm.icu.text.SimpleDateFormat;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.ReportGeneratorAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class NutzerStatistikForm extends HorizontalPanel{


	ReportGeneratorAsync reportVerwaltung = ClientsideSettings.getReportGenerator();
	
	private Button reportStart = new Button("Report Starten");
	private Label startDatum = new Label("Startdatum");
	private DateBox dateBox = new DateBox();
	private FlexTable ft = new FlexTable();
   
	private VerticalPanel vpanel = new VerticalPanel();
	
	public NutzerStatistikForm() {
		
		Nutzer n = new Nutzer();
		n.setId(Integer.parseInt(Cookies.getCookie("id")));
		dateBox.getDatePicker().setYearArrowsVisible(true);
		
		ft.setWidget(0, 0, startDatum);
		ft.setWidget(1, 0, dateBox);
		ft.setWidget(2, 0, reportStart);
		
		this.add(ft);	
	}

}
