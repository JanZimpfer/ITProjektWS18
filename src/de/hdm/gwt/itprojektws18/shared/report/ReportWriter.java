package de.hdm.gwt.itprojektws18.shared.report;


public abstract class ReportWriter {
	
	/**
	   * Übersetzen eines <code>BeitragStatistikReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu übersetzende Report
	   * @author thies + leichte Abänderung Niklas
	   */
	  public abstract void process(BeitragStatistikReport r);

	  /**
	   * Übersetzen eines <code>NutzerStatistikReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu übersetzende Report
	   * @author thies + leichte Abänderung Niklas
	   */
	  public abstract void process(NutzerStatistikReport r);

}
