package de.hdm.gwt.itprojektws18.client.gui.report;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.gwt.itprojektws18.client.BeitragStatistikCallback;
import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.client.NutzerStatistikCallback;
import de.hdm.gwt.itprojektws18.shared.ReportGeneratorAsync;

public class BeitragStatistikForm extends HorizontalPanel {

	ReportGeneratorAsync reportVerwaltung = ClientsideSettings.getReportGenerator();

	private Button reportStart = new Button("Report Starten");
	private Label startDatum = new Label("Startdatum");
	private DateBox dateBox = new DateBox();
	private Label endDatum = new Label("Enddatum");
	private DateBox endDateBox = new DateBox();
	private FlexTable ft = new FlexTable();

	private VerticalPanel vpanel = new VerticalPanel();
	private DateTimeFormat dtf = DateTimeFormat.getFormat("dd.MM.yyyy");

	public BeitragStatistikForm() {

		ft.setWidget(0, 0, startDatum);
		ft.setWidget(0, 1, dateBox);
		ft.setWidget(1, 0, endDatum);
		ft.setWidget(1, 1, endDateBox);
		ft.setWidget(2, 0, reportStart);

		dateBox.setFormat(new DateBox.DefaultFormat(dtf));
		endDateBox.setFormat(new DateBox.DefaultFormat(dtf));

		dateBox.addValueChangeHandler(new StartDatum());
		endDateBox.addValueChangeHandler(new EndDatum());

		reportStart.addClickHandler(new ReportStartClickhandler());

		this.add(ft);
	}

	private class ReportStartClickhandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			vpanel.clear();
			vpanel.add(new BeitragStatistikCallback(dateBox.getValue(), endDateBox.getValue()));
			RootPanel.get("contentReport").add(vpanel);

		}

	}

	private class StartDatum implements ValueChangeHandler<Date> {

		@Override
		public void onValueChange(ValueChangeEvent<Date> event) {
			if (dateBox.getValue().after(endDateBox.getValue())) {
				Window.alert("Startdatum muss vor dem Enddatum liegen!");
				dateBox.setValue(null);
			}
		}

	}

	private class EndDatum implements ValueChangeHandler<Date> {
		@Override
		public void onValueChange(ValueChangeEvent<Date> event) {
			if (dateBox.getValue().after(endDateBox.getValue())) {
				Window.alert("Startdatum muss vor dem Enddatum liegen!");
				endDateBox.setValue(null);
			}
		}
	}
}
