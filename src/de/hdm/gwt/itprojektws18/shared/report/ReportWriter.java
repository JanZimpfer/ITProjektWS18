package de.hdm.gwt.itprojektws18.shared.report;


public abstract class ReportWriter {
	
	/**
	   * �bersetzen eines <code>BeitragStatistikReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu �bersetzende Report
	   * @author thies + leichte Ab�nderung niklas
	   */
	  public abstract void process(BeitragStatistikReport r);

	  /**
	   * �bersetzen eines <code>NutzerStatistikReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu �bersetzende Report
	   * @author thies + leichte Ab�nderung niklas
	   */
	  public abstract void process(NutzerStatistikReport r);

}
