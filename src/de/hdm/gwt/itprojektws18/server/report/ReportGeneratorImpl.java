package de.hdm.gwt.itprojektws18.server.report;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.text.SimpleDateFormat;
import java.util.Vector;


import de.hdm.gwt.itprojektws18.server.PinnwandVerwaltungImpl;
import de.hdm.gwt.itprojektws18.shared.report.*;
import de.hdm.gwt.itprojektws18.shared.bo.*;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltung;
import de.hdm.gwt.itprojektws18.shared.ReportGenerator;


@SuppressWarnings("serial")

public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {
	
	private PinnwandVerwaltung pinnwandAdmin = null;
	
	public ReportGeneratorImpl() throws IllegalArgumentException{
		
	}
	
	@Override
	public void init() throws IllegalArgumentException{
		PinnwandVerwaltungImpl pinnwandVerwaltung = new PinnwandVerwaltungImpl();
		pinnwandVerwaltung.init();
		this.pinnwandAdmin = pinnwandVerwaltung;
	}
	
	protected PinnwandVerwaltung getPinnwandVerwaltung() {
		return this.pinnwandAdmin;
	}
	@Override
	public NutzerStatistikReport createNutzerStatistikReport(Nutzer nutzer) throws IllegalArgumentException {
		
	if(this.getPinnwandVerwaltung() == null) {
		return null;
	}
	else {
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss ");

		Vector <Nutzer> alleNutzer = new Vector <Nutzer>();
		
	
		
		
		
	}
	return null;
	
	}
	
	@Override
	public BeitragStatistikReport createBeitragStatistikReport(Beitrag beitrag) throws IllegalArgumentException{
		return null;
	}
	
	

	
	
}
