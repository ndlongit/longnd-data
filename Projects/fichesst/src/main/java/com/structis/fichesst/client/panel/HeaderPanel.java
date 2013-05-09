package com.structis.fichesst.client.panel;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout.VBoxLayoutAlign;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
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

	private Html acceuilLink;

	private Html adminLink;

	private HTML banner;

	private LayoutContainer leftPanel;

	private HTML logo;

	private LayoutContainer mainPanel;

	private final NavigationService navigation = NavigationFactory.getNavigation();

	private Label nomLabel;

	private HTML version;

	public HeaderPanel() {
		this.setHeight(80);
		loadPanel();
	}

	public void loadPanel() {
		setWidth(GuiUtil.getScreenWidth() - 30);
		nomLabel = new Label(messages.hello(), false);
		nomLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		acceuilLink = new Html(messages.welcome());
		acceuilLink.setStyleName(ACTION_HTML);
		acceuilLink.setVisible(false);
		adminLink = new Html(messages.adminstration());
		adminLink.setStyleName(ACTION_HTML);
		adminLink.setVisible(false);
		adminLink.setStyleAttribute("visibility", "hidden");
		UtilisateurGrpModel user = navigation.getContext().getUserModel();
		if( user != null ) {
			String username = user.getIdentifiant();

			if( Boolean.TRUE.equals(user.getBadmin()) ) {
				adminLink.setVisible(true);
				adminLink.setStyleAttribute("visibility", "visible");
			}
			else {
				adminLink.setVisible(false);
			}

			if( user.getIdentifiant() != null ) {
				nomLabel.setText(messages.hello() + Constants.SPACE + username.substring(
						username.lastIndexOf("\\") + 1, username.length()));
				acceuilLink.setVisible(true);
			}
			else {
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
		mainPanel.add(leftPanel, new BorderLayoutData(LayoutRegion.WEST, 110.0f));
		leftPanel.setLayout(new RowLayout(Orientation.VERTICAL));
		logo = new HTML("<img src='./images/logo.gif'/>");
		leftPanel.add(logo);
		LayoutContainer rightPanel = new LayoutContainer();

		mainPanel.add(rightPanel, new BorderLayoutData(LayoutRegion.EAST, 500));
		VBoxLayout vBoxLayout = new VBoxLayout();
		vBoxLayout.setPadding(new Padding(PADDING));
        vBoxLayout.setVBoxLayoutAlign(VBoxLayoutAlign.RIGHT);
		rightPanel.setLayout(vBoxLayout);
		rightPanel.add(nomLabel);
		LayoutContainer linksPanel = new LayoutContainer();
		TableLayout tl_linksPanel = new TableLayout(3);
		tl_linksPanel.setWidth("100%");
		tl_linksPanel.setCellHorizontalAlign(HorizontalAlignment.LEFT);
		linksPanel.setLayout(tl_linksPanel);

		acceuilLink.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				History.newItem("acceuil");
				GuiUtil.gotoEcran(new AcceuilEcran());
			}
		});
		linksPanel.add(acceuilLink, new TableData(HorizontalAlignment.RIGHT, VerticalAlignment.BOTTOM));
		adminLink.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				History.newItem("admin");
				GuiUtil.gotoEcran(new AdminEcran());
			}
		});

		linksPanel.add(new Html(LINKS_SPACE));
		
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
		banner.setStyleName(ACTION_HTML);
		banner.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				History.newItem("acceuil");
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
