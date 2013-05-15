package com.structis.vip.client.panel;

import java.util.ArrayList;

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
import com.google.gwt.user.client.Element;
import com.structis.vip.client.constant.ConstantClient;
import com.structis.vip.client.event.ContentEvent;
import com.structis.vip.client.event.ContentEventHandler;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.event.DelegationListProjectHandler;
import com.structis.vip.client.event.DelegationModelEvent;
import com.structis.vip.client.event.FieldRuleEvent;
import com.structis.vip.client.event.LoadGroupDelegationModelEvent;
import com.structis.vip.client.event.LoadUserEvent;
import com.structis.vip.client.event.ModifyCollaboratureEvent;
import com.structis.vip.client.event.ModifyDocumentEvent;
import com.structis.vip.client.event.ModifyUserEvent;
import com.structis.vip.client.event.PerimetreEvent;
import com.structis.vip.client.event.document.LoadDocEvent;
import com.structis.vip.client.event.document.ModifyDocEvent;
import com.structis.vip.client.message.ActionMessages;
import com.structis.vip.client.navigation.NavigationFactory;
import com.structis.vip.client.navigation.NavigationService;
import com.structis.vip.client.panel.ReferentielTabPanel.AdminTabAction;
import com.structis.vip.client.panel.document.DocFormPanel;
import com.structis.vip.client.panel.document.DocListPanel;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.shared.model.PerimetreTreeModel;

public class AdministrationTabPanel extends LayoutContainer {
	public final String ACTION_ADMIN_RULE = "actionAdminRule";
	public final String ACTION_ADMIN_PEREMETRE = "actionAdminPerimetre";
	public final String ACTION_ADMIN_COLLABORATURE = "actionAdminCollaborature";
	public final String ACTION_ADMIN_UTILISATEUR = "actionAdminUtilisateur";
	public final String ACTION_ADMIN_DOCUMENT = "actionAdminDocument";
	public final String ACTION_ADMIN_REFERENTIELS = "actionAdminReferentiels";
	public final String ACTION_ADMIN_DOC = "actionAdminDoc";

	ActionMessages actionMessages = GWT.create(ActionMessages.class);
	NavigationService navigation = NavigationFactory.getNavigation();
	TabPanel tabSet;

	private SimpleEventBus bus;
	private ListDelegationModelPanel delegationModelPanel;
	private ListGroupDelegationModelPanel groupDelegationModelPanel;
	private FieldRulePanel rulePanel;

	private AdministrationCollaboraturePanel collaboratureListPanel;
	private CollaboratureFormPanel collaboratureFormPanel;

	private DocumentListPanel documentListPanel;
	private DocumentFormPanel documentFormPanel;
	private DocumentViewPanel documentViewPanel;

	private NewPerimetrePanel newPerimetrePanel;

	private UserListPanel userListPanel;
	private UserFormPanel userFormPanel;

	private DocListPanel docListPanel;
	private DocFormPanel docFormPanel;

	
	private LayoutContainer containerTabPerimetre = new LayoutContainer();
	private LayoutContainer containerTabCollaborature = new LayoutContainer();
	private LayoutContainer containerTabDocument = new LayoutContainer();
	private LayoutContainer containerTabDelegation = new LayoutContainer();
	private LayoutContainer containerTabUser = new LayoutContainer();
	private LayoutContainer containerTabDoc = new LayoutContainer();
	private ReferentielTabPanel containerTabReferentiel;

	public AdministrationTabPanel(SimpleEventBus bus) {
		this.bus = bus;

		delegationModelPanel = new ListDelegationModelPanel(this.bus);
		groupDelegationModelPanel = new ListGroupDelegationModelPanel(bus);
		rulePanel = new FieldRulePanel(this.bus);

		collaboratureListPanel = new AdministrationCollaboraturePanel(bus);
		collaboratureFormPanel = new CollaboratureFormPanel(bus);

		documentFormPanel = new DocumentFormPanel(bus);
		documentListPanel = new DocumentListPanel(bus);
		documentViewPanel = new DocumentViewPanel(bus);

		newPerimetrePanel = new NewPerimetrePanel(bus);

		userListPanel = new UserListPanel(bus);
		userFormPanel = new UserFormPanel(bus);
		docListPanel = new DocListPanel(bus);
		docFormPanel = new DocFormPanel(bus);

		containerTabReferentiel = new ReferentielTabPanel(bus);
	}

	@Override
	public void onRender(Element parent, int index) {
		super.onRender(parent, index);

		newContent(containerTabDelegation, groupDelegationModelPanel);
		newContent(containerTabDocument, documentListPanel);
		newContent(containerTabPerimetre, newPerimetrePanel);
		newContent(containerTabCollaborature, collaboratureListPanel);
		newContent(containerTabUser, userListPanel);
		newContent(containerTabDoc, docListPanel);
		initTab();

		addHandler();
	}

