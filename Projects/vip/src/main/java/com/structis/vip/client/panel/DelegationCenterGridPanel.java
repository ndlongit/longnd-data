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

public class DelegationCenterGridPanel extends AbstractPanel {

    private EditorGrid<DelegationModel> grid;
    private PagingToolBar toolBar;
    private SimpleComboBox<String> pageSizeCombobox;

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

    boolean isSuperUser = false;
    private ContentPanel main;
    private int totalRecord = 0;
    private int pagingSize = ClientConstant.DEFAULT_PAGE_SIZE_50;

    private DelegationFilter filter;

    public DelegationCenterGridPanel(SimpleEventBus bus) {
        this.bus = bus;

        if (currentUser != null) {
            isSuperUser = currentUser.isSuperUser();
        }

        languageService.getLanguages(new AsyncCallbackWithErrorResolution<List<LanguageModel>>() {

            @Override
            public void onSuccess(List<LanguageModel> arg0) {
                languages = new ArrayList<LanguageModel>();
                languages.addAll(arg0);
            }
        });
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
        resultLabel = new Label("0 " + messages.commonDelegations() + " "); // set as default value
        resultLabel.setStyleAttribute("border-right", "1px solid #99BBE8");

        // print button
        printButton = new Button(messages.delegationdetailbuttonprinter());
        printButton.setIcon(IconHelper.createPath("html/print-icon.png"));

        // new delegation button
        ajouterButton = new Button(messages.commonAjouterDelegationPrincipale());
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
        proxy = new RpcProxy<PagingLoadResult<DelegationModel>>() {

            @Override
            public void load(Object loadConfig, final AsyncCallback<PagingLoadResult<DelegationModel>> callback) {
                DelegationFilter newFilter = (DelegationFilter) loadConfig;
                if (newFilter != null && newFilter.getEntite() != null) {
                    main.mask(messages.commonloadingdata());
                    newFilter.setLimit(newFilter.getOffset() + pagingSize);
                    delegationService.getValidDelegationsByEntiteByPaging(newFilter,
                            new AsyncCallbackWithErrorResolution<PagingLoadResult<DelegationModel>>() {

                                @Override
                                public void onFailure(Throwable arg0) {
                                    main.unmask();
                                    callback.onFailure(arg0);
                                }

                                @Override
                                public void onSuccess(PagingLoadResult<DelegationModel> arg0) {
                                    main.unmask();
                                    callback.onSuccess(arg0);
                                    totalRecord = loader.getTotalCount();
                                    resultLabel.setText(totalRecord + " " + messages.commonDelegations() + " ");
                                    toolBar.getItem(9).setEnabled(true);
                                }
                            });
                }
            }
        };

        loader = new BasePagingLoader<PagingLoadResult<DelegationModel>>(proxy) {

            @Override
            protected Object newLoadConfig() {
                return filter;
            };
        };
        loader.setRemoteSort(true);

        toolBar = new PagingToolBar(ClientConstant.DEFAULT_PAGE_SIZE_50);
        toolBar.setHeight(0);

        pageSizeCombobox = new SimpleComboBox<String>();
        pageSizeCombobox.setWidth(70);
        pageSizeCombobox.add(AppUtil.getPagingValue());
        pageSizeCombobox.setSimpleValue(ClientConstant.DEFAULT_PAGE_SIZE_50 + "");
        pageSizeCombobox.setTriggerAction(TriggerAction.ALL);

        // add combobox, position in PagingToolBar
        toolBar.insert(pageSizeCombobox, 9);
        toolBar.bind(loader);

        grid = new EditorGrid<DelegationModel>(new ListStore<DelegationModel>(loader), new ColumnModel(getListColumn()));
        grid.setStateId("pagingGridDelegation");
        grid.setStateful(true);

        grid.setColumnLines(true);
        grid.setAutoHeight(true);
        grid.setBorders(false);
        grid.setStripeRows(true);
        grid.setSelectionModel(new GridSelectionModel<DelegationModel>());
        grid.getView().setAutoFill(true);
        grid.getView().setForceFit(true);
        grid.setAutoExpandColumn("action");
        WindowResizeBinder.bind(grid);

        main.add(grid, new RowData(1, 1));
        main.setBottomComponent(toolBar);
    }

