package com.structis.fichesst.client.ecran;

import java.util.List;

import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.structis.fichesst.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.fichesst.client.navigation.NavigationEvent;
import com.structis.fichesst.client.navigation.NavigationFactory;
import com.structis.fichesst.client.navigation.NavigationService;
import com.structis.fichesst.client.panel.HeaderPanel;
import com.structis.fichesst.client.service.ClientRoleService;
import com.structis.fichesst.client.service.ClientRoleServiceAsync;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class ErrorEcran extends LayoutContainer implements EcranLoadable {
	
	private HeaderPanel headerPanel;

	private final NavigationService navigation = NavigationFactory.getNavigation();
	
	private final ClientRoleServiceAsync serviceRole = GWT.create(ClientRoleService.class);

	public ErrorEcran() {
		loadPanel();
		setMonitorWindowResize(true);
		setLayout(new FitLayout());

		LayoutContainer mainContent = new LayoutContainer();
		headerPanel.setEnabled(false);
		LayoutContainer errorlPanel = new LayoutContainer();
		errorlPanel.setHeight(40);
		Html error = new Html("Accès refuse. Vous n’avez pas les droits suffisants pour l’opération demandée");
		error.setStyleAttribute("color", "red");
		errorlPanel.add(error);
		GuiUtil.setPadding(errorlPanel);

		mainContent.setLayout(new FitLayout());
		mainContent.add(headerPanel);
		mainContent.add(errorlPanel);
		add(mainContent);
	}

	public void loadPanel() {
		UtilisateurGrpModel user = navigation.getContext().getUserModel();
		headerPanel = new HeaderPanel();
		headerPanel.setHeight(45);
		if( navigation.getContext().getUserModel() != null ) {
			serviceRole.findRolesByIdUser(user.getId(), new AsyncCallbackWithErrorResolution<List<RoleModel>>() {
				@Override
				public void onSuccess(List<RoleModel> arg0) {
					if( arg0.size() > 0 ) {
						headerPanel.setEnabled(true);
					}
				}
			});
		}

	}

	@Override
	public void onLoadApplication(NavigationEvent event) {
	}
}
