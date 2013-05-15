package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
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
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.LoadDocumentHandler;
import com.structis.vip.client.event.ModifyStatusEvent;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDelegationStatusServiceAsync;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.exception.DelegationException;
import com.structis.vip.shared.model.DelegationStatusModel;

public class StatusListPanel extends LayoutContainer {
	private final Messages messages = GWT.create(Messages.class);
	private final int WIDTH = 800;
	private final int HEIGHT = 480;

	private SimpleEventBus bus;
	private ListStore<DelegationStatusModel> store = new ListStore<DelegationStatusModel>();
	
	private Button btnAdd;
	private Button btnModifer;
	private Button btnSupprimer;
	private Grid<DelegationStatusModel> grid;
	private PagingLoader<PagingLoadResult<DelegationStatusModel>> loader;
	private PagingModelMemoryProxy proxy;

	private ClientDelegationStatusServiceAsync clientDelegationStatusService = ClientDelegationStatusServiceAsync.Util.getInstance();

	public StatusListPanel(SimpleEventBus bus) {
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
		bus.addHandler(LoadDocumentEvent.getType(), new LoadDocumentHandler() {
			@Override
			public void onLoadAction(LoadDocumentEvent event) {
				disableEvents(true);
				initData();
				disableEvents(false);
			}
		});
		
		bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler(){
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
		clientDelegationStatusService.getAllDelegationStatuses(new AsyncCallback<List<DelegationStatusModel>>() {
					@Override
					public void onSuccess(List<DelegationStatusModel> arg0) {
						proxy.setData(arg0);
						loader.load(0, 50);
						store = new ListStore<DelegationStatusModel>(loader);
						grid.unmask();
					}

					@Override
					public void onFailure(Throwable arg0) {
						grid.unmask();
					}
				});
	}
	
	private void initEvent() {
		grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<DelegationStatusModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<DelegationStatusModel> se) {
				if (se.getSelectedItem() != null) {
					btnModifer.setEnabled(true);
					btnSupprimer.setEnabled(true);
				} else {
					btnModifer.setEnabled(false);
					btnSupprimer.setEnabled(false);
				}
			}
		});
		
		final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
			public void handleEvent(MessageBoxEvent ce) {
				Button btn = ce.getButtonClicked();
				String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
				if (txtReturn.equals(btn.getText())) {
					final DelegationStatusModel model = grid.getSelectionModel().getSelectedItem();
					clientDelegationStatusService.delete(model, new AsyncCallback<Boolean>() {
						@Override
						public void onSuccess(Boolean arg0) {
							initData();
							Info.display(messages.commoninfo(), messages.statusmessagedeletesuccessfully());
						}

						@Override
						public void onFailure(Throwable caught) {
							String details = caught.getMessage();
							if (caught instanceof DelegationException) {
								details = ExceptionMessageHandler.getErrorMessage(((DelegationException) caught).getCode());
							}
							Info.display(messages.commonerror(), details);
						}
					});
				} else {
				}
			}
		};
		
		btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ContentEvent event = new ContentEvent();
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_STATUS_CREATE_FORM);
				ModifyStatusEvent subEvent = new ModifyStatusEvent();
				subEvent.setModel(null);
				event.setEvent(subEvent);
				bus.fireEvent(event);
			}
		});
		
		btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ContentEvent event = new ContentEvent();
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_STATUS_CREATE_FORM);
				ModifyStatusEvent subEvent = new ModifyStatusEvent();
				subEvent.setModel(grid.getSelectionModel().getSelectedItem());
				event.setEvent(subEvent);
				bus.fireEvent(event);
			}
		});
		
		btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				DelegationStatusModel model = grid.getSelectionModel().getSelectedItem(); 
				if (model != null) {
					MessageBox box = new MessageBox();
					box.setButtons(MessageBox.YESNO);
					box.setIcon(MessageBox.INFO);
					box.setTitle(messages.commonConfirmation());
					box.addCallback(l);
					box.setMessage(messages.commonDeleteMessage(model.getName()));
					((Button) box.getDialog().getButtonBar().getItem(0)).setText(messages.commonOui());
					((Button) box.getDialog().getButtonBar().getItem(1)).setText(messages.commonNon());
					box.show();
				}
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
		
		btnSupprimer = new Button(messages.commonSupprimer());
		btnSupprimer.setIcon(IconHelper.createPath("html/delete-icon.png"));
		btnSupprimer.setEnabled(false);
		
//		topToolBar.add(btnAdd);
		topToolBar.add(btnModifer);
//		topToolBar.add(btnSupprimer);

		ColumnConfig name = new ColumnConfig(DelegationStatusModel.DELEGATION_STATUS_NAME, messages.statusnom(), 200);
		ColumnConfig description = new ColumnConfig(DelegationStatusModel.DELEGATION_STATUS_DESCRIPTION, messages.statusdescription(), 300);

		proxy = new PagingModelMemoryProxy(new ArrayList<DelegationStatusModel>());
		loader = new BasePagingLoader<PagingLoadResult<DelegationStatusModel>>(proxy);
		loader.setRemoteSort(true);
		store = new ListStore<DelegationStatusModel>(loader);
		toolBar.bind(loader);
		loader.load(0, 50);
		
		List<ColumnConfig> config = new ArrayList<ColumnConfig>();
		config.add(name);
		config.add(description);

		final ColumnModel cm = new ColumnModel(config);

		grid = new Grid<DelegationStatusModel>(store, cm);
		
		GridFilters filters = new GridFilters();  
		filters.setLocal(true);
		StringFilter nameFilter = new StringFilter(DelegationStatusModel.DELEGATION_STATUS_NAME); 
		filters.addFilter(nameFilter); 
		
		grid.setBorders(true);
		grid.addPlugin(filters);
		grid.setLoadMask(true);
		grid.getView().setAutoFill(true);
		grid.getView().setForceFit(true);
		WindowResizeBinder.bind(grid);
		
		ContentPanel panel = new ContentPanel();
		panel.setHeading(messages.statuslistedesstatuts());
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
