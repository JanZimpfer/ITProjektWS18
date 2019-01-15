package de.hdm.gwt.itprojektws18.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.gwt.itprojektws18.shared.bo.Abonnement;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;

/**
 * Dies ist eine Mapper-Klasse, die Nutzer-Objekte auf eine relationale
 * Datenbank darstellt. Sie enthält Methoden zum erstellen, suchen, bearbeiten
 * und löschen.
 * 
 * @author jan
 **/

public class AbonnementMapper {

	private static AbonnementMapper abonnementMapper = null;

	protected AbonnementMapper() {
	}

	public static AbonnementMapper abonnementMapper() {

		if (abonnementMapper == null) {

			abonnementMapper = new AbonnementMapper();
		}

		return abonnementMapper;

	}

	public Abonnement insertAbonnement(Abonnement a) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) as 'maxId' " + "FROM abonnement");

			if (rs.next()) {

				a.setId(rs.getInt("maxId") + 1);

				stmt = con.createStatement();
				stmt.executeUpdate(
						"INSERT INTO abonnement (id, nutzer_a_FK, pinnwand_a_FK, erstellzeitpunkt)" + "Values ( " +
								a.getId() + "," + a.getNutzerFK() + "," + a.getPinnwandFK() + "," + "'"
								+ a.getErstellZeitpunkt() + "'" + ")");

			}

		}

		catch (SQLException e) {
			e.printStackTrace();

		}

		return a;

	}

	public void deleteAbonnement(Abonnement a) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM abonnement WHERE id=" + "'" + a.getId() + "'");

		}

		catch (SQLException e) {

			e.printStackTrace();

		}
	}
	
	/**
	 * Die Mapper_Methode getAboFor dient zur Ausgabe eines Abo_objects,
	 * auf das die pinnwandId AND nutzerId zutreffen.
	 * Ist dies nicht der Fall wird null zur�ckgegeben.
	 * @author matthias
	 */
	
	public Abonnement getAboFor(int pinnwandId, int nutzerId) {
		
		Connection con =DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, nutzer_a_FK, pinnwand_a_FK FROM abonnement "
			+ "WHERE nutzer_a_FK= " + "'" + nutzerId + "'" + "AND " + "pinnwand_a_FK= " + "'" + pinnwandId + "'");
		
		
		if (rs.next()) {
			
			Abonnement a = new Abonnement () ;
			
			a.setId(rs.getInt("id"));
			a.setNutzerFK(rs.getInt("nutzer_a_FK"));
			a.setPinnwandFK(rs.getInt("pinnwand_a_FK"));
			
			return a;
		}
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	

	public Vector<Abonnement> getAllAbosByNutzer(Nutzer n) {

		Connection con = DBConnection.connection();
		Vector<Abonnement> result = new Vector<Abonnement>();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, nutzer_a_FK, pinnwand_a_FK, erstellzeitpunkt  FROM abonnement WHERE nutzer_a_FK="
					+ "'" + n.getId() + "'");

			while (rs.next()) {

				Abonnement a = new Abonnement();

				a.setId(rs.getInt("id"));
				a.setNutzerFK(rs.getInt("nutzer_a_FK"));
				a.setPinnwandFK(rs.getInt("pinnwand_a_FK"));
				a.setErstellZeitpunkt(rs.getTimestamp("erstellzeitpunkt"));
				result.addElement(a);

			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	public Vector<Abonnement> getAllAbosByPinnwand(Pinnwand p) {

		Connection con = DBConnection.connection();
		Vector<Abonnement> result = new Vector<Abonnement>();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT id, nutzer_a_FK, pinnwand_a_FK FROM abonnement WHERE pinnwand_a_FK =" + "'"
							+ p.getId() + "'");

			while (rs.next()) {

				Abonnement a = new Abonnement();
				a.setId(rs.getInt("id"));
				a.setNutzerFK(rs.getInt("nutzer_a_FK"));
				a.setPinnwandFK(rs.getInt("pinnwand_a_FK"));
				result.addElement(a);

			}

		}

		catch (SQLException e) {

			e.printStackTrace();
		}

		return result;

	}

}
