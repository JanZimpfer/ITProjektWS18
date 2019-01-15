package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.sql.Timestamp;

import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

/**
 * Dies ist eine Mapper-Klasse, die Pinnwand-Objekte auf eine relationale
 * Datenbank darstellt. Sie enthält Methoden zum erstellen, suchen, bearbeiten
 * und löschen sowie zum ermitteln einer Pinnwand anhand des Nutzers.
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
	 * Einfügen eines Pinnwand-Objekts in die Datenbank:
	 * 
	 * @param p
	 * @return Pinnwand p
	 */
	public Pinnwand insertPinnwand(Pinnwand p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Als erstes wird überprüft, welches der derzeit höchste Primärschlüssel ist.

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS 'maxid' " + "FROM pinnwand ");

			if (rs.next()) {

				p.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO pinnwand (id, nutzer_p_FK, erstellzeitpunkt) " + 
				"VALUES (" + 
				
				p.getId()+ "," + 
				p.getNutzerFK() + "," + 
				"'" + p.getErstellZeitpunkt() + "'" + ")"); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return p;
	}

	/**
	 * Ändern eines Objekts aus der Datenbank:
	 * 
	 * @param p
	 * @return Pinnwand p
	 */
	public Pinnwand updatePinnwand(Pinnwand p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate(
					"UPDATE pinnwand set nutzer_p_FK= " + "'"  + p.getNutzerFK() + "'" + "WHERE id= " + "'" + p.getId() + "'");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}

	/**
	 * Löschen der Daten eines Pinnwand-Objekts aus der Datenbank
	 * 
	 * @param p
	 */
	public void deletePinnwand(Pinnwand p) {
		Connection con = DBConnection.connection();

		try {
			// Statement ohne Inhalt anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM pinnwand WHERE id=" + "'" + p.getId() + "'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Diese Mehtode dient dazu, eine Pinnwand anhand der ID des zugehörigen Nutzers
	 * zu finden.
	 * 
	 * @param id
	 */
	public Pinnwand getPinnwandByNutzer(int id) {

		Connection con = DBConnection.connection();

		try {

			// Statement ohne Inhalt anlegen
			Statement stmt = con.createStatement();
			// Query ausführen
			ResultSet rs = stmt
					.executeQuery("SELECT id, nutzer_p_FK FROM pinnwand " + "WHERE nutzer_p_FK = " +
			"'" + id + "'" );

			// Prüfen ob ein Ergebnis vorliegt
			if (rs.next()) {
				// Vorhandenes Ergebnis in ein Objekt umwandeln
				Pinnwand p = new Pinnwand();
				p.setId(rs.getInt("id"));
				p.setNutzerFK(rs.getInt("nutzer_p_FK"));
//				p.setErstellZeitpunkt(rs.getDate("erstellzeitpunkt"));
				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	public Pinnwand getPinnwandByNutzer(Nutzer n) {
		return getPinnwandByNutzer(n.getId());
	}

	/**
	 * Diese Mehtode dient dazu, eine Pinnwand anhand der entsprechenden Pinnwand-ID zu finden.
	 * 
	 * @param id
	 */
	public Pinnwand getPinnwandByID(int id) {
		Connection con = DBConnection.connection();
		
		try {
			// Statement ohne Inhalt anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, nutzer_p_FK FROM pinnwand " 
			+ "WHERE id= " + "'" +id + "'");
			
			// Prüfen ob ein Ergebnis vorliegt
			if (rs.next()) {
				// Vorhandenes Ergebnis in ein Objekt umwandeln
				Pinnwand p = new Pinnwand();
				p.setId(rs.getInt("id"));
				p.setNutzerFK(rs.getInt("nutzer_p_FK"));
//				p.setErstellZeitpunkt(rs.getDate("erstellzeitpunkt"));
				return p;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
			
		}	
		
		return null;
	}

	/**
	 * Diese Mehtode dient dazu, alle vorhandenen Pinnwaende zu finden.
	 * 
	 * @return Vector<Pinnwand>
	 */
	public Vector<Pinnwand> getAllPinnwaende() {
		Connection con = DBConnection.connection();

		// Ergebnisvektor anlegen
		Vector<Pinnwand> result = new Vector<Pinnwand>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, nutzer_p_FK, FROM pinnwand " + " ORDER BY id");

			// Für jeden Eintrag im Suchergebnis wird nun ein Pinnwand-Objekt erstellt.
			while (rs.next()) {
				Pinnwand p = new Pinnwand();
				p.setId(rs.getInt("id"));
				p.setNutzerFK(rs.getInt("nutzer_p_FK"));
//				p.setErstellZeitpunkt(rs.getTimestamp("erstellzeitpunkt"));

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.addElement(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

}
