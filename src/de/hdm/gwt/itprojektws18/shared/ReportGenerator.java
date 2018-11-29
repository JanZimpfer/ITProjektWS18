package de.hdm.gwt.itprojektws18.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.report.BeitragStatistikReport;
import de.hdm.gwt.itprojektws18.shared.report.NutzerStatistikReport;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;


@RemoteServiceRelativePath("reportgenerator")
public interface ReportGenerator extends RemoteService{
	
	
public void init() throws IllegalArgumentException;

NutzerStatistikReport createNutzerStatistikReport(Nutzer nutzer) throws IllegalArgumentException;

BeitragStatistikReport createBeitragStatistikReport(Beitrag beitrag) throws IllegalArgumentException;

}


