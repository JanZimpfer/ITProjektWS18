package de.hdm.gwt.itprojektws18.server.report;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.Date;
//import java.text.SimpleDateFormat;
import java.util.Vector;

import de.hdm.gwt.itprojektws18.server.PinnwandVerwaltungImpl;
import de.hdm.gwt.itprojektws18.shared.report.*;
import de.hdm.gwt.itprojektws18.shared.bo.*;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltung;
import de.hdm.gwt.itprojektws18.shared.ReportGenerator;
import de.hdm.gwt.itprojektws18.shared.report.BeitragStatistikReport;

/**
 * Implementierung des <code>ReportGenerator</code>-Interface. Die technische
 * Realisierung bzgl. <code>RemoteServiceServlet</code> bzw. GWT RPC erfolgt
 * analog zu {@PinnwandVerwaltungImpl}. Für Details zu GWT RPC siehe dort.
 * 
 * @see ReportGenerator
 * @author in Anlehnung Thies
 */

@SuppressWarnings("serial")

public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	/**
	 * Zugriff auf die PinnwandAdmin um essentielle Methoden für die Koexistenz von
	 * Datenobjekten (vgl. bo- Package) bietet.
	 * 
	 * @author Ayse, in Anlehnung Thies
	 */
	private PinnwandVerwaltung pinnwandAdmin = null;

	public ReportGeneratorImpl() throws IllegalArgumentException {

	}

	@Override
	public void init() throws IllegalArgumentException {

		/*
		 * Ein ReportGeneratorImpl-Objekt instantiiert für seinen Eigenbedarf eine
		 * PinnwandVerwaltungImpl-Instanz.
		 * 
		 * @author in Anlehnung Thies
		 */

		PinnwandVerwaltungImpl pinnwandVerwaltung = new PinnwandVerwaltungImpl();
		pinnwandVerwaltung.init();
		this.pinnwandAdmin = pinnwandVerwaltung;
	}

	/**
	 * Auslesen der zugehörigen PinnwandVerwaltung (interner Gebrauch).
	 * 
	 * @return das PinnwandAdminobjekt
	 */
	protected PinnwandVerwaltung getPinnwandVerwaltung() {
		return this.pinnwandAdmin;
	}

	/**
	 * Hinzufügen des Report-Impressums. Diese Methode ist aus den
	 * <code>create...</code>-Methoden ausgegliedert, da jede dieser Methoden diese
	 * Tätigkeiten redundant auszuführen hätte. Stattdessen rufen die
	 * <code>create...</code>-Methoden diese Methode auf.
	 * 
	 * @param r der um das Impressum zu erweiternde Report.
	 * @author in Anlehnung Thies
	 */

	protected void addImprint(Report r) {

		/*
		 * Das Imressum soll mehrzeilig sein.
		 */

		CompositeParagraph imprint = new CompositeParagraph();
		imprint.addSubParagraph(new SimpleParagraph("@tellIT"));
		imprint.addSubParagraph(new SimpleParagraph("Nobelstraße 10"));
		imprint.addSubParagraph(new SimpleParagraph("70569 Stuttgart"));

		// Das eigentliche Hinzufügen des Impressums zum Report.

		r.setImprint(imprint);
	}

	/**
	 * Diese Methode soll alle Abonnements, Likes, Beitr�ge und Kommentare in einem
	 * Zeitraum von einem Nutzer anzeigen (non-Javadoc)
	 * 
	 * @see de.hdm.gwt.itprojektws18.shared.ReportGenerator#createNutzerStatistikReport(de.hdm.gwt.itprojektws18.shared.bo.Nutzer)
	 *
	 * @return der fertige Report
	 */
	@Override
	public NutzerStatistikReport createNutzerStatistikReport(String nickname, Date firstDate, Date lastDate)
			throws IllegalArgumentException {

		if (this.getPinnwandVerwaltung() == null) {
			return null;
		}

		Nutzer n = this.pinnwandAdmin.getNutzerByNickname(nickname);

		if (n != null) {

			// Ein leeren Report anlegen.
			NutzerStatistikReport result = new NutzerStatistikReport();
			// Jeder Report hat einen Titel.
			result.setTitle("Nutzerstatistik:");

			/**
			 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben auf
			 * dem Report stehen) des Reports. Die Kopfdaten sind mehrzeilig, daher die
			 * Verwendung von CompositeParagraph.
			 * 
			 * @author in Anlehnung Thies
			 */
			CompositeParagraph header = new CompositeParagraph();
			//Impressumsbezeichnung hinzufügen
			header.addSubParagraph(new SimpleParagraph("Impressum: "));
			//Hinzufügen des zusammengestellten Kopfdaten 
			result.setHeaderData(header);

			//Erstellen und Abrufen der benötigten Ergebnisvektoren mittels PinnwandVerwaltung
			Vector<Abonnement> abonnenten = this.getPinnwandVerwaltung().getAllAbosForNutzerWithTime(n, firstDate, lastDate);
			Vector<Abonnement> abos = this.getPinnwandVerwaltung().getAllAbosForWithTime(n, firstDate, lastDate);
			Vector<Kommentar> kommentar = this.getPinnwandVerwaltung().getAllKommentareByNutzerWithTime(n, firstDate,
					lastDate);
			Vector<Like> likes = this.getPinnwandVerwaltung().getAllLikesByNutzerWithTime(n, firstDate, lastDate);
			Vector<Beitrag> alleBeitraege = this.getPinnwandVerwaltung().getAllBeitraegeByNutzerWithTime(n, firstDate,
					lastDate);

			// Kopfzeile für die Nutzerstatistik- Tabelle.
			Row headline = new Row();
			/**
			 * Wir wollen Zeilen mit 5 Spalten in der Tabelle erzeugen. In die erste Spalte
			 * schreiben wir die Anzahl an Abonnenten, in die zweite Spalte die Anzahl von
			 * Abonnements des Nutzers, in die dritte Spalte die jeweilige Beitraganzahl,
			 * in die vierte Spalte die jeweilige Kommentaranzahl und in die fünfte Spalte die jeweilige Likeanzahl.
			 * In der Kopfzeile werden die entsprechenden Überschriften angelegt.
			 * 
			 * @author Ayse, in Anlehnung Thies
			 */
			headline.addColumn(new Column("Abonnenten"));
			headline.addColumn(new Column("Abonniert"));
			headline.addColumn(new Column("Beitraganzahl"));
			headline.addColumn(new Column("Kommentaranzahl"));
			headline.addColumn(new Column("Likeanzahl"));

			// Hinzufügen der Kopfzeile.
			result.addRow(headline);

			// Eine leere Zeile anlegen.
			Row row = new Row();

			// Erste Spalte: Abonnentenanzahl
			row.addColumn(new Column(abonnenten.size() + ""));
			// Zweite Spalte: Abonnementanzahl hinzufügen.
			row.addColumn(new Column(abos.size() + ""));
			// Dritte Spalte: Beitraganzahl hinzufügen.
			row.addColumn(new Column(alleBeitraege.size() + ""));
			// Vierte Spalte: Kommentaranzahl hinzufügen.
			row.addColumn(new Column(kommentar.size() + ""));
			// Fünfte Spalte: Likeanzahl hinzufügen.
			row.addColumn(new Column(likes.size() + ""));

			// und die Zeilen dem Report hinzufügen
			result.addRow(row);

			// Impressum hinzufügen
			this.addImprint(result);

			// zum Schluss müssen wir noch den fertigen Report zurückgeben
			return result;

		} else {
			return null;
		}

	}

	/*
	 * Diese Methode soll alle Kommentare und Likes für einen Zeitraum aller
	 * Beitr�ge anzeigen (non-Javadoc)
	 * 
	 * @see
	 * de.hdm.gwt.itprojektws18.shared.ReportGenerator#createNutzerStatistikReport(
	 * de.hdm.gwt.itprojektws18.shared.bo.Nutzer) *@return der fertige Report
	 * 
	 */

	@Override
	public BeitragStatistikReport createBeitragStatistikReport(Date startDate, Date endDate)
			throws IllegalArgumentException {
		if (this.getPinnwandVerwaltung() == null) {
			return null;
		}

		// Ein leeren Report anlegen.
		BeitragStatistikReport result = new BeitragStatistikReport();
		// Jeder Report hat einen Titel
		result.setTitle("Beitragstatistik:");

		/**
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben auf
		 * dem Report stehen) des Reports. Die Kopfdaten sind mehrzeilig, daher die
		 * Verwendung von CompositeParagraph.
		 * 
		 * @author in Anlehnung Thies
		 */
		CompositeParagraph header = new CompositeParagraph();
		//Impressumsbezeichnung hinzufügen
		header.addSubParagraph(new SimpleParagraph("Impressum: "));
		//Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		result.setHeaderData(header);

		//Erstellen und Abrufen des benötigten Ergebnisvektors mittels PinnwandVerwaltung
		Vector<Beitrag> alleBeitraege = this.getPinnwandVerwaltung().getAllBeitraegeWithTime(startDate,
				endDate);
		// Kopfzeile für die Beitragstatistik- Tabelle.
		Row headline = new Row();
		/**
		 * Wir wollen Zeilen mit 4 Spalten in der Tabelle erzeugen. In die erste Spalte
		 * schreiben wir den Autor eines Beitrags, in die zweite Spalte den betreffenden Beitrag,
		 * in die dritte Spalte die jeweilige Kommentaranzahl und in die vierte Zeile die Likeanzahl.
		 * In der Kopfzeile werden die entsprechenden Überschriften angelegt.
		 * 
		 * @author Ayse, in Anlehnung Thies
		 */
		
		headline.addColumn(new Column("Autor"));
		headline.addColumn(new Column("Beitrag"));
		headline.addColumn(new Column("Kommentare"));
		headline.addColumn(new Column("Likes"));

		// Hinzufügen der Kopfzeile.
		result.addRow(headline);

		for (Beitrag beitrag2 : alleBeitraege) {
			
			Nutzer nutzerBeitrag =  this.getPinnwandVerwaltung().getNutzerbyID(beitrag2.getNutzerFK());
			
			// Eine leere Zeile anlegen.
			Row row = new Row();

			row.addColumn(new Column(nutzerBeitrag.getNickname()));
			// Erste Spalte: Beitrag anzeigen.
			row.addColumn(new Column(beitrag2.getText()));
			// Zweite Spalte: Kommentaranzahl hinzufügen.
			row.addColumn(new Column(this.getPinnwandVerwaltung().getAllKommentareByBeitrag(beitrag2).size() + ""));
			// Dritte Spalte: Likeanzahl hinzufügen.
			row.addColumn(new Column(this.getPinnwandVerwaltung().getAllLikesByBeitrag(beitrag2).size() + ""));
			// und die Zeilen dem Report hinzufügen
			result.addRow(row);

		}
		// Impressum hinzufügen
		this.addImprint(result);

		// zum Schluss müssen wir noch den fertigen Report zurückgeben.
		return result;

	}

	@Override
	public Nutzer getNutzerByNickname(Nutzer nickname) throws IllegalArgumentException {
		if (this.getPinnwandVerwaltung() == null) {

			return null;

		}
		return this.getPinnwandVerwaltung().getNutzerByNickname(nickname.getNickname());
	}
	
	@Override
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

		Nutzer n = new Nutzer();

		if (this.getPinnwandVerwaltung() == null) {
			return null;
		}
		n = this.getPinnwandVerwaltung().checkEmail(mail);

		if (n != null) {
			return n;
		} else {
			return null;
		}
	}

	@Override
	public Nutzer getNutzerById(int nutzerID) throws IllegalArgumentException {

		Nutzer n = new Nutzer();

		if (this.getPinnwandVerwaltung() == null) {
			return null;
		}
		n = this.getPinnwandVerwaltung().getNutzerbyID(nutzerID);

		if (n != null) {
			return n;
		} else {
			return null;
		}
	}

	@Override
	public Vector<Beitrag> getBeitrageByNutzer(Nutzer n) throws IllegalArgumentException {
		if (this.getPinnwandVerwaltung() == null) {
			return null;
		}
		return this.getPinnwandVerwaltung().getAllBeitraegeByNutzer(n);
	}
	
	@Override
	public Vector<Nutzer> getAllNutzer() throws IllegalArgumentException {
		if (this.getPinnwandVerwaltung() == null) {
			return null;
		}
		return this.getPinnwandVerwaltung().getAllNutzer();
	}

}
