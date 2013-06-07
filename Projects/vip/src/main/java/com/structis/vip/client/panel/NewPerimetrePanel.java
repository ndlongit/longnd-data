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
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.event.AdministrationTreeEvent;
import com.structis.vip.client.event.PerimetreEvent;
import com.structis.vip.client.event.PerimetreHandler;
import com.structis.vip.client.message.Messages;
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

    private SimpleEventBus bus;

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

        this.setLayout(new FlowLayout(10));
        this.setScrollMode(Scroll.AUTO);
        this.setStyleAttribute("paddingBottom", "20px");
        this.setStyleAttribute("paddingRight", "10px");
        this.setWidth(WIDTH);

        this.panel = new FormPanel();
        this.panel.setLabelWidth(180);
        this.panel.setFrame(true);
        this.panel.setHeading(this.messages.perimetreform());
        this.panel.setBorders(false);
        this.panel.setCollapsible(false);
        this.panel.setLayout(new FlowLayout());
        this.panel.setSize(WIDTH, -1);
        this.panel.setButtonAlign(HorizontalAlignment.RIGHT);

        this.info = new Label(this.messages.perimetrenotselect());
        this.panel.add(this.info);
        this.initOuPath();
        // add the seperator line
        this.lcLine = new LayoutContainer();
        this.lcLine.setSize(WIDTH, HEIGHT);
        this.lcLine.setLayout(new ColumnLayout());
        this.lcLine.add(new HTML("<hr width='670px'/>"));
        this.panel.add(this.lcLine);
        this.initTopForm();
        // add the seperator line
        this.lcLine1 = new LayoutContainer();
        this.lcLine1.setSize(WIDTH, HEIGHT);
        this.lcLine1.setLayout(new ColumnLayout());
        this.lcLine1.add(new HTML("<hr width='670px'/>"));
        this.panel.add(this.lcLine1);
        this.initNonArgosForm();

        // lcLine1 = new LayoutContainer();
        // lcLine1.setSize(WIDTH, HEIGHT);
        // lcLine1.setLayout(new ColumnLayout());
        // lcLine1.add(new HTML("<hr width='670px'/>"));
        // panel.add(lcLine1);

        this.initMainForm();
        this.initButton();
        // add handler when click edit or create button
        bus.addHandler(PerimetreEvent.getType(), new PerimetreHandler() {

            @Override
            public void onLoadAction(PerimetreEvent event) {
                if ((event.getIsUoAdmin() != null) && (!event.getIsUoAdmin())) {
                    NewPerimetrePanel.this.setVisibleAll(false);
                    NewPerimetrePanel.this.info.setVisible(true);
                    NewPerimetrePanel.this.info.setText(NewPerimetrePanel.this.messages.commonnopermissionperimetre());
                } else {
                    switch (event.getMode()) {
                    case PerimetreEvent.MODE_IS_CREATE:
                        NewPerimetrePanel.this.newMode(event);
                        break;
                    case PerimetreEvent.MODE_IS_EDIT:
                        NewPerimetrePanel.this.editMode(event);
                        break;
                    case PerimetreEvent.MODE_IS_VIEW:
                        NewPerimetrePanel.this.viewMode(event);
                        break;
                    case PerimetreEvent.MODE_IS_NOT_SELECTED:
                        NewPerimetrePanel.this.showNoPerimetre();
                        break;
                    }
                }
            }
        });
        this.add(this.panel);
    }

    private void showNoPerimetre() {
        this.setVisibleAll(false);
        this.info.setVisible(true);
        this.info.setText(this.messages.perimetrenotselect());
    }

    private void setVisibleAll(boolean visible) {
        for (Field<?> field : this.panel.getFields()) {
            field.setVisible(visible);
        }
        this.lblOUPath.setVisible(visible);
        this.lcLine.setVisible(visible);
        this.lcLine1.setVisible(visible);
        this.btnAnnuler.setVisible(visible);
        this.btnModifier.setVisible(visible);
    }

    private void setEnabledAll(boolean enable) {
        for (Field<?> field : this.panel.getFields()) {
            field.setEnabled(enable);
        }
        this.btnAnnuler.setVisible(enable);
        this.btnModifier.setVisible(enable);
    }

    private void newMode(PerimetreEvent event) {
        AppUtil.putInAdminEditMode();
        AppUtil.putInPerimetreEditMode();
        this.setEnabledAll(true);
        this.setVisibleAll(true);
        this.info.setVisible(false);
        this.lblArgosName.setVisible(false);
        this.txtChantierPartenaires.setVisible(false);
        this.panel.reset();
        if (event.getPath() != null) {
            this.lblOUPath.setText(this.messages.perimetretitle() + ": " + event.getPath());
            String path = event.getPath();
            if (path.lastIndexOf(">") != -1) {
                this.lblRattachement.setValue(path.substring(path.lastIndexOf(">") + 1, path.length()).trim());
            } else {
                this.lblRattachement.setValue(path.trim());
            }
        }
        this.perimetreId = null;
        this.perimetreParentId = event.getPerimetreParentId();
        this.perimetre = new PerimetreModel();
        this.btnModifier.setText(this.messages.commonValiderButton());
        this.initData();
        this.lcNonArgos.setVisible(true);
        this.lblNonArgos.setValue(this.messages.perimetrenonargosvalue() + " " + SessionServiceImpl.getInstance().getUserContext().getUserName());
        if (event.getPerimetreParentId() != null) {
            this.clientPerimetreService.findById(event.getPerimetreParentId(), new AsyncCallback<PerimetreModel>() {

                @Override
                public void onSuccess(PerimetreModel arg0) {
                    if (arg0 != null) {
                        if (arg0.getLanguage() != null) {
                            NewPerimetrePanel.this.cbLanguage.setValue(arg0.getLanguage());

                        }
                        if (arg0.getEntiteJuridique() != null) {
                            NewPerimetrePanel.this.cbEtj.setValue(arg0.getEntiteJuridique()); // R01
                        }
                    }
                }

                @Override
                public void onFailure(Throwable arg0) {
                }
            });
        }
    }

    private void editMode(PerimetreEvent event) {
        AppUtil.putInAdminEditMode();
        AppUtil.putInPerimetreEditMode();
        this.setEnabledAll(true);
        this.setVisibleAll(true);
        this.info.setVisible(false);
        this.lblArgosName.setVisible(false);
        this.panel.reset();
        if (event.getPath() != null) {
            this.lblOUPath.setText(this.messages.perimetretitle() + ": " + event.getPath());
        }
        this.perimetreId = event.getPerimetreId();
        this.perimetreParentId = event.getPerimetreParentId();
        this.btnModifier.setText(this.messages.commonModifierButton());
        this.initData();
        this.clientPerimetreService.findById(event.getPerimetreId(), new AsyncCallback<PerimetreModel>() {

            @Override
            public void onSuccess(PerimetreModel arg0) {
                if (arg0 != null) {
                    NewPerimetrePanel.this.perimetre = arg0;
                    NewPerimetrePanel.this.cbEtj.setValue(arg0.getEntiteJuridique());
                    NewPerimetrePanel.this.txtLibelle.setValue(NewPerimetrePanel.this.perimetre.getName());
                    NewPerimetrePanel.this.cbPerimetreType.setValue(NewPerimetrePanel.this.perimetre.getType());
                    NewPerimetrePanel.this.dfDebut.setValue(NewPerimetrePanel.this.perimetre.getChantierStartDate());
                    NewPerimetrePanel.this.dfPrevisionnelle.setValue(NewPerimetrePanel.this.perimetre.getChantierPlannedEndDate());
                    NewPerimetrePanel.this.dfDefinitive.setValue(NewPerimetrePanel.this.perimetre.getChantierEndDate());
                    // lblCodeProjet.setValue(perimetre.getPerId());

                    NewPerimetrePanel.this.txtVille.setValue(NewPerimetrePanel.this.perimetre.getCity());
                    NewPerimetrePanel.this.txtAdresse.setValue(NewPerimetrePanel.this.perimetre.getAddress());

                    NewPerimetrePanel.this.cbLanguage.setValue(NewPerimetrePanel.this.perimetre.getLanguage());
                    if (NewPerimetrePanel.this.perimetre.getIsSubdelegable() != null) {
                        NewPerimetrePanel.this.cbIsSubDelegable.setValue(NewPerimetrePanel.this.perimetre.getIsSubdelegable() > 0);
                    }
                    NewPerimetrePanel.this.cbSEP.setValue(((NewPerimetrePanel.this.perimetre.getChantierSEP() != null) && (1 == NewPerimetrePanel.this.perimetre
                            .getChantierSEP())) ? true : false);

                    NewPerimetrePanel.this.cbChantierType.setValue(NewPerimetrePanel.this.perimetre.getChantierType());
                    // if (perimetre.getType() != null && ConstantClient.PERIMETER_TYPE_NAME_IS_CHANTIER.equals(perimetre.getType().getName())) {
                    if (NewPerimetrePanel.this.perimetre.getType() != null
                            && CommonUtils.isChantierType(NewPerimetrePanel.this.perimetre.getType().getName())) {
                        NewPerimetrePanel.this.cbGroupment.setValue(((NewPerimetrePanel.this.perimetre.getChantierGroupement() != null) && (1 == NewPerimetrePanel.this.perimetre
                                .getChantierGroupement())) ? true : false);
                        NewPerimetrePanel.this.txtNumeroDeProjet.setValue(NewPerimetrePanel.this.perimetre.getChantierNumeroDeProjet());

                        if (NewPerimetrePanel.this.cbSEP.getValue() || NewPerimetrePanel.this.cbGroupment.getValue()) {
                            NewPerimetrePanel.this.txtChantierPartenaires.setValue(NewPerimetrePanel.this.perimetre.getChantierPartenaires());
                            NewPerimetrePanel.this.txtChantierPartenaires.setVisible(true);
                        } else {
                            NewPerimetrePanel.this.txtChantierPartenaires.setVisible(false);
                        }
                    } else {
                        NewPerimetrePanel.this.cbGroupment.setVisible(false);
                        NewPerimetrePanel.this.txtNumeroDeProjet.setVisible(false);
                        NewPerimetrePanel.this.txtChantierPartenaires.setVisible(false);
                    }
                    // if (perimetre.getEntite().getEntId().equals(ConstantClient.ENTITE_ID_IS_ETDE)) {
                    // if (perimetre.getArgosName() != null) {
                    // lcNonArgos.setVisible(false);
                    // lblArgosName.setVisible(true);
                    // lblArgosName.setValue(perimetre.getArgosName());
                    // } else {
                    // lcNonArgos.setVisible(true);
                    // lblNonArgos.setValue(messages.perimetrenonargosvalue() + " " + perimetre.getCreatedBy().getUserName());
                    // lblArgosName.setVisible(false);
                    // lblArgosName.setValue(null);
                    // }
                    // } else { //BYEFE
                    if (NewPerimetrePanel.this.perimetre.getArgosName() != null) {
                        NewPerimetrePanel.this.lcNonArgos.setVisible(false);
                        NewPerimetrePanel.this.lblArgosName.setVisible(true);
                        NewPerimetrePanel.this.lblArgosName.setValue(NewPerimetrePanel.this.perimetre.getArgosName());
                    } else {
                        NewPerimetrePanel.this.lcNonArgos.setVisible(true);
                        String user = "<Compte utilisateur Windows>";
                        if (NewPerimetrePanel.this.perimetre.getCreatedBy() != null) {
                            user = NewPerimetrePanel.this.perimetre.getCreatedBy().getUserName();
                        }

                        NewPerimetrePanel.this.lblNonArgos.setValue(NewPerimetrePanel.this.messages.perimetrenonargosvalue() + " " + user);
                        NewPerimetrePanel.this.lblArgosName.setVisible(false);
                        NewPerimetrePanel.this.lblArgosName.setValue(null);
                    }
                    // }
                    if (NewPerimetrePanel.this.perimetre.getParent() != null) {
                        NewPerimetrePanel.this.lblRattachement.setValue(NewPerimetrePanel.this.perimetre.getParent().getName());
                    } else {
                        NewPerimetrePanel.this.lblRattachement.setValue(NewPerimetrePanel.this.perimetre.getEntite().getName());
                    }

                    NewPerimetrePanel.this.clientDelegationServiceAsync.findByPerimetre(arg0.getPerId(), new AsyncCallback<List<DelegationModel>>() {

                        @Override
                        public void onSuccess(List<DelegationModel> arg0) {
                            if ((arg0 != null) && (arg0.size() != 0)) {
                                NewPerimetrePanel.this.cbPerimetreType.setEnabled(false);
                            } else {
                                NewPerimetrePanel.this.cbPerimetreType.setEnabled(true);
                            }
                        }

                        @Override
                        public void onFailure(Throwable arg0) {
                        }
                    });
                } else {
                    NewPerimetrePanel.this.lcNonArgos.setVisible(true);
                    NewPerimetrePanel.this.lblArgosName.setVisible(false);
                    NewPerimetrePanel.this.lblNonArgos.setValue(NewPerimetrePanel.this.messages.perimetrenonargosvalue() + " "
                            + SessionServiceImpl.getInstance().getUserContext().getUserName());
                }
            }

            @Override
            public void onFailure(Throwable arg0) {
            }
        });
    }

    private void viewMode(PerimetreEvent event) {
        this.setVisibleAll(true);
        this.setEnabledAll(false);
        this.info.setVisible(false);
        this.lblArgosName.setVisible(false);
        if (event.getPath() != null) {
            this.lblOUPath.setText(this.messages.perimetretitle() + ": " + event.getPath());
        }
        this.perimetreId = event.getPerimetreId();
        this.perimetreParentId = event.getPerimetreParentId();
        this.panel.reset();
        this.btnModifier.setText(this.messages.commonModifierButton());
        this.initData();
        this.clientPerimetreService.findById(event.getPerimetreId(), new AsyncCallback<PerimetreModel>() {

            @Override
            public void onSuccess(PerimetreModel arg0) {
                if (arg0 != null) {
                    NewPerimetrePanel.this.perimetre = arg0;
                    NewPerimetrePanel.this.cbEtj.setValue(arg0.getEntiteJuridique());
                    NewPerimetrePanel.this.txtLibelle.setValue(NewPerimetrePanel.this.perimetre.getName());
                    NewPerimetrePanel.this.cbPerimetreType.setValue(NewPerimetrePanel.this.perimetre.getType());
                    NewPerimetrePanel.this.dfDebut.setValue(NewPerimetrePanel.this.perimetre.getChantierStartDate());
                    NewPerimetrePanel.this.dfPrevisionnelle.setValue(NewPerimetrePanel.this.perimetre.getChantierPlannedEndDate());
                    NewPerimetrePanel.this.dfDefinitive.setValue(NewPerimetrePanel.this.perimetre.getChantierEndDate());
                    // lblCodeProjet.setValue(perimetre.getPerId());

                    NewPerimetrePanel.this.txtVille.setValue(NewPerimetrePanel.this.perimetre.getCity());
                    NewPerimetrePanel.this.txtAdresse.setValue(NewPerimetrePanel.this.perimetre.getAddress());

                    NewPerimetrePanel.this.cbLanguage.setValue(NewPerimetrePanel.this.perimetre.getLanguage());
                    if (NewPerimetrePanel.this.perimetre.getIsSubdelegable() != null) {
                        NewPerimetrePanel.this.cbIsSubDelegable.setValue(NewPerimetrePanel.this.perimetre.getIsSubdelegable() > 0);
                    }
                    NewPerimetrePanel.this.cbSEP.setValue(((NewPerimetrePanel.this.perimetre.getChantierSEP() != null) && (1 == NewPerimetrePanel.this.perimetre
                            .getChantierSEP())) ? true : false);
                    NewPerimetrePanel.this.cbGroupment.setValue(((NewPerimetrePanel.this.perimetre.getChantierGroupement() != null) && (1 == NewPerimetrePanel.this.perimetre
                            .getChantierGroupement())) ? true : false);
                    NewPerimetrePanel.this.txtNumeroDeProjet.setValue(NewPerimetrePanel.this.perimetre.getChantierNumeroDeProjet());

                    if (NewPerimetrePanel.this.cbSEP.getValue() || NewPerimetrePanel.this.cbGroupment.getValue()) {
                        NewPerimetrePanel.this.txtChantierPartenaires.setValue(NewPerimetrePanel.this.perimetre.getChantierPartenaires());
                        NewPerimetrePanel.this.txtChantierPartenaires.setVisible(true);
                    } else {
                        NewPerimetrePanel.this.txtChantierPartenaires.setVisible(false);
                    }
                    // if (perimetre.getType() != null && ConstantClient.PERIMETER_TYPE_NAME_IS_CHANTIER.equals(perimetre.getType().getName())) {
                    if (NewPerimetrePanel.this.perimetre.getType() != null
                            && CommonUtils.isChantierType(NewPerimetrePanel.this.perimetre.getType().getName())) {
                        NewPerimetrePanel.this.cbGroupment.setValue(((NewPerimetrePanel.this.perimetre.getChantierGroupement() != null) && (1 == NewPerimetrePanel.this.perimetre
                                .getChantierGroupement())) ? true : false);
                        NewPerimetrePanel.this.txtNumeroDeProjet.setValue(NewPerimetrePanel.this.perimetre.getChantierNumeroDeProjet());

                        if (NewPerimetrePanel.this.cbSEP.getValue() || NewPerimetrePanel.this.cbGroupment.getValue()) {
                            NewPerimetrePanel.this.txtChantierPartenaires.setValue(NewPerimetrePanel.this.perimetre.getChantierPartenaires());
                            NewPerimetrePanel.this.txtChantierPartenaires.setVisible(true);
                        } else {
                            NewPerimetrePanel.this.txtChantierPartenaires.setVisible(false);
                        }
                    } else {
                        NewPerimetrePanel.this.cbGroupment.setVisible(false);
                        NewPerimetrePanel.this.txtNumeroDeProjet.setVisible(false);
                        NewPerimetrePanel.this.txtChantierPartenaires.setVisible(false);
                    }
                    NewPerimetrePanel.this.cbChantierType.setValue(NewPerimetrePanel.this.perimetre.getChantierType());
                    // if (perimetre.getEntite().getEntId().equals(ConstantClient.ENTITE_ID_IS_ETDE)) {
                    // if (perimetre.getArgosName() != null) {
                    // lblArgosName.setVisible(true);
                    // lblArgosName.setValue(perimetre.getArgosName());
                    // }
                    // }
                    if (NewPerimetrePanel.this.perimetre.getArgosName() != null) {
                        NewPerimetrePanel.this.lcNonArgos.setVisible(false);
                        NewPerimetrePanel.this.lblArgosName.setVisible(true);
                        NewPerimetrePanel.this.lblArgosName.setValue(NewPerimetrePanel.this.perimetre.getArgosName());
                    } else {
                        NewPerimetrePanel.this.lcNonArgos.setVisible(true);
                        String user = "<Compte utilisateur Windows>";
                        if (NewPerimetrePanel.this.perimetre.getCreatedBy() != null) {
                            user = NewPerimetrePanel.this.perimetre.getCreatedBy().getUserName();
                        }

                        NewPerimetrePanel.this.lblNonArgos.setValue(NewPerimetrePanel.this.messages.perimetrenonargosvalue() + " " + user);
                        NewPerimetrePanel.this.lblArgosName.setVisible(false);
                        NewPerimetrePanel.this.lblArgosName.setValue(null);
                    }
                    if (NewPerimetrePanel.this.perimetre.getParent() != null) {
                        NewPerimetrePanel.this.lblRattachement.setValue(NewPerimetrePanel.this.perimetre.getParent().getName());
                    } else {
                        NewPerimetrePanel.this.lblRattachement.setValue(NewPerimetrePanel.this.perimetre.getEntite().getName());
                    }
                }
            }

            @Override
            public void onFailure(Throwable arg0) {
            }
        });
    }

    private void initOuPath() {
        this.lblOUPath = new Label("");
        this.panel.add(this.lblOUPath);
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

        this.perimetreTypes = new ListStore<PerimetreTypeModel>();
        this.cbPerimetreType = new ComboBox<PerimetreTypeModel>();
        this.cbPerimetreType.setFieldLabel(this.messages.perimetretype());
        this.cbPerimetreType.setStore(this.perimetreTypes);
        this.cbPerimetreType.setDisplayField(PerimetreTypeModel.PERIMETRE_TYPE_NAME);
        this.cbPerimetreType.setTriggerAction(TriggerAction.ALL);
        this.cbPerimetreType.setEditable(false);
        this.cbPerimetreType.setSimpleTemplate("<span title=\"{" + this.cbPerimetreType.getDisplayField() + "}\">{"
                + this.cbPerimetreType.getDisplayField() + "}</span>");
        this.cbPerimetreType.setAllowBlank(false);
        lcLeft.add(this.cbPerimetreType, this.formData);

        this.lblRattachement = new LabelField();
        this.lblRattachement.setFieldLabel("Rattachement:");
        // lblRattachement.setVisible(false);
        lcLeft.add(this.lblRattachement, this.formData);

        // setup right layout
        LayoutContainer lcRight = new LayoutContainer();
        FormLayout flRight = new FormLayout();
        flRight.setLabelAlign(LabelAlign.LEFT);
        flRight.setLabelWidth(140);
        lcRight.setLayout(flRight);

        this.lblCodeProjet = new LabelField();
        this.lblCodeProjet.setFieldLabel(this.messages.perimetrecode());
        // TODO Add code project
        // lcRight.add(lblCodeProjet, formData);

        this.etjs = new ListStore<EntiteJuridiqueModel>();
        this.cbEtj = new ComboBox<EntiteJuridiqueModel>();
        this.cbEtj.setFieldLabel(this.messages.perimetreentitejuridique());
        this.cbEtj.setStore(this.etjs);
        this.cbEtj.setEditable(false);
        this.cbEtj.setDisplayField(EntiteJuridiqueModel.ENTITE_JURIDIQUE_NAME);
        this.cbEtj.setTriggerAction(TriggerAction.ALL);
        this.cbEtj.setSimpleTemplate("<span title=\"{" + this.cbEtj.getDisplayField() + "}\">{" + this.cbEtj.getDisplayField() + "}</span>");
        lcRight.add(this.cbEtj, this.formData);

        this.languages = new ListStore<LanguageModel>();
        this.cbLanguage = new ComboBox<LanguageModel>();
        this.cbLanguage.setFieldLabel(this.messages.perimetrelangues());
        this.cbLanguage.setStore(this.languages);
        this.cbLanguage.setDisplayField(LanguageModel.LAG_NAME);
        this.cbLanguage.setTriggerAction(TriggerAction.ALL);
        this.cbLanguage.setEditable(false);
        this.cbLanguage.setSimpleTemplate("<span title=\"{" + this.cbLanguage.getDisplayField() + "}\">{" + this.cbLanguage.getDisplayField()
                + "}</span>");
        lcRight.add(this.cbLanguage, this.formData);

        this.cbIsSubDelegable = new CheckBox();

        this.cbIsSubDelegable.setLabelSeparator(":");
        this.cbIsSubDelegable.setFieldLabel(this.messages.delegationsousdelegable());
        lcLeft.add(this.cbIsSubDelegable, this.formData);

        lcInformation.add(lcLeft, new ColumnData(.5));
        lcInformation.add(lcRight, new ColumnData(.5));

        this.panel.add(lcInformation);
    }

    private void initNonArgosForm() {
        // setup information layout
        this.lcNonArgos = new LayoutContainer();
        this.lcNonArgos.setSize(WIDTH - 20, HEIGHT);
        this.lcNonArgos.setLayout(new ColumnLayout());

        // setup left layout
        LayoutContainer lcLeft = new LayoutContainer();
        lcLeft.setStyleAttribute("paddingRight", "10px");
        FormLayout flLeft = new FormLayout();
        flLeft.setLabelAlign(LabelAlign.LEFT);
        flLeft.setLabelWidth(140);
        lcLeft.setLayout(flLeft);
        flLeft.setLabelSeparator(":");

        this.lblNonArgos = new LabelField();
        this.lblNonArgos.setFieldLabel(this.messages.perimetrenonargos());
        this.lblNonArgos.setValue(this.messages.perimetrenonargosvalue());
        this.lblNonArgos.setVisible(true);
        lcLeft.add(this.lblNonArgos, this.formData);

        this.lcNonArgos.add(lcLeft, new ColumnData(1));

        this.panel.add(this.lcNonArgos);
        this.lcLine1 = new LayoutContainer();
        this.lcLine1.setSize(WIDTH, HEIGHT);
        this.lcLine1.setLayout(new ColumnLayout());
        this.lcLine1.add(new HTML("<hr width='670px'/>"));
        this.lcNonArgos.add(this.lcLine1);
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

        this.lblArgosName = new LabelField();
        this.lblArgosName.setFieldLabel(this.messages.perimetreargosname());
        this.lblArgosName.setVisible(false);
        lcLeft.add(this.lblArgosName, this.formData);

        this.txtLibelle = new TextField<String>();
        this.txtLibelle.setFieldLabel(this.messages.perimetrelibelle());
        this.txtLibelle.setAllowBlank(false);
        lcLeft.add(this.txtLibelle, this.formData);

        this.chantierTypes = new ListStore<ChantierTypeModel>();
        this.cbChantierType = new ComboBox<ChantierTypeModel>();
        this.cbChantierType.setFieldLabel(this.messages.perimetretypechantier());
        this.cbChantierType.setStore(this.chantierTypes);
        this.cbChantierType.setDisplayField(ChantierTypeModel.CTY_LABEL);
        this.cbChantierType.setTriggerAction(TriggerAction.ALL);
        this.cbChantierType.setEditable(false);
        this.cbChantierType.setSimpleTemplate("<span title=\"{" + this.cbChantierType.getDisplayField() + "}\">{"
                + this.cbChantierType.getDisplayField() + "}</span>");
        this.cbChantierType.setVisible(false);
        lcLeft.add(this.cbChantierType, this.formData);

        this.cbPerimetreType.addSelectionChangedListener(new SelectionChangedListener<PerimetreTypeModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<PerimetreTypeModel> se) {
                if (se.getSelectedItem() != null) {
                    // if (ConstantClient.PERIMETER_TYPE_NAME_IS_CHANTIER.equals(se.getSelectedItem().getName())) {
                    if (CommonUtils.isChantierType(se.getSelectedItem().getName())) {
                        NewPerimetrePanel.this.cbSEP.setVisible(true);
                        NewPerimetrePanel.this.dfDebut.setVisible(true);
                        NewPerimetrePanel.this.dfDefinitive.setVisible(true);
                        NewPerimetrePanel.this.dfPrevisionnelle.setVisible(true);
                        NewPerimetrePanel.this.cbChantierType.setVisible(true);
                        NewPerimetrePanel.this.cbGroupment.setVisible(true);
                        NewPerimetrePanel.this.txtNumeroDeProjet.setVisible(true);
                    } else {
                        NewPerimetrePanel.this.cbSEP.setVisible(false);
                        NewPerimetrePanel.this.dfDebut.setVisible(false);
                        NewPerimetrePanel.this.dfDefinitive.setVisible(false);
                        NewPerimetrePanel.this.dfPrevisionnelle.setVisible(false);
                        NewPerimetrePanel.this.cbChantierType.setVisible(false);

                        NewPerimetrePanel.this.cbGroupment.setVisible(false);
                        NewPerimetrePanel.this.txtNumeroDeProjet.setVisible(false);
                    }
                    // change the default sub delegation
                    if (NewPerimetrePanel.this.perimetre == null || NewPerimetrePanel.this.perimetre.getPerId() == null) {
                        NewPerimetrePanel.this.cbIsSubDelegable.setValue(se.getSelectedItem() == null ? null : se.getSelectedItem()
                                .getIsSubdelegable() == null ? null : se.getSelectedItem().getIsSubdelegable() > 0);
                    } else {
                        if (NewPerimetrePanel.this.perimetre.getIsSubdelegable() == null) {
                            NewPerimetrePanel.this.cbIsSubDelegable.setValue(se.getSelectedItem() == null ? null : se.getSelectedItem()
                                    .getIsSubdelegable() == null ? null : se.getSelectedItem().getIsSubdelegable() > 0);
                        } else {
                            if (!NewPerimetrePanel.this.perimetre.getIsSubdelegable().equals(se.getSelectedItem().getIsSubdelegable())) {
                                NewPerimetrePanel.this.cbIsSubDelegable.setValue(se.getSelectedItem() == null ? null : se.getSelectedItem()
                                        .getIsSubdelegable() == null ? null : se.getSelectedItem().getIsSubdelegable() > 0);
                            }
                        }
                    }
                }
            }
        });

        // date debut
        this.dfDebut = new DateField();
        this.dfDebut.setFieldLabel(this.messages.perimetredebut());
        this.dfDebut.setEditable(true);
        this.dfDebut.setFormatValue(true);
        this.dfDebut.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        lcLeft.add(this.dfDebut, this.formData);

        // date previsionnelle
        this.dfPrevisionnelle = new DateField();
        this.dfPrevisionnelle.setFieldLabel(this.messages.perimetreprevisionnelle());
        this.dfPrevisionnelle.setEditable(true);
        this.dfPrevisionnelle.setFormatValue(true);
        this.dfPrevisionnelle.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        lcLeft.add(this.dfPrevisionnelle, this.formData);

        // date definitive
        this.dfDefinitive = new DateField();
        this.dfDefinitive.setFieldLabel(this.messages.perimetredefinitive());
        this.dfDefinitive.setEditable(true);
        this.dfDefinitive.setFormatValue(true);
        this.dfDefinitive.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        lcLeft.add(this.dfDefinitive, this.formData);

        this.txtVille = new TextField<String>();
        this.txtVille.setFieldLabel(this.messages.perimetreville());
        lcLeft.add(this.txtVille, this.formData);

        this.txtAdresse = new TextField<String>();
        this.txtAdresse.setFieldLabel(this.messages.perimetreadresse());
        lcLeft.add(this.txtAdresse, this.formData);

        this.txtNumeroDeProjet = new TextField<String>();
        this.txtNumeroDeProjet.setFieldLabel(this.messages.perimetrenumeroprojet());

        lcLeft.add(this.txtNumeroDeProjet, this.formData);

        this.cbSEP = new CheckBox();
        this.cbSEP.setBoxLabel(this.messages.delegationsep());
        this.cbSEP.setLabelSeparator("");
        this.cbSEP.setEnabled(false);
        this.cbSEP.setStyleAttribute("padding", "0px");
        lcLeft.add(this.cbSEP, this.formData);
        this.cbSEP.addListener(Events.OnClick, new Listener<ComponentEvent>() {

            @Override
            public void handleEvent(ComponentEvent be) {
                if (NewPerimetrePanel.this.cbSEP.getValue() || NewPerimetrePanel.this.cbGroupment.getValue()) {
                    NewPerimetrePanel.this.txtChantierPartenaires.setVisible(true);
                } else {
                    NewPerimetrePanel.this.txtChantierPartenaires.setVisible(false);
                }
            }
        });

        this.cbGroupment = new CheckBox();
        this.cbGroupment.setBoxLabel(this.messages.perimetregroupment());
        this.cbGroupment.setLabelSeparator("");
        this.cbGroupment.setEnabled(false);
        this.cbGroupment.setStyleAttribute("padding", "0px");
        lcLeft.add(this.cbGroupment, this.formData);
        this.cbGroupment.addListener(Events.OnClick, new Listener<ComponentEvent>() {

            @Override
            public void handleEvent(ComponentEvent be) {
                if (NewPerimetrePanel.this.cbSEP.getValue() || NewPerimetrePanel.this.cbGroupment.getValue()) {
                    NewPerimetrePanel.this.txtChantierPartenaires.setVisible(true);
                } else {
                    NewPerimetrePanel.this.txtChantierPartenaires.setVisible(false);
                }
            }
        });

        this.txtChantierPartenaires = new TextField<String>();
        this.txtChantierPartenaires.setFieldLabel(this.messages.perimetrepartenaires());
        lcLeft.add(this.txtChantierPartenaires, this.formData);

        lcInformation.add(lcLeft, new ColumnData(.5));

        this.panel.add(lcInformation);
    }

    private void applyData() {
        this.perimetre.setEntiteJuridique(this.cbEtj.getValue());
        this.perimetre.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
        this.perimetre.setName(this.txtLibelle.getValue());
        this.perimetre.setType(this.cbPerimetreType.getValue());
        if (this.dfDebut.isVisible()) {
            this.perimetre.setChantierStartDate(this.dfDebut.getValue());
        }
        if (this.dfPrevisionnelle.isVisible()) {
            this.perimetre.setChantierPlannedEndDate(this.dfPrevisionnelle.getValue());
        }
        if (this.dfDefinitive.isVisible()) {
            this.perimetre.setChantierEndDate(this.dfDefinitive.getValue());
        }
        if (this.cbSEP.isVisible()) {
            this.perimetre.setChantierSEP(this.cbSEP.getValue() ? 1 : 0);
        }
        if (this.cbChantierType.isVisible()) {
            this.perimetre.setChantierType(this.cbChantierType.getValue());
        }
        if (this.txtVille.isVisible()) {
            this.perimetre.setCity(this.txtVille.getValue());
        }
        if (this.txtAdresse.isVisible()) {
            this.perimetre.setAddress(this.txtAdresse.getValue());
        }
        this.perimetre.setLanguage(this.cbLanguage.getValue());
        if (this.cbIsSubDelegable.getValue() != null) {
            this.perimetre.setIsSubdelegable(this.cbIsSubDelegable.getValue() ? 1 : 0);

        }
        if (this.cbGroupment.isVisible()) {
            this.perimetre.setChantierGroupement(this.cbGroupment.getValue() ? 1 : 0);
        }
        if (this.txtNumeroDeProjet.isVisible()) {
            this.perimetre.setChantierNumeroDeProjet(this.txtNumeroDeProjet.getValue());
        }
        if (this.txtChantierPartenaires.isVisible()) {
            this.perimetre.setChantierPartenaires(this.txtChantierPartenaires.getValue());
        }
    }

    private void initButton() {
        // add button
        this.btnAnnuler = new Button(this.messages.delegationformannuler());
        this.btnAnnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                AppUtil.removeAdminInEditMode();
                AppUtil.removePerminetreEditMode();
                if (NewPerimetrePanel.this.perimetre.getPerId() != null) {
                    PerimetreEvent event = new PerimetreEvent();
                    // event.setPerimetreId(perimetre.getPerId());
                    // event.setPerimetreParentId(perimetreParentId);
                    event.setMode(PerimetreEvent.MODE_IS_NOT_SELECTED);
                    NewPerimetrePanel.this.bus.fireEvent(event);
                } else if (NewPerimetrePanel.this.perimetreParentId != null) {
                    PerimetreEvent event = new PerimetreEvent();
                    // event.setPerimetreId(perimetreParentId);
                    event.setMode(PerimetreEvent.MODE_IS_NOT_SELECTED);
                    NewPerimetrePanel.this.bus.fireEvent(event);
                } else {
                    NewPerimetrePanel.this.showNoPerimetre();
                }
            }
        });
        // remove button
        this.btnModifier = new Button(this.messages.commonAddButton());

        this.btnModifier.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if ((NewPerimetrePanel.this.perimetre != null) && (NewPerimetrePanel.this.panel.isValid())) {
                    NewPerimetrePanel.this.applyData();

                    if (NewPerimetrePanel.this.perimetre.getPerId() == null) {
                        NewPerimetrePanel.this.perimetre.setCreatedBy(SessionServiceImpl.getInstance().getUserContext());
                        NewPerimetrePanel.this.clientPerimetreService.insert(NewPerimetrePanel.this.perimetreParentId,
                                NewPerimetrePanel.this.perimetre, new AsyncCallback<String>() {

                                    @Override
                                    public void onSuccess(String arg0) {
                                        if ("".equals(arg0)) {
                                            Info.display(NewPerimetrePanel.this.messages.commonerror(),
                                                    NewPerimetrePanel.this.messages.perimetresavefailed());
                                        } else {
                                            Info.display(NewPerimetrePanel.this.messages.commoninfo(),
                                                    NewPerimetrePanel.this.messages.perimetresavesuccess());
                                            PerimetreEvent event = new PerimetreEvent();
                                            event.setPerimetreId(arg0);
                                            event.setMode(PerimetreEvent.MODE_IS_VIEW);
                                            AdministrationTreeEvent treeEvent = new AdministrationTreeEvent();
                                            treeEvent.setParentId(NewPerimetrePanel.this.perimetreParentId);
                                            treeEvent.setPerId(arg0);
                                            NewPerimetrePanel.this.bus.fireEvent(event);
                                            NewPerimetrePanel.this.bus.fireEvent(treeEvent);
                                            AppUtil.removeAdminInEditMode();
                                            AppUtil.removePerminetreEditMode();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Throwable arg0) {
                                        Info.display("Infos", "Insert failed");
                                    }
                                });
                    } else {
                        NewPerimetrePanel.this.clientPerimetreService.update(NewPerimetrePanel.this.perimetre, new AsyncCallback<Boolean>() {

                            @Override
                            public void onSuccess(Boolean arg0) {
                                if (arg0) {
                                    Info.display(NewPerimetrePanel.this.messages.commoninfo(),
                                            NewPerimetrePanel.this.messages.perimetreupdatesuccess());
                                    PerimetreEvent event = new PerimetreEvent();
                                    event.setPerimetreId(NewPerimetrePanel.this.perimetre.getPerId());
                                    event.setMode(PerimetreEvent.MODE_IS_VIEW);
                                    AdministrationTreeEvent treeEvent = new AdministrationTreeEvent();
                                    treeEvent.setParentId(NewPerimetrePanel.this.perimetreParentId);
                                    treeEvent.setPerId(NewPerimetrePanel.this.perimetre.getPerId());
                                    NewPerimetrePanel.this.bus.fireEvent(event);
                                    NewPerimetrePanel.this.bus.fireEvent(treeEvent);
                                    AppUtil.removeAdminInEditMode();
                                } else {
                                    Info.display(NewPerimetrePanel.this.messages.commonerror(),
                                            NewPerimetrePanel.this.messages.perimetreupdatefailed());
                                }
                            }

                            @Override
                            public void onFailure(Throwable arg0) {
                                Info.display(NewPerimetrePanel.this.messages.commoninfo(), NewPerimetrePanel.this.messages.perimetreupdatefailed());
                            }
                        });
                    }
                }
            }
        });
        this.panel.addButton(this.btnAnnuler);
        this.panel.addButton(this.btnModifier);
    }

    private void initData() {
        final String entId = SessionServiceImpl.getInstance().getEntiteContext().getEntId();
        this.clientPerimetreTypeService.getPerimetreTypes(entId, new AsyncCallback<List<PerimetreTypeModel>>() {

            @Override
            public void onSuccess(List<PerimetreTypeModel> arg0) {
                NewPerimetrePanel.this.perimetreTypes.removeAll();
                NewPerimetrePanel.this.perimetreTypes.add(arg0);
                if (NewPerimetrePanel.this.cbPerimetreType.getValue() == null) {
                    // add BYTP
                    // if (ConstantClient.ENTITE_ID_IS_BYEFE.equals(entId)) {
                    if (CommonUtils.belongsBYEFEGroup(entId)) {
                        if (arg0 != null && arg0.size() > 0) {
                            PerimetreTypeModel ptm = arg0.get(0);
                            NewPerimetrePanel.this.cbPerimetreType.select(0);
                            NewPerimetrePanel.this.cbPerimetreType.setValue(ptm);
                        }
                    } else { // ETDE - default is site
                        NewPerimetrePanel.this.perimetreTypes.addFilter(new StoreFilter<PerimetreTypeModel>() {

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

                        PerimetreTypeModel ptm = this.findSiteType(arg0);
                        if (ptm != null) {
                            NewPerimetrePanel.this.cbPerimetreType.select(ptm);
                            NewPerimetrePanel.this.cbPerimetreType.setValue(ptm);
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

            @Override
            public void onFailure(Throwable arg0) {
            }
        });

        this.clientChantierTypeService.findChantierByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallback<List<ChantierTypeModel>>() {

                    @Override
                    public void onSuccess(List<ChantierTypeModel> arg0) {
                        NewPerimetrePanel.this.chantierTypes.removeAll();
                        NewPerimetrePanel.this.chantierTypes.add(arg0);
                        if (NewPerimetrePanel.this.cbChantierType.getValue() == null) {
                            if (arg0 != null && arg0.size() > 0) {
                                ChantierTypeModel ptm = arg0.get(0);
                                NewPerimetrePanel.this.cbChantierType.select(0);
                                NewPerimetrePanel.this.cbChantierType.setValue(ptm);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                    }
                });

        this.clientLanguageServiceAsync.getLanguages(new AsyncCallback<List<LanguageModel>>() {

            @Override
            public void onSuccess(List<LanguageModel> arg0) {
                NewPerimetrePanel.this.languages.removeAll();
                NewPerimetrePanel.this.languages.add(arg0);

                if (NewPerimetrePanel.this.cbLanguage.getValue() == null) {
                    if (arg0 != null && arg0.size() > 0) {
                        LanguageModel ptm = arg0.get(0);
                        NewPerimetrePanel.this.cbLanguage.select(0);
                        NewPerimetrePanel.this.cbLanguage.setValue(ptm);
                    }
                }
            }

            @Override
            public void onFailure(Throwable arg0) {
            }
        });

        this.clientEntiteJuridiqueServiceAsync.findByEntiteId(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallback<List<EntiteJuridiqueModel>>() {

                    @Override
                    public void onSuccess(List<EntiteJuridiqueModel> arg0) {
                        NewPerimetrePanel.this.etjs.removeAll();
                        NewPerimetrePanel.this.etjs.add(arg0);

                        if (arg0.isEmpty() == false) {
                            for (EntiteJuridiqueModel entiteJuridiqueModel : arg0) {
                                if (entiteJuridiqueModel.getIsDefault() != null && entiteJuridiqueModel.getIsDefault().intValue() == 1) {
                                    NewPerimetrePanel.this.cbEtj.setValue(entiteJuridiqueModel);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                    }
                });
    }
}
