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
import com.structis.vip.client.event.ModifyLanguageEvent;
import com.structis.vip.client.event.ModifyLanguageHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientLanguageServiceAsync;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.LanguageModel;

public class LanguageFormPanel extends LayoutContainer {
	private final Messages messages = GWT.create(Messages.class);
	private final FormData formData = new FormData("98%");
	private final int WIDTH = 500;
	
	private ClientLanguageServiceAsync clientLanguageService = ClientLanguageServiceAsync.Util.getInstance();
	
	private SimpleEventBus bus;
	private FormPanel panel;
	private TextField<String> tfName;
	private TextField<String> tfCode;
	private Button btnAmnuler;
	private Button btnSave;
	private LanguageModel model;
	private boolean isEdit = true;
	
	public LanguageFormPanel(SimpleEventBus bus) {
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
		bus.addHandler(ModifyLanguageEvent.getType(), new ModifyLanguageHandler() {
			@Override
			public void onLoadAction(ModifyLanguageEvent event) {
				AppUtil.putInAdminEditMode();
				if (event.getModel() != null) {
					isEdit = true;
					model = event.getModel();
					tfName.setValue(model.getName());
					tfCode.setValue(model.getCode());
					
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
	}
	
	private void initUI() {
		panel = new FormPanel();
		panel.setHeading(messages.languageformheader());
		panel.setFrame(true);
		panel.setButtonAlign(HorizontalAlignment.RIGHT);
		panel.setWidth(WIDTH);
		
		tfName = new TextField<String>();
		tfName.setFieldLabel(messages.languagenom());
		tfName.setMaxLength(50);
		tfName.setName("name");
		tfName.setAllowBlank(false);
		panel.add(tfName, formData);
		
		tfCode = new TextField<String>();
		tfCode.setFieldLabel(messages.languagecode());
		tfCode.setMaxLength(3);
		tfCode.setName("code");
		tfCode.setAllowBlank(false);
		panel.add(tfCode, formData);
		
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
		Label lblBack = new Label(messages.languageback());
		
		lblBack.setStyleName("x-link-item");
		backLink.setStyleAttribute("margin-bottom", "20px	");
		backLink.add(lblBack);
		
		lblBack.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_LANGUAGE_LIST);
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
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_LANGUAGE_LIST);
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
			model = new LanguageModel();
		}
		model.setName(tfName.getValue());
		model.setCode(tfCode.getValue());
		
		if (isEdit == false) {
			clientLanguageService.insert(model, new AsyncCallback<LanguageModel>() {
				
				@Override
				public void onSuccess(LanguageModel arg0) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_LANGUAGE_LIST);
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
			clientLanguageService.update(model, new AsyncCallback<LanguageModel>() {
				@Override
				public void onSuccess(LanguageModel arg0) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_LANGUAGE_LIST);
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
