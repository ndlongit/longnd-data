package com.structis.vip.client.panel;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.ModifyDelegantTypeGroupEvent;
import com.structis.vip.client.event.ModifyDelegantTypeGroupHandler;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.service.ClientDelegantTypeGroupServiceAsync;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.DelegantTypeGroupModel;

public class DelegantTypeGroupFormPanel extends AbstractPanel {

    private final FormData formData = new FormData("98%");
    private final int WIDTH = 500;

    private ClientDelegantTypeGroupServiceAsync clientDelegantTypeGroupService = ClientDelegantTypeGroupServiceAsync.Util.getInstance();

    private FormPanel panel;
    private TextField<String> tfGroup;
    private TextField<String> tfName;
    private Button btnAmnuler;
    private Button btnSave;
    private DelegantTypeGroupModel model;
    private boolean isEdit = true;

    public DelegantTypeGroupFormPanel(SimpleEventBus bus) {
        this.bus = bus;

        setLayout(new FlowLayout(10));
        setScrollMode(Scroll.AUTO);
        setWidth(WIDTH);

        addHandler();
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        initData();

        initBackLink();
        initUI();
        initEvent();
    }

    private void addHandler() {
        bus.addHandler(ModifyDelegantTypeGroupEvent.getType(), new ModifyDelegantTypeGroupHandler() {

            @Override
            public void onLoadAction(ModifyDelegantTypeGroupEvent event) {
                AppUtil.putInAdminEditMode();
                if (event.getModel() != null) {
                    isEdit = true;
                    model = event.getModel();
                    tfName.setValue(model.getName());
                    tfGroup.setValue(model.getGroup());
                    btnSave.setText(DelegantTypeGroupFormPanel.messages.commonModifierButton());
                } else {
                    model = null;
                    isEdit = false;
                    panel.reset();
                    panel.clear();

                    btnSave.setText(DelegantTypeGroupFormPanel.messages.commonValiderButton());
                }
            }
        });
    }

    private void initData() {
    }

    private void initUI() {
        panel = new FormPanel();
        panel.setHeading(messages.deleganttypegroupformheader());
        panel.setFrame(true);
        panel.setButtonAlign(HorizontalAlignment.RIGHT);
        panel.setWidth(WIDTH);

        tfGroup = new TextField<String>();
        tfGroup.setFieldLabel(messages.deleganttypegroupgroup());
        tfGroup.setMaxLength(10);
        tfGroup.setName("group");
        tfGroup.setAllowBlank(false);
        panel.add(tfGroup, formData);

        tfName = new TextField<String>();
        tfName.setFieldLabel(messages.deleganttypegroupnom());
        tfName.setMaxLength(50);
        tfName.setName("name");
        tfName.setAllowBlank(false);
        panel.add(tfName, formData);

        btnAmnuler = new Button(messages.commonAnnulerButton());
        btnSave = new Button(messages.commonValiderButton());

        panel.addButton(btnAmnuler);
        panel.addButton(btnSave);

        panel.getButtonBar().setStyleAttribute("padding-right", "16px");

        add(panel);
    }

    private void initBackLink() {
        LayoutContainer backLink = new LayoutContainer();
        backLink.setSize(WIDTH, -1);
        Label lblBack = new Label(messages.deleganttypegroupback());

        lblBack.setStyleName("x-link-item");
        backLink.setStyleAttribute("margin-bottom", "20px	");
        backLink.add(lblBack);

        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_GROUP_LIST);
                    bus.fireEvent(contentEvent);
                }
            }
        });

        add(backLink);
    }

    private void initEvent() {
        btnAmnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_GROUP_LIST);
                bus.fireEvent(event);
                AppUtil.removeAdminInEditMode();
            }
        });

        btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (panel.isValid()) {
                    save();
                }
            }
        });
    }

    private void save() {
        if (model == null) {
            model = new DelegantTypeGroupModel();
        }
        model.setName(tfName.getValue());
        model.setGroup(tfGroup.getValue());

        if (isEdit == false) {
            clientDelegantTypeGroupService.insert(model, new AsyncCallbackWithErrorResolution<DelegantTypeGroupModel>() {

                @Override
                public void onSuccess(DelegantTypeGroupModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_GROUP_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }
            });
        } else {
            clientDelegantTypeGroupService.update(model, new AsyncCallbackWithErrorResolution<DelegantTypeGroupModel>() {

                @Override
                public void onSuccess(DelegantTypeGroupModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_GROUP_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }
            });
        }
    }
}
