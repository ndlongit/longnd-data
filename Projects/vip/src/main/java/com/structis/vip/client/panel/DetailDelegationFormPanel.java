package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ComponentManager;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.dialog.UploadDocumentDialog;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationEvent;
import com.structis.vip.client.event.DelegationEventHandler;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.service.ClientDelegationDocumentServiceAsync;
import com.structis.vip.client.service.ClientDelegationModelServiceAsync;
import com.structis.vip.client.service.ClientDemDomServiceAsync;
import com.structis.vip.client.service.ClientFieldRuleServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.client.util.NameValuePair;
import com.structis.vip.client.util.ReportUtil;
import com.structis.vip.shared.SharedConstant;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.DelegationDocumentModel;
import com.structis.vip.shared.model.DelegationModel;
import com.structis.vip.shared.model.DelegationNatureModel;
import com.structis.vip.shared.model.DemDomModel;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.DomDelModel;
import com.structis.vip.shared.model.EntiteJuridiqueModel;
import com.structis.vip.shared.model.FieldRuleModel;
import com.structis.vip.shared.model.LanguageModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTypeModel;

/**
 * Implement new delegation field set class
 */
public class DetailDelegationFormPanel extends CommonDelegationPanel {

    private LabelField lblType;
    private LabelField cbDemandeur;
    private LabelField cbStatus;
    private LabelField cbNature;
    private FieldSet fsDescription;
    private Label lblDescription;
    private LabelField lblDelegationPrincipale;
    private LabelField cbDelegant;
    private LabelField cbDelegataire;
    private LabelField cbConjoin;
    private LabelField dfDebut;
    private LabelField dfFin;
    private LabelField dfSignatureProposition;
    private LabelField txtSignatureProposition;
    private LabelField dfSignature;
    private LabelField lblDelegataireDateFormation;
    private LabelField txtSignature;
    private LabelField dfSignatureRecommandation;
    private LabelField txtSignatureRecommandation;
    private LabelField txtLimiteCommercial;
    private LabelField txtLimiteAvenants;
    private LabelField txtLimiteDevis;
    private LabelField txtLimiteEntreprise;
    private LabelField txtLimiteAssurance;
    private LabelField txtChamps;
    private ColumnModel columnModel;
    private LabelField txtZone;
    private LabelField txtOperations;

    private Label lblErrorMessage;
    private LayoutContainer errorLayout;

    private Button btnAnnuler;
    private Button btnModifier;
    private Button btnAnnulerSignedDocument;

    private ClientDemDomServiceAsync clientDemDomServiceAsync = ClientDemDomServiceAsync.Util.getInstance();
    private ClientDelegationModelServiceAsync clientDelegationModelServiceAsync = ClientDelegationModelServiceAsync.Util.getInstance();
    private ClientFieldRuleServiceAsync clientFieldRuleService = ClientFieldRuleServiceAsync.Util.getInstance();
    private ClientDelegationDocumentServiceAsync clientDelegationDocumentServiceAsync = ClientDelegationDocumentServiceAsync.Util.getInstance();

    private Grid<DocumentMdlModel> documentGrid;
    private ContentPanel cpDocumentGrid;

    private Integer group;
    private Button btnValider;

    private ListStore<DelegationDocumentModel> delegationDocumentModels;
    private Grid<DelegationDocumentModel> delegationDocumentGrid;
    private ContentPanel cpDelegationDocumentGrid;
    private ColumnModel columnModelOther;
    private Button btnAddDocument;

    private boolean isChanged = false;

