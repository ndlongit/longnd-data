package com.structis.vip.client.panel;

import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreFilter;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Label;
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
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.event.shared.SimpleEventBus;

import com.google.gwt.user.client.ui.HTML;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.event.AdministrationTreeEvent;
import com.structis.vip.client.event.PerimetreEvent;
import com.structis.vip.client.event.PerimetreHandler;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.service.ClientChantierTypeServiceAsync;
import com.structis.vip.client.service.ClientDelegationServiceAsync;
import com.structis.vip.client.service.ClientEntiteJuridiqueServiceAsync;
import com.structis.vip.client.service.ClientLanguageServiceAsync;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.service.ClientPerimetreTypeServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.shared.model.ChantierTypeModel;
import com.structis.vip.shared.model.DelegationModel;
import com.structis.vip.shared.model.EntiteJuridiqueModel;
import com.structis.vip.shared.model.LanguageModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTypeModel;

public class NewPerimetrePanel extends AbstractPanel {

    private final FormData formData = new FormData("95%");
    private final static int WIDTH = 700;
    private final static int HEIGHT = -1;

    private ClientPerimetreServiceAsync clientPerimetreService = ClientPerimetreServiceAsync.Util.getInstance();
    private ClientPerimetreTypeServiceAsync clientPerimetreTypeService = ClientPerimetreTypeServiceAsync.Util.getInstance();
    private ClientLanguageServiceAsync clientLanguageServiceAsync = ClientLanguageServiceAsync.Util.getInstance();
    private ClientChantierTypeServiceAsync clientChantierTypeService = ClientChantierTypeServiceAsync.Util.getInstance();
    private ClientEntiteJuridiqueServiceAsync clientEntiteJuridiqueServiceAsync = ClientEntiteJuridiqueServiceAsync.Util.getInstance();
    private ClientDelegationServiceAsync clientDelegationServiceAsync = ClientDelegationServiceAsync.Util.getInstance();

    private Label lblOUPath;
    private Label info;

    private TextField<String> txtLibelle;
    private LabelField lblArgosName;
    private LabelField lblRattachement;
    private ListStore<PerimetreTypeModel> perimetreTypes;
    private ComboBox<PerimetreTypeModel> cbPerimetreType;
    private DateField dfDebut;
    private DateField dfPrevisionnelle;
    private DateField dfDefinitive;
    private LabelField lblCodeProjet;
    private TextField<String> txtVille;
    private TextField<String> txtAdresse;
    private CheckBox cbSEP;
    private CheckBox cbGroupment;
    private TextField<String> txtNumeroDeProjet;
    private TextField<String> txtChantierPartenaires;

    private ListStore<EntiteJuridiqueModel> etjs;
    private ComboBox<EntiteJuridiqueModel> cbEtj;
    private ListStore<LanguageModel> languages;
    private ComboBox<LanguageModel> cbLanguage;

    private ListStore<ChantierTypeModel> chantierTypes;
    private ComboBox<ChantierTypeModel> cbChantierType;
    private LabelField lblNonArgos;
    private LayoutContainer lcNonArgos;

    private Button btnAnnuler;
    private Button btnModifier;
    private LayoutContainer lcLine;
    private LayoutContainer lcLine1;

    @SuppressWarnings("unused")
    private String perimetreId;
    private String perimetreParentId;

    private PerimetreModel perimetre;

    private FormPanel panel;
    private CheckBox cbIsSubDelegable;

    public NewPerimetrePanel(SimpleEventBus bus) {
        this.bus = bus;
        // setup field set

        setLayout(new FlowLayout(10));
        setScrollMode(Scroll.AUTO);
        setStyleAttribute("paddingBottom", "20px");
        setStyleAttribute("paddingRight", "10px");
        setWidth(WIDTH);

        panel = new FormPanel();
        panel.setLabelWidth(180);
        panel.setFrame(true);
        panel.setHeading(messages.perimetreform());
        panel.setBorders(false);
        panel.setCollapsible(false);
        panel.setLayout(new FlowLayout());
        panel.setSize(WIDTH, -1);
        panel.setButtonAlign(HorizontalAlignment.RIGHT);

        info = new Label(messages.perimetrenotselect());
        panel.add(info);
        initOuPath();
        // add the seperator line
        lcLine = new LayoutContainer();
        lcLine.setSize(WIDTH, HEIGHT);
        lcLine.setLayout(new ColumnLayout());
        lcLine.add(new HTML("<hr width='670px'/>"));
        panel.add(lcLine);
        initTopForm();
        // add the seperator line
        lcLine1 = new LayoutContainer();
        lcLine1.setSize(WIDTH, HEIGHT);
        lcLine1.setLayout(new ColumnLayout());
        lcLine1.add(new HTML("<hr width='670px'/>"));
        panel.add(lcLine1);
        initNonArgosForm();

        // lcLine1 = new LayoutContainer();
        // lcLine1.setSize(WIDTH, HEIGHT);
        // lcLine1.setLayout(new ColumnLayout());
        // lcLine1.add(new HTML("<hr width='670px'/>"));
        // panel.add(lcLine1);

        initMainForm();
        initButton();
        // add handler when click edit or create button
        bus.addHandler(PerimetreEvent.getType(), new PerimetreHandler() {

            @Override
            public void onLoadAction(PerimetreEvent event) {
                if ((event.getIsUoAdmin() != null) && (!event.getIsUoAdmin())) {
                    setVisibleAll(false);
                    info.setVisible(true);
                    info.setText(messages.commonnopermissionperimetre());
                } else {
                    switch (event.getMode()) {
                    case PerimetreEvent.MODE_IS_CREATE:
                        newMode(event);
                        break;
                    case PerimetreEvent.MODE_IS_EDIT:
                        editMode(event);
                        break;
                    case PerimetreEvent.MODE_IS_VIEW:
                        viewMode(event);
                        break;
                    case PerimetreEvent.MODE_IS_NOT_SELECTED:
                        showNoPerimetre();
                        break;
                    }
                }
            }
        });
        add(panel);
    }

