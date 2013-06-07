package com.structis.vip.client.panel.control;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.ListViewEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.DateTimePropertyEditor;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.event.control.ControlFilterEvent;
import com.structis.vip.client.event.control.RefreshTreeEvent;
import com.structis.vip.client.event.control.RefreshTreeHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientCollaborateurServiceAsync;
import com.structis.vip.client.service.ClientControlTypeServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.widget.XComboBox;
import com.structis.vip.shared.model.ControlModel;
import com.structis.vip.shared.model.ControlTypeModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.KeyValueModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;

public class FilterPanel extends VerticalPanel {

    private final Messages messages = GWT.create(Messages.class);
    private static final int COMBOBOX_WIDTH = 275;
    private static final int LABEL_WIDTH = 120;
    private int pagingSize = ClientConstant.DEFAULT_PAGE_SIZE_50;

    SimpleEventBus bus;
    ControlFilterEvent controlFilterEvent;

    TextField<String> tfCodeProject;
    XComboBox<KeyValueModel> cbCaractere;
    private ComboBox<KeyValueModel> tfControllerName;
    XComboBox<ControlTypeModel> controlTypeCombobox;

    DateField fromDateField;
    DateField toDateField;
    Button buttonFiltrer;
    FormPanel panel;

    private ListStore<KeyValueModel> caracteres = new ListStore<KeyValueModel>();
    private ListStore<KeyValueModel> controleurs = new ListStore<KeyValueModel>();
    private ListStore<ControlTypeModel> controlTypes = new ListStore<ControlTypeModel>();

    List<EntiteModel> entiteAllSelection = new ArrayList<EntiteModel>();
    List<PerimetreModel> societeAllSelection = new ArrayList<PerimetreModel>();
    List<PerimetreModel> serviceAllSelection = new ArrayList<PerimetreModel>();
    List<PerimetreModel> uoAllSelection = new ArrayList<PerimetreModel>();
    List<PerimetreModel> directionAllSelection = new ArrayList<PerimetreModel>();
    private List<ControlTypeModel> controlTypeAllSelection = new ArrayList<ControlTypeModel>();
    private List<KeyValueModel> caractereAllSelection = new ArrayList<KeyValueModel>();

    ControlTypeModel controlTypeAll;
    private KeyValueModel caracterAll;
    private EntiteModel selectedEntiteModel;
    private PerimetreModel selectedPerimetreModel;
    private ClientCollaborateurServiceAsync clientCollaborateurServiceAsync = ClientCollaborateurServiceAsync.Util.getInstance();
    private KeyValueModel nullHolderController = new KeyValueModel();

    public FilterPanel(SimpleEventBus bus) {
        this.bus = bus;

        this.nullHolderController.setKey(null);
        this.nullHolderController.setValue(null);
        this.nullHolderController.setType("");
        this.initData();

        this.initUI();

        this.addHandler();
    }

    private void initData() {
        this.controlTypeAll = new ControlTypeModel();
        this.controlTypeAll.setLabel("Tous");
        this.controlTypeAll.setId(0);
        this.controlTypes.add(this.controlTypeAll);
        this.controlTypeAllSelection.add(this.controlTypeAll);
        this.caracterAll = new KeyValueModel();
        this.caracterAll.setKey("");
        this.caracterAll.setValue("Tous");
        this.caracterAll.setId(0);
        this.caractereAllSelection.add(this.caracterAll);
    }

