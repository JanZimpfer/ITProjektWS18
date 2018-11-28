package de.hdm.gwt.itprojektws18.shared.report;

import java.io.Serializable;
import java.util.Vector;


public class Row implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	   * Speicherplatz f�r die Spalten der Zeile.
	   * @author thies
	   */
	  private Vector<Column> columns = new Vector<Column>();

	  /**
	   * Hinzuf�gen einer Spalte.
	   * 
	   * @param c das Spaltenobjekt
	   * @author thies
	   */
	  public void addColumn(Column c) {
	    this.columns.addElement(c);
	  }

	  /**
	   * Entfernen einer benannten Spalte
	   * 
	   * @param c das zu entfernende Spaltenobjekt
	   * @author thies
	   */
	  public void removeColumn(Column c) {
	    this.columns.removeElement(c);
	  }

	  /**
	   * Auslesen s�mtlicher Spalten.
	   * 
	   * @return <code>Vector</code>-Objekts mit s�mtlichen Spalten
	   * @author thies
	   */
	  public Vector<Column> getColumns() {
	    return this.columns;
	  }

	  /**
	   * Auslesen der Anzahl s�mtlicher Spalten.
	   * 
	   * @return int Anzahl der Spalten
	   * @author thies
	   */
	  public int getNumColumns() {
	    return this.columns.size();
	  }

	  /**
	   * Auslesen eines einzelnen Spalten-Objekts.
	   * 
	   * @param i der Index der auszulesenden Spalte (0 <= i < n), mit n = Anzahl
	   *          der Spalten.
	   * @return das gew�nschte Spaltenobjekt.
	   * @author thies
	   */
	  public Column getColumnAt(int i) {
	    return this.columns.elementAt(i);
	  }
}