    private void showNoPerimetre() {
        setVisibleAll(false);
        info.setVisible(true);
        info.setText(messages.perimetrenotselect());
    }

    private void setVisibleAll(boolean visible) {
        for (Field<?> field : panel.getFields()) {
            field.setVisible(visible);
        }
        lblOUPath.setVisible(visible);
        lcLine.setVisible(visible);
        lcLine1.setVisible(visible);
        btnAnnuler.setVisible(visible);
        btnModifier.setVisible(visible);
    }

    private void setEnabledAll(boolean enable) {
        for (Field<?> field : panel.getFields()) {
            field.setEnabled(enable);
        }
        btnAnnuler.setVisible(enable);
        btnModifier.setVisible(enable);
    }

    private void newMode(PerimetreEvent event) {
        AppUtil.putInAdminEditMode();
        AppUtil.putInPerimetreEditMode();
        setEnabledAll(true);
        setVisibleAll(true);
        info.setVisible(false);
        lblArgosName.setVisible(false);
        txtChantierPartenaires.setVisible(false);
        panel.reset();
        if (event.getPath() != null) {
            lblOUPath.setText(messages.perimetretitle() + ": " + event.getPath());
            String path = event.getPath();
            if (path.lastIndexOf(">") != -1) {
                lblRattachement.setValue(path.substring(path.lastIndexOf(">") + 1, path.length()).trim());
            } else {
                lblRattachement.setValue(path.trim());
            }
        }
        perimetreId = null;
        perimetreParentId = event.getPerimetreParentId();
        perimetre = new PerimetreModel();
        btnModifier.setText(messages.commonValiderButton());
        initData();
        lcNonArgos.setVisible(true);
        lblNonArgos.setValue(messages.perimetrenonargosvalue() + " " + SessionServiceImpl.getInstance().getUserContext().getUserName());
        if (event.getPerimetreParentId() != null) {
            clientPerimetreService.findById(event.getPerimetreParentId(), new AsyncCallbackWithErrorResolution<PerimetreModel>() {

                @Override
                public void onSuccess(PerimetreModel arg0) {
                    if (arg0 != null) {
                        if (arg0.getLanguage() != null) {
                            cbLanguage.setValue(arg0.getLanguage());

                        }
                        if (arg0.getEntiteJuridique() != null) {
                            cbEtj.setValue(arg0.getEntiteJuridique()); // R01
                        }
                    }
                }
            });
        }
    }

