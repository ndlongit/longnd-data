package com.structis.vip.client.panel.document;

import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
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
import com.structis.vip.client.event.document.LoadDocEvent;
import com.structis.vip.client.event.document.ModifyDocEvent;
import com.structis.vip.client.event.document.ModifyDocHandler;
import com.structis.vip.client.panel.AbstractPanel;
import com.structis.vip.client.service.ClientCategoryServiceAsync;
import com.structis.vip.client.service.ClientDocumentServiceAsync;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.CategoryModel;
import com.structis.vip.shared.model.DocumentModel;

public class DocFormPanel extends AbstractPanel {

	private final FormData formData = new FormData("98%");
	private final int WIDTH = 500;

	private ClientDocumentServiceAsync clientDocService = ClientDocumentServiceAsync.Util.getInstance();

	private SimpleEventBus bus;
	private FormPanel panel;
	private TextField<String> tfDocName;
	private TextField<String> tfComment;
	private TextField<String> tfLink;
	private Button btnAmnuler;
	private Button btnSave;
	private DocumentModel model;
	private boolean isEdit = true;
	private ComboBox<CategoryModel> cbCategory;

	private ListStore<CategoryModel> lstCategories = new ListStore<CategoryModel>();

	public DocFormPanel(SimpleEventBus bus) {
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
		this.bus.addHandler(ModifyDocEvent.getType(), new ModifyDocHandler() {

			@Override
			public void onLoadAction(ModifyDocEvent event) {
				AppUtil.putInAdminEditMode();
				initData();
				if (event.getModel() != null) {
					isEdit = true;
					model = event.getModel();
					tfDocName.setValue(model.getName());
					tfComment.setValue(model.getComment());
					tfLink.setValue(model.getLink());
					btnSave.setText(DocFormPanel.messages.commonModifierButton());
				} else {
					model = null;
					isEdit = false;
					panel.reset();
					panel.clear();
					btnSave.setText(DocFormPanel.messages.commonValiderButton());
				}
			}
		});
	}

	private void initData() {
		ClientCategoryServiceAsync.Util.getInstance().getCategories(new AsyncCallback<List<CategoryModel>>() {

			@Override
			public void onFailure(Throwable arg0) {
			}

			@Override
			public void onSuccess(List<CategoryModel> arg0) {
				lstCategories.removeAll();
				lstCategories.add(arg0);
			}

		});
	}

	private void initUI() {
		this.panel = new FormPanel();
		this.panel.setAction(GWT.getHostPageBaseURL() + ".uploadDocumentServlet");
		this.panel.setEncoding(Encoding.MULTIPART);
		this.panel.setMethod(Method.POST);
		this.panel.setHeading(messages.documentheaderform());
		this.panel.setFrame(true);
		this.panel.setButtonAlign(HorizontalAlignment.RIGHT);
		this.panel.setWidth(this.WIDTH);

		this.tfDocName = new TextField<String>();
		this.tfDocName.setFieldLabel(messages.docname());
		this.tfDocName.setMaxLength(255);
		this.tfDocName.setName("name");
		this.tfDocName.setAllowBlank(false);
		this.panel.add(this.tfDocName, this.formData);

		this.cbCategory = new ComboBox<CategoryModel>();
		this.cbCategory.setAllowBlank(false);
		this.cbCategory.setEditable(false);
		this.cbCategory.setTriggerAction(TriggerAction.ALL);
		this.cbCategory.setFieldLabel(messages.categoryformheader());
		this.cbCategory.setDisplayField(CategoryModel.CAT_NAME);
		this.cbCategory.setStore(this.lstCategories);
		this.panel.add(this.cbCategory, this.formData);

		this.tfLink = new TextField<String>();
		this.tfLink.setFieldLabel(messages.doclink());
		this.tfLink.setMaxLength(255);
		this.tfLink.setName("link");
		this.tfLink.setAllowBlank(false);
		this.panel.add(this.tfLink, this.formData);

		this.tfComment = new TextField<String>();
		this.tfComment.setFieldLabel(messages.doccomment());
		this.tfComment.setMaxLength(255);
		this.tfComment.setName("lname");
		this.tfComment.setAllowBlank(true);
		this.panel.add(this.tfComment, this.formData);

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
		Label lblBack = new Label(messages.documentback());

		lblBack.setStyleName("x-link-item");
		backLink.setStyleAttribute("margin-bottom", "20px	");
		backLink.add(lblBack);

		lblBack.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent arg0) {
				if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_EXTERN_CONTROLLER_LIST);
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
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOC_LIST);
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
			this.model = new DocumentModel();
		}
		this.model.setName(this.tfDocName.getValue());
		this.model.setLink(this.tfLink.getValue());
		this.model.setCategory(this.cbCategory.getValue());
		this.model.setComment(this.tfComment.getValue());

		if (this.isEdit == false) {
			this.clientDocService.insert(this.model, new AsyncCallback<DocumentModel>() {

				@Override
				public void onSuccess(DocumentModel arg0) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOC_LIST);
					contentEvent.setEvent(new LoadDocEvent());
					bus.fireEvent(contentEvent);
					AppUtil.removeAdminInEditMode();
				}

				@Override
				public void onFailure(Throwable caught) {
					Info.display(DocFormPanel.messages.commonerror(), DocFormPanel.messages.commonServererror());
				}
			});
		} else {
			this.clientDocService.update(this.model, new AsyncCallback<DocumentModel>() {

				@Override
				public void onSuccess(DocumentModel arg0) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOC_LIST);
					contentEvent.setEvent(new LoadDocEvent());
					bus.fireEvent(contentEvent);
					AppUtil.removeAdminInEditMode();
				}

				@Override
				public void onFailure(Throwable arg0) {
					Info.display(DocFormPanel.messages.commonerror(), DocFormPanel.messages.commonServererror());
				}
			});
		}
	}
}
