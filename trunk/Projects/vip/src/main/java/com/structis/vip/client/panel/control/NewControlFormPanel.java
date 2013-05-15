package com.structis.vip.client.panel.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.DateTimePropertyEditor;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.control.ListControleEvent;
import com.structis.vip.client.event.control.RefreshExternControllerGridEvent;
import com.structis.vip.client.event.control.RefreshExternControllerGridHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientCollaborateurServiceAsync;
import com.structis.vip.client.service.ClientControlServiceAsync;
import com.structis.vip.client.service.ClientControlTypeServiceAsync;
import com.structis.vip.client.service.ClientExternControllerControlServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.ControlModel;
import com.structis.vip.shared.model.ControlTypeModel;
import com.structis.vip.shared.model.ExtControllerControlModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserRoleModel;


public class NewControlFormPanel extends FormPanel {
	private final static int WIDTH = 700;
	private final static int LABEL_WIDTH = 120;
	private final static int HEIGHT = -1;
	
	private final Messages messages = GWT.create(Messages.class);
	private SimpleEventBus bus;
	
	private LabelField lblOrganisationNom;
	private LabelField lblPerimetreAssocie;
	private TextField<String> lblCodeProjet;
	private ComboBox<ControlTypeModel> cbControlType;
	private DateField dfControlDate;
	private ComboBox<CollaborateurModel> cbCollaborateur;
	private ColumnModel controllerColumnModel;
	private Grid<ExtControllerControlModel> externControllerGrid;
	private FieldSet fsExternController;
	private Button btnSelect;
	private FileUploadField ffFile;
	private FileUploadField ffTemp;
	private final FormData formData = new FormData("35%");
	private Button btnAnnuler;
	private Button btnModifier;
		
	private RadioGroup controlerGroup;
	private Radio internalRadio;
	private Radio externalRadio;
	
	
	private ListStore<ControlTypeModel> lstControlTypes = new ListStore<ControlTypeModel>();
	private ListStore<CollaborateurModel> lstCollaborateurs = new ListStore<CollaborateurModel>();
	private ListStore<ExtControllerControlModel> lstExternControlers = new ListStore<ExtControllerControlModel>();
	
	private ClientControlTypeServiceAsync clientControlTypeServiceAsync = ClientControlTypeServiceAsync.Util.getInstance();
	private ClientCollaborateurServiceAsync clientCollaborateurServiceAsync = ClientCollaborateurServiceAsync.Util.getInstance();
	private ClientControlServiceAsync clientControlServiceAsync = ClientControlServiceAsync.Util.getInstance();
	private ClientExternControllerControlServiceAsync clientExternControllerControlServiceAsync = ClientExternControllerControlServiceAsync.Util.getInstance();
		
	
	private ControlModel currentControle;
	private PerimetreTreeModel perimetreTreeModel;
	
	public NewControlFormPanel(SimpleEventBus bus) {
		this.setAction(GWT.getHostPageBaseURL() + ".uploadRapportServlet");
		this.setEncoding(Encoding.MULTIPART);
		this.setMethod(Method.POST);
		this.bus = bus;
		this.setHeading(messages.delegationformheading());
		this.setFrame(true);
		this.setCollapsible(false);
		this.setLayout(new FlowLayout());
		this.setScrollMode(Scroll.AUTO);				
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
		handleEvents();
	}

	private void handleEvents() {
		bus.addHandler(RefreshExternControllerGridEvent.getType(), new RefreshExternControllerGridHandler() {
			public void onLoadAction(RefreshExternControllerGridEvent event) {
				lstExternControlers.removeAll();
				if (event.getExternControllers() != null) {
					lstExternControlers.add(event.getExternControllers());					
				}
//				clientExternControllerControlServiceAsync.findByControl(currentControle.getId(), new AsyncCallback<List<ExtControllerControlModel>>() {
//					@Override
//					public void onSuccess(List<ExtControllerControlModel> arg0) {						
//						lstExternControlers.removeAll();
//						if (arg0 != null ) {
//							lstExternControlers.add(arg0);
//						}
//					}
//					@Override
//					public void onFailure(Throwable arg0) {
//					}
//				});				
			}
		});
	}

