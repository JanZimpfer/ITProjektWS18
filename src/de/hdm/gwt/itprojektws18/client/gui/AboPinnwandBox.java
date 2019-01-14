package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

import de.hdm.gwt.itprojektws18.client.ClientsideSettings;
import de.hdm.gwt.itprojektws18.shared.PinnwandVerwaltungAsync;
import de.hdm.gwt.itprojektws18.shared.bo.Abonnement;
import de.hdm.gwt.itprojektws18.shared.bo.Nutzer;
import de.hdm.gwt.itprojektws18.shared.bo.Pinnwand;

public class AboPinnwandBox extends HorizontalPanel{

	
	/**
	 * Erzeugen eines PinnwandVerwaltung-Objekts um eine Applikationsverwaltung zu initialisieren.
	 */
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandVerwaltung();

	/**
	 * Instanziierung der GUI Elemente
	 */
	private Label nutzerNameLabel = new Label();
	
	private Label nickNameLabel = new Label();
	
//	 Nutzer nutzer = new Nutzer();
	Abonnement abo = new Abonnement();
	
	public AboPinnwandBox() {
		
	}
	
	public AboPinnwandBox(final Abonnement a) {
		
		abo = a;
		
		nutzerNameLabel.addStyleName("vornameAbobox");
		nickNameLabel.addStyleName("nicknameAboBox");
		this.addStyleName("aboPinnwandBox");
		
		
		nutzerNameLabel.addClickHandler(new PinnwandAnzeigenClickhandler());
		nickNameLabel.addClickHandler(new PinnwandAnzeigenClickhandler());
		
		this.add(nutzerNameLabel);
		this.add(nickNameLabel);
		
		super.onLoad();
	}
	
	public void updateNutzerNameLabel(String nutzernameString) {
		
		this.nutzerNameLabel.setText(nutzernameString);
		
	}
	
	public void updateNickNameLabel(String nicknameString) {
		
		this.nickNameLabel.setText(nicknameString);
	}
	
	
	
	class PinnwandAnzeigenClickhandler implements ClickHandler{
	
			
			public void onClick(ClickEvent event) {
				PinnwandBox pBox = new PinnwandBox(abo.getPinnwandFK());
				
				RootPanel.get("InhaltDiv").clear();
				RootPanel.get("InhaltDiv").add(pBox);
			}
			
		}
	
	//Hinzuf�gen von Methoden, die Methoden der Impl verwenden, um Nick- und Nutzername den jeweilgien Labels hinzuzuf�gen
	//(Id Abgleich in Konstruktor, ob ID(bzw Pinnwand)bereits verwendet wurde bzw. ob Abonnementbeziehung bereits in Abobox angezeigt wird)
	// Verkn�pfung mit Suchleiste: Neue Abo Beziehung wird erstellt -> beide update Methoden in AboPWBox werden ausgef�hrt und ein AboPWBox Objekt in AboBox erstellt
}
