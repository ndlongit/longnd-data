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
import com.structis.vip.client.constant.ConstantClient;
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

public class ChantierTypeFormPanel extends LayoutContainer {
	private final Messages messages = GWT.create(Messages.class);
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
		bus.addHandler(ModifyChantierTypeEvent.getType(), new ModifyChantierTypeHandler() {
			@Override
			public void onLoadAction(ModifyChantierTypeEvent event) {
				AppUtil.putInAdminEditMode();
				if (event.getModel() != null) {
					isEdit = true;
					model = event.getModel();
					tfName.setValue(model.getLabel());
					if (model.getIsSubdelegable() != null) {
						rgSub.setValue((model.getIsSubdelegable() == 1) ? roSubOui : roSubNon);
					}
				} else {
					model = null;
					isEdit = false;
					panel.reset();
					panel.clear();
				}
				//add BYTP
//				if (ConstantClient.ENTITE_ID_IS_BYEFE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
				if (CommonUtils.belongsBYEFEGroup(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
					tfEndDate.setVisible(true);
					tfEndDate.setAllowBlank(false);
					if (isEdit) {
						tfEndDate.setValue(model.getEndDate());
					}
				} else {
					tfEndDate.setVisible(false);
					tfEndDate.setAllowBlank(true);
					tfEndDate.setValue(null);
					
				}
			}
		});
	}
	
	private void initData() {
	}
	
	private void initUI() {
		panel = new FormPanel();
		panel.setLabelWidth(200);
		panel.setHeading(messages.chantiertypeformheader());
		panel.setFrame(true);
		panel.setButtonAlign(HorizontalAlignment.RIGHT);
		panel.setWidth(WIDTH);
		
		tfName = new TextField<String>();
		tfName.setFieldLabel(messages.chantiertypelabel());
		tfName.setMaxLength(80);
		tfName.setName("label");
		tfName.setAllowBlank(false);
		panel.add(tfName, formData);
		
		tfEndDate = new DateField();
		tfEndDate.setPropertyEditor(new DateTimePropertyEditor(ConstantClient.DATE_FORMAT_DDMM));
		tfEndDate.setFieldLabel(messages.chantiertypeendDate());
		tfEndDate.setName("endDate");
		tfEndDate.setMaxLength(5);
		panel.add(tfEndDate, formData);
		
		roSubOui = new Radio();
		roSubOui.setBoxLabel(messages.commonOui());
		roSubOui.setValue(true);

		roSubNon = new Radio();
		roSubNon.setBoxLabel(messages.commonNon());

		rgSub = new RadioGroup();
		rgSub.setFieldLabel(messages.chantiertypechantierSubdelegable());
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
		Label lblBack = new Label(messages.chantiertypeback());
		
		lblBack.setStyleName("x-link-item");
		backLink.setStyleAttribute("margin-bottom", "20px	");
		backLink.add(lblBack);
		
		lblBack.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CHANTIER_TYPE_LIST);
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
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CHANTIER_TYPE_LIST);
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
			model = new ChantierTypeModel();
		}
		model.setLabel(tfName.getValue());
		
		if (tfEndDate.isVisible()) {
			model.setEndDate(tfEndDate.getValue());
		}
		
		model.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
		
		model.setIsSubdelegable(0);
		if (roSubOui.getValue() == true) {
			model.setIsSubdelegable(1);
		}
		
		if (isEdit == false) {
			clientChantierTypeService.insert(model, new AsyncCallback<ChantierTypeModel>() {
				
				@Override
				public void onSuccess(ChantierTypeModel arg0) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CHANTIER_TYPE_LIST);
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
			clientChantierTypeService.update(model, new AsyncCallback<ChantierTypeModel>() {
				@Override
				public void onSuccess(ChantierTypeModel arg0) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_CHANTIER_TYPE_LIST);
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
