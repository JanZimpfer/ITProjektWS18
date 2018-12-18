package de.hdm.gwt.itprojektws18.shared;

import java.sql.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.report.BeitragStatistikReport;
import de.hdm.gwt.itprojektws18.shared.report.NutzerStatistikReport;
import de.hdm.gwt.itprojektws18.client.gui.report.LikeStatistikReport;
import de.hdm.gwt.itprojektws18.shared.bo.Beitrag;

@RemoteServiceRelativePath("reportgenerator")
public interface ReportGenerator extends RemoteService{
	
	
public void init() throws IllegalArgumentException;

public abstract NutzerStatistikReport createNutzerStatistikReport(Nutzer nutzer) throws IllegalArgumentException;

//public abstract BeitragStatistikReport createBeitragStatistikReport(Beitrag beitrag) throws IllegalArgumentException;

public abstract LikeStatistikReport createLikeStatistikReport (int like) throws IllegalArgumentException;

public Vector<Nutzer> getNutzerByName(String vorname, String nachname) throws IllegalArgumentException;

public Vector<Nutzer> getNutzerByNickname(String nickname) throws IllegalArgumentException;

Nutzer findNutzerByEmail(String mail) throws IllegalArgumentException;

BeitragStatistikReport createBeitragStatistikReport(Nutzer nutzer, Beitrag beitrag, Date dateFrom)
		throws IllegalArgumentException;

}


