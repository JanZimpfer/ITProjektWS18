package de.hdm.gwt.itprojektws18.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;
import de.hdm.gwt.itprojektws18.shared.bo.Abonnement;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Kommentar;
import de.hdm.gwt.itprojektws18.shared.bo.Like;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface PinnwandVerwaltungAsync {

	void erstelleNutzer(String vorname, String nachname, String nickname, AsyncCallback<Nutzer> callback);

	void speichern(Nutzer n, AsyncCallback<Void> callback);

	void getNutzerbyID(int nutzerID, AsyncCallback<Nutzer> callback);

	void getNutzerByName(String vorname, String nachname, AsyncCallback<Nutzer> callback);

	void getNutzerByNickname(String nickname, AsyncCallback<Nutzer> callback);

	void loeschen(Nutzer n, AsyncCallback<Void> callback);

	void erstellePinnwand(Nutzer n, AsyncCallback<Pinnwand> callback);

	void speichern(Pinnwand p, AsyncCallback<Void> callback);

	void getPinnwandByID(int pinnwandID, AsyncCallback<Pinnwand> callback);

	void loeschen(Pinnwand p, AsyncCallback<Void> callback);

	void erstelleBeitrag(Pinnwand p, String text, AsyncCallback<Beitrag> callback);

	void speichern(Beitrag b, AsyncCallback<Void> callback);

	void getBeitragByID(int beitragID, AsyncCallback<Beitrag> callback);

	void getAllBeitraege(AsyncCallback<Vector<Beitrag>> callback);

	void getAllBeitraegeByPinnwand(Pinnwand p, AsyncCallback<Vector<Beitrag>> callback);

	void loeschen(Beitrag b, AsyncCallback<Void> callback);

	void erstelleKommentar(Beitrag b, String text, AsyncCallback<Kommentar> callback);

	void loeschen(Kommentar k, AsyncCallback<Void> callback);

	void getAllKommentare(AsyncCallback<Vector<Kommentar>> callback);

	void getAllKommentareByBeitrag(Beitrag b, AsyncCallback<Vector<Kommentar>> callback);

	void erstelleLike(Beitrag b, AsyncCallback<Like> callback);

	void loeschen(Like l, AsyncCallback<Void> callback);

	void getAllLikesByNutzer(Nutzer n, AsyncCallback<Vector<Like>> callback);

	void erstelleAbonnement(Pinnwand p, Nutzer n, AsyncCallback<Abonnement> callback);

	void getAllAbosFor(Nutzer n, AsyncCallback<Vector<Abonnement>> callback);

	void loeschen(Abonnement a, AsyncCallback<Void> callback);

	void getAllKommentareByNutzer(Nutzer n, AsyncCallback<Vector<Kommentar>> callback);

	void getAllLikesByBeitrag(Beitrag b, AsyncCallback<Vector<Like>> callback);

	void getPinnwandByNutzer(Nutzer n, AsyncCallback<Pinnwand> callback);

	void getAllAbosFor(Pinnwand p, AsyncCallback<Vector<Abonnement>> callback);
}
