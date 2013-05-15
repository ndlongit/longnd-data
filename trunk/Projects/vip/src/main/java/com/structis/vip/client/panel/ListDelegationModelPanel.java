package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.dialog.ApplyDelegantTypeDialog;
import com.structis.vip.client.dialog.ApplyDocumentDialog;
import com.structis.vip.client.dialog.ApplyPerimetreTypeDialog;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.DelegationModelEvent;
import com.structis.vip.client.event.DelegationModelHandler;
import com.structis.vip.client.event.FieldRuleEvent;
import com.structis.vip.client.event.LoadGroupDelegationModelEvent;
import com.structis.vip.client.event.RefreshDelegantGridEvent;
import com.structis.vip.client.event.RefreshDelegantGridHandler;
import com.structis.vip.client.event.RefreshPerimetreTypeGridEvent;
import com.structis.vip.client.event.RefreshPerimetreTypeGridHandler;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.service.ClientCollaborateurTypeServiceAsync;
import com.structis.vip.client.service.ClientDelegationModelServiceAsync;
import com.structis.vip.client.service.ClientDelegationNatureServiceAsync;
import com.structis.vip.client.service.ClientDemDomServiceAsync;
import com.structis.vip.client.service.ClientFieldRuleServiceAsync;
import com.structis.vip.client.service.ClientFieldServiceAsync;
import com.structis.vip.client.service.ClientLanguageServiceAsync;
import com.structis.vip.client.service.ClientPerimetreTypeServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.widget.WindowResizeBinder;
import com.structis.vip.shared.model.CollaborateurTypeModel;
import com.structis.vip.shared.model.DelegationMdlModel;
import com.structis.vip.shared.model.DelegationNatureModel;
import com.structis.vip.shared.model.DemDomModel;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.FieFieldModel;
import com.structis.vip.shared.model.FieldRuleModel;
import com.structis.vip.shared.model.LanguageModel;
import com.structis.vip.shared.model.PerimetreTypeModel;

public class ListDelegationModelPanel extends LayoutContainer {
	private static int WIDTH = 800;
	private static int GRID_HEIGHT = 145;
	private final Messages messages = GWT.create(Messages.class);
	private final FormData formData = new FormData("98%");

	SimpleEventBus bus;
	private ListStore<DelegationNatureModel> lstDelegationNature = new ListStore<DelegationNatureModel>();
	private ClientDelegationNatureServiceAsync clientDelegationNatureService = ClientDelegationNatureServiceAsync.Util
			.getInstance();
	private ClientDelegationModelServiceAsync clientDelegationModelServiceAsync = ClientDelegationModelServiceAsync.Util
			.getInstance();
	private ClientPerimetreTypeServiceAsync clientPerimetreTypeServiceAsync = ClientPerimetreTypeServiceAsync.Util
			.getInstance();
	private ClientCollaborateurTypeServiceAsync clientCollaborateurTypeServiceAsync = ClientCollaborateurTypeServiceAsync.Util
			.getInstance();
	private ClientLanguageServiceAsync clientLanguageServiceAsync = ClientLanguageServiceAsync.Util.getInstance();
	private ClientDemDomServiceAsync clientDemDomServiceAsync = ClientDemDomServiceAsync.Util.getInstance();
	
	private ClientFieldRuleServiceAsync clientFieldRuleService = ClientFieldRuleServiceAsync.Util.getInstance();
	private ClientFieldServiceAsync clientFieldService = ClientFieldServiceAsync.Util.getInstance();
	
	private ListStore<LanguageModel> languages = new ListStore<LanguageModel>();
	private ListStore<PerimetreTypeModel> perimetreTypes = new ListStore<PerimetreTypeModel>();
	private ListStore<CollaborateurTypeModel> delegantTypes = new ListStore<CollaborateurTypeModel>();

	private ListStore<DelegationMdlModel> ptyStore;
	private ListStore<DelegationMdlModel> cotStore;
	
	private EditorGrid<DelegationMdlModel> delegationModelGrid;
	private ComboBox<DelegationNatureModel> cbNature;
	private ComboBox<LanguageModel> cbLanguage;

	private Integer group = 0;
	private Button btnSave;
	private Button btnAnnuler;
	private Button btnEdit;
	private Button btnAddDocument;

	private TabPanel tab;
	private FieldRulePanel rulePanel;
	private LayoutContainer filterPanel;
	private ContentPanel delegationModelPanel;
	
	private ContentPanel delegantTypePanel;
	
	private ListStore<DocumentMdlModel> documentMdlModels;
	private Grid<DocumentMdlModel> documentGrid;
	
	private DelegationModelEvent mEvent;
	private CheckBox cbHasMultipleDelegation;
	private CheckBox cbHasMultipleDelegataire;
	private CheckBox cbSubDelegation;
	
