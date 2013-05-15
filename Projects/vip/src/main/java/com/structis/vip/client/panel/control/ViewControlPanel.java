package com.structis.vip.client.panel.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ComponentManager;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.control.EditControleEvent;
import com.structis.vip.client.event.control.ListControleEvent;
import com.structis.vip.client.event.control.ViewControleEvent;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientCollaborateurServiceAsync;
import com.structis.vip.client.service.ClientControlServiceAsync;
import com.structis.vip.client.service.ClientControlTypeServiceAsync;
import com.structis.vip.client.service.ClientExternControllerControlServiceAsync;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.ControlModel;
import com.structis.vip.shared.model.ControlTypeModel;
import com.structis.vip.shared.model.ExtControllerControlModel;
import com.structis.vip.shared.model.ExternControllerModel;
import com.structis.vip.shared.model.PerimetreTreeModel;


public class ViewControlPanel extends FormPanel {
	private final static int WIDTH = 700;
	private final static int LABEL_WIDTH = 120;
	private final static int HEIGHT = -1;
	
	private final Messages messages = GWT.create(Messages.class);
	private SimpleEventBus bus;
	
	private LabelField lblOrganisationNom;
	private LabelField lblPerimetreAssocie;
	private LabelField lblCodeProjet;
	private LabelField cbControlType;
	private LabelField dfControlDate;
	private LabelField cbCollaborateur;
	private ColumnModel controllerColumnModel;
	private Grid<ExtControllerControlModel> externControllerGrid;
	private FieldSet fsExternController;	
	private LabelField ffFile;
	private final FormData formData = new FormData("35%");
	private Button btnAnnuler;	
	private Button btnModifier;
	private LabelField controlerGroup;
	
	
//	private ListStore<ControlTypeModel> lstControlTypes = new ListStore<ControlTypeModel>();
//	private ListStore<CollaborateurModel> lstCollaborateurs = new ListStore<CollaborateurModel>();
	private ListStore<ExtControllerControlModel> lstExternControlers = new ListStore<ExtControllerControlModel>();
	
//	private ClientControlTypeServiceAsync clientControlTypeServiceAsync = ClientControlTypeServiceAsync.Util.getInstance();
//	private ClientCollaborateurServiceAsync clientCollaborateurServiceAsync = ClientCollaborateurServiceAsync.Util.getInstance();
	
	private ClientExternControllerControlServiceAsync clientExternControllerControlServiceAsync = ClientExternControllerControlServiceAsync.Util.getInstance();
		
	
	private ControlModel currentControle;
	private PerimetreTreeModel perimetreTreeModel;
	
	public ViewControlPanel(SimpleEventBus bus) {
		this.bus = bus;
		this.setHeading(messages.delegationformheading());
		this.setFrame(true);
		this.setCollapsible(false);
		this.setLayout(new FlowLayout());
		this.setScrollMode(Scroll.AUTO);		
		this.setLabelSeparator(":");
		this.addBackLink();
		// add view in field set
		this.initTopForm();
		// add the seperator line
		// setup top layout
		LayoutContainer lcLine = new LayoutContainer();
		lcLine.setSize(WIDTH, HEIGHT);
		lcLine.setLayout(new ColumnLayout());
		lcLine.add(new HTML("<hr width='680px'/>"));
		this.add(lcLine);
		// add view for information form
		this.initInformationForm();
		// add field set
		fsExternController = this.initFieldSets();
		this.add(fsExternController);
		this.initBottomForm();
		// add buttons
		this.initButtons();
	}

	private void initBottomForm() {
		LayoutContainer left = new LayoutContainer();
	    FormLayout layout = new FormLayout();	    
		layout.setLabelAlign(LabelAlign.RIGHT);
		layout.setLabelWidth(LABEL_WIDTH);
		
		left.setBorders(false);
	    left.setLayout(layout);
	    left.setStyleAttribute("paddingLeft", "5px");
	    
		ffFile = new LabelField();		
		ffFile.setWidth(400);
		ffFile.setName("uploadedfile");
		ffFile.setFieldLabel(messages.controlerapportfile());
		left.add(ffFile, formData);
		this.add(left);			
	}

