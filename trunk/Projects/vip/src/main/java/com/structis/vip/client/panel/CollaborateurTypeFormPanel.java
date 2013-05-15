package com.structis.vip.client.panel;

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
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
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
import com.structis.vip.client.event.ModifyCollaborateurTypeEvent;
import com.structis.vip.client.event.ModifyCollaborateurTypeHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientCollaborateurTypeServiceAsync;
import com.structis.vip.client.service.ClientDelegantTypeGroupServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.CollaborateurTypeModel;
import com.structis.vip.shared.model.DelegantTypeGroupModel;
import com.structis.vip.shared.model.LanguageModel;

public class CollaborateurTypeFormPanel extends LayoutContainer {
	private final Messages messages = GWT.create(Messages.class);
	private final FormData formData = new FormData("98%");
	private final int WIDTH = 500;
	
	private ClientCollaborateurTypeServiceAsync clientCollaborateurTypeService = ClientCollaborateurTypeServiceAsync.Util.getInstance();
	private ClientDelegantTypeGroupServiceAsync clientDelegantTypeGroupService = ClientDelegantTypeGroupServiceAsync.Util.getInstance();
	
	private SimpleEventBus bus;
	private FormPanel panel;
	private ComboBox<DelegantTypeGroupModel> cbGroup;
	
	private TextField<String> tfName;
	private Button btnAmnuler;
	private Button btnSave;
	private CollaborateurTypeModel model;
	private boolean isEdit = true;
	private ListStore<DelegantTypeGroupModel> groupStore = new ListStore<DelegantTypeGroupModel>();
	
	public CollaborateurTypeFormPanel(SimpleEventBus bus) {
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
		bus.addHandler(ModifyCollaborateurTypeEvent.getType(), new ModifyCollaborateurTypeHandler() {

			@Override
			public void onLoadAction(ModifyCollaborateurTypeEvent event) {
				initData();
				AppUtil.putInAdminEditMode();
				if (event.getModel() != null) {
					isEdit = true;
					model = event.getModel();
					tfName.setValue(model.getName());					
					cbGroup.setValue(model.getGroup());
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
		System.out.println("vaooooooooooooo");
		clientDelegantTypeGroupService.getDelegantTypeGroups(new AsyncCallback<List<DelegantTypeGroupModel>>() {
			@Override
			public void onSuccess(List<DelegantTypeGroupModel> arg0) {
				groupStore.removeAll();
				groupStore.add(arg0);
			}
			
			@Override
			public void onFailure(Throwable arg0) {
			}
		});
	}
	
	private void initUI() {
		panel = new FormPanel();
		panel.setHeading(messages.deleganttypeformheader());
		panel.setFrame(true);
		panel.setButtonAlign(HorizontalAlignment.RIGHT);
		panel.setWidth(WIDTH);
		
		cbGroup = new ComboBox<DelegantTypeGroupModel>();
		cbGroup.setAllowBlank(false);
		cbGroup.setEditable(false);
		cbGroup.setFieldLabel(messages.deleganttypegroupnom());
		cbGroup.setName("group");
		cbGroup.setDisplayField(LanguageModel.LAG_NAME);
		cbGroup.setTriggerAction(TriggerAction.ALL);
		cbGroup.setStore(groupStore);
		panel.add(cbGroup, formData);
				
		
		tfName = new TextField<String>();
		tfName.setFieldLabel(messages.collaborateurtypenom());
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
		Label lblBack = new Label(messages.collaborateurtypeback());
		
		lblBack.setStyleName("x-link-item");
		backLink.setStyleAttribute("margin-bottom", "20px	");
		backLink.add(lblBack);
		
		lblBack.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_LIST);
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
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_LIST);
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
			model = new CollaborateurTypeModel();
		}
		model.setName(tfName.getValue());		
		model.setGroup(cbGroup.getValue());
		model.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
		
		if (isEdit == false) {
			clientCollaborateurTypeService.insert(model, new AsyncCallback<CollaborateurTypeModel>() {
				
				@Override
				public void onSuccess(CollaborateurTypeModel arg0) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_LIST);
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
			clientCollaborateurTypeService.update(model, new AsyncCallback<CollaborateurTypeModel>() {
				@Override
				public void onSuccess(CollaborateurTypeModel arg0) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_LIST);
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