	public ListDelegationModelPanel(SimpleEventBus bus) {
		this.bus = bus;

		rulePanel = new FieldRulePanel(this.bus);
		
		setLayout(new BorderLayout());
		setStyleAttribute("padding", "10px");
		setStyleAttribute("paddingTop", "90px");
		setScrollMode(Scroll.AUTO);

		addBackLink();
		initFilter();
		initButton();
		initGrid();
		initDocument();

		this.bus.addHandler(DelegationModelEvent.getType(), new DelegationModelHandler() {
			@Override
			public void onLoadAction(DelegationModelEvent event) {
				AppUtil.putInAdminEditMode();
				mEvent = event;
				disableEvents(true);
				switch (event.getMode()) {
				case DelegationModelEvent.MODE_IS_NEW:
					newMode(event);
					break;
				case DelegationModelEvent.MODE_IS_EDIT:
					editMode(event);
					break;
				case DelegationModelEvent.MODE_IS_UPDATE_DOCUMENT:
					changeDocumentTable();
					break;
				}
				checkAndInsertFieldRule(event);
				disableEvents(false);
			}
		});
	}
	
	@Override
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		GWT.log(this.getClass().getName() + ":onRender");
	}

	private void newMode(DelegationModelEvent event) {
		// set group 
		group = 0;
		
		// remove last information
		cbNature.setValue(null);
		cbLanguage.setValue(null);
		cbHasMultipleDelegation.setValue(false);
		cbHasMultipleDelegataire.setValue(false);
		cbSubDelegation.setValue(false);
		ptyStore.removeAll();
		cotStore.removeAll();
		tab.setVisible(false);
		documentMdlModels.removeAll();
		
		// update button
		btnEdit.setEnabled(false);
		btnAddDocument.setEnabled(false);
		btnSave.setText(messages.commonValiderButton());
		btnAnnuler.setText(messages.commonAnnulerButton());
		
		// init data
		initData();
	}

	private void editMode(DelegationModelEvent event) {
		// set group
		group = event.getGroup();

		// set value for edit
		cbNature.setValue(event.getNatureModel());
		cbLanguage.setValue(event.getLanguageModel());
		cbHasMultipleDelegation.setValue(event.getHasMultipleDelegation() == null || event.getHasMultipleDelegation() == 0?false:true);
		cbHasMultipleDelegataire.setValue(event.getHasMultipleDelegataire() == null || event.getHasMultipleDelegataire() == 0?false:true);
		cbSubDelegation.setValue(event.getSubDelegation() == null || event.getSubDelegation() == 0?false:true);

		// update button
		btnEdit.setEnabled(true);
		btnAddDocument.setEnabled(true);
		tab.setVisible(true);
		btnSave.setText(messages.commonModifierButton());
		btnAnnuler.setText(messages.commonAnnulerButton());
		
		// remove last information
		ptyStore.removeAll();
		documentMdlModels.removeAll();

		// init data
		initData();		
		changeDocumentTable();
		
		delegationModelGrid.mask(messages.commonloadingdata());
		clientDelegationModelServiceAsync.getDelegationModelsByGroup(group,
				new AsyncCallback<List<DelegationMdlModel>>() {
					@Override
					public void onSuccess(List<DelegationMdlModel> arg0) {
						cotStore.removeAll();
						ptyStore.removeAll();
						splitIntoStores(arg0, cotStore, ptyStore);
						//ptyStore.add(arg0);
						delegationModelGrid.unmask();
					}

					

					private void splitIntoStores(List<DelegationMdlModel> arg0,
							ListStore<DelegationMdlModel> cotStore,
							ListStore<DelegationMdlModel> ptyStore) {
						for (DelegationMdlModel m: arg0) {														
							if (m.getPerimetreType() != null && !ptyStore.contains(m)) {
								ptyStore.add(m);
							}
							if (m.getCollaborateurType() != null && !cotStore.contains(m)) {
								cotStore.add(m);
							}
						}
						
					}



					@Override
					public void onFailure(Throwable arg0) {
						delegationModelGrid.unmask();
					}
				});
	}
	
	private void checkAndInsertFieldRule(final DelegationModelEvent event) {
		if (event.getGroup() != 0) { 
			clientFieldRuleService.getRulesByDemGroup(event.getGroup(), new AsyncCallback<List<FieldRuleModel>>() {
				@Override
				public void onSuccess(List<FieldRuleModel> arg0) {
					if (arg0.size() == 0) {
						clientFieldService.getFieldsByEntiteId(SessionServiceImpl.getInstance().getEntiteContext().getEntId(), new AsyncCallback<List<FieFieldModel>>() {
							
							@Override
							public void onSuccess(List<FieFieldModel> arg0) {
								if (arg0 != null && arg0.size() > 0) {
									List<FieldRuleModel> fields = new ArrayList<FieldRuleModel>();
									FieldRuleModel model;
									for (FieFieldModel fieFieldModel : arg0) {
										model = new FieldRuleModel();
										model.setGroup(event.getGroup());
										model.setIsDisplayed(0);
										model.setIsRequired(0);
										model.setField(fieFieldModel);
										
										fields.add(model);
									}
									
									clientFieldRuleService.insertList(fields, new AsyncCallback<List<FieldRuleModel>>() {
										@Override
										public void onSuccess(List<FieldRuleModel> arg0) {
											FieldRuleEvent frEvent = new FieldRuleEvent();
											frEvent.setEntite(event.getEntiteModel());
											frEvent.setGroup(event.getGroup());
											bus.fireEvent(frEvent);
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
					} else {
						FieldRuleEvent frEvent = new FieldRuleEvent();
						frEvent.setEntite(event.getEntiteModel());
						frEvent.setGroup(event.getGroup());
						bus.fireEvent(frEvent);
					}
				}
				
				@Override
				public void onFailure(Throwable arg0) {
				}
			});
		}
	}

	/**
	 * initilize top filter
	 */
	private void initFilter() {
		// setup top layout
		filterPanel = new LayoutContainer();		
		filterPanel.setSize(WIDTH, -1);
		filterPanel.setStyleAttribute("marginTop", "40px");
		filterPanel.setStyleAttribute("marginLeft", "10px");
		filterPanel.setLayout(new ColumnLayout());

		// setup sub layout for first field
		LayoutContainer lcSubTop = new LayoutContainer();
		lcSubTop.setStyleAttribute("paddingRight", "5px");
		FormLayout flSubTop = new FormLayout();
		flSubTop.setLabelWidth(60);
		flSubTop.setLabelAlign(LabelAlign.LEFT);
		lcSubTop.setLayout(flSubTop);

		// setup sub layout for first field
		lcSubTop = new LayoutContainer();
		//lcSubTop.setStyleAttribute("paddingRight", "10px");
		flSubTop = new FormLayout();
		flSubTop.setLabelWidth(60);
		flSubTop.setLabelAlign(LabelAlign.LEFT);
		lcSubTop.setLayout(flSubTop);

		cbNature = new ComboBox<DelegationNatureModel>();
		cbNature.setFieldLabel(messages.nature());
		cbNature.setDisplayField(DelegationNatureModel.DELE_NATURE_NAME);
		cbNature.setStore(lstDelegationNature);
		cbNature.setTriggerAction(TriggerAction.ALL);
		cbNature.setEditable(false);
		cbNature.setAllowBlank(false);
		cbNature.setSimpleTemplate("<span title=\"{" + cbNature.getDisplayField() + "}\">{"
				+ cbNature.getDisplayField() + "}</span>");
		lcSubTop.add(cbNature, formData);

		filterPanel.add(lcSubTop, new ColumnData(.5));

		// setup sub layout for first field
		lcSubTop = new LayoutContainer();
		//lcSubTop.setStyleAttribute("paddingRight", "10px");
		flSubTop = new FormLayout();
		flSubTop.setLabelWidth(60);
		flSubTop.setLabelAlign(LabelAlign.LEFT);
		lcSubTop.setLayout(flSubTop);

		cbLanguage = new ComboBox<LanguageModel>();
		cbLanguage.setFieldLabel(messages.commonlanguage());
		cbLanguage.setDisplayField(LanguageModel.LAG_NAME);
		cbLanguage.setStore(languages);
		cbLanguage.setWidth(10);
		cbLanguage.setTriggerAction(TriggerAction.ALL);
		cbLanguage.setEditable(false);
		cbLanguage.setAllowBlank(false);
		lcSubTop.add(cbLanguage, formData);

		filterPanel.add(lcSubTop, new ColumnData(0.25));

		LayoutContainer selectPanel = new LayoutContainer();		
		selectPanel.setSize(120, 120);
		selectPanel.setStyleAttribute("marginTop", "0px");
		selectPanel.setStyleAttribute("marginLeft", "0px");
		selectPanel.setLayout(new RowLayout());
		
		lcSubTop = new LayoutContainer();
//		lcSubTop.setStyleAttribute("paddingRight", "10px");
		flSubTop = new FormLayout();
		flSubTop.setLabelWidth(30);
		flSubTop.setLabelAlign(LabelAlign.LEFT);
		lcSubTop.setLayout(flSubTop);
		cbHasMultipleDelegation = new CheckBox();
		cbHasMultipleDelegation.setLabelSeparator("");
		cbHasMultipleDelegation.setBoxLabel("Délégation multiple");
		lcSubTop.add(cbHasMultipleDelegation, formData);		
		
		selectPanel.add(lcSubTop);
		
		lcSubTop = new LayoutContainer();
		flSubTop = new FormLayout();
		flSubTop.setLabelWidth(30);
		flSubTop.setLabelAlign(LabelAlign.LEFT);
		lcSubTop.setLayout(flSubTop);
		cbHasMultipleDelegataire = new CheckBox();
		cbHasMultipleDelegataire.setLabelSeparator("");
		cbHasMultipleDelegataire.setBoxLabel("Délégataire multiple");
		lcSubTop.add(cbHasMultipleDelegataire, formData);
		selectPanel.add(lcSubTop);
		
		lcSubTop = new LayoutContainer();
		flSubTop = new FormLayout();
		flSubTop.setLabelWidth(30);
		flSubTop.setLabelAlign(LabelAlign.LEFT);
		lcSubTop.setLayout(flSubTop);
		cbSubDelegation = new CheckBox();
		cbSubDelegation.setLabelSeparator("");
		cbSubDelegation.setBoxLabel("Sous Delegation");
		lcSubTop.add(cbSubDelegation, formData);
		selectPanel.add(lcSubTop);
		
		filterPanel.add(selectPanel, new ColumnData(0.25));
			
		this.add(filterPanel);
	}

	private void initGrid() {
		btnAddDocument = new Button(messages.delegationmodeladddocumentbutton());
		btnAddDocument.setIcon(IconHelper.createPath("html/edit-icon.png"));
		btnAddDocument.setEnabled(false);
		btnAddDocument.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (group != 0) {
					ApplyDocumentDialog dialog = new ApplyDocumentDialog(bus);
					dialog.setData(SessionServiceImpl.getInstance().getEntiteContext(), group);
					dialog.show();
				}
			}
		});
		
		ContentPanel mainPanel = new ContentPanel();
		mainPanel.setButtonAlign(HorizontalAlignment.LEFT);
		mainPanel.setHeading(messages.delegationmodelheading());
		//mainPanel.setHeaderVisible(false);
		mainPanel.setBodyBorder(true);
		mainPanel.setBorders(true);
		mainPanel.setLayout(new RowLayout());
		
		mainPanel.setScrollMode(Scroll.NONE);
		
		LayoutContainer delegationModelContainer = new LayoutContainer();
		delegationModelContainer.setHeight(300);
		delegationModelContainer.setLayout(new ColumnLayout());
		
		mainPanel.add(delegationModelContainer);
		
		initPerimetreTypeGrid();
		initDelegantTypeGrid();
		
		delegationModelContainer.add(delegationModelPanel, new ColumnData(0.5));
		delegationModelContainer.add(delegantTypePanel, new ColumnData(0.5));
		mainPanel.add(delegationModelContainer, new RowData(1,GRID_HEIGHT));
		mainPanel.addButton(btnSave);
		mainPanel.addButton(btnAnnuler);
		
		BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH, 210);
		northData.setMargins(new Margins(22, 0, 5, 0));
		northData.setCollapsible(true);
		northData.setSplit(true);
		add(mainPanel, northData);
		//add(delegationModelPanel, northData);
	}

	private void initPerimetreTypeGrid() {
		ToolBar toolBar = new ToolBar();
		final Button btnAdd = new Button(messages.delegationmodelruleaddbutton());

		btnAdd.setIcon(IconHelper.createPath("html/icon/perimetresetting.png"));
		btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				ApplyPerimetreTypeDialog dialog = new ApplyPerimetreTypeDialog(bus);
				dialog.setData(SessionServiceImpl.getInstance().getEntiteContext(), ptyStore.getModels());
				dialog.show();
			}
		});
		toolBar.add(btnAdd);

		ptyStore = new ListStore<DelegationMdlModel>();

		ColumnConfig cfPerimetreType = new ColumnConfig("perimetreType.name", messages.delegationmodelperimetretype(),
				320);

		List<ColumnConfig> config = new ArrayList<ColumnConfig>();
		config.add(cfPerimetreType);

		final ColumnModel cm = new ColumnModel(config);

		delegationModelGrid = new EditorGrid<DelegationMdlModel>(ptyStore, cm);
		delegationModelGrid.setBorders(false);
		delegationModelGrid.getView().setAutoFill(true);
		delegationModelGrid.getView().setForceFit(true);		
				
		delegationModelGrid.setAutoWidth(true);
		WindowResizeBinder.bind(delegationModelGrid);
				
		
		delegationModelPanel = new ContentPanel();
		delegationModelPanel.setHeaderVisible(false);
		delegationModelPanel.setTopComponent(toolBar);
		
		delegationModelPanel.setLayout(new FitLayout());
		delegationModelPanel.setStyleAttribute("paddingRight", "10px");		
	    
		delegationModelPanel.setButtonAlign(HorizontalAlignment.LEFT);
		delegationModelPanel.add(delegationModelGrid);//, new RowData(-1, 200));
		delegationModelPanel.setHeight(GRID_HEIGHT);

		delegationModelGrid.getAriaSupport().setLabelledBy(delegationModelPanel.getHeader().getId() + "-label");
		
		bus.addHandler(RefreshPerimetreTypeGridEvent.getType(), new RefreshPerimetreTypeGridHandler() {
			public void onLoadAction(RefreshPerimetreTypeGridEvent event) {
				ptyStore.removeAll();				
				if (event.getPerimetreTypes() != null) {					
					for (PerimetreTypeModel c : event.getPerimetreTypes() ) {
						DelegationMdlModel mdl = new DelegationMdlModel();
						mdl.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
						mdl.setDelegationNature(cbNature.getValue());
						mdl.setLanguage(cbLanguage.getValue());						
						mdl.setPerimetreType(c);
						mdl.setGroup(group);
						ptyStore.add(mdl);
					}
										
				}
			}
		});
		
	}
	private void initDelegantTypeGrid() {
		cotStore = new ListStore<DelegationMdlModel>();
		ToolBar toolBar = new ToolBar();
		final Button btnAdd = new Button(messages.delegationmodelruleadddelegantbutton());
		btnAdd.setIcon(IconHelper.createPath("html/icon/delegantsetting.png"));						
		btnAdd.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ApplyDelegantTypeDialog dialog = new ApplyDelegantTypeDialog(bus);
				dialog.setData(SessionServiceImpl.getInstance().getEntiteContext(), cotStore.getModels());
				dialog.show();
			}
		});
		
		toolBar.add(btnAdd);		
		
		
		// setup grid for external controlers
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		
		// Column documents
		ColumnConfig delegantTypeColumn = new ColumnConfig("collaborateurType.name", messages.delegationmodeldeleganttype(),
				320);		
		configs.add(delegantTypeColumn);

		// setup column model
		ColumnModel columnModel = new ColumnModel(configs);

		
		Grid<DelegationMdlModel> delegantGrid = new Grid<DelegationMdlModel>(cotStore, columnModel);
		delegantGrid.setBorders(true);
		delegantGrid.getView().setAutoFill(true);
		delegantGrid.getView().setForceFit(true);		
		
		//delegantGrid.setAutoWidth(true);		
		//WindowResizeBinder.bind(delegantGrid);
		
		
		delegantTypePanel = new ContentPanel();
		delegantTypePanel.setStyleAttribute("paddingLeft", "10px");
		
		delegantTypePanel.setHeaderVisible(false);
		delegantTypePanel.setTopComponent(toolBar);
		delegantTypePanel.setLayout(new FitLayout());
		
		delegantTypePanel.setButtonAlign(HorizontalAlignment.LEFT);
		delegantTypePanel.add(delegantGrid);//, new RowData(-1, 200));
		delegantTypePanel.setHeight(GRID_HEIGHT);
		
		
		bus.addHandler(RefreshDelegantGridEvent.getType(), new RefreshDelegantGridHandler() {
			public void onLoadAction(RefreshDelegantGridEvent event) {
				cotStore.removeAll();
				if (event.getDelegantTypes() != null) {					
					for (CollaborateurTypeModel c : event.getDelegantTypes() ) {
						DelegationMdlModel mdl = new DelegationMdlModel();
						mdl.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
						mdl.setDelegationNature(cbNature.getValue());
						mdl.setLanguage(cbLanguage.getValue());						
						mdl.setCollaborateurType(c);
						mdl.setGroup(group);
						cotStore.add(mdl);
					}
										
				}
			}
		});
		
	}
	private void initData() {
		clientDelegationNatureService.findNatureByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(), new AsyncCallback<List<DelegationNatureModel>>() {
			@Override
			public void onSuccess(List<DelegationNatureModel> arg0) {
				lstDelegationNature.removeAll();
				lstDelegationNature.add(arg0);
				cbNature.setStore(lstDelegationNature);
				if (cbNature.getValue() == null) {
					if (arg0 != null && arg0.size() > 0) {
						DelegationNatureModel em = arg0.get(0);
						cbNature.select(0);
						cbNature.setValue(em);
					}
				}
			}

			@Override
			public void onFailure(Throwable arg0) {
			}
		});
		
		clientLanguageServiceAsync.getLanguages(new AsyncCallback<List<LanguageModel>>() {
			@Override
			public void onSuccess(List<LanguageModel> arg0) {
				languages.removeAll();
				languages.add(arg0);
				if (cbLanguage.getValue() == null) {
					if (arg0 != null && arg0.size() > 0) {
						LanguageModel em = arg0.get(0);
						cbLanguage.select(0);
						cbLanguage.setValue(em);
					}
				}
			}

			@Override
			public void onFailure(Throwable arg0) {
			}
		});
		clientPerimetreTypeServiceAsync.getPerimetreTypes(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
				new AsyncCallback<List<PerimetreTypeModel>>() {
					@Override
					public void onSuccess(List<PerimetreTypeModel> arg0) {
						perimetreTypes.removeAll();
						perimetreTypes.add(arg0);
					}

					@Override
					public void onFailure(Throwable arg0) {
					}
				});

		clientCollaborateurTypeServiceAsync.getCollaborateurTypeByEntite(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
				new AsyncCallback<List<CollaborateurTypeModel>>() {
					@Override
					public void onSuccess(List<CollaborateurTypeModel> arg0) {
						delegantTypes.removeAll();
						delegantTypes.add(arg0);
					}

					@Override
					public void onFailure(Throwable arg0) {
					}
				});
	}

