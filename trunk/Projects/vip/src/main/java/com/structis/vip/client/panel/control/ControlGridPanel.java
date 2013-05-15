package com.structis.vip.client.panel.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ComponentManager;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableData;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.DeleteDelegationEvent;
import com.structis.vip.client.event.control.ControlFilterEvent;
import com.structis.vip.client.event.control.ControlFilterHandler;
import com.structis.vip.client.event.control.EditControleEvent;
import com.structis.vip.client.event.control.ListControleEvent;
import com.structis.vip.client.event.control.RefreshTreeEvent;
import com.structis.vip.client.event.control.RefreshTreeHandler;
import com.structis.vip.client.event.control.ViewControleEvent;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientControlServiceAsync;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.util.NameValuePair;
import com.structis.vip.client.util.ReportUtil;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.ControlFilter;
import com.structis.vip.shared.exception.DelegationException;
import com.structis.vip.shared.model.ControlModel;
import com.structis.vip.shared.model.ControlTypeModel;
import com.structis.vip.shared.model.ExtControllerControlModel;
import com.structis.vip.shared.model.KeyValueModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserModel;
import com.structis.vip.shared.model.UserRoleModel;

public class ControlGridPanel extends LayoutContainer {
	private final Messages messages = GWT.create(Messages.class);
	private SimpleEventBus bus;
	private UserModel currentUser;

	private Label resultLabel;
	private Button printButton;
	private Button ajouterButton;
	private ContentPanel main;
	private RpcProxy<PagingLoadResult<ControlModel>> proxy;
	private int pagingSize = ConstantClient.DEFAULT_PAGE_SIZE_50;
	private ControlFilter filter;
	private PagingLoader<PagingLoadResult<ControlModel>> loader;
	
	private ListStore<ControlModel> store = new ListStore<ControlModel>();
	private PagingToolBar toolBar;
	private SimpleComboBox<String> pageSizeCombobox;
	
	private List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
	private ColumnModel cm;
	
	private EditorGrid<ControlModel> grid;	
	private int totalRecord = 0;
	
	private ClientControlServiceAsync controlService = ClientControlServiceAsync.Util.getInstance();
	private PerimetreTreeModel perimetreTreeModel;
	
//	private ClientDelegationTypeServiceAsync delegationTypeService = ClientDelegationTypeServiceAsync.Util.getInstance();
//	private ClientLanguageServiceAsync languageService = ClientLanguageServiceAsync.Util.getInstance();
//	
//	private List<LanguageModel> languages = new ArrayList<LanguageModel>();
//
//	
//	private PerimetreModel perimetreModel;
//	private PerimetreTreeModel perimetreTreeModel;
//	private EntiteModel entiteModel;
//	
	
//	boolean isSuperUser = false;
	
	
	
	
	

	public ControlGridPanel(SimpleEventBus bus) {
		this.bus = bus;
		
		this.currentUser = SessionServiceImpl.getInstance().getUserContext();
//		if (currentUser != null) {
//			isSuperUser = currentUser.isSuperUser();
//		}
//		
//		languageService.getLanguages(new AsyncCallback<List<LanguageModel>>() {
//			
//			@Override
//			public void onSuccess(List<LanguageModel> arg0) {
//				languages = new ArrayList<LanguageModel>();
//				languages.addAll(arg0);
//			}
//			
//			@Override
//			public void onFailure(Throwable arg0) {
//			}
//		});
	}
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		
		setLayoutOnChange(true);
		setLayout(new FitLayout());
		
		main = new ContentPanel();
		main.setHeaderVisible(false);
		main.setBodyBorder(false);
		main.setBorders(true);
		main.setScrollMode(Scroll.AUTO);
		
		main.setLayout(new RowLayout(Orientation.HORIZONTAL));
		
		initTop();
		initGrid();
		addHandler();
		
