package com.structis.fichesst.client.ecran;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.structis.fichesst.client.navigation.NavigationEvent;
import com.structis.fichesst.client.navigation.NavigationFactory;
import com.structis.fichesst.client.navigation.NavigationService;
import com.structis.fichesst.client.panel.HeaderPanel;
import com.structis.fichesst.client.panel.TransfertPpPanel;
import com.structis.fichesst.client.panel.TransfertppBreadcumbPanel;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class TransfertppEcran extends AbstractEcran implements EcranLoadable {
	protected NavigationService navigation = NavigationFactory.getNavigation();
	ChantierModel chantier;
	Integer transfertPpId;
	RoleModel role;
	UtilisateurGrpModel user;

	public TransfertppEcran(/* ChantierModel chantier, Integer transfertPpId, RoleModel role, UtilisateurGrpModel user */) {
		chantier = navigation.getContext().getCurrentChantier();
		transfertPpId = navigation.getContext().getTransfertppId();
		user = navigation.getContext().getUserModel();
		// miss role
		// setHeaderVisible(false);
		// setBodyBorder(false);
		// setWidth(GuiUtil.getScreenWidth() - 60);
		/* setScrollMode(Scroll.AUTO); */
		setId("transfertEcran");
		setLayout(new FitLayout());
		HeaderPanel headerPanel = new HeaderPanel();
		headerPanel.setHeight(45);
		headerPanel.setStyleAttribute("padding-left", "10px");
		// headerPanel.setStyleAttribute("padding-right", "30px");
		TransfertppBreadcumbPanel breadcrumbPanel = new TransfertppBreadcumbPanel(chantier, transfertPpId, role, user);
		breadcrumbPanel.setStyleAttribute("padding-left", "10px");
		TransfertPpPanel transfertPpPanel = new TransfertPpPanel(chantier, transfertPpId, role, user);
		// transfertPpPanel.setWidth(GuiUtil.getScreenWidth() - 40);
		LayoutContainer mainContent = new LayoutContainer();
		mainContent.add(headerPanel);
		mainContent.add(breadcrumbPanel);
		mainContent.add(transfertPpPanel);
		mainContent.setLayout(new FitLayout());
		add(mainContent);
	}

	@Override
	public void onLoadApplication(NavigationEvent event) {

	}
}
