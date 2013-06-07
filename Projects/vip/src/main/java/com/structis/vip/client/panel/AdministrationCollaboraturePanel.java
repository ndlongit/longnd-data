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

    private final int WIDTH = 800;
    private final int HEIGHT = 480;

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

        this.setLayout(new FlowLayout(0));
        this.setScrollMode(Scroll.AUTO);

        this.initUI();
        this.initEvent();
        this.addHandler();
    }

    private void addHandler() {
        this.bus.addHandler(ModifyCollaboratureEvent.getType(), new ModifyCollaboratureHandler() {

            @Override
            public void onLoadAction(ModifyCollaboratureEvent event) {
                disableEvents(true);
                initData();
                disableEvents(false);
            }
        });

        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(final DelegationListProjectEvent event) {
                disableEvents(true);
                // add BYTP
                disableEvents(false);
            }
        });

        this.bus.addHandler(SynETDEEvent.getType(), new SynETDEHandler() {

            @Override
            public void onLoadAction(SynETDEEvent event) {
                grid.mask(messages.commonsyncdata());

                clientSyncService.syncRUBISWithItems(SessionServiceImpl.getInstance().getEntiteContext().getEntId(), event.getModels(),
                        new AsyncCallback<List<CollaborateurModel>>() {

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
        this.pagingConfig.setOffset(0);
        this.pagingConfig.setLimit(ClientConstant.DEFAULT_PAGE_SIZE_50);
        this.loader.load(this.pagingConfig);
        this.btnAddPerimetreDelegataire.setEnabled(false);
        this.btnAddPerimetreDelegant.setEnabled(false);
    }

    private void initEvent() {
        this.grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<CollaborateurModel>() {

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

        this.grid.addListener(Events.RowDoubleClick, new Listener<BaseEvent>() {

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
                    clientCollaboratureService.delete(model, new AsyncCallback<Boolean>() {

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
                } else {
                }
            }
        };

        this.txtFilter.addListener(Events.OnKeyUp, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                String filter = txtFilter.getValue();
                if (filter != null) {
                    filter = (!"".equals(filter)) ? filter.trim() : "";
                } else {
                    filter = "";
                }
                pagingConfig.setOffset(0);
                pagingConfig.setLimit(ClientConstant.DEFAULT_PAGE_SIZE_50);
                pagingConfig.set("filterName", filter);
                loader.load(pagingConfig);
            }
        });

        this.btnImporter.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                SynETDEDialog dialog = new SynETDEDialog(bus, SessionServiceImpl.getInstance().getEntiteContext());
                dialog.show();
            }
        });

        this.btnSupprimer.addSelectionListener(new SelectionListener<ButtonEvent>() {

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

        this.btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {

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

        this.btnModifer.addSelectionListener(new SelectionListener<ButtonEvent>() {

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

        this.btnConsulter.addSelectionListener(new SelectionListener<ButtonEvent>() {

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

        this.btnApplyPerimetreDelegant.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                applyPerimetreDelegant();
            }
        });

        this.btnApplyPerimetreDelegataire.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                applyPerimetreDelegataire();
            }
        });
        this.btnAddPerimetreDelegant.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                addPerimetreDelegant();
            }
        });

        this.btnAddPerimetreDelegataire.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                addPerimetreDelegataire();
            }
        });

    }

    private void initUI() {
        this.proxy = new RpcProxy<PagingLoadResult<CollaborateurModel>>() {

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

        this.loader = new BasePagingLoader<PagingLoadResult<CollaborateurModel>>(this.proxy);
        this.loader.setRemoteSort(true);

        this.store = new ListStore<CollaborateurModel>(this.loader);

        this.toolBar = new PagingToolBar(ClientConstant.DEFAULT_PAGE_SIZE_50);

        this.toolBar.bind(this.loader);

        VerticalPanel toolbarPanel = new VerticalPanel();
        toolbarPanel.setTableWidth("100%");
        toolbarPanel.setBorders(false);
        toolbarPanel.setHeight(80);

        ToolBar topToolBar = new ToolBar();
        ToolBar topSecondToolBar = new ToolBar();
        ToolBar topThirdToolBar = new ToolBar();

        this.txtFilter = new TextField<String>();
        this.txtFilter.setTitle(messages.collaboraturenom());

        this.btnConsulter = new Button(messages.commonConsulterbutton());
        this.btnConsulter.setStyleAttribute("margin-left", "10px");
        this.btnConsulter.setIcon(IconHelper.createPath("html/view-icon.png"));
        this.btnConsulter.setEnabled(false);

        this.btnAdd = new Button(messages.commonCreerbutton());
        this.btnAdd.setStyleAttribute("margin-left", "10px");
        this.btnAdd.setIcon(IconHelper.createPath("html/add-icon.png"));

        this.btnModifer = new Button(messages.commonmodifierbutton());
        this.btnModifer.setIcon(IconHelper.createPath("html/save-icon.png"));
        this.btnModifer.setEnabled(false);

        this.btnSupprimer = new Button(messages.commonSupprimer());
        this.btnSupprimer.setIcon(IconHelper.createPath("html/delete-icon.png"));
        this.btnSupprimer.setEnabled(false);

        this.btnImporter = new Button(messages.collaboraturebuttonimportdepuisrubis());
        this.btnImporter.setIcon(IconHelper.createPath("html/import-icon.png"));
        // btnImporter.setToolTip("Currently we only support for ETDE...");

        // btnImporter = new Button(messages.collaboraturebuttonimportdepuisrubis());
        // btnImporter.setIcon(IconHelper.createPath("html/import-icon.png"));
        // btnImporter.setToolTip("Currently we only support for ETDE...");

        // tdo
        this.cbDisplaySortie = new CheckBox();
        this.cbDisplaySortie.setBoxLabel("Yc Sorties");
        this.cbDisplaySortie.addListener(Events.OnClick, new Listener<ComponentEvent>() {

            @Override
            public void handleEvent(ComponentEvent be) {
                pagingConfig.setOffset(0);
                pagingConfig.setLimit(ClientConstant.DEFAULT_PAGE_SIZE_50);
                if (cbDisplaySortie.getValue()) {
                    pagingConfig.set("displaySortie", true);
                } else {
                    pagingConfig.set("displaySortie", false);
                }
                loader.load(pagingConfig);
            }

        });

        this.btnApplyPerimetreDelegant = new Button(messages.collaboraturebuttonapplyperietredelegant());
        this.btnApplyPerimetreDelegant.setIcon(IconHelper.createPath("html/perDelegant.png"));
        this.btnApplyPerimetreDelegant.setEnabled(true);

        this.btnApplyPerimetreDelegataire = new Button(messages.collaboraturebuttonapplyperietredelegataire());
        this.btnApplyPerimetreDelegataire.setIcon(IconHelper.createPath("html/perDelegataire.png"));
        this.btnApplyPerimetreDelegataire.setEnabled(true);

        this.btnAddPerimetreDelegataire = new Button(messages.collaboraturebuttonaddperietredelegataire());
        this.btnAddPerimetreDelegataire.setIcon(IconHelper.createPath("html/addperDelegant.png"));
        this.btnAddPerimetreDelegataire.setEnabled(true);

        this.btnAddPerimetreDelegant = new Button(messages.collaboraturebuttonaddperietredelegataire());
        this.btnAddPerimetreDelegant.setIcon(IconHelper.createPath("html/addperDelegataire.png"));
        this.btnAddPerimetreDelegant.setEnabled(true);

        topToolBar.add(new LabelToolItem(messages.collaboraturerechercher()));
        topToolBar.add(this.txtFilter);
        topToolBar.add(this.btnAdd);
        topToolBar.add(this.btnConsulter);
        topToolBar.add(this.btnModifer);
        topToolBar.add(this.btnSupprimer);
        topToolBar.add(this.btnImporter);
        LabelToolItem sep = new LabelToolItem();
        sep.setWidth(60);
        topToolBar.add(sep);
        topToolBar.add(this.cbDisplaySortie);

        // topToolBar.add(new LabelToolItem("Sortie"));//tdo
        topSecondToolBar.add(this.btnApplyPerimetreDelegant);
        topThirdToolBar.add(this.btnApplyPerimetreDelegataire);
        topSecondToolBar.add(this.btnAddPerimetreDelegant);
        topThirdToolBar.add(this.btnAddPerimetreDelegataire);

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
        // perimetreDelegant.setRenderer(perimetreDelegantRender);
        // perimetreDelegataire.setRenderer(perimetreDelegataireRender);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(name);
        config.add(delegant);
        config.add(perimetreDelegant);
        config.add(delegataire);
        config.add(perimetreDelegataire);

        final ColumnModel cm = new ColumnModel(config);

        this.grid = new Grid<CollaborateurModel>(this.store, cm);
        this.grid.setStateId("pagingGridCollaborature");
        this.grid.setStateful(true);

        // grid.addListener(Events.Attach, new Listener<GridEvent<CollaborateurModel>>() {
        // public void handleEvent(GridEvent<CollaborateurModel> be) {
        // loader.load(0, ConstantClient.DEFAULT_PAGE_SIZE_50);
        // }
        // });
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
        this.grid.getView().setViewConfig(viewConfigView);

        GridFilters filters = new GridFilters();
        filters.setLocal(true);
        StringFilter nameFilter = new StringFilter(CollaborateurModel.COLLA_FULL_NAME_NO_SEPARATER);
        filters.addFilter(nameFilter);

        this.grid.setBorders(true);
        this.grid.setLoadMask(true);
        this.grid.addPlugin(filters);
        this.grid.setSelectionModel(new GridSelectionModel<CollaborateurModel>());

        this.grid.getView().setAutoFill(true);
        this.grid.getView().setForceFit(true);
        WindowResizeBinder.bind(this.grid);

        ContentPanel panel = new ContentPanel();
        panel.setHeading(messages.collaboraturelistedescollaboratures());
        toolbarPanel.add(topToolBar);
        toolbarPanel.add(topSecondToolBar);
        toolbarPanel.add(topThirdToolBar);
        panel.setTopComponent(toolbarPanel);
        panel.setBottomComponent(this.toolBar);

        panel.setStyleAttribute("padding", "10px");

        panel.setAnimCollapse(false);
        panel.setCollapsible(true);
        panel.setFrame(true);
        panel.setSize(this.WIDTH, this.HEIGHT);
        panel.setLayout(new FitLayout());
        panel.add(this.grid);
        this.grid.getAriaSupport().setLabelledBy(panel.getHeader().getId() + "-label");

        this.add(panel);
    }

    @SuppressWarnings("unchecked")
    private TreePanel<PerimetreTreeModel> getAdminTree() {
        TreePanel<PerimetreTreeModel> component = (TreePanel<PerimetreTreeModel>) ComponentManager.get().get(ClientConstant.ADMIN_TREE_ID);
        return component;
    }

    protected void applyPerimetreDelegataire() {
        final CollaborateurModel currentCol = this.grid.getSelectionModel().getSelectedItem();
        this.applyPerimetreForDelegataire(currentCol, 1, 2);
    }

    protected void applyPerimetreForDelegataire(final CollaborateurModel model, int isDelegataire, int isAddOrReplace) {
        List<PerimetreTreeModel> selectedPerimetres = new ArrayList<PerimetreTreeModel>();
        TreePanel<PerimetreTreeModel> perimetreTree = this.getAdminTree();
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

            this.clientCollaboratureService.getAndUpdate(model, new AsyncCallback<CollaborateurModel>() {

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

                @Override
                public void onFailure(Throwable arg0) {

                }
            });

            // }
        }
    }

    protected void addPerimetreDelegant() {
        final CollaborateurModel currentCol = this.grid.getSelectionModel().getSelectedItem();
        this.applyPerimetreForDelegant(currentCol, 1, 1);
    }

    protected void addPerimetreDelegataire() {
        final CollaborateurModel currentCol = this.grid.getSelectionModel().getSelectedItem();
        this.applyPerimetreForDelegataire(currentCol, 1, 1);
    }

    protected void applyPerimetreDelegant() {
        final CollaborateurModel currentCol = this.grid.getSelectionModel().getSelectedItem();
        this.applyPerimetreForDelegant(currentCol, 1, 2);
    }

    private void applyPerimetreForDelegant(final CollaborateurModel model, int isDelegant, int isAddOrReplace) {
        List<PerimetreTreeModel> selectedPerimetres = new ArrayList<PerimetreTreeModel>();
        TreePanel<PerimetreTreeModel> perimetreTree = this.getAdminTree();
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
            this.clientCollaboratureService.getAndUpdate(model, new AsyncCallback<CollaborateurModel>() {

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

                @Override
                public void onFailure(Throwable arg0) {

                }
            });
        }

    }

    private boolean checkIfPerimeterSelected(CheckBox cb) {
        if (cb.getValue()) {
            PerimetreTreeModel selectedPerimetre = null;
            TreePanel<PerimetreTreeModel> perimetreTree = this.getAdminTree();
            if (perimetreTree != null) {
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
