package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.gwt.itprojektws18.shared.bo.Abonnement;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;


/**Dies ist eine Mapper-Klasse, die Nutzer-Objekte auf eine relationale
* Datenbank darstellt. Sie enthält Methoden zum erstellen, suchen, bearbeiten
* und löschen.
* 
* @author jan
**/

public class AbonnementMapper {
	
	public static AbonnementMapper AbonnementMapper = null;
	
	protected AbonnementMapper () {}
	
	public static AbonnementMapper AbonnementMapper() {
		
		if ( AbonnementMapper ==null ) {
			
			AbonnementMapper = new AbonnementMapper ();	
		}
		
		return AbonnementMapper;
		
	}
	
public Abonnement insertAbonnement (Abonnement Abonnement) {
	
	Connection con=DBConnection.connection() ;
	
	try {
			Statement stmt =con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT MAX(id) as maxId"+"FROM abonnement");
			
			if(rs.next()) {
				
				Abonnement.setId(rs.getInt("MAX id") + 1 );
				
				 stmt=con.createStatement();
				stmt.executeUpdate("INSERT INTO abonnement (id, bezugsprofilId, erstellungszeitpunkt)"+ "Values ( "+
			
					Abonnement.getId()+","+
					Abonnement.getBezugsprofilId() + ""+
					Abonnement.getErstellZeitpunkt() +")");
			
				}			
	
		}
	
	catch (SQLException e2) {
	e2.printStackTrace();
		
	}

	return Abonnement;
	

}
	

public void  deleteAbonnement(Abonnement Abonnement) {
	
	Connection con=DBConnection.connection();
	
	try {
			Statement stmt=con.createStatement();
			stmt.executeUpdate("DELETE FROM abonnement" + "WHERE abonnement=" + Abonnement.getId());
			
	}
	
	catch (SQLException e2) {
		
		e2.printStackTrace();
		
		}
	}


public Vector<Abonnement> getAllAbosFor (Nutzer Nutzer){
	
	Connection con=DBConnection.connection();
	Vector <Abonnement> result =new Vector <Abonnement> ();
	
try {
	
	Statement stmt=con.createStatement();
	ResultSet rs= stmt.executeQuery("SELECT id FROM abonnement" + "WHERE bezugsprofilId=" + Nutzer.getId());
	
	while(rs.next()) {
		
		Abonnement abo= new Abonnement ();
		abo.setId(rs.getInt("id"));
		abo.setBezugsprofilId(rs.getInt("bezugsprofilId"));
		
		result.addElement(abo);
		
	}
}
	
	catch (SQLException e2) {
	e2.printStackTrace();
}

return result;
	
	
}

}
