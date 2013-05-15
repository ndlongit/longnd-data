package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreFilter;
import com.extjs.gxt.ui.client.widget.ComponentManager;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.DateTimePropertyEditor;
import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.ModifyCollaboratureEvent;
import com.structis.vip.client.event.ModifyCollaboratureHandler;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientCollaborateurServiceAsync;
import com.structis.vip.client.service.ClientCollaborateurTypeServiceAsync;
import com.structis.vip.client.service.ClientFormationServiceAsync;
import com.structis.vip.client.service.ClientSyncServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.shared.model.AddressModel;
import com.structis.vip.shared.model.CollaborateurFormationModel;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.CollaborateurTypeModel;
import com.structis.vip.shared.model.DelegantPerimetreModel;
import com.structis.vip.shared.model.DelegatairePerimetreModel;
import com.structis.vip.shared.model.FormationModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;

public class CollaboratureFormPanel extends LayoutContainer {
	private final Messages messages = GWT.create(Messages.class);
	private final FormData formData = new FormData("95%");
	private final FormData formData40 = new FormData("60%");
	private final static int WIDTH = 700;
	private final static int HEIGHT = -1;

	private ClientFormationServiceAsync clientFormationService = ClientFormationServiceAsync.Util.getInstance();
	private ClientCollaborateurServiceAsync clientCollaboratureService = ClientCollaborateurServiceAsync.Util.getInstance();
	private ClientCollaborateurTypeServiceAsync clientCollaborateurTypeSerivce = ClientCollaborateurTypeServiceAsync.Util.getInstance();
	private ClientSyncServiceAsync clientSyncService = ClientSyncServiceAsync.Util.getInstance();
	
	private SimpleEventBus bus;

	private SimpleComboBox<String> cbCivilite;
	private TextField<String> tfNom;
	private TextField<String> tfPrenom;
	private DateField dfDateDeNaissance;
	private TextField<String> tfLieuDeNaissance;
	private TextField<String> tfNationalite;
	private TextArea tfAddressPersonel;
	
	private LabelField lfDateMiseAJourRubis;
	private LabelField lfSocieteRubis;
	private LabelField lfMatricule;
	private LabelField lfDateEntreGroup;
	private LabelField lfDateDeSortieSociete;
	
	private TextField<String> tfSocieteSiExterne;
	private TextField<String> tfNiveauHierarchique;
	private RadioGroup rg;
	private Radio roSortiOui;
	private Radio roSortiNon;
	private CheckBox cbCollaboratureDelegant;
	private LabelField lbPeriemetreDelegant;	
	private CheckBox cbCollaboratureDelegataire;
	private LabelField lbPeriemetreDelegataire;
	private DateField dfDateDeFormation;
	private TextField<String> tfQualiteDuDelegant;
	private DateField dfDateDuConseilAdministration;
	private SimpleComboBox<String> cbStatuDuConseilAdministration;
	private DateField dfAEffectDu;

	private DateField dfDateDelegation;
	private ComboBox<CollaborateurModel> cbDelegant;
	private ComboBox<CollaborateurTypeModel> cbColType;
	private TextField<String> tfQualiteCollaboratureDelegant;
//	private TextField<String> tfZone;
//	private TextField<String> tfOperations;
	
	private Label lfFormation;
	private EditorGrid<FormationModel> grid;
	private HorizontalPanel gridPanel;
	
	private ListStore<FormationModel> formationStore = new ListStore<FormationModel>();

	private FormPanel panel;
	private Button btnAnnuler;
	private Button btnModifier;
	private CollaborateurModel model;
	private boolean isEdit = true;
	private List<PerimetreModel> delegatairePerimetres = new ArrayList<PerimetreModel>();
	private List<PerimetreModel> delegantPerimetres = new ArrayList<PerimetreModel>();
	private StoreFilter<CollaborateurModel> typeFilter;	

	public CollaboratureFormPanel(SimpleEventBus bus) {
		this.bus = bus;
		
		setLayout(new FlowLayout(10));
		setScrollMode(Scroll.AUTO);
		setStyleAttribute("paddingBottom", "20px");
		setStyleAttribute("paddingRight", "10px");
		setWidth(WIDTH);		
		initUI();
		createTypeFilter();
		initEvent();
		addHandler();
	}
	
	private void createTypeFilter() {
		typeFilter = new StoreFilter<CollaborateurModel>() {

			@Override
			public boolean select(Store<CollaborateurModel> store, CollaborateurModel parent, CollaborateurModel item,
					String property) {
				return isInMandataireGroup(item.getType());
			}

			private boolean isInMandataireGroup(CollaborateurTypeModel m) {
				if( m == null ) {
					return false;
				}

				return CollaborateurTypeModel.belongsMandataireSocial(m.getGroup());
			}
		};
	}
	
	private void initUI() {
		panel = new FormPanel();
		panel.setLabelWidth(180);
		panel.setFrame(true);
		panel.setHeading("Fiche collaborateur");
		panel.setBorders(false);
		panel.setCollapsible(false);
		panel.setLayout(new FlowLayout());
		panel.setSize(WIDTH, -1);
		panel.setButtonAlign(HorizontalAlignment.RIGHT);
		
		initBackLink();
		initBasicForm();
		
		LayoutContainer lcLine = new LayoutContainer();
		lcLine.setSize(WIDTH, HEIGHT);
		lcLine.setLayout(new ColumnLayout());
		lcLine.add(new HTML("<hr width='670px'/>"));
		panel.add(lcLine);
		
		initMoreForm();
		
		gridPanel.setVisible(false);
		visibledFieldsForDelegant(false);
		visibledFieldsForDelegataire(false);
		
		btnAnnuler = new Button(messages.commonAnnulerButton());
		btnModifier = new Button(messages.commonValiderButton()); 
		
		panel.addButton(btnAnnuler);
		panel.addButton(btnModifier);
		
		add(panel);
	}
	
