package com.structis.vip.client.ecran;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Frame;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.navigation.Action;
import com.structis.vip.client.navigation.NavigationEvent;
import com.structis.vip.client.panel.document.DocListPanel;

public class DocumentEcran extends AbstractTabEcran implements EcranLoadable {

	private SimpleEventBus bus = new SimpleEventBus();
	private final Messages messages = GWT.create(Messages.class);

	Frame frame;
	private LayoutContainer container = new LayoutContainer();

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		HorizontalPanel hpPanel;
		hpPanel = new HorizontalPanel();
		// set attributes
		hpPanel.setStyleAttribute("padding-top", "10px");
		hpPanel.setStyleAttribute("padding-left", "10px");
		hpPanel.setBorders(false);

		Label label = new Label(messages.reportchoose() + ":");
		label.setStyleAttribute("margin-left", "5px");	

//		LayoutContainer container = new LayoutContainer();
		container.setStyleAttribute("marginBottom", "10px");
		container.setLayout(new BorderLayout());

		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
		centerData.setMargins(new Margins(0, 0, 0, 0));
		centerData.setSplit(false);
		DocListPanel docPanel = new DocListPanel(bus);
		docPanel.loadPanel();
		docPanel.setDisplayButtons(false);
		container.add(docPanel, centerData);
		initTab(container, Action.ACTION_DOCUMENT);
		initData();					
	}

	private void initData() {		
	}

	public void onLoadApplication(NavigationEvent event) {
		resetTab();
//		initTab(container, Action.ACTION_DOCUMENT);
	}
}