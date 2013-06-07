package com.structis.vip.client.panel;

import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
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
import com.structis.vip.client.event.ModifyEntiteJuridiqueEvent;
import com.structis.vip.client.event.ModifyEntiteJuridiqueHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientFieldServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.model.EntiteJuridiqueModel;
import com.structis.vip.shared.model.FieFieldModel;

public class EntiteJuridiqueViewPanel extends AbstractPanel {

    private final FormData formData = new FormData("98%");
    private final int WIDTH = 500;

    private SimpleEventBus bus;
    private FormPanel panel;
    private LabelField tfName;
    private LabelField tfStatus;
    private LabelField tfCapital;
    private LabelField taAddress;
    private LabelField tfRegistrationId;
    private LabelField tfRegistrationAddress;
    private Button btnSave;
    private EntiteJuridiqueModel model;

    private ClientFieldServiceAsync clientFieldService = ClientFieldServiceAsync.Util.getInstance();

    public EntiteJuridiqueViewPanel(SimpleEventBus bus) {
        this.bus = bus;

        this.setLayout(new FlowLayout(10));
        this.setScrollMode(Scroll.AUTO);
        this.setWidth(this.WIDTH);

        this.addHandler();
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        this.initBackLink();
        this.initUI();
        this.initEvent();
    }

    private void initBackLink() {
        LayoutContainer backLink = new LayoutContainer();
        backLink.setSize(this.WIDTH, -1);
        Label lblBack = new Label(this.messages.entiteJuridiqueBack());

        lblBack.setStyleName("x-link-item");
        backLink.setStyleAttribute("margin-bottom", "20px	");
        backLink.add(lblBack);

        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);

                ModifyEntiteJuridiqueEvent subEvent = new ModifyEntiteJuridiqueEvent();
                subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);
                subEvent.setModel(null);
                event.setEvent(subEvent);

                EntiteJuridiqueViewPanel.this.bus.fireEvent(event);
            }
        });

        this.add(backLink);
    }

    private void addHandler() {
        this.bus.addHandler(ModifyEntiteJuridiqueEvent.getType(), new ModifyEntiteJuridiqueHandler() {

            @Override
            public void onLoadAction(ModifyEntiteJuridiqueEvent event) {
                EntiteJuridiqueViewPanel.this.clientFieldService.getFieldsByGroupName(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                        "Société", new AsyncCallback<List<FieFieldModel>>() {

                            @Override
                            public void onSuccess(List<FieFieldModel> arg0) {
                                if (arg0.isEmpty() == false && EntiteJuridiqueViewPanel.this.panel != null) {
                                    for (Field<?> field : EntiteJuridiqueViewPanel.this.panel.getFields()) {
                                        for (FieFieldModel fieFieldModel : arg0) {
                                            if ((field.getId() != null) && (field.getId().equals(fieFieldModel.getFormFieldId()))) {
                                                field.setFieldLabel(fieFieldModel.getLabel());
                                                break;
                                            }
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Throwable arg0) {
                            }
                        });

                if (ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_VIEW_FORM == event.getMode()) {
                    if (event.getModel() != null) {
                        EntiteJuridiqueViewPanel.this.model = event.getModel();
                        EntiteJuridiqueViewPanel.this.tfName.setValue(EntiteJuridiqueViewPanel.this.model.getName());
                        EntiteJuridiqueViewPanel.this.tfStatus.setValue(EntiteJuridiqueViewPanel.this.model.getStatut());
                        EntiteJuridiqueViewPanel.this.tfCapital.setValue(EntiteJuridiqueViewPanel.this.model.getCapital());
                        EntiteJuridiqueViewPanel.this.taAddress.setValue(EntiteJuridiqueViewPanel.this.model.getAddress());
                        EntiteJuridiqueViewPanel.this.tfRegistrationId.setValue(EntiteJuridiqueViewPanel.this.model.getRegistrationId());
                        EntiteJuridiqueViewPanel.this.tfRegistrationAddress.setValue(EntiteJuridiqueViewPanel.this.model.getRegistrationAddress());
                    }
                }
            }
        });
    }

    private void initUI() {
        this.panel = new FormPanel();
        this.panel.setHeading(this.messages.entiteJuridiqueTitle());
        this.panel.setFrame(true);
        this.panel.setButtonAlign(HorizontalAlignment.RIGHT);
        this.panel.setLabelWidth(200);
        this.panel.setWidth(this.WIDTH);

        this.tfName = new LabelField();
        this.tfName.setFieldLabel(this.messages.entiteJuridiqueName());
        this.tfName.setId("lblSocieteNom");
        this.panel.add(this.tfName, this.formData);

        this.tfStatus = new LabelField();
        this.tfStatus.setFieldLabel(this.messages.entiteJuridiqueStatut());
        this.tfStatus.setId("lblSocieteStatusJuridique");
        this.panel.add(this.tfStatus, this.formData);

        this.tfCapital = new LabelField();
        this.tfCapital.setFieldLabel(this.messages.entiteJuridiqueCapital());
        this.tfCapital.setId("lblSocieteCapital");
        this.panel.add(this.tfCapital, this.formData);

        this.taAddress = new LabelField();
        this.taAddress.setFieldLabel(this.messages.entiteJuridiqueAddress());
        this.taAddress.setId("lblSocieteAdresse");
        this.panel.add(this.taAddress, this.formData);

        this.tfRegistrationId = new LabelField();
        this.tfRegistrationId.setFieldLabel(this.messages.entiteJuridiqueRegistrationId());
        this.tfRegistrationId.setId("lblSocieteSiret");
        this.panel.add(this.tfRegistrationId, this.formData);

        this.tfRegistrationAddress = new LabelField();
        this.tfRegistrationAddress.setFieldLabel(this.messages.entiteJuridiqueRegistrationAddress());
        this.tfRegistrationAddress.setId("lblSocieteVille");
        this.panel.add(this.tfRegistrationAddress, this.formData);

        this.btnSave = new Button(this.messages.commonModifierButton());

        this.panel.addButton(this.btnSave);

        this.panel.getButtonBar().setStyleAttribute("padding-right", "16px");

        this.add(this.panel);
    }

    private void initEvent() {
        this.btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (EntiteJuridiqueViewPanel.this.panel.isValid()) {
                    EntiteJuridiqueViewPanel.this.modify();
                }
            }
        });
    }

    private void modify() {
        ContentEvent event = new ContentEvent();
        event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_EDIT_FORM);
        ModifyEntiteJuridiqueEvent subEvent = new ModifyEntiteJuridiqueEvent();
        subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_EDIT_FORM);
        subEvent.setModel(this.model);
        event.setEvent(subEvent);
        this.bus.fireEvent(event);
    }
}
