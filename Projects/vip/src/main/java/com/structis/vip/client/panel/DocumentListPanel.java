package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
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
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.ModifyDocumentEvent;
import com.structis.vip.client.event.ModifyDocumentHandler;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDocumentMdlServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.NameValuePair;
import com.structis.vip.client.util.ReportUtil;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.exception.DocumentMdlException;
import com.structis.vip.shared.model.DocumentMdlModel;

public class DocumentListPanel extends LayoutContainer {
	public SimpleEventBus bus;
	private final Messages messages = GWT.create(Messages.class);
	private final int WIDTH = 800;
	private final int HEIGHT = 480;

	private Grid<DocumentMdlModel> grid;
	private PagingLoader<PagingLoadResult<DocumentMdlModel>> loader;
	private PagingModelMemoryProxy proxy;
	private Button btnAdd;
	private Button btnConsulter;
	private Button btnModifer;
	private Button btnSupprimer;
	
	private ListStore<DocumentMdlModel> store = new ListStore<DocumentMdlModel>();
	private ClientDocumentMdlServiceAsync clientDocumentMdlService = ClientDocumentMdlServiceAsync.Util.getInstance();

	public DocumentListPanel(SimpleEventBus bus) {
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
		bus.addHandler(ModifyDocumentEvent.getType(), new ModifyDocumentHandler() {
			@Override
			public void onLoadAction(ModifyDocumentEvent event) {
				disableEvents(true);
				initData();
				disableEvents(false);
			}
		});
	}
	
