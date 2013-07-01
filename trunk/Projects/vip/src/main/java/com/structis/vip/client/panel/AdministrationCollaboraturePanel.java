package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ComponentManager;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
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
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.GridViewConfig;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.LabelToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.dialog.SynETDEDialog;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.ModifyCollaboratureEvent;
import com.structis.vip.client.event.ModifyCollaboratureHandler;
import com.structis.vip.client.event.SynETDEEvent;
import com.structis.vip.client.event.SynETDEHandler;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.service.ClientCollaborateurServiceAsync;
import com.structis.vip.client.service.ClientSyncServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.exception.CollaborateurException;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.DelegantPerimetreModel;
import com.structis.vip.shared.model.DelegatairePerimetreModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;

public class AdministrationCollaboraturePanel extends AbstractPanel {

    private ListStore<CollaborateurModel> store = new ListStore<CollaborateurModel>();

    private TextField<String> txtFilter;
    private Button btnAdd;
    private Button btnConsulter;
    private Button btnModifer;
    private Button btnImporter;
    private Button btnSupprimer;
    private CheckBox cbDisplaySortie;

    private Button btnApplyPerimetreDelegant;
    private Button btnApplyPerimetreDelegataire;
    private Button btnAddPerimetreDelegataire;
    private Button btnAddPerimetreDelegant;

    private PagingToolBar toolBar;

    private Grid<CollaborateurModel> grid;
    private PagingLoader<PagingLoadResult<CollaborateurModel>> loader;
    private RpcProxy<PagingLoadResult<CollaborateurModel>> proxy;

    private ClientCollaborateurServiceAsync clientCollaboratureService = ClientCollaborateurServiceAsync.Util.getInstance();
    private ClientSyncServiceAsync clientSyncService = ClientSyncServiceAsync.Util.getInstance();
    private PagingLoadConfig pagingConfig = new BasePagingLoadConfig();

    public AdministrationCollaboraturePanel(SimpleEventBus bus) {
        this.bus = bus;

        setLayout(new FlowLayout(0));
        setScrollMode(Scroll.AUTO);

        initUI();
        initEvent();
        addHandler();
    }

