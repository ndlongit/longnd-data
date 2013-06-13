package com.structis.vip.client.panel.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ComponentManager;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.event.control.EditControleEvent;
import com.structis.vip.client.event.control.ListControleEvent;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientExternControllerControlServiceAsync;
import com.structis.vip.shared.model.ControlModel;
import com.structis.vip.shared.model.ExtControllerControlModel;
import com.structis.vip.shared.model.PerimetreTreeModel;

public class ViewControlPanel extends FormPanel {

    private final static int WIDTH = 700;
    private final static int LABEL_WIDTH = 120;
    private final static int HEIGHT = -1;

    private final Messages messages = GWT.create(Messages.class);
    private SimpleEventBus bus;

    private LabelField lblOrganisationNom;
    private LabelField lblPerimetreAssocie;
    private LabelField lblCodeProjet;
    private LabelField cbControlType;
    private LabelField dfControlDate;
    private LabelField cbCollaborateur;
    private ColumnModel controllerColumnModel;
    private Grid<ExtControllerControlModel> externControllerGrid;
    private FieldSet fsExternController;
    private LabelField ffFile;
    private final FormData formData = new FormData("35%");
    private Button btnAnnuler;
    private Button btnModifier;
    private LabelField controlerGroup;

    // private ListStore<ControlTypeModel> lstControlTypes = new ListStore<ControlTypeModel>();
    // private ListStore<CollaborateurModel> lstCollaborateurs = new ListStore<CollaborateurModel>();
    private ListStore<ExtControllerControlModel> lstExternControlers = new ListStore<ExtControllerControlModel>();

    // private ClientControlTypeServiceAsync clientControlTypeServiceAsync = ClientControlTypeServiceAsync.Util.getInstance();
    // private ClientCollaborateurServiceAsync clientCollaborateurServiceAsync = ClientCollaborateurServiceAsync.Util.getInstance();

    private ClientExternControllerControlServiceAsync clientExternControllerControlServiceAsync = ClientExternControllerControlServiceAsync.Util
            .getInstance();

    private ControlModel currentControle;

    public ViewControlPanel(SimpleEventBus bus) {
        this.bus = bus;
        this.setHeading(this.messages.delegationformheading());
        this.setFrame(true);
        this.setCollapsible(false);
        this.setLayout(new FlowLayout());
        this.setScrollMode(Scroll.AUTO);
        this.setLabelSeparator(":");
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
    }

    private void initBottomForm() {
        LayoutContainer left = new LayoutContainer();
        FormLayout layout = new FormLayout();
        layout.setLabelAlign(LabelAlign.RIGHT);
        layout.setLabelWidth(LABEL_WIDTH);

        left.setBorders(false);
        left.setLayout(layout);
        left.setStyleAttribute("paddingLeft", "5px");

        this.ffFile = new LabelField();
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
        this.externControllerGrid.setHeight(100);
        this.externControllerGrid.setAutoExpandColumn("externalController.name");
        this.externControllerGrid.setBorders(false);
        this.externControllerGrid.setStripeRows(true);
        this.externControllerGrid.setColumnLines(true);
        this.externControllerGrid.setColumnReordering(true);

        // externControllerGrid.getAriaSupport().setLabelledBy(cp.getId() + "-label");
        fsControlerExternal.add(this.externControllerGrid);
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
        this.btnModifier = new Button(this.messages.commonmodifierbutton());
        btPanel.addButton(this.btnAnnuler);
        btPanel.addButton(this.btnModifier);
        this.add(btPanel);
        this.btnAnnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
               returnControleList();
            }
        });
        this.btnModifier.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
               openEditControle(ViewControlPanel.this.currentControle);
            }
        });

    }

    public void openEditControle(ControlModel model) {
        ControlLeftPanel clp = (ControlLeftPanel) ComponentManager.get().get(ClientConstant.CONTROL_TREE_PANEL_ID);
        PerimetreTreeModel ptModel = clp.getSelectedPerimetreTreeModel();
        EditControleEvent controlEvent = new EditControleEvent();
        controlEvent.setPerimetreTreeModel(ptModel);
        controlEvent.setControlModel(model);
        this.bus.fireEvent(controlEvent);
    }

    private void initInformationForm() {
        LayoutContainer lcInfo = new LayoutContainer();
        lcInfo.setSize(WIDTH, HEIGHT);
        lcInfo.setLayout(new ColumnLayout());

        LayoutContainer left = new LayoutContainer();
        FormLayout layout = new FormLayout();
        layout.setLabelSeparator(":");
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

        this.lblCodeProjet = new LabelField();
        this.lblCodeProjet.setFieldLabel(this.messages.controlecodeprojet());
        this.lblCodeProjet.setValue("Testing");
        left.add(this.lblCodeProjet);

        this.cbControlType = new LabelField();

        this.cbControlType.setFieldLabel(this.messages.controletype());
        left.add(this.cbControlType);

        right.add(new LabelField(""));
        this.dfControlDate = new LabelField();
        this.dfControlDate.setId("dfDebut");
        this.dfControlDate.setFieldLabel(this.messages.datedecontrole());
        this.dfControlDate.setValue(new Date());
        right.add(this.dfControlDate);

        this.controlerGroup = new LabelField();
        this.controlerGroup.setFieldLabel(this.messages.controlertype());

        this.controlerGroup.setStyleAttribute("padding-left", "20px");

        left.add(this.controlerGroup);

        this.cbCollaborateur = new LabelField();
        this.cbCollaborateur.setFieldLabel(this.messages.controlercollaborateur());
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
        layout.setLabelSeparator(":");
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
               returnControleList();
               resetForm();
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

    private void returnControleList() {
        ListControleEvent lce = new ListControleEvent();
        this.bus.fireEvent(lce);
    }

    private void displayFormData() {
        String orgName = (this.currentControle.getPerimetre()) == null ? null : this.currentControle.getPerimetre().getName();
        this.lblOrganisationNom.setText(orgName);
        this.lblPerimetreAssocie.setText(this.currentControle.getPerimetreParent());
        this.lblCodeProjet.setValue(this.currentControle.getCodeProject());
        this.cbControlType.setValue(this.currentControle.getControlType().getLabel());
        this.dfControlDate.setValue(this.currentControle.getDate() != null ? DateTimeFormat.getFormat(ClientConstant.DATE_FORMAT).format(
                this.currentControle.getDate()) : "");
        this.controlerGroup.setValue(this.currentControle.getCharacter());
        if (this.currentControle.getCharacter() == null || this.currentControle.getCharacter().equalsIgnoreCase(ControlModel.INTERNAL)) {
            this.cbCollaborateur.setValue(this.currentControle.getCollaborateur().getFullname());
            this.cbCollaborateur.setVisible(true);
            this.fsExternController.setVisible(false);
        } else {
            this.cbCollaborateur.setValue(null);
            this.cbCollaborateur.setVisible(false);
            this.fsExternController.setVisible(true);
        }
        this.ffFile.setValue(this.currentControle.getRapport());
    }

    public void applyControlModel(ControlModel controlModel, PerimetreTreeModel perimetreTree) {
        this.currentControle = controlModel;
        if (ControlModel.EXTERNAL.equalsIgnoreCase(this.currentControle.getCharacter())) {

            this.clientExternControllerControlServiceAsync.findByControl(controlModel.getId(), new AsyncCallback<List<ExtControllerControlModel>>() {

                @Override
                public void onSuccess(List<ExtControllerControlModel> arg0) {
                   currentControle.setExternControllers(arg0);
                   lstExternControlers.removeAll();
                    if (arg0 != null) {
                       lstExternControlers.add(arg0);
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
    }
}
