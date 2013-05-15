package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.DateTimePropertyEditor;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.DelegationFilterEvent;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.DelegationPagingEvent;
import com.structis.vip.client.event.DelegationPagingHandler;
import com.structis.vip.client.event.DelegationTreeEvent;
import com.structis.vip.client.event.DelegationTreeHandler;
import com.structis.vip.client.event.DeleteDelegationEvent;
import com.structis.vip.client.event.DeleteDelegationHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientCollaborateurServiceAsync;
import com.structis.vip.client.service.ClientDelegationNatureServiceAsync;
import com.structis.vip.client.service.ClientDelegationStatusServiceAsync;
import com.structis.vip.client.service.ClientDelegationTypeServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.widget.XComboBox;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.DelegationNatureModel;
import com.structis.vip.shared.model.DelegationStatusModel;
import com.structis.vip.shared.model.DelegationTypeModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.PerimetreTypeModel;

public class DelegationCenterFormPanel extends VerticalPanel {
	
	private static final int COMBOBOX_WIDTH = 275;
	private static final int LABEL_WIDTH = 120;
	private final Messages messages = GWT.create(Messages.class);
	
	SimpleEventBus bus;
	DelegationFilterEvent delegationFilterEvent;

	Label periodeLabel;
	Label auLabel;

	XComboBox<DelegationNatureModel> natureCombobox;
	XComboBox<DelegationStatusModel> statusCombobox;
	XComboBox<DelegationTypeModel> typeCombobox;
	XComboBox<CollaborateurModel> delegantCombobox;
	XComboBox<CollaborateurModel> delegataireCombobox;
	
	int pagingSize = ConstantClient.DEFAULT_PAGE_SIZE_50;

	CheckBox afficherCheck;
	CheckBox sepCheckBox;
	CheckBox delegationCheckBox;

	DateField fromDateField;
	DateField toDateField;
	
	Button buttonFiltrer;
	
	private ClientDelegationNatureServiceAsync natureService = ClientDelegationNatureServiceAsync.Util.getInstance();
	private ListStore<DelegationNatureModel> natures = new ListStore<DelegationNatureModel>();
	
	private ClientDelegationStatusServiceAsync statusService = ClientDelegationStatusServiceAsync.Util.getInstance();
	private ListStore<DelegationStatusModel> statuses = new ListStore<DelegationStatusModel>();
	
	private ClientDelegationTypeServiceAsync typeService = ClientDelegationTypeServiceAsync.Util.getInstance();
	private ListStore<DelegationTypeModel> types = new ListStore<DelegationTypeModel>();
	
	private ClientCollaborateurServiceAsync collaborateurService = ClientCollaborateurServiceAsync.Util.getInstance();
	private ListStore<CollaborateurModel> delegants = new ListStore<CollaborateurModel>();
	private ListStore<CollaborateurModel> delegataires = new ListStore<CollaborateurModel>();
	
	DelegationNatureModel natureAll;
	DelegationStatusModel statusAll;
	DelegationTypeModel typeAll;
	CollaborateurModel delegantAll;
	CollaborateurModel delegataireAll;
	
	List<DelegationNatureModel> natureAllSelection = new ArrayList<DelegationNatureModel>();
	List<DelegationStatusModel> statusAllSelection = new ArrayList<DelegationStatusModel>();
	List<DelegationTypeModel> typeAllSelection = new ArrayList<DelegationTypeModel>();
	List<CollaborateurModel> delegantAllSelection = new ArrayList<CollaborateurModel>();
	List<CollaborateurModel> delegataireAllSelection = new ArrayList<CollaborateurModel>();
	
	public DelegationCenterFormPanel(SimpleEventBus bus, DelegationFilterEvent delegationFilterEvent) {
		this.bus = bus;
		this.delegationFilterEvent = delegationFilterEvent;
		
		initData();
		
		initUI();
		
		addHandler();
	}
	
