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
	 *Einfügen eines Kommentar-Objekts in die DB
	 * @author Matthias
	 */
	
	public Kommentar insertKommentar(Kommentar k) {
		
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery( "SELECT MAX(id) AS 'maxid' " 
			+ "FROM kommentar");
			
			if (rs.next()) {
				k.setId(rs.getInt("maxid")+1);
				stmt = con.createStatement();
				
				stmt.executeUpdate("INSERT INTO kommentar (id, text, erstellzeitpunkt, beitrag_k_FK, nutzer_k_FK) "
						+ "VALUES("
						+ k.getId()
						+ ","
						+ k.getText()
						+ ","
						+ k.getErstellZeitpunkt()
						+ ","
						+ k.getBeitragFK()
						+ ","
						+ k.getNutzerFK()
						+ ")");
						
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return k;
		
	}
	

	/**
	 * Löschen des übergebenen KOmmentar-Objekts
	 * @author Matthias
	 */
	
	public void deleteKommentar(Kommentar k) {
		Connection con= DBConnection.connection();
		
		try {
			Statement stmt=con.createStatement();
			stmt.executeUpdate("DELETE FROM kommentar" + "WHERE id=" + "'" + k.getId()+"'");
		}
		
		catch(SQLException e2) {
			e2.printStackTrace();
		}
		
	}
	
	/**
	 * Löschen aller zugehörigen KOmmentar-Objekte des übergebenen
	 * Beitrag-Objektes
	 * @param b
	 */
	
	public void deleteKommentareOf(Beitrag b) {
		Connection con =DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			/**
			 * beitragFK ist ein FK der Tabelle Kommentar welcher auf 
			 * die Tabelle beitag verweist.
			 */
			stmt.executeUpdate("DELETE FROM kommentar" + "WHERE beitrag_k_FK=" 
			 + "'" + b.getId() + "'");
		}
		
		catch(SQLException e2) {
			e2.printStackTrace();
		}
		
	}
	
	/**
	 * Aufrufen eines KOmmentar Objekts by id
	 * @author Matthias
	 */
	
	public Kommentar getKommentarById (int id) {
		
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt= con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, text, erstellzeitpunkt FROM kommentar" 
			+ "WHERE id =" + "'" + id +"'");
			
			if (rs.next()) {
				Kommentar k = new Kommentar();
				k.setId(rs.getInt("id"));
				k.setText(rs.getString("text"));
				k.setErstellZeitpunkt(rs.getDate("erstellzeitpunkt"));
				
				return k;
				
			}
		
		}	
		catch (SQLException e) {
			e.printStackTrace();
			
			return null;
		}
		
		return null;
		
	}
	
	/**
	 * Aufrufen aller Kommentar-Objekte
	 * @author Matthias
	 */
	
	public Vector<Kommentar> getAllKommentare() {
		
		Connection con = DBConnection.connection();
		
		Vector<Kommentar> result = new Vector<Kommentar>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, text, erstellzeitpunkt FROM kommentar "
			+ "ORDER BY id");
			
			while (rs.next());{
				Kommentar k = new Kommentar();
				k.setId(rs.getInt("id"));
				k.setText(rs.getString("text"));
				k.setErstellZeitpunkt(rs.getDate("erstellzeitpunkt"));
				
				result.addElement(k);
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
		
	}
	
	/**
	 * Aufrufen aller Kommentar-Objekte eines Beitrag-Objekts
	 * @author Matthias
	 */
	
	public Vector<Kommentar> getAllKommentareByBeitrag(int beitragFK) {
		
		Connection con = DBConnection.connection();
		Vector<Kommentar> result = new Vector<Kommentar>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT id, text, erstellzeitpunkt" 
			+"WHERE = beitrag_k_FK=" + "'" + beitragFK + "'" +"ORDER BY id");
			
			while (rs.next()) {
				Kommentar k = new Kommentar();
				k.setId(rs.getInt("id"));
				k.setText(rs.getString("text"));
				k.setErstellZeitpunkt(rs.getDate("erstellzeitpunkt"));
				
				result.addElement(k);
			}			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
		
	}
	
	public Vector<Kommentar> getAllKommentareByBeitrag (Beitrag b) {
		/*
		 * Auslesen der Beitrag-ID um diese dann an
		 * getAllKommentareByPinnwand(int beitragId) zu uebergeben
		 */
		return getAllKommentareByBeitrag(b.getId());
	}
	
	
	public Vector<Kommentar> getAllKommentareByNutzer (int nutzerFK) {
		
		Connection con = DBConnection.connection();
		Vector<Kommentar> result = new Vector<Kommentar>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT id, text, erstellzeitpunkt" 
			+"WHERE = nutzer_k_FK=" + "'" + nutzerFK + "'"+  "ORDER BY id");
			
			while (rs.next()) {
				Kommentar k = new Kommentar();
				k.setId(rs.getInt("id"));
				k.setText(rs.getString("text"));
				k.setErstellZeitpunkt(rs.getDate("erstellzeitpunkt"));
				
				result.addElement(k);
			}			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
		
	}
	
	public Vector<Kommentar> getAllKommentareByNutzer (Nutzer n) {
		/*
		 * Auslesen der Nutzer-ID(entspricht dem Autor) um diese dann an
		 * getAllKommentareByNutzer (int nutzerId) zu uebergeben
		 */
		return getAllKommentareByNutzer(n.getId());
	}
	
	
}
