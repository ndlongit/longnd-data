package com.structis.fichesst.client.ecran;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.structis.fichesst.client.navigation.NavigationEvent;
import com.structis.fichesst.client.panel.HeaderHome;
import com.structis.fichesst.client.panel.HeaderPanel;

public class HomeEcran extends AbstractEcran implements EcranLoadable {
	public HomeEcran() {
		setScrollMode(Scroll.AUTO);
		LayoutContainer rootPanel = createRootPanel();

		LayoutContainer topPanel = new LayoutContainer();
		setWhiteBackgroundColor(topPanel);
		topPanel.setLayout(new BorderLayout());

		HeaderPanel headerPanel = new HeaderPanel();
		topPanel.add(headerPanel, new BorderLayoutData(LayoutRegion.NORTH, 80.0f));

		HeaderHome breadcrumbPanel = new HeaderHome();
		setPadding(breadcrumbPanel);

		topPanel.add(breadcrumbPanel, new BorderLayoutData(LayoutRegion.SOUTH, 50));
		rootPanel.add(topPanel, new BorderLayoutData(LayoutRegion.NORTH, 140.0f));
	}

	protected LayoutContainer createRootPanel() {
		LayoutContainer rootPanel = new LayoutContainer();
		rootPanel.setLayout(new BorderLayout());
		setWhiteBackgroundColor(rootPanel);
		addRootPanel(rootPanel);
		return rootPanel;
	}

	@Override
	public void onLoadApplication(NavigationEvent event) {

	}

}
