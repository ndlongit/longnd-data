package com.structis.vip.client.ecran;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.navigation.NavigationEvent;
import com.structis.vip.client.panel.LoginPanel;

public class LoginEcran extends  LayoutContainer implements EcranLoadable {
	private SimpleEventBus bus = new SimpleEventBus();


	@Override
	protected void onRender(Element parent, int index) {		
		super.onRender(parent, index);		
		setBorders(false);
		LayoutContainer container = new LayoutContainer();
		container.setLayout(new CenterLayout());
		
		LoginPanel panel = new LoginPanel(bus);		
		container.add(panel);

		Viewport viewport = new Viewport();
		final BorderLayout layout = new BorderLayout();
		viewport.setLayout(layout);
		viewport.setStyleAttribute("padding", "0px");
		viewport.setBorders(true);
		viewport.add(new Label(""), new BorderLayoutData(LayoutRegion.SOUTH, 150));
		viewport.setStyleAttribute("background", "white");
		viewport.add(container, new BorderLayoutData(LayoutRegion.CENTER));
		add(viewport);
		
		this.addHandler();
	}

	private void addHandler() {
	}
	
	public void onLoadApplication(NavigationEvent event) {
		if (event.getObject() instanceof DelegationListProjectEvent) {
			bus.fireEvent((DelegationListProjectEvent) event.getObject());
		}
	}
}