    private void editMode(PerimetreEvent event) {
        AppUtil.putInAdminEditMode();
        AppUtil.putInPerimetreEditMode();
        setEnabledAll(true);
        setVisibleAll(true);
        info.setVisible(false);
        lblArgosName.setVisible(false);
        panel.reset();
        if (event.getPath() != null) {
            lblOUPath.setText(messages.perimetretitle() + ": " + event.getPath());
        }
        perimetreId = event.getPerimetreId();
        perimetreParentId = event.getPerimetreParentId();
        btnModifier.setText(messages.commonModifierButton());
        initData();
        clientPerimetreService.findById(event.getPerimetreId(), new AsyncCallbackWithErrorResolution<PerimetreModel>() {

            @Override
            public void onSuccess(PerimetreModel arg0) {
                if (arg0 != null) {
                    perimetre = arg0;
                    cbEtj.setValue(arg0.getEntiteJuridique());
                    txtLibelle.setValue(perimetre.getName());
                    cbPerimetreType.setValue(perimetre.getType());
                    dfDebut.setValue(perimetre.getChantierStartDate());
                    dfPrevisionnelle.setValue(perimetre.getChantierPlannedEndDate());
                    dfDefinitive.setValue(perimetre.getChantierEndDate());
                    // lblCodeProjet.setValue(perimetre.getPerId());

                    txtVille.setValue(perimetre.getCity());
                    txtAdresse.setValue(perimetre.getAddress());

                    cbLanguage.setValue(perimetre.getLanguage());
                    if (perimetre.getIsSubdelegable() != null) {
                        cbIsSubDelegable.setValue(perimetre.getIsSubdelegable() > 0);
                    }
                    cbSEP.setValue(((perimetre.getChantierSEP() != null) && (1 == perimetre.getChantierSEP())) ? true : false);

                    cbChantierType.setValue(perimetre.getChantierType());
                    // if (perimetre.getType() != null &&
                    // ConstantClient.PERIMETER_TYPE_NAME_IS_CHANTIER.equals(perimetre.getType().getName()))
                    // {
                    if (perimetre.getType() != null && CommonUtils.isChantierType(perimetre.getType().getName())) {
                        cbGroupment.setValue(((perimetre.getChantierGroupement() != null) && (1 == perimetre.getChantierGroupement())) ? true : false);
                        txtNumeroDeProjet.setValue(perimetre.getChantierNumeroDeProjet());

                        if (cbSEP.getValue() || cbGroupment.getValue()) {
                            txtChantierPartenaires.setValue(perimetre.getChantierPartenaires());
                            txtChantierPartenaires.setVisible(true);
                        } else {
                            txtChantierPartenaires.setVisible(false);
                        }
                    } else {
                        cbGroupment.setVisible(false);
                        txtNumeroDeProjet.setVisible(false);
                        txtChantierPartenaires.setVisible(false);
                    }
                    // if
                    // (perimetre.getEntite().getEntId().equals(ConstantClient.ENTITE_ID_IS_ETDE))
                    // {
                    // if (perimetre.getArgosName() != null) {
                    // lcNonArgos.setVisible(false);
                    // lblArgosName.setVisible(true);
                    // lblArgosName.setValue(perimetre.getArgosName());
                    // } else {
                    // lcNonArgos.setVisible(true);
                    // lblNonArgos.setValue(messages.perimetrenonargosvalue() +
                    // " " + perimetre.getCreatedBy().getUserName());
                    // lblArgosName.setVisible(false);
                    // lblArgosName.setValue(null);
                    // }
                    // } else { //BYEFE
                    if (perimetre.getArgosName() != null) {
                        lcNonArgos.setVisible(false);
                        lblArgosName.setVisible(true);
                        lblArgosName.setValue(perimetre.getArgosName());
                    } else {
                        lcNonArgos.setVisible(true);
                        String user = "<Compte utilisateur Windows>";
                        if (perimetre.getCreatedBy() != null) {
                            user = perimetre.getCreatedBy().getUserName();
                        }

                        lblNonArgos.setValue(messages.perimetrenonargosvalue() + " " + user);
                        lblArgosName.setVisible(false);
                        lblArgosName.setValue(null);
                    }
                    // }
                    if (perimetre.getParent() != null) {
                        lblRattachement.setValue(perimetre.getParent().getName());
                    } else {
                        lblRattachement.setValue(perimetre.getEntite().getName());
                    }

                    clientDelegationServiceAsync.findByPerimetre(arg0.getPerId(), new AsyncCallbackWithErrorResolution<List<DelegationModel>>() {

                        @Override
                        public void onSuccess(List<DelegationModel> arg0) {
                            if ((arg0 != null) && (arg0.size() != 0)) {
                                cbPerimetreType.setEnabled(false);
                            } else {
                                cbPerimetreType.setEnabled(true);
                            }
                        }
                    });
                } else {
                    lcNonArgos.setVisible(true);
                    lblArgosName.setVisible(false);
                    lblNonArgos.setValue(messages.perimetrenonargosvalue() + " " + SessionServiceImpl.getInstance().getUserContext().getUserName());
                }
            }
        });
    }

