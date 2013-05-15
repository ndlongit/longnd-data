package com.structis.vip.client.panel;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.ContentEventHandler;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.LoadDelegationTypeEvent;
import com.structis.vip.client.event.LoadDocumentEvent;
import com.structis.vip.client.event.ModifyCategoryEvent;
import com.structis.vip.client.event.ModifyChantierTypeEvent;
import com.structis.vip.client.event.ModifyCollaborateurTypeEvent;
import com.structis.vip.client.event.ModifyControlTypeEvent;
import com.structis.vip.client.event.ModifyDelegantTypeGroupEvent;
import com.structis.vip.client.event.ModifyDelegationTypeEvent;
import com.structis.vip.client.event.ModifyDocTypeEvent;
import com.structis.vip.client.event.ModifyEntiteJuridiqueEvent;
import com.structis.vip.client.event.ModifyFormationEvent;
import com.structis.vip.client.event.ModifyLanguageEvent;
import com.structis.vip.client.event.ModifyNatureEvent;
import com.structis.vip.client.event.ModifyPerimetreTypeEvent;
import com.structis.vip.client.event.ModifyStatusEvent;
import com.structis.vip.client.event.control.ModifyExternControllerEvent;
import com.structis.vip.client.message.ActionMessages;
import com.structis.vip.client.navigation.NavigationFactory;
import com.structis.vip.client.navigation.NavigationService;
import com.structis.vip.client.service.ClientEntiteJuridiqueServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.shared.model.EntiteJuridiqueModel;

public class ReferentielTabPanel extends LayoutContainer {
	public final static String ACTION_ADMIN_NATURE = "actionAdminNature";
	public final static String ACTION_ADMIN_TYPE_DELEGATION = "actionAdminTypeDelegation";
	public final static String ACTION_ADMIN_STATUT_DELEGATION = "actionAdminStatutDelegation";
	public final static String ACTION_ADMIN_TYPE_CHANTIER = "actionAdminTypeChantier";
	public final static String ACTION_ADMIN_LANGUE = "actionAdminLangue";
	public final static String ACTION_ADMIN_DOCTYPE = "actionAdminDocType";
	public final static String ACTION_ADMIN_CATEGORY = "actionAdminCategory";
	public final static String ACTION_ADMIN_ENTITE_JURIDIQUE = "actionAdminEntiteJuridique";
	public final static String ACTION_ADMIN_FORMATION = "actionAdminFormations";
	public final static String ACTION_ADMIN_TYPE_CONTROLE = "actionAdminTypeControle";
	public final static String ACTION_ADMIN_EXTERNAL_CONTROLLER = "actionAdminExternalController";
	public static final String ACTION_ADMIN_TYPE_PERIMETRE = "actionAdminTypePerimetre";
	static final String ACTION_ADMIN_DELEGANT_TYPE_GROUP =  "actionAdminDelegantTypeGroup";
	static final String ACTION_ADMIN_DELEGANT_TYPE = "actionAdminDelegantType";
	
	
	ActionMessages actionMessages = GWT.create(ActionMessages.class);
	NavigationService navigation = NavigationFactory.getNavigation();
	TabPanel tabSet;
	
	
	private SimpleEventBus bus;
	
	private NatureListPanel natureListPanel;
	private NatureFormPanel natureFormPanel;
	
	private LanguageListPanel languageListPanel;
	private LanguageFormPanel languageFormPanel;
	
	private DocTypeListPanel docTypeListPanel;
	private DocTypeFormPanel docTypeFormPanel;
	
	private CategoryListPanel categoryListPanel;
	private CategoryFormPanel categoryFormPanel;

	
	private CollaborateurTypeListPanel delegantTypeListPanel;
	private CollaborateurTypeFormPanel delegantTypeFormPanel;
	
	private DelegantTypeGroupListPanel delegantTypeGroupListPanel;
	private DelegantTypeGroupFormPanel delegantTypeGroupFormPanel;
	
	private StatusListPanel statusListPanel;
	private StatusFormPanel statusFormPanel;

	private DelegationTypeListPanel delegationTypeListPanel;
	private DelegationTypeFormPanel delegationTypeFormPanel;
	
