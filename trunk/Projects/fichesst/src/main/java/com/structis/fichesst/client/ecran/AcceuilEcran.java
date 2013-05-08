package com.structis.fichesst.client.ecran;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Window;
import com.structis.fichesst.client.navigation.NavigationEvent;
import com.structis.fichesst.client.panel.AcceuilPanel;
import com.structis.fichesst.client.panel.HeaderAcceuil;
import com.structis.fichesst.client.panel.HeaderPanel;
import com.structis.fichesst.client.util.GuiUtil;

public class AcceuilEcran extends LayoutContainer implements EcranLoadable {
    AcceuilPanel acceuilPanel;
    HeaderPanel headerPanel;
    NavigationEvent navigationEvent;

    public AcceuilEcran() {
	// setHeight(768);
	setWidth(GuiUtil.getScreenWidth() - 30);
	setMonitorWindowResize(true);
	setLayout(new FitLayout());
	setScrollMode(Scroll.AUTO);
	Window.enableScrolling(true);
	// Window.addResizeHandler(new ResizeHandler() {
	// @Override
	// public void onResize(ResizeEvent arg0) {
	// if (arg0.getWidth() < 1300) {
	// setScrollMode(Scroll.AUTO);
	// } else {
	// }
	// }
	// });
	headerPanel = new HeaderPanel();
	headerPanel.setHeight(45);
	headerPanel.setStyleAttribute("padding-right", "20px");
	HeaderAcceuil breadcrumbPanel = new HeaderAcceuil();
	acceuilPanel = new AcceuilPanel();
	LayoutContainer mainContent = new LayoutContainer();
	mainContent.setLayout(new FitLayout());
	mainContent.add(headerPanel);
	mainContent.add(breadcrumbPanel);
	mainContent.add(acceuilPanel);
	add(mainContent);

    }

    @Override
    public void onLoadApplication(final NavigationEvent event) {

    }
}