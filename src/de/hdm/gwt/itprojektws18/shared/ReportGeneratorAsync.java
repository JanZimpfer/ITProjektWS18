package de.hdm.gwt.itprojektws18.shared;

import java.sql.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.gwt.itprojektws18.client.gui.report.LikeStatistikReport;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.report.BeitragStatistikReport;
import de.hdm.gwt.itprojektws18.shared.report.NutzerStatistikReport;

public interface ReportGeneratorAsync {

	void init(AsyncCallback<Void> callback);

	void createNutzerStatistikReport(Nutzer nutzer, AsyncCallback<NutzerStatistikReport> callback);

	void createBeitragStatistikReport(Nutzer nutzer, Beitrag beitrag, Date dateFrom,
			AsyncCallback<BeitragStatistikReport> callback);
	
//	void createLikeStatistikReport(int like, AsyncCallback<LikeStatistikReport> callback);

	void getNutzerByName(String vorname, String nachname, AsyncCallback<Vector<Nutzer>> callback);

	void getNutzerByNickname(String nickname, AsyncCallback<Vector<Nutzer>> callback);

	void findNutzerByEmail(String mail, AsyncCallback<Nutzer> callback);

	void getBeitrageByNutzer(Nutzer n, AsyncCallback<Vector<Beitrag>> callback);

	void getNutzerById(int nutzerID, AsyncCallback<Nutzer> callback);
}
