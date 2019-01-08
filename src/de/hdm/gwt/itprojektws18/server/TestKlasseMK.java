package de.hdm.gwt.itprojektws18.server;

import de.hdm.gwt.itprojektws18.server.db.DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import java.sql.Date;
import java.util.Date;
import java.util.Vector;
import java.sql.Timestamp;

import com.mysql.cj.jdbc.Driver;

import de.hdm.gwt.itprojektws18.server.db.NutzerMapper;
import de.hdm.gwt.itprojektws18.server.db.PinnwandMapper;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Kommentar;
import de.hdm.gwt.itprojektws18.shared.bo.Like;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;
import de.hdm.gwt.itprojektws18.server.db.BeitragMapper;
import de.hdm.gwt.itprojektws18.server.db.KommentarMapper;
import de.hdm.gwt.itprojektws18.server.db.AbonnementMapper;
import de.hdm.gwt.itprojektws18.server.db.LikeMapper;

public class TestKlasseMK {
	
	public static void main(String[] args) {
		
		PinnwandVerwaltungImpl impl = new  PinnwandVerwaltungImpl();
		impl.init();
		
    Nutzer n = new Nutzer ();
    
//    n = impl.getNutzerbyID(1);
    
//    System.out.println(n);
  
    n = impl.erstelleNutzer("'To'", "'ToTo'", "'DerTo'", n.getErstellZeitpunkt(), "'to.mustermann@m.com'");
		
    //Pinnwand p = new Pinnwand ();
	
	//p = impl.getPinnwandByID(2);
	
	//p = impl.erstellePinnwand(n, p.getErstellZeitpunkt());
	
	//Beitrag b = new Beitrag ();
	
	
	//b = impl.getBeitragByID(1);
	
	//System.out.println(b);
	
	//b.setText("Ich bin mir unsicher");
	
	//impl.speichern(b);
	
	
	
	
	
	
	 //b = impl.erstelleBeitrag(p, "'Mein Beitrag'", b.getErstellZeitpunkt(), n);
	
	//Vector <Beitrag> result = new Vector <Beitrag> ();
	
	//result = impl.getAllBeitraegeByPinnwand(p);
	
	//System.out.print(result);
	
	
	
//	Kommentar k = new Kommentar ();
//	
//	Vector <Kommentar> result = new Vector <Kommentar> ();
//	
//	result =impl.getAllKommentareByBeitrag(b);
//	
//	System.out.print(result);
//	
	
	
	//k = impl.erstelleKommentar(b, "'toll'", k.getErstellZeitpunkt(), n);
	
	//Like l = new Like();
	
	//l = impl.erstelleLike(b, l.getErstellZeitpunkt(), n);
	
	//impl.loeschen(b);
	
	
		
	}	
}
