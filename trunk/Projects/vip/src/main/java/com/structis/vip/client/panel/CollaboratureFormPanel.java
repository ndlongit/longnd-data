package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreFilter;
import com.extjs.gxt.ui.client.widget.ComponentManager;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.DateTimePropertyEditor;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.ModifyCollaboratureEvent;
import com.structis.vip.client.event.ModifyCollaboratureHandler;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.service.ClientCollaborateurServiceAsync;
import com.structis.vip.client.service.ClientCollaborateurTypeServiceAsync;
import com.structis.vip.client.service.ClientFormationServiceAsync;
import com.structis.vip.client.service.ClientSyncServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.shared.SharedConstant;
import com.structis.vip.shared.model.AddressModel;
import com.structis.vip.shared.model.CollaborateurFormationModel;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.CollaborateurTypeModel;
import com.structis.vip.shared.model.DelegantPerimetreModel;
import com.structis.vip.shared.model.DelegatairePerimetreModel;
import com.structis.vip.shared.model.FormationModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;

public class CollaboratureFormPanel extends AbstractPanel {

    private final FormData formData = new FormData("95%");
    private final FormData formData40 = new FormData("60%");
    private final static int WIDTH = 700;
    private final static int HEIGHT = -1;

    private ClientFormationServiceAsync clientFormationService = ClientFormationServiceAsync.Util.getInstance();
    private ClientCollaborateurServiceAsync clientCollaboratureService = ClientCollaborateurServiceAsync.Util.getInstance();
    private ClientCollaborateurTypeServiceAsync clientCollaborateurTypeSerivce = ClientCollaborateurTypeServiceAsync.Util.getInstance();
    private ClientSyncServiceAsync clientSyncService = ClientSyncServiceAsync.Util.getInstance();

    private SimpleComboBox<String> cbCivilite;
    private TextField<String> tfNom;
    private TextField<String> tfPrenom;
    private DateField dfDateDeNaissance;
    private TextField<String> tfLieuDeNaissance;
    private TextField<String> tfNationalite;
    private TextArea tfAddressPersonel;

    private LabelField lfDateMiseAJourRubis;
    private LabelField lfSocieteRubis;
    private LabelField lfMatricule;
    private LabelField lfDateEntreGroup;
    private LabelField lfDateDeSortieSociete;

    private TextField<String> tfSocieteSiExterne;
    private TextField<String> tfNiveauHierarchique;
    private RadioGroup rg;
    private Radio roSortiOui;
    private Radio roSortiNon;
    private CheckBox cbCollaboratureDelegant;
    private LabelField lbPeriemetreDelegant;
    private CheckBox cbCollaboratureDelegataire;
    private LabelField lbPeriemetreDelegataire;
    private DateField dfDateDeFormation;
    private TextField<String> tfQualiteDuDelegant;
    private DateField dfDateDuConseilAdministration;
    private SimpleComboBox<String> cbStatuDuConseilAdministration;
    private DateField dfAEffectDu;

    private DateField dfDateDelegation;
    private ComboBox<CollaborateurModel> cbDelegant;
    private ComboBox<CollaborateurTypeModel> cbColType;
    private TextField<String> tfQualiteCollaboratureDelegant;

    private Label lfFormation;
    private EditorGrid<FormationModel> grid;
    private HorizontalPanel gridPanel;

    private ListStore<FormationModel> formationStore = new ListStore<FormationModel>();

    private FormPanel panel;
    private Button btnAnnuler;
    private Button btnModifier;
    private CollaborateurModel model;
    private boolean isEdit = true;
    private List<PerimetreModel> delegatairePerimetres = new ArrayList<PerimetreModel>();
    private List<PerimetreModel> delegantPerimetres = new ArrayList<PerimetreModel>();
    private StoreFilter<CollaborateurModel> typeFilter;