	private ChantierTypeListPanel chantierTypeListPanel;
	private ChantierTypeFormPanel chantierTypeFormPanel;
	
	private PerimetreTypeListPanel perimetreTypeListPanel;
	private PerimetreTypeFormPanel perimetreTypeFormPanel;
	
	private FormationListPanel formationListPanel;
	private FormationFormPanel formationFormPanel;
	
	private ControlTypeListPanel controlTypeListPanel;
	private ControlTypeFormPanel controlTypeFormPanel;
	
	private EntiteJuridiqueListPanel entiteJuridiqueListPanel;
	private EntiteJuridiqueViewPanel entiteJuridiqueViewPanel;
	private EntiteJuridiqueFormPanel entiteJuridiqueFormPanel;
	
	private ExternalControllerListPanel externalControllerListPanel;
	private ExternalControllerFormPanel externalControllerFormPanel;
	
	private LayoutContainer containerTabNature = new LayoutContainer();
	private LayoutContainer containerTabTypeDelegation = new LayoutContainer();
	private LayoutContainer containerTabStatutDelegation = new LayoutContainer();
	private LayoutContainer containerTabTypeChantier = new LayoutContainer();
	private LayoutContainer containerTabTypePerimetre = new LayoutContainer();
	private LayoutContainer containerTabLangue = new LayoutContainer();
	private LayoutContainer containerTabEntiteJuridique = new LayoutContainer();
	private LayoutContainer containerTabFormation = new LayoutContainer();
	private LayoutContainer containerTabTypeControle = new LayoutContainer();
	private LayoutContainer containerTabExternalController = new LayoutContainer();
	private LayoutContainer containerTabDocType = new LayoutContainer();
	private LayoutContainer containerTabCategory = new LayoutContainer();
	private LayoutContainer containerTabDelegantTypeGroup = new LayoutContainer();
	private LayoutContainer containerTabDelegantType = new LayoutContainer();
	
	public ReferentielTabPanel(SimpleEventBus bus) {
		this.bus = bus;
		
		natureListPanel = new NatureListPanel(bus);
		natureFormPanel = new NatureFormPanel(bus);
		
		languageListPanel = new LanguageListPanel(bus);
		languageFormPanel = new LanguageFormPanel(bus);
		
		statusListPanel = new StatusListPanel(bus);
		statusFormPanel = new StatusFormPanel(bus);
		
		chantierTypeListPanel = new ChantierTypeListPanel(bus);
		chantierTypeFormPanel = new ChantierTypeFormPanel(bus);
		
		perimetreTypeListPanel = new PerimetreTypeListPanel(bus);
		perimetreTypeFormPanel = new PerimetreTypeFormPanel(bus);
		
		delegationTypeListPanel = new DelegationTypeListPanel(bus);
		delegationTypeFormPanel = new DelegationTypeFormPanel(bus);
		
		formationListPanel = new FormationListPanel(bus);
		formationFormPanel = new FormationFormPanel(bus);
		
		controlTypeListPanel =  new ControlTypeListPanel(bus);
		controlTypeFormPanel = new ControlTypeFormPanel(bus);
		
		externalControllerListPanel =  new ExternalControllerListPanel(bus);
		externalControllerFormPanel = new ExternalControllerFormPanel(bus);
		
		entiteJuridiqueListPanel = new EntiteJuridiqueListPanel(bus);
		entiteJuridiqueViewPanel = new EntiteJuridiqueViewPanel(bus);
		entiteJuridiqueFormPanel = new EntiteJuridiqueFormPanel(bus);
		
		docTypeListPanel = new DocTypeListPanel(bus);
		docTypeFormPanel = new DocTypeFormPanel(bus);
		
		categoryListPanel = new CategoryListPanel(bus);
		categoryFormPanel = new CategoryFormPanel(bus);
		
		delegantTypeGroupListPanel = new DelegantTypeGroupListPanel(bus);
		delegantTypeGroupFormPanel = new DelegantTypeGroupFormPanel(bus);
		
		delegantTypeListPanel = new CollaborateurTypeListPanel(bus);
		delegantTypeFormPanel = new CollaborateurTypeFormPanel(bus);

		newContent(containerTabNature, natureListPanel);
		newContent(containerTabStatutDelegation, statusListPanel);
		newContent(containerTabTypeDelegation, delegationTypeListPanel);
		newContent(containerTabTypeChantier, chantierTypeListPanel);
		newContent(containerTabTypePerimetre, perimetreTypeListPanel);
		newContent(containerTabDelegantTypeGroup, delegantTypeGroupListPanel);
		newContent(containerTabDelegantType, delegantTypeListPanel);
		newContent(containerTabLangue, languageListPanel);
		newContent(containerTabDocType, docTypeListPanel);
		newContent(containerTabCategory, categoryListPanel);
		newContent(containerTabEntiteJuridique, entiteJuridiqueListPanel);
		newContent(containerTabFormation, formationListPanel);
		newContent(containerTabTypeControle, controlTypeListPanel);
		newContent(containerTabExternalController, externalControllerListPanel);
		
	    initTab(ACTION_ADMIN_NATURE);
	    
	    addHandler();
	}
	
