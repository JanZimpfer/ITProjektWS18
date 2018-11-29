package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Kommentar;

public class KommentarMapper {

private static KommentarMapper kommentarMapper = null;
	
	protected KommentarMapper() {
		
	}
	
	public static KommentarMapper kommentarMapper() {
		if (kommentarMapper == null) {		
			kommentarMapper = new KommentarMapper();
		}
		return kommentarMapper;
	}
	
	
	public Kommentar insertKommentar(Kommentar k) {
	

	}
	
	public void deleteKommentar(Kommentar k) {
		
	}
	
	public void deleteKommentareOf(Beitrag b) {
		
	}
	
	public Kommentar getKommentarById (int id) {
		
	}
	
	public Vector<Kommentar> getAllKommentare() {
		
	}
	
	public Vector<Kommentar> getAllKommentareByBeitrag(int beitragId) {
		
	}
	
	public Vector<Kommentar> getAllKommentareByBeitrag (Beitrag b) {
		/*
		 * Auslesen der Beitrag-ID um diese dann an
		 * getAllKommentareByPinnwand(int beitragId) zu uebergeben
		 */
		return getAllKommentareByPinnwand(b.getId());
	}
	
	
	
}
