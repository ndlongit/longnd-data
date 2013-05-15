package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.CheckBoxListView;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.DateTimePropertyEditor;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.HtmlEditor;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.constant.Helper;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationEvent;
import com.structis.vip.client.event.DelegationEventHandler;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.exception.ExceptionMessageHandler;
import com.structis.vip.client.service.ClientCollaborateurServiceAsync;
import com.structis.vip.client.service.ClientDelegationModelServiceAsync;
import com.structis.vip.client.service.ClientDelegationNatureServiceAsync;
import com.structis.vip.client.service.ClientDelegationServiceAsync;
import com.structis.vip.client.service.ClientDelegationStatusServiceAsync;
import com.structis.vip.client.service.ClientDelegationTypeServiceAsync;
import com.structis.vip.client.service.ClientDemDomServiceAsync;
import com.structis.vip.client.service.ClientDocumentMdlServiceAsync;
import com.structis.vip.client.service.ClientEntiteJuridiqueServiceAsync;
import com.structis.vip.client.service.ClientFieldRuleServiceAsync;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.client.util.NameValuePair;
import com.structis.vip.client.util.ReportUtil;
import com.structis.vip.shared.exception.DelegationException;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.DelegationDelegataireModel;
import com.structis.vip.shared.model.DelegationModel;
import com.structis.vip.shared.model.DelegationNatureModel;
import com.structis.vip.shared.model.DelegationStatusModel;
import com.structis.vip.shared.model.DelegationTypeModel;
import com.structis.vip.shared.model.DemDomModel;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.DomDelModel;
import com.structis.vip.shared.model.EntiteJuridiqueModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.FieldRuleModel;
import com.structis.vip.shared.model.LanguageModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTypeModel;

/**
 * Implement new delegation field set class
 */
public class NewDelegationFormPanel extends CommonDelegationPanel {

	private LabelField lblType;
	private LabelField cbStatus;
	private ComboBox<DelegationNatureModel> cbNature;
	private ContentPanel cpDescription;
	private HtmlEditor heDescription;
	private LabelField lblDelegationPrincipale;
	private ComboBox<CollaborateurModel> cbDelegant;
	private ComboBox<CollaborateurModel> cbDelegataire;
	private SimpleComboBox<String> cbConjoin;
	private DateField dfDebut;
	private DateField dfFin;
	private DateField dfSignatureProposition;
	private TextField<String> txtSignatureProposition;
	private DateField dfSignature;
	private TextField<String> txtSignature;
	private DateField lblDelegataireDateFormation;
	private DateField dfSignatureRecommandation;
	private TextField<String> txtSignatureRecommandation;
	private NumberField txtLimiteCommercial;
	private NumberField txtLimiteAvenants;
	private NumberField txtLimiteDevis;
	private NumberField txtLimiteEntreprise;
	private NumberField txtLimiteAssurance;
	private TextArea txtChamps;
	private Label lblErrorMessage;
	private LayoutContainer errorLayout;
	private TextField<String> txtZone;
	private TextField<String> txtOperations;
	
	private Button btnAjouter;
	private Button btnAnnuler;
	private Button btnModifier;
	private boolean isEditMode = false;
	private boolean isRenewMode = false;
	private boolean isSubMode = false;
	private boolean isTempMode = false;

	private ClientCollaborateurServiceAsync clientCollaborateurService = ClientCollaborateurServiceAsync.Util.getInstance();
	private ListStore<CollaborateurModel> lstDelegant = new ListStore<CollaborateurModel>();
	private ListStore<CollaborateurModel> lstDelegataire = new ListStore<CollaborateurModel>();
	
	private ClientDocumentMdlServiceAsync clientDocumentMdlService = ClientDocumentMdlServiceAsync.Util.getInstance();
	private ClientDemDomServiceAsync clientDemDomServiceAsync = ClientDemDomServiceAsync.Util.getInstance();
//	private ListStore<DocumentMdlModel> lstDocumentEnt = new ListStore<DocumentMdlModel>();

	private ClientDelegationStatusServiceAsync clientDelegationStatusService = ClientDelegationStatusServiceAsync.Util.getInstance();

	private ClientDelegationNatureServiceAsync clientDelegationNatureService = ClientDelegationNatureServiceAsync.Util.getInstance();
	private ListStore<DelegationNatureModel> lstDelegationNature = new ListStore<DelegationNatureModel>();

	private ClientDelegationServiceAsync clientDelegationService = ClientDelegationServiceAsync.Util.getInstance();
	private ClientPerimetreServiceAsync clientPerimetreService = ClientPerimetreServiceAsync.Util.getInstance();
	private ClientEntiteJuridiqueServiceAsync clientEntiteJuridiqueService = ClientEntiteJuridiqueServiceAsync.Util.getInstance();
	private ClientFieldRuleServiceAsync clientFieldRuleService = ClientFieldRuleServiceAsync.Util.getInstance();
	private ClientDelegationModelServiceAsync clientDelegationModelServiceAsync = ClientDelegationModelServiceAsync.Util.getInstance();
	private ClientDelegationTypeServiceAsync clientDelegationTypeService = ClientDelegationTypeServiceAsync.Util.getInstance();
	
	private DelegationModel delegationModel;
	private DelegationModel delegationModelParent;

	private EntiteModel entiteModel = new EntiteModel();
	private PerimetreModel perimetreModel = new PerimetreModel();

	private EntiteJuridiqueModel entiteJuridiqueModel = new EntiteJuridiqueModel();
	
	//store list of documents of delegation
	private ListStore<DocumentMdlModel> documentMdlModels;
	private Grid<DocumentMdlModel> documentGrid;

	private ColumnModel documentColumnModel;
	
	private DelegationNatureModel originalNature;
	private ContentPanel documentView;
	private ListStore<DelegationDelegataireModel> lstDelegataires = new ListStore<DelegationDelegataireModel>();
	private Label viewTitle;
	private CheckBoxListView<DelegationDelegataireModel> view;
	
