package de.hdm.gwt.itprojektws18.client.gui.report;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HTMLResultPanel extends VerticalPanel {

	public void append(String text) {
		HTML content = new HTML(text);
		content.setStylePrimaryName("social-simpletext");
		this.add(content);
	}
}
