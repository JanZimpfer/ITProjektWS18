package de.hdm.gwt.itprojektws18.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.report.BeitragStatistikReport;
import de.hdm.gwt.itprojektws18.shared.report.NutzerStatistikReport;

public interface ReportGeneratorAsync {

	void init(AsyncCallback<Void> callback);

	void createNutzerStatistikReport(Nutzer nutzer, AsyncCallback<NutzerStatistikReport> callback);

	void createBeitragStatistikReport(Beitrag beitrag, AsyncCallback<BeitragStatistikReport> callback);

}