	public NewDelegationFormPanel(SimpleEventBus bus) {
		this.bus = bus;		
		// setup field set
		this.setHeading(messages.delegationformheading());
		this.setFrame(true);
		this.setCollapsible(false);
		this.setLayout(new FlowLayout());
		this.setScrollMode(Scroll.AUTO);
		
		this.addBackLink();
		LayoutContainer lcLine0 = new LayoutContainer();
		lcLine0.setSize(WIDTH, HEIGHT);
		lcLine0.setLayout(new ColumnLayout());
		lcLine0.add(new HTML("<hr width='680px'/>"));
		this.add(lcLine0);
		this.initButtons();
		
		this.addErrorLabel();

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
		this.initFieldSets();
		
		this.bus.addHandler(DelegationEvent.getType(), new DelegationEventHandler() {
			@Override
			public void onLoadAction(final DelegationEvent event) {
				if (event.getMode() == DelegationEvent.MODE_IS_UPDATED_DOCUMENT) {
					return;
				}				
				resetForm();				
				if (documentMdlModels != null) {
					documentMdlModels.removeAll();
				}
				
				documentColumnModel.setHidden(1, true);
				lblDelegationPrincipale.setVisible(false);
				
				enabledFields(true);
				
				switch (event.getMode()) {
				case DelegationEvent.MODE_IS_NEW:
					newMode(event);
					break;
				case DelegationEvent.MODE_IS_EDIT:
					editMode(event);
					break;
				case DelegationEvent.MODE_IS_PRINT_DOCUMENT:
					enabledFields(false);
					editMode(event);
					break;
				case DelegationEvent.MODE_IS_CREATE_SUB_DELEGATION:							
					clientDelegationService.findById(event.getDelegationId(), new AsyncCallbackWithErrorResolution<DelegationModel>() {
						public void onSuccess(DelegationModel arg0) {
							final DelegationModel parentDelegationModel = arg0; 
							clientDelegationTypeService.getSubType(new AsyncCallbackWithErrorResolution<DelegationTypeModel>() {
								public void onSuccess(DelegationTypeModel arg0) {
									newModeSub(parentDelegationModel.getPerimeter().getEntite(),parentDelegationModel.getPerimeter(), parentDelegationModel.getDelegataire(), arg0);
									delegationModel.setParent(parentDelegationModel);		
									
									// if delegation type is sub-deleagtion, show principal
									if (parentDelegationModel != null) {
										lblDelegationPrincipale.setText(parentDelegationModel.getDelegationNature().getName());
										lblDelegationPrincipale.setRawValue(parentDelegationModel.getId().toString());
										lblDelegationPrincipale.setVisible(true);
										cpDescription.setVisible(true);
									}
								};
							});
						};
					});
					break;
				case DelegationEvent.MODE_IS_CREATE_TEM_DELEGATION:
					clientDelegationService.findById(event.getDelegationId(), new AsyncCallbackWithErrorResolution<DelegationModel>() {
						public void onSuccess(DelegationModel arg0) {
							isTempMode = true;
							delegationModel = arg0;
							
							clientDelegationTypeService.getTemporaryType(new AsyncCallbackWithErrorResolution<DelegationTypeModel>() {
								public void onSuccess(DelegationTypeModel arg0) {
									delegationModel.setDelegationType(arg0);
									
									newModeTemp(event);
									DelegationModel parent = new DelegationModel();
									parent.setId(event.getDelegationId());
									delegationModel.setParent(parent);
									delegationModel.setId(null);
								};
							});
						};
					});
					break;
				case DelegationEvent.MODE_IS_RENEW_DELEGATION:
					clientDelegationService.findById(event.getDelegationId(), new AsyncCallbackWithErrorResolution<DelegationModel>() {
						public void onSuccess(DelegationModel model) {
							isRenewMode = true;							
							delegationModel = model;
							delegationModelParent = event.getDelegationModel();
							delegationModel.setParent(delegationModelParent);
							Date startDate = CalendarUtil.copyDate(delegationModelParent.getEndDate());
							CalendarUtil.addDaysToDate(startDate, 1);
							delegationModel.setStartDate(startDate);
							delegationModel.setId(null);
							delegationModel.setIsSigned(0);
							delegationModel.setDate1(null); //R12
							delegationModel.setDate2(null);
							delegationModel.setDate3(null);
							
							clientDelegationStatusService.findById(ConstantClient.DELEGATION_STATUS_IS_P, new AsyncCallbackWithErrorResolution<DelegationStatusModel>() {
								@Override
								public void onSuccess(DelegationStatusModel arg0) {
									delegationModel.setDelegationStatus(arg0);
									renewMode(event);
								}
							});
						};
					});
					break;
				case DelegationEvent.MODE_IS_REPLACE_DELEGANT_OR_DELEGATAIRE:
					if (!ConstantClient.ENTITE_ID_IS_ETDE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
						enabledFields(false);
						
						cbDelegant.setEnabled(true);
						cbDelegataire.setEnabled(true);
						dfDebut.setEnabled(true);
						
						delegantFieldSet.setEnabled(true);
						delegataireFieldSet.setEnabled(true);
						societeFieldSet.setEnabled(true);
						chantierFieldSet.setEnabled(true);
						dfDebut.setEnabled(true); //R12
						dfFin.setEnabled(true);//R12
						dfSignature.setEnabled(true);//R12
						dfSignatureProposition.setEnabled(true);//R12
						dfSignatureRecommandation.setEnabled(true);//R12
						txtSignature.setEnabled(true);//R12
						txtSignatureProposition.setEnabled(true);//R12
						txtSignatureRecommandation.setEnabled(true);//R12
						
						delegantFieldSet.getTitre().setEnabled(true);
						
						clientDelegationService.findById(event.getDelegationId(), new AsyncCallbackWithErrorResolution<DelegationModel>() {
							public void onSuccess(DelegationModel model) {
								isRenewMode = true;							
								delegationModel = model;
								delegationModelParent = event.getDelegationModel();
								delegationModel.setParent(delegationModelParent);
								delegationModel.setStartDate(null);//R12
								delegationModel.setId(null);
								delegationModel.setIsSigned(0);
								delegationModel.setStartDate(null);//R12
								delegationModel.setDate1(null);//R12
								delegationModel.setDate2(null);//R12
								delegationModel.setDate3(null);//R12
	//							delegationModel.setDelegant(null);//R12
								//delegationModel.setDelegataire(null);//R12
								delegationModel.setDelegantTitle(null);
								
								clientDelegationStatusService.findById(ConstantClient.DELEGATION_STATUS_IS_P, new AsyncCallbackWithErrorResolution<DelegationStatusModel>() {
									@Override
									public void onSuccess(DelegationStatusModel arg0) {
										delegationModel.setDelegationStatus(arg0);
										renewMode(event);
									}
								});
							};
						});
					} else {
						enabledFields(false);
						
						cbDelegant.setEnabled(true);
						cbDelegataire.setEnabled(true);
								
						delegantFieldSet.setEnabled(true);
						delegataireFieldSet.setEnabled(true);		
						
						delegantFieldSet.getTitre().setEnabled(true);
						clientDelegationService.findById(event.getDelegationId(), new AsyncCallbackWithErrorResolution<DelegationModel>() {
							public void onSuccess(DelegationModel model) {
								isRenewMode = true;							
								delegationModel = model;
								delegationModelParent = event.getDelegationModel();
								delegationModel.setParent(delegationModelParent);
								Date startDate = CalendarUtil.copyDate(delegationModelParent.getEndDate());
								CalendarUtil.addDaysToDate(startDate, 1);
								delegationModel.setStartDate(startDate);
								delegationModel.setId(null);
								delegationModel.setIsSigned(0);
								
								clientDelegationStatusService.findById(ConstantClient.DELEGATION_STATUS_IS_P, new AsyncCallbackWithErrorResolution<DelegationStatusModel>() {
									@Override
									public void onSuccess(DelegationStatusModel arg0) {
										delegationModel.setDelegationStatus(arg0);
										replaceDelegantDelegataireMode(event);
									}
								});
							};
						});
					}
					break;
				}
				
				// if perimeter is chantier, conjointe combobox must be showed
//				if (delegationModel != null 
//						&& ConstantClient.PERIMETER_TYPE_NAME_IS_CHANTIER.equals(delegationModel.getPerimeter().getType().getName())) {
				if (delegationModel != null 
						&& CommonUtils.isChantierType(delegationModel.getPerimeter().getType().getName())) {
					cbConjoin.setVisible(true);
				} else {
					cbConjoin.setVisible(false);
				}
			}
		});
		addDateLieuFieldHandlers();
	}
	
	@Override
	protected void onRender(Element target, int index) {
		super.onRender(target, index);
		GWT.log(this.getClass().getName() + ":onRender");
	}
	
