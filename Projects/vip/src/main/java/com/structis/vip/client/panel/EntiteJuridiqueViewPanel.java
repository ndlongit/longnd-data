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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.ModifyEntiteJuridiqueEvent;
import com.structis.vip.client.event.ModifyEntiteJuridiqueHandler;
import com.structis.vip.client.service.ClientFieldServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.model.EntiteJuridiqueModel;
import com.structis.vip.shared.model.FieFieldModel;

public class EntiteJuridiqueViewPanel extends AbstractPanel {

    private final FormData formData = new FormData("98%");
    private final int WIDTH = 500;

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

        setLayout(new FlowLayout(10));
        setScrollMode(Scroll.AUTO);
        setWidth(WIDTH);

        addHandler();
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        initBackLink();
        initUI();
        initEvent();
    }

    private void initBackLink() {
        LayoutContainer backLink = new LayoutContainer();
        backLink.setSize(WIDTH, -1);
        Label lblBack = new Label(messages.entiteJuridiqueBack());

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

                bus.fireEvent(event);
            }
        });

        add(backLink);
    }

    private void addHandler() {
        bus.addHandler(ModifyEntiteJuridiqueEvent.getType(), new ModifyEntiteJuridiqueHandler() {

            @Override
            public void onLoadAction(ModifyEntiteJuridiqueEvent event) {
                clientFieldService.getFieldsByGroupName(SessionServiceImpl.getInstance().getEntiteContext().getEntId(), "Société",
                        new AsyncCallback<List<FieFieldModel>>() {

                            @Override
                            public void onSuccess(List<FieFieldModel> arg0) {
                                if (arg0.isEmpty() == false && panel != null) {
                                    for (Field<?> field : panel.getFields()) {
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
                        model = event.getModel();
                        tfName.setValue(model.getName());
                        tfStatus.setValue(model.getStatut());
                        tfCapital.setValue(model.getCapital());
                        taAddress.setValue(model.getAddress());
                        tfRegistrationId.setValue(model.getRegistrationId());
                        tfRegistrationAddress.setValue(model.getRegistrationAddress());
                    }
                }
            }
        });
    }

    private void initUI() {
        panel = new FormPanel();
        panel.setHeading(messages.entiteJuridiqueTitle());
        panel.setFrame(true);
        panel.setButtonAlign(HorizontalAlignment.RIGHT);
        panel.setLabelWidth(200);
        panel.setWidth(WIDTH);

        tfName = new LabelField();
        tfName.setFieldLabel(messages.entiteJuridiqueName());
        tfName.setId("lblSocieteNom");
        panel.add(tfName, formData);

        tfStatus = new LabelField();
        tfStatus.setFieldLabel(messages.entiteJuridiqueStatut());
        tfStatus.setId("lblSocieteStatusJuridique");
        panel.add(tfStatus, formData);

        tfCapital = new LabelField();
        tfCapital.setFieldLabel(messages.entiteJuridiqueCapital());
        tfCapital.setId("lblSocieteCapital");
        panel.add(tfCapital, formData);

        taAddress = new LabelField();
        taAddress.setFieldLabel(messages.entiteJuridiqueAddress());
        taAddress.setId("lblSocieteAdresse");
        panel.add(taAddress, formData);

        tfRegistrationId = new LabelField();
        tfRegistrationId.setFieldLabel(messages.entiteJuridiqueRegistrationId());
        tfRegistrationId.setId("lblSocieteSiret");
        panel.add(tfRegistrationId, formData);

        tfRegistrationAddress = new LabelField();
        tfRegistrationAddress.setFieldLabel(messages.entiteJuridiqueRegistrationAddress());
        tfRegistrationAddress.setId("lblSocieteVille");
        panel.add(tfRegistrationAddress, formData);

        btnSave = new Button(messages.commonModifierButton());

        panel.addButton(btnSave);

        panel.getButtonBar().setStyleAttribute("padding-right", "16px");

        add(panel);
    }

    private void initEvent() {
        btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (panel.isValid()) {
                    modify();
                }
            }
        });
    }

    private void modify() {
        ContentEvent event = new ContentEvent();
        event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_EDIT_FORM);
        ModifyEntiteJuridiqueEvent subEvent = new ModifyEntiteJuridiqueEvent();
        subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_EDIT_FORM);
        subEvent.setModel(model);
        event.setEvent(subEvent);
        bus.fireEvent(event);
    }
}
