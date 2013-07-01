package com.structis.vip.client.ecran;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout.HBoxLayoutAlign;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.ContentEventHandler;
import com.structis.vip.client.event.DelegationEvent;
import com.structis.vip.client.event.DelegationFilterEvent;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.DelegationTreeEvent;
import com.structis.vip.client.event.DelegationTreeHandler;
import com.structis.vip.client.navigation.Action;
import com.structis.vip.client.navigation.NavigationEvent;
import com.structis.vip.client.panel.DelegationCenterFormPanel;
import com.structis.vip.client.panel.DelegationCenterGridPanel;
import com.structis.vip.client.panel.DelegationFormPanel;
import com.structis.vip.client.panel.DelegationLeftPanel;
import com.structis.vip.client.panel.DetailDelegationFormPanel;
import com.structis.vip.client.panel.NewDelegationFormPanel;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;

public class DelegationEcran extends AbstractTabEcran implements EcranLoadable {

    private DelegationFormPanel delegationFormPanel = new DelegationFormPanel(this.bus);
    private ContentPanel oUPathPanel = new ContentPanel();
    private DelegationLeftPanel delegationLeftPanel = new DelegationLeftPanel(this.bus);
    private DelegationCenterGridPanel delegationCenterGridPanel = new DelegationCenterGridPanel(this.bus);
    private NewDelegationFormPanel newDelegationForm = new NewDelegationFormPanel(this.bus);
    private DetailDelegationFormPanel detailDelegationForm = new DetailDelegationFormPanel(this.bus);
    private DelegationCenterFormPanel delegationCenterFormPanel;
    private Label contentPathLabel;
    private Label pathLabel;

    private DelegationFilterEvent delegationFilterEvent = new DelegationFilterEvent();

    private LayoutContainer containerCenter = new LayoutContainer();

    /**
     * (non-Javadoc)
     * 
     * @see com.structis.vip.client.ecran.AbstractTabEcran#onRender(com.google.gwt.user.client.Element, int)
     */
    @Override
    public void onRender(Element parent, int index) {
        super.onRender(parent, index);

        this.delegationCenterFormPanel = new DelegationCenterFormPanel(this.bus, this.delegationFilterEvent);

        LayoutContainer container = new LayoutContainer();
        final BorderLayout layout1 = new BorderLayout();
        container.setLayout(layout1);

        BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH, 70);
        northData.setHideCollapseTool(true);

        BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 190, 190, 400);
        westData.setMargins(new Margins(0, 5, 0, 0));
        westData.setCollapsible(true);
        westData.setSplit(true);

        BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
        centerData.setMargins(new Margins(0, 0, 0, 0));
        centerData.setSplit(false);

        ContentPanel north = new ContentPanel();
        north.setHeaderVisible(false);
        north.setBodyBorder(false);
        north.setStyleAttribute("border-left", "1px solid #99BBE8");
        north.setStyleAttribute("border-right", "1px solid #99BBE8");
        north.setHeight("20%");
        north.setWidth("100%");
        north.setLayout(new RowLayout(Orientation.VERTICAL));

        this.oUPathPanel.setHeight("50%");
        this.oUPathPanel.setHeaderVisible(false);
        this.oUPathPanel.setBodyBorder(false);
        this.oUPathPanel.setStyleAttribute("border-top", "1px solid #99BBE8");

        this.delegationFormPanel.setHeight("50%");
        this.delegationFormPanel.setWidth("100%");

        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(5));
        layout.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
        this.oUPathPanel.setLayout(layout);
        this.oUPathPanel.setBodyBorder(false);

        this.pathLabel = new Label(this.messages.delegationlabelpath());
        this.pathLabel.setStyleAttribute("padding-left", "5px");
        this.contentPathLabel = new Label();

        this.oUPathPanel.add(this.pathLabel, new HBoxLayoutData(new Margins(0, 0, 0, 0)));
        this.oUPathPanel.add(this.contentPathLabel, new HBoxLayoutData(new Margins(0, 0, 0, 5)));

        north.add(this.delegationFormPanel, new RowData(1, -1, new Margins(0)));
        north.add(this.oUPathPanel, new RowData(1, 1, new Margins(0)));

        this.delegationLeftPanel.setHeading(this.messages.delegationheaderperimetre());

        container.add(north, northData);
        container.add(this.delegationLeftPanel, westData);

        this.containerCenter = this.createGridPanel();

        container.add(this.containerCenter, centerData);

        this.initTab(container, Action.ACTION_DELEGATION);

        this.addHandler();
    }

    /**
     * Create grid panel which contains filter panel and data grid panl
     * 
     * @return layoutContainer
     */
    private LayoutContainer createGridPanel() {
        LayoutContainer gridPanel = new LayoutContainer();
        gridPanel.setLayout(new BorderLayout());

        BorderLayoutData northDataSub = new BorderLayoutData(LayoutRegion.NORTH);
        northDataSub.setSplit(false);
        northDataSub.setCollapsible(true);
        northDataSub.setMargins(new Margins(0, 0, 0, 0));

        BorderLayoutData centerDataSub = new BorderLayoutData(LayoutRegion.CENTER);
        centerDataSub.setMargins(new Margins(0, 0, 0, 0));
        centerDataSub.setSplit(false);

        // filter form
        final ContentPanel c = new ContentPanel();
        c.setHeading(this.messages.delegationheadersuivants());
        c.add(this.delegationCenterFormPanel);

        gridPanel.add(c, northDataSub); // add filter form
        gridPanel.add(this.delegationCenterGridPanel, centerDataSub); // add data grid

        return gridPanel;
    }

    private void addHandler() {
        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(DelegationListProjectEvent event) {
                disableEvents(true);
                String path = event.getPerimetreModel().getName();
                if (!path.equals("")) {
                    path = " UO > " + path;
                    contentPathLabel.setText(path);
                }

                ContentEvent contentEvent = new ContentEvent();
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GRID_DELEGATION_PANEL);
                bus.fireEvent(contentEvent);

                disableEvents(false);
            }
        });

        this.bus.addHandler(DelegationTreeEvent.getType(), new DelegationTreeHandler() {

            @Override
            public void onLoadAction(DelegationTreeEvent event) {
                disableEvents(true);
                ModelData treeModel = event.getTreeModel();
                String path = "";

                if (treeModel != null) {
                    path = treeModel.get("path") == null ? "" : treeModel.get("path").toString();
                }
                path = " UO > " + path;
                contentPathLabel.setText(path);

                disableEvents(false);
            }
        });

        this.bus.addHandler(ContentEvent.getType(), new ContentEventHandler() {

            @Override
            public void onLoadAction(ContentEvent event) {
                disableEvents(true);

                switch (event.getMode()) {
                case ContentEvent.CHANGE_MODE_TO_NEW_DELEGATION_FORM:
                    if (event.getEvent() instanceof DelegationEvent) {
                        if (newContent(newDelegationForm)) {
                            bus.fireEvent(event.getEvent());
                            delegationFormPanel.setEnableForm(false);
                            delegationLeftPanel.getTreePanel().setEnabled(false);
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_DETAIL_DELEGATION_FORM:
                    if (event.getEvent() instanceof DelegationEvent) {
                        if (newContent(detailDelegationForm)) {
                            bus.fireEvent(event.getEvent());
                            delegationFormPanel.setEnableForm(false);
                            delegationLeftPanel.getTreePanel().setEnabled(false);
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_GRID_DELEGATION_PANEL:
                    if (newContent(createGridPanel())) {
                        delegationLeftPanel.getTreePanel().setEnabled(true);
                        delegationFormPanel.setEnableForm(true);
                        if (event.isReload()) {
                            delegationCenterFormPanel.getFilterButton().fireEvent(Events.Select);
                        }
                    }
                    break;
                }

                disableEvents(false);
            }
        });
    }

    /**
     * (non-Javadoc)
     * 
     * @see com.structis.vip.client.ecran.EcranLoadable#onLoadApplication(com.structis.vip.client.navigation.NavigationEvent)
     */
    @Override
    public void onLoadApplication(NavigationEvent event) {
        this.resetTab();
        if (event.getObject() instanceof DelegationListProjectEvent) {
            this.bus.fireEvent((DelegationListProjectEvent) event.getObject());
        } else {
            EntiteModel selectedEntiteModel = SessionServiceImpl.getInstance().getEntiteContext();
            PerimetreModel selectedPerimetreModel = SessionServiceImpl.getInstance().getPerimetreContext();
            DelegationListProjectEvent e = new DelegationListProjectEvent(selectedEntiteModel, selectedPerimetreModel);
            this.bus.fireEvent(e);
        }
    }

    private boolean newContent(LayoutContainer content) {
        BorderLayoutData centerDataSub = new BorderLayoutData(LayoutRegion.CENTER);
        centerDataSub.setMargins(new Margins(0, 0, 0, 0));
        centerDataSub.setSplit(false);

        this.containerCenter.removeAll();
        this.containerCenter.setLayout(new BorderLayout());
        this.containerCenter.add(content, centerDataSub);
        return this.containerCenter.layout();
    }
}