	private void addHandler() {
		this.bus.addHandler(ContentEvent.getType(), new ContentEventHandler() {
			@Override
			public void onLoadAction(ContentEvent event) {
				disableEvents(true);

				switch (event.getMode()) {
				case ContentEvent.CHANGE_MODE_TO_RULE_ADMIN_FORM:
					if (event.getEvent() instanceof FieldRuleEvent) {
						if (newContent(containerTabDelegation, rulePanel)) {
							bus.fireEvent((FieldRuleEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_GROUP_DELEGATION_MODEL_ADMIN_FORM:
					if (event.getEvent() instanceof LoadGroupDelegationModelEvent) {
						if (newContent(containerTabDelegation, groupDelegationModelPanel)) {
							bus.fireEvent((LoadGroupDelegationModelEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_DELEGATION_MODEL_ADMIN_FORM:
					if (event.getEvent() instanceof DelegationModelEvent) {
						if (newContent(containerTabDelegation, delegationModelPanel)) {
							bus.fireEvent((DelegationModelEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT:
					if (newContent(containerTabDocument, documentListPanel)) {
						if (event.getEvent() != null) {
							bus.fireEvent((ModifyDocumentEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_CREATE_FORM:
										
					if (newContent(containerTabDocument, documentFormPanel)) {
						if (event.getEvent() != null) {
							bus.fireEvent((ModifyDocumentEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_VIEW_DOCUMENT:
					if (newContent(containerTabDocument, documentViewPanel)) {
						if (event.getEvent() != null) {
							bus.fireEvent((ModifyDocumentEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_PERIMETRE_FORM:
					if (event.getEvent() instanceof PerimetreEvent) {
						tabSet.setSelection(tabSet.getItemByItemId(ACTION_ADMIN_PEREMETRE));
						bus.fireEvent((PerimetreEvent) event.getEvent());
					}
					break;

				case ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_CREATE_FORM:
					if (newContent(containerTabCollaborature, collaboratureFormPanel)) {
						if (event.getEvent() != null) {
							bus.fireEvent((ModifyCollaboratureEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_LIST:
					if (newContent(containerTabCollaborature, collaboratureListPanel)) {
						if (event.getEvent() != null) {
							bus.fireEvent((ModifyCollaboratureEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_USER_CREATE_FORM:
					if (newContent(containerTabUser, userFormPanel)) {
						if (event.getEvent() != null) {
							bus.fireEvent((ModifyUserEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_USER_LIST:
					if (newContent(containerTabUser, userListPanel)) {
						if (event.getEvent() != null) {
							bus.fireEvent((LoadUserEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_DOC_LIST:
					if (newContent(containerTabDoc, docListPanel)) {
						if (event.getEvent() != null) {
							bus.fireEvent((LoadDocEvent) event.getEvent());
						}
					}
					break;
				case ContentEvent.CHANGE_MODE_TO_ADMIN_DOC_CREATE_FORM:
					if (newContent(containerTabDoc, docFormPanel)) {						
						if (event.getEvent() != null) {
							bus.fireEvent((ModifyDocEvent) event.getEvent());
						}
					}
					break;

				}

				disableEvents(false);
			}
		});

		this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {
			public void onLoadAction(final DelegationListProjectEvent event) {
				disableEvents(true);
				restoreUI(event);
				disableEvents(false);
			}
		});
	}

	private void restoreUI(DelegationListProjectEvent pevent) {
		// update perimetre form
		PerimetreEvent event = new PerimetreEvent();
		PerimetreTreeModel perimetreTreeModel = new PerimetreTreeModel(pevent.getPerimetreModel(), SessionServiceImpl
				.getInstance().getUserContext().getUserRoles());
		event.setMode(PerimetreEvent.MODE_IS_VIEW);
		event.setIsUoAdmin(perimetreTreeModel.getIsUoAdmin());
		event.setPerimetreId(pevent.getPerimetreModel().getPerId());
		event.setPath(pevent.getPerimetreModel().getName());
		bus.fireEvent(event);

		if (SessionServiceImpl.getInstance().getUserContext().isApplicationAdmin()) {
			tabSet.getItemByItemId(ACTION_ADMIN_RULE).setEnabled(true);
			tabSet.getItemByItemId(ACTION_ADMIN_DOCUMENT).setEnabled(true);
			tabSet.getItemByItemId(ACTION_ADMIN_REFERENTIELS).setEnabled(true);
			// add BYTP			
//			if (ConstantClient.ENTITE_ID_IS_BYEFE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
			if (CommonUtils.belongsBYEFEGroup(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
				containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_FORMATION).setEnabled(true);
				containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_CONTROLE).setEnabled(true);				
				containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_EXTERNAL_CONTROLLER).setEnabled(true);
			} else {
				containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_FORMATION).setEnabled(false);
				containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_CONTROLE).setEnabled(false);
				containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_EXTERNAL_CONTROLLER).setEnabled(false);
			}			
			
		} else {
			tabSet.getItemByItemId(ACTION_ADMIN_RULE).setEnabled(false);
			tabSet.getItemByItemId(ACTION_ADMIN_DOCUMENT).setEnabled(false);
			if (SessionServiceImpl.getInstance().getUserContext().isUoAdmin()) {
				tabSet.getItemByItemId(ACTION_ADMIN_REFERENTIELS).setEnabled(true);
				if (CommonUtils.belongsBYEFEGroup(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {					
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_FORMATION).setEnabled(false);
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_CONTROLE).setEnabled(false);				
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_EXTERNAL_CONTROLLER).setEnabled(false);
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_PERIMETRE).setEnabled(true);
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_CHANTIER).setEnabled(true);
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_DELEGANT_TYPE_GROUP).setEnabled(false);
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_DELEGANT_TYPE).setEnabled(false);
					
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_NATURE).setEnabled(false);
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_DELEGATION).setEnabled(false);				
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_STATUT_DELEGATION).setEnabled(false);
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_LANGUE).setEnabled(false);
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_DOCTYPE).setEnabled(false);
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_CATEGORY).setEnabled(false);
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_ENTITE_JURIDIQUE).setEnabled(false);
										
				} else {
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_FORMATION).setEnabled(false);
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_CONTROLE).setEnabled(false);
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_EXTERNAL_CONTROLLER).setEnabled(false);
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_PERIMETRE).setEnabled(false);
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_CHANTIER).setEnabled(false);
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_DELEGANT_TYPE_GROUP).setEnabled(false);
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_DELEGANT_TYPE).setEnabled(false);
				}				
			} else {
				tabSet.getItemByItemId(ACTION_ADMIN_REFERENTIELS).setEnabled(false);
			}
		}
		
		tabSet.setSelection(tabSet.getItemByItemId(ACTION_ADMIN_PEREMETRE));
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

	private void changeTab(String tab) {
		if (ACTION_ADMIN_RULE.equals(tab)) {
			ContentEvent contentEvent = new ContentEvent();
			contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GROUP_DELEGATION_MODEL_ADMIN_FORM);
			contentEvent.setEvent(new LoadGroupDelegationModelEvent());
			bus.fireEvent(contentEvent);
		} else if (ACTION_ADMIN_DOCUMENT.equals(tab)) {
			ContentEvent contentEvent = new ContentEvent();
			contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);
			contentEvent.setEvent(new ModifyDocumentEvent());
			bus.fireEvent(contentEvent);
		} else if (ACTION_ADMIN_COLLABORATURE.equals(tab)) {
			ContentEvent contentEvent = new ContentEvent();
			contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_LIST);
			contentEvent.setEvent(new ModifyCollaboratureEvent());
			bus.fireEvent(contentEvent);
		} else if (ACTION_ADMIN_UTILISATEUR.equals(tab)) {
			ContentEvent contentEvent = new ContentEvent();
			contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_USER_LIST);
			contentEvent.setEvent(new LoadUserEvent());
			bus.fireEvent(contentEvent);
		} else if (ACTION_ADMIN_REFERENTIELS.equals(tab)) {
			containerTabReferentiel.getTabPanel().setSelection(
					containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_NATURE));
		} else if (ACTION_ADMIN_DOC.equals(tab)) {
			ContentEvent contentEvent = new ContentEvent();
			contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOC_LIST);
			contentEvent.setEvent(new LoadDocEvent());
			bus.fireEvent(contentEvent);
		} 
	}

	public void initTab() {
		ArrayList<AdminTabAction> tabActionList = new ArrayList<AdminTabAction>();
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_PEREMETRE, containerTabPerimetre));
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_COLLABORATURE, containerTabCollaborature));
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_UTILISATEUR, containerTabUser));
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_DOCUMENT, containerTabDocument));
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_RULE, containerTabDelegation));
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_REFERENTIELS, containerTabReferentiel));
		tabActionList.add(new AdminTabAction(ACTION_ADMIN_DOC, containerTabDoc));

		tabSet = new TabPanel();
		tabSet.setTabScroll(true);

		TabItem item;
		for (final AdminTabAction tab : tabActionList) {
			item = new TabItem();
			item.setText(actionMessages.getString(tab.key));
			item.setClosable(false);
			item.setLayout(new FitLayout());
			item.setId(tab.key);
			tabSet.add(item);
			item.add(tab.content, new FitData(0));

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
					changeTab(tabSet.getSelectedItem().getId());
					disableEvents(false);					
				}
			});
		}

		Viewport viewport = new Viewport();
		viewport.setLayout(new BorderLayout());
		viewport.setBorders(true);
		viewport.setStyleAttribute("padding", "0px");
		viewport.setStyleAttribute("background", "white");
		viewport.add(tabSet, new BorderLayoutData(LayoutRegion.CENTER));
		viewport.add(new Label(""), new BorderLayoutData(LayoutRegion.SOUTH, 45));
		viewport.add(new Label(""), new BorderLayoutData(LayoutRegion.EAST, 184));
		add(viewport);
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
