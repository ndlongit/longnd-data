package com.structis.vip.client.panel;

import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.ModifyCollaborateurTypeEvent;
import com.structis.vip.client.event.ModifyCollaborateurTypeHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientCollaborateurTypeServiceAsync;
import com.structis.vip.client.service.ClientDelegantTypeGroupServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.CollaborateurTypeModel;
import com.structis.vip.shared.model.DelegantTypeGroupModel;
import com.structis.vip.shared.model.LanguageModel;

public class CollaborateurTypeFormPanel extends AbstractPanel {

    private final FormData formData = new FormData("98%");
    private final int WIDTH = 500;

    private ClientCollaborateurTypeServiceAsync clientCollaborateurTypeService = ClientCollaborateurTypeServiceAsync.Util.getInstance();
    private ClientDelegantTypeGroupServiceAsync clientDelegantTypeGroupService = ClientDelegantTypeGroupServiceAsync.Util.getInstance();

    private SimpleEventBus bus;
    private FormPanel panel;
    private ComboBox<DelegantTypeGroupModel> cbGroup;

    private TextField<String> tfName;
    private Button btnAmnuler;
    private Button btnSave;
    private CollaborateurTypeModel model;
    private boolean isEdit = true;
    private ListStore<DelegantTypeGroupModel> groupStore = new ListStore<DelegantTypeGroupModel>();

    public CollaborateurTypeFormPanel(SimpleEventBus bus) {
        this.bus = bus;

        this.setLayout(new FlowLayout(10));
        this.setScrollMode(Scroll.AUTO);
        this.setWidth(this.WIDTH);

        this.addHandler();
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        this.initData();
        this.initBackLink();
        this.initUI();
        this.initEvent();
    }

    private void addHandler() {
        this.bus.addHandler(ModifyCollaborateurTypeEvent.getType(), new ModifyCollaborateurTypeHandler() {

            @Override
            public void onLoadAction(ModifyCollaborateurTypeEvent event) {
                CollaborateurTypeFormPanel.this.initData();
                AppUtil.putInAdminEditMode();
                if (event.getModel() != null) {
                    CollaborateurTypeFormPanel.this.isEdit = true;
                    CollaborateurTypeFormPanel.this.model = event.getModel();
                    CollaborateurTypeFormPanel.this.tfName.setValue(CollaborateurTypeFormPanel.this.model.getName());
                    CollaborateurTypeFormPanel.this.cbGroup.setValue(CollaborateurTypeFormPanel.this.model.getGroup());
                    CollaborateurTypeFormPanel.this.btnSave.setText(CollaborateurTypeFormPanel.this.messages.commonModifierButton());
                } else {
                    CollaborateurTypeFormPanel.this.model = null;
                    CollaborateurTypeFormPanel.this.isEdit = false;
                    CollaborateurTypeFormPanel.this.panel.reset();
                    CollaborateurTypeFormPanel.this.panel.clear();

                    CollaborateurTypeFormPanel.this.btnSave.setText(CollaborateurTypeFormPanel.this.messages.commonValiderButton());
                }
            }
        });
    }

    private void initData() {
        System.out.println("vaooooooooooooo");
        this.clientDelegantTypeGroupService.getDelegantTypeGroups(new AsyncCallback<List<DelegantTypeGroupModel>>() {

            @Override
            public void onSuccess(List<DelegantTypeGroupModel> arg0) {
                CollaborateurTypeFormPanel.this.groupStore.removeAll();
                CollaborateurTypeFormPanel.this.groupStore.add(arg0);
            }

            @Override
            public void onFailure(Throwable arg0) {
            }
        });
    }

    private void initUI() {
        this.panel = new FormPanel();
        this.panel.setHeading(this.messages.deleganttypeformheader());
        this.panel.setFrame(true);
        this.panel.setButtonAlign(HorizontalAlignment.RIGHT);
        this.panel.setWidth(this.WIDTH);

        this.cbGroup = new ComboBox<DelegantTypeGroupModel>();
        this.cbGroup.setAllowBlank(false);
        this.cbGroup.setEditable(false);
        this.cbGroup.setFieldLabel(this.messages.deleganttypegroupnom());
        this.cbGroup.setName("group");
        this.cbGroup.setDisplayField(LanguageModel.LAG_NAME);
        this.cbGroup.setTriggerAction(TriggerAction.ALL);
        this.cbGroup.setStore(this.groupStore);
        this.panel.add(this.cbGroup, this.formData);

        this.tfName = new TextField<String>();
        this.tfName.setFieldLabel(this.messages.collaborateurtypenom());
        this.tfName.setMaxLength(50);
        this.tfName.setName("name");
        this.tfName.setAllowBlank(false);
        this.panel.add(this.tfName, this.formData);

        this.btnAmnuler = new Button(this.messages.commonAnnulerButton());
        this.btnSave = new Button(this.messages.commonValiderButton());

        this.panel.addButton(this.btnAmnuler);
        this.panel.addButton(this.btnSave);

        this.panel.getButtonBar().setStyleAttribute("padding-right", "16px");

        this.add(this.panel);
    }

    private void initBackLink() {
        LayoutContainer backLink = new LayoutContainer();
        backLink.setSize(this.WIDTH, -1);
        Label lblBack = new Label(this.messages.collaborateurtypeback());

        lblBack.setStyleName("x-link-item");
        backLink.setStyleAttribute("margin-bottom", "20px	");
        backLink.add(lblBack);

        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_LIST);
                    CollaborateurTypeFormPanel.this.bus.fireEvent(contentEvent);
                }
            }
        });

        this.add(backLink);
    }

    private void initEvent() {
        this.btnAmnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_LIST);
                CollaborateurTypeFormPanel.this.bus.fireEvent(event);
                AppUtil.removeAdminInEditMode();
            }
        });

        this.btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (CollaborateurTypeFormPanel.this.panel.isValid()) {
                    CollaborateurTypeFormPanel.this.save();
                }
            }
        });
    }

    private void save() {
        if (this.model == null) {
            this.model = new CollaborateurTypeModel();
        }
        this.model.setName(this.tfName.getValue());
        this.model.setGroup(this.cbGroup.getValue());
        this.model.setEntite(SessionServiceImpl.getInstance().getEntiteContext());

        if (this.isEdit == false) {
            this.clientCollaborateurTypeService.insert(this.model, new AsyncCallback<CollaborateurTypeModel>() {

                @Override
                public void onSuccess(CollaborateurTypeModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    CollaborateurTypeFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable caught) {
                    Info.display(CollaborateurTypeFormPanel.this.messages.commonerror(), CollaborateurTypeFormPanel.this.messages.commonServererror());
                }
            });
        } else {
            this.clientCollaborateurTypeService.update(this.model, new AsyncCallback<CollaborateurTypeModel>() {

                @Override
                public void onSuccess(CollaborateurTypeModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    CollaborateurTypeFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable arg0) {
                    Info.display(CollaborateurTypeFormPanel.this.messages.commonerror(), CollaborateurTypeFormPanel.this.messages.commonServererror());
                }
            });
        }
    }
}
