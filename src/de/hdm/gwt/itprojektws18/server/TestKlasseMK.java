package de.hdm.gwt.itprojektws18.server;

import de.hdm.gwt.itprojektws18.server.db.DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import java.sql.Date;
import java.util.Date;
import java.util.Vector;

import com.mysql.jdbc.Driver;

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
		
    //Nutzer n = new Nutzer ();
    
    //n = impl.getNutzerbyID(3);
    
  
    // n = impl.erstelleNutzer("'Dieter'", "'Krause'", "'Hausmeister123'", n.getErstellZeitpunkt());
		
    //Pinnwand p = new Pinnwand ();
	
	//p = impl.getPinnwandByID(2);
	
	//p = impl.erstellePinnwand(n, p.getErstellZeitpunkt());
	
	//Beitrag b = new Beitrag ();
	
	//b = impl.getBeitragByID(2);
	
	 //b = impl.erstelleBeitrag(p, "'Bald ist Weihnachten..'", b.getErstellZeitpunkt(), n);
	
	//Kommentar k = new Kommentar ();
	
	//k = impl.get
	
	
	
	//k = impl.erstelleKommentar(b, "' Das ist soooo LECKER!'", k.getErstellZeitpunkt(), n);
	
	//Like l = new Like();
	
	//l = impl.erstelleLike(b, l.getErstellZeitpunkt(), n);
	
	//impl.loeschen(b);
	
	
		
	}	
}
