package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Like;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

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
		
		//Herstellen der Verbindung zur DB Connection
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			// Als erstes wird überprüft, welches der derzeit höchste Primärschlüssel ist.

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS 'maxid' " + "FROM likes");


			if (rs.next()) {


				l.setId(rs.getInt("maxid") + 1);


				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO likes (id, beitrag_l_FK, erstellzeitpunkt, nutzer_l_FK)" 
				+ "VALUES ( "+
				l.getId()+ "," +
				l.getBeitragFK() + "," + 
				"'" + l.getErstellZeitpunkt() + "'" 
				+ ","
				+ l.getNutzerFK()
				+ ")");

			}



		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return l;
	}

	/**
	 * Löschen der Daten eines Like-Objekts aus der Datenbank
	 * 
	 * @param l
	 */
	public void deleteLike(Like l) {
		//Herstellen der Verbindung zur DB Connection
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();


			stmt.executeUpdate("DELETE FROM likes WHERE id =" + "'" + l.getId() + "'");


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Die Methode getLikeFor dient zur Ausgabe eines likesObjekt,
	 * auf das die übergebene beitragId AND nutzerId zutreffen.
	 * Ist dies nicht der Fall wird null zurückgegeben.
	 * @author matthias
	 */
	
	public Like getLikeFor(int beitragId, int nutzerId) {
		
		//Herstellen der Verbindung zu DBConnection
		Connection con =DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, beitrag_l_FK, nutzer_l_FK FROM likes " 
			+ "WHERE beitrag_l_FK= " + "'" + beitragId + "'" + "AND " + "nutzer_l_FK = " + "'" + nutzerId + "'");		
		
		if (rs.next()){
			
			Like l = new Like();
			
			l.setId(rs.getInt("id"));
			l.setBeitragFK(rs.getInt("beitrag_l_FK"));
			l.setNutzerFK(rs.getInt("nutzer_l_FK"));
			
			return l;
		}
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	/**
	 * Löschen aller Like-Objekte eines Nutzers aus der Datenbank
	 * 
	 * @param n
	 */
	public void deleteLikesOf(Nutzer n) {
		
		//Herstellen der Verbindung zur DB Connection
		Connection con = DBConnection.connection();

		try {
			//Anlegen eines Statements
			Statement stmt = con.createStatement();


			stmt.executeUpdate("DELETE FROM likes " + "WHERE nutzer_l_FK =" + "'" + n.getId() + "'");


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Auslesen aller Like-Objekte eines Nutzers aus der Datenbank
	 * 
	 * @param nutzerId
	 */
	public Vector<Like> getAllLikesByNutzer(int nutzerFK) {
		
		//Herstellen der Verbindung zur DB Connection
		Connection con = DBConnection.connection();

		// Ergebnisvektor anlegen
		Vector<Like> result = new Vector<Like>();

		try {
			// Statement ohne Inhalt anlegen
			Statement stmt = con.createStatement();
			// Query ausführen
			
			ResultSet rs = stmt.executeQuery("SELECT id, beitrag_l_FK, nutzer_l_FK, erstellzeitpunkt FROM likes WHERE nutzer_l_FK =" + "'" +nutzerFK + "'" );
		
		// Prüfen ob ein Ergebnis vorliegt
		while(rs.next()){
				// Vorhandenes Ergebnis in ein Objekt umwandeln
				Like l= new Like();
				l.setId(rs.getInt("id"));
				l.setBeitragFK(rs.getInt("beitrag_l_FK"));
				l.setNutzerFK(rs.getInt("nutzer_l_FK"));
				l.setErstellZeitpunkt(rs.getTimestamp("erstellzeitpunkt"));
				
				result.addElement(l);
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Vector<Like> getAllLikesByNutzer(Nutzer n) {
		/*
		 * Auslesen der Nutzer-ID um diese dann an 
		 * getAllLikesByNutzer(int nutzerId) zu
		 * uebergeben
		 */
		return getAllLikesByNutzer(n.getId());
	}
	
	/**
	 * Aufrufen aller Like-Objekte zu eines Beitragobjekt
	 * 
	 */

	public Vector<Like> getAllLikesByBeitrag(int beitragFK) {
		//Herstellen der Verbindung zur DB Connection
		Connection con = DBConnection.connection();

		// Ergebnisvektor anlegen
		Vector<Like> result = new Vector<Like>();

		try {
			//Anlegen eines Statements
			Statement stmt = con.createStatement();

			// Alle Likes für den Beitrag der zugehörigen ID abfragen und diese
			// nach Erstellungszeitpunkt sortiert zurückgeben
			
			ResultSet rs = stmt.executeQuery("SELECT id, beitrag_l_FK, nutzer_l_FK, erstellzeitpunkt FROM likes WHERE beitrag_l_FK =" + "'"+  beitragFK + "'"); //+ " ORDER BY erstellungszeitpunkt");

			while (rs.next()) {

				Like l = new Like();
				l.setId(rs.getInt("id"));
				l.setBeitragFK(rs.getInt("beitrag_l_FK"));
				l.setNutzerFK(rs.getInt("nutzer_l_FK"));
				l.setErstellZeitpunkt(rs.getTimestamp("erstellZeitpunkt"));

				result.addElement(l);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public Vector<Like> getAllLikesByBeitrag(Beitrag b) {
		/*
		 * Auslesen der Beitrag-ID um diese dann an getAllLikesByBeitrag(int beitragId)
		 * zu uebergeben
		 */
		return getAllLikesByBeitrag(b.getId());
	}

}
