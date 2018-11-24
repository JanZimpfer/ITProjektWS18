package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;


/**
 * Dies ist eine Mapper-Klasse, die Pinnwand-Objekte auf eine relationale
 * Datenbank darstellt. Sie enthält Methoden zum erstellen, suchen, bearbeiten
 * und löschen.
 * 
 * @author Florian
 */

public class PinnwandMapper {

	private static PinnwandMapper pinnwandMapper = null;

	protected PinnwandMapper() {
	}

	public static PinnwandMapper pinnwandMapper() {
		if (pinnwandMapper == null) {
			pinnwandMapper = new PinnwandMapper();
		}

		return pinnwandMapper;
	}
	
/** 
 *Diese Mehtode dient dazu, eine Pinnwnad anhand ihrer ID zu finden. NICHT FERTIG!
 */
	public Pinnwand getPinnwandByID(int id) {
		   Connection con = DBConnection.connection();
	}
	
}

