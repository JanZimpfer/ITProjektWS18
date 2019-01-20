//package de.hdm.gwt.itprojektws18.client.gui;
//
//import java.util.Vector;
//
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.DialogBox;
//import com.google.gwt.user.client.ui.Label;
//import com.google.gwt.user.client.ui.VerticalPanel;
//
//import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
//
//import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
//import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
//
//public class SuchergebnisBox extends DialogBox {
//
//	/**
//	 * Instanziierung eines PinnwandVerwaltung-Objekts um eine
//	 * Applikationsverwaltung zu initialisieren
//	 */
//	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();
//
//	/**
//	 * Erstellung der benötigten GUI-Elemente
//	 */
//
//	private VerticalPanel resultPanel = new VerticalPanel();
//
//	private Button resultSchliessenBtn = new Button("Schliessen");
//
//	public SuchergebnisBox() {
//
//	}
//
//	public SuchergebnisBox(final String s) {
//
//		this.add(resultPanel);
//
//		pinnwandVerwaltung.searchNutzer(s, new ErgebnisCallback ());
//		
//		
//	}
//
//			
//
//	class ErgebnisCallback implements AsyncCallback<Vector<Nutzer>> {
//
//		@Override
//		public void onFailure(Throwable caught) {
//
//		}
//
//		@Override
//		public void onSuccess(Vector<Nutzer> result) {
//
//			for (int i = 0; i < result.size(); i++) {
//
//				Label resultLabel = new Label();
//
//				String text = "Nutzer: " + result.elementAt(i).getVorname() + " " + result.elementAt(i).getNachname()
//						+ " " + result.elementAt(i).getNickname() + "";
//
//				resultLabel.setText(text);
//
//				resultPanel.add(resultLabel);
//
//				resultPanel.add(resultSchliessenBtn);
//
//				resultSchliessenBtn.addClickHandler(new SchliessenClickHandler());
//
//			}
//		}
//
//	}
//
//	class SchliessenClickHandler implements ClickHandler {
//
//		public void onClick(ClickEvent event) {
//			hide();
//
//		}
//
//	}
//
//}
