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
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.ModifyFormationEvent;
import com.structis.vip.client.event.ModifyFormationHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientFormationServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.FormationModel;

public class FormationFormPanel extends AbstractPanel {

    private final FormData formData = new FormData("98%");
    private final int WIDTH = 500;

    private ClientFormationServiceAsync clientFormationService = ClientFormationServiceAsync.Util.getInstance();

    private SimpleEventBus bus;
    private FormPanel panel;
    private TextField<String> tfName;
    private TextArea taDescription;
    private Button btnAmnuler;
    private Button btnSave;
    private FormationModel model;
    private boolean isEdit = true;

    public FormationFormPanel(SimpleEventBus bus) {
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
        this.bus.addHandler(ModifyFormationEvent.getType(), new ModifyFormationHandler() {

            @Override
            public void onLoadAction(ModifyFormationEvent event) {
                AppUtil.putInAdminEditMode();
                if (event.getModel() != null) {
                    FormationFormPanel.this.isEdit = true;
                    FormationFormPanel.this.model = event.getModel();
                    FormationFormPanel.this.tfName.setValue(FormationFormPanel.this.model.getLabel());
                    FormationFormPanel.this.taDescription.setValue(FormationFormPanel.this.model.getDescription());
                    FormationFormPanel.this.btnSave.setText(FormationFormPanel.this.messages.commonModifierButton());
                } else {
                    FormationFormPanel.this.model = null;
                    FormationFormPanel.this.isEdit = false;
                    FormationFormPanel.this.panel.reset();
                    FormationFormPanel.this.panel.clear();

                    FormationFormPanel.this.btnSave.setText(FormationFormPanel.this.messages.commonValiderButton());
                }
            }
        });
    }

    private void initData() {
    }

    private void initUI() {
        this.panel = new FormPanel();
        this.panel.setHeading(this.messages.formationformheader());
        this.panel.setFrame(true);
        this.panel.setButtonAlign(HorizontalAlignment.RIGHT);
        this.panel.setWidth(this.WIDTH);

        this.tfName = new TextField<String>();
        this.tfName.setFieldLabel(this.messages.formationnom());
        this.tfName.setMaxLength(80);
        this.tfName.setName("name");
        this.tfName.setAllowBlank(false);
        this.panel.add(this.tfName, this.formData);

        this.taDescription = new TextArea();
        this.taDescription.setFieldLabel(this.messages.formationdescription());
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
        Label lblBack = new Label(this.messages.formationback());

        lblBack.setStyleName("x-link-item");
        backLink.setStyleAttribute("margin-bottom", "20px	");
        backLink.add(lblBack);

        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_FORMATION_LIST);
                    FormationFormPanel.this.bus.fireEvent(contentEvent);
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
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_FORMATION_LIST);
                FormationFormPanel.this.bus.fireEvent(event);
                AppUtil.removeAdminInEditMode();
            }
        });

        this.btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (FormationFormPanel.this.panel.isValid()) {
                    FormationFormPanel.this.save();
                }
            }
        });
    }

    private void save() {
        if (this.model == null) {
            this.model = new FormationModel();
        }
        this.model.setLabel(this.tfName.getValue());
        this.model.setDescription(this.taDescription.getValue());
        this.model.setEntite(SessionServiceImpl.getInstance().getEntiteContext());

        if (this.isEdit == false) {
            this.clientFormationService.insert(this.model, new AsyncCallback<FormationModel>() {

                @Override
                public void onSuccess(FormationModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_FORMATION_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    FormationFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable caught) {
                    Info.display(FormationFormPanel.this.messages.commonerror(), FormationFormPanel.this.messages.commonServererror());
                }
            });
        } else {
            this.clientFormationService.update(this.model, new AsyncCallback<FormationModel>() {

                @Override
                public void onSuccess(FormationModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_FORMATION_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    FormationFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable arg0) {
                    Info.display(FormationFormPanel.this.messages.commonerror(), FormationFormPanel.this.messages.commonServererror());
                }
            });
        }
    }
}
