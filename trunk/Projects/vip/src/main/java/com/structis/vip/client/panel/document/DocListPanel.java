package com.structis.vip.client.panel.document;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ComponentManager;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridGroupRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.GroupColumnData;
import com.extjs.gxt.ui.client.widget.grid.GroupingView;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.LabelToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.ModifyCollaboratureEvent;
import com.structis.vip.client.event.document.LoadDocEvent;
import com.structis.vip.client.event.document.LoadDocHandler;
import com.structis.vip.client.event.document.ModifyDocEvent;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDocumentServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.util.NameValuePair;
import com.structis.vip.client.util.ReportUtil;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.DocumentFilter;
import com.structis.vip.shared.model.ControlModel;
import com.structis.vip.shared.model.DocumentModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.FieldRuleModel;
import com.structis.vip.shared.model.PerimetreTreeModel;

public class DocListPanel extends LayoutContainer {
	private final Messages messages = GWT.create(Messages.class);
	private final int WIDTH = 800;
	private final int HEIGHT = 480;

	private SimpleEventBus bus;
//	private ListStore<DocumentModel> store = new ListStore<DocumentModel>();
	private GroupingStore<DocumentModel> store = new GroupingStore<DocumentModel>();
	
	private TextField<String> txtFilter;
	private Button btnAdd;
	private Button btnModifer;
	private Button btnSupprimer;
		
	private PagingToolBar toolBar; 
		
	private Grid<DocumentModel> grid;
	private PagingLoader<PagingLoadResult<DocumentModel>> loader;
	private RpcProxy<PagingLoadResult<DocumentModel>> proxy;
	private DocumentFilter documentFilter;

	private ClientDocumentServiceAsync clientDocumentService = ClientDocumentServiceAsync.Util.getInstance();

	public DocListPanel(SimpleEventBus bus) {
		this.bus = bus;
		
		setLayout(new FlowLayout(0));
		setScrollMode(Scroll.AUTO);
		
		initUI();
		initEvent();
		addHandler();
	}
	
	private void addHandler() {
		bus.addHandler(LoadDocEvent.getType(), new LoadDocHandler() {
			@Override
			public void onLoadAction(LoadDocEvent event) {
				disableEvents(true);
				initData();
				disableEvents(false);
			}
		});				
		
	}
	
	private void initData() {
		documentFilter = new DocumentFilter();		
		documentFilter.setOffset(0);
		documentFilter.setLimit(ConstantClient.DEFAULT_PAGE_SIZE_50);
		documentFilter.setName("");
		loader.load(documentFilter);
	}
	
