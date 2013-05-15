package com.structis.vip.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.structis.vip.client.message.Messages;

public class AccueilHeaderPanel extends SimplePanel {
	
	private final Messages messages = GWT.create(Messages.class);
	
	public AccueilHeaderPanel(){
		buildPanel();
	}

	private void buildPanel() {
		setStyleName("accueilHeader");
		HTML content = new HTML(messages.accueilContentHeader());
		content.setStyleName("accueilHeaderText");
		
		add(content);
	}

}
