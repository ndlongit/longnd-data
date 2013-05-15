package com.structis.vip.client.panel.document;

import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.document.LoadDocEvent;
import com.structis.vip.client.event.document.ModifyDocEvent;
import com.structis.vip.client.event.document.ModifyDocHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientCategoryServiceAsync;
import com.structis.vip.client.service.ClientDocumentServiceAsync;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.CategoryModel;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.ControlTypeModel;
import com.structis.vip.shared.model.DocumentModel;

public class DocFormPanel extends LayoutContainer {
	private final Messages messages = GWT.create(Messages.class);
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
		bus.addHandler(ModifyDocEvent.getType(), new ModifyDocHandler() {
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
		panel = new FormPanel();
		panel.setAction(GWT.getHostPageBaseURL() + ".uploadDocumentServlet");
		panel.setEncoding(Encoding.MULTIPART);
		panel.setMethod(Method.POST);
		panel.setHeading(messages.documentheaderform());
		panel.setFrame(true);
		panel.setButtonAlign(HorizontalAlignment.RIGHT);
		panel.setWidth(WIDTH);
		
		tfDocName = new TextField<String>();
		tfDocName.setFieldLabel(messages.docname());
		tfDocName.setMaxLength(255);
		tfDocName.setName("name");
		tfDocName.setAllowBlank(false);
		panel.add(tfDocName, formData);
		
		cbCategory = new ComboBox<CategoryModel>();
		cbCategory.setAllowBlank(false);
		cbCategory.setEditable(false);
		cbCategory.setTriggerAction(TriggerAction.ALL);
		cbCategory.setFieldLabel(messages.categoryformheader());
		cbCategory.setDisplayField(CategoryModel.CAT_NAME);
		cbCategory.setStore(lstCategories);
		panel.add(cbCategory, formData);		
				
		tfLink = new TextField<String>();
		tfLink.setFieldLabel(messages.doclink());		
		tfLink.setMaxLength(255);
		tfLink.setName("link");
		tfLink.setAllowBlank(false);
		panel.add(tfLink, formData);		
		
		tfComment = new TextField<String>();
		tfComment.setFieldLabel(messages.doccomment());
		tfComment.setMaxLength(255);
		tfComment.setName("lname");
		tfComment.setAllowBlank(true);
		panel.add(tfComment, formData);

		
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
		
		add(backLink);			
	}
	
	private void initEvent() {
		btnAmnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ContentEvent event = new ContentEvent();
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOC_LIST);
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
		if (model == null)  {
			model = new DocumentModel();
		}
		model.setName(tfDocName.getValue());
		model.setLink(tfLink.getValue());
		model.setCategory(cbCategory.getValue());		
		model.setComment(tfComment.getValue());		
		
		if (isEdit == false) {
			clientDocService.insert(model, new AsyncCallback<DocumentModel>() {
				
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
					Info.display(messages.commonerror(), messages.commonServererror());
				}
			});
		} else {
			clientDocService.update(model, new AsyncCallback<DocumentModel>() {
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
					Info.display(messages.commonerror(), messages.commonServererror());
				}
			});
		}
	}
}
