package com.structis.vip.client.ecran;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.navigation.NavigationEvent;
import com.structis.vip.client.panel.AdministrationTabPanel;
import com.structis.vip.client.panel.AdministrationTreePanel;

public class AdministrationEcran extends Viewport implements EcranLoadable {

    private SimpleEventBus bus = new SimpleEventBus();

    private AdministrationTreePanel treePanel = new AdministrationTreePanel(this.bus);
    private AdministrationTabPanel tabPanel = new AdministrationTabPanel(this.bus);

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        this.setLayout(new BorderLayout());

        BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 180);
        westData.setMargins(new Margins(0, 5, 0, 2));
        westData.setCollapsible(true);
        westData.setSplit(true);

        BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0, 0, 0, 0));
        westData.setCollapsible(true);
        centerData.setSplit(true);

        this.add(this.treePanel, westData);
        this.add(this.tabPanel, centerData);
        this.add(new Label(""), new BorderLayoutData(LayoutRegion.SOUTH, 60));

        this.addHandler();
    }

    private void addHandler() {
    }

    @Override
    public void onLoadApplication(NavigationEvent event) {
        if (event.getObject() instanceof DelegationListProjectEvent) {
            this.bus.fireEvent((DelegationListProjectEvent) event.getObject());
        }
    }
}
