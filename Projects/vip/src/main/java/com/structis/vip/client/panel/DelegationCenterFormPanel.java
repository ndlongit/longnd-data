package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.DateTimePropertyEditor;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.event.DelegationFilterEvent;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.DelegationPagingEvent;
import com.structis.vip.client.event.DelegationPagingHandler;
import com.structis.vip.client.event.DelegationTreeEvent;
import com.structis.vip.client.event.DelegationTreeHandler;
import com.structis.vip.client.event.DeleteDelegationEvent;
import com.structis.vip.client.event.DeleteDelegationHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientCollaborateurServiceAsync;
import com.structis.vip.client.service.ClientDelegationNatureServiceAsync;
import com.structis.vip.client.service.ClientDelegationStatusServiceAsync;
import com.structis.vip.client.service.ClientDelegationTypeServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.widget.XComboBox;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.DelegationNatureModel;
import com.structis.vip.shared.model.DelegationStatusModel;
import com.structis.vip.shared.model.DelegationTypeModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.PerimetreTypeModel;

public class DelegationCenterFormPanel extends VerticalPanel {

    private static final int COMBOBOX_WIDTH = 275;
    private static final int LABEL_WIDTH = 120;
    private final Messages messages = GWT.create(Messages.class);

    SimpleEventBus bus;
    DelegationFilterEvent delegationFilterEvent;

    Label periodeLabel;
    Label auLabel;

    XComboBox<DelegationNatureModel> natureCombobox;
    XComboBox<DelegationStatusModel> statusCombobox;
    XComboBox<DelegationTypeModel> typeCombobox;
    XComboBox<CollaborateurModel> delegantCombobox;
    XComboBox<CollaborateurModel> delegataireCombobox;

    int pagingSize = ClientConstant.DEFAULT_PAGE_SIZE_50;

    CheckBox afficherCheck;
    CheckBox sepCheckBox;
    CheckBox delegationCheckBox;

    DateField fromDateField;
    DateField toDateField;

    Button buttonFiltrer;

    private ClientDelegationNatureServiceAsync natureService = ClientDelegationNatureServiceAsync.Util.getInstance();
    private ListStore<DelegationNatureModel> natures = new ListStore<DelegationNatureModel>();

    private ClientDelegationStatusServiceAsync statusService = ClientDelegationStatusServiceAsync.Util.getInstance();
    private ListStore<DelegationStatusModel> statuses = new ListStore<DelegationStatusModel>();

    private ClientDelegationTypeServiceAsync typeService = ClientDelegationTypeServiceAsync.Util.getInstance();
    private ListStore<DelegationTypeModel> types = new ListStore<DelegationTypeModel>();

    private ClientCollaborateurServiceAsync collaborateurService = ClientCollaborateurServiceAsync.Util.getInstance();
    private ListStore<CollaborateurModel> delegants = new ListStore<CollaborateurModel>();
    private ListStore<CollaborateurModel> delegataires = new ListStore<CollaborateurModel>();

    DelegationNatureModel natureAll;
    DelegationStatusModel statusAll;
    DelegationTypeModel typeAll;
    CollaborateurModel delegantAll;
    CollaborateurModel delegataireAll;

    List<DelegationNatureModel> natureAllSelection = new ArrayList<DelegationNatureModel>();
    List<DelegationStatusModel> statusAllSelection = new ArrayList<DelegationStatusModel>();
    List<DelegationTypeModel> typeAllSelection = new ArrayList<DelegationTypeModel>();
    List<CollaborateurModel> delegantAllSelection = new ArrayList<CollaborateurModel>();
    List<CollaborateurModel> delegataireAllSelection = new ArrayList<CollaborateurModel>();

    public DelegationCenterFormPanel(SimpleEventBus bus, DelegationFilterEvent delegationFilterEvent) {
        this.bus = bus;
        this.delegationFilterEvent = delegationFilterEvent;

        this.initData();

        this.initUI();

        this.addHandler();
    }

