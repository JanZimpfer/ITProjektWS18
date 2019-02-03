package de.hdm.gwt.itprojektws18.shared.report;

import java.io.Serializable;

public class SimpleParagraph extends Paragraph implements Serializable{

	private static final long serialVersionUID = 1L;

	  /**
	   * Inhalt des Absatzes.
	   * @author thies
	   */
	  private String text = "";

	  /**
	   * <p>
	   * Serialisierbare Klassen, die mittels GWT-RPC transportiert werden sollen,
	   * müssen einen No-Argument-Konstruktor besitzen. Ist kein Konstruktor
	   * explizit angegeben, so existiert ini Java-Klassen implizit der
	   * Default-Konstruktor, der dem No-Argument-Konstruktor entspricht.
	   * </p>
	   * <p>
	   * Besitzt eine Klasse mind. einen explizit implementierten Konstruktor, so
	   * gelten nur diese explizit implementierten Konstruktoren. Der
	   * Default-Konstruktor gilt dann nicht. Wenn wir in einer solchen Situation
	   * aber dennoch einen No-Argument-Konstruktor benötigen, müssen wir diesen wie
	   * in diesem Beispiel explizit implementieren.
	   * </p>
	   * 
	   * @see #SimpleParagraph(String)
	   * @author thies
	   */
	  public SimpleParagraph() {
	  }

	  /**
	   * Dieser Konstruktor ermöglicht es, bereits bei Instantiierung von
	   * <code>SimpleParagraph</code>-Objekten deren Inhalt angeben zu können.
	   * 
	   * @param value der Inhalt des Absatzes
	   * @see #SimpleParagraph()
	   * @author thies
	   */
	  public SimpleParagraph(String value) {
	    this.text = value;
	  }

	  /**
	   * Auslesen des Inhalts.
	   * 
	   * @return Inhalt als String
	   * @author thies
	   */
	  public String getText() {
	    return this.text;
	  }

	  /**
	   * Überschreiben des Inhalts.
	   * 
	   * @param text der neue Inhalt des Absatzes.
	   * @author thies
	   */
	  public void setText(String text) {
	    this.text = text;
	  }

	  /**
	   * Umwandeln des <code>SimpleParagraph</code>-Objekts in einen String.
	   * @author thies
	   */
	  @Override
	public String toString() {
	    return this.text;
	  }
}
