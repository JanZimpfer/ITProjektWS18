package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;


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
			ResultSet rs = stmt.executeQuery("SELECT id, vorname, nachhame" + "WHERE vorname & nachname =" + vorname + nachname + "ORDER BY nachname" );
			
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
		
	public Nutzer getNutzerByNickname () {
		
		
	}
	
	
}
	



	

