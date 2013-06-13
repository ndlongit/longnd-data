package com.structis.vip.client.panel;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
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
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.ModifyPerimetreTypeEvent;
import com.structis.vip.client.event.ModifyPerimetreTypeHandler;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.service.ClientPerimetreTypeServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.PerimetreTypeModel;

public class PerimetreTypeFormPanel extends AbstractPanel {

    private final FormData formData = new FormData("98%");
    private final int WIDTH = 500;

    private ClientPerimetreTypeServiceAsync clientPerimetreTypeService = ClientPerimetreTypeServiceAsync.Util.getInstance();

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
        bus.addHandler(ModifyPerimetreTypeEvent.getType(), new ModifyPerimetreTypeHandler() {

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
        panel = new FormPanel();
        panel.setLabelWidth(200);
        panel.setHeading(messages.perimetretypeformheader());
        panel.setFrame(true);
        panel.setButtonAlign(HorizontalAlignment.RIGHT);
        panel.setWidth(WIDTH);

        tfName = new TextField<String>();
        tfName.setFieldLabel(messages.perimetretypename());
        tfName.setMaxLength(80);
        tfName.setName("label");
        tfName.setAllowBlank(false);
        panel.add(tfName, formData);

        roSubOui = new Radio();
        roSubOui.setBoxLabel(messages.commonOui());
        roSubOui.setValue(true);

        roSubNon = new Radio();
        roSubNon.setBoxLabel(messages.commonNon());

        rgSub = new RadioGroup();
        rgSub.setFieldLabel(messages.perimetretypeSubdelegable());
        rgSub.add(roSubOui);
        rgSub.add(roSubNon);
        rgSub.setSelectionRequired(true);
        panel.add(rgSub, formData);

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

        add(backLink);
    }

    private void initEvent() {
        btnAmnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                ContentEvent event = new ContentEvent();
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_PERIMETRE_TYPE_LIST);
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
            model = new PerimetreTypeModel();
        }
        model.setName(tfName.getValue());

        model.setEntite(SessionServiceImpl.getInstance().getEntiteContext());

        model.setIsSubdelegable(0);
        if (roSubOui.getValue() == true) {
            model.setIsSubdelegable(1);
        }

        if (isEdit == false) {
            clientPerimetreTypeService.insert(model, new AsyncCallbackWithErrorResolution<PerimetreTypeModel>() {

                @Override
                public void onSuccess(PerimetreTypeModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_PERIMETRE_TYPE_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }
            });
        } else {
            clientPerimetreTypeService.update(model, new AsyncCallbackWithErrorResolution<PerimetreTypeModel>() {

                @Override
                public void onSuccess(PerimetreTypeModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_PERIMETRE_TYPE_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }
            });
        }
    }
}
