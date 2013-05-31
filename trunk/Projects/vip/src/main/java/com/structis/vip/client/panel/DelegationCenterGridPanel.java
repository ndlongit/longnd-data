package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SortDir;
import com.extjs.gxt.ui.client.Style.VerticalAlignment;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.dialog.PrintDocumentDialog;
import com.structis.vip.client.dialog.RenewDelegationDialog;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationEvent;
import com.structis.vip.client.event.DelegationFilterEvent;
import com.structis.vip.client.event.DelegationFilterHandler;
import com.structis.vip.client.event.DelegationGridProjectEvent;
import com.structis.vip.client.event.DelegationGridProjectHandler;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.DelegationPagingEvent;
import com.structis.vip.client.event.DelegationTreeEvent;
import com.structis.vip.client.event.DelegationTreeHandler;
import com.structis.vip.client.event.DeleteDelegationEvent;
import com.structis.vip.client.event.RenewDelegationEvent;
import com.structis.vip.client.event.RenewDelegationHandler;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.ConstantMessages;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDelegationServiceAsync;
import com.structis.vip.client.service.ClientDelegationTypeServiceAsync;
import com.structis.vip.client.service.ClientLanguageServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.util.NameValuePair;
import com.structis.vip.client.util.ReportUtil;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.DelegationFilter;
import com.structis.vip.shared.SharedConstant;
import com.structis.vip.shared.exception.DelegationException;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.DelegationModel;
import com.structis.vip.shared.model.DelegationNatureModel;
import com.structis.vip.shared.model.DelegationStatusModel;
import com.structis.vip.shared.model.DelegationTypeModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.LanguageModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.PerimetreTypeModel;
import com.structis.vip.shared.model.UserModel;

public class DelegationCenterGridPanel extends LayoutContainer {

    private final Messages messages = GWT.create(Messages.class);
    
    private static final ConstantMessages config = GWT.create(ConstantMessages.class);

    private ListStore<DelegationModel> store = new ListStore<DelegationModel>();

    private SimpleEventBus bus;
    private EditorGrid<DelegationModel> grid;
    private PagingToolBar toolBar;
    private SimpleComboBox<String> pageSizeCombobox;
    private ColumnModel cm;

    private PagingLoader<PagingLoadResult<DelegationModel>> loader;
    private RpcProxy<PagingLoadResult<DelegationModel>> proxy;

    private ClientDelegationServiceAsync delegationService = ClientDelegationServiceAsync.Util.getInstance();
    private ClientDelegationTypeServiceAsync delegationTypeService = ClientDelegationTypeServiceAsync.Util.getInstance();
    private ClientLanguageServiceAsync languageService = ClientLanguageServiceAsync.Util.getInstance();

    private List<LanguageModel> languages = new ArrayList<LanguageModel>();

    private Label resultLabel;
    private Button printButton;
    private Button ajouterButton;

    private PerimetreModel perimetreModel;
    private PerimetreTreeModel perimetreTreeModel;
    private EntiteModel entiteModel;

    private UserModel currentUser;
    boolean isSuperUser = false;
    private ContentPanel main;
    private int totalRecord = 0;
    private int pagingSize = ClientConstant.DEFAULT_PAGE_SIZE_50;

    private DelegationFilter filter;

    public DelegationCenterGridPanel(SimpleEventBus bus) {
        this.bus = bus;

        this.currentUser = SessionServiceImpl.getInstance().getUserContext();
        if (this.currentUser != null) {
            this.isSuperUser = this.currentUser.isSuperUser();
        }

        this.languageService.getLanguages(new AsyncCallbackWithErrorResolution<List<LanguageModel>>() {

            @Override
            public void onSuccess(List<LanguageModel> arg0) {
                DelegationCenterGridPanel.this.languages = new ArrayList<LanguageModel>();
                DelegationCenterGridPanel.this.languages.addAll(arg0);
            }
        });
    }

    @Override
    protected void onRender(Element parent, int index) {
        GWT.log(this.getClass().getName() + ":onRender");
        super.onRender(parent, index);

        this.setLayoutOnChange(true);
        this.setLayout(new FitLayout());

        this.main = new ContentPanel();
        this.main.setHeaderVisible(false);
        this.main.setBodyBorder(false);
        this.main.setBorders(true);
        this.main.setScrollMode(Scroll.AUTO);

        this.main.setLayout(new RowLayout(Orientation.HORIZONTAL));

        this.initTop();
        this.initGrid();
        this.addHandler();

        this.add(this.main);
    }