    public DetailDelegationFormPanel(SimpleEventBus bus) {
        super(bus);

        // setup field set
        this.setHeading(this.messages.delegationformheading());
        this.setAction(GWT.getHostPageBaseURL() + ".uploadSignedDocumentServiceServlet");
        this.setMethod(Method.POST);
        this.setEncoding(Encoding.MULTIPART);

        // add back link
        this.addBackLink();
        LayoutContainer lcLine0 = new LayoutContainer();
        lcLine0.setSize(WIDTH, HEIGHT);
        lcLine0.setLayout(new ColumnLayout());
        lcLine0.add(new HTML("<hr width='680px'/>"));
        this.add(lcLine0);

        this.addErrorLabel();

        // add view in field set
        this.initTopForm();
        // // add the seperator line
        // // setup top layout
        LayoutContainer lcLine = new LayoutContainer();
        lcLine.setSize(WIDTH, HEIGHT);
        lcLine.setLayout(new ColumnLayout());
        lcLine.add(new HTML("<hr width='680px'/>"));
        this.add(lcLine);
        // add view for information form
        this.initInformationForm();
        // add field set
        this.initFieldSets();
        // add buttons
        this.initButtons();

        bus.addHandler(DelegationEvent.getType(), new DelegationEventHandler() {

            @Override
            public void onLoadAction(DelegationEvent event) {
                int mode = event.getMode();
                if (mode == DelegationEvent.MODE_IS_UPDATED_DOCUMENT) {
                    if (DetailDelegationFormPanel.this.delegationModel != null) {
                        DetailDelegationFormPanel.this.changeOtherDocumentTable(DetailDelegationFormPanel.this.delegationModel.getId());
                        return;
                    }
                }
                DetailDelegationFormPanel.this.resetForm();

                DetailDelegationFormPanel.this.columnModel.setHidden(2, true);

                DetailDelegationFormPanel.this.btnValider.setVisible(false);
                DetailDelegationFormPanel.this.btnAnnulerSignedDocument.setVisible(false);
                DetailDelegationFormPanel.this.btnAnnuler.setVisible(true);

                if ((event.getIsModification() == null) || (!event.getIsModification())) {
                    DetailDelegationFormPanel.this.btnModifier.setVisible(false);
                } else {
                    DetailDelegationFormPanel.this.btnModifier.setVisible(true);
                }

                DetailDelegationFormPanel.this.columnModelOther.setHidden(2, true);
                DetailDelegationFormPanel.this.btnAddDocument.setVisible(false);

                if (mode == DelegationEvent.MODE_IS_VIEW || mode == DelegationEvent.MODE_IS_ADD_DOCUMENT_SIGNEE
                        || mode == DelegationEvent.MODE_IS_ADD_DOCUMENT || mode == DelegationEvent.MODE_IS_PRINT_DOCUMENT) {
                    DetailDelegationFormPanel.this.clientDelegationService.findById(event.getDelegationId(), new AsyncCallback<DelegationModel>() {

                        @Override
                        public void onSuccess(DelegationModel arg0) {
                            DetailDelegationFormPanel.this.delegationModel = arg0;

                            DetailDelegationFormPanel.this.lblType.setText(DetailDelegationFormPanel.this.delegationModel.getDelegationType()
                                    .getName());

                            if (DetailDelegationFormPanel.this.delegationModel.getDelegationType().getId() == ClientConstant.DELEGATION_TYPE_IS_PRINCIPAL) {
                                DetailDelegationFormPanel.this.lblDelegationPrincipale.setVisible(false);
                            } else {
                                if (arg0.getParent() != null) {
                                    DetailDelegationFormPanel.this.lblDelegationPrincipale.setText(arg0.getParent().getDelegationNature().getName());
                                    DetailDelegationFormPanel.this.lblDelegationPrincipale.setRawValue(arg0.getParent().getId().toString());
                                    DetailDelegationFormPanel.this.lblDelegationPrincipale.setVisible(true);
                                }
                                DetailDelegationFormPanel.this.lblDescription.setText(DetailDelegationFormPanel.this.delegationModel.getDescription());
                                if (DetailDelegationFormPanel.this.delegationModel.getZone() != null) {
                                    DetailDelegationFormPanel.this.txtZone.setValue(DetailDelegationFormPanel.this.delegationModel.getZone());
                                }
                                if (DetailDelegationFormPanel.this.delegationModel.getOperations() != null) {
                                    DetailDelegationFormPanel.this.txtOperations.setValue(DetailDelegationFormPanel.this.delegationModel
                                            .getOperations());
                                }

                                if (DetailDelegationFormPanel.this.delegationModel.getDelegationType().getId().intValue() == ClientConstant.DELEGATION_TYPE_IS_SOUS_DELEGATION) {
                                    DetailDelegationFormPanel.this.dfDebut.setVisible(true);
                                    DetailDelegationFormPanel.this.dfDebut.setEnabled(true);

                                    // show date de fin
                                    DetailDelegationFormPanel.this.dfFin.setVisible(true);
                                    DetailDelegationFormPanel.this.dfFin.setEnabled(true);
                                    DetailDelegationFormPanel.this.lblDelegationPrincipale.setEnabled(true);
                                }
                            }

                            DetailDelegationFormPanel.this.cbNature.setText(DetailDelegationFormPanel.this.delegationModel.getDelegationNature()
                                    .getName());
                            DetailDelegationFormPanel.this.cbDemandeur.setText((DetailDelegationFormPanel.this.delegationModel.getDemandeur() != null
                                    && DetailDelegationFormPanel.this.delegationModel.getDemandeur().getFullname() != null && !""
                                    .equals(DetailDelegationFormPanel.this.delegationModel.getDemandeur().getFullname())) ? DetailDelegationFormPanel.this.delegationModel
                                    .getDemandeur().getFullname() : "");

                            if (DetailDelegationFormPanel.this.delegationModel.getDelegationStatus().getId().intValue() != ClientConstant.DELEGATION_STATUS_IS_P) {
                                DetailDelegationFormPanel.this.btnModifier.setVisible(false);
                            }

                            DetailDelegationFormPanel.this.cbStatus.setText(DetailDelegationFormPanel.this.delegationModel.getDelegationStatus()
                                    .getName());

                            DetailDelegationFormPanel.this.dfDebut.setText((DetailDelegationFormPanel.this.delegationModel.getStartDate() != null) ? DateTimeFormat
                                    .getFormat(ClientConstant.DATE_FORMAT).format(DetailDelegationFormPanel.this.delegationModel.getStartDate()) : "");
                            DetailDelegationFormPanel.this.dfFin.setText((DetailDelegationFormPanel.this.delegationModel.getEndDate() != null) ? DateTimeFormat
                                    .getFormat(ClientConstant.DATE_FORMAT).format(DetailDelegationFormPanel.this.delegationModel.getEndDate()) : "");

                            DetailDelegationFormPanel.this.cbDelegant.setText(DetailDelegationFormPanel.this.delegationModel.getDelegant()
                                    .getFullname());
                            if (SessionServiceImpl.getInstance().getEntiteContext().getEntId().equals(SharedConstant.ENTITE_ID_ETDE)) {
                                DetailDelegationFormPanel.this.cbDelegataire.setText(DetailDelegationFormPanel.this.delegationModel.getDelegataire()
                                        .getFullname());
                            } else {
                                DetailDelegationFormPanel.this.clientDelegationService.getDelegataires(
                                        DetailDelegationFormPanel.this.delegationModel.getId(), new AsyncCallback<String>() {

                                            @Override
                                            public void onSuccess(String arg0) {

                                                arg0 = arg0.replace("|", "<br>");
                                                // html.setHTML(SafeHtmlUtils.fromString(arg0) );
                                                DetailDelegationFormPanel.this.cbDelegataire.setText(arg0);
                                            }

                                            @Override
                                            public void onFailure(Throwable arg0) {
                                            }
                                        });
                            }

                            if (DetailDelegationFormPanel.this.delegationModel.getDelegationConjointe() != null) {
                                if (DetailDelegationFormPanel.this.delegationModel.getDelegationConjointe() == 1) {
                                    DetailDelegationFormPanel.this.cbConjoin.setText(DetailDelegationFormPanel.this.messages.commonOui());
                                } else if (DetailDelegationFormPanel.this.delegationModel.getDelegationConjointe() == 0) {
                                    DetailDelegationFormPanel.this.cbConjoin.setText(DetailDelegationFormPanel.this.messages.commonNon());
                                }
                            }
                            // if (ConstantClient.PERIMETER_TYPE_NAME_IS_CHANTIER.equals(delegationModel.getPerimeter().getType().getName())) {
                            if (CommonUtils.isChantierType(DetailDelegationFormPanel.this.delegationModel.getPerimeter().getType().getName())) {
                                DetailDelegationFormPanel.this.cbConjoin.setVisible(true);
                            } else {
                                DetailDelegationFormPanel.this.cbConjoin.setVisible(false);
                            }

                            DetailDelegationFormPanel.this.dfSignature.setText(DetailDelegationFormPanel.this.delegationModel.getDate2() != null ? DateTimeFormat
                                    .getFormat(ClientConstant.DATE_FORMAT).format(DetailDelegationFormPanel.this.delegationModel.getDate2()) : "");
                            DetailDelegationFormPanel.this.txtSignature.setText(DetailDelegationFormPanel.this.delegationModel.getPlace2() != null ? DetailDelegationFormPanel.this.delegationModel
                                    .getPlace2().toString() : "");

                            DetailDelegationFormPanel.this.lblDelegataireDateFormation.setText("");

                            DetailDelegationFormPanel.this.dfSignatureProposition
                                    .setText(DetailDelegationFormPanel.this.delegationModel.getDate1() != null ? DateTimeFormat.getFormat(
                                            ClientConstant.DATE_FORMAT).format(DetailDelegationFormPanel.this.delegationModel.getDate1()) : "");
                            DetailDelegationFormPanel.this.txtSignatureProposition
                                    .setText(DetailDelegationFormPanel.this.delegationModel.getPlace1() != null ? DetailDelegationFormPanel.this.delegationModel
                                            .getPlace1().toString() : "");

                            DetailDelegationFormPanel.this.dfSignatureRecommandation.setText(DetailDelegationFormPanel.this.delegationModel
                                    .getDate3() != null ? DateTimeFormat.getFormat(ClientConstant.DATE_FORMAT).format(
                                    DetailDelegationFormPanel.this.delegationModel.getDate3()) : "");
                            DetailDelegationFormPanel.this.txtSignatureRecommandation.setText(DetailDelegationFormPanel.this.delegationModel
                                    .getPlace3() != null ? DetailDelegationFormPanel.this.delegationModel.getPlace3().toString() : "");

                            DetailDelegationFormPanel.this.txtLimiteCommercial.setText(CommonUtils
                                    .formatNumber(DetailDelegationFormPanel.this.delegationModel.getAmount1()));
                            DetailDelegationFormPanel.this.txtLimiteAvenants.setText(CommonUtils
                                    .formatNumber(DetailDelegationFormPanel.this.delegationModel.getAmount2()));
                            DetailDelegationFormPanel.this.txtLimiteDevis.setText(CommonUtils
                                    .formatNumber(DetailDelegationFormPanel.this.delegationModel.getAmount3()));
                            DetailDelegationFormPanel.this.txtLimiteEntreprise.setText(CommonUtils
                                    .formatNumber(DetailDelegationFormPanel.this.delegationModel.getAmount4()));
                            DetailDelegationFormPanel.this.txtLimiteAssurance.setText(CommonUtils
                                    .formatNumber(DetailDelegationFormPanel.this.delegationModel.getAmount5()));
                            DetailDelegationFormPanel.this.txtChamps.setText(DetailDelegationFormPanel.this.delegationModel.getComment1() != null ? DetailDelegationFormPanel.this.delegationModel
                                    .getComment1().toString() : "");
                            DetailDelegationFormPanel.this.txtZone.setText(DetailDelegationFormPanel.this.delegationModel.getZone());
                            DetailDelegationFormPanel.this.txtOperations.setText(DetailDelegationFormPanel.this.delegationModel.getOperations());

                            DetailDelegationFormPanel.this.entiteModel = DetailDelegationFormPanel.this.delegationModel.getPerimeter().getEntite();

                            DetailDelegationFormPanel.this.changeSocieteFieldSet(DetailDelegationFormPanel.this.delegationModel.getEntiteJuridique());
                            DetailDelegationFormPanel.this.changeDelegantFieldSet(DetailDelegationFormPanel.this.delegationModel.getDelegant());
                            DetailDelegationFormPanel.this.changeDelegataireFieldSet(DetailDelegationFormPanel.this.delegationModel.getDelegataire());
                            DetailDelegationFormPanel.this.changeChantierFieldSet(DetailDelegationFormPanel.this.delegationModel.getPerimeter());

                            // applyRule(delegationModel.getDelegationNature().getId());
                            DetailDelegationFormPanel.this.changeOtherDocumentTable(DetailDelegationFormPanel.this.delegationModel.getId());

                            DetailDelegationFormPanel.this.applyRule2(DetailDelegationFormPanel.this.delegationModel,
                                    DetailDelegationFormPanel.this.delegationModel.getPerimeter().getType(),
                                    DetailDelegationFormPanel.this.delegationModel.getDelegationNature(), null);
                        }

                        @Override
                        public void onFailure(Throwable arg0) {
                        }
                    });
                }

                if (mode == DelegationEvent.MODE_IS_PRINT_DOCUMENT) {
                    DetailDelegationFormPanel.this.btnModifier.setVisible(false);
                }

                if (mode == DelegationEvent.MODE_IS_ADD_DOCUMENT_SIGNEE) {
                    DetailDelegationFormPanel.this.columnModel.setHidden(2, false);
                    DetailDelegationFormPanel.this.btnValider.setVisible(true);
                    DetailDelegationFormPanel.this.btnModifier.setVisible(false);
                    DetailDelegationFormPanel.this.btnAnnulerSignedDocument.setVisible(true);
                    DetailDelegationFormPanel.this.btnAnnuler.setVisible(false);
                }

                if (mode == DelegationEvent.MODE_IS_ADD_DOCUMENT) {
                    DetailDelegationFormPanel.this.columnModelOther.setHidden(2, false);
                    DetailDelegationFormPanel.this.btnAddDocument.setVisible(true);
                    DetailDelegationFormPanel.this.btnModifier.setVisible(false);
                    DetailDelegationFormPanel.this.btnAnnuler.setVisible(false);
                }

                // @Lan (2012/03/01): Fixing bug #96
                if (event.getEntiteModel() != null && SharedConstant.ENTITE_ID_ETDE.equals(event.getEntiteModel().getEntId())) {
                    DetailDelegationFormPanel.this.cbDemandeur.setVisible(false);
                } else {
                    // cbDemandeur.setVisible(true);
                    DetailDelegationFormPanel.this.cbDemandeur.setVisible(false);
                }
            }
        });

        this.addListener(Events.BeforeSubmit, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                DetailDelegationFormPanel.this.showErrorLabel(false, "");

                List<DocumentMdlModel> models = DetailDelegationFormPanel.this.documentGrid.getStore().getModels();
                for (DocumentMdlModel documentMdlModel : models) {
                    String signedFilename = documentMdlModel.getSignedFilename();
                    if (signedFilename != null && !"".equals(signedFilename)) {
                        FileUploadField component = (FileUploadField) ComponentManager.get().get(signedFilename);
                        if (component != null) {
                            String fileName = component.getValue();
                            int lastDot = fileName.lastIndexOf(".");
                            String extFile = fileName.substring(lastDot, fileName.length()).toLowerCase();
                            if (!ClientConstant.PDF_EXTENSION_FILE.equals(extFile)) {
                                DetailDelegationFormPanel.this.documentGrid.getSelectionModel().select(documentMdlModel, false);
                                DetailDelegationFormPanel.this.showErrorLabel(true, "Délégation signée doit être un fichier pdf");
                                be.setCancelled(true);
                                break;
                            }
                        }
                    }
                }
            }
        });

        this.addListener(Events.Submit, new Listener<FormEvent>() {

            @Override
            public void handleEvent(FormEvent be) {
                List<DocumentMdlModel> models = DetailDelegationFormPanel.this.documentGrid.getStore().getModels();
                if (!models.isEmpty()) {
                    List<DomDelModel> insertModels = new ArrayList<DomDelModel>();
                    for (DocumentMdlModel documentMdlModel : models) {
                        if (documentMdlModel.getSignedFilename() != null && !"".equals(documentMdlModel.getSignedFilename())) {
                            DomDelModel model = new DomDelModel();
                            model.setDelegation(DetailDelegationFormPanel.this.delegationModel);
                            model.setDocumentMdl(documentMdlModel);
                            model.setSignedDate(new Date());
                            model.setSignedFilename(documentMdlModel.getSignedFilename());

                            insertModels.add(model);
                        }
                    }
                    if (!insertModels.isEmpty()) {
                        DetailDelegationFormPanel.this.clientDocumentMdlService.createNewDocument(insertModels, new AsyncCallback<Boolean>() {

                            @Override
                            public void onSuccess(Boolean arg0) {
                                DetailDelegationFormPanel.this.changeDocumentTable(DetailDelegationFormPanel.this.group);
                                DetailDelegationFormPanel.this.cpDocumentGrid.repaint();
                                DetailDelegationFormPanel.this.updateStatusAuto();
                            }

                            @Override
                            public void onFailure(Throwable arg0) {
                                Info.display(DetailDelegationFormPanel.this.messages.commonerror(),
                                        DetailDelegationFormPanel.this.messages.commonServererror());
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);
        GWT.log(this.getClass().getName() + ":onRender");
    }

    private void updateStatusAuto() {
        this.clientDocumentMdlService.getDocumentsByDelegation(this.delegationModel.getId(), new AsyncCallback<List<DomDelModel>>() {

            @Override
            public void onSuccess(List<DomDelModel> arg0) {
                if (arg0.size() == DetailDelegationFormPanel.this.documentGrid.getStore().getCount()) {
                    DetailDelegationFormPanel.this.clientDelegationService.updateStatusAuto(DetailDelegationFormPanel.this.delegationModel,
                            new AsyncCallback<Boolean>() {

                                @Override
                                public void onSuccess(Boolean arg0) {
                                    DetailDelegationFormPanel.this.clientDelegationService.findById(
                                            DetailDelegationFormPanel.this.delegationModel.getId(), new AsyncCallback<DelegationModel>() {

                                                @Override
                                                public void onSuccess(DelegationModel arg0) {
                                                    DetailDelegationFormPanel.this.delegationModel = arg0;
                                                    DetailDelegationFormPanel.this.cbStatus.setText(DetailDelegationFormPanel.this.delegationModel
                                                            .getDelegationStatus().getName());
                                                    // true to refesh when back to list delegation
                                                    DetailDelegationFormPanel.this.isChanged = true;
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
            }

            @Override
            public void onFailure(Throwable arg0) {
            }
        });
    }

    public void addBackLink() {
        // LayoutContainer backLink = new LayoutContainer();
        // backLink.setSize(WIDTH, HEIGHT);
        com.google.gwt.user.client.ui.Label lblBack = new com.google.gwt.user.client.ui.Label(this.messages.commonRetoursalaLstedesdelegations());
        lblBack.setStyleName("x-link-item");
        // backLink.setStyleAttribute("margin-bottom", "20px");
        // backLink.add(lblBack);
        //
        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInEditMode()) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GRID_DELEGATION_PANEL);
                    if (DetailDelegationFormPanel.this.isChanged) {
                        contentEvent.setReload(true);
                    }
                    DetailDelegationFormPanel.this.resetForm();
                    DetailDelegationFormPanel.this.documentGrid.getStore().removeAll();
                    DetailDelegationFormPanel.this.bus.fireEvent(contentEvent);
                }
            }
        });
        // HorizontalPanel hpButton = new HorizontalPanel();
        // hpButton.setHorizontalAlign(HorizontalAlignment.CENTER);
        // hpButton.setTableWidth("150");
        //
        // btnAnnuler = new Button(messages.delegationformannuler());
        // btnModifier = new Button(messages.delegationformmodifier());
        //
        // hpButton.add(btnAnnuler);
        // hpButton.add(btnModifier);
        //
        // this.add(hpButton, new FlowData(10, 0, 0, 548));
        // this.add(backLink);
        HorizontalPanel hpButton = new HorizontalPanel();
        hpButton.setHorizontalAlign(HorizontalAlignment.LEFT);
        hpButton.setTableWidth("" + WIDTH);
        hpButton.add(lblBack);
        LayoutContainer lc = new LayoutContainer();
        lc.setWidth(200);
        hpButton.add(lc);
        // hpButton.add(lb2);
        // add button
        this.btnAnnuler = new Button(this.messages.delegationformannuler());
        hpButton.add(this.btnAnnuler);

        // remove button
        this.btnModifier = new Button(this.messages.delegationformmodifier());
        hpButton.add(this.btnModifier);

        this.add(hpButton, new FlowData(10, 0, 0, 0));
    }

    private void addErrorLabel() {
        this.errorLayout = new LayoutContainer();
        this.errorLayout.setHeight(30);

        this.lblErrorMessage = new Label("Just Test");
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

        this.cbNature = new LabelField();
        this.cbNature.setFieldLabel(this.messages.nature());
        lcSubTop.add(this.cbNature, this.formData);

        lcTop.add(lcSubTop, new ColumnData(0.45));

        // cbIsDelegable = new LabelField();
        // cbIsDelegable.setFieldLabel(messages.delegationsousdelegable());
        // lcSubTop.add(cbIsDelegable, formData);

        lcTop.add(lcSubTop, new ColumnData(0.45));

        // setup sub layout for second field
        lcSubTop = new LayoutContainer();
        flSubTop = new FormLayout();
        flSubTop.setLabelAlign(LabelAlign.LEFT);
        // flSubTop.setLabelWidth(300);
        lcSubTop.setLayout(flSubTop);

        this.cbDemandeur = new LabelField();
        this.cbDemandeur.setFieldLabel(this.messages.delegationformdemandeur());
        lcSubTop.add(this.cbDemandeur, this.formData);

        this.cbStatus = new LabelField();
        this.cbStatus.setFieldLabel(this.messages.delegationformstatus());
        lcSubTop.add(this.cbStatus, this.formData);
        lcTop.add(lcSubTop, new ColumnData(0.55));

        // add to field set
        this.add(lcTop);

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
        flLeft.setLabelAlign(LabelAlign.LEFT);
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
                event.setDelegationId(Integer.parseInt(DetailDelegationFormPanel.this.lblDelegationPrincipale.getRawValue()));
                event.setMode(DelegationEvent.MODE_IS_VIEW);
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_DETAIL_DELEGATION_FORM);
                contentEvent.setEvent(event);
                DetailDelegationFormPanel.this.bus.fireEvent(contentEvent);
            }
        });

        lcLeft.add(this.lblDelegationPrincipale, this.formData);

        // description
        this.fsDescription = new FieldSet();
        this.fsDescription.setHeading(this.messages.delegationformdescription());
        this.lblDescription = new Label();
        this.fsDescription.add(this.lblDescription);
        this.fsDescription.setVisible(false);
        lcLeft.add(this.fsDescription, this.formData);

        // delegant
        this.cbDelegant = new LabelField();
        this.cbDelegant.setFieldLabel(this.messages.delegationformdelegant());
        lcLeft.add(this.cbDelegant, this.formData);

        // delegataire
        this.cbDelegataire = new LabelField();
        this.cbDelegataire.setFieldLabel(this.messages.delegationformdelegataire());
        lcLeft.add(this.cbDelegataire, this.formData);

        // conjointe
        this.cbConjoin = new LabelField();
        this.cbConjoin.setFieldLabel(this.messages.delegationformconjoin());
        this.cbConjoin.setVisible(false);
        lcLeft.add(this.cbConjoin, this.formData);

        this.txtChamps = new LabelField();
        this.txtChamps.setId("txtChamps");
        this.txtChamps.setFieldLabel(ClientConstant.EMPTY);
        lcLeft.add(this.txtChamps, this.formData);

        this.txtLimiteCommercial = new LabelField();
        this.txtLimiteCommercial.setId("txtLimiteCommercial");
        this.txtLimiteCommercial.setFieldLabel(ClientConstant.EMPTY);
        lcLeft.add(this.txtLimiteCommercial, this.formData);

        this.txtLimiteAvenants = new LabelField();
        this.txtLimiteAvenants.setId("txtLimiteAvenants");
        this.txtLimiteAvenants.setFieldLabel(ClientConstant.EMPTY);
        lcLeft.add(this.txtLimiteAvenants, this.formData);

        this.txtLimiteDevis = new LabelField();
        this.txtLimiteDevis.setId("txtLimiteDevis");
        this.txtLimiteDevis.setFieldLabel(ClientConstant.EMPTY);
        lcLeft.add(this.txtLimiteDevis, this.formData);

        this.txtLimiteEntreprise = new LabelField();
        this.txtLimiteEntreprise.setId("txtLimiteEntreprise");
        this.txtLimiteEntreprise.setFieldLabel(ClientConstant.EMPTY);
        lcLeft.add(this.txtLimiteEntreprise, this.formData);

        this.txtLimiteAssurance = new LabelField();
        this.txtLimiteAssurance.setId("txtLimiteAssurance");
        this.txtLimiteAssurance.setFieldLabel(ClientConstant.EMPTY);
        lcLeft.add(this.txtLimiteAssurance, this.formData);
        this.txtZone = new LabelField();
        this.txtZone.setVisible(false);
        this.txtZone.setFieldLabel(this.messages.collaboraturezone());
        lcLeft.add(this.txtZone, this.formData);

        this.txtOperations = new LabelField();
        this.txtOperations.setVisible(false);
        this.txtOperations.setFieldLabel(this.messages.collaboratureoperations());
        lcLeft.add(this.txtOperations, this.formData);

        // setup right layout
        LayoutContainer lcRight = new LayoutContainer();
        FormLayout flRight = new FormLayout();
        flRight.setLabelAlign(LabelAlign.LEFT);
        flRight.setLabelWidth(240);
        lcRight.setLayout(flRight);

        // date debut
        this.dfDebut = new LabelField();
        this.dfDebut.setId("dfDebut");
        this.dfDebut.setFieldLabel(ClientConstant.EMPTY);
        lcRight.add(this.dfDebut, this.formData);

        // date fin
        this.dfFin = new LabelField();
        this.dfFin.setId("dfFin");
        this.dfFin.setFieldLabel(ClientConstant.EMPTY);
        lcRight.add(this.dfFin, this.formData);

        // date signature
        this.dfSignature = new LabelField();
        this.dfSignature.setId("dfSignature");
        this.dfSignature.setFieldLabel(ClientConstant.EMPTY);
        lcRight.add(this.dfSignature, this.formData);

        this.txtSignature = new LabelField();
        this.txtSignature.setId("txtSignature");
        this.txtSignature.setFieldLabel(ClientConstant.EMPTY);
        lcRight.add(this.txtSignature, this.formData);

        this.lblDelegataireDateFormation = new LabelField();
        this.lblDelegataireDateFormation.setId("lblDelegataireDateFormation");
        this.lblDelegataireDateFormation.setFieldLabel(ClientConstant.EMPTY);
        lcRight.add(this.lblDelegataireDateFormation, this.formData);

        this.dfSignatureProposition = new LabelField();
        this.dfSignatureProposition.setId("dfSignatureProposition");
        this.dfSignatureProposition.setFieldLabel(ClientConstant.EMPTY);
        lcRight.add(this.dfSignatureProposition, this.formData);

        this.txtSignatureProposition = new LabelField();
        this.txtSignatureProposition.setId("txtSignatureProposition");
        this.txtSignatureProposition.setFieldLabel(ClientConstant.EMPTY);
        lcRight.add(this.txtSignatureProposition, this.formData);

        this.dfSignatureRecommandation = new LabelField();
        this.dfSignatureRecommandation.setId("dfSignatureRecommandation");
        this.dfSignatureRecommandation.setFieldLabel(ClientConstant.EMPTY);
        lcRight.add(this.dfSignatureRecommandation, this.formData);

        this.txtSignatureRecommandation = new LabelField();
        this.txtSignatureRecommandation.setId("txtSignatureRecommandation");
        this.txtSignatureRecommandation.setFieldLabel(ClientConstant.EMPTY);
        lcRight.add(this.txtSignatureRecommandation, this.formData);

        lcInformation.add(lcLeft, new ColumnData(.45));
        lcInformation.add(lcRight, new ColumnData(.55));

        this.add(lcInformation);
        this.add(this.createDocumentView(), this.formData);
        this.add(this.createOtherDocumentView(), this.formData);
    }

    /**
     * create other document view
     */
    private ContentPanel createOtherDocumentView() {
        // setup grid for document view
        List<ColumnConfig> otherConfigs = new ArrayList<ColumnConfig>();

        // Column documents
        ColumnConfig ccDescription = new ColumnConfig();
        ccDescription.setId(DelegationDocumentModel.ODD_DESCRIPTION);
        ccDescription.setHeader(this.messages.delegationdocumentdescription());
        ccDescription.setWidth(270);
        ccDescription.setRowHeader(true);
        ccDescription.setSortable(false);

        GridCellRenderer<DelegationDocumentModel> fileNameRender = new GridCellRenderer<DelegationDocumentModel>() {

            @Override
            public Object render(final DelegationDocumentModel model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config,
                    int rowIndex, int colIndex, ListStore<DelegationDocumentModel> store, Grid<DelegationDocumentModel> grid) {
                final com.google.gwt.user.client.ui.Label label = new com.google.gwt.user.client.ui.Label();
                label.setStyleName("x-link-item");
                label.setText(model.getFileName());
                label.setTitle(model.getFileName());

                label.addClickHandler(new ClickHandler() {

                    @Override
                    public void onClick(ClickEvent arg0) {
                        String reportUrl = GWT.getHostPageBaseURL() + ".printDelegationDocumentServiceServlet";
                        List<NameValuePair> values = new ArrayList<NameValuePair>();
                        values.add(new NameValuePair("fileName", model.getFileName()));
                        ReportUtil.showReport(reportUrl, values.toArray(new NameValuePair[0]));
                    }
                });

                return label;
            }
        };

        // Column documents
        ColumnConfig ccFileName = new ColumnConfig();
        ccFileName.setId(DelegationDocumentModel.ODD_FILENAME);
        ccFileName.setHeader(this.messages.delegationdocumentfile());
        ccFileName.setWidth(200);
        ccFileName.setRowHeader(true);
        ccFileName.setRenderer(fileNameRender);
        ccFileName.setSortable(false);

        GridCellRenderer<DelegationDocumentModel> actionRender = new GridCellRenderer<DelegationDocumentModel>() {

            @Override
            public Object render(final DelegationDocumentModel model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config,
                    int rowIndex, int colIndex, ListStore<DelegationDocumentModel> store, final Grid<DelegationDocumentModel> grid) {
                final Button btnDelete = new Button(DetailDelegationFormPanel.this.messages.commonSupprimer());
                btnDelete.addSelectionListener(new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        DetailDelegationFormPanel.this.clientDelegationDocumentServiceAsync.delete(model, new AsyncCallback<Boolean>() {

                            @Override
                            public void onSuccess(Boolean result) {
                                DelegationEvent event = new DelegationEvent();
                                event.setMode(DelegationEvent.MODE_IS_UPDATED_DOCUMENT);
                                DetailDelegationFormPanel.this.bus.fireEvent(event);
                                if (true == result) {
                                    Info.display(DetailDelegationFormPanel.this.messages.commoninfo(),
                                            DetailDelegationFormPanel.this.messages.delegationdocumentdeletemessage());
                                }
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                Info.display(DetailDelegationFormPanel.this.messages.commonerror(),
                                        DetailDelegationFormPanel.this.messages.delegationdocumentdeletefailed());
                            }
                        });
                    }
                });
                return btnDelete;
            }
        };

        ColumnConfig ccAction = new ColumnConfig("action", this.messages.delegationdocumentaction(), 200);
        ccAction.setRenderer(actionRender);

        otherConfigs.add(ccDescription);
        otherConfigs.add(ccFileName);
        otherConfigs.add(ccAction);

        // setup column model
        this.columnModelOther = new ColumnModel(otherConfigs);

        this.btnAddDocument = new Button(this.messages.delegationdocumentaddbutton());
        this.btnAddDocument.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                UploadDocumentDialog uploadDocumentDialog = new UploadDocumentDialog(DetailDelegationFormPanel.this.bus,
                        DetailDelegationFormPanel.this.delegationModel);
                uploadDocumentDialog.show();
            }
        });

        // Content panel with header
        this.cpDelegationDocumentGrid = new ContentPanel();
        this.cpDelegationDocumentGrid.setBodyBorder(true);
        this.cpDelegationDocumentGrid.setHeaderVisible(false);
        this.cpDelegationDocumentGrid.setButtonAlign(HorizontalAlignment.CENTER);
        this.cpDelegationDocumentGrid.setLayout(new FitLayout());
        this.cpDelegationDocumentGrid.setSize(680, 180);
        this.cpDelegationDocumentGrid.setButtonAlign(HorizontalAlignment.RIGHT);
        this.cpDelegationDocumentGrid.setStyleAttribute("paddingTop", "10px");
        this.cpDelegationDocumentGrid.setStyleAttribute("paddingBottom", "10px");

        // Grid
        this.delegationDocumentModels = new ListStore<DelegationDocumentModel>();

        this.delegationDocumentGrid = new Grid<DelegationDocumentModel>(this.delegationDocumentModels, this.columnModelOther);
        this.delegationDocumentGrid.setStyleAttribute("borderTop", "none");
        this.delegationDocumentGrid.setHeight(100);
        this.delegationDocumentGrid.setBorders(false);
        this.delegationDocumentGrid.setStripeRows(true);
        this.delegationDocumentGrid.setColumnLines(true);
        this.delegationDocumentGrid.setColumnReordering(true);

        this.delegationDocumentGrid.getAriaSupport().setLabelledBy(this.cpDelegationDocumentGrid.getHeader().getId() + "-label");
        this.cpDelegationDocumentGrid.add(this.delegationDocumentGrid);
        this.cpDelegationDocumentGrid.addButton(this.btnAddDocument);

        return this.cpDelegationDocumentGrid;
    }

    /**
     * create document view
     */
    private ContentPanel createDocumentView() {

        this.createCommonDocUIs();

        GridCellRenderer<DocumentMdlModel> actionRender = new GridCellRenderer<DocumentMdlModel>() {

            @Override
            public Object render(final DocumentMdlModel model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex,
                    int colIndex, ListStore<DocumentMdlModel> store, final Grid<DocumentMdlModel> grid) {
                ContentPanel panel = new ContentPanel();
                panel.setHeaderVisible(false);
                panel.setBodyBorder(false);

                final FileUploadField btnUpload = new FileUploadField();
                Date now = new Date();
                String uploadId = now.getTime() + CommonUtils.randomString(12) + ".pdf";
                btnUpload.setName(uploadId);
                btnUpload.setWidth(190);
                btnUpload.setItemId(uploadId);
                btnUpload.setId(uploadId);
                btnUpload.getMessages().setBrowseText(DetailDelegationFormPanel.this.messages.documentBrowseText());
                btnUpload.setStyleAttribute("paddingRight", "20px");
                btnUpload.addListener(Events.OnChange, new Listener<BaseEvent>() {

                    @Override
                    public void handleEvent(BaseEvent be) {
                        AppUtil.putInEditMode();
                        model.setSignedFilename(btnUpload.getName());
                    }
                });

                final Button btnDelete = new Button(DetailDelegationFormPanel.this.messages.commonSupprimer());
                btnDelete.setVisible(false);
                btnDelete.addSelectionListener(new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        DetailDelegationFormPanel.this.clientDocumentMdlService.getDocumentsByDelegation(
                                DetailDelegationFormPanel.this.delegationModel.getId(), new AsyncCallbackWithErrorResolution<List<DomDelModel>>() {

                                    @Override
                                    public void onSuccess(List<DomDelModel> arg0) {
                                        if (arg0.size() == 0) {
                                            return;
                                        }

                                        for (DomDelModel domDelModel : arg0) {
                                            if (domDelModel.getDocumentMdl().getId() == model.getId()) {
                                                DetailDelegationFormPanel.this.clientDocumentMdlService.deleteDocument(domDelModel,
                                                        new AsyncCallbackWithErrorResolution<Boolean>() {

                                                            @Override
                                                            public void onSuccess(Boolean arg0) {
                                                                Info.display(DetailDelegationFormPanel.this.messages.commoninfo(),
                                                                        "Supprimé avec succès.");
                                                                DetailDelegationFormPanel.this
                                                                        .changeDocumentTable(DetailDelegationFormPanel.this.group);
                                                                DetailDelegationFormPanel.this.cpDocumentGrid.repaint();
                                                                // true to refesh when back to list delegation
                                                                DetailDelegationFormPanel.this.isChanged = true;

                                                                DetailDelegationFormPanel.this.clientDelegationService.findById(
                                                                        DetailDelegationFormPanel.this.delegationModel.getId(),
                                                                        new AsyncCallbackWithErrorResolution<DelegationModel>() {

                                                                            @Override
                                                                            public void onSuccess(DelegationModel arg0) {
                                                                                DetailDelegationFormPanel.this.delegationModel = arg0;
                                                                                if (arg0 != null) {
                                                                                    DetailDelegationFormPanel.this.cbStatus.setText(arg0
                                                                                            .getDelegationStatus().getName());
                                                                                }
                                                                            }
                                                                        });

                                                            }
                                                        });
                                            }
                                        }
                                    }
                                });
                    }
                });

                panel.add(btnUpload);
                panel.add(btnDelete);

                DetailDelegationFormPanel.this.clientDocumentMdlService.getDocumentsByDelegation(
                        DetailDelegationFormPanel.this.delegationModel.getId(), new AsyncCallbackWithErrorResolution<List<DomDelModel>>() {

                            @Override
                            public void onSuccess(List<DomDelModel> arg0) {
                                if (arg0.size() == 0) {
                                    btnUpload.setVisible(true);
                                    btnDelete.setVisible(false);
                                } else {
                                    for (DomDelModel domDelModel : arg0) {
                                        if (domDelModel.getDocumentMdl().getId() == model.getId()) {
                                            btnUpload.setVisible(false);
                                            btnDelete.setVisible(true);
                                        }
                                    }
                                }
                            }
                        });

                return panel;
            }
        };

        ColumnConfig action = new ColumnConfig("action", "Action", 202);
        action.setRenderer(actionRender);
        this.configs.add(action);

        // setup column model
        this.columnModel = new ColumnModel(this.configs);

        this.btnValider = new Button(this.messages.commonValiderButton());
        this.btnValider.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DetailDelegationFormPanel.this.submit();
                AppUtil.removeInEditMode();
            }
        });

        this.btnAnnulerSignedDocument = new Button(this.messages.commonAnnulerButton());
        this.btnAnnulerSignedDocument.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DetailDelegationFormPanel.this.changeDocumentTable(DetailDelegationFormPanel.this.group);
                AppUtil.removeInEditMode();
            }
        });

        // Content panel with header
        this.cpDocumentGrid = new ContentPanel();
        this.cpDocumentGrid.setBodyBorder(true);
        this.cpDocumentGrid.setHeaderVisible(false);
        this.cpDocumentGrid.setButtonAlign(HorizontalAlignment.CENTER);
        this.cpDocumentGrid.setLayout(new FitLayout());
        this.cpDocumentGrid.setSize(680, 180);
        this.cpDocumentGrid.setButtonAlign(HorizontalAlignment.RIGHT);
        this.cpDocumentGrid.setStyleAttribute("paddingTop", "10px");
        this.cpDocumentGrid.setStyleAttribute("paddingBottom", "10px");

        this.documentGrid = new Grid<DocumentMdlModel>(new ListStore<DocumentMdlModel>(), this.columnModel);
        this.documentGrid.setStyleAttribute("borderTop", "none");
        this.documentGrid.setHeight(100);
        this.documentGrid.setBorders(false);
        this.documentGrid.setStripeRows(true);
        this.documentGrid.setColumnLines(true);
        this.documentGrid.setColumnReordering(true);

        this.documentGrid.getAriaSupport().setLabelledBy(this.cpDocumentGrid.getHeader().getId() + "-label");
        this.cpDocumentGrid.add(this.documentGrid);
        this.cpDocumentGrid.addButton(this.btnAnnulerSignedDocument);
        this.cpDocumentGrid.addButton(this.btnValider);

        return this.cpDocumentGrid;
    }

    /**
     * init button
     */
    private void initButtons() {

        this.btnAnnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DetailDelegationFormPanel.this.resetForm();
                DetailDelegationFormPanel.this.documentGrid.getStore().removeAll();
                ContentEvent contentEvent = new ContentEvent();
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GRID_DELEGATION_PANEL);
                DetailDelegationFormPanel.this.bus.fireEvent(contentEvent);
            }
        });

        // add button event listener for create
        this.btnModifier.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                DelegationEvent event = new DelegationEvent();
                event.setDelegationId(DetailDelegationFormPanel.this.delegationModel.getId());
                event.setMode(DelegationEvent.MODE_IS_EDIT);
                event.setEntiteModel(DetailDelegationFormPanel.this.entiteModel);
                DetailDelegationFormPanel.this.resetForm();
                DetailDelegationFormPanel.this.documentGrid.getStore().removeAll();

                ContentEvent contentEvent = new ContentEvent();
                contentEvent.setEvent(event);
                contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_NEW_DELEGATION_FORM);
                DetailDelegationFormPanel.this.bus.fireEvent(contentEvent);
            }
        });
    }

    /**
     * reset data in form
     */
    private void resetForm() {
        this.isChanged = false;
        this.reset();
        for (Field<?> field : this.getFields()) {
            field.setVisible(true);
        }
        resetFieldSets(this.delegantFieldSet, this.delegataireFieldSet, this.societeFieldSet, this.chantierFieldSet);
        this.invisibleExtFields();
    }

    /**
     * invisible all extension fields
     */
    private void invisibleExtFields() {
        this.dfDebut.setVisible(false);
        this.dfFin.setVisible(false);
        this.txtChamps.setVisible(false);
        this.dfSignature.setVisible(false);
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
        this.txtZone.setVisible(false);
        this.txtOperations.setVisible(false);
    }

    private void applyRule2(final DelegationModel delegationModel, PerimetreTypeModel perimetre, DelegationNatureModel nature, Integer collaborateType) {
        this.invisibleExtFields();
        // TODO Hard code apply Language
        LanguageModel lang = new LanguageModel();
        lang.setId(1);
        this.clientDelegationModelServiceAsync.getGroup(lang, perimetre, nature, collaborateType, new AsyncCallbackWithErrorResolution<Integer>() {

            @Override
            public void onSuccess(Integer arg0) {
                DetailDelegationFormPanel.this.group = arg0;
                if (arg0 != 0) {
                    DetailDelegationFormPanel.this.clientFieldRuleService.getRulesByDemGroup(arg0,
                            new AsyncCallbackWithErrorResolution<List<FieldRuleModel>>() {

                                @Override
                                public void onSuccess(List<FieldRuleModel> arg0) {
                                    DetailDelegationFormPanel.this.processFieldRules(delegationModel, arg0);
                                }
                            });
                    DetailDelegationFormPanel.this.changeDocumentTable(arg0);
                }
            }
        });
    }

    @Override
    protected void processSpecificFields(final DelegationModel delegationModel) {
        // cbNature.setValue(cbNature.getValue());
        this.cbDemandeur.setValue(this.cbDemandeur.getValue());
        this.cbDelegant.setValue(this.cbDelegant.getValue());
        this.cbDelegataire.setValue(this.cbDelegataire.getValue());

        if (delegationModel.getDelegationType().getId().intValue() == ClientConstant.DELEGATION_TYPE_IS_TEMPORAIRE) {
            this.dfDebut.setVisible(true);
            this.dfFin.setVisible(true);
        }
        if (this.isCirmad((String) this.cbNature.getValue())) {
            this.txtZone.setVisible(true);
            this.txtOperations.setVisible(true);
        } else {
            this.txtZone.setVisible(false);
            this.txtOperations.setVisible(false);
        }
    }

    /**
     * apply data to dataset
     */
    private void changeDelegantFieldSet(CollaborateurModel collaborateurModel) {
        if ((this.delegationModel != null) && (collaborateurModel != null)) {
            this.delegantFieldSet.applyInformation(this.delegationModel, collaborateurModel);
            this.delegantFieldSet.setVisible(true);
            this.delegantFieldSet.getTitre().setEnabled(false);
            this.delegantFieldSet.getLblDelegantQualite().setEnabled(false);
            this.delegantFieldSet.collapse();
        }
    }

    /**
     * apply data to dataset
     */
    private void changeDelegataireFieldSet(CollaborateurModel collaborateurModel) {
        if ((this.delegationModel != null) && (collaborateurModel != null)) {
            this.delegataireFieldSet.applyInformation(collaborateurModel);
            this.delegataireFieldSet.setVisible(true);
            this.delegataireFieldSet.collapse();
            // delegataireFieldSet.setShow();
        }
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
     * apply data to dataset //
     */
    private void changeChantierFieldSet(PerimetreModel perimetreModel) {

        // if (perimetreModel != null && perimetreModel.getType()!= null &&
        // ConstantClient.PERIMETER_TYPE_NAME_IS_CHANTIER.equals(perimetreModel.getType().getName())) {
        if (perimetreModel != null && perimetreModel.getType() != null && CommonUtils.isChantierType(perimetreModel.getType().getName())) {
            this.chantierFieldSet.applyInformation(perimetreModel);
            this.chantierFieldSet.setVisible(true);
            this.chantierFieldSet.collapse();
        }

    }

    private void changeDocumentTable(Integer group) {
        this.clientDemDomServiceAsync.getAllDemDomsByDemGroup(group, new AsyncCallbackWithErrorResolution<List<DemDomModel>>() {

            @Override
            public void onSuccess(List<DemDomModel> arg0) {
                DetailDelegationFormPanel.this.documentGrid.getStore().removeAll();
                for (DemDomModel demDom : arg0) {
                    DetailDelegationFormPanel.this.documentGrid.getStore().add(demDom.getDocumentMdl());
                }
            }
        });
    }

    private void changeOtherDocumentTable(Integer delegationId) {
        this.clientDelegationDocumentServiceAsync.getDelegationDocuments(delegationId,
                new AsyncCallbackWithErrorResolution<List<DelegationDocumentModel>>() {

                    @Override
                    public void onSuccess(List<DelegationDocumentModel> result) {
                        DetailDelegationFormPanel.this.delegationDocumentModels.removeAll();
                        DetailDelegationFormPanel.this.delegationDocumentModels.add(result);
                    }
                });
    }
}
