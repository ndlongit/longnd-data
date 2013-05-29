package com.structis.vip.client.panel.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.DateTimePropertyEditor;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.event.control.ListControleEvent;
import com.structis.vip.client.event.control.RefreshExternControllerGridEvent;
import com.structis.vip.client.event.control.RefreshExternControllerGridHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientCollaborateurServiceAsync;
import com.structis.vip.client.service.ClientControlServiceAsync;
import com.structis.vip.client.service.ClientControlTypeServiceAsync;
import com.structis.vip.client.service.ClientExternControllerControlServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.ControlModel;
import com.structis.vip.shared.model.ControlTypeModel;
import com.structis.vip.shared.model.ExtControllerControlModel;
import com.structis.vip.shared.model.PerimetreTreeModel;

public class NewControlFormPanel extends FormPanel {

    private final static int WIDTH = 700;
    private final static int LABEL_WIDTH = 120;
    private final static int HEIGHT = -1;

    private final Messages messages = GWT.create(Messages.class);
    private SimpleEventBus bus;

    private LabelField lblOrganisationNom;
    private LabelField lblPerimetreAssocie;
    private TextField<String> lblCodeProjet;
    private ComboBox<ControlTypeModel> cbControlType;
    private DateField dfControlDate;
    private ComboBox<CollaborateurModel> cbCollaborateur;
    private ColumnModel controllerColumnModel;
    private Grid<ExtControllerControlModel> externControllerGrid;
    private FieldSet fsExternController;
    private Button btnSelect;
    private FileUploadField ffFile;
    private final FormData formData = new FormData("35%");
    private Button btnAnnuler;
    private Button btnModifier;

    private RadioGroup controlerGroup;
    private Radio internalRadio;
    private Radio externalRadio;

    private ListStore<ControlTypeModel> lstControlTypes = new ListStore<ControlTypeModel>();
    private ListStore<CollaborateurModel> lstCollaborateurs = new ListStore<CollaborateurModel>();
    private ListStore<ExtControllerControlModel> lstExternControlers = new ListStore<ExtControllerControlModel>();

    private ClientControlTypeServiceAsync clientControlTypeServiceAsync = ClientControlTypeServiceAsync.Util.getInstance();
    private ClientCollaborateurServiceAsync clientCollaborateurServiceAsync = ClientCollaborateurServiceAsync.Util.getInstance();
    private ClientControlServiceAsync clientControlServiceAsync = ClientControlServiceAsync.Util.getInstance();
    private ClientExternControllerControlServiceAsync clientExternControllerControlServiceAsync = ClientExternControllerControlServiceAsync.Util
            .getInstance();

    private ControlModel currentControle;
    private PerimetreTreeModel perimetreTreeModel;

    public NewControlFormPanel(SimpleEventBus bus) {
        this.setAction(GWT.getHostPageBaseURL() + ".uploadRapportServlet");
        this.setEncoding(Encoding.MULTIPART);
        this.setMethod(Method.POST);
        this.bus = bus;
        this.setHeading(this.messages.delegationformheading());
        this.setFrame(true);
        this.setCollapsible(false);
        this.setLayout(new FlowLayout());
        this.setScrollMode(Scroll.AUTO);
        this.addBackLink();
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
        this.fsExternController = this.initFieldSets();
        this.add(this.fsExternController);
        this.initBottomForm();
        // add buttons
        this.initButtons();
        this.handleEvents();
    }

    private void handleEvents() {
        this.bus.addHandler(RefreshExternControllerGridEvent.getType(), new RefreshExternControllerGridHandler() {

            @Override
            public void onLoadAction(RefreshExternControllerGridEvent event) {
                NewControlFormPanel.this.lstExternControlers.removeAll();
                if (event.getExternControllers() != null) {
                    NewControlFormPanel.this.lstExternControlers.add(event.getExternControllers());
                }
                // clientExternControllerControlServiceAsync.findByControl(currentControle.getId(), new
                // AsyncCallback<List<ExtControllerControlModel>>() {
                // @Override
                // public void onSuccess(List<ExtControllerControlModel> arg0) {
                // lstExternControlers.removeAll();
                // if (arg0 != null ) {
                // lstExternControlers.add(arg0);
                // }
                // }
                // @Override
                // public void onFailure(Throwable arg0) {
                // }
                // });
            }
        });
    }