    private void initData() {
        // initialize nature list
        this.natures.removeAll();
        this.natureAll = new DelegationNatureModel();
        this.natureAll.setName(this.messages.commonTous());
        this.natureAll.setId(0);
        this.natures.add(this.natureAll);
        this.natureAllSelection.add(this.natureAll);

        // initialize status list
        this.statuses.removeAll();
        this.statusAll = new DelegationStatusModel();
        this.statusAll.setName(this.messages.commonTous());
        this.statusAll.setId(0);
        this.statuses.add(this.statusAll);
        this.statusAllSelection.add(this.statusAll);

        // initialize type list
        this.types.removeAll();
        this.typeAll = new DelegationTypeModel();
        this.typeAll.setName(this.messages.commonTous());
        this.typeAll.setId(0);
        this.types.add(this.typeAll);
        this.typeAllSelection.add(this.typeAll);

        // initialize delegant list
        this.delegants.removeAll();
        this.delegantAll = new CollaborateurModel();
        // TODO need to set default full name?
        // this.delegantAll.setFullname(this.messages.commonTous());
        this.delegantAll.setFullnameNoSeparater(this.messages.commonTous());
        this.delegantAll.setId(0);
        this.delegants.add(this.delegantAll);
        this.delegantAllSelection.add(this.delegantAll);

        // initialize delegataire list
        this.delegataires.removeAll();
        this.delegataireAll = new CollaborateurModel();
        // this.delegataireAll.setFullname(this.messages.commonTous());
        this.delegataireAll.setFullnameNoSeparater(this.messages.commonTous());
        this.delegataireAll.setId(0);
        this.delegataires.add(this.delegataireAll);
        this.delegataireAllSelection.add(this.delegataireAll);
    }

