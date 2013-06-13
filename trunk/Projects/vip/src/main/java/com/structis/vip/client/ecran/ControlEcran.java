package com.structis.vip.client.ecran;

import java.util.List;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout.HBoxLayoutAlign;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Element;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.event.control.ControlFilterEvent;
import com.structis.vip.client.event.control.EditControleEvent;
import com.structis.vip.client.event.control.EditControleHandler;
import com.structis.vip.client.event.control.ListControleEvent;
import com.structis.vip.client.event.control.ListControleHandler;
import com.structis.vip.client.event.control.RefreshTreeEvent;
import com.structis.vip.client.event.control.RefreshTreeHandler;
import com.structis.vip.client.event.control.ViewControleEvent;
import com.structis.vip.client.event.control.ViewControleHandler;
import com.structis.vip.client.navigation.Action;
import com.structis.vip.client.navigation.NavigationEvent;
import com.structis.vip.client.panel.control.ControlGridPanel;
import com.structis.vip.client.panel.control.ControlLeftPanel;
import com.structis.vip.client.panel.control.ControlTopPanel;
import com.structis.vip.client.panel.control.FilterPanel;
import com.structis.vip.client.panel.control.NewControlFormPanel;
import com.structis.vip.client.panel.control.ViewControlPanel;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserRoleModel;

public class ControlEcran extends AbstractTabEcran implements EcranLoadable {

    private ControlTopPanel controlTopPanel = new ControlTopPanel(this.bus);
    private ContentPanel oUPathPanel = new ContentPanel();
    private ControlLeftPanel controlLeftPanel = new ControlLeftPanel(this.bus);
    private ControlGridPanel controlGridPanel = new ControlGridPanel(this.bus);

    private FilterPanel filterPanel;
    private Label contentPathLabel;
    private Label pathLabel;

    private LayoutContainer containerCenter = new LayoutContainer();
    private LayoutContainer gridFilterPanel = new LayoutContainer();

    private EntiteModel selectedEntiteModel;
    private PerimetreModel selectedPerimetreModel;

    /**
     * (non-Javadoc)
     * 
     * @see com.structis.vip.client.ecran.AbstractTabEcran#onRender(com.google.gwt.user.client.Element, int)
     */
    @Override
    public void onRender(Element parent, int index) {
        super.onRender(parent, index);

        this.filterPanel = new FilterPanel(this.bus);

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

        // delegationFormPanel.setHeight("50%");
        // delegationFormPanel.setWidth("100%");

        HBoxLayout layout = new HBoxLayout();
        layout.setPadding(new Padding(5));
        layout.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
        this.oUPathPanel.setLayout(layout);
        this.oUPathPanel.setBodyBorder(false);

        this.pathLabel = new Label(this.messages.delegationlabelpath());

        this.pathLabel.setStyleAttribute("padding-left", "5px");
        this.contentPathLabel = new Label();
        this.contentPathLabel.setId(ClientConstant.PATH_LABEL_ID);

        this.oUPathPanel.add(this.pathLabel, new HBoxLayoutData(new Margins(0, 0, 0, 0)));
        this.oUPathPanel.add(this.contentPathLabel, new HBoxLayoutData(new Margins(0, 0, 0, 5)));

        north.add(this.controlTopPanel, new RowData(1, -1, new Margins(0)));
        north.add(this.oUPathPanel, new RowData(1, 1, new Margins(0)));

        this.controlLeftPanel.setHeading(this.messages.delegationheaderperimetre());

        container.add(north, northData);
        container.add(this.controlLeftPanel, westData);
        this.gridFilterPanel = this.createGridPanel();
        this.containerCenter.setLayout(new FitLayout());
        this.containerCenter.add(this.gridFilterPanel);
        container.add(this.containerCenter, centerData);

        this.initTab(container, Action.ACTION_CONTROL);

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
        c.add(this.filterPanel);

        gridPanel.add(c, northDataSub); // add filter form
        gridPanel.add(this.controlGridPanel, centerDataSub); // add data grid

        return gridPanel;
    }

