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
	public Nutzer erstelleNutzer(String vorname, String nachname, String nickname, String email) throws IllegalArgumentException {

		
		Nutzer n1 = new Nutzer();
		
		n1 = nMapper.getNutzerByNickname(nickname);
		
		if (n1 != null) {
			return null;
		}
		
		else {
					
		
		//Erstellen eines Nutzerobjekts mit Vorname, Nachname und Nachname
		Nutzer n = new Nutzer();
		
		n.setVorname(vorname);
		n.setNachname(nachname);
		n.setNickname(nickname);
		n.setErstellZeitpunkt(new Timestamp(System.currentTimeMillis()));
		n.setEmail(email);
		
		//Setzen einer vorlaeufigen ID, welche nach Kommunikation mit der DB
		//auf den nächsthöchsten Wert gesetzt wird
		n.setId(1);
		
		//Speichern in der DB
		return this.nMapper.insertNutzer(n);
		}
		
	}

	/**
	 * Auslesen eines Nutzers anhand seiner Email
	 * @param email
	 * @return nutzer
	 */
	@Override
	public Nutzer checkEmail(String mail) throws IllegalArgumentException {
		Nutzer nutzer = new Nutzer();
		nutzer = this.nMapper.getNutzerByEmail(mail);
		
		if(nutzer == null) {
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
	public Nutzer speichern(Nutzer n) throws IllegalArgumentException {
		
		nMapper.updateNutzer(n);
		return n;
	}
	
	/**
	 * Auslesen eines Nutzers anhand seiner ID
	 * @param nutzerID
	 * @return Nutzer
	 */
	@Override
	public Nutzer getNutzerbyID(int nutzerID) throws IllegalArgumentException {
		return this.nMapper.getNutzerbyid(nutzerID);
	}
	
	/**
	 * Auslesen eines Nutzers anhand seines Vor- und Nachnamens
	 * @param vorname
	 * @param nachname
	 * @return Nutzer
	 */
	@Override
	public Nutzer getNutzerByName(String vorname, String nachname) throws IllegalArgumentException {
		return this.nMapper.getNutzerByName(vorname, nachname);
	}
	 
	/**
	 * Auslesen eines Nutzers anhand seines Nickname
	 * @param nickname
	 * @return Nutzer
	 */
	@Override
	public Nutzer getNutzerByNickname(String nickname)  throws IllegalArgumentException {
		return this.nMapper.getNutzerByNickname(nickname);
	}
	
	/**
	 * Loeschen eines Nutzers
	 * @param Nutzer n
	 */
	@Override
	public void loeschen (Nutzer n) throws IllegalArgumentException {
		
		/**
		 * Löschen alle Abonemennt-Objekte in denen der zu löschende user
		 * als FK auftritt.
		 */
		
		Vector<Abonnement> abos = this.getAllAbosFor(n);
		
		if (abos != null) {
			for (Abonnement a : abos) {
				this.loeschen(a);
			}
		}
		
		/**
		 * Löschen aller Like-Objekte in denen der zu löschende Nutzer
		 * als FK hinterlegt ist
		 */
		Vector<Like> likes = this.getAllLikesByNutzer(n);
		if (likes != null) {
			for (Like l : likes) {
				this.loeschen(l);
			}
		}
		
		/**
		 * Löschen aller Kommentar-Objekte in denen der zu löschende Nutzer
		 * als FK hinterlegt ist
		 */
		Vector<Kommentar> kommentare = this.getAllKommentareByNutzer(n);
		if(kommentare != null) {
			for (Kommentar k : kommentare) {
				this.loeschen(k);
			}
		}
		
		/*
		 * Zunaechst wird die Pinnwand des Nutzers geloescht.
		 * Dies loest eine Loesch-Kaskade aus die alle zugehörigen Objekte loescht.
		 * 
		 */
		
		
		Pinnwand p1 = this.getPinnwandByNutzer(n);
		
		if (p1 != null) {
			this.loeschen(p1);
			//Loeschen des Nutzers
			
		}
		
		this.nMapper.deleteNutzer(n);

	}
	
	/**
	 * Auslesen aller Nutzer im System
	 * 
	 * @return Vector<Nutzer>
	 */
	public Vector <Nutzer> getAllNutzer() throws IllegalArgumentException {
		
		return this.nMapper.getAllNutzer();
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
	public Pinnwand erstellePinnwand (Nutzer n) throws IllegalArgumentException {
		
		//Erstellen eines Pinnwandobjekts
		//Zuweisen der InhaberID
		Pinnwand p = new Pinnwand();
		p.setNutzerFK(n.getId());
		p.setErstellZeitpunkt(new Timestamp(System.currentTimeMillis()));
		
		//Setzen einer vorläufigen ID, die später richtig gesetzt wird
		p.setId(1);
		
		//Speichern in der DB
		return this.pMapper.insertPinnwand(p);
	}
	
	/**
	 * Speichern einer bearbeiteten Pinnwand
	 * @param Pinnwand p
	 */
	@Override
	public void speichern (Pinnwand p) throws IllegalArgumentException {
		
		pMapper.updatePinnwand(p);
		
	}
	
	/**
	 * Auslesen einer Pinnwand anhand dessen ID
	 * @param pinnwandID
	 * @return Pinnwand
	 */
	
	public Pinnwand getPinnwandByID(int pinnwandID) throws IllegalArgumentException {
		
		return this.pMapper.getPinnwandByID(pinnwandID);
	}
	
	/**
	 * Auslesen einer Pinnwand anhand des Nutzers
	 * @param Nutzer n
	 * @return Pinnwand
	 */
	@Override
	public Pinnwand getPinnwandByNutzer(Nutzer n) throws IllegalArgumentException {
		Pinnwand p = new Pinnwand();
		p = this.pMapper.getPinnwandByNutzer(n);
		
		if (p == null) {
			return null;
		} else {
			return p;
		}
	}
	
	/**
	 * Loeschen einer Pinnwand
	 * @param Pinnwand p
	 */
	@Override
	public void loeschen (Pinnwand p) throws IllegalArgumentException {
		
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
	public Beitrag erstelleBeitrag(Pinnwand p, String text, Nutzer n)  throws IllegalArgumentException {
		
		//Erstellen eines Beitragobjekts
		//Zuweisen der PinnwandID zur Feststellung, zu welcher Pinnwand der Beitrag gehoert
		Beitrag b = new Beitrag();
		b.setPinnwandFK(p.getId());
		b.setErstellZeitpunkt(new Timestamp(System.currentTimeMillis()));
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
	public void speichern (Beitrag b)  throws IllegalArgumentException{
		
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
	public Beitrag getBeitragByID(int beitragID) throws IllegalArgumentException {
		
		return this.bMapper.getBeitragById(beitragID);
	}
	
	/**
	 * Auslesen aller Beiträge
	 * @return Vector<Beitrag>
	 */
	@Override
	public Vector <Beitrag> getAllBeitraege() throws IllegalArgumentException {
		
		return this.bMapper.getAllBeitraege();
	}
	
	/**
	 * Auslesen aller Beitraege einer bestimmten Pinnwand
	 * @param Pinnwand p
	 * @return Vector<Beitrag>
	 */
	
	public Vector<Beitrag> getAllBeitraegeByPinnwand (Pinnwand p) throws IllegalArgumentException {
		
		return this.bMapper.getAllBeitraegeByPinnwand(p);
	}
	
	/**
	 * Auslesen aller Beitraege eines Nutzers
	 * @param Nutzer n
	 * @return Vector<Beitrag>
	 */
	@Override
	public Vector<Beitrag> getAllBeitraegeByNutzer(Nutzer n) throws IllegalArgumentException {
		return this.bMapper.getAllBeitraegeByNutzer(n);
	}

	
	/**
	 * Loeschen eines Beitrags
	 * @param Beitrag b
	 */
	@Override
	public void loeschen(Beitrag b) throws IllegalArgumentException {
		
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
	public Kommentar erstelleKommentar(Beitrag b, String text, Nutzer n) throws IllegalArgumentException {
		
		//Erstellen eines Kommentarobjekts
		//Zuweisen der PinnwandID zur Feststellung, zu welcher Pinnwand der Beitrag gehoert
		Kommentar k = new Kommentar();
		k.setBeitragFK(b.getId());
		k.setErstellZeitpunkt(new Timestamp(System.currentTimeMillis()));
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
	public void loeschen (Kommentar k) throws IllegalArgumentException {
		this.kMapper.deleteKommentar(k);
	}	
	
	/**
	 * Auslesen aller Kommentare
	 * @return Vector<Kommentar>
	 */
	@Override
	public Vector<Kommentar> getAllKommentare()  throws IllegalArgumentException {
		return this.kMapper.getAllKommentare();
	}
	
	/**
	 * Auslesen aller Kommentare eines bestimmten Beitrags
	 * @param Beitrag b
	 * @return Vector<Kommentar>
	 */
	@Override
	public Vector<Kommentar> getAllKommentareByBeitrag (Beitrag b) throws IllegalArgumentException {
		return this.kMapper.getAllKommentareByBeitrag(b);
	}
	
	/**
	 * Auslesen aller Kommentare eines bestimmten Nutzers
	 * @param Nutzer n
	 * @return Vector<Kommentar>
	 */
	@Override
	public Vector<Kommentar> getAllKommentareByNutzer (Nutzer n)  throws IllegalArgumentException {
		
		return this.kMapper.getAllKommentareByNutzer(n);
	}
	
	/**
	 * Speichern eines bearbeiteten Kommentars
	 * @param k
	 */
	public void speichern (Kommentar k) throws IllegalArgumentException {
		
		
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
	public Like erstelleLike(Beitrag b, Nutzer n)  throws IllegalArgumentException {
		
		Like l = new Like();
		/*
		 * Überprüfen ob bereits ein Like-Objekt mit diesen Werten besteht
		 */
		if (b != null && n != null) {
			l = getLikeFor(b.getId(), n.getId());
		} else {
			return null;
		}
		
		
		/*
		 * Gibt es noch kein Like-Objekt für diese Werte, so wird eines erstellt.
		 */
		if (l == null) {
			
			Like like = new Like();
			
			/*
			 * Setzen einer vorläufigen ID, welche nach Kommunikation mit der DB
			 * auf den nächsthöchsten Wert gesetzt wird
			 */
			like.setId(1);
			
			like.setBeitragFK(b.getId());
			like.setErstellZeitpunkt(new Timestamp(System.currentTimeMillis()));
			like.setNutzerFK(n.getId());
		
			
			return this.lMapper.insertLike(like);
		} else {
			
			return null;
		}
	}
	
	/**
	 * Loeschen eines Likes
	 * @param Like l
	 */
	@Override
	public void loeschen (Like l) throws IllegalArgumentException {
		if (l != null) {
			this.lMapper.deleteLike(l);
		}
		
	}
	
	/**
	 * Methode zur Ausgabe/Abfrage eines bestimmten LikeObjects
	 *
	 */
	@Override
	public Like getLikeFor (int beitragId, int nutzerId) throws IllegalArgumentException {
		return this.lMapper.getLikeFor(beitragId, nutzerId);
	}
	
	/**
	 * Auslesen aller Likes eines Nutezrs
	 * @param Nutzer n
	 * @return Vector<Like>
	 */
	@Override
	public Vector<Like> getAllLikesByNutzer (Nutzer n)throws IllegalArgumentException {
		return this.lMapper.getAllLikesByNutzer(n);
	}
	
	/**
	 * Auslesen aller Likes eines Beitrags
	 * @param Beitrag b
	 * @return Vector<Like>
	 */
	@Override
	public Vector<Like> getAllLikesByBeitrag (Beitrag b) throws IllegalArgumentException {
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
	public Abonnement erstelleAbonnement(Pinnwand p, Nutzer n) throws IllegalArgumentException {
		
//		Vector<Abonnement> abos = new Vector<Abonnement>();
//		abos.addAll(this.getAllAbosFor(n));
		
		Abonnement a = new Abonnement();
		
		if (p != null && n != null) {
			a = getAboFor(p.getId(), n.getId());
		} else {
			return null;
		}
		
		if(a == null) {
			Abonnement abo = new Abonnement();
			
			abo.setPinnwandFK(p.getId());
			abo.setNutzerFK(n.getId());
			abo.setErstellZeitpunkt(new Timestamp(System.currentTimeMillis()));
			
			//Setzen einer vorlaeufigen ID, welche nach Kommunikation mit der DB
			//auf den nächsthöchsten Wert gesetzt wird
			abo.setId(1);	
			
			return this.aMapper.insertAbonnement(abo);
		} else {
			return null;
		}
		
	}

	/**
	 * Loeschen eines Abonnements
	 * @param Abonnement
	 */
	@Override
	public void loeschen (Abonnement a) throws IllegalArgumentException {
	
		if(a != null) {
			this.aMapper.deleteAbonnement(a);
		}
		
	}
	
	@Override
	public Abonnement getAboFor (int pinnwandId, int nutzerId)  throws IllegalArgumentException {
		return this.aMapper.getAboFor(pinnwandId, nutzerId);
	}
	
	/**
	 * Auslesen aller Abonnements eines Nutzers
	 */
	@Override
	public Vector<Abonnement> getAllAbosFor (Nutzer n)  throws IllegalArgumentException {
		return this.aMapper.getAllAbosByNutzer(n);
	}
	
	/**
	 * Auslesen aller Abonnenten eines Nutzers
	 */
	@Override
	public Vector<Abonnement> getAllAbosForNutzer(Nutzer n) throws IllegalArgumentException {
		
		return this.aMapper.getAllAbosForNutzer(n);
	}
	
	
	/**
	 * Auslesen aller Abonnements einer Pinnwand
	 * @param Pinnwand p
	 * @return Vector<Abonnement>
	 */
	@Override
	public Vector<Abonnement> getAllAbosFor (Pinnwand p) throws IllegalArgumentException {
		return this.aMapper.getAllAbosByPinnwand(p);
	}
	
	/**
	 * Auslesen aller Abos in einer Zeitspanne
	 * @param Nutzer n, Date firstDate, Date secondDate
	 * @return Vector<Abonnement>
	 */
	@Override
	public Vector<Abonnement> getAllAbosForWithTime(Nutzer n, Date firstDate, Date secondDate) throws IllegalArgumentException {
		
		Vector<Abonnement> aboVector = getAllAbosFor(n);
		Vector<Abonnement> filterVector  = new Vector<Abonnement>();
		for (Abonnement abonnement : aboVector) {
			if(abonnement.getErstellZeitpunkt().after(firstDate) && abonnement.getErstellZeitpunkt().before(secondDate)) {
				filterVector.add(abonnement);
			}
		}
	
		return filterVector;
		
	}
	
	/**
	 * Auslesen aller Abonnenten eines Nutzers in einer Zeitspanne
	 * @param Nutzer n, Date first Date, Date secondDate
	 * @return Vector<Abonnement>
	 */
	@Override
	public Vector<Abonnement> getAllAbosForNutzerWithTime(Nutzer n, Date firstDate, Date secondDate) throws IllegalArgumentException {
		
		Vector<Abonnement> aboVector = getAllAbosForNutzer(n);
		Vector<Abonnement> filterVector  = new Vector<Abonnement>();
		for (Abonnement abonnement : aboVector) {
			if(abonnement.getErstellZeitpunkt().after(firstDate) && abonnement.getErstellZeitpunkt().before(secondDate)) {
				filterVector.add(abonnement);
			}
		}
	
		return filterVector;
		
	}
	
		/**
		 * Auslesen aller Beitraege eines Nutzers in einer bestimmten Zeitspanne
		 * @param Nutzer n, Date firstDate, Date secondDate
		 * @return Vector<Beitrag>
		 */
		@Override
		public Vector<Beitrag> getAllBeitraegeByNutzerWithTime(Nutzer n, Date firstDate, Date secondDate) throws IllegalArgumentException {
			
			Vector<Beitrag> beitragVector = getAllBeitraegeByNutzer(n);
			Vector<Beitrag> filterVector = new Vector<Beitrag>();
			for (Beitrag beitrag : beitragVector) {
				if(beitrag.getErstellZeitpunkt().after(firstDate) && beitrag.getErstellZeitpunkt().before(secondDate)) {
					filterVector.add(beitrag);
				}
			}
			
			return filterVector;
		}
		
		/**
		 * Auslesen aller Kommentare eines bestimmten Nutzers in einer bestimmten Zeitspanne
		 * @param Nutzer n, Date firstDate, Date secondDate
		 * @return Vector<Kommentar>
		 */
		@Override
		public Vector<Kommentar> getAllKommentareByNutzerWithTime (Nutzer n, Date firstDate, Date secondDate) throws IllegalArgumentException {
			
			Vector<Kommentar> kommentarVector = getAllKommentareByNutzer(n);
			Vector<Kommentar> filterVector = new Vector<Kommentar>();
			
			for (Kommentar kommentar : kommentarVector) {
				if(kommentar.getErstellZeitpunkt().after(firstDate) && kommentar.getErstellZeitpunkt().before(secondDate)) {
					filterVector.add(kommentar);
				}
			}
			
			return filterVector;
		}
		
		/**
		 * Auslesen aller Likes eines Nutzers in einer bestimmte Zeitspanne
		 * @param Nutzer n, Date firstDate, Date secondDate
		 * @return Vector<Like>
		 */
		@Override
		public Vector<Like> getAllLikesByNutzerWithTime (Nutzer n, Date firstDate, Date secondDate) throws IllegalArgumentException {
			
			Vector<Like> likeVector = getAllLikesByNutzer(n);
			Vector<Like> filterVector = new Vector<Like>();
			
			for (Like like : likeVector) {
				if(like.getErstellZeitpunkt().after(firstDate) && like.getErstellZeitpunkt().before(secondDate)) {
					filterVector.add(like);
				}
			}
			
			return filterVector;
		}
		
		@Override
		public Vector<Beitrag> getAllBeitraegeWithTime(Date firstDate, Date secondDate) throws IllegalArgumentException {
			
			Vector<Beitrag> beitragVector = getAllBeitraege();
			Vector<Beitrag> filterVector = new Vector<Beitrag>();
			for (Beitrag beitrag : beitragVector) {
				if(beitrag.getErstellZeitpunkt().after(firstDate) && beitrag.getErstellZeitpunkt().before(secondDate)) {
					filterVector.add(beitrag);
				}
			}
			
			return filterVector;
		}



}
