package com.structis.fichesst.client.ecran;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import com.structis.fichesst.client.constant.ConstantClient;
import com.structis.fichesst.client.message.Messages;
import com.structis.fichesst.client.navigation.NavigationEvent;
import com.structis.fichesst.client.navigation.NavigationFactory;
import com.structis.fichesst.client.navigation.NavigationService;
import com.structis.fichesst.client.util.GuiUtil;
import com.structis.fichesst.client.util.NameValuePair;
import com.structis.fichesst.client.util.ReportUtil;
import com.structis.fichesst.shared.util.Constants;

public abstract class AbstractEcran extends LayoutContainer implements EcranLoadable {

	private static final int MIN_WIDTH = Constants.MIN_WIDTH;

	protected static Messages messages = GWT.create(Messages.class);

	protected static final RootPanel ROOTPANEL = RootPanel.get("appContent");

	protected static final String DEFAULT_BACKGROUD_COLOR = Constants.DEFAULT_BACKGROUD_COLOR;

	protected Viewport viewPort = new Viewport();

	protected SimpleEventBus bus = new SimpleEventBus();

	protected LayoutContainer rootPanel;
	protected TabPanel tabPanel;
	protected NavigationService navigation = NavigationFactory.getNavigation();

	protected AbstractEcran() {
		setWidth(GuiUtil.getScreenWidth() - 30);
		setLayout(new FitLayout());
		setStyleAttribute("padding-left", "5px");
		// viewPort.setLayout(new FitLayout());
		// add(viewPort);
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setBorders(false);
	}

	@Override
	public void onLoadApplication(NavigationEvent event) {
	}

	protected void addRootPanel(LayoutContainer rootPanel) {
		viewPort.add(rootPanel);
	}

	protected void addTabpanel(TabPanel tabPanel) {
		viewPort.add(tabPanel);
	}

	protected static void setWhiteBackgroundColor(Component c) {
		GuiUtil.setWhiteBackgroundColor(c);
	}

	protected static void setPadding(Component c) {
		GuiUtil.setPadding(c);
	}

	protected void setDefaultBackgroundColor() {
		setBackgroundColor(this, DEFAULT_BACKGROUD_COLOR);
	}

	protected static void setDefaultBackgroundColor(Component c) {
		setBackgroundColor(c, DEFAULT_BACKGROUD_COLOR);
	}

	protected void setBackgroundColor(String color) {
		setBackgroundColor(this, color);
	}

	protected static void setBackgroundColor(Component c, String color) {
		c.setStyleAttribute("background-color", color);
	}

	protected static void showLoading(Component c) {
		GuiUtil.showLoading(c);
	}

	protected static void showSaving(Component c) {
		GuiUtil.showSaving(c);
	}

	protected void setScrolling() {
		if (rootPanel != null) {
			int width = RootPanel.get().getOffsetWidth();

			if (width < MIN_WIDTH) {
				rootPanel.setScrollMode(Scroll.AUTO);
			} else {
				rootPanel.setScrollMode(Scroll.AUTOY);
			}

			com.google.gwt.user.client.Window.addResizeHandler(new ResizeHandler() {
				@Override
				public void onResize(ResizeEvent arg0) {
					if (RootPanel.get().getOffsetWidth() < MIN_WIDTH) {
						// com.google.gwt.user.client.Window.enableScrolling(true);
						rootPanel.setScrollMode(Scroll.AUTO);
					} else {
						rootPanel.setScrollMode(Scroll.AUTOY);
					}
				}
			});
		}
	}

	protected void showDownloadDialog(String fileName) {
		String exportUrl = GWT.getHostPageBaseURL() + ConstantClient.FILE_DOWNLOAD_SERVLET_NAME;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new NameValuePair("fileName", fileName));
		ReportUtil.showReport(exportUrl, params.toArray(new NameValuePair[params.size()]));
	}
}
