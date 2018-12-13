package de.hdm.gwt.itprojektws18.shared.report;

import java.util.Vector;


public class PlainTextReportWriter extends ReportWriter {

	/**
	 * Diese Variable wird mit dem Ergebnis einer Umwandlung (vgl.
	 * <code>process...</code>-Methoden) belegt. Format: Text
	 * 
	 * @author Thies
	 */
	private String reportText = "";

	/**
	 * Zurücksetzen der Variable <code>reportText</code>.
	 * 
	 * @author Thies
	 */
	public void resetReportText() {
		this.reportText = "";
	}

	/**
	 * Header-Text produzieren.
	 * 
	 * @return Text
	 * @author Thies
	 */
	public String getHeader() {
		// Wir benötigen für Demozwecke keinen Header.
		return "";
	}

	/**
	 * Trailer-Text produzieren.
	 * 
	 * @return Text
	 * @author Thies + leichte Abaenderung Florian
	 */
	public String getTrailer() {
		// Um das Ende des Reports zu markieren,
		// werden hier aneinandergereihte Punkte verwendet.
		return ".............................................";
	}

	public void process(NutzerStatistikReport r) {

	}

	public void process(BeitragStatistikReport r) {

	}

	/**
	 * Auslesen des Ergebnisses der zuletzt aufgerufenen Prozessierungsmethode.
	 * 
	 * @return ein String bestehend aus einfachem Text
	 * @author Thies
	 */
	public String getReportText() {
		return this.getHeader() + this.reportText + this.getTrailer();
	}

}