    private void initUI() {
        FormData formData = new FormData("100%");
        FormData formDataR = new FormData("100%");

        LayoutContainer main = new LayoutContainer();
        main.setLayout(new ColumnLayout());
        main.setStyleAttribute("padding", "5px");

        LayoutContainer left = new LayoutContainer();
        FormLayout layout = new FormLayout();
        layout.setLabelAlign(LabelAlign.RIGHT);
        layout.setLabelWidth(LABEL_WIDTH);
        left.setLayout(layout);
        left.setStyleAttribute("paddingRight", "5px");

        // nature combobox
        this.natureCombobox = new XComboBox<DelegationNatureModel>();
        this.natureCombobox.setWidth(COMBOBOX_WIDTH);
        this.natureCombobox.setEditable(false);
        this.natureCombobox.setTriggerAction(TriggerAction.ALL);
        this.natureCombobox.setStore(this.natures);
        this.natureCombobox.setFieldLabel(this.messages.delegationnature());
        this.natureCombobox.setLabelSeparator("");
        this.natureCombobox.setSelection(this.natureAllSelection);
        this.natureCombobox.setDisplayField(DelegationNatureModel.DELE_NATURE_NAME);
        this.natureCombobox.setLabelStyle("white-space: nowrap");
        left.add(this.natureCombobox, formData);

        // status combobox
        this.statusCombobox = new XComboBox<DelegationStatusModel>();
        this.statusCombobox.setWidth(COMBOBOX_WIDTH);
        this.statusCombobox.setTriggerAction(TriggerAction.ALL);
        this.statusCombobox.setStore(this.statuses);
        this.statusCombobox.setEditable(false);
        this.statusCombobox.setDisplayField(DelegationStatusModel.DELEGATION_STATUS_NAME);
        this.statusCombobox.setSelection(this.statusAllSelection);
        this.statusCombobox.setFieldLabel(this.messages.delegationstatus());
        this.statusCombobox.setLabelStyle("white-space: nowrap");
        this.statusCombobox.setLabelSeparator("");
        left.add(this.statusCombobox, formData);

        // type combobox
        this.typeCombobox = new XComboBox<DelegationTypeModel>();
        this.typeCombobox.setWidth(COMBOBOX_WIDTH);
        this.typeCombobox.setEditable(false);
        this.typeCombobox.setTriggerAction(TriggerAction.ALL);
        this.typeCombobox.setDisplayField(DelegationTypeModel.DELEGATION_TYPE_NAME);
        this.typeCombobox.setStore(this.types);
        this.typeCombobox.setSelection(this.typeAllSelection);
        this.typeCombobox.setFieldLabel(this.messages.delegationtype());
        this.typeCombobox.setLabelSeparator("");
        this.typeCombobox.setLabelStyle("white-space: nowrap");
        left.add(this.typeCombobox, formData);

        this.afficherCheck = new CheckBox();
        this.afficherCheck.setValue(true);
        this.afficherCheck.setBoxLabel(this.messages.delegationafficher());
        this.afficherCheck.setStyleAttribute("margin-left", "0px");
        this.afficherCheck.setStyleAttribute("paddingLeft", "0px");
        this.afficherCheck.setLabelSeparator("");
        left.add(this.afficherCheck, formData);

        // right column
        LayoutContainer right = new LayoutContainer();
        right.setLayout(new FormLayout());
        ((FormLayout) right.getLayout()).setLabelAlign(LabelAlign.RIGHT);
        ((FormLayout) right.getLayout()).setLabelWidth(LABEL_WIDTH);

        // delegant
        this.delegantCombobox = new XComboBox<CollaborateurModel>();
        this.delegantCombobox.setWidth(COMBOBOX_WIDTH);
        this.delegantCombobox.setLabelSeparator("");
        this.delegantCombobox.setEditable(false);
        this.delegantCombobox.setTriggerAction(TriggerAction.ALL);
        this.delegantCombobox.setDisplayField(CollaborateurModel.COLLA_FULL_NAME_NO_SEPARATER);
        this.delegantCombobox.setStore(this.delegants);
        this.delegantCombobox.setFieldLabel(this.messages.delegationdelegant());
        this.delegantCombobox.setLabelStyle("white-space: nowrap");
        this.delegantCombobox.setSelection(this.delegantAllSelection);

        // delegataire
        this.delegataireCombobox = new XComboBox<CollaborateurModel>();
        this.delegataireCombobox.setLabelSeparator("");
        this.delegataireCombobox.setEditable(false);
        this.delegataireCombobox.setWidth(COMBOBOX_WIDTH);
        this.delegataireCombobox.setTriggerAction(TriggerAction.ALL);
        this.delegataireCombobox.setDisplayField(CollaborateurModel.COLLA_FULL_NAME_NO_SEPARATER);
        this.delegataireCombobox.setStore(this.delegataires);
        this.delegataireCombobox.setSelection(this.delegataireAllSelection);
        this.delegataireCombobox.setFieldLabel(this.messages.delegationdelegataire());
        this.delegataireCombobox.setLabelStyle("white-space: nowrap");

        // @Lan(2012/03/01): Fixing bug #98 - Remove the default date for start date and end date
        // periodeLabel = new Label(messages.delegationperiode());
        this.fromDateField = new DateField();
        this.fromDateField.setLabelSeparator("");
        this.fromDateField.setFieldLabel(this.messages.delegationperiode());
        this.fromDateField.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        this.fromDateField.setLabelStyle("white-space: nowrap");
        this.fromDateField.setWidth(210);

        // to date
        // auLabel = new Label(messages.delegationau());
        this.toDateField = new DateField();
        this.toDateField.setLabelSeparator("");
        this.toDateField.setFieldLabel(this.messages.delegationau());
        this.toDateField.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        this.toDateField.setLabelStyle("white-space: nowrap");
        this.toDateField.setWidth(210);

        LayoutContainer layoutDateField = new LayoutContainer();
        layoutDateField.setLayout(new ColumnLayout());
        LayoutContainer dateLeft = new LayoutContainer();
        dateLeft.setStyleAttribute("paddingRight", "2px");
        FormLayout layoutLeft = new FormLayout();
        layoutLeft.setLabelAlign(LabelAlign.RIGHT);
        layoutLeft.setLabelWidth(LABEL_WIDTH);
        dateLeft.setLayout(layoutLeft);
        dateLeft.add(this.fromDateField, formData);
        LayoutContainer dateRight = new LayoutContainer();
        dateRight.setStyleAttribute("paddingRight", "0px");
        FormLayout layoutRight = new FormLayout();
        layoutRight.setLabelAlign(LabelAlign.RIGHT);
        layoutRight.setLabelWidth(LABEL_WIDTH);

        dateRight.setLayout(layoutRight);
        dateRight.add(this.toDateField, formData);
        layoutDateField.add(dateLeft, new ColumnData(.5));
        layoutDateField.add(dateRight, new ColumnData(.5));

        dateRight.setLayout(layoutRight);
        dateRight.add(this.toDateField, formData);
        layoutDateField.add(dateLeft, new ColumnData(.5));
        layoutDateField.add(dateRight, new ColumnData(.5));

        this.sepCheckBox = new CheckBox();
        this.sepCheckBox.setBoxLabel(this.messages.delegationsep());
        this.sepCheckBox.setLabelSeparator("");
        this.sepCheckBox.setEnabled(false);
        this.sepCheckBox.setStyleAttribute("paddingLeft", "0px");
        this.sepCheckBox.setStyleAttribute("margin-left", "0px");

        this.delegationCheckBox = new CheckBox();
        this.delegationCheckBox.setBoxLabel(this.messages.delegationdelegationconjointe());
        this.delegationCheckBox.setLabelSeparator("");
        this.delegationCheckBox.setEnabled(false);
        this.delegationCheckBox.setStyleAttribute("paddingLeft", "0px");
        this.delegationCheckBox.setStyleAttribute("margin-left", "0px");

        right.add(this.delegantCombobox, formDataR);
        right.add(this.delegataireCombobox, formDataR);
        right.add(layoutDateField, formDataR);
        right.add(this.sepCheckBox, formDataR);

        this.buttonFiltrer = new Button(this.messages.delegationfiltrer());
        this.buttonFiltrer.setWidth(35);
        this.buttonFiltrer.setAutoWidth(false);

        LayoutContainer layoutButton = new LayoutContainer();
        layoutButton.setLayout(new ColumnLayout());
        LayoutContainer btnLeft = new LayoutContainer();
        btnLeft.setStyleAttribute("paddingRight", "2px");
        FormLayout loBtnLeft = new FormLayout();
        loBtnLeft.setLabelAlign(LabelAlign.RIGHT);
        loBtnLeft.setLabelWidth(LABEL_WIDTH);
        btnLeft.setLayout(loBtnLeft);
        btnLeft.add(this.delegationCheckBox, formData);
        LayoutContainer btnRight = new LayoutContainer();
        btnRight.setStyleAttribute("paddingRight", "0px");
        FormLayout loBtnRight = new FormLayout();
        loBtnRight.setLabelAlign(LabelAlign.RIGHT);
        loBtnRight.setLabelWidth(LABEL_WIDTH + 5);
        btnRight.setLayout(loBtnRight);
        btnRight.add(this.buttonFiltrer, formData);

        LayoutContainer btnCenter = new LayoutContainer();
        btnCenter.setStyleAttribute("paddingRight", "2px");
        btnCenter.add(new Label(""), formData);

        layoutButton.add(btnLeft, new ColumnData(.58));
        layoutButton.add(btnCenter, new ColumnData(.16));
        layoutButton.add(btnRight, new ColumnData(.26));

        right.add(layoutButton, formDataR);

        // add to main panel
        main.setStyleAttribute("padding-left", "0px");
        main.setStyleAttribute("padding-bottom", "0px");
        main.add(left, new ColumnData(.5));
        main.add(right, new ColumnData(.5));

        // init main form panel
        FormPanel panel = new FormPanel();
        panel.setPadding(0);
        panel.setStyleAttribute("background", "white");
        panel.setHeaderVisible(false);
        panel.setSize(860, -1);
        panel.setBodyBorder(false);
        panel.setLabelAlign(LabelAlign.RIGHT);
        panel.setLabelWidth(LABEL_WIDTH);
        panel.setButtonAlign(HorizontalAlignment.CENTER);
        FormData formData2 = new FormData("100%");
        formData2.setMargins(new Margins(10, 0, 0, 0));
        panel.add(main, formData2);

        // add to root panel
        this.setStyleAttribute("padding-left", "5px");
        this.setSpacing(0);
        this.setHeight(150);
        this.add(panel);
    }

