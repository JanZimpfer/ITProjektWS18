package de.hdm.gwt.itprojektws18.server.report;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.hdm.gwt.itprojektws18.client.gui.report.NutzerStatistikReport;
import de.hdm.gwt.itprojektws18.server.PinnwandVerwaltungImpl;
import de.hdm.gwt.itprojektws18.shared.report.*;
import de.hdm.gwt.itprojektws18.shared.bo.*;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltung;
import de.hdm.gwt.itprojektws18.shared.ReportGenerator;

@SuppressWarnings("serial")

public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {
	
	private PinnwandVerwaltung pinnwandAdmin = null;
	
	public ReportGeneratorImpl() throws IllegalArgumentException{
		
//	private Nutzer vorname = null;
//	
//	private Nutzer nachname = null;
		
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
		

		Nutzer name= getNutzerByName(name, name);
		Nutzer nickname= getNutzerByNickname(nickname);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss ");
		
		Vector <Nutzer> alleNutzer = new Vector <Nutzer>();
		
		NutzerStatistikReport result= new NutzerStatistikReport();
		result.setTitle("Alle Nutzer auf der Pinnwand");
		this.addImprint(result);
		
		
	}
	return null;
	
	}
	
	private Nutzer getNutzerByName(Nutzer vorname, Nutzer nachname) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(this.getPinnwandVerwaltung()==0) {
			return null;
		}
		return this.getPinnwandVerwaltung().getNutzerByName(vorname.getVorname(), nachname.getNachname());
	}

	
	public Nutzer getNutzerByNickname(Nutzer nickname) throws IllegalArgumentException {
		// TODO Auto-generated method stub

		if(this.getPinnwandVerwaltung()==0) {
			
		return null;
		
	}
		return this.getPinnwandVerwaltung().getNutzerByNickname(nickname.getNickname());
	}

	
	@Override
	public BeitragStatistikReport createBeitragStatistikReport(Beitrag beitrag) throws IllegalArgumentException{
		return null;
	}
	
	
	
	
	public Vector<Like> getLikesFromUser(Nutzer nutzer){
		return null;
	}
	
	
	
	
	
}
