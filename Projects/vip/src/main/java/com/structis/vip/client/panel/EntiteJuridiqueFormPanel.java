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
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.ModifyEntiteJuridiqueEvent;
import com.structis.vip.client.event.ModifyEntiteJuridiqueHandler;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.service.ClientEntiteJuridiqueServiceAsync;
import com.structis.vip.client.service.ClientFieldServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.EntiteJuridiqueModel;
import com.structis.vip.shared.model.FieFieldModel;

public class EntiteJuridiqueFormPanel extends AbstractPanel {

    private final FormData formData = new FormData("98%");
    private final int WIDTH = 500;

    private ClientEntiteJuridiqueServiceAsync clientEntiteJuridiqueService = ClientEntiteJuridiqueServiceAsync.Util.getInstance();
    private ClientFieldServiceAsync clientFieldService = ClientFieldServiceAsync.Util.getInstance();

    private FormPanel panel;
    private TextField<String> tfName;
    private TextField<String> tfStatus;
    private TextField<String> tfCapital;
    private TextArea taAddress;
    private TextField<String> tfRegistrationId;
    private TextField<String> tfRegistrationAddress;
    private Button btnAmnuler;
    private Button btnSave;
    private EntiteJuridiqueModel model;
    private boolean isEdit = true;

    public EntiteJuridiqueFormPanel(SimpleEventBus bus) {
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
                if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    ContentEvent event = new ContentEvent();
                    event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);

                    ModifyEntiteJuridiqueEvent subEvent = new ModifyEntiteJuridiqueEvent();
                    subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);
                    subEvent.setModel(null);
                    event.setEvent(subEvent);

                    bus.fireEvent(event);
                }
            }
        });

        add(backLink);
    }

    private void addHandler() {
        bus.addHandler(ModifyEntiteJuridiqueEvent.getType(), new ModifyEntiteJuridiqueHandler() {

            @Override
            public void onLoadAction(ModifyEntiteJuridiqueEvent event) {
                clientFieldService.getFieldsByGroupName(SessionServiceImpl.getInstance().getEntiteContext().getEntId(), "Société",
                        new AsyncCallbackWithErrorResolution<List<FieFieldModel>>() {

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
                        });

                if (ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_EDIT_FORM == event.getMode()) {
                    AppUtil.putInAdminEditMode();
                    if (event.getModel() != null) {
                        isEdit = true;
                        model = event.getModel();
                        tfName.setValue(model.getName());
                        tfStatus.setValue(model.getStatut());
                        tfCapital.setValue(model.getCapital());
                        taAddress.setValue(model.getAddress());
                        tfRegistrationId.setValue(model.getRegistrationId());
                        tfRegistrationAddress.setValue(model.getRegistrationAddress());
                    } else {
                        model = null;
                        isEdit = false;
                        panel.reset();
                        panel.clear();
                    }
                }
            }
        });
    }

    private void initData() {
    }

    private void initUI() {
        panel = new FormPanel();
        panel.setHeading(messages.entiteJuridiqueTitle());
        panel.setFrame(true);
        panel.setButtonAlign(HorizontalAlignment.RIGHT);
        panel.setLabelWidth(200);
        panel.setWidth(WIDTH);

        tfName = new TextField<String>();
        tfName.setFieldLabel(messages.entiteJuridiqueName());
        tfName.setMaxLength(80);
        tfName.setId("lblSocieteNom");
        tfName.setAllowBlank(false);
        panel.add(tfName, formData);

        tfStatus = new TextField<String>();
        tfStatus.setFieldLabel(messages.entiteJuridiqueStatut());
        tfStatus.setMaxLength(80);
        tfStatus.setId("lblSocieteStatusJuridique");
        tfStatus.setAllowBlank(false);
        panel.add(tfStatus, formData);

        tfCapital = new TextField<String>();
        tfCapital.setFieldLabel(messages.entiteJuridiqueCapital());
        tfCapital.setMaxLength(80);
        tfCapital.setId("lblSocieteCapital");
        tfCapital.setAllowBlank(false);
        panel.add(tfCapital, formData);

        taAddress = new TextArea();
        taAddress.setFieldLabel(messages.entiteJuridiqueAddress());
        taAddress.setId("lblSocieteAdresse");
        taAddress.setMaxLength(255);
        panel.add(taAddress, formData);

        tfRegistrationId = new TextField<String>();
        tfRegistrationId.setFieldLabel(messages.entiteJuridiqueRegistrationId());
        tfRegistrationId.setMaxLength(80);
        tfRegistrationId.setId("lblSocieteSiret");
        tfRegistrationId.setAllowBlank(false);
        panel.add(tfRegistrationId, formData);

        tfRegistrationAddress = new TextField<String>();
        tfRegistrationAddress.setFieldLabel(messages.entiteJuridiqueRegistrationAddress());
        tfRegistrationAddress.setMaxLength(80);
        tfRegistrationAddress.setId("lblSocieteVille");
        tfRegistrationAddress.setAllowBlank(false);
        panel.add(tfRegistrationAddress, formData);

        btnAmnuler = new Button(messages.commonAnnulerButton());
        btnSave = new Button(messages.commonValiderButton());

        panel.addButton(btnAmnuler);
        panel.addButton(btnSave);

        panel.getButtonBar().setStyleAttribute("padding-right", "16px");

        add(panel);
    }

    private void initEvent() {
        btnAmnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);

                ModifyEntiteJuridiqueEvent subEvent = new ModifyEntiteJuridiqueEvent();
                subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);
                subEvent.setModel(null);
                event.setEvent(subEvent);

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
            model = new EntiteJuridiqueModel();
        }
        model.setName(tfName.getValue());
        model.setStatut(tfStatus.getValue());
        model.setCapital(tfCapital.getValue());
        model.setAddress(taAddress.getValue());
        model.setRegistrationId(tfRegistrationId.getValue());
        model.setRegistrationAddress(tfRegistrationAddress.getValue());
        model.setEntite(SessionServiceImpl.getInstance().getEntiteContext());

        if (isEdit == false) {
            clientEntiteJuridiqueService.insert(model, new AsyncCallbackWithErrorResolution<EntiteJuridiqueModel>() {

                @Override
                public void onSuccess(EntiteJuridiqueModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);

                    ModifyEntiteJuridiqueEvent subEvent = new ModifyEntiteJuridiqueEvent();
                    subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);
                    subEvent.setModel(null);
                    contentEvent.setEvent(subEvent);

                    bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }
            });
        } else {
            clientEntiteJuridiqueService.update(model, new AsyncCallbackWithErrorResolution<EntiteJuridiqueModel>() {

                @Override
                public void onSuccess(EntiteJuridiqueModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);

                    ModifyEntiteJuridiqueEvent event = new ModifyEntiteJuridiqueEvent();
                    event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);
                    event.setModel(null);
                    contentEvent.setEvent(event);

                    bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }
            });
        }
    }
}
