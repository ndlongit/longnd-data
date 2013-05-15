package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.Date;
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
import com.extjs.gxt.ui.client.widget.ComponentManager;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FileUploadField;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.dialog.UploadDocumentDialog;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationEvent;
import com.structis.vip.client.event.DelegationEventHandler;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.service.ClientDelegationDocumentServiceAsync;
import com.structis.vip.client.service.ClientDelegationModelServiceAsync;
import com.structis.vip.client.service.ClientDelegationServiceAsync;
import com.structis.vip.client.service.ClientDemDomServiceAsync;
import com.structis.vip.client.service.ClientDocumentMdlServiceAsync;
import com.structis.vip.client.service.ClientFieldRuleServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.client.util.NameValuePair;
import com.structis.vip.client.util.ReportUtil;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.DelegationDocumentModel;
import com.structis.vip.shared.model.DelegationModel;
import com.structis.vip.shared.model.DelegationNatureModel;
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
public class DetailDelegationFormPanel extends CommonDelegationPanel {

	private LabelField lblType;
	private LabelField cbDemandeur;
	private LabelField cbStatus;
	private LabelField cbNature;	
	private FieldSet fsDescription;
	private Label lblDescription;
	private LabelField lblDelegationPrincipale;	
	private LabelField cbDelegant;
	private LabelField cbDelegataire;
	private LabelField cbConjoin;
	private LabelField dfDebut;
	private LabelField dfFin;
	private LabelField dfSignatureProposition;
	private LabelField txtSignatureProposition;
	private LabelField dfSignature;
	private LabelField lblDelegataireDateFormation;
	private LabelField txtSignature;
	private LabelField dfSignatureRecommandation;
	private LabelField txtSignatureRecommandation;
	private LabelField txtLimiteCommercial;
	private LabelField txtLimiteAvenants;
	private LabelField txtLimiteDevis;
	private LabelField txtLimiteEntreprise;
	private LabelField txtLimiteAssurance;
	private LabelField txtChamps;
	private ColumnModel columnModel;
	private LabelField txtZone;
	private LabelField txtOperations;
	
	private Label lblErrorMessage;
	private LayoutContainer errorLayout;

	private Button btnAnnuler;
	private Button btnModifier;
	private Button btnAnnulerSignedDocument;
	
	private ClientDelegationServiceAsync clientDelegationService = ClientDelegationServiceAsync.Util.getInstance();
	private ClientDemDomServiceAsync clientDemDomServiceAsync = ClientDemDomServiceAsync.Util.getInstance();
	private ClientDelegationModelServiceAsync clientDelegationModelServiceAsync = ClientDelegationModelServiceAsync.Util.getInstance();
	private ClientFieldRuleServiceAsync clientFieldRuleService = ClientFieldRuleServiceAsync.Util.getInstance();
	private ClientDocumentMdlServiceAsync clientDocumentMdlService = ClientDocumentMdlServiceAsync.Util.getInstance();
	private ClientDelegationDocumentServiceAsync clientDelegationDocumentServiceAsync = ClientDelegationDocumentServiceAsync.Util.getInstance();
	
	private DelegationModel delegationModel;
	private EntiteModel entiteModel = new EntiteModel();

	private ListStore<DocumentMdlModel> documentMdlModels;
	
	@SuppressWarnings("unused")
	private PerimetreModel perimetreModel;
	private Grid<DocumentMdlModel> documentGrid;
	private ContentPanel cpDocumentGrid;
	
	@SuppressWarnings("unused")
	private DocumentMdlModel currentDocumentModel;
	private Integer group;
	private Button btnValider;

	private ListStore<DelegationDocumentModel> delegationDocumentModels;
	private Grid<DelegationDocumentModel> delegationDocumentGrid;
	private ContentPanel cpDelegationDocumentGrid;
	private ColumnModel columnModelOther;
	private Button btnAddDocument;
	
	private boolean isChanged = false;
	
