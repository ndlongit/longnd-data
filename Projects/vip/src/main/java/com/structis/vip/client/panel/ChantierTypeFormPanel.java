package com.structis.vip.client.panel;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.DateTimePropertyEditor;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
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
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.ModifyChantierTypeEvent;
import com.structis.vip.client.event.ModifyChantierTypeHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientChantierTypeServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.shared.model.ChantierTypeModel;

public class ChantierTypeFormPanel extends AbstractPanel {

    private final FormData formData = new FormData("98%");
    private final int WIDTH = 500;

    private ClientChantierTypeServiceAsync clientChantierTypeService = ClientChantierTypeServiceAsync.Util.getInstance();

    private SimpleEventBus bus;
    private FormPanel panel;
    private TextField<String> tfName;
    private DateField tfEndDate;
    private Radio roSubOui;
    private Radio roSubNon;
    private RadioGroup rgSub;
    private Button btnAmnuler;
    private Button btnSave;
    private ChantierTypeModel model;
    private boolean isEdit = true;

    public ChantierTypeFormPanel(SimpleEventBus bus) {
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
        this.bus.addHandler(ModifyChantierTypeEvent.getType(), new ModifyChantierTypeHandler() {

            @Override
            public void onLoadAction(ModifyChantierTypeEvent event) {
                AppUtil.putInAdminEditMode();
                if (event.getModel() != null) {
                    ChantierTypeFormPanel.this.isEdit = true;
                    ChantierTypeFormPanel.this.model = event.getModel();
                    ChantierTypeFormPanel.this.tfName.setValue(ChantierTypeFormPanel.this.model.getLabel());
                    if (ChantierTypeFormPanel.this.model.getIsSubdelegable() != null) {
                        ChantierTypeFormPanel.this.rgSub.setValue((ChantierTypeFormPanel.this.model.getIsSubdelegable() == 1) ? ChantierTypeFormPanel.this.roSubOui
                                : ChantierTypeFormPanel.this.roSubNon);
                    }
                } else {
                    ChantierTypeFormPanel.this.model = null;
                    ChantierTypeFormPanel.this.isEdit = false;
                    ChantierTypeFormPanel.this.panel.reset();
                    ChantierTypeFormPanel.this.panel.clear();
                }
                // add BYTP
                // if (ConstantClient.ENTITE_ID_IS_BYEFE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
                if (CommonUtils.belongsBYEFEGroup(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
                    ChantierTypeFormPanel.this.tfEndDate.setVisible(true);
                    ChantierTypeFormPanel.this.tfEndDate.setAllowBlank(false);
                    if (ChantierTypeFormPanel.this.isEdit) {
                        ChantierTypeFormPanel.this.tfEndDate.setValue(ChantierTypeFormPanel.this.model.getEndDate());
                    }
                } else {
                    ChantierTypeFormPanel.this.tfEndDate.setVisible(false);
                    ChantierTypeFormPanel.this.tfEndDate.setAllowBlank(true);
                    ChantierTypeFormPanel.this.tfEndDate.setValue(null);

                }
            }
        });
    }

    private void initData() {
    }

    private void initUI() {
        this.panel = new FormPanel();
        this.panel.setLabelWidth(200);
        this.panel.setHeading(this.messages.chantiertypeformheader());
        this.panel.setFrame(true);
        this.panel.setButtonAlign(HorizontalAlignment.RIGHT);
        this.panel.setWidth(this.WIDTH);

        this.tfName = new TextField<String>();
        this.tfName.setFieldLabel(this.messages.chantiertypelabel());
        this.tfName.setMaxLength(80);
        this.tfName.setName("label");
        this.tfName.setAllowBlank(false);
        this.panel.add(this.tfName, this.formData);

        this.tfEndDate = new DateField();
        this.tfEndDate.setPropertyEditor(new DateTimePropertyEditor(ClientConstant.DATE_FORMAT_DDMM));
        this.tfEndDate.setFieldLabel(this.messages.chantiertypeendDate());
        this.tfEndDate.setName("endDate");
        this.tfEndDate.setMaxLength(5);
        this.panel.add(this.tfEndDate, this.formData);

        this.roSubOui = new Radio();
        this.roSubOui.setBoxLabel(this.messages.commonOui());
        this.roSubOui.setValue(true);

        this.roSubNon = new Radio();
        this.roSubNon.setBoxLabel(this.messages.commonNon());

        this.rgSub = new RadioGroup();
        this.rgSub.setFieldLabel(this.messages.chantiertypechantierSubdelegable());
        this.rgSub.add(this.roSubOui);
        this.rgSub.add(this.roSubNon);
        this.rgSub.setSelectionRequired(true);
        this.panel.add(this.rgSub, this.formData);

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
        Label lblBack = new Label(this.messages.chantiertypeback());

        lblBack.setStyleName("x-link-item");
        backLink.setStyleAttribute("margin-bottom", "20px	");
        backLink.add(lblBack);

        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CHANTIER_TYPE_LIST);
                    ChantierTypeFormPanel.this.bus.fireEvent(contentEvent);
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
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CHANTIER_TYPE_LIST);
                ChantierTypeFormPanel.this.bus.fireEvent(event);
                AppUtil.removeAdminInEditMode();
            }
        });

        this.btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (ChantierTypeFormPanel.this.panel.isValid()) {
                    ChantierTypeFormPanel.this.save();
                }
            }
        });
    }

    private void save() {
        if (this.model == null) {
            this.model = new ChantierTypeModel();
        }
        this.model.setLabel(this.tfName.getValue());

        if (this.tfEndDate.isVisible()) {
            this.model.setEndDate(this.tfEndDate.getValue());
        }

        this.model.setEntite(SessionServiceImpl.getInstance().getEntiteContext());

        this.model.setIsSubdelegable(0);
        if (this.roSubOui.getValue() == true) {
            this.model.setIsSubdelegable(1);
        }

        if (this.isEdit == false) {
            this.clientChantierTypeService.insert(this.model, new AsyncCallback<ChantierTypeModel>() {

                @Override
                public void onSuccess(ChantierTypeModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CHANTIER_TYPE_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    ChantierTypeFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable caught) {
                    Info.display(ChantierTypeFormPanel.this.messages.commonerror(), ChantierTypeFormPanel.this.messages.commonServererror());
                }
            });
        } else {
            this.clientChantierTypeService.update(this.model, new AsyncCallback<ChantierTypeModel>() {

                @Override
                public void onSuccess(ChantierTypeModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CHANTIER_TYPE_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    ChantierTypeFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable arg0) {
                    Info.display(ChantierTypeFormPanel.this.messages.commonerror(), ChantierTypeFormPanel.this.messages.commonServererror());
                }
            });
        }
    }
}
