package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.Vector;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

/**Dies ist eine Mapper-Klasse, die Nutzer-Objekte auf eine relationale
* Datenbank darstellt. Sie enthÃ¤lt Methoden zum erstellen, suchen, bearbeiten
* und lÃ¶schen.
* 
* @author jan
**/

public class NutzerMapper {
	
	

	private static NutzerMapper nutzerMapper = null;
	
	protected NutzerMapper () {}
	
	public static NutzerMapper nutzerMapper () {
		
		if ( nutzerMapper == null) {
			
			nutzerMapper = new NutzerMapper();
			
		}
		
		return nutzerMapper; 
			
		}
	 
	
	public Nutzer getNutzerbyid (int id) {
		
		Connection con = DBConnection.connection() ;
		
		try {
			
			Statement stmt =con.createStatement() ;
			ResultSet rs = stmt.executeQuery("SELECT id, vorname, nachname, nickname FROM nutzer " + 
			"WHERE id= " + "'" + id + "'") ;
			
			if (rs.next()){
				Nutzer n = new Nutzer ();
				
				n.setId(rs.getInt("id"));
				n.setVorname(rs.getString("vorname"));
				n.setNachname(rs.getString("nachname"));
				n.setNickname(rs.getString("nickname"));
				return n ;
			
			}		
			
		}
		catch (SQLException e) {
				
				e.printStackTrace();
				
				return null;
		
			}
			
			return null;
		}
		
		
	public Nutzer getNutzerByName(String vorname, String nachname) {
		
		Connection con =DBConnection.connection();
		
		try {
			
			Statement stmt =con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, vorname, nachname FROM nutzer "
			+ "WHERE vorname= " + "'" + vorname + "'" + "AND " + "nachname= " + "'" + nachname + "'");
			
			if (rs.next()) {
				
				Nutzer n = new Nutzer () ;
				
				n.setId(rs.getInt("id"));
				n.setVorname(rs.getString("vorname"));
				n.setNachname(rs.getString("nachname"));
				
				return n;
			}
				
			}
		catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}	
		
		return  null;
		
		}
		
	public Nutzer getNutzerByNickname(String nickname) {
			
		Connection con =DBConnection.connection();
	try {
		
		Statement stmt=con.createStatement();
		ResultSet rs =stmt.executeQuery("SELECT id, vorname, nachname, "
				+ "nickname FROM nutzer WHERE nickname= " 
		+"'" + nickname +"'");
		
		if (rs.next()) {
			
			Nutzer n = new Nutzer ();
			n.setId(rs.getInt("id"));
			n.setVorname(rs.getString("vorname"));
			n.setNachname(rs.getString("nachname"));
//			n.setNickname(rs.getString("nickname"));
			
			
			return n;		
		}
		
	}
		catch (SQLException e) {
		e.printStackTrace();
		return null;
		
		}
	
	return null;
		
	
	}
	
	public Nutzer insertNutzer(Nutzer n) {
		
		Connection con=DBConnection.connection();
		
	
		
	try {
		
		Statement stmt=con.createStatement();
		
		ResultSet rs = stmt.executeQuery( "SELECT MAX(id) AS 'maxid' "
		+ "FROM nutzer");
		
		if (rs.next()) {
			n.setId(rs.getInt("maxid")+1);
			stmt = con.createStatement();
		
			 
			stmt.executeUpdate("INSERT INTO nutzer"
					+ " (id, erstellzeitpunkt, vorname, nachname, nickname)" + 
					"VALUES ( "+
			
					n.getId()+ "," +
					 "'" +n.getErstellZeitpunkt() + "'" + ","+
					n.getVorname() + ","+
					n.getNachname() + ","+
					n.getNickname() + ")" );
					
		}
		}
	
	catch (SQLException e2) { 
			
			e2.printStackTrace();
		}
	
	return n;
		
	}
	
	public Nutzer updateNutzer(Nutzer n) {
		
		Connection con=DBConnection.connection();
		
		try {
			
			Statement stmt=con.createStatement();
			stmt.executeUpdate("UPDATE nutzer set " + 
			"vorname= " + "'" + n.getVorname() + "'"+","+
			"nachname= " + "'"+ n.getNachname() + "'"+ ","+
			"nickname= " + "'" + n.getNickname() + "'"
			+"WHERE id= " + "'" + n.getId() + "'" );
			
		}
		catch(SQLException e2) {
			
			e2.printStackTrace();
		}
		
		return n;
	}
	
	
	public void deleteNutzer (Nutzer n) {
		
		Connection con=DBConnection.connection();
		
		try {
			
			Statement stmt=con.createStatement();
			stmt.executeUpdate("DELETE FROM nutzer WHERE id= " + "'" + n.getId() + "'");
			
		}
		
		catch(SQLException e2) {
			
			e2.printStackTrace();
			
		}
	
	}
	
	public Nutzer findNutzerByEmail(String email){

		/**
		 * Verbindung zur DB Connection
		 */
		Connection con = DBConnection.connection();

		Nutzer n = new Nutzer();

		try {

			PreparedStatement stmt = con.prepareStatement("SELECT * FROM nutzer WHERE mail = ?");

			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			/**
			 * Für jeden Eintrag im Suchergebnis wird nun ein Nutzer-Objekt
			 * erstellt.
			 */
			if (rs.next()) {
				Nutzer nutzer = new Nutzer();

				nutzer.setId(rs.getInt("id"));
				nutzer.setEmail(rs.getString("mail"));
				
				n = nutzer;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		/**
		 * Ergebnisvektor zurückgeben
		 */
		finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return n;
		
	}
	
}