	private void initBottomForm() {
		LayoutContainer left = new LayoutContainer();
	    FormLayout layout = new FormLayout();	    
		layout.setLabelAlign(LabelAlign.RIGHT);
		layout.setLabelWidth(LABEL_WIDTH);
		
		left.setBorders(false);
	    left.setLayout(layout);
	    left.setStyleAttribute("paddingLeft", "5px");
	    
		ffFile = new FileUploadField();
		//ffFile.setAllowBlank(false);
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
		externControllerGrid.setHeight(200);
				
		externControllerGrid.setAutoExpandColumn("externalController.name");
		externControllerGrid.setBorders(false);
		externControllerGrid.setStripeRows(true);
		externControllerGrid.setColumnLines(true);
		externControllerGrid.setColumnReordering(true);
		
		//externControllerGrid.getAriaSupport().setLabelledBy(cp.getId() + "-label");
		fsControlerExternal.add(externControllerGrid);
		
		btnSelect = new Button(messages.selectexterncontroller());		
		fsControlerExternal.add(btnSelect, new MarginData(3,0,0,0));
		btnSelect.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				//if (currentControle.getId() != null && currentControle.getId() != 0) {
					ApplyExternControllerDialog dialog = new ApplyExternControllerDialog(bus);
					dialog.setData(SessionServiceImpl.getInstance().getEntiteContext(), currentControle, lstExternControlers.getModels());
					dialog.show();
				//}
			}
		});
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
		btnModifier = new Button(messages.commonValiderButton());
		btPanel.addButton(btnAnnuler);
		btPanel.addButton(btnModifier);
		this.add(btPanel);
		btnAnnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				returnControleList(false);				
			}
		});
		btnModifier.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (isValid()) {
					submit();
				}