	private void initData() {
		// initialize nature list
		natures.removeAll();
		natureAll = new DelegationNatureModel();
		natureAll.setName(messages.commonTous());
		natureAll.setId(0);
		natures.add(natureAll);
		natureAllSelection.add(natureAll);
		
		// initialize status list
		statuses.removeAll();
		statusAll = new DelegationStatusModel();
		statusAll.setName(messages.commonTous());
		statusAll.setId(0);
		statuses.add(statusAll);
		statusAllSelection.add(statusAll);
		
		// initialize type list
		types.removeAll();
		typeAll = new DelegationTypeModel();
		typeAll.setName(messages.commonTous());
		typeAll.setId(0);
		types.add(typeAll);
		typeAllSelection.add(typeAll);
		
		// initialize delegant list
		delegants.removeAll();
		delegantAll = new CollaborateurModel();
		delegantAll.setFullname(messages.commonTous());
		delegantAll.setFullnameNoSeparater(messages.commonTous());
		delegantAll.setId(0);
		delegants.add(delegantAll);
		delegantAllSelection.add(delegantAll);
		
		// initialize delegataire list
		delegataires.removeAll();		
		delegataireAll = new CollaborateurModel();
		delegataireAll.setFullname(messages.commonTous());
		delegataireAll.setFullnameNoSeparater(messages.commonTous());
		delegataireAll.setId(0);
		delegataires.add(delegataireAll);
		delegataireAllSelection.add(delegataireAll);
	}