    private void initBottomForm() {
        LayoutContainer left = new LayoutContainer();
        FormLayout layout = new FormLayout();
        layout.setLabelAlign(LabelAlign.RIGHT);
        layout.setLabelWidth(LABEL_WIDTH);

        left.setBorders(false);
        left.setLayout(layout);
        left.setStyleAttribute("paddingLeft", "5px");

        this.ffFile = new FileUploadField();
        // ffFile.setAllowBlank(false);
        this.ffFile.setWidth(400);
        this.ffFile.setName("uploadedfile");
        this.ffFile.setFieldLabel(this.messages.controlerapportfile());
        left.add(this.ffFile, this.formData);
        this.add(left);
    }

    private FieldSet initFieldSets() {
        FieldSet fsControlerExternal = new FieldSet();
        fsControlerExternal.setHeading(this.messages.controlerexternalfieldset());
        fsControlerExternal.setCollapsible(true);
        fsControlerExternal.setWidth(WIDTH);

        // setup grid for external controlers
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        // Column documents
        ColumnConfig column = new ColumnConfig();
        column.setId("externalController.name");
        column.setHeader(this.messages.controlgridfullname());
        column.setWidth(300);
        column.setRowHeader(true);
        column.setSortable(true);
        configs.add(column);

        // setup column model
        this.controllerColumnModel = new ColumnModel(configs);

        this.lstExternControlers = new ListStore<ExtControllerControlModel>();
        this.externControllerGrid = new Grid<ExtControllerControlModel>(this.lstExternControlers, this.controllerColumnModel);
        this.externControllerGrid.setStyleAttribute("borderTop", "none");
        this.externControllerGrid.setHeight(200);

        this.externControllerGrid.setAutoExpandColumn("externalController.name");
        this.externControllerGrid.setBorders(false);
        this.externControllerGrid.setStripeRows(true);
        this.externControllerGrid.setColumnLines(true);
        this.externControllerGrid.setColumnReordering(true);

        // externControllerGrid.getAriaSupport().setLabelledBy(cp.getId() + "-label");
        fsControlerExternal.add(this.externControllerGrid);

        this.btnSelect = new Button(this.messages.selectexterncontroller());
        fsControlerExternal.add(this.btnSelect, new MarginData(3, 0, 0, 0));
        this.btnSelect.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                // if (currentControle.getId() != null && currentControle.getId() != 0) {
                ApplyExternControllerDialog dialog = new ApplyExternControllerDialog(NewControlFormPanel.this.bus);
                dialog.setData(SessionServiceImpl.getInstance().getEntiteContext(), NewControlFormPanel.this.currentControle,
                        NewControlFormPanel.this.lstExternControlers.getModels());
                dialog.show();
                // }
            }
        });
        return fsControlerExternal;
    }

    private void initButtons() {
        ContentPanel btPanel = new ContentPanel();
        btPanel.setSize(WIDTH, HEIGHT);
        btPanel.add(new HTML("<hr width='680px'/>"));
        btPanel.setButtonAlign(HorizontalAlignment.RIGHT);
        btPanel.setHeaderVisible(false);
        // btPanel.setBodyBorder(false);
        this.btnAnnuler = new Button(this.messages.commonAnnulerButton());
        this.btnModifier = new Button(this.messages.commonValiderButton());
        btPanel.addButton(this.btnAnnuler);
        btPanel.addButton(this.btnModifier);
        this.add(btPanel);
        this.btnAnnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                NewControlFormPanel.this.returnControleList(false);
            }
        });
        this.btnModifier.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (NewControlFormPanel.this.isValid()) {
                    NewControlFormPanel.this.submit();
                }
                // updateControle();
            }
        });
        this.addListener(Events.BeforeSubmit, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                if (NewControlFormPanel.this.ffFile.getValue() != null) {
                    String fileName = NewControlFormPanel.this.ffFile.getValue();

                    if (fileName != null && !"".equals(fileName)) {
                        int lastDot = fileName.lastIndexOf(".");
                        String extFile = fileName.substring(lastDot, fileName.length()).toLowerCase();
                        if (!ClientConstant.PDF_EXTENSION_FILE.equals(extFile)) {
                            Window.alert("Document doit être un fichier pdf");
                            be.setCancelled(true);
                        }
                    } else {
                        be.setCancelled(true);
                        NewControlFormPanel.this.updateControle(fileName);
                    }
                }
            }
        });

        this.addListener(Events.Submit, new Listener<FormEvent>() {

            @Override
            public void handleEvent(FormEvent be) {
                String fileName = null;
                if ((NewControlFormPanel.this.ffFile.getValue() != null) && (!"".equals(NewControlFormPanel.this.ffFile.getValue()))) {
                    fileName = NewControlFormPanel.this.ffFile.getValue();
                }
                NewControlFormPanel.this.updateControle(fileName);
            }
        });
    }

    protected void updateControle(String fileName) {
        if (this.isValid()) {
            if (this.controlerGroup.getValue() == this.internalRadio || this.lstExternControlers.getCount() > 0) {
                this.applyFormDataToModel(fileName);
                this.callUpdateControlService();
            } else {
                Window.alert("Please add an extern controller");
            }

        }
    }

    private void callUpdateControlService() {
        if (this.currentControle.getId() != null) {
            this.clientControlServiceAsync.update(this.currentControle, new AsyncCallback<ControlModel>() {

                @Override
                public void onFailure(Throwable arg0) {
                }

                @Override
                public void onSuccess(ControlModel arg0) {
                    // currentControle.setCharacter(arg0.getCharacter());
                    // currentControle.setCollaborateur(arg0.getCollaborateur());
                    // currentControle.setCodeProject(arg0.getCodeProject());
                    // currentControle.setControlType(arg0.getControlType());
                    // currentControle.setDate(arg0.getDate());
                    // currentControle.setExternControllers(currentControle.getExternControllers());
                    NewControlFormPanel.this.returnControleList(true);
                }

            });
        } else {
            this.clientControlServiceAsync.insert(this.currentControle, new AsyncCallback<ControlModel>() {

                @Override
                public void onFailure(Throwable arg0) {
                }

                @Override
                public void onSuccess(ControlModel arg0) {

                    NewControlFormPanel.this.currentControle = arg0;
                    NewControlFormPanel.this.currentControle.setPermissionByRole(NewControlFormPanel.this.perimetreTreeModel.getIsLectureControl(),
                            NewControlFormPanel.this.perimetreTreeModel.getIsModificationControl());
                    NewControlFormPanel.this.currentControle.setExternControllers(NewControlFormPanel.this.lstExternControlers.getModels());
                    NewControlFormPanel.this.currentControle.updateExtControllerNames();
                    NewControlFormPanel.this.returnControleList(true);
                }

            });

        }
    }

    private void applyFormDataToModel(String fileName) {
        this.currentControle.setCodeProject(this.lblCodeProjet.getValue());
        this.currentControle.setControlType(this.cbControlType.getValue());
        this.currentControle.setDate(this.dfControlDate.getValue());
        this.currentControle.setCharacter(this.controlerGroup.getValue().getBoxLabel());
        if (this.controlerGroup.getValue() == this.internalRadio) {
            this.currentControle.setCollaborateur(this.cbCollaborateur.getValue());
            this.currentControle.setExternControllers(null);
        } else {
            this.currentControle.setCollaborateur(null);
            this.currentControle.setExternControllers(this.lstExternControlers.getModels());
        }
        this.currentControle.setRapport(fileName);
        // currentControle.setRapport(ffFile.getValue());
    }

    private void initInformationForm() {
        LayoutContainer lcInfo = new LayoutContainer();
        lcInfo.setSize(WIDTH, HEIGHT);
        lcInfo.setLayout(new ColumnLayout());

        LayoutContainer left = new LayoutContainer();
        FormLayout layout = new FormLayout();
        layout.setLabelAlign(LabelAlign.RIGHT);
        layout.setLabelWidth(LABEL_WIDTH);

        left.setBorders(false);
        left.setLayout(layout);
        left.setStyleAttribute("paddingLeft", "5px");

        LayoutContainer right = new LayoutContainer();
        FormLayout layout2 = new FormLayout();
        layout2.setLabelAlign(LabelAlign.RIGHT);
        right.setLayout(layout2);
        right.setStyleAttribute("paddingLeft", "5px");

        this.lblCodeProjet = new TextField<String>();
        this.lblCodeProjet.setAllowBlank(false);
        this.lblCodeProjet.setFieldLabel(this.messages.controlecodeprojet());
        this.lblCodeProjet.setValue("");
        left.add(this.lblCodeProjet);

        this.cbControlType = new ComboBox<ControlTypeModel>();
        this.cbControlType.setEditable(false);
        this.cbControlType.setTriggerAction(TriggerAction.ALL);
        this.cbControlType.setAllowBlank(false);
        this.cbControlType.setFieldLabel(this.messages.controletype());
        this.cbControlType.setDisplayField(ControlTypeModel.CON_LABEL);
        this.cbControlType.setStore(this.lstControlTypes);
        left.add(this.cbControlType);

        right.add(new LabelField(""));
        this.dfControlDate = new DateField();
        this.dfControlDate.setId("dfDebut");
        this.dfControlDate.setFieldLabel(this.messages.datedecontrole());
        this.dfControlDate.setEditable(true);
        this.dfControlDate.setFormatValue(true);
        this.dfControlDate.setAllowBlank(false);
        this.dfControlDate.setValue(new Date());
        this.dfControlDate.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        right.add(this.dfControlDate);

        this.controlerGroup = new RadioGroup();
        this.controlerGroup.setFieldLabel(this.messages.controlertype());
        this.internalRadio = new Radio();
        this.externalRadio = new Radio();

        this.internalRadio.setBoxLabel(this.messages.controlerinternal());
        this.externalRadio.setBoxLabel(this.messages.controlerexternal());

        this.controlerGroup.setStyleAttribute("padding-left", "20px");

        this.controlerGroup.add(this.internalRadio);
        this.controlerGroup.add(this.externalRadio);
        this.controlerGroup.addListener(Events.Change, new Listener<FieldEvent>() {

            @Override
            public void handleEvent(FieldEvent fe) {
                if (NewControlFormPanel.this.controlerGroup.getValue() == NewControlFormPanel.this.internalRadio) {
                    NewControlFormPanel.this.cbCollaborateur.setVisible(true);
                    NewControlFormPanel.this.cbCollaborateur.setAllowBlank(false);
                    NewControlFormPanel.this.fsExternController.setVisible(false);
                } else {
                    NewControlFormPanel.this.cbCollaborateur.setVisible(false);
                    NewControlFormPanel.this.cbCollaborateur.setAllowBlank(true);
                    NewControlFormPanel.this.fsExternController.setVisible(true);
                }
            }
        });

        left.add(this.controlerGroup);

        this.cbCollaborateur = new ComboBox<CollaborateurModel>();
        this.cbCollaborateur.setEditable(true);

        this.cbCollaborateur.setValidator(new Validator() {

            @Override
            public String validate(Field<?> field, String value) {
                if (field == NewControlFormPanel.this.cbCollaborateur && NewControlFormPanel.this.cbCollaborateur.getValue() == null) {
                    return NewControlFormPanel.this.messages.controleinternnotexiste();
                }
                return null;
            }

        });
        this.cbCollaborateur.setTriggerAction(TriggerAction.ALL);
        this.cbCollaborateur.setFieldLabel(this.messages.controlercollaborateur());
        this.cbCollaborateur.setDisplayField(CollaborateurModel.COLLA_FULL_NAME);
        this.cbCollaborateur.setStore(this.lstCollaborateurs);
        left.add(this.cbCollaborateur);

        lcInfo.add(left);
        lcInfo.add(right);
        this.add(lcInfo);
    }

    private void initTopForm() {
        LayoutContainer lcTop = new LayoutContainer();
        lcTop.setSize(WIDTH, HEIGHT);
        lcTop.setLayout(new ColumnLayout());
        LayoutContainer left = new LayoutContainer();
        FormLayout layout = new FormLayout();
        layout.setLabelAlign(LabelAlign.RIGHT);
        layout.setLabelWidth(LABEL_WIDTH);

        left.setBorders(false);
        left.setLayout(layout);
        left.setStyleAttribute("paddingLeft", "5px");

        LayoutContainer right = new LayoutContainer();
        FormLayout layout2 = new FormLayout();
        layout2.setLabelAlign(LabelAlign.RIGHT);
        right.setLayout(layout2);
        right.setStyleAttribute("paddingLeft", "5px");

        this.lblOrganisationNom = new LabelField();
        this.lblOrganisationNom.setFieldLabel(this.messages.controleorganisationnom());
        this.lblOrganisationNom.setText("");
        this.lblPerimetreAssocie = new LabelField();
        this.lblPerimetreAssocie.setFieldLabel(this.messages.controleperimetreassocie());
        this.lblPerimetreAssocie.setText("");
        left.add(this.lblOrganisationNom);
        right.add(this.lblPerimetreAssocie);
        lcTop.add(left);
        lcTop.add(right);
        this.add(lcTop);
    }

    private void addBackLink() {
        LayoutContainer backLink = new LayoutContainer();
        backLink.setSize(WIDTH, HEIGHT);
        Label lblBack = new Label(this.messages.retourlistcontrole());

        lblBack.setStyleName("x-link-item");
        backLink.setStyleAttribute("margin-bottom", "20px");
        backLink.add(lblBack);

        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                NewControlFormPanel.this.returnControleList(false);

                // if (!AppUtil.checkToShowWarningInEditMode() ) {
                NewControlFormPanel.this.resetForm();

                // documentMdlModels.removeAll();
                // ContentEvent contentEvent = new ContentEvent();
                // contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GRID_DELEGATION_PANEL);
                // bus.fireEvent(contentEvent);
                // }
            }
        });

        this.add(backLink);

    }

    /**
     * reset data in form
     */
    private void resetForm() {
        this.reset();
    }

    private void returnControleList(boolean refresh) {
        ListControleEvent lce = new ListControleEvent();
        lce.setRefresh(refresh);
        if (refresh) {
            lce.setControlModel(this.currentControle);
        }
        this.bus.fireEvent(lce);
    }

    private void displayFormData() {
        String orgName = (this.currentControle.getPerimetre()) == null ? null : this.currentControle.getPerimetre().getName();
        this.lblOrganisationNom.setText(orgName);
        this.lblPerimetreAssocie.setText(this.currentControle.getPerimetreParent());
        if (this.currentControle.getId() == null) {
            if (this.currentControle.getPerimetre() != null) {
                this.lblCodeProjet.setValue(this.currentControle.getPerimetre().getChantierNumeroDeProjet());
            }
        } else {
            this.lblCodeProjet.setValue(this.currentControle.getCodeProject());
        }
        this.cbControlType.setValue(this.currentControle.getControlType());
        this.dfControlDate.setValue(this.currentControle.getDate());
        if (this.currentControle.getCharacter() == null || this.currentControle.getCharacter().equalsIgnoreCase(ControlModel.INTERNAL)) {
            this.cbCollaborateur.setValue(this.currentControle.getCollaborateur());
            this.cbCollaborateur.setVisible(true);
            this.cbCollaborateur.setAllowBlank(false);
            this.fsExternController.setVisible(false);
            this.controlerGroup.setValue(this.internalRadio);
        } else {
            this.cbCollaborateur.setValue(null);
            this.cbCollaborateur.setAllowBlank(true);
            this.fsExternController.setVisible(true);
            this.controlerGroup.setValue(this.externalRadio);
        }

        this.ffFile.setValue(this.currentControle.getRapport());

    }

    public void applyControlModel(ControlModel controlModel, PerimetreTreeModel perimetreTree) {
        this.currentControle = controlModel;
        this.perimetreTreeModel = perimetreTree;
        if (ControlModel.EXTERNAL.equalsIgnoreCase(this.currentControle.getCharacter())) {

            this.clientExternControllerControlServiceAsync.findByControl(controlModel.getId(), new AsyncCallback<List<ExtControllerControlModel>>() {

                @Override
                public void onSuccess(List<ExtControllerControlModel> arg0) {
                    NewControlFormPanel.this.currentControle.setExternControllers(arg0);
                    NewControlFormPanel.this.lstExternControlers.removeAll();
                    if (arg0 != null) {
                        NewControlFormPanel.this.lstExternControlers.add(arg0);
                    }
                }

                @Override
                public void onFailure(Throwable arg0) {
                }
            });

        }
        this.displayFormData();
    }

    public void setControlModel(ControlModel controlModel) {
        this.currentControle = controlModel;
    }

    public ControlModel getControlModel() {
        return this.currentControle;
    }

    public void loadInitData() {
        // add BYTP

        // clientControlTypeServiceAsync.findByEntite(ConstantClient.ENTITE_ID_IS_BYEFE,new AsyncCallback<List<ControlTypeModel>>() {
        this.clientControlTypeServiceAsync.findByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallback<List<ControlTypeModel>>() {

                    @Override
                    public void onSuccess(List<ControlTypeModel> arg0) {
                        NewControlFormPanel.this.lstControlTypes.removeAll();
                        NewControlFormPanel.this.lstControlTypes.add(arg0);
                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                    }
                });
        // add BYTP
        // clientCollaborateurServiceAsync.getAllCollaborateursByEntiteId(ConstantClient.ENTITE_ID_IS_BYEFE, false, new
        // AsyncCallback<List<CollaborateurModel>>() {
        this.clientCollaborateurServiceAsync.getAllCollaborateursByEntiteId(SessionServiceImpl.getInstance().getEntiteContext().getEntId(), false,
                new AsyncCallback<List<CollaborateurModel>>() {

                    @Override
                    public void onSuccess(List<CollaborateurModel> arg0) {
                        NewControlFormPanel.this.lstCollaborateurs.removeAll();
                        NewControlFormPanel.this.lstCollaborateurs.add(arg0);
                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                    }
                });
        // private ListStore<CollaborateurModel> lstCollaborateurs = new ListStore<CollaborateurModel>();
    }
}
