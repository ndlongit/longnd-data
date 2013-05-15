package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
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
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
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
import com.google.gwt.user.client.ui.HTML;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.LoadDocumentHandler;
import com.structis.vip.client.event.ModifyLanguageEvent;
import com.structis.vip.client.event.control.ModifyExternControllerEvent;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientExternControllerServiceAsync;
import com.structis.vip.client.service.ClientLanguageServiceAsync;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.exception.ExternControllerException;
import com.structis.vip.shared.exception.LanguageException;
import com.structis.vip.shared.model.ExternControllerModel;

public class ExternalControllerListPanel extends LayoutContainer {
	private final Messages messages = GWT.create(Messages.class);
	private final int WIDTH = 800;
	private final int HEIGHT = 480;

	private SimpleEventBus bus;
	private ListStore<ExternControllerModel> store = new ListStore<ExternControllerModel>();
	
	private Button btnAdd;
	private Button btnModifer;
	private Button btnSupprimer;
	private Grid<ExternControllerModel> grid;
	private PagingLoader<PagingLoadResult<ExternControllerModel>> loader;
	private PagingModelMemoryProxy proxy;

	private ClientExternControllerServiceAsync clientExternControllerService = ClientExternControllerServiceAsync.Util.getInstance();

	public ExternalControllerListPanel(SimpleEventBus bus) {
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
		clientExternControllerService.findAll(new AsyncCallback<List<ExternControllerModel>>() {
					@Override
					public void onSuccess(List<ExternControllerModel> arg0) {
						proxy.setData(arg0);
						loader.load(0, 50);
						store = new ListStore<ExternControllerModel>(loader);
						grid.unmask();
					}

					@Override
					public void onFailure(Throwable arg0) {
						grid.unmask();
					}
				});
	}
	
	private void initEvent() {
		grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<ExternControllerModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ExternControllerModel> se) {
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
					final ExternControllerModel model = grid.getSelectionModel().getSelectedItem();
					clientExternControllerService.delete(model, new AsyncCallback<Boolean>() {
						@Override
						public void onSuccess(Boolean arg0) {
							initData();
							Info.display(messages.commoninfo(), messages.externcontrollermessagedeletesuccessfully());
						}

						@Override
						public void onFailure(Throwable caught) {
							String details = caught.getMessage();
							if (caught instanceof ExternControllerException) {
								details = ExceptionMessageHandler.getErrorMessage(((ExternControllerException) caught).getCode());
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
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_EXTERN_CONTROLLER_CREATE_FORM);
				ModifyExternControllerEvent subEvent = new ModifyExternControllerEvent();
				subEvent.setModel(null);
				event.setEvent(subEvent);
				bus.fireEvent(event);
			}
		});
		
		btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ContentEvent event = new ContentEvent();
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_EXTERN_CONTROLLER_CREATE_FORM);
				ModifyExternControllerEvent subEvent = new ModifyExternControllerEvent();
				subEvent.setModel(grid.getSelectionModel().getSelectedItem());
				event.setEvent(subEvent);
				bus.fireEvent(event);
			}
		});
		
		btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ExternControllerModel model = grid.getSelectionModel().getSelectedItem(); 
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
		
		topToolBar.add(btnAdd);
		topToolBar.add(btnModifer);
		topToolBar.add(btnSupprimer);

//		ColumnConfig fname = new ColumnConfig(ExternControllerModel.EXC_FIRSTNAME, messages.adminexccontrollerlname(), 150);
		ColumnConfig lname = new ColumnConfig(ExternControllerModel.EXC_NAME, messages.adminexccontrollerlname(), 100);
		lname.setAlignment(HorizontalAlignment.LEFT);
//		ColumnConfig nationality = new ColumnConfig(ExternControllerModel.EXC_NATIONALITY, messages.adminexccontrollernationality(), 100);
//		nationality.setAlignment(HorizontalAlignment.CENTER);
//		ColumnConfig address = new ColumnConfig(ExternControllerModel.EXC_ADDRESS, messages.adminexccontrolleraddress(), 150);
//		address.setAlignment(HorizontalAlignment.CENTER);
		
		GridCellRenderer<ExternControllerModel> fullnameRender = new GridCellRenderer<ExternControllerModel>() {
			@Override
			public Object render(final ExternControllerModel model, String property,
					ColumnData config, int rowIndex, int colIndex,
					final ListStore<ExternControllerModel> store, Grid<ExternControllerModel> grid) {
				String fullname = model.getName();
				return new HTML(fullname);
			}
		};
		
		lname.setRenderer(fullnameRender);

		proxy = new PagingModelMemoryProxy(new ArrayList<ExternControllerModel>());
		loader = new BasePagingLoader<PagingLoadResult<ExternControllerModel>>(proxy);
		loader.setRemoteSort(true);
		store = new ListStore<ExternControllerModel>(loader);
		toolBar.bind(loader);
		loader.load(0, 50);
		
		List<ColumnConfig> config = new ArrayList<ColumnConfig>();
		config.add(lname);
//		config.add(nationality);
//		config.add(address);

		final ColumnModel cm = new ColumnModel(config);

		grid = new Grid<ExternControllerModel>(store, cm);
		
		GridFilters filters = new GridFilters();  
		filters.setLocal(true);
		StringFilter nameFilter = new StringFilter(ExternControllerModel.EXC_NAME); 
		filters.addFilter(nameFilter); 
		
		grid.setBorders(true);
		grid.addPlugin(filters);
		grid.setLoadMask(true);
		grid.getView().setAutoFill(true);
		grid.getView().setForceFit(true);
		WindowResizeBinder.bind(grid);
		
		ContentPanel panel = new ContentPanel();
		panel.setHeading(messages.extcontrollerheading());
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
