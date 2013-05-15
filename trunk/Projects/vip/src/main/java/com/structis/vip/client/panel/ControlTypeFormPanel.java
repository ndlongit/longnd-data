package com.structis.vip.client.panel;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
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
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.ModifyControlTypeEvent;
import com.structis.vip.client.event.ModifyControlTypeHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientControlTypeServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.ControlTypeModel;

public class ControlTypeFormPanel extends LayoutContainer {
	private final Messages messages = GWT.create(Messages.class);
	private final FormData formData = new FormData("98%");
	private final int WIDTH = 500;
	
	private ClientControlTypeServiceAsync clientControlTypeService = ClientControlTypeServiceAsync.Util.getInstance();
	
	private SimpleEventBus bus;
	private FormPanel panel;
	private TextField<String> tfName;
	private TextArea taDescription;
	private Button btnAmnuler;
	private Button btnSave;
	private ControlTypeModel model;
	private boolean isEdit = true;
	
	public ControlTypeFormPanel(SimpleEventBus bus) {
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
		bus.addHandler(ModifyControlTypeEvent.getType(), new ModifyControlTypeHandler() {
			@Override
			public void onLoadAction(ModifyControlTypeEvent event) {
				AppUtil.putInAdminEditMode();
				if (event.getModel() != null) {
					isEdit = true;
					model = event.getModel();
					tfName.setValue(model.getLabel());
					taDescription.setValue(model.getDescription());
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
		panel.setHeading(messages.controltypeformheader());
		panel.setFrame(true);
		panel.setButtonAlign(HorizontalAlignment.RIGHT);
		panel.setWidth(WIDTH);
		
		tfName = new TextField<String>();
		tfName.setFieldLabel(messages.controltypenom());
		tfName.setMaxLength(80);
		tfName.setName("name");
		tfName.setAllowBlank(false);
		panel.add(tfName, formData);
		
		taDescription = new TextArea();
		taDescription.setFieldLabel(messages.controltypedescription());
		taDescription.setName("description");
		taDescription.setMaxLength(255);
		panel.add(taDescription, formData);

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
		Label lblBack = new Label(messages.controltypeback());
		
		lblBack.setStyleName("x-link-item");
		backLink.setStyleAttribute("margin-bottom", "20px	");
		backLink.add(lblBack);
		
		lblBack.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CONTROL_TYPE_LIST);
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
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CONTROL_TYPE_LIST);
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
			model = new ControlTypeModel();
		}
		model.setLabel(tfName.getValue());
		model.setDescription(taDescription.getValue());
		model.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
		
		if (isEdit == false) {
			clientControlTypeService.insert(model, new AsyncCallback<ControlTypeModel>() {
				
				@Override
				public void onSuccess(ControlTypeModel arg0) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CONTROL_TYPE_LIST);
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
			clientControlTypeService.update(model, new AsyncCallback<ControlTypeModel>() {
				@Override
				public void onSuccess(ControlTypeModel arg0) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CONTROL_TYPE_LIST);
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
