package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.gwt.itprojektws18.shared.bo.Abonnement;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Like;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;

/**
 * Dies ist eine Mapper-Klasse, die Like-Objekte auf eine relationale
 * Datenbank darstellt. Sie enthält Methoden zum erstellen, löschen von Likes,
 * sowie zum ermittlen von Likes je Beitrag oder Nutzer.
 * 
 * @author Florian
 */


public class LikeMapper {

	private static LikeMapper likeMapper = null;

	protected LikeMapper() {

	}

	public static LikeMapper likeMapper() {
		if (likeMapper == null) {
			likeMapper = new LikeMapper();
		}
		return likeMapper;
	}

	/**
	 * Einfügen eines Like-Objekts in die Datenbank:
	 * 
	 * @param l
	 * @return Like l
	 */
	public Like insertLike(Like l) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Als erstes wird überprüft, welches der derzeit höchste Primärschlüssel ist.
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS 'maxid' " + "FROM like");

			if (rs.next()) {

				l.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO like (id, beitrag_l_FK, nutzer_l_FK, erstellzeitpunkt) " 
				+ "VALUES("
				+ l.getId()
				+ "," 
				+ l.getBeitragFK() 
				+ "," 
				+ l.getNutzerFK()
				+ ","
				+ "'" + l.getErstellZeitpunkt() + "'" 
				+ ")");
			}

		} catch (SQLException el1) {
			el1.printStackTrace();
		}
		return l;
	}

	/**
	 * Löschen der Daten eines Like-Objekts aus der Datenbank
	 * 
	 * @param l
	 */
	public void deleteLike(Like l) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM like" 
			+ "WHERE id=" + "'" + l.getId() + "'");

		} catch (SQLException el2) {
			el2.printStackTrace();
		}
	}
	
	/**
	 * Löschen aller Like-Objekte eines Nutzers aus der Datenbank
	 * 
	 * @param n
	 */
	public void deleteLikesOf(Nutzer n) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM like " + "WHERE nutzer_l_FK=" + "'" + n.getId() + "'");

		} catch (SQLException el3) {
			el3.printStackTrace();
		}
	}
	
	/**
	 * Auslesen aller Like-Objekte eines Nutzers aus der Datenbank
	 * 
	 * @param nutzerId
	 */
	public Vector<Like> getAllLikesByNutzer(int nutzerFK) {
		Connection con = DBConnection.connection();

		// Ergebnisvektor anlegen
		Vector<Like> nutzerlikeresult = new Vector<Like>();

		try {
			// Statement ohne Inhalt anlegen
			Statement stmt = con.createStatement();
			// Query ausführen
			ResultSet rs = stmt.executeQuery("SELECT id, beitrag_l_FK, nutzer_l_FK, erstellzeitpunkt FROM like " 
			+ "WHERE nutzer_l_FK=" + "'" +nutzerFK + "'" );
		
		// Prüfen ob ein Ergebnis vorliegt
		while(rs.next()){
				// Vorhandenes Ergebnis in ein Objekt umwandeln
				Like l= new Like();
				l.setId(rs.getInt("id"));
				l.setBeitragFK(rs.getInt("beitrag_l_FK"));
				l.setNutzerFK(rs.getInt("nutzer_l_FK"));
				l.setErstellZeitpunkt(rs.getDate("erstellzeitpunkt"));
				
				nutzerlikeresult.addElement(l);
		}
		}catch (SQLException el4) {
			el4.printStackTrace();
		}
		return nutzerlikeresult;
	}

	public Vector<Like> getAllLikesByNutzer(Nutzer n) {
		/*
		 * Auslesen der Nutzer-ID um diese dann an 
		 * getAllLikesByNutzer(int nutzerId) zu
		 * uebergeben
		 */
		return getAllLikesByNutzer(n.getId());
	}

	public Vector<Like> getAllLikesByBeitrag(int beitragFK) {
		Connection con = DBConnection.connection();

		// Ergebnisvektor anlegen
		Vector<Like> likeresult = new Vector<Like>();

		try {
			Statement stmt = con.createStatement();

			// Alle Likes für den Beitrag der zugehörigen ID abfragen und diese
			// nach Erstellungszeitpunkt sortiert zurückgeben
			ResultSet rs = stmt.executeQuery("SELECT id, betrag_l_FK, nutzer_l_FK, erstellungszeitpunkt FROM like " 
			+ "WHERE beitrag_l_FK=" + "'"+  beitragFK + "'" + " ORDER BY erstellungszeitpunkt");

			while (rs.next()) {

				Like l = new Like();
				l.setId(rs.getInt("id"));
				l.setBeitragFK(rs.getInt("beitrag_l_FK"));
				l.setNutzerFK(rs.getInt("nutzer_l_FK"));
				l.setErstellZeitpunkt(rs.getDate("erstellZeitpunkt"));

				likeresult.addElement(l);
			}

		} catch (SQLException el5) {
			el5.printStackTrace();
		}
		return likeresult;

	}

	public Vector<Like> getAllLikesByBeitrag(Beitrag b) {
		/*
		 * Auslesen der Beitrag-ID um diese dann an getAllLikesByBeitrag(int beitragId)
		 * zu uebergeben
		 */
		return getAllLikesByBeitrag(b.getId());
	}

}