    private void addHandler() {
        printButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                final String reportUrl = GWT.getHostPageBaseURL() + ".printDelegationGridServiceServlet";
                final List<NameValuePair> values = new ArrayList<NameValuePair>();

                if (filter != null) {

                    delegationService.getDelegationIdsByEntite(filter, new AsyncCallbackWithErrorResolution<List<Integer>>() {

                        @Override
                        public void onSuccess(java.util.List<Integer> ids) {
                            delegationService.getDelegationByIds(ids, new AsyncCallbackWithErrorResolution<List<DelegationModel>>() {

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
                                        String currentLangCode = path.substring(path.lastIndexOf(paramName) + 8, path.lastIndexOf(paramName) + 10);
                                        for (LanguageModel languageModel : languages) {
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
        bus.addHandler(DelegationGridProjectEvent.getType(), new DelegationGridProjectHandler() {

            @Override
            public void onLoadAction(DelegationGridProjectEvent event) {
                disableEvents(true);
                loader.load(0, ClientConstant.DEFAULT_PAGE_SIZE_50);
                disableEvents(false);
            }
        });

        // add handler for Filter button
        bus.addHandler(DelegationFilterEvent.getType(), new DelegationFilterHandler() {

            @Override
            public void onLoadAction(final DelegationFilterEvent event) {
                perimetreTreeModel = event.getPerimetreTreeModel();
                ajouterButton.setVisible(perimetreTreeModel.getIsModificationDelegation());

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
                    filter.setLimit(ClientConstant.DEFAULT_PAGE_SIZE_50);
                }

                if (state.containsKey("sortField")) {
                    filter.setSortField((String) state.get("sortField"));
                    filter.setSortDir(SortDir.valueOf((String) state.get("sortDir")));
                }

                loader.load(filter);
            }
        });

        // catch tree event to get selected perimeter
        bus.addHandler(DelegationTreeEvent.getType(), new DelegationTreeHandler() {

            @Override
            public void onLoadAction(DelegationTreeEvent event) {
                if (event.getTreeModel() != null) {
                    perimetreModel = new PerimetreModel();
                    perimetreModel.setPerId((String) event.getTreeModel().get("id"));
                    perimetreModel.setName(event.getTreeModel().get("typeName").toString());

                    PerimetreTypeModel type = new PerimetreTypeModel();
                    type.setPtyId(event.getTreeModel().get("type").toString());
                    type.setName(event.getTreeModel().get("typeName").toString());

                    perimetreModel.setType(type);

                }
            }
        });

        // catch event on Valider button to get perimeter and entite model
        bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(DelegationListProjectEvent event) {
                perimetreModel = event.getPerimetreModel();
                entiteModel = event.getEntiteModel();
            }
        });

        // catch event on Renew delegation
        bus.addHandler(RenewDelegationEvent.getType(), new RenewDelegationHandler() {

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
                event.setEntiteModel(entiteModel);

                ContentEvent contentEvent = new ContentEvent();
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_NEW_DELEGATION_FORM);

                contentEvent.setEvent(event);
                bus.fireEvent(contentEvent);
            }
        });

        // add listner for new delegation button
        ajouterButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                delegationTypeService.getPrincipleType(new AsyncCallbackWithErrorResolution<DelegationTypeModel>() {

                    @Override
                    public void onSuccess(DelegationTypeModel arg0) {
                        // create new delegation
                        if ((perimetreModel != null) && (entiteModel != null) && (arg0 != null)) {
                            DelegationEvent event = new DelegationEvent();
                            event.setDelegationTypeModel(arg0);
                            event.setPerimetreModel(perimetreModel);
                            event.setEntiteModel(entiteModel);
                            event.setMode(DelegationEvent.MODE_IS_NEW);

                            ContentEvent contentEvent = new ContentEvent();
                            contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_NEW_DELEGATION_FORM);
                            contentEvent.setEvent(event);
                            bus.fireEvent(contentEvent);
                        }
                    }
                });
            }
        });

        grid.addListener(Events.RowDoubleClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                DelegationModel selectedModel = grid.getSelectionModel().getSelectedItem();

                ContentEvent contentEvent = new ContentEvent();

                DelegationEvent subEvent = new DelegationEvent();

                subEvent.setDelegationId(selectedModel.getId());
                subEvent.setMode(DelegationEvent.MODE_IS_VIEW);
                subEvent.setEntiteModel(entiteModel);
                subEvent.setIsModification(selectedModel.getIsModification());

                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_DETAIL_DELEGATION_FORM);
                contentEvent.setEvent(subEvent);

                bus.fireEvent(contentEvent);
            }
        });

        pageSizeCombobox.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<String>>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<SimpleComboValue<String>> se) {
                int result = 0;

                if (pageSizeCombobox.getValue() == null || pageSizeCombobox.getValue().getValue() == null) {
                    result = ClientConstant.DEFAULT_PAGE_SIZE_50;
                } else {
                    String data = pageSizeCombobox.getValue().getValue();
                    if (messages.commonTous().equals(data)) {
                        result = totalRecord;
                    } else {
                        result = Integer.parseInt(data);
                    }
                }
                pagingSize = result;

                DelegationPagingEvent event = new DelegationPagingEvent();
                event.setPageSize(pagingSize);
                bus.fireEvent(event);
            }
        });
    }

    private List<ColumnConfig> getListColumn() {
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
        ColumnConfig column = null;

        column = new ColumnConfig("perimeter.name", messages.perimetre(), 220);
        column.setRowHeader(true);
        column.setResizable(true);
        configs.add(column);

        column = new ColumnConfig("delegationNature.name", messages.nature(), 150);
        column.setResizable(true);
        configs.add(column);

        column = new ColumnConfig("delegant.fullname", messages.delegant(), 150);
        column.setResizable(true);
        configs.add(column);

        GridCellRenderer<DelegationModel> delegataireRender = new GridCellRenderer<DelegationModel>() {

            @Override
            public Object render(final DelegationModel model, String property, ColumnData config, int rowIndex, int colIndex,
                    ListStore<DelegationModel> store, Grid<DelegationModel> pGrid) {
                return buildDelegataireColumn(model);
            }
        };

        column = new ColumnConfig();
        column.setRenderer(delegataireRender);
        column.setHeader(messages.delegataire());
        column.setId("delegataire.fullname");
        column.setResizable(true);
        column.setWidth(150);
        configs.add(column);

        column = new ColumnConfig();
        column.setHeader(messages.debutdevalidite());
        column.setDateTimeFormat(DateTimeFormat.getFormat(ClientConstant.DATE_FORMAT));
        column.setId("startDate");
        column.setResizable(true);
        column.setWidth(90);
        configs.add(column);

        column = new ColumnConfig();
        column.setHeader(messages.findevalidite());
        column.setDateTimeFormat(DateTimeFormat.getFormat(ClientConstant.DATE_FORMAT));
        column.setId("endDate");
        column.setResizable(true);
        column.setWidth(90);
        configs.add(column);

        column = new ColumnConfig();
        column.setHeader(messages.delegationstatus());
        column.setId("delegationStatus.name");
        column.setResizable(true);
        column.setWidth(150);
        configs.add(column);

        column = new ColumnConfig();
        column.setHeader(messages.type());
        column.setId("delegationType.name");
        column.setResizable(true);
        column.setWidth(100);
        configs.add(column);

        column = new ColumnConfig();

        GridCellRenderer<DelegationModel> actionRender = new GridCellRenderer<DelegationModel>() {

            @Override
            public Object render(final DelegationModel model, String property, ColumnData config, int rowIndex, int colIndex,
                    ListStore<DelegationModel> store, Grid<DelegationModel> pGrid) {
                return buildActionColumn(model);
            }
        };

        column.setHeader(messages.action());
        column.setId("action");
        column.setResizable(true);
        column.setWidth(90);
        column.setRenderer(actionRender);
        configs.add(column);

        column = new ColumnConfig("id", "Id", 60);

        // Display if DEV mode
        column.setHidden(!SharedConstant.RunMode.DEVELOPMENT.value().equals(config.runMode()));
        configs.add(column);

        return configs;
    }

    protected Object buildDelegataireColumn(final DelegationModel model) {
        HorizontalPanel container = new HorizontalPanel();
        final HTML html = new HTML();
        if (model.getDelegataire() == null) {
            html.setText(" ");
        } else {
            html.setText(model.getDelegataire().getFullname());
        }
        html.setStyleName("gwt-DelegataireColumn");
        container.add(html);
        if (!SharedConstant.ENTITE_ID_ETDE.equals(entiteModel.getEntId())) {
            com.google.gwt.user.client.ui.Label viewAll = new com.google.gwt.user.client.ui.Label("...");

            viewAll.setStyleName("x-link-item");
            viewAll.addClickHandler(new ClickHandler() {

                @Override
                public void onClick(ClickEvent arg0) {
                    displayAllDelegataires(model.getId());
                }

                public void displayAllDelegataires(Integer delId) {
                    delegationService.getDelegataires(delId, new AsyncCallbackWithErrorResolution<String>() {

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

    private Object buildActionColumn(final DelegationModel delegationModel) {
        final Button btn = new Button();
        btn.setText(messages.action());
        Menu menu = new Menu();

        MenuItem menuItemView = new MenuItem(messages.delegationcomboboxaction1()); // view
        menuItemView.addSelectionListener(new ActionMenu(1, delegationModel));

        MenuItem menuItemModify = new MenuItem(messages.delegationcomboboxaction2()); // modify
        menuItemModify.addSelectionListener(new ActionMenu(2, delegationModel));

        MenuItem menuItemDelete = new MenuItem(messages.delegationcomboboxaction3()); // delete
        menuItemDelete.addSelectionListener(new ActionMenu(3, delegationModel));

        MenuItem menuItemPrintDocument = new MenuItem(messages.delegationcomboboxaction4()); // print document
        menuItemPrintDocument.addSelectionListener(new ActionMenu(4, delegationModel));

        MenuItem menuItemAddSignedDelegation = new MenuItem(messages.delegationcomboboxaction5()); // add a signed delegation
        menuItemAddSignedDelegation.addSelectionListener(new ActionMenu(5, delegationModel));

        MenuItem menuItemDeleteSignedDelegation = new MenuItem(messages.delegationcomboboxaction11()); // delete a signed delegation
        menuItemDeleteSignedDelegation.addSelectionListener(new ActionMenu(11, delegationModel));

        MenuItem menuItemAddDocument = new MenuItem(messages.delegationcomboboxaction6()); // add document
        menuItemAddDocument.addSelectionListener(new ActionMenu(6, delegationModel));

        MenuItem menuItemCreateSubDelegation = new MenuItem(messages.delegationcomboboxaction7()); // create sub delegation
        menuItemCreateSubDelegation.addSelectionListener(new ActionMenu(DelegationEvent.MODE_IS_CREATE_SUB_DELEGATION, delegationModel));

        MenuItem menuItemReplaceDelegantDelegataire = new MenuItem(messages.delegationcomboboxaction8()); // replace delegant _ delegataire
        menuItemReplaceDelegantDelegataire.addSelectionListener(new ActionMenu(8, delegationModel));

        MenuItem menuItemRenewDelegation = new MenuItem(messages.delegationcomboboxaction9()); // renew delegation
        menuItemRenewDelegation.addSelectionListener(new ActionMenu(9, delegationModel));

        MenuItem menuItem10 = new MenuItem(messages.delegationcomboboxaction10()); // create temporary
        menuItem10.addSelectionListener(new ActionMenu(10, delegationModel));

        int statusId = delegationModel.getDelegationStatus().getId();
        int delegationType = delegationModel.getDelegationType().getId();

        // consulter for all role and status
        menu.add(menuItemView);

        boolean canModify = (delegationModel.getIsModification() != null) && (delegationModel.getIsModification());
        if (statusId == ClientConstant.DELEGATION_STATUS_IS_P) {
            // modifier for status P
            if (canModify) {
                // modifier for role creation/modification and all status
                menu.add(menuItemModify);
            }
        }

        if (canModify && (statusId == ClientConstant.DELEGATION_STATUS_IS_P)) {
            // supprimer for role creation/modification and status P
            menu.add(menuItemDelete);
        } else if ((delegationModel.getIsValidation() != null) && (delegationModel.getIsValidation())) {
            // supprimer for role validation and all status
            menu.add(menuItemDelete);
        }

        // imprimer document for all role and status
        menu.add(menuItemPrintDocument);

        if (statusId == ClientConstant.DELEGATION_STATUS_IS_P) {
            // ajouter une document for status P
            if ((delegationModel.getIsValidation() != null) && (delegationModel.getIsValidation())) {
                // ajouter une document for role validation
                menu.add(menuItemAddSignedDelegation);
            }
        }

        if (statusId == ClientConstant.DELEGATION_STATUS_IS_V || statusId == ClientConstant.DELEGATION_STATUS_IS_D) {
            // ajouter autre document for status V, D
            if ((delegationModel.getIsValidation() != null) && (delegationModel.getIsValidation())) {
                // ajouter autre document for role validation
                menu.add(menuItemAddDocument);
            }
        }
        if (statusId == ClientConstant.DELEGATION_STATUS_IS_D) {
            // ajouter autre document for status V, D
            if ((delegationModel.getIsValidation() != null) && (delegationModel.getIsValidation())) {
                // ajouter autre document for role validation
                menu.add(menuItemDeleteSignedDelegation);
            }
        }
        if (delegationType != ClientConstant.DELEGATION_TYPE_IS_SOUS_DELEGATION && delegationType != ClientConstant.DELEGATION_TYPE_IS_TEMPORAIRE) {
            // create sub delegation for only principle delegation
            if (statusId == ClientConstant.DELEGATION_STATUS_IS_D) {
                // create sub delegation for status D
                if (entiteModel != null && !SharedConstant.ENTITE_ID_ETDE.equalsIgnoreCase(entiteModel.getEntId())) {
                    // create sub delegation for all entite except ETDE
                    if (canModify) {
                        // create sub delegation for creation/modification role
                        // tdo 28 Jan
                        if (delegationModel.getPerimeter() != null && delegationModel.getPerimeter().getIsSubdelegable() != null) {
                            if (delegationModel.getPerimeter().getIsSubdelegable() > 0) {
                                menu.add(menuItemCreateSubDelegation);
                            }
                        } else {
                            if (delegationModel.getPerimeter().getType() != null
                                    && delegationModel.getPerimeter().getType().getIsSubdelegable() != null) {
                                if (delegationModel.getPerimeter().getType().getIsSubdelegable() > 0) {
                                    menu.add(menuItemCreateSubDelegation);
                                }
                            } else {
                                if (delegationModel.getPerimeter().getChantierType() != null
                                        && delegationModel.getPerimeter().getChantierType().getIsSubdelegable() != null) {
                                    if (delegationModel.getPerimeter().getChantierType().getIsSubdelegable() > 0) {
                                        menu.add(menuItemCreateSubDelegation);
                                    }
                                }
                            }
                        }
                    }

                    // replace delegant, delegataire for only principle delegation
                    // replace delegant, delegataire for status V, D
                    // replace delegant, delegataire for role creation/modification and all status
                    if ((delegationModel.getIsCanRenew() != null) && (delegationModel.getIsCanRenew())) {
                        menu.add(menuItemReplaceDelegantDelegataire);
                    }

                    // renew delegation and create temporary delegation for only principle delegation
                    // renew delegation and create temporary delegation for status D
                    Date today = new Date();
                    if (delegationModel.getEndDate() == null || today.compareTo(delegationModel.getEndDate()) <= 0) {
                        // renew delegation for status V, D
                        // renew delegation for creation/modification role
                        if ((delegationModel.getIsCanRenew() != null) && (delegationModel.getIsCanRenew())) {
                            menu.add(menuItemRenewDelegation);
                        }
                    }

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

            listener = new Listener<MessageBoxEvent>() {

                @Override
                public void handleEvent(MessageBoxEvent ce) {
                    Button btn = ce.getButtonClicked();
                    String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
                    if (txtReturn.equals(btn.getText())) {
                        delegationService.delete(model, new AsyncCallbackWithErrorResolution<Boolean>() {

                            @Override
                            public void onSuccess(Boolean arg0) {
                                Info.display(messages.commoninfo(), messages.statusmessagedeletesuccessfully());
                                DeleteDelegationEvent event = new DeleteDelegationEvent();
                                event.setModel(model);
                                bus.fireEvent(event);
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
                    }
                }
            };
        }

        @Override
        public void componentSelected(MenuEvent ce) {
            final ContentEvent contentEvent = new ContentEvent();
            final DelegationEvent event = new DelegationEvent();
            event.setDelegationId(model.getId());
            event.setEntiteModel(entiteModel);
            event.setIsModification(model.getIsModification());

            switch (index) {
            case 1:
                event.setMode(DelegationEvent.MODE_IS_VIEW);
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_DETAIL_DELEGATION_FORM);
                break;

            case 2:
                event.setMode(DelegationEvent.MODE_IS_EDIT);
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_NEW_DELEGATION_FORM);
                break;

            case 3:
                if (model != null) {
                    MessageBox box = new MessageBox();
                    box.setButtons(MessageBox.YESNO);
                    box.setIcon(MessageBox.INFO);
                    box.setTitle(messages.commonConfirmation());
                    box.addCallback(listener);
                    box.setMessage(messages.commonDeleteMessage(model.getDelegationNature().getName()));
                    ((Button) box.getDialog().getButtonBar().getItem(0)).setText(messages.commonOui());
                    ((Button) box.getDialog().getButtonBar().getItem(1)).setText(messages.commonNon());
                    box.show();
                }

                break;

            case 4:
                PrintDocumentDialog dialog = new PrintDocumentDialog(model);
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

            case DelegationEvent.MODE_IS_CREATE_SUB_DELEGATION:
                event.setMode(DelegationEvent.MODE_IS_CREATE_SUB_DELEGATION);
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_NEW_DELEGATION_FORM);
                break;

            case 8: // DelegationEvent.MODE_IS_REPLACE_DELEGANT_OR_DELEGATAIRE
                if (model.getEndDate() == null) {
                    RenewDelegationDialog chooseDateDlg = new RenewDelegationDialog(model, bus);
                    chooseDateDlg.setTypeRenew(1);
                    chooseDateDlg.show();
                } else {
                    RenewDelegationEvent renewEvent = new RenewDelegationEvent();
                    renewEvent.setTypeRenew(1);
                    renewEvent.setModel(model);
                    bus.fireEvent(renewEvent);
                }
                return;
                // event.setMode(DelegationEvent.MODE_IS_REPLACE_DELEGANT_OR_DELEGATAIRE);
                // event.setDelegationModel(model);
                // contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_NEW_DELEGATION_FORM);
                // break;

            case 9:
                if (model.getEndDate() == null) {
                    RenewDelegationDialog chooseDateDlg = new RenewDelegationDialog(model, bus);
                    chooseDateDlg.show();
                } else {
                    RenewDelegationEvent renewEvent = new RenewDelegationEvent();
                    renewEvent.setModel(model);
                    bus.fireEvent(renewEvent);
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
            bus.fireEvent(contentEvent);
        }
    }

    public EditorGrid<DelegationModel> getGrid() {
        return grid;
    }
}
