package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

/**
 * Diese Mapper Klasse stellt, Beitrags-Objekte auf einer relationalen Datenbank dar.
 * Sie beinhaltet Methoden zum suchen, erzeugen, bearbeiten und löschen von Beitrags-
 * Objekten. DB-Strukturen können hierdurch in Objekt-Strukturen und anders herum 
 * umgewandelt werden.
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
	
	public Beitrag insertBeitrag(Beitrag b) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery( "SELECT MAX(id) AS maxid" + "FROM beitrag");
			
			if (rs.next()) {
				b.setId(rs.getInt("maxid")+1);
				stmt = con.createStatement();
				
				stmt.executeUpdate("INSERT INTO beitrag (id, text, erstellungsdatum) "
						+ "VALUES("
						+ b.getId()
						+ ","
						+ b.getText()
						+ ","
						+ b.getErstellZeitpunkt()
						+ ")");
						
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return b;
		
	}
	
	public Beitrag updateBeitrag(Beitrag beitrag) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("UPDATE beitag" + "set text=\"" + beitrag.getText() + "\""
					+ "WHERE id=" + beitrag.getId());
		}
		catch(SQLException e2) {
			e2.printStackTrace();
		}
		
		return beitrag;
	}
	
	
	public void deleteBeitrag(Beitrag beitrag) {
		Connection con= DBConnection.connection();
		
		try {
			Statement stmt=con.createStatement();
			stmt.executeUpdate("DELETE FROM beitrag" + "WHERE id=" + beitrag.getId());
		}
		
		catch(SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	
	public void deleteBeitraegeOf(Nutzer nutzer) {
		Connection con =DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			/**
			 * nutzerId ist ein FK der Tabelle beitrag welcher auf die Tabelle nutzer verweist.
			 */
			stmt.executeUpdate("DELETE FROM beitrag" + "WHERE nutzerId=" + nutzer.getId());
		}
		
		catch(SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	public Beitrag getBeitragById(int id) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt= con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, text, erstellungsdatum" + "WHERE id =" + id);
			
			if (rs.next()) {
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setText(rs.getString("text"));
				b.setErstellZeitpunkt(rs.getDate("erstellungsdatum"));
				
				return b;
				
			}
		
		}	
		catch (SQLException e) {
			e.printStackTrace();
			
			return null;
		}
		
		return null;
		
	}
	
	public Vector<Beitrag> getAllBeitraege() {
		Connection con = DBConnection.connection();
		
		Vector<Beitrag> result = new Vector<Beitrag>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, text, erstellungsdatum FROM beitrag"
			+ "ORDER BY id");
			
			while (rs.next());{
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setText(rs.getString("text"));
				b.setErstellZeitpunkt(rs.getDate("erstellungsdatum"));
				
				result.addElement(b);
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
	public Vector<Beitrag> getAllBeitraege(int zielId) {
		Connection con = DBConnection.connection();
		Vector<Beitrag> result = new Vector<Beitrag>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT id, text, erstellungsdatum" +"WHERE = pinnwandId="
			+ zielId + "ORDER BY id");
			
			while (rs.next()) {
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setText(rs.getString("text"));
				b.setErstellZeitpunkt(rs.getDate("erstellungsdatum"));
				
				result.addElement(b);
			}			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

}