	private void initUI() {
		FormData formData = new FormData("100%");
		FormData formDataR = new FormData("100%");
		
		LayoutContainer main = new LayoutContainer();		
		main.setLayout(new ColumnLayout()); 
		main.setStyleAttribute("padding", "5px");
		
		LayoutContainer left = new LayoutContainer();
	    FormLayout layout = new FormLayout();	    
		layout.setLabelAlign(LabelAlign.RIGHT);
		layout.setLabelWidth(LABEL_WIDTH);
	    left.setLayout(layout);
	    left.setStyleAttribute("paddingRight", "5px");
	    
	    // nature combobox
		natureCombobox = new XComboBox<DelegationNatureModel>();
		natureCombobox.setWidth(COMBOBOX_WIDTH);
		natureCombobox.setEditable(false);
		natureCombobox.setTriggerAction(TriggerAction.ALL);
		natureCombobox.setStore(natures);
		natureCombobox.setFieldLabel(messages.delegationnature());
		natureCombobox.setLabelSeparator("");
		natureCombobox.setSelection(natureAllSelection);
		natureCombobox.setDisplayField(DelegationNatureModel.DELE_NATURE_NAME);
		natureCombobox.setLabelStyle("white-space: nowrap");
		left.add(natureCombobox, formData);
		// nature label
//		Label labelNature = new Label(messages.delegationnature());
//		labelNature.setStyleAttribute("width", "122px");
//		labelNature.setStyleAttribute("text-align", "right");
		
//		LayoutContainer layoutNature = new LayoutContainer();
//		HBoxLayout hLayoutNature = new HBoxLayout();
//		layoutNature.setLayout(hLayoutNature);
//		layoutNature.add(labelNature, new HBoxLayoutData(new Margins(2,2,2,0)));
//		layoutNature.add(natureCombobox, new HBoxLayoutData(new Margins(2,2,2,0)));
		
		// status combobox
		statusCombobox = new XComboBox<DelegationStatusModel>();
		statusCombobox.setWidth(COMBOBOX_WIDTH);
		statusCombobox.setTriggerAction(TriggerAction.ALL);		
		statusCombobox.setStore(statuses);
		statusCombobox.setEditable(false);
		statusCombobox.setDisplayField(DelegationStatusModel.DELEGATION_STATUS_NAME);
		statusCombobox.setSelection(statusAllSelection);
		statusCombobox.setFieldLabel(messages.delegationstatus());
		statusCombobox.setLabelStyle("white-space: nowrap");
		statusCombobox.setLabelSeparator("");
		left.add(statusCombobox, formData);
		
		// status label
//		Label labelStatus = new Label(messages.delegationstatus());
//		labelStatus.setStyleAttribute("width", "122px");
//		labelStatus.setStyleAttribute("text-align", "right");
		
//		HBoxLayout hLayoutStatus = new HBoxLayout();
//		LayoutContainer layoutStatus = new LayoutContainer(hLayoutStatus);
//		layoutStatus.add(labelStatus, new HBoxLayoutData(new Margins(2,2,2,0)));
//		layoutStatus.add(statusCombobox, new HBoxLayoutData(new Margins(2,2,2,0)));
		
		// type combobox
		typeCombobox = new XComboBox<DelegationTypeModel>();
		typeCombobox.setWidth(COMBOBOX_WIDTH);
		typeCombobox.setEditable(false);
		typeCombobox.setTriggerAction(TriggerAction.ALL);
		typeCombobox.setDisplayField(DelegationTypeModel.DELEGATION_TYPE_NAME);
		typeCombobox.setStore(types);
		typeCombobox.setSelection(typeAllSelection);
		typeCombobox.setFieldLabel(messages.delegationtype());
		typeCombobox.setLabelSeparator("");
		typeCombobox.setLabelStyle("white-space: nowrap");
		left.add(typeCombobox, formData);
		
		afficherCheck = new CheckBox();
		afficherCheck.setValue(true);		
		afficherCheck.setBoxLabel(messages.delegationafficher());
		afficherCheck.setStyleAttribute("margin-left", "0px");
		afficherCheck.setStyleAttribute("paddingLeft", "0px");
		afficherCheck.setLabelSeparator("");
		left.add(afficherCheck, formData);
		// type label
//		Label labelType = new Label(messages.delegationtype());
//		labelType.setStyleAttribute("width", "122px");
//		labelType.setStyleAttribute("text-align", "right");
//		
//		HBoxLayout hLayoutType = new HBoxLayout();
//		LayoutContainer layoutType = new LayoutContainer(hLayoutType);
//		layoutType.add(labelType, new HBoxLayoutData(new Margins(2,2,2,0)));
//		layoutType.add(typeCombobox, new HBoxLayoutData(new Margins(2,2,2,0)));
		
//		left.add(layoutNature, formData);
//		left.add(layoutStatus, formData);
//		left.add(layoutType, formData);
		
		// right column
		LayoutContainer right = new LayoutContainer();
		right.setLayout(new FormLayout());
		((FormLayout)right.getLayout()).setLabelAlign(LabelAlign.RIGHT);
		((FormLayout)right.getLayout()).setLabelWidth(LABEL_WIDTH);
		
		// delegant
		delegantCombobox = new XComboBox<CollaborateurModel>();
		delegantCombobox.setWidth(COMBOBOX_WIDTH);
		delegantCombobox.setLabelSeparator("");
		delegantCombobox.setEditable(false);
		delegantCombobox.setTriggerAction(TriggerAction.ALL);
		delegantCombobox.setDisplayField(CollaborateurModel.COLLA_FULL_NAME_NO_SEPARATER);
		delegantCombobox.setStore(delegants);
		delegantCombobox.setFieldLabel(messages.delegationdelegant());
		delegantCombobox.setLabelStyle("white-space: nowrap");
		delegantCombobox.setSelection(delegantAllSelection);
//		delegantCombobox.setStyleAttribute("margin-left", "25px");
		
		// delegataire
		delegataireCombobox = new XComboBox<CollaborateurModel>();
		delegataireCombobox.setLabelSeparator("");
		delegataireCombobox.setEditable(false);
		delegataireCombobox.setWidth(COMBOBOX_WIDTH);
		delegataireCombobox.setTriggerAction(TriggerAction.ALL);
		delegataireCombobox.setDisplayField(CollaborateurModel.COLLA_FULL_NAME_NO_SEPARATER);
		delegataireCombobox.setStore(delegataires);
		delegataireCombobox.setSelection(delegataireAllSelection);
		delegataireCombobox.setFieldLabel(messages.delegationdelegataire());
		delegataireCombobox.setLabelStyle("white-space: nowrap");
//		delegataireCombobox.setStyleAttribute("margin-left", "25px");
		//delegataireCombobox.setListStyle("dropdown-list-style"); 		
		

		// @Lan(2012/03/01): Fixing bug #98 - Remove the default date for start date and end date
		
		// form date
		//periodeLabel = new Label(messages.delegationperiode());
		fromDateField = new DateField();
		fromDateField.setLabelSeparator("");
		fromDateField.setFieldLabel(messages.delegationperiode());
		fromDateField.setPropertyEditor(new DateTimePropertyEditor(ConstantClient.DATE_FORMAT));
		fromDateField.setLabelStyle("white-space: nowrap");		
		fromDateField.setWidth(210);
		
		// to date
		//auLabel = new Label(messages.delegationau());
		toDateField = new DateField();
		toDateField.setLabelSeparator("");
		toDateField.setFieldLabel(messages.delegationau());
		toDateField.setPropertyEditor(new DateTimePropertyEditor(ConstantClient.DATE_FORMAT));
		toDateField.setLabelStyle("white-space: nowrap");
		toDateField.setWidth(210);
		
		LayoutContainer layoutDateField = new LayoutContainer();
		layoutDateField.setLayout(new ColumnLayout());
		LayoutContainer dateLeft = new LayoutContainer();   
		dateLeft.setStyleAttribute("paddingRight", "2px");
		FormLayout layoutLeft = new FormLayout();   
		layoutLeft.setLabelAlign(LabelAlign.RIGHT);   
		layoutLeft.setLabelWidth(LABEL_WIDTH);
		dateLeft.setLayout(layoutLeft);  
		dateLeft.add(fromDateField, formData);
		LayoutContainer dateRight = new LayoutContainer();   
		dateRight.setStyleAttribute("paddingRight", "0px");		
		FormLayout layoutRight = new FormLayout();   
		layoutRight.setLabelAlign(LabelAlign.RIGHT);   
		layoutRight.setLabelWidth(LABEL_WIDTH);
		
		dateRight.setLayout(layoutRight);  
		dateRight.add(toDateField, formData);
		layoutDateField.add(dateLeft, new ColumnData(.5));   
		layoutDateField.add(dateRight, new ColumnData(.5));   
			
		dateRight.setLayout(layoutRight);  
		dateRight.add(toDateField, formData);
		layoutDateField.add(dateLeft, new ColumnData(.5));   
		layoutDateField.add(dateRight, new ColumnData(.5));
		
		sepCheckBox = new CheckBox();
		sepCheckBox.setBoxLabel(messages.delegationsep());
		sepCheckBox.setLabelSeparator("");
		sepCheckBox.setEnabled(false);
		sepCheckBox.setStyleAttribute("paddingLeft", "0px");
		sepCheckBox.setStyleAttribute("margin-left", "0px");
		
		delegationCheckBox = new CheckBox();
		delegationCheckBox.setBoxLabel(messages.delegationdelegationconjointe());
		delegationCheckBox.setLabelSeparator("");
		delegationCheckBox.setEnabled(false);
		delegationCheckBox.setStyleAttribute("paddingLeft", "0px");
		delegationCheckBox.setStyleAttribute("margin-left", "0px");
		
		right.add(delegantCombobox, formDataR);
		right.add(delegataireCombobox, formDataR);
		right.add(layoutDateField, formDataR);
		right.add(sepCheckBox, formDataR);		
		
		
		buttonFiltrer = new Button(messages.delegationfiltrer());
		buttonFiltrer.setWidth(35);
		buttonFiltrer.setAutoWidth(false);
		
		
		LayoutContainer layoutButton = new LayoutContainer();
		layoutButton.setLayout(new ColumnLayout());
		LayoutContainer btnLeft = new LayoutContainer();   
		btnLeft.setStyleAttribute("paddingRight", "2px");
		FormLayout loBtnLeft = new FormLayout();   
		loBtnLeft.setLabelAlign(LabelAlign.RIGHT);   
		loBtnLeft.setLabelWidth(LABEL_WIDTH);
		btnLeft.setLayout(loBtnLeft);
		btnLeft.add(delegationCheckBox, formData);
		LayoutContainer btnRight = new LayoutContainer();		
		btnRight.setStyleAttribute("paddingRight", "0px");		
		FormLayout loBtnRight = new FormLayout();   
		loBtnRight.setLabelAlign(LabelAlign.RIGHT); 
		loBtnRight.setLabelWidth(LABEL_WIDTH+5);
		btnRight.setLayout(loBtnRight);
		btnRight.add(buttonFiltrer, formData);
		
		LayoutContainer btnCenter = new LayoutContainer();
		btnCenter.setStyleAttribute("paddingRight", "2px");
		btnCenter.add(new Label(""), formData);
		
		layoutButton.add(btnLeft, new ColumnData(.58)); 
		layoutButton.add(btnCenter, new ColumnData(.16));
		layoutButton.add(btnRight, new ColumnData(.26));   
		
		right.add(layoutButton, formDataR);
		
		// add to main panel		
		main.setStyleAttribute("padding-left", "0px");
		main.setStyleAttribute("padding-bottom", "0px");
		main.add(left, new ColumnData(.5));
		main.add(right, new ColumnData(.5));		
		
		// init main form panel
		FormPanel panel = new FormPanel();				
		panel.setPadding(0);
//		panel.setStyleAttribute("margin-left", "0px");
//		panel.setStyleAttribute("paddingLeft", "0px");
		panel.setStyleAttribute("background", "white");
		panel.setHeaderVisible(false);
		panel.setSize(860, -1);
		panel.setBodyBorder(false);
		panel.setLabelAlign(LabelAlign.RIGHT);
		panel.setLabelWidth(LABEL_WIDTH);
		panel.setButtonAlign(HorizontalAlignment.CENTER);		
		FormData formData2 = new FormData("100%");
		formData2.setMargins(new Margins(10,0,0,0));
		panel.add(main, formData2);
//		FormData formData3 = new FormData("100%");
//		formData3.setMargins(new Margins(0,0,0,0));
//		panel.add(createChild(), formData3);
		
		// add to root panel
		setStyleAttribute("padding-left", "5px");
		setSpacing(0);	
		setHeight(150);
		add(panel);
	}
	
//	private LayoutContainer createChild() {
//		FormData formData = new FormData("100%");
//		
//		LayoutContainer child = new LayoutContainer();		
//		child.setLayout(new ColumnLayout()); 
//		
//		// left
//		LayoutContainer childLeft = new LayoutContainer();
//		childLeft.setLayout(new FormLayout());
//		((FormLayout)childLeft.getLayout()).setLabelAlign(LabelAlign.LEFT);
//		
////		afficherCheck = new CheckBox();
////		afficherCheck.setValue(true);		
////		afficherCheck.setBoxLabel(messages.delegationafficher());
////		afficherCheck.setStyleAttribute("margin-left", "19px");
////		afficherCheck.setLabelSeparator("");
////		
////		childLeft.add(afficherCheck, formData);
//		
//		// right
//		LayoutContainer childRight = new LayoutContainer();
//		childRight.setLayout(new FormLayout());
//		((FormLayout)childRight.getLayout()).setLabelAlign(LabelAlign.RIGHT);
//		
////		sepCheckBox = new CheckBox();
////		sepCheckBox.setBoxLabel(messages.delegationsep());
////		sepCheckBox.setLabelSeparator("");
////		sepCheckBox.setEnabled(false);
////		sepCheckBox.setStyleAttribute("padding", "0px");
////		
////		delegationCheckBox = new CheckBox();
////		delegationCheckBox.setBoxLabel(messages.delegationdelegationconjointe());
////		delegationCheckBox.setLabelSeparator("");
////		delegationCheckBox.setEnabled(false);
////		delegationCheckBox.setStyleAttribute("padding", "0px");
//		
////		childRight.add(sepCheckBox, formData);
////		childRight.add(delegationCheckBox, formData);
//		
//		// button
//		buttonFiltrer = new Button(messages.delegationfiltrer());
//		buttonFiltrer.setSize(30, -1);
//		buttonFiltrer.setStyleAttribute("marginTop", "20px");
//		
//		child.add(childLeft, new ColumnData(.437));
//		child.add(childRight, new ColumnData(.294));
//		child.add(buttonFiltrer, new ColumnData(.1));
//		
//		return child;
//	}
	
