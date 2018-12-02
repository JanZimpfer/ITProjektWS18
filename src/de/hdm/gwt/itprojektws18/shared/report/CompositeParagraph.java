package de.hdm.gwt.itprojektws18.shared.report;

import java.io.Serializable;
import java.util.Vector;


public class CompositeParagraph extends Paragraph implements Serializable{

	private static final long serialVersionUID = 1L;

	  /**
	   * Speicherort der Unterabschnitte.
	   * @author thies
	   */
	  private Vector<SimpleParagraph> subParagraphs = new Vector<SimpleParagraph>();

	  /**
	   * Einen Unterabschnitt hinzuf�gen.
	   * 
	   * @param p der hinzuzuf�gende Unterabschnitt.
	   * @author thies
	   */
	  public void addSubParagraph(SimpleParagraph p) {
	    this.subParagraphs.addElement(p);
	  }

	  /**
	   * Einen Unterabschnitt entfernen.
	   * 
	   * @param p der zu entfernende Unterabschnitt.
	   * @author thies
	   */
	  public void removeSubParagraph(SimpleParagraph p) {
	    this.subParagraphs.removeElement(p);
	  }

	  /**
	   * Auslesen s�mtlicher Unterabschnitte.
	   * 
	   * @return <code>Vector</code>, der s�mtliche Unterabschnitte enth�lt.
	   * @author thies
	   */
	  public Vector<SimpleParagraph> getSubParagraphs() {
	    return this.subParagraphs;
	  }

	  /**
	   * Auslesen der Anzahl der Unterabschnitte.
	   * 
	   * @return Anzahl der Unterabschnitte
	   * @author thies
	   */
	  public int getNumParagraphs() {
	    return this.subParagraphs.size();
	  }

	  /**
	   * Auslesen eines einzelnen Unterabschnitts.
	   * 
	   * @param i der Index des gew�nschten Unterabschnitts (0 <= i <n), mit n =
	   *          Anzahl der Unterabschnitte.
	   * 
	   * @return der gew�nschte Unterabschnitt.
	   * @author thies
	   */
	  public SimpleParagraph getParagraphAt(int i) {
	    return this.subParagraphs.elementAt(i);
	  }

	  /**
	   * Umwandeln eines <code>CompositeParagraph</code> in einen
	   * <code>String</code>.
	   * @author thies
	   */
	  @Override
	public String toString() {
	    /*
	     * Wir legen einen leeren Buffer an, in den wir sukzessive s�mtliche
	     * String-Repr�sentationen der Unterabschnitte eintragen.
	     * @author thies
	     */
	    StringBuffer result = new StringBuffer();
	 
	    for (int i = 0; i < this.subParagraphs.size(); i++) {
	      SimpleParagraph p = this.subParagraphs.elementAt(i);

	      /*
	       * den jew. Unterabschnitt in einen String wandeln und an den Buffer h�ngen.
	       * @author thies
	       */
	      result.append(p.toString() + "\n");
	    }

	    /*
	     * Schlie�lich wird der Buffer in einen String umgewandelt und
	     * zur�ckgegeben.
	     * @author thies
	     */
	    return result.toString();
	  }
}