	private void initEvent() {
		grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<DocumentMdlModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<DocumentMdlModel> se) {
				if (se.getSelectedItem() != null) {
					btnConsulter.setEnabled(true);
					btnModifer.setEnabled(true);
					btnSupprimer.setEnabled(true);
				} else {
					btnConsulter.setEnabled(false);
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
					final DocumentMdlModel model = grid.getSelectionModel().getSelectedItem();
					clientDocumentMdlService.delete(model, new AsyncCallback<Boolean>() {
						@Override
						public void onSuccess(Boolean arg0) {
							initData();
							Info.display(messages.commoninfo(), messages.delegationmodeldeleted());
						}

						@Override
						public void onFailure(Throwable caught) {
							String details = caught.getMessage();
							if (caught instanceof DocumentMdlException) {
								details = ExceptionMessageHandler.getErrorMessage(((DocumentMdlException) caught).getCode());
							}
							Info.display(messages.commonerror(), details);
						}
					});
				} else {
				}
			}
		};
		
		btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				DocumentMdlModel model = grid.getSelectionModel().getSelectedItem(); 
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
		
		btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ContentEvent event = new ContentEvent();
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_CREATE_FORM);
				
				ModifyDocumentEvent subEvent = new ModifyDocumentEvent();
				subEvent.setModel(null);
				subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_CREATE_FORM);
				
				event.setEvent(subEvent);
				bus.fireEvent(event);
			}
		});
		
		btnConsulter.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ContentEvent event = new ContentEvent();
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_VIEW_DOCUMENT);
				
				ModifyDocumentEvent subEvent = new ModifyDocumentEvent();
				subEvent.setModel(grid.getSelectionModel().getSelectedItem());
				subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_VIEW_DOCUMENT);
				
				event.setEvent(subEvent);
				bus.fireEvent(event);
			}
		});
		
		btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ContentEvent event = new ContentEvent();
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_CREATE_FORM);
				
				ModifyDocumentEvent subEvent = new ModifyDocumentEvent();
				subEvent.setModel(grid.getSelectionModel().getSelectedItem());
				subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_CREATE_FORM);
				
				event.setEvent(subEvent);
				bus.fireEvent(event);
			}
		});
	}
	
	private void initData() {
		store.removeAll();
		grid.mask(messages.commonloadingdata());
		clientDocumentMdlService.getAllDocumentModelsByEntite(SessionServiceImpl.getInstance().getEntiteContext(), 
				new AsyncCallback<List<DocumentMdlModel>>() {
					@Override
					public void onSuccess(List<DocumentMdlModel> arg0) {
						proxy.setData(arg0);
						loader.load(0, 50);
						store = new ListStore<DocumentMdlModel>(loader);
						grid.getView().refresh(true);
						grid.unmask();
					}

					@Override
					public void onFailure(Throwable arg0) {
						grid.unmask();
					}
				});
	}
	
	private void initUI() {
		PagingToolBar toolBar = new PagingToolBar(50);
		ToolBar topToolBar = new ToolBar();
		
		btnAdd = new Button(messages.documentcreate());
		btnAdd.setStyleAttribute("margin-left", "10px");
		btnAdd.setIcon(IconHelper.createPath("html/add-icon.png"));
		
		btnConsulter = new Button(messages.commonConsulterbutton());
		btnConsulter.setStyleAttribute("margin-left", "10px");
		btnConsulter.setIcon(IconHelper.createPath("html/view-icon.png"));
		btnConsulter.setEnabled(false);
		
		btnModifer = new Button(messages.commonmodifierbutton());
		btnModifer.setIcon(IconHelper.createPath("html/save-icon.png"));
		btnModifer.setEnabled(false);
		
		btnSupprimer = new Button(messages.commonSupprimer());
		btnSupprimer.setIcon(IconHelper.createPath("html/delete-icon.png"));
		btnSupprimer.setEnabled(false);
		
		topToolBar.add(btnAdd);
		topToolBar.add(btnConsulter);
		topToolBar.add(btnModifer);
		topToolBar.add(btnSupprimer);

		ColumnConfig name = new ColumnConfig(DocumentMdlModel.DOM_NAME, messages.documentname(), 180);
		
		GridCellRenderer<DocumentMdlModel> nameRender = new GridCellRenderer<DocumentMdlModel>() {
			@Override
			public Object render(final DocumentMdlModel model, String property,
					ColumnData config, int rowIndex, int colIndex,
					final ListStore<DocumentMdlModel> store, Grid<DocumentMdlModel> grid) {
				final Label label = new Label();
				label.setStyleName("x-link-item");
				label.setText(model.getName());
				
				label.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						String reportUrl = GWT.getHostPageBaseURL() + ".printTemplateDocumentServiceServlet";
						List<NameValuePair> values = new ArrayList<NameValuePair>();						
						values.add(new NameValuePair("fileName", URL.encode(model.getFilename())));					
						ReportUtil.showReport(reportUrl, values.toArray(new NameValuePair[0]));
					}
				});
				return label;
			}
		};
		name.setRenderer(nameRender);
		
		ColumnConfig fileName = new ColumnConfig(DocumentMdlModel.DOM_FILENAME, messages.documentfilename(), 180);		
		ColumnConfig type = new ColumnConfig(DocumentMdlModel.DOM_TYPE, messages.documenttype(), 40);
		type.setAlignment(HorizontalAlignment.CENTER);
		ColumnConfig language = new ColumnConfig("language.name", messages.documentlanguage(), 80);
		language.setAlignment(HorizontalAlignment.CENTER);		
		ColumnConfig version = new ColumnConfig(DocumentMdlModel.DOM_VERSION, messages.documentversion(), 40);

		List<ColumnConfig> config = new ArrayList<ColumnConfig>();
		config.add(name);
		config.add(fileName);
		config.add(type);
		config.add(language);
		config.add(version);

		final ColumnModel cm = new ColumnModel(config);
		
		proxy = new PagingModelMemoryProxy(new ArrayList<DocumentMdlModel>());
		loader = new BasePagingLoader<PagingLoadResult<DocumentMdlModel>>(proxy);
		loader.setRemoteSort(true);
		store = new ListStore<DocumentMdlModel>(loader);
		loader.load(0, 50);
		toolBar.bind(loader);
		
		grid = new Grid<DocumentMdlModel>(store, cm);
		grid.setWidth(500);
		GridSelectionModel<DocumentMdlModel> selectionMode = new GridSelectionModel<DocumentMdlModel>();
		selectionMode.setSelectionMode(SelectionMode.SINGLE);
		grid.setSelectionModel(selectionMode);
		
		GridFilters filters = new GridFilters();  
		filters.setLocal(true);
		StringFilter nameFilter = new StringFilter(DocumentMdlModel.DOM_NAME); 
		filters.addFilter(nameFilter);
//		LiveGridView liveView = new LiveGridView();  
//	    liveView.setEmptyText("No rows available on the server.");  
	      
//		grid.setView(liveView);
		grid.setColumnLines(true);
		grid.setBorders(true);
		grid.addPlugin(filters);
		grid.setAutoExpandColumn(DocumentMdlModel.DOM_NAME);		
				
		
		grid.getView().setAutoFill(true);
		grid.getView().setForceFit(true);
		WindowResizeBinder.bind(grid);
		
		ContentPanel panel = new ContentPanel();
		panel.setHeading(messages.documentlistdocuments());
		panel.setTopComponent(topToolBar);
		panel.setBottomComponent(toolBar);
		panel.setCollapsible(true);
		panel.setFrame(true);
		panel.setSize(WIDTH, HEIGHT);
		panel.setLayout(new FitLayout());
		panel.add(grid);
		grid.getAriaSupport().setLabelledBy(panel.getHeader().getId() + "-label");
		
		add(panel);	
	}
}