//				updateControle();				
			}
		});
		addListener(Events.BeforeSubmit, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				if (ffFile.getValue() != null) {
					String fileName = ffFile.getValue(); 
				
					if (fileName != null && !"".equals(fileName)) {
						int lastDot = fileName.lastIndexOf(".");
						String extFile = fileName.substring(lastDot, fileName.length()).toLowerCase();
						if (!ConstantClient.PDF_EXTENSION_FILE.equals(extFile)) {
							Window.alert("Document doit être un fichier pdf");
							be.setCancelled(true);
						} 
					} else {
						be.setCancelled(true);
						updateControle(fileName);
					}
				}
			}
		});

		
		addListener(Events.Submit, new Listener<FormEvent>() {
			@Override
			public void handleEvent(FormEvent be) {
				String fileName = null;				
				if ((ffFile.getValue() != null) && (!"".equals(ffFile.getValue()))) {
					fileName = ffFile.getValue();
				}
				updateControle(fileName);				
			}
		});
	}

	protected void updateControle(String fileName) {		
		if (this.isValid()) {
			if (controlerGroup.getValue() == internalRadio || lstExternControlers.getCount() > 0) {
				applyFormDataToModel(fileName);
				callUpdateControlService();
			} else {
				Window.alert("Please add an extern controller");
			}
			
		}
	}

	private void callUpdateControlService() {
		if (currentControle.getId() != null) {
		clientControlServiceAsync.update(currentControle, new AsyncCallback<ControlModel>() {

			@Override
			public void onFailure(Throwable arg0) {
			}

			@Override
			public void onSuccess(ControlModel arg0) {
//				currentControle.setCharacter(arg0.getCharacter());
//				currentControle.setCollaborateur(arg0.getCollaborateur());
//				currentControle.setCodeProject(arg0.getCodeProject());
//				currentControle.setControlType(arg0.getControlType());
//				currentControle.setDate(arg0.getDate());
//				currentControle.setExternControllers(currentControle.getExternControllers());				
				returnControleList(true);
			}
			
		});
		} else {
			clientControlServiceAsync.insert(currentControle, new AsyncCallback<ControlModel>() {

				@Override
				public void onFailure(Throwable arg0) {
				}

				@Override
				public void onSuccess(ControlModel arg0) {
					
					currentControle = arg0;
					currentControle.setPermissionByRole(perimetreTreeModel.getIsLectureControl(), perimetreTreeModel.getIsModificationControl());
					currentControle.setExternControllers(lstExternControlers.getModels());
					currentControle.updateExtControllerNames();
					returnControleList(true);
				}
				
			});

		}
	}

	private void applyFormDataToModel(String fileName) {
		currentControle.setCodeProject(lblCodeProjet.getValue());
		currentControle.setControlType(cbControlType.getValue());
		currentControle.setDate(dfControlDate.getValue());
		currentControle.setCharacter(controlerGroup.getValue().getBoxLabel());
		if (controlerGroup.getValue() == internalRadio) {
			currentControle.setCollaborateur(cbCollaborateur.getValue());			
			currentControle.setExternControllers(null);			
		} else {
			currentControle.setCollaborateur(null);			
			currentControle.setExternControllers(lstExternControlers.getModels());						
		}
		currentControle.setRapport(fileName);
		//currentControle.setRapport(ffFile.getValue());				
	}

	private void initInformationForm() {
		LayoutContainer lcInfo = new LayoutContainer();		
		lcInfo.setSize(WIDTH, HEIGHT);
		lcInfo.setLayout(new ColumnLayout());
		
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
	    right.setLayout(layout2);
	    right.setStyleAttribute("paddingLeft", "5px");	
	    
		lblCodeProjet = new TextField<String>();
		lblCodeProjet.setAllowBlank(false);
		lblCodeProjet.setFieldLabel(messages.controlecodeprojet());
		lblCodeProjet.setValue("");
		left.add(lblCodeProjet);
		
				
		cbControlType = new ComboBox<ControlTypeModel>();
		cbControlType.setEditable(false);
		cbControlType.setTriggerAction(TriggerAction.ALL);
		cbControlType.setAllowBlank(false);
		cbControlType.setFieldLabel(messages.controletype());
		cbControlType.setDisplayField(ControlTypeModel.CON_LABEL);
		cbControlType.setStore(lstControlTypes);
		left.add(cbControlType);
		
		right.add(new LabelField(""));
		dfControlDate = new DateField();
		dfControlDate.setId("dfDebut");
		dfControlDate.setFieldLabel(messages.datedecontrole());
		dfControlDate.setEditable(true);
		dfControlDate.setFormatValue(true);
		dfControlDate.setAllowBlank(false);
		dfControlDate.setValue(new Date());
		dfControlDate.setPropertyEditor(new DateTimePropertyEditor(ConstantClient.DATE_FORMAT));
		right.add(dfControlDate);
		
		controlerGroup = new RadioGroup();
		controlerGroup.setFieldLabel(messages.controlertype());
		internalRadio = new Radio();
		externalRadio = new Radio();
		
		internalRadio.setBoxLabel(messages.controlerinternal());
		externalRadio.setBoxLabel(messages.controlerexternal());
		
		controlerGroup.setStyleAttribute("padding-left", "20px");

		controlerGroup.add(internalRadio);
		controlerGroup.add(externalRadio);
		controlerGroup.addListener(Events.Change, new Listener<FieldEvent>() {
			@Override
			public void handleEvent(FieldEvent fe) {				
				if (controlerGroup.getValue() == internalRadio) {					
					cbCollaborateur.setVisible(true);
					cbCollaborateur.setAllowBlank(false);
					fsExternController.setVisible(false);					
				} else {
					cbCollaborateur.setVisible(false);
					cbCollaborateur.setAllowBlank(true);
					fsExternController.setVisible(true);
				}
			} 
		});

		
		left.add(controlerGroup);
		
		cbCollaborateur = new ComboBox<CollaborateurModel>();		
		cbCollaborateur.setEditable(true);
		
		cbCollaborateur.setValidator(new Validator(){

			@Override
			public String validate(Field<?> field, String value) {
				if (field == cbCollaborateur && cbCollaborateur.getValue() == null) {						
					return messages.controleinternnotexiste();
				} 
				return null;
			}
			
		});
		cbCollaborateur.setTriggerAction(TriggerAction.ALL);
		cbCollaborateur.setFieldLabel(messages.controlercollaborateur());
		cbCollaborateur.setDisplayField(CollaborateurModel.COLLA_FULL_NAME);
		cbCollaborateur.setStore(lstCollaborateurs);
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
				returnControleList(false);
				
				//if (!AppUtil.checkToShowWarningInEditMode() ) {
				resetForm();
					
					//documentMdlModels.removeAll();
					//ContentEvent contentEvent = new ContentEvent();
					//contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GRID_DELEGATION_PANEL);
					//bus.fireEvent(contentEvent);
				//}
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
	private void returnControleList(boolean refresh) {				
		ListControleEvent lce = new ListControleEvent();
		lce.setRefresh(refresh);
		if (refresh) {
			lce.setControlModel(currentControle);
		}			
		bus.fireEvent(lce);
	}

	private void displayFormData() {
		String orgName =  (currentControle.getPerimetre()) == null ? null : currentControle.getPerimetre().getName();			
		lblOrganisationNom.setText(orgName);
		lblPerimetreAssocie.setText(currentControle.getPerimetreParent());	
		if (currentControle.getId() == null) {
			if (currentControle.getPerimetre() != null) {
				lblCodeProjet.setValue(currentControle.getPerimetre().getChantierNumeroDeProjet());
			}
		} else {
			lblCodeProjet.setValue(currentControle.getCodeProject());
		}
		cbControlType.setValue(currentControle.getControlType());
		dfControlDate.setValue(currentControle.getDate());
		if (currentControle.getCharacter() == null  || currentControle.getCharacter().equalsIgnoreCase(ControlModel.INTERNAL)) {
			cbCollaborateur.setValue(currentControle.getCollaborateur());
			cbCollaborateur.setVisible(true);
			cbCollaborateur.setAllowBlank(false);
			fsExternController.setVisible(false);
			controlerGroup.setValue(internalRadio);
		}	else {
			cbCollaborateur.setValue(null);
			cbCollaborateur.setAllowBlank(true);
			fsExternController.setVisible(true);			
			controlerGroup.setValue(externalRadio);
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
		// add BYTP
		
//		clientControlTypeServiceAsync.findByEntite(ConstantClient.ENTITE_ID_IS_BYEFE,new AsyncCallback<List<ControlTypeModel>>() {
		clientControlTypeServiceAsync.findByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),new AsyncCallback<List<ControlTypeModel>>() {
			@Override
			public void onSuccess(List<ControlTypeModel> arg0) {
				lstControlTypes.removeAll();
				lstControlTypes.add(arg0);
			}

			@Override
			public void onFailure(Throwable arg0) {
			}
		});
		//add BYTP
//		clientCollaborateurServiceAsync.getAllCollaborateursByEntiteId(ConstantClient.ENTITE_ID_IS_BYEFE, false, new AsyncCallback<List<CollaborateurModel>>() {
		clientCollaborateurServiceAsync.getAllCollaborateursByEntiteId(SessionServiceImpl.getInstance().getEntiteContext().getEntId(), false, new AsyncCallback<List<CollaborateurModel>>() {
			@Override
			public void onSuccess(List<CollaborateurModel> arg0) {
				lstCollaborateurs.removeAll();
				lstCollaborateurs.add(arg0);
			}
			@Override
			public void onFailure(Throwable arg0) {
			}
		});
//		private ListStore<CollaborateurModel> lstCollaborateurs = new ListStore<CollaborateurModel>();				
	}
}