    private void viewMode(PerimetreEvent event) {
        setVisibleAll(true);
        setEnabledAll(false);
        info.setVisible(false);
        lblArgosName.setVisible(false);
        if (event.getPath() != null) {
            lblOUPath.setText(messages.perimetretitle() + ": " + event.getPath());
        }
        perimetreId = event.getPerimetreId();
        perimetreParentId = event.getPerimetreParentId();
        panel.reset();
        btnModifier.setText(messages.commonModifierButton());
        initData();
        clientPerimetreService.findById(event.getPerimetreId(), new AsyncCallbackWithErrorResolution<PerimetreModel>() {

            @Override
            public void onSuccess(PerimetreModel arg0) {
                if (arg0 != null) {
                    perimetre = arg0;
                    cbEtj.setValue(arg0.getEntiteJuridique());
                    txtLibelle.setValue(perimetre.getName());
                    cbPerimetreType.setValue(perimetre.getType());
                    dfDebut.setValue(perimetre.getChantierStartDate());
                    dfPrevisionnelle.setValue(perimetre.getChantierPlannedEndDate());
                    dfDefinitive.setValue(perimetre.getChantierEndDate());
                    // lblCodeProjet.setValue(perimetre.getPerId());

                    txtVille.setValue(perimetre.getCity());
                    txtAdresse.setValue(perimetre.getAddress());

                    cbLanguage.setValue(perimetre.getLanguage());
                    if (perimetre.getIsSubdelegable() != null) {
                        cbIsSubDelegable.setValue(perimetre.getIsSubdelegable() > 0);
                    }
                    cbSEP.setValue(((perimetre.getChantierSEP() != null) && (1 == perimetre.getChantierSEP())) ? true : false);
                    cbGroupment.setValue(((perimetre.getChantierGroupement() != null) && (1 == perimetre.getChantierGroupement())) ? true : false);
                    txtNumeroDeProjet.setValue(perimetre.getChantierNumeroDeProjet());

                    if (cbSEP.getValue() || cbGroupment.getValue()) {
                        txtChantierPartenaires.setValue(perimetre.getChantierPartenaires());
                        txtChantierPartenaires.setVisible(true);
                    } else {
                        txtChantierPartenaires.setVisible(false);
                    }
                    // if (perimetre.getType() != null &&
                    // ConstantClient.PERIMETER_TYPE_NAME_IS_CHANTIER.equals(perimetre.getType().getName()))
                    // {
                    if (perimetre.getType() != null && CommonUtils.isChantierType(perimetre.getType().getName())) {
                        cbGroupment.setValue(((perimetre.getChantierGroupement() != null) && (1 == perimetre.getChantierGroupement())) ? true : false);
                        txtNumeroDeProjet.setValue(perimetre.getChantierNumeroDeProjet());

                        if (cbSEP.getValue() || cbGroupment.getValue()) {
                            txtChantierPartenaires.setValue(perimetre.getChantierPartenaires());
                            txtChantierPartenaires.setVisible(true);
                        } else {
                            txtChantierPartenaires.setVisible(false);
                        }
                    } else {
                        cbGroupment.setVisible(false);
                        txtNumeroDeProjet.setVisible(false);
                        txtChantierPartenaires.setVisible(false);
                    }
                    cbChantierType.setValue(perimetre.getChantierType());
                    // if
                    // (perimetre.getEntite().getEntId().equals(ConstantClient.ENTITE_ID_IS_ETDE))
                    // {
                    // if (perimetre.getArgosName() != null) {
                    // lblArgosName.setVisible(true);
                    // lblArgosName.setValue(perimetre.getArgosName());
                    // }
                    // }
                    if (perimetre.getArgosName() != null) {
                        lcNonArgos.setVisible(false);
                        lblArgosName.setVisible(true);
                        lblArgosName.setValue(perimetre.getArgosName());
                    } else {
                        lcNonArgos.setVisible(true);
                        String user = "<Compte utilisateur Windows>";
                        if (perimetre.getCreatedBy() != null) {
                            user = perimetre.getCreatedBy().getUserName();
                        }

                        lblNonArgos.setValue(messages.perimetrenonargosvalue() + " " + user);
                        lblArgosName.setVisible(false);
                        lblArgosName.setValue(null);
                    }
                    if (perimetre.getParent() != null) {
                        lblRattachement.setValue(perimetre.getParent().getName());
                    } else {
                        lblRattachement.setValue(perimetre.getEntite().getName());
                    }
                }
            }
        });
    }

    private void initOuPath() {
        lblOUPath = new Label("");
        panel.add(lblOUPath);
    }