//	private Object buildPerimetreTypeColumn(final DelegationMdlModel model) {
////		for (PerimetreTypeModel pt: perimetreTypes.getModels()) {
////			if (pt.getId() == model.getPerimetreType().getId()) {
////				return new HTML(pt.getName());
////			}
////		}		
////		return new HTML("");
//		ComboBox<PerimetreTypeModel> cbPerimetreType = new ComboBox<PerimetreTypeModel>();
//
//		cbPerimetreType.setWidth(300);
//		cbPerimetreType.setForceSelection(true);
//		cbPerimetreType.setTriggerAction(TriggerAction.ALL);
//		cbPerimetreType.setDisplayField(PerimetreTypeModel.PERIMETRE_TYPE_NAME);
//		cbPerimetreType.setEditable(false);
//		cbPerimetreType.setStore(perimetreTypes);
//		if (model.getPerimetreType() != null) {
//			cbPerimetreType.setValue(model.getPerimetreType());
//		} else if (perimetreTypes != null && perimetreTypes.getCount() != 0) {
//			cbPerimetreType.setValue(perimetreTypes.getAt(0));
//			model.setPerimetreType(perimetreTypes.getAt(0));
//		}
//
//		cbPerimetreType.addSelectionChangedListener(new SelectionChangedListener<PerimetreTypeModel>() {
//			@Override
//			public void selectionChanged(SelectionChangedEvent<PerimetreTypeModel> se) {
//				delegationModelGrid.getSelectionModel().getSelectedItem().setPerimetreType(se.getSelectedItem());
//			}
//		});
//
//		return cbPerimetreType;
//	}

