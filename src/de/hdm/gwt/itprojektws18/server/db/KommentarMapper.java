package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Kommentar;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

/**
 * Diese Mapper Klasse stellt, Kommentar-Objekte auf einer relationalen 
 * Datenbank dar. Sie beinhaltet Methoden zum suchen, erzeugen, bearbeiten
 * und löschen von KommentarObjekten. DB-Strukturen können hierdurch
 *  in Objekt-Strukturen und anders herum umgewandelt werden.
 */

public class KommentarMapper {

	private static KommentarMapper kommentarMapper = null;

	protected KommentarMapper() {

	}

	public static KommentarMapper kommentarMapper() {
		if (kommentarMapper == null) {
			kommentarMapper = new KommentarMapper();
		}
		return kommentarMapper;
	}

	/**
	 * Einfügen eines Kommentar-Objekts in die DB
	 * 
	 * @author Matthias
	 */

	public Kommentar insertKommentar(Kommentar k) {
		
		//Herstellen der Verbindung zur DB Connection
		Connection con = DBConnection.connection();

		try {
			// Anlegen eines Statements
			Statement stmt = con.createStatement();
			
			//Überprüfung des derzeitig höchsten Primärschlüssels(id) der Tabelle kommentar
			ResultSet rs = stmt.executeQuery( "SELECT MAX(id) AS 'maxid' " + "FROM kommentar");
			
			if (rs.next()) {
				k.setId(rs.getInt("maxid")+1);
				stmt =con.createStatement();
				
			//Einfügen eines Kommentars in die nächsthöchste Zeile der Tabelle
			stmt.executeUpdate("INSERT INTO kommentar ( text, erstellzeitpunkt, beitrag_k_FK, nutzer_k_FK) " + "VALUES("
					+ k.getText() + "," + "'" + k.getErstellZeitpunkt() + "'" + "," + k.getBeitragFK() + ","
					+ k.getNutzerFK() + ")");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return k;

	}

	/**
	 * Diese Methode ermöglicht das editiern des übergebenen Kommentar-Objekts
	 * 
	 * @author Matthias
	 */

	public Kommentar updateKommentar(Kommentar k) {
		
		//Herstellen der Verbindung zur DB Connection
		Connection con = DBConnection.connection();

		try {
			// Anlegen eines Statements
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE kommentar set "+
					"text = " + "'" + k.getText() + "'" 
					+"WHERE id=" + "'" + k.getId() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return k;
	}

	/**
	 * Löschen des übergebenen KOmmentar-Objekts
	 * 
	 * @author Matthias
	 */

	public void deleteKommentar(Kommentar k) {
		
		//Herstellen der Verbindung zur DB Connection
		Connection con = DBConnection.connection();

		try {
			// Anlegen eines Statements
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM kommentar WHERE id =" + "'" + k.getId() + "'");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Löschen aller zugehörigen KOmmentar-Objekte des übergebenen Beitrag-Objektes
	 * 
	 * @param b
	 */

	public void deleteKommentareOf(Beitrag b) {
		
		//Herstellen der Verbindung zur DB Connection
		Connection con = DBConnection.connection();

		try {
			// Anlegen eines Statements
			Statement stmt = con.createStatement();
			/**
			 * beitragFK ist ein FK der Tabelle Kommentar welcher auf die Tabelle beitag
			 * verweist.
			 */
			stmt.executeUpdate("DELETE FROM kommentar WHERE beitrag_k_FK =" + "'" + b.getId() + "'");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Aufrufen eines KOmmentar Objekts by id
	 * 
	 * @author Matthias
	 */

	public Kommentar getKommentarById(int id) {
		
		//Herstellen der Verbindung zur DB Connection
		Connection con = DBConnection.connection();

		try {
			//Anlegen eines Statements
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, text, beitrag_k_FK, nutzer_k_FK, erstellzeitpunkt FROM kommentar WHERE id =" + "'" + id
							+ "'");
			
			//Umwandlung des Ergebnis in Objekt
			if (rs.next()) {
				Kommentar k = new Kommentar();
				k.setId(rs.getInt("id"));
				k.setText(rs.getString("text"));
				k.setBeitragFK(rs.getInt("beitrag_k_FK"));
				k.setNutzerFK(rs.getInt("nutzer_k_FK"));
				k.setErstellZeitpunkt(rs.getTimestamp("erstellzeitpunkt"));

				return k;

			}

		} catch (SQLException e) {
			e.printStackTrace();

			return null;
		}

		return null;

	}

	/**
	 * Aufrufen aller Kommentar-Objekte
	 * 
	 * @author Matthias
	 */

	public Vector<Kommentar> getAllKommentare() {
		
		//Herstellen der Verbindung zur DB Connection
		Connection con = DBConnection.connection();
		
		// Ergebnisvektor anlegen
		Vector<Kommentar> result = new Vector<Kommentar>();

		try {
			//Anlegeneines Statements
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM kommentar");

			//Umwandlung der Ergebnisse in Objekte und Befüllung des Vectors
			while (rs.next()){
				Kommentar k = new Kommentar();
				k.setId(rs.getInt("id"));
				k.setText(rs.getString("text"));
				k.setErstellZeitpunkt(rs.getTimestamp("erstellzeitpunkt"));

				result.addElement(k);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * Aufrufen aller Kommentar-Objekte eines Beitrag-Objekts
	 * 
	 * @author Matthias
	 */

	public Vector<Kommentar> getAllKommentareByBeitrag(int beitragFK) {

		//Herstellen der Verbindung zur DB Connection
		Connection con = DBConnection.connection();
		
		//Ergebnisvektor anlegen
		Vector<Kommentar> result = new Vector<Kommentar>();

		try {
			//Anlegen eines Statements
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, text, beitrag_k_FK, nutzer_k_FK, erstellzeitpunkt FROM kommentar WHERE beitrag_k_FK ="
							+ "'" + beitragFK + "'" + "ORDER BY erstellzeitpunkt ASC");
			
			//Umwandlung der Ergebnisse in Objekte und Befüllung des Vectors
			while (rs.next()) {
				Kommentar k = new Kommentar();
				k.setId(rs.getInt("id"));
				k.setText(rs.getString("text"));
				k.setBeitragFK(rs.getInt("beitrag_k_FK"));
				k.setNutzerFK(rs.getInt("nutzer_k_FK"));
				k.setErstellZeitpunkt(rs.getTimestamp("erstellzeitpunkt"));

				result.addElement(k);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public Vector<Kommentar> getAllKommentareByBeitrag(Beitrag b) {
		/*
		 * Auslesen der Beitrag-ID um diese dann an getAllKommentareByPinnwand(int
		 * beitragId) zu uebergeben
		 */
		return getAllKommentareByBeitrag(b.getId());
	}

	/**
	 * Ausgabe aller Kommentar-Objekte eines Nutzer-Objekts
	 * 
	 */
	
	public Vector<Kommentar> getAllKommentareByNutzer(int nutzerFK) {

		//Herstellen der Verbindung zur DB Connection
		Connection con = DBConnection.connection();
		
		//Ergebnisvektor anlegen
		Vector<Kommentar> result = new Vector<Kommentar>();

		try {
			//Anlegen eines Statements
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, text, erstellzeitpunkt, nutzer_k_FK, beitrag_k_FK FROM kommentar WHERE nutzer_k_FK =" + "'" + nutzerFK + "'");
					
			//Umwandlung der Ergebnisse in Objekte und Befüllung des Vectors
			while (rs.next()) {
				Kommentar k = new Kommentar();
				k.setId(rs.getInt("id"));
				k.setText(rs.getString("text"));
				k.setNutzerFK(rs.getInt("nutzer_k_FK"));
				k.setBeitragFK(rs.getInt("beitrag_k_FK"));
				k.setErstellZeitpunkt(rs.getTimestamp("erstellzeitpunkt"));

				result.addElement(k);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public Vector<Kommentar> getAllKommentareByNutzer(Nutzer n) {
		/*
		 * Auslesen der Nutzer-ID(entspricht dem Autor) um diese dann an
		 * getAllKommentareByNutzer (int nutzerId) zu uebergeben
		 */
		return getAllKommentareByNutzer(n.getId());
	}

}