	public DetailDelegationFormPanel(SimpleEventBus bus) {
		this.bus = bus;

		// setup field set
		this.setHeading(messages.delegationformheading());
		this.setFrame(true);
		this.setCollapsible(false);
		this.setLayout(new FlowLayout());
		this.setScrollMode(Scroll.AUTO);
		this.setAction(GWT.getHostPageBaseURL() + ".uploadSignedDocumentServiceServlet");
		this.setMethod(Method.POST);
		this.setEncoding(Encoding.MULTIPART);
		
		// add back link
		this.addBackLink();
		LayoutContainer lcLine0 = new LayoutContainer();
		lcLine0.setSize(WIDTH, HEIGHT);
		lcLine0.setLayout(new ColumnLayout());
		lcLine0.add(new HTML("<hr width='680px'/>"));
		this.add(lcLine0);
		
		this.addErrorLabel();
		
		// add view in field set
		this.initTopForm();
//		// add the seperator line
//		// setup top layout
		LayoutContainer lcLine = new LayoutContainer();
		lcLine.setSize(WIDTH, HEIGHT);
		lcLine.setLayout(new ColumnLayout());
		lcLine.add(new HTML("<hr width='680px'/>"));
		this.add(lcLine);
		// add view for information form
		this.initInformationForm();
		// add field set
		this.initFieldSets();
		// add buttons
		this.initButtons();
		
		bus.addHandler(DelegationEvent.getType(), new DelegationEventHandler() {
			@Override
			public void onLoadAction(DelegationEvent event) {
				if (event.getMode() == DelegationEvent.MODE_IS_UPDATED_DOCUMENT) {
					if (delegationModel != null) {
						changeOtherDocumentTable(delegationModel.getId());
						return;
					}
				}
				resetForm();
				
				columnModel.setHidden(2, true);
				
				btnValider.setVisible(false);
				btnAnnulerSignedDocument.setVisible(false);
				btnAnnuler.setVisible(true);
				
				if ((event.getIsModification() == null) || (!event.getIsModification())) {
					btnModifier.setVisible(false);
				} else {
					btnModifier.setVisible(true);
				}
				
				columnModelOther.setHidden(2, true);
				btnAddDocument.setVisible(false);
				
				if (event.getMode() == DelegationEvent.MODE_IS_VIEW
						|| event.getMode() == DelegationEvent.MODE_IS_ADD_DOCUMENT_SIGNEE
						|| event.getMode() == DelegationEvent.MODE_IS_ADD_DOCUMENT || event.getMode() == DelegationEvent.MODE_IS_PRINT_DOCUMENT) {
					clientDelegationService.findById(event.getDelegationId(), new AsyncCallback<DelegationModel>() {
						@Override
						public void onSuccess(DelegationModel arg0) {
							delegationModel = arg0;
							perimetreModel = delegationModel.getPerimeter();
							
							lblType.setText(delegationModel.getDelegationType().getName());
							
							if (delegationModel.getDelegationType().getId() == ConstantClient.DELEGATION_TYPE_IS_PRINCIPAL) {
								lblDelegationPrincipale.setVisible(false);
							} else {
								if (arg0.getParent() != null) {
									lblDelegationPrincipale.setText(arg0.getParent().getDelegationNature().getName());
									lblDelegationPrincipale.setRawValue(arg0.getParent().getId().toString());
									lblDelegationPrincipale.setVisible(true);
								}
								lblDescription.setText(delegationModel.getDescription());
								if (delegationModel.getZone() != null) {
									txtZone.setValue(delegationModel.getZone());
								}
								if (delegationModel.getOperations() != null) {
									txtOperations.setValue(delegationModel.getOperations());
								}
								
								if (delegationModel.getDelegationType().getId().intValue() == ConstantClient.DELEGATION_TYPE_IS_SOUS_DELEGATION) {
									dfDebut.setVisible(true);
									dfDebut.setEnabled(true);

									// show date de fin
									dfFin.setVisible(true);
									dfFin.setEnabled(true);
									lblDelegationPrincipale.setEnabled(true);
								}
							}
							
							cbNature.setText(delegationModel.getDelegationNature().getName());
							cbDemandeur.setText((delegationModel.getDemandeur() != null && delegationModel.getDemandeur().getFullname() != null && !"".equals(delegationModel.getDemandeur().getFullname())) ? 
									delegationModel.getDemandeur().getFullname() : "");
							
							if (delegationModel.getDelegationStatus().getId().intValue() != ConstantClient.DELEGATION_STATUS_IS_P) {
								btnModifier.setVisible(false);
							}
							
							cbStatus.setText(delegationModel.getDelegationStatus().getName());
							
							dfDebut.setText((delegationModel.getStartDate() != null) ? DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).format(delegationModel.getStartDate()) : "");
							dfFin.setText((delegationModel.getEndDate() != null) ? DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).format(delegationModel.getEndDate()) : "");
							
							cbDelegant.setText(delegationModel.getDelegant().getFullname());
							if (SessionServiceImpl.getInstance().getEntiteContext().getEntId().equals(ConstantClient.ENTITE_ID_IS_ETDE)) {
								cbDelegataire.setText(delegationModel.getDelegataire().getFullname());
							} else {
								clientDelegationService.getDelegataires(delegationModel.getId(), new AsyncCallback<String>(){
									@Override
									public void onSuccess(String arg0) {
										
										arg0 = arg0.replace("|", "<br>");							
										//html.setHTML(SafeHtmlUtils.fromString(arg0) );
										cbDelegataire.setText(arg0);
									}
	
									@Override
									public void onFailure(Throwable arg0) {
									}
								});
							}
							
							
							if (delegationModel.getDelegationConjointe()!= null) {
								if (delegationModel.getDelegationConjointe() == 1) {
									cbConjoin.setText(messages.commonOui());	
								} else if (delegationModel.getDelegationConjointe() == 0) {
									cbConjoin.setText(messages.commonNon());
								}
							}
							//if (ConstantClient.PERIMETER_TYPE_NAME_IS_CHANTIER.equals(delegationModel.getPerimeter().getType().getName())) {
							if (CommonUtils.isChantierType(delegationModel.getPerimeter().getType().getName())) {
								cbConjoin.setVisible(true);
							} else {
								cbConjoin.setVisible(false);
							}
							
							dfSignature.setText(delegationModel.getDate2()!= null ? DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).format(delegationModel.getDate2()):"");
							txtSignature.setText(delegationModel.getPlace2()!= null ? delegationModel.getPlace2().toString():"");
							
							lblDelegataireDateFormation.setText("");
							
							dfSignatureProposition.setText(delegationModel.getDate1()!= null ? DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).format(delegationModel.getDate1()):"");
							txtSignatureProposition.setText(delegationModel.getPlace1()!= null ? delegationModel.getPlace1().toString():"");
														
							dfSignatureRecommandation.setText(delegationModel.getDate3()!= null ? DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).format(delegationModel.getDate3()):"");
							txtSignatureRecommandation.setText(delegationModel.getPlace3()!= null ? delegationModel.getPlace3().toString():"");
							
							txtLimiteCommercial.setText(CommonUtils.formatNumber(delegationModel.getAmount1()));
							txtLimiteAvenants.setText(CommonUtils.formatNumber(delegationModel.getAmount2()));
							txtLimiteDevis.setText(CommonUtils.formatNumber(delegationModel.getAmount3()));
							txtLimiteEntreprise.setText(CommonUtils.formatNumber(delegationModel.getAmount4()));
							txtLimiteAssurance.setText(CommonUtils.formatNumber(delegationModel.getAmount5()));
							txtChamps.setText(delegationModel.getComment1()!= null? delegationModel.getComment1().toString():"");
							txtZone.setText(delegationModel.getZone());
							txtOperations.setText(delegationModel.getOperations());
							
							entiteModel = delegationModel.getPerimeter().getEntite();
							
							changeSocieteFieldSet(delegationModel.getEntiteJuridique());
							changeDelegantFieldSet(delegationModel.getDelegant());
							changeDelegataireFieldSet(delegationModel.getDelegataire());
							changeChantierFieldSet(delegationModel.getPerimeter());
							
							//applyRule(delegationModel.getDelegationNature().getId());
							changeOtherDocumentTable(delegationModel.getId());
							
							applyRule2(delegationModel, delegationModel.getPerimeter().getType(), delegationModel.getDelegationNature(), null);
						}
	
						@Override
						public void onFailure(Throwable arg0) {
						}
					});
				}
				
				if (event.getMode() == DelegationEvent.MODE_IS_PRINT_DOCUMENT) {
					btnModifier.setVisible(false);
				}
				
				if (event.getMode() == DelegationEvent.MODE_IS_ADD_DOCUMENT_SIGNEE) {					
					columnModel.setHidden(2, false);
					btnValider.setVisible(true);
					btnModifier.setVisible(false);
					btnAnnulerSignedDocument.setVisible(true);
					btnAnnuler.setVisible(false);
				}
				
				if (event.getMode() == DelegationEvent.MODE_IS_ADD_DOCUMENT) {					
					columnModelOther.setHidden(2, false);
					btnAddDocument.setVisible(true);
					btnModifier.setVisible(false);
					btnAnnuler.setVisible(false);
				}
				
				// @Lan (2012/03/01): Fixing bug #96
				if (event.getEntiteModel() != null && ConstantClient.ENTITE_ID_IS_ETDE.equals(event.getEntiteModel().getEntId())) {
					cbDemandeur.setVisible(false);
				} else {
					//cbDemandeur.setVisible(true);
					cbDemandeur.setVisible(false);
				}	
			}
		});
		
		addListener(Events.BeforeSubmit, new Listener<BaseEvent>() {
			@Override
			public void handleEvent(BaseEvent be) {
				showErrorLabel(false, "");	
				
				List<DocumentMdlModel> models = documentGrid.getStore().getModels();
				for (DocumentMdlModel documentMdlModel : models) {
					String signedFilename = documentMdlModel.getSignedFilename();
					if (signedFilename != null && !"".equals(signedFilename)) {
						FileUploadField component = (FileUploadField) ComponentManager.get().get(signedFilename);
						if (component != null) {
							String fileName = component.getValue();
							int lastDot = fileName.lastIndexOf(".");
							String extFile = fileName.substring(lastDot, fileName.length()).toLowerCase();
							if (!ConstantClient.PDF_EXTENSION_FILE.equals(extFile)) {
								documentGrid.getSelectionModel().select(documentMdlModel, false);
								showErrorLabel(true, "Délégation signée doit être un fichier pdf");
								be.setCancelled(true);
								break;
							}
						}
					}
				}
			}
		});
		
		addListener(Events.Submit, new Listener<FormEvent>() {
			@Override
			public void handleEvent(FormEvent be) {
				List<DocumentMdlModel> models = documentGrid.getStore().getModels();
				if (!models.isEmpty()) {
					List<DomDelModel> insertModels = new ArrayList<DomDelModel>();
					for (DocumentMdlModel documentMdlModel : models) {
						if (documentMdlModel.getSignedFilename() != null && !"".equals(documentMdlModel.getSignedFilename())) {
							DomDelModel model = new DomDelModel();
							model.setDelegation(delegationModel);
							model.setDocumentMdl(documentMdlModel);
							model.setSignedDate(new Date());
							model.setSignedFilename(documentMdlModel.getSignedFilename());
							
							insertModels.add(model);
						}
					}
					if (!insertModels.isEmpty()) {
							clientDocumentMdlService.createNewDocument(insertModels,
									new AsyncCallback<Boolean>() {
										@Override
										public void onSuccess(Boolean arg0) {
											changeDocumentTable(group);
											cpDocumentGrid.repaint();
											updateStatusAuto();
										}

										@Override
										public void onFailure(Throwable arg0) {
											Info.display(messages.commonerror(),
													messages.commonServererror());
										}
									});
					}
				}
			}			
		});
	}
	
	@Override
	protected void onRender(Element target, int index) {
		super.onRender(target, index);
		GWT.log(this.getClass().getName() + ":onRender");
	}
	
	private void updateStatusAuto() {
		clientDocumentMdlService.getDocumentsByDelegation(delegationModel.getId(), new AsyncCallback<List<DomDelModel>>() {
			@Override
			public void onSuccess(List<DomDelModel> arg0) {
				if (arg0.size() == documentMdlModels.getCount()) {
					clientDelegationService.updateStatusAuto(delegationModel, new AsyncCallback<Boolean>() {
						@Override
						public void onSuccess(Boolean arg0) {
							clientDelegationService.findById(delegationModel.getId(), new AsyncCallback<DelegationModel>() {
								@Override
								public void onSuccess(DelegationModel arg0) {
									delegationModel = arg0;
									cbStatus.setText(delegationModel.getDelegationStatus().getName());
									// true to refesh when back to list delegation
									isChanged = true;
								}
								
								@Override
								public void onFailure(Throwable arg0) {
								}
							});
						}
						
						@Override
						public void onFailure(Throwable arg0) {
						}
					});
				}
			}

			@Override
			public void onFailure(Throwable arg0) {
			}
		});
	}
	
	public void addBackLink() {
//		LayoutContainer backLink = new LayoutContainer();
//		backLink.setSize(WIDTH, HEIGHT);
		com.google.gwt.user.client.ui.Label lblBack = new com.google.gwt.user.client.ui.Label(messages.commonRetoursalaLstedesdelegations());
		lblBack.setStyleName("x-link-item");
//		backLink.setStyleAttribute("margin-bottom", "20px");
//		backLink.add(lblBack);
//		
		lblBack.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				if (!AppUtil.checkToShowWarningInEditMode() ) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GRID_DELEGATION_PANEL);
					if (isChanged) {
						contentEvent.setReload(true);
					}
					resetForm();
					documentMdlModels.removeAll();
					bus.fireEvent(contentEvent);
				}
			}
		});
