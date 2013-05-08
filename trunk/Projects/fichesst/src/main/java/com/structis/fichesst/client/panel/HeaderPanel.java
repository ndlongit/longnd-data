package com.structis.fichesst.client.panel;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.structis.fichesst.client.ecran.AcceuilEcran;
import com.structis.fichesst.client.ecran.AdminEcran;
import com.structis.fichesst.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.fichesst.client.navigation.NavigationFactory;
import com.structis.fichesst.client.navigation.NavigationService;
import com.structis.fichesst.client.service.ClientPropertiesServiceAsync;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.shared.dto.UtilisateurGrpModel;
import com.structis.fichesst.shared.util.Constants;

public class HeaderPanel extends AbstractPanel {

    Html acceuilLink;

    Html adminLink;

    HTML banner;

    LayoutContainer leftPanel;

    HTML logo;

    LayoutContainer mainPanel;

    private final NavigationService navigation = NavigationFactory.getNavigation();

    private Label nomLabel;

    private HTML version;
    Hyperlink welcomLink;

    public HeaderPanel() {
	loadPanel();

    }

    public void loadPanel() {
	nomLabel = new Label(messages.hello());
	nomLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
	acceuilLink = new Html(messages.welcome());
	acceuilLink.setStyleName("actionHTML");
	acceuilLink.setVisible(false);
	adminLink = new Html(messages.adminstration());
	adminLink.setStyleName("actionHTML");
	adminLink.setVisible(false);
	adminLink.setStyleAttribute("visibility", "hidden");
	if (navigation.getContext().getUserModel() != null) {
	    UtilisateurGrpModel user = navigation.getContext().getUserModel();
	    String username = user.getIdentifiant();
	    if (user.getBadmin() == null) {
		adminLink.setVisible(false);
	    }
	    if (user.getBadmin() != null && user.getBadmin() == false) {
		adminLink.setVisible(false);
	    }
	    if (user.getBadmin() != null && user.getBadmin() == true) {
		adminLink.setVisible(true);
		adminLink.setStyleAttribute("visibility", "visible");
	    }

	    if (user != null && user.getIdentifiant() != null) {
		nomLabel.setText(messages.hello() + Constants.SPACE + username.substring(username.lastIndexOf("\\") + 1, username.length()));
		acceuilLink.setVisible(true);
		if (user.getBadmin() == null || user.getBadmin() == false) {
		    adminLink.setVisible(false);
		}
	    } else {
		nomLabel.setText(messages.hello());
	    }
	}

    }

    @Override
    protected void onRender(Element parent, int index) {
	super.onRender(parent, index);
	this.setBorders(false);
	setWhiteBackgroundColor(this);
	mainPanel = new LayoutContainer();
	mainPanel.setMonitorWindowResize(true);
	mainPanel.setBorders(true);
	setLayout(new BorderLayout());
	mainPanel.setLayout(new BorderLayout());
	HTML newLine = new HTML("<br>", true);
	add(newLine, new BorderLayoutData(LayoutRegion.NORTH, 20.0f));
	leftPanel = new LayoutContainer();
	setPadding(leftPanel);
	mainPanel.add(leftPanel, new BorderLayoutData(LayoutRegion.WEST, 110.0f));
	leftPanel.setLayout(new RowLayout(Orientation.VERTICAL));
	logo = new HTML("<img src='./images/logo.gif'/>");
	leftPanel.add(logo);
	LayoutContainer rightPanel = new LayoutContainer();

	mainPanel.add(rightPanel, new BorderLayoutData(LayoutRegion.EAST, 200));
	rightPanel.setLayout(new VBoxLayout());
	rightPanel.add(nomLabel);
	LayoutContainer linksPanel = new LayoutContainer();
	// linksPanel.setWidth("100%");
	TableLayout tl_linksPanel = new TableLayout(2);
	tl_linksPanel.setWidth("100%");
	// tl_linksPanel.setCellPadding(7);
	tl_linksPanel.setCellHorizontalAlign(HorizontalAlignment.LEFT);
	tl_linksPanel.setTableStyle("padding-left:45px");
	linksPanel.setLayout(tl_linksPanel);

	acceuilLink.addListener(Events.OnClick, new Listener<BaseEvent>() {
	    @Override
	    public void handleEvent(BaseEvent be) {
		GuiUtil.gotoEcran(new AcceuilEcran());
	    }
	});
	linksPanel.add(acceuilLink, new TableData(HorizontalAlignment.RIGHT, VerticalAlignment.BOTTOM));

	adminLink.addListener(Events.OnClick, new Listener<BaseEvent>() {
	    @Override
	    public void handleEvent(BaseEvent be) {
		GuiUtil.gotoEcran(new AdminEcran());
	    }
	});
	linksPanel.add(adminLink, new TableData(HorizontalAlignment.RIGHT, VerticalAlignment.BOTTOM));

	version = new HTML("", false);
	version.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
	version.setWordWrap(false);
	ClientPropertiesServiceAsync.Util.getInstance().getVersionInfo(new AsyncCallbackWithErrorResolution<String>() {
	    @Override
	    public void onSuccess(String result) {
		version.setHTML("<font size='1'>" + result + "</font>");
	    }
	});

	// linksPanel.add(version);
	rightPanel.add(linksPanel);
	LayoutContainer layoutBanner = new LayoutContainer();
	LayoutContainer layoutVersion = new LayoutContainer();
	layoutVersion.setLayout(new RowLayout(Orientation.HORIZONTAL));
	layoutVersion.add(version, new RowData());
	layoutVersion.setHeight(20);
	layoutVersion.setWidth(50);
	layoutVersion.setStyleAttribute("padding-bottom", "25px");
	layoutBanner.setLayout(new TableLayout(2));
	banner = new HTML("<img src='./images/banner-first.png'/>");
	banner.setStyleName("actionHTML");
	banner.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		GuiUtil.gotoEcran(new AcceuilEcran());
	    }
	});
	layoutBanner.add(banner, new TableData());
	layoutBanner.add(layoutVersion, new TableData(HorizontalAlignment.LEFT, VerticalAlignment.BOTTOM));
	mainPanel.add(layoutBanner, new BorderLayoutData(LayoutRegion.CENTER));
	add(mainPanel, new BorderLayoutData(LayoutRegion.CENTER));
	mainPanel.setStyleAttribute("border-color", "black");
	setPadding(this);
	mainPanel.setId("headerPanel");
	rightPanel.setId("eastPanel");
	setDefaultBackgroundColor(mainPanel);
    }

}
