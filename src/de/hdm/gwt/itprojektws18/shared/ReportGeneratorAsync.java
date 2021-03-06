package de.hdm.gwt.itprojektws18.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.report.BeitragStatistikReport;
import de.hdm.gwt.itprojektws18.shared.report.NutzerStatistikReport;
 
public interface ReportGeneratorAsync {

	void init(AsyncCallback<Void> callback);

	void getNutzerByName(String vorname, String nachname, AsyncCallback<Vector<Nutzer>> callback);

	void getNutzerByNickname(String nickname, AsyncCallback<Vector<Nutzer>> callback);

	void findNutzerByEmail(String mail, AsyncCallback<Nutzer> callback);

	void getBeitrageByNutzer(Nutzer n, AsyncCallback<Vector<Beitrag>> callback);

	void getNutzerById(int nutzerID, AsyncCallback<Nutzer> callback);

	void createNutzerStatistikReport(String nickname, Date firstDate, Date lastDate,
			AsyncCallback<NutzerStatistikReport> callback);

	void getAllNutzer(AsyncCallback<Vector<Nutzer>> callback);

	void createBeitragStatistikReport(Date startDate, Date endDate, AsyncCallback<BeitragStatistikReport> callback);
}