    private void addHandler() {
        // process events : create controles, list controles, view controles
        this.bus.addHandler(EditControleEvent.getType(), new EditControleHandler() {

            @Override
            public void onLoadAction(EditControleEvent event) {
                NewControlFormPanel newControlForm = new NewControlFormPanel(ControlEcran.this.bus);
                newControlForm.loadInitData();
                newControlForm.applyControlModel(event.getControlModel(), event.getPerimetreTreeModel());
                ControlEcran.this.newContent(newControlForm);
            }
        });
        this.bus.addHandler(ListControleEvent.getType(), new ListControleHandler() {

            @Override
            public void onLoadAction(ListControleEvent event) {
                if (event.getRefresh()) {
                    ControlEcran.this.controlGridPanel.refresh(event.getControlModel());
                }
                ControlEcran.this.newContent(ControlEcran.this.gridFilterPanel);
            }
        });
        this.bus.addHandler(ViewControleEvent.getType(), new ViewControleHandler() {

            @Override
            public void onLoadAction(ViewControleEvent event) {
                ViewControlPanel viewControlForm = new ViewControlPanel(ControlEcran.this.bus);
                viewControlForm.loadInitData();
                viewControlForm.applyControlModel(event.getControlModel(), event.getPerimetreTreeModel());
                ControlEcran.this.newContent(viewControlForm);
            }
        });
        this.bus.addHandler(RefreshTreeEvent.getType(), new RefreshTreeHandler() {

            @Override
            public void onLoadAction(RefreshTreeEvent event) {
                ControlEcran.this.disableEvents(true);
                ControlEcran.this.selectedPerimetreModel = event.getPerimetreModel();
                String path = event.getPerimetreModel().getName();
                if (!path.equals("")) {
                    path = " UO > " + path;
                    ControlEcran.this.contentPathLabel.setText(path);
                }
                // refresh main area

                ControlFilterEvent controlFilterEvent = new ControlFilterEvent();
                // TreePanel<PerimetreTreeModel> p = (TreePanel<PerimetreTreeModel>)AppUtil.getPanel(ConstantClient.CONTROL_TREE_ID);
                PerimetreTreeModel treeModel = new PerimetreTreeModel(event.getPerimetreModel(), SessionServiceImpl.getInstance().getUserContext()
                        .getUserRoles());
                // add BYTP
                treeModel.setEntiteId(SessionServiceImpl.getInstance().getUserContext().getEntite().getEntId());
                // treeModel.setEntiteId(ConstantClient.ENTITE_ID_IS_BYEFE);
                treeModel.setLevel(0);
                treeModel.setPath(event.getPerimetreModel().getName());
                treeModel.setIsEntite(false);

                treeModel.setName(SafeHtmlUtils.htmlEscape(treeModel.getName()));
                // 27 Nov
                List<UserRoleModel> roles = SessionServiceImpl.getInstance().getUserContext().getUserRoles();
                for (UserRoleModel r : roles) {
                    treeModel.setPermissionByRole(r.getRole());
                }

                controlFilterEvent.setPerimetreTreeModel(treeModel);
                // controlFilterEvent.setPerimetreTreeModel(selectedPerimetreTreeModel);
                controlFilterEvent.setEntiteModel(SessionServiceImpl.getInstance().getEntiteContext());
                controlFilterEvent.setTypeModel(null);
                controlFilterEvent.setStartDate(null);
                controlFilterEvent.setEndDate(null);
                controlFilterEvent.setPageSize(50);

                // fire event delegation filter
                ControlEcran.this.bus.fireEvent(controlFilterEvent);

                // ContentEvent contentEvent = new ContentEvent();
                // contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GRID_DELEGATION_PANEL);
                // bus.fireEvent(contentEvent);

                ControlEcran.this.disableEvents(false);
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
        this.selectedEntiteModel = SessionServiceImpl.getInstance().getEntiteContext();
        this.selectedPerimetreModel = SessionServiceImpl.getInstance().getPerimetreContext();
        this.filterPanel.onLoadPanel();
        this.controlLeftPanel.onLoadPanel();
        this.controlTopPanel.onLoadPanel();
        RefreshTreeEvent e = new RefreshTreeEvent();
        e.setPerimetreModel(this.selectedPerimetreModel);
        e.setEntiteModel(this.selectedEntiteModel);
        this.bus.fireEvent(e);
        // if (event.getObject() instanceof DelegationListProjectEvent) {
        // bus.fireEvent((DelegationListProjectEvent) event.getObject());
        // }
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
