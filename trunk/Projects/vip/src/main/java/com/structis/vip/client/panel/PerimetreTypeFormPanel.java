package com.structis.vip.client.panel;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.ModifyPerimetreTypeEvent;
import com.structis.vip.client.event.ModifyPerimetreTypeHandler;
import com.structis.vip.client.service.ClientPerimetreTypeServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.PerimetreTypeModel;

public class PerimetreTypeFormPanel extends AbstractPanel {

    private final FormData formData = new FormData("98%");
    private final int WIDTH = 500;

    private ClientPerimetreTypeServiceAsync clientPerimetreTypeService = ClientPerimetreTypeServiceAsync.Util.getInstance();

    private SimpleEventBus bus;
    private FormPanel panel;
    private TextField<String> tfName;
    private Radio roSubOui;
    private Radio roSubNon;
    private RadioGroup rgSub;
    private Button btnAmnuler;
    private Button btnSave;
    private PerimetreTypeModel model;
    private boolean isEdit = true;

    public PerimetreTypeFormPanel(SimpleEventBus bus) {
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
        this.bus.addHandler(ModifyPerimetreTypeEvent.getType(), new ModifyPerimetreTypeHandler() {

            @Override
            public void onLoadAction(ModifyPerimetreTypeEvent event) {
                AppUtil.putInAdminEditMode();
                if (event.getModel() != null) {
                    isEdit = true;
                    model = event.getModel();
                    tfName.setValue(model.getName());
                    if (model.getIsSubdelegable() != null) {
                        rgSub.setValue((model.getIsSubdelegable() == 1) ? roSubOui : roSubNon);
                    } else {
                        rgSub.setValue(roSubNon);
                    }
                } else {
                    model = null;
                    isEdit = false;
                    panel.reset();
                    panel.clear();
                    rgSub.setValue(roSubNon);
                }

            }
        });
    }

    private void initData() {
    }

    private void initUI() {
        this.panel = new FormPanel();
        this.panel.setLabelWidth(200);
        this.panel.setHeading(messages.perimetretypeformheader());
        this.panel.setFrame(true);
        this.panel.setButtonAlign(HorizontalAlignment.RIGHT);
        this.panel.setWidth(this.WIDTH);

        this.tfName = new TextField<String>();
        this.tfName.setFieldLabel(messages.perimetretypename());
        this.tfName.setMaxLength(80);
        this.tfName.setName("label");
        this.tfName.setAllowBlank(false);
        this.panel.add(this.tfName, this.formData);

        this.roSubOui = new Radio();
        this.roSubOui.setBoxLabel(messages.commonOui());
        this.roSubOui.setValue(true);

        this.roSubNon = new Radio();
        this.roSubNon.setBoxLabel(messages.commonNon());

        this.rgSub = new RadioGroup();
        this.rgSub.setFieldLabel(messages.perimetretypeSubdelegable());
        this.rgSub.add(this.roSubOui);
        this.rgSub.add(this.roSubNon);
        this.rgSub.setSelectionRequired(true);
        this.panel.add(this.rgSub, this.formData);

        this.btnAmnuler = new Button(messages.commonAnnulerButton());
        this.btnSave = new Button(messages.commonValiderButton());

        this.panel.addButton(this.btnAmnuler);
        this.panel.addButton(this.btnSave);

        this.panel.getButtonBar().setStyleAttribute("padding-right", "16px");

        this.add(this.panel);
    }

    private void initBackLink() {
        LayoutContainer backLink = new LayoutContainer();
        backLink.setSize(this.WIDTH, -1);
        Label lblBack = new Label(messages.perimetretypeback());

        lblBack.setStyleName("x-link-item");
        backLink.setStyleAttribute("margin-bottom", "20px	");
        backLink.add(lblBack);

        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_PERIMETRE_TYPE_LIST);
                    bus.fireEvent(contentEvent);
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
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_PERIMETRE_TYPE_LIST);
                bus.fireEvent(event);
                AppUtil.removeAdminInEditMode();
            }
        });

        this.btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (panel.isValid()) {
                    save();
                }
            }
        });
    }

    private void save() {
        if (this.model == null) {
            this.model = new PerimetreTypeModel();
        }
        this.model.setName(this.tfName.getValue());

        this.model.setEntite(SessionServiceImpl.getInstance().getEntiteContext());

        this.model.setIsSubdelegable(0);
        if (this.roSubOui.getValue() == true) {
            this.model.setIsSubdelegable(1);
        }

        if (this.isEdit == false) {
            this.clientPerimetreTypeService.insert(this.model, new AsyncCallback<PerimetreTypeModel>() {

                @Override
                public void onSuccess(PerimetreTypeModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_PERIMETRE_TYPE_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable caught) {
                    Info.display(messages.commonerror(), messages.commonServererror());
                }
            });
        } else {
            this.clientPerimetreTypeService.update(this.model, new AsyncCallback<PerimetreTypeModel>() {

                @Override
                public void onSuccess(PerimetreTypeModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_PERIMETRE_TYPE_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable arg0) {
                    Info.display(messages.commonerror(), messages.commonServererror());
                }
            });
        }
    }
}
