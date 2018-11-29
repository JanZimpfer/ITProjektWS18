package de.hdm.gwt.itprojektws18.server.report;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.gwt.itprojektws18.server.PinnwandVerwaltungImpl;
import de.hdm.gwt.itprojektws18.shared.report.*;
import de.hdm.gwt.itprojektws18.shared.bo.*;
import de.hdm.gwt.itprojektws18.shared.ReportGenerator;


@SuppressWarnings("serial")

public class ReportGeneratorImpl extends RemoteServiceServlet 

implements ReportGenerator{
	

	
	public ReportGeneratorImpl() throws IllegalArgumentException{
		
	}
	
	
	public void init() throws IllegalArgumentException{
		
	}
	
	
	public nutzerStatistikReport createNutzerStatistikReport(Nutzer nutzer) {
		
	}
	
	
	public beitragStatistikReport createBeitragStatistikReport(Beitrag beitrag) {
		
	}

	
	
}
