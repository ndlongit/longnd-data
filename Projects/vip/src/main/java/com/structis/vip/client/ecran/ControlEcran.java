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
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Element;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.control.ControlFilterEvent;
import com.structis.vip.client.event.control.EditControleEvent;
import com.structis.vip.client.event.control.EditControleHandler;
import com.structis.vip.client.event.control.ListControleEvent;
import com.structis.vip.client.event.control.ListControleHandler;
import com.structis.vip.client.event.control.RefreshTreeEvent;
import com.structis.vip.client.event.control.RefreshTreeHandler;
import com.structis.vip.client.event.control.ViewControleEvent;
import com.structis.vip.client.event.control.ViewControleHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.navigation.Action;
import com.structis.vip.client.navigation.NavigationEvent;
import com.structis.vip.client.panel.control.ControlGridPanel;
import com.structis.vip.client.panel.control.ControlLeftPanel;
import com.structis.vip.client.panel.control.ControlTopPanel;
import com.structis.vip.client.panel.control.FilterPanel;
import com.structis.vip.client.panel.control.NewControlFormPanel;
import com.structis.vip.client.panel.control.ViewControlPanel;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserRoleModel;

public class ControlEcran extends AbstractTabEcran implements EcranLoadable {
	private SimpleEventBus bus = new SimpleEventBus();
	private ControlTopPanel controlTopPanel = new ControlTopPanel(bus);
	private ContentPanel oUPathPanel = new ContentPanel();
	private ControlLeftPanel controlLeftPanel = new ControlLeftPanel(bus);
	private ControlGridPanel controlGridPanel = new ControlGridPanel(bus);
	
	//private DetailDelegationFormPanel detailDelegationForm = new DetailDelegationFormPanel(bus);
	
	private FilterPanel filterPanel;
	private final Messages messages = GWT.create(Messages.class);
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
	public void onRender(Element parent, int index) {
		super.onRender(parent, index);

		filterPanel = new FilterPanel(bus);

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

		oUPathPanel.setHeight("50%");
		oUPathPanel.setHeaderVisible(false);
		oUPathPanel.setBodyBorder(false);
		oUPathPanel.setStyleAttribute("border-top", "1px solid #99BBE8");

//		delegationFormPanel.setHeight("50%");
//		delegationFormPanel.setWidth("100%");

		HBoxLayout layout = new HBoxLayout();
		layout.setPadding(new Padding(5));
		layout.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
		oUPathPanel.setLayout(layout);
		oUPathPanel.setBodyBorder(false);

		pathLabel = new Label(messages.delegationlabelpath());
		
		pathLabel.setStyleAttribute("padding-left", "5px");
		contentPathLabel = new Label();
		contentPathLabel.setId(ConstantClient.PATH_LABEL_ID);

		oUPathPanel.add(pathLabel, new HBoxLayoutData(new Margins(0, 0, 0, 0)));		
		oUPathPanel.add(contentPathLabel, new HBoxLayoutData(new Margins(0, 0, 0, 5)));

		north.add(controlTopPanel, new RowData(1, -1, new Margins(0)));
		north.add(oUPathPanel, new RowData(1, 1, new Margins(0)));

		controlLeftPanel.setHeading(messages.delegationheaderperimetre());

		container.add(north, northData);
		container.add(controlLeftPanel, westData);
		gridFilterPanel = createGridPanel();
		containerCenter.setLayout(new FitLayout());
		containerCenter.add(gridFilterPanel); 		
		container.add(containerCenter, centerData);

		initTab(container, Action.ACTION_CONTROL);

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
		c.setHeading(messages.delegationheadersuivants());
		c.add(filterPanel);
		
		gridPanel.add(c, northDataSub); // add filter form
		gridPanel.add(controlGridPanel, centerDataSub); // add data grid

		return gridPanel;
	}

