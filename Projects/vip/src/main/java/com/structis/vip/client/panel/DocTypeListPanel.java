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
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.LoadDocumentHandler;
import com.structis.vip.client.event.ModifyDocTypeEvent;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDocTypeServiceAsync;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.exception.LanguageException;
import com.structis.vip.shared.model.DocumentTypeModel;

public class DocTypeListPanel extends LayoutContainer {
	private final Messages messages = GWT.create(Messages.class);
	private final int WIDTH = 800;
	private final int HEIGHT = 480;

	private SimpleEventBus bus;
	private ListStore<DocumentTypeModel> store = new ListStore<DocumentTypeModel>();
	
	private Button btnAdd;
	private Button btnModifer;
	private Button btnSupprimer;
	private Grid<DocumentTypeModel> grid;
	private PagingLoader<PagingLoadResult<DocumentTypeModel>> loader;
	private PagingModelMemoryProxy proxy;

	private ClientDocTypeServiceAsync clientDocTypeService = ClientDocTypeServiceAsync.Util.getInstance();

	public DocTypeListPanel(SimpleEventBus bus) {
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
		clientDocTypeService.getDocTypes(new AsyncCallback<List<DocumentTypeModel>>() {
					@Override
					public void onSuccess(List<DocumentTypeModel> arg0) {
						proxy.setData(arg0);
						loader.load(0, 50);
						store = new ListStore<DocumentTypeModel>(loader);
						grid.unmask();
					}

					@Override
					public void onFailure(Throwable arg0) {
						grid.unmask();
					}
				});
	}
	
	private void initEvent() {
		grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<DocumentTypeModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<DocumentTypeModel> se) {
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
					final DocumentTypeModel model = grid.getSelectionModel().getSelectedItem();
					clientDocTypeService.delete(model, new AsyncCallback<Boolean>() {
						@Override
						public void onSuccess(Boolean arg0) {
							initData();
							Info.display(messages.commoninfo(), messages.doctypemessagedeletesuccessfully());
						}

						@Override
						public void onFailure(Throwable caught) {
							String details = caught.getMessage();
							if (caught instanceof LanguageException) {
								details = ExceptionMessageHandler.getErrorMessage(((LanguageException) caught).getCode());
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
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCTYPE_CREATE_FORM);
				ModifyDocTypeEvent subEvent = new ModifyDocTypeEvent();
				subEvent.setModel(null);
				event.setEvent(subEvent);
				bus.fireEvent(event);
			}
		});
		
		btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ContentEvent event = new ContentEvent();
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCTYPE_CREATE_FORM);
				ModifyDocTypeEvent subEvent = new ModifyDocTypeEvent();
				subEvent.setModel(grid.getSelectionModel().getSelectedItem());
				event.setEvent(subEvent);
				bus.fireEvent(event);
			}
		});
		
		btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				DocumentTypeModel model = grid.getSelectionModel().getSelectedItem(); 
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

		ColumnConfig name = new ColumnConfig(DocumentTypeModel.DOC_TYPE_NAME, messages.doctypenom(), 100);
		ColumnConfig desc = new ColumnConfig(DocumentTypeModel.DOC_TYPE_DESC, messages.doctypedesc(), 100);
				
		proxy = new PagingModelMemoryProxy(new ArrayList<DocumentTypeModel>());
		loader = new BasePagingLoader<PagingLoadResult<DocumentTypeModel>>(proxy);
		loader.setRemoteSort(true);
		store = new ListStore<DocumentTypeModel>(loader);
		toolBar.bind(loader);
		loader.load(0, 50);
		
		List<ColumnConfig> config = new ArrayList<ColumnConfig>();
		config.add(name);
		config.add(desc);

		final ColumnModel cm = new ColumnModel(config);

		grid = new Grid<DocumentTypeModel>(store, cm);
		
		GridFilters filters = new GridFilters();  
		filters.setLocal(true);
		StringFilter nameFilter = new StringFilter(DocumentTypeModel.DOC_TYPE_NAME); 
		filters.addFilter(nameFilter); 
		
		grid.setBorders(true);
		grid.addPlugin(filters);
		grid.setLoadMask(true);
		grid.getView().setAutoFill(true);
		grid.getView().setForceFit(true);
		WindowResizeBinder.bind(grid);
		
		ContentPanel panel = new ContentPanel();
		panel.setHeading(messages.doctypelistheader());
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