    private void addHandler() {
        // add event for Filter button
        this.buttonFiltrer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (delegationFilterEvent == null) {
                    delegationFilterEvent = new DelegationFilterEvent();
                }
                delegationFilterEvent.setNatureModel(natureCombobox.getSelection());
                delegationFilterEvent.setStatusModel(statusCombobox.getSelection());
                delegationFilterEvent.setTypeModel(typeCombobox.getSelection());
                delegationFilterEvent.setDelegant(delegantCombobox.getSelection());
                delegationFilterEvent.setDelegataire(delegataireCombobox.getSelection());
                delegationFilterEvent.setStartDate(fromDateField.getValue());
                delegationFilterEvent.setEndDate(toDateField.getValue());
                delegationFilterEvent.setSep(sepCheckBox.getValue());
                delegationFilterEvent.setConjointe(delegationCheckBox.getValue());
                delegationFilterEvent.setShowLevel(afficherCheck.getValue());
                delegationFilterEvent.setPageSize(pagingSize);

                // fire event delegation filter
                bus.fireEvent(delegationFilterEvent);
            }
        });

        // catch delegation tree event on left panel
        this.bus.addHandler(DelegationTreeEvent.getType(), new DelegationTreeHandler() {

            @Override
            public void onLoadAction(DelegationTreeEvent event) {
                PerimetreTreeModel treeModel = event.getTreeModel();
                if (treeModel != null) {
                    if (delegationFilterEvent == null) {
                        delegationFilterEvent = new DelegationFilterEvent();
                    }

                    PerimetreModel perimetreModel = new PerimetreModel();
                    perimetreModel.setPerId(treeModel.getPerId());
                    perimetreModel.setName(treeModel.getName());

                    PerimetreTypeModel type = new PerimetreTypeModel();
                    type.setPtyId(treeModel.getType());
                    type.setName(treeModel.get("typeName").toString());

                    perimetreModel.setType(type);

                    delegationFilterEvent.setPerimetreTreeModel(treeModel);

                    if (Arrays.asList(ClientConstant.PERIMETRE_TYPE_IS_CHANTIER).contains(type.getPtyId())) {
                        sepCheckBox.setEnabled(true);
                        delegationCheckBox.setEnabled(true);
                    } else {
                        sepCheckBox.setEnabled(false);
                        delegationCheckBox.setEnabled(false);
                    }

                    buttonFiltrer.fireEvent(Events.Select);

                    String perId = event.getTreeModel().getPerId();
                    String entiteId = event.getTreeModel().getEntiteId();

                    // initialize delegant list
                    delegants.removeAll();
                    delegantAll = new CollaborateurModel();
                    // delegantAll.setFullname(messages.commonTous());
                    delegantAll.setFullnameNoSeparater(messages.commonTous());
                    delegantAll.setId(0);
                    delegants.add(delegantAll);
                    delegantAllSelection.add(delegantAll);

                    // initialize delegataire list
                    delegataires.removeAll();
                    delegataireAll = new CollaborateurModel();
                    // delegataireAll.setFullname(messages.commonTous());
                    delegataireAll.setFullnameNoSeparater(messages.commonTous());
                    delegataireAll.setId(0);
                    delegataires.add(delegataireAll);
                    delegataireAllSelection.add(delegataireAll);

                    // load delegants
                    collaborateurService.getAllDelegantsByPerimeter(perId, entiteId, new AsyncCallback<List<CollaborateurModel>>() {

                        // collaborateurService.getAllDelegantsByEntiteId(event.getEntiteModel().getEntId(), new
                        // AsyncCallback<List<CollaborateurModel>>() {
                        @Override
                        public void onSuccess(List<CollaborateurModel> arg0) {
                            delegants.add(arg0);
                            delegantCombobox.setSelection(delegantAllSelection);
                        }

                        @Override
                        public void onFailure(Throwable arg0) {
                        }
                    });

                    // load delegataires
                    collaborateurService.getAllDelegatairesByPerimeter(perId, entiteId, new AsyncCallback<List<CollaborateurModel>>() {

                        @Override
                        public void onSuccess(List<CollaborateurModel> arg0) {
                            delegataires.add(arg0);
                            delegataireCombobox.setSelection(delegataireAllSelection);
                        }

                        @Override
                        public void onFailure(Throwable arg0) {
                        }
                    });
                }
            }
        });

        // catch event Filter on top form panel
        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(final DelegationListProjectEvent event) {
                if (event.getPerimetreModel() != null) {

                    if (Arrays.asList(ClientConstant.PERIMETRE_TYPE_IS_CHANTIER).contains(event.getPerimetreModel().getType().getPtyId())) {
                        sepCheckBox.setEnabled(true);
                        delegationCheckBox.setEnabled(true);
                    } else {
                        sepCheckBox.setEnabled(false);
                        delegationCheckBox.setEnabled(false);
                    }

                    if (delegationFilterEvent == null) {
                        delegationFilterEvent = new DelegationFilterEvent();
                    }
                    delegationFilterEvent.setEntiteModel(event.getEntiteModel());

                    PerimetreTreeModel perimetreTreeModel = new PerimetreTreeModel(event.getPerimetreModel(), SessionServiceImpl.getInstance()
                            .getUserContext().getUserRoles());

                    delegationFilterEvent.setPerimetreTreeModel(perimetreTreeModel);

                    buttonFiltrer.fireEvent(Events.Select);

                    String perId = event.getPerimetreModel().getPerId();
                    String entiteId = event.getEntiteModel().getEntId();

                    initData();
                    // load natures
                    natureService.findNatureByEntite(entiteId, new AsyncCallback<List<DelegationNatureModel>>() {

                        @Override
                        public void onSuccess(List<DelegationNatureModel> arg0) {
                            natures.add(arg0);
                            natureCombobox.setSelection(natureAllSelection);
                        }

                        @Override
                        public void onFailure(Throwable arg0) {
                        }
                    });

                    // load statues
                    statusService.getAllDelegationStatuses(new AsyncCallback<List<DelegationStatusModel>>() {

                        @Override
                        public void onSuccess(List<DelegationStatusModel> arg0) {
                            statuses.add(arg0);
                            statusCombobox.setSelection(statusAllSelection);
                        }

                        @Override
                        public void onFailure(Throwable arg0) {
                        }
                    });

                    // load types
                    typeService.getAllTypes(new AsyncCallback<List<DelegationTypeModel>>() {

                        @Override
                        public void onSuccess(List<DelegationTypeModel> arg0) {
                            types.add(arg0);
                            typeCombobox.setSelection(typeAllSelection);
                        }

                        @Override
                        public void onFailure(Throwable arg0) {
                        }
                    });

                    // load delegants
                    collaborateurService.getAllDelegantsByPerimeter(perId, entiteId, new AsyncCallback<List<CollaborateurModel>>() {

                        @Override
                        public void onSuccess(List<CollaborateurModel> arg0) {
                            delegants.add(arg0);
                            delegantCombobox.setSelection(delegantAllSelection);
                        }

                        @Override
                        public void onFailure(Throwable arg0) {
                        }
                    });

                    // load delegataires
                    collaborateurService.getAllDelegatairesByPerimeter(perId, entiteId, new AsyncCallback<List<CollaborateurModel>>() {

                        @Override
                        public void onSuccess(List<CollaborateurModel> arg0) {
                            delegataires.add(arg0);
                            delegataireCombobox.setSelection(delegataireAllSelection);
                        }

                        @Override
                        public void onFailure(Throwable arg0) {
                        }
                    });
                }
            }
        });

        this.bus.addHandler(DeleteDelegationEvent.getType(), new DeleteDelegationHandler() {

            @Override
            public void onLoadAction(DeleteDelegationEvent event) {
                buttonFiltrer.fireEvent(Events.Select);
            }
        });

        this.bus.addHandler(DelegationPagingEvent.getType(), new DelegationPagingHandler() {

            @Override
            public void onLoadAction(DelegationPagingEvent event) {
                pagingSize = event.getPageSize();
                buttonFiltrer.fireEvent(Events.Select);
            }
        });
    }

    public Button getFilterButton() {
        return this.buttonFiltrer;
    }
}