	private void initEvent() {
		btnAnnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				AppUtil.removeAdminInEditMode();
				ContentEvent event = new ContentEvent();
				event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_LIST);
				bus.fireEvent(event);
			}
		});
		
		btnModifier.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {	
				if (panel.isValid()) {
					save();
				}
			}
		});
		
		cbCollaboratureDelegant.addListener(Events.OnClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {			
				if (checkIfPerimeterSelected(cbCollaboratureDelegant, lbPeriemetreDelegant, delegantPerimetres, true)) {
					if (cbCollaboratureDelegant.getValue() == false) {						
						visibledFieldsForDelegant(false);
					} else if (!ConstantClient.ENTITE_ID_IS_ETDE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
						visibledFieldsForDelegant(true);						
					}
				} else {
					cbCollaboratureDelegant.setValue(!cbCollaboratureDelegant.getValue());
					be.setCancelled(false);							
				}								
			}
		});
		
		cbCollaboratureDelegataire.addListener(Events.OnClick, new Listener<BaseEvent>() {

			@Override
			public void handleEvent(BaseEvent be) {
				if (checkIfPerimeterSelected(cbCollaboratureDelegataire, lbPeriemetreDelegataire, delegatairePerimetres, false)) {
					if (cbCollaboratureDelegataire.getValue() == false) {
						gridPanel.setVisible(false);
						visibledFieldsForDelegataire(false);
					} else if (!ConstantClient.ENTITE_ID_IS_ETDE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
						gridPanel.setVisible(true);
						visibledFieldsForDelegataire(true);
					}
				} else {
					cbCollaboratureDelegataire.setValue(!cbCollaboratureDelegataire.getValue());
					be.setCancelled(false);							
				}
			}
		});
		
		cbDelegant.addSelectionChangedListener(new SelectionChangedListener<CollaborateurModel>() {			
			@Override
			public void selectionChanged(SelectionChangedEvent<CollaborateurModel> se) {
//				if (tfQualiteCollaboratureDelegant.getValue() == null) {
					tfQualiteCollaboratureDelegant.setValue(se.getSelectedItem() == null ? "": se.getSelectedItem().getQualiteDelegant());
//				}
			}
		});

		cbColType.addSelectionChangedListener(new SelectionChangedListener<CollaborateurTypeModel>() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent<CollaborateurTypeModel> se) {
				changeWithColType(se.getSelectedItem());
			}
		});
	}
	
	private void changeWithColType(CollaborateurTypeModel type) {
		if (type != null) {
			//19 Feb 2013
			if (CommonUtils.belongsBYEFEGroup(type.getEntite().getEntId())) {
				ListStore<CollaborateurModel> store = cbDelegant.getStore();
				if (CollaborateurTypeModel.belongsMandataireSocial(type.getGroup().getName())) {
					tfQualiteDuDelegant.setVisible(true);
					dfDateDuConseilAdministration.setVisible(true);
					cbStatuDuConseilAdministration.setVisible(true);
					dfAEffectDu.setVisible(true);
					dfDateDelegation.setVisible(false);				
					cbDelegant.setVisible(false);
					tfQualiteCollaboratureDelegant.setVisible(false);
					if (store != null) {
						store.clearFilters();
					}
				} else {
					tfQualiteDuDelegant.setVisible(false);
					dfDateDuConseilAdministration.setVisible(false);
					cbStatuDuConseilAdministration.setVisible(false);
					dfAEffectDu.setVisible(false);
					dfDateDelegation.setVisible(true);
					cbDelegant.setVisible(true);
					cbDelegant.setValue(null);
					//26 Nov					
					if (store != null) {
						store.addFilter(typeFilter);
						store.applyFilters("type");
					}
					tfQualiteCollaboratureDelegant.setVisible(true);
				}
				
			} else { //ETDE
				if (type.getId() == ConstantClient.COLLABORATEUR_TYPE_DG || type.getId() == ConstantClient.COLLABORATEUR_TYPE_DGD) {
	//			if (type.getGroup().getName().equals(ConstantClient.COLLABORATEUR_TYPE_MANDATAIRE_SOCIAL)) {					
					tfQualiteDuDelegant.setVisible(true);
					dfDateDuConseilAdministration.setVisible(true);
					cbStatuDuConseilAdministration.setVisible(true);
					dfAEffectDu.setVisible(true);
					dfDateDelegation.setVisible(false);				
					cbDelegant.setVisible(false);
					tfQualiteCollaboratureDelegant.setVisible(false);
				} else {
					tfQualiteDuDelegant.setVisible(false);
					dfDateDuConseilAdministration.setVisible(false);
					cbStatuDuConseilAdministration.setVisible(false);
					dfAEffectDu.setVisible(false);
					dfDateDelegation.setVisible(true);
					cbDelegant.setVisible(true);					
					
					tfQualiteCollaboratureDelegant.setVisible(true);
					
				}
			}
		}
	}
	
	private void visibledFieldsForDelegant(boolean visibled) {
		tfQualiteDuDelegant.setVisible(false);
		dfDateDuConseilAdministration.setVisible(false);
		cbStatuDuConseilAdministration.setVisible(false);
		dfAEffectDu.setVisible(false);
		dfDateDelegation.setVisible(false);
		cbDelegant.setVisible(false);
		tfQualiteCollaboratureDelegant.setVisible(false);
		
		cbColType.setVisible(visibled);
		cbColType.setValue(null);
	}
	
	private void visibledFieldsForDelegataire(boolean visibled) {
		dfDateDeFormation.setVisible(visibled);
//		tfZone.setVisible(visibled);
//		tfOperations.setVisible(visibled);
	}
	
	private void setEnabledAll(boolean enable) {
		for (Field<?> field : panel.getFields()) {
			if (field instanceof LabelField) {
				continue;
			} else {
				field.setEnabled(enable);
			}
		}
		btnModifier.setVisible(enable);
	}
	
	private void addHandler() {
		bus.addHandler(ModifyCollaboratureEvent.getType(), new ModifyCollaboratureHandler() {
			@Override
			public void onLoadAction(ModifyCollaboratureEvent event) {
				initData();
				
				if (ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_CREATE_FORM == event.getMode()
						|| event.getMode() == ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_VIEW_FORM) {
					if (event.getMode() == ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_VIEW_FORM) {
						setEnabledAll(false);
						grid.setEnabled(false);
					} else {
						setEnabledAll(true);
						grid.setEnabled(true);
						AppUtil.putInAdminEditMode();
					}
					
					if (event.getModel() != null) {						
						isEdit = true;						
						btnModifier.setText(messages.commonModifierButton());
											
						clientCollaboratureService.findById(event.getModel().getId(), new AsyncCallbackWithErrorResolution<CollaborateurModel>() {
							@Override
							public void onSuccess(CollaborateurModel arg0) {
								model = arg0;								
								fillData(arg0);
								if (isEdit == true && model != null) {
									ListStore<CollaborateurModel> store = cbDelegant.getStore();
									for (CollaborateurModel item : store.getModels()) {
										if (item.getId().intValue() == model.getId().intValue()) {
											store.remove(item);
											if (cbDelegant.getValue() == item) {												
												cbDelegant.setValue(null);
											}
											break;
										}
									}
								}								
							}

							private void fillData(final CollaborateurModel model) {
								if (model.getDelegantPerimetreNames() != null) {
									lbPeriemetreDelegant.setValue(model.getDelegantPerimetreNames());
									lbPeriemetreDelegant.setVisible(true);
								}
								
								if (model.getDelegatairesPerimetreNames() != null) {
									lbPeriemetreDelegataire.setValue(model.getDelegatairesPerimetreNames());
									lbPeriemetreDelegataire.setVisible(true);
								}
								cbCivilite.setSimpleValue(model.getCivilite());
								tfNom.setValue(model.getLastname());
								tfPrenom.setValue(model.getFirstname());
								dfDateDeNaissance.setValue(model.getDateNaissance());
								tfLieuDeNaissance.setValue(model.getLieuNaissance());
								cbCollaboratureDelegant.setValue((model.getIsDelegant() != null && model.getIsDelegant() == 1) ? true : false);
								cbCollaboratureDelegataire.setValue((model.getIsDelegataire() != null && model.getIsDelegataire() == 1) ? true : false);
								tfNationalite.setValue(model.getNationality());
								tfNiveauHierarchique.setValue(model.getNiveauHierarchique());
								tfAddressPersonel.setValue(model.getAddress());
								//add BYTP
//								if (ConstantClient.ENTITE_ID_IS_BYEFE.equalsIgnoreCase(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
								if (CommonUtils.belongsBYEFEGroup(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
									if (model.getIsDelegataire() != null && model.getIsDelegataire() == 1) {
										gridPanel.setVisible(true);
										visibledFieldsForDelegant(false);
										visibledFieldsForDelegataire(true);
									} 
									
									if (model.getIsDelegant() != null && model.getIsDelegant() == 1) {
										gridPanel.setVisible(false);
										visibledFieldsForDelegant(true);
										visibledFieldsForDelegataire(false);
										
										if (model.getType() != null) {
											CollaborateurTypeModel type = model.getType();
											cbColType.setValue(type);
											
											changeWithColType(type);
											String colGroup = type != null ? type.getGroup().getName(): null;
											if (CollaborateurTypeModel.belongsMandataireSocial(colGroup)) {
											//if (model.getType().getId() == ConstantClient.COLLABORATEUR_TYPE_DG || type.getId() == ConstantClient.COLLABORATEUR_TYPE_DGD) {
												tfQualiteDuDelegant.setValue(model.getQualiteDelegant());
												dfDateDuConseilAdministration.setValue(model.getDateConseil());
												cbStatuDuConseilAdministration.setSimpleValue(model.getStatutConseil());
												dfAEffectDu.setValue(model.getDateEffet());
											} else {
												dfDateDelegation.setValue(model.getDateDelegation());
												cbDelegant.setValue(model.getDelegant());	
												if (model.getId() != null) {
													tfQualiteCollaboratureDelegant.setValue(model.getQualiteColDelegant());
												}
											}
										}
									}
									
									if (model.getIsDelegant() != null && model.getIsDelegant() == 1 && 
											model.getIsDelegataire() != null && model.getIsDelegataire() == 1) {
										gridPanel.setVisible(true);
										visibledFieldsForDelegant(true);
										visibledFieldsForDelegataire(true);
										
										if (model.getType() != null) {
											CollaborateurTypeModel type = model.getType();
											cbColType.setValue(type);
											
											changeWithColType(type);
											
											if (model.getType().getId() == ConstantClient.COLLABORATEUR_TYPE_DG || type.getId() == ConstantClient.COLLABORATEUR_TYPE_DGD) {
												tfQualiteDuDelegant.setValue(model.getQualiteDelegant());
												dfDateDuConseilAdministration.setValue(model.getDateConseil());
												cbStatuDuConseilAdministration.setSimpleValue(model.getStatutConseil());
												dfAEffectDu.setValue(model.getDateEffet());
											} else {
												dfDateDelegation.setValue(model.getDateDelegation());
												cbDelegant.setValue(model.getDelegant());
												tfQualiteCollaboratureDelegant.setValue(model.getQualiteColDelegant());
											}
										}
									}
									
									
									clientFormationService.findByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(), new AsyncCallback<List<FormationModel>>() {
										
										@Override
										public void onSuccess(final List<FormationModel> formations) {
											clientCollaboratureService.findByCollaborateurId(model.getId(), new AsyncCallback<List<CollaborateurFormationModel>>() {
												@Override
												public void onSuccess(List<CollaborateurFormationModel> arg0) {
													formationStore.removeAll();
													List<FormationModel> selection = new ArrayList<FormationModel>();
													
													if (formations.size() != 0 && arg0.isEmpty() == false) {
														for (FormationModel forModel : formations) {
															boolean has = false;
															for (CollaborateurFormationModel colForModel : arg0) {
																if (colForModel != null) {
																	if (forModel.getId().intValue() == colForModel.getFormation().getId().intValue()) {
																		forModel.setDate(colForModel.getDate());
																		selection.add(forModel);
																		formationStore.add(forModel);
																		has = true;
																		break;
																	}
																}
															}
															if (has == false) {
																formationStore.add(forModel);
															}
														};
													} else {
														formationStore.add(formations);
													}
													grid.getSelectionModel().setSelection(selection);
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
								clientSyncService.getAddress(model.getIdBycn(), new AsyncCallback<AddressModel>() {
									@Override
									public void onSuccess(AddressModel arg0) {
										if (arg0 != null && arg0.getIdbycn() != null) {
											lfDateMiseAJourRubis.setText(model.getDateMajRubis()!= null ? DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).format(model.getDateMajRubis()): "");
											//lfSocieteRubis.setText(model.)
											lfMatricule.setText(model.getIdBycn());
											lfDateEntreGroup.setText(model.getDateEntree()!= null ? DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).format(model.getDateEntree()): "");
											lfDateDeSortieSociete.setText(model.getDateSortie()!= null ? DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT).format(model.getDateSortie()): "");
										}
									}
			
									@Override
									public void onFailure(Throwable arg0) {
									}
								});
								
							}
							
						});
						
						clientCollaborateurTypeSerivce.getCollaborateurTypeByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),new AsyncCallbackWithErrorResolution<List<CollaborateurTypeModel>>() {
							@Override
							public void onSuccess(List<CollaborateurTypeModel> arg0) {
								cbColType.getStore().removeAll();
								cbColType.getStore().add(arg0);
							}
						});					
					} else {
						isEdit = false;
						model = new CollaborateurModel();
						panel.reset();
						panel.clear();
						
						// set default
						rg.setValue(roSortiNon);
						btnModifier.setText(messages.commonValiderButton());
						
						gridPanel.setVisible(false);
						visibledFieldsForDelegant(false);
						visibledFieldsForDelegataire(false);
					}
				}
			}
		});
	}
	
	private void initMoreForm() {
		LayoutContainer lcMoreForm = new LayoutContainer();
		lcMoreForm.setLayout(new RowLayout());
		lcMoreForm.setWidth(WIDTH);
		
		LayoutContainer lcInput = new LayoutContainer();				
		
		FormLayout flInput = new FormLayout();
		flInput.setLabelAlign(LabelAlign.RIGHT);
		flInput.setLabelPad(5);
		flInput.setLabelWidth(200);
		lcInput.setLayout(flInput);
		lcInput.setWidth(WIDTH);
		
		tfSocieteSiExterne = new TextField<String>();
		tfSocieteSiExterne.setFieldLabel(messages.collaboraturesocietesiexterne());
		lcInput.add(tfSocieteSiExterne, formData40);
		
		tfNiveauHierarchique = new TextField<String>();
		tfNiveauHierarchique.setAllowBlank(false);
		tfNiveauHierarchique.setFieldLabel(messages.collaboratureniveauhierarchique());
		lcInput.add(tfNiveauHierarchique, formData40);
		
		roSortiNon = new Radio();
		roSortiNon.setBoxLabel(messages.commonNon());
		roSortiNon.setValueAttribute("Non");
		roSortiNon.setValue(true);
		
		roSortiOui = new Radio();
		roSortiOui.setBoxLabel(messages.commonOui());
		roSortiOui.setValueAttribute("Oui");
		
		rg = new RadioGroup();
		rg.setFieldLabel(messages.collaboraturesorti());
		rg.add(roSortiOui);
		rg.add(roSortiNon);
		
		rg.setValue(roSortiNon);
		lcInput.add(rg, formData40);
		
		lcMoreForm.add(lcInput);
		
		LayoutContainer lcColaborateur = new LayoutContainer();
		lcColaborateur.setSize(WIDTH-20, HEIGHT);		
		lcColaborateur.setLayout(new ColumnLayout());

		LayoutContainer lcL = new LayoutContainer();						
		FormLayout flLeft = new FormLayout();
		flLeft.setLabelAlign(LabelAlign.RIGHT);
		flLeft.setLabelWidth(200);
		lcL.setLayout(flLeft);
//		LayoutContainer lcR = new LayoutContainer();
//		FormLayout flRight = new FormLayout();
//		flRight.setLabelAlign(LabelAlign.LEFT);
//		flRight.setLabelWidth(200);
//		lcR.setLayout(flRight);
		
		
		cbCollaboratureDelegant = new CheckBox();
		cbCollaboratureDelegant.setFieldLabel(messages.collaboraturecollaborateurdelegant());
		cbCollaboratureDelegant.setStyleAttribute("padding", "0px");		
		lcL.add(cbCollaboratureDelegant, new FormData("32%"));
		lbPeriemetreDelegant = new LabelField();
		lbPeriemetreDelegant.setLabelSeparator(":");
		lbPeriemetreDelegant.setFieldLabel(messages.collaboraturelabelapplyperietredelegant());

		lcL.add(lbPeriemetreDelegant, formData);
		
		cbCollaboratureDelegataire = new CheckBox();
		cbCollaboratureDelegataire.setStyleAttribute("padding", "0px");
		cbCollaboratureDelegataire.setFieldLabel(messages.collaboraturecollaborateurdelegataire());
		lcL.add(cbCollaboratureDelegataire, new FormData("32%"));
		lbPeriemetreDelegataire = new LabelField();
		lbPeriemetreDelegataire.setLabelSeparator(":");
		lbPeriemetreDelegataire.setFieldLabel(messages.collaboraturelabelapplyperietredelegataire());
		lcL.add(lbPeriemetreDelegataire, formData);
		lcColaborateur.add(lcL, new ColumnData(1));
//		lcColaborateur.add(lcR, new ColumnData(.65));
		lcMoreForm.add(lcColaborateur);
		
		LayoutContainer lcInput2 = new LayoutContainer();
		
		FormLayout flInput2 = new FormLayout();
		flInput2.setLabelAlign(LabelAlign.RIGHT);
		flInput2.setLabelPad(5);
		flInput2.setLabelWidth(200);
		lcInput2.setLayout(flInput2);
		lcInput2.setWidth(WIDTH);
		cbColType = new ComboBox<CollaborateurTypeModel>();
		cbColType.setTriggerAction(TriggerAction.ALL);
		cbColType.setEditable(false);
		cbColType.setVisible(false);
		cbColType.setFieldLabel(messages.collaboraturetypedelegant());
//		cbColType.setDisplayField(CollaborateurTypeModel.COT_NAME);
		cbColType.setDisplayField(CollaborateurTypeModel.COT_GROUP_NAME);		
		cbColType.setStore(new ListStore<CollaborateurTypeModel>());
		lcInput2.add(cbColType, formData40);
		// new 27 Nov
		dfDateDelegation = new DateField();
		dfDateDelegation.setPropertyEditor(new DateTimePropertyEditor(ConstantClient.DATE_FORMAT));
		dfDateDelegation.setFieldLabel(messages.collaboraturedatedelegation());
		lcInput2.add(dfDateDelegation, formData40);
		
		cbDelegant = new ComboBox<CollaborateurModel>();
		cbDelegant.setTriggerAction(TriggerAction.ALL);
		cbDelegant.setEditable(false);
		cbDelegant.setFieldLabel(messages.collaboraturedelegant());
		cbDelegant.setDisplayField(CollaborateurModel.COLLA_FULL_NAME_NO_SEPARATER);
		cbDelegant.setStore(new ListStore<CollaborateurModel>());
		
		lcInput2.add(cbDelegant, formData40);
		
		tfQualiteCollaboratureDelegant = new TextField<String>();
		tfQualiteCollaboratureDelegant.setFieldLabel(messages.collaboraturequalitecollaborateurdelegant());
		lcInput2.add(tfQualiteCollaboratureDelegant, formData40);
		lcMoreForm.add(lcInput2);
		// 27 Nov
		CheckBoxSelectionModel<FormationModel> sm = new CheckBoxSelectionModel<FormationModel>();  
		sm.setSelectionMode(SelectionMode.SIMPLE);
		
		DateField dateField = new DateField();  
		dateField.getPropertyEditor().setFormat(DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT));  
		
		ColumnConfig name = new ColumnConfig(FormationModel.FOR_LABEL, messages.formationformheader(), 196);
		
		ColumnConfig date = new ColumnConfig(FormationModel.FOR_DATE, messages.formationdate(), 100);
		date.setDateTimeFormat(DateTimeFormat.getFormat(ConstantClient.DATE_FORMAT));
		date.setEditor(new CellEditor(dateField));  
		
		List<ColumnConfig> config = new ArrayList<ColumnConfig>();
		config.add(sm.getColumn());
		config.add(name);
		config.add(date);
		
		final ColumnModel cm = new ColumnModel(config);
		
		grid = new EditorGrid<FormationModel>(formationStore, cm);
		grid.setSelectionModel(sm);  
		grid.addPlugin(sm);
		grid.setSize(338, 110);
		grid.setBorders(true);
		grid.setColumnLines(true);
		grid.setStyleAttribute("marginLeft", "2px");
		
		lfFormation = new Label(messages.collaboratureformationaladelegation() + ":");

		gridPanel = new HorizontalPanel();
		gridPanel.setHorizontalAlign(HorizontalAlignment.LEFT);
		gridPanel.setStyleAttribute("marginLeft", "50px");
		gridPanel.setStyleAttribute("marginBottom", "5px");
		gridPanel.setTableWidth("500");
		gridPanel.setSize(500, 110);
		gridPanel.add(lfFormation);
		gridPanel.add(grid);
		gridPanel.setVisible(false);
		lcInput2.add(gridPanel, formData);
		
		dfDateDeFormation = new DateField();
		dfDateDeFormation.setVisible(false);
		dfDateDeFormation.setPropertyEditor(new DateTimePropertyEditor(ConstantClient.DATE_FORMAT));
		dfDateDeFormation.setFieldLabel(messages.collaboraturedatedeformatonaladelegation());
		lcInput2.add(dfDateDeFormation, formData40);
		
//		tfZone = new TextField<String>();
//		tfZone.setVisible(false);		
//		tfZone.setFieldLabel(messages.collaboraturezone());
//		lcInput2.add(tfZone, formData40);
//		
//		tfOperations = new TextField<String>();
//		tfOperations.setVisible(false);		
//		tfOperations.setFieldLabel(messages.collaboratureoperations());
//		lcInput2.add(tfOperations, formData40);
		
		
		tfQualiteDuDelegant = new TextField<String>();
		tfQualiteDuDelegant.setVisible(false);
		tfQualiteDuDelegant.setFieldLabel(messages.collaboraturequalitedudelegant());
		lcInput2.add(tfQualiteDuDelegant, formData40);
		
		dfDateDuConseilAdministration = new DateField();
		dfDateDuConseilAdministration.setVisible(false);
		dfDateDuConseilAdministration.setPropertyEditor(new DateTimePropertyEditor(ConstantClient.DATE_FORMAT));
		dfDateDuConseilAdministration.setFieldLabel(messages.collaboraturedateduconseiladministration());
		lcInput2.add(dfDateDuConseilAdministration, formData40);
		
		cbStatuDuConseilAdministration = new SimpleComboBox<String>();
		cbStatuDuConseilAdministration.setVisible(false);
		cbStatuDuConseilAdministration.setTriggerAction(TriggerAction.ALL);
		cbStatuDuConseilAdministration.setEditable(false);
		cbStatuDuConseilAdministration.setFieldLabel(messages.collaboraturestatutduconseiladministration());
		cbStatuDuConseilAdministration.add(messages.commonnomme());
		cbStatuDuConseilAdministration.add(messages.commonconfirme());
		lcInput2.add(cbStatuDuConseilAdministration, formData40);
		
		dfAEffectDu = new DateField();
		dfAEffectDu.setVisible(false);
		dfAEffectDu.setPropertyEditor(new DateTimePropertyEditor(ConstantClient.DATE_FORMAT));
		dfAEffectDu.setFieldLabel(messages.collaboratureaeffetdu());
		lcInput2.add(dfAEffectDu, formData40);
		
		panel.add(lcMoreForm);
	}

	private void initBackLink() {
		LayoutContainer backLink = new LayoutContainer();
		backLink.setSize(WIDTH, -1);
		Label lblBack = new Label(messages.collaboratureback());
		
		lblBack.setStyleName("x-link-item");
		backLink.setStyleAttribute("margin-bottom", "20px");
		backLink.add(lblBack);
		
		lblBack.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_LIST);
					bus.fireEvent(contentEvent);
				}
			}			
		});		
		
		add(backLink);			
	}

	private void initBasicForm() {
		LayoutContainer lcInformation = new LayoutContainer();
		lcInformation.setSize(WIDTH-10, HEIGHT);
		lcInformation.setLayout(new ColumnLayout());

		LayoutContainer lcLeft = new LayoutContainer();
		FormLayout flLeft = new FormLayout();
		flLeft.setLabelAlign(LabelAlign.RIGHT);
		flLeft.setLabelWidth(130);
		lcLeft.setLayout(flLeft);

		cbCivilite = new SimpleComboBox<String>();
		cbCivilite.add("Monsieur");
		cbCivilite.add("Madame");
		cbCivilite.add("Mademoiselle");
		cbCivilite.setTriggerAction(TriggerAction.ALL);
		cbCivilite.setAllowBlank(false);
		cbCivilite.setEditable(false);
		cbCivilite.setFieldLabel(messages.collaboraturecivilite());
		lcLeft.add(cbCivilite, formData);
		
		tfNom = new TextField<String>();
		tfNom.setMaxLength(30);
		tfNom.setAllowBlank(false);
		tfNom.setFieldLabel(messages.collaboraturenom());
		lcLeft.add(tfNom, formData);
		
		tfPrenom = new TextField<String>();
		tfPrenom.setMaxLength(30);
		tfPrenom.setAllowBlank(false);
		tfPrenom.setFieldLabel(messages.collaboratureprenom());
		lcLeft.add(tfPrenom, formData);
		
		dfDateDeNaissance = new DateField();
		dfDateDeNaissance.setFieldLabel(messages.collaboraturedatedenaissance());
		dfDateDeNaissance.setAllowBlank(false);
		dfDateDeNaissance.setPropertyEditor(new DateTimePropertyEditor(ConstantClient.DATE_FORMAT));
		lcLeft.add(dfDateDeNaissance, formData);
		
		tfLieuDeNaissance = new TextField<String>();
		tfLieuDeNaissance.setMaxLength(60);
		tfLieuDeNaissance.setAllowBlank(false);
		tfLieuDeNaissance.setFieldLabel(messages.collaboraturelieudenaissance());
		lcLeft.add(tfLieuDeNaissance, formData);
		
		tfNationalite = new TextField<String>();
		tfNationalite.setMaxLength(60);
		tfNationalite.setAllowBlank(false);
		tfNationalite.setFieldLabel(messages.collaboratureNationalite());
		lcLeft.add(tfNationalite, formData);
		
//		tfAddressPersonel = new TextField<String>();
//		tfAddressPersonel.setMaxLength(300);
//		tfAddressPersonel.setAllowBlank(false);
//		tfAddressPersonel.setFieldLabel(messages.collaboratureadressepersonnelle());
//		lcLeft.add(tfAddressPersonel, formData);
		
		// setup right layout
		LayoutContainer lcRight = new LayoutContainer();
		FormLayout flRight = new FormLayout();
		flRight.setLabelAlign(LabelAlign.RIGHT);
		flRight.setLabelWidth(140);
		lcRight.setLayout(flRight);

		lfDateMiseAJourRubis = new LabelField();
		lfDateMiseAJourRubis.setFieldLabel(messages.collaboraturedatemiseajourrubis());
		lcRight.add(lfDateMiseAJourRubis, formData);

		lfSocieteRubis = new LabelField();
		lfSocieteRubis.setFieldLabel(messages.collaboraturesocieterubis());
		lcRight.add(lfSocieteRubis, formData);
		
		lfMatricule = new LabelField();
		lfMatricule.setFieldLabel(messages.collaboraturematriculerubis());
		lcRight.add(lfMatricule, formData);
		
		lfDateEntreGroup = new LabelField();
		lfDateEntreGroup.setFieldLabel(messages.collaboraturedateentregroup());
		lcRight.add(lfDateEntreGroup, formData);
		
		lfDateDeSortieSociete = new LabelField();
		lfDateDeSortieSociete.setFieldLabel(messages.collaboraturedatedesortiesociete());
		lcRight.add(lfDateDeSortieSociete, formData);

		lcInformation.add(lcLeft, new ColumnData(.5));
		lcInformation.add(lcRight, new ColumnData(.5));

		tfAddressPersonel = new TextArea();
		tfAddressPersonel.setMaxLength(300);
		//tfAddressPersonel.setWidth(50);
		tfAddressPersonel.setHeight(50);
		tfAddressPersonel.setAllowBlank(false);
		tfAddressPersonel.setFieldLabel(messages.collaboratureadressepersonnelle());
		
		LayoutContainer lcAddress = new LayoutContainer();		
		FormLayout flAddress = new FormLayout();		
		lcAddress.setSize(WIDTH-10, HEIGHT);		
		flAddress.setLabelAlign(LabelAlign.RIGHT);
		flAddress.setLabelWidth(130);
		lcAddress.setLayout(flAddress);
		lcAddress.add(tfAddressPersonel, formData);
		
		panel.add(lcInformation);
		panel.add(lcAddress);
	}

	private void initData() {		
		delegantPerimetres.clear();
		delegatairePerimetres.clear();
		lbPeriemetreDelegant.reset();
		lbPeriemetreDelegataire.reset();
		clientFormationService.findByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(), new AsyncCallbackWithErrorResolution<List<FormationModel>>() {
			
			@Override
			public void onSuccess(List<FormationModel> arg0) {
				formationStore.removeAll();
				formationStore.add(arg0);
			}
		});
		
		clientCollaboratureService.getAllDelegantsByEntiteId(SessionServiceImpl.getInstance().getEntiteContext().getEntId(), new AsyncCallbackWithErrorResolution<List<CollaborateurModel>>() {
			@Override
			public void onSuccess(List<CollaborateurModel> arg0) {
				ListStore<CollaborateurModel> store = cbDelegant.getStore();	
				store.removeAll();
				store.add(arg0);
				
//				if (isEdit == true && model != null) {
//					for (CollaborateurModel item : arg0) {
//						if (item.getId().intValue() == model.getId().intValue()) {
//							delegantStore.remove(item);
//							break;
//						}
//					}
//				}
			}
		});
				
		clientCollaborateurTypeSerivce.getCollaborateurTypeByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(), new AsyncCallbackWithErrorResolution<List<CollaborateurTypeModel>>() {
			@Override
			public void onSuccess(List<CollaborateurTypeModel> arg0) {
				cbColType.getStore().removeAll();
				cbColType.getStore().add(arg0);
			}
		});
	}
	
	private void save() {
		if (model == null)  {
			model = new CollaborateurModel();
		}
		
		model.setCivilite(cbCivilite.getSimpleValue());
		model.setFirstname(tfPrenom.getValue());
		model.setLastname(tfNom.getValue());
		model.setDateNaissance(dfDateDeNaissance.getValue());
		model.setLieuNaissance(tfLieuDeNaissance.getValue());
		model.setNationality(tfNationalite.getValue());
		model.setAddress(tfAddressPersonel.getValue());	
		//societe (si extreme)
		model.setNiveauHierarchique(tfNiveauHierarchique.getValue());
		//sorti
		if (roSortiOui.getValue()) {
			model.setSorti("O");
			model.setDateSortie(new Date());
		} else {
			model.setSorti("N");
		}
		model.setIsDelegant((cbCollaboratureDelegant.getValue() == true) ? 1 : 0);
		model.setIsDelegataire((cbCollaboratureDelegataire.getValue() == true) ? 1 : 0);
		
		model.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
		model.setDateEntree(new Date());
		
		if (cbCollaboratureDelegataire.getValue()) {
			model.setDelegatairePerimetres(createDelegatairePerimetres(model,delegatairePerimetres));
			//model.setPerimetreDelegataire(periemetreDelegataire);
			if (grid.getSelectionModel().getSelectedItems().isEmpty()) {
				model.setFormations(new ArrayList<FormationModel>());
			} else {
				model.setFormations(grid.getSelectionModel().getSelectedItems());
			}
		} else {
			model.setPerimetreDelegataire(null);
			model.setFormations(new ArrayList<FormationModel>());
		}

		if (cbCollaboratureDelegant.getValue()) {
			CollaborateurTypeModel type = cbColType.getValue();
			model.setType(cbColType.getValue());
			//model.setPerimetreDelegant(periemetreDelegant);
			model.setDelegantPerimetres(createDelegantPerimetres(model,delegantPerimetres));
			
//			Type mandataire social :
//				Nomme/confirme (champ bouton radio)
//				Date du Conseil d’Administration
//				Date d’effet de la decision
//
//			Type autres (DE, DP, …) :
//				Date de délégation du mandataire social
//				Nom mandataire
//				Prénom mandataire
//				Qualite Mandataire

			if (type != null) {				
				if (CommonUtils.belongsBYEFEGroup(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
					String colGroup = type != null ? type.getGroup().getName(): null;
					if (CollaborateurTypeModel.belongsMandataireSocial(colGroup)) {				
						model.setQualiteDelegant(tfQualiteDuDelegant.getValue());
						model.setDateConseil(dfDateDuConseilAdministration.getValue());
						model.setStatutConseil(cbStatuDuConseilAdministration.getSimpleValue());
						model.setDateEffet(dfAEffectDu.getValue());
						
						model.setDateDelegation(null);
						model.setDelegant(null);
						model.setQualiteColDelegant(null);
					} else {
						model.setQualiteDelegant(null);
						model.setDateConseil(null);
						model.setStatutConseil(null);
						model.setDateEffet(null);
						model.setDateDelegation(dfDateDelegation.getValue());
						model.setDelegant(cbDelegant.getValue());
						model.setQualiteColDelegant(tfQualiteCollaboratureDelegant.getValue());
					}
				} else { // ETDE - no change
					if (type.getId() == ConstantClient.COLLABORATEUR_TYPE_DG || type.getId() == ConstantClient.COLLABORATEUR_TYPE_DGD) {
						model.setQualiteDelegant(tfQualiteDuDelegant.getValue());
						model.setDateConseil(dfDateDuConseilAdministration.getValue());
						model.setStatutConseil(cbStatuDuConseilAdministration.getSimpleValue());
						model.setDateEffet(dfAEffectDu.getValue());
						
						model.setDateDelegation(null);
						model.setDelegant(null);
						model.setQualiteColDelegant(null);
					} else {
						model.setQualiteDelegant(null);
						model.setDateConseil(null);
						model.setStatutConseil(null);
						model.setDateEffet(null);
						model.setDateDelegation(dfDateDelegation.getValue());
						model.setDelegant(cbDelegant.getValue());
						model.setQualiteColDelegant(tfQualiteCollaboratureDelegant.getValue());
					}
				}
			}
		} else {
			model.setPerimetreDelegant(null);
		}
		
		if (isEdit == false) {
			clientCollaboratureService.insert(model, new AsyncCallback<CollaborateurModel>() {
				
				@Override
				public void onSuccess(CollaborateurModel arg0) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_LIST);
					contentEvent.setEvent(new ModifyCollaboratureEvent());
					bus.fireEvent(contentEvent);
					AppUtil.removeAdminInEditMode();
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Info.display(messages.commonerror(), messages.commonServererror());
				}
			});
		} else {
			clientCollaboratureService.updateAndFormation(model, new AsyncCallback<CollaborateurModel>() {
				@Override
				public void onSuccess(CollaborateurModel arg0) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_LIST);
					contentEvent.setEvent(new ModifyCollaboratureEvent());
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
	private List<DelegantPerimetreModel> createDelegantPerimetres(
			CollaborateurModel col, List<PerimetreModel> perimetres) {
		List<DelegantPerimetreModel> ret = new ArrayList<DelegantPerimetreModel>();
		for (PerimetreModel m: perimetres) {
			DelegantPerimetreModel dp = new DelegantPerimetreModel();
			dp.setPerimetre(m);
			dp.setDelegant(col);
			ret.add(dp);
		}
		return ret;
	}

	private List<DelegatairePerimetreModel> createDelegatairePerimetres(
			CollaborateurModel col,
			List<PerimetreModel> perimetres) {
		List<DelegatairePerimetreModel> ret = new ArrayList<DelegatairePerimetreModel>();
		for (PerimetreModel m: perimetres) {
			DelegatairePerimetreModel dp = new DelegatairePerimetreModel();
			dp.setPerimetre(m);
			dp.setDelegataire(col);
			ret.add(dp);
		}
		return ret;
	}

	private boolean checkIfPerimeterSelected(CheckBox cb, LabelField lb, List<PerimetreModel> hf, boolean isDelegantPerimetre) {		
		if (cb.getValue()) {
			List<PerimetreTreeModel> selectedPerimetres = new ArrayList<PerimetreTreeModel>();
			TreePanel<PerimetreTreeModel> perimetreTree = getAdminTree();
			if (perimetreTree != null) {
				selectedPerimetres = perimetreTree.getSelectionModel() == null ? null : (List<PerimetreTreeModel>) perimetreTree
						.getSelectionModel().getSelectedItems();
				if (selectedPerimetres != null) {
					StringBuffer perNames = new StringBuffer(); 
					for (PerimetreTreeModel item: selectedPerimetres) { 
						if (true == item.getIsUoAdmin()) {
							PerimetreModel pm =  new PerimetreModel();
							if (item.getPerId()!= null) {
								pm.setPerId(item.getPerId());
							} else if (item.getParent() == null) {
								pm.setPerId(SessionServiceImpl.getInstance().getEntiteContext().getName());
							}
							hf.add(pm);
							perNames.append(item.getName());
							perNames.append("<br>");																	
						} else {
							Info.display(messages.commoninfo(), messages.commonnopermissionperimetre());
							//hf = null;
							//lb.reset();							
						}
					}					
					lb.setText(perNames.toString());		
					if (hf.isEmpty()) {
						hf = null;
//						lb.reset();	
//						lb.setVisible(false);
						Info.display(messages.commoninfo(), messages.commonnopermissionperimetre());
						return false;
					} else {
						lb.setVisible(true);
						if (isDelegantPerimetre) {
							model.setChangeDelegantPerimetres(2);
						} else {
							model.setChangeDelegatairePerimetres(2);
						}			
						return true;
					}
					
				} else {
					Info.display(messages.commoninfo(), messages.admintreeselect());
					hf = null;
//					lb.reset();
//					lb.setVisible(false);
					return false;
				}			
			}
			hf = null;
//			lb.reset();
			return false;
		} else {
			if (isDelegantPerimetre) {
				model.setChangeDelegantPerimetres(2);
				
			} else {
				model.setChangeDelegatairePerimetres(2);
			}
			hf = null;
			lb.reset();
			lb.setVisible(false);
			return true;
		}
	}
	@SuppressWarnings("unchecked")
	private TreePanel<PerimetreTreeModel> getAdminTree() {
		TreePanel<PerimetreTreeModel> component = (TreePanel<PerimetreTreeModel>) ComponentManager.get().get(
				ConstantClient.ADMIN_TREE_ID);
		return component;
	}
}