	public void loadPanel(){
		initData();
	}
	private void initEvent() {
		grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<DocumentModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<DocumentModel> se) {
				if (se.getSelectedItem() != null) {					
//					if (ConstantClient.ENTITE_ID_IS_BYEFE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
						btnModifer.setEnabled(true);
						btnSupprimer.setEnabled(true);
//					}
				} else {
					btnModifer.setEnabled(false);
					btnSupprimer.setEnabled(false);
				}
			}
		});

		grid.addListener(Events.RowDoubleClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
			}
		});
		
		final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
			public void handleEvent(MessageBoxEvent ce) {
				Button btn = ce.getButtonClicked();
				String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(1)).getText();
				if (txtReturn.equals(btn.getText())) {
					final DocumentModel model = grid.getSelectionModel().getSelectedItem();
					clientDocumentService.delete(model, new AsyncCallback<Boolean>() {
						@Override
						public void onSuccess(Boolean arg0) {
							initData();
							Info.display(messages.commoninfo(), messages.documentmessagedeletesuccessfully());
						}

						@Override
						public void onFailure(Throwable caught) {
						}
					});
				} else {
				}
			}
		};
		
		txtFilter.addListener(Events.OnKeyUp, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				String filter = txtFilter.getValue();
				if (filter != null) {
					filter = (!"".equals(filter)) ? filter.trim() : "";
				} else {
					filter = "";
				}
				if (documentFilter == null) {
					documentFilter = new DocumentFilter();
				}
				documentFilter.setOffset(0);
				documentFilter.setLimit(ConstantClient.DEFAULT_PAGE_SIZE_50);
				documentFilter.setName(filter);
				//config.set("filterName", filter);
				loader.load(documentFilter);
			}
		});
		
		
		btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				DocumentModel item = grid.getSelectionModel().getSelectedItem();
								
				deleteDocument(item);
			}
		});
		
		btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ContentEvent event = new ContentEvent();
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOC_CREATE_FORM);
				
				ModifyDocEvent subEvent = new ModifyDocEvent();
				subEvent.setModel(null);
				subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOC_CREATE_FORM);
				
				event.setEvent(subEvent);
				bus.fireEvent(event);
			}
		});
		
		btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ContentEvent event = new ContentEvent();
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOC_CREATE_FORM);
				
				ModifyDocEvent subEvent = new ModifyDocEvent();
				subEvent.setModel(grid.getSelectionModel().getSelectedItem());
				subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOC_CREATE_FORM);
				
				event.setEvent(subEvent);
				bus.fireEvent(event);
			}
		});
		
	}

	public void deleteDocument(final DocumentModel model) {
		AppUtil.showConfirmMessageBox(messages.documentDeleteDocumentMessage(model.getName()), new Listener<MessageBoxEvent>() {
			@Override
			public void handleEvent(MessageBoxEvent be) {
				if (be.getButtonClicked().getText().equalsIgnoreCase(messages.commonDialogOuiButton()) ) {
					clientDocumentService.delete(model, new AsyncCallback<Boolean>() {
						@Override
						public void onSuccess(Boolean arg0) {
							Info.display(messages.commoninfo(), messages.statusmessagedeletesuccessfully());
							grid.getStore().remove(model);
						}

						@Override
						public void onFailure(Throwable caught) {
							String details = caught.getMessage();									
							Info.display(messages.commonerror(), details);
						}
					});
				}						
			}
			
		});
	}	
	private void initUI() {
		proxy = new RpcProxy<PagingLoadResult<DocumentModel>>() {
			@Override
			public void load(Object loadConfig, AsyncCallback<PagingLoadResult<DocumentModel>> callback) {
				EntiteModel entiteModel = SessionServiceImpl.getInstance().getEntiteContext();
				if (entiteModel != null && entiteModel.getEntId() != null) {
					String name ="";
					BasePagingLoadConfig config = null;
					if (loadConfig != null ){
						config = (BasePagingLoadConfig)loadConfig;
						if (config instanceof DocumentFilter) {
							name = ((DocumentFilter)config).getName();
						}
					} else {
						config = new DocumentFilter();
						config.setOffset(0);						
						config.setLimit(ConstantClient.DEFAULT_PAGE_SIZE_50);
					}
						
					
					clientDocumentService.findDocumentsWithPaging(name, (PagingLoadConfig)config,  callback);	
					toolBar.setEnabled(true);
				}
			}
		};
			
		loader = new BasePagingLoader<PagingLoadResult<DocumentModel>>(proxy);
		loader.setRemoteSort(true);		
				
		store = new GroupingStore<DocumentModel>(loader);
		
		toolBar = new PagingToolBar(ConstantClient.DEFAULT_PAGE_SIZE_50);
		toolBar.bind(loader);
		
		VerticalPanel toolbarPanel = new  VerticalPanel();
		toolbarPanel.setTableWidth("100%");
		toolbarPanel.setBorders(false);
		toolbarPanel.setHeight(54);
		
		ToolBar topToolBar = new ToolBar();
		ToolBar topSecondToolBar = new ToolBar();

		txtFilter = new TextField<String>();
		txtFilter.setTitle(messages.documentnom());
		
		btnAdd = new Button(messages.commonCreerbutton());
		btnAdd.setStyleAttribute("margin-left", "10px");
		btnAdd.setIcon(IconHelper.createPath("html/add-icon.png"));
		
		btnModifer = new Button(messages.commonmodifierbutton());
		btnModifer.setIcon(IconHelper.createPath("html/save-icon.png"));
		btnModifer.setEnabled(false);
		
		btnSupprimer = new Button(messages.commonSupprimer());
		btnSupprimer.setIcon(IconHelper.createPath("html/delete-icon.png"));
		btnSupprimer.setEnabled(false);
		

		topToolBar.add(new LabelToolItem(messages.documentrechercher()));
		topToolBar.add(txtFilter);
		topToolBar.add(btnAdd);
		topToolBar.add(btnModifer);
		topToolBar.add(btnSupprimer);
			
		store.groupBy("category.name");
		
		ColumnConfig name = new ColumnConfig("name", messages.docname(), 40);
		ColumnConfig link = new ColumnConfig("link", messages.doclink(), 40);
		GridCellRenderer<DocumentModel> linkRenderer = new GridCellRenderer<DocumentModel>() {			
			@Override
			public Object render(final DocumentModel model, String property,
					com.extjs.gxt.ui.client.widget.grid.ColumnData config,
					int rowIndex, int colIndex,
					ListStore<DocumentModel> store,					
					Grid<DocumentModel> grid) {
				final Label label = new Label();
				label.setStyleName("x-link-item");
				label.setText(model.getLink());
				
				label.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						com.google.gwt.user.client.Window.open(model.getLink(), "Document",					    		 
					             "menubar=no," + 
					             "location=no," + 
					             "resizable=no," + 
					             "scrollbars=yes," + 
					             "status=no");
					}
				});
				return label;
			}
		};
		link.setRenderer(linkRenderer);
		ColumnConfig comment = new ColumnConfig("comments", messages.doccomment(), 20);		
		ColumnConfig date = new ColumnConfig("date", messages.docdate(), 20);
		date.setDateTimeFormat(DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT));
		date.setAlignment(HorizontalAlignment.CENTER);
		
		

		List<ColumnConfig> config = new ArrayList<ColumnConfig>();
		config.add(name);
		config.add(link);
		config.add(comment);
		config.add(date);

		final ColumnModel cm = new ColumnModel(config);

		GroupingView view = new GroupingView();
		view.setForceFit(true);
		view.setAutoFill(true);
		view.setShowGroupedColumn(false);
		view.setGroupRenderer(new GridGroupRenderer() {
			public String render(GroupColumnData data) {
				return data.group;
			}
		});

		GridFilters filters = new GridFilters();  
		filters.setLocal(true);
		StringFilter nameFilter = new StringFilter(DocumentModel.DOC_NAME); 
		filters.addFilter(nameFilter); 
		
		grid = new Grid<DocumentModel>(store, cm);
		grid.setView(view);
		grid.setAutoHeight(true);
		grid.setBorders(true);
		grid.setLoadMask(true);
		WindowResizeBinder.bind(grid);
				
		grid.addPlugin(filters);

		ContentPanel panel = new ContentPanel();
		panel.setHeading(messages.documentlistedesdocuments());
		toolbarPanel.add(topToolBar);
		toolbarPanel.add(topSecondToolBar);
		panel.setTopComponent(toolbarPanel);
		panel.setBottomComponent(toolBar);
		
		panel.setStyleAttribute("padding", "10px");
		
		panel.setAnimCollapse(false); 
		panel.setCollapsible(true);
		panel.setFrame(true);
		panel.setSize(WIDTH, HEIGHT);
		panel.setLayout(new FitLayout());
		panel.add(grid);
		add(panel);
		//add(grid);
//		grid.getAriaSupport().setLabelledBy(this.getHeader().getId() + "-label");			
	}
	
	public void setDisplayButtons(boolean visible) {
			btnAdd.setVisible(visible);
			btnModifer.setVisible(visible);
			btnSupprimer.setVisible(visible);
	}
	
	@SuppressWarnings("unchecked")
	private TreePanel<PerimetreTreeModel> getAdminTree() {
		TreePanel<PerimetreTreeModel> component = (TreePanel<PerimetreTreeModel>) ComponentManager.get().get(
				ConstantClient.ADMIN_TREE_ID);
		return component;
	}	
}