//	private Object buildDelegantTypeColumn(final DelegationMdlModel model) {
//		ComboBox<CollaborateurTypeModel> cbDelegantType = new ComboBox<CollaborateurTypeModel>();
//
//		cbDelegantType.setWidth(300);
//		cbDelegantType.setForceSelection(true);
//		cbDelegantType.setTriggerAction(TriggerAction.ALL);
//		cbDelegantType.setDisplayField(CollaborateurTypeModel.COT_NAME);
//		cbDelegantType.setEditable(false);
//		cbDelegantType.setStore(delegantTypes);
//
//		if (model.getCollaborateurType() != null) {
//			cbDelegantType.setValue(model.getCollaborateurType());
//		} else if (delegantTypes != null && delegantTypes.getCount() != 0) {
//			//cbDelegantType.setValue(delegantTypes.getAt(0));
//			//model.setCollaborateurType(delegantTypes.getAt(0));
//		}
//
//		cbDelegantType.addSelectionChangedListener(new SelectionChangedListener<CollaborateurTypeModel>() {
//			@Override
//			public void selectionChanged(SelectionChangedEvent<CollaborateurTypeModel> se) {
//				delegationModelGrid.getSelectionModel().getSelectedItem().setCollaborateurType(se.getSelectedItem());
//			}
//		});
//
//		return cbDelegantType;
//	}
//
//	private Object buildActionColumn(final DelegationMdlModel model) {
//		LayoutContainer lcTop = new LayoutContainer();
//		lcTop.setSize(100, -1);
//		lcTop.setLayout(new ColumnLayout());
//
//		// setup sub layout for first field
//		LayoutContainer lcSubTop = new LayoutContainer();
//		lcSubTop.setStyleAttribute("paddingRight", "2px");
//		FormLayout flSubTop = new FormLayout();
//		flSubTop.setLabelWidth(50);
//		flSubTop.setLabelAlign(LabelAlign.LEFT);
//		lcSubTop.setLayout(flSubTop);
//
//		final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {
//			public void handleEvent(MessageBoxEvent ce) {
//				Button btn = ce.getButtonClicked();
//				String txtReturn = ((Button) ce.getDialog().getButtonBar().getItem(0)).getText();
//				if (txtReturn.equals(btn.getText())) {
//					ptyStore.remove(model);
//				}
//			}
//		};
//
//		final Button btnDelete = new Button(messages.commonSupprimer());
//		btnDelete.setIcon(IconHelper.createPath("html/delete-icon.png"));
//
//		btnDelete.addSelectionListener(new SelectionListener<ButtonEvent>() {
//			@Override
//			public void componentSelected(ButtonEvent ce) {
//				MessageBox box = new MessageBox();
//				box.setButtons(MessageBox.YESNO);
//				box.setIcon(MessageBox.INFO);
//				box.setTitle(messages.commonConfirmation());
//				box.addCallback(l);
//				box.setMessage(messages.delegationmodeldeleteconfirm());
//				((Button) box.getDialog().getButtonBar().getItem(0)).setText(messages.commonOui());
//				((Button) box.getDialog().getButtonBar().getItem(1)).setText(messages.commonNon());
//				box.show();
//			}
//		});
//
//		lcSubTop.add(btnDelete, formData);
//		lcTop.add(lcSubTop, new ColumnData(1));
//
//		return lcTop;
//	}

	
	private void initDocument() {
		// setup grid for document view
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		
		// Column documents
		ColumnConfig column = new ColumnConfig();
		column.setId(DocumentMdlModel.DOM_NAME);
//		column.setHeader(messages.delegationmodeldocumentheader());
		column.setWidth(400);
		column.setRowHeader(true);
		column.setSortable(false);
		configs.add(column);

		// setup column model
		ColumnModel cm = new ColumnModel(configs);
		
		// Content panel with header
		ContentPanel cp = new ContentPanel();
		cp.setBodyBorder(false);
		cp.setHeaderVisible(false);
		cp.setButtonAlign(HorizontalAlignment.CENTER);
		cp.setLayout(new RowLayout());
		cp.setAutoHeight(true);
		cp.setWidth(WIDTH);
		cp.setButtonAlign(HorizontalAlignment.LEFT);
		cp.setStyleAttribute("padding", "2px");
		
		documentMdlModels = new ListStore<DocumentMdlModel>();
		documentGrid = new Grid<DocumentMdlModel>(documentMdlModels, cm);
		documentGrid.setStyleAttribute("borderTop", "none");
		documentGrid.setAutoHeight(true);
		documentGrid.setAutoExpandColumn(DocumentMdlModel.DOM_NAME);
		documentGrid.setBorders(false);
		documentGrid.setStripeRows(true);
		documentGrid.setColumnLines(true);
		documentGrid.setColumnReordering(true);
		documentGrid.getView().setAutoFill(true);
		documentGrid.getView().setForceFit(true);
		WindowResizeBinder.bind(documentGrid);
		documentGrid.getAriaSupport().setLabelledBy(cp.getId() + "-label");
		
		cp.add(documentGrid);
		cp.addButton(btnAddDocument);
		
		tab = new TabPanel();
		tab.setWidth(WIDTH);
		tab.setAutoHeight(true);
		tab.setVisible(false);
		
		TabItem documentTab = new TabItem(messages.delegationmodeldocumentheader());
		documentTab.add(cp);
		documentTab.setAutoHeight(true);
		
		TabItem ruleTab = new TabItem(messages.commonrulebutton());
		ruleTab.add(rulePanel);
		ruleTab.setAutoHeight(true);
		
		tab.add(documentTab);
		tab.add(ruleTab);
		
//		this.add(tab);
		
		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
		centerData.setMargins(new Margins(5, 0, 0, 0));
		centerData.setSplit(true);
		centerData.setCollapsible(true);
		
		add(tab, centerData);
	}
	
	/**
	 * initilize bottom button
	 */
	private void initButton() {
		HorizontalPanel hpButton = new HorizontalPanel();
		hpButton.setHorizontalAlign(HorizontalAlignment.LEFT);
		hpButton.setTableWidth("200");

		btnSave = new Button(messages.commonmodifierbutton());
		btnEdit = new Button(messages.commonrulebutton());

		btnSave.setIcon(IconHelper.createPath("html/save-icon.png"));
		
		btnAnnuler = new Button(messages.commonAnnulerButton());
		btnAnnuler.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				ContentEvent contentEvent = new ContentEvent();
				contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GROUP_DELEGATION_MODEL_ADMIN_FORM);
				contentEvent.setEvent(new LoadGroupDelegationModelEvent());
				bus.fireEvent(contentEvent);
				AppUtil.removeAdminInEditMode();
			}
		});		
		
		
		btnSave.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (ptyStore.getCount() != 0) {
					for (int i = 0; i < ptyStore.getCount(); i++) {
						ptyStore.getAt(i).setId(null);
						ptyStore.getAt(i).setEntite(SessionServiceImpl.getInstance().getEntiteContext());
						ptyStore.getAt(i).setDelegationNature(cbNature.getValue());
						ptyStore.getAt(i).setLanguage(cbLanguage.getValue());
						ptyStore.getAt(i).setHasMultipleDelegation(cbHasMultipleDelegation.getValue()?1:0);
						ptyStore.getAt(i).setHasMultipleDelegataire(cbHasMultipleDelegataire.getValue()?1:0);
						ptyStore.getAt(i).setSubDelegation(cbSubDelegation.getValue()?1:0);
					}
				}
				if (cotStore.getCount() != 0) {
					for (int i = 0; i < cotStore.getCount(); i++) {
						cotStore.getAt(i).setId(null);
						cotStore.getAt(i).setEntite(SessionServiceImpl.getInstance().getEntiteContext());
						cotStore.getAt(i).setDelegationNature(cbNature.getValue());
						cotStore.getAt(i).setLanguage(cbLanguage.getValue());
						cotStore.getAt(i).setHasMultipleDelegation(cbHasMultipleDelegation.getValue()?1:0);
						cotStore.getAt(i).setHasMultipleDelegataire(cbHasMultipleDelegataire.getValue()?1:0);
						cotStore.getAt(i).setSubDelegation(cbSubDelegation.getValue()?1:0);
					}
				}
					final List<DelegationMdlModel> allModel = new ArrayList<DelegationMdlModel>();
					allModel.addAll(ptyStore.getModels());
					allModel.addAll(cotStore.getModels());
					if (group == 0) {
						
						clientDelegationModelServiceAsync.insert(allModel, 0, new AsyncCallback<Integer>() {
							@Override
							public void onSuccess(Integer arg0) {
								if (arg0 != 0) {
									group = arg0;
									mEvent.setGroup(group);

									btnSave.setText(messages.commonModifierButton());
									tab.setVisible(true);
									btnEdit.setEnabled(true);
									btnAddDocument.setEnabled(true);
									
									checkAndInsertFieldRule(mEvent);
									AppUtil.removeAdminInEditMode();
									Info.display(messages.commoninfo(), messages.delegationmodelsavesuccess());
								} else {
									Info.display(messages.commonerror(), messages.delegationmodelsavefailed());
								}								
							}

							@Override
							public void onFailure(Throwable arg0) {
								arg0.printStackTrace();
								Info.display(messages.commonerror(), messages.delegationmodelsavefailed());
							}
						});
					} else {
						clientDelegationModelServiceAsync.deleteByGroup(group, new AsyncCallback<Boolean>() {
							@Override
							public void onSuccess(Boolean arg0) {
								clientDelegationModelServiceAsync.insert(allModel, group,
										new AsyncCallback<Integer>() {
											@Override
											public void onSuccess(Integer arg0) {
												if (arg0 != 0) {
													AppUtil.removeAdminInEditMode();
													Info.display(messages.commoninfo(), messages.delegationmodelupdatesuccess());
												} else {
													Info.display(messages.commonerror(), messages.delegationmodelupdatefailed());
												}
											}

											@Override
											public void onFailure(Throwable arg0) {
												Info.display(messages.commonerror(), messages.delegationmodelupdatefailed());
											}
										});
							}

							@Override
							public void onFailure(Throwable arg0) {
							}
						});
					}
				
			}
		});
		
		btnEdit.setIcon(IconHelper.createPath("html/edit-icon.png"));
		btnEdit.setEnabled(false);
		btnEdit.addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (group != 0) {
					FieldRuleEvent fieldRuleEvent = new FieldRuleEvent();
					fieldRuleEvent.setGroup(group);
					fieldRuleEvent.setEntite(SessionServiceImpl.getInstance().getEntiteContext());
					
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_RULE_ADMIN_FORM);
					contentEvent.setEvent(fieldRuleEvent);
					bus.fireEvent(contentEvent);
				}
			}
		});
		
		hpButton.add(btnSave);
		hpButton.add(btnEdit);
		
//		this.add(hpButton, new FlowData(10, 0, 0, 0));
	}

	private void addBackLink() {
		LayoutContainer backLink = new LayoutContainer();
		backLink.setSize(700, -1);
		Label lblBack = new Label(messages.delegationruleback());
		lblBack.setStyleName("x-link-item");
		
		backLink.setStyleAttribute("marginTop", "10px");
		backLink.setStyleAttribute("marginLeft", "10px");
		backLink.add(lblBack);

		lblBack.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				if (!AppUtil.checkToShowWarningInAdminEditMode(false)) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GROUP_DELEGATION_MODEL_ADMIN_FORM);
					contentEvent.setEvent(new LoadGroupDelegationModelEvent());
					bus.fireEvent(contentEvent);
				}
			}
		});
		this.add(backLink);
	}
	
	private void changeDocumentTable() {
		if (group != 0) {
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
	}
}