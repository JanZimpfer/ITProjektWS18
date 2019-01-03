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

		imprint.addSubParagraph(new SimpleParagraph("@Hdm Network"));
		imprint.addSubParagraph(new SimpleParagraph("Nobelstra�e 10"));
		imprint.addSubParagraph(new SimpleParagraph("70569 Stuttgart"));

		r.setImprint(imprint);
	}
	
	/*
	 * Diese Methode soll alle Abonnements, Likes, Beitr�ge und Kommentare in einem Zeitraum von einem Nutzer anzeigen 
	 * (non-Javadoc)
	 * @see de.hdm.gwt.itprojektws18.shared.ReportGenerator#createNutzerStatistikReport(de.hdm.gwt.itprojektws18.shared.bo.Nutzer)
	 */
	@Override
	public NutzerStatistikReport createNutzerStatistikReport(Nutzer nutzer) throws IllegalArgumentException {

		if (this.getPinnwandVerwaltung() == null) {
			return null;
		} 

			Nutzer n = getNutzerById(nutzer.getId());
		
		
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss ");

			Vector<Nutzer> alleNutzer = new Vector<Nutzer>();
			NutzerStatistikReport result = new NutzerStatistikReport();
			result.setTitle("Nutzerstatistik:");
			CompositeParagraph header = new CompositeParagraph();
			header.addSubParagraph(new SimpleParagraph("Nutzer: " + nutzer.getNickname()));
			result.setHeaderData(header);
			
			Vector<Beitrag> alleBeitraege = getBeitrageByNutzer(n);
			
			Row headline = new Row();
			headline.addColumn(new Column("Nutzer"));
			headline.addColumn(new Column("Abonnementanzahl"));
			headline.addColumn(new Column("Beitr�ge"));
			headline.addColumn(new Column("Kommentare"));
			headline.addColumn(new Column("Likes"));

			result.addRow(headline);
			
			Row row = new Row();
			row.addColumn(new Column(nutzer.getNickname()));
			row.addColumn(new Column(""));
			row.addColumn(new Column(alleBeitraege.size()+""));
		
			result.addRow(row);
			
			this.addImprint(result);

		
		return result;

	}
	
	/*
	 * Diese Methode soll alle  Kommentare und Likes f�r einen Zeitraum aller Beitr�ge anzeigen 
	 * (non-Javadoc)
	 * @see de.hdm.gwt.itprojektws18.shared.ReportGenerator#createNutzerStatistikReport(de.hdm.gwt.itprojektws18.shared.bo.Nutzer)
	 */
	
	@Override
	public BeitragStatistikReport createBeitragStatistikReport(Nutzer nutzer, Beitrag beitrag, Date dateFrom) throws IllegalArgumentException {
		if (this.getPinnwandVerwaltung() == null) {
			return null;
		} 

			Nutzer n = getNutzerById(nutzer.getId());
		
		
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss ");

			Vector<Nutzer> alleNutzer = new Vector<Nutzer>();
			BeitragStatistikReport result = new BeitragStatistikReport();
			result.setTitle("Beitragstatistik:");
			CompositeParagraph header = new CompositeParagraph();
			header.addSubParagraph(new SimpleParagraph("Nutzer: " + nutzer.getNickname()));
			result.setHeaderData(header);
			
			Vector<Beitrag> alleBeitraege = getBeitrageByNutzer(n);
			
			Row headline = new Row();
			headline.addColumn(new Column("Nutzer"));
			headline.addColumn(new Column("Beitr�ge"));
			headline.addColumn(new Column("Kommentare"));
			headline.addColumn(new Column("Likes"));

			result.addRow(headline);
			
			Row row = new Row();
			row.addColumn(new Column(nutzer.getNickname()));
			row.addColumn(new Column(""));
			row.addColumn(new Column(alleBeitraege.size()+""));
			result.addRow(row);
			
			this.addImprint(result);

		
		return result;

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

	

	public Vector<Like> getLikesFromUser(Nutzer nutzer) {
		return this.getLikesFromUser(nutzer);
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
	@Override
	public Nutzer getNutzerById(int nutzerID) throws IllegalArgumentException {
		if(this.getPinnwandVerwaltung() == null) {
			return null;
		}
		return this.getPinnwandVerwaltung().getNutzerbyID(nutzerID);
	}
	@Override
	public Vector<Beitrag> getBeitrageByNutzer(Nutzer n) throws IllegalArgumentException {
		if(this.getPinnwandVerwaltung() == null) {
			return null;
		}
		return this.getPinnwandVerwaltung().getAllBeitraegeByNutzer(n);
	}

}
