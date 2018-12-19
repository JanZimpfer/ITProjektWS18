package de.hdm.gwt.itprojektws18.server.report;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.hdm.gwt.itprojektws18.client.gui.report.LikeStatistikReport;
import de.hdm.gwt.itprojektws18.server.PinnwandVerwaltungImpl;
import de.hdm.gwt.itprojektws18.shared.report.*;
import de.hdm.gwt.itprojektws18.shared.bo.*;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltung;
import de.hdm.gwt.itprojektws18.shared.ReportGenerator;
import de.hdm.gwt.itprojektws18.shared.report.BeitragStatistikReport;

@SuppressWarnings("serial")

public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	
	private PinnwandVerwaltung pinnwandAdmin = null;

	public ReportGeneratorImpl() throws IllegalArgumentException {

	}

	private Nutzer nutzer = new Nutzer();

	@Override
	public void init() throws IllegalArgumentException {
		PinnwandVerwaltungImpl pinnwandVerwaltung = new PinnwandVerwaltungImpl();
		pinnwandVerwaltung.init();
		this.pinnwandAdmin = pinnwandVerwaltung;
	}

	protected PinnwandVerwaltung getPinnwandVerwaltung() {
		return this.pinnwandAdmin;
	}

	protected void addImprint(Report r) {
		CompositeParagraph imprint = new CompositeParagraph();

		imprint.addSubParagraph(new SimpleParagraph("Social Media Pinnwand der HdM"));
		imprint.addSubParagraph(new SimpleParagraph("Nobelstraße 10"));
		imprint.addSubParagraph(new SimpleParagraph("70569 Stuttgart"));

		r.setImprint(imprint);
	}
	
	/*
	 * Diese Methode soll alle Abonnements, Likes, Beiträge und Kommentare von einem Nutzer anzeigen 
	 * (non-Javadoc)
	 * @see de.hdm.gwt.itprojektws18.shared.ReportGenerator#createNutzerStatistikReport(de.hdm.gwt.itprojektws18.shared.bo.Nutzer)
	 */
	@Override
	public NutzerStatistikReport createNutzerStatistikReport(Nutzer nutzer) throws IllegalArgumentException {

		if (this.getPinnwandVerwaltung() == null) {
			return null;
		} else {

			Vector<Nutzer> name = getNutzerByName(nutzer.getVorname(), nutzer.getNachname());
			Vector<Nutzer> nickname = getNutzerByNickname(nutzer.getNickname());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss ");

			Vector<Nutzer> alleNutzer = new Vector<Nutzer>();

			NutzerStatistikReport result = new NutzerStatistikReport();
			result.setTitle("Alle Nutzer auf der Pinnwand");
			this.addImprint(result);

		}
		return null;

	}

	private Nutzer getNutzerByName(Nutzer vorname, Nutzer nachname) throws IllegalArgumentException {
		if (this.getPinnwandVerwaltung() == null) {
			return null;
		}
		return this.getPinnwandVerwaltung().getNutzerByName(vorname.getVorname(), nachname.getNachname());
	}

	public Nutzer getNutzerByNickname(Nutzer nickname) throws IllegalArgumentException {
		if (this.getPinnwandVerwaltung() == null) {

			return null;

		}
		return this.getPinnwandVerwaltung().getNutzerByNickname(nickname.getNickname());
	}

	@Override
	public BeitragStatistikReport createBeitragStatistikReport(Nutzer nutzer, Beitrag beitrag, Date dateFrom) throws IllegalArgumentException {
		
		
		BeitragStatistikReport result= new BeitragStatistikReport();
		
		int i = 0;
		
		Row headline= new Row();
		
		headline.addColumn(new Column( "Beitrag"));
		headline.addColumn(new Column("Erstelldatum"));
		headline.addColumn(new Column ("Änderungsdatum"));
		headline.addColumn(new Column("Beitragsinhalt"));
		
		result.addRow(headline);
		
		//for (Beitrag b: beitrag) {
			
			Row postRow= new Row();
			
			
//			if=(dateFrom null){
				
				i++;
				
				
			
				
//			}
			
//		}
		
		
				return result;		
	}

	public Vector<Like> getLikesFromUser(Nutzer nutzer) {
		return this.getLikesFromUser(nutzer);
	}

	@Override
	public LikeStatistikReport createLikeStatistikReport(int like) throws IllegalArgumentException {
		return this.createLikeStatistikReport(like);
	}

	@Override
	public Vector<Nutzer> getNutzerByName(String vorname, String nachname) throws IllegalArgumentException {
		return this.getNutzerByName(vorname, nachname);
	}

	@Override
	public Vector<Nutzer> getNutzerByNickname(String nickname) throws IllegalArgumentException {
		return this.getNutzerByNickname(nickname);
	}
	
	@Override
	public Nutzer findNutzerByEmail(String mail) throws IllegalArgumentException {
		if(this.getPinnwandVerwaltung() == null) {
			return null;
		}
		return this.getPinnwandVerwaltung().checkEmail(mail);
	}

}
