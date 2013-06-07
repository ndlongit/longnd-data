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
import com.structis.vip.client.event.ModifyDocTypeEvent;
import com.structis.vip.client.event.ModifyDocTypeHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDocTypeServiceAsync;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.DocumentTypeModel;

public class DocTypeFormPanel extends AbstractPanel {

    private final FormData formData = new FormData("98%");
    private final int WIDTH = 500;

    private ClientDocTypeServiceAsync clientDocTypeService = ClientDocTypeServiceAsync.Util.getInstance();

    private SimpleEventBus bus;
    private FormPanel panel;
    private TextField<String> tfName;
    private TextField<String> tfDesc;
    private Button btnAmnuler;
    private Button btnSave;
    private DocumentTypeModel model;
    private boolean isEdit = true;

    public DocTypeFormPanel(SimpleEventBus bus) {
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
        this.bus.addHandler(ModifyDocTypeEvent.getType(), new ModifyDocTypeHandler() {

            @Override
            public void onLoadAction(ModifyDocTypeEvent event) {
                AppUtil.putInAdminEditMode();
                if (event.getModel() != null) {
                    DocTypeFormPanel.this.isEdit = true;
                    DocTypeFormPanel.this.model = event.getModel();
                    DocTypeFormPanel.this.tfName.setValue(DocTypeFormPanel.this.model.getName());
                    DocTypeFormPanel.this.tfDesc.setValue(DocTypeFormPanel.this.model.getDescription());
                    DocTypeFormPanel.this.btnSave.setText(DocTypeFormPanel.this.messages.commonModifierButton());
                } else {
                    DocTypeFormPanel.this.model = null;
                    DocTypeFormPanel.this.isEdit = false;
                    DocTypeFormPanel.this.panel.reset();
                    DocTypeFormPanel.this.panel.clear();

                    DocTypeFormPanel.this.btnSave.setText(DocTypeFormPanel.this.messages.commonValiderButton());
                }
            }
        });
    }

    private void initData() {
    }

    private void initUI() {
        this.panel = new FormPanel();
        this.panel.setHeading(this.messages.doctypeformheader());
        this.panel.setFrame(true);
        this.panel.setButtonAlign(HorizontalAlignment.RIGHT);
        this.panel.setWidth(this.WIDTH);

        this.tfName = new TextField<String>();
        this.tfName.setFieldLabel(this.messages.doctypenom());
        this.tfName.setMaxLength(3);
        this.tfName.setWidth(10);
        this.tfName.setName("name");
        this.tfName.setAllowBlank(false);
        this.panel.add(this.tfName, this.formData);

        this.tfDesc = new TextField<String>();
        this.tfDesc.setFieldLabel(this.messages.doctypedesc());
        this.tfDesc.setMaxLength(255);
        this.tfDesc.setName("desc");
        this.tfDesc.setAllowBlank(true);
        this.panel.add(this.tfDesc, this.formData);

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
        Label lblBack = new Label(this.messages.doctypeback());

        lblBack.setStyleName("x-link-item");
        backLink.setStyleAttribute("margin-bottom", "20px");
        backLink.add(lblBack);

        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCTYPE_LIST);
                    DocTypeFormPanel.this.bus.fireEvent(contentEvent);
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
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCTYPE_LIST);
                DocTypeFormPanel.this.bus.fireEvent(event);
                AppUtil.removeAdminInEditMode();
            }
        });

        this.btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (DocTypeFormPanel.this.panel.isValid()) {
                    DocTypeFormPanel.this.save();
                }
            }
        });
    }

    private void save() {
        if (this.model == null) {
            this.model = new DocumentTypeModel();
        }
        this.model.setName(this.tfName.getValue());
        this.model.setDescription(this.tfDesc.getValue());

        if (this.isEdit == false) {
            this.clientDocTypeService.insert(this.model, new AsyncCallback<DocumentTypeModel>() {

                @Override
                public void onSuccess(DocumentTypeModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCTYPE_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    DocTypeFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable caught) {
                    Info.display(DocTypeFormPanel.this.messages.commonerror(), DocTypeFormPanel.this.messages.commonServererror());
                }
            });
        } else {
            this.clientDocTypeService.update(this.model, new AsyncCallback<DocumentTypeModel>() {

                @Override
                public void onSuccess(DocumentTypeModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCTYPE_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    DocTypeFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable arg0) {
                    Info.display(DocTypeFormPanel.this.messages.commonerror(), DocTypeFormPanel.this.messages.commonServererror());
                }
            });
        }
    }
}
