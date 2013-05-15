package com.structis.vip.client.panel.control;

import java.util.List;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ComponentManager;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.control.ControlFilterEvent;
import com.structis.vip.client.event.control.RefreshTreeEvent;
import com.structis.vip.client.event.control.RefreshTreeHandler;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserRoleModel;

public class ControlLeftPanel extends ContentPanel {

	private TreeLoader<PerimetreTreeModel> loader;
	private TreeStore<PerimetreTreeModel> store = new TreeStore<PerimetreTreeModel>();
	private TreePanel<PerimetreTreeModel> tree = new TreePanel<PerimetreTreeModel>(store);

	private ClientPerimetreServiceAsync clientPerimetreService = ClientPerimetreServiceAsync.Util.getInstance();

	private Label titleTreeLabel;
	private SimpleEventBus bus;
	private EntiteModel selectedEntiteModel;
	private PerimetreModel selectedPerimetreModel;
	private PerimetreTreeModel selectedPerimetreTreeModel;
	private LayoutContainer top;

	public ControlLeftPanel(SimpleEventBus bus) {
		this.bus = bus;
		setId(ConstantClient.CONTROL_TREE_PANEL_ID);
		setStyleAttribute("paddingBottom", "0px");
		setBodyBorder(true);
		setScrollMode(Scroll.AUTO);
	}

	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);

		buildPanel();
	}

	private void buildPanel() {
		top = new LayoutContainer();
		titleTreeLabel = new Label("");
		titleTreeLabel.setStyleAttribute("marginLeft", "20px");
		titleTreeLabel.setStyleAttribute("text-align", "center");

		top.setStyleAttribute("paddingTop", "2px");
		top.setWidth(420);
		top.add(titleTreeLabel);
		top.setLayout(new FlowLayout());
		
		titleTreeLabel.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (!AppUtil.checkToShowWarningInEditMode()) {
					tree.getSelectionModel().deselectAll();
					
					top.addStyleName("x-ftree2-selected");
					PerimetreModel perimetreModel = selectedPerimetreModel;
					RefreshTreeEvent event = new RefreshTreeEvent(selectedEntiteModel, perimetreModel);
					bus.fireEvent(event);
					
//					DelegationListProjectEvent event = new DelegationListProjectEvent(selectedEntiteModel, selectedPerimetreModel);
//					event.setMode(DelegationListProjectEvent.DELEGATION_FILTER_FROM_DELEGATION_FILTER);
//					bus.fireEvent(event);
				}
			}
		});
		
		titleTreeLabel.addListener(Events.OnMouseOver, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				top.addStyleName("x-ftree2-node-over");
			}
		});
		
		titleTreeLabel.addListener(Events.OnMouseOut, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				top.removeStyleName("x-ftree2-node-over");
			}
		});

		ContentPanel panel = new ContentPanel();
		panel.setLayout(new FlowLayout());
		panel.setHeaderVisible(false);
		panel.setBodyBorder(false);
		panel.setHeight(-1);
		panel.setStyleAttribute("paddingTop", "2px");

		bus.addHandler(RefreshTreeEvent.getType(), new RefreshTreeHandler() {
			public void onLoadAction(RefreshTreeEvent event) {
				disableEvents(true);
				selectedEntiteModel = event.getEntiteModel();
				selectedPerimetreModel = event.getPerimetreModel();
				selectedPerimetreTreeModel = new PerimetreTreeModel();
				selectedPerimetreTreeModel.setPerId(selectedPerimetreModel.getPerId());
				selectedPerimetreTreeModel.setName(selectedPerimetreModel.getName());
				selectedPerimetreTreeModel.setPath(selectedPerimetreModel.getName());
				selectedPerimetreTreeModel.setParent(selectedPerimetreModel.getParent() == null ? null : selectedPerimetreModel.getParent().getName());
				// 27 Nov
				List<UserRoleModel> roles = SessionServiceImpl.getInstance().getUserContext().getUserRoles();
				for (UserRoleModel r: roles) { 
					selectedPerimetreTreeModel.setPermissionByRole(r.getRole());
				}
				titleTreeLabel.setText(event.getPerimetreModel().getName());
				tree.getSelectionModel().deselectAll();
				
				top.addStyleName("x-ftree2-selected");
								
				store.removeAll();
				loader.load();				
				disableEvents(false);
			}
		});

		RpcProxy<List<PerimetreTreeModel>> proxy = new RpcProxy<List<PerimetreTreeModel>>() {
			@Override
			protected void load(Object loadConfig, AsyncCallback<List<PerimetreTreeModel>> callback) {
				PerimetreTreeModel model = (PerimetreTreeModel) loadConfig;
				if (selectedEntiteModel != null && selectedPerimetreModel != null) {
					if (model == null) {
						model = new PerimetreTreeModel(selectedPerimetreModel, SessionServiceImpl.getInstance().getUserContext().getUserRoles());
						model.setEntiteId(selectedEntiteModel.getEntId());
						model.setLevel(0);
						model.setPath(selectedPerimetreModel.getName());
						model.setIsEntite(false);
					}
					model.setName(SafeHtmlUtils.htmlEscape(model.getName()));
					clientPerimetreService.getTreeModelByParent(selectedEntiteModel.getEntId(), SessionServiceImpl
							.getInstance().getUserContext().getUserRoles(), model, callback);
				}
			}
		};

		loader = new BaseTreeLoader<PerimetreTreeModel>(proxy) {
			@Override
			public boolean hasChildren(PerimetreTreeModel parent) {
				return parent.getIsParent();
			}
		};

		store = new TreeStore<PerimetreTreeModel>(loader);

		tree = new TreePanel<PerimetreTreeModel>(store);
		tree.setId(ConstantClient.CONTROL_TREE_ID);
		
		tree.setIconProvider(new ModelIconProvider<PerimetreTreeModel>() {

			public AbstractImagePrototype getIcon(PerimetreTreeModel model) {
				if ((model.getIsLectureControl() != null) && (model.getIsLectureControl())) {
					return IconHelper.createPath("html/icon/check.png");
				} else {
					return null;
				}
			}

		});
		tree.setDisplayProperty(PerimetreModel.PERIMETRE_NAME);		
		tree.addListener(Events.OnClick, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {					
				final PerimetreTreeModel node = tree.getSelectionModel().getSelectedItem();
				if (node != null) {					
						top.removeStyleName("x-ftree2-selected");						
						selectedPerimetreTreeModel = node;
						String path = selectedPerimetreTreeModel.getPath();
						if (!path.equals("")) {
							path = " UO > " + path;							
						}
						Label contentPathLabel = (Label)ComponentManager.get().get(ConstantClient.PATH_LABEL_ID);
						contentPathLabel.setText(path);
						
						ControlFilterEvent controlFilterEvent = new ControlFilterEvent();						
						controlFilterEvent.setPerimetreTreeModel(selectedPerimetreTreeModel);
						controlFilterEvent.setEntiteModel(SessionServiceImpl.getInstance().getEntiteContext());
						controlFilterEvent.setTypeModel(null);				
						controlFilterEvent.setStartDate(null);
						controlFilterEvent.setEndDate(null);						
						
						// fire event delegation filter
						bus.fireEvent(controlFilterEvent);
						
						//bus.fireEvent(new DelegationTreeEvent(node));					
				}				
			}
		});
		tree.setDisplayProperty(PerimetreModel.PERIMETRE_NAME);
		tree.setStateful(true);
		tree.getStyle().setJointCollapsedIcon(IconHelper.createPath("html/icon/plus.jpg"));
		tree.getStyle().setJointExpandedIcon(IconHelper.createPath("html/icon/minus.jpg"));
		tree.getStyle().setNodeCloseIcon(null);
		tree.getStyle().setNodeOpenIcon(null);

		panel.add(tree);
		top.add(panel);
		add(top);
	}

	public TreePanel<PerimetreTreeModel> getTreePanel() {
		return this.tree;
	}
	public void onLoadPanel() {
		disableEvents(true);
		selectedEntiteModel = SessionServiceImpl.getInstance().getEntiteContext();
		selectedPerimetreModel = SessionServiceImpl.getInstance().getPerimetreContext();
		selectedPerimetreTreeModel = new PerimetreTreeModel();
		selectedPerimetreTreeModel.setPerId(selectedPerimetreModel.getPerId());
		selectedPerimetreTreeModel.setName(selectedPerimetreModel.getName());
		selectedPerimetreTreeModel.setPath(selectedPerimetreModel.getName());
		selectedPerimetreTreeModel.setParent(selectedPerimetreModel.getParent() == null ? null : selectedPerimetreModel.getParent().getName());
		// 27 Nov
		List<UserRoleModel> roles = SessionServiceImpl.getInstance().getUserContext().getUserRoles();
		for (UserRoleModel r: roles) { 
			selectedPerimetreTreeModel.setPermissionByRole(r.getRole());
		}
		titleTreeLabel.setText(selectedPerimetreModel.getName());
		tree.getSelectionModel().deselectAll();
		
		top.addStyleName("x-ftree2-selected");
		
		String path = selectedPerimetreTreeModel.getPath();
		if (!path.equals("")) {
			path = " UO > " + path;							
		}
		Label contentPathLabel = (Label)ComponentManager.get().get(ConstantClient.PATH_LABEL_ID);
		if (contentPathLabel != null) {
			contentPathLabel.setText(path);
		}
		
		store.removeAll();
		loader.load();		
		disableEvents(false);
	}
	
	public PerimetreTreeModel getSelectedPerimetreTreeModel() {
		return selectedPerimetreTreeModel;
	}
}
