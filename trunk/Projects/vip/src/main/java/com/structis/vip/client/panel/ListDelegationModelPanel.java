package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.dialog.ApplyDelegantTypeDialog;
import com.structis.vip.client.dialog.ApplyDocumentDialog;
import com.structis.vip.client.dialog.ApplyPerimetreTypeDialog;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationModelEvent;
import com.structis.vip.client.event.DelegationModelHandler;
import com.structis.vip.client.event.FieldRuleEvent;
import com.structis.vip.client.event.LoadGroupDelegationModelEvent;
import com.structis.vip.client.event.RefreshDelegantGridEvent;
import com.structis.vip.client.event.RefreshDelegantGridHandler;
import com.structis.vip.client.event.RefreshPerimetreTypeGridEvent;
import com.structis.vip.client.event.RefreshPerimetreTypeGridHandler;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientCollaborateurTypeServiceAsync;
import com.structis.vip.client.service.ClientDelegationModelServiceAsync;
import com.structis.vip.client.service.ClientDelegationNatureServiceAsync;
import com.structis.vip.client.service.ClientDemDomServiceAsync;
import com.structis.vip.client.service.ClientFieldRuleServiceAsync;
import com.structis.vip.client.service.ClientFieldServiceAsync;
import com.structis.vip.client.service.ClientLanguageServiceAsync;
import com.structis.vip.client.service.ClientPerimetreTypeServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.model.CollaborateurTypeModel;
import com.structis.vip.shared.model.DelegationMdlModel;
import com.structis.vip.shared.model.DelegationNatureModel;
import com.structis.vip.shared.model.DemDomModel;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.FieFieldModel;
import com.structis.vip.shared.model.FieldRuleModel;
import com.structis.vip.shared.model.LanguageModel;
import com.structis.vip.shared.model.PerimetreTypeModel;

public class ListDelegationModelPanel extends LayoutContainer {

    private static int WIDTH = 800;
    private static int GRID_HEIGHT = 145;
    private final Messages messages = GWT.create(Messages.class);
    private final FormData formData = new FormData("98%");

    SimpleEventBus bus;
    private ListStore<DelegationNatureModel> lstDelegationNature = new ListStore<DelegationNatureModel>();
    private ClientDelegationNatureServiceAsync clientDelegationNatureService = ClientDelegationNatureServiceAsync.Util.getInstance();
    private ClientDelegationModelServiceAsync clientDelegationModelServiceAsync = ClientDelegationModelServiceAsync.Util.getInstance();
    private ClientPerimetreTypeServiceAsync clientPerimetreTypeServiceAsync = ClientPerimetreTypeServiceAsync.Util.getInstance();
    private ClientCollaborateurTypeServiceAsync clientCollaborateurTypeServiceAsync = ClientCollaborateurTypeServiceAsync.Util.getInstance();
    private ClientLanguageServiceAsync clientLanguageServiceAsync = ClientLanguageServiceAsync.Util.getInstance();
    private ClientDemDomServiceAsync clientDemDomServiceAsync = ClientDemDomServiceAsync.Util.getInstance();

    private ClientFieldRuleServiceAsync clientFieldRuleService = ClientFieldRuleServiceAsync.Util.getInstance();
    private ClientFieldServiceAsync clientFieldService = ClientFieldServiceAsync.Util.getInstance();

    private ListStore<LanguageModel> languages = new ListStore<LanguageModel>();
    private ListStore<PerimetreTypeModel> perimetreTypes = new ListStore<PerimetreTypeModel>();
    private ListStore<CollaborateurTypeModel> delegantTypes = new ListStore<CollaborateurTypeModel>();

    private ListStore<DelegationMdlModel> ptyStore;
    private ListStore<DelegationMdlModel> cotStore;

    private EditorGrid<DelegationMdlModel> delegationModelGrid;
    private ComboBox<DelegationNatureModel> cbNature;
    private ComboBox<LanguageModel> cbLanguage;

    private Integer group = 0;
    private Button btnSave;
    private Button btnAnnuler;
    private Button btnEdit;
    private Button btnAddDocument;

    private TabPanel tab;
    private FieldRulePanel rulePanel;
    private LayoutContainer filterPanel;
    private ContentPanel delegationModelPanel;

    private ContentPanel delegantTypePanel;

    private ListStore<DocumentMdlModel> documentMdlModels;
    private Grid<DocumentMdlModel> documentGrid;

    private DelegationModelEvent mEvent;
    private CheckBox cbHasMultipleDelegation;
    private CheckBox cbHasMultipleDelegataire;
    private CheckBox cbSubDelegation;

