package de.hdm.gwt.itprojektws18.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.report.BeitragStatistikReport;
import de.hdm.gwt.itprojektws18.shared.report.NutzerStatistikReport;
import de.hdm.gwt.itprojektws18.client.gui.report.LikeStatistikReport;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;

@RemoteServiceRelativePath("reportGenerator")
public interface ReportGenerator extends RemoteService{
	
	
public void init() throws IllegalArgumentException;



public Vector<Nutzer> getNutzerByName(String vorname, String nachname) throws IllegalArgumentException;

public Vector<Nutzer> getNutzerByNickname(String nickname) throws IllegalArgumentException;

Nutzer findNutzerByEmail(String mail) throws IllegalArgumentException;

BeitragStatistikReport createBeitragStatistikReport(Nutzer nutzer, Date startDate, Date endDate)
		throws IllegalArgumentException;

Vector<Beitrag> getBeitrageByNutzer(Nutzer n) throws IllegalArgumentException;

Nutzer getNutzerById(int nutzerID) throws IllegalArgumentException;

NutzerStatistikReport createNutzerStatistikReport(Nutzer nutzer, Date firstDate, Date lastDate)
		throws IllegalArgumentException;

}


