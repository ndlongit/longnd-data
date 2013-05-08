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
import com.structis.fichesst.client.event.ExportFicheStEvent;
import com.structis.fichesst.client.event.SaveFicheStEvent;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.shared.dto.ChantierModel;
import com.structis.fichesst.shared.dto.RoleModel;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;

public class BreadcrumbPanel extends AbstractPanel {

	private HTML path;
	private HTML saveFicheSt;
	private ChantierModel chainter;

	public BreadcrumbPanel(SimpleEventBus b, final ChantierModel c, final RoleModel role, final UtilisateurGrpModel user) {
		super();
		this.bus = b;
		this.chainter = c;
		setHeight("40px");
		setLayout(new BorderLayout());

		LayoutContainer westPanel = new LayoutContainer();
		westPanel.setLayout(new RowLayout(Orientation.HORIZONTAL));
		
		HTML chantier = new HTML("Chantier: " + this.chainter.getNom(), false);
		chantier.setStyleName("actionHTML");
		chantier.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				GuiUtil.gotoEcran(new SyntheseEcran(c, role, user) );
			}
		});
		westPanel.add(chantier);

		path = new HTML("", false);
		setSociete("");
		westPanel.add(path);
		
		add(westPanel, new BorderLayoutData(LayoutRegion.WEST, 450.0f));
		LayoutContainer eastPanel = new LayoutContainer();
		eastPanel.setLayout(new RowLayout(Orientation.HORIZONTAL));

		saveFicheSt = new HTML("<img src='./images/sauvegarder.png'/> " + messages.saveForm(), false);
		saveFicheSt.setStyleName("actionHTML");
		eastPanel.add(saveFicheSt);
		saveFicheSt.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				bus.fireEvent(new SaveFicheStEvent());
			}
		});

		eastPanel.add(new HTML(LINKS_SPACE, false));
		HTML printAll = new HTML("<img src='./images/imprimer.png'/> " + messages.printAll(), false);
		printAll.setStyleName("actionHTML");

		eastPanel.add(printAll);
		add(eastPanel, new BorderLayoutData(LayoutRegion.EAST, 355.0f));

		LayoutContainer centerPanel = new LayoutContainer();
		centerPanel.setLayout(new RowLayout(Orientation.VERTICAL));
		
		printAll.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				bus.fireEvent(new ExportFicheStEvent());
			}
		});

		HTML html = new HTML("<br>", true);
		centerPanel.add(html);

		Label pageTitle = new Label("Fiche de suivi du sous traitant");
		centerPanel.add(pageTitle);
		pageTitle.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		add(centerPanel, new BorderLayoutData(LayoutRegion.CENTER));
	}

	public void setSociete(String societeName) {
		if( societeName == null ) {
			societeName = "";
		}

		path.setHTML("&nbsp;&nbsp;> Fiche ST: " + societeName);
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
	}
}