//		HorizontalPanel hpButton = new HorizontalPanel();
//		hpButton.setHorizontalAlign(HorizontalAlignment.CENTER);
//		hpButton.setTableWidth("150");
//		
//		btnAnnuler = new Button(messages.delegationformannuler());
//		btnModifier = new Button(messages.delegationformmodifier());
//		
//		hpButton.add(btnAnnuler);
//		hpButton.add(btnModifier);
//		
//		this.add(hpButton, new FlowData(10, 0, 0, 548));
//		this.add(backLink);
		HorizontalPanel hpButton = new HorizontalPanel();
		hpButton.setHorizontalAlign(HorizontalAlignment.LEFT);
		hpButton.setTableWidth(""+WIDTH);
		hpButton.add(lblBack );		
		LayoutContainer lc = new LayoutContainer();
		lc.setWidth(200);
		hpButton.add(lc);
//		hpButton.add(lb2);
		// add button
		btnAnnuler = new Button(messages.delegationformannuler());
		hpButton.add(btnAnnuler);

		// remove button
		btnModifier = new Button(messages.delegationformmodifier());
		hpButton.add(btnModifier);

		this.add(hpButton, new FlowData(10, 0, 0, 0));
	}
	
	private void addErrorLabel() {
		errorLayout = new LayoutContainer();
		errorLayout.setHeight(30);			
						
		lblErrorMessage = new Label("Just Test");
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

		cbNature = new LabelField();
		cbNature.setFieldLabel(messages.nature());
		lcSubTop.add(cbNature, formData);

		lcTop.add(lcSubTop, new ColumnData(0.45));
		
//		cbIsDelegable = new LabelField();
//		cbIsDelegable.setFieldLabel(messages.delegationsousdelegable());
//		lcSubTop.add(cbIsDelegable, formData);

		lcTop.add(lcSubTop, new ColumnData(0.45));

		// setup sub layout for second field
		lcSubTop = new LayoutContainer();
		flSubTop = new FormLayout();
		flSubTop.setLabelAlign(LabelAlign.LEFT);
		// flSubTop.setLabelWidth(300);
		lcSubTop.setLayout(flSubTop);

		cbDemandeur = new LabelField();
		cbDemandeur.setFieldLabel(messages.delegationformdemandeur());
		lcSubTop.add(cbDemandeur, formData);

		cbStatus = new LabelField();
		cbStatus.setFieldLabel(messages.delegationformstatus());
		lcSubTop.add(cbStatus, formData);
		lcTop.add(lcSubTop, new ColumnData(0.55));

		// add to field set
		this.add(lcTop);

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
		flLeft.setLabelAlign(LabelAlign.LEFT);
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
		fsDescription = new FieldSet();
		fsDescription.setHeading(messages.delegationformdescription());
		lblDescription = new Label();
		fsDescription.add(lblDescription);
		fsDescription.setVisible(false);
		lcLeft.add(fsDescription, formData);

		// delegant
		cbDelegant = new LabelField();
		cbDelegant.setFieldLabel(messages.delegationformdelegant());
		lcLeft.add(cbDelegant, formData);

		// delegataire
		cbDelegataire = new LabelField();
		cbDelegataire.setFieldLabel(messages.delegationformdelegataire());
		lcLeft.add(cbDelegataire, formData);

		// conjointe
		cbConjoin = new LabelField();
		cbConjoin.setFieldLabel(messages.delegationformconjoin());
		cbConjoin.setVisible(false);
		lcLeft.add(cbConjoin, formData);

		txtChamps = new LabelField();
		txtChamps.setId("txtChamps");
		txtChamps.setFieldLabel(ConstantClient.EMPTY);
		lcLeft.add(txtChamps, formData);

		txtLimiteCommercial = new LabelField();
		txtLimiteCommercial.setId("txtLimiteCommercial");
		txtLimiteCommercial.setFieldLabel(ConstantClient.EMPTY);
		lcLeft.add(txtLimiteCommercial, formData);

		txtLimiteAvenants = new LabelField();
		txtLimiteAvenants.setId("txtLimiteAvenants");
		txtLimiteAvenants.setFieldLabel(ConstantClient.EMPTY);
		lcLeft.add(txtLimiteAvenants, formData);

		txtLimiteDevis = new LabelField();
		txtLimiteDevis.setId("txtLimiteDevis");
		txtLimiteDevis.setFieldLabel(ConstantClient.EMPTY);
		lcLeft.add(txtLimiteDevis, formData);

		txtLimiteEntreprise = new LabelField();
		txtLimiteEntreprise.setId("txtLimiteEntreprise");
		txtLimiteEntreprise.setFieldLabel(ConstantClient.EMPTY);
		lcLeft.add(txtLimiteEntreprise, formData);

		txtLimiteAssurance = new LabelField();
		txtLimiteAssurance.setId("txtLimiteAssurance");
		txtLimiteAssurance.setFieldLabel(ConstantClient.EMPTY);
		lcLeft.add(txtLimiteAssurance, formData);
		txtZone = new LabelField();
		txtZone.setVisible(false);		
		txtZone.setFieldLabel(messages.collaboraturezone());
		lcLeft.add(txtZone, formData);
		
		txtOperations = new LabelField();
		txtOperations.setVisible(false);		
		txtOperations.setFieldLabel(messages.collaboratureoperations());
		lcLeft.add(txtOperations, formData);
		
		// setup right layout
		LayoutContainer lcRight = new LayoutContainer();
		FormLayout flRight = new FormLayout();
		flRight.setLabelAlign(LabelAlign.LEFT);
		flRight.setLabelWidth(240);
		lcRight.setLayout(flRight);

		// date debut
		dfDebut = new LabelField();
		dfDebut.setId("dfDebut");
		dfDebut.setFieldLabel(ConstantClient.EMPTY);
		lcRight.add(dfDebut, formData);

		// date fin
		dfFin = new LabelField();
		dfFin.setId("dfFin");
		dfFin.setFieldLabel(ConstantClient.EMPTY);
		lcRight.add(dfFin, formData);

		// date signature
		dfSignature = new LabelField();
		dfSignature.setId("dfSignature");
		dfSignature.setFieldLabel(ConstantClient.EMPTY);
		lcRight.add(dfSignature, formData);

		txtSignature = new LabelField();
		txtSignature.setId("txtSignature");
		txtSignature.setFieldLabel(ConstantClient.EMPTY);
		lcRight.add(txtSignature, formData);
		
		lblDelegataireDateFormation = new LabelField();
		lblDelegataireDateFormation.setId("lblDelegataireDateFormation");
		lblDelegataireDateFormation.setFieldLabel(ConstantClient.EMPTY);
		lcRight.add(lblDelegataireDateFormation, formData);

		dfSignatureProposition = new LabelField();
		dfSignatureProposition.setId("dfSignatureProposition");
		dfSignatureProposition.setFieldLabel(ConstantClient.EMPTY);
		lcRight.add(dfSignatureProposition, formData);

		txtSignatureProposition = new LabelField();
		txtSignatureProposition.setId("txtSignatureProposition");
		txtSignatureProposition.setFieldLabel(ConstantClient.EMPTY);
		lcRight.add(txtSignatureProposition, formData);

		dfSignatureRecommandation = new LabelField();
		dfSignatureRecommandation.setId("dfSignatureRecommandation");
		dfSignatureRecommandation.setFieldLabel(ConstantClient.EMPTY);
		lcRight.add(dfSignatureRecommandation, formData);

		txtSignatureRecommandation = new LabelField();
		txtSignatureRecommandation.setId("txtSignatureRecommandation");
		txtSignatureRecommandation
				.setFieldLabel(ConstantClient.EMPTY);
		lcRight.add(txtSignatureRecommandation, formData);

		lcInformation.add(lcLeft, new ColumnData(.45));
		lcInformation.add(lcRight, new ColumnData(.55));

		this.add(lcInformation);
		this.add(createDocumentView(), formData);
		this.add(createOtherDocumentView(), formData);
	}
	
	/**
	 * create other document view
	 */
	private ContentPanel createOtherDocumentView() {
		// setup grid for document view
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		// Column documents
		ColumnConfig ccDescription = new ColumnConfig();
		ccDescription.setId(DelegationDocumentModel.ODD_DESCRIPTION);
		ccDescription.setHeader(messages.delegationdocumentdescription());
		ccDescription.setWidth(270);
		ccDescription.setRowHeader(true);
		ccDescription.setSortable(false);

		GridCellRenderer<DelegationDocumentModel> fileNameRender = new GridCellRenderer<DelegationDocumentModel>() {

			@Override
			public Object render(final DelegationDocumentModel model, String property,
					com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex,
					ListStore<DelegationDocumentModel> store, Grid<DelegationDocumentModel> grid) {
				final com.google.gwt.user.client.ui.Label label = new com.google.gwt.user.client.ui.Label();
				label.setStyleName("x-link-item");
				label.setText(model.getFileName());
				label.setTitle(model.getFileName());

				label.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						String reportUrl = GWT.getHostPageBaseURL() + ".printDelegationDocumentServiceServlet";
						List<NameValuePair> values = new ArrayList<NameValuePair>();
						values.add(new NameValuePair("fileName", model.getFileName()));
						ReportUtil.showReport(reportUrl, values.toArray(new NameValuePair[0]));
					}
				});

				return label;
			}
		};

		// Column documents
		ColumnConfig ccFileName = new ColumnConfig();
		ccFileName.setId(DelegationDocumentModel.ODD_FILENAME);
		ccFileName.setHeader(messages.delegationdocumentfile());
		ccFileName.setWidth(200);
		ccFileName.setRowHeader(true);
		ccFileName.setRenderer(fileNameRender);
		ccFileName.setSortable(false);

		GridCellRenderer<DelegationDocumentModel> actionRender = new GridCellRenderer<DelegationDocumentModel>() {

			@Override
			public Object render(final DelegationDocumentModel model, String property,
					com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex,
					ListStore<DelegationDocumentModel> store, final Grid<DelegationDocumentModel> grid) {
				final Button btnDelete = new Button(messages.commonSupprimer());
				btnDelete.addSelectionListener(new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						clientDelegationDocumentServiceAsync.delete(model, new AsyncCallback<Boolean>() {
							
							@Override
							public void onSuccess(Boolean result) {
								DelegationEvent event = new DelegationEvent();
								event.setMode(DelegationEvent.MODE_IS_UPDATED_DOCUMENT);
								bus.fireEvent(event);
								if (true == result) {
									Info.display(messages.commoninfo(), messages.delegationdocumentdeletemessage());
								}
							}
							
							@Override
							public void onFailure(Throwable caught) {
								Info.display(messages.commonerror(), messages.delegationdocumentdeletefailed());
							}
						});
					}
				});
				return btnDelete;
			}
		};

		ColumnConfig ccAction = new ColumnConfig("action", messages.delegationdocumentaction(), 200);
		ccAction.setRenderer(actionRender);

		configs.add(ccDescription);
		configs.add(ccFileName);
		configs.add(ccAction);

		// setup column model
		columnModelOther = new ColumnModel(configs);

		btnAddDocument = new Button(messages.delegationdocumentaddbutton());
		btnAddDocument.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				UploadDocumentDialog uploadDocumentDialog = new UploadDocumentDialog(bus, delegationModel);
				uploadDocumentDialog.show();
			}
		});

		// Content panel with header
		cpDelegationDocumentGrid = new ContentPanel();
		cpDelegationDocumentGrid.setBodyBorder(true);
		cpDelegationDocumentGrid.setHeaderVisible(false);
		cpDelegationDocumentGrid.setButtonAlign(HorizontalAlignment.CENTER);
		cpDelegationDocumentGrid.setLayout(new FitLayout());
		cpDelegationDocumentGrid.setSize(680, 180);
		cpDelegationDocumentGrid.setButtonAlign(HorizontalAlignment.RIGHT);
		cpDelegationDocumentGrid.setStyleAttribute("paddingTop", "10px");
		cpDelegationDocumentGrid.setStyleAttribute("paddingBottom", "10px");

		// Grid
		delegationDocumentModels = new ListStore<DelegationDocumentModel>();

		delegationDocumentGrid = new Grid<DelegationDocumentModel>(delegationDocumentModels, columnModelOther);
		delegationDocumentGrid.setStyleAttribute("borderTop", "none");
		delegationDocumentGrid.setHeight(100);
		delegationDocumentGrid.setBorders(false);
		delegationDocumentGrid.setStripeRows(true);
		delegationDocumentGrid.setColumnLines(true);
		delegationDocumentGrid.setColumnReordering(true);

		delegationDocumentGrid.getAriaSupport().setLabelledBy(cpDelegationDocumentGrid.getHeader().getId() + "-label");
		cpDelegationDocumentGrid.add(delegationDocumentGrid);
		cpDelegationDocumentGrid.addButton(btnAddDocument);
		

		return cpDelegationDocumentGrid;
	}
	
	/**
	 * create document view
	 */
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
				final com.google.gwt.user.client.ui.Label label = new com.google.gwt.user.client.ui.Label();
				label.setStyleName("x-link-item");
				int delegationType = delegationModel.getDelegationType().getId();
				if (delegationType == ConstantClient.DELEGATION_TYPE_IS_TEMPORAIRE) {
					label.setText(model.getName() + " " + messages.commontemporary());
					label.setTitle(model.getName() + " " + messages.commontemporary());
				} else {
					label.setText(model.getName());
					label.setTitle(model.getName());
				}
				
				label.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						String reportUrl = GWT.getHostPageBaseURL() + ".printDocumentServiceServlet";
						List<NameValuePair> values = new ArrayList<NameValuePair>();
						values.add(new NameValuePair("domId", model.getId().toString()));
						values.add(new NameValuePair("delId", delegationModel.getId().toString()));
						ReportUtil.showReport(reportUrl, values.toArray(new NameValuePair[0]));
					}
				});
				
				return label;
			}
		};

		// Column documents
		ColumnConfig name = new ColumnConfig();
		name.setId("documentMdl.name");
		name.setHeader(messages.delegationformlesdocuments());
		name.setWidth(270);
		name.setRowHeader(true);
		name.setRenderer(documentRender);
		name.setSortable(false);
		
		GridCellRenderer<DocumentMdlModel> actionRender = new GridCellRenderer<DocumentMdlModel>() {
			
			@Override
			public Object render(final DocumentMdlModel model, String property,
					com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex,
					ListStore<DocumentMdlModel> store, final Grid<DocumentMdlModel> grid) {
				ContentPanel panel = new ContentPanel();
				panel.setHeaderVisible(false);
				panel.setBodyBorder(false);
				
				final FileUploadField btnUpload = new FileUploadField();				
				Date now = new Date();
				String uploadId = now.getTime() + CommonUtils.randomString(12) + ".pdf";
				btnUpload.setName(uploadId);
				btnUpload.setWidth(190);
				btnUpload.setItemId(uploadId);
				btnUpload.setId(uploadId);
				btnUpload.getMessages().setBrowseText(messages.documentBrowseText());
				btnUpload.setStyleAttribute("paddingRight", "20px");
				btnUpload.addListener(Events.OnChange, new Listener<BaseEvent>() {
					@Override
					public void handleEvent(BaseEvent be) {
						AppUtil.putInEditMode();
						model.setSignedFilename(btnUpload.getName());
						currentDocumentModel = model;
					}
				});
					
				final Button btnDelete = new Button(messages.commonSupprimer());
				btnDelete.setVisible(false);
				btnDelete.addSelectionListener(new SelectionListener<ButtonEvent>() {
					@Override
					public void componentSelected(ButtonEvent ce) {
						clientDocumentMdlService.getDocumentsByDelegation(delegationModel.getId(), new AsyncCallback<List<DomDelModel>>() {
							@Override
							public void onSuccess(List<DomDelModel> arg0) {
								if (arg0.size() != 0) {
									for (DomDelModel domDelModel : arg0) {
										if (domDelModel.getDocumentMdl().getId() == model.getId()) {
											clientDocumentMdlService.deleteDocument(domDelModel, new AsyncCallback<Boolean>() {
												@Override
												public void onSuccess(Boolean arg0) {
													Info.display(messages.commoninfo(), "Supprimé avec succès.");
													changeDocumentTable(group);
													cpDocumentGrid.repaint();
													// true to refesh when back to list delegation
													isChanged = true;
													
													clientDelegationService.findById(delegationModel.getId(), new AsyncCallback<DelegationModel>() {
														
														@Override
														public void onSuccess(DelegationModel arg0) {
															delegationModel = arg0;
															if (arg0 != null) {
																cbStatus.setText(arg0.getDelegationStatus().getName());
															}
														}
														
														@Override
														public void onFailure(Throwable arg0) {
														}
													});
													
												}
												
												@Override
												public void onFailure(Throwable arg0) {
													Info.display(messages.commonerror(), messages.commonServererror());
												}
											});
										}
									}
								}
							}
							
							@Override
							public void onFailure(Throwable arg0) {
							}
						});
					}
				});
				
				panel.add(btnUpload);
				panel.add(btnDelete);
				
				clientDocumentMdlService.getDocumentsByDelegation(delegationModel.getId(), new AsyncCallback<List<DomDelModel>>() {
					@Override
					public void onSuccess(List<DomDelModel> arg0) {
						if (arg0.size() == 0) {
							btnUpload.setVisible(true);
							btnDelete.setVisible(false);
						} else {
							for (DomDelModel domDelModel : arg0) {
								if (domDelModel.getDocumentMdl().getId() == model.getId()) {
									btnUpload.setVisible(false);
									btnDelete.setVisible(true);
								}
							}
						}
					}
					
					@Override
					public void onFailure(Throwable arg0) {
					}
				});
				
				return panel;
			}
		};
		
		ColumnConfig action = new ColumnConfig("action", "Action", 202);
		action.setRenderer(actionRender);

		ColumnConfig signed = new ColumnConfig("signed", "Signed", 200);
		
		GridCellRenderer<DocumentMdlModel> signedRender = new GridCellRenderer<DocumentMdlModel>() {
			
			@Override
			public Object render(final DocumentMdlModel model, String property,
					com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex,
					ListStore<DocumentMdlModel> store, Grid<DocumentMdlModel> grid) {
				final com.google.gwt.user.client.ui.Label lbl = new com.google.gwt.user.client.ui.Label();
				
				lbl.setStyleName("x-link-item");
				
				clientDocumentMdlService.getDocumentsByDelegation(delegationModel.getId(), new AsyncCallback<List<DomDelModel>>() {
					@Override
					public void onSuccess(List<DomDelModel> arg0) {
						if (arg0.size() == 0) {
						} else {
							for (DomDelModel domDelModel : arg0) {
								if (domDelModel.getDocumentMdl().getId() == model.getId()) {
									if (domDelModel.getSignedFilename() == null) {
										lbl.setText(domDelModel.getHemeraLien());
										lbl.setTitle(domDelModel.getHemeraLien());
									} else {					
										lbl.setText(domDelModel.getSignedFilename());
										lbl.setTitle(domDelModel.getSignedFilename());										
									}
								}
							}
						}
					}
					
					@Override
					public void onFailure(Throwable arg0) {
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
				
				return lbl;
			}
		};
		signed.setRenderer(signedRender);
		
		configs.add(name);
		configs.add(signed);
		configs.add(action);
		
		// setup column model
		columnModel = new ColumnModel(configs);
		
		btnValider = new Button(messages.commonValiderButton());
		btnValider.addSelectionListener(new SelectionListener<ButtonEvent>() {
			
			@Override
			public void componentSelected(ButtonEvent ce) {
				submit();
				AppUtil.removeInEditMode();
			}
		});
		
		btnAnnulerSignedDocument = new Button(messages.commonAnnulerButton());
		btnAnnulerSignedDocument.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				changeDocumentTable(group);
				AppUtil.removeInEditMode();
			}
		});

		// Content panel with header
		cpDocumentGrid = new ContentPanel();
		cpDocumentGrid.setBodyBorder(true);
		cpDocumentGrid.setHeaderVisible(false);
		cpDocumentGrid.setButtonAlign(HorizontalAlignment.CENTER);
		cpDocumentGrid.setLayout(new FitLayout());
		cpDocumentGrid.setSize(680, 180);
		cpDocumentGrid.setButtonAlign(HorizontalAlignment.RIGHT);
		cpDocumentGrid.setStyleAttribute("paddingTop", "10px");
		cpDocumentGrid.setStyleAttribute("paddingBottom", "10px");

		// Grid
		documentMdlModels = new ListStore<DocumentMdlModel>();
		
		documentGrid = new Grid<DocumentMdlModel>(documentMdlModels, columnModel);
		documentGrid.setStyleAttribute("borderTop", "none");
		documentGrid.setHeight(100);
		documentGrid.setBorders(false);
		documentGrid.setStripeRows(true);
		documentGrid.setColumnLines(true);
		documentGrid.setColumnReordering(true);
		
		documentGrid.getAriaSupport().setLabelledBy(cpDocumentGrid.getHeader().getId() + "-label");
		cpDocumentGrid.add(documentGrid);
		cpDocumentGrid.addButton(btnAnnulerSignedDocument);
		cpDocumentGrid.addButton(btnValider);

		return cpDocumentGrid;
	}
	
	/**
	 * init button
	 */
	private void initButtons() {
		// create panel for button
//		HorizontalPanel hpButton = new HorizontalPanel();
//		hpButton.setHorizontalAlign(HorizontalAlignment.CENTER);
//		hpButton.setTableWidth("150");
//		
//		btnAnnuler = new Button(messages.delegationformannuler());
//		btnModifier = new Button(messages.delegationformmodifier());
//		
//		hpButton.add(btnAnnuler);
//		hpButton.add(btnModifier);
//		
//		this.add(hpButton, new FlowData(10, 0, 0, 548));

		btnAnnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
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
				DelegationEvent event = new DelegationEvent();
				event.setDelegationId(delegationModel.getId());
				event.setMode(DelegationEvent.MODE_IS_EDIT);
				event.setEntiteModel(entiteModel);
				resetForm();
				documentMdlModels.removeAll();
				
				ContentEvent contentEvent = new ContentEvent();
				contentEvent.setEvent(event);
				contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_NEW_DELEGATION_FORM);
				bus.fireEvent(contentEvent);
				
