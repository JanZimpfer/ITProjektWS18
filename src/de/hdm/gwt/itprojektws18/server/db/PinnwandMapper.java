package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

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
	 * @param p
	 * @return Pinnwand p
	 */
	public Pinnwand insert(Pinnwand p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Als erstes wird überprüft, welches der derzeit höchste Primärschlüssel ist.

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM pinnwaende ");

			if (rs.next()) {

				p.setId(rs.getInt("maxid") + 1);
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO pinnwaende (id, nutzerFK, erstellzeitpunkt) " 
				+ "VALUES (" + p.getId() + "," + p.getNutzerFK() + "," + p.getErstellZeitpunkt() + ")");
			}
		} catch (SQLException ep1) {
			ep1.printStackTrace();
		}

		return p;
	}

	/** 
	 * Ändern eines Objekts aus der Datenbank:
	 * @param p
	 * @return Pinnwand p
	 */
	public Pinnwand update(Pinnwand p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate(
					"UPDATE pinnwaende " + "SET nutzerFK=\" " + p.getNutzerFK() + "\" " + "WHERE id=" + p.getId());

		} catch (SQLException ep2) {
			ep2.printStackTrace();
		}

		return p;
	}

	/**
	 *  Löschen der Daten eines Pinnwand-Objekts aus der Datenbank
	 * @param p
	 */
	public void delete(Pinnwand p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM pinnwaende " + "WHERE id=" + p.getId());

		} catch (SQLException ep3) {
			ep3.printStackTrace();
		}
	}

	/**
	 * Diese Mehtode dient dazu, eine Pinnwand anhand der ID des zugehörigen Nutzers zu finden:
	 * @param id
	 * @return null
	 */
	public Pinnwand getPinnwandByNutzer(int id) {

		Connection con = DBConnection.connection();

		try {

			// Statement ohne Inhalt anlegen
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, nutzerFK, erstellzeitpunkt FROM pinnwaende" 
			+ "WHERE inhaberId=" + id);

			// Prüfen ob ein Ergebnis vorliegt
			if (rs.next()) {
				// Vorhandene Ergebnise in Objekte umwandeln
				Pinnwand p = new Pinnwand();
				p.setId(rs.getInt("id"));
				p.setNutzerFK(rs.getInt("nutzerFK"));
				p.setErstellZeitpunkt(rs.getDate("erstellzeitpunkt"));
				return p;
			}
		} catch (SQLException ep4) {
			ep4.printStackTrace();
			return null;
		}

		return null;
	}
	
	public Pinnwand getPinnwandByNutzer(Nutzer n) {
		return getPinnwandByNutzer(n.getId());
	}
	
	
	/**
	 * Diese Mehtode dient dazu, alle vorhandenen Pinnwaende zu finden:
	 * @return Vector<Pinnwand>
	 */
	public Vector<Pinnwand> getAllPinnwaende() {
		Connection con = DBConnection.connection();

		// Ergebnisvektor anlegen
		Vector<Pinnwand> result = new Vector<Pinnwand>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, nutzerFK, erstellzeitpunkt FROM pinnwaende " + " ORDER BY id");

			// Für jeden Eintrag im Suchergebnis wird nun ein Pinnwand-Objekt erstellt.
			while (rs.next()) {
				Pinnwand p = new Pinnwand();
				p.setId(rs.getInt("id"));
				p.setNutzerFK(rs.getInt("nutzerFK"));
				p.setErstellZeitpunkt(rs.getDate("erstellzeitpunkt"));

				// Hinzufügen des neuen Objekts zum Ergebnisvektor
				result.addElement(p);
			}
		} catch (SQLException ep5) {
			ep5.printStackTrace();
		}

		// Ergebnisvektor zurückgeben
		return result;
	}

}
