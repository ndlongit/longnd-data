package com.structis.fichesst.client.panel;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.structis.fichesst.client.event.SaveFicheStEvent;
import com.structis.fichesst.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.fichesst.client.service.ClientChantierService;
import com.structis.fichesst.client.service.ClientChantierServiceAsync;
import com.structis.fichesst.client.service.ClientRefTransfertppService;
import com.structis.fichesst.client.service.ClientRefTransfertppServiceAsync;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.RefTransfertPPDto;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class TransfertppBreadcumbPanel extends AbstractPanel {

	SimpleEventBus bus = new SimpleEventBus();
	Hyperlink hyperlink;
	HTML chantier;
	final ClientChantierServiceAsync serviceChantier = GWT.create(ClientChantierService.class);
	final ClientRefTransfertppServiceAsync serviceRefTransfert = GWT.create(ClientRefTransfertppService.class);

	public TransfertppBreadcumbPanel(final ChantierModel chantierModel, final Integer transfertPpId, final RoleModel role,
			final UtilisateurGrpModel user) {
		super();
		/* this.bus = b; */
		loadPanel(chantierModel, transfertPpId);
		setHeight("40px");
		setLayout(new BorderLayout());
		setMonitorWindowResize(true);

		LayoutContainer westPanel = new LayoutContainer();
		westPanel.setLayout(new RowLayout(Orientation.HORIZONTAL));
		chantier = new HTML();
		/* chantier.setVisible(false); */
		chantier.setWidth("1000px");
		chantier.setStyleName("actionHTML2");
		westPanel.add(chantier);
		chantier.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {

				Window.Location.reload();
				History.newItem(chantierModel.getId().toString());

			}
		});
		add(westPanel, new BorderLayoutData(LayoutRegion.WEST, 500.0f));
		LayoutContainer eastPanel = new LayoutContainer();
		eastPanel.setLayout(new RowLayout(Orientation.HORIZONTAL));
		HTML saveFicheSt = new HTML("<img src='./images/sauvegarder.png'/> " + messages.saveForm());
		saveFicheSt.setStyleName("actionHTML");
		/* eastPanel.add(saveFicheSt); */
		saveFicheSt.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				bus.fireEvent(new SaveFicheStEvent());
			}
		});

		HTML space = new HTML("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;", true);
		eastPanel.add(space);

		HTML printAll = new HTML("<img src='./images/imprimer.png'/> " + messages.printAll());
		printAll.setStyleName("actionHTML");

		/* eastPanel.add(printAll); */
		add(eastPanel, new BorderLayoutData(LayoutRegion.EAST, 390.0f));

		LayoutContainer centerPanel = new LayoutContainer();
		centerPanel.setLayout(new RowLayout(Orientation.VERTICAL));

		HTML html = new HTML("<br>", true);
		centerPanel.add(html);
		Label pageTitle = new Label("Fiche de Transfert PP");
		centerPanel.add(pageTitle);
		pageTitle.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		setPadding(centerPanel);
		add(centerPanel, new BorderLayoutData(LayoutRegion.CENTER));
	}

	public void loadPanel(ChantierModel chantierModel, final Integer idTransfertpp) {
		serviceChantier.findChantierById(chantierModel.getId(), new AsyncCallbackWithErrorResolution<ChantierModel>() {
			@Override
			public void onSuccess(final ChantierModel c) {
				serviceRefTransfert.findById(idTransfertpp, new AsyncCallbackWithErrorResolution<RefTransfertPPDto>() {
					@Override
					public void onSuccess(RefTransfertPPDto arg0) {

						chantier.setHTML("Chantier:" + c.getNom() + "<label style='color:black'>&nbsp;>Fiche de Transfert PP: " + arg0.getLabel()
								+ "</label>");
						chantier.setVisible(true);
					}
				});
			}
		});

	}

}
