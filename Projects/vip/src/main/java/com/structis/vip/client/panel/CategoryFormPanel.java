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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.ModifyCategoryEvent;
import com.structis.vip.client.event.ModifyCategoryHandler;
import com.structis.vip.client.service.ClientCategoryServiceAsync;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.CategoryModel;

public class CategoryFormPanel extends AbstractPanel {

    private final FormData formData = new FormData("98%");
    private final int WIDTH = 500;

    private ClientCategoryServiceAsync clientCategoryService = ClientCategoryServiceAsync.Util.getInstance();

    private FormPanel panel;
    private TextField<String> tfName;
    private Button btnAmnuler;
    private Button btnSave;
    private CategoryModel model;
    private boolean isEdit = true;

    public CategoryFormPanel(SimpleEventBus bus) {
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

    private void addHandler() {
        bus.addHandler(ModifyCategoryEvent.getType(), new ModifyCategoryHandler() {

            @Override
            public void onLoadAction(ModifyCategoryEvent event) {
                AppUtil.putInAdminEditMode();
                if (event.getModel() != null) {
                    isEdit = true;
                    model = event.getModel();
                    tfName.setValue(model.getName());

                    btnSave.setText(messages.commonModifierButton());
                } else {
                    model = null;
                    isEdit = false;
                    panel.reset();
                    panel.clear();

                    btnSave.setText(messages.commonValiderButton());
                }
            }
        });
    }

    private void initUI() {
        panel = new FormPanel();
        panel.setHeading(messages.categoryformheader());
        panel.setFrame(true);
        panel.setButtonAlign(HorizontalAlignment.RIGHT);
        panel.setWidth(WIDTH);

        tfName = new TextField<String>();
        tfName.setFieldLabel(messages.categorynom());
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
        Label lblBack = new Label(messages.categoryback());

        lblBack.setStyleName("x-link-item");
        backLink.setStyleAttribute("margin-bottom", "20px	");
        backLink.add(lblBack);

        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CATEGORY_LIST);
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
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CATEGORY_LIST);
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
            model = new CategoryModel();
        }
        model.setName(tfName.getValue());

        if (isEdit == false) {
            clientCategoryService.insert(model, new AsyncCallback<CategoryModel>() {

                @Override
                public void onSuccess(CategoryModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CATEGORY_LIST);
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
            clientCategoryService.update(model, new AsyncCallback<CategoryModel>() {

                @Override
                public void onSuccess(CategoryModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CATEGORY_LIST);
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