	private FieldSet initFieldSets() {
		FieldSet fsControlerExternal = new FieldSet();		
		fsControlerExternal.setHeading(messages.controlerexternalfieldset());
		fsControlerExternal.setCollapsible(true);
		fsControlerExternal.setWidth(WIDTH);
		
		// setup grid for external controlers
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		// Column documents
		ColumnConfig column = new ColumnConfig();
		column.setId("externalController.name");
		column.setHeader(messages.controlgridfullname());
		column.setWidth(300);
		column.setRowHeader(true);		
		column.setSortable(true);
		configs.add(column);
						

		// setup column model
		controllerColumnModel = new ColumnModel(configs);

		lstExternControlers = new ListStore<ExtControllerControlModel>();
		externControllerGrid = new Grid<ExtControllerControlModel>(lstExternControlers, controllerColumnModel);
		externControllerGrid.setStyleAttribute("borderTop", "none");
		externControllerGrid.setHeight(100);
		externControllerGrid.setAutoExpandColumn("externalController.name");
		externControllerGrid.setBorders(false);
		externControllerGrid.setStripeRows(true);
		externControllerGrid.setColumnLines(true);
		externControllerGrid.setColumnReordering(true);
		
		//externControllerGrid.getAriaSupport().setLabelledBy(cp.getId() + "-label");
		fsControlerExternal.add(externControllerGrid);		
		return fsControlerExternal;
	}

