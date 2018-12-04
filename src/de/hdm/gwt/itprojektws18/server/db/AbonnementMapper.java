package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.gwt.itprojektws18.shared.bo.Abonnement;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;


/**Dies ist eine Mapper-Klasse, die Nutzer-Objekte auf eine relationale
* Datenbank darstellt. Sie enthält Methoden zum erstellen, suchen, bearbeiten
* und löschen.
* 
* @author jan
**/

public class AbonnementMapper {
	
	private static AbonnementMapper abonnementMapper = null;
	
	protected AbonnementMapper () {}
	
	public static AbonnementMapper abonnementMapper() {
		
		if ( abonnementMapper ==null ) {
			
			abonnementMapper = new AbonnementMapper ();	
		}
		
		return abonnementMapper;
		
	}
	
public Abonnement insertAbonnement (Abonnement a) {
	
	Connection con=DBConnection.connection() ;
	
	try {
			Statement stmt =con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT MAX(id) as maxId"+"FROM abonnement");
			
			if(rs.next()) {
				
				a.setId(rs.getInt("MAX id") + 1 );
				
				 stmt=con.createStatement();
				stmt.executeUpdate("INSERT INTO abonnement (id, nutzerFK, pinnwandFK erstellzeitpunkt)"+ "Values ( "+
			
					a.getId()+","+
					a.getNutzerFK() + ""+
					a.getPinnwandFK()+ ""+
					a.getErstellZeitpunkt() +")");
			
				}			
	
		}
	
	catch (SQLException e2) {
	e2.printStackTrace();
		
	}

	return a;
	

}
	

public void  deleteAbonnement(Abonnement a) {
	
	Connection con=DBConnection.connection();
	
	try {
			Statement stmt=con.createStatement();
			stmt.executeUpdate("DELETE FROM abonnement" + "WHERE abonnement=" + a.getId());
			
	}
	
	catch (SQLException e2) {
		
		e2.printStackTrace();
		
		}
	}


public Vector<Abonnement> getAllAbosByNutzer (Nutzer n){
	
	Connection con=DBConnection.connection();
	Vector <Abonnement> result =new Vector <Abonnement> ();
	
try {
	
	Statement stmt=con.createStatement();
	ResultSet rs= stmt.executeQuery("SELECT (id, nutzerFK, pinnwandFK)  FROM abonnement" + "WHERE nutzerFK=" + n.getId());
	
	while(rs.next()) {
		
		Abonnement a= new Abonnement ();
		
		a.setId(rs.getInt("id"));
		a.setNutzerFK(rs.getInt("nutzerFK"));
		a.setPinnwandFK(rs.getInt("pinnwandFK"));
		result.addElement(a);
		
	}
}
	
	catch (SQLException e2) {
	e2.printStackTrace();
}

return result;
	
	
}


public Vector<Abonnement> getAllAbosByPinnwand (Pinnwand p) {
	
	Connection con = DBConnection.connection() ;
	Vector <Abonnement> result = new Vector <Abonnement> ();

try {
	
	Statement stmt = con.createStatement();
	ResultSet rs =stmt.executeQuery("SELECT (id, nutzerFK,pinnwandFK) FROM abonnement" + "WHERE pinnwandFK =" + p.getId());
	
	while(rs.next()) {
		
		Abonnement a = new Abonnement();
		a.setId(rs.getInt("id"));
		a.setNutzerFK(rs.getInt("nutzerFK"));
		a.setPinnwanndFK(rs.getInt("pinnwandFK"));
		result.addElement(a);
	
	}
	
}
	
catch(SQLException e2) {
	
	e2.printStackTrace();
}

return result;	

}



}
