package de.hdm.gwt.itprojektws18.client;

import java.util.Date;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.gwt.itprojektws18.client.gui.report.HTMLResultPanel;
import de.hdm.gwt.itprojektws18.shared.ReportGeneratorAsync;
import de.hdm.gwt.itprojektws18.shared.report.HTMLReportWriter;
import de.hdm.gwt.itprojektws18.shared.report.NutzerStatistikReport;

public class NutzerStatistikCallback extends HTMLResultPanel {
	ReportGeneratorAsync reportverwaltung = ClientsideSettings.getReportGenerator();

	public NutzerStatistikCallback(String nickname, Date firstDate, Date lastDate) {

		reportverwaltung.createNutzerStatistikReport(nickname, firstDate, lastDate, new NutzerStatistik());
	}

	class NutzerStatistik implements AsyncCallback<NutzerStatistikReport> {

		@Override
		public void onFailure(Throwable caught) {

		}

		@Override
		public void onSuccess(NutzerStatistikReport result) {

			int resultSize = result.getRows().size();
			if (resultSize == 0) {
				Window.alert("Es wurden keine Daten geladen.");
			} else {
				HTMLReportWriter hrw = new HTMLReportWriter();
				hrw.process(result);
				append(hrw.getReportText());

			}
		}

	}

}