	private void initButtons() {
		ContentPanel btPanel = new ContentPanel();
		btPanel.setSize(WIDTH, HEIGHT);
		btPanel.add(new HTML("<hr width='680px'/>"));
		btPanel.setButtonAlign(HorizontalAlignment.RIGHT);		
		btPanel.setHeaderVisible(false);
		//btPanel.setBodyBorder(false);
		btnAnnuler = new Button(messages.commonAnnulerButton());				
		btnModifier = new Button(messages.commonmodifierbutton());		
		btPanel.addButton(btnAnnuler);		
		btPanel.addButton(btnModifier);
		this.add(btPanel);
		btnAnnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				returnControleList();				
			}
		});
		btnModifier.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				openEditControle(currentControle);				
			}
		});		
		
	}

	public void openEditControle(ControlModel model) {
		ControlLeftPanel clp = (ControlLeftPanel)ComponentManager.get().get(ConstantClient.CONTROL_TREE_PANEL_ID);		
		PerimetreTreeModel ptModel = clp.getSelectedPerimetreTreeModel();				
		EditControleEvent controlEvent = new EditControleEvent();
		controlEvent.setPerimetreTreeModel(ptModel);		
		controlEvent.setControlModel(model);
		bus.fireEvent(controlEvent);		
	}

	private void initInformationForm() {
		LayoutContainer lcInfo = new LayoutContainer();		
		lcInfo.setSize(WIDTH, HEIGHT);
		lcInfo.setLayout(new ColumnLayout());
		
		LayoutContainer left = new LayoutContainer();
	    FormLayout layout = new FormLayout();	    
	    layout.setLabelSeparator(":");
		layout.setLabelAlign(LabelAlign.RIGHT);
		layout.setLabelWidth(LABEL_WIDTH);
		
		left.setBorders(false);
	    left.setLayout(layout);
	    left.setStyleAttribute("paddingLeft", "5px");
	    
	    
	    LayoutContainer right = new LayoutContainer();
	    FormLayout layout2 = new FormLayout();	    
		layout2.setLabelAlign(LabelAlign.RIGHT);				
	    right.setLayout(layout2);
	    right.setStyleAttribute("paddingLeft", "5px");	
	    
		lblCodeProjet = new LabelField();
		lblCodeProjet.setFieldLabel(messages.controlecodeprojet());
		lblCodeProjet.setValue("Testing");
		left.add(lblCodeProjet);		
				
		cbControlType = new LabelField();
		
		cbControlType.setFieldLabel(messages.controletype());		
		left.add(cbControlType);
		
		right.add(new LabelField(""));		
		dfControlDate = new LabelField();
		dfControlDate.setId("dfDebut");
		dfControlDate.setFieldLabel(messages.datedecontrole());		
		dfControlDate.setValue(new Date());		
		right.add(dfControlDate);
		
		controlerGroup = new LabelField();
		controlerGroup.setFieldLabel(messages.controlertype());
		
		controlerGroup.setStyleAttribute("padding-left", "20px");		
		
		left.add(controlerGroup);
		
		cbCollaborateur = new LabelField();
		cbCollaborateur.setFieldLabel(messages.controlercollaborateur());		
		left.add(cbCollaborateur);				
				
		
		lcInfo.add(left);
		lcInfo.add(right);
		this.add(lcInfo);
	}

	private void initTopForm() {
		LayoutContainer lcTop = new LayoutContainer();
		lcTop.setSize(WIDTH, HEIGHT);
		lcTop.setLayout(new ColumnLayout());
		LayoutContainer left = new LayoutContainer();		
	    FormLayout layout = new FormLayout();	    
	    layout.setLabelSeparator(":");
		layout.setLabelAlign(LabelAlign.RIGHT);
		layout.setLabelWidth(LABEL_WIDTH);
		
		left.setBorders(false);
	    left.setLayout(layout);
	    left.setStyleAttribute("paddingLeft", "5px");
	    
	    
	    LayoutContainer right = new LayoutContainer();
	    FormLayout layout2 = new FormLayout();	    
		layout2.setLabelAlign(LabelAlign.RIGHT);				
	    right.setLayout(layout2);
	    right.setStyleAttribute("paddingLeft", "5px");	
	    
	    
		lblOrganisationNom = new LabelField();
		lblOrganisationNom.setFieldLabel(messages.controleorganisationnom());
		lblOrganisationNom.setText("");
		lblPerimetreAssocie = new LabelField();
		lblPerimetreAssocie.setFieldLabel(messages.controleperimetreassocie());
		lblPerimetreAssocie.setText("");
		left.add(lblOrganisationNom);
		right.add(lblPerimetreAssocie);
		lcTop.add(left);
		lcTop.add(right);
		this.add(lcTop);		
	}
	
	private void addBackLink() {
		LayoutContainer backLink = new LayoutContainer();
		backLink.setSize(WIDTH, HEIGHT);
		Label lblBack = new Label(messages.retourlistcontrole());
		
		lblBack.setStyleName("x-link-item");
		backLink.setStyleAttribute("margin-bottom", "20px");
		backLink.add(lblBack);
		
		lblBack.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				returnControleList();				
				resetForm();					
			}						
		});		
		
		this.add(backLink);
				
	}
	/**
	 * reset data in form
	 */
	private void resetForm() {
		this.reset();
	}
	private void returnControleList() {
		ListControleEvent lce = new ListControleEvent();
		bus.fireEvent(lce);
	}

	private void displayFormData() {		
		String orgName =  (currentControle.getPerimetre()) == null ? null : currentControle.getPerimetre().getName();			
		lblOrganisationNom.setText(orgName);
		lblPerimetreAssocie.setText(currentControle.getPerimetreParent());		
		lblCodeProjet.setValue(currentControle.getCodeProject());		
		cbControlType.setValue(currentControle.getControlType().getLabel());
		dfControlDate.setValue(currentControle.getDate() != null ? DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).format(currentControle.getDate()) : "");
		controlerGroup.setValue(currentControle.getCharacter());
		if (currentControle.getCharacter() == null  || currentControle.getCharacter().equalsIgnoreCase(ControlModel.INTERNAL)) {
			cbCollaborateur.setValue(currentControle.getCollaborateur().getFullname());
			cbCollaborateur.setVisible(true);
			fsExternController.setVisible(false);			
		}	else {
			cbCollaborateur.setValue(null);
			cbCollaborateur.setVisible(false);
			fsExternController.setVisible(true);			
		}		
		ffFile.setValue(currentControle.getRapport());				
	}
	
	public void applyControlModel(ControlModel controlModel, PerimetreTreeModel perimetreTree) {
		this.currentControle = controlModel;
		this.perimetreTreeModel = perimetreTree;
		if (ControlModel.EXTERNAL.equalsIgnoreCase(this.currentControle.getCharacter())) {
			
			clientExternControllerControlServiceAsync.findByControl(controlModel.getId(), new AsyncCallback<List<ExtControllerControlModel>>() {
				@Override
				public void onSuccess(List<ExtControllerControlModel> arg0) {
					currentControle.setExternControllers(arg0);
					lstExternControlers.removeAll();
					if (arg0 != null ) {
						lstExternControlers.add(arg0);
					}
				}
				@Override
				public void onFailure(Throwable arg0) {
				}
			});
			 
			
		}
		displayFormData();
	}
	
	public void setControlModel(ControlModel controlModel) {
		this.currentControle = controlModel;
	}
	
	public ControlModel getControlModel() {
		return this.currentControle;
	}
	
	public void loadInitData() {
		}
}