    private void initTop() {
        // result label
        this.resultLabel = new Label("0 " + this.messages.commonDelegations() + " "); // set as default value
        this.resultLabel.setStyleAttribute("border-right", "1px solid #99BBE8");

        // print button
        this.printButton = new Button(this.messages.delegationdetailbuttonprinter());
        this.printButton.setIcon(IconHelper.createPath("html/print-icon.png"));

        // new delegation button
        this.ajouterButton = new Button(this.messages.commonAjouterDelegationPrincipale());
        this.ajouterButton.setIcon(IconHelper.createPath("html/add-icon.png"));

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
        h.add(this.resultLabel);
        h.add(this.printButton);

        c.add(h, new TableData(HorizontalAlignment.LEFT, VerticalAlignment.MIDDLE));
        c.add(this.ajouterButton, new TableData(HorizontalAlignment.RIGHT, VerticalAlignment.MIDDLE));

        this.main.setTopComponent(c);
    }

    private void initGrid() {
        this.proxy = new RpcProxy<PagingLoadResult<DelegationModel>>() {

            @Override
            public void load(Object loadConfig, final AsyncCallback<PagingLoadResult<DelegationModel>> callback) {
                DelegationFilter newFilter = (DelegationFilter) loadConfig;
                if (newFilter != null && newFilter.getEntite() != null) {
                    DelegationCenterGridPanel.this.main.mask(DelegationCenterGridPanel.this.messages.commonloadingdata());

                    newFilter.setLimit(newFilter.getOffset() + DelegationCenterGridPanel.this.pagingSize);

                    DelegationCenterGridPanel.this.delegationService.getValidDelegationsByEntiteByPaging(newFilter,
                            new AsyncCallbackWithErrorResolution<PagingLoadResult<DelegationModel>>() {

                                @Override
                                public void onFailure(Throwable arg0) {
                                    callback.onFailure(arg0);
                                    DelegationCenterGridPanel.this.main.unmask();
                                }

                                @Override
                                public void onSuccess(PagingLoadResult<DelegationModel> arg0) {
                                    callback.onSuccess(arg0);

                                    DelegationCenterGridPanel.this.totalRecord = DelegationCenterGridPanel.this.loader.getTotalCount();
                                    DelegationCenterGridPanel.this.resultLabel.setText(DelegationCenterGridPanel.this.totalRecord + " "
                                            + DelegationCenterGridPanel.this.messages.commonDelegations() + " ");

                                    DelegationCenterGridPanel.this.toolBar.getItem(9).setEnabled(true);

                                    DelegationCenterGridPanel.this.main.unmask();
                                }
                            });
                }
            }
        };

        this.loader = new BasePagingLoader<PagingLoadResult<DelegationModel>>(this.proxy) {

            @Override
            protected Object newLoadConfig() {
                return DelegationCenterGridPanel.this.filter;
            };
        };
        this.loader.setRemoteSort(true);

        this.store = new ListStore<DelegationModel>(this.loader);

        this.toolBar = new PagingToolBar(ClientConstant.DEFAULT_PAGE_SIZE_50);
        this.toolBar.setHeight(0);
        this.toolBar.bind(this.loader);

        this.pageSizeCombobox = new SimpleComboBox<String>();
        this.pageSizeCombobox.setWidth(70);
        this.pageSizeCombobox.add(AppUtil.getPagingValue());
        this.pageSizeCombobox.setSimpleValue(ClientConstant.DEFAULT_PAGE_SIZE_50 + "");
        this.pageSizeCombobox.setTriggerAction(TriggerAction.ALL);

        // add combobox, position in PagingToolBar
        this.toolBar.insert(this.pageSizeCombobox, 9);
        this.toolBar.bind(this.loader);

        this.cm = new ColumnModel(this.getListColumn());

        this.grid = new EditorGrid<DelegationModel>(this.store, this.cm);
        this.grid.setStateId("pagingGridDelegation");
        this.grid.setStateful(true);

        this.grid.setColumnLines(true);
        this.grid.setAutoHeight(true);
        this.grid.setBorders(false);
        this.grid.setStripeRows(true);
        this.grid.setSelectionModel(new GridSelectionModel<DelegationModel>());
        this.grid.getView().setAutoFill(true);
        this.grid.getView().setForceFit(true);
        WindowResizeBinder.bind(this.grid);

        this.main.add(this.grid, new RowData(1, 1));
        this.main.setBottomComponent(this.toolBar);
    }