    private void initUI() {
        FormData formData = new FormData("100%");
        FormData formDataR = new FormData("102%");

        LayoutContainer main = new LayoutContainer();
        main.setLayout(new ColumnLayout());
        main.setStyleAttribute("padding", "5px");

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
        layout2.setLabelWidth(LABEL_WIDTH);
        right.setLayout(layout2);
        right.setStyleAttribute("paddingLeft", "5px");

        // entite combobox
        this.tfCodeProject = new TextField<String>();
        this.tfCodeProject.setWidth(COMBOBOX_WIDTH);
        this.tfCodeProject.setFieldLabel("CI Code Projet");
        this.tfCodeProject.setLabelSeparator("");
        left.add(this.tfCodeProject, formData);

        // societe combobox
        this.cbCaractere = new XComboBox<KeyValueModel>() {

            @Override
            protected void processOnChanges(List<KeyValueModel> selection) {

                KeyValueModel val = selection == null ? null : selection.get(0);
                if (val == null || val.getId() == 0) {// all
                    controleurs.clearFilters();
                } else if (val.getId() == 1) {// interne
                    nullHolderController.setType("0");
                    controleurs.filter("type", "0");
                } else { // externe
                    nullHolderController.setType("1");
                    controleurs.filter("type", "1");
                }
            }
        };
        this.cbCaractere.setWidth(COMBOBOX_WIDTH);
        this.cbCaractere.setFieldLabel(this.messages.controlertype());
        this.cbCaractere.setDisplayField(KeyValueModel.VALUE);
        this.cbCaractere.setLabelSeparator("");
        this.cbCaractere.setStore(this.caracteres);
        this.cbCaractere.setSelection(this.caractereAllSelection);
        left.add(this.cbCaractere, formData);

        // form date
        this.fromDateField = new DateField();
        this.fromDateField.setLabelSeparator("");
        this.fromDateField.setFieldLabel(this.messages.controledatededebut());
        this.fromDateField.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        this.fromDateField.setLabelStyle("white-space: nowrap");
        this.fromDateField.setWidth(210);
        left.add(this.fromDateField, formData);

        this.controlTypeCombobox = new XComboBox<ControlTypeModel>();
        this.controlTypeCombobox.setWidth(COMBOBOX_WIDTH);
        this.controlTypeCombobox.setEditable(false);
        this.controlTypeCombobox.setTriggerAction(TriggerAction.ALL);
        this.controlTypeCombobox.setStore(this.controlTypes);
        this.controlTypeCombobox.setFieldLabel(this.messages.controletypedecontrole());
        this.controlTypeCombobox.setLabelSeparator("");
        this.controlTypeCombobox.setSelection(this.controlTypeAllSelection);
        this.controlTypeCombobox.setDisplayField(ControlTypeModel.CON_LABEL);
        this.controlTypeCombobox.setLabelStyle("white-space: nowrap");

        right.add(this.controlTypeCombobox, formData);

        // tfControllerName = new TextField<String>();
        this.tfControllerName = new ComboBox<KeyValueModel>();
        this.tfControllerName.setDisplayField(KeyValueModel.VALUE);
        this.tfControllerName.setEditable(true);
        this.tfControllerName.setTriggerAction(TriggerAction.ALL);
        this.tfControllerName.setStore(this.controleurs);
        this.tfControllerName.setWidth(COMBOBOX_WIDTH);
        this.tfControllerName.setFieldLabel(this.messages.controlenomducontrole());
        this.tfControllerName.setLabelSeparator("");
        right.add(this.tfControllerName, formData);

        this.toDateField = new DateField();
        this.toDateField.setLabelSeparator("");
        this.toDateField.setFieldLabel(this.messages.controledatedefin());
        this.toDateField.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT));
        this.toDateField.setLabelStyle("white-space: nowrap");
        this.toDateField.setWidth(210);
        right.add(this.toDateField, formData);

        ContentPanel pButton = new ContentPanel();
        pButton.setStyleAttribute("paddingRight", "0px");
        pButton.setStyleAttribute("margin-right", "0px");

        pButton.setBodyBorder(false);

        pButton.setBorders(false);
        pButton.setHeaderVisible(false);
        pButton.setButtonAlign(HorizontalAlignment.RIGHT);

        this.buttonFiltrer = new Button("Filtre");
        this.buttonFiltrer.setWidth(100);
        pButton.addButton(this.buttonFiltrer);
        right.add(pButton, formDataR);

        // add to main panel
        main.setStyleAttribute("padding-left", "0px");
        main.setStyleAttribute("padding-bottom", "0px");
        main.add(left, new ColumnData(.5));
        main.add(right, new ColumnData(.5));

        // init main form panel
        this.panel = new FormPanel();
        this.panel.setPadding(0);
        this.panel.setStyleAttribute("background", "white");
        this.panel.setHeaderVisible(false);
        this.panel.setSize(860, -1);
        this.panel.setBodyBorder(false);
        this.panel.setLabelAlign(LabelAlign.RIGHT);
        this.panel.setLabelWidth(LABEL_WIDTH);
        this.panel.setButtonAlign(HorizontalAlignment.RIGHT);
        FormData formData2 = new FormData("100%");
        formData2.setMargins(new Margins(10, 0, 0, 0));
        this.panel.add(main, formData2);

        // add to root panel
        this.setStyleAttribute("padding-left", "5px");
        this.setSpacing(0);
        this.setHeight(150);
        this.add(this.panel);
    }

    private void addHandler() {
        // add event for Filter button

        this.cbCaractere.getListView().addListener(Events.SelectionChange, new Listener<ListViewEvent<KeyValueModel>>() {

            @Override
            public void handleEvent(ListViewEvent<KeyValueModel> be) {
                KeyValueModel val = cbCaractere.getSelection() == null ? null : cbCaractere.getSelection().get(0);
                if (val == null || val.getId() == 0) {// all
                    controleurs.clearFilters();
                } else if (val.getId() == 1) {// interne
                    controleurs.filter("type", "0");
                } else { // externe
                    controleurs.filter("type", "1");
                }

            }
        });
        this.buttonFiltrer.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (controlFilterEvent == null) {
                    controlFilterEvent = new ControlFilterEvent();
                }
                TreePanel<PerimetreTreeModel> p = (TreePanel<PerimetreTreeModel>) AppUtil.getPanel(ClientConstant.CONTROL_TREE_ID);
                PerimetreTreeModel treeModel = p.getSelectionModel().getSelectedItem();
                if (treeModel == null) {
                    treeModel = new PerimetreTreeModel(selectedPerimetreModel, SessionServiceImpl.getInstance().getUserContext()
                            .getUserRoles());
                    treeModel.setEntiteId(selectedEntiteModel.getEntId());
                    treeModel.setLevel(0);
                    treeModel.setPath(selectedPerimetreModel.getName());
                    treeModel.setIsEntite(false);
                }
                treeModel.setName(SafeHtmlUtils.htmlEscape(treeModel.getName()));
                controlFilterEvent.setPerimetreTreeModel(treeModel);
                controlFilterEvent.setEntiteModel(SessionServiceImpl.getInstance().getEntiteContext());
                controlFilterEvent.setTypeModel(controlTypeCombobox.getSelection());
                controlFilterEvent.setStartDate(fromDateField.getValue());
                controlFilterEvent.setEndDate(toDateField.getValue());
                controlFilterEvent.setCodeProject(tfCodeProject.getValue());
                controlFilterEvent.setCaracteres(cbCaractere.getSelection());
                controlFilterEvent.setControllerName(tfControllerName.getRawValue());
                controlFilterEvent.setPageSize(pagingSize);

                // fire event delegation filter
                bus.fireEvent(controlFilterEvent);
            }
        });

        this.bus.addHandler(RefreshTreeEvent.getType(), new RefreshTreeHandler() {

            @Override
            public void onLoadAction(RefreshTreeEvent event) {
                panel.reset();
                selectedPerimetreModel = event.getPerimetreModel();
            }
        });

    }

    public Button getFilterButton() {
        return this.buttonFiltrer;
    }

    public void onLoadPanel() {
        KeyValueModel inter = new KeyValueModel();
        inter.setId(1);
        inter.setKey(ControlModel.INTERNAL);
        inter.setValue(ControlModel.INTERNAL);
        KeyValueModel exter = new KeyValueModel();
        exter.setId(2);
        exter.setKey(ControlModel.EXTERNAL);
        exter.setValue(ControlModel.EXTERNAL);
        this.caracteres.removeAll();
        this.caracteres.add(this.caracterAll);
        this.caracteres.add(inter);
        this.caracteres.add(exter);
        this.cbCaractere.setSelection(this.caractereAllSelection);

        this.selectedEntiteModel = SessionServiceImpl.getInstance().getEntiteContext();
        this.selectedPerimetreModel = SessionServiceImpl.getInstance().getPerimetreContext();
        this.controlTypeCombobox.setSelection(this.controlTypeAllSelection);

        ClientControlTypeServiceAsync.Util.getInstance().findAll(new AsyncCallback<List<ControlTypeModel>>() {

            @Override
            public void onSuccess(List<ControlTypeModel> result) {
                controlTypes.removeAll();
                controlTypes.add(controlTypeAll);
                controlTypes.add(result);
            }

            @Override
            public void onFailure(Throwable arg0) {
            }
        });
        ClientControlTypeServiceAsync.Util.getInstance().findAll(new AsyncCallback<List<ControlTypeModel>>() {

            @Override
            public void onSuccess(List<ControlTypeModel> result) {
                controlTypes.removeAll();
                controlTypes.add(controlTypeAll);
                controlTypes.add(result);
            }

            @Override
            public void onFailure(Throwable arg0) {
            }
        });
        // add BYTP

        this.clientCollaborateurServiceAsync.getAllControleursByEntiteId(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                new AsyncCallback<List<KeyValueModel>>() {

                    @Override
                    public void onSuccess(List<KeyValueModel> arg0) {
                        controleurs.removeAll();

                        controleurs.add(nullHolderController);
                        controleurs.add(arg0);
                    }

                    @Override
                    public void onFailure(Throwable arg0) {
                    }
                });
    }
}
