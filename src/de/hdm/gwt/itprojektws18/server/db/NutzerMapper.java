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
			ResultSet rs = stmt.executeQuery("SELECT id, vorname, nachname" + "WHERE id =" + id) ;
			
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
		
		
	public Nutzer getNutzerByName(String vorname, String nachname) {
		
		Connection con =DBConnection.connection();
		
		try {
			
			Statement stmt =con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, vorname, nachhame" + "WHERE vorname =" + vorname + "WHERE nachname=" + nachname);
			
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
		ResultSet rs =stmt.executeQuery("SELECT id, vorname, nachname" + "WHERE nickname=" + nickname);
		
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
	
	public Nutzer insertNutzer(Nutzer n) {
		
		Connection con=DBConnection.connection();
	try {
		
		Statement stmt=con.createStatement();
		
			
			 stmt=con.createStatement();
			stmt.executeUpdate("INSERT INTO nutzer (id, erstellzeitpunkt, vorname, nachname, nickname)" + "VALUES ( "+
			
					n.getId()+ "," +
					n.getErstellZeitpunkt() + ","+
					n.getVorname() + ","+
					n.getNachname() + ","+
					n.getNickname() + ")" );
					
		
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
			stmt.executeUpdate("UPDATE nutzer " + "set Vorname=\"" + n.getVorname() + "\"" + "set Nachname=\""+ n.getNachname() + 
								"set Nickname=\"" + n.getNickname() + "\"" +"WHERE id=" + n.getId() );
			
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
			stmt.executeUpdate("DELETE FROM nutzer" + "WHERE id=" + n.getId());
			
		}
		
		catch(SQLException e2) {
			
			e2.printStackTrace();
			
		}
	
	}
	
}
	



	

