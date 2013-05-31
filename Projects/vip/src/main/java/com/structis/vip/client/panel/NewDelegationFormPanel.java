package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.DateTimePropertyEditor;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.HtmlEditor;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.constant.Helper;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationEvent;
import com.structis.vip.client.event.DelegationEventHandler;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.service.ClientCollaborateurServiceAsync;
import com.structis.vip.client.service.ClientDelegationModelServiceAsync;
import com.structis.vip.client.service.ClientDelegationNatureServiceAsync;
import com.structis.vip.client.service.ClientDelegationStatusServiceAsync;
import com.structis.vip.client.service.ClientDelegationTypeServiceAsync;
import com.structis.vip.client.service.ClientDemDomServiceAsync;
import com.structis.vip.client.service.ClientEntiteJuridiqueServiceAsync;
import com.structis.vip.client.service.ClientFieldRuleServiceAsync;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.shared.SharedConstant;
import com.structis.vip.shared.exception.DelegationException;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.DelegationDelegataireModel;
import com.structis.vip.shared.model.DelegationModel;
import com.structis.vip.shared.model.DelegationNatureModel;
import com.structis.vip.shared.model.DelegationStatusModel;
import com.structis.vip.shared.model.DelegationTypeModel;
import com.structis.vip.shared.model.DemDomModel;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.EntiteJuridiqueModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.FieldRuleModel;
import com.structis.vip.shared.model.LanguageModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTypeModel;

/**
 * Implement new delegation field set class
 */
public class NewDelegationFormPanel extends CommonDelegationPanel {

    private LabelField lblType;
    private LabelField cbStatus;
    private ComboBox<DelegationNatureModel> cbNature;
    private ContentPanel cpDescription;
    private HtmlEditor heDescription;
    private LabelField lblDelegationPrincipale;
    private ComboBox<CollaborateurModel> cbDelegant;
    private ComboBox<CollaborateurModel> cbDelegataire;
    private SimpleComboBox<String> cbConjoin;
    private DateField dfDebut;
    private DateField dfFin;
    private DateField dfSignatureProposition;
    private TextField<String> txtSignatureProposition;
    private DateField dfSignature;
    private TextField<String> txtSignature;
    private DateField lblDelegataireDateFormation;
    private DateField dfSignatureRecommandation;
    private TextField<String> txtSignatureRecommandation;
    private NumberField txtLimiteCommercial;
    private NumberField txtLimiteAvenants;
    private NumberField txtLimiteDevis;
    private NumberField txtLimiteEntreprise;
    private NumberField txtLimiteAssurance;
    private TextArea txtChamps;
    private Label lblErrorMessage;
    private LayoutContainer errorLayout;
    private TextField<String> txtZone;
    private TextField<String> txtOperations;

    private Button btnAjouter;
    private Button btnAnnuler;
    private Button btnModifier;
    private boolean isEditMode = false;
    private boolean isRenewMode = false;
    private boolean isSubMode = false;
    private boolean isTempMode = false;

    private ClientCollaborateurServiceAsync clientCollaborateurService = ClientCollaborateurServiceAsync.Util.getInstance();
    private ListStore<CollaborateurModel> lstDelegant = new ListStore<CollaborateurModel>();
    private ListStore<CollaborateurModel> lstDelegataire = new ListStore<CollaborateurModel>();

    private ClientDemDomServiceAsync clientDemDomServiceAsync = ClientDemDomServiceAsync.Util.getInstance();

    private ClientDelegationStatusServiceAsync clientDelegationStatusService = ClientDelegationStatusServiceAsync.Util.getInstance();

    private ClientDelegationNatureServiceAsync clientDelegationNatureService = ClientDelegationNatureServiceAsync.Util.getInstance();
    private ListStore<DelegationNatureModel> lstDelegationNature = new ListStore<DelegationNatureModel>();

    private ClientPerimetreServiceAsync clientPerimetreService = ClientPerimetreServiceAsync.Util.getInstance();
    private ClientEntiteJuridiqueServiceAsync clientEntiteJuridiqueService = ClientEntiteJuridiqueServiceAsync.Util.getInstance();
    private ClientFieldRuleServiceAsync clientFieldRuleService = ClientFieldRuleServiceAsync.Util.getInstance();
    private ClientDelegationModelServiceAsync clientDelegationModelServiceAsync = ClientDelegationModelServiceAsync.Util.getInstance();
    private ClientDelegationTypeServiceAsync clientDelegationTypeService = ClientDelegationTypeServiceAsync.Util.getInstance();

    private DelegationModel delegationModelParent;

    private PerimetreModel perimetreModel = new PerimetreModel();

    private EntiteJuridiqueModel entiteJuridiqueModel = new EntiteJuridiqueModel();

    private Grid<DocumentMdlModel> documentGrid;

    private ColumnModel documentColumnModel;

    private DelegationNatureModel originalNature;
    private ContentPanel documentView;
    private ListStore<DelegationDelegataireModel> lstDelegataires = new ListStore<DelegationDelegataireModel>();
    private Label viewTitle;
    private CheckBoxListView<DelegationDelegataireModel> view;

