package com.structis.fichesst.client.util;

import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.structis.fichesst.client.ecran.AbstractEcran;
import com.structis.fichesst.client.message.Messages;
import com.structis.fichesst.client.panel.AbstractPanel;

public class GuiUtil {

	private static Messages messages = GWT.create(Messages.class);

	public static void setWhiteBackgroundColor(Component c) {
		c.setStyleAttribute("background-color", "white");
	}

	public static void showLoading(Component c) {
		if (c != null) {
			c.mask(messages.loading());
		}
	}

	public static void showSaving(Component c) {
		if (c != null) {
			c.mask(messages.saving());
		}
	}

	public static void setPadding(Component c) {
		// c.setStyleAttribute("padding-left", "10px");
		// c.setStyleAttribute("padding-right", "10px");
		// c.setStyleAttribute("padding-bottom", "10px");
	}

	public static void openNewWindow(String name, String url) {
		com.google.gwt.user.client.Window.open(url, name.replace(" ", "_"), "menubar=no," + "location=false," + "resizable=1," + "scrollbars=1,"
				+ "status=yes," + "dependent=true," + "height=940," + "width=1680");
	}

	public static String buildUrl(String navi) {
		String url = "http://" + Window.Location.getHost() + Window.Location.getPath() + Window.Location.getQueryString() + navi;
		return url;
	}

	public static void addTab(TabPanel tabPanel, String text, AbstractPanel contentPanel) {
		TabItem tabItem = new TabItem();
		tabItem.setText(text);
		tabItem.setClosable(true);
		tabItem.add(contentPanel);
		tabPanel.add(tabItem);
		tabPanel.setSelection(tabItem);

	}

	public static void addTab(TabPanel tabPanel, String text, AbstractEcran contentPanel) {
		TabItem tabItem = new TabItem();
		tabItem.setText(text);
		tabItem.setClosable(true);
		tabItem.add(contentPanel);
		tabPanel.add(tabItem);
		tabPanel.setSelection(tabItem);

	}

	public static void addTab(TabPanel tabPanel, String text, ContentPanel contentPanel) {
		TabItem tabItem = new TabItem();
		tabItem.setText(text);
		tabItem.setClosable(true);
		tabItem.add(contentPanel);
		tabPanel.add(tabItem);
		tabPanel.setSelection(tabItem);

	}

	public static void addTab(TabPanel tabPanel, String text, LayoutContainer contentPanel) {
		TabItem tabItem = new TabItem();

		tabItem.setText(text);
		tabItem.setClosable(true);
		tabItem.add(contentPanel);
		tabPanel.add(tabItem);
		tabPanel.setSelection(tabItem);
	}

	public static void gotoEcran(Widget widget) {
		RootPanel.get("appContent").clear();
		RootPanel.get("appContent").add(widget);
	}

	public static void showSuccessInfo() {
		Info.display("Information", messages.saveSuccess());
	}

	public static final native int getScreenWidth() /*-{
		return screen.availWidth;
	}-*/;

	public static final native int getScreenHeight() /*-{
		return screen.availHeight;
	}-*/;

}
