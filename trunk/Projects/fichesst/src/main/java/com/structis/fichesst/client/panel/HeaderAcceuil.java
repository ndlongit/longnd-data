package com.structis.fichesst.client.panel;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.structis.fichesst.client.event.SaveFicheStEvent;

public class HeaderAcceuil extends AbstractPanel {
	private String societe = "Métaux de Paris";

	private HTML path;
	public HeaderAcceuil(){

		super();
		/*this.bus = b;*/
		setHeight("40px");
		setLayout(new BorderLayout());
		setMonitorWindowResize(true);
		
		LayoutContainer westPanel = new LayoutContainer();
		westPanel.setLayout(new RowLayout(Orientation.HORIZONTAL));
		
		com.extjs.gxt.ui.client.widget.Label chantier = new com.extjs.gxt.ui.client.widget.Label(messages.accueilLink());
		chantier.setStyleName("actionHTML5");
		westPanel.add(chantier);

		path = new HTML("&nbsp;> Fiche ST : " + societe);
		path.setWidth("220px");
	/*	westPanel.add(path);*/
		add(westPanel, new BorderLayoutData(LayoutRegion.WEST, 390.0f));

		LayoutContainer eastPanel = new LayoutContainer();
		eastPanel.setLayout(new RowLayout(Orientation.HORIZONTAL));

		HTML saveFicheSt = new HTML("<img src='./images/sauvegarder.png'/> " + messages.saveForm());
		saveFicheSt.setStyleName("actionHTML");
		/*eastPanel.add(saveFicheSt);*/
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

	/*	eastPanel.add(printAll);*/
		add(eastPanel, new BorderLayoutData(LayoutRegion.EAST, 390.0f));

		LayoutContainer centerPanel = new LayoutContainer();
		centerPanel.setLayout(new RowLayout(Orientation.VERTICAL));

		HTML html = new HTML("<br>", true);
		centerPanel.add(html);

		Label pageTitle = new Label("Choix du chantier");
		centerPanel.add(pageTitle);
		pageTitle.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		setPadding(centerPanel);
		add(centerPanel, new BorderLayoutData(LayoutRegion.CENTER));
	
	}
	public void setSociete(String societe) {
		if( societe == null ) {
			societe = "";
		}

		path.setText("> Fiche ST : " + societe);
	}
}
