package de.hdm.gwt.itprojektws18.shared.report;

import java.util.Vector;


import java.io.Serializable;

public abstract class SimpleReport extends Report implements Serializable{

	private static final long serialVersionUID = 1L;
	
	 /**
	   * Tabelle mit Positionsdaten. Die Tabelle wird zeilenweise in diesem
	   * <code>Vector</code> abgelegt.
	   * @author thies
	   */
	  private Vector<Row> table = new Vector<Row>();

	  /**
	   * Hinzufügen einer Zeile.
	   * 
	   * @param r die hinzuzufügende Zeile
	   * @author thies
	   */
	  public void addRow(Row r) {
	    this.table.addElement(r);
	  }

	  /**
	   * Entfernen einer Zeile.
	   * 
	   * @param r die zu entfernende Zeile.
	   * @author thies
	   */
	  public void removeRow(Row r) {
	    this.table.removeElement(r);
	  }

	  /**
	   * Auslesen sämtlicher Positionsdaten.
	   * 
	   * @return die Tabelle der Positionsdaten
	   * @author thies
	   */
	  public Vector<Row> getRows() {
	    return this.table;
	  }
}
