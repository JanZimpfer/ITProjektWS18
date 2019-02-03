package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;

/**
 * Diese Mapper Klasse stellt, Beitrags-Objekte auf einer relationalen 
 * Datenbank dar. Sie beinhaltet Methoden zum suchen, erzeugen, bearbeiten
 * und löschen von BeitragsObjekten. DB-Strukturen können hierdurch
 *  in Objekt-Strukturen und anders herum umgewandelt werden.
 */

public class BeitragMapper {
	
	private static BeitragMapper beitragMapper = null;
	
	protected BeitragMapper() {
		
	}
	
	public static BeitragMapper beitragMapper() {
		if (beitragMapper == null) {
			beitragMapper = new BeitragMapper();
		}
		
		return beitragMapper;
	
	}
	
	/**
	 * Einfügen eines Beitrag-Objekts in die Datenbank
	 * @author Matthias
	 */
	
	public Beitrag insertBeitrag(Beitrag b) {
		
		/**
		 * Herstellen der Verbindung zur DB Connection 
		 */
		
		Connection con = DBConnection.connection();
		
		try {
			// Anlegen eines Statements
			Statement stmt = con.createStatement();
			
			//Überprüfung des derzeitig höchsten Primärschlüssels(id) der Tabelle beitrag
			ResultSet rs = stmt.executeQuery( "SELECT MAX(id) AS 'maxid' " + "FROM beitrag");
			
			if (rs.next()) {
				b.setId(rs.getInt("maxid")+1);
				stmt = con.createStatement();
				
				//Einfügen des Beitrags in die nächsthöchste Zeile der Tabelle
				stmt.executeUpdate("INSERT INTO beitrag (id, text, erstellzeitpunkt, pinnwand_b_FK, nutzer_b_FK) "
						+ "VALUES("
						+ b.getId()
						+ ","
						+ b.getText()
						+ ","
						+ "'" + b.getErstellZeitpunkt().toLocalDateTime() + "'"
						+","
						+ b.getPinnwandFK()
						+ ","
						+ b.getNutzerFK()
						+ ")");
						
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b;
		
	}
	
	/**
	 * Diese Methode ermöglicht das editiern des übergebenen 
	 * Beitrag-Objekts
	 * @author Matthias
	 */
	
	public Beitrag updateBeitrag(Beitrag b) {
		//Herstellung einer Verbindung zur DBConnection
		Connection con = DBConnection.connection();
		
		try {
			
			//Anlegen eines Statements
			Statement stmt = con.createStatement();
			
			//Update des Textes in ausgewählter Zeile
			stmt.executeUpdate("UPDATE beitrag set "+
			"text = " + "'" + b.getText() + "'" 
			+"WHERE id=" + "'" + b.getId() + "'");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 * Diese Methode löscht das übergebene Beitrag-Objekt
	 * @author Matthias
	 */
	
	public void deleteBeitrag(Beitrag b) {
		
		//Herstellung einer Verbindung zur DBConnection
		Connection con= DBConnection.connection();
		
		try {
			// Anlegen eines Statements
			Statement stmt=con.createStatement();
			
			//Löschen des ausgewählten Beitragobjekts
			stmt.executeUpdate("DELETE FROM beitrag WHERE id =" + "'" + b.getId() + "'");
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Diese Methode dient dem Löschen aller zugehörigen Beitrags-Objekte 
	 * des übergebenen Nutzerobjekts
	 * @author Matthias
	 */
	
	public void deleteBeitraegeOf(Nutzer n) {
		
		//Herstellung einer Verbindung zur DB-Connection
		Connection con =DBConnection.connection();
		
		try {
			// Anlegen eines Statements
			Statement stmt = con.createStatement();
			/**
			 * nutzerId ist ein FK der Tabelle beitrag welcher auf die Tabelle nutzer verweist.
			 */
			//Löschen der ausgewählten Beitragobjekte
			stmt.executeUpdate("DELETE FROM beitrag WHERE nutzer_b_FK=" + "'" + n.getId() + "'");
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Diese Methode erm�glicht das suchen eines Beitrag-Objekts �ber die Id.
	 * @author Matthias
	 */
	
	public Beitrag getBeitragById(int id) {
		
		//Herstellung einer Verbindung zur DBConnection
		Connection con = DBConnection.connection();
	
		try {
			// Anlegen eines Statements
			Statement stmt= con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT id, text, pinnwand_b_FK, nutzer_b_FK, erstellzeitpunkt FROM beitrag WHERE id = " + "'" + id  +"'");
			
			//Ergebnis in Objekt umwandeln
			if (rs.next()) {
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setText(rs.getString("text"));
				b.setPinnwandFK(rs.getInt("pinnwand_b_FK"));
				b.setNutzerFK(rs.getInt("nutzer_b_FK"));
				b.setErstellZeitpunkt(rs.getTimestamp("erstellzeitpunkt"));
				
				return b;
				
			}
		
		}	
		catch (SQLException e) {
			e.printStackTrace();
			
			return null;
		}
		
		return null;
		
	}
	
	/**
	 * Methode zur Ausgabe aller Beiträge 
	 * @author Matthias
	 */
	
	public Vector<Beitrag> getAllBeitraege() {
		
		//Herstellung einer Verbindung zur DBConnection
		Connection con = DBConnection.connection();
		
		Vector<Beitrag> result = new Vector<Beitrag>();
		
		try {
			// Anlegen eines Statements
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM beitrag");
			
			//Umwandlung der Ergebnisse in Objekte und befüllung des Vectors
			while (rs.next()){
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setText(rs.getString("text"));
				b.setErstellZeitpunkt(rs.getTimestamp("erstellzeitpunkt"));
				b.setPinnwandFK(rs.getInt("pinnwand_b_FK"));
				b.setNutzerFK(rs.getInt("nutzer_b_FK"));
				
				result.addElement(b);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ausgabe aller Beitrag-objekte eines Pinnwand-objekts
	 * @author Matthias
	 */
	
	public Vector<Beitrag> getAllBeitraegeByPinnwand(int pinnwandFK) {
		
		//Herstellung einer Verbindung zur DBConnection
		Connection con = DBConnection.connection();
		Vector<Beitrag> result = new Vector<Beitrag>();
		
		try {
			// Anlegen eines Statements
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT id, text, erstellzeitpunkt, pinnwand_b_FK, nutzer_b_FK FROM beitrag WHERE pinnwand_b_FK =" + "'" + pinnwandFK + "'" + "ORDER BY erstellzeitpunkt DESC");
			
			//Umwandlung der Ergebnisse in Objekte und befüllung des Vectors
			while (rs.next()) {
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setText(rs.getString("text"));
				b.setErstellZeitpunkt(rs.getTimestamp("erstellzeitpunkt"));
				b.setPinnwandFK(rs.getInt("pinnwand_b_FK"));
				b.setNutzerFK(rs.getInt("nutzer_b_FK"));
				
				result.addElement(b);
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Vector <Beitrag> getAllBeitraegeByPinnwand (Pinnwand p) {
		/*
		 * Auslesen der Pinnwand-ID um diese dann an
		 * getAllBeitraegeByPinnwand(int zielId) zu uebergeben.
		 */
		return getAllBeitraegeByPinnwand(p.getId());
	}


	/**
	 * ausgabe aller Beitrag-objekte eines Pinnwand-objekts
	 * @author Ayse
	 */
	
	public Vector<Beitrag> getAllBeitraegeByNutzer(int nutzerID) {
		
		//Herstellung einer Verbindung zur DBConnection
		Connection con = DBConnection.connection();
		Vector<Beitrag> result = new Vector<Beitrag>();
		
		try {
			// Anlegen eines Statements
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT id, text, erstellzeitpunkt, pinnwand_b_FK, nutzer_b_FK FROM beitrag WHERE nutzer_b_FK =" + "'" + nutzerID + "'" + "ORDER BY erstellzeitpunkt DESC");
			
			//Umwandlung der Ergebnisse in Objekte und befüllung des Vectors
			while (rs.next()) {
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setText(rs.getString("text"));
				b.setErstellZeitpunkt(rs.getTimestamp("erstellzeitpunkt"));
				b.setPinnwandFK(rs.getInt("pinnwand_b_FK"));
				b.setNutzerFK(rs.getInt("nutzer_b_FK"));
				
				result.addElement(b);
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public Vector <Beitrag> getAllBeitraegeByNutzer (Nutzer n) {
		/*
		 * Auslesen der Pinnwand-ID um diese dann an
		 * getAllBeitraegeByPinnwand(int zielId) zu uebergeben.
		 */
		return getAllBeitraegeByNutzer(n.getId());
	}
	
}