    public ListDelegationModelPanel(SimpleEventBus bus) {
        this.bus = bus;

        this.rulePanel = new FieldRulePanel(this.bus);

        this.setLayout(new BorderLayout());
        this.setStyleAttribute("padding", "10px");
        this.setStyleAttribute("paddingTop", "90px");
        this.setScrollMode(Scroll.AUTO);

        this.addBackLink();
        this.initFilter();
        this.initButton();
        this.initGrid();
        this.initDocument();

        this.bus.addHandler(DelegationModelEvent.getType(), new DelegationModelHandler() {

            @Override
            public void onLoadAction(DelegationModelEvent event) {
                AppUtil.putInAdminEditMode();
                ListDelegationModelPanel.this.mEvent = event;
                ListDelegationModelPanel.this.disableEvents(true);
                switch (event.getMode()) {
                case DelegationModelEvent.MODE_IS_NEW:
                    ListDelegationModelPanel.this.newMode(event);
                    break;
                case DelegationModelEvent.MODE_IS_EDIT:
                    ListDelegationModelPanel.this.editMode(event);
                    break;
                case DelegationModelEvent.MODE_IS_UPDATE_DOCUMENT:
                    ListDelegationModelPanel.this.changeDocumentTable();
                    break;
                }
                ListDelegationModelPanel.this.checkAndInsertFieldRule(event);
                ListDelegationModelPanel.this.disableEvents(false);
            }
        });
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);
        GWT.log(this.getClass().getName() + ":onRender");
    }

    private void newMode(DelegationModelEvent event) {
        // set group
        this.group = 0;

        // remove last information
        this.cbNature.setValue(null);
        this.cbLanguage.setValue(null);
        this.cbHasMultipleDelegation.setValue(false);
        this.cbHasMultipleDelegataire.setValue(false);
        this.cbSubDelegation.setValue(false);
        this.ptyStore.removeAll();
        this.cotStore.removeAll();
        this.tab.setVisible(false);
        this.documentMdlModels.removeAll();

        // update button
        this.btnEdit.setEnabled(false);
        this.btnAddDocument.setEnabled(false);
        this.btnSave.setText(this.messages.commonValiderButton());
        this.btnAnnuler.setText(this.messages.commonAnnulerButton());

        // init data
        this.initData();
    }

    private void editMode(DelegationModelEvent event) {
        // set group
        this.group = event.getGroup();

        // set value for edit
        this.cbNature.setValue(event.getNatureModel());
        this.cbLanguage.setValue(event.getLanguageModel());
        this.cbHasMultipleDelegation.setValue(event.getHasMultipleDelegation() == null || event.getHasMultipleDelegation() == 0 ? false : true);
        this.cbHasMultipleDelegataire.setValue(event.getHasMultipleDelegataire() == null || event.getHasMultipleDelegataire() == 0 ? false : true);
        this.cbSubDelegation.setValue(event.getSubDelegation() == null || event.getSubDelegation() == 0 ? false : true);

        // update button
        this.btnEdit.setEnabled(true);
        this.btnAddDocument.setEnabled(true);
        this.tab.setVisible(true);
        this.btnSave.setText(this.messages.commonModifierButton());
        this.btnAnnuler.setText(this.messages.commonAnnulerButton());

        // remove last information
        this.ptyStore.removeAll();
        this.documentMdlModels.removeAll();

        // init data
        this.initData();
        this.changeDocumentTable();

        this.delegationModelGrid.mask(this.messages.commonloadingdata());
        this.clientDelegationModelServiceAsync.getDelegationModelsByGroup(this.group,
                new AsyncCallbackWithErrorResolution<List<DelegationMdlModel>>() {

                    @Override
                    public void onSuccess(List<DelegationMdlModel> arg0) {
                        ListDelegationModelPanel.this.cotStore.removeAll();
                        ListDelegationModelPanel.this.ptyStore.removeAll();
                        this.splitIntoStores(arg0, ListDelegationModelPanel.this.cotStore, ListDelegationModelPanel.this.ptyStore);
                        // ptyStore.add(arg0);
                        ListDelegationModelPanel.this.delegationModelGrid.unmask();
                    }

                    private void splitIntoStores(List<DelegationMdlModel> arg0, ListStore<DelegationMdlModel> cotStore,
                            ListStore<DelegationMdlModel> ptyStore) {
                        for (DelegationMdlModel m : arg0) {
                            if (m.getPerimetreType() != null && !ptyStore.contains(m)) {
                                ptyStore.add(m);
                            }
                            if (m.getCollaborateurType() != null && !cotStore.contains(m)) {
                                cotStore.add(m);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                        ListDelegationModelPanel.this.delegationModelGrid.unmask();
                    }
                });
    }

    private void checkAndInsertFieldRule(final DelegationModelEvent event) {
        if (event.getGroup() != 0) {
            this.clientFieldRuleService.getRulesByDemGroup(event.getGroup(), new AsyncCallbackWithErrorResolution<List<FieldRuleModel>>() {

                @Override
                public void onSuccess(List<FieldRuleModel> arg0) {
                    if (arg0.size() == 0) {
                        ListDelegationModelPanel.this.clientFieldService.getFieldsByEntiteId(SessionServiceImpl.getInstance().getEntiteContext()
                                .getEntId(), new AsyncCallbackWithErrorResolution<List<FieFieldModel>>() {

                            @Override
                            public void onSuccess(List<FieFieldModel> arg0) {
                                if (arg0 != null && arg0.size() > 0) {
                                    List<FieldRuleModel> fields = new ArrayList<FieldRuleModel>();
                                    FieldRuleModel model;
                                    for (FieFieldModel fieFieldModel : arg0) {
                                        model = new FieldRuleModel();
                                        model.setGroup(event.getGroup());
                                        model.setIsDisplayed(0);
                                        model.setIsRequired(0);
                                        model.setField(fieFieldModel);

                                        fields.add(model);
                                    }

                                    ListDelegationModelPanel.this.clientFieldRuleService.insertList(fields,
                                            new AsyncCallbackWithErrorResolution<List<FieldRuleModel>>() {

                                                @Override
                                                public void onSuccess(List<FieldRuleModel> arg0) {
                                                    FieldRuleEvent frEvent = new FieldRuleEvent();
                                                    frEvent.setEntite(event.getEntiteModel());
                                                    frEvent.setGroup(event.getGroup());
                                                    ListDelegationModelPanel.this.bus.fireEvent(frEvent);
                                                }
                                            });
                                }
                            }
                        });
                    } else {
                        FieldRuleEvent frEvent = new FieldRuleEvent();
                        frEvent.setEntite(event.getEntiteModel());
                        frEvent.setGroup(event.getGroup());
                        ListDelegationModelPanel.this.bus.fireEvent(frEvent);
                    }
                }
            });
        }
    }

    /**
     * initilize top filter
     */
    private void initFilter() {
        // setup top layout
        this.filterPanel = new LayoutContainer();
        this.filterPanel.setSize(WIDTH, -1);
        this.filterPanel.setStyleAttribute("marginTop", "40px");
        this.filterPanel.setStyleAttribute("marginLeft", "10px");
        this.filterPanel.setLayout(new ColumnLayout());

        // setup sub layout for first field
        LayoutContainer lcSubTop = new LayoutContainer();
        lcSubTop.setStyleAttribute("paddingRight", "5px");
        FormLayout flSubTop = new FormLayout();
        flSubTop.setLabelWidth(60);
        flSubTop.setLabelAlign(LabelAlign.LEFT);
        lcSubTop.setLayout(flSubTop);

        // setup sub layout for first field
        lcSubTop = new LayoutContainer();
        // lcSubTop.setStyleAttribute("paddingRight", "10px");
        flSubTop = new FormLayout();
        flSubTop.setLabelWidth(60);
        flSubTop.setLabelAlign(LabelAlign.LEFT);
        lcSubTop.setLayout(flSubTop);

        this.cbNature = new ComboBox<DelegationNatureModel>();
        this.cbNature.setFieldLabel(this.messages.nature());
        this.cbNature.setDisplayField(DelegationNatureModel.DELE_NATURE_NAME);
        this.cbNature.setStore(this.lstDelegationNature);
        this.cbNature.setTriggerAction(TriggerAction.ALL);
        this.cbNature.setEditable(false);
        this.cbNature.setAllowBlank(false);
        this.cbNature.setSimpleTemplate("<span title=\"{" + this.cbNature.getDisplayField() + "}\">{" + this.cbNature.getDisplayField() + "}</span>");
        lcSubTop.add(this.cbNature, this.formData);

        this.filterPanel.add(lcSubTop, new ColumnData(.5));

        // setup sub layout for first field
        lcSubTop = new LayoutContainer();
        // lcSubTop.setStyleAttribute("paddingRight", "10px");
        flSubTop = new FormLayout();
        flSubTop.setLabelWidth(60);
        flSubTop.setLabelAlign(LabelAlign.LEFT);
        lcSubTop.setLayout(flSubTop);

        this.cbLanguage = new ComboBox<LanguageModel>();
        this.cbLanguage.setFieldLabel(this.messages.commonlanguage());
        this.cbLanguage.setDisplayField(LanguageModel.LAG_NAME);
        this.cbLanguage.setStore(this.languages);
        this.cbLanguage.setWidth(10);
        this.cbLanguage.setTriggerAction(TriggerAction.ALL);
        this.cbLanguage.setEditable(false);
        this.cbLanguage.setAllowBlank(false);
        lcSubTop.add(this.cbLanguage, this.formData);

        this.filterPanel.add(lcSubTop, new ColumnData(0.25));

        LayoutContainer selectPanel = new LayoutContainer();
        selectPanel.setSize(120, 120);
        selectPanel.setStyleAttribute("marginTop", "0px");
        selectPanel.setStyleAttribute("marginLeft", "0px");
        selectPanel.setLayout(new RowLayout());

        lcSubTop = new LayoutContainer();
        // lcSubTop.setStyleAttribute("paddingRight", "10px");
        flSubTop = new FormLayout();
        flSubTop.setLabelWidth(30);
        flSubTop.setLabelAlign(LabelAlign.LEFT);
        lcSubTop.setLayout(flSubTop);
        this.cbHasMultipleDelegation = new CheckBox();
        this.cbHasMultipleDelegation.setLabelSeparator("");
        this.cbHasMultipleDelegation.setBoxLabel("Délégation multiple");
        lcSubTop.add(this.cbHasMultipleDelegation, this.formData);

        selectPanel.add(lcSubTop);

        lcSubTop = new LayoutContainer();
        flSubTop = new FormLayout();
        flSubTop.setLabelWidth(30);
        flSubTop.setLabelAlign(LabelAlign.LEFT);
        lcSubTop.setLayout(flSubTop);
        this.cbHasMultipleDelegataire = new CheckBox();
        this.cbHasMultipleDelegataire.setLabelSeparator("");
        this.cbHasMultipleDelegataire.setBoxLabel("Délégataire multiple");
        lcSubTop.add(this.cbHasMultipleDelegataire, this.formData);
        selectPanel.add(lcSubTop);

        lcSubTop = new LayoutContainer();
        flSubTop = new FormLayout();
        flSubTop.setLabelWidth(30);
        flSubTop.setLabelAlign(LabelAlign.LEFT);
        lcSubTop.setLayout(flSubTop);
        this.cbSubDelegation = new CheckBox();
        this.cbSubDelegation.setLabelSeparator("");
        this.cbSubDelegation.setBoxLabel("Sous Delegation");
        lcSubTop.add(this.cbSubDelegation, this.formData);
        selectPanel.add(lcSubTop);

        this.filterPanel.add(selectPanel, new ColumnData(0.25));

        this.add(this.filterPanel);
    }

    private void initGrid() {
        this.btnAddDocument = new Button(this.messages.delegationmodeladddocumentbutton());
        this.btnAddDocument.setIcon(IconHelper.createPath("html/edit-icon.png"));
        this.btnAddDocument.setEnabled(false);
        this.btnAddDocument.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (ListDelegationModelPanel.this.group != 0) {
                    ApplyDocumentDialog dialog = new ApplyDocumentDialog(ListDelegationModelPanel.this.bus);
                    dialog.setData(SessionServiceImpl.getInstance().getEntiteContext(), ListDelegationModelPanel.this.group);
                    dialog.show();
                }
            }
        });

        ContentPanel mainPanel = new ContentPanel();
        mainPanel.setButtonAlign(HorizontalAlignment.LEFT);
        mainPanel.setHeading(this.messages.delegationmodelheading());
        // mainPanel.setHeaderVisible(false);
        mainPanel.setBodyBorder(true);
        mainPanel.setBorders(true);
        mainPanel.setLayout(new RowLayout());

        mainPanel.setScrollMode(Scroll.NONE);

        LayoutContainer delegationModelContainer = new LayoutContainer();
        delegationModelContainer.setHeight(300);
        delegationModelContainer.setLayout(new ColumnLayout());

        mainPanel.add(delegationModelContainer);

        this.initPerimetreTypeGrid();
        this.initDelegantTypeGrid();

        delegationModelContainer.add(this.delegationModelPanel, new ColumnData(0.5));
        delegationModelContainer.add(this.delegantTypePanel, new ColumnData(0.5));
        mainPanel.add(delegationModelContainer, new RowData(1, GRID_HEIGHT));
        mainPanel.addButton(this.btnSave);
        mainPanel.addButton(this.btnAnnuler);

        BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH, 210);
        northData.setMargins(new Margins(22, 0, 5, 0));
        northData.setCollapsible(true);
        northData.setSplit(true);
        this.add(mainPanel, northData);
        // add(delegationModelPanel, northData);
    }

    private void initPerimetreTypeGrid() {
        ToolBar toolBar = new ToolBar();
        final Button btnAdd = new Button(this.messages.delegationmodelruleaddbutton());

        btnAdd.setIcon(IconHelper.createPath("html/icon/perimetresetting.png"));
        btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ApplyPerimetreTypeDialog dialog = new ApplyPerimetreTypeDialog(ListDelegationModelPanel.this.bus);
                dialog.setData(SessionServiceImpl.getInstance().getEntiteContext(), ListDelegationModelPanel.this.ptyStore.getModels());
                dialog.show();
            }
        });
        toolBar.add(btnAdd);

        this.ptyStore = new ListStore<DelegationMdlModel>();

        ColumnConfig cfPerimetreType = new ColumnConfig("perimetreType.name", this.messages.delegationmodelperimetretype(), 320);

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(cfPerimetreType);

        final ColumnModel cm = new ColumnModel(config);

        this.delegationModelGrid = new EditorGrid<DelegationMdlModel>(this.ptyStore, cm);
        this.delegationModelGrid.setBorders(false);
        this.delegationModelGrid.getView().setAutoFill(true);
        this.delegationModelGrid.getView().setForceFit(true);

        this.delegationModelGrid.setAutoWidth(true);
        WindowResizeBinder.bind(this.delegationModelGrid);

        this.delegationModelPanel = new ContentPanel();
        this.delegationModelPanel.setHeaderVisible(false);
        this.delegationModelPanel.setTopComponent(toolBar);

        this.delegationModelPanel.setLayout(new FitLayout());
        this.delegationModelPanel.setStyleAttribute("paddingRight", "10px");

        this.delegationModelPanel.setButtonAlign(HorizontalAlignment.LEFT);
        this.delegationModelPanel.add(this.delegationModelGrid);// , new RowData(-1, 200));
        this.delegationModelPanel.setHeight(GRID_HEIGHT);

        this.delegationModelGrid.getAriaSupport().setLabelledBy(this.delegationModelPanel.getHeader().getId() + "-label");

        this.bus.addHandler(RefreshPerimetreTypeGridEvent.getType(), new RefreshPerimetreTypeGridHandler() {

            @Override
            public void onLoadAction(RefreshPerimetreTypeGridEvent event) {
                ListDelegationModelPanel.this.ptyStore.removeAll();
                if (event.getPerimetreTypes() != null) {
                    for (PerimetreTypeModel c : event.getPerimetreTypes()) {
                        DelegationMdlModel mdl = new DelegationMdlModel();
                        mdl.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
                        mdl.setDelegationNature(ListDelegationModelPanel.this.cbNature.getValue());
                        mdl.setLanguage(ListDelegationModelPanel.this.cbLanguage.getValue());
                        mdl.setPerimetreType(c);
                        mdl.setGroup(ListDelegationModelPanel.this.group);
                        ListDelegationModelPanel.this.ptyStore.add(mdl);
                    }
                }
            }
        });
    }

    private void initDelegantTypeGrid() {
        this.cotStore = new ListStore<DelegationMdlModel>();
        ToolBar toolBar = new ToolBar();
        final Button btnAdd = new Button(this.messages.delegationmodelruleadddelegantbutton());
        btnAdd.setIcon(IconHelper.createPath("html/icon/delegantsetting.png"));
        btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ApplyDelegantTypeDialog dialog = new ApplyDelegantTypeDialog(ListDelegationModelPanel.this.bus);
                dialog.setData(SessionServiceImpl.getInstance().getEntiteContext(), ListDelegationModelPanel.this.cotStore.getModels());
                dialog.show();
            }
        });

        toolBar.add(btnAdd);

        // setup grid for external controlers
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        // Column documents
        ColumnConfig delegantTypeColumn = new ColumnConfig("collaborateurType.name", this.messages.delegationmodeldeleganttype(), 320);
        configs.add(delegantTypeColumn);

        // setup column model
        ColumnModel columnModel = new ColumnModel(configs);

        Grid<DelegationMdlModel> delegantGrid = new Grid<DelegationMdlModel>(this.cotStore, columnModel);
        delegantGrid.setBorders(true);
        delegantGrid.getView().setAutoFill(true);
        delegantGrid.getView().setForceFit(true);

        // delegantGrid.setAutoWidth(true);
        // WindowResizeBinder.bind(delegantGrid);

        this.delegantTypePanel = new ContentPanel();
        this.delegantTypePanel.setStyleAttribute("paddingLeft", "10px");

        this.delegantTypePanel.setHeaderVisible(false);
        this.delegantTypePanel.setTopComponent(toolBar);
        this.delegantTypePanel.setLayout(new FitLayout());

        this.delegantTypePanel.setButtonAlign(HorizontalAlignment.LEFT);
        this.delegantTypePanel.add(delegantGrid);// , new RowData(-1, 200));
        this.delegantTypePanel.setHeight(GRID_HEIGHT);

        this.bus.addHandler(RefreshDelegantGridEvent.getType(), new RefreshDelegantGridHandler() {

            @Override
            public void onLoadAction(RefreshDelegantGridEvent event) {
                ListDelegationModelPanel.this.cotStore.removeAll();
                if (event.getDelegantTypes() != null) {
                    for (CollaborateurTypeModel c : event.getDelegantTypes()) {
                        DelegationMdlModel mdl = new DelegationMdlModel();
                        mdl.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
                        mdl.setDelegationNature(ListDelegationModelPanel.this.cbNature.getValue());
                        mdl.setLanguage(ListDelegationModelPanel.this.cbLanguage.getValue());
                        mdl.setCollaborateurType(c);
                        mdl.setGroup(ListDelegationModelPanel.this.group);
                        ListDelegationModelPanel.this.cotStore.add(mdl);
                    }

                }
            }
        });

    }

    private void initData() {
        this.clientDelegationNatureService.findNatureByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallbackWithErrorResolution<List<DelegationNatureModel>>() {

                    @Override
                    public void onSuccess(List<DelegationNatureModel> arg0) {
                        ListDelegationModelPanel.this.lstDelegationNature.removeAll();
                        ListDelegationModelPanel.this.lstDelegationNature.add(arg0);
                        ListDelegationModelPanel.this.cbNature.setStore(ListDelegationModelPanel.this.lstDelegationNature);
                        if (ListDelegationModelPanel.this.cbNature.getValue() == null) {
                            if (arg0 != null && arg0.size() > 0) {
                                DelegationNatureModel em = arg0.get(0);
                                ListDelegationModelPanel.this.cbNature.select(0);
                                ListDelegationModelPanel.this.cbNature.setValue(em);
                            }
                        }
                    }
                });

        this.clientLanguageServiceAsync.getLanguages(new AsyncCallbackWithErrorResolution<List<LanguageModel>>() {

            @Override
            public void onSuccess(List<LanguageModel> arg0) {
                ListDelegationModelPanel.this.languages.removeAll();
                ListDelegationModelPanel.this.languages.add(arg0);
                if (ListDelegationModelPanel.this.cbLanguage.getValue() == null) {
                    if (arg0 != null && arg0.size() > 0) {
                        LanguageModel em = arg0.get(0);
                        ListDelegationModelPanel.this.cbLanguage.select(0);
                        ListDelegationModelPanel.this.cbLanguage.setValue(em);
                    }
                }
            }
        });
        this.clientPerimetreTypeServiceAsync.getPerimetreTypes(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallbackWithErrorResolution<List<PerimetreTypeModel>>() {

                    @Override
                    public void onSuccess(List<PerimetreTypeModel> arg0) {
                        ListDelegationModelPanel.this.perimetreTypes.removeAll();
                        ListDelegationModelPanel.this.perimetreTypes.add(arg0);
                    }
                });

        this.clientCollaborateurTypeServiceAsync.getCollaborateurTypeByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallbackWithErrorResolution<List<CollaborateurTypeModel>>() {

                    @Override
                    public void onSuccess(List<CollaborateurTypeModel> arg0) {
                        ListDelegationModelPanel.this.delegantTypes.removeAll();
                        ListDelegationModelPanel.this.delegantTypes.add(arg0);
                    }
                });
    }

    private void initDocument() {
        // setup grid for document view
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        // Column documents
        ColumnConfig column = new ColumnConfig();
        column.setId(DocumentMdlModel.DOM_NAME);
        // column.setHeader(messages.delegationmodeldocumentheader());
        column.setWidth(400);
        column.setRowHeader(true);
        column.setSortable(false);
        configs.add(column);

        // setup column model
        ColumnModel cm = new ColumnModel(configs);

        // Content panel with header
        ContentPanel cp = new ContentPanel();
        cp.setBodyBorder(false);
        cp.setHeaderVisible(false);
        cp.setButtonAlign(HorizontalAlignment.CENTER);
        cp.setLayout(new RowLayout());
        cp.setAutoHeight(true);
        cp.setWidth(WIDTH);
        cp.setButtonAlign(HorizontalAlignment.LEFT);
        cp.setStyleAttribute("padding", "2px");

        this.documentMdlModels = new ListStore<DocumentMdlModel>();
        this.documentGrid = new Grid<DocumentMdlModel>(this.documentMdlModels, cm);
        this.documentGrid.setStyleAttribute("borderTop", "none");
        this.documentGrid.setAutoHeight(true);
        this.documentGrid.setAutoExpandColumn(DocumentMdlModel.DOM_NAME);
        this.documentGrid.setBorders(false);
        this.documentGrid.setStripeRows(true);
        this.documentGrid.setColumnLines(true);
        this.documentGrid.setColumnReordering(true);
        this.documentGrid.getView().setAutoFill(true);
        this.documentGrid.getView().setForceFit(true);
        WindowResizeBinder.bind(this.documentGrid);
        this.documentGrid.getAriaSupport().setLabelledBy(cp.getId() + "-label");

        cp.add(this.documentGrid);
        cp.addButton(this.btnAddDocument);

        this.tab = new TabPanel();
        this.tab.setWidth(WIDTH);
        this.tab.setAutoHeight(true);
        this.tab.setVisible(false);

        TabItem documentTab = new TabItem(this.messages.delegationmodeldocumentheader());
        documentTab.add(cp);
        documentTab.setAutoHeight(true);

        TabItem ruleTab = new TabItem(this.messages.commonrulebutton());
        ruleTab.add(this.rulePanel);
        ruleTab.setAutoHeight(true);

        this.tab.add(documentTab);
        this.tab.add(ruleTab);

        // this.add(tab);

        BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
        centerData.setMargins(new Margins(5, 0, 0, 0));
        centerData.setSplit(true);
        centerData.setCollapsible(true);

        this.add(this.tab, centerData);
    }

    /**
     * initilize bottom button
     */
    private void initButton() {
        HorizontalPanel hpButton = new HorizontalPanel();
        hpButton.setHorizontalAlign(HorizontalAlignment.LEFT);
        hpButton.setTableWidth("200");

        this.btnSave = new Button(this.messages.commonmodifierbutton());
        this.btnEdit = new Button(this.messages.commonrulebutton());

        this.btnSave.setIcon(IconHelper.createPath("html/save-icon.png"));

        this.btnAnnuler = new Button(this.messages.commonAnnulerButton());
        this.btnAnnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent contentEvent = new ContentEvent();
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GROUP_DELEGATION_MODEL_ADMIN_FORM);
                contentEvent.setEvent(new LoadGroupDelegationModelEvent());
                ListDelegationModelPanel.this.bus.fireEvent(contentEvent);
                AppUtil.removeAdminInEditMode();
            }
        });

        this.btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (ListDelegationModelPanel.this.ptyStore.getCount() != 0) {
                    for (int i = 0; i < ListDelegationModelPanel.this.ptyStore.getCount(); i++) {
                        DelegationMdlModel pty = ListDelegationModelPanel.this.ptyStore.getAt(i);
                        pty.setId(null);
                        pty.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
                        pty.setDelegationNature(ListDelegationModelPanel.this.cbNature.getValue());
                        pty.setLanguage(ListDelegationModelPanel.this.cbLanguage.getValue());
                        pty.setHasMultipleDelegation(ListDelegationModelPanel.this.cbHasMultipleDelegation.getValue() ? 1 : 0);
                        pty.setHasMultipleDelegataire(ListDelegationModelPanel.this.cbHasMultipleDelegataire.getValue() ? 1 : 0);
                        pty.setSubDelegation(ListDelegationModelPanel.this.cbSubDelegation.getValue() ? 1 : 0);
                    }
                }
                if (ListDelegationModelPanel.this.cotStore.getCount() != 0) {
                    for (int i = 0; i < ListDelegationModelPanel.this.cotStore.getCount(); i++) {
                        DelegationMdlModel cot = ListDelegationModelPanel.this.cotStore.getAt(i);
                        cot.setId(null);
                        cot.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
                        cot.setDelegationNature(ListDelegationModelPanel.this.cbNature.getValue());
                        cot.setLanguage(ListDelegationModelPanel.this.cbLanguage.getValue());
                        cot.setHasMultipleDelegation(ListDelegationModelPanel.this.cbHasMultipleDelegation.getValue() ? 1 : 0);
                        cot.setHasMultipleDelegataire(ListDelegationModelPanel.this.cbHasMultipleDelegataire.getValue() ? 1 : 0);
                        cot.setSubDelegation(ListDelegationModelPanel.this.cbSubDelegation.getValue() ? 1 : 0);
                    }
                }
                final List<DelegationMdlModel> allModel = new ArrayList<DelegationMdlModel>();
                allModel.addAll(ListDelegationModelPanel.this.ptyStore.getModels());
                allModel.addAll(ListDelegationModelPanel.this.cotStore.getModels());
                if (ListDelegationModelPanel.this.group == 0) {

                    ListDelegationModelPanel.this.clientDelegationModelServiceAsync.insert(allModel, 0,
                            new AsyncCallbackWithErrorResolution<Integer>() {

                                @Override
                                public void onSuccess(Integer arg0) {
                                    if (arg0 != 0) {
                                        ListDelegationModelPanel.this.group = arg0;
                                        ListDelegationModelPanel.this.mEvent.setGroup(ListDelegationModelPanel.this.group);

                                        ListDelegationModelPanel.this.btnSave.setText(ListDelegationModelPanel.this.messages.commonModifierButton());
                                        ListDelegationModelPanel.this.tab.setVisible(true);
                                        ListDelegationModelPanel.this.btnEdit.setEnabled(true);
                                        ListDelegationModelPanel.this.btnAddDocument.setEnabled(true);

                                        ListDelegationModelPanel.this.checkAndInsertFieldRule(ListDelegationModelPanel.this.mEvent);
                                        AppUtil.removeAdminInEditMode();
                                        Info.display(ListDelegationModelPanel.this.messages.commoninfo(),
                                                ListDelegationModelPanel.this.messages.delegationmodelsavesuccess());
                                    } else {
                                        Info.display(ListDelegationModelPanel.this.messages.commonerror(),
                                                ListDelegationModelPanel.this.messages.delegationmodelsavefailed());
                                    }
                                }

                                @Override
                                public void onFailure(Throwable arg0) {
                                    arg0.printStackTrace();
                                    Info.display(ListDelegationModelPanel.this.messages.commonerror(),
                                            ListDelegationModelPanel.this.messages.delegationmodelsavefailed());
                                }
                            });
                } else {
                    ListDelegationModelPanel.this.clientDelegationModelServiceAsync.deleteByGroup(ListDelegationModelPanel.this.group,
                            new AsyncCallbackWithErrorResolution<Boolean>() {

                                @Override
                                public void onSuccess(Boolean arg0) {
                                    ListDelegationModelPanel.this.clientDelegationModelServiceAsync.insert(allModel,
                                            ListDelegationModelPanel.this.group, new AsyncCallbackWithErrorResolution<Integer>() {

                                                @Override
                                                public void onSuccess(Integer arg0) {
                                                    if (arg0 != 0) {
                                                        AppUtil.removeAdminInEditMode();
                                                        Info.display(ListDelegationModelPanel.this.messages.commoninfo(),
                                                                ListDelegationModelPanel.this.messages.delegationmodelupdatesuccess());
                                                    } else {
                                                        Info.display(ListDelegationModelPanel.this.messages.commonerror(),
                                                                ListDelegationModelPanel.this.messages.delegationmodelupdatefailed());
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Throwable arg0) {
                                                    Info.display(ListDelegationModelPanel.this.messages.commonerror(),
                                                            ListDelegationModelPanel.this.messages.delegationmodelupdatefailed());
                                                }
                                            });
                                }
                            });
                }

            }
        });

        this.btnEdit.setIcon(IconHelper.createPath("html/edit-icon.png"));
        this.btnEdit.setEnabled(false);
        this.btnEdit.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (ListDelegationModelPanel.this.group != 0) {
                    FieldRuleEvent fieldRuleEvent = new FieldRuleEvent();
                    fieldRuleEvent.setGroup(ListDelegationModelPanel.this.group);
                    fieldRuleEvent.setEntite(SessionServiceImpl.getInstance().getEntiteContext());

                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_RULE_ADMIN_FORM);
                    contentEvent.setEvent(fieldRuleEvent);
                    ListDelegationModelPanel.this.bus.fireEvent(contentEvent);
                }
            }
        });

        hpButton.add(this.btnSave);
        hpButton.add(this.btnEdit);
    }

    private void addBackLink() {
        LayoutContainer backLink = new LayoutContainer();
        backLink.setSize(700, -1);
        Label lblBack = new Label(this.messages.delegationruleback());
        lblBack.setStyleName("x-link-item");

        backLink.setStyleAttribute("marginTop", "10px");
        backLink.setStyleAttribute("marginLeft", "10px");
        backLink.add(lblBack);

        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GROUP_DELEGATION_MODEL_ADMIN_FORM);
                    contentEvent.setEvent(new LoadGroupDelegationModelEvent());
                    ListDelegationModelPanel.this.bus.fireEvent(contentEvent);
                }
            }
        });
        this.add(backLink);
    }

    private void changeDocumentTable() {
        if (this.group != 0) {
            this.clientDemDomServiceAsync.getAllDemDomsByDemGroup(this.group, new AsyncCallbackWithErrorResolution<List<DemDomModel>>() {

                @Override
                public void onSuccess(List<DemDomModel> arg0) {
                    ListDelegationModelPanel.this.documentMdlModels.removeAll();
                    for (DemDomModel demDom : arg0) {
                        ListDelegationModelPanel.this.documentMdlModels.add(demDom.getDocumentMdl());
                    }
                }
            });
        }
    }
}
