package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

import java.util.Vector;

import com.ibm.icu.util.Calendar;

import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

/**
 * Dies ist eine Mapper-Klasse, die Nutzer-Objekte auf eine relationale
 * Datenbank darstellt. Sie enthält Methoden zum erstellen, suchen, bearbeiten
 * und löschen.
 * 
 * @author jan
 **/

public class NutzerMapper {

	private static NutzerMapper nutzerMapper = null;

	protected NutzerMapper() {
	}

	public static NutzerMapper nutzerMapper() {

		if (nutzerMapper == null) {

			nutzerMapper = new NutzerMapper();

		}

		return nutzerMapper;

	}

	public Nutzer getNutzerbyid(int id) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, vorname, nachname, nickname FROM nutzer " + "WHERE id= " + "'" + id + "'");

			if (rs.next()) {
				Nutzer n = new Nutzer();

				n.setId(rs.getInt("id"));
				n.setVorname(rs.getString("vorname"));
				n.setNachname(rs.getString("nachname"));
				n.setNickname(rs.getString("nickname"));
				return n;

			}

		} catch (SQLException e) {

			e.printStackTrace();

			return null;

		}

		return null;
	}

	public Nutzer getNutzerByName(String vorname, String nachname) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, vorname, nachname FROM nutzer " + "WHERE vorname= " + "'"
					+ vorname + "'" + "AND " + "nachname= " + "'" + nachname + "'");

			if (rs.next()) {

				Nutzer n = new Nutzer();

				n.setId(rs.getInt("id"));
				n.setVorname(rs.getString("vorname"));
				n.setNachname(rs.getString("nachname"));

				return n;
			}

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}

		return null;

	}

	public Nutzer getNutzerByNickname(String nickname) {

		Connection con = DBConnection.connection();
		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, vorname, nachname, " + "nickname FROM nutzer WHERE nickname= " + "'" + nickname + "'");

			if (rs.next()) {

				Nutzer n = new Nutzer();
				n.setId(rs.getInt("id"));
				n.setVorname(rs.getString("vorname"));
				n.setNachname(rs.getString("nachname"));
//			n.setNickname(rs.getString("nickname"));

				return n;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		}

		return null;

	}

	public Nutzer insertNutzer(Nutzer n) {
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM nutzer");

			if (rs.next()) {
				n.setId(rs.getInt("maxid") + 1);
				
				PreparedStatement stmt1 = con.prepareStatement(
						"INSERT INTO nutzer (id, erstellzeitpunkt, vorname, nachname, mail, nickname) VALUES (?, ?, ?, ?, ?, ?) ",

						Statement.RETURN_GENERATED_KEYS);

				System.out.println();
				stmt1.setInt(1, n.getId());
				stmt1.setTimestamp(2, n.getErstellZeitpunkt());
				stmt1.setString(3, n.getVorname());
				stmt1.setString(4, n.getNachname());
				stmt1.setString(5, n.getEmail());
				stmt1.setString(6, n.getNickname());

				System.out.println(stmt);
				stmt1.executeUpdate();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		} 
		return n;
}
	

	public Nutzer updateNutzer(Nutzer n) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE nutzer set " + "vorname= " + "'" + n.getVorname() + "'" + "," + "nachname= "
					+ "'" + n.getNachname() + "'" + "," + "nickname= " + "'" + n.getNickname() + "'" + "WHERE id= "
					+ "'" + n.getId() + "'");

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return n;
	}

	public void deleteNutzer(Nutzer n) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM nutzer WHERE id=" + "'" + n.getId() + "'");

		}

		catch (SQLException e) {

			e.printStackTrace();

		}

	}

	public Nutzer getNutzerByEmail(String mail) {

		/**
		 * Verbindung zur DB Connection
		 */
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM nutzer WHERE mail= " + "'" + mail + "'");

			/**
			 * F�r jeden Eintrag im Suchergebnis wird nun ein Nutzer-Objekt erstellt.
			 */
			if (rs.next()) {
				Nutzer nutzer = new Nutzer();

				nutzer.setId(rs.getInt("id"));
				nutzer.setErstellZeitpunkt(rs.getTimestamp("erstellzeitpunkt"));
				nutzer.setVorname(rs.getString("vorname"));
				nutzer.setNachname(rs.getString("nachname"));
				nutzer.setNickname(rs.getString("nickname"));
				nutzer.setEmail(rs.getString("mail"));

				return nutzer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return null;

	}

	public Vector<Nutzer> getAllNutzer() {
		Connection con = DBConnection.connection();

		Vector<Nutzer> result = new Vector<Nutzer>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM nutzer");

			while (rs.next()) {
				Nutzer n = new Nutzer();
				n.setId(rs.getInt("id"));
				n.setErstellZeitpunkt(rs.getTimestamp("erstellzeitpunkt"));
				n.setVorname(rs.getString("vorname"));
				n.setNachname(rs.getString("nachname"));
				n.setNickname(rs.getString("nickname"));
				result.addElement(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Vector<Nutzer> searchNutzer(String sucheingabe){

		Connection con = DBConnection.connection();
		
		Vector<Nutzer> result = new Vector<Nutzer>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM nutzer "
			+ "WHERE nickname= " + "'" + sucheingabe + "'" + "OR " 
			+ "vorname= " + "'" + sucheingabe + "'" + "OR "
			+ "nachname= " + "'" + sucheingabe + "'");
			
			while (rs.next()) {
				Nutzer n = new Nutzer();
				n.setId(rs.getInt("id"));
				n.setErstellZeitpunkt(rs.getTimestamp("erstellzeitpunkt"));
				n.setVorname(rs.getString("vorname"));
				n.setNachname(rs.getString("nachname"));
				n.setNickname(rs.getString("nickname"));
				result.addElement(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

}