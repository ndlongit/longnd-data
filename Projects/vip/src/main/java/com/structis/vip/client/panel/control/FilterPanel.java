package com.structis.vip.client.panel.control;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.ListViewEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.DateTimePropertyEditor;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.control.ControlFilterEvent;
import com.structis.vip.client.event.control.RefreshTreeEvent;
import com.structis.vip.client.event.control.RefreshTreeHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientCollaborateurServiceAsync;
import com.structis.vip.client.service.ClientControlTypeServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.widget.XComboBox;
import com.structis.vip.shared.model.ControlModel;
import com.structis.vip.shared.model.ControlTypeModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.KeyValueModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;


public class FilterPanel extends VerticalPanel {
	private final Messages messages = GWT.create(Messages.class);
	private static final int COMBOBOX_WIDTH = 275;
	private static final int LABEL_WIDTH = 120;
	private int pagingSize = ConstantClient.DEFAULT_PAGE_SIZE_50;

	SimpleEventBus bus;
	ControlFilterEvent controlFilterEvent;

	TextField<String>   tfCodeProject;	
	XComboBox<KeyValueModel>   cbCaractere;
	//TextField<String>   tfControllerName;
	private ComboBox<KeyValueModel> tfControllerName;
	
//	XComboBox<EntiteModel> entiteCombobox;
//	XComboBox<PerimetreModel> societeCombobox;
//	XComboBox<PerimetreModel> serviceCombobox;
//	XComboBox<PerimetreModel> uoCombobox;
//	XComboBox<PerimetreModel> directionCombobox;
	XComboBox<ControlTypeModel> controlTypeCombobox;

	DateField fromDateField;
	DateField toDateField;
	Button buttonFiltrer;
	FormPanel panel;

	private ListStore<KeyValueModel> caracteres = new ListStore<KeyValueModel>();
	private ListStore<KeyValueModel> controleurs = new ListStore<KeyValueModel>();
	private ListStore<ControlTypeModel> controlTypes = new ListStore<ControlTypeModel>();

	List<EntiteModel> entiteAllSelection = new ArrayList<EntiteModel>();
	List<PerimetreModel> societeAllSelection = new ArrayList<PerimetreModel>();
	List<PerimetreModel> serviceAllSelection = new ArrayList<PerimetreModel>();
	List<PerimetreModel> uoAllSelection = new ArrayList<PerimetreModel>();
	List<PerimetreModel> directionAllSelection = new ArrayList<PerimetreModel>();
	private List<ControlTypeModel> controlTypeAllSelection = new ArrayList<ControlTypeModel>();
	private List<KeyValueModel> caractereAllSelection = new ArrayList<KeyValueModel>();
	
	ControlTypeModel controlTypeAll;
	private KeyValueModel caracterAll;
	private EntiteModel selectedEntiteModel;
	private PerimetreModel selectedPerimetreModel;
	private ClientCollaborateurServiceAsync clientCollaborateurServiceAsync = ClientCollaborateurServiceAsync.Util.getInstance();
	private KeyValueModel nullHolderController = new KeyValueModel();
	
	
	public FilterPanel(SimpleEventBus bus) {
		this.bus = bus;
		 
		nullHolderController.setKey(null);
		nullHolderController.setValue(null);
		nullHolderController.setType("");
		initData();
		
		initUI();
		
		addHandler();
	}
	
	private void initData() {
		controlTypeAll = new ControlTypeModel();
		controlTypeAll.setLabel("Tous");
		controlTypeAll.setId(0);
		controlTypes.add(controlTypeAll);
		controlTypeAllSelection.add(controlTypeAll);
		caracterAll = new KeyValueModel();		
		caracterAll.setKey("");
		caracterAll.setValue("Tous");
		caracterAll.setId(0);
		caractereAllSelection.add(caracterAll);
	}