	private void addDateLieuFieldHandlers() {
		dfSignature.addListener(Events.Blur, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				if (dfSignatureProposition.getValue() == null) {
					dfSignatureProposition.setValue(dfSignature.getValue());
				}
				if (dfSignatureRecommandation.getValue() == null) {
					dfSignatureRecommandation.setValue(dfSignature.getValue());
				}
			}
		});
		txtSignature.addListener(Events.Blur, new Listener<BaseEvent>() {
			public void handleEvent(BaseEvent be) {
				if (txtSignatureProposition.getValue() == null || txtSignatureProposition.getValue().isEmpty()) {
					txtSignatureProposition.setValue(txtSignature.getValue());
				}
				if (txtSignatureRecommandation.getValue() == null || txtSignatureRecommandation.getValue().isEmpty()) {
					txtSignatureRecommandation.setValue(txtSignature.getValue());
				}
			}
		});		
	}

	private void newMode(DelegationEvent event) {
		AppUtil.putInEditMode();
		isEditMode = false;
		documentColumnModel.setHidden(1, true);
		
		delegationModel = new DelegationModel();		
		entiteModel = event.getEntiteModel();
		perimetreModel = event.getPerimetreModel();
		perimetreModel.setEntite(entiteModel);
		delegationModel.setEntite(entiteModel);
		delegationModel.setDelegationType(event.getDelegationTypeModel());
		delegationModel.setPerimeter(perimetreModel);
		
		//default status set to P
		DelegationStatusModel status = new DelegationStatusModel();
		status.setId(ConstantClient.DELEGATION_STATUS_IS_P);
		delegationModel.setDelegationStatus(status);	
		delegationModel.setIsSigned(0);
		// add BYTP
		// set end date for BYEFE automatic at 31 May. ETDE not need end date		
//		if (ConstantClient.ENTITE_ID_IS_BYEFE.equals(entiteModel.getEntId())) {
		if (CommonUtils.belongsBYEFEGroup(entiteModel.getEntId())) {
			delegationModel.setEndDate(Helper.getBYEFEEndDate());
		} else if (ConstantClient.ENTITE_ID_IS_ETDE.equals(entiteModel.getEntId())) {
			delegationModel.setEndDate(null);
		}
		loadDataFields(delegationModel);
		loadData(false);
		documentView.setVisible(false);
	}
	
	private void newModeSub(EntiteModel entiteModel, PerimetreModel perimetreModel, CollaborateurModel delegataire, DelegationTypeModel delegationTypeModel){
		AppUtil.putInEditMode();
		documentColumnModel.setHidden(1, true);
		
		isSubMode = true;
		isEditMode = false;
		delegationModel = new DelegationModel();
		this.entiteModel = entiteModel;
		this.perimetreModel = perimetreModel;
		delegationModel.setDelegationType(delegationTypeModel);
		delegationModel.setPerimeter(this.perimetreModel);
		DelegationStatusModel status = new DelegationStatusModel();
		status.setId(ConstantClient.DELEGATION_STATUS_IS_P);
		delegationModel.setDelegationStatus(status);
		delegationModel.setIsSigned(0);
		if (lstDelegant.contains(delegataire)) {
			delegationModel.setDelegant(delegataire); //R13
		} else {
			lstDelegant.add(delegataire);
			delegationModel.setDelegant(delegataire); //R13
			//AppUtil.showWarning(messages.perimetredelegatairedelegantnotmap());			
		}
				
		// add BYTP
		// set end date for BYEFE automatic at 31 May. ETDE not need end date
//		if (ConstantClient.ENTITE_ID_IS_BYEFE.equals(entiteModel.getEntId())) {
		if (CommonUtils.belongsBYEFEGroup(entiteModel.getEntId())) {
			cbDelegant.setEnabled(false);
			delegationModel.setEndDate(Helper.getBYEFEEndDate());
		} else if (ConstantClient.ENTITE_ID_IS_ETDE.equals(entiteModel.getEntId())) {
			delegationModel.setEndDate(null);
		}
		loadDataFields(delegationModel);
		loadData(true);
		documentView.setVisible(false);
	}
	
	private void newModeTemp(DelegationEvent event) {
		AppUtil.putInEditMode();
		isEditMode = false;

		documentColumnModel.setHidden(1, true);
		entiteModel = event.getEntiteModel();
		
		perimetreModel = delegationModel.getPerimeter();
		if (perimetreModel.getEntite() == null) {
			perimetreModel.setEntite(entiteModel);
		}				
		if (entiteModel == null) {
			entiteModel = perimetreModel.getEntite();
		}
		
		// reset data for the new temporary delegation
		delegationModel.setIsSigned(0);
		clientDelegationStatusService.findById(ConstantClient.DELEGATION_STATUS_IS_P, new AsyncCallbackWithErrorResolution<DelegationStatusModel>() {
			@Override
			public void onSuccess(DelegationStatusModel arg0) {
				delegationModel.setDelegationStatus(arg0);
				if (arg0 != null) {
					cbStatus.setText(arg0.getName());
				}
			}
		});
		
		delegationModel.setDelegataire(null);
		delegationModel.setStartDate(null);
		delegationModel.setEndDate(null);
		
		// apply rules		
		applyRule2(delegationModel, delegationModel.getPerimeter().getType(), delegationModel.getDelegationNature(), null);
		
		// load data fields
		loadDataFields(delegationModel);
		loadData(false);
		
		if (delegationModel.getParent() != null) {
			// if delegation type is sub-deleagtion, show principal
			lblDelegationPrincipale.setText(delegationModel.getParent().getDelegationNature().getName());
			lblDelegationPrincipale.setRawValue(delegationModel.getParent().getId().toString());
			lblDelegationPrincipale.setVisible(true);
		}
		
		// show and hide field depend on Temporary Type
		enabledFields(false);
		cbDelegant.setEnabled(false);
		
		// show delegataire
		cbDelegataire.setVisible(true);
		cbDelegataire.setEnabled(true);
		// show date debut
		dfDebut.setVisible(true);
		dfDebut.setEnabled(true);
		// show date de fin
		dfFin.setVisible(true);
		dfFin.setEnabled(true);
		
		delegantFieldSet.setEnabled(true);
		delegataireFieldSet.setEnabled(true);
		societeFieldSet.setEnabled(true);
		chantierFieldSet.setEnabled(true);
		delegantFieldSet.getTitre().setEnabled(false);
		documentView.setVisible(false);
	}
	
	private void editMode(DelegationEvent event){
		AppUtil.putInEditMode();
		documentView.setVisible(true);
		documentColumnModel.setHidden(1, false);
		
		entiteModel = event.getEntiteModel();
		clientDelegationService.findById(event.getDelegationId(), new AsyncCallbackWithErrorResolution<DelegationModel>() {
			@Override
			public void onSuccess(DelegationModel arg0) {
				isEditMode = true;
				delegationModel = arg0;
				originalNature = delegationModel.getDelegationNature();
				
				perimetreModel = delegationModel.getPerimeter();
				if (perimetreModel.getEntite() == null) {
					perimetreModel.setEntite(entiteModel);
				}				
				if (entiteModel == null) {
					entiteModel = perimetreModel.getEntite();
				}
				//tdo
				applyRule2(delegationModel, delegationModel.getPerimeter().getType(), delegationModel.getDelegationNature(), null);
				
				//applyRule(delegationModel.getDelegationNature().getId());
				
				loadDataFields(delegationModel);
				loadData(false);
				
				if ((arg0.getParent() != null) && (delegationModel.getDelegationType() != null)
						&& (delegationModel.getDelegationType().getId().intValue() != ConstantClient.DELEGATION_TYPE_IS_PRINCIPAL)) {
					// if delegation type is sub-deleagtion, show principal
					lblDelegationPrincipale.setText(arg0.getParent().getDelegationNature().getName());
					lblDelegationPrincipale.setRawValue(arg0.getParent().getId().toString());
					lblDelegationPrincipale.setVisible(true);
				}
				if (delegationModel.getIsSigned() > 0) {
					cbNature.setEditable(false);
				}
			}
		});
	}
	
	private void renewMode(DelegationEvent event){
		AppUtil.putInEditMode();
		documentView.setVisible(true);
		documentColumnModel.setHidden(1, false);

		entiteModel = event.getEntiteModel();
		originalNature = delegationModel.getDelegationNature();
		perimetreModel = delegationModel.getPerimeter();
		
		if (perimetreModel.getEntite() == null) {
			perimetreModel.setEntite(entiteModel);
		}				
		if (entiteModel == null) {
			entiteModel = perimetreModel.getEntite();
		}
		applyRule2(delegationModel, delegationModel.getPerimeter().getType(), delegationModel.getDelegationNature(), null);
		
		loadDataFields(delegationModel);
		loadData(false);
	}
	private void  replaceDelegantDelegataireMode(DelegationEvent event) {
		renewMode(event);
		isRenewMode = true;		
	}
	
	private void loadDataFields(DelegationModel delegationModel) {
//		if (delegationModel.getIsSubdelegable() != null) {
//			cbIsSubDelegable.setValue(delegationModel.getIsSubdelegable() > 0);				
//		}
		if (delegationModel.getStartDate() != null) {
			dfDebut.setValue(delegationModel.getStartDate());
		}
		if (delegationModel.getEndDate() != null) {
			dfFin.setValue(delegationModel.getEndDate());
		}

		if (delegationModel.getDate2() != null) {
			dfSignature.setValue(delegationModel.getDate2());
		}
		if (delegationModel.getPlace2() != null) {
			txtSignature.setValue(delegationModel.getPlace2());
		}

		if (delegationModel.getDate1() != null) {
			dfSignatureProposition.setValue(delegationModel.getDate1());
		}
		if (delegationModel.getPlace1() != null) {
			txtSignatureProposition.setValue(delegationModel.getPlace1());
		}

		if (delegationModel.getDate3() != null) {
			dfSignatureRecommandation.setValue(delegationModel.getDate3());
		}
		if (delegationModel.getPlace3() != null) {
			txtSignatureRecommandation.setValue(delegationModel.getPlace3());
		}

		if (delegationModel.getAmount1() != null) {
			txtLimiteCommercial.setValue(delegationModel.getAmount1());
		}
		if (delegationModel.getAmount2() != null) {
			txtLimiteAvenants.setValue(delegationModel.getAmount2());
		}
		if (delegationModel.getAmount3() != null) {
			txtLimiteDevis.setValue(delegationModel.getAmount3());
		}
		if (delegationModel.getAmount4() != null) {
			txtLimiteEntreprise.setValue(delegationModel.getAmount4());
		}
		if (delegationModel.getAmount5() != null) {
			txtLimiteAssurance.setValue(delegationModel.getAmount5());
		}
		if (delegationModel.getComment1() != null) {
			txtChamps.setValue(delegationModel.getComment1());
		}
		if (delegationModel.getDescription() != null) {
			heDescription.setValue(delegationModel.getDescription());
		}
		if (delegationModel.getZone() != null) {
			txtZone.setValue(delegationModel.getZone());
		}
		if (delegationModel.getOperations() != null) {
			txtOperations.setValue(delegationModel.getOperations());
		}
		
		cbConjoin.setSimpleValue((delegationModel.getDelegationConjointe() != null && delegationModel
				.getDelegationConjointe() == 1) ? messages.commonOui() : messages.commonNon()); // R13
	}
	
	private void addBackLink() {
//		LayoutContainer backLink = new LayoutContainer();
//		backLink.setSize(WIDTH, HEIGHT);
		Label lblBack = new Label(messages.commonRetoursalaLstedesdelegations());
		
		lblBack.setStyleName("x-link-item");
//		backLink.setStyleAttribute("margin-bottom", "20px");
//		backLink.add(lblBack);
		
		lblBack.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				if (!AppUtil.checkToShowWarningInEditMode() ) {
					resetForm();
					documentMdlModels.removeAll();
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GRID_DELEGATION_PANEL);
					bus.fireEvent(contentEvent);
				}
			}			
		});
		
		//this.add(backLink);
				
		HorizontalPanel hpButton = new HorizontalPanel();
		hpButton.setHorizontalAlign(HorizontalAlignment.LEFT);
		hpButton.setTableWidth(""+WIDTH);
		hpButton.add(lblBack );
		LayoutContainer lb = new LayoutContainer();
		lb.setWidth("200");
		hpButton.add(lb);
		// add button
		btnAnnuler = new Button(messages.delegationformannuler());
		hpButton.add(btnAnnuler);

		// remove button
		btnModifier = new Button(messages.commonValiderButton());
		hpButton.add(btnModifier);

		this.add(hpButton, new FlowData(10, 0, 0, 0));
	}
	
	private void addErrorLabel() {
		errorLayout = new LayoutContainer();
		errorLayout.setHeight(30);			
						
		lblErrorMessage = new Label("");
		lblErrorMessage.setStyleName("errorMessage");
		errorLayout.add(lblErrorMessage);
		this.add(errorLayout);
		errorLayout.setVisible(false);
	}
	
	private void showErrorLabel(Boolean isShow, String message) {
		lblErrorMessage.setText(message);
		errorLayout.setVisible(isShow);
	}
	
	/**
	 * load data from other table
	 */
	private void loadData(Boolean isSub) {
		lblType.setValue(delegationModel.getDelegationType().getName());
		clientPerimetreService.findById(perimetreModel.getPerId(), new AsyncCallbackWithErrorResolution<PerimetreModel>() {

			@Override
			public void onSuccess(PerimetreModel arg0) {
				perimetreModel = arg0; //tdo 24 Dec

				if (arg0 != null && arg0.getEntiteJuridique() != null) {
					entiteJuridiqueModel = arg0.getEntiteJuridique();
				} else {
					clientEntiteJuridiqueService.getDefaultByEntiteId(SessionServiceImpl.getInstance().getEntiteContext().getEntId(), new AsyncCallbackWithErrorResolution<EntiteJuridiqueModel>() {
						@Override
						public void onSuccess(EntiteJuridiqueModel arg0) {
							entiteJuridiqueModel = arg0;
						}
					});
				}
			}
		});
		
		clientDelegationStatusService.getAllDelegationStatuses(new AsyncCallbackWithErrorResolution<List<DelegationStatusModel>>() {
			@Override
			public void onSuccess(List<DelegationStatusModel> arg0) {
				if (isEditMode || isRenewMode || isTempMode) {
					if (delegationModel.getDelegationStatus() != null) {
						cbStatus.setText(delegationModel.getDelegationStatus().getName());
					}
				} else {
					for (DelegationStatusModel delegationStatusModel : arg0) {
						if (delegationStatusModel.getId() == 6) {
							cbStatus.setText(delegationStatusModel.getName());
							break;
						}
					}
				}
			}
		});
		
		String ptyId = perimetreModel.getType() == null ? null : perimetreModel.getType().getPtyId();
		
		clientDelegationNatureService.findNatureForNew(perimetreModel.getPerId(), entiteModel.getEntId(), ptyId,isSub,
				new AsyncCallbackWithErrorResolution<List<DelegationNatureModel>>() {
					@Override
					public void onSuccess(List<DelegationNatureModel> arg0) {
						lstDelegationNature.removeAll();
						lstDelegationNature.add(arg0);
						cbNature.setStore(lstDelegationNature);
						if (isEditMode || isRenewMode || isTempMode) {
							if (delegationModel.getDelegationNature() != null) {
								lstDelegationNature.add(delegationModel.getDelegationNature());
								cbNature.setValue(delegationModel.getDelegationNature());
							}
						}
					}
				});
		
		clientCollaborateurService.getAllDelegantsByPerimeter(perimetreModel.getPerId(),entiteModel.getEntId(), 
				new AsyncCallbackWithErrorResolution<List<CollaborateurModel>>() {
					@Override
					public void onSuccess(List<CollaborateurModel> arg0) {
						lstDelegant.removeAll();
						lstDelegant.add(arg0);
						for (CollaborateurModel cm : lstDelegant.getModels()) {
							cm.setFullname();
						}
						cbDelegant.setStore(lstDelegant);
						if (isEditMode || isRenewMode || isTempMode || isSubMode) { //R13
							if (delegationModel.getDelegant() != null) {								
								cbDelegant.setValue(delegationModel.getDelegant());
							}
						}
					}
				});

		clientCollaborateurService.getAllDelegatairesByPerimeter(perimetreModel.getPerId(),entiteModel.getEntId(), 
				new AsyncCallbackWithErrorResolution<List<CollaborateurModel>>() {
					@Override
					public void onSuccess(List<CollaborateurModel> arg0) {
						lstDelegataire.removeAll();
						lstDelegataire.add(arg0);
						for (CollaborateurModel cm : lstDelegataire.getModels()) {
							cm.setFullname();
						}
						
						cbDelegataire.setStore(lstDelegataire);
						if (isEditMode || isRenewMode || isTempMode) {
							if (delegationModel.getDelegataire() != null && !contains(lstDelegataire.getModels(), delegationModel.getDelegataire())) {
								delegationModel.getDelegataire().setFullname();
								lstDelegataire.add(delegationModel.getDelegataire());
							}
							if (delegationModel.getDelegataire() != null) {
								cbDelegataire.setValue(delegationModel.getDelegataire());
							}
						}
					}
				});
		
//		clientDocumentMdlService.getAllDocumentModelsByEntite(entiteModel, new AsyncCallbackWithErrorResolution<List<DocumentMdlModel>>(){
//			@Override
//			public void onSuccess(List<DocumentMdlModel> arg0) {
//				lstDocumentEnt.removeAll();
//				lstDocumentEnt.add(arg0);
//			}
//		});
		//getDelegataireInfo(delegationModel.getId()); //tdo 26 Dec
//		getDelegataireInfo(delegationModel); //tdo 26 Dec
}

	/**
	 * Create top form in new delegation field set
	 */
	private void initTopForm() {

		// setup top layout
		LayoutContainer lcTop = new LayoutContainer();
		lcTop.setSize(WIDTH, HEIGHT);
		lcTop.setLayout(new ColumnLayout());

		// setup sub layout for first field
		LayoutContainer lcSubTop = new LayoutContainer();
		lcSubTop.setStyleAttribute("paddingRight", "10px");
		FormLayout flSubTop = new FormLayout();
		flSubTop.setLabelWidth(120);
		flSubTop.setLabelAlign(LabelAlign.LEFT);
		lcSubTop.setLayout(flSubTop);

		lblType = new LabelField();
		lblType.setFieldLabel(messages.delegationformtypedelegation());
		lcSubTop.add(lblType, formData);

		lcTop.add(lcSubTop, new ColumnData(0.5));

		// setup sub layout for second field
		lcSubTop = new LayoutContainer();
		flSubTop = new FormLayout();
		flSubTop.setLabelAlign(LabelAlign.LEFT);
		flSubTop.setLabelWidth(50);
		lcSubTop.setLayout(flSubTop);

		cbStatus = new LabelField();
		cbStatus.setLabelSeparator(":");
		cbStatus.setFieldLabel(messages.delegationformstatus());
		lcSubTop.add(cbStatus, formData);
		lcTop.add(lcSubTop, new ColumnData(0.5));
		
		lcSubTop = new LayoutContainer();
		flSubTop = new FormLayout();
		flSubTop.setLabelWidth(120);
		flSubTop.setLabelAlign(LabelAlign.LEFT);
		// flSubTop.setLabelWidth(300);
		lcSubTop.setLayout(flSubTop);
		cbNature = new ComboBox<DelegationNatureModel>();
		
		cbNature.setFieldLabel(messages.nature());
		cbNature.setDisplayField(DelegationNatureModel.DELE_NATURE_NAME);
		cbNature.setStore(lstDelegationNature);
		cbNature.setTriggerAction(TriggerAction.ALL);
		cbNature.setEditable(false);
		cbNature.setAllowBlank(false);
		cbNature.setSimpleTemplate("<span title=\"{"+cbNature.getDisplayField()+"}\">{"+cbNature.getDisplayField()+"}</span>");		
		lcSubTop.add(cbNature, formData);
		lcTop.add(lcSubTop, new ColumnData(.85));
		
		lcSubTop = new LayoutContainer();
		flSubTop = new FormLayout();
		flSubTop.setLabelWidth(120);
		flSubTop.setLabelAlign(LabelAlign.LEFT);		
		lcSubTop.setLayout(flSubTop);
		
		// add to field set
		this.add(lcTop);
		
		addListeners();		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void addListeners() {
		cbNature.addListener(Events.OnMouseOver, new Listener() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (cbNature.getValue() != null) {
					cbNature.setToolTip(cbNature.getValue().getName());
				} else {
					cbNature.removeToolTip();
				}
			}
		});

		cbNature.addSelectionChangedListener(new SelectionChangedListener<DelegationNatureModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<DelegationNatureModel> se) {
				if (se.getSelectedItem() != null) {
					if (isCirmad(se.getSelectedItem().getName())) {
						txtZone.setVisible(true);
						txtOperations.setVisible(true);
					} else {
						txtZone.setVisible(false);
						txtOperations.setVisible(false);
					}
					if (!SessionServiceImpl.getInstance().getUserContext().getEntite().getEntId().equals(ConstantClient.ENTITE_ID_IS_ETDE)) {
						filterDelegants(se.getSelectedItem());
					}
					changeSocieteFieldSet(entiteJuridiqueModel);
					changeDelegantFieldSet(cbDelegant.getValue());
					changeDelegataireFieldSet(cbDelegataire.getValue());
					changeChantierFieldSet(perimetreModel);
					delegationModel.setDelegationNature(se.getSelectedItem());
					applyRule2(delegationModel, delegationModel.getPerimeter().getType(), delegationModel.getDelegationNature(), null);
				}
			}
		});
	}

	protected void filterDelegants(DelegationNatureModel selectedItem) {
		
		clientCollaborateurService.getDelegantsByNatureAndPerimetre(perimetreModel.getPerId(), perimetreModel.getType().getPtyId(),entiteModel.getEntId(), selectedItem.getId(),  
				new AsyncCallbackWithErrorResolution<List<CollaborateurModel>>() {
					@Override
					public void onSuccess(List<CollaborateurModel> arg0) {
						lstDelegant.removeAll();
						lstDelegant.add(arg0);
						if (delegationModel.getDelegant() != null && !contains(lstDelegant.getModels(), delegationModel.getDelegant())) {
							delegationModel.getDelegant().setFullname();
							lstDelegant.add(delegationModel.getDelegant());
							cbDelegant.setValue(delegationModel.getDelegant());
						}
						if (lstDelegant.getModels().isEmpty()) {
							AppUtil.showWarning(messages.perimetrelistdelegantempty());						
						}
					}
				});
	}
	private boolean contains(List<CollaborateurModel> arg0,
			CollaborateurModel c) {
		for (CollaborateurModel a: arg0) {							
			if (a.getId().equals(c.getId())) {
				return true;
			}						
		}
		return false;
	}

	/**
	 * Create information form in new delegation field set
	 */
	private void initInformationForm() {

		// setup information layout
		LayoutContainer lcInformation = new LayoutContainer();
		lcInformation.setSize(WIDTH, HEIGHT);
		lcInformation.setLayout(new ColumnLayout());

		// setup left layout
		LayoutContainer lcLeft = new LayoutContainer();
		lcLeft.setStyleAttribute("paddingRight", "10px");
		FormLayout flLeft = new FormLayout();
		flLeft.setLabelAlign(LabelAlign.TOP);
		lcLeft.setLayout(flLeft);
		
		// delegation principale
		lblDelegationPrincipale = new LabelField();
		lblDelegationPrincipale.setFieldLabel(messages.delegationformprincipale());
		lblDelegationPrincipale.setStyleName("x-link-item");
		lblDelegationPrincipale.addListener(Events.OnClick, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				final ContentEvent contentEvent = new ContentEvent();
				final DelegationEvent event = new DelegationEvent();
				event.setDelegationId(Integer.parseInt(lblDelegationPrincipale.getRawValue()));
				event.setMode(DelegationEvent.MODE_IS_VIEW);
				contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_DETAIL_DELEGATION_FORM);
				contentEvent.setEvent(event);
				bus.fireEvent(contentEvent);
			}
		});
		lcLeft.add(lblDelegationPrincipale, formData);

		// description
		cpDescription = new ContentPanel();
		cpDescription.setHeaderVisible(false);
		cpDescription.setLayout(new FlowLayout());
		
		heDescription = new HtmlEditor();
		heDescription.setFieldLabel(messages.delegationformdescription());
		heDescription.setHeight(108);
		
		cpDescription.add(heDescription);
		cpDescription.setVisible(false);
		lcLeft.add(cpDescription, formData);

		// delegant
		cbDelegant = new ComboBox<CollaborateurModel>();
		cbDelegant.setFieldLabel(messages.delegationformdelegant());
		cbDelegant.setDisplayField(CollaborateurModel.COLLA_FULL_NAME);
		cbDelegant.setStore(lstDelegant);
		cbDelegant.setTriggerAction(TriggerAction.ALL);
		cbDelegant.setEditable(false);
		cbDelegant.setAllowBlank(false);
		lcLeft.add(cbDelegant, formData);

		// delegataire
		//tdo
		
		addDelegataireGrid(lcLeft);
		
		//end tdo
				
		cbDelegataire = new ComboBox<CollaborateurModel>();
		cbDelegataire.setId("abc");
		cbDelegataire.setFieldLabel(messages.delegationformdelegataire());
		cbDelegataire.setDisplayField(CollaborateurModel.COLLA_FULL_NAME);
		cbDelegataire.setStore(lstDelegataire);
		cbDelegataire.setTriggerAction(TriggerAction.ALL);
		cbDelegataire.setEditable(false);
		cbDelegataire.setAllowBlank(false);
		lcLeft.add(cbDelegataire, formData);

		// conjointe
		cbConjoin = new SimpleComboBox<String>();
		cbConjoin.setFieldLabel(messages.delegationformconjoin());
		cbConjoin.add(messages.delegationformoui());
		cbConjoin.add(messages.delegationformnon());
		cbConjoin.setSimpleValue(messages.delegationformoui());
		cbConjoin.setTriggerAction(TriggerAction.ALL);
		cbConjoin.setEditable(false);
		cbConjoin.setVisible(false);
		lcLeft.add(cbConjoin, formData);
				
		txtChamps = new TextArea();
		txtChamps.setId("txtChamps");
		txtChamps.setMaxLength(255);
		txtChamps.setFieldLabel(ConstantClient.EMPTY);
		lcLeft.add(txtChamps, formData);

		txtLimiteCommercial = new NumberField();
		txtLimiteCommercial.setFormat(NumberFormat.getFormat(CommonUtils.NUMBER_FORMAT));
		txtLimiteCommercial.setId("txtLimiteCommercial");
		txtLimiteCommercial.setFieldLabel(ConstantClient.EMPTY);
		lcLeft.add(txtLimiteCommercial, formData);

		txtLimiteAvenants = new NumberField();
