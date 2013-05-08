package com.structis.fichesst.client.ecran;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.structis.fichesst.client.navigation.NavigationEvent;
import com.structis.fichesst.client.navigation.NavigationFactory;
import com.structis.fichesst.client.navigation.NavigationService;
import com.structis.fichesst.client.panel.AdminPanel;
import com.structis.fichesst.client.panel.HeaderAdmin;
import com.structis.fichesst.client.panel.HeaderPanel;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class AdminEcran extends LayoutContainer implements EcranLoadable {
	AdminPanel					  adminPanel;
	HeaderAdmin					 headerAdmin;
	HeaderPanel					 headerPanel;
	private final NavigationService navigation = NavigationFactory.getNavigation();
	UtilisateurGrpModel			 user;
	
	public AdminEcran() {
		setWidth(GuiUtil.getScreenWidth() - 20);
		setLayout(new FitLayout());
		setScrollMode(Scroll.AUTO);
		Window.enableScrolling(true);
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent arg0) {
				if (RootPanel.get().getOffsetWidth() < 1300) {
					setScrollMode(Scroll.AUTO);
					// setWidth(1250);
				} else {
					// setWidth(1950);
				}
			}
		});
		setMonitorWindowResize(true);
		headerPanel = new HeaderPanel();
		headerPanel.setHeight(45);
		headerAdmin = new HeaderAdmin();
		adminPanel = new AdminPanel();
		LayoutContainer mainContainer = new LayoutContainer();
		mainContainer.setLayout(new FitLayout());
		mainContainer.add(headerPanel);
		mainContainer.add(headerAdmin);
		mainContainer.add(adminPanel);
		add(mainContainer);
	}
	
	@Override
	public void onLoadApplication(final NavigationEvent event) {
		
	}
	
}
