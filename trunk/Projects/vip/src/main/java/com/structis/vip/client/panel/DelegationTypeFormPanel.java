package com.structis.vip.client.panel;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextArea;
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
import com.structis.vip.client.event.LoadDelegationTypeEvent;
import com.structis.vip.client.event.ModifyDelegationTypeEvent;
import com.structis.vip.client.event.ModifyDelegationTypeHandler;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDelegationTypeServiceAsync;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.exception.DelegationTypeException;
import com.structis.vip.shared.model.DelegationTypeModel;

public class DelegationTypeFormPanel extends LayoutContainer {

    private final Messages messages = GWT.create(Messages.class);
    private final FormData formData = new FormData("98%");
    private final int WIDTH = 500;

    private ClientDelegationTypeServiceAsync clientDelegationTypeService = ClientDelegationTypeServiceAsync.Util.getInstance();

    private SimpleEventBus bus;
    private FormPanel panel;
    private TextField<String> tfName;
    private TextArea taDescription;
    private Button btnAmnuler;
    private Button btnSave;
    private DelegationTypeModel model;
    private boolean isEdit = true;

    public DelegationTypeFormPanel(SimpleEventBus bus) {
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
        this.bus.addHandler(ModifyDelegationTypeEvent.getType(), new ModifyDelegationTypeHandler() {

            @Override
            public void onLoadAction(ModifyDelegationTypeEvent event) {
                AppUtil.putInAdminEditMode();
                if (event.getModel() != null) {
                    DelegationTypeFormPanel.this.isEdit = true;
                    DelegationTypeFormPanel.this.model = event.getModel();
                    DelegationTypeFormPanel.this.tfName.setValue(DelegationTypeFormPanel.this.model.getName());
                    DelegationTypeFormPanel.this.taDescription.setValue(DelegationTypeFormPanel.this.model.getDescription());
                    DelegationTypeFormPanel.this.btnSave.setText(DelegationTypeFormPanel.this.messages.commonModifierButton());
                } else {
                    DelegationTypeFormPanel.this.model = null;
                    DelegationTypeFormPanel.this.isEdit = false;
                    DelegationTypeFormPanel.this.panel.reset();
                    DelegationTypeFormPanel.this.panel.clear();
                    DelegationTypeFormPanel.this.btnSave.setText(DelegationTypeFormPanel.this.messages.commonValiderButton());
                }
            }
        });
    }

    private void initData() {
    }

    private void initUI() {
        this.panel = new FormPanel();
        this.panel.setHeading(this.messages.delegationtypeformheader());
        this.panel.setFrame(true);
        this.panel.setButtonAlign(HorizontalAlignment.RIGHT);
        this.panel.setWidth(this.WIDTH);

        this.tfName = new TextField<String>();
        this.tfName.setFieldLabel(this.messages.delegationtypenom());
        this.tfName.setMaxLength(80);
        this.tfName.setName("name");
        this.tfName.setAllowBlank(false);
        this.panel.add(this.tfName, this.formData);

        this.taDescription = new TextArea();
        this.taDescription.setFieldLabel(this.messages.delegationtypedescription());
        this.taDescription.setName("description");
        this.taDescription.setMaxLength(255);
        this.panel.add(this.taDescription, this.formData);

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
        Label lblBack = new Label(this.messages.delegationtypeback());

        lblBack.setStyleName("x-link-item");
        backLink.setStyleAttribute("margin-bottom", "20px	");
        backLink.add(lblBack);

        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGATION_TYPE_LIST);
                    DelegationTypeFormPanel.this.bus.fireEvent(contentEvent);
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
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGATION_TYPE_LIST);
                DelegationTypeFormPanel.this.bus.fireEvent(event);
                AppUtil.removeAdminInEditMode();
            }
        });

        this.btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (DelegationTypeFormPanel.this.panel.isValid()) {
                    DelegationTypeFormPanel.this.save();
                }
            }
        });
    }

    private void save() {
        if (this.model == null) {
            this.model = new DelegationTypeModel();
        }
        this.model.setName(this.tfName.getValue());
        this.model.setDescription(this.taDescription.getValue());

        if (this.isEdit == false) {
            this.clientDelegationTypeService.insert(this.model, new AsyncCallback<DelegationTypeModel>() {

                @Override
                public void onSuccess(DelegationTypeModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGATION_TYPE_LIST);
                    contentEvent.setEvent(new LoadDelegationTypeEvent());
                    DelegationTypeFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable arg0) {
                    String details = arg0.getMessage();
                    if (arg0 instanceof DelegationTypeException) {
                        details = ExceptionMessageHandler.getErrorMessage(((DelegationTypeException) arg0).getCode());
                    }
                    Info.display(DelegationTypeFormPanel.this.messages.commonerror(), details);
                }
            });

        } else {
            this.clientDelegationTypeService.update(this.model, new AsyncCallback<DelegationTypeModel>() {

                @Override
                public void onSuccess(DelegationTypeModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGATION_TYPE_LIST);
                    contentEvent.setEvent(new LoadDelegationTypeEvent());
                    DelegationTypeFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable arg0) {
                    Info.display(DelegationTypeFormPanel.this.messages.commonerror(), DelegationTypeFormPanel.this.messages.commonServererror());
                }
            });
        }
    }
}
