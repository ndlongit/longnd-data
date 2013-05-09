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
import com.structis.fichesst.client.event.ExportSyntheseEvent;
import com.structis.fichesst.client.event.ModificationEvent;
import com.structis.fichesst.client.event.SyntheseEvent;
import com.structis.fichesst.client.handler.ModificationHandler;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class SyntheseBreadcrumbPanel extends AbstractPanel {

	private final LayoutContainer layoutSave;
	private final HTML saveAll;

	public SyntheseBreadcrumbPanel(SimpleEventBus b, final ChantierModel c, final RoleModel role, final UtilisateurGrpModel user) {
		super();
		this.bus = b;
		setHeight("40px");
		setLayout(new BorderLayout());
		LayoutContainer westPanel = new LayoutContainer();
		westPanel.setLayout(new RowLayout(Orientation.HORIZONTAL));
		HTML chantier = new HTML("Chantier: " + c.getNom(), false);
		chantier.setStyleName(ACTION_HTML);
		chantier.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// GuiUtil.gotoEcran(new SyntheseEcran(c, role, user));
			}
		});
		westPanel.add(chantier);
		westPanel.add(new HTML("&nbsp;&nbsp;> Synthèse", false));
		add(westPanel, new BorderLayoutData(LayoutRegion.WEST, 420.0f));
		LayoutContainer eastPanel = new LayoutContainer();
		eastPanel.setLayout(new RowLayout(Orientation.HORIZONTAL));

		saveAll = new HTML("<img src='./images/sauvegarder.png'/> " + messages.saveSynthese(), false);
		saveAll.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		layoutSave = new LayoutContainer();
		layoutSave.add(saveAll);
		layoutSave.setEnabled(false);

		eastPanel.add(layoutSave);
		layoutSave.setStyleAttribute("paddingTop", "5px");
		saveAll.addStyleName(ACTION_HTML4);
		saveAll.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String styleName = saveAll.getStyleName();
				if (ACTION_HTML.equalsIgnoreCase(styleName)) {
					bus.fireEvent(new SyntheseEvent());
					layoutSave.setEnabled(false);
					saveAll.removeStyleName(ACTION_HTML);
					saveAll.addStyleName(ACTION_HTML4);
				}
			}
		});

		if (isAdminOrContributor(role, user)) {
			saveAll.setVisible(true);
		} else {
			saveAll.setVisible(false);
		}
		eastPanel.add(new HTML(LINKS_SPACE));
		LayoutContainer layoutPrint = new LayoutContainer();
		HTML printAll = new HTML("<img src='./images/imprimer.png'/> " + messages.printSynthese(), false);
		printAll.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		printAll.setStyleName(ACTION_HTML);

		printAll.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				bus.fireEvent(new ExportSyntheseEvent());
			}
		});
		layoutPrint.setStyleAttribute("paddingTop", "5px");
		layoutPrint.add(printAll);
		eastPanel.add(layoutPrint);
		add(eastPanel, new BorderLayoutData(LayoutRegion.EAST, 335.0f));

		LayoutContainer centerPanel = new LayoutContainer();
		centerPanel.setLayout(new RowLayout(Orientation.VERTICAL));
		saveAll.setStyleName(ACTION_HTML4);
		HTML html = new HTML("<br>", true);
		centerPanel.add(html);
		bus.addHandler(ModificationEvent.TYPE, new ModificationHandler() {
			@Override
			public void onload(ModificationEvent event) {
				layoutSave.setEnabled(event.getIsEdit());
				if (Boolean.TRUE.equals(event.getIsEdit())) {
					saveAll.setStyleName(ACTION_HTML);
				} else {
					saveAll.removeStyleName(ACTION_HTML);
					saveAll.setStyleName(ACTION_HTML4);
				}
			}
		});
		Label pageTitle = new Label(messages.synthese());
		centerPanel.add(pageTitle);
		pageTitle.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		add(centerPanel, new BorderLayoutData(LayoutRegion.CENTER));
	}
}