//				navigation.goToEcran(Action.ACTION_NEW_DELEGATION, new NavigationEvent(event));
			}
		});
	}

	/**
	 * reset data in form
	 */
	private void resetForm() {
		isChanged = false;
		this.reset();
		for (Field<?> field : getFields()) {
			field.setVisible(true);
		}
		resetFieldSets(this.delegantFieldSet, this.delegataireFieldSet, this.societeFieldSet, this.chantierFieldSet);
		invisibleExtFields();
	}

	/**
	 * invisible all extension fields
	 */
	private void invisibleExtFields() {
		this.dfDebut.setVisible(false);
		this.dfFin.setVisible(false);
		this.txtChamps.setVisible(false);
		this.dfSignature.setVisible(false);
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
		this.txtZone.setVisible(false);
		this.txtOperations.setVisible(false);
	}

	private void applyRule2(final DelegationModel delegationModel, PerimetreTypeModel perimetre, DelegationNatureModel nature, Integer collaborateType) {
		invisibleExtFields();
		// TODO Hard code apply Language
		LanguageModel lang = new LanguageModel();
		lang.setId(1);
		clientDelegationModelServiceAsync.getGroup(lang, perimetre, nature, collaborateType, new AsyncCallbackWithErrorResolution<Integer>() {
			@Override
			public void onSuccess(Integer arg0) {
				group = arg0;
				if (arg0 != 0) {
					clientFieldRuleService.getRulesByDemGroup(arg0, new AsyncCallbackWithErrorResolution<List<FieldRuleModel>>() {
						@Override
						public void onSuccess(List<FieldRuleModel> arg0) {
							processFieldRules(delegationModel, arg0);
						}
					});
					changeDocumentTable(arg0);
				}
			}
		});
	}

	@Override
	protected void processSpecificFields(final DelegationModel delegationModel) {
//		cbNature.setValue(cbNature.getValue());
		cbDemandeur.setValue(cbDemandeur.getValue());
		cbDelegant.setValue(cbDelegant.getValue());
		cbDelegataire.setValue(cbDelegataire.getValue());

		if (delegationModel.getDelegationType().getId().intValue() == ConstantClient.DELEGATION_TYPE_IS_TEMPORAIRE) {
			dfDebut.setVisible(true);
			dfFin.setVisible(true);
		}
		if (isCirmad((String) cbNature.getValue())) {
			txtZone.setVisible(true);
			txtOperations.setVisible(true);
		} else {
			txtZone.setVisible(false);
			txtOperations.setVisible(false);
		}
	}


	/**
	 * apply data to dataset
	 */
	private void changeDelegantFieldSet(CollaborateurModel collaborateurModel) {
		if ((delegationModel != null) && (collaborateurModel != null)) {
			delegantFieldSet.applyInformation(delegationModel, collaborateurModel);
			delegantFieldSet.setVisible(true);			
			delegantFieldSet.getTitre().setEnabled(false);
			delegantFieldSet.getLblDelegantQualite().setEnabled(false);
			delegantFieldSet.collapse();
//			delegantFieldSet.setShow();
		}
	}

	/**
	 * apply data to dataset
	 */
	private void changeDelegataireFieldSet(CollaborateurModel collaborateurModel) {
		if ((delegationModel != null) && (collaborateurModel != null)) {
			delegataireFieldSet.applyInformation(collaborateurModel);
			delegataireFieldSet.setVisible(true);
			delegataireFieldSet.collapse();
//			delegataireFieldSet.setShow();
		}
	}
	
	
	/**
	 * apply data to dataset
	 */
	private void changeSocieteFieldSet(EntiteJuridiqueModel entiteJuridiqueModel) {
		if (cbNature.getValue() != null && entiteJuridiqueModel != null) {
			societeFieldSet.applyInformation(entiteJuridiqueModel);
			societeFieldSet.setVisible(true);
			societeFieldSet.collapse();
//			societeFieldSet.setShow();
			
		}
	}
	
	/**
	 * apply data to dataset
//	 */
	private void changeChantierFieldSet(PerimetreModel perimetreModel) {
		
		//if (perimetreModel != null && perimetreModel.getType()!= null && ConstantClient.PERIMETER_TYPE_NAME_IS_CHANTIER.equals(perimetreModel.getType().getName())) {
		if (perimetreModel != null && perimetreModel.getType()!= null && CommonUtils.isChantierType(perimetreModel.getType().getName())) {
			chantierFieldSet.applyInformation(perimetreModel);
			chantierFieldSet.setVisible(true);
			chantierFieldSet.collapse();
//			chantierFieldSet.setShow();
		}
		
	}
	
	private void changeDocumentTable(Integer group) {
		clientDemDomServiceAsync.getAllDemDomsByDemGroup(group, new AsyncCallback<List<DemDomModel>>() {
			@Override
			public void onSuccess(List<DemDomModel> arg0) {				
				documentMdlModels.removeAll();
				for (DemDomModel demDom : arg0) {
					documentMdlModels.add(demDom.getDocumentMdl());
				}				
			}

			@Override
			public void onFailure(Throwable arg0) {
			}
		});
	}
	
	private void changeOtherDocumentTable(Integer delegationId) {
		clientDelegationDocumentServiceAsync.getDelegationDocuments(delegationId, new AsyncCallback<List<DelegationDocumentModel>>() {
			
			@Override
			public void onSuccess(List<DelegationDocumentModel> result) {
				delegationDocumentModels.removeAll();
				delegationDocumentModels.add(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}	
}