//		txtLimiteAvenants.setPropertyEditor(new NumberPropertyEditor("###,###.##"));
		txtLimiteAvenants.setFormat(NumberFormat.getFormat(CommonUtils.NUMBER_FORMAT));
		txtLimiteAvenants.setId("txtLimiteAvenants");
		txtLimiteAvenants.setFieldLabel(ConstantClient.EMPTY);
		lcLeft.add(txtLimiteAvenants, formData);

		txtLimiteDevis = new NumberField();
		txtLimiteDevis.setFormat(NumberFormat.getFormat(CommonUtils.NUMBER_FORMAT));
		txtLimiteDevis.setId("txtLimiteDevis");
		txtLimiteDevis.setFieldLabel(ConstantClient.EMPTY);
		lcLeft.add(txtLimiteDevis, formData);

		txtLimiteEntreprise = new NumberField();
		txtLimiteEntreprise.setFormat(NumberFormat.getFormat(CommonUtils.NUMBER_FORMAT));
		txtLimiteEntreprise.setId("txtLimiteEntreprise");
		txtLimiteEntreprise.setFieldLabel(ConstantClient.EMPTY);
		lcLeft.add(txtLimiteEntreprise, formData);

		txtLimiteAssurance = new NumberField();
		txtLimiteAssurance.setFormat(NumberFormat.getFormat(CommonUtils.NUMBER_FORMAT));
		txtLimiteAssurance.setId("txtLimiteAssurance");
		txtLimiteAssurance.setFieldLabel(ConstantClient.EMPTY);
		lcLeft.add(txtLimiteAssurance, formData);
		
		txtZone = new TextField<String>();
		txtZone.setVisible(false);		
		txtZone.setFieldLabel(messages.collaboraturezone());
		lcLeft.add(txtZone, formData);
		
		txtOperations = new TextField<String>();
		txtOperations.setVisible(false);		
		txtOperations.setFieldLabel(messages.collaboratureoperations());
		lcLeft.add(txtOperations, formData);
		
		btnAjouter = new Button();
		btnAjouter.setText(messages.delegationformajoutersignee());
		// lcLeft.add(btnAjouter);

		// setup right layout
		LayoutContainer lcRight = new LayoutContainer();
		FormLayout flRight = new FormLayout();
		flRight.setLabelAlign(LabelAlign.TOP);
		lcRight.setLayout(flRight);

		// date debut
		dfDebut = new DateField();
		dfDebut.setId("dfDebut");
		dfDebut.setFieldLabel(ConstantClient.EMPTY);
		dfDebut.setEditable(true);
		dfDebut.setAllowBlank(false);
		//dfDebut.setValue(new Date()); //R12
		dfDebut.setPropertyEditor(new DateTimePropertyEditor(ConstantClient.DATE_FORMAT));
		dfDebut.setFormatValue(true);		
		lcRight.add(dfDebut, formData);

		// date fin
		dfFin = new DateField();
		dfFin.setId("dfFin");
		dfFin.setFieldLabel(ConstantClient.EMPTY);
		dfFin.setEditable(true);
		dfFin.setFormatValue(true);
		dfFin.setAllowBlank(false);		
		dfFin.setPropertyEditor(new DateTimePropertyEditor(ConstantClient.DATE_FORMAT));
		lcRight.add(dfFin, formData);

		// date signature
		dfSignature = new DateField();
		dfSignature.setId("dfSignature");
		dfSignature.setFormatValue(true);
		dfSignature.setFieldLabel(ConstantClient.EMPTY);
		dfSignature.setPropertyEditor(new DateTimePropertyEditor(ConstantClient.DATE_FORMAT));
		lcRight.add(dfSignature, formData);

		txtSignature = new TextField<String>();
		txtSignature.setId("txtSignature");
		txtSignature.setFieldLabel(ConstantClient.EMPTY);
		lcRight.add(txtSignature, formData);
		
		lblDelegataireDateFormation= new DateField();
		lblDelegataireDateFormation.setId("lblDelegataireDateFormation");
		lblDelegataireDateFormation.setFieldLabel(ConstantClient.EMPTY);
		lblDelegataireDateFormation.setPropertyEditor(new DateTimePropertyEditor(ConstantClient.DATE_FORMAT));
		lcRight.add(lblDelegataireDateFormation, formData);

		dfSignatureProposition = new DateField();
		dfSignatureProposition.setFormatValue(true);
		dfSignatureProposition.setId("dfSignatureProposition");
		dfSignatureProposition.setFieldLabel(ConstantClient.EMPTY);
		dfSignatureProposition.setPropertyEditor(new DateTimePropertyEditor(ConstantClient.DATE_FORMAT));
		lcRight.add(dfSignatureProposition, formData);

		txtSignatureProposition = new TextField<String>();
		txtSignatureProposition.setId("txtSignatureProposition");
		txtSignatureProposition.setFieldLabel(ConstantClient.EMPTY);
		lcRight.add(txtSignatureProposition, formData);

		dfSignatureRecommandation = new DateField();
		dfSignatureRecommandation.setFormatValue(true);
		dfSignatureRecommandation.setId("dfSignatureRecommandation");
		dfSignatureRecommandation.setFieldLabel(ConstantClient.EMPTY);
		dfSignatureRecommandation.setPropertyEditor(new DateTimePropertyEditor(ConstantClient.DATE_FORMAT));
		lcRight.add(dfSignatureRecommandation, formData);

		txtSignatureRecommandation = new TextField<String>();
		txtSignatureRecommandation.setId("txtSignatureRecommandation");
		txtSignatureRecommandation
				.setFieldLabel(ConstantClient.EMPTY);
		lcRight.add(txtSignatureRecommandation, formData);

		lcInformation.add(lcLeft, new ColumnData(.5));
		lcInformation.add(lcRight, new ColumnData(.5));

		this.add(lcInformation);
		documentView = createDocumentView();
		this.add(documentView, formData);

		cbDelegant.addSelectionChangedListener(new SelectionChangedListener<CollaborateurModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<CollaborateurModel> se) {
				if (se.getSelectedItem() != null) {
					changeDelegantFieldSet(se.getSelectedItem());
				}
			}
		});

		cbDelegataire.addSelectionChangedListener(new SelectionChangedListener<CollaborateurModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<CollaborateurModel> se) {
				if (se.getSelectedItem() != null) {
					changeDelegataireFieldSet(se.getSelectedItem());
				}
			}
		});
	}

	private void addDelegataireGrid(LayoutContainer lcLeft) {	
		
		
		viewTitle = new Label(messages.delegationformdelegataire() + ":");
		lcLeft.add(viewTitle, formData);
		
		view = new CheckBoxListView<DelegationDelegataireModel>();		
		view.setHeight(80);		
		view.setStore(lstDelegataires);
		//view.setDisplayProperty(ExternControllerModel.EXC_LASTNAME);
		view.setTemplate( createTemplate());
		
		view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		lcLeft.add(view, formData);
//		lcLeft.add(delegatairesGrid, formData);
	}
	private String createTemplate() {
		 String spacing = GXT.isIE && !GXT.isStrict ? "0" : "3";
	      String template = "<tpl for=\".\"><div class='x-view-item x-view-item-check'><table cellspacing='"
	          + spacing  + "' cellpadding=0><tr><td><input class=\"x-view-item-checkbox\" type=\"checkbox\" /></td><td><td>{colName}</td></tr></table></div></tpl>";
	      return  template; 
	}
	private void displaySingleOrMultiDelegataire(Integer group, final DelegationModel del) {
		if (SessionServiceImpl.getInstance().getEntiteContext().getEntId().equals(ConstantClient.ENTITE_ID_IS_ETDE)) {
			cbDelegataire.setVisible(true);
			cbDelegataire.setAllowBlank(false);
			viewTitle.setVisible(false);
			view.setVisible(false);	
		} else {
			clientDelegationModelServiceAsync.getHasMutiDelegataire(group, new AsyncCallbackWithErrorResolution<Boolean>() {
				@Override
				public void onSuccess(Boolean arg0) {
					if (arg0 ) {
						cbDelegataire.setVisible(false);
						cbDelegataire.setAllowBlank(true);
						viewTitle.setVisible(true);
						view.setVisible(true);
						loadDelegataireInfo(del);
					} else {
						cbDelegataire.setVisible(true);
						cbDelegataire.setAllowBlank(false);
						viewTitle.setVisible(false);
						view.setVisible(false);	
					}
				}
			});
		}
	}
	
	public void loadDelegataireInfo(DelegationModel del) {
		clientDelegationService.findDelegataires(del.getId(), perimetreModel.getPerId(), entiteModel.getEntId(),
				new AsyncCallbackWithErrorResolution<List<DelegationDelegataireModel>>() {
					@Override
					public void onSuccess(List<DelegationDelegataireModel> arg0) {
						lstDelegataires.removeAll();
						if (arg0 != null) {
							lstDelegataires.add(arg0);
						}

						// moi 27 Dec tdo - chua save delegataire
						// @TODO : save delegationdelegataire to table in db + display in grid + genernate info of delegataire list in document
						for (int i = 0; i < lstDelegataires.getCount(); i++) {
							if (lstDelegataires.getAt(i).getDelId() != null && lstDelegataires.getAt(i).getDelId() > 0) {
								view.setChecked(lstDelegataires.getAt(i), true);
							}

						}
					}
				});
	}

	private ContentPanel createDocumentView() {
		// setup grid for document view
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		GridCellRenderer<DocumentMdlModel> documentRender = new GridCellRenderer<DocumentMdlModel>() {
			
			@Override
			public Object render(final DocumentMdlModel model, String property,
					com.extjs.gxt.ui.client.widget.grid.ColumnData config,
					int rowIndex, int colIndex,
					ListStore<DocumentMdlModel> store,
					Grid<DocumentMdlModel> grid) {
				final Label label = new Label();
				if (delegationModel != null && delegationModel.getId() != null) {
					label.setStyleName("x-link-item");
				}
				int delegationType = delegationModel.getDelegationType().getId();
				if (delegationType == ConstantClient.DELEGATION_TYPE_IS_TEMPORAIRE) {
					label.setText(model.getName() + " " + messages.commontemporary());
				} else {
					label.setText(model.getName());
				}
				
				if (delegationModel != null && delegationModel.getId() != null) {
					label.addClickHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent arg0) {
							String reportUrl = GWT.getHostPageBaseURL() + ".printDocumentServiceServlet";
							List<NameValuePair> values = new ArrayList<NameValuePair>();
							values.add(new NameValuePair("domId", model.getId().toString()));
							if (delegationModel != null && delegationModel.getId() != null) {
								values.add(new NameValuePair("delId", delegationModel.getId().toString()));
							}
							ReportUtil.showReport(reportUrl, values.toArray(new NameValuePair[0]));
						}
					});
				}
				
				return label;
			}
		};

		// Column documents
		ColumnConfig column = new ColumnConfig();
		column.setId("documentMdl.name");
		column.setHeader(messages.delegationformlesdocuments());
		column.setWidth(300);
		column.setRowHeader(true);
		column.setRenderer(documentRender);
		column.setSortable(false);
		configs.add(column);
		
		ColumnConfig signed = new ColumnConfig("signed", "Signed", 200);
		
		GridCellRenderer<DocumentMdlModel> signedRender = new GridCellRenderer<DocumentMdlModel>() {
			
			@Override
			public Object render(final DocumentMdlModel model, String property,
					com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex,
					ListStore<DocumentMdlModel> store, Grid<DocumentMdlModel> grid) {
				final com.google.gwt.user.client.ui.Label lbl = new com.google.gwt.user.client.ui.Label();
				lbl.setStyleName("x-link-item");
				
				if (delegationModel != null && delegationModel.getId() != null) {
					clientDocumentMdlService.getDocumentsByDelegation(delegationModel.getId(), new AsyncCallbackWithErrorResolution<List<DomDelModel>>() {
						@Override
						public void onSuccess(List<DomDelModel> arg0) {
							if (arg0.size() == 0) {
							} else {
								for (DomDelModel domDelModel : arg0) {
									if (domDelModel.getDocumentMdl().getId() == model.getId()) {
										if (domDelModel.getSignedFilename() != null) {
											lbl.setText(domDelModel.getSignedFilename());
											lbl.setTitle(domDelModel.getSignedFilename());
										} else {
											lbl.setText(domDelModel.getHemeraLien());
											lbl.setTitle(domDelModel.getHemeraLien());
										}
									}
								}
							}
						}
					});
					
					lbl.addClickHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent arg0) {
							if (lbl.getText() != null && lbl.getText().startsWith("http://")) {
								com.google.gwt.user.client.Window.open(lbl.getText(), "Signed Document",					    		 
							             "menubar=no," + 
							             "location=no," + 
							             "resizable=no," + 
							             "scrollbars=yes," + 
							             "status=no");
							} else {
								String reportUrl = GWT.getHostPageBaseURL() + ".printSignedDocumentServiceServlet";
								List<NameValuePair> values = new ArrayList<NameValuePair>();
								values.add(new NameValuePair("fileName", lbl.getText()));
								values.add(new NameValuePair("delId", delegationModel.getId().toString()));
								ReportUtil.showReport(reportUrl, values.toArray(new NameValuePair[0]));
							}
						}
					});
				}
				return lbl;
			}
		};
		signed.setRenderer(signedRender);
		configs.add(signed);

		// setup column model
		documentColumnModel = new ColumnModel(configs);

		// Content panel with header
		ContentPanel cp = new ContentPanel();
		cp.setBodyBorder(true);
		cp.setHeaderVisible(false);
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new FormLayout());
		cp.setHeight(120);
		cp.setWidth(680);
		cp.setStyleAttribute("paddingTop", "10px");
		

		documentMdlModels = new ListStore<DocumentMdlModel>();
		documentGrid = new Grid<DocumentMdlModel>(documentMdlModels, documentColumnModel);
		documentGrid.setStyleAttribute("borderTop", "none");
		documentGrid.setHeight(100);
		documentGrid.setAutoExpandColumn("documentMdl.name");
		documentGrid.setBorders(false);
		documentGrid.setStripeRows(true);
		documentGrid.setColumnLines(true);
		documentGrid.setColumnReordering(true);
		
		documentGrid.getAriaSupport().setLabelledBy(cp.getId() + "-label");
		cp.add(documentGrid);
		
		return cp;
	}

	/**
	 * init button
	 */
	private void initButtons() {

		// add button event listener for cancel
		btnAnnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				AppUtil.removeInEditMode();
				resetForm();
				documentMdlModels.removeAll();
				ContentEvent contentEvent = new ContentEvent();
				contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GRID_DELEGATION_PANEL);
				bus.fireEvent(contentEvent);				
			}
		});

		// add button event listener for create
		btnModifier.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
