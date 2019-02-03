package de.hdm.gwt.itprojektws18.client;

import java.util.Date;

//import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.gwt.itprojektws18.client.gui.report.HTMLResultPanel;
import de.hdm.gwt.itprojektws18.shared.ReportGeneratorAsync;
import de.hdm.gwt.itprojektws18.shared.report.HTMLReportWriter;
import de.hdm.gwt.itprojektws18.shared.report.BeitragStatistikReport;

public class BeitragStatistikCallback extends HTMLResultPanel{
	ReportGeneratorAsync reportverwaltung= ClientsideSettings.getReportGenerator();
	
	public BeitragStatistikCallback(Date firstDate, Date lastDate) {

		reportverwaltung.createBeitragStatistikReport(firstDate, lastDate, new BeitragStatistik());
		
}

	
	class BeitragStatistik implements AsyncCallback<BeitragStatistikReport>{

		@Override
		public void onFailure(Throwable caught) {
	
			
		}

		@Override
		public void onSuccess(BeitragStatistikReport result) {
	
			int resultSize = result.getRows().size();
			if(resultSize == 0){
				Window.alert("Es wurden keine Daten geladen.");
			} else {
				HTMLReportWriter hrw = new HTMLReportWriter();
				hrw.process(result);
				append(hrw.getReportText());
				
			}
		}
		
	}

}

		
