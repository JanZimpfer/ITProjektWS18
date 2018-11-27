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
	   * Einen Unterabschnitt hinzufügen.
	   * 
	   * @param p der hinzuzufügende Unterabschnitt.
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
	   * Auslesen sämtlicher Unterabschnitte.
	   * 
	   * @return <code>Vector</code>, der sämtliche Unterabschnitte enthält.
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
	   * @param i der Index des gewünschten Unterabschnitts (0 <= i <n), mit n =
	   *          Anzahl der Unterabschnitte.
	   * 
	   * @return der gewünschte Unterabschnitt.
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
	     * Wir legen einen leeren Buffer an, in den wir sukzessive sämtliche
	     * String-Repräsentationen der Unterabschnitte eintragen.
	     * @author thies
	     */
	    StringBuffer result = new StringBuffer();
	 
	    for (int i = 0; i < this.subParagraphs.size(); i++) {
	      SimpleParagraph p = this.subParagraphs.elementAt(i);

	      /*
	       * den jew. Unterabschnitt in einen String wandeln und an den Buffer hängen.
	       * @author thies
	       */
	      result.append(p.toString() + "\n");
	    }

	    /*
	     * Schließlich wird der Buffer in einen String umgewandelt und
	     * zurückgegeben.
	     * @author thies
	     */
	    return result.toString();
	  }
}
