package de.hdm.gwt.itprojektws18.shared.report;

import java.util.Vector;

public class HTMLReportWriter extends ReportWriter {

	/**
	 * Diese Variable wird mit dem Ergebnis einer Umwandlung (vgl.
	 * <code>process...</code>-Methoden) belegt. Format: HTML-Text
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
	 * HTML-Header-Text produzieren.
	 * 
	 * @return HTML-Text
	 * @author Thies
	 */
	public String getHeader() {
		StringBuffer result = new StringBuffer();

		result.append("<html><head><title></title></head><body>");
		return result.toString();
	}

	/**
	 * Umwandeln eines <code>Paragraph</code>-Objekts in HTML.
	 * 
	 * @param p der Paragraph
	 * @return HTML-Text
	 * @author Thies
	 */
	public String paragraph2HTML(Paragraph p) {
		if (p instanceof CompositeParagraph) {
			return this.paragraph2HTML((CompositeParagraph) p);
		} else {
			return this.paragraph2HTML((SimpleParagraph) p);
		}
	}

	/**
	 * Umwandeln eines <code>CompositeParagraph</code>-Objekts in HTML.
	 * 
	 * @param p der CompositeParagraph
	 * @return HTML-Text
	 * @author Thies
	 */
	public String paragraph2HTML(CompositeParagraph p) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < p.getNumParagraphs(); i++) {
			result.append("<p>" + p.getParagraphAt(i) + "</p>");
		}

		return result.toString();
	}

	/**
	 * Umwandeln eines <code>SimpleParagraph</code>-Objekts in HTML.
	 * 
	 * @param p der SimpleParagraph
	 * @return HTML-Text
	 * @author Thies
	 */
	public String paragraph2HTML(SimpleParagraph p) {
		return "<p>" + p.toString() + "</p>";
	}

	/**
	 * HTML-Trailer-Text produzieren.
	 * 
	 * @return HTML-Text
	 * @author Thies
	 */
	public String getTrailer() {
		return "</body></html>";
	}

	public void process(NutzerStatistikReport r) {
		this.resetReportText();
		StringBuffer result = new StringBuffer();
		// result.append("<h3>" + p.getTitle() + "</h3>");
		result.append("<table style=\"width:40%\"><tr>");
		result.append("<td><b>" + r.getTitle() + "</b></td></tr><tr>");

		result.append("<td><b>" + paragraph2HTML(r.getHeaderData()) + "</b></td>");

		result.append("<td width=200>" + paragraph2HTML(r.getImprint()) + "</td>");
		result.append("</tr><tr><td></td><td>" + r.getCreated().toString() + "</td></tr></table>");

		Vector<Row> rows = r.getRows();
		result.append("<br><br>");
		result.append("<table style=\"width:100%\">");
		for (int i = 0; i < rows.size(); i++) {
			Row row = rows.elementAt(i);
			result.append("<tr>");
			for (int k = 0; k < row.getColumns().size(); k++) {
				if (i == 0) {

					result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnAt(k) + "</td>");
				} else {
					if (i > 1) {
						result.append("<td style=\"border-top:1px solid silver\">" + row.getColumnAt(k) + "</td>");
					} else {
						result.append("<td valign=\"top\">" + row.getColumnAt(k) + "</td>");
					}
				}
			}
			result.append("</tr>");
		}

		result.append("</table>");
		this.reportText = result.toString();
	}

	public void process(BeitragStatistikReport r) {
		this.resetReportText();
		StringBuffer result = new StringBuffer();
		// result.append("<h3>" + p.getTitle() + "</h3>");
		result.append("<table style=\"width:40%\"><tr>");
		result.append("<td><b>" + r.getTitle() + "</b></td></tr><tr>");

		result.append("<td><b>" + paragraph2HTML(r.getHeaderData()) + "</b></td>");

		result.append("<td width=200>" + paragraph2HTML(r.getImprint()) + "</td>");
		result.append("</tr><tr><td></td><td>" + r.getCreated().toString() + "</td></tr></table>");

		Vector<Row> rows = r.getRows();
		result.append("<br><br>");
		result.append("<table style=\"width:100%\">");
		for (int i = 0; i < rows.size(); i++) {
			Row row = rows.elementAt(i);
			result.append("<tr>");
			for (int k = 0; k < row.getColumns().size(); k++) {
				if (i == 0) {

					result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnAt(k) + "</td>");
				} else {
					if (i > 1) {
						result.append("<td style=\"border-top:1px solid silver\">" + row.getColumnAt(k) + "</td>");
					} else {
						result.append("<td valign=\"top\">" + row.getColumnAt(k) + "</td>");
					}
				}
			}
			result.append("</tr>");
		}

		result.append("</table>");
		this.reportText = result.toString();
	}

	/**
	 * Auslesen des Ergebnisses der zuletzt aufgerufenen Prozessierungsmethode.
	 * 
	 * @return ein String im HTML-Format
	 * @author Thies
	 */
	public String getReportText() {
		return this.getHeader() + this.reportText + this.getTrailer();
	}

}
