package de.hdm.gwt.itprojektws18.server;

import java.util.logging.Logger;

import de.hdm.gwt.itprojektws18.shared.CommonSettings;

/**
 * Klasse mit Eigenschaften und Diensten, die fuer alle Server-seitigen Klassen
 * relevant sind.
 * 
 * @author thies
 *
 */

public class ServersideSettings extends CommonSettings {

	private static final String LOGGER_NAME = "PinnwandVerwaltung Server";
	private static final Logger log = Logger.getLogger(LOGGER_NAME);
	
	/**
	 * Auslesen des serverseitig zentralen Loggers.
	 * @return Logger Instanz fuer die Server-Seite
	 */
	public static Logger getLogger() {
		return log;
	}
}