//				final List<DocumentMdlModel> models = documentMdlModels.getModels();
				if ((isEditMode) && (checkValidation())) {
					applyDataToDelegation();

					clientDelegationService.update(delegationModel, new AsyncCallbackWithErrorResolution<Boolean>() {
						@Override
						public void onSuccess(Boolean arg0) {
							resetForm();
							documentMdlModels.removeAll();
							ContentEvent contentEvent = new ContentEvent();
							contentEvent.setModel(delegationModel);
							contentEvent.setReload(true);
							contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GRID_DELEGATION_PANEL);
							bus.fireEvent(contentEvent);
							AppUtil.removeInEditMode();
						}

						@Override
						public void onFailure(Throwable caught) {
							 String details = caught.getMessage();
						     if (caught instanceof DelegationException) {						    	
						    	 details = ExceptionMessageHandler.getErrorMessage(((DelegationException) caught).getCode());						    	 
						     } 
						     showErrorLabel(true, details);
						     scrollToTop();
						}
					});
				} else if (checkValidation()) {
					applyDataToDelegation();
					
					if (isRenewMode ) {
						clientDelegationService.insertRenew(delegationModel, new AsyncCallbackWithErrorResolution<DelegationModel>() {
							@Override
							public void onSuccess(DelegationModel arg0) {
								delegationModel = arg0;
								resetForm();
								documentMdlModels.removeAll();
								ContentEvent contentEvent = new ContentEvent();
								contentEvent.setReload(true);
								contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GRID_DELEGATION_PANEL);
								bus.fireEvent(contentEvent);
								AppUtil.removeInEditMode();
							}
	
							@Override
							public void onFailure(Throwable caught) {
								 String details = caught.getMessage();
							     if (caught instanceof DelegationException) {						    	
							    	 details = ExceptionMessageHandler.getErrorMessage(((DelegationException) caught).getCode());						    	 
							     } 
							     showErrorLabel(true, details);
							     scrollToTop();
							}
						});
					} else {
						clientDelegationService.insert(delegationModel, new AsyncCallbackWithErrorResolution<DelegationModel>() {
							@Override
							public void onSuccess(DelegationModel arg0) {
								delegationModel = arg0;
								resetForm();
								documentMdlModels.removeAll();
								ContentEvent contentEvent = new ContentEvent();
								contentEvent.setReload(true);
								contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GRID_DELEGATION_PANEL);
								bus.fireEvent(contentEvent);
								AppUtil.removeInEditMode();
							}
	
							@Override
							public void onFailure(Throwable caught) {
								 String details = caught.getMessage();
							     if (caught instanceof DelegationException) {						    	
							    	 details = ExceptionMessageHandler.getErrorMessage(((DelegationException) caught).getCode());						    	 
							     } 
							     showErrorLabel(true, details);
							     scrollToTop();
							}
						});
					}
				}
			}
		});		
	}
	
	private void scrollToTop() {
		setVScrollPosition(0);
	}
	
	/**
	 * disable fields
	 */
	private void enabledFields(boolean disabled) {
		for (Field<?> field : getFields()) {
			field.setEnabled(disabled);
		}
		documentGrid.setEnabled(true);
	}

	/**
	 * check validate form
	 */
	private boolean checkValidation() {
		boolean valid = true;
		for (Field<?> f : getFields()) {			
			if ((f.isVisible()) && (!f.isValid(false))) {				
				valid = false;
				delegantFieldSet.expand();					
				break;
			}
		}
		if (view.isVisible()) {
			boolean notChecked = view.getChecked() == null || view.getChecked().isEmpty();			
			if (notChecked) {
				showErrorLabel(true, "Le champ délégataire est obligatoire");
				valid = false;
			}			
		}
		
		if (valid == false) {
			scrollToTop();
		}		
		
		return valid;
	}

	/**
	 * reset data in form
	 */
	private void resetForm() {
		this.reset();
		for (Field<?> field : getFields()) {
			field.setVisible(true);
		}
		isEditMode = false;
		isRenewMode = false;
		isTempMode = false;
		resetFieldSets(this.delegantFieldSet, this.delegataireFieldSet, this.societeFieldSet, this.chantierFieldSet);
		invisibleExtFields();
		showErrorLabel(false, "");
	}

	/**
	 * invisible all extension fields
	 */
	private void invisibleExtFields() {
		this.dfDebut.setVisible(false);
		this.dfFin.setVisible(false);
		this.txtChamps.setVisible(false);
		this.dfSignature.setVisible(false);
		this.lblDelegataireDateFormation.setVisible(false);
		this.txtSignature.setVisible(false);
		this.dfSignatureProposition.setVisible(false);
		this.txtSignatureProposition.setVisible(false);
		this.dfSignatureRecommandation.setVisible(false);
		this.txtSignatureRecommandation.setVisible(false);
		this.txtLimiteCommercial.setVisible(false);
		this.txtLimiteAvenants.setVisible(false);
		this.txtLimiteDevis.setVisible(false);
		this.txtLimiteEntreprise.setVisible(false);
		this.txtLimiteAssurance.setVisible(false);
	}

	/**
	 * apply data to delegation
	 */
	private void applyDataToDelegation() {		
		delegationModel.setEntite(entiteModel);

		// delegation nature
		if (cbNature.getValue() != null) {
			delegationModel.setDelegationNature(cbNature.getValue());
		}

		// start date
		if (dfDebut.isVisible()) {
			delegationModel.setStartDate(dfDebut.getValue());
		}

		// end date
		if (dfFin.isVisible()) {
			delegationModel.setEndDate(dfFin.getValue());
		}
		
		// delegant
		if (cbDelegant.getValue() != null) {
			CollaborateurModel delegant = cbDelegant.getValue();
			delegationModel.setDelegant(delegant);
			delegationModel.setDelegantFirstname(delegant.getFirstname());
			delegationModel.setDelegantLastname(delegant.getLastname());
			
			setMoreDelegantInformation(delegant);
		}
		
		//set conjointe
		if (cbConjoin.isVisible() && null != cbConjoin.getValue()) {	
			delegationModel.setDelegationConjointe(messages.commonOui().equals(cbConjoin.getSelectedText())?1:0);
		}
		
		//set entite juridique
		if (entiteJuridiqueModel != null) {
			delegationModel.setEntiteJuridique(entiteJuridiqueModel);				
		}
		
		// signature proposition date
		if (dfSignatureProposition.isVisible()) {
			delegationModel.setDate1(dfSignatureProposition.getValue());
		}
		
		// signature proposition date
		if (txtSignatureProposition.isVisible()) {
			delegationModel.setPlace1(txtSignatureProposition.getValue());
		}
		
		// signature delegation date
		if (dfSignature.isVisible()) {
			delegationModel.setDate2(dfSignature.getValue());
		}
		
		// signature delegation date
		if (txtSignature.isVisible()) {
			delegationModel.setPlace2(txtSignature.getValue());
		}
		
		// signature recommender date
		if (dfSignatureRecommandation.isVisible()) {
			delegationModel.setDate3(dfSignatureRecommandation.getValue());
		}
		
		// signature recommender date
		if (txtSignatureRecommandation.isVisible()) {
			delegationModel.setPlace3(txtSignatureRecommandation.getValue());
		}
		
		// Limite Commercial
		if (txtLimiteCommercial.isVisible() && txtLimiteCommercial.getValue() != null) {
			delegationModel.setAmount1(txtLimiteCommercial.getValue().floatValue());
		}
		
		// Limite Avenants
		if (txtLimiteAvenants.isVisible() && txtLimiteAvenants.getValue() != null) {
			delegationModel.setAmount2( txtLimiteAvenants.getValue().floatValue());
		}
		
		// Limite Devis
		if (txtLimiteDevis.isVisible() && txtLimiteDevis.getValue() != null) {
			delegationModel.setAmount3(txtLimiteDevis.getValue().floatValue());
		}
		
		// Limite Entreprise
		if (txtLimiteEntreprise.isVisible() && txtLimiteEntreprise.getValue() != null) {
			delegationModel.setAmount4(txtLimiteEntreprise.getValue().floatValue());
		}
		
		// Limite Assurance
		if (txtLimiteAssurance.isVisible() && txtLimiteAssurance.getValue() != null) {
			delegationModel.setAmount5(txtLimiteAssurance.getValue().floatValue());
		}
		
		// Champs
		if (txtChamps.isVisible()) {
			delegationModel.setComment1(txtChamps.getValue());
		}
		
//		// Description
		if (heDescription.getValue() != null) {
			delegationModel.setDescription(heDescription.getValue());	
		}
		
		if (txtZone.getValue() != null) {
			delegationModel.setZone(txtZone.getValue());
		}
		if (txtOperations.getValue() != null) {
			delegationModel.setOperations(txtOperations.getValue());
		}
		
		if (societeFieldSet != null && societeFieldSet.isVisible()) {
			if (societeFieldSet.getLblSocieteNom().isVisible()) {
				delegationModel.setEtjName(societeFieldSet.getLblSocieteNom().getText());
			}
			if (societeFieldSet.getLblSocieteStatusJuridique().isVisible()) {
				delegationModel.setEtjStatut(societeFieldSet.getLblSocieteStatusJuridique().getText());
			}
			if (societeFieldSet.getLblSocieteCapital().isVisible()) {
				delegationModel.setEtjCapital(societeFieldSet.getLblSocieteCapital().getText());
			}
			if (societeFieldSet.getLblSocieteAdresse().isVisible()) {
				delegationModel.setEtjAddress(societeFieldSet.getLblSocieteAdresse().getText());
			}
			if (societeFieldSet.getLblSocieteSiret().isVisible()) {
				delegationModel.setEtjRegistrationId(societeFieldSet.getLblSocieteSiret().getText());
			}
			if (societeFieldSet.getLblSocieteVille().isVisible()) {
				delegationModel.setEtjRegistrationAddress(societeFieldSet.getLblSocieteVille().getText());
			}
		}
		
		if (chantierFieldSet != null && chantierFieldSet.isVisible()) {
			if (chantierFieldSet.getLblChantierNom().isVisible()) {
				delegationModel.setPerChantierName(chantierFieldSet.getLblChantierNom().getText());
			}
			if (chantierFieldSet.getLblChantierVille().isVisible()) {
				delegationModel.setPerChantierCity(chantierFieldSet.getLblChantierVille().getText());
			}
			if (chantierFieldSet.getLblChantierNumeroProjet().isVisible()) {
				delegationModel.setPerChantierID(chantierFieldSet.getLblChantierNumeroProjet().getText());
			}
			if (chantierFieldSet.getLblChantierDateTravaux().isVisible()) {
				delegationModel.setPerChantierStartDate((chantierFieldSet.getLblChantierDateTravaux().getText() != null &&
						!"".equalsIgnoreCase(chantierFieldSet.getLblChantierDateTravaux().getText())) ? 
						DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).parse(chantierFieldSet.getLblChantierDateTravaux().getText())
						: null);
			}
			if (chantierFieldSet.getLblChantierDatePrevisionnelle().isVisible()) {
				delegationModel.setPerChantierPlannedEndDate((chantierFieldSet.getLblChantierDatePrevisionnelle().getText() != null && 
						!"".equals(chantierFieldSet.getLblChantierDatePrevisionnelle().getText())) ? 
						DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).parse(chantierFieldSet.getLblChantierDatePrevisionnelle().getText())
						: null);
			}
			if (chantierFieldSet.getLblChantierDateDefinitive().isVisible()) {
				delegationModel.setPerChantierEndDate((chantierFieldSet.getLblChantierDateDefinitive().getText() != null && 
						!"".equals(chantierFieldSet.getLblChantierDateDefinitive().getText())) ?
						DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).parse(chantierFieldSet.getLblChantierDateDefinitive().getText())
						: null);
			}
		}
		
		if( delegataireFieldSet != null && delegataireFieldSet.isVisible() ) {
			delegationModel.setDelegataireNiveauHierarchique(delegataireFieldSet.getLblDelegataireQualite().getText());
			delegationModel.setDelegataireStatut(delegataireFieldSet.getLblDelegataireQualite().getText());
			if( delegataireFieldSet.getLblDelegataireDateNaissance().isVisible() ) {
				delegationModel.setDelegataireDateNaissance((delegataireFieldSet.getLblDelegataireDateNaissance().getText() != null && !"".equals(delegataireFieldSet.getLblDelegataireDateNaissance().getText())) ? DateTimeFormat.getFormat(
						ConstantClient.DATE_FORMAT).parse(delegataireFieldSet.getLblDelegataireDateNaissance().getText()) : null);
			}
			if( delegataireFieldSet.getLblDelegataireLieuNaissance().isVisible() ) {
				delegationModel.setDelegataireLieuNaissance(delegataireFieldSet.getLblDelegataireLieuNaissance().getText());
			}
			if( delegataireFieldSet.getLblDelegataireNationalite().isVisible() ) {
				delegationModel.setDelegataireNationalite(delegataireFieldSet.getLblDelegataireNationalite().getText());
			}
			if( delegataireFieldSet.getLblDelegataireAdresse() != null && delegataireFieldSet.getLblDelegataireAdresse().getText() != null ) {
				delegationModel.setDelegataireAddress(delegataireFieldSet.getLblDelegataireAdresse().getText().replaceAll(
						"</br>", ", "));
			}

			if( delegataireFieldSet.getLblDelegataireQualite() != null ) {
				delegationModel.setDelegataireQualite(delegataireFieldSet.getLblDelegataireQualite().getText());
			}
		}
		
		if (delegantFieldSet !=  null && delegantFieldSet.isVisible()) {			
			delegationModel.setDelegantTitle(delegantFieldSet.getTitleOrQualite());
			if(delegantFieldSet.getLblDelegantQualite() != null) {
				delegationModel.setDelegantQualite(delegantFieldSet.getLblDelegantQualite().getText());
			}
			
			if (delegantFieldSet.getLblDelegantStatut() != null) {
				delegationModel.setDelegantStatut(delegantFieldSet.getLblDelegantStatut().getText());
			}
		}
		
		if (view.isVisible()) {
			List<DelegationDelegataireModel> lst = new ArrayList<DelegationDelegataireModel>();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
			for (DelegationDelegataireModel mdl : view.getChecked()) {				
				lst.add(mdl);
			}
			CollaborateurModel c = new CollaborateurModel();
			c.setId(lst.get(0).getColId());
			delegationModel.setDelegataire(c);
			delegationModel.setLstDelegataires(lst);						
		} else {
			// delegataire
			if (cbDelegataire.getValue() != null) {
				CollaborateurModel delegataire = cbDelegataire.getValue();
				
				delegationModel.setDelegataire(delegataire);
				delegationModel.setDelegataireFirstname(delegataire.getFirstname());
				delegationModel.setDelegataireLastname(delegataire.getLastname());
			}
		}
	}

	private void setMoreDelegantInformation(CollaborateurModel delegant) {
		if (!ConstantClient.ENTITE_ID_IS_ETDE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
			String group = delegant.getType() == null ? "" : delegant.getType().getGroup() == null ? "" : delegant.getType().getGroup().getName();
			if (ConstantClient.COLLABORATEUR_TYPE_MANDATAIRE_SOCIAL.equals(group)) {
				delegationModel.setDelegantDateConseil(delegant.getDateConseil());
				delegationModel.setDelegantStatutConseil(delegant.getStatutConseil());
				delegationModel.setDelegantDateEffet(delegant.getDateEffet());
			} else {
				delegationModel.setDateDelegation(delegant.getDateDelegation());
				CollaborateurModel mandataire = delegant.getDelegant();
				if (mandataire != null) {
					delegationModel.setDelegantQualite1(mandataire.getQualiteDelegant());
					delegationModel.setDelegantNom1(mandataire.getLastname());
					delegationModel.setDelegantPrenom1(mandataire.getFirstname());
				}
			}
		}
	}

	private void applyRule2(final DelegationModel delegationModel, PerimetreTypeModel perimetre, DelegationNatureModel nature, Integer collaborateType) {
		invisibleExtFields();
		// TODO Hard code apply Language
		LanguageModel lang = new LanguageModel();
		lang.setId(1);
		documentMdlModels.removeAll();
		clientDelegationModelServiceAsync.getGroup(lang, perimetre, nature, collaborateType, new AsyncCallbackWithErrorResolution<Integer>() {
			@Override
			public void onSuccess(Integer arg0) {
				if (arg0 != 0) {
					displaySingleOrMultiDelegataire(arg0, delegationModel);
					clientFieldRuleService.getRulesByDemGroup(arg0, new AsyncCallbackWithErrorResolution<List<FieldRuleModel>>() {
						@Override
						public void onSuccess(List<FieldRuleModel> arg0) {
							processFieldRules(delegationModel, arg0);
						}
					});
					
					changeDocumentTable(arg0);
				} else {
					final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

						public void handleEvent(MessageBoxEvent ce) {
							if (isEditMode || isRenewMode) {
								cbNature.setValue(originalNature);
								cbNature.select(originalNature);
							} else {
								cbNature.setValue(null);
							}
						}
					};

					MessageBox.alert(messages.commonalert(), messages.delegationformmsgnaturenotavailable(), l);
				}
			}
		});
	}

	@Override
	protected void processSpecificFields(final DelegationModel delegationModel) {
		cbNature.setValue(cbNature.getValue());
		cbDelegant.setValue(cbDelegant.getValue());
		cbDelegataire.setValue(cbDelegataire.getValue());

		if (isEditMode == false && isRenewMode == false) {
			dfDebut.setValue(new Date());
		}

		clientDelegationTypeService.getTemporaryType(new AsyncCallbackWithErrorResolution<DelegationTypeModel>() {
			public void onSuccess(DelegationTypeModel arg0) {
				if (arg0 != null && arg0.getId().intValue() == delegationModel.getDelegationType().getId().intValue()) {
					// show and hide field depend on Temporary Type
					enabledFields(false);
					cbDelegant.setEnabled(false);

					// show delegataire
					cbDelegataire.setVisible(true);
					cbDelegataire.setEnabled(true);
					// show date debut
					dfDebut.setVisible(true);
					dfDebut.setEnabled(true);
					// show date de fin
					dfFin.setVisible(true);
					dfFin.setEnabled(true);
					dfFin.setAllowBlank(false);
					lblDelegationPrincipale.setEnabled(true);
					delegantFieldSet.setEnabled(true);
					delegataireFieldSet.setEnabled(true);
					societeFieldSet.setEnabled(true);
					chantierFieldSet.setEnabled(true);
					delegantFieldSet.getTitre().setEnabled(false);
				}
			};
		});
	}

	/**
	 * apply data to dataset
	 */
	private void changeSocieteFieldSet(EntiteJuridiqueModel entiteJuridiqueModel) {
		if (cbNature.getValue() != null && entiteJuridiqueModel != null) {
			societeFieldSet.applyInformation(entiteJuridiqueModel);
			societeFieldSet.setVisible(true);
			societeFieldSet.collapse();			
		}
	}
	
	/**
	 * apply data to dataset
	 */
	private void changeChantierFieldSet(PerimetreModel perimetreModel) {
		if (cbNature.getValue() != null && perimetreModel != null && perimetreModel.getType()!= null && CommonUtils.isChantierType(perimetreModel.getType().getName())) {
			chantierFieldSet.applyInformation(perimetreModel);
			chantierFieldSet.setVisible(true);
			chantierFieldSet.collapse();			
		}		
	}	
	
	/**
	 * apply data to dataset
	 */
	private void changeDelegantFieldSet(CollaborateurModel collaborateurModel) {
		if (cbNature.getValue() != null && collaborateurModel != null) {
			delegantFieldSet.applyInformation(delegationModel, collaborateurModel);
			delegantFieldSet.setVisible(true);
			delegantFieldSet.collapse();
		}
	}

	/**
	 * apply data to dataset
	 */
	private void changeDelegataireFieldSet(CollaborateurModel collaborateurModel) {
		if ((cbNature.getValue() != null) && (collaborateurModel != null)) {
			delegataireFieldSet.applyInformation(collaborateurModel);
			delegataireFieldSet.setVisible(true);
			delegataireFieldSet.collapse();			
		}
	}
	
	private void changeDocumentTable(Integer group) {
		clientDemDomServiceAsync.getAllDemDomsByDemGroup(group, new AsyncCallbackWithErrorResolution<List<DemDomModel>>() {
			@Override
			public void onSuccess(List<DemDomModel> arg0) {
				documentMdlModels.removeAll();
				for (DemDomModel demDom : arg0) {					
					documentMdlModels.add(demDom.getDocumentMdl());
				}
			}
		});
	}
}