    public CollaboratureFormPanel(SimpleEventBus bus) {
        this.bus = bus;

        this.setLayout(new FlowLayout(10));
        this.setScrollMode(Scroll.AUTO);
        this.setStyleAttribute("paddingBottom", "20px");
        this.setStyleAttribute("paddingRight", "10px");
        this.setWidth(WIDTH);
        this.initUI();
        this.createTypeFilter();
        this.initEvent();
        this.addHandler();
    } 
    
    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
    }

    private void createTypeFilter() {
        this.typeFilter = new StoreFilter<CollaborateurModel>() {

            @Override
            public boolean select(Store<CollaborateurModel> store, CollaborateurModel parent, CollaborateurModel item, String property) {
                return this.isInMandataireGroup(item.getType());
            }

            private boolean isInMandataireGroup(CollaborateurTypeModel m) {
                if (m == null) {
                    return false;
                }

                return CollaborateurTypeModel.belongsMandataireSocial(m.getGroup());
            }
        };
    }

    private void initUI() {
        this.panel = new FormPanel();
        this.panel.setLabelWidth(180);
        this.panel.setFrame(true);
        this.panel.setHeading("Fiche collaborateur");
        this.panel.setBorders(false);
        this.panel.setCollapsible(false);
        this.panel.setLayout(new FlowLayout());
        this.panel.setSize(WIDTH, -1);
        this.panel.setButtonAlign(HorizontalAlignment.RIGHT);

        this.initBackLink();
        this.initBasicForm();

        LayoutContainer lcLine = new LayoutContainer();
        lcLine.setSize(WIDTH, HEIGHT);
        lcLine.setLayout(new ColumnLayout());
        lcLine.add(new HTML("<hr width='670px'/>"));
        this.panel.add(lcLine);

        this.initMoreForm();

        this.gridPanel.setVisible(false);
        this.visibledFieldsForDelegant(false);
        this.visibledFieldsForDelegataire(false);

        this.btnAnnuler = new Button(messages.commonAnnulerButton());
        this.btnModifier = new Button(messages.commonValiderButton());

        this.panel.addButton(this.btnAnnuler);
        this.panel.addButton(this.btnModifier);

        this.add(this.panel);
    }

    private void initEvent() {
        this.btnAnnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                AppUtil.removeAdminInEditMode();
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_LIST);
                CollaboratureFormPanel.this.bus.fireEvent(event);
            }
        });

        this.btnModifier.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (CollaboratureFormPanel.this.panel.isValid()) {
                    CollaboratureFormPanel.this.save();
                }
            }
        });

        this.cbCollaboratureDelegant.addListener(Events.OnClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                if (CollaboratureFormPanel.this.checkIfPerimeterSelected(CollaboratureFormPanel.this.cbCollaboratureDelegant,
                        CollaboratureFormPanel.this.lbPeriemetreDelegant, CollaboratureFormPanel.this.delegantPerimetres, true)) {
                    if (CollaboratureFormPanel.this.cbCollaboratureDelegant.getValue() == false) {
                        CollaboratureFormPanel.this.visibledFieldsForDelegant(false);
                    } else if (!SharedConstant.ENTITE_ID_ETDE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
                        CollaboratureFormPanel.this.visibledFieldsForDelegant(true);
                    }
                } else {
                    CollaboratureFormPanel.this.cbCollaboratureDelegant.setValue(!CollaboratureFormPanel.this.cbCollaboratureDelegant.getValue());
                    be.setCancelled(false);
                }
            }
        });

        this.cbCollaboratureDelegataire.addListener(Events.OnClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                if (CollaboratureFormPanel.this.checkIfPerimeterSelected(CollaboratureFormPanel.this.cbCollaboratureDelegataire,
                        CollaboratureFormPanel.this.lbPeriemetreDelegataire, CollaboratureFormPanel.this.delegatairePerimetres, false)) {
                    if (CollaboratureFormPanel.this.cbCollaboratureDelegataire.getValue() == false) {
                        CollaboratureFormPanel.this.gridPanel.setVisible(false);
                        CollaboratureFormPanel.this.visibledFieldsForDelegataire(false);
                    } else if (!SharedConstant.ENTITE_ID_ETDE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
                        CollaboratureFormPanel.this.gridPanel.setVisible(true);
                        CollaboratureFormPanel.this.visibledFieldsForDelegataire(true);
                    }
                } else {
                    CollaboratureFormPanel.this.cbCollaboratureDelegataire.setValue(!CollaboratureFormPanel.this.cbCollaboratureDelegataire
                            .getValue());
                    be.setCancelled(false);
                }
            }
        });

        this.cbDelegant.addSelectionChangedListener(new SelectionChangedListener<CollaborateurModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<CollaborateurModel> se) {
                // if (tfQualiteCollaboratureDelegant.getValue() == null) {
                CollaboratureFormPanel.this.tfQualiteCollaboratureDelegant.setValue(se.getSelectedItem() == null ? "" : se.getSelectedItem()
                        .getQualiteDelegant());
                // }
            }
        });

        this.cbColType.addSelectionChangedListener(new SelectionChangedListener<CollaborateurTypeModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<CollaborateurTypeModel> se) {
                CollaboratureFormPanel.this.changeWithColType(se.getSelectedItem());
            }
        });
    }

    private void changeWithColType(CollaborateurTypeModel type) {
        if (type != null) {
            // 19 Feb 2013
            if (CommonUtils.belongsBYEFEGroup(type.getEntite().getEntId())) {
                ListStore<CollaborateurModel> store = this.cbDelegant.getStore();
                if (CollaborateurTypeModel.belongsMandataireSocial(type.getGroup().getName())) {
                    this.tfQualiteDuDelegant.setVisible(true);
                    this.dfDateDuConseilAdministration.setVisible(true);
                    this.cbStatuDuConseilAdministration.setVisible(true);
                    this.dfAEffectDu.setVisible(true);
                    this.dfDateDelegation.setVisible(false);
                    this.cbDelegant.setVisible(false);
                    this.tfQualiteCollaboratureDelegant.setVisible(false);
                    if (store != null) {
                        store.clearFilters();
                    }
                } else {
                    this.tfQualiteDuDelegant.setVisible(false);
                    this.dfDateDuConseilAdministration.setVisible(false);
                    this.cbStatuDuConseilAdministration.setVisible(false);
                    this.dfAEffectDu.setVisible(false);
                    this.dfDateDelegation.setVisible(true);
                    this.cbDelegant.setVisible(true);
                    this.cbDelegant.setValue(null);
                    // 26 Nov
                    if (store != null) {
                        store.addFilter(this.typeFilter);
                        store.applyFilters("type");
                    }
                    this.tfQualiteCollaboratureDelegant.setVisible(true);
                }

            } else { // ETDE
                if (type.getId() == ClientConstant.COLLABORATEUR_TYPE_DG || type.getId() == ClientConstant.COLLABORATEUR_TYPE_DGD) {
                    // if (type.getGroup().getName().equals(ConstantClient.COLLABORATEUR_TYPE_MANDATAIRE_SOCIAL)) {
                    this.tfQualiteDuDelegant.setVisible(true);
                    this.dfDateDuConseilAdministration.setVisible(true);
                    this.cbStatuDuConseilAdministration.setVisible(true);
                    this.dfAEffectDu.setVisible(true);
                    this.dfDateDelegation.setVisible(false);
                    this.cbDelegant.setVisible(false);
                    this.tfQualiteCollaboratureDelegant.setVisible(false);
                } else {
                    this.tfQualiteDuDelegant.setVisible(false);
                    this.dfDateDuConseilAdministration.setVisible(false);
                    this.cbStatuDuConseilAdministration.setVisible(false);
                    this.dfAEffectDu.setVisible(false);
                    this.dfDateDelegation.setVisible(true);
                    this.cbDelegant.setVisible(true);

                    this.tfQualiteCollaboratureDelegant.setVisible(true);

                }
            }
        }
    }

    private void visibledFieldsForDelegant(boolean visibled) {
        this.tfQualiteDuDelegant.setVisible(false);
        this.dfDateDuConseilAdministration.setVisible(false);
        this.cbStatuDuConseilAdministration.setVisible(false);
        this.dfAEffectDu.setVisible(false);
        this.dfDateDelegation.setVisible(false);
        this.cbDelegant.setVisible(false);
        this.tfQualiteCollaboratureDelegant.setVisible(false);

        this.cbColType.setVisible(visibled);
        this.cbColType.setValue(null);
    }

    private void visibledFieldsForDelegataire(boolean visibled) {
        this.dfDateDeFormation.setVisible(visibled);
        // tfZone.setVisible(visibled);
        // tfOperations.setVisible(visibled);
    }

    private void setEnabledAll(boolean enable) {
        for (Field<?> field : this.panel.getFields()) {
            if (field instanceof LabelField) {
                continue;
            } else {
                field.setEnabled(enable);
            }
        }
        this.btnModifier.setVisible(enable);
    }

    private void addHandler() {
        this.bus.addHandler(ModifyCollaboratureEvent.getType(), new ModifyCollaboratureHandler() {

            @Override
            public void onLoadAction(ModifyCollaboratureEvent event) {
                CollaboratureFormPanel.this.initData();

                if (ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_CREATE_FORM == event.getMode()
                        || event.getMode() == ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_VIEW_FORM) {
                    if (event.getMode() == ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_VIEW_FORM) {
                        CollaboratureFormPanel.this.setEnabledAll(false);
                        CollaboratureFormPanel.this.grid.setEnabled(false);
                    } else {
                        CollaboratureFormPanel.this.setEnabledAll(true);
                        CollaboratureFormPanel.this.grid.setEnabled(true);
                        AppUtil.putInAdminEditMode();
                    }

                    if (event.getModel() != null) {
                        CollaboratureFormPanel.this.isEdit = true;
                        CollaboratureFormPanel.this.btnModifier.setText(messages.commonModifierButton());

                        CollaboratureFormPanel.this.clientCollaboratureService.findById(event.getModel().getId(),
                                new AsyncCallbackWithErrorResolution<CollaborateurModel>() {

                                    @Override
                                    public void onSuccess(CollaborateurModel arg0) {
                                        CollaboratureFormPanel.this.model = arg0;
                                        this.fillData(arg0);
                                        if (CollaboratureFormPanel.this.isEdit == true && CollaboratureFormPanel.this.model != null) {
                                            ListStore<CollaborateurModel> store = CollaboratureFormPanel.this.cbDelegant.getStore();
                                            for (CollaborateurModel item : store.getModels()) {
                                                if (item.getId().intValue() == CollaboratureFormPanel.this.model.getId().intValue()) {
                                                    store.remove(item);
                                                    if (CollaboratureFormPanel.this.cbDelegant.getValue() == item) {
                                                        CollaboratureFormPanel.this.cbDelegant.setValue(null);
                                                    }
                                                    break;
                                                }
                                            }
                                        }
                                    }

                                    private void fillData(final CollaborateurModel model) {
                                        if (model.getDelegantPerimetreNames() != null) {
                                            CollaboratureFormPanel.this.lbPeriemetreDelegant.setValue(model.getDelegantPerimetreNames());
                                            CollaboratureFormPanel.this.lbPeriemetreDelegant.setVisible(true);
                                        }

                                        if (model.getDelegatairesPerimetreNames() != null) {
                                            CollaboratureFormPanel.this.lbPeriemetreDelegataire.setValue(model.getDelegatairesPerimetreNames());
                                            CollaboratureFormPanel.this.lbPeriemetreDelegataire.setVisible(true);
                                        }
                                        CollaboratureFormPanel.this.cbCivilite.setSimpleValue(model.getCivilite());
                                        CollaboratureFormPanel.this.tfNom.setValue(model.getLastname());
                                        CollaboratureFormPanel.this.tfPrenom.setValue(model.getFirstname());
                                        CollaboratureFormPanel.this.dfDateDeNaissance.setValue(model.getDateNaissance());
                                        CollaboratureFormPanel.this.tfLieuDeNaissance.setValue(model.getLieuNaissance());
                                        CollaboratureFormPanel.this.cbCollaboratureDelegant.setValue((model.getIsDelegant() != null && model
                                                .getIsDelegant() == 1) ? true : false);
                                        CollaboratureFormPanel.this.cbCollaboratureDelegataire.setValue((model.getIsDelegataire() != null && model
                                                .getIsDelegataire() == 1) ? true : false);
                                        CollaboratureFormPanel.this.tfNationalite.setValue(model.getNationality());
                                        CollaboratureFormPanel.this.tfNiveauHierarchique.setValue(model.getNiveauHierarchique());
                                        CollaboratureFormPanel.this.tfAddressPersonel.setValue(model.getAddress());
                                        // add BYTP
                                        // if
                                        // (ConstantClient.ENTITE_ID_IS_BYEFE.equalsIgnoreCase(SessionServiceImpl.getInstance().getEntiteContext().getEntId()))
                                        // {
                                        if (CommonUtils.belongsBYEFEGroup(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
                                            if (model.getIsDelegataire() != null && model.getIsDelegataire() == 1) {
                                                CollaboratureFormPanel.this.gridPanel.setVisible(true);
                                                CollaboratureFormPanel.this.visibledFieldsForDelegant(false);
                                                CollaboratureFormPanel.this.visibledFieldsForDelegataire(true);
                                            }

                                            if (model.getIsDelegant() != null && model.getIsDelegant() == 1) {
                                                CollaboratureFormPanel.this.gridPanel.setVisible(false);
                                                CollaboratureFormPanel.this.visibledFieldsForDelegant(true);
                                                CollaboratureFormPanel.this.visibledFieldsForDelegataire(false);

                                                if (model.getType() != null) {
                                                    CollaborateurTypeModel type = model.getType();
                                                    CollaboratureFormPanel.this.cbColType.setValue(type);

                                                    CollaboratureFormPanel.this.changeWithColType(type);
                                                    String colGroup = type != null ? type.getGroup().getName() : null;
                                                    if (CollaborateurTypeModel.belongsMandataireSocial(colGroup)) {
                                                        // if (model.getType().getId() == ConstantClient.COLLABORATEUR_TYPE_DG || type.getId() ==
                                                        // ConstantClient.COLLABORATEUR_TYPE_DGD) {
                                                        CollaboratureFormPanel.this.tfQualiteDuDelegant.setValue(model.getQualiteDelegant());
                                                        CollaboratureFormPanel.this.dfDateDuConseilAdministration.setValue(model.getDateConseil());
                                                        CollaboratureFormPanel.this.cbStatuDuConseilAdministration.setSimpleValue(model
                                                                .getStatutConseil());
                                                        CollaboratureFormPanel.this.dfAEffectDu.setValue(model.getDateEffet());
                                                    } else {
                                                        CollaboratureFormPanel.this.dfDateDelegation.setValue(model.getDateDelegation());
                                                        CollaboratureFormPanel.this.cbDelegant.setValue(model.getDelegant());
                                                        if (model.getId() != null) {
                                                            CollaboratureFormPanel.this.tfQualiteCollaboratureDelegant.setValue(model
                                                                    .getQualiteColDelegant());
                                                        }
                                                    }
                                                }
                                            }

                                            if (model.getIsDelegant() != null && model.getIsDelegant() == 1 && model.getIsDelegataire() != null
                                                    && model.getIsDelegataire() == 1) {
                                                CollaboratureFormPanel.this.gridPanel.setVisible(true);
                                                CollaboratureFormPanel.this.visibledFieldsForDelegant(true);
                                                CollaboratureFormPanel.this.visibledFieldsForDelegataire(true);

                                                if (model.getType() != null) {
                                                    CollaborateurTypeModel type = model.getType();
                                                    CollaboratureFormPanel.this.cbColType.setValue(type);

                                                    CollaboratureFormPanel.this.changeWithColType(type);

                                                    if (model.getType().getId() == ClientConstant.COLLABORATEUR_TYPE_DG
                                                            || type.getId() == ClientConstant.COLLABORATEUR_TYPE_DGD) {
                                                        CollaboratureFormPanel.this.tfQualiteDuDelegant.setValue(model.getQualiteDelegant());
                                                        CollaboratureFormPanel.this.dfDateDuConseilAdministration.setValue(model.getDateConseil());
                                                        CollaboratureFormPanel.this.cbStatuDuConseilAdministration.setSimpleValue(model
                                                                .getStatutConseil());
                                                        CollaboratureFormPanel.this.dfAEffectDu.setValue(model.getDateEffet());
                                                    } else {
                                                        CollaboratureFormPanel.this.dfDateDelegation.setValue(model.getDateDelegation());
                                                        CollaboratureFormPanel.this.cbDelegant.setValue(model.getDelegant());
                                                        CollaboratureFormPanel.this.tfQualiteCollaboratureDelegant.setValue(model
                                                                .getQualiteColDelegant());
                                                    }
                                                }
                                            }

                                            CollaboratureFormPanel.this.clientFormationService.findByEntite(SessionServiceImpl.getInstance()
                                                    .getEntiteContext().getEntId(), new AsyncCallback<List<FormationModel>>() {

                                                @Override
                                                public void onSuccess(final List<FormationModel> formations) {
                                                    CollaboratureFormPanel.this.clientCollaboratureService.findByCollaborateurId(model.getId(),
                                                            new AsyncCallback<List<CollaborateurFormationModel>>() {

                                                                @Override
                                                                public void onSuccess(List<CollaborateurFormationModel> arg0) {
                                                                    CollaboratureFormPanel.this.formationStore.removeAll();
                                                                    List<FormationModel> selection = new ArrayList<FormationModel>();

                                                                    if (formations.size() != 0 && arg0.isEmpty() == false) {
                                                                        for (FormationModel forModel : formations) {
                                                                            boolean has = false;
                                                                            for (CollaborateurFormationModel colForModel : arg0) {
                                                                                if (colForModel != null) {
                                                                                    if (forModel.getId().intValue() == colForModel.getFormation()
                                                                                            .getId().intValue()) {
                                                                                        forModel.setDate(colForModel.getDate());
                                                                                        selection.add(forModel);
                                                                                        CollaboratureFormPanel.this.formationStore.add(forModel);
                                                                                        has = true;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                            }
                                                                            if (has == false) {
                                                                                CollaboratureFormPanel.this.formationStore.add(forModel);
                                                                            }
                                                                        }
                                                                        ;
                                                                    } else {
                                                                        CollaboratureFormPanel.this.formationStore.add(formations);
                                                                    }
                                                                    CollaboratureFormPanel.this.grid.getSelectionModel().setSelection(selection);
                                                                }

                                                                @Override
                                                                public void onFailure(Throwable arg0) {
                                                                }
                                                            });
                                                }

                                                @Override
                                                public void onFailure(Throwable arg0) {
                                                }
                                            });
                                        }
                                        CollaboratureFormPanel.this.clientSyncService.getAddress(model.getIdBycn(),
                                                new AsyncCallback<AddressModel>() {

                                                    @Override
                                                    public void onSuccess(AddressModel arg0) {
                                                        if (arg0 != null && arg0.getIdbycn() != null) {
                                                            CollaboratureFormPanel.this.lfDateMiseAJourRubis.setText(model.getDateMajRubis() != null ? DateTimeFormat
                                                                    .getFormat(ClientConstant.DATE_FORMAT).format(model.getDateMajRubis()) : "");
                                                            // lfSocieteRubis.setText(model.)
                                                            CollaboratureFormPanel.this.lfMatricule.setText(model.getIdBycn());
                                                            CollaboratureFormPanel.this.lfDateEntreGroup.setText(model.getDateEntree() != null ? DateTimeFormat
                                                                    .getFormat(ClientConstant.DATE_FORMAT).format(model.getDateEntree()) : "");
                                                            CollaboratureFormPanel.this.lfDateDeSortieSociete.setText(model.getDateSortie() != null ? DateTimeFormat
                                                                    .getFormat(ClientConstant.DATE_FORMAT).format(model.getDateSortie()) : "");
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Throwable arg0) {
                                                    }
                                                });
                                    }

                                });

                        CollaboratureFormPanel.this.clientCollaborateurTypeSerivce.getCollaborateurTypeByEntite(SessionServiceImpl.getInstance()
                                .getEntiteContext().getEntId(), new AsyncCallbackWithErrorResolution<List<CollaborateurTypeModel>>() {

                            @Override
                            public void onSuccess(List<CollaborateurTypeModel> arg0) {
                                CollaboratureFormPanel.this.cbColType.getStore().removeAll();
                                CollaboratureFormPanel.this.cbColType.getStore().add(arg0);
                            }
                        });
                    } else {
                        CollaboratureFormPanel.this.isEdit = false;
                        CollaboratureFormPanel.this.model = new CollaborateurModel();
                        CollaboratureFormPanel.this.panel.reset();
                        CollaboratureFormPanel.this.panel.clear();

                        // set default
                        CollaboratureFormPanel.this.rg.setValue(CollaboratureFormPanel.this.roSortiNon);
                        CollaboratureFormPanel.this.btnModifier.setText(messages.commonValiderButton());

                        CollaboratureFormPanel.this.gridPanel.setVisible(false);
                        CollaboratureFormPanel.this.visibledFieldsForDelegant(false);
                        CollaboratureFormPanel.this.visibledFieldsForDelegataire(false);
                    }
                }
            }
        });
    }

    private void initMoreForm() {
        LayoutContainer lcMoreForm = new LayoutContainer();
        lcMoreForm.setLayout(new RowLayout());
        lcMoreForm.setWidth(WIDTH);

        LayoutContainer lcInput = new LayoutContainer();

        FormLayout flInput = new FormLayout();
        flInput.setLabelAlign(LabelAlign.RIGHT);
        flInput.setLabelPad(5);
        flInput.setLabelWidth(200);
        lcInput.setLayout(flInput);
        lcInput.setWidth(WIDTH);

        this.tfSocieteSiExterne = new TextField<String>();
        this.tfSocieteSiExterne.setFieldLabel(messages.collaboraturesocietesiexterne());
        lcInput.add(this.tfSocieteSiExterne, this.formData40);

        this.tfNiveauHierarchique = new TextField<String>();
        this.tfNiveauHierarchique.setAllowBlank(false);
        this.tfNiveauHierarchique.setFieldLabel(messages.collaboratureniveauhierarchique());
        lcInput.add(this.tfNiveauHierarchique, this.formData40);

        this.roSortiNon = new Radio();
        this.roSortiNon.setBoxLabel(messages.commonNon());
        this.roSortiNon.setValueAttribute("Non");
        this.roSortiNon.setValue(true);

        this.roSortiOui = new Radio();
        this.roSortiOui.setBoxLabel(messages.commonOui());
        this.roSortiOui.setValueAttribute("Oui");

        this.rg = new RadioGroup();
        this.rg.setFieldLabel(messages.collaboraturesorti());
        this.rg.add(this.roSortiOui);
        this.rg.add(this.roSortiNon);

        this.rg.setValue(this.roSortiNon);
        lcInput.add(this.rg, this.formData40);

        lcMoreForm.add(lcInput);

        LayoutContainer lcColaborateur = new LayoutContainer();
        lcColaborateur.setSize(WIDTH - 20, HEIGHT);
        lcColaborateur.setLayout(new ColumnLayout());

        LayoutContainer lcL = new LayoutContainer();
        FormLayout flLeft = new FormLayout();
        flLeft.setLabelAlign(LabelAlign.RIGHT);
        flLeft.setLabelWidth(200);
        lcL.setLayout(flLeft);

        this.cbCollaboratureDelegant = new CheckBox();
        this.cbCollaboratureDelegant.setFieldLabel(messages.collaboraturecollaborateurdelegant());
        this.cbCollaboratureDelegant.setStyleAttribute("padding", "0px");
        lcL.add(this.cbCollaboratureDelegant, new FormData("32%"));
        this.lbPeriemetreDelegant = new LabelField();
        this.lbPeriemetreDelegant.setLabelSeparator(":");
        this.lbPeriemetreDelegant.setFieldLabel(messages.collaboraturelabelapplyperietredelegant());

        lcL.add(this.lbPeriemetreDelegant, this.formData);

        this.cbCollaboratureDelegataire = new CheckBox();
        this.cbCollaboratureDelegataire.setStyleAttribute("padding", "0px");
        this.cbCollaboratureDelegataire.setFieldLabel(messages.collaboraturecollaborateurdelegataire());
        lcL.add(this.cbCollaboratureDelegataire, new FormData("32%"));
        this.lbPeriemetreDelegataire = new LabelField();
        this.lbPeriemetreDelegataire.setLabelSeparator(":");
        this.lbPeriemetreDelegataire.setFieldLabel(messages.collaboraturelabelapplyperietredelegataire());
        lcL.add(this.lbPeriemetreDelegataire, this.formData);
        lcColaborateur.add(lcL, new ColumnData(1));
        // lcColaborateur.add(lcR, new ColumnData(.65));
        lcMoreForm.add(lcColaborateur);

        LayoutContainer lcInput2 = new LayoutContainer();

        FormLayout flInput2 = new FormLayout();
        flInput2.setLabelAlign(LabelAlign.RIGHT);
        flInput2.setLabelPad(5);
        flInput2.setLabelWidth(200);
        lcInput2.setLayout(flInput2);
        lcInput2.setWidth(WIDTH);
        this.cbColType = new ComboBox<CollaborateurTypeModel>();
        this.cbColType.setTriggerAction(TriggerAction.ALL);
        this.cbColType.setEditable(false);
        this.cbColType.setVisible(false);
        this.cbColType.setFieldLabel(messages.collaboraturetypedelegant());
        // cbColType.setDisplayField(CollaborateurTypeModel.COT_NAME);
        this.cbColType.setDisplayField(CollaborateurTypeModel.COT_GROUP_NAME);
        this.cbColType.setStore(new ListStore<CollaborateurTypeModel>());
        lcInput2.add(this.cbColType, this.formData40);
        // new 27 Nov
        this.dfDateDelegation = new DateField();
        this.dfDateDelegation.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        this.dfDateDelegation.setFieldLabel(messages.collaboraturedatedelegation());
        lcInput2.add(this.dfDateDelegation, this.formData40);

        this.cbDelegant = new ComboBox<CollaborateurModel>();
        this.cbDelegant.setTriggerAction(TriggerAction.ALL);
        this.cbDelegant.setEditable(false);
        this.cbDelegant.setFieldLabel(messages.collaboraturedelegant());
        this.cbDelegant.setDisplayField(CollaborateurModel.COLLA_FULL_NAME_NO_SEPARATER);
        this.cbDelegant.setStore(new ListStore<CollaborateurModel>());

        lcInput2.add(this.cbDelegant, this.formData40);

        this.tfQualiteCollaboratureDelegant = new TextField<String>();
        this.tfQualiteCollaboratureDelegant.setFieldLabel(messages.collaboraturequalitecollaborateurdelegant());
        lcInput2.add(this.tfQualiteCollaboratureDelegant, this.formData40);
        lcMoreForm.add(lcInput2);
        // 27 Nov
        CheckBoxSelectionModel<FormationModel> sm = new CheckBoxSelectionModel<FormationModel>();
        sm.setSelectionMode(SelectionMode.SIMPLE);

        DateField dateField = new DateField();
        dateField.getPropertyEditor().setFormat(DateTimeFormat.getFormat(ClientConstant.DATE_FORMAT));

        ColumnConfig name = new ColumnConfig(FormationModel.FOR_LABEL, messages.formationformheader(), 196);

        ColumnConfig date = new ColumnConfig(FormationModel.FOR_DATE, messages.formationdate(), 100);
        date.setDateTimeFormat(DateTimeFormat.getFormat(ClientConstant.DATE_FORMAT));
        date.setEditor(new CellEditor(dateField));

        List<ColumnConfig> config = new ArrayList<ColumnConfig>();
        config.add(sm.getColumn());
        config.add(name);
        config.add(date);

        final ColumnModel cm = new ColumnModel(config);

        this.grid = new EditorGrid<FormationModel>(this.formationStore, cm);
        this.grid.setSelectionModel(sm);
        this.grid.addPlugin(sm);
        this.grid.setSize(338, 110);
        this.grid.setBorders(true);
        this.grid.setColumnLines(true);
        this.grid.setStyleAttribute("marginLeft", "2px");

        this.lfFormation = new Label(messages.collaboratureformationaladelegation() + ":");

        this.gridPanel = new HorizontalPanel();
        this.gridPanel.setHorizontalAlign(HorizontalAlignment.LEFT);
        this.gridPanel.setStyleAttribute("marginLeft", "50px");
        this.gridPanel.setStyleAttribute("marginBottom", "5px");
        this.gridPanel.setTableWidth("500");
        this.gridPanel.setSize(500, 110);
        this.gridPanel.add(this.lfFormation);
        this.gridPanel.add(this.grid);
        this.gridPanel.setVisible(false);
        lcInput2.add(this.gridPanel, this.formData);

        this.dfDateDeFormation = new DateField();
        this.dfDateDeFormation.setVisible(false);
        this.dfDateDeFormation.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        this.dfDateDeFormation.setFieldLabel(messages.collaboraturedatedeformatonaladelegation());
        lcInput2.add(this.dfDateDeFormation, this.formData40);

        this.tfQualiteDuDelegant = new TextField<String>();
        this.tfQualiteDuDelegant.setVisible(false);
        this.tfQualiteDuDelegant.setFieldLabel(messages.collaboraturequalitedudelegant());
        lcInput2.add(this.tfQualiteDuDelegant, this.formData40);

        this.dfDateDuConseilAdministration = new DateField();
        this.dfDateDuConseilAdministration.setVisible(false);
        this.dfDateDuConseilAdministration.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        this.dfDateDuConseilAdministration.setFieldLabel(messages.collaboraturedateduconseiladministration());
        lcInput2.add(this.dfDateDuConseilAdministration, this.formData40);

        this.cbStatuDuConseilAdministration = new SimpleComboBox<String>();
        this.cbStatuDuConseilAdministration.setVisible(false);
        this.cbStatuDuConseilAdministration.setTriggerAction(TriggerAction.ALL);
        this.cbStatuDuConseilAdministration.setEditable(false);
        this.cbStatuDuConseilAdministration.setFieldLabel(messages.collaboraturestatutduconseiladministration());
        this.cbStatuDuConseilAdministration.add(messages.commonnomme());
        this.cbStatuDuConseilAdministration.add(messages.commonconfirme());
        lcInput2.add(this.cbStatuDuConseilAdministration, this.formData40);

        this.dfAEffectDu = new DateField();
        this.dfAEffectDu.setVisible(false);
        this.dfAEffectDu.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        this.dfAEffectDu.setFieldLabel(messages.collaboratureaeffetdu());
        lcInput2.add(this.dfAEffectDu, this.formData40);

        this.panel.add(lcMoreForm);
    }

    private void initBackLink() {
        LayoutContainer backLink = new LayoutContainer();
        backLink.setSize(WIDTH, -1);
        Label lblBack = new Label(messages.collaboratureback());

        lblBack.setStyleName("x-link-item");
        backLink.setStyleAttribute("margin-bottom", "20px");
        backLink.add(lblBack);

        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_LIST);
                    CollaboratureFormPanel.this.bus.fireEvent(contentEvent);
                }
            }
        });

        this.add(backLink);
    }

    private void initBasicForm() {
        LayoutContainer lcInformation = new LayoutContainer();
        lcInformation.setSize(WIDTH - 10, HEIGHT);
        lcInformation.setLayout(new ColumnLayout());

        LayoutContainer lcLeft = new LayoutContainer();
        FormLayout flLeft = new FormLayout();
        flLeft.setLabelAlign(LabelAlign.RIGHT);
        flLeft.setLabelWidth(130);
        lcLeft.setLayout(flLeft);

        this.cbCivilite = new SimpleComboBox<String>();
        this.cbCivilite.add("Monsieur");
        this.cbCivilite.add("Madame");
        this.cbCivilite.add("Mademoiselle");
        this.cbCivilite.setTriggerAction(TriggerAction.ALL);
        this.cbCivilite.setAllowBlank(false);
        this.cbCivilite.setEditable(false);
        this.cbCivilite.setFieldLabel(messages.collaboraturecivilite());
        lcLeft.add(this.cbCivilite, this.formData);

        this.tfNom = new TextField<String>();
        this.tfNom.setMaxLength(30);
        this.tfNom.setAllowBlank(false);
        this.tfNom.setFieldLabel(messages.collaboraturenom());
        lcLeft.add(this.tfNom, this.formData);

        this.tfPrenom = new TextField<String>();
        this.tfPrenom.setMaxLength(30);
        this.tfPrenom.setAllowBlank(false);
        this.tfPrenom.setFieldLabel(messages.collaboratureprenom());
        lcLeft.add(this.tfPrenom, this.formData);

        this.dfDateDeNaissance = new DateField();
        this.dfDateDeNaissance.setFieldLabel(messages.collaboraturedatedenaissance());
        this.dfDateDeNaissance.setAllowBlank(false);
        this.dfDateDeNaissance.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        lcLeft.add(this.dfDateDeNaissance, this.formData);

        this.tfLieuDeNaissance = new TextField<String>();
        this.tfLieuDeNaissance.setMaxLength(60);
        this.tfLieuDeNaissance.setAllowBlank(false);
        this.tfLieuDeNaissance.setFieldLabel(messages.collaboraturelieudenaissance());
        lcLeft.add(this.tfLieuDeNaissance, this.formData);

        this.tfNationalite = new TextField<String>();
        this.tfNationalite.setMaxLength(60);
        this.tfNationalite.setAllowBlank(false);
        this.tfNationalite.setFieldLabel(messages.collaboratureNationalite());
        lcLeft.add(this.tfNationalite, this.formData);

        // setup right layout
        LayoutContainer lcRight = new LayoutContainer();
        FormLayout flRight = new FormLayout();
        flRight.setLabelAlign(LabelAlign.RIGHT);
        flRight.setLabelWidth(140);
        lcRight.setLayout(flRight);

        this.lfDateMiseAJourRubis = new LabelField();
        this.lfDateMiseAJourRubis.setFieldLabel(messages.collaboraturedatemiseajourrubis());
        lcRight.add(this.lfDateMiseAJourRubis, this.formData);

        this.lfSocieteRubis = new LabelField();
        this.lfSocieteRubis.setFieldLabel(messages.collaboraturesocieterubis());
        lcRight.add(this.lfSocieteRubis, this.formData);

        this.lfMatricule = new LabelField();
        this.lfMatricule.setFieldLabel(messages.collaboraturematriculerubis());
        lcRight.add(this.lfMatricule, this.formData);

        this.lfDateEntreGroup = new LabelField();
        this.lfDateEntreGroup.setFieldLabel(messages.collaboraturedateentregroup());
        lcRight.add(this.lfDateEntreGroup, this.formData);

        this.lfDateDeSortieSociete = new LabelField();
        this.lfDateDeSortieSociete.setFieldLabel(messages.collaboraturedatedesortiesociete());
        lcRight.add(this.lfDateDeSortieSociete, this.formData);

        lcInformation.add(lcLeft, new ColumnData(.5));
        lcInformation.add(lcRight, new ColumnData(.5));

        this.tfAddressPersonel = new TextArea();
        this.tfAddressPersonel.setMaxLength(300);
        // tfAddressPersonel.setWidth(50);
        this.tfAddressPersonel.setHeight(50);
        this.tfAddressPersonel.setAllowBlank(false);
        this.tfAddressPersonel.setFieldLabel(messages.collaboratureadressepersonnelle());

        LayoutContainer lcAddress = new LayoutContainer();
        FormLayout flAddress = new FormLayout();
        lcAddress.setSize(WIDTH - 10, HEIGHT);
        flAddress.setLabelAlign(LabelAlign.RIGHT);
        flAddress.setLabelWidth(130);
        lcAddress.setLayout(flAddress);
        lcAddress.add(this.tfAddressPersonel, this.formData);

        this.panel.add(lcInformation);
        this.panel.add(lcAddress);
    }

    private void initData() {
        this.delegantPerimetres.clear();
        this.delegatairePerimetres.clear();
        this.lbPeriemetreDelegant.reset();
        this.lbPeriemetreDelegataire.reset();
        this.clientFormationService.findByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallbackWithErrorResolution<List<FormationModel>>() {

                    @Override
                    public void onSuccess(List<FormationModel> arg0) {
                        CollaboratureFormPanel.this.formationStore.removeAll();
                        CollaboratureFormPanel.this.formationStore.add(arg0);
                    }
                });

        this.clientCollaboratureService.getAllDelegantsByEntiteId(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallbackWithErrorResolution<List<CollaborateurModel>>() {

                    @Override
                    public void onSuccess(List<CollaborateurModel> arg0) {
                        ListStore<CollaborateurModel> store = CollaboratureFormPanel.this.cbDelegant.getStore();
                        store.removeAll();
                        store.add(arg0);
                    }
                });

        this.clientCollaborateurTypeSerivce.getCollaborateurTypeByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallbackWithErrorResolution<List<CollaborateurTypeModel>>() {

                    @Override
                    public void onSuccess(List<CollaborateurTypeModel> arg0) {
                        CollaboratureFormPanel.this.cbColType.getStore().removeAll();
                        CollaboratureFormPanel.this.cbColType.getStore().add(arg0);
                    }
                });
    }

    private void save() {
        if (this.model == null) {
            this.model = new CollaborateurModel();
        }

        this.model.setCivilite(this.cbCivilite.getSimpleValue());
        this.model.setFirstname(this.tfPrenom.getValue());
        this.model.setLastname(this.tfNom.getValue());
        this.model.setDateNaissance(this.dfDateDeNaissance.getValue());
        this.model.setLieuNaissance(this.tfLieuDeNaissance.getValue());
        this.model.setNationality(this.tfNationalite.getValue());
        this.model.setAddress(this.tfAddressPersonel.getValue());
        // societe (si extreme)
        this.model.setNiveauHierarchique(this.tfNiveauHierarchique.getValue());
        // sorti
        if (this.roSortiOui.getValue()) {
            this.model.setSorti("O");
            this.model.setDateSortie(new Date());
        } else {
            this.model.setSorti("N");
        }
        this.model.setIsDelegant((this.cbCollaboratureDelegant.getValue() == true) ? 1 : 0);
        this.model.setIsDelegataire((this.cbCollaboratureDelegataire.getValue() == true) ? 1 : 0);

        this.model.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
        this.model.setDateEntree(new Date());

        if (this.cbCollaboratureDelegataire.getValue()) {
            this.model.setDelegatairePerimetres(this.createDelegatairePerimetres(this.model, this.delegatairePerimetres));
            // model.setPerimetreDelegataire(periemetreDelegataire);
            if (this.grid.getSelectionModel().getSelectedItems().isEmpty()) {
                this.model.setFormations(new ArrayList<FormationModel>());
            } else {
                this.model.setFormations(this.grid.getSelectionModel().getSelectedItems());
            }
        } else {
            this.model.setPerimetreDelegataire(null);
            this.model.setFormations(new ArrayList<FormationModel>());
        }

        if (this.cbCollaboratureDelegant.getValue()) {
            CollaborateurTypeModel type = this.cbColType.getValue();
            this.model.setType(this.cbColType.getValue());
            // model.setPerimetreDelegant(periemetreDelegant);
            this.model.setDelegantPerimetres(this.createDelegantPerimetres(this.model, this.delegantPerimetres));

            // Type mandataire social :
            // Nomme/confirme (champ bouton radio)
            // Date du Conseil d’Administration
            // Date d’effet de la decision
            //
            // Type autres (DE, DP, …) :
            // Date de délégation du mandataire social
            // Nom mandataire
            // Prénom mandataire
            // Qualite Mandataire

            if (type != null) {
                if (CommonUtils.belongsBYEFEGroup(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
                    String colGroup = type != null ? type.getGroup().getName() : null;
                    if (CollaborateurTypeModel.belongsMandataireSocial(colGroup)) {
                        this.model.setQualiteDelegant(this.tfQualiteDuDelegant.getValue());
                        this.model.setDateConseil(this.dfDateDuConseilAdministration.getValue());
                        this.model.setStatutConseil(this.cbStatuDuConseilAdministration.getSimpleValue());
                        this.model.setDateEffet(this.dfAEffectDu.getValue());

                        this.model.setDateDelegation(null);
                        this.model.setDelegant(null);
                        this.model.setQualiteColDelegant(null);
                    } else {
                        this.model.setQualiteDelegant(null);
                        this.model.setDateConseil(null);
                        this.model.setStatutConseil(null);
                        this.model.setDateEffet(null);
                        this.model.setDateDelegation(this.dfDateDelegation.getValue());
                        this.model.setDelegant(this.cbDelegant.getValue());
                        this.model.setQualiteColDelegant(this.tfQualiteCollaboratureDelegant.getValue());
                    }
                } else { // ETDE - no change
                    if (type.getId() == ClientConstant.COLLABORATEUR_TYPE_DG || type.getId() == ClientConstant.COLLABORATEUR_TYPE_DGD) {
                        this.model.setQualiteDelegant(this.tfQualiteDuDelegant.getValue());
                        this.model.setDateConseil(this.dfDateDuConseilAdministration.getValue());
                        this.model.setStatutConseil(this.cbStatuDuConseilAdministration.getSimpleValue());
                        this.model.setDateEffet(this.dfAEffectDu.getValue());

                        this.model.setDateDelegation(null);
                        this.model.setDelegant(null);
                        this.model.setQualiteColDelegant(null);
                    } else {
                        this.model.setQualiteDelegant(null);
                        this.model.setDateConseil(null);
                        this.model.setStatutConseil(null);
                        this.model.setDateEffet(null);
                        this.model.setDateDelegation(this.dfDateDelegation.getValue());
                        this.model.setDelegant(this.cbDelegant.getValue());
                        this.model.setQualiteColDelegant(this.tfQualiteCollaboratureDelegant.getValue());
                    }
                }
            }
        } else {
            this.model.setPerimetreDelegant(null);
        }

        if (this.isEdit == false) {
            this.clientCollaboratureService.insert(this.model, new AsyncCallback<CollaborateurModel>() {

                @Override
                public void onSuccess(CollaborateurModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_LIST);
                    contentEvent.setEvent(new ModifyCollaboratureEvent());
                    CollaboratureFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable caught) {
                    Info.display(messages.commonerror(), messages.commonServererror());
                }
            });
        } else {
            this.clientCollaboratureService.updateAndFormation(this.model, new AsyncCallback<CollaborateurModel>() {

                @Override
                public void onSuccess(CollaborateurModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_LIST);
                    contentEvent.setEvent(new ModifyCollaboratureEvent());
                    CollaboratureFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable arg0) {
                    Info.display(messages.commonerror(), messages.commonServererror());
                }
            });
        }
    }

    private List<DelegantPerimetreModel> createDelegantPerimetres(CollaborateurModel col, List<PerimetreModel> perimetres) {
        List<DelegantPerimetreModel> ret = new ArrayList<DelegantPerimetreModel>();
        for (PerimetreModel m : perimetres) {
            DelegantPerimetreModel dp = new DelegantPerimetreModel();
            dp.setPerimetre(m);
            dp.setDelegant(col);
            ret.add(dp);
        }
        return ret;
    }

    private List<DelegatairePerimetreModel> createDelegatairePerimetres(CollaborateurModel col, List<PerimetreModel> perimetres) {
        List<DelegatairePerimetreModel> ret = new ArrayList<DelegatairePerimetreModel>();
        for (PerimetreModel m : perimetres) {
            DelegatairePerimetreModel dp = new DelegatairePerimetreModel();
            dp.setPerimetre(m);
            dp.setDelegataire(col);
            ret.add(dp);
        }
        return ret;
    }

    private boolean checkIfPerimeterSelected(CheckBox cb, LabelField lb, List<PerimetreModel> hf, boolean isDelegantPerimetre) {
        if (cb.getValue()) {
            List<PerimetreTreeModel> selectedPerimetres = new ArrayList<PerimetreTreeModel>();
            TreePanel<PerimetreTreeModel> perimetreTree = this.getAdminTree();
            if (perimetreTree != null) {
                selectedPerimetres = perimetreTree.getSelectionModel() == null ? null : (List<PerimetreTreeModel>) perimetreTree.getSelectionModel()
                        .getSelectedItems();
                if (selectedPerimetres != null) {
                    StringBuffer perNames = new StringBuffer();
                    for (PerimetreTreeModel item : selectedPerimetres) {
                        if (true == item.getIsUoAdmin()) {
                            PerimetreModel pm = new PerimetreModel();
                            if (item.getPerId() != null) {
                                pm.setPerId(item.getPerId());
                            } else if (item.getParent() == null) {
                                pm.setPerId(SessionServiceImpl.getInstance().getEntiteContext().getName());
                            }
                            hf.add(pm);
                            perNames.append(item.getName());
                            perNames.append("<br>");
                        } else {
                            Info.display(messages.commoninfo(), messages.commonnopermissionperimetre());
                            // hf = null;
                            // lb.reset();
                        }
                    }
                    lb.setText(perNames.toString());
                    if (hf.isEmpty()) {
                        hf = null;
                        // lb.reset();
                        // lb.setVisible(false);
                        Info.display(messages.commoninfo(), messages.commonnopermissionperimetre());
                        return false;
                    } else {
                        lb.setVisible(true);
                        if (isDelegantPerimetre) {
                            this.model.setChangeDelegantPerimetres(2);
                        } else {
                            this.model.setChangeDelegatairePerimetres(2);
                        }
                        return true;
                    }

                } else {
                    Info.display(messages.commoninfo(), messages.admintreeselect());
                    hf = null;
                    // lb.reset();
                    // lb.setVisible(false);
                    return false;
                }
            }
            hf = null;
            // lb.reset();
            return false;
        } else {
            if (isDelegantPerimetre) {
                this.model.setChangeDelegantPerimetres(2);

            } else {
                this.model.setChangeDelegatairePerimetres(2);
            }
            hf = null;
            lb.reset();
            lb.setVisible(false);
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    private TreePanel<PerimetreTreeModel> getAdminTree() {
        TreePanel<PerimetreTreeModel> component = (TreePanel<PerimetreTreeModel>) ComponentManager.get().get(ClientConstant.ADMIN_TREE_ID);
        return component;
    }
}