	private void addHandler() {
		this.bus.addHandler(ContentEvent.getType(), new ContentEventHandler() {
			@Override
			public void onLoadAction(ContentEvent event) {
				disableEvents(true);

				switch (event.getMode()) {
				case ContentEvent.CHANGE_MODE_TO_ADMIN_NATURE_CREATE_FORM:
					if (newContent(containerTabNature, natureFormPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((ModifyNatureEvent) event.getEvent());
						}
					}
					break;
					
				case ContentEvent.CHANGE_MODE_TO_ADMIN_NATURE_LIST:
					if (newContent(containerTabNature, natureListPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((LoadDocumentEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_LANGUAGE_CREATE_FORM:
					if (newContent(containerTabLangue, languageFormPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((ModifyLanguageEvent) event.getEvent());
						}
					}
					break;
					
				case ContentEvent.CHANGE_MODE_TO_ADMIN_LANGUAGE_LIST:
					if (newContent(containerTabLangue, languageListPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((LoadDocumentEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_STATUS_CREATE_FORM:
					if (newContent(containerTabStatutDelegation, statusFormPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((ModifyStatusEvent) event.getEvent());
						}
					}
					break;					
				case ContentEvent.CHANGE_MODE_TO_ADMIN_STATUS_LIST:
					if (newContent(containerTabStatutDelegation, statusListPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((LoadDocumentEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGATION_TYPE_CREATE_FORM:
					if (newContent(containerTabTypeDelegation, delegationTypeFormPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((ModifyDelegationTypeEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGATION_TYPE_LIST:
					if (newContent(containerTabTypeDelegation, delegationTypeListPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((LoadDelegationTypeEvent) event.getEvent());
						}
					}
					break;		
					
				case ContentEvent.CHANGE_MODE_TO_ADMIN_CHANTIER_TYPE_CREATE_FORM:
					if (newContent(containerTabTypeChantier, chantierTypeFormPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((ModifyChantierTypeEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_CHANTIER_TYPE_LIST:
					if (newContent(containerTabTypeChantier, chantierTypeListPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((LoadDocumentEvent) event.getEvent());
						}
					}
					break;	
					
				case ContentEvent.CHANGE_MODE_TO_ADMIN_PERIMETRE_TYPE_CREATE_FORM:
					if (newContent(containerTabTypePerimetre, perimetreTypeFormPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((ModifyPerimetreTypeEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_PERIMETRE_TYPE_LIST:
					if (newContent(containerTabTypePerimetre, perimetreTypeListPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((LoadDocumentEvent) event.getEvent());
						}
					}
					break;
					
				case ContentEvent.CHANGE_MODE_TO_ADMIN_FORMATION_CREATE_FORM:
					if (newContent(containerTabFormation, formationFormPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((ModifyFormationEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_FORMATION_LIST:
					if (newContent(containerTabFormation, formationListPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((LoadDocumentEvent) event.getEvent());
						}
					}
					break;	
					
				case ContentEvent.CHANGE_MODE_TO_ADMIN_CONTROL_TYPE_CREATE_FORM:
					if (newContent(containerTabTypeControle, controlTypeFormPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((ModifyControlTypeEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_CONTROL_TYPE_LIST:
					if (newContent(containerTabTypeControle, controlTypeListPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((LoadDocumentEvent) event.getEvent());
						}
					}
					break;	
					
				case ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_VIEW_FORM:
					if (newContent(containerTabEntiteJuridique, entiteJuridiqueViewPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((ModifyEntiteJuridiqueEvent) event.getEvent());
						}
					}
					break;
					
				case ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_EDIT_FORM:
					if (newContent(containerTabEntiteJuridique, entiteJuridiqueFormPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((ModifyEntiteJuridiqueEvent) event.getEvent());
						}
					}
					break;
					
				case ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM:
					if (newContent(containerTabEntiteJuridique, entiteJuridiqueListPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((ModifyEntiteJuridiqueEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_EXTERN_CONTROLLER_LIST:
					if (newContent(containerTabExternalController, externalControllerListPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((LoadDocumentEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_EXTERN_CONTROLLER_CREATE_FORM:
					if (newContent(containerTabExternalController, externalControllerFormPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((ModifyExternControllerEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_DOCTYPE_CREATE_FORM:
					if (newContent(containerTabDocType, docTypeFormPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((ModifyDocTypeEvent) event.getEvent());
						}
					}
					break;
					
				case ContentEvent.CHANGE_MODE_TO_ADMIN_DOCTYPE_LIST:
					if (newContent(containerTabDocType, docTypeListPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((LoadDocumentEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_CATEGORY_CREATE_FORM:
					if (newContent(containerTabCategory, categoryFormPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((ModifyCategoryEvent) event.getEvent());
						}
					}
					break;
					
				case ContentEvent.CHANGE_MODE_TO_ADMIN_CATEGORY_LIST:
					if (newContent(containerTabCategory, categoryListPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((LoadDocumentEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_GROUP_CREATE_FORM:
					if (newContent(containerTabDelegantTypeGroup, delegantTypeGroupFormPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((ModifyDelegantTypeGroupEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_GROUP_LIST:
					if (newContent(containerTabDelegantTypeGroup, delegantTypeGroupListPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((LoadDocumentEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_CREATE_FORM:
					if (newContent(containerTabDelegantType, delegantTypeFormPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((ModifyCollaborateurTypeEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_LIST:
					if (newContent(containerTabDelegantType, delegantTypeListPanel)) {
						if (event.getEvent() !=  null) {
							bus.fireEvent((LoadDocumentEvent) event.getEvent());
						}
					}
					break;
				
				}
				
				disableEvents(false);
			}
		});
		
		this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler(){
			public void onLoadAction(final DelegationListProjectEvent event) {
				disableEvents(true);
				//add BYTP
				if (CommonUtils.belongsBYEFEGroup(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
//				if (ConstantClient.ENTITE_ID_IS_BYEFE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
					tabSet.getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_FORMATION).setEnabled(true);
					tabSet.getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_CONTROLE).setEnabled(true);
					tabSet.getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_EXTERNAL_CONTROLLER).setEnabled(true);
				} else {
					tabSet.getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_FORMATION).setEnabled(false);
					tabSet.getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_CONTROLE).setEnabled(false);
					tabSet.getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_EXTERNAL_CONTROLLER).setEnabled(false);
				}
				disableEvents(false);
			}
		});					
	}
	
	private boolean newContent(LayoutContainer parentContent, LayoutContainer newContent) {
		BorderLayoutData centerLayout = new BorderLayoutData(LayoutRegion.CENTER);
		centerLayout.setMargins(new Margins(0, 0, 0, 0));
		centerLayout.setSplit(false);
		
		parentContent.removeAll();
		parentContent.setLayout(new BorderLayout());
		parentContent.add(newContent, centerLayout);
		return parentContent.layout();
	}

	public void initTab(String key){
		ArrayList<AdminTabAction> tabActionList= new ArrayList<AdminTabAction>();
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_NATURE, containerTabNature));
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_TYPE_DELEGATION, containerTabTypeDelegation));		
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_STATUT_DELEGATION, containerTabStatutDelegation));
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_TYPE_CHANTIER, containerTabTypeChantier));
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_TYPE_PERIMETRE, containerTabTypePerimetre));
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_DELEGANT_TYPE_GROUP, containerTabDelegantTypeGroup));
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_DELEGANT_TYPE, containerTabDelegantType));
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_LANGUE, containerTabLangue));
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_DOCTYPE, containerTabDocType));
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_CATEGORY, containerTabCategory));
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_ENTITE_JURIDIQUE, containerTabEntiteJuridique));
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_FORMATION, containerTabFormation));
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_TYPE_CONTROLE, containerTabTypeControle));
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_EXTERNAL_CONTROLLER, containerTabExternalController));

		tabSet = new TabPanel();
	    tabSet.setTabScroll(true);	    
	    
	    TabItem item;
	    for(final AdminTabAction tab:tabActionList){
	    	item = new TabItem();
	    	item.setText(actionMessages.getString(tab.key));
	    	item.setClosable(false);
		    item.setLayout(new FitLayout());
		    item.setId(tab.key);
		    item.setItemId(tab.key);
		    tabSet.add(item);
		    item.add(tab.content, new FitData(0));
		    
	    	if (key == tab.key) {
	    		tabSet.setSelection(item);
	    	}
	    	item.addListener(Events.BeforeSelect, new Listener<ComponentEvent>() {
				public void handleEvent(ComponentEvent be) {
					if (AppUtil.checkToShowWarningInAdminEditMode(false)) {											
						be.setCancelled(true);
						be.cancelBubble();
					}
				}
			});
	    	item.addListener(Events.Select, new Listener<ComponentEvent>() {
    			public void handleEvent(ComponentEvent be) {
    				disableEvents(true);
    				restoreUI(tabSet.getSelectedItem().getId());
    				disableEvents(false);
    			}
    		});
	    	
	    }
	   
//	    Viewport viewport = new Viewport();
//	    viewport.setScrollMode(Scroll.AUTOX);
//		final BorderLayout layout = new BorderLayout();  
//		viewport.setLayout(layout);  
//		viewport.setStyleAttribute("padding", "0px");  
//		viewport.setBorders(true);
//		viewport.add(new Label(""), new BorderLayoutData(LayoutRegion.SOUTH,45));
//	    viewport.setStyleAttribute("background", "white");
//	    viewport.add(tabSet, new BorderLayoutData(LayoutRegion.CENTER));
	    
	    Viewport viewport = new Viewport();
		viewport.setLayout(new BorderLayout());
		viewport.setBorders(true);
		viewport.setStyleAttribute("padding", "0px");
		viewport.setStyleAttribute("background", "white");
		viewport.add(tabSet, new BorderLayoutData(LayoutRegion.CENTER));
		viewport.add(new Label(""), new BorderLayoutData(LayoutRegion.SOUTH, 54));
		viewport.add(new Label(""), new BorderLayoutData(LayoutRegion.EAST, 184));
	    add(viewport);
	}
		

	private void restoreUI(String tab) {
		if (ACTION_ADMIN_ENTITE_JURIDIQUE.equals(tab)) {
			ClientEntiteJuridiqueServiceAsync.Util.getInstance().findByEntiteId(SessionServiceImpl.getInstance().getEntiteContext().getEntId(), new AsyncCallback<List<EntiteJuridiqueModel>>() {
				@Override
				public void onSuccess(List<EntiteJuridiqueModel> arg0) {
					ContentEvent contentEvent = new ContentEvent();
					contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);
					
					ModifyEntiteJuridiqueEvent event = new ModifyEntiteJuridiqueEvent();
					event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);
					event.setModel(null);
					contentEvent.setEvent(event);
					bus.fireEvent(contentEvent);
				}
				
				@Override
				public void onFailure(Throwable arg0) {
				}
			});
		}
	}
	
	public TabPanel getTabPanel() {
		return tabSet;
	}
	
	public class AdminTabAction {
		String key;
		LayoutContainer content;
		
		public AdminTabAction(String key, LayoutContainer content) {
			this.key = key;
			this.content = content;
		}
	}
}
