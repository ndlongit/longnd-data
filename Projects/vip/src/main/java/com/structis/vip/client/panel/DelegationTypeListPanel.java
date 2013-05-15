package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.LoadDelegationTypeEvent;
import com.structis.vip.client.event.LoadDelegationTypeHandler;
import com.structis.vip.client.event.ModifyDelegationTypeEvent;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDelegationTypeServiceAsync;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.model.DelegationTypeModel;

public class DelegationTypeListPanel extends LayoutContainer {
	private final Messages messages = GWT.create(Messages.class);
	private final int WIDTH = 800;
	private final int HEIGHT = 480;

	private SimpleEventBus bus;
	private ListStore<DelegationTypeModel> store = new ListStore<DelegationTypeModel>();

	private Button btnAdd;
	private Button btnModifer;
	private Grid<DelegationTypeModel> grid;
	private PagingLoader<PagingLoadResult<DelegationTypeModel>> loader;
	private PagingModelMemoryProxy proxy;

	private ClientDelegationTypeServiceAsync clientDelegationTypeService = ClientDelegationTypeServiceAsync.Util
			.getInstance();

	public DelegationTypeListPanel(SimpleEventBus bus) {
		this.bus = bus;

		setLayout(new FlowLayout(10));
		setScrollMode(Scroll.AUTO);

		initUI();
		initEvent();
		addHandler();
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
	}

	private void addHandler() {
		bus.addHandler(LoadDelegationTypeEvent.getType(), new LoadDelegationTypeHandler() {

			@Override
			public void onLoadAction(LoadDelegationTypeEvent event) {
				disableEvents(true);
				initData();
				disableEvents(false);
			}
		});

		bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {
			public void onLoadAction(final DelegationListProjectEvent event) {
				disableEvents(true);
				initData();
				disableEvents(false);
			}
		});
	}

	private void initData() {
		store.removeAll();
		grid.mask(messages.commonloadingdata());
		clientDelegationTypeService.getAllTypes(new AsyncCallback<List<DelegationTypeModel>>() {

			@Override
			public void onSuccess(List<DelegationTypeModel> arg0) {
				proxy.setData(arg0);
				loader.load(0, 50);
				store = new ListStore<DelegationTypeModel>(loader);
				grid.unmask();
			}

			@Override
			public void onFailure(Throwable arg0) {
				grid.unmask();
			}
		});
	}

	private void initEvent() {
		grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<DelegationTypeModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<DelegationTypeModel> se) {
				if (se.getSelectedItem() != null) {
					btnModifer.setEnabled(true);
				} else {
					btnModifer.setEnabled(false);
				}
			}
		});

		btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ContentEvent event = new ContentEvent();
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGATION_TYPE_CREATE_FORM);
				ModifyDelegationTypeEvent subEvent = new ModifyDelegationTypeEvent();
				subEvent.setModel(null);
				event.setEvent(subEvent);
				bus.fireEvent(event);
			}
		});

		btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ContentEvent event = new ContentEvent();
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGATION_TYPE_CREATE_FORM);
				ModifyDelegationTypeEvent subEvent = new ModifyDelegationTypeEvent();
				subEvent.setModel(grid.getSelectionModel().getSelectedItem());
				event.setEvent(subEvent);
				bus.fireEvent(event);
			}
		});
	}

	private void initUI() {
		PagingToolBar toolBar = new PagingToolBar(50);
		ToolBar topToolBar = new ToolBar();

		btnAdd = new Button(messages.commonCreerbutton());
		btnAdd.setStyleAttribute("margin-left", "10px");
		btnAdd.setIcon(IconHelper.createPath("html/add-icon.png"));

		btnModifer = new Button(messages.commonmodifierbutton());
		btnModifer.setIcon(IconHelper.createPath("html/save-icon.png"));
		btnModifer.setEnabled(false);

//		topToolBar.add(btnAdd);
		topToolBar.add(btnModifer);

		ColumnConfig name = new ColumnConfig(DelegationTypeModel.DELEGATION_TYPE_NAME, messages.delegationtypenom(),
				200);
		ColumnConfig code = new ColumnConfig(DelegationTypeModel.DELEGATION_TYPE_DESCRIPTION,
				messages.delegationtypedescription(), 580);

		proxy = new PagingModelMemoryProxy(new ArrayList<DelegationTypeModel>());
		loader = new BasePagingLoader<PagingLoadResult<DelegationTypeModel>>(proxy);
		loader.setRemoteSort(true);
		store = new ListStore<DelegationTypeModel>(loader);
		toolBar.bind(loader);
		loader.load(0, 50);

		List<ColumnConfig> config = new ArrayList<ColumnConfig>();
		config.add(name);
		config.add(code);

		final ColumnModel cm = new ColumnModel(config);

		grid = new Grid<DelegationTypeModel>(store, cm);

		grid.setBorders(true);
		grid.setLoadMask(true);
		grid.getView().setAutoFill(true);
		grid.getView().setForceFit(true);
		WindowResizeBinder.bind(grid);

		ContentPanel panel = new ContentPanel();
		panel.setHeading(messages.delegationtypelist());
		panel.setBottomComponent(toolBar);
		panel.setTopComponent(topToolBar);
		panel.setCollapsible(true);
		panel.setFrame(true);
		panel.setSize(WIDTH, HEIGHT);
		panel.setLayout(new FitLayout());
		panel.add(grid);
		grid.getAriaSupport().setLabelledBy(panel.getHeader().getId() + "-label");

		add(panel);
	}
}