package com.structis.fichesst.client.panel;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;

public class HeaderHome extends AbstractPanel {
	public HeaderHome() {
		setHeight("40px");
		setLayout(new BorderLayout());
		setMonitorWindowResize(true);
		
		LayoutContainer westPanel = new LayoutContainer();
		westPanel.setLayout(new RowLayout(Orientation.HORIZONTAL));
		
		com.extjs.gxt.ui.client.widget.Label chantier = new com.extjs.gxt.ui.client.widget.Label("Acceuil");
		chantier.setStyleName("actionHTML5");
		westPanel.add(chantier);
		
		add(westPanel, new BorderLayoutData(LayoutRegion.WEST, 390.0f));
		
		LayoutContainer eastPanel = new LayoutContainer();
		eastPanel.setLayout(new RowLayout(Orientation.HORIZONTAL));
		
		HTML space = new HTML("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;", true);
		eastPanel.add(space);
		
		add(eastPanel, new BorderLayoutData(LayoutRegion.EAST, 390.0f));
		
		LayoutContainer centerPanel = new LayoutContainer();
		centerPanel.setLayout(new RowLayout(Orientation.VERTICAL));
		
		HTML html = new HTML("<br>", true);
		centerPanel.add(html);
		
		Label pageTitle = new Label("Administration des rôles");
		/* centerPanel.add(pageTitle); */
		pageTitle.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		setPadding(centerPanel);
		add(centerPanel, new BorderLayoutData(LayoutRegion.CENTER));
	}
}