    private void addHandler() {
        bus.addHandler(ModifyCollaboratureEvent.getType(), new ModifyCollaboratureHandler() {

            @Override
            public void onLoadAction(ModifyCollaboratureEvent event) {
                disableEvents(true);
                initData();
                disableEvents(false);
            }
        });

        bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(final DelegationListProjectEvent event) {
                disableEvents(true);
                // add BYTP
                disableEvents(false);
            }
        });

        bus.addHandler(SynETDEEvent.getType(), new SynETDEHandler() {

            @Override
            public void onLoadAction(SynETDEEvent event) {
                grid.mask(messages.commonsyncdata());

                clientSyncService.syncRUBISWithItems(SessionServiceImpl.getInstance().getEntiteContext().getEntId(), event.getModels(),
                        new AsyncCallbackWithErrorResolution<List<CollaborateurModel>>() {

                            @Override
                            public void onSuccess(List<CollaborateurModel> arg0) {
                                Info.display(messages.commonInfoHeader(), messages.collaboratureimportersucces());
                                initData();
                            }

                            @Override
                            public void onFailure(Throwable arg0) {
                                Info.display(messages.commonerror(), arg0.getMessage());
                                grid.unmask();
                            }
                        });
            }
        });
    }

    private void initData() {
        pagingConfig.setOffset(0);
        pagingConfig.setLimit(ClientConstant.DEFAULT_PAGE_SIZE);
        loader.load(pagingConfig);
        btnAddPerimetreDelegataire.setEnabled(false);
        btnAddPerimetreDelegant.setEnabled(false);
    }

    private void initEvent() {
        grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<CollaborateurModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<CollaborateurModel> se) {
                if (se.getSelectedItem() != null) {
                    btnConsulter.setEnabled(true);

                    // if (ConstantClient.ENTITE_ID_IS_BYEFE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
                    btnModifer.setEnabled(true);
                    btnSupprimer.setEnabled(true);
                    // }
                    btnAddPerimetreDelegant.setEnabled(se.getSelectedItem().getIsDelegant() != null && se.getSelectedItem().getIsDelegant() > 0);
                    btnAddPerimetreDelegataire.setEnabled(se.getSelectedItem().getIsDelegataire() != null
                            && se.getSelectedItem().getIsDelegataire() > 0);
                } else {
                    btnConsulter.setEnabled(false);
                    btnModifer.setEnabled(false);
                    btnSupprimer.setEnabled(false);
                }
            }
        });

        grid.addListener(Events.RowDoubleClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_CREATE_FORM);

                ModifyCollaboratureEvent subEvent = new ModifyCollaboratureEvent();
                subEvent.setModel(grid.getSelectionModel().getSelectedItem());
                subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_VIEW_FORM);

                event.setEvent(subEvent);
                bus.fireEvent(event);
            }
        });

        final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

            @Override
            public void handleEvent(MessageBoxEvent ce) {
                Button btn = ce.getButtonClicked();
                String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(1)).getText();
                if (txtReturn.equals(btn.getText())) {
                    final CollaborateurModel model = grid.getSelectionModel().getSelectedItem();
                    clientCollaboratureService.delete(model, new AsyncCallbackWithErrorResolution<Boolean>() {

                        @Override
                        public void onSuccess(Boolean arg0) {
                            initData();
                            Info.display(messages.commoninfo(), messages.collaboraturemessagedeletesuccessfully());
                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            String details = caught.getMessage();
                            if (caught instanceof CollaborateurException) {
                                details = ExceptionMessageHandler.getErrorMessage(((CollaborateurException) caught).getCode());
                            }
                            Info.display(messages.commonerror(), details);
                        }
                    });
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
                pagingConfig.setOffset(0);
                pagingConfig.setLimit(ClientConstant.DEFAULT_PAGE_SIZE);
                pagingConfig.set("filterName", filter);
                loader.load(pagingConfig);
            }
        });

        btnImporter.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                SynETDEDialog dialog = new SynETDEDialog(bus, SessionServiceImpl.getInstance().getEntiteContext());
                dialog.show();
            }
        });

        btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                CollaborateurModel item = grid.getSelectionModel().getSelectedItem();

                MessageBox box = new MessageBox();
                box.setButtons(MessageBox.YESNO);
                box.setIcon(MessageBox.INFO);
                box.setTitle(messages.commonConfirmation());
                box.addCallback(l);
                box.setMessage(messages.commonDeleteCollaborateurMessage(item.getFullnameNoSeparater()));
                ((Button) box.getDialog().getButtonBar().getItem(0)).setText(messages.commonNon());
                ((Button) box.getDialog().getButtonBar().getItem(1)).setText(messages.commonOui());
                box.show();
            }
        });

        btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_CREATE_FORM);

                ModifyCollaboratureEvent subEvent = new ModifyCollaboratureEvent();
                subEvent.setModel(null);
                subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_CREATE_FORM);

                event.setEvent(subEvent);
                bus.fireEvent(event);
            }
        });

        btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_CREATE_FORM);

                ModifyCollaboratureEvent subEvent = new ModifyCollaboratureEvent();
                subEvent.setModel(grid.getSelectionModel().getSelectedItem());
                subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_CREATE_FORM);

                event.setEvent(subEvent);
                bus.fireEvent(event);
            }
        });

        btnConsulter.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_CREATE_FORM);

                ModifyCollaboratureEvent subEvent = new ModifyCollaboratureEvent();
                subEvent.setModel(grid.getSelectionModel().getSelectedItem());
                subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_VIEW_FORM);

                event.setEvent(subEvent);
                bus.fireEvent(event);
            }
        });

        btnApplyPerimetreDelegant.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                applyPerimetreDelegant();
            }
        });

        btnApplyPerimetreDelegataire.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                applyPerimetreDelegataire();
            }
        });
        btnAddPerimetreDelegant.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                addPerimetreDelegant();
            }
        });

        btnAddPerimetreDelegataire.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                addPerimetreDelegataire();
            }
        });
    }

    private void initUI() {
        proxy = new RpcProxy<PagingLoadResult<CollaborateurModel>>() {

            @Override
            public void load(Object loadConfig, AsyncCallback<PagingLoadResult<CollaborateurModel>> callback) {
                EntiteModel entiteModel = SessionServiceImpl.getInstance().getEntiteContext();
                if (entiteModel != null && entiteModel.getEntId() != null) {
                    PagingLoadConfig internConfig = (PagingLoadConfig) loadConfig;
                    PagingLoadConfig config = pagingConfig;// (PagingLoadConfig) loadConfig;
                    config.setLimit(internConfig.getLimit());
                    config.setOffset(internConfig.getOffset());
                    config.setSortField(internConfig.getSortField());
                    config.setSortDir(internConfig.getSortDir());

                    clientCollaboratureService.getAllCollaborateursByEntiteIdPaging(config, entiteModel.getEntId(), callback);
                    toolBar.setEnabled(true);
                }
            }
        };

        loader = new BasePagingLoader<PagingLoadResult<CollaborateurModel>>(proxy);
        loader.setRemoteSort(true);

        store = new ListStore<CollaborateurModel>(loader);

        toolBar = new PagingToolBar(ClientConstant.DEFAULT_PAGE_SIZE);

        toolBar.bind(loader);

        VerticalPanel toolbarPanel = new VerticalPanel();
        toolbarPanel.setTableWidth("100%");
        toolbarPanel.setBorders(false);
        toolbarPanel.setHeight(80);

        ToolBar topToolBar = new ToolBar();
        ToolBar topSecondToolBar = new ToolBar();
        ToolBar topThirdToolBar = new ToolBar();

        txtFilter = new TextField<String>();
        txtFilter.setTitle(messages.collaboraturenom());

        btnConsulter = new Button(messages.commonConsulterbutton());
        btnConsulter.setStyleAttribute("margin-left", "10px");
        btnConsulter.setIcon(IconHelper.createPath("html/view-icon.png"));
        btnConsulter.setEnabled(false);

        btnAdd = new Button(messages.commonCreerbutton());
        btnAdd.setStyleAttribute("margin-left", "10px");
        btnAdd.setIcon(IconHelper.createPath("html/add-icon.png"));

        btnModifer = new Button(messages.commonmodifierbutton());
        btnModifer.setIcon(IconHelper.createPath("html/save-icon.png"));
        btnModifer.setEnabled(false);

        btnSupprimer = new Button(messages.commonSupprimer());
        btnSupprimer.setIcon(IconHelper.createPath("html/delete-icon.png"));
        btnSupprimer.setEnabled(false);

        btnImporter = new Button(messages.collaboraturebuttonimportdepuisrubis());
        btnImporter.setIcon(IconHelper.createPath("html/import-icon.png"));

        // tdo
        cbDisplaySortie = new CheckBox();
        cbDisplaySortie.setBoxLabel("Yc Sorties");
        cbDisplaySortie.addListener(Events.OnClick, new Listener<ComponentEvent>() {

            @Override
            public void handleEvent(ComponentEvent be) {
                pagingConfig.setOffset(0);
                pagingConfig.setLimit(ClientConstant.DEFAULT_PAGE_SIZE);
                if (cbDisplaySortie.getValue()) {
                    pagingConfig.set("displaySortie", true);
                } else {
                    pagingConfig.set("displaySortie", false);
                }
                loader.load(pagingConfig);
            }

        });

        btnApplyPerimetreDelegant = new Button(messages.collaboraturebuttonapplyperietredelegant());
        btnApplyPerimetreDelegant.setIcon(IconHelper.createPath("html/perDelegant.png"));
        btnApplyPerimetreDelegant.setEnabled(true);

        btnApplyPerimetreDelegataire = new Button(messages.collaboraturebuttonapplyperietredelegataire());
        btnApplyPerimetreDelegataire.setIcon(IconHelper.createPath("html/perDelegataire.png"));
        btnApplyPerimetreDelegataire.setEnabled(true);

        btnAddPerimetreDelegataire = new Button(messages.collaboraturebuttonaddperietredelegataire());
        btnAddPerimetreDelegataire.setIcon(IconHelper.createPath("html/addperDelegant.png"));
        btnAddPerimetreDelegataire.setEnabled(true);

        btnAddPerimetreDelegant = new Button(messages.collaboraturebuttonaddperietredelegataire());
        btnAddPerimetreDelegant.setIcon(IconHelper.createPath("html/addperDelegataire.png"));
        btnAddPerimetreDelegant.setEnabled(true);

        topToolBar.add(new LabelToolItem(messages.collaboraturerechercher()));
        topToolBar.add(txtFilter);
        topToolBar.add(btnAdd);
        topToolBar.add(btnConsulter);
        topToolBar.add(btnModifer);
        topToolBar.add(btnSupprimer);
        topToolBar.add(btnImporter);
        LabelToolItem sep = new LabelToolItem();
        sep.setWidth(60);
        topToolBar.add(sep);
        topToolBar.add(cbDisplaySortie);

        // topToolBar.add(new LabelToolItem("Sortie"));//tdo
        topSecondToolBar.add(btnApplyPerimetreDelegant);
        topThirdToolBar.add(btnApplyPerimetreDelegataire);
        topSecondToolBar.add(btnAddPerimetreDelegant);
        topThirdToolBar.add(btnAddPerimetreDelegataire);

        ColumnConfig name = new ColumnConfig(CollaborateurModel.COLLA_FULL_NAME_NO_SEPARATER, messages.collaboraturenometprenom(), 200);
        ColumnConfig delegant = new ColumnConfig(CollaborateurModel.COLLA_IS_DELEGANT, messages.delegationdelegant(), 60);
        delegant.setAlignment(HorizontalAlignment.CENTER);
        ColumnConfig delegataire = new ColumnConfig(CollaborateurModel.COLLA_IS_DELEGATAIRE, messages.delegationdelegataire(), 60);
        delegataire.setAlignment(HorizontalAlignment.CENTER);

        ColumnConfig perimetreDelegant = new ColumnConfig(CollaborateurModel.COLLA_DELEGANT_PERIMETRES, messages.perimetredelegant(), 140);
        perimetreDelegant.setAlignment(HorizontalAlignment.CENTER);

        ColumnConfig perimetreDelegataire = new ColumnConfig(CollaborateurModel.COLLA_DELEGATAIRE_PERIMETRES, messages.perimetredelegataire(), 140);
        perimetreDelegataire.setAlignment(HorizontalAlignment.CENTER);

        GridCellRenderer<CollaborateurModel> delegantRender = new GridCellRenderer<CollaborateurModel>() {

            @Override
            public Object render(final CollaborateurModel model, String property, ColumnData config, int rowIndex, int colIndex,
                    ListStore<CollaborateurModel> store, Grid<CollaborateurModel> grid) {
                final CheckBox cb = new CheckBox();
                cb.setValue((model != null && model.getIsDelegant() != null && model.getIsDelegant() == 1) ? true : false);
                cb.addListener(Events.OnClick, new Listener<BaseEvent>() {

                    @Override
                    public void handleEvent(BaseEvent be) {
                        if (checkIfPerimeterSelected(cb)) {
                            applyPerimetreForDelegant(model, (cb.getValue()) ? 1 : 0, 2);
                        } else {
                            cb.setValue(!cb.getValue());
                            be.setCancelled(false);
                        }
                    }

                });

                return cb;
            }
        };

        GridCellRenderer<CollaborateurModel> delegataireRender = new GridCellRenderer<CollaborateurModel>() {

            @Override
            public Object render(final CollaborateurModel model, String property, ColumnData config, int rowIndex, int colIndex,
                    ListStore<CollaborateurModel> store, Grid<CollaborateurModel> grid) {
                final CheckBox cb = new CheckBox();
                cb.setValue((model != null && model.getIsDelegataire() != null && model.getIsDelegataire() == 1) ? true : false);
                cb.addListener(Events.OnClick, new Listener<BaseEvent>() {

                    @Override
                    public void handleEvent(BaseEvent be) {
                        if (checkIfPerimeterSelected(cb)) {
                            applyPerimetreForDelegataire(model, (cb.getValue()) ? 1 : 0, 2);
                        } else {
                            cb.setValue(!cb.getValue());
                            be.setCancelled(false);
                        }
                    }

                });
                return cb;
            }
        };

        delegant.setRenderer(delegantRender);
        delegataire.setRenderer(delegataireRender);
        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(name);
        config.add(delegant);
        config.add(perimetreDelegant);
        config.add(delegataire);
        config.add(perimetreDelegataire);

        final ColumnModel cm = new ColumnModel(config);

        grid = new Grid<CollaborateurModel>(store, cm);
        grid.setStateId("pagingGridCollaborature");
        grid.setStateful(true);

        GridViewConfig viewConfigView = new GridViewConfig() {

            @Override
            public String getRowStyle(ModelData model, int rowIndex, ListStore<ModelData> ds) {
                CollaborateurModel m = (CollaborateurModel) model;
                boolean isSortie = true;
                if (m.getSorti() == null || "N".equals(m.getSorti()) || m.getDateSortie() == null || m.getDateSortie().after(new Date())) {
                    isSortie = false;
                }

                if (isSortie) {
                    return "inactiveRowStyle";
                } else {
                    return "";
                }
            }
        };
        grid.getView().setViewConfig(viewConfigView);

        GridFilters filters = new GridFilters();
        filters.setLocal(true);
        StringFilter nameFilter = new StringFilter(CollaborateurModel.COLLA_FULL_NAME_NO_SEPARATER);
        filters.addFilter(nameFilter);

        grid.setBorders(true);
        grid.setLoadMask(true);
        grid.addPlugin(filters);
        grid.setSelectionModel(new GridSelectionModel<CollaborateurModel>());

        grid.getView().setAutoFill(true);
        grid.getView().setForceFit(true);
        WindowResizeBinder.bind(grid);

        ContentPanel panel = new ContentPanel();
        panel.setHeading(messages.collaboraturelistedescollaboratures());
        toolbarPanel.add(topToolBar);
        toolbarPanel.add(topSecondToolBar);
        toolbarPanel.add(topThirdToolBar);
        panel.setTopComponent(toolbarPanel);
        panel.setBottomComponent(toolBar);

        panel.setStyleAttribute("padding", "10px");

        panel.setAnimCollapse(false);
        panel.setCollapsible(true);
        panel.setFrame(true);
        panel.setSize(WIDTH, HEIGHT);
        panel.setLayout(new FitLayout());
        panel.add(grid);
        grid.getAriaSupport().setLabelledBy(panel.getHeader().getId() + "-label");

        add(panel);
    }

    @SuppressWarnings("unchecked")
    private TreePanel<PerimetreTreeModel> getAdminTree() {
        TreePanel<PerimetreTreeModel> component = (TreePanel<PerimetreTreeModel>) ComponentManager.get().get(ClientConstant.ADMIN_TREE_ID);
        return component;
    }

    protected void applyPerimetreDelegataire() {
        final CollaborateurModel currentCol = grid.getSelectionModel().getSelectedItem();
        applyPerimetreForDelegataire(currentCol, 1, 2);
    }

    protected void applyPerimetreForDelegataire(final CollaborateurModel model, int isDelegataire, int isAddOrReplace) {
        List<PerimetreTreeModel> selectedPerimetres = new ArrayList<PerimetreTreeModel>();
        TreePanel<PerimetreTreeModel> perimetreTree = getAdminTree();
        if (perimetreTree != null && model != null) {
            List<DelegatairePerimetreModel> relations = new ArrayList<DelegatairePerimetreModel>();
            model.setIsDelegataire(isDelegataire);
            if (isDelegataire > 0) {
                selectedPerimetres = perimetreTree.getSelectionModel() == null ? null : (List<PerimetreTreeModel>) perimetreTree.getSelectionModel()
                        .getSelectedItems();
                if (selectedPerimetres == null) {
                    // FIXME
                    // selectedPerimetres.add(perimetreTree.getStore().getRootItems().get(0));
                }
                boolean isSeted = false;
                // get all perimetres has UoAdmin
                for (PerimetreTreeModel item : selectedPerimetres) {
                    if (true == item.getIsUoAdmin()) {
                        String perId = null;
                        if (item.getIsEntite()) {
                            perId = item.getName();
                        } else {
                            perId = item.getPerId();
                        }

                        if (isDelegataire == 0) {
                            // model.setPerimetreDelegant(null);
                        } else {
                            DelegatairePerimetreModel relation = new DelegatairePerimetreModel();
                            relation.setDelegataire(model);
                            PerimetreModel p = new PerimetreModel();
                            p.setPerId(perId);
                            p.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
                            p.setName(item.getName());
                            relation.setPerimetre(p);
                            relations.add(relation);
                            if (!isSeted) {
                                model.setPerimetreDelegataire(p);
                            }
                            // model.setPerimetreDelegant(p);
                        }
                    }
                }
            } else {
                model.setPerimetreDelegataire(null);
            }
            model.setDelegatairePerimetres(relations);
            model.setChangeDelegatairePerimetres(isAddOrReplace);

            clientCollaboratureService.getAndUpdate(model, new AsyncCallbackWithErrorResolution<CollaborateurModel>() {

                @Override
                public void onSuccess(CollaborateurModel arg0) {
                    CollaborateurModel currentCol = model;
                    // CollaborateurModel currentCol = grid.getSelectionModel().getSelectedItem();
                    if (arg0 != null) {
                        // currentCol.setPerimetreDelegant(arg0.getPerimetreDelegant());
                        // StringBuffer extNames = new StringBuffer();
                        // for (DelegatairePerimetreModel e: currentCol.getDelegatairePerimetres()) {
                        // extNames.append("<br>").append(e.getPerimetre().getName());
                        // }
                        // if (extNames.length() >= 4) {
                        // currentCol.setDelegatairesPerimetreNames(extNames.substring(4));//(arg0.getPerimetreDelegant());
                        // } else {
                        // currentCol.setDelegatairesPerimetreNames(null);
                        // }
                        currentCol.setDelegatairesPerimetreNames(arg0.getDelegatairesPerimetreNames());
                        currentCol.setIsDelegataire(arg0.getIsDelegataire());
                        btnAddPerimetreDelegataire.setEnabled(currentCol.getIsDelegataire() != null && currentCol.getIsDelegataire() > 0);
                        grid.getStore().update(currentCol);
                    }
                }
            });

            // }
        }
    }

    protected void addPerimetreDelegant() {
        final CollaborateurModel currentCol = grid.getSelectionModel().getSelectedItem();
        applyPerimetreForDelegant(currentCol, 1, 1);
    }

    protected void addPerimetreDelegataire() {
        final CollaborateurModel currentCol = grid.getSelectionModel().getSelectedItem();
        applyPerimetreForDelegataire(currentCol, 1, 1);
    }

    protected void applyPerimetreDelegant() {
        final CollaborateurModel currentCol = grid.getSelectionModel().getSelectedItem();
        applyPerimetreForDelegant(currentCol, 1, 2);
    }

    private void applyPerimetreForDelegant(final CollaborateurModel model, int isDelegant, int isAddOrReplace) {
        List<PerimetreTreeModel> selectedPerimetres = new ArrayList<PerimetreTreeModel>();
        TreePanel<PerimetreTreeModel> perimetreTree = getAdminTree();
        if (perimetreTree != null && model != null) {
            List<DelegantPerimetreModel> relations = new ArrayList<DelegantPerimetreModel>();
            model.setIsDelegant(isDelegant);
            if (isDelegant > 0) {
                selectedPerimetres = perimetreTree.getSelectionModel() == null ? null : (List<PerimetreTreeModel>) perimetreTree.getSelectionModel()
                        .getSelectedItems();
                if (selectedPerimetres == null) {
                    // FIXME
                    // selectedPerimetres.add(perimetreTree.getStore().getRootItems().get(0));
                }
                boolean isSeted = false;
                // get all perimetres has UoAdmin
                for (PerimetreTreeModel item : selectedPerimetres) {
                    if (true == item.getIsUoAdmin()) {
                        String perId = null;
                        if (item.getIsEntite()) {
                            perId = item.getName();
                        } else {
                            perId = item.getPerId();
                        }

                        if (isDelegant == 0) {
                            // model.setPerimetreDelegant(null);
                        } else {
                            DelegantPerimetreModel relation = new DelegantPerimetreModel();
                            relation.setDelegant(model);
                            PerimetreModel p = new PerimetreModel();
                            p.setPerId(perId);
                            p.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
                            p.setName(item.getName());
                            relation.setPerimetre(p);
                            relations.add(relation);
                            if (!isSeted) {
                                model.setPerimetreDelegant(p);
                                isSeted = true;
                            }
                        }
                    }
                }
            } else {
                model.setPerimetreDelegant(null);
            }
            model.setDelegantPerimetres(relations);
            model.setChangeDelegantPerimetres(isAddOrReplace);
            clientCollaboratureService.getAndUpdate(model, new AsyncCallbackWithErrorResolution<CollaborateurModel>() {

                @Override
                public void onSuccess(CollaborateurModel arg0) {
                    CollaborateurModel currentCol = model;
                    if (arg0 != null) {
                        currentCol.setDelegantPerimetreNames(arg0.getDelegantPerimetreNames());
                        currentCol.setIsDelegant(arg0.getIsDelegant());
                        grid.getStore().update(currentCol);
                        btnAddPerimetreDelegant.setEnabled(currentCol.getIsDelegant() != null && currentCol.getIsDelegant() > 0);
                    }
                }
            });
        }

    }

    private boolean checkIfPerimeterSelected(CheckBox cb) {
        if (cb.getValue()) {
            TreePanel<PerimetreTreeModel> perimetreTree = getAdminTree();
            if (perimetreTree != null) {
                PerimetreTreeModel selectedPerimetre = null;
                selectedPerimetre = perimetreTree.getSelectionModel() == null ? null : (PerimetreTreeModel) perimetreTree.getSelectionModel()
                        .getSelectedItem();
                if (selectedPerimetre != null) {
                    if (true == selectedPerimetre.getIsUoAdmin()) {
                        return true;
                    } else {
                        Info.display(messages.commoninfo(), messages.commonnopermissionperimetre());
                        return false;
                    }
                } else {
                    Info.display(messages.commoninfo(), messages.admintreeselect());
                    return false;
                }
            }
            return false;
        } else {
            return true;
        }
    }
}