    private void initTopForm() {
        // setup information layout
        LayoutContainer lcInformation = new LayoutContainer();
        lcInformation.setSize(WIDTH - 20, HEIGHT);
        lcInformation.setLayout(new ColumnLayout());

        // setup left layout
        LayoutContainer lcLeft = new LayoutContainer();
        lcLeft.setStyleAttribute("paddingRight", "10px");
        FormLayout flLeft = new FormLayout();
        flLeft.setLabelAlign(LabelAlign.LEFT);
        flLeft.setLabelWidth(120);
        lcLeft.setLayout(flLeft);

        perimetreTypes = new ListStore<PerimetreTypeModel>();
        cbPerimetreType = new ComboBox<PerimetreTypeModel>();
        cbPerimetreType.setFieldLabel(messages.perimetretype());
        cbPerimetreType.setStore(perimetreTypes);
        cbPerimetreType.setDisplayField(PerimetreTypeModel.PERIMETRE_TYPE_NAME);
        cbPerimetreType.setTriggerAction(TriggerAction.ALL);
        cbPerimetreType.setEditable(false);
        cbPerimetreType.setSimpleTemplate("<span title=\"{" + cbPerimetreType.getDisplayField() + "}\">{"
                + cbPerimetreType.getDisplayField() + "}</span>");
        cbPerimetreType.setAllowBlank(false);
        lcLeft.add(cbPerimetreType, formData);

        lblRattachement = new LabelField();
        lblRattachement.setFieldLabel("Rattachement:");
        // lblRattachement.setVisible(false);
        lcLeft.add(lblRattachement, formData);

        // setup right layout
        LayoutContainer lcRight = new LayoutContainer();
        FormLayout flRight = new FormLayout();
        flRight.setLabelAlign(LabelAlign.LEFT);
        flRight.setLabelWidth(140);
        lcRight.setLayout(flRight);

        lblCodeProjet = new LabelField();
        lblCodeProjet.setFieldLabel(messages.perimetrecode());
        // TODO Add code project
        // lcRight.add(lblCodeProjet, formData);

        etjs = new ListStore<EntiteJuridiqueModel>();
        cbEtj = new ComboBox<EntiteJuridiqueModel>();
        cbEtj.setFieldLabel(messages.perimetreentitejuridique());
        cbEtj.setStore(etjs);
        cbEtj.setEditable(false);
        cbEtj.setDisplayField(EntiteJuridiqueModel.ENTITE_JURIDIQUE_NAME);
        cbEtj.setTriggerAction(TriggerAction.ALL);
        cbEtj.setSimpleTemplate("<span title=\"{" + cbEtj.getDisplayField() + "}\">{" + cbEtj.getDisplayField() + "}</span>");
        lcRight.add(cbEtj, formData);

        languages = new ListStore<LanguageModel>();
        cbLanguage = new ComboBox<LanguageModel>();
        cbLanguage.setFieldLabel(messages.perimetrelangues());
        cbLanguage.setStore(languages);
        cbLanguage.setDisplayField(LanguageModel.LAG_NAME);
        cbLanguage.setTriggerAction(TriggerAction.ALL);
        cbLanguage.setEditable(false);
        cbLanguage.setSimpleTemplate("<span title=\"{" + cbLanguage.getDisplayField() + "}\">{" + cbLanguage.getDisplayField()
                + "}</span>");
        lcRight.add(cbLanguage, formData);

        cbIsSubDelegable = new CheckBox();

        cbIsSubDelegable.setLabelSeparator(":");
        cbIsSubDelegable.setFieldLabel(messages.delegationsousdelegable());
        lcLeft.add(cbIsSubDelegable, formData);

        lcInformation.add(lcLeft, new ColumnData(.5));
        lcInformation.add(lcRight, new ColumnData(.5));

        panel.add(lcInformation);
    }

    private void initNonArgosForm() {
        // setup information layout
        lcNonArgos = new LayoutContainer();
        lcNonArgos.setSize(WIDTH - 20, HEIGHT);
        lcNonArgos.setLayout(new ColumnLayout());

        // setup left layout
        LayoutContainer lcLeft = new LayoutContainer();
        lcLeft.setStyleAttribute("paddingRight", "10px");
        FormLayout flLeft = new FormLayout();
        flLeft.setLabelAlign(LabelAlign.LEFT);
        flLeft.setLabelWidth(140);
        lcLeft.setLayout(flLeft);
        flLeft.setLabelSeparator(":");

        lblNonArgos = new LabelField();
        lblNonArgos.setFieldLabel(messages.perimetrenonargos());
        lblNonArgos.setValue(messages.perimetrenonargosvalue());
        lblNonArgos.setVisible(true);
        lcLeft.add(lblNonArgos, formData);

        lcNonArgos.add(lcLeft, new ColumnData(1));

        panel.add(lcNonArgos);
        lcLine1 = new LayoutContainer();
        lcLine1.setSize(WIDTH, HEIGHT);
        lcLine1.setLayout(new ColumnLayout());
        lcLine1.add(new HTML("<hr width='670px'/>"));
        lcNonArgos.add(lcLine1);
    }

