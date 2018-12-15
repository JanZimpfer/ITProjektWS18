package de.hdm.gwt.itprojektws18.shared.bo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class BusinessObject implements Serializable{
	
	private static final long serialversionUID = 1L;
	
	/**
	 * Eindeutige Identifikationsnummer einer Instanz der Klasse
	 */
	private int id = 0;
	
	/**
	 * Eindeutiger Erstellungszeitpunkt einer Instanz dieser Klasse
	 */
	
	private Date erstellZeitpunkt;

//	private Date erstellzeitpunkt = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

	
//	/**
//	 * Default Konstruktor für BusinessObject
//	 */
//	public BusinessObject() {
//		
//	}
//	
//	/**
//	 * Ermittlung des Erstellzeitupunkt für ein BusinessObject
//	 * @param erstellZeitpunkt
//	 */
//	public BusinessObject(Timestamp erstellZeitpunkt) {
//		this.erstellZeitpunkt = (Timestamp) new Date();
//	}


	/**
	 * Default Konstruktor für BusinessObject
	 */
	public BusinessObject() {
		
	}
	
	/**
	 * Ermittlung des Erstellzeitupunkt für ein BusinessObject
	 * @param erstellZeitpunkt
	 */
	public BusinessObject(Date erstellZeitpunkt) {
		this.erstellZeitpunkt = new Date();
	}

	
	/**
	 * Id wird ausgelesen
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Id wird gesetzt
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Erstellungszeitpunkt wird ausgelesen
	 * @return
	 */
	public Date getErstellZeitpunkt() {
		return erstellZeitpunkt;
	}

	/**
	 * Erstellungszeitpunkt wird gesetzt
	 * @param erstellZeitpunkt
	 */

	public void setErstellZeitpunkt(Date erstellZeitpunkt) {

		this.erstellZeitpunkt = new Date ();

	}
	
	/**
	   * <p>
	   * Feststellen der <em>inhaltlichen</em> Gleichheit zweier
	   * <code>BusinessObject</code>-Objekte. Die Gleichheit wird in diesem Beispiel auf eine
	   * identische ID beschränkt.
	   * </p>
	   * <p>
	   * <b>ACHTUNG:</b> Die inhaltliche Gleichheit nicht mit dem Vergleich der
	   * <em>Identität</em> eines Objekts mit einem anderen verwechseln!!! Dies
	   * würde durch den Operator <code>==</code> bestimmt. Bei Unklarheit hierzu
	   * können Sie nocheinmal in die Definition des Sprachkerns von Java schauen.
	   * Die Methode <code>equals(...)</code> ist für jeden Referenzdatentyp
	   * definiert, da sie bereits in der Klasse <code>Object</code> in einfachster
	   * Form realisiert ist. Dort ist sie allerdings auf die simple Bestimmung der
	   * Gleicheit der Java-internen Objekt-ID der verglichenen Objekte beschränkt.
	   * In unseren eigenen Klassen können wir diese Methode überschreiben und ihr
	   * mehr Intelligenz verleihen.
	   * </p>
	   * 
	   * @author thies
	   */
	public boolean equals(Object o) {
		
		if(o != null && o instanceof BusinessObject) {
			BusinessObject bo = (BusinessObject) o;
			
		try {
			if(bo.getId() == this.id) return true;
		} 
		catch(IllegalArgumentException e) {
			return false;	
		}
	 }
		return false;
	}
	
	/**
	 * String-Repräsentation eines Objekts
	 */
	public String toString() {
		
		return this.getClass().getName() + "Id. Nr:" + this.id;
	}
	
	/**
	 * Kennzeichnung eines Objekts durch eine Id
	 */
	public int hashCode() {
		return this.id;
	}
	

}