		add(main);
	}

	private void initTop() {
		// result label
		resultLabel = new Label("0 " + messages.commonControls() + " "); // set as default value
		resultLabel.setStyleAttribute("border-right", "1px solid #99BBE8");
		
		// print button
		printButton = new Button(messages.controldetailbuttonprinter());
		printButton.setIcon(IconHelper.createPath("html/print-icon.png"));
		
		// new delegation button
		ajouterButton = new Button(messages.commonAjouterControle());
		ajouterButton.setIcon(IconHelper.createPath("html/add-icon.png"));
		
		ContentPanel c = new ContentPanel();
		c.setHeaderVisible(false);
		c.setBorders(false);
		c.setBodyBorder(true);

		TableLayout tableLayout = new TableLayout(2);
		tableLayout.setCellPadding(2);
		tableLayout.setCellSpacing(2);
		tableLayout.setWidth("100%");
		tableLayout.setCellVerticalAlign(VerticalAlignment.MIDDLE);
		
		c.setLayout(tableLayout);
		
		HorizontalPanel h = new HorizontalPanel();
		h.setWidth(250);
		h.setSpacing(5);
		h.add(resultLabel);
		h.add(printButton);
		
		c.add(h, new TableData(HorizontalAlignment.LEFT, VerticalAlignment.MIDDLE)); 
		c.add(ajouterButton, new TableData(HorizontalAlignment.RIGHT, VerticalAlignment.MIDDLE));
		
		main.setTopComponent(c);
	}

	private void initGrid() {
		proxy = new RpcProxy<PagingLoadResult<ControlModel>>() {
			@Override
			public void load(Object loadConfig, final AsyncCallback<PagingLoadResult<ControlModel>> callback) {
				ControlFilter newFilter = (ControlFilter) loadConfig;
				if (newFilter != null && newFilter.getEntite() != null) {
					main.mask(messages.commonloadingdata());
					
					newFilter.setLimit(newFilter.getOffset() + pagingSize);
					
					controlService.getControlsWithPaging(newFilter, new AsyncCallback<PagingLoadResult<ControlModel>>(){
						@Override
						public void onFailure(Throwable arg0) {
							callback.onFailure(arg0);
							main.unmask();
						}

						@Override
						public void onSuccess(PagingLoadResult<ControlModel> arg0) {
							callback.onSuccess(arg0);
							totalRecord = loader.getTotalCount();
							resultLabel.setText(totalRecord + " " + messages.commonControls() + " ");
							
							toolBar.getItem(9).setEnabled(true);
							
							main.unmask();
						}
					});
				}
			}
		};
		
		loader = new BasePagingLoader<PagingLoadResult<ControlModel>>(proxy) {
			protected Object newLoadConfig() {
				return filter;
			};
		};
		loader.setRemoteSort(true);
		
		store = new ListStore<ControlModel>(loader);
		
		toolBar = new PagingToolBar(ConstantClient.DEFAULT_PAGE_SIZE_50);
		toolBar.setHeight(0);
		toolBar.bind(loader);
		
		pageSizeCombobox = new SimpleComboBox<String>();
		pageSizeCombobox.setWidth(70);
		pageSizeCombobox.add(AppUtil.getPagingValue());
		pageSizeCombobox.setSimpleValue(ConstantClient.DEFAULT_PAGE_SIZE_50 + "");
		pageSizeCombobox.setTriggerAction(TriggerAction.ALL);
		
		//add combobox, position in PagingToolBar
		toolBar.insert(pageSizeCombobox, 9);
		toolBar.bind(loader);

		columns = getListColumn();
		cm = new ColumnModel(columns);
		
		grid = new EditorGrid<ControlModel>(store, cm);
		grid.setStateId("pagingGridControl");  
		grid.setStateful(true);  

//		grid.addListener(Events.Attach, new Listener<GridEvent<DelegationModel>>() {
//			public void handleEvent(GridEvent<DelegationModel> be) {
//				if (filter != null) {
//					filter.setOffset(0);  
//					filter.setLimit(50);  
//			          
//			        Map<String, Object> state = grid.getState();  
//			        if (state.containsKey("offset")) {  
//			          int offset = (Integer)state.get("offset");  
//			          int limit = (Integer)state.get("limit");  
//			          filter.setOffset(offset);  
//			          filter.setLimit(limit);  
//			        }  
//			        if (state.containsKey("sortField")) {  
//			        	filter.setSortField((String)state.get("sortField"));  
//			        	filter.setSortDir(SortDir.valueOf((String)state.get("sortDir")));  
//			        }
//				}
//			}
//		});
		
		grid.setColumnLines(true);
		grid.setAutoHeight(true);
		grid.setBorders(false);
		grid.setStripeRows(true);
		grid.setSelectionModel(new GridSelectionModel<ControlModel>());
		grid.getView().setAutoFill(true);
		grid.getView().setForceFit(true);
		WindowResizeBinder.bind(grid);
		
		main.add(grid, new RowData(1, 1));
		main.setBottomComponent(toolBar);
	}
	
	private void addHandler() {
//		// add handler for Filter button		
				
		bus.addHandler(ControlFilterEvent.getType(), new ControlFilterHandler() {
			@Override
			public void onLoadAction(final ControlFilterEvent event) {
				perimetreTreeModel = event.getPerimetreTreeModel();
				if (perimetreTreeModel != null) {
					ajouterButton.setVisible(perimetreTreeModel.getIsModificationControl());
				} 
				
				filter = buildFilter(event);
				filter.setUserRoles(SessionServiceImpl.getInstance().getUserContext().getUserRoles());
				
				toolBar.setPageSize(event.getPageSize());
				
				
				Map<String, Object> state = grid.getState();
				if (state.containsKey("offset")) {
					int offset = (Integer) state.get("offset");
					filter.setOffset(offset);
				} else {
					filter.setOffset(0);
				}
				
				if (state.containsKey("limit")) {
					int limit = (Integer) state.get("limit");
					if (limit != event.getPageSize()) {
						filter.setLimit(event.getPageSize());
						filter.setOffset(0);
					} else {
						filter.setLimit(limit);
					}
				} else {
					filter.setLimit(ConstantClient.DEFAULT_PAGE_SIZE_50);
				}
				
				if (state.containsKey("sortField")) {  
		        	filter.setSortField((String)state.get("sortField"));  
		        	filter.setSortDir(SortDir.valueOf((String)state.get("sortDir")));  
		        }
				filter.setPerimetreTreeModel(perimetreTreeModel);
				
				loader.load(filter);				
			}
		});
		// add listner for new delegation button
		ajouterButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				openNewControle();
				
			}
		});
		
		printButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				exportToCSV();
			}
		});

	}
	
	

	private List<ColumnConfig> getListColumn() {
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		ColumnConfig column = new ColumnConfig();
//		column.setHeader("Entité");
//		column.setId("perimeter.name");
//		column.setRowHeader(true);
//		column.setResizable(true);
//		column.setWidth(220);
//		configs.add(column);
//
//		column = new ColumnConfig();
//		column.setHeader("UO");
//		column.setId("delegationNature.name");
//		column.setResizable(true);
//		column.setWidth(150);
//		configs.add(column);
//
//		column = new ColumnConfig();
//		column.setHeader("Direction Du production");
//		column.setId("delegant.fullname");
//		column.setResizable(true);
//		column.setWidth(150);
//		configs.add(column);
//		
//		column = new ColumnConfig();
//		column.setHeader("Service");
//		column.setId("delegataire.fullname");
//		column.setResizable(true);
//		column.setWidth(150);
//		configs.add(column);

//		column = new ColumnConfig();
		column.setHeader(messages.controlgridlibelleduchantier());		
		column.setId("perimetre.name");
		column.setResizable(true);
		column.setWidth(90);
		configs.add(column);

//		column = new ColumnConfig();
//		column.setHeader(messages.controlgridciduchantier());		
//		column.setId("CI du Chantier");
//		column.setResizable(true);
//		column.setWidth(90);
//		configs.add(column);
		
		column = new ColumnConfig();
		column.setHeader(messages.controlgridcicodeprojet());		
		column.setId("codeProject");
		column.setResizable(true);
		column.setWidth(90);
		configs.add(column);
		
		column = new ColumnConfig();
		column.setHeader(messages.controlgridtypedecontrole());
		column.setId("controlType.label");		
		column.setResizable(true);
		column.setWidth(150);
		configs.add(column);

		column = new ColumnConfig();
		column.setDateTimeFormat(DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT));
		column.setHeader(messages.controlgriddateducontrole());
		column.setId("date");
		column.setResizable(true);
		column.setWidth(100);
		configs.add(column);
		
		GridCellRenderer<ControlModel> controllerInternRender = new GridCellRenderer<ControlModel>() {			
			@Override
			public Object render(final ControlModel model, String property,
					com.extjs.gxt.ui.client.widget.grid.ColumnData config,
					int rowIndex, int colIndex,
					ListStore<ControlModel> store,					
					Grid<ControlModel> grid) {
						String fullName = model.getCollaborateur() == null ? "" : model.getCollaborateur().getFullname(); 
						return new HTML(fullName);
			}
		};
		column = new ColumnConfig();
		column.setHeader(messages.controlgridnomducontroleurinterne());
		column.setId("collaborateur.fullname");
		column.setRenderer(controllerInternRender);
		column.setResizable(true);
		column.setWidth(150);
		configs.add(column);
				
		GridCellRenderer<ControlModel> controllerExternRender = new GridCellRenderer<ControlModel>() {			
			@Override
			public Object render(final ControlModel model, String property,
					com.extjs.gxt.ui.client.widget.grid.ColumnData config,
					int rowIndex, int colIndex,
					ListStore<ControlModel> store,					
					Grid<ControlModel> grid) {
						//for ()
						return new HTML(model.getExtControlNames());
						//return model.getExternalController().getLastname() + " " + model.getExternalController().getFirstname();
				
			}
		};
		
		column = new ColumnConfig();
		column.setHeader(messages.controlgridnomducontroleurexterne());
		column.setId("externController");
		column.setRenderer(controllerExternRender);
		column.setResizable(true);
		column.setWidth(150);
		configs.add(column);		
		
		column = new ColumnConfig();
		
		GridCellRenderer<ControlModel> actionRender = new GridCellRenderer<ControlModel>() {
			
			@Override
			public Object render(final ControlModel model, String property,
					ColumnData config, int rowIndex, int colIndex,
					ListStore<ControlModel> store, Grid<ControlModel> pGrid) {
				return buildActionColumn(model);
			}
		};

		column.setHeader(messages.action());
		column.setId("action");
		column.setResizable(true);
		column.setWidth(120);
		column.setRenderer(actionRender);
		configs.add(column);
		
		column = new ColumnConfig();
		column.setId("id");
		column.setHidden(true);
		configs.add(column);

		return configs;
	}
	
	private Object buildActionColumn(final ControlModel model) {
		final Button btn = new Button();
		btn.setText(messages.action());
		Menu menu = new Menu();
		
		MenuItem menuItem1 = new MenuItem(messages.controlactionconsulter());
		menuItem1.addSelectionListener(new ActionMenu(1, model));		
		
		MenuItem menuItem2 = new MenuItem(messages.controlactionmodifier());
		menuItem2.addSelectionListener(new ActionMenu(2, model));
		
//		MenuItem menuItem3 = new MenuItem(messages.controlactionexporter());
//		menuItem3.addSelectionListener(new ActionMenu(3, model));
		
		MenuItem menuItem4 = new MenuItem(messages.controlactionimprimer());
		menuItem4.addSelectionListener(new ActionMenu(3, model));
		
		MenuItem menuItem5 = new MenuItem(messages.controlactionsupprimer());
		menuItem5.addSelectionListener(new ActionMenu(4, model));				
						
		// consulter for all role and status
		menu.add(menuItem1); // view
		if ((model.getCanModified() != null) && (model.getCanModified())) {
			menu.add(menuItem2); // edit
		}
		
		menu.add(menuItem4); //print
		if ((model.getCanModified() != null) && (model.getCanModified())) {
			menu.add(menuItem5); // delete
		}
				
		
		btn.setMenu(menu);

		return btn;
	}
	
	private ControlFilter buildFilter(ControlFilterEvent event) {
		ControlFilter filter = new ControlFilter();
		filter.setEntite(event.getEntiteModel());
		PerimetreModel perimetreModel = new PerimetreModel(); 
		if (event.getPerimetreTreeModel() != null) {
			perimetreModel.setPerId(event.getPerimetreTreeModel().getPerId());
			perimetreModel.setName(event.getPerimetreTreeModel().getName());
		}
		filter.setPerimetre(perimetreModel);		
					
		List<ControlTypeModel> types = new ArrayList<ControlTypeModel>();
		if (event.getTypeModel() != null && !event.getTypeModel().isEmpty()) {
			for (ControlTypeModel i: event.getTypeModel()) {
				if (i.getId() == 0 || i.getId() == null) {
					types = new ArrayList<ControlTypeModel>();
					break;
				}
				types.add(i);
			}
		}
		filter.setTypes(types);		
		
		filter.setStartDate(event.getStartDate());
		filter.setEndDate(event.getEndDate());		
		filter.setPerimetreTreeModel(event.getPerimetreTreeModel());
		filter.setCodeProject(event.getCodeProject());
		List<KeyValueModel> caracteres = new ArrayList<KeyValueModel>();
		if (event.getCaracteres() != null && !event.getCaracteres().isEmpty()) {
			for (KeyValueModel i: event.getCaracteres()) {
				if (i.getKey() == null || i.getKey() == "") {
					caracteres = new ArrayList<KeyValueModel>();
					break;
				}
				caracteres.add(i);
			}
		}
		filter.setCaracteres(caracteres);
		filter.setControllerName(event.getControllerName());
		return filter;
	}
	
	private class ActionMenu extends SelectionListener<MenuEvent> {
		private int index;
		private ControlModel model;
		private Listener<MessageBoxEvent> listener;
		private ClientControlServiceAsync delegationService = ClientControlServiceAsync.Util.getInstance();
		
		public ActionMenu(int index, final ControlModel model) {
			this.index = index;
			this.model = model;
			
			listener = new Listener<MessageBoxEvent>() {
				public void handleEvent(MessageBoxEvent ce) {
					Button btn = ce.getButtonClicked();
					String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
					if (txtReturn.equals(btn.getText())) {
//						delegationService.delete(model, new AsyncCallback<Boolean>() {
//							@Override
//							public void onSuccess(Boolean arg0) {
//								Info.display(messages.commoninfo(), messages.statusmessagedeletesuccessfully());
//								DeleteDelegationEvent event = new DeleteDelegationEvent();
//								event.setModel(model);
//								bus.fireEvent(event);
//							}
//
//							@Override
//							public void onFailure(Throwable caught) {
//								String details = caught.getMessage();
//								if (caught instanceof DelegationException) {
//									details = ExceptionMessageHandler.getErrorMessage(((DelegationException) caught).getCode());
//								}
//								Info.display(messages.commonerror(), details);
//							}
//						});
					} else {
					}
				}
			};
		}

		@Override
		public void componentSelected(MenuEvent ce) {
			switch (index) {
			case 1: // consulter
				openViewControl(model);
				break;
				
			case 2: // modifier
				openEditControle(model);
				break;
				
//			case 3: // exporter
//				Window.alert("exporter");				
//				break;

			case 3: // imprimer
				String reportUrl = GWT.getHostPageBaseURL() + ".printControlRapportServlet";
				List<NameValuePair> values = new ArrayList<NameValuePair>();
				values.add(new NameValuePair("fileName", model.getRapport()));
				values.add(new NameValuePair("isPrint", "true"));
				ReportUtil.showReport(reportUrl, values.toArray(new NameValuePair[0]));
				//Window.alert("imprimer");				
				break;
				
			case 4: // supprimer				
				deleteControle(model);
				break;
			}
		}
	}

	public EditorGrid<ControlModel> getGrid() {
		return grid;
	}
	
	public void openViewControl(ControlModel model) {
		ControlLeftPanel clp = (ControlLeftPanel)ComponentManager.get().get(ConstantClient.CONTROL_TREE_PANEL_ID);		
		PerimetreTreeModel ptModel = clp.getSelectedPerimetreTreeModel();				
		ViewControleEvent controlEvent = new ViewControleEvent();
		controlEvent.setPerimetreTreeModel(ptModel);		
		controlEvent.setControlModel(model);
		bus.fireEvent(controlEvent);
	}

	public void deleteControle(final ControlModel model) {
		AppUtil.showConfirmMessageBox(messages.commonDeleteMessage(model.getPerimetre().getName()+ ", " + model.getCodeProject()), new Listener<MessageBoxEvent>() {
			@Override
			public void handleEvent(MessageBoxEvent be) {
				if (be.getButtonClicked().getText().equalsIgnoreCase(messages.commonDialogOuiButton()) ) {
					controlService.delete(model, new AsyncCallback<Boolean>() {
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

	public void openEditControle(ControlModel model) {
		ControlLeftPanel clp = (ControlLeftPanel)ComponentManager.get().get(ConstantClient.CONTROL_TREE_PANEL_ID);		
		PerimetreTreeModel ptModel = clp.getSelectedPerimetreTreeModel();				
		EditControleEvent controlEvent = new EditControleEvent();
		controlEvent.setPerimetreTreeModel(ptModel);		
		controlEvent.setControlModel(model);
		bus.fireEvent(controlEvent);		
	}

	private void openNewControle() {
		
		ControlLeftPanel clp = (ControlLeftPanel)ComponentManager.get().get(ConstantClient.CONTROL_TREE_PANEL_ID);		
		final PerimetreTreeModel ptModel = clp.getSelectedPerimetreTreeModel();
		ClientPerimetreServiceAsync.Util.getInstance().findById(ptModel.getPerId(), new AsyncCallback<PerimetreModel>() {
			@Override
			public void onSuccess(PerimetreModel arg0) {
				PerimetreModel pm = arg0;
						
				ControlModel cm = new ControlModel();
				cm.setId(null);
				cm.setPerimetre(pm);
				cm.setPerimetreParent(ptModel.getParentName());
				
				EditControleEvent controlEvent = new EditControleEvent();
				controlEvent.setPerimetreTreeModel(ptModel);
				
				controlEvent.setControlModel(cm);
				bus.fireEvent(controlEvent);
			}

			@Override
			public void onFailure(Throwable arg0) {
			}
		});
		
			
	}

	public void refresh(ControlModel control) {	
		control.updateExtControllerNames();
		if (store.contains(control)) {			
			store.update(control);
		} else {
			store.add(control);
		}
	}
	
	protected void exportToCSV() {
		String exportUrl = GWT.getHostPageBaseURL() + ".exportCSVServlet";
		List<NameValuePair> values = new ArrayList<NameValuePair>();
		values.add(new NameValuePair("codeProject", filter.getCodeProject() == null ? "" : filter.getCodeProject()));
		values.add(new NameValuePair("controllerName", filter.getControllerName() == null ? "" : filter.getControllerName()));
		values.add(new NameValuePair("caracteres", listToString(filter.getCaracteres(),',', KeyValueModel.VALUE)));
		
		values.add(new NameValuePair("endDate", filter.getEndDate()!= null?DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).format(filter.getEndDate()):""));
		values.add(new NameValuePair("startDate",  filter.getStartDate()!= null?DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).format(filter.getStartDate()):""));
		values.add(new NameValuePair("entId", ""+filter.getEntite().getEntId()));
		values.add(new NameValuePair("perId", ""+filter.getPerimetre().getPerId()));
		values.add(new NameValuePair("controlTypes", listToString( filter.getTypes(), ',', ControlTypeModel.CON_ID)));
		values.add(new NameValuePair("userId", ""+SessionServiceImpl.getInstance().getUserContext().getId()));
		
		ReportUtil.showReport(exportUrl, values.toArray(new NameValuePair[9]));
	}
	private <E> String listToString(List<E> input, char sep, String col) {
		StringBuffer ret = new StringBuffer();
		for (E in: input) {
			ret.append(sep).append(((ModelData)in).get(col));
		}
		if (ret.length() > 0) {
			return ret.substring(1);
		} else 
			return ret.toString();
	}
	
	
}
