package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;

import com.google.appengine.api.utils.SystemProperty;

public class DBConnection {

	
	private static Connection con = null;
	
	/** Hier entsteht unsere neue DB Connection, welche mit Hilfe der URL die 
	Datenbank ansprechen wird 
	
	 private static String googleUrl =
	 private static String localUrl = 
	 */
	
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
	                    Class.forName("com.mysql.jdbc.Driver");
	                    url = localUrl;
	                }
	                
	           /**Dann erst kann uns der DriverManager eine Verbindung mit den
                 oben in der Variable url angegebenen Verbindungsinformationen
                 aufbauen.
                 */
                 
                con = DriverManager.getConnection(url);
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
	

