package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

/**Dies ist eine Mapper-Klasse, die Nutzer-Objekte auf eine relationale
* Datenbank darstellt. Sie enthält Methoden zum erstellen, suchen, bearbeiten
* und löschen.
* 
* @author jan
**/

public class NutzerMapper {

	private static NutzerMapper nutzermapper = null;
	
	protected NutzerMapper () {}
	
	public static NutzerMapper nutzermapper () {
		
		if ( nutzermapper == null) {
			
			nutzermapper = new NutzerMapper();
			
		}
		
		return nutzermapper; 
			
		}
	
	
	public Nutzer getNutzerbyid (int id) {
		
		Connection con = DBConnection.connection() ;
		
		try {
			
			Statement stmt =con.createStatement() ;
			ResultSet rs = stmt.executeQuery("SELECT id, vorname, nachname" + "WHERE id =" + id + " ORDERD BY nachname") ;
			
			if (rs.next()){
				Nutzer n = new Nutzer ();
				
				n.setId(rs.getInt("id"));
				n.setVorname(rs.getString("vorname"));
				n.setNachname(rs.getString("nachname"));
				
				return n ;
			
			}		
			
		}
		catch (SQLException e) {
				
				e.printStackTrace();
				
				return null;
		
			}
			
			return null;
		}
		
		
	public Nutzer getNuterByName(String vorname, String nachname) {
		
		Connection con =DBConnection.connection();
		
		try {
			
			Statement stmt =con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, vorname, nachhame" + "WHERE vorname =" + vorname + "WHERE nachname=" + nachname + "ORDER BY nachname" );
			
			if (rs.next()) {
				
				Nutzer n = new Nutzer () ;
				
				n.setId(rs.getInt("id"));
				n.setVorname(rs.getString("vorname"));
				n.setNachname(rs.getString("Nachname"));
				
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
		ResultSet rs =stmt.executeQuery("SELECT id, vorname, nachname" + "WHERE nickname=" + nickname + "ORDER BY nachname");
		
		if (rs.next()) {
			
			Nutzer n = new Nutzer ();
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
		
	return null;
	
	}
	
	public Nutzer insertNutzer(Nutzer nutzer) {
		
		Connection con=DBConnection.connection();
	try {
		
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("SELECT MAX(id) as maxid " + "FROM Nutzer");
		
		if(rs.next()) {
			
			nutzer.setId(rs.getInt("MAX id") + 1 );
			
			 stmt=con.createStatement();
			stmt.executeUpdate("INSERT INTO nutzer (id, erstellungszeipunkt, vorname, nachname, nickname)" + "VALUES ( "+
			
					nutzer.getId()+ "," +
					nutzer.getErstellZeitpunkt() + ","+
					nutzer.getVorname() + ","+
					nutzer.getNachname() + ","+
					nutzer.getNickname() + ")" );
					}
		
		}
	
	catch (SQLException e2) {
			
			e2.printStackTrace();
		}
	
	return nutzer;
		
	}
	
	public Nutzer updateNutzer(Nutzer nutzer) {
		
		Connection con=DBConnection.connection();
		
		try {
			
			Statement stmt=con.createStatement();
			stmt.executeUpdate("UPDATE nutzer " + "set nutzerVorname=\"" + nutzer.getVorname() + "\"" + "set nutzerNachname=\""+ nutzer.getNachname() + 
								"set nutzerNickname=\"" + nutzer.getNickname() + "\"" +"WHERE id=" + nutzer.getId() );
			
		}
		catch(SQLException e2) {
			
			e2.printStackTrace();
		}
		
		return nutzer;
	}
	
	
	public void deleteNutzer (Nutzer nutzer) {
		
		Connection con=DBConnection.connection();
		
		try {
			
			Statement stmt=con.createStatement();
			stmt.executeUpdate("DELETE FROM nutzer" + "WHERE id=" + nutzer.getId());
			
		}
		
		catch(SQLException e2) {
			
			e2.printStackTrace();
			
		}
	
	}
	
}
	



	

