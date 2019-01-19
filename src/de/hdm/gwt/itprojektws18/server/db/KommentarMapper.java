package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Kommentar;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

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
	 * Einf�gen eines Kommentar-Objekts in die DB
	 * 
	 * @author Matthias
	 */

	public Kommentar insertKommentar(Kommentar k) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery( "SELECT MAX(id) AS 'maxid' " + "FROM kommentar");
			
			if (rs.next()) {
				k.setId(rs.getInt("maxid")+1);
				stmt =con.createStatement();
			
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
	 * Diese Methode erm�glicht das editiern des �bergebenen Kommentar-Objekts
	 * 
	 * @author Matthias
	 */

	public Kommentar updateKommentar(Kommentar k) {
		Connection con = DBConnection.connection();

		try {
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
	 * L�schen des �bergebenen KOmmentar-Objekts
	 * 
	 * @author Matthias
	 */

	public void deleteKommentar(Kommentar k) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM kommentar WHERE id =" + "'" + k.getId() + "'");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * L�schen aller zugeh�rigen KOmmentar-Objekte des �bergebenen Beitrag-Objektes
	 * 
	 * @param b
	 */

	public void deleteKommentareOf(Beitrag b) {
		Connection con = DBConnection.connection();

		try {
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

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, text, beitrag_k_FK, nutzer_k_FK, erstellzeitpunkt FROM kommentar WHERE id =" + "'" + id
							+ "'");

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

		Connection con = DBConnection.connection();

		Vector<Kommentar> result = new Vector<Kommentar>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM kommentar");

			while (rs.next())
				;
			{
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

		Connection con = DBConnection.connection();
		Vector<Kommentar> result = new Vector<Kommentar>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, text, beitrag_k_FK, nutzer_k_FK, erstellzeitpunkt FROM kommentar WHERE beitrag_k_FK ="
							+ "'" + beitragFK + "'" + "ORDER BY erstellzeitpunkt ASC");

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

	public Vector<Kommentar> getAllKommentareByNutzer(int nutzerFK) {

		Connection con = DBConnection.connection();
		Vector<Kommentar> result = new Vector<Kommentar>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, text, erstellzeitpunkt, nutzer_k_FK, beitrag_k_FK FROM kommentar WHERE nutzer_k_FK =" + "'" + nutzerFK + "'");
					

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
