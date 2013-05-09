package com.structis.fichesst.client.panel;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.structis.fichesst.client.event.ExportFicheStEvent;
import com.structis.fichesst.client.event.SaveFicheStEvent;
import com.structis.fichesst.shared.dto.ChantierModel;

public class BreadcrumbPanel extends AbstractPanel {

	private final HTML path;
	private final HTML saveFicheSt;
	private final ChantierModel chainter;

	public BreadcrumbPanel(SimpleEventBus b, final ChantierModel c, String societeName) {
		super();
		this.bus = b;
		this.chainter = c;
		setHeight(40);
		setLayout(new BorderLayout());

		LayoutContainer westPanel = new LayoutContainer();
		westPanel.setLayout(new RowLayout(Orientation.HORIZONTAL));

		HTML chantier = new HTML("Chantier: " + this.chainter.getNom(), false);
		chantier.setStyleName(ACTION_HTML);
		chantier.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.Location.reload();
				History.newItem(c.getId().toString());
			}
		});
		westPanel.add(chantier);

		path = new HTML("", false);
		setSociete(societeName);
		westPanel.add(path);

		add(westPanel, new BorderLayoutData(LayoutRegion.WEST, 500.0f));
		LayoutContainer eastPanel = new LayoutContainer();
		
		HBoxLayout hBoxLayout = new HBoxLayout();
		hBoxLayout.setPadding(new Padding(PADDING));
		eastPanel.setLayout(hBoxLayout);

		saveFicheSt = new HTML("<img src='./images/sauvegarder.png'/> " + messages.saveForm(), false);
		saveFicheSt.setStyleName(ACTION_HTML);
		
		HBoxLayoutData flex = new HBoxLayoutData();
		flex.setFlex(1);
		eastPanel.add(new Text(), flex);
		eastPanel.add(saveFicheSt, new HBoxLayoutData());
		
		saveFicheSt.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				bus.fireEvent(new SaveFicheStEvent());
			}
		});

		eastPanel.add(new HTML(LINKS_SPACE, false), new HBoxLayoutData());
		HTML printAll = new HTML("<img src='./images/imprimer.png'/> " + messages.printAll(), false);
		printAll.setStyleName(ACTION_HTML);

		eastPanel.add(printAll, new HBoxLayoutData());
		
		add(eastPanel, new BorderLayoutData(LayoutRegion.EAST, 500.0f));

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
		if (societeName == null) {
			societeName = "";
		}

		path.setHTML("&nbsp;&nbsp;> Fiche ST: " + societeName);
	}

	@Override
	protected void onLoad() {
		super.onLoad();
	}
}
