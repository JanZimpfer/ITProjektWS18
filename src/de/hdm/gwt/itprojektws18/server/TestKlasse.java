package de.hdm.gwt.itprojektws18.server;

import de.hdm.gwt.itprojektws18.server.db.DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;

import de.hdm.gwt.itprojektws18.server.db.NutzerMapper;
import de.hdm.gwt.itprojektws18.server.db.PinnwandMapper;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.server.db.BeitragMapper;
import de.hdm.gwt.itprojektws18.server.db.KommentarMapper;
import de.hdm.gwt.itprojektws18.server.db.AbonnementMapper;
import de.hdm.gwt.itprojektws18.server.db.LikeMapper;

public class TestKlasse {
	
	public static void main(String[] args) {
		
		PinnwandVerwaltungImpl impl = new  PinnwandVerwaltungImpl();
		impl.init();
		
		Nutzer n = new  Nutzer();
//		n.setId(1);
//		n.setVorname("Jan");
//		n.setNachname("Zimpfer");
//		n.setVorname("DatenbankKing");
		
		String vorname2 = "Jan";
		String nachname2 ="Zimpfer";
		String nickname2 = "DatenbankKing";
		
		impl.erstelleNutzer(vorname2, nachname2, nickname2);
		
		Nutzer n3 = impl.getNutzerByNickname("DatenbankKing");
		System.out.println(n3);
	}
	
	
}
	
	
//	private static Connection con = null;
//	private static String databaseName= "sw1819";
//	private static String url = "jdbc:mysql//127.0.0.1:3306/" + databaseName;
//	
//	static String username = "root";
//	static String password = "sw1819";
//	
//	public static void main (String [] args) {
//		
//
//	if ( con == null) {
//		String url = null;
//	}
//	try {
//		
//		Class.forName("com.mysql.jbdc.Driver").newInstance();
//		
//		Connection con = DriverManager.getConnection(url, username, password);
//		
//		System.out.println("Connection izz da");
//	}	
//		catch (Exception e) {
//			
//			System.err.println(e);
//		}
//		
//	}
//
//}
