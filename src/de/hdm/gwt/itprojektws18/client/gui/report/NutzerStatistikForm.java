package de.hdm.gwt.itprojektws18.client.gui.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.ReportGeneratorAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;

public class NutzerStatistikForm extends HorizontalPanel{


	ReportGeneratorAsync reportVerwaltung = ClientsideSettings.getReportGenerator();
	
	private Button reportStart = new Button("Report Starten");
	
	private MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	private SuggestBox box = new SuggestBox(oracle);
	private List<Nutzer> nutzerListe = new ArrayList<>();
	private List<Nutzer> nutzerSuggestBox = new ArrayList<>();
	
	private CellTable<Nutzer> selectedNutzerCT = new CellTable<Nutzer>();
	private ListDataProvider<Nutzer> nutzerDataProvider = new ListDataProvider<Nutzer>(nutzerSuggestBox);
	private Nutzer nutzerausdb = null;
	
	private Label labelNutzer = new Label("Nutzer von: ");
	
	private VerticalPanel vpanel = new VerticalPanel();
	
	public NutzerStatistikForm() {
		vpanel.clear();
		Nutzer n = new Nutzer();
		n.setId(Integer.parseInt(Cookies.getCookie("id")));
		
		this.add(vpanel);	
	}
	
	public class FindNutzerByEmail implements AsyncCallback<Nutzer>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler: " + caught.getMessage());
		}

		@Override
		public void onSuccess(Nutzer result) {
			nutzerausdb = result;
		}
		
	}
	
	public class FindNutzerByNickname implements AsyncCallback<Nutzer>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler: " + caught.getMessage());			
		}

		@Override
		public void onSuccess(Nutzer result) {
			nutzerausdb = result;
		}
		
	}
	
	public class FindNutzerByName implements AsyncCallback<Nutzer>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler: " + caught.getMessage());
		}

		@Override
		public void onSuccess(Nutzer result) {
			nutzerausdb = result;
		}
		
	}
	
	class NutzerStatistikReport implements AsyncCallback<Vector<Nutzer>>{

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Fehler: " + caught.getMessage());
		}

		@Override
		public void onSuccess(Vector<Nutzer> result) {
			for(Nutzer nutzer : result) {
				oracle.add(nutzer.getNickname());
			}
		}
		
	}
	
	class AlleNutzerClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			
		}
		
	}

}
