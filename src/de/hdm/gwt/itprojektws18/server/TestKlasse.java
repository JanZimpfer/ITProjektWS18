package de.hdm.gwt.itprojektws18.server;

import de.hdm.gwt.itprojektws18.server.db.DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Date;

import java.util.Vector;


import com.mysql.jdbc.Driver;

import de.hdm.gwt.itprojektws18.shared.bo.Abonnement;
import de.hdm.gwt.itprojektws18.server.db.NutzerMapper;
import de.hdm.gwt.itprojektws18.server.db.PinnwandMapper;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;
//import de.hdm.gwt.itprojektws18.server.db.BeitragMapper;
//import de.hdm.gwt.itprojektws18.server.db.KommentarMapper;
import de.hdm.gwt.itprojektws18.server.db.AbonnementMapper;
//import de.hdm.gwt.itprojektws18.server.db.LikeMapper;

public class TestKlasse {
	
	public static void main(String[] args) {
		
		PinnwandVerwaltungImpl impl = new  PinnwandVerwaltungImpl();
		impl.init();
		
		
		Abonnement a = new Abonnement ();
		
		Pinnwand p = new Pinnwand ();
 
		Nutzer n = new Nutzer ();
	
		p = impl.getPinnwandByID(3);
		
		n = impl.getNutzerByName("Jan", "Zimpfer");
		
		a = impl.erstelleAbonnement(p, n, a.getErstellZeitpunkt());
  
//	n = impl.getNutzerbyID(2);
//	
//	p = impl.getPinnwandByID(2);
//	
//	a = impl.erstelleAbonnement(p, n, a.getErstellZeitpunkt());
	
////  
  
//		p = impl.getPinnwandByID(1);
//  
//		System.out.println(p);
  
  
//  
  
		
	}	
}
