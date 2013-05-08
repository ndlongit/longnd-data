package com.structis.fichesst.client.panel;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.structis.fichesst.client.ecran.SyntheseEcran;
import com.structis.fichesst.client.event.ExportSyntheseEcranEvent;
import com.structis.fichesst.client.event.IsEditEvent;
import com.structis.fichesst.client.event.SyntheseEvent;
import com.structis.fichesst.client.handler.IsEditHandler;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class SyntheseBreadcrumbPanel extends AbstractPanel {
	LayoutContainer layoutSave;
	
	public SyntheseBreadcrumbPanel(SimpleEventBus b, final ChantierModel c, final RoleModel role, final UtilisateurGrpModel user) {
		super();
		this.bus = b;
		setHeight("40px");
		setLayout(new BorderLayout());
		LayoutContainer westPanel = new LayoutContainer();
		westPanel.setLayout(new RowLayout(Orientation.HORIZONTAL));
		HTML chantier = new HTML("Chantier: " + c.getNom(), false);
		chantier.setStyleName("actionHTML");
		chantier.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				GuiUtil.gotoEcran(new SyntheseEcran(c, role, user));
			}
		});
		westPanel.add(chantier);
		westPanel.add(new HTML("&nbsp;&nbsp;> Synthèse", false));
		add(westPanel, new BorderLayoutData(LayoutRegion.WEST, 390.0f));
		LayoutContainer eastPanel = new LayoutContainer();
		eastPanel.setLayout(new RowLayout(Orientation.HORIZONTAL));
		
		HTML saveAll = new HTML("<img src='./images/sauvegarder.png'/> " + messages.saveSynthese(), false);
		saveAll.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		saveAll.setStyleName("actionHTML");
		layoutSave = new LayoutContainer();
		layoutSave.add(saveAll);
		layoutSave.setEnabled(false);
		eastPanel.add(layoutSave);
		saveAll.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				bus.fireEvent(new SyntheseEvent());
				layoutSave.setEnabled(false);
				// bus.fireEvent(new IsEditEvent(false));
			}
		});
		;
		if ((user.getBadmin() != null && user.getBadmin()) || (role.getBcontributeur() != null && role.getBcontributeur())) {
			saveAll.setVisible(true);
		} else {
			saveAll.setVisible(false);
		}
		eastPanel.add(new HTML(LINKS_SPACE));
		HTML printAll = new HTML("<img src='./images/imprimer.png'/> " + messages.printSynthese(), false);
		printAll.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		printAll.setStyleName("actionHTML");
		
		printAll.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				bus.fireEvent(new ExportSyntheseEcranEvent());
			}
		});
		eastPanel.add(printAll);
		add(eastPanel, new BorderLayoutData(LayoutRegion.EAST, 355.0f));
		
		LayoutContainer centerPanel = new LayoutContainer();
		centerPanel.setLayout(new RowLayout(Orientation.VERTICAL));
		
		HTML html = new HTML("<br>", true);
		centerPanel.add(html);
		bus.addHandler(IsEditEvent.TYPE, new IsEditHandler() {
			
			@Override
			public void onload(IsEditEvent event) {
				if (event.getIsEdit() == true) {
					layoutSave.setEnabled(true);
				} else {
					layoutSave.setEnabled(false);
				}
				
			}
		});
		Label pageTitle = new Label(messages.synthese());
		centerPanel.add(pageTitle);
		pageTitle.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		add(centerPanel, new BorderLayoutData(LayoutRegion.CENTER));
	}
}
