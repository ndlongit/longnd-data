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
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
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
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.LoadDocumentHandler;
import com.structis.vip.client.event.ModifyChantierTypeEvent;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientChantierTypeServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.exception.ChantierTypeException;
import com.structis.vip.shared.model.ChantierTypeModel;

public class ChantierTypeListPanel extends LayoutContainer {
	private final Messages messages = GWT.create(Messages.class);
	private final int WIDTH = 800;
	private final int HEIGHT = 480;

	private SimpleEventBus bus;
	private ListStore<ChantierTypeModel> store = new ListStore<ChantierTypeModel>();
	
	private Button btnAdd;
	private Button btnModifer;
	private Button btnSupprimer;
	private Grid<ChantierTypeModel> grid;
	private ColumnModel columnModel;
	private PagingLoader<PagingLoadResult<ChantierTypeModel>> loader;
	private PagingModelMemoryProxy proxy;

	private ClientChantierTypeServiceAsync clientChantierTypeService = ClientChantierTypeServiceAsync.Util.getInstance();

	public ChantierTypeListPanel(SimpleEventBus bus) {
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
		// add BYTP
//		if (ConstantClient.ENTITE_ID_IS_BYEFE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
		if (CommonUtils.belongsBYEFEGroup(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
			columnModel.setHidden(1, false);
		} else {
			columnModel.setHidden(1, true);
		}
		
		store.removeAll();
		grid.mask(messages.commonloadingdata());
		clientChantierTypeService.findChantierByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(), new AsyncCallback<List<ChantierTypeModel>>() {
					@Override
					public void onSuccess(List<ChantierTypeModel> arg0) {
						proxy.setData(arg0);
						loader.load(0, 50);
						store = new ListStore<ChantierTypeModel>(loader);
						grid.unmask();
					}

					@Override
					public void onFailure(Throwable arg0) {
						grid.unmask();
					}
				});
	}
	
	private void initEvent() {
		grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<ChantierTypeModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<ChantierTypeModel> se) {
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
					final ChantierTypeModel model = grid.getSelectionModel().getSelectedItem();
					clientChantierTypeService.delete(model, new AsyncCallback<Boolean>() {
						@Override
						public void onSuccess(Boolean arg0) {
							initData();
							Info.display(messages.commoninfo(), messages.chantiertypemessagedeletesuccessfully());
						}

						@Override
						public void onFailure(Throwable caught) {
							String details = caught.getMessage();
							if (caught instanceof ChantierTypeException) {
								details = ExceptionMessageHandler.getErrorMessage(((ChantierTypeException) caught).getCode());
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
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CHANTIER_TYPE_CREATE_FORM);
				ModifyChantierTypeEvent subEvent = new ModifyChantierTypeEvent();
				subEvent.setModel(null);
				event.setEvent(subEvent);
				bus.fireEvent(event);
			}
		});
		
		btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ContentEvent event = new ContentEvent();
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CHANTIER_TYPE_CREATE_FORM);
				ModifyChantierTypeEvent subEvent = new ModifyChantierTypeEvent();
				subEvent.setModel(grid.getSelectionModel().getSelectedItem());
				event.setEvent(subEvent);
				bus.fireEvent(event);
			}
		});
		
		btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				MessageBox box = new MessageBox();
				box.setButtons(MessageBox.YESNO);
				box.setIcon(MessageBox.INFO);
				box.setTitle(messages.commonConfirmation());
				box.addCallback(l);
				box.setMessage(messages.delegationmodeldeleteconfirm());
				((Button) box.getDialog().getButtonBar().getItem(0)).setText(messages.commonOui());
				((Button) box.getDialog().getButtonBar().getItem(1)).setText(messages.commonNon());
				box.show();
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

		ColumnConfig label = new ColumnConfig(ChantierTypeModel.CTY_LABEL, messages.chantiertypelabel(), 200);
		ColumnConfig endDate = new ColumnConfig(ChantierTypeModel.CTY_ENDDATE, messages.chantiertypeendDate(), 150);
		
		GridCellRenderer<ChantierTypeModel> endDateRenderer = new GridCellRenderer<ChantierTypeModel>() {
			@Override
			public Object render(final ChantierTypeModel model, String property,
					ColumnData config, int rowIndex, int colIndex,
					final ListStore<ChantierTypeModel> store, Grid<ChantierTypeModel> grid) {
				final Label lbl = new Label();
				if (model.getEndDate() != null) {
					lbl.setText(DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT_DDMM).format(model.getEndDate()));
				}
				return lbl;
			}
		};
		endDate.setRenderer(endDateRenderer);
		
		ColumnConfig isSubdelegable = new ColumnConfig(ChantierTypeModel.CTY_IS_SUBDELEGABLE, messages.chantiertypechantierSubdelegable(), 150);

		GridCellRenderer<ChantierTypeModel> isSubdelegableRenderer = new GridCellRenderer<ChantierTypeModel>() {
			@Override
			public Object render(final ChantierTypeModel model, String property,
					ColumnData config, int rowIndex, int colIndex,
					final ListStore<ChantierTypeModel> store, Grid<ChantierTypeModel> grid) {
				final Label lbl = new Label();
				if (model.getIsSubdelegable() != null) {
					lbl.setText((model.getIsSubdelegable() == 1) ? messages.commonOui() : messages.commonNon());
				}
				return lbl;
			}
		};
		isSubdelegable.setRenderer(isSubdelegableRenderer);
		
		proxy = new PagingModelMemoryProxy(new ArrayList<ChantierTypeModel>());
		loader = new BasePagingLoader<PagingLoadResult<ChantierTypeModel>>(proxy);
		loader.setRemoteSort(true);
		store = new ListStore<ChantierTypeModel>(loader);
		toolBar.bind(loader);
		loader.load(0, 50);
		
		List<ColumnConfig> config = new ArrayList<ColumnConfig>();
		config.add(label);
		config.add(endDate);
		config.add(isSubdelegable);

		columnModel = new ColumnModel(config);

		grid = new Grid<ChantierTypeModel>(store, columnModel);
		
		GridFilters filters = new GridFilters();  
		filters.setLocal(true);
		StringFilter nameFilter = new StringFilter(ChantierTypeModel.CTY_LABEL); 
		filters.addFilter(nameFilter); 
		
		grid.setBorders(true);
		grid.addPlugin(filters);
		grid.setLoadMask(true);
		grid.getView().setAutoFill(true);
		grid.getView().setForceFit(true);
		WindowResizeBinder.bind(grid);
		
		ContentPanel panel = new ContentPanel();
		panel.setHeading(messages.chantiertypelistedeschantiers());
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
