package de.hdm.gwt.itprojektws18.server;

import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltung;

import java.sql.Timestamp;
import java.util.Date;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.gwt.itprojektws18.server.db.NutzerMapper;
import de.hdm.gwt.itprojektws18.server.db.PinnwandMapper;
import de.hdm.gwt.itprojektws18.server.db.BeitragMapper;
import de.hdm.gwt.itprojektws18.server.db.KommentarMapper;
import de.hdm.gwt.itprojektws18.server.db.LikeMapper;
import de.hdm.gwt.itprojektws18.server.db.AbonnementMapper;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;
import de.hdm.gwt.itprojektws18.shared.bo.Abonnement;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Kommentar;
import de.hdm.gwt.itprojektws18.shared.bo.Like;


@SuppressWarnings("serial")
public class PinnwandVerwaltungImpl extends RemoteServiceServlet
implements PinnwandVerwaltung {
	
	
	/**
	 * Serialisierung
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Referenzen auf Mapperklassen
	 */
	private NutzerMapper nMapper = null;
	private PinnwandMapper pMapper = null;
	private BeitragMapper bMapper = null;
	private KommentarMapper kMapper = null;
	private LikeMapper lMapper = null;
	private AbonnementMapper aMapper = null;
	
	
	/*
	 **********************************
	 * Abschnitt Beginn: Initialisierung
	 **********************************
	 */
	
		
public PinnwandVerwaltungImpl() {
		
	}
	
	public void init() {
		
		this.nMapper = NutzerMapper.nutzerMapper();
		this.pMapper = PinnwandMapper.pinnwandMapper();
		this.bMapper = BeitragMapper.beitragMapper();
		this.kMapper = KommentarMapper.kommentarMapper();
		this.lMapper = LikeMapper.likeMapper();
		this.aMapper = AbonnementMapper.abonnementMapper();
	}
	
	
	/*
	 **********************************
	 * Abschnitt Ende: Initialisierung
	 **********************************
	 */
	
	
	/*
	 **********************************
	 * Abschnitt Beginn: Nutzer
	 **********************************
	 */
	
	
	/**
	 * Ersellen eines Nutzers und anschliessendes Speichern in der DB
	 * @param vorname
	 * @param nachname
	 * @param nickname
	 * @return nutzer
	 */
	@Override

	public Nutzer erstelleNutzer(String vorname, String nachname, String nickname, Timestamp erstellzeitpunkt, String email) {

		
		//Erstellen eines Nutzerobjekts mit Vorname, Nachname und Nachname
		Nutzer n = new Nutzer();
		
		n.setVorname(vorname);
		n.setNachname(nachname);
		n.setNickname(nickname);
		n.setErstellZeitpunkt(erstellzeitpunkt);
		n.setEmail(email);
		
		//Setzen einer vorlaeufigen ID, welche nach Kommunikation mit der DB
		//auf den nächsthöchsten Wert gesetzt wird
		n.setId(1);
		
		//Speichern in der DB
		return this.nMapper.insertNutzer(n);
		
	}

	/**
	 * Auslesen eines Nutzers anhand seiner Email
	 * @param email
	 * @return nutzer
	 */
	@Override
	public Nutzer checkEmail(String mail) throws IllegalArgumentException{
		Nutzer nutzer = new Nutzer();
		nutzer = this.nMapper.getNutzerByEmail(mail);
		
		if(nutzer.getId() == 0) {
			return null;
		}
		else {
			return nutzer;
		}
	}
	/**
	 * Speichern eines bearbeiteten Nutzers
	 * @param Nutzer n
	 */
	@Override
	public void speichern(Nutzer n) {
		
		//Bearbeiten
//		n.setVorname(null);
//		n.setNachname(null);
//		n.setNickname(null);
		
		nMapper.updateNutzer(n);
				
	}
	
	/**
	 * Auslesen eines Nutzers anhand seiner ID
	 * @param nutzerID
	 * @return Nutzer
	 */
	@Override
	public Nutzer getNutzerbyID(int nutzerID) {
		return this.nMapper.getNutzerbyid(nutzerID);
	}
	
	/**
	 * Auslesen eines Nutzers anhand seines Vor- und Nachnamens
	 * @param vorname
	 * @param nachname
	 * @return Nutzer
	 */
	@Override
	public Nutzer getNutzerByName(String vorname, String nachname) {
		return this.nMapper.getNutzerByName(vorname, nachname);
	}
	 
	/**
	 * Auslesen eines Nutzers anhand seines Nickname
	 * @param nickname
	 * @return Nutzer
	 */
	@Override
	public Nutzer getNutzerByNickname(String nickname) {
		return this.nMapper.getNutzerByNickname(nickname);
	}
	
	/**
	 * Loeschen eines Nutzers
	 * @param Nutzer n
	 */
	@Override
	public void loeschen (Nutzer n) {
		
		/*
		 * Zunaechst wird die Pinnwand des Nutzers geloescht.
		 * Dies loest eine Loesch-Kaskade aus die alle zugehörigen Objekte loescht.
		 * 
		 */
		
		Pinnwand p1 = this.getPinnwandByNutzer(n);
		
		if (p1 != null) {
			this.loeschen(p1);
		}
		
		//Loeschen des Nutzers
		this.nMapper.deleteNutzer(n);
	}
	
	/*
	 **********************************
	 * Abschnitt Ende: Nutzer
	 **********************************
	 */
	
	/*
	 **********************************
	 * Abschnitt Beginn: Pinnwand
	 **********************************
	 */
	
	/**
	 * Erstellen einer Pinnwand und anschliessendes Speichern in der DB
	 * @param Nutzer n - Inhaber der Pinnwand
	 * @return Pinnwnad
	 */
	@Override
	public Pinnwand erstellePinnwand (Nutzer n, Timestamp erstellzeitpunkt) {
		
		//Erstellen eines Pinnwandobjekts
		//Zuweisen der InhaberID
		Pinnwand p = new Pinnwand();
		p.setNutzerFK(n.getId());
		p.setErstellZeitpunkt(erstellzeitpunkt);
		
		//Setzen einer vorlaeufigen ID, welche nach Kommunikation mit der DB
		//auf den nächsthöchsten Wert gesetzt wird
		p.setId(1);
		
		//Speichern in der DB
		return this.pMapper.insertPinnwand(p);
	}
	
	/**
	 * Speichern einer bearbeiteten Pinnwand
	 * @param Pinnwand p
	 */
	@Override
	public void speichern (Pinnwand p) {
		
		pMapper.updatePinnwand(p);
		
	}
	
	/**
	 * Auslesen einer Pinnwand anhand dessen ID
	 * @param pinnwandID
	 * @return Pinnwand
	 */
	
	public Pinnwand getPinnwandByID(int pinnwandID) {
		
		return this.pMapper.getPinnwandByID(pinnwandID);
	}
	
	/**
	 * Auslesen einer Pinnwand anhand des Nutzers
	 * @param Nutzer n
	 * @return Pinnwand
	 */
	@Override
	public Pinnwand getPinnwandByNutzer(Nutzer n) {
		return this.pMapper.getPinnwandByNutzer(n);
	}
	
	/**
	 * Loeschen einer Pinnwand
	 * @param Pinnwand p
	 */
	@Override
	public void loeschen (Pinnwand p) {
		
		//Zunaechst werden alle Beitraege der Pinnwand geloescht
		Vector<Beitrag> beitraege = this.getAllBeitraegeByPinnwand(p);
		
		if (beitraege != null) {
			for (Beitrag b : beitraege) {
				this.loeschen(b);
			}
		}
		
		
		//Loeschen aller Abonnements einer Pinnwand
		Vector<Abonnement> abos = this.getAllAbosFor(p);
		
		if (abos != null) {
			for (Abonnement a : abos) {
				this.loeschen(a);
			}
		}
		
		//Loeschen der Pinnwand
		this.pMapper.deletePinnwand(p);
	}
	
	/*
	 **********************************
	 * Abschnitt Ende: Pinnwand
	 **********************************
	 */
	
	
	/*
	 **********************************
	 * Abschnitt Beginn: Beitrag
	 **********************************
	 */
	
	
	/**
	 * Erstellen eines Beitrags und anschliessendes Speichern in der DB
	 * @param Ziel-Pinnwand p
	 * @param text
	 * @return Beitrag
	 */
	@Override
	public Beitrag erstelleBeitrag(Pinnwand p, String text, Timestamp erstellzeitpunkt, Nutzer n) {
		
		//Erstellen eines Beitragobjekts
		//Zuweisen der PinnwandID zur Feststellung, zu welcher Pinnwand der Beitrag gehoert
		Beitrag b = new Beitrag();
		b.setPinnwandFK(p.getId());
		b.setErstellZeitpunkt(erstellzeitpunkt);
		b.setNutzerFK(n.getId());
		
		//Setzen des Inhalts des Beitrags (Text)
		b.setText(text);
		
		//Setzen einer vorlaeufigen ID, welche nach Kommunikation mit der DB
		//auf den nächsthöchsten Wert gesetzt wird
		b.setId(1);
		
		//Speichern in dr DB
		return this.bMapper.insertBeitrag(b);
	}
	
	/**
	 * Speichern eines bearbeiteten Beitrags
	 * @param Beitrag b
	 */
	@Override
	public void speichern (Beitrag b) {
		
		//Bearbeiten
	//	b.setText(null);
		
		bMapper.updateBeitrag(b);
	}
	
	/**
	 * Auslesen eines Beitrags anhand seiner ID
	 * @param beitragID
	 * @return Beitrag
	 */
	@Override
	public Beitrag getBeitragByID(int beitragID) {
		
		return this.bMapper.getBeitragById(beitragID);
	}
	
	/**
	 * Auslesen aller Beitraege
	 * @return Vector<Beitrag>
	 */
	@Override
	public Vector <Beitrag> getAllBeitraege() {
		
		return this.bMapper.getAllBeitraege();
	}
	
	/**
	 * Auslesen aller Beitraege einer bestimmten Pinnwand
	 * @param Pinnwand p
	 * @return Vector<Beitrag>
	 */
	
	public Vector<Beitrag> getAllBeitraegeByPinnwand (Pinnwand p) {
		
		return this.bMapper.getAllBeitraegeByPinnwand(p);
	}
	
	/**
	 * Loeschen eines Beitrags
	 * @param Beitrag b
	 */
	@Override
	public void loeschen(Beitrag b) {
		
		//Loeschen aller Kommentare eines Beitrags
		Vector<Kommentar> kommentare = this.getAllKommentareByBeitrag(b);
		
		if (kommentare != null) {
			for (Kommentar k : kommentare) {
				this.loeschen(k);
			}
		}
		
		//Loeschen aller Likes eines Beitrags
		Vector<Like> likes = this.getAllLikesByBeitrag(b);
		
		if (likes != null) {
			for (Like l : likes) {
				this.loeschen(l);
			}
		}
		
		//Beitrag loeschen
		this.bMapper.deleteBeitrag(b);
	}


	
	/*
	 **********************************
	 * Abschnitt Ende: Beitrag
	 **********************************
	 */
	
	
	
	/*
	 **********************************
	 * Abschnitt Beginn: Kommentar
	 **********************************
	 */
	
	
	/**
	 * Erstellen eines Kommentars und anschliessendes Speichern in der DB
	 * @param Ziel-Beitrag b
	 * @param text
	 * @return Kommentar
	 */
	@Override
	public Kommentar erstelleKommentar(Beitrag b, String text, Timestamp erstellzeitpunkt, Nutzer n) {
		
		//Erstellen eines Kommentarobjekts
		//Zuweisen der PinnwandID zur Feststellung, zu welcher Pinnwand der Beitrag gehoert
		Kommentar k = new Kommentar();
		k.setBeitragFK(b.getId());
		k.setErstellZeitpunkt(erstellzeitpunkt);
		k.setNutzerFK(n.getId());
		
		//Setzen des Inhalts des Beitrags (Text)
		k.setText(text);
		
		//Setzen einer vorlaeufigen ID, welche nach Kommunikation mit der DB
		//auf den nächsthöchsten Wert gesetzt wird
		k.setId(1);
		
		//Speichern in dr DB
		return this.kMapper.insertKommentar(k);
	}
	/**
	 * Loeschen eines Kommentars
	 * @param Kommentar k
	 */
	@Override
	public void loeschen (Kommentar k) {
		this.kMapper.deleteKommentar(k);
	}	
	
	/**
	 * Auslesen aller Kommentare
	 * @return Vector<Kommentar>
	 */
	@Override
	public Vector<Kommentar> getAllKommentare() {
		return this.kMapper.getAllKommentare();
	}
	
	/**
	 * Auslesen aller Kommentare eines bestimmten Beitrags
	 * @param Beitrag b
	 * @return Vector<Kommentar>
	 */
	@Override
	public Vector<Kommentar> getAllKommentareByBeitrag (Beitrag b) {
		return this.kMapper.getAllKommentareByBeitrag(b);
	}
	
	/**
	 * Auslesen aller Kommentare eines bestimmten Nutzers
	 * @param Nutzer n
	 * @return Vector<Kommentar>
	 */
	@Override
	public Vector<Kommentar> getAllKommentareByNutzer (Nutzer n) {
		
		return this.kMapper.getAllKommentareByNutzer(n);
	}
	
	/**
	 * Speichern eines bearbeiteten Kommentars
	 * @param k
	 */
	public void speichern (Kommentar k) {
		
		
		kMapper.updateKommentar(k);
		
	}
	
	
	
	/*
	 **********************************
	 * Abschnitt Ende: Kommentar
	 **********************************
	 */
	
	/*
	 **********************************
	 * Abschnitt Beginn: Like
	 **********************************
	 */

	/**
	 * Erstellen eines Likes und anschliessendes Speichern in der DB
	 * @param Ziel-Beitrag b
	 * @return Like 
	 */
	@Override
	public Like erstelleLike(Beitrag b, Timestamp erstellzeitpunkt, Nutzer n) {
		
		//Erstellen eines Beitragobjekts
		//Zuweisen der PinnwandID zur Feststellung, zu welcher Pinnwand der Beitrag gehoert
		Like l = new Like();
		l.setBeitragFK(b.getId());
		l.setErstellZeitpunkt(erstellzeitpunkt);
		l.setNutzerFK(n.getId());
		
		//Setzen einer vorlaeufigen ID, welche nach Kommunikation mit der DB
		//auf den nächsthöchsten Wert gesetzt wird
		l.setId(1);
		
		//Speichern in dr DB
		return this.lMapper.insertLike(l);
	}
	
	/**
	 * Loeschen eines Likes
	 * @param Like l
	 */
	@Override
	public void loeschen (Like l) {
		this.lMapper.deleteLike(l);
	}
	
	
	
	/**
	 * Auslesen aller Likes eines Nutezrs
	 * @param Nutzer n
	 * @return Vector<Like>
	 */
	@Override
	public Vector<Like> getAllLikesByNutzer (Nutzer n) {
		return this.lMapper.getAllLikesByNutzer(n);
	}
	
	/**
	 * Auslesen aller Likes eines Beitrags
	 * @param Beitrag b
	 * @return Vector<Like>
	 */
	@Override
	public Vector<Like> getAllLikesByBeitrag (Beitrag b) {
		return this.lMapper.getAllLikesByBeitrag(b);
	}
	
	
	/*
	 **********************************
	 * Abschnitt Beginn: Abonnement
	 **********************************
	 */
	
	
	/**
	 * Erstellen eines Abonnements und anschliessendes Speichern in der DB
	 * @param Pinnwand p (Abonnement-Ziel)
	 * @param Nutzer n (Abonnent)
	 * @return Abonnement
	 */
	@Override
	public Abonnement erstelleAbonnement(Pinnwand p, Nutzer n, Timestamp erstellzeitpunkt) {
		
		//Erstellen eines Abonnementobjekts
		//Zuweisen der PinnwandID, die abonniert werden soll
		Abonnement a = new Abonnement();
		a.setNutzerFK(n.getId());
		a.setPinnwandFK(p.getId());
		a.setErstellZeitpunkt(erstellzeitpunkt);
		
		
		//Setzen einer vorlaeufigen ID, welche nach Kommunikation mit der DB
		//auf den nächsthöchsten Wert gesetzt wird
		a.setId(1);
		
		//Speichern in dr DB
		return this.aMapper.insertAbonnement(a);
	}

	/**
	 * Loeschen eines Abonnements
	 * @param Abonnement
	 */
	@Override
	public void loeschen (Abonnement a) {
	
		this.aMapper.deleteAbonnement(a);
	}
	
	
	/**
	 * Auslesen aller Abonnements eines Nutzers
	 */
	@Override
	public Vector<Abonnement> getAllAbosFor (Nutzer n) {
		return this.aMapper.getAllAbosByNutzer(n);
	}
	
	/**
	 * Auslesen aller Abonnements einer Pinnwand
	 * @param Pinnwand p
	 * @return Vector<Abonnement>
	 */
	@Override
	public Vector<Abonnement> getAllAbosFor (Pinnwand p) {
		return this.aMapper.getAllAbosByPinnwand(p);
	}


}