    public NewDelegationFormPanel(SimpleEventBus bus) {
        super(bus);
        this.setHeading(this.messages.delegationformheading());

        this.addBackLink();
        LayoutContainer lcLine0 = new LayoutContainer();
        lcLine0.setSize(WIDTH, HEIGHT);
        lcLine0.setLayout(new ColumnLayout());
        lcLine0.add(new HTML("<hr width='680px'/>"));
        this.add(lcLine0);
        this.initButtons();

        this.addErrorLabel();

        // add view in field set
        this.initTopForm();
        // add the seperator line
        // setup top layout
        LayoutContainer lcLine = new LayoutContainer();
        lcLine.setSize(WIDTH, HEIGHT);
        lcLine.setLayout(new ColumnLayout());
        lcLine.add(new HTML("<hr width='680px'/>"));
        this.add(lcLine);
        // add view for information form
        this.initInformationForm();
        // add field set
        this.initFieldSets();

        this.bus.addHandler(DelegationEvent.getType(), new DelegationEventHandler() {

            @Override
            public void onLoadAction(final DelegationEvent event) {
                if (event.getMode() == DelegationEvent.MODE_IS_UPDATED_DOCUMENT) {
                    return;
                }
                NewDelegationFormPanel.this.resetForm();
                if (NewDelegationFormPanel.this.documentGrid.getStore() != null) {
                    NewDelegationFormPanel.this.documentGrid.getStore().removeAll();
                }

                NewDelegationFormPanel.this.documentColumnModel.setHidden(1, true);
                NewDelegationFormPanel.this.lblDelegationPrincipale.setVisible(false);

                NewDelegationFormPanel.this.enabledFields(true);

                switch (event.getMode()) {
                case DelegationEvent.MODE_IS_NEW:
                    NewDelegationFormPanel.this.newMode(event);
                    break;
                case DelegationEvent.MODE_IS_EDIT:
                    NewDelegationFormPanel.this.editMode(event);
                    break;
                case DelegationEvent.MODE_IS_PRINT_DOCUMENT:
                    NewDelegationFormPanel.this.enabledFields(false);
                    NewDelegationFormPanel.this.editMode(event);
                    break;
                case DelegationEvent.MODE_IS_CREATE_SUB_DELEGATION:
                    NewDelegationFormPanel.this.clientDelegationService.findById(event.getDelegationId(),
                            new AsyncCallbackWithErrorResolution<DelegationModel>() {

                                @Override
                                public void onSuccess(DelegationModel arg0) {
                                    final DelegationModel parentDelegationModel = arg0;
                                    NewDelegationFormPanel.this.clientDelegationTypeService
                                            .getSubType(new AsyncCallbackWithErrorResolution<DelegationTypeModel>() {

                                                @Override
                                                public void onSuccess(DelegationTypeModel arg0) {
                                                    NewDelegationFormPanel.this.newModeSub(parentDelegationModel.getPerimeter().getEntite(),
                                                            parentDelegationModel.getPerimeter(), parentDelegationModel.getDelegataire(), arg0);
                                                    NewDelegationFormPanel.this.delegationModel.setParent(parentDelegationModel);

                                                    // if delegation type is sub-deleagtion, show principal
                                                    if (parentDelegationModel != null) {
                                                        NewDelegationFormPanel.this.lblDelegationPrincipale.setText(parentDelegationModel
                                                                .getDelegationNature().getName());
                                                        NewDelegationFormPanel.this.lblDelegationPrincipale.setRawValue(parentDelegationModel.getId()
                                                                .toString());
                                                        NewDelegationFormPanel.this.lblDelegationPrincipale.setVisible(true);
                                                        NewDelegationFormPanel.this.cpDescription.setVisible(true);
                                                    }
                                                };
                                            });
                                };
                            });
                    break;
                case DelegationEvent.MODE_IS_CREATE_TEM_DELEGATION:
                    NewDelegationFormPanel.this.clientDelegationService.findById(event.getDelegationId(),
                            new AsyncCallbackWithErrorResolution<DelegationModel>() {

                                @Override
                                public void onSuccess(DelegationModel arg0) {
                                    NewDelegationFormPanel.this.isTempMode = true;
                                    NewDelegationFormPanel.this.delegationModel = arg0;

                                    NewDelegationFormPanel.this.clientDelegationTypeService
                                            .getTemporaryType(new AsyncCallbackWithErrorResolution<DelegationTypeModel>() {

                                                @Override
                                                public void onSuccess(DelegationTypeModel arg0) {
                                                    NewDelegationFormPanel.this.delegationModel.setDelegationType(arg0);

                                                    NewDelegationFormPanel.this.newModeTemp(event);
                                                    DelegationModel parent = new DelegationModel();
                                                    parent.setId(event.getDelegationId());
                                                    NewDelegationFormPanel.this.delegationModel.setParent(parent);
                                                    NewDelegationFormPanel.this.delegationModel.setId(null);
                                                };
                                            });
                                };
                            });
                    break;
                case DelegationEvent.MODE_IS_RENEW_DELEGATION:
                    NewDelegationFormPanel.this.clientDelegationService.findById(event.getDelegationId(),
                            new AsyncCallbackWithErrorResolution<DelegationModel>() {

                                @Override
                                public void onSuccess(DelegationModel model) {
                                    NewDelegationFormPanel.this.isRenewMode = true;
                                    NewDelegationFormPanel.this.delegationModel = model;
                                    NewDelegationFormPanel.this.delegationModelParent = event.getDelegationModel();
                                    NewDelegationFormPanel.this.delegationModel.setParent(NewDelegationFormPanel.this.delegationModelParent);
                                    Date startDate = CalendarUtil.copyDate(NewDelegationFormPanel.this.delegationModelParent.getEndDate());
                                    CalendarUtil.addDaysToDate(startDate, 1);
                                    NewDelegationFormPanel.this.delegationModel.setStartDate(startDate);
                                    NewDelegationFormPanel.this.delegationModel.setId(null);
                                    NewDelegationFormPanel.this.delegationModel.setIsSigned(0);
                                    NewDelegationFormPanel.this.delegationModel.setDate1(null); // R12
                                    NewDelegationFormPanel.this.delegationModel.setDate2(null);
                                    NewDelegationFormPanel.this.delegationModel.setDate3(null);

                                    NewDelegationFormPanel.this.clientDelegationStatusService.findById(ClientConstant.DELEGATION_STATUS_IS_P,
                                            new AsyncCallbackWithErrorResolution<DelegationStatusModel>() {

                                                @Override
                                                public void onSuccess(DelegationStatusModel arg0) {
                                                    NewDelegationFormPanel.this.delegationModel.setDelegationStatus(arg0);
                                                    NewDelegationFormPanel.this.renewMode(event);
                                                }
                                            });
                                };
                            });
                    break;
                case DelegationEvent.MODE_IS_REPLACE_DELEGANT_OR_DELEGATAIRE:
                    if (!SharedConstant.ENTITE_ID_ETDE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
                        NewDelegationFormPanel.this.enabledFields(false);

                        NewDelegationFormPanel.this.cbDelegant.setEnabled(true);
                        NewDelegationFormPanel.this.cbDelegataire.setEnabled(true);
                        NewDelegationFormPanel.this.dfDebut.setEnabled(true);

                        NewDelegationFormPanel.this.delegantFieldSet.setEnabled(true);
                        NewDelegationFormPanel.this.delegataireFieldSet.setEnabled(true);
                        NewDelegationFormPanel.this.societeFieldSet.setEnabled(true);
                        NewDelegationFormPanel.this.chantierFieldSet.setEnabled(true);
                        NewDelegationFormPanel.this.dfDebut.setEnabled(true); // R12
                        NewDelegationFormPanel.this.dfFin.setEnabled(true);// R12
                        NewDelegationFormPanel.this.dfSignature.setEnabled(true);// R12
                        NewDelegationFormPanel.this.dfSignatureProposition.setEnabled(true);// R12
                        NewDelegationFormPanel.this.dfSignatureRecommandation.setEnabled(true);// R12
                        NewDelegationFormPanel.this.txtSignature.setEnabled(true);// R12
                        NewDelegationFormPanel.this.txtSignatureProposition.setEnabled(true);// R12
                        NewDelegationFormPanel.this.txtSignatureRecommandation.setEnabled(true);// R12

                        NewDelegationFormPanel.this.delegantFieldSet.getTitre().setEnabled(true);

                        NewDelegationFormPanel.this.clientDelegationService.findById(event.getDelegationId(),
                                new AsyncCallbackWithErrorResolution<DelegationModel>() {

                                    @Override
                                    public void onSuccess(DelegationModel model) {
                                        NewDelegationFormPanel.this.isRenewMode = true;
                                        NewDelegationFormPanel.this.delegationModel = model;
                                        NewDelegationFormPanel.this.delegationModelParent = event.getDelegationModel();
                                        NewDelegationFormPanel.this.delegationModel.setParent(NewDelegationFormPanel.this.delegationModelParent);
                                        NewDelegationFormPanel.this.delegationModel.setStartDate(null);// R12
                                        NewDelegationFormPanel.this.delegationModel.setId(null);
                                        NewDelegationFormPanel.this.delegationModel.setIsSigned(0);
                                        NewDelegationFormPanel.this.delegationModel.setStartDate(null);// R12
                                        NewDelegationFormPanel.this.delegationModel.setDate1(null);// R12
                                        NewDelegationFormPanel.this.delegationModel.setDate2(null);// R12
                                        NewDelegationFormPanel.this.delegationModel.setDate3(null);// R12
                                        // delegationModel.setDelegant(null);//R12
                                        // delegationModel.setDelegataire(null);//R12
                                        NewDelegationFormPanel.this.delegationModel.setDelegantTitle(null);

                                        NewDelegationFormPanel.this.clientDelegationStatusService.findById(ClientConstant.DELEGATION_STATUS_IS_P,
                                                new AsyncCallbackWithErrorResolution<DelegationStatusModel>() {

                                                    @Override
                                                    public void onSuccess(DelegationStatusModel arg0) {
                                                        NewDelegationFormPanel.this.delegationModel.setDelegationStatus(arg0);
                                                        NewDelegationFormPanel.this.renewMode(event);
                                                    }
                                                });
                                    };
                                });
                    } else {
                        NewDelegationFormPanel.this.enabledFields(false);

                        NewDelegationFormPanel.this.cbDelegant.setEnabled(true);
                        NewDelegationFormPanel.this.cbDelegataire.setEnabled(true);

                        NewDelegationFormPanel.this.delegantFieldSet.setEnabled(true);
                        NewDelegationFormPanel.this.delegataireFieldSet.setEnabled(true);

                        NewDelegationFormPanel.this.delegantFieldSet.getTitre().setEnabled(true);
                        NewDelegationFormPanel.this.clientDelegationService.findById(event.getDelegationId(),
                                new AsyncCallbackWithErrorResolution<DelegationModel>() {

                                    @Override
                                    public void onSuccess(DelegationModel model) {
                                        NewDelegationFormPanel.this.isRenewMode = true;
                                        NewDelegationFormPanel.this.delegationModel = model;
                                        NewDelegationFormPanel.this.delegationModelParent = event.getDelegationModel();
                                        NewDelegationFormPanel.this.delegationModel.setParent(NewDelegationFormPanel.this.delegationModelParent);
                                        Date startDate = CalendarUtil.copyDate(NewDelegationFormPanel.this.delegationModelParent.getEndDate());
                                        CalendarUtil.addDaysToDate(startDate, 1);
                                        NewDelegationFormPanel.this.delegationModel.setStartDate(startDate);
                                        NewDelegationFormPanel.this.delegationModel.setId(null);
                                        NewDelegationFormPanel.this.delegationModel.setIsSigned(0);

                                        NewDelegationFormPanel.this.clientDelegationStatusService.findById(ClientConstant.DELEGATION_STATUS_IS_P,
                                                new AsyncCallbackWithErrorResolution<DelegationStatusModel>() {

                                                    @Override
                                                    public void onSuccess(DelegationStatusModel arg0) {
                                                        NewDelegationFormPanel.this.delegationModel.setDelegationStatus(arg0);
                                                        NewDelegationFormPanel.this.replaceDelegantDelegataireMode(event);
                                                    }
                                                });
                                    };
                                });
                    }
                    break;
                }

                // if perimeter is chantier, conjointe combobox must be showed
                // if (delegationModel != null
                // && ConstantClient.PERIMETER_TYPE_NAME_IS_CHANTIER.equals(delegationModel.getPerimeter().getType().getName())) {
                if (NewDelegationFormPanel.this.delegationModel != null
                        && CommonUtils.isChantierType(NewDelegationFormPanel.this.delegationModel.getPerimeter().getType().getName())) {
                    NewDelegationFormPanel.this.cbConjoin.setVisible(true);
                } else {
                    NewDelegationFormPanel.this.cbConjoin.setVisible(false);
                }
            }
        });
        this.addDateLieuFieldHandlers();
    }

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);
        GWT.log(this.getClass().getName() + ":onRender");
    }

    private void addDateLieuFieldHandlers() {
        this.dfSignature.addListener(Events.Blur, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                if (NewDelegationFormPanel.this.dfSignatureProposition.getValue() == null) {
                    NewDelegationFormPanel.this.dfSignatureProposition.setValue(NewDelegationFormPanel.this.dfSignature.getValue());
                }
                if (NewDelegationFormPanel.this.dfSignatureRecommandation.getValue() == null) {
                    NewDelegationFormPanel.this.dfSignatureRecommandation.setValue(NewDelegationFormPanel.this.dfSignature.getValue());
                }
            }
        });
        this.txtSignature.addListener(Events.Blur, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                if (NewDelegationFormPanel.this.txtSignatureProposition.getValue() == null
                        || NewDelegationFormPanel.this.txtSignatureProposition.getValue().isEmpty()) {
                    NewDelegationFormPanel.this.txtSignatureProposition.setValue(NewDelegationFormPanel.this.txtSignature.getValue());
                }
                if (NewDelegationFormPanel.this.txtSignatureRecommandation.getValue() == null
                        || NewDelegationFormPanel.this.txtSignatureRecommandation.getValue().isEmpty()) {
                    NewDelegationFormPanel.this.txtSignatureRecommandation.setValue(NewDelegationFormPanel.this.txtSignature.getValue());
                }
            }
        });
    }

    private void newMode(DelegationEvent event) {
        AppUtil.putInEditMode();
        this.isEditMode = false;
        this.documentColumnModel.setHidden(1, true);

        this.delegationModel = new DelegationModel();
        this.entiteModel = event.getEntiteModel();
        this.perimetreModel = event.getPerimetreModel();
        this.perimetreModel.setEntite(this.entiteModel);
        this.delegationModel.setEntite(this.entiteModel);
        this.delegationModel.setDelegationType(event.getDelegationTypeModel());
        this.delegationModel.setPerimeter(this.perimetreModel);

        // default status set to P
        DelegationStatusModel status = new DelegationStatusModel();
        status.setId(ClientConstant.DELEGATION_STATUS_IS_P);
        this.delegationModel.setDelegationStatus(status);
        this.delegationModel.setIsSigned(0);
        // add BYTP
        // set end date for BYEFE automatic at 31 May. ETDE not need end date
        // if (ConstantClient.ENTITE_ID_IS_BYEFE.equals(entiteModel.getEntId())) {
        if (CommonUtils.belongsBYEFEGroup(this.entiteModel.getEntId())) {
            this.delegationModel.setEndDate(Helper.getBYEFEEndDate());
        } else if (SharedConstant.ENTITE_ID_ETDE.equals(this.entiteModel.getEntId())) {
            this.delegationModel.setEndDate(null);
        }
        this.loadDataFields(this.delegationModel);
        this.loadData(false);
        this.documentView.setVisible(false);
    }

    private void newModeSub(EntiteModel entiteModel, PerimetreModel perimetreModel, CollaborateurModel delegataire,
            DelegationTypeModel delegationTypeModel) {
        AppUtil.putInEditMode();
        this.documentColumnModel.setHidden(1, true);

        this.isSubMode = true;
        this.isEditMode = false;
        this.delegationModel = new DelegationModel();
        this.entiteModel = entiteModel;
        this.perimetreModel = perimetreModel;
        this.delegationModel.setDelegationType(delegationTypeModel);
        this.delegationModel.setPerimeter(this.perimetreModel);
        DelegationStatusModel status = new DelegationStatusModel();
        status.setId(ClientConstant.DELEGATION_STATUS_IS_P);
        this.delegationModel.setDelegationStatus(status);
        this.delegationModel.setIsSigned(0);
        if (this.lstDelegant.contains(delegataire)) {
            this.delegationModel.setDelegant(delegataire); // R13
        } else {
            this.lstDelegant.add(delegataire);
            this.delegationModel.setDelegant(delegataire); // R13
            // AppUtil.showWarning(messages.perimetredelegatairedelegantnotmap());
        }

        // add BYTP
        // set end date for BYEFE automatic at 31 May. ETDE not need end date
        // if (ConstantClient.ENTITE_ID_IS_BYEFE.equals(entiteModel.getEntId())) {
        if (CommonUtils.belongsBYEFEGroup(entiteModel.getEntId())) {
            this.cbDelegant.setEnabled(false);
            this.delegationModel.setEndDate(Helper.getBYEFEEndDate());
        } else if (SharedConstant.ENTITE_ID_ETDE.equals(entiteModel.getEntId())) {
            this.delegationModel.setEndDate(null);
        }
        this.loadDataFields(this.delegationModel);
        this.loadData(true);
        this.documentView.setVisible(false);
    }

    private void newModeTemp(DelegationEvent event) {
        AppUtil.putInEditMode();
        this.isEditMode = false;

        this.documentColumnModel.setHidden(1, true);
        this.entiteModel = event.getEntiteModel();

        this.perimetreModel = this.delegationModel.getPerimeter();
        if (this.perimetreModel.getEntite() == null) {
            this.perimetreModel.setEntite(this.entiteModel);
        }
        if (this.entiteModel == null) {
            this.entiteModel = this.perimetreModel.getEntite();
        }

        // reset data for the new temporary delegation
        this.delegationModel.setIsSigned(0);
        this.clientDelegationStatusService.findById(ClientConstant.DELEGATION_STATUS_IS_P,
                new AsyncCallbackWithErrorResolution<DelegationStatusModel>() {

                    @Override
                    public void onSuccess(DelegationStatusModel arg0) {
                        NewDelegationFormPanel.this.delegationModel.setDelegationStatus(arg0);
                        if (arg0 != null) {
                            NewDelegationFormPanel.this.cbStatus.setText(arg0.getName());
                        }
                    }
                });

        this.delegationModel.setDelegataire(null);
        this.delegationModel.setStartDate(null);
        this.delegationModel.setEndDate(null);

        // apply rules
        this.applyRule2(this.delegationModel, this.delegationModel.getPerimeter().getType(), this.delegationModel.getDelegationNature(), null);

        // load data fields
        this.loadDataFields(this.delegationModel);
        this.loadData(false);

        if (this.delegationModel.getParent() != null) {
            // if delegation type is sub-deleagtion, show principal
            this.lblDelegationPrincipale.setText(this.delegationModel.getParent().getDelegationNature().getName());
            this.lblDelegationPrincipale.setRawValue(this.delegationModel.getParent().getId().toString());
            this.lblDelegationPrincipale.setVisible(true);
        }

        // show and hide field depend on Temporary Type
        this.enabledFields(false);
        this.cbDelegant.setEnabled(false);

        // show delegataire
        this.cbDelegataire.setVisible(true);
        this.cbDelegataire.setEnabled(true);
        // show date debut
        this.dfDebut.setVisible(true);
        this.dfDebut.setEnabled(true);
        // show date de fin
        this.dfFin.setVisible(true);
        this.dfFin.setEnabled(true);

        this.delegantFieldSet.setEnabled(true);
        this.delegataireFieldSet.setEnabled(true);
        this.societeFieldSet.setEnabled(true);
        this.chantierFieldSet.setEnabled(true);
        this.delegantFieldSet.getTitre().setEnabled(false);
        this.documentView.setVisible(false);
    }

    private void editMode(DelegationEvent event) {
        AppUtil.putInEditMode();
        this.documentView.setVisible(true);
        this.documentColumnModel.setHidden(1, false);

        this.entiteModel = event.getEntiteModel();
        this.clientDelegationService.findById(event.getDelegationId(), new AsyncCallbackWithErrorResolution<DelegationModel>() {

            @Override
            public void onSuccess(DelegationModel arg0) {
                NewDelegationFormPanel.this.isEditMode = true;
                NewDelegationFormPanel.this.delegationModel = arg0;
                NewDelegationFormPanel.this.originalNature = NewDelegationFormPanel.this.delegationModel.getDelegationNature();

                NewDelegationFormPanel.this.perimetreModel = NewDelegationFormPanel.this.delegationModel.getPerimeter();
                if (NewDelegationFormPanel.this.perimetreModel.getEntite() == null) {
                    NewDelegationFormPanel.this.perimetreModel.setEntite(NewDelegationFormPanel.this.entiteModel);
                }
                if (NewDelegationFormPanel.this.entiteModel == null) {
                    NewDelegationFormPanel.this.entiteModel = NewDelegationFormPanel.this.perimetreModel.getEntite();
                }
                // tdo
                NewDelegationFormPanel.this.applyRule2(NewDelegationFormPanel.this.delegationModel, NewDelegationFormPanel.this.delegationModel
                        .getPerimeter().getType(), NewDelegationFormPanel.this.delegationModel.getDelegationNature(), null);

                // applyRule(delegationModel.getDelegationNature().getId());

                NewDelegationFormPanel.this.loadDataFields(NewDelegationFormPanel.this.delegationModel);
                NewDelegationFormPanel.this.loadData(false);

                if ((arg0.getParent() != null)
                        && (NewDelegationFormPanel.this.delegationModel.getDelegationType() != null)
                        && (NewDelegationFormPanel.this.delegationModel.getDelegationType().getId().intValue() != ClientConstant.DELEGATION_TYPE_IS_PRINCIPAL)) {
                    // if delegation type is sub-deleagtion, show principal
                    NewDelegationFormPanel.this.lblDelegationPrincipale.setText(arg0.getParent().getDelegationNature().getName());
                    NewDelegationFormPanel.this.lblDelegationPrincipale.setRawValue(arg0.getParent().getId().toString());
                    NewDelegationFormPanel.this.lblDelegationPrincipale.setVisible(true);
                }
                if (NewDelegationFormPanel.this.delegationModel.getIsSigned() > 0) {
                    NewDelegationFormPanel.this.cbNature.setEditable(false);
                }
            }
        });
    }

    private void renewMode(DelegationEvent event) {
        AppUtil.putInEditMode();
        this.documentView.setVisible(true);
        this.documentColumnModel.setHidden(1, false);

        this.entiteModel = event.getEntiteModel();
        this.originalNature = this.delegationModel.getDelegationNature();
        this.perimetreModel = this.delegationModel.getPerimeter();

        if (this.perimetreModel.getEntite() == null) {
            this.perimetreModel.setEntite(this.entiteModel);
        }
        if (this.entiteModel == null) {
            this.entiteModel = this.perimetreModel.getEntite();
        }
        this.applyRule2(this.delegationModel, this.delegationModel.getPerimeter().getType(), this.delegationModel.getDelegationNature(), null);

        this.loadDataFields(this.delegationModel);
        this.loadData(false);
    }

    private void replaceDelegantDelegataireMode(DelegationEvent event) {
        this.renewMode(event);
        this.isRenewMode = true;
    }

    private void loadDataFields(DelegationModel delegationModel) {
        if (delegationModel.getStartDate() != null) {
            this.dfDebut.setValue(delegationModel.getStartDate());
        }
        if (delegationModel.getEndDate() != null) {
            this.dfFin.setValue(delegationModel.getEndDate());
        }

        if (delegationModel.getDate2() != null) {
            this.dfSignature.setValue(delegationModel.getDate2());
        }
        if (delegationModel.getPlace2() != null) {
            this.txtSignature.setValue(delegationModel.getPlace2());
        }

        if (delegationModel.getDate1() != null) {
            this.dfSignatureProposition.setValue(delegationModel.getDate1());
        }
        if (delegationModel.getPlace1() != null) {
            this.txtSignatureProposition.setValue(delegationModel.getPlace1());
        }

        if (delegationModel.getDate3() != null) {
            this.dfSignatureRecommandation.setValue(delegationModel.getDate3());
        }
        if (delegationModel.getPlace3() != null) {
            this.txtSignatureRecommandation.setValue(delegationModel.getPlace3());
        }

        if (delegationModel.getAmount1() != null) {
            this.txtLimiteCommercial.setValue(delegationModel.getAmount1());
        }
        if (delegationModel.getAmount2() != null) {
            this.txtLimiteAvenants.setValue(delegationModel.getAmount2());
        }
        if (delegationModel.getAmount3() != null) {
            this.txtLimiteDevis.setValue(delegationModel.getAmount3());
        }
        if (delegationModel.getAmount4() != null) {
            this.txtLimiteEntreprise.setValue(delegationModel.getAmount4());
        }
        if (delegationModel.getAmount5() != null) {
            this.txtLimiteAssurance.setValue(delegationModel.getAmount5());
        }
        if (delegationModel.getComment1() != null) {
            this.txtChamps.setValue(delegationModel.getComment1());
        }
        if (delegationModel.getDescription() != null) {
            this.heDescription.setValue(delegationModel.getDescription());
        }
        if (delegationModel.getZone() != null) {
            this.txtZone.setValue(delegationModel.getZone());
        }
        if (delegationModel.getOperations() != null) {
            this.txtOperations.setValue(delegationModel.getOperations());
        }

        this.cbConjoin
                .setSimpleValue((delegationModel.getDelegationConjointe() != null && delegationModel.getDelegationConjointe() == 1) ? this.messages
                        .commonOui() : this.messages.commonNon()); // R13
    }

    private void addBackLink() {
        // LayoutContainer backLink = new LayoutContainer();
        // backLink.setSize(WIDTH, HEIGHT);
        Label lblBack = new Label(this.messages.commonRetoursalaLstedesdelegations());

        lblBack.setStyleName("x-link-item");
        // backLink.setStyleAttribute("margin-bottom", "20px");
        // backLink.add(lblBack);

        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInEditMode()) {
                    NewDelegationFormPanel.this.resetForm();
                    NewDelegationFormPanel.this.documentGrid.getStore().removeAll();
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GRID_DELEGATION_PANEL);
                    NewDelegationFormPanel.this.bus.fireEvent(contentEvent);
                }
            }
        });

        // this.add(backLink);

        HorizontalPanel hpButton = new HorizontalPanel();
        hpButton.setHorizontalAlign(HorizontalAlignment.LEFT);
        hpButton.setTableWidth("" + WIDTH);
        hpButton.add(lblBack);
        LayoutContainer lb = new LayoutContainer();
        lb.setWidth("200");
        hpButton.add(lb);
        // add button
        this.btnAnnuler = new Button(this.messages.delegationformannuler());
        hpButton.add(this.btnAnnuler);

        // remove button
        this.btnModifier = new Button(this.messages.commonValiderButton());
        hpButton.add(this.btnModifier);

        this.add(hpButton, new FlowData(10, 0, 0, 0));
    }

    private void addErrorLabel() {
        this.errorLayout = new LayoutContainer();
        this.errorLayout.setHeight(30);

        this.lblErrorMessage = new Label("");
        this.lblErrorMessage.setStyleName("errorMessage");
        this.errorLayout.add(this.lblErrorMessage);
        this.add(this.errorLayout);
        this.errorLayout.setVisible(false);
    }

    private void showErrorLabel(Boolean isShow, String message) {
        this.lblErrorMessage.setText(message);
        this.errorLayout.setVisible(isShow);
    }

    /**
     * load data from other table
     */
    private void loadData(Boolean isSub) {
        this.lblType.setValue(this.delegationModel.getDelegationType().getName());
        this.clientPerimetreService.findById(this.perimetreModel.getPerId(), new AsyncCallbackWithErrorResolution<PerimetreModel>() {

            @Override
            public void onSuccess(PerimetreModel arg0) {
                NewDelegationFormPanel.this.perimetreModel = arg0; // tdo 24 Dec

                if (arg0 != null && arg0.getEntiteJuridique() != null) {
                    NewDelegationFormPanel.this.entiteJuridiqueModel = arg0.getEntiteJuridique();
                } else {
                    NewDelegationFormPanel.this.clientEntiteJuridiqueService.getDefaultByEntiteId(SessionServiceImpl.getInstance().getEntiteContext()
                            .getEntId(), new AsyncCallbackWithErrorResolution<EntiteJuridiqueModel>() {

                        @Override
                        public void onSuccess(EntiteJuridiqueModel arg0) {
                            NewDelegationFormPanel.this.entiteJuridiqueModel = arg0;
                        }
                    });
                }
            }
        });

        this.clientDelegationStatusService.getAllDelegationStatuses(new AsyncCallbackWithErrorResolution<List<DelegationStatusModel>>() {

            @Override
            public void onSuccess(List<DelegationStatusModel> arg0) {
                if (NewDelegationFormPanel.this.isEditMode || NewDelegationFormPanel.this.isRenewMode || NewDelegationFormPanel.this.isTempMode) {
                    if (NewDelegationFormPanel.this.delegationModel.getDelegationStatus() != null) {
                        NewDelegationFormPanel.this.cbStatus.setText(NewDelegationFormPanel.this.delegationModel.getDelegationStatus().getName());
                    }
                } else {
                    for (DelegationStatusModel delegationStatusModel : arg0) {
                        if (delegationStatusModel.getId() == 6) {
                            NewDelegationFormPanel.this.cbStatus.setText(delegationStatusModel.getName());
                            break;
                        }
                    }
                }
            }
        });

        String ptyId = this.perimetreModel.getType() == null ? null : this.perimetreModel.getType().getPtyId();

        this.clientDelegationNatureService.findNatureForNew(this.perimetreModel.getPerId(), this.entiteModel.getEntId(), ptyId, isSub,
                new AsyncCallbackWithErrorResolution<List<DelegationNatureModel>>() {

                    @Override
                    public void onSuccess(List<DelegationNatureModel> arg0) {
                        NewDelegationFormPanel.this.lstDelegationNature.removeAll();
                        NewDelegationFormPanel.this.lstDelegationNature.add(arg0);
                        NewDelegationFormPanel.this.cbNature.setStore(NewDelegationFormPanel.this.lstDelegationNature);
                        if (NewDelegationFormPanel.this.isEditMode || NewDelegationFormPanel.this.isRenewMode
                                || NewDelegationFormPanel.this.isTempMode) {
                            if (NewDelegationFormPanel.this.delegationModel.getDelegationNature() != null) {
                                NewDelegationFormPanel.this.lstDelegationNature.add(NewDelegationFormPanel.this.delegationModel.getDelegationNature());
                                NewDelegationFormPanel.this.cbNature.setValue(NewDelegationFormPanel.this.delegationModel.getDelegationNature());
                            }
                        }
                    }
                });

        this.clientCollaborateurService.getAllDelegantsByPerimeter(this.perimetreModel.getPerId(), this.entiteModel.getEntId(),
                new AsyncCallbackWithErrorResolution<List<CollaborateurModel>>() {

                    @Override
                    public void onSuccess(List<CollaborateurModel> arg0) {
                        NewDelegationFormPanel.this.lstDelegant.removeAll();
                        NewDelegationFormPanel.this.lstDelegant.add(arg0);
                        for (CollaborateurModel cm : NewDelegationFormPanel.this.lstDelegant.getModels()) {
                            cm.setFullname();
                        }
                        NewDelegationFormPanel.this.cbDelegant.setStore(NewDelegationFormPanel.this.lstDelegant);
                        if (NewDelegationFormPanel.this.isEditMode || NewDelegationFormPanel.this.isRenewMode
                                || NewDelegationFormPanel.this.isTempMode || NewDelegationFormPanel.this.isSubMode) { // R13
                            if (NewDelegationFormPanel.this.delegationModel.getDelegant() != null) {
                                NewDelegationFormPanel.this.cbDelegant.setValue(NewDelegationFormPanel.this.delegationModel.getDelegant());
                            }
                        }
                    }
                });

        this.clientCollaborateurService.getAllDelegatairesByPerimeter(this.perimetreModel.getPerId(), this.entiteModel.getEntId(),
                new AsyncCallbackWithErrorResolution<List<CollaborateurModel>>() {

                    @Override
                    public void onSuccess(List<CollaborateurModel> arg0) {
                        NewDelegationFormPanel.this.lstDelegataire.removeAll();
                        NewDelegationFormPanel.this.lstDelegataire.add(arg0);
                        for (CollaborateurModel cm : NewDelegationFormPanel.this.lstDelegataire.getModels()) {
                            cm.setFullname();
                        }

                        NewDelegationFormPanel.this.cbDelegataire.setStore(NewDelegationFormPanel.this.lstDelegataire);
                        if (NewDelegationFormPanel.this.isEditMode || NewDelegationFormPanel.this.isRenewMode
                                || NewDelegationFormPanel.this.isTempMode) {
                            if (NewDelegationFormPanel.this.delegationModel.getDelegataire() != null
                                    && !NewDelegationFormPanel.this.contains(NewDelegationFormPanel.this.lstDelegataire.getModels(),
                                            NewDelegationFormPanel.this.delegationModel.getDelegataire())) {
                                NewDelegationFormPanel.this.delegationModel.getDelegataire().setFullname();
                                NewDelegationFormPanel.this.lstDelegataire.add(NewDelegationFormPanel.this.delegationModel.getDelegataire());
                            }
                            if (NewDelegationFormPanel.this.delegationModel.getDelegataire() != null) {
                                NewDelegationFormPanel.this.cbDelegataire.setValue(NewDelegationFormPanel.this.delegationModel.getDelegataire());
                            }
                        }
                    }
                });
    }

    /**
     * Create top form in new delegation field set
     */
    private void initTopForm() {

        // setup top layout
        LayoutContainer lcTop = new LayoutContainer();
        lcTop.setSize(WIDTH, HEIGHT);
        lcTop.setLayout(new ColumnLayout());

        // setup sub layout for first field
        LayoutContainer lcSubTop = new LayoutContainer();
        lcSubTop.setStyleAttribute("paddingRight", "10px");
        FormLayout flSubTop = new FormLayout();
        flSubTop.setLabelWidth(120);
        flSubTop.setLabelAlign(LabelAlign.LEFT);
        lcSubTop.setLayout(flSubTop);

        this.lblType = new LabelField();
        this.lblType.setFieldLabel(this.messages.delegationformtypedelegation());
        lcSubTop.add(this.lblType, this.formData);

        lcTop.add(lcSubTop, new ColumnData(0.5));

        // setup sub layout for second field
        lcSubTop = new LayoutContainer();
        flSubTop = new FormLayout();
        flSubTop.setLabelAlign(LabelAlign.LEFT);
        flSubTop.setLabelWidth(50);
        lcSubTop.setLayout(flSubTop);

        this.cbStatus = new LabelField();
        this.cbStatus.setLabelSeparator(":");
        this.cbStatus.setFieldLabel(this.messages.delegationformstatus());
        lcSubTop.add(this.cbStatus, this.formData);
        lcTop.add(lcSubTop, new ColumnData(0.5));

        lcSubTop = new LayoutContainer();
        flSubTop = new FormLayout();
        flSubTop.setLabelWidth(120);
        flSubTop.setLabelAlign(LabelAlign.LEFT);
        // flSubTop.setLabelWidth(300);
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
        lcTop.add(lcSubTop, new ColumnData(.85));

        lcSubTop = new LayoutContainer();
        flSubTop = new FormLayout();
        flSubTop.setLabelWidth(120);
        flSubTop.setLabelAlign(LabelAlign.LEFT);
        lcSubTop.setLayout(flSubTop);

        // add to field set
        this.add(lcTop);

        this.addListeners();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void addListeners() {
        this.cbNature.addListener(Events.OnMouseOver, new Listener() {

            @Override
            public void handleEvent(BaseEvent be) {
                if (NewDelegationFormPanel.this.cbNature.getValue() != null) {
                    NewDelegationFormPanel.this.cbNature.setToolTip(NewDelegationFormPanel.this.cbNature.getValue().getName());
                } else {
                    NewDelegationFormPanel.this.cbNature.removeToolTip();
                }
            }
        });

        this.cbNature.addSelectionChangedListener(new SelectionChangedListener<DelegationNatureModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<DelegationNatureModel> se) {
                if (se.getSelectedItem() != null) {
                    if (NewDelegationFormPanel.this.isCirmad(se.getSelectedItem().getName())) {
                        NewDelegationFormPanel.this.txtZone.setVisible(true);
                        NewDelegationFormPanel.this.txtOperations.setVisible(true);
                    } else {
                        NewDelegationFormPanel.this.txtZone.setVisible(false);
                        NewDelegationFormPanel.this.txtOperations.setVisible(false);
                    }
                    if (!SessionServiceImpl.getInstance().getUserContext().getEntite().getEntId().equals(SharedConstant.ENTITE_ID_ETDE)) {
                        NewDelegationFormPanel.this.filterDelegants(se.getSelectedItem());
                    }
                    NewDelegationFormPanel.this.changeSocieteFieldSet(NewDelegationFormPanel.this.entiteJuridiqueModel);
                    NewDelegationFormPanel.this.changeDelegantFieldSet(NewDelegationFormPanel.this.cbDelegant.getValue());
                    NewDelegationFormPanel.this.changeDelegataireFieldSet(NewDelegationFormPanel.this.cbDelegataire.getValue());
                    NewDelegationFormPanel.this.changeChantierFieldSet(NewDelegationFormPanel.this.perimetreModel);
                    NewDelegationFormPanel.this.delegationModel.setDelegationNature(se.getSelectedItem());
                    NewDelegationFormPanel.this.applyRule2(NewDelegationFormPanel.this.delegationModel, NewDelegationFormPanel.this.delegationModel
                            .getPerimeter().getType(), NewDelegationFormPanel.this.delegationModel.getDelegationNature(), null);
                }
            }
        });
    }

    protected void filterDelegants(DelegationNatureModel selectedItem) {

        this.clientCollaborateurService.getDelegantsByNatureAndPerimetre(this.perimetreModel.getPerId(), this.perimetreModel.getType().getPtyId(),
                this.entiteModel.getEntId(), selectedItem.getId(), new AsyncCallbackWithErrorResolution<List<CollaborateurModel>>() {

                    @Override
                    public void onSuccess(List<CollaborateurModel> arg0) {
                        NewDelegationFormPanel.this.lstDelegant.removeAll();
                        NewDelegationFormPanel.this.lstDelegant.add(arg0);
                        if (NewDelegationFormPanel.this.delegationModel.getDelegant() != null
                                && !NewDelegationFormPanel.this.contains(NewDelegationFormPanel.this.lstDelegant.getModels(),
                                        NewDelegationFormPanel.this.delegationModel.getDelegant())) {
                            NewDelegationFormPanel.this.delegationModel.getDelegant().setFullname();
                            NewDelegationFormPanel.this.lstDelegant.add(NewDelegationFormPanel.this.delegationModel.getDelegant());
                            NewDelegationFormPanel.this.cbDelegant.setValue(NewDelegationFormPanel.this.delegationModel.getDelegant());
                        }
                        if (NewDelegationFormPanel.this.lstDelegant.getModels().isEmpty()) {
                            AppUtil.showWarning(NewDelegationFormPanel.this.messages.perimetrelistdelegantempty());
                        }
                    }
                });
    }

    private boolean contains(List<CollaborateurModel> arg0, CollaborateurModel c) {
        for (CollaborateurModel a : arg0) {
            if (a.getId().equals(c.getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Create information form in new delegation field set
     */
    private void initInformationForm() {

        // setup information layout
        LayoutContainer lcInformation = new LayoutContainer();
        lcInformation.setSize(WIDTH, HEIGHT);
        lcInformation.setLayout(new ColumnLayout());

        // setup left layout
        LayoutContainer lcLeft = new LayoutContainer();
        lcLeft.setStyleAttribute("paddingRight", "10px");
        FormLayout flLeft = new FormLayout();
        flLeft.setLabelAlign(LabelAlign.TOP);
        lcLeft.setLayout(flLeft);

        // delegation principale
        this.lblDelegationPrincipale = new LabelField();
        this.lblDelegationPrincipale.setFieldLabel(this.messages.delegationformprincipale());
        this.lblDelegationPrincipale.setStyleName("x-link-item");
        this.lblDelegationPrincipale.addListener(Events.OnClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                final ContentEvent contentEvent = new ContentEvent();
                final DelegationEvent event = new DelegationEvent();
                event.setDelegationId(Integer.parseInt(NewDelegationFormPanel.this.lblDelegationPrincipale.getRawValue()));
                event.setMode(DelegationEvent.MODE_IS_VIEW);
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_DETAIL_DELEGATION_FORM);
                contentEvent.setEvent(event);
                NewDelegationFormPanel.this.bus.fireEvent(contentEvent);
            }
        });
        lcLeft.add(this.lblDelegationPrincipale, this.formData);

        // description
        this.cpDescription = new ContentPanel();
        this.cpDescription.setHeaderVisible(false);
        this.cpDescription.setLayout(new FlowLayout());

        this.heDescription = new HtmlEditor();
        this.heDescription.setFieldLabel(this.messages.delegationformdescription());
        this.heDescription.setHeight(108);

        this.cpDescription.add(this.heDescription);
        this.cpDescription.setVisible(false);
        lcLeft.add(this.cpDescription, this.formData);

        // delegant
        this.cbDelegant = new ComboBox<CollaborateurModel>();
        this.cbDelegant.setFieldLabel(this.messages.delegationformdelegant());
        this.cbDelegant.setDisplayField(CollaborateurModel.COLLA_FULL_NAME);
        this.cbDelegant.setStore(this.lstDelegant);
        this.cbDelegant.setTriggerAction(TriggerAction.ALL);
        this.cbDelegant.setEditable(false);
        this.cbDelegant.setAllowBlank(false);
        lcLeft.add(this.cbDelegant, this.formData);

        // delegataire
        // tdo

        this.addDelegataireGrid(lcLeft);

        // end tdo

        this.cbDelegataire = new ComboBox<CollaborateurModel>();
        this.cbDelegataire.setId("abc");
        this.cbDelegataire.setFieldLabel(this.messages.delegationformdelegataire());
        this.cbDelegataire.setDisplayField(CollaborateurModel.COLLA_FULL_NAME);
        this.cbDelegataire.setStore(this.lstDelegataire);
        this.cbDelegataire.setTriggerAction(TriggerAction.ALL);
        this.cbDelegataire.setEditable(false);
        this.cbDelegataire.setAllowBlank(false);
        lcLeft.add(this.cbDelegataire, this.formData);

        // conjointe
        this.cbConjoin = new SimpleComboBox<String>();
        this.cbConjoin.setFieldLabel(this.messages.delegationformconjoin());
        this.cbConjoin.add(this.messages.delegationformoui());
        this.cbConjoin.add(this.messages.delegationformnon());
        this.cbConjoin.setSimpleValue(this.messages.delegationformoui());
        this.cbConjoin.setTriggerAction(TriggerAction.ALL);
        this.cbConjoin.setEditable(false);
        this.cbConjoin.setVisible(false);
        lcLeft.add(this.cbConjoin, this.formData);

        this.txtChamps = new TextArea();
        this.txtChamps.setId("txtChamps");
        this.txtChamps.setMaxLength(255);
        this.txtChamps.setFieldLabel(ClientConstant.EMPTY);
        lcLeft.add(this.txtChamps, this.formData);

        this.txtLimiteCommercial = new NumberField();
        this.txtLimiteCommercial.setFormat(NumberFormat.getFormat(CommonUtils.NUMBER_FORMAT));
        this.txtLimiteCommercial.setId("txtLimiteCommercial");
        this.txtLimiteCommercial.setFieldLabel(ClientConstant.EMPTY);
        lcLeft.add(this.txtLimiteCommercial, this.formData);

        this.txtLimiteAvenants = new NumberField();
        // txtLimiteAvenants.setPropertyEditor(new NumberPropertyEditor("###,###.##"));
        this.txtLimiteAvenants.setFormat(NumberFormat.getFormat(CommonUtils.NUMBER_FORMAT));
        this.txtLimiteAvenants.setId("txtLimiteAvenants");
        this.txtLimiteAvenants.setFieldLabel(ClientConstant.EMPTY);
        lcLeft.add(this.txtLimiteAvenants, this.formData);

        this.txtLimiteDevis = new NumberField();
        this.txtLimiteDevis.setFormat(NumberFormat.getFormat(CommonUtils.NUMBER_FORMAT));
        this.txtLimiteDevis.setId("txtLimiteDevis");
        this.txtLimiteDevis.setFieldLabel(ClientConstant.EMPTY);
        lcLeft.add(this.txtLimiteDevis, this.formData);

        this.txtLimiteEntreprise = new NumberField();
        this.txtLimiteEntreprise.setFormat(NumberFormat.getFormat(CommonUtils.NUMBER_FORMAT));
        this.txtLimiteEntreprise.setId("txtLimiteEntreprise");
        this.txtLimiteEntreprise.setFieldLabel(ClientConstant.EMPTY);
        lcLeft.add(this.txtLimiteEntreprise, this.formData);

        this.txtLimiteAssurance = new NumberField();
        this.txtLimiteAssurance.setFormat(NumberFormat.getFormat(CommonUtils.NUMBER_FORMAT));
        this.txtLimiteAssurance.setId("txtLimiteAssurance");
        this.txtLimiteAssurance.setFieldLabel(ClientConstant.EMPTY);
        lcLeft.add(this.txtLimiteAssurance, this.formData);

        this.txtZone = new TextField<String>();
        this.txtZone.setVisible(false);
        this.txtZone.setFieldLabel(this.messages.collaboraturezone());
        lcLeft.add(this.txtZone, this.formData);

        this.txtOperations = new TextField<String>();
        this.txtOperations.setVisible(false);
        this.txtOperations.setFieldLabel(this.messages.collaboratureoperations());
        lcLeft.add(this.txtOperations, this.formData);

        this.btnAjouter = new Button();
        this.btnAjouter.setText(this.messages.delegationformajoutersignee());
        // lcLeft.add(btnAjouter);

        // setup right layout
        LayoutContainer lcRight = new LayoutContainer();
        FormLayout flRight = new FormLayout();
        flRight.setLabelAlign(LabelAlign.TOP);
        lcRight.setLayout(flRight);

        // date debut
        this.dfDebut = new DateField();
        this.dfDebut.setId("dfDebut");
        this.dfDebut.setFieldLabel(ClientConstant.EMPTY);
        this.dfDebut.setEditable(true);
        this.dfDebut.setAllowBlank(false);
        // dfDebut.setValue(new Date()); //R12
        this.dfDebut.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        this.dfDebut.setFormatValue(true);
        lcRight.add(this.dfDebut, this.formData);

        // date fin
        this.dfFin = new DateField();
        this.dfFin.setId("dfFin");
        this.dfFin.setFieldLabel(ClientConstant.EMPTY);
        this.dfFin.setEditable(true);
        this.dfFin.setFormatValue(true);
        this.dfFin.setAllowBlank(false);
        this.dfFin.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        lcRight.add(this.dfFin, this.formData);

        // date signature
        this.dfSignature = new DateField();
        this.dfSignature.setId("dfSignature");
        this.dfSignature.setFormatValue(true);
        this.dfSignature.setFieldLabel(ClientConstant.EMPTY);
        this.dfSignature.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        lcRight.add(this.dfSignature, this.formData);

        this.txtSignature = new TextField<String>();
        this.txtSignature.setId("txtSignature");
        this.txtSignature.setFieldLabel(ClientConstant.EMPTY);
        lcRight.add(this.txtSignature, this.formData);

        this.lblDelegataireDateFormation = new DateField();
        this.lblDelegataireDateFormation.setId("lblDelegataireDateFormation");
        this.lblDelegataireDateFormation.setFieldLabel(ClientConstant.EMPTY);
        this.lblDelegataireDateFormation.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        lcRight.add(this.lblDelegataireDateFormation, this.formData);

        this.dfSignatureProposition = new DateField();
        this.dfSignatureProposition.setFormatValue(true);
        this.dfSignatureProposition.setId("dfSignatureProposition");
        this.dfSignatureProposition.setFieldLabel(ClientConstant.EMPTY);
        this.dfSignatureProposition.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        lcRight.add(this.dfSignatureProposition, this.formData);

        this.txtSignatureProposition = new TextField<String>();
        this.txtSignatureProposition.setId("txtSignatureProposition");
        this.txtSignatureProposition.setFieldLabel(ClientConstant.EMPTY);
        lcRight.add(this.txtSignatureProposition, this.formData);

        this.dfSignatureRecommandation = new DateField();
        this.dfSignatureRecommandation.setFormatValue(true);
        this.dfSignatureRecommandation.setId("dfSignatureRecommandation");
        this.dfSignatureRecommandation.setFieldLabel(ClientConstant.EMPTY);
        this.dfSignatureRecommandation.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        lcRight.add(this.dfSignatureRecommandation, this.formData);

        this.txtSignatureRecommandation = new TextField<String>();
        this.txtSignatureRecommandation.setId("txtSignatureRecommandation");
        this.txtSignatureRecommandation.setFieldLabel(ClientConstant.EMPTY);
        lcRight.add(this.txtSignatureRecommandation, this.formData);

        lcInformation.add(lcLeft, new ColumnData(.5));
        lcInformation.add(lcRight, new ColumnData(.5));

        this.add(lcInformation);
        this.documentView = this.createDocumentView();
        this.add(this.documentView, this.formData);

        this.cbDelegant.addSelectionChangedListener(new SelectionChangedListener<CollaborateurModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<CollaborateurModel> se) {
                if (se.getSelectedItem() != null) {
                    NewDelegationFormPanel.this.changeDelegantFieldSet(se.getSelectedItem());
                }
            }
        });

        this.cbDelegataire.addSelectionChangedListener(new SelectionChangedListener<CollaborateurModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<CollaborateurModel> se) {
                if (se.getSelectedItem() != null) {
                    NewDelegationFormPanel.this.changeDelegataireFieldSet(se.getSelectedItem());
                }
            }
        });
    }

    private void addDelegataireGrid(LayoutContainer lcLeft) {

        this.viewTitle = new Label(this.messages.delegationformdelegataire() + ":");
        lcLeft.add(this.viewTitle, this.formData);

        this.view = new CheckBoxListView<DelegationDelegataireModel>();
        this.view.setHeight(80);
        this.view.setStore(this.lstDelegataires);
        // view.setDisplayProperty(ExternControllerModel.EXC_LASTNAME);
        this.view.setTemplate(this.createTemplate());

        this.view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lcLeft.add(this.view, this.formData);
        // lcLeft.add(delegatairesGrid, formData);
    }

    private String createTemplate() {
        String spacing = GXT.isIE && !GXT.isStrict ? "0" : "3";
        String template = "<tpl for='.'><div class='x-view-item x-view-item-check'><table cellspacing='"
                + spacing
                + "' cellpadding=0><tr><td><input class='x-view-item-checkbox' type='checkbox' /></td><td><td>{colName}</td></tr></table></div></tpl>";
        return template;
    }

    private void displaySingleOrMultiDelegataire(Integer group, final DelegationModel del) {
        if (SessionServiceImpl.getInstance().getEntiteContext().getEntId().equals(SharedConstant.ENTITE_ID_ETDE)) {
            this.cbDelegataire.setVisible(true);
            this.cbDelegataire.setAllowBlank(false);
            this.viewTitle.setVisible(false);
            this.view.setVisible(false);
        } else {
            this.clientDelegationModelServiceAsync.getHasMutiDelegataire(group, new AsyncCallbackWithErrorResolution<Boolean>() {

                @Override
                public void onSuccess(Boolean arg0) {
                    NewDelegationFormPanel.this.cbDelegataire.setVisible(!arg0);
                    NewDelegationFormPanel.this.cbDelegataire.setAllowBlank(!arg0);
                    NewDelegationFormPanel.this.viewTitle.setVisible(!arg0);
                    NewDelegationFormPanel.this.view.setVisible(!arg0);
                    if (arg0) {
                        NewDelegationFormPanel.this.loadDelegataireInfo(del);
                    }
                }
            });
        }
    }

    public void loadDelegataireInfo(DelegationModel del) {
        this.clientDelegationService.findDelegataires(del.getId(), this.perimetreModel.getPerId(), this.entiteModel.getEntId(),
                new AsyncCallbackWithErrorResolution<List<DelegationDelegataireModel>>() {

                    @Override
                    public void onSuccess(List<DelegationDelegataireModel> arg0) {
                        NewDelegationFormPanel.this.lstDelegataires.removeAll();
                        if (arg0 != null) {
                            NewDelegationFormPanel.this.lstDelegataires.add(arg0);
                        }

                        // moi 27 Dec tdo - chua save delegataire
                        // @TODO : save delegationdelegataire to table in db + display in grid + genernate info of delegataire list in document
                        for (int i = 0; i < NewDelegationFormPanel.this.lstDelegataires.getCount(); i++) {
                            if (NewDelegationFormPanel.this.lstDelegataires.getAt(i).getDelId() != null
                                    && NewDelegationFormPanel.this.lstDelegataires.getAt(i).getDelId() > 0) {
                                NewDelegationFormPanel.this.view.setChecked(NewDelegationFormPanel.this.lstDelegataires.getAt(i), true);
                            }

                        }
                    }
                });
    }

    private ContentPanel createDocumentView() {
        this.createCommonDocUIs();

        // setup column model
        this.documentColumnModel = new ColumnModel(this.configs);

        // Content panel with header
        ContentPanel cp = new ContentPanel();
        cp.setBodyBorder(true);
        cp.setHeaderVisible(false);
        cp.setButtonAlign(HorizontalAlignment.CENTER);
        cp.setLayout(new FormLayout());
        cp.setHeight(120);
        cp.setWidth(680);
        cp.setStyleAttribute("paddingTop", "10px");

        this.documentGrid = new Grid<DocumentMdlModel>(new ListStore<DocumentMdlModel>(), this.documentColumnModel);
        this.documentGrid.setStyleAttribute("borderTop", "none");
        this.documentGrid.setHeight(100);
        this.documentGrid.setAutoExpandColumn("documentMdl.name");
        this.documentGrid.setBorders(false);
        this.documentGrid.setStripeRows(true);
        this.documentGrid.setColumnLines(true);
        this.documentGrid.setColumnReordering(true);

        this.documentGrid.getAriaSupport().setLabelledBy(cp.getId() + "-label");
        cp.add(this.documentGrid);

        return cp;
    }

    /**
     * init button
     */
    private void initButtons() {

        // add button event listener for cancel
        this.btnAnnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                AppUtil.removeInEditMode();
                NewDelegationFormPanel.this.resetForm();
                NewDelegationFormPanel.this.documentGrid.getStore().removeAll();
                ContentEvent contentEvent = new ContentEvent();
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GRID_DELEGATION_PANEL);
                NewDelegationFormPanel.this.bus.fireEvent(contentEvent);
            }
        });

        // add button event listener for create
        this.btnModifier.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if ((NewDelegationFormPanel.this.isEditMode) && (NewDelegationFormPanel.this.checkValidation())) {
                    NewDelegationFormPanel.this.applyDataToDelegation();

                    NewDelegationFormPanel.this.clientDelegationService.update(NewDelegationFormPanel.this.delegationModel,
                            new AsyncCallbackWithErrorResolution<Boolean>() {

                                @Override
                                public void onSuccess(Boolean arg0) {
                                    NewDelegationFormPanel.this.resetForm();
                                    NewDelegationFormPanel.this.documentGrid.getStore().removeAll();
                                    ContentEvent contentEvent = new ContentEvent();
                                    contentEvent.setModel(NewDelegationFormPanel.this.delegationModel);
                                    contentEvent.setReload(true);
                                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GRID_DELEGATION_PANEL);
                                    NewDelegationFormPanel.this.bus.fireEvent(contentEvent);
                                    AppUtil.removeInEditMode();
                                }

                                @Override
                                public void onFailure(Throwable caught) {
                                    String details = caught.getMessage();
                                    if (caught instanceof DelegationException) {
                                        details = ExceptionMessageHandler.getErrorMessage(((DelegationException) caught).getCode());
                                    }
                                    NewDelegationFormPanel.this.showErrorLabel(true, details);
                                    NewDelegationFormPanel.this.scrollToTop();
                                }
                            });
                } else if (NewDelegationFormPanel.this.checkValidation()) {
                    NewDelegationFormPanel.this.applyDataToDelegation();

                    if (NewDelegationFormPanel.this.isRenewMode) {
                        NewDelegationFormPanel.this.clientDelegationService.insertRenew(NewDelegationFormPanel.this.delegationModel,
                                new AsyncCallbackWithErrorResolution<DelegationModel>() {

                                    @Override
                                    public void onSuccess(DelegationModel arg0) {
                                        NewDelegationFormPanel.this.delegationModel = arg0;
                                        NewDelegationFormPanel.this.resetForm();
                                        NewDelegationFormPanel.this.documentGrid.getStore().removeAll();
                                        ContentEvent contentEvent = new ContentEvent();
                                        contentEvent.setReload(true);
                                        contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GRID_DELEGATION_PANEL);
                                        NewDelegationFormPanel.this.bus.fireEvent(contentEvent);
                                        AppUtil.removeInEditMode();
                                    }

                                    @Override
                                    public void onFailure(Throwable caught) {
                                        String details = caught.getMessage();
                                        if (caught instanceof DelegationException) {
                                            details = ExceptionMessageHandler.getErrorMessage(((DelegationException) caught).getCode());
                                        }
                                        NewDelegationFormPanel.this.showErrorLabel(true, details);
                                        NewDelegationFormPanel.this.scrollToTop();
                                    }
                                });
                    } else {
                        NewDelegationFormPanel.this.clientDelegationService.insert(NewDelegationFormPanel.this.delegationModel,
                                new AsyncCallbackWithErrorResolution<DelegationModel>() {

                                    @Override
                                    public void onSuccess(DelegationModel arg0) {
                                        NewDelegationFormPanel.this.delegationModel = arg0;
                                        NewDelegationFormPanel.this.resetForm();
                                        NewDelegationFormPanel.this.documentGrid.getStore().removeAll();
                                        ContentEvent contentEvent = new ContentEvent();
                                        contentEvent.setReload(true);
                                        contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GRID_DELEGATION_PANEL);
                                        NewDelegationFormPanel.this.bus.fireEvent(contentEvent);
                                        AppUtil.removeInEditMode();
                                    }

                                    @Override
                                    public void onFailure(Throwable caught) {
                                        String details = caught.getMessage();
                                        if (caught instanceof DelegationException) {
                                            details = ExceptionMessageHandler.getErrorMessage(((DelegationException) caught).getCode());
                                        }
                                        NewDelegationFormPanel.this.showErrorLabel(true, details);
                                        NewDelegationFormPanel.this.scrollToTop();
                                    }
                                });
                    }
                }
            }
        });
    }

    private void scrollToTop() {
        this.setVScrollPosition(0);
    }

    /**
     * disable fields
     */
    private void enabledFields(boolean disabled) {
        for (Field<?> field : this.getFields()) {
            field.setEnabled(disabled);
        }
        this.documentGrid.setEnabled(true);
    }

    /**
     * check validate form
     */
    private boolean checkValidation() {
        boolean valid = true;
        if (!SharedConstant.ENTITE_ID_BYEFE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
            for (Field<?> f : this.getFields()) {
                if ((f.isVisible()) && (!f.isValid(false))) {
                    valid = false;
                    this.delegantFieldSet.expand();
                    break;
                }
            }
            if (this.view.isVisible()) {
                boolean notChecked = this.view.getChecked() == null || this.view.getChecked().isEmpty();
                if (notChecked) {
                    this.showErrorLabel(true, "Le champ délégataire est obligatoire");
                    valid = false;
                }
            }
        }

        if (valid == false) {
            this.scrollToTop();
        }

        return valid;
    }

    /**
     * reset data in form
     */
    private void resetForm() {
        this.reset();
        for (Field<?> field : this.getFields()) {
            field.setVisible(true);
        }
        this.isEditMode = false;
        this.isRenewMode = false;
        this.isTempMode = false;
        resetFieldSets(this.delegantFieldSet, this.delegataireFieldSet, this.societeFieldSet, this.chantierFieldSet);
        this.invisibleExtFields();
        this.showErrorLabel(false, "");
    }

    /**
     * invisible all extension fields
     */
    private void invisibleExtFields() {
        this.dfDebut.setVisible(false);
        this.dfFin.setVisible(false);
        this.txtChamps.setVisible(false);
        this.dfSignature.setVisible(false);
        this.lblDelegataireDateFormation.setVisible(false);
        this.txtSignature.setVisible(false);
        this.dfSignatureProposition.setVisible(false);
        this.txtSignatureProposition.setVisible(false);
        this.dfSignatureRecommandation.setVisible(false);
        this.txtSignatureRecommandation.setVisible(false);
        this.txtLimiteCommercial.setVisible(false);
        this.txtLimiteAvenants.setVisible(false);
        this.txtLimiteDevis.setVisible(false);
        this.txtLimiteEntreprise.setVisible(false);
        this.txtLimiteAssurance.setVisible(false);
    }

    /**
     * apply data to delegation
     */
    private void applyDataToDelegation() {
        this.delegationModel.setEntite(this.entiteModel);

        // delegation nature
        if (this.cbNature.getValue() != null) {
            this.delegationModel.setDelegationNature(this.cbNature.getValue());
        }

        // start date
        if (this.dfDebut.isVisible()) {
            this.delegationModel.setStartDate(this.dfDebut.getValue());
        }

        // end date
        if (this.dfFin.isVisible()) {
            this.delegationModel.setEndDate(this.dfFin.getValue());
        }

        // delegant
        if (this.cbDelegant.getValue() != null) {
            CollaborateurModel delegant = this.cbDelegant.getValue();
            this.delegationModel.setDelegant(delegant);
            this.delegationModel.setDelegantFirstname(delegant.getFirstname());
            this.delegationModel.setDelegantLastname(delegant.getLastname());

            this.setMoreDelegantInformation(delegant);
        }

        // set conjointe
        if (this.cbConjoin.isVisible() && null != this.cbConjoin.getValue()) {
            this.delegationModel.setDelegationConjointe(this.messages.commonOui().equals(this.cbConjoin.getSelectedText()) ? 1 : 0);
        }

        // set entite juridique
        if (this.entiteJuridiqueModel != null) {
            this.delegationModel.setEntiteJuridique(this.entiteJuridiqueModel);
        }

        // signature proposition date
        if (this.dfSignatureProposition.isVisible()) {
            this.delegationModel.setDate1(this.dfSignatureProposition.getValue());
        }

        // signature proposition date
        if (this.txtSignatureProposition.isVisible()) {
            this.delegationModel.setPlace1(this.txtSignatureProposition.getValue());
        }

        // signature delegation date
        if (this.dfSignature.isVisible()) {
            this.delegationModel.setDate2(this.dfSignature.getValue());
        }

        // signature delegation date
        if (this.txtSignature.isVisible()) {
            this.delegationModel.setPlace2(this.txtSignature.getValue());
        }

        // signature recommender date
        if (this.dfSignatureRecommandation.isVisible()) {
            this.delegationModel.setDate3(this.dfSignatureRecommandation.getValue());
        }

        // signature recommender date
        if (this.txtSignatureRecommandation.isVisible()) {
            this.delegationModel.setPlace3(this.txtSignatureRecommandation.getValue());
        }

        // Limite Commercial
        if (this.txtLimiteCommercial.isVisible() && this.txtLimiteCommercial.getValue() != null) {
            this.delegationModel.setAmount1(this.txtLimiteCommercial.getValue().floatValue());
        }

        // Limite Avenants
        if (this.txtLimiteAvenants.isVisible() && this.txtLimiteAvenants.getValue() != null) {
            this.delegationModel.setAmount2(this.txtLimiteAvenants.getValue().floatValue());
        }

        // Limite Devis
        if (this.txtLimiteDevis.isVisible() && this.txtLimiteDevis.getValue() != null) {
            this.delegationModel.setAmount3(this.txtLimiteDevis.getValue().floatValue());
        }

        // Limite Entreprise
        if (this.txtLimiteEntreprise.isVisible() && this.txtLimiteEntreprise.getValue() != null) {
            this.delegationModel.setAmount4(this.txtLimiteEntreprise.getValue().floatValue());
        }

        // Limite Assurance
        if (this.txtLimiteAssurance.isVisible() && this.txtLimiteAssurance.getValue() != null) {
            this.delegationModel.setAmount5(this.txtLimiteAssurance.getValue().floatValue());
        }

        // Champs
        if (this.txtChamps.isVisible()) {
            this.delegationModel.setComment1(this.txtChamps.getValue());
        }

        // // Description
        if (this.heDescription.getValue() != null) {
            this.delegationModel.setDescription(this.heDescription.getValue());
        }

        if (this.txtZone.getValue() != null) {
            this.delegationModel.setZone(this.txtZone.getValue());
        }
        if (this.txtOperations.getValue() != null) {
            this.delegationModel.setOperations(this.txtOperations.getValue());
        }

        if (this.societeFieldSet != null && this.societeFieldSet.isVisible()) {
            if (this.societeFieldSet.getLblSocieteNom().isVisible()) {
                this.delegationModel.setEtjName(this.societeFieldSet.getLblSocieteNom().getText());
            }
            if (this.societeFieldSet.getLblSocieteStatusJuridique().isVisible()) {
                this.delegationModel.setEtjStatut(this.societeFieldSet.getLblSocieteStatusJuridique().getText());
            }
            if (this.societeFieldSet.getLblSocieteCapital().isVisible()) {
                this.delegationModel.setEtjCapital(this.societeFieldSet.getLblSocieteCapital().getText());
            }
            if (this.societeFieldSet.getLblSocieteAdresse().isVisible()) {
                this.delegationModel.setEtjAddress(this.societeFieldSet.getLblSocieteAdresse().getText());
            }
            if (this.societeFieldSet.getLblSocieteSiret().isVisible()) {
                this.delegationModel.setEtjRegistrationId(this.societeFieldSet.getLblSocieteSiret().getText());
            }
            if (this.societeFieldSet.getLblSocieteVille().isVisible()) {
                this.delegationModel.setEtjRegistrationAddress(this.societeFieldSet.getLblSocieteVille().getText());
            }
        }

        if (this.chantierFieldSet != null && this.chantierFieldSet.isVisible()) {
            if (this.chantierFieldSet.getLblChantierNom().isVisible()) {
                this.delegationModel.setPerChantierName(this.chantierFieldSet.getLblChantierNom().getText());
            }
            if (this.chantierFieldSet.getLblChantierVille().isVisible()) {
                this.delegationModel.setPerChantierCity(this.chantierFieldSet.getLblChantierVille().getText());
            }
            if (this.chantierFieldSet.getLblChantierNumeroProjet().isVisible()) {
                this.delegationModel.setPerChantierID(this.chantierFieldSet.getLblChantierNumeroProjet().getText());
            }
            if (this.chantierFieldSet.getLblChantierDateTravaux().isVisible()) {
                this.delegationModel.setPerChantierStartDate((this.chantierFieldSet.getLblChantierDateTravaux().getText() != null && !""
                        .equalsIgnoreCase(this.chantierFieldSet.getLblChantierDateTravaux().getText())) ? DateTimeFormat.getFormat(
                        ClientConstant.DATE_FORMAT).parse(this.chantierFieldSet.getLblChantierDateTravaux().getText()) : null);
            }
            if (this.chantierFieldSet.getLblChantierDatePrevisionnelle().isVisible()) {
                this.delegationModel.setPerChantierPlannedEndDate((this.chantierFieldSet.getLblChantierDatePrevisionnelle().getText() != null && !""
                        .equals(this.chantierFieldSet.getLblChantierDatePrevisionnelle().getText())) ? DateTimeFormat.getFormat(
                        ClientConstant.DATE_FORMAT).parse(this.chantierFieldSet.getLblChantierDatePrevisionnelle().getText()) : null);
            }
            if (this.chantierFieldSet.getLblChantierDateDefinitive().isVisible()) {
                this.delegationModel.setPerChantierEndDate((this.chantierFieldSet.getLblChantierDateDefinitive().getText() != null && !""
                        .equals(this.chantierFieldSet.getLblChantierDateDefinitive().getText())) ? DateTimeFormat.getFormat(
                        ClientConstant.DATE_FORMAT).parse(this.chantierFieldSet.getLblChantierDateDefinitive().getText()) : null);
            }
        }

        if (this.delegataireFieldSet != null && this.delegataireFieldSet.isVisible()) {
            this.delegationModel.setDelegataireNiveauHierarchique(this.delegataireFieldSet.getLblDelegataireQualite().getText());
            this.delegationModel.setDelegataireStatut(this.delegataireFieldSet.getLblDelegataireQualite().getText());
            if (this.delegataireFieldSet.getLblDelegataireDateNaissance().isVisible()) {
                this.delegationModel.setDelegataireDateNaissance((this.delegataireFieldSet.getLblDelegataireDateNaissance().getText() != null && !""
                        .equals(this.delegataireFieldSet.getLblDelegataireDateNaissance().getText())) ? DateTimeFormat.getFormat(
                        ClientConstant.DATE_FORMAT).parse(this.delegataireFieldSet.getLblDelegataireDateNaissance().getText()) : null);
            }
            if (this.delegataireFieldSet.getLblDelegataireLieuNaissance().isVisible()) {
                this.delegationModel.setDelegataireLieuNaissance(this.delegataireFieldSet.getLblDelegataireLieuNaissance().getText());
            }
            if (this.delegataireFieldSet.getLblDelegataireNationalite().isVisible()) {
                this.delegationModel.setDelegataireNationalite(this.delegataireFieldSet.getLblDelegataireNationalite().getText());
            }
            if (this.delegataireFieldSet.getLblDelegataireAdresse() != null && this.delegataireFieldSet.getLblDelegataireAdresse().getText() != null) {
                this.delegationModel.setDelegataireAddress(this.delegataireFieldSet.getLblDelegataireAdresse().getText().replaceAll("</br>", ", "));
            }

            if (this.delegataireFieldSet.getLblDelegataireQualite() != null) {
                this.delegationModel.setDelegataireQualite(this.delegataireFieldSet.getLblDelegataireQualite().getText());
            }
        }

        if (this.delegantFieldSet != null && this.delegantFieldSet.isVisible()) {
            this.delegationModel.setDelegantTitle(this.delegantFieldSet.getTitleOrQualite());
            if (this.delegantFieldSet.getLblDelegantQualite() != null) {
                this.delegationModel.setDelegantQualite(this.delegantFieldSet.getLblDelegantQualite().getText());
            }

            if (this.delegantFieldSet.getLblDelegantStatut() != null) {
                this.delegationModel.setDelegantStatut(this.delegantFieldSet.getLblDelegantStatut().getText());
            }
        }

        if (!SharedConstant.ENTITE_ID_BYEFE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
            if (this.view.isVisible()) {
                List<DelegationDelegataireModel> lst = new ArrayList<DelegationDelegataireModel>();
                for (DelegationDelegataireModel mdl : this.view.getChecked()) {
                    lst.add(mdl);
                }
                CollaborateurModel c = new CollaborateurModel();
                c.setId(lst.get(0).getColId());
                this.delegationModel.setDelegataire(c);
                this.delegationModel.setLstDelegataires(lst);
            } else {
                // delegataire
                if (this.cbDelegataire.getValue() != null) {
                    CollaborateurModel delegataire = this.cbDelegataire.getValue();

                    this.delegationModel.setDelegataire(delegataire);
                    this.delegationModel.setDelegataireFirstname(delegataire.getFirstname());
                    this.delegationModel.setDelegataireLastname(delegataire.getLastname());
                }
            }
        }
    }

    private void setMoreDelegantInformation(CollaborateurModel delegant) {
        if (!SharedConstant.ENTITE_ID_ETDE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
            String group = delegant.getType() == null ? "" : delegant.getType().getGroup() == null ? "" : delegant.getType().getGroup().getName();
            if (ClientConstant.COLLABORATEUR_TYPE_MANDATAIRE_SOCIAL.equals(group)) {
                this.delegationModel.setDelegantDateConseil(delegant.getDateConseil());
                this.delegationModel.setDelegantStatutConseil(delegant.getStatutConseil());
                this.delegationModel.setDelegantDateEffet(delegant.getDateEffet());
            } else {
                this.delegationModel.setDateDelegation(delegant.getDateDelegation());
                CollaborateurModel mandataire = delegant.getDelegant();
                if (mandataire != null) {
                    this.delegationModel.setDelegantQualite1(mandataire.getQualiteDelegant());
                    this.delegationModel.setDelegantNom1(mandataire.getLastname());
                    this.delegationModel.setDelegantPrenom1(mandataire.getFirstname());
                }
            }
        }
    }

    private void applyRule2(final DelegationModel delegationModel, PerimetreTypeModel perimetre, DelegationNatureModel nature, Integer collaborateType) {
        this.invisibleExtFields();
        // TODO Hard code apply Language
        LanguageModel lang = new LanguageModel();
        lang.setId(1);
        this.documentGrid.getStore().removeAll();
        this.clientDelegationModelServiceAsync.getGroup(lang, perimetre, nature, collaborateType, new AsyncCallbackWithErrorResolution<Integer>() {

            @Override
            public void onSuccess(Integer arg0) {
                if (arg0 != 0) {
                    NewDelegationFormPanel.this.displaySingleOrMultiDelegataire(arg0, delegationModel);
                    NewDelegationFormPanel.this.clientFieldRuleService.getRulesByDemGroup(arg0,
                            new AsyncCallbackWithErrorResolution<List<FieldRuleModel>>() {

                                @Override
                                public void onSuccess(List<FieldRuleModel> arg0) {
                                    NewDelegationFormPanel.this.processFieldRules(delegationModel, arg0);
                                }
                            });

                    NewDelegationFormPanel.this.changeDocumentTable(arg0);
                } else {
                    final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

                        @Override
                        public void handleEvent(MessageBoxEvent ce) {
                            if (NewDelegationFormPanel.this.isEditMode || NewDelegationFormPanel.this.isRenewMode) {
                                NewDelegationFormPanel.this.cbNature.setValue(NewDelegationFormPanel.this.originalNature);
                                NewDelegationFormPanel.this.cbNature.select(NewDelegationFormPanel.this.originalNature);
                            } else {
                                NewDelegationFormPanel.this.cbNature.setValue(null);
                            }
                        }
                    };

                    MessageBox.alert(NewDelegationFormPanel.this.messages.commonalert(),
                            NewDelegationFormPanel.this.messages.delegationformmsgnaturenotavailable(), l);
                }
            }
        });
    }

    @Override
    protected void processSpecificFields(final DelegationModel delegationModel) {
        this.cbNature.setValue(this.cbNature.getValue());
        this.cbDelegant.setValue(this.cbDelegant.getValue());
        this.cbDelegataire.setValue(this.cbDelegataire.getValue());

        if (this.isEditMode == false && this.isRenewMode == false) {
            this.dfDebut.setValue(new Date());
        }

        this.clientDelegationTypeService.getTemporaryType(new AsyncCallbackWithErrorResolution<DelegationTypeModel>() {

            @Override
            public void onSuccess(DelegationTypeModel arg0) {
                if (arg0 != null && arg0.getId().intValue() == delegationModel.getDelegationType().getId().intValue()) {
                    // show and hide field depend on Temporary Type
                    NewDelegationFormPanel.this.enabledFields(false);
                    NewDelegationFormPanel.this.cbDelegant.setEnabled(false);

                    // show delegataire
                    NewDelegationFormPanel.this.cbDelegataire.setVisible(true);
                    NewDelegationFormPanel.this.cbDelegataire.setEnabled(true);
                    // show date debut
                    NewDelegationFormPanel.this.dfDebut.setVisible(true);
                    NewDelegationFormPanel.this.dfDebut.setEnabled(true);
                    // show date de fin
                    NewDelegationFormPanel.this.dfFin.setVisible(true);
                    NewDelegationFormPanel.this.dfFin.setEnabled(true);
                    NewDelegationFormPanel.this.dfFin.setAllowBlank(false);
                    NewDelegationFormPanel.this.lblDelegationPrincipale.setEnabled(true);
                    NewDelegationFormPanel.this.delegantFieldSet.setEnabled(true);
                    NewDelegationFormPanel.this.delegataireFieldSet.setEnabled(true);
                    NewDelegationFormPanel.this.societeFieldSet.setEnabled(true);
                    NewDelegationFormPanel.this.chantierFieldSet.setEnabled(true);
                    NewDelegationFormPanel.this.delegantFieldSet.getTitre().setEnabled(false);
                }
            };
        });
    }

    /**
     * apply data to dataset
     */
    private void changeSocieteFieldSet(EntiteJuridiqueModel entiteJuridiqueModel) {
        if (this.cbNature.getValue() != null && entiteJuridiqueModel != null) {
            this.societeFieldSet.applyInformation(entiteJuridiqueModel);
            this.societeFieldSet.setVisible(true);
            this.societeFieldSet.collapse();
        }
    }

    /**
     * apply data to dataset
     */
    private void changeChantierFieldSet(PerimetreModel perimetreModel) {
        if (this.cbNature.getValue() != null && perimetreModel != null && perimetreModel.getType() != null
                && CommonUtils.isChantierType(perimetreModel.getType().getName())) {
            this.chantierFieldSet.applyInformation(perimetreModel);
            this.chantierFieldSet.setVisible(true);
            this.chantierFieldSet.collapse();
        }
    }

    /**
     * apply data to dataset
     */
    private void changeDelegantFieldSet(CollaborateurModel collaborateurModel) {
        if (this.cbNature.getValue() != null && collaborateurModel != null) {
            this.delegantFieldSet.applyInformation(this.delegationModel, collaborateurModel);
            this.delegantFieldSet.setVisible(true);
            this.delegantFieldSet.collapse();
        }
    }

    /**
     * apply data to dataset
     */
    private void changeDelegataireFieldSet(CollaborateurModel collaborateurModel) {
        if ((this.cbNature.getValue() != null) && (collaborateurModel != null)) {
            this.delegataireFieldSet.applyInformation(collaborateurModel);
            this.delegataireFieldSet.setVisible(true);
            this.delegataireFieldSet.collapse();
        }
    }

    private void changeDocumentTable(Integer group) {
        this.clientDemDomServiceAsync.getAllDemDomsByDemGroup(group, new AsyncCallbackWithErrorResolution<List<DemDomModel>>() {

            @Override
            public void onSuccess(List<DemDomModel> arg0) {
                NewDelegationFormPanel.this.documentGrid.getStore().removeAll();
                for (DemDomModel demDom : arg0) {
                    NewDelegationFormPanel.this.documentGrid.getStore().add(demDom.getDocumentMdl());
                }
            }
        });
    }
}