    private void addHandler() {
        this.printButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                final String reportUrl = GWT.getHostPageBaseURL() + ".printDelegationGridServiceServlet";
                final List<NameValuePair> values = new ArrayList<NameValuePair>();

                if (filter != null) {

                    delegationService.getDelegationIdsByEntite(filter, new AsyncCallbackWithErrorResolution<List<Integer>>() {

                        public void onSuccess(java.util.List<Integer> ids) {
                            DelegationCenterGridPanel.this.delegationService.getDelegationByIds(ids,
                                    new AsyncCallbackWithErrorResolution<List<DelegationModel>>() {

                                        @Override
                                        public void onSuccess(List<DelegationModel> arg0) {
                                            if (arg0.isEmpty() == false) {
                                                DelegationModel model;
                                                for (int i = 0; i < arg0.size(); i++) {
                                                    model = arg0.get(i);
                                                    values.add(new NameValuePair("delegationId", model.getId().toString()));
                                                }
                                            }

                                            String path = Window.Location.getHref();
                                            String paramName = "&locale=";

                                            String currentLanguage = null;

                                            if (path.lastIndexOf(paramName) != -1) {
                                                String currentLangCode = path.substring(path.lastIndexOf(paramName) + 8,
                                                        path.lastIndexOf(paramName) + 10);
                                                for (LanguageModel languageModel : DelegationCenterGridPanel.this.languages) {
                                                    if (languageModel.getCode().equalsIgnoreCase(currentLangCode)) {
                                                        currentLanguage = languageModel.getCode();
                                                        break;
                                                    }
                                                }
                                            } else {
                                                currentLanguage = ClientConstant.DEFAULT_LANGUAGE_CODE;
                                            }

                                            if (currentLanguage != null) {
                                                values.add(new NameValuePair("localeCode", currentLanguage));
                                            }

                                            ReportUtil.openNewWindow("", reportUrl, values);
                                        }
                                    });
                        };
                    });

                }
            }
        });

        // add handler to build grid
        this.bus.addHandler(DelegationGridProjectEvent.getType(), new DelegationGridProjectHandler() {

            @Override
            public void onLoadAction(DelegationGridProjectEvent event) {
                DelegationCenterGridPanel.this.disableEvents(true);
                DelegationCenterGridPanel.this.loader.load(0, ClientConstant.DEFAULT_PAGE_SIZE_50);
                DelegationCenterGridPanel.this.disableEvents(false);
            }
        });

        // add handler for Filter button
        this.bus.addHandler(DelegationFilterEvent.getType(), new DelegationFilterHandler() {

            @Override
            public void onLoadAction(final DelegationFilterEvent event) {
                DelegationCenterGridPanel.this.perimetreTreeModel = event.getPerimetreTreeModel();
                DelegationCenterGridPanel.this.ajouterButton.setVisible(DelegationCenterGridPanel.this.perimetreTreeModel
                        .getIsModificationDelegation());

                DelegationCenterGridPanel.this.filter = DelegationCenterGridPanel.this.buildFilter(event);
                DelegationCenterGridPanel.this.filter.setUserRoles(SessionServiceImpl.getInstance().getUserContext().getUserRoles());

                DelegationCenterGridPanel.this.toolBar.setPageSize(event.getPageSize());

                Map<String, Object> state = DelegationCenterGridPanel.this.grid.getState();
                if (state.containsKey("offset")) {
                    int offset = (Integer) state.get("offset");
                    DelegationCenterGridPanel.this.filter.setOffset(offset);
                } else {
                    DelegationCenterGridPanel.this.filter.setOffset(0);
                }

                if (state.containsKey("limit")) {
                    int limit = (Integer) state.get("limit");
                    if (limit != event.getPageSize()) {
                        DelegationCenterGridPanel.this.filter.setLimit(event.getPageSize());
                        DelegationCenterGridPanel.this.filter.setOffset(0);
                    } else {
                        DelegationCenterGridPanel.this.filter.setLimit(limit);
                    }
                } else {
                    DelegationCenterGridPanel.this.filter.setLimit(ClientConstant.DEFAULT_PAGE_SIZE_50);
                }

                if (state.containsKey("sortField")) {
                    DelegationCenterGridPanel.this.filter.setSortField((String) state.get("sortField"));
                    DelegationCenterGridPanel.this.filter.setSortDir(SortDir.valueOf((String) state.get("sortDir")));
                }

                DelegationCenterGridPanel.this.loader.load(DelegationCenterGridPanel.this.filter);
            }
        });

        // catch tree event to get selected perimeter
        this.bus.addHandler(DelegationTreeEvent.getType(), new DelegationTreeHandler() {

            @Override
            public void onLoadAction(DelegationTreeEvent event) {
                if (event.getTreeModel() != null) {
                    DelegationCenterGridPanel.this.perimetreModel = new PerimetreModel();
                    DelegationCenterGridPanel.this.perimetreModel.setPerId((String) event.getTreeModel().get("id"));
                    DelegationCenterGridPanel.this.perimetreModel.setName(event.getTreeModel().get("typeName").toString());

                    PerimetreTypeModel type = new PerimetreTypeModel();
                    type.setPtyId(event.getTreeModel().get("type").toString());
                    type.setName(event.getTreeModel().get("typeName").toString());

                    DelegationCenterGridPanel.this.perimetreModel.setType(type);

                }
            }
        });

        // catch event on Valider button to get perimeter and entite model
        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(DelegationListProjectEvent event) {
                DelegationCenterGridPanel.this.perimetreModel = event.getPerimetreModel();
                DelegationCenterGridPanel.this.entiteModel = event.getEntiteModel();
            }
        });

        // catch event on Renew delegation
        this.bus.addHandler(RenewDelegationEvent.getType(), new RenewDelegationHandler() {

            @Override
            public void onLoadAction(RenewDelegationEvent pevent) {
                DelegationEvent event = new DelegationEvent();
                if (pevent.getTypeRenew() == 0) {
                    event.setMode(DelegationEvent.MODE_IS_RENEW_DELEGATION);
                } else {
                    event.setMode(DelegationEvent.MODE_IS_REPLACE_DELEGANT_OR_DELEGATAIRE);
                }
                event.setDelegationId(pevent.getModel().getId());
                event.setDelegationModel(pevent.getModel());
                event.setEntiteModel(DelegationCenterGridPanel.this.entiteModel);

                ContentEvent contentEvent = new ContentEvent();
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_NEW_DELEGATION_FORM);

                contentEvent.setEvent(event);
                DelegationCenterGridPanel.this.bus.fireEvent(contentEvent);
            }
        });

        // add listner for new delegation button
        this.ajouterButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DelegationCenterGridPanel.this.delegationTypeService.getPrincipleType(new AsyncCallbackWithErrorResolution<DelegationTypeModel>() {

                    @Override
                    public void onSuccess(DelegationTypeModel arg0) {
                        // create new delegation
                        if ((DelegationCenterGridPanel.this.perimetreModel != null) && (DelegationCenterGridPanel.this.entiteModel != null)
                                && (arg0 != null)) {
                            DelegationEvent event = new DelegationEvent();
                            event.setDelegationTypeModel(arg0);
                            event.setPerimetreModel(DelegationCenterGridPanel.this.perimetreModel);
                            event.setEntiteModel(DelegationCenterGridPanel.this.entiteModel);
                            event.setMode(DelegationEvent.MODE_IS_NEW);

                            ContentEvent contentEvent = new ContentEvent();
                            contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_NEW_DELEGATION_FORM);
                            contentEvent.setEvent(event);
                            DelegationCenterGridPanel.this.bus.fireEvent(contentEvent);
                        }
                    }
                });
            }
        });

        this.grid.addListener(Events.RowDoubleClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                DelegationModel selectedModel = DelegationCenterGridPanel.this.grid.getSelectionModel().getSelectedItem();

                ContentEvent contentEvent = new ContentEvent();

                DelegationEvent subEvent = new DelegationEvent();

                subEvent.setDelegationId(selectedModel.getId());
                subEvent.setMode(DelegationEvent.MODE_IS_VIEW);
                subEvent.setEntiteModel(DelegationCenterGridPanel.this.entiteModel);
                subEvent.setIsModification(selectedModel.getIsModification());

                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_DETAIL_DELEGATION_FORM);
                contentEvent.setEvent(subEvent);

                DelegationCenterGridPanel.this.bus.fireEvent(contentEvent);
            }
        });

        this.pageSizeCombobox.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<SimpleComboValue<String>> se) {
                int result = 0;

                if (DelegationCenterGridPanel.this.pageSizeCombobox.getValue() == null
                        || DelegationCenterGridPanel.this.pageSizeCombobox.getValue().getValue() == null) {
                    result = ClientConstant.DEFAULT_PAGE_SIZE_50;
                } else {
                    String data = DelegationCenterGridPanel.this.pageSizeCombobox.getValue().getValue();
                    if (DelegationCenterGridPanel.this.messages.commonTous().equals(data)) {
                        result = DelegationCenterGridPanel.this.totalRecord;
                    } else {
                        result = Integer.parseInt(data);
                    }
                }
                DelegationCenterGridPanel.this.pagingSize = result;

                DelegationPagingEvent event = new DelegationPagingEvent();
                event.setPageSize(DelegationCenterGridPanel.this.pagingSize);
                DelegationCenterGridPanel.this.bus.fireEvent(event);
            }
        });
    }

    private List<ColumnConfig> getListColumn() {
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
        ColumnConfig column = null;

        //For development mode only
        if(SharedConstant.RunMode.DEVELOPMENT.value().equals(config.runMode())) {
//            column = new ColumnConfig("id", "Id", 60);
//            column.setRowHeader(true);
//            column.setResizable(true);
//            configs.add(column);
        }
        
        column = new ColumnConfig();
        column.setHeader(this.messages.perimetre());
        column.setId("perimeter.name");
        column.setRowHeader(true);
        column.setResizable(true);
        column.setWidth(220);
        configs.add(column);

        column = new ColumnConfig();
        column.setHeader(this.messages.nature());
        column.setId("delegationNature.name");
        column.setResizable(true);
        column.setWidth(150);
        configs.add(column);

        column = new ColumnConfig();
        column.setHeader(this.messages.delegant());
        column.setId("delegant.fullname");
        column.setResizable(true);
        column.setWidth(150);
        configs.add(column);

        GridCellRenderer<DelegationModel> delegataireRender = new GridCellRenderer<DelegationModel>() {

            @Override
            public Object render(final DelegationModel model, String property, ColumnData config, int rowIndex, int colIndex,
                    ListStore<DelegationModel> store, Grid<DelegationModel> pGrid) {
                return DelegationCenterGridPanel.this.buildDelegataireColumn(model);
            }
        };

        column = new ColumnConfig();
        column.setRenderer(delegataireRender);
        column.setHeader(this.messages.delegataire());
        column.setId("delegataire_fullname");
        column.setResizable(true);
        column.setWidth(150);
        configs.add(column);

        column = new ColumnConfig();
        column.setHeader(this.messages.debutdevalidite());
        column.setDateTimeFormat(DateTimeFormat.getFormat(ClientConstant.DATE_FORMAT));
        column.setId("startDate");
        column.setResizable(true);
        column.setWidth(90);
        configs.add(column);

        column = new ColumnConfig();
        column.setHeader(this.messages.findevalidite());
        column.setDateTimeFormat(DateTimeFormat.getFormat(ClientConstant.DATE_FORMAT));
        column.setId("endDate");
        column.setResizable(true);
        column.setWidth(90);
        configs.add(column);

        column = new ColumnConfig();
        column.setHeader(this.messages.delegationstatus());
        column.setId("delegationStatus.name");
        column.setResizable(true);
        column.setWidth(150);
        configs.add(column);

        column = new ColumnConfig();
        column.setHeader(this.messages.type());
        column.setId("delegationType.name");
        column.setResizable(true);
        column.setWidth(100);
        configs.add(column);

        column = new ColumnConfig();

        GridCellRenderer<DelegationModel> actionRender = new GridCellRenderer<DelegationModel>() {

            @Override
            public Object render(final DelegationModel model, String property, ColumnData config, int rowIndex, int colIndex,
                    ListStore<DelegationModel> store, Grid<DelegationModel> pGrid) {
                return DelegationCenterGridPanel.this.buildActionColumn(model);
            }
        };

        column.setHeader(this.messages.action());
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

    protected Object buildDelegataireColumn(final DelegationModel model) {
        HorizontalPanel container = new HorizontalPanel();
        final HTML html = new HTML(model.getDelegataire().getFullname());
        container.add(html);
        if (!SharedConstant.ENTITE_ID_ETDE.equals(this.entiteModel.getEntId())) {
            com.google.gwt.user.client.ui.Label viewAll = new com.google.gwt.user.client.ui.Label("...");

            viewAll.setStyleName("x-link-item");
            viewAll.addClickHandler(new ClickHandler() {

                @Override
                public void onClick(ClickEvent arg0) {
                    this.displayAllDelegataires(model.getId());
                }

                public void displayAllDelegataires(Integer delId) {
                    DelegationCenterGridPanel.this.delegationService.getDelegataires(delId, new AsyncCallbackWithErrorResolution<String>() {

                        @Override
                        public void onSuccess(String arg0) {
                            if (arg0 != null) {
                                arg0 = arg0.replace("|", "\n");
                                // html.setHTML(SafeHtmlUtils.fromString(arg0) );
                                html.setText(arg0);
                            }

                        }
                    });
                }
            });
            container.add(new Label(" "));
            container.add(viewAll);
        }

        return container;

    }

    private Object buildActionColumn(final DelegationModel model) {
        final Button btn = new Button();
        btn.setText(this.messages.action());
        Menu menu = new Menu();

        MenuItem menuItemView = new MenuItem(this.messages.delegationcomboboxaction1()); // view
        menuItemView.addSelectionListener(new ActionMenu(1, model));

        MenuItem menuItemModify = new MenuItem(this.messages.delegationcomboboxaction2()); // modify
        menuItemModify.addSelectionListener(new ActionMenu(2, model));

        MenuItem menuItemDelete = new MenuItem(this.messages.delegationcomboboxaction3()); // delete
        menuItemDelete.addSelectionListener(new ActionMenu(3, model));

        MenuItem menuItemPrintDocument = new MenuItem(this.messages.delegationcomboboxaction4()); // print document
        menuItemPrintDocument.addSelectionListener(new ActionMenu(4, model));

        MenuItem menuItemAddSignedDelegation = new MenuItem(this.messages.delegationcomboboxaction5()); // add a signed delegation
        menuItemAddSignedDelegation.addSelectionListener(new ActionMenu(5, model));

        MenuItem menuItemDeleteSignedDelegation = new MenuItem(this.messages.delegationcomboboxaction11()); // delete a signed delegation
        menuItemDeleteSignedDelegation.addSelectionListener(new ActionMenu(11, model));

        MenuItem menuItemAddDocument = new MenuItem(this.messages.delegationcomboboxaction6()); // add document
        menuItemAddDocument.addSelectionListener(new ActionMenu(6, model));

        MenuItem menuItemCreateSubDelegation = new MenuItem(this.messages.delegationcomboboxaction7()); // create sub delegation
        menuItemCreateSubDelegation.addSelectionListener(new ActionMenu(7, model));

        MenuItem menuItemReplaceDelegantDelegataire = new MenuItem(this.messages.delegationcomboboxaction8()); // replace delegant _ delegataire
        menuItemReplaceDelegantDelegataire.addSelectionListener(new ActionMenu(8, model));

        MenuItem menuItemRenewDelegation = new MenuItem(this.messages.delegationcomboboxaction9()); // renew delegation
        menuItemRenewDelegation.addSelectionListener(new ActionMenu(9, model));

        MenuItem menuItem10 = new MenuItem(this.messages.delegationcomboboxaction10()); // create temporary
        menuItem10.addSelectionListener(new ActionMenu(10, model));

        int statusId = model.getDelegationStatus().getId();
        int delegationType = model.getDelegationType().getId();

        // consulter for all role and status
        menu.add(menuItemView);

        if (statusId == ClientConstant.DELEGATION_STATUS_IS_P) {
            // modifier for status P
            if ((model.getIsModification() != null) && (model.getIsModification())) {
                // modifier for role creation/modification and all status
                menu.add(menuItemModify);
            }
        }

        if ((model.getIsModification() != null) && (model.getIsModification()) && (statusId == ClientConstant.DELEGATION_STATUS_IS_P)) {
            // supprimer for role creation/modification and status P
            menu.add(menuItemDelete);
        } else if ((model.getIsValidation() != null) && (model.getIsValidation())) {
            // supprimer for role validation and all status
            menu.add(menuItemDelete);
        }

        // imprimer document for all role and status
        menu.add(menuItemPrintDocument);

        if (statusId == ClientConstant.DELEGATION_STATUS_IS_P) {
            // ajouter une document for status P
            if ((model.getIsValidation() != null) && (model.getIsValidation())) {
                // ajouter une document for role validation
                menu.add(menuItemAddSignedDelegation);
            }
        }

        if (statusId == ClientConstant.DELEGATION_STATUS_IS_V || statusId == ClientConstant.DELEGATION_STATUS_IS_D) {
            // ajouter autre document for status V, D
            if ((model.getIsValidation() != null) && (model.getIsValidation())) {
                // ajouter autre document for role validation
                menu.add(menuItemAddDocument);
            }
        }
        if (statusId == ClientConstant.DELEGATION_STATUS_IS_D) {
            // ajouter autre document for status V, D
            if ((model.getIsValidation() != null) && (model.getIsValidation())) {
                // ajouter autre document for role validation
                menu.add(menuItemDeleteSignedDelegation);
            }
        }
        if (delegationType != ClientConstant.DELEGATION_TYPE_IS_SOUS_DELEGATION && delegationType != ClientConstant.DELEGATION_TYPE_IS_TEMPORAIRE) {
            // create sub delegation for only principle delegation
            if (statusId == ClientConstant.DELEGATION_STATUS_IS_D) {
                // create sub delegation for status D
                if (this.entiteModel != null && !SharedConstant.ENTITE_ID_ETDE.equalsIgnoreCase(this.entiteModel.getEntId())) {
                    // create sub delegation for all entite except ETDE
                    if ((model.getIsModification() != null) && (model.getIsModification())) {
                        // create sub delegation for creation/modification role
                        // if (model.getPerimeter().getChantierType() != null && model.getPerimeter().getChantierType().getIsSubdelegable() !=
                        // ConstantClient.CHANTIER_CLASSIC) {
                        // tdo 28 Jan
                        if (model.getPerimeter() != null && model.getPerimeter().getIsSubdelegable() != null) {
                            if (model.getPerimeter().getIsSubdelegable() > 0) {
                                menu.add(menuItemCreateSubDelegation);
                            }
                        } else {
                            if (model.getPerimeter().getType() != null && model.getPerimeter().getType().getIsSubdelegable() != null) {
                                if (model.getPerimeter().getType().getIsSubdelegable() > 0) {
                                    menu.add(menuItemCreateSubDelegation);
                                }
                            } else {
                                if (model.getPerimeter().getChantierType() != null
                                        && model.getPerimeter().getChantierType().getIsSubdelegable() != null) {
                                    if (model.getPerimeter().getChantierType().getIsSubdelegable() > 0) {
                                        menu.add(menuItemCreateSubDelegation);
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }

        if (delegationType != ClientConstant.DELEGATION_TYPE_IS_SOUS_DELEGATION && delegationType != ClientConstant.DELEGATION_TYPE_IS_TEMPORAIRE) {
            // replace delegant, delegataire for only principle delegation
            if (statusId == ClientConstant.DELEGATION_STATUS_IS_D) {
                // replace delegant, delegataire for status V, D
                if ((model.getIsModification() != null) && (model.getIsModification())) {
                    // replace delegant, delegataire for role creation/modification and all status
                    if ((model.getIsCanRenew() != null) && (model.getIsCanRenew())) {
                        menu.add(menuItemReplaceDelegantDelegataire);
                    }
                }
            }
        }

        if (delegationType != ClientConstant.DELEGATION_TYPE_IS_SOUS_DELEGATION && delegationType != ClientConstant.DELEGATION_TYPE_IS_TEMPORAIRE) {
            // renew delegation and create temporary delegation for only principle delegation
            if (statusId == ClientConstant.DELEGATION_STATUS_IS_D) {
                // renew delegation and create temporary delegation for status D
                Date today = new Date();
                if (model.getEndDate() == null || today.compareTo(model.getEndDate()) <= 0) {
                    // renew delegation for status V, D
                    if ((model.getIsModification() != null) && (model.getIsModification())) {
                        // renew delegation for creation/modification role
                        if ((model.getIsCanRenew() != null) && (model.getIsCanRenew())) {
                            menu.add(menuItemRenewDelegation);
                        }
                    }
                }
                if ((model.getIsModification() != null) && (model.getIsModification())) {
                    // create temporary delegation for creation/modification role
                    menu.add(menuItem10);
                }
            }
        }

        btn.setMenu(menu);

        return btn;
    }

    private DelegationFilter buildFilter(DelegationFilterEvent event) {
        DelegationFilter filter = new DelegationFilter();
        filter.setEntite(event.getEntiteModel());
        PerimetreModel perimetreModel = new PerimetreModel();
        perimetreModel.setPerId(event.getPerimetreTreeModel().getPerId());
        perimetreModel.setName(event.getPerimetreTreeModel().getName());
        filter.setPerimetre(perimetreModel);

        List<DelegationNatureModel> natures = new ArrayList<DelegationNatureModel>();
        if (event.getNatureModel() != null && !event.getNatureModel().isEmpty()) {
            for (DelegationNatureModel i : event.getNatureModel()) {
                if (i.getId() == 0 || i.getId() == null) {
                    natures = new ArrayList<DelegationNatureModel>();
                    break;
                }
                natures.add(i);
            }
        }
        filter.setNatures(natures);

        List<DelegationStatusModel> statuses = new ArrayList<DelegationStatusModel>();
        if (event.getStatusModel() != null && !event.getStatusModel().isEmpty()) {
            for (DelegationStatusModel i : event.getStatusModel()) {
                if (i.getId() == 0 || i.getId() == null) {
                    statuses = new ArrayList<DelegationStatusModel>();
                    break;
                }
                statuses.add(i);
            }
        }
        filter.setStatuses(statuses);

        List<DelegationTypeModel> types = new ArrayList<DelegationTypeModel>();
        if (event.getTypeModel() != null && !event.getTypeModel().isEmpty()) {
            for (DelegationTypeModel i : event.getTypeModel()) {
                if (i.getId() == 0 || i.getId() == null) {
                    types = new ArrayList<DelegationTypeModel>();
                    break;
                }
                types.add(i);
            }
        }
        filter.setTypes(types);

        List<CollaborateurModel> delegants = new ArrayList<CollaborateurModel>();
        if (event.getDelegant() != null && !event.getDelegant().isEmpty()) {
            for (CollaborateurModel i : event.getDelegant()) {
                if (i.getId() == 0 || i.getId() == null) {
                    delegants = new ArrayList<CollaborateurModel>();
                    break;
                }
                delegants.add(i);
            }
        }
        filter.setDelegants(delegants);

        List<CollaborateurModel> delegataires = new ArrayList<CollaborateurModel>();
        if (event.getDelegataire() != null && !event.getDelegataire().isEmpty()) {
            for (CollaborateurModel i : event.getDelegataire()) {
                if (i.getId() == 0 || i.getId() == null) {
                    delegataires = new ArrayList<CollaborateurModel>();
                    break;
                }
                delegataires.add(i);
            }
        }
        filter.setDelegataires(delegataires);

        filter.setStartDate(event.getStartDate());
        filter.setEndDate(event.getEndDate());
        filter.setSep(event.getSep());
        filter.setConjointe(event.getConjointe());
        filter.setIsDisplayAllLevel(event.getShowLevel());
        filter.setPerimetreTreeModel(event.getPerimetreTreeModel());

        return filter;
    }

    private class ActionMenu extends SelectionListener<MenuEvent> {

        private int index;
        private DelegationModel model;
        private Listener<MessageBoxEvent> listener;
        private ClientDelegationServiceAsync delegationService = ClientDelegationServiceAsync.Util.getInstance();

        public ActionMenu(int index, final DelegationModel model) {
            this.index = index;
            this.model = model;

            this.listener = new Listener<MessageBoxEvent>() {

                @Override
                public void handleEvent(MessageBoxEvent ce) {
                    Button btn = ce.getButtonClicked();
                    String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                    if (txtReturn.equals(btn.getText())) {
                        ActionMenu.this.delegationService.delete(model, new AsyncCallbackWithErrorResolution<Boolean>() {

                            @Override
                            public void onSuccess(Boolean arg0) {
                                Info.display(DelegationCenterGridPanel.this.messages.commoninfo(),
                                        DelegationCenterGridPanel.this.messages.statusmessagedeletesuccessfully());
                                DeleteDelegationEvent event = new DeleteDelegationEvent();
                                event.setModel(model);
                                DelegationCenterGridPanel.this.bus.fireEvent(event);
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                String details = caught.getMessage();
                                if (caught instanceof DelegationException) {
                                    details = ExceptionMessageHandler.getErrorMessage(((DelegationException) caught).getCode());
                                }
                                Info.display(DelegationCenterGridPanel.this.messages.commonerror(), details);
                            }
                        });
                    }
                }
            };
        }

        @Override
        public void componentSelected(MenuEvent ce) {
            final ContentEvent contentEvent = new ContentEvent();
            final DelegationEvent event = new DelegationEvent();
            event.setDelegationId(this.model.getId());
            event.setEntiteModel(DelegationCenterGridPanel.this.entiteModel);
            event.setIsModification(this.model.getIsModification());

            switch (this.index) {
            case 1:
                event.setMode(DelegationEvent.MODE_IS_VIEW);
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_DETAIL_DELEGATION_FORM);
                break;

            case 2:
                event.setMode(DelegationEvent.MODE_IS_EDIT);
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_NEW_DELEGATION_FORM);
                break;

            case 3:
                if (this.model != null) {
                    MessageBox box = new MessageBox();
                    box.setButtons(MessageBox.YESNO);
                    box.setIcon(MessageBox.INFO);
                    box.setTitle(DelegationCenterGridPanel.this.messages.commonConfirmation());
                    box.addCallback(this.listener);
                    box.setMessage(DelegationCenterGridPanel.this.messages.commonDeleteMessage(this.model.getDelegationNature().getName()));
                    ((Button) box.getDialog().getButtonBar().getItem(0)).setText(DelegationCenterGridPanel.this.messages.commonOui());
                    ((Button) box.getDialog().getButtonBar().getItem(1)).setText(DelegationCenterGridPanel.this.messages.commonNon());
                    box.show();
                }

                break;

            case 4:
                PrintDocumentDialog dialog = new PrintDocumentDialog(this.model);
                dialog.show();
                break;

            case 5:
                event.setMode(DelegationEvent.MODE_IS_ADD_DOCUMENT_SIGNEE);
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_DETAIL_DELEGATION_FORM);

                break;

            case 6:
                event.setMode(DelegationEvent.MODE_IS_ADD_DOCUMENT);
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_DETAIL_DELEGATION_FORM);
                break;

            case 7:
                event.setMode(DelegationEvent.MODE_IS_CREATE_SUB_DELEGATION);
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_NEW_DELEGATION_FORM);
                break;

            case 8: // DelegationEvent.MODE_IS_REPLACE_DELEGANT_OR_DELEGATAIRE
                if (this.model.getEndDate() == null) {
                    RenewDelegationDialog chooseDateDlg = new RenewDelegationDialog(this.model, DelegationCenterGridPanel.this.bus);
                    chooseDateDlg.setTypeRenew(1);
                    chooseDateDlg.show();
                } else {
                    RenewDelegationEvent renewEvent = new RenewDelegationEvent();
                    renewEvent.setTypeRenew(1);
                    renewEvent.setModel(this.model);
                    DelegationCenterGridPanel.this.bus.fireEvent(renewEvent);
                }
                return;
                // event.setMode(DelegationEvent.MODE_IS_REPLACE_DELEGANT_OR_DELEGATAIRE);
                // event.setDelegationModel(model);
                // contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_NEW_DELEGATION_FORM);
                // break;

            case 9:
                if (this.model.getEndDate() == null) {
                    RenewDelegationDialog chooseDateDlg = new RenewDelegationDialog(this.model, DelegationCenterGridPanel.this.bus);
                    chooseDateDlg.show();
                } else {
                    RenewDelegationEvent renewEvent = new RenewDelegationEvent();
                    renewEvent.setModel(this.model);
                    DelegationCenterGridPanel.this.bus.fireEvent(renewEvent);
                }
                return;

            case 10:
                event.setMode(DelegationEvent.MODE_IS_CREATE_TEM_DELEGATION);
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_NEW_DELEGATION_FORM);
                break;
            case 11:
                event.setMode(DelegationEvent.MODE_IS_ADD_DOCUMENT_SIGNEE);
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_DETAIL_DELEGATION_FORM);

                break;
            }

            contentEvent.setEvent(event);
            DelegationCenterGridPanel.this.bus.fireEvent(contentEvent);
        }
    }

    public EditorGrid<DelegationModel> getGrid() {
        return this.grid;
    }
}
