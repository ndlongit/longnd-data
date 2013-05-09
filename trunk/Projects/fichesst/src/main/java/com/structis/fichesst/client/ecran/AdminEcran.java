package com.structis.fichesst.client.ecran;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Window;
import com.structis.fichesst.client.navigation.NavigationEvent;
import com.structis.fichesst.client.navigation.NavigationFactory;
import com.structis.fichesst.client.navigation.NavigationService;
import com.structis.fichesst.client.panel.AdminPanel;
import com.structis.fichesst.client.panel.HeaderAdmin;
import com.structis.fichesst.client.panel.HeaderPanel;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class AdminEcran extends LayoutContainer implements EcranLoadable {
	AdminPanel adminPanel;
	HeaderAdmin headerAdmin;
	HeaderPanel headerPanel;
	private final NavigationService navigation = NavigationFactory.getNavigation();
	UtilisateurGrpModel user;

	public AdminEcran() {
		setWidth(GuiUtil.getScreenWidth() - 30);
		setLayout(new FitLayout());
		setScrollMode(Scroll.AUTO);
		Window.enableScrolling(true);
		headerPanel = new HeaderPanel();
		headerPanel.setHeight(45);
		headerPanel.setStyleAttribute("padding-left", "12px");
		headerPanel.setStyleAttribute("padding-right", "5px");
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
