package com.structis.fichesst.client.ecran;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.structis.fichesst.client.navigation.NavigationEvent;
import com.structis.fichesst.client.panel.TransfertppBreadcumbPanel;
import com.structis.fichesst.client.panel.HeaderPanel;
import com.structis.fichesst.client.panel.TransfertPpPanel;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class TransfertppEcran extends ContentPanel implements EcranLoadable {
	
	public TransfertppEcran(ChantierModel chantier, Integer transfertPpId,RoleModel role,UtilisateurGrpModel user) {
		setWidth(GuiUtil.getScreenWidth()-30);
		setHeaderVisible(false);
		setBodyBorder(false);
		/*setScrollMode(Scroll.AUTO);*/
		setId("transfertEcran");
		setBorders(false);
		setStyleAttribute("position", "relative");
		Window.enableScrolling(true);
		setLayout(new FitLayout());
		setScrollMode(Scroll.AUTOY);
		HeaderPanel headerPanel = new HeaderPanel();
		headerPanel.setHeight(45);
		TransfertppBreadcumbPanel breadcrumbPanel = new TransfertppBreadcumbPanel(chantier,transfertPpId,role,user);
		breadcrumbPanel.setStyleAttribute("padding-left","10px" );
		TransfertPpPanel transfertPpPanel = new TransfertPpPanel(chantier,transfertPpId,role,user);
		LayoutContainer mainContent = new LayoutContainer();
		mainContent.add(headerPanel);
		mainContent.add(breadcrumbPanel);
		mainContent.add(transfertPpPanel);
		mainContent.setLayout(new FitLayout());
		com.google.gwt.user.client.Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent arg0) {
				if( RootPanel.get("appContent").getOffsetWidth() < 1276 ) {
				
					setScrollMode(Scroll.AUTO);
				}
			
			}
		});
		add(mainContent);
	}

	@Override
	public void onLoadApplication(NavigationEvent event) {

	}
}
