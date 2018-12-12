package de.hdm.gwt.itprojektws18.shared.report;

import java.util.Vector;

public class PlainTextReportWriter extends ReportWriter {

	private String reportText = "";

	public void resetReportText() {
		this.reportText = "";
	}

	public String getHeader() {
		return "";
	}

	public String getTrailer() {

		return "................................";
	}

	public void process(NutzerStatistikReport r) {

	}

	public void process(BeitragStatistikReport r) {

	}

	public String getReportText() {
		return this.getHeader() + this.reportText + this.getTrailer();
	}

}
