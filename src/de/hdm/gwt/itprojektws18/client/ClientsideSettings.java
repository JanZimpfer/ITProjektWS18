package de.hdm.gwt.itprojektws18.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.gwt.itprojektws18.shared.CommonSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltung;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.ReportGenerator;
import de.hdm.gwt.itprojektws18.shared.ReportGeneratorAsync;

/**
 * Klasse mit Eigenschaften und Diensten, die fuer alle client-seitigen
 * Klassen relevant sind.
 * 
 * @author Thies
 * @see BankProjekt 2.0
 */

public class ClientsideSettings extends CommonSettings{

	
	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitigen
	 * Dienst PinnwandVerwaltung
	 */
	private static PinnwandVerwaltungAsync pinnwandVerwaltung = null;
	
	
	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitigen
	 * Dienst ReportGenerator
	 */
	private static ReportGeneratorAsync reportGenerator = null;
	
	
	/**
	 * Name des client-seitigen Loggers
	 */
	private static final String LOGGER_NAME = "PinnwandVerwaltung Web Client";
	
	
	/**
	 * Instanz des client-seitigen Loggers
	 */
	private static final Logger log = Logger.getLogger(LOGGER_NAME);
	
	
	/**
	 * Auslesen des applikationsweiten client-seitig zentralen Loggers
	 * 
	 * @return Logger-Instanz
	 */
	public static Logger getLogger() {
		return log;
	}
	
	
	/**
	 * Anlegen und Auslesen der PinnwandVerwaltung.
	 */
	public static PinnwandVerwaltungAsync getPinnwandVerwaltung() {
		//Ueberpruefe ob es bisher eine PinnwandVerwaltung-Instanz gab
		if (pinnwandVerwaltung == null) {
			//Instantiierung PinnwandVerwaltung
			pinnwandVerwaltung = GWT.create(PinnwandVerwaltung.class);
		}
		
		//Rueckgabe der PinnwandVerwaltung
		return pinnwandVerwaltung;
	}
	
	
	/**
	 * Anlegen und Auslesen des ReportGenerators
	 */
	public static ReportGeneratorAsync getReportGenerator() {
		//Ueberpruefe ob es bisher eine ReportGenerator-Instanz gab
		if (reportGenerator == null) {
			//Instantiierung ReportGenerator
			reportGenerator = GWT.create(ReportGenerator.class);
			
			final AsyncCallback<Void> initReportGeneratorCallback = new AsyncCallback<Void>() {

				@Override
				public void onFailure(Throwable caught) {
					ClientsideSettings.getLogger().severe(
							"Der ReportGenerator konnte nicht initialisiert werden!");
					
				}

				@Override
				public void onSuccess(Void result) {
					ClientsideSettings.getLogger().info(
							"Der ReportGenerator wurde initialisiert.");
					
				}
				
			};
			
			reportGenerator.init(initReportGeneratorCallback);
		}
		
		//Rueckgabe des ReportGenerators
		return reportGenerator;
	}
	
}