	private void addHandler() {
		// process events : create controles, list controles, view controles
		bus.addHandler(EditControleEvent.getType(), new EditControleHandler() {
			public void onLoadAction(EditControleEvent event) {
				NewControlFormPanel newControlForm = new NewControlFormPanel(bus);				
				newControlForm.loadInitData();
				newControlForm.applyControlModel(event.getControlModel(), event.getPerimetreTreeModel());
				newContent(newControlForm);				
			}
		});
		bus.addHandler(ListControleEvent.getType(), new ListControleHandler() {
			public void onLoadAction(ListControleEvent event) {	
				if (event.getRefresh()) {
					controlGridPanel.refresh(event.getControlModel());
				}
				newContent(gridFilterPanel);				
			}
		});
		bus.addHandler(ViewControleEvent.getType(), new ViewControleHandler() {
			public void onLoadAction(ViewControleEvent event) {
				ViewControlPanel viewControlForm = new ViewControlPanel(bus);				
				viewControlForm.loadInitData();
				viewControlForm.applyControlModel(event.getControlModel(), event.getPerimetreTreeModel());
				newContent(viewControlForm);				
			}
		});
		bus.addHandler(RefreshTreeEvent.getType(), new RefreshTreeHandler() {
			public void onLoadAction(RefreshTreeEvent event) {				
				disableEvents(true);
				selectedPerimetreModel = event.getPerimetreModel();
				String path = event.getPerimetreModel().getName();
				if (!path.equals("")) {
					path = " UO > " + path;
					contentPathLabel.setText(path);
				}
				// refresh main area				
				
				ControlFilterEvent controlFilterEvent = new ControlFilterEvent();
//				TreePanel<PerimetreTreeModel> p = (TreePanel<PerimetreTreeModel>)AppUtil.getPanel(ConstantClient.CONTROL_TREE_ID);
				PerimetreTreeModel treeModel = new PerimetreTreeModel(event.getPerimetreModel(), SessionServiceImpl.getInstance().getUserContext().getUserRoles());
				//add BYTP
				treeModel.setEntiteId(SessionServiceImpl.getInstance().getUserContext().getEntite().getEntId());				
				//treeModel.setEntiteId(ConstantClient.ENTITE_ID_IS_BYEFE);
				treeModel.setLevel(0);
				treeModel.setPath(event.getPerimetreModel().getName());
				treeModel.setIsEntite(false);
				
				treeModel.setName(SafeHtmlUtils.htmlEscape(treeModel.getName()));
				// 27 Nov
				List<UserRoleModel> roles = SessionServiceImpl.getInstance().getUserContext().getUserRoles();
				for (UserRoleModel r: roles) { 
					treeModel.setPermissionByRole(r.getRole());
				}
				
				
				controlFilterEvent.setPerimetreTreeModel(treeModel);
				//controlFilterEvent.setPerimetreTreeModel(selectedPerimetreTreeModel);
				controlFilterEvent.setEntiteModel(SessionServiceImpl.getInstance().getEntiteContext());
				controlFilterEvent.setTypeModel(null);
				controlFilterEvent.setStartDate(null);
				controlFilterEvent.setEndDate(null);
				controlFilterEvent.setPageSize(50);
				
				// fire event delegation filter
				bus.fireEvent(controlFilterEvent);
				
//				ContentEvent contentEvent = new ContentEvent();
//				contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GRID_DELEGATION_PANEL);
//				bus.fireEvent(contentEvent);
				
				disableEvents(false);
			}
		});
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.structis.vip.client.ecran.EcranLoadable#onLoadApplication(com.structis.vip.client.navigation.NavigationEvent)
	 */
	public void onLoadApplication(NavigationEvent event) {
		resetTab();
		selectedEntiteModel = SessionServiceImpl.getInstance().getEntiteContext();
		selectedPerimetreModel = SessionServiceImpl.getInstance().getPerimetreContext();		
		filterPanel.onLoadPanel();
		controlLeftPanel.onLoadPanel();
		controlTopPanel.onLoadPanel();
		RefreshTreeEvent e = new RefreshTreeEvent();
		e.setPerimetreModel(selectedPerimetreModel);
		e.setEntiteModel(selectedEntiteModel);
		bus.fireEvent(e);
//		if (event.getObject() instanceof DelegationListProjectEvent) {
//			bus.fireEvent((DelegationListProjectEvent) event.getObject());
//		}
	}

	private boolean newContent(LayoutContainer content) {
		BorderLayoutData centerDataSub = new BorderLayoutData(LayoutRegion.CENTER);
		centerDataSub.setMargins(new Margins(0, 0, 0, 0));
		centerDataSub.setSplit(false);

		containerCenter.removeAll();
		containerCenter.setLayout(new BorderLayout());
		containerCenter.add(content, centerDataSub);
		return containerCenter.layout();
	}
	
}
