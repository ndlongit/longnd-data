package com.structis.vip.client.panel;

import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
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
import com.structis.vip.client.event.ModifyEntiteJuridiqueEvent;
import com.structis.vip.client.event.ModifyEntiteJuridiqueHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientEntiteJuridiqueServiceAsync;
import com.structis.vip.client.service.ClientFieldServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.EntiteJuridiqueModel;
import com.structis.vip.shared.model.FieFieldModel;

public class EntiteJuridiqueFormPanel extends LayoutContainer {

    private final Messages messages = GWT.create(Messages.class);
    private final FormData formData = new FormData("98%");
    private final int WIDTH = 500;

    private ClientEntiteJuridiqueServiceAsync clientEntiteJuridiqueService = ClientEntiteJuridiqueServiceAsync.Util.getInstance();
    private ClientFieldServiceAsync clientFieldService = ClientFieldServiceAsync.Util.getInstance();

    private SimpleEventBus bus;
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
                if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    ContentEvent event = new ContentEvent();
                    event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);

                    ModifyEntiteJuridiqueEvent subEvent = new ModifyEntiteJuridiqueEvent();
                    subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);
                    subEvent.setModel(null);
                    event.setEvent(subEvent);

                    EntiteJuridiqueFormPanel.this.bus.fireEvent(event);
                }
            }
        });

        this.add(backLink);
    }

    private void addHandler() {
        this.bus.addHandler(ModifyEntiteJuridiqueEvent.getType(), new ModifyEntiteJuridiqueHandler() {

            @Override
            public void onLoadAction(ModifyEntiteJuridiqueEvent event) {
                EntiteJuridiqueFormPanel.this.clientFieldService.getFieldsByGroupName(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                        "Société", new AsyncCallback<List<FieFieldModel>>() {

                            @Override
                            public void onSuccess(List<FieFieldModel> arg0) {
                                if (arg0.isEmpty() == false && EntiteJuridiqueFormPanel.this.panel != null) {
                                    for (Field<?> field : EntiteJuridiqueFormPanel.this.panel.getFields()) {
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

                if (ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_EDIT_FORM == event.getMode()) {
                    AppUtil.putInAdminEditMode();
                    if (event.getModel() != null) {
                        EntiteJuridiqueFormPanel.this.isEdit = true;
                        EntiteJuridiqueFormPanel.this.model = event.getModel();
                        EntiteJuridiqueFormPanel.this.tfName.setValue(EntiteJuridiqueFormPanel.this.model.getName());
                        EntiteJuridiqueFormPanel.this.tfStatus.setValue(EntiteJuridiqueFormPanel.this.model.getStatut());
                        EntiteJuridiqueFormPanel.this.tfCapital.setValue(EntiteJuridiqueFormPanel.this.model.getCapital());
                        EntiteJuridiqueFormPanel.this.taAddress.setValue(EntiteJuridiqueFormPanel.this.model.getAddress());
                        EntiteJuridiqueFormPanel.this.tfRegistrationId.setValue(EntiteJuridiqueFormPanel.this.model.getRegistrationId());
                        EntiteJuridiqueFormPanel.this.tfRegistrationAddress.setValue(EntiteJuridiqueFormPanel.this.model.getRegistrationAddress());
                    } else {
                        EntiteJuridiqueFormPanel.this.model = null;
                        EntiteJuridiqueFormPanel.this.isEdit = false;
                        EntiteJuridiqueFormPanel.this.panel.reset();
                        EntiteJuridiqueFormPanel.this.panel.clear();
                    }
                }
            }
        });
    }

    private void initData() {
    }

    private void initUI() {
        this.panel = new FormPanel();
        this.panel.setHeading(this.messages.entiteJuridiqueTitle());
        this.panel.setFrame(true);
        this.panel.setButtonAlign(HorizontalAlignment.RIGHT);
        this.panel.setLabelWidth(200);
        this.panel.setWidth(this.WIDTH);

        this.tfName = new TextField<String>();
        this.tfName.setFieldLabel(this.messages.entiteJuridiqueName());
        this.tfName.setMaxLength(80);
        this.tfName.setId("lblSocieteNom");
        this.tfName.setAllowBlank(false);
        this.panel.add(this.tfName, this.formData);

        this.tfStatus = new TextField<String>();
        this.tfStatus.setFieldLabel(this.messages.entiteJuridiqueStatut());
        this.tfStatus.setMaxLength(80);
        this.tfStatus.setId("lblSocieteStatusJuridique");
        this.tfStatus.setAllowBlank(false);
        this.panel.add(this.tfStatus, this.formData);

        this.tfCapital = new TextField<String>();
        this.tfCapital.setFieldLabel(this.messages.entiteJuridiqueCapital());
        this.tfCapital.setMaxLength(80);
        this.tfCapital.setId("lblSocieteCapital");
        this.tfCapital.setAllowBlank(false);
        this.panel.add(this.tfCapital, this.formData);

        this.taAddress = new TextArea();
        this.taAddress.setFieldLabel(this.messages.entiteJuridiqueAddress());
        this.taAddress.setId("lblSocieteAdresse");
        this.taAddress.setMaxLength(255);
        this.panel.add(this.taAddress, this.formData);

        this.tfRegistrationId = new TextField<String>();
        this.tfRegistrationId.setFieldLabel(this.messages.entiteJuridiqueRegistrationId());
        this.tfRegistrationId.setMaxLength(80);
        this.tfRegistrationId.setId("lblSocieteSiret");
        this.tfRegistrationId.setAllowBlank(false);
        this.panel.add(this.tfRegistrationId, this.formData);

        this.tfRegistrationAddress = new TextField<String>();
        this.tfRegistrationAddress.setFieldLabel(this.messages.entiteJuridiqueRegistrationAddress());
        this.tfRegistrationAddress.setMaxLength(80);
        this.tfRegistrationAddress.setId("lblSocieteVille");
        this.tfRegistrationAddress.setAllowBlank(false);
        this.panel.add(this.tfRegistrationAddress, this.formData);

        this.btnAmnuler = new Button(this.messages.commonAnnulerButton());
        this.btnSave = new Button(this.messages.commonValiderButton());

        this.panel.addButton(this.btnAmnuler);
        this.panel.addButton(this.btnSave);

        this.panel.getButtonBar().setStyleAttribute("padding-right", "16px");

        this.add(this.panel);
    }

    private void initEvent() {
        this.btnAmnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);

                ModifyEntiteJuridiqueEvent subEvent = new ModifyEntiteJuridiqueEvent();
                subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);
                subEvent.setModel(null);
                event.setEvent(subEvent);

                EntiteJuridiqueFormPanel.this.bus.fireEvent(event);
                AppUtil.removeAdminInEditMode();
            }
        });

        this.btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (EntiteJuridiqueFormPanel.this.panel.isValid()) {
                    EntiteJuridiqueFormPanel.this.save();
                }
            }
        });
    }

    private void save() {
        if (this.model == null) {
            this.model = new EntiteJuridiqueModel();
        }
        this.model.setName(this.tfName.getValue());
        this.model.setStatut(this.tfStatus.getValue());
        this.model.setCapital(this.tfCapital.getValue());
        this.model.setAddress(this.taAddress.getValue());
        this.model.setRegistrationId(this.tfRegistrationId.getValue());
        this.model.setRegistrationAddress(this.tfRegistrationAddress.getValue());
        this.model.setEntite(SessionServiceImpl.getInstance().getEntiteContext());

        if (this.isEdit == false) {
            this.clientEntiteJuridiqueService.insert(this.model, new AsyncCallback<EntiteJuridiqueModel>() {

                @Override
                public void onSuccess(EntiteJuridiqueModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);

                    ModifyEntiteJuridiqueEvent subEvent = new ModifyEntiteJuridiqueEvent();
                    subEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);
                    subEvent.setModel(null);
                    contentEvent.setEvent(subEvent);

                    EntiteJuridiqueFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable caught) {
                    Info.display(EntiteJuridiqueFormPanel.this.messages.commonerror(), EntiteJuridiqueFormPanel.this.messages.commonServererror());
                }
            });
        } else {
            this.clientEntiteJuridiqueService.update(this.model, new AsyncCallback<EntiteJuridiqueModel>() {

                @Override
                public void onSuccess(EntiteJuridiqueModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);

                    ModifyEntiteJuridiqueEvent event = new ModifyEntiteJuridiqueEvent();
                    event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);
                    event.setModel(null);
                    contentEvent.setEvent(event);

                    EntiteJuridiqueFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable arg0) {
                    Info.display(EntiteJuridiqueFormPanel.this.messages.commonerror(), EntiteJuridiqueFormPanel.this.messages.commonServererror());
                }
            });
        }
    }
}
