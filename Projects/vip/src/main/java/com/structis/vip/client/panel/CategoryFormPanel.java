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
import com.google.gwt.core.client.GWT;
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
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientCategoryServiceAsync;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.CategoryModel;

public class CategoryFormPanel extends AbstractPanel {

    private final FormData formData = new FormData("98%");
    private final int WIDTH = 500;

    private ClientCategoryServiceAsync clientCategoryService = ClientCategoryServiceAsync.Util.getInstance();

    private SimpleEventBus bus;
    private FormPanel panel;
    private TextField<String> tfName;
    private Button btnAmnuler;
    private Button btnSave;
    private CategoryModel model;
    private boolean isEdit = true;

    public CategoryFormPanel(SimpleEventBus bus) {
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
        this.bus.addHandler(ModifyCategoryEvent.getType(), new ModifyCategoryHandler() {

            @Override
            public void onLoadAction(ModifyCategoryEvent event) {
                AppUtil.putInAdminEditMode();
                if (event.getModel() != null) {
                    CategoryFormPanel.this.isEdit = true;
                    CategoryFormPanel.this.model = event.getModel();
                    CategoryFormPanel.this.tfName.setValue(CategoryFormPanel.this.model.getName());

                    CategoryFormPanel.this.btnSave.setText(CategoryFormPanel.this.messages.commonModifierButton());
                } else {
                    CategoryFormPanel.this.model = null;
                    CategoryFormPanel.this.isEdit = false;
                    CategoryFormPanel.this.panel.reset();
                    CategoryFormPanel.this.panel.clear();

                    CategoryFormPanel.this.btnSave.setText(CategoryFormPanel.this.messages.commonValiderButton());
                }
            }
        });
    }

    private void initData() {
    }

    private void initUI() {
        this.panel = new FormPanel();
        this.panel.setHeading(this.messages.categoryformheader());
        this.panel.setFrame(true);
        this.panel.setButtonAlign(HorizontalAlignment.RIGHT);
        this.panel.setWidth(this.WIDTH);

        this.tfName = new TextField<String>();
        this.tfName.setFieldLabel(this.messages.categorynom());
        this.tfName.setMaxLength(50);
        this.tfName.setName("name");
        this.tfName.setAllowBlank(false);
        this.panel.add(this.tfName, this.formData);

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
        Label lblBack = new Label(this.messages.categoryback());

        lblBack.setStyleName("x-link-item");
        backLink.setStyleAttribute("margin-bottom", "20px	");
        backLink.add(lblBack);

        lblBack.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CATEGORY_LIST);
                    CategoryFormPanel.this.bus.fireEvent(contentEvent);
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
                event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CATEGORY_LIST);
                CategoryFormPanel.this.bus.fireEvent(event);
                AppUtil.removeAdminInEditMode();
            }
        });

        this.btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (CategoryFormPanel.this.panel.isValid()) {
                    CategoryFormPanel.this.save();
                }
            }
        });
    }

    private void save() {
        if (this.model == null) {
            this.model = new CategoryModel();
        }
        this.model.setName(this.tfName.getValue());

        if (this.isEdit == false) {
            this.clientCategoryService.insert(this.model, new AsyncCallback<CategoryModel>() {

                @Override
                public void onSuccess(CategoryModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CATEGORY_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    CategoryFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable caught) {
                    Info.display(CategoryFormPanel.this.messages.commonerror(), CategoryFormPanel.this.messages.commonServererror());
                }
            });
        } else {
            this.clientCategoryService.update(this.model, new AsyncCallback<CategoryModel>() {

                @Override
                public void onSuccess(CategoryModel arg0) {
                    ContentEvent contentEvent = new ContentEvent();
                    contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CATEGORY_LIST);
                    contentEvent.setEvent(new LoadDocumentEvent());
                    CategoryFormPanel.this.bus.fireEvent(contentEvent);
                    AppUtil.removeAdminInEditMode();
                }

                @Override
                public void onFailure(Throwable arg0) {
                    Info.display(CategoryFormPanel.this.messages.commonerror(), CategoryFormPanel.this.messages.commonServererror());
                }
            });
        }
    }
}
