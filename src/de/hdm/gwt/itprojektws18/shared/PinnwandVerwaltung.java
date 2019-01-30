package de.hdm.gwt.itprojektws18.shared;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;
import de.hdm.gwt.itprojektws18.shared.bo.Abonnement;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Kommentar;
import de.hdm.gwt.itprojektws18.shared.bo.Like;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("pinnwandverwaltung")
public interface PinnwandVerwaltung extends RemoteService {


	Nutzer erstelleNutzer(String vorname, String nachname, String nickname, String email) throws IllegalArgumentException;

	void speichern(Nutzer n);

	public Nutzer checkEmail(String mail) throws IllegalArgumentException;
	
	Nutzer getNutzerbyID(int nutzerID) throws IllegalArgumentException;

	Nutzer getNutzerByName(String vorname, String nachname) throws IllegalArgumentException;

	Nutzer getNutzerByNickname(String nickname) throws IllegalArgumentException;
	
	Vector<Nutzer> getAllNutzer() throws IllegalArgumentException;
	
	Vector<Nutzer> searchNutzer(String sucheingabe) throws IllegalArgumentException;

	void loeschen(Nutzer n) throws IllegalArgumentException;

	Pinnwand erstellePinnwand(Nutzer n) throws IllegalArgumentException;

	void speichern(Pinnwand p) throws IllegalArgumentException;

	Pinnwand getPinnwandByID(int pinnwandID) throws IllegalArgumentException;

	void loeschen(Pinnwand p) throws IllegalArgumentException;

	Beitrag erstelleBeitrag(Pinnwand p, String text, Nutzer n) throws IllegalArgumentException;

	void speichern(Beitrag b) throws IllegalArgumentException;

	Beitrag getBeitragByID(int beitragID) throws IllegalArgumentException;

	Vector<Beitrag> getAllBeitraege() throws IllegalArgumentException;

	Vector<Beitrag> getAllBeitraegeByPinnwand(Pinnwand p) throws IllegalArgumentException;

	void loeschen(Beitrag b) throws IllegalArgumentException;

	Kommentar erstelleKommentar(Beitrag b, String text, Nutzer n) throws IllegalArgumentException;

	void loeschen(Kommentar k) throws IllegalArgumentException;

	Vector<Kommentar> getAllKommentare() throws IllegalArgumentException;

	Vector<Kommentar> getAllKommentareByBeitrag(Beitrag b) throws IllegalArgumentException;

	Like erstelleLike(Beitrag b, Nutzer n) throws IllegalArgumentException;

	void loeschen(Like l) throws IllegalArgumentException;
	
	Like getLikeFor(int beitragId, int nutzerId) throws IllegalArgumentException;

	Vector<Like> getAllLikesByNutzer(Nutzer n) throws IllegalArgumentException;

	Abonnement erstelleAbonnement(Pinnwand p, Nutzer n) throws IllegalArgumentException;

	void loeschen(Abonnement a) throws IllegalArgumentException;

	Vector<Abonnement> getAllAbosFor(Nutzer n) throws IllegalArgumentException;

	Vector<Kommentar> getAllKommentareByNutzer(Nutzer n) throws IllegalArgumentException;

	Vector<Like> getAllLikesByBeitrag(Beitrag b) throws IllegalArgumentException;

	Pinnwand getPinnwandByNutzer(Nutzer n) throws IllegalArgumentException;

	Vector<Abonnement> getAllAbosFor(Pinnwand p) throws IllegalArgumentException;

	Vector<Beitrag> getAllBeitraegeByNutzer(Nutzer n) throws IllegalArgumentException;

	void speichern(Kommentar k) throws IllegalArgumentException;

	Vector<Abonnement> getAllAbosForWithTime(Nutzer n, Date firstDate, Date secondDate) throws IllegalArgumentException;

	Vector<Beitrag> getAllBeitraegeByNutzerWithTime(Nutzer n, Date firstDate, Date secondDate) throws IllegalArgumentException;

	Vector<Kommentar> getAllKommentareByNutzerWithTime(Nutzer n, Date firstDate, Date secondDate) throws IllegalArgumentException;

	Vector<Like> getAllLikesByNutzerWithTime(Nutzer n, Date firstDate, Date secondDate) throws IllegalArgumentException;

	Abonnement getAboFor(int pinnwandId, int nutzerId) throws IllegalArgumentException;


	
}