	private void initUI() {
		FormData formData = new FormData("100%");
		FormData formDataR = new FormData("102%");
		
		LayoutContainer main = new LayoutContainer();		
		main.setLayout(new ColumnLayout()); 
		main.setStyleAttribute("padding", "5px");
		
		LayoutContainer left = new LayoutContainer();
	    FormLayout layout = new FormLayout();	    
		layout.setLabelAlign(LabelAlign.RIGHT);
		layout.setLabelWidth(LABEL_WIDTH);
		left.setBorders(false);
	    left.setLayout(layout);
	    left.setStyleAttribute("paddingLeft", "5px");
	    
	    
	    LayoutContainer right = new LayoutContainer();
	    FormLayout layout2 = new FormLayout();	    
		layout2.setLabelAlign(LabelAlign.RIGHT);
		layout2.setLabelWidth(LABEL_WIDTH);		
	    right.setLayout(layout2);
	    right.setStyleAttribute("paddingLeft", "5px");	    
	    
	    // entite combobox
	    tfCodeProject = new TextField<String>();
	    tfCodeProject.setWidth(COMBOBOX_WIDTH);
	    tfCodeProject.setFieldLabel("CI Code Projet");
	    tfCodeProject.setLabelSeparator("");
	    left.add(tfCodeProject, formData);

		
		// societe combobox
	    cbCaractere = new XComboBox<KeyValueModel>() {
	    	@Override
	    	protected void processOnChanges(List<KeyValueModel> selection) {
	    		
	    		KeyValueModel val = selection == null ? null :selection.get(0); 
				if (val == null || val.getId()==0) {//all
					controleurs.clearFilters();
				} else if (val.getId() == 1) {//interne
					nullHolderController.setType("0");
					controleurs.filter("type", "0");
				} else { //externe
					nullHolderController.setType("1");
					controleurs.filter("type", "1");
				}
	    	}
	    };
	    cbCaractere.setWidth(COMBOBOX_WIDTH);
	    cbCaractere.setFieldLabel(messages.controlertype());
	    cbCaractere.setDisplayField(KeyValueModel.VALUE);
	    cbCaractere.setLabelSeparator("");
	    cbCaractere.setStore(caracteres);
	    cbCaractere.setSelection(caractereAllSelection);
	    left.add(cbCaractere, formData);
	    				
		// form date
		fromDateField = new DateField();
		fromDateField.setLabelSeparator("");
		fromDateField.setFieldLabel(messages.controledatededebut());
		fromDateField.setPropertyEditor(new DateTimePropertyEditor(ConstantClient.DATE_FORMAT));
		fromDateField.setLabelStyle("white-space: nowrap");		
		fromDateField.setWidth(210);
		left.add(fromDateField, formData);
		
		controlTypeCombobox = new XComboBox<ControlTypeModel>();
		controlTypeCombobox.setWidth(COMBOBOX_WIDTH);
		controlTypeCombobox.setEditable(false);
		controlTypeCombobox.setTriggerAction(TriggerAction.ALL);
		controlTypeCombobox.setStore(controlTypes);
		controlTypeCombobox.setFieldLabel(messages.controletypedecontrole());
		controlTypeCombobox.setLabelSeparator("");
		controlTypeCombobox.setSelection(controlTypeAllSelection);
		controlTypeCombobox.setDisplayField(ControlTypeModel.CON_LABEL);
		controlTypeCombobox.setLabelStyle("white-space: nowrap");
		
		right.add(controlTypeCombobox, formData);
		
		//tfControllerName = new TextField<String>();
		tfControllerName = new ComboBox<KeyValueModel>();
		tfControllerName.setDisplayField(KeyValueModel.VALUE);
		tfControllerName.setEditable(true);
		tfControllerName.setTriggerAction(TriggerAction.ALL);
		tfControllerName.setStore(controleurs);
		tfControllerName.setWidth(COMBOBOX_WIDTH);
		tfControllerName.setFieldLabel(messages.controlenomducontrole());
		tfControllerName.setLabelSeparator("");
		right.add(tfControllerName, formData);
		
		toDateField = new DateField();
		toDateField.setLabelSeparator("");
		toDateField.setFieldLabel(messages.controledatedefin());
		toDateField.setPropertyEditor(new DateTimePropertyEditor(ConstantClient.DATE_FORMAT));
		toDateField.setLabelStyle("white-space: nowrap");
		toDateField.setWidth(210);
		right.add(toDateField, formData);	
		
		ContentPanel pButton = new ContentPanel();				
		pButton.setStyleAttribute("paddingRight", "0px");	 
		pButton.setStyleAttribute("margin-right", "0px");		
		
		pButton.setBodyBorder(false);
		
		pButton.setBorders(false);
		pButton.setHeaderVisible(false);
		pButton.setButtonAlign(HorizontalAlignment.RIGHT);
		
		buttonFiltrer = new Button("Filtre");
		buttonFiltrer.setWidth(100);
		pButton.addButton(buttonFiltrer);
		right.add(pButton, formDataR);		
		
		// add to main panel		
		main.setStyleAttribute("padding-left", "0px");
		main.setStyleAttribute("padding-bottom", "0px");
		main.add(left, new ColumnData(.5));
		main.add(right, new ColumnData(.5));		
		
		// init main form panel
		panel = new FormPanel();				
		panel.setPadding(0);
		panel.setStyleAttribute("background", "white");
		panel.setHeaderVisible(false);
		panel.setSize(860, -1);
		panel.setBodyBorder(false);
		panel.setLabelAlign(LabelAlign.RIGHT);
		panel.setLabelWidth(LABEL_WIDTH);
		panel.setButtonAlign(HorizontalAlignment.RIGHT);		
		FormData formData2 = new FormData("100%");
		formData2.setMargins(new Margins(10,0,0,0));
		panel.add(main, formData2);
		
		// add to root panel
		setStyleAttribute("padding-left", "5px");
		setSpacing(0);	
		setHeight(150);
		add(panel);
	}
	
	
	private void addHandler() {
		// add event for Filter button
		
		cbCaractere.getListView().addListener( Events.SelectionChange, new Listener<ListViewEvent<KeyValueModel>>() {
			@Override
			public void handleEvent(ListViewEvent<KeyValueModel> be) {
				KeyValueModel val = cbCaractere.getSelection() == null ? null :cbCaractere.getSelection().get(0); 
				if (val == null || val.getId()==0) {//all
					controleurs.clearFilters();
				} else if (val.getId() == 1) {//interne
					controleurs.filter("type", "0");
				} else { //externe
					controleurs.filter("type", "1");
				}
				
			}
		});
		buttonFiltrer.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (controlFilterEvent == null) {
					controlFilterEvent = new ControlFilterEvent();
				}
				TreePanel<PerimetreTreeModel> p = (TreePanel<PerimetreTreeModel>)AppUtil.getPanel(ConstantClient.CONTROL_TREE_ID);				
				PerimetreTreeModel treeModel =  p.getSelectionModel().getSelectedItem();
				if (treeModel == null) {
					treeModel = new PerimetreTreeModel(selectedPerimetreModel, SessionServiceImpl.getInstance().getUserContext().getUserRoles());
					treeModel.setEntiteId(selectedEntiteModel.getEntId());
					treeModel.setLevel(0);
					treeModel.setPath(selectedPerimetreModel.getName());
					treeModel.setIsEntite(false);
				}
				treeModel.setName(SafeHtmlUtils.htmlEscape(treeModel.getName()));
				controlFilterEvent.setPerimetreTreeModel(treeModel);
				controlFilterEvent.setEntiteModel(SessionServiceImpl.getInstance().getEntiteContext());
				controlFilterEvent.setTypeModel(controlTypeCombobox.getSelection());				
				controlFilterEvent.setStartDate(fromDateField.getValue());
				controlFilterEvent.setEndDate(toDateField.getValue());
				controlFilterEvent.setCodeProject(tfCodeProject.getValue());
				controlFilterEvent.setCaracteres(cbCaractere.getSelection());
				controlFilterEvent.setControllerName(tfControllerName.getRawValue());
				controlFilterEvent.setPageSize(pagingSize);
				
				// fire event delegation filter
				bus.fireEvent(controlFilterEvent);
			}
		});
		
		bus.addHandler(RefreshTreeEvent.getType(), new RefreshTreeHandler() {
			public void onLoadAction(RefreshTreeEvent event) {
				panel.reset();
				selectedPerimetreModel = event.getPerimetreModel();
			}
		});
		
	}
	
	public Button getFilterButton () {
		return this.buttonFiltrer;
	}
	public void onLoadPanel() {
		KeyValueModel inter = new KeyValueModel();
		inter.setId(1);
		inter.setKey(ControlModel.INTERNAL);
		inter.setValue(ControlModel.INTERNAL);
		KeyValueModel exter = new KeyValueModel();
		exter.setId(2);
		exter.setKey(ControlModel.EXTERNAL);
		exter.setValue(ControlModel.EXTERNAL);
		caracteres.removeAll();
		caracteres.add(caracterAll);
		caracteres.add(inter);
		caracteres.add(exter);
		cbCaractere.setSelection(caractereAllSelection);
		
		selectedEntiteModel = SessionServiceImpl.getInstance().getEntiteContext();
		selectedPerimetreModel = SessionServiceImpl.getInstance().getPerimetreContext();		
		controlTypeCombobox.setSelection(controlTypeAllSelection);
		
		ClientControlTypeServiceAsync.Util.getInstance().findAll(new AsyncCallback<List<ControlTypeModel>>() {
			@Override
			public void onSuccess(List<ControlTypeModel> result) {
				controlTypes.removeAll();				
				controlTypes.add(controlTypeAll);				
				controlTypes.add(result);
			}

			@Override
			public void onFailure(Throwable arg0) {
			}
		});
		ClientControlTypeServiceAsync.Util.getInstance().findAll(new AsyncCallback<List<ControlTypeModel>>() {
			@Override
			public void onSuccess(List<ControlTypeModel> result) {
				controlTypes.removeAll();				
				controlTypes.add(controlTypeAll);				
				controlTypes.add(result);
			}

			@Override
			public void onFailure(Throwable arg0) {
			}
		});		
		// add BYTP
		
		clientCollaborateurServiceAsync.getAllControleursByEntiteId(SessionServiceImpl.getInstance().getEntiteContext().getEntId(), new AsyncCallback<List<KeyValueModel>>() {
			@Override
			public void onSuccess(List<KeyValueModel> arg0) {
				controleurs.removeAll();
				
				controleurs.add(nullHolderController);
				controleurs.add(arg0);				
			}
			@Override
			public void onFailure(Throwable arg0) {
			}
		});
	}
}
