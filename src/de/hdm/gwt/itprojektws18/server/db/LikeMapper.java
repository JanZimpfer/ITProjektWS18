package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.gwt.itprojektws18.shared.bo.Like;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class LikeMapper {

private static LikeMapper likeMapper = null;
	
	protected LikeMapper() {
		
	}
	
	public static LikeMapper likeMapper() {
		if (likeMapper == null) {		
			likeMapper = new LikeMapper();
		}
		return likeMapper;
	}
	
	
	public Like insertLike(Like l) {
		return l;
	

	}
	
	public void deleteLike(Like l) {
		
	}
	
	public void deleteLikesOf(Nutzer n) {
		
	}
	
	
	public Vector<Like> getAllLikesByNutzer(int nutzerId) {
		
		
	}
	
	public Vector<Like> getAllLikesByNutzer (Nutzer n) {
		/*
		 * Auslesen der Beitrag-ID um diese dann an
		 * getAllKommentareByPinnwand(int beitragId) zu uebergeben
		 */
		return getAllLikesByNutzer(n.getId());
	}
	
}
