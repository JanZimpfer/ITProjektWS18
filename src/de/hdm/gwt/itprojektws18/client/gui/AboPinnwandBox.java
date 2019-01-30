package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Abonnement;

/**
 * Klasse, die es ermöglicht, einzelne Abonnements eines Nutzers zu erstellen, die dessen Nutzername
 * und Nickname beinhalten. Instanzen dieser Klasse werden in der Klasse "AboBox" dargestellt.
 * @author NiklasFuchs
 *
 */

public class AboPinnwandBox extends HorizontalPanel{
	
	/**
	 * Erzeugen eines PinnwandVerwaltung-Objekts um eine Applikationsverwaltung zu initialisieren.
	 * @author NiklasFuchs
	 */
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	/**
	 * Instanziierung der GUI Elemente
	 * @author NiklasFuchs
	 */
	private Label nutzerNameLabel = new Label();
	
	private Label nickNameLabel = new Label();
	
	/**
	 * Deklarierung des Business Object das verwendet wird
	 * @author NiklasFuchs
	 */
	Abonnement abo = new Abonnement();
	
	public AboPinnwandBox() {
		
	}
	
	public AboPinnwandBox(final Abonnement a) {
		
		abo = a;
		
		/**
		 * Hinzufügen von Clickhandler auf die Label, um auf die Pinnwand		 * 
		 * des angeklickten Nutzers zu gelangen
		 * @author NiklasFuchs
		 */
		nutzerNameLabel.addClickHandler(new PinnwandAnzeigenClickhandler());
		nickNameLabel.addClickHandler(new PinnwandAnzeigenClickhandler());
		
		this.add(nutzerNameLabel);
		this.add(nickNameLabel);
		
		/**
		 * Hinzufügen der StyleNamen für CSS-Styling
		 * @author NiklasFuchs
		 */
		nutzerNameLabel.addStyleName("vornameAbobox");
		nickNameLabel.addStyleName("nicknameAboBox");
		this.addStyleName("aboPinnwandBox");
		
		super.onLoad();
	}
	
	/**
	 * Methode zum Setzen eines Nutzernamens einer AboPinnwandBox
	 * @param nutzernameString
	 */
	public void updateNutzerNameLabel(String nutzernameString) {
		
		this.nutzerNameLabel.setText(nutzernameString);
		
	}
	
	/**
	 * Methode zum Setzen eines Nicknamens einer AboPinnwandBox
	 * @param nicknameString
	 */
	public void updateNickNameLabel(String nicknameString) {
		
		this.nickNameLabel.setText(nicknameString);
	}
	

	/**
	 * Nested Class für das Anzeigen der Pinnwand des abonnierten Nutzers, implementiert
	 * das ClickHandler Interface
	 * @author NiklasFuchs
	 *
	 */
	class PinnwandAnzeigenClickhandler implements ClickHandler{
	
			
			public void onClick(ClickEvent event) {
				PinnwandBox pBox = new PinnwandBox(abo.getPinnwandFK());
				
				RootPanel.get("InhaltDiv").clear();
				RootPanel.get("InhaltDiv").add(pBox);
			}
			
		}
	
}
