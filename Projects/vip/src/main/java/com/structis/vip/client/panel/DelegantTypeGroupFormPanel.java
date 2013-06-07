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
import com.structis.vip.client.event.ModifyDelegantTypeGroupEvent;
import com.structis.vip.client.event.ModifyDelegantTypeGroupHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientDelegantTypeGroupServiceAsync;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.DelegantTypeGroupModel;

public class DelegantTypeGroupFormPanel extends AbstractPanel {

    private final FormData formData = new FormData("98%");
    private final int WIDTH = 500;

    private ClientDelegantTypeGroupServiceAsync clientDelegantTypeGroupService = ClientDelegantTypeGroupServiceAsync.Util.getInstance();

    private SimpleEventBus bus;
    private FormPanel panel;
    private TextField<String> tfGroup;
    private TextField<String> tfName;
    private Button btnAmnuler;
    private Button btnSave;
    private DelegantTypeGroupModel model;
    private boolean isEdit = true;

    public DelegantTypeGroupFormPanel(SimpleEventBus bus) {
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
        this.bus.addHandler(ModifyDelegantTypeGroupEvent.getType(), new ModifyDelegantTypeGroupHandler() {

            @Override
            public void onLoadAction(ModifyDelegantTypeGroupEvent event) {
                AppUtil.putInAdminEditMode();
                if (event.getModel() != null) {
                    DelegantTypeGroupFormPanel.this.isEdit = true;
                    DelegantTypeGroupFormPanel.this.model = event.getModel();
                    DelegantTypeGroupFormPanel.this.tfName.setValue(DelegantTypeGroupFormPanel.this.model.getName());
                    DelegantTypeGroupFormPanel.this.tfGroup.setValue(DelegantTypeGroupFormPanel.this.model.getGroup());
                    DelegantTypeGroupFormPanel.this.btnSave.setText(DelegantTypeGroupFormPanel.this.messages.commonModifierButton());
                } else {
                    DelegantTypeGroupFormPanel.this.model = null;
                    DelegantTypeGroupFormPanel.this.isEdit = false;
                    DelegantTypeGroupFormPanel.this.panel.reset();
                    DelegantTypeGroupFormPanel.this.panel.clear();

                    DelegantTypeGroupFormPanel.this.btnSave.setText(DelegantTypeGroupFormPanel.this.messages.commonValiderButton());
                }
            }
        });
    }

    private void initData() {
    }

    private void initUI() {
        this.panel = new FormPanel();
        this.panel.setHeading(this.messages.deleganttypegroupformheader());
        this.panel.setFrame(true);
        this.panel.setButtonAlign(HorizontalAlignment.RIGHT);
        this.panel.setWidth(this.WIDTH);

        this.tfGroup = new TextField<String>();
        this.tfGroup.setFieldLabel(this.messages.deleganttypegroupgroup());
        this.tfGroup.setMaxLength(10);
        this.tfGroup.setName("group");
        this.tfGroup.setAllowBlank(false);
        this.panel.add(this.tfGroup, this.formData);

        this.tfName = new TextField<String>();
        this.tfName.setFieldLabel(this.messages.deleganttypegroupnom());
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
        Label lblBack = new Label(this.messages.deleganttypegroupback());

        lblBack.setStyleName("x-link-item");
        backLink.setStyleAttribute("margin-bottom", "20px	");
        backLink.add(lblBack);

        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_GROUP_LIST);
                    DelegantTypeGroupFormPanel.this.bus.fireEvent(contentEvent);
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
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_GROUP_LIST);
                DelegantTypeGroupFormPanel.this.bus.fireEvent(event);
                AppUtil.removeAdminInEditMode();
            }
        });

        this.btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (DelegantTypeGroupFormPanel.this.panel.isValid()) {
                    DelegantTypeGroupFormPanel.this.save();
                }
            }
        });
    }

    private void save() {
        if (this.model == null) {
            this.model = new DelegantTypeGroupModel();
        }
        this.model.setName(this.tfName.getValue());
        this.model.setGroup(this.tfGroup.getValue());

        if (this.isEdit == false) {
            this.clientDelegantTypeGroupService.insert(this.model, new AsyncCallback<DelegantTypeGroupModel>() {

                @Override
                public void onSuccess(DelegantTypeGroupModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_GROUP_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    DelegantTypeGroupFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable caught) {
                    Info.display(DelegantTypeGroupFormPanel.this.messages.commonerror(), DelegantTypeGroupFormPanel.this.messages.commonServererror());
                }
            });
        } else {
            this.clientDelegantTypeGroupService.update(this.model, new AsyncCallback<DelegantTypeGroupModel>() {

                @Override
                public void onSuccess(DelegantTypeGroupModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_GROUP_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    DelegantTypeGroupFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable arg0) {
                    Info.display(DelegantTypeGroupFormPanel.this.messages.commonerror(), DelegantTypeGroupFormPanel.this.messages.commonServererror());
                }
            });
        }
    }
}
