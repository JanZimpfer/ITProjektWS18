package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import com.mysql.jdbc.Driver;
import com.google.appengine.api.utils.SystemProperty;

public class DBConnection {


	private static Connection con = null;
	
	
	 private static String googleUrl = "XYZ";
	 private static String localUrl = "jdbc:mysql://localhost:3306/sw1819?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	 private static String username = "root";
	 private static String password ="sw1819";
	 
	 
	
	public static Connection connection() {
		
		if (con==null) {
			
			String url =null;
			
			try {
				  if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
	                    // Load the class that provides the new
	                    // "jdbc:google:mysql://" prefix.
	                    Class.forName("com.mysql.jdbc.GoogleDriver");
	                    url = googleUrl;
	                } else {
	                    // Local MySQL instance to use during development.
	                    Class.forName("com.mysql.cj.jdbc.Driver");
	                    url = localUrl;
	                }
	                
	           /**Dann erst kann uns der DriverManager eine Verbindung mit den
                 oben in der Variable url angegebenen Verbindungsinformationen
                 aufbauen.
                 */
                 
                con = DriverManager.getConnection(url, username, password);
              } 
                catch (Exception e) {
             	con = null;
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());     
                 }
                 
               
                 
                 }
				
				 // Zurückgegeben der Verbindung
        		return con;
				
			}
	
	}
	

