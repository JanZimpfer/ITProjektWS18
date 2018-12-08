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
 * und lï¿½schen von BeitragsObjekten. DB-Strukturen kï¿½nnen hierdurch
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
	 * Einfï¿½gen eines Beitrag-Objekts in die Datenbank
	 * @author Matthias
	 */
	
	public Beitrag insertBeitrag(Beitrag b) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery( "SELECT MAX(id) AS maxid" + "FROM beitrag");
			
			if (rs.next()) {
				b.setId(rs.getInt("maxid")+1);
				stmt = con.createStatement();
				
				stmt.executeUpdate("INSERT INTO beitrag (id, text, erstellzeitpunkt, pinnwandFK) "
						+ "VALUES("
						+ b.getId()
						+ ","
						+ b.getText()
						+ ","
						+ b.getErstellZeitpunkt()
						+","
						+b.getPinnwandFK()
						+ ")");
						
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return b;
		
	}
	
	/**
	 * Diese Methode ermöglicht das editiern des übergebenen 
	 * Beitrag-Objekts
	 * @author Matthias
	 */
	
	public Beitrag updateBeitrag(Beitrag beitrag) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE beitag" + "set text=\"" + beitrag.getText() 
			+ "\"" + "WHERE id=" + beitrag.getId());
		}
		catch(SQLException e2) {
			e2.printStackTrace();
		}
		
		return beitrag;
	}
	
	/**
	 * Diese Methode löscht das übergebene Beitrag-Objekt
	 * @author Matthias
	 */
	
	public void deleteBeitrag(Beitrag beitrag) {
		Connection con= DBConnection.connection();
		
		try {
			Statement stmt=con.createStatement();
			stmt.executeUpdate("DELETE FROM beitrag" + "WHERE id=" 
			+ beitrag.getId());
		}
		
		catch(SQLException e2) {
			e2.printStackTrace();
		}
	}
	/**
	 * Diese Methode dient dem Löschen aller zugehörigen Beitrags-Objekte 
	 * des übergebenen Nutzerobjekts
	 * @author Matthias
	 */
	
	public void deleteBeitraegeOf(Nutzer nutzer) {
		Connection con =DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			/**
			 * nutzerId ist ein FK der Tabelle beitrag welcher auf die Tabelle nutzer verweist.
			 */
			stmt.executeUpdate("DELETE FROM beitrag" + "WHERE nutzerFK=" + nutzer.getId());
		}
		
		catch(SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	/**
	 * Diese Methode ermöglicht das suchen eines Beitrag-Objekts über die Id.
	 * @author Matthias
	 */
	
	public Beitrag getBeitragById(int id) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt= con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, text, erstellzeitpunkt" + "WHERE id =" + id);
			
			if (rs.next()) {
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setText(rs.getString("text"));
				b.setErstellZeitpunkt(rs.getDate("erstellzeitpunkt"));
				
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
			ResultSet rs = stmt.executeQuery("SELECT id, text, erstellzeitpunkt FROM beitrag"
			+ "ORDER BY id");
			
			while (rs.next());{
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setText(rs.getString("text"));
				b.setErstellZeitpunkt(rs.getDate("erstellzeitpunkt"));
				
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
			
			ResultSet rs = stmt.executeQuery("SELECT id, text, erstellzeitpunkt" +"WHERE = pinnwandFK="
			+ pinnwandFK + "ORDER BY id");
			
			while (rs.next()) {
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setText(rs.getString("text"));
				b.setErstellZeitpunkt(rs.getDate("erstellzeitpunkt"));
				
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

}
