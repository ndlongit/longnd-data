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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
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

public class ListDelegationModelPanel extends AbstractPanel {

    private static int GRID_HEIGHT = 145;
    private final FormData formData = new FormData("98%");

    private ListStore<DelegationNatureModel> lstDelegationNature = new ListStore<DelegationNatureModel>();
    private ClientDelegationNatureServiceAsync clientDelegationNatureService = ClientDelegationNatureServiceAsync.Util.getInstance();
    private ClientDelegationModelServiceAsync clientDelegationModelService = ClientDelegationModelServiceAsync.Util.getInstance();
    private ClientPerimetreTypeServiceAsync clientPerimetreTypeService = ClientPerimetreTypeServiceAsync.Util.getInstance();
    private ClientCollaborateurTypeServiceAsync clientCollaborateurTypeService = ClientCollaborateurTypeServiceAsync.Util.getInstance();
    private ClientLanguageServiceAsync clientLanguageService = ClientLanguageServiceAsync.Util.getInstance();
    private ClientDemDomServiceAsync clientDemDomService = ClientDemDomServiceAsync.Util.getInstance();

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
                mEvent = event;
                disableEvents(true);
                switch (event.getMode()) {
                case DelegationModelEvent.MODE_IS_NEW:
                    newMode(event);
                    break;
                case DelegationModelEvent.MODE_IS_EDIT:
                    editMode(event);
                    break;
                case DelegationModelEvent.MODE_IS_UPDATE_DOCUMENT:
                    changeDocumentTable();
                    break;
                }
                checkAndInsertFieldRule(event);
                disableEvents(false);
            }
        });
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
        this.btnSave.setText(messages.commonValiderButton());
        this.btnAnnuler.setText(messages.commonAnnulerButton());

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
        this.btnSave.setText(messages.commonModifierButton());
        this.btnAnnuler.setText(messages.commonAnnulerButton());

        // remove last information
        this.ptyStore.removeAll();
        this.documentMdlModels.removeAll();

        // init data
        this.initData();
        this.changeDocumentTable();

        this.delegationModelGrid.mask(messages.commonloadingdata());
        this.clientDelegationModelService.getDelegationModelsByGroup(this.group, new AsyncCallbackWithErrorResolution<List<DelegationMdlModel>>() {

            @Override
            public void onSuccess(List<DelegationMdlModel> arg0) {
                cotStore.removeAll();
                ptyStore.removeAll();
                this.splitIntoStores(arg0, cotStore, ptyStore);
                // ptyStore.add(arg0);
                delegationModelGrid.unmask();
            }

            private void splitIntoStores(List<DelegationMdlModel> arg0, ListStore<DelegationMdlModel> cotStore, ListStore<DelegationMdlModel> ptyStore) {
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
                delegationModelGrid.unmask();
            }
        });
    }

    private void checkAndInsertFieldRule(final DelegationModelEvent event) {
        if (event.getGroup() != 0) {
            this.clientFieldRuleService.getRulesByDemGroup(event.getGroup(), new AsyncCallbackWithErrorResolution<List<FieldRuleModel>>() {

                @Override
                public void onSuccess(List<FieldRuleModel> arg0) {
                    if (arg0.size() == 0) {
                        clientFieldService.getFieldsByEntiteId(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                                new AsyncCallbackWithErrorResolution<List<FieFieldModel>>() {

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

                                            clientFieldRuleService.insertList(fields, new AsyncCallbackWithErrorResolution<List<FieldRuleModel>>() {

                                                @Override
                                                public void onSuccess(List<FieldRuleModel> arg0) {
                                                    FieldRuleEvent frEvent = new FieldRuleEvent();
                                                    frEvent.setEntite(event.getEntiteModel());
                                                    frEvent.setGroup(event.getGroup());
                                                    bus.fireEvent(frEvent);
                                                }
                                            });
                                        }
                                    }
                                });
                    } else {
                        FieldRuleEvent frEvent = new FieldRuleEvent();
                        frEvent.setEntite(event.getEntiteModel());
                        frEvent.setGroup(event.getGroup());
                        bus.fireEvent(frEvent);
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
        this.cbNature.setFieldLabel(messages.nature());
        this.cbNature.setDisplayField(DelegationNatureModel.NAME);
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
        this.cbLanguage.setFieldLabel(messages.commonlanguage());
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
        this.btnAddDocument = new Button(messages.delegationmodeladddocumentbutton());
        this.btnAddDocument.setIcon(IconHelper.createPath("html/edit-icon.png"));
        this.btnAddDocument.setEnabled(false);
        this.btnAddDocument.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (group != 0) {
                    ApplyDocumentDialog dialog = new ApplyDocumentDialog(bus);
                    dialog.setData(SessionServiceImpl.getInstance().getEntiteContext(), group);
                    dialog.show();
                }
            }
        });

        ContentPanel mainPanel = new ContentPanel();
        mainPanel.setButtonAlign(HorizontalAlignment.LEFT);
        mainPanel.setHeading(messages.delegationmodelheading());
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
        final Button btnAdd = new Button(messages.delegationmodelruleaddbutton());

        btnAdd.setIcon(IconHelper.createPath("html/icon/perimetresetting.png"));
        btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ApplyPerimetreTypeDialog dialog = new ApplyPerimetreTypeDialog(bus);
                dialog.setData(SessionServiceImpl.getInstance().getEntiteContext(), ptyStore.getModels());
                dialog.show();
            }
        });
        toolBar.add(btnAdd);

        this.ptyStore = new ListStore<DelegationMdlModel>();

        ColumnConfig cfPerimetreType = new ColumnConfig("perimetreType.name", messages.delegationmodelperimetretype(), 320);

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
                ptyStore.removeAll();
                if (event.getPerimetreTypes() != null) {
                    for (PerimetreTypeModel c : event.getPerimetreTypes()) {
                        DelegationMdlModel mdl = new DelegationMdlModel();
                        mdl.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
                        mdl.setDelegationNature(cbNature.getValue());
                        mdl.setLanguage(cbLanguage.getValue());
                        mdl.setPerimetreType(c);
                        mdl.setGroup(group);
                        ptyStore.add(mdl);
                    }
                }
            }
        });
    }

    private void initDelegantTypeGrid() {
        this.cotStore = new ListStore<DelegationMdlModel>();
        ToolBar toolBar = new ToolBar();
        final Button btnAdd = new Button(messages.delegationmodelruleadddelegantbutton());
        btnAdd.setIcon(IconHelper.createPath("html/icon/delegantsetting.png"));
        btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ApplyDelegantTypeDialog dialog = new ApplyDelegantTypeDialog(bus);
                dialog.setData(SessionServiceImpl.getInstance().getEntiteContext(), cotStore.getModels());
                dialog.show();
            }
        });

        toolBar.add(btnAdd);

        // setup grid for external controlers
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        // Column documents
        ColumnConfig delegantTypeColumn = new ColumnConfig("collaborateurType.name", messages.delegationmodeldeleganttype(), 320);
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
                cotStore.removeAll();
                if (event.getDelegantTypes() != null) {
                    for (CollaborateurTypeModel c : event.getDelegantTypes()) {
                        DelegationMdlModel mdl = new DelegationMdlModel();
                        mdl.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
                        mdl.setDelegationNature(cbNature.getValue());
                        mdl.setLanguage(cbLanguage.getValue());
                        mdl.setCollaborateurType(c);
                        mdl.setGroup(group);
                        cotStore.add(mdl);
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
                        lstDelegationNature.removeAll();
                        lstDelegationNature.add(arg0);
                        cbNature.setStore(lstDelegationNature);
                        if (cbNature.getValue() == null) {
                            if (arg0 != null && arg0.size() > 0) {
                                DelegationNatureModel em = arg0.get(0);
                                cbNature.select(0);
                                cbNature.setValue(em);
                            }
                        }
                    }
                });

        this.clientLanguageService.getLanguages(new AsyncCallbackWithErrorResolution<List<LanguageModel>>() {

            @Override
            public void onSuccess(List<LanguageModel> arg0) {
                languages.removeAll();
                languages.add(arg0);
                if (cbLanguage.getValue() == null) {
                    if (arg0 != null && arg0.size() > 0) {
                        LanguageModel em = arg0.get(0);
                        cbLanguage.select(0);
                        cbLanguage.setValue(em);
                    }
                }
            }
        });
        this.clientPerimetreTypeService.getPerimetreTypes(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallbackWithErrorResolution<List<PerimetreTypeModel>>() {

                    @Override
                    public void onSuccess(List<PerimetreTypeModel> arg0) {
                        perimetreTypes.removeAll();
                        perimetreTypes.add(arg0);
                    }
                });

        this.clientCollaborateurTypeService.getCollaborateurTypeByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallbackWithErrorResolution<List<CollaborateurTypeModel>>() {

                    @Override
                    public void onSuccess(List<CollaborateurTypeModel> arg0) {
                        delegantTypes.removeAll();
                        delegantTypes.add(arg0);
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

        TabItem documentTab = new TabItem(messages.delegationmodeldocumentheader());
        documentTab.add(cp);
        documentTab.setAutoHeight(true);

        TabItem ruleTab = new TabItem(messages.commonrulebutton());
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

        this.btnSave = new Button(messages.commonmodifierbutton());
        this.btnEdit = new Button(messages.commonrulebutton());

        this.btnSave.setIcon(IconHelper.createPath("html/save-icon.png"));

        this.btnAnnuler = new Button(messages.commonAnnulerButton());
        this.btnAnnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent contentEvent = new ContentEvent();
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GROUP_DELEGATION_MODEL_ADMIN_FORM);
                contentEvent.setEvent(new LoadGroupDelegationModelEvent());
                bus.fireEvent(contentEvent);
                AppUtil.removeAdminInEditMode();
            }
        });

        this.btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (ptyStore.getCount() != 0) {
                    for (int i = 0; i < ptyStore.getCount(); i++) {
                        DelegationMdlModel pty = ptyStore.getAt(i);
                        pty.setId(null);
                        pty.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
                        pty.setDelegationNature(cbNature.getValue());
                        pty.setLanguage(cbLanguage.getValue());
                        pty.setHasMultipleDelegation(cbHasMultipleDelegation.getValue() ? 1 : 0);
                        pty.setHasMultipleDelegataire(cbHasMultipleDelegataire.getValue() ? 1 : 0);
                        pty.setSubDelegation(cbSubDelegation.getValue() ? 1 : 0);
                    }
                }
                if (cotStore.getCount() != 0) {
                    for (int i = 0; i < cotStore.getCount(); i++) {
                        DelegationMdlModel cot = cotStore.getAt(i);
                        cot.setId(null);
                        cot.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
                        cot.setDelegationNature(cbNature.getValue());
                        cot.setLanguage(cbLanguage.getValue());
                        cot.setHasMultipleDelegation(cbHasMultipleDelegation.getValue() ? 1 : 0);
                        cot.setHasMultipleDelegataire(cbHasMultipleDelegataire.getValue() ? 1 : 0);
                        cot.setSubDelegation(cbSubDelegation.getValue() ? 1 : 0);
                    }
                }
                final List<DelegationMdlModel> allModel = new ArrayList<DelegationMdlModel>();
                allModel.addAll(ptyStore.getModels());
                allModel.addAll(cotStore.getModels());
                if (group == 0) {

                    clientDelegationModelService.insert(allModel, 0, new AsyncCallbackWithErrorResolution<Integer>() {

                        @Override
                        public void onSuccess(Integer arg0) {
                            if (arg0 != 0) {
                                group = arg0;
                                mEvent.setGroup(group);

                                btnSave.setText(messages.commonModifierButton());
                                tab.setVisible(true);
                                btnEdit.setEnabled(true);
                                btnAddDocument.setEnabled(true);

                                checkAndInsertFieldRule(mEvent);
                                AppUtil.removeAdminInEditMode();
                                Info.display(messages.commoninfo(), messages.delegationmodelsavesuccess());
                            } else {
                                Info.display(messages.commonerror(), messages.delegationmodelsavefailed());
                            }
                        }

                        @Override
                        public void onFailure(Throwable arg0) {
                            arg0.printStackTrace();
                            Info.display(messages.commonerror(), messages.delegationmodelsavefailed());
                        }
                    });
                } else {
                    clientDelegationModelService.deleteByGroup(group, new AsyncCallbackWithErrorResolution<Boolean>() {

                        @Override
                        public void onSuccess(Boolean arg0) {
                            clientDelegationModelService.insert(allModel, group, new AsyncCallbackWithErrorResolution<Integer>() {

                                @Override
                                public void onSuccess(Integer arg0) {
                                    if (arg0 != 0) {
                                        AppUtil.removeAdminInEditMode();
                                        Info.display(messages.commoninfo(), messages.delegationmodelupdatesuccess());
                                    } else {
                                        Info.display(messages.commonerror(), messages.delegationmodelupdatefailed());
                                    }
                                }

                                @Override
                                public void onFailure(Throwable arg0) {
                                    Info.display(messages.commonerror(), messages.delegationmodelupdatefailed());
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
                if (group != 0) {
                    FieldRuleEvent fieldRuleEvent = new FieldRuleEvent();
                    fieldRuleEvent.setGroup(group);
                    fieldRuleEvent.setEntite(SessionServiceImpl.getInstance().getEntiteContext());

                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_RULE_ADMIN_FORM);
                    contentEvent.setEvent(fieldRuleEvent);
                    bus.fireEvent(contentEvent);
                }
            }
        });

        hpButton.add(this.btnSave);
        hpButton.add(this.btnEdit);
    }

    private void addBackLink() {
        LayoutContainer backLink = new LayoutContainer();
        backLink.setSize(700, -1);
        Label lblBack = new Label(messages.delegationruleback());
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
                    bus.fireEvent(contentEvent);
                }
            }
        });
        this.add(backLink);
    }

    private void changeDocumentTable() {
        if (this.group != 0) {
            this.clientDemDomService.getAllDemDomsByDemGroup(this.group, new AsyncCallbackWithErrorResolution<List<DemDomModel>>() {

                @Override
                public void onSuccess(List<DemDomModel> arg0) {
                    documentMdlModels.removeAll();
                    for (DemDomModel demDom : arg0) {
                        documentMdlModels.add(demDom.getDocumentMdl());
                    }
                }
            });
        }
    }
}
