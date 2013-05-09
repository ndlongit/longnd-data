package com.structis.fichesst.client.ecran;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.structis.fichesst.client.panel.HeaderPanel;
import com.structis.fichesst.client.panel.SyntheseBreadcrumbPanel;
import com.structis.fichesst.client.panel.SyntheseMainPanel;
import com.structis.fichesst.client.service.ClientChantierService;
import com.structis.fichesst.client.service.ClientChantierServiceAsync;
import com.structis.fichesst.client.service.ClientRoleService;
import com.structis.fichesst.client.service.ClientRoleServiceAsync;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class SyntheseEcran extends AbstractEcran {
	TabPanel tabPanel;
	TabPanel tabPanel1;
	TabPanel tabPanel2;
	private final ChantierModel chantier;
	private final RoleModel role;
	private final ClientChantierServiceAsync service = GWT.create(ClientChantierService.class);
	private final ClientRoleServiceAsync serviceRole = GWT.create(ClientRoleService.class);
	private final UtilisateurGrpModel user;

	public SyntheseEcran(/* final ChantierModel chantier, final RoleModel role, final UtilisateurGrpModel user */) {
		// bus.addHandler(LoadTransfertppSummaryDtoEvent.TYPE,
		// new LoadTransfertppSummaryDtoHandler() {
		//
		// @Override
		// public void onLoad(LoadTransfertppSummaryDtoEvent event) {
		// TransfertPpSummaryDto transfertPpSummaryDto = event
		// .getTransfertPpSummaryDto();
		// TransfertppEcran transfertppEcran = new TransfertppEcran(chantier, transfertPpSummaryDto.getRefTransfertPpId(),role,user);
		// GuiUtil.addTab(tabPanel, "TransfertPP Panel",
		// transfertppEcran);
		// }
		// });
		// bus.addHandler(LoadFichestEcranEvent.TYPE, new LoadFichestEcranHandler() {
		//
		// @Override
		// public void onLoad(LoadFichestEcranEvent event) {
		// ChantierModel chantier = event.getChantierModel();
		// FicheStDto ficheStDto = event.getFichestDto();
		// FicheSTEcran fichestEcran = new FicheSTEcran(chantier,
		// ficheStDto.getId(), role, user);
		// LayoutContainer tabContent=new LayoutContainer();
		// tabContent.add(fichestEcran);
		// GuiUtil.addTab(tabPanel, "Fichest Ecran", tabContent);
		//
		// }
		// });
		// setScrolling();

		// RoleModel role = navigation.getContext().getRoleModel();
		setStyleAttribute("padding-left", "5px");
		user = navigation.getContext().getUserModel();
		chantier = navigation.getContext().getCurrentChantier();
		role = navigation.getContext().getRoleModel();
		HeaderPanel headerPanel = new HeaderPanel();
		headerPanel.setHeight(45);
		final SyntheseBreadcrumbPanel breadcrumbPanel = new SyntheseBreadcrumbPanel(bus, chantier, role, user);

		setPadding(breadcrumbPanel);
		final SyntheseMainPanel centerPanel = new SyntheseMainPanel(bus, chantier, role, user);
		LayoutContainer mainContent = new LayoutContainer();
		mainContent.setLayout(new FitLayout());
		mainContent.add(headerPanel);
		// mainContent.add(new Html("<br/>"));
		mainContent.add(breadcrumbPanel);
		mainContent.add(centerPanel);
		// setPadding(centerPanel);
		// TabItem item=new TabItem();
		// // item.setTabIndex(0);
		// item.setText("Synthese");
		// item.add(mainContent);
		// tabPanel.add(item);
		add(mainContent);

	}

}