	private void addHandler() {
		// add event for Filter button
		buttonFiltrer.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (delegationFilterEvent == null) {
					delegationFilterEvent = new DelegationFilterEvent();
				} 
				delegationFilterEvent.setNatureModel(natureCombobox.getSelection());
				delegationFilterEvent.setStatusModel(statusCombobox.getSelection());
				delegationFilterEvent.setTypeModel(typeCombobox.getSelection());
				delegationFilterEvent.setDelegant(delegantCombobox.getSelection());
				delegationFilterEvent.setDelegataire(delegataireCombobox.getSelection());
				delegationFilterEvent.setStartDate(fromDateField.getValue());
				delegationFilterEvent.setEndDate(toDateField.getValue());
				delegationFilterEvent.setSep(sepCheckBox.getValue());
				delegationFilterEvent.setConjointe(delegationCheckBox.getValue());
				delegationFilterEvent.setShowLevel(afficherCheck.getValue());
				delegationFilterEvent.setPageSize(pagingSize);
				
				// fire event delegation filter
				bus.fireEvent(delegationFilterEvent);
			}
		});
		
		// catch delegation tree event on left panel
		bus.addHandler(DelegationTreeEvent.getType(), new DelegationTreeHandler() {
			@Override
			public void onLoadAction(DelegationTreeEvent event) {
				PerimetreTreeModel treeModel = event.getTreeModel();
				if(treeModel != null){
					if (delegationFilterEvent == null) {
						delegationFilterEvent = new DelegationFilterEvent();
					}
					
					PerimetreModel perimetreModel = new PerimetreModel();
					perimetreModel.setPerId(treeModel.getPerId());
					perimetreModel.setName(treeModel.getName());
					
					PerimetreTypeModel type = new PerimetreTypeModel();
					type.setPtyId(treeModel.getType());
					type.setName(treeModel.get("typeName").toString());
					
					perimetreModel.setType(type);
					
					
					delegationFilterEvent.setPerimetreTreeModel(treeModel);
					
					if (Arrays.asList(ConstantClient.PERIMETRE_TYPE_IS_CHANTIER).contains(type.getPtyId())) {
						sepCheckBox.setEnabled(true);
						delegationCheckBox.setEnabled(true);
					} else {
						sepCheckBox.setEnabled(false);
						delegationCheckBox.setEnabled(false);
					}
					
					buttonFiltrer.fireEvent(Events.Select);
					
					String perId = event.getTreeModel().getPerId();
					String entiteId = event.getTreeModel().getEntiteId();
					
					// initialize delegant list
					delegants.removeAll();
					delegantAll = new CollaborateurModel();
					delegantAll.setFullname(messages.commonTous());
					delegantAll.setFullnameNoSeparater(messages.commonTous());
					delegantAll.setId(0);
					delegants.add(delegantAll);
					delegantAllSelection.add(delegantAll);
					
					// initialize delegataire list
					delegataires.removeAll();		
					delegataireAll = new CollaborateurModel();
					delegataireAll.setFullname(messages.commonTous());
					delegataireAll.setFullnameNoSeparater(messages.commonTous());
					delegataireAll.setId(0);
					delegataires.add(delegataireAll);
					delegataireAllSelection.add(delegataireAll);
					
					// load delegants	
					collaborateurService.getAllDelegantsByPerimeter(perId, entiteId, new AsyncCallback<List<CollaborateurModel>>() {
//						collaborateurService.getAllDelegantsByEntiteId(event.getEntiteModel().getEntId(), new AsyncCallback<List<CollaborateurModel>>() {
						@Override
						public void onSuccess(List<CollaborateurModel> arg0) {
							delegants.add(arg0);
							delegantCombobox.setSelection(delegantAllSelection);
						}
						@Override
						public void onFailure(Throwable arg0) {
						}
					});
					
					// load delegataires
					collaborateurService.getAllDelegatairesByPerimeter(perId, entiteId, new AsyncCallback<List<CollaborateurModel>>() {
					//collaborateurService.getAllDelegatairesByEntiteId(event.getEntiteModel().getEntId(), new AsyncCallback<List<CollaborateurModel>>() {
						@Override
						public void onSuccess(List<CollaborateurModel> arg0) {
							delegataires.add(arg0);
							delegataireCombobox.setSelection(delegataireAllSelection);
						}
						@Override
						public void onFailure(Throwable arg0) {
						}
					});
				}
			}
		});
		
		// catch event Filter on top form panel
		bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {
			@Override
			public void onLoadAction(final DelegationListProjectEvent event) {
				if (event.getPerimetreModel() != null) {
					
					if (Arrays.asList(ConstantClient.PERIMETRE_TYPE_IS_CHANTIER).contains(event.getPerimetreModel().getType().getPtyId())) {
						sepCheckBox.setEnabled(true);
						delegationCheckBox.setEnabled(true);
					} else {
						sepCheckBox.setEnabled(false);
						delegationCheckBox.setEnabled(false);
					}
					
					if (delegationFilterEvent == null) {
						delegationFilterEvent = new DelegationFilterEvent();
					}
					delegationFilterEvent.setEntiteModel(event.getEntiteModel());
					
					PerimetreTreeModel perimetreTreeModel = new PerimetreTreeModel(event.getPerimetreModel(),
							SessionServiceImpl.getInstance().getUserContext().getUserRoles());
					
					delegationFilterEvent.setPerimetreTreeModel(perimetreTreeModel);
					
					buttonFiltrer.fireEvent(Events.Select);
					
					String perId = event.getPerimetreModel().getPerId();
					String entiteId = event.getEntiteModel().getEntId();
					
					
					initData();
					// load natures
					natureService.findNatureByEntite(entiteId, new AsyncCallback<List<DelegationNatureModel>>() {
						@Override
						public void onSuccess(List<DelegationNatureModel> arg0) {
							natures.add(arg0);
							natureCombobox.setSelection(natureAllSelection);
						}
						@Override
						public void onFailure(Throwable arg0) {
						}
					});
					
					// load statues
					statusService.getAllDelegationStatuses(new AsyncCallback<List<DelegationStatusModel>>() {
						@Override
						public void onSuccess(List<DelegationStatusModel> arg0) {
							statuses.add(arg0);
							statusCombobox.setSelection(statusAllSelection);
						}
						@Override
						public void onFailure(Throwable arg0) {
						}
					});
					
					// load types
					typeService.getAllTypes(new AsyncCallback<List<DelegationTypeModel>>() {
						@Override
						public void onSuccess(List<DelegationTypeModel> arg0) {
							types.add(arg0);
							typeCombobox.setSelection(typeAllSelection);
						}
						@Override
						public void onFailure(Throwable arg0) {
						}
					});
					
					// load delegants	
					collaborateurService.getAllDelegantsByPerimeter(perId, entiteId, new AsyncCallback<List<CollaborateurModel>>() {
//						collaborateurService.getAllDelegantsByEntiteId(event.getEntiteModel().getEntId(), new AsyncCallback<List<CollaborateurModel>>() {
						@Override
						public void onSuccess(List<CollaborateurModel> arg0) {
							delegants.add(arg0);
							delegantCombobox.setSelection(delegantAllSelection);
						}
						@Override
						public void onFailure(Throwable arg0) {
						}
					});
					
					// load delegataires
					collaborateurService.getAllDelegatairesByPerimeter(perId, entiteId, new AsyncCallback<List<CollaborateurModel>>() {
					//collaborateurService.getAllDelegatairesByEntiteId(event.getEntiteModel().getEntId(), new AsyncCallback<List<CollaborateurModel>>() {
						@Override
						public void onSuccess(List<CollaborateurModel> arg0) {
							delegataires.add(arg0);
							delegataireCombobox.setSelection(delegataireAllSelection);
						}
						@Override
						public void onFailure(Throwable arg0) {
						}
					});
				} 
			}
		});
		
		bus.addHandler(DeleteDelegationEvent.getType(), new DeleteDelegationHandler() {
			@Override
			public void onLoadAction(DeleteDelegationEvent event) {
				buttonFiltrer.fireEvent(Events.Select);
			}
		});
		
		bus.addHandler(DelegationPagingEvent.getType(), new DelegationPagingHandler() {
			@Override
			public void onLoadAction(DelegationPagingEvent event) {
				pagingSize = event.getPageSize();
				buttonFiltrer.fireEvent(Events.Select);
			}
		});
	}
	
	public Button getFilterButton () {
		return this.buttonFiltrer;
	}
}
