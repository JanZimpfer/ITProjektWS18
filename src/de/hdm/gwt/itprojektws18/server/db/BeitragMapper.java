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
 * und l�schen von BeitragsObjekten. DB-Strukturen k�nnen hierdurch
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
	 * Einf�gen eines Beitrag-Objekts in die Datenbank
	 * @author Matthias
	 */
	
	public Beitrag insertBeitrag(Beitrag b) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery( "SELECT MAX(id) AS 'maxid' " + "FROM beitrag");
			
			if (rs.next()) {
				b.setId(rs.getInt("maxid")+1);
				stmt = con.createStatement();
				
				stmt.executeUpdate("INSERT INTO beitrag (id, text, erstellzeitpunkt, pinnwand_b_FK, nutzer_b_FK) "
						+ "VALUES("
						+ b.getId()
						+ ","
						+ b.getText()
						+ ","
						+ "'" + b.getErstellZeitpunkt() + "'"
						+","
						+ b.getPinnwandFK()
						+ ","
						+ b.getNutzerFK()
						+ ")");
						
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return b;
		
	}
	
	/**
	 * Diese Methode erm�glicht das editiern des �bergebenen 
	 * Beitrag-Objekts
	 * @author Matthias
	 */
	
	public Beitrag updateBeitrag(Beitrag b) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE beitrag set "+
			"text = " + "'" + b.getText() + "'" 
			+"WHERE id=" + "'" + b.getId() + "'");
		}
		catch(SQLException e2) {
			e2.printStackTrace();
		}
		
		return b;
	}
	
	/**
	 * Diese Methode l�scht das �bergebene Beitrag-Objekt
	 * @author Matthias
	 */
	
	public void deleteBeitrag(Beitrag b) {
		Connection con= DBConnection.connection();
		
		try {
			Statement stmt=con.createStatement();
			stmt.executeUpdate("DELETE FROM beitrag WHERE id =" + "'" + b.getId() + "'");
		}
		
		catch(SQLException e2) {
			e2.printStackTrace();
		}
	}
	/**
	 * Diese Methode dient dem L�schen aller zugeh�rigen Beitrags-Objekte 
	 * des �bergebenen Nutzerobjekts
	 * @author Matthias
	 */
	
	public void deleteBeitraegeOf(Nutzer n) {
		Connection con =DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			/**
			 * nutzerId ist ein FK der Tabelle beitrag welcher auf die Tabelle nutzer verweist.
			 */
			stmt.executeUpdate("DELETE FROM beitrag WHERE nutzer_b_FK=" + "'" + n.getId() + "'");
		}
		
		catch(SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * Diese Methode erm�glicht das suchen eines Beitrag-Objekts �ber die Id.
	 * @author Matthias
	 */
	
	public Beitrag getBeitragById(int id) {
		Connection con = DBConnection.connection();
	
		try {
			Statement stmt= con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, text, pinnwand_b_FK, nutzer_b_FK, erstellzeitpunkt FROM beitrag WHERE id = " + "'" + id  +"'");
			
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
	 * Ausgabe aller Beitraege
	 * @author Matthias
	 */
	
	public Vector<Beitrag> getAllBeitraege() {
		Connection con = DBConnection.connection();
		
		Vector<Beitrag> result = new Vector<Beitrag>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, text, erstellzeitpunkt FROM beitrag");
			
			
			while (rs.next()){
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setText(rs.getString("text"));
				b.setErstellZeitpunkt(rs.getTimestamp("erstellzeitpunkt"));
				
				result.addElement(b);
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ausgabe aller Beitrag-objekte eines Pinnwand-objekts
	 * @author Matthias
	 */
	
	public Vector<Beitrag> getAllBeitraegeByPinnwand(int pinnwandFK) {
		Connection con = DBConnection.connection();
		Vector<Beitrag> result = new Vector<Beitrag>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT id, text, erstellzeitpunkt, pinnwand_b_FK, nutzer_b_FK FROM beitrag WHERE pinnwand_b_FK =" + "'" + pinnwandFK + "'");
			
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
		catch (SQLException e2) {
			e2.printStackTrace();
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
		Connection con = DBConnection.connection();
		Vector<Beitrag> result = new Vector<Beitrag>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT id, text, erstellzeitpunkt, pinnwand_b_FK, nutzer_b_FK FROM beitrag WHERE nutzer_b_FK =" + "'" + nutzerID + "'" + "ORDER BY erstellzeitpunkt DESC");
			
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
		catch (SQLException e2) {
			e2.printStackTrace();
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
