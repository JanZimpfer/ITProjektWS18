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
	
public Abonnement insertAbonnement (Abonnement abonnement) {
	
	Connection con=DBConnection.connection() ;
	
	try {
			Statement stmt =con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT MAX(id) as maxId"+"FROM abonnement");
			
			if(rs.next()) {
				
				abonnement.setId(rs.getInt("MAX id") + 1 );
				
				 stmt=con.createStatement();
				stmt.executeUpdate("INSERT INTO abonnement (id, bezugsprofilId, erstellungszeitpunkt)"+ "Values ( "+
			
					abonnement.getId()+","+
					abonnement.getBezugsProfilId() + ""+
					abonnement.getErstellZeitpunkt() +")");
			
				}			
	
		}
	
	catch (SQLException e2) {
	e2.printStackTrace();
		
	}

	return abonnement;
	

}
	

public void  deleteAbonnement(Abonnement abonnement) {
	
	Connection con=DBConnection.connection();
	
	try {
			Statement stmt=con.createStatement();
			stmt.executeUpdate("DELETE FROM abonnement" + "WHERE abonnement=" + abonnement.getId());
			
	}
	
	catch (SQLException e2) {
		
		e2.printStackTrace();
		
		}
	}


public Vector<Abonnement> getAllAbosByNutzer (Nutzer nutzer){
	
	Connection con=DBConnection.connection();
	Vector <Abonnement> result =new Vector <Abonnement> ();
	
try {
	
	Statement stmt=con.createStatement();
	ResultSet rs= stmt.executeQuery("SELECT id FROM abonnement" + "WHERE bezugsprofilId=" + nutzer.getId());
	
	while(rs.next()) {
		
		Abonnement abo= new Abonnement ();
		abo.setId(rs.getInt("id"));
		abo.setBezugsProfilId(rs.getInt("bezugsprofilId"));
		
		result.addElement(abo);
		
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
	ResultSet rs =stmt.executeQuery("SELECT id FROM abonnement" + "WHERE FK_pinwannd =" + p.getId());
	
	while(rs.next()) {
		
		Abonnement abo = new Abonnement();
		abo.setId(rs.getInt("id"));
		abo.setBezugsProfilId(rs.getInt("bezugsprofilId"));
		
		result.addElement(abo);
	
	}
	
}
	
catch(SQLException e2) {
	
	e2.printStackTrace();
}

return result;	

}



}
