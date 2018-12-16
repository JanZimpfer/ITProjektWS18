package de.hdm.gwt.itprojektws18.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

import java.util.Vector;

public class BeitragBox extends FlowPanel {

	private Vector<KommentarBox> kommentareOfBeitrag = new Vector<KommentarBox>();
	private Vector<LikeBox> likesOfBeitrag = new Vector<LikeBox>();

	// Panels fuer die Darstellung der BeitragBox
	private VerticalPanel InhaltPanel = new VerticalPanel();
	private VerticalPanel InfoPanel = new VerticalPanel();

	//benoetigte Label
	private Label beitragInhalt = new Label();
	private Label erstellZeitpunkt = new Label();
	private Label kommentarAnzahlText = new Label();
	private int kommentarAnzahl = kommentareOfBeitrag.size();
	private Label likeAnzahlText = new Label();
	private int likeAnzahl = likesOfBeitrag.size();
	
	// Erstellen von Kommentaren
	private TextArea kommentarTextArea = new TextArea();
	private Button kommentierButton = new Button("Kommentieren");


	public BeitragBox() {

	}

	public void onLoad() {

		this.add(InhaltPanel);
		this.add(InfoPanel);

		InhaltPanel.add(beitragInhalt);
		InhaltPanel.add(erstellZeitpunkt);
		InhaltPanel.add(kommentarAnzahlText);
		InhaltPanel.add(likeAnzahlText);
		

	}

}
