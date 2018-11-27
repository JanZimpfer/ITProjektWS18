package de.hdm.gwt.itprojektws18.shared.report;

import java.io.Serializable;
import java.util.Date;


public abstract class Report implements Serializable{
	
	private static final long serialVersionUID = 1L;

	  /**
	   * Ein kleines Impressum, das eine Art Briefkopf darstellt. Jedes Unternehmen
	   * einige Daten wie Firmenname, Adresse, Logo, etc. auf Gesch�ftsdokumenten
	   * ab. Dies gilt auch f�r die hier realisierten Reports.
	   * @author thies
	   */
	  private Paragraph imprint = null;

	  /**
	   * Kopfdaten des Berichts.
	   * @author thies
	   */
	  private Paragraph headerData = null;

	  /**
	   * Jeder Bericht kann einen individuellen Titel besitzen.
	   * @author thies
	   */
	  private String title = "Report";

	  /**
	   * Datum der Erstellung des Berichts.
	   * @author thies
	   */
	  private Date created = new Date();

	  /**
	   * Auslesen des Impressums.
	   * 
	   * @return Text des Impressums
	   * @author thies
	   */
	  public Paragraph getImprint() {
	    return this.imprint;
	  }

	  /**
	   * Setzen des Impressums.
	   * 
	   * @param imprint Text des Impressums
	   * @author thies
	   */
	  public void setImprint(Paragraph imprint) {
	    this.imprint = imprint;
	  }

	  /**
	   * Auslesen der Kopfdaten.
	   * 
	   * @return Text der Kopfdaten.
	   * @author thies
	   */
	  public Paragraph getHeaderData() {
	    return this.headerData;
	  }

	  /**
	   * Setzen der Kopfdaten.
	   * 
	   * @param headerData Text der Kopfdaten.
	   * @author thies
	   */
	  public void setHeaderData(Paragraph headerData) {
	    this.headerData = headerData;
	  }

	  /**
	   * Auslesen des Berichtstitels.
	   * 
	   * @return Titeltext
	   * @author thies
	   */
	  public String getTitle() {
	    return this.title;
	  }

	  /**
	   * Setzen des Berichtstitels.
	   * 
	   * @param title Titeltext
	   * @author thies
	   */
	  public void setTitle(String title) {
	    this.title = title;
	  }

	  /**
	   * Auslesen des Erstellungsdatums.
	   * 
	   * @return Datum der Erstellung des Berichts
	   * @author thies
	   */
	  public Date getCreated() {
	    return this.created;
	  }

	  /**
	   * Setzen des Erstellungsdatums. <b>Hinweis:</b> Der Aufruf dieser Methoden
	   * ist nicht unbedingt erforderlich, da jeder Report bei seiner Erstellung
	   * automatisch den aktuellen Zeitpunkt festh�lt.
	   * 
	   * @param created Zeitpunkt der Erstellung
	   * @author thies
	   */
	  public void setCreated(Date created) {
	    this.created = created;
	  }
	

}