    private void initMainForm() {
        // setup information layout
        LayoutContainer lcInformation = new LayoutContainer();
        lcInformation.setSize(WIDTH - 20, HEIGHT);
        lcInformation.setLayout(new ColumnLayout());

        // setup left layout
        LayoutContainer lcLeft = new LayoutContainer();
        lcLeft.setStyleAttribute("paddingRight", "10px");
        FormLayout flLeft = new FormLayout();
        flLeft.setLabelAlign(LabelAlign.LEFT);
        flLeft.setLabelWidth(140);
        lcLeft.setLayout(flLeft);

        lblArgosName = new LabelField();
        lblArgosName.setFieldLabel(messages.perimetreargosname());
        lblArgosName.setVisible(false);
        lcLeft.add(lblArgosName, formData);

        txtLibelle = new TextField<String>();
        txtLibelle.setFieldLabel(messages.perimetrelibelle());
        txtLibelle.setAllowBlank(false);
        lcLeft.add(txtLibelle, formData);

        chantierTypes = new ListStore<ChantierTypeModel>();
        cbChantierType = new ComboBox<ChantierTypeModel>();
        cbChantierType.setFieldLabel(messages.perimetretypechantier());
        cbChantierType.setStore(chantierTypes);
        cbChantierType.setDisplayField(ChantierTypeModel.CTY_LABEL);
        cbChantierType.setTriggerAction(TriggerAction.ALL);
        cbChantierType.setEditable(false);
        cbChantierType.setSimpleTemplate("<span title=\"{" + cbChantierType.getDisplayField() + "}\">{"
                + cbChantierType.getDisplayField() + "}</span>");
        cbChantierType.setVisible(false);
        lcLeft.add(cbChantierType, formData);

        cbPerimetreType.addSelectionChangedListener(new SelectionChangedListener<PerimetreTypeModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<PerimetreTypeModel> se) {
                if (se.getSelectedItem() != null) {
                    // if
                    // (ConstantClient.PERIMETER_TYPE_NAME_IS_CHANTIER.equals(se.getSelectedItem().getName()))
                    // {
                    if (CommonUtils.isChantierType(se.getSelectedItem().getName())) {
                        cbSEP.setVisible(true);
                        dfDebut.setVisible(true);
                        dfDefinitive.setVisible(true);
                        dfPrevisionnelle.setVisible(true);
                        cbChantierType.setVisible(true);
                        cbGroupment.setVisible(true);
                        txtNumeroDeProjet.setVisible(true);
                    } else {
                        cbSEP.setVisible(false);
                        dfDebut.setVisible(false);
                        dfDefinitive.setVisible(false);
                        dfPrevisionnelle.setVisible(false);
                        cbChantierType.setVisible(false);

                        cbGroupment.setVisible(false);
                        txtNumeroDeProjet.setVisible(false);
                    }
                    // change the default sub delegation
                    if (perimetre == null || perimetre.getPerId() == null) {
                        cbIsSubDelegable.setValue(se.getSelectedItem() == null ? null : se.getSelectedItem().getIsSubdelegable() == null ? null : se
                                .getSelectedItem().getIsSubdelegable() > 0);
                    } else {
                        if (perimetre.getIsSubdelegable() == null) {
                            cbIsSubDelegable.setValue(se.getSelectedItem() == null ? null : se.getSelectedItem().getIsSubdelegable() == null ? null
                                    : se.getSelectedItem().getIsSubdelegable() > 0);
                        } else {
                            if (!perimetre.getIsSubdelegable().equals(se.getSelectedItem().getIsSubdelegable())) {
                                cbIsSubDelegable.setValue(se.getSelectedItem() == null ? null
                                        : se.getSelectedItem().getIsSubdelegable() == null ? null : se.getSelectedItem().getIsSubdelegable() > 0);
                            }
                        }
                    }
                }
            }
        });

        // date debut
        dfDebut = new DateField();
        dfDebut.setFieldLabel(messages.perimetredebut());
        dfDebut.setEditable(true);
        dfDebut.setFormatValue(true);
        dfDebut.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        lcLeft.add(dfDebut, formData);

        // date previsionnelle
        dfPrevisionnelle = new DateField();
        dfPrevisionnelle.setFieldLabel(messages.perimetreprevisionnelle());
        dfPrevisionnelle.setEditable(true);
        dfPrevisionnelle.setFormatValue(true);
        dfPrevisionnelle.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        lcLeft.add(dfPrevisionnelle, formData);

        // date definitive
        dfDefinitive = new DateField();
        dfDefinitive.setFieldLabel(messages.perimetredefinitive());
        dfDefinitive.setEditable(true);
        dfDefinitive.setFormatValue(true);
        dfDefinitive.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        lcLeft.add(dfDefinitive, formData);

        txtVille = new TextField<String>();
        txtVille.setFieldLabel(messages.perimetreville());
        lcLeft.add(txtVille, formData);

        txtAdresse = new TextField<String>();
        txtAdresse.setFieldLabel(messages.perimetreadresse());
        lcLeft.add(txtAdresse, formData);

        txtNumeroDeProjet = new TextField<String>();
        txtNumeroDeProjet.setFieldLabel(messages.perimetrenumeroprojet());

        lcLeft.add(txtNumeroDeProjet, formData);

        cbSEP = new CheckBox();
        cbSEP.setBoxLabel(messages.delegationsep());
        cbSEP.setLabelSeparator("");
        cbSEP.setEnabled(false);
        cbSEP.setStyleAttribute("padding", "0px");
        lcLeft.add(cbSEP, formData);
        cbSEP.addListener(Events.OnClick, new Listener<ComponentEvent>() {

            @Override
            public void handleEvent(ComponentEvent be) {
                if (cbSEP.getValue() || cbGroupment.getValue()) {
                    txtChantierPartenaires.setVisible(true);
                } else {
                    txtChantierPartenaires.setVisible(false);
                }
            }
        });

        cbGroupment = new CheckBox();
        cbGroupment.setBoxLabel(messages.perimetregroupment());
        cbGroupment.setLabelSeparator("");
        cbGroupment.setEnabled(false);
        cbGroupment.setStyleAttribute("padding", "0px");
        lcLeft.add(cbGroupment, formData);
        cbGroupment.addListener(Events.OnClick, new Listener<ComponentEvent>() {

            @Override
            public void handleEvent(ComponentEvent be) {
                if (cbSEP.getValue() || cbGroupment.getValue()) {
                    txtChantierPartenaires.setVisible(true);
                } else {
                    txtChantierPartenaires.setVisible(false);
                }
            }
        });

        txtChantierPartenaires = new TextField<String>();
        txtChantierPartenaires.setFieldLabel(messages.perimetrepartenaires());
        lcLeft.add(txtChantierPartenaires, formData);

        lcInformation.add(lcLeft, new ColumnData(.5));

        panel.add(lcInformation);
    }

    private void applyData() {
        perimetre.setEntiteJuridique(cbEtj.getValue());
        perimetre.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
        perimetre.setName(txtLibelle.getValue());
        perimetre.setType(cbPerimetreType.getValue());
        if (dfDebut.isVisible()) {
            perimetre.setChantierStartDate(dfDebut.getValue());
        }
        if (dfPrevisionnelle.isVisible()) {
            perimetre.setChantierPlannedEndDate(dfPrevisionnelle.getValue());
        }
        if (dfDefinitive.isVisible()) {
            perimetre.setChantierEndDate(dfDefinitive.getValue());
        }
        if (cbSEP.isVisible()) {
            perimetre.setChantierSEP(cbSEP.getValue() ? 1 : 0);
        }
        if (cbChantierType.isVisible()) {
            perimetre.setChantierType(cbChantierType.getValue());
        }
        if (txtVille.isVisible()) {
            perimetre.setCity(txtVille.getValue());
        }
        if (txtAdresse.isVisible()) {
            perimetre.setAddress(txtAdresse.getValue());
        }
        perimetre.setLanguage(cbLanguage.getValue());
        if (cbIsSubDelegable.getValue() != null) {
            perimetre.setIsSubdelegable(cbIsSubDelegable.getValue() ? 1 : 0);

        }
        if (cbGroupment.isVisible()) {
            perimetre.setChantierGroupement(cbGroupment.getValue() ? 1 : 0);
        }
        if (txtNumeroDeProjet.isVisible()) {
            perimetre.setChantierNumeroDeProjet(txtNumeroDeProjet.getValue());
        }
        if (txtChantierPartenaires.isVisible()) {
            perimetre.setChantierPartenaires(txtChantierPartenaires.getValue());
        }
    }

    private void initButton() {
        // add button
        btnAnnuler = new Button(messages.delegationformannuler());
        btnAnnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                AppUtil.removeAdminInEditMode();
                AppUtil.removePerminetreEditMode();
                if (perimetre.getPerId() != null) {
                    PerimetreEvent event = new PerimetreEvent();
                    // event.setPerimetreId(perimetre.getPerId());
                    // event.setPerimetreParentId(perimetreParentId);
                    event.setMode(PerimetreEvent.MODE_IS_NOT_SELECTED);
                    bus.fireEvent(event);
                } else if (perimetreParentId != null) {
                    PerimetreEvent event = new PerimetreEvent();
                    // event.setPerimetreId(perimetreParentId);
                    event.setMode(PerimetreEvent.MODE_IS_NOT_SELECTED);
                    bus.fireEvent(event);
                } else {
                    showNoPerimetre();
                }
            }
        });
        // remove button
        btnModifier = new Button(messages.commonAddButton());

        btnModifier.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if ((perimetre != null) && (panel.isValid())) {
                    applyData();

                    if (perimetre.getPerId() == null) {
                        perimetre.setCreatedBy(SessionServiceImpl.getInstance().getUserContext());
                        clientPerimetreService.insert(perimetreParentId, perimetre, new AsyncCallbackWithErrorResolution<String>() {

                            @Override
                            public void onSuccess(String arg0) {
                                if ("".equals(arg0)) {
                                    Info.display(messages.commonerror(), messages.perimetresavefailed());
                                } else {
                                    Info.display(messages.commoninfo(), messages.perimetresavesuccess());
                                    PerimetreEvent event = new PerimetreEvent();
                                    event.setPerimetreId(arg0);
                                    event.setMode(PerimetreEvent.MODE_IS_VIEW);
                                    AdministrationTreeEvent treeEvent = new AdministrationTreeEvent();
                                    treeEvent.setParentId(perimetreParentId);
                                    treeEvent.setPerId(arg0);
                                    bus.fireEvent(event);
                                    bus.fireEvent(treeEvent);
                                    AppUtil.removeAdminInEditMode();
                                    AppUtil.removePerminetreEditMode();
                                }
                            }
                        });
                    } else {
                        clientPerimetreService.update(perimetre, new AsyncCallbackWithErrorResolution<Boolean>() {

                            @Override
                            public void onSuccess(Boolean arg0) {
                                if (arg0) {
                                    Info.display(messages.commoninfo(), messages.perimetreupdatesuccess());
                                    PerimetreEvent event = new PerimetreEvent();
                                    event.setPerimetreId(perimetre.getPerId());
                                    event.setMode(PerimetreEvent.MODE_IS_VIEW);
                                    AdministrationTreeEvent treeEvent = new AdministrationTreeEvent();
                                    treeEvent.setParentId(perimetreParentId);
                                    treeEvent.setPerId(perimetre.getPerId());
                                    bus.fireEvent(event);
                                    bus.fireEvent(treeEvent);
                                    AppUtil.removeAdminInEditMode();
                                } else {
                                    Info.display(messages.commonerror(), messages.perimetreupdatefailed());
                                }
                            }
                        });
                    }
                }
            }
        });
        panel.addButton(btnAnnuler);
        panel.addButton(btnModifier);
    }

    private void initData() {
        final String entId = SessionServiceImpl.getInstance().getEntiteContext().getEntId();
        clientPerimetreTypeService.getPerimetreTypes(entId, new AsyncCallbackWithErrorResolution<List<PerimetreTypeModel>>() {

            @Override
            public void onSuccess(List<PerimetreTypeModel> arg0) {
                perimetreTypes.removeAll();
                perimetreTypes.add(arg0);
                if (cbPerimetreType.getValue() == null) {
                    // add BYTP
                    // if (ConstantClient.ENTITE_ID_IS_BYEFE.equals(entId)) {
                    if (CommonUtils.belongsBYEFEGroup(entId)) {
                        if (arg0 != null && arg0.size() > 0) {
                            PerimetreTypeModel ptm = arg0.get(0);
                            cbPerimetreType.select(0);
                            cbPerimetreType.setValue(ptm);
                        }
                    } else { // ETDE - default is site
                        perimetreTypes.addFilter(new StoreFilter<PerimetreTypeModel>() {

                            @Override
                            public boolean select(Store<PerimetreTypeModel> store, PerimetreTypeModel parent, PerimetreTypeModel item, String property) {
                                if (item.getName().equalsIgnoreCase("Site") || item.getName().equalsIgnoreCase("Service")
                                        || item.getName().equalsIgnoreCase("Chantier")) {
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });

                        PerimetreTypeModel ptm = findSiteType(arg0);
                        if (ptm != null) {
                            cbPerimetreType.select(ptm);
                            cbPerimetreType.setValue(ptm);
                        }

                    }
                }
            }

            private PerimetreTypeModel findSiteType(List<PerimetreTypeModel> arg0) {
                for (PerimetreTypeModel pm : arg0) {
                    if (pm.getName().equals(ClientConstant.TYPE_SITE)) {
                        return pm;
                    }
                }
                return null;
            }
        });

        clientChantierTypeService.findChantierByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallbackWithErrorResolution<List<ChantierTypeModel>>() {

                    @Override
                    public void onSuccess(List<ChantierTypeModel> arg0) {
                        chantierTypes.removeAll();
                        chantierTypes.add(arg0);
                        if (cbChantierType.getValue() == null) {
                            if (arg0 != null && arg0.size() > 0) {
                                ChantierTypeModel ptm = arg0.get(0);
                                cbChantierType.select(0);
                                cbChantierType.setValue(ptm);
                            }
                        }
                    }
                });

        clientLanguageServiceAsync.getLanguages(new AsyncCallbackWithErrorResolution<List<LanguageModel>>() {

            @Override
            public void onSuccess(List<LanguageModel> arg0) {
                languages.removeAll();
                languages.add(arg0);

                if (cbLanguage.getValue() == null) {
                    if (arg0 != null && arg0.size() > 0) {
                        LanguageModel ptm = arg0.get(0);
                        cbLanguage.select(0);
                        cbLanguage.setValue(ptm);
                    }
                }
            }
        });

        clientEntiteJuridiqueServiceAsync.findByEntiteId(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallbackWithErrorResolution<List<EntiteJuridiqueModel>>() {

                    @Override
                    public void onSuccess(List<EntiteJuridiqueModel> arg0) {
                        etjs.removeAll();
                        etjs.add(arg0);

                        if (arg0.isEmpty() == false) {
                            for (EntiteJuridiqueModel entiteJuridiqueModel : arg0) {
                                if (entiteJuridiqueModel.getIsDefault() != null && entiteJuridiqueModel.getIsDefault().intValue() == 1) {
                                    cbEtj.setValue(entiteJuridiqueModel);
                                }
                            }
                        }
                    }
                });
    }
}
