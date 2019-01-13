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


	Nutzer erstelleNutzer(String vorname, String nachname, String nickname, Timestamp erstellzeitpunkt, String email);

	void speichern(Nutzer n);

	public Nutzer checkEmail(String mail) throws IllegalArgumentException;
	
	Nutzer getNutzerbyID(int nutzerID);

	Nutzer getNutzerByName(String vorname, String nachname) throws IllegalArgumentException;

	Nutzer getNutzerByNickname(String nickname);
	
	Vector<Nutzer> getAllNutzer();

	void loeschen(Nutzer n);

	Pinnwand erstellePinnwand(Nutzer n, Timestamp erstellzeitpunkt);

	void speichern(Pinnwand p);

	Pinnwand getPinnwandByID(int pinnwandID);

	void loeschen(Pinnwand p);

	Beitrag erstelleBeitrag(Pinnwand p, String text, Timestamp erstellzeitpunkt, Nutzer n);

	void speichern(Beitrag b);

	Beitrag getBeitragByID(int beitragID);

	Vector<Beitrag> getAllBeitraege();

	Vector<Beitrag> getAllBeitraegeByPinnwand(Pinnwand p);

	void loeschen(Beitrag b);

	Kommentar erstelleKommentar(Beitrag b, String text, Timestamp erstellzeitpunkt, Nutzer n);

	void loeschen(Kommentar k);

	Vector<Kommentar> getAllKommentare();

	Vector<Kommentar> getAllKommentareByBeitrag(Beitrag b);

	Like erstelleLike(Beitrag b, Timestamp erstellzeitpunkt, Nutzer n);

	void loeschen(Like l);

	Vector<Like> getAllLikesByNutzer(Nutzer n);

	Abonnement erstelleAbonnement(Pinnwand p, Nutzer n, Timestamp erstellzeitpunkt);

	void loeschen(Abonnement a);

	Vector<Abonnement> getAllAbosFor(Nutzer n);

	Vector<Kommentar> getAllKommentareByNutzer(Nutzer n);

	Vector<Like> getAllLikesByBeitrag(Beitrag b);

	Pinnwand getPinnwandByNutzer(Nutzer n);

	Vector<Abonnement> getAllAbosFor(Pinnwand p);

	Vector<Beitrag> getAllBeitraegeByNutzer(Nutzer n);

	void speichern(Kommentar k);

	
}
