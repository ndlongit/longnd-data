package com.structis.vip.client.panel;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
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
import com.structis.vip.client.event.control.ModifyExternControllerEvent;
import com.structis.vip.client.event.control.ModifyExternControllerHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientExternControllerServiceAsync;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.ExternControllerModel;

public class ExternalControllerFormPanel extends LayoutContainer {

    private final Messages messages = GWT.create(Messages.class);
    private final FormData formData = new FormData("98%");
    private final int WIDTH = 500;

    private ClientExternControllerServiceAsync clientExternControllerService = ClientExternControllerServiceAsync.Util.getInstance();

    private SimpleEventBus bus;
    private FormPanel panel;
    private TextField<String> tfName;
    // private TextField<String> tfAddress;
    private Button btnAmnuler;
    private Button btnSave;
    private ExternControllerModel model;
    private boolean isEdit = true;

    public ExternalControllerFormPanel(SimpleEventBus bus) {
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
        this.bus.addHandler(ModifyExternControllerEvent.getType(), new ModifyExternControllerHandler() {

            @Override
            public void onLoadAction(ModifyExternControllerEvent event) {
                AppUtil.putInAdminEditMode();
                if (event.getModel() != null) {
                    ExternalControllerFormPanel.this.isEdit = true;
                    ExternalControllerFormPanel.this.model = event.getModel();
                    ExternalControllerFormPanel.this.tfName.setValue(ExternalControllerFormPanel.this.model.getName());
                    // tfAddress.setValue(model.getAddress());
                    ExternalControllerFormPanel.this.btnSave.setText(ExternalControllerFormPanel.this.messages.commonModifierButton());
                } else {
                    ExternalControllerFormPanel.this.model = null;
                    ExternalControllerFormPanel.this.isEdit = false;
                    ExternalControllerFormPanel.this.panel.reset();
                    ExternalControllerFormPanel.this.panel.clear();

                    ExternalControllerFormPanel.this.btnSave.setText(ExternalControllerFormPanel.this.messages.commonValiderButton());
                }
            }
        });
    }

    private void initData() {
    }

    private void initUI() {
        this.panel = new FormPanel();
        this.panel.setHeading(this.messages.externcontrollerformheader());
        this.panel.setFrame(true);
        this.panel.setButtonAlign(HorizontalAlignment.RIGHT);
        this.panel.setWidth(this.WIDTH);

        this.tfName = new TextField<String>();
        this.tfName.setFieldLabel(this.messages.adminexccontrollerlname());
        this.tfName.setMaxLength(255);
        this.tfName.setName("name");
        this.tfName.setAllowBlank(false);
        this.panel.add(this.tfName, this.formData);

        // panel.add(tfNationality, formData);
        // tfAddress = new TextField<String>();
        // tfAddress.setFieldLabel(messages.adminexccontrolleraddress());
        // tfAddress.setName("address");
        // panel.add(tfAddress, formData);
        //
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
        Label lblBack = new Label(this.messages.externcontrollerback());

        lblBack.setStyleName("x-link-item");
        backLink.setStyleAttribute("margin-bottom", "20px");
        backLink.add(lblBack);

        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_EXTERN_CONTROLLER_LIST);
                    ExternalControllerFormPanel.this.bus.fireEvent(contentEvent);
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
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_EXTERN_CONTROLLER_LIST);
                ExternalControllerFormPanel.this.bus.fireEvent(event);
                AppUtil.removeAdminInEditMode();
            }
        });

        this.btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (ExternalControllerFormPanel.this.panel.isValid()) {
                    ExternalControllerFormPanel.this.save();
                }
            }
        });
    }

    private void save() {
        if (this.model == null) {
            this.model = new ExternControllerModel();
        }
        this.model.setName(this.tfName.getValue());
        // model.setAddress(tfAddress.getValue());

        if (this.isEdit == false) {
            this.clientExternControllerService.insert(this.model, new AsyncCallback<ExternControllerModel>() {

                @Override
                public void onSuccess(ExternControllerModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_EXTERN_CONTROLLER_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    ExternalControllerFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable caught) {
                    Info.display(ExternalControllerFormPanel.this.messages.commonerror(),
                            ExternalControllerFormPanel.this.messages.commonServererror());
                }
            });
        } else {
            this.clientExternControllerService.update(this.model, new AsyncCallback<ExternControllerModel>() {

                @Override
                public void onSuccess(ExternControllerModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_EXTERN_CONTROLLER_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    ExternalControllerFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable arg0) {
                    Info.display(ExternalControllerFormPanel.this.messages.commonerror(),
                            ExternalControllerFormPanel.this.messages.commonServererror());
                }
            });
        }
    }
}
