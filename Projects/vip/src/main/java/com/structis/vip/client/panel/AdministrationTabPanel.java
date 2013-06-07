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
import com.structis.vip.client.event.PerimetreEvent;
import com.structis.vip.client.event.document.LoadDocEvent;
import com.structis.vip.client.message.ActionMessages;
import com.structis.vip.client.navigation.NavigationFactory;
import com.structis.vip.client.navigation.NavigationService;
import com.structis.vip.client.panel.document.DocFormPanel;
import com.structis.vip.client.panel.document.DocListPanel;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.shared.model.PerimetreTreeModel;

public class AdministrationTabPanel extends AbstractPanel {

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

        this.delegationModelPanel = new ListDelegationModelPanel(this.bus);
        this.groupDelegationModelPanel = new ListGroupDelegationModelPanel(bus);
        this.rulePanel = new FieldRulePanel(this.bus);

        this.collaboratureListPanel = new AdministrationCollaboraturePanel(bus);
        this.collaboratureFormPanel = new CollaboratureFormPanel(bus);

        this.documentFormPanel = new DocumentFormPanel(bus);
        this.documentListPanel = new DocumentListPanel(bus);
        this.documentViewPanel = new DocumentViewPanel(bus);

        this.newPerimetrePanel = new NewPerimetrePanel(bus);

        this.userListPanel = new UserListPanel(bus);
        this.userFormPanel = new UserFormPanel(bus);
        this.docListPanel = new DocListPanel(bus);
        this.docFormPanel = new DocFormPanel(bus);

        this.containerTabReferentiel = new ReferentielTabPanel(bus);
    }

    @Override
    public void onRender(Element parent, int index) {
        super.onRender(parent, index);

        this.newContent(this.containerTabDelegation, this.groupDelegationModelPanel);
        this.newContent(this.containerTabDocument, this.documentListPanel);
        this.newContent(this.containerTabPerimetre, this.newPerimetrePanel);
        this.newContent(this.containerTabCollaborature, this.collaboratureListPanel);
        this.newContent(this.containerTabUser, this.userListPanel);
        this.newContent(this.containerTabDoc, this.docListPanel);
        this.initTab();

        this.addHandler();
    }

    private void addHandler() {
        this.bus.addHandler(ContentEvent.getType(), new ContentEventHandler() {

            @Override
            public void onLoadAction(ContentEvent event) {
                AdministrationTabPanel.this.disableEvents(true);

                switch (event.getMode()) {
                case ContentEvent.CHANGE_MODE_TO_RULE_ADMIN_FORM:
                    if (event.getEvent() instanceof FieldRuleEvent) {
                        if (AdministrationTabPanel.this.newContent(AdministrationTabPanel.this.containerTabDelegation,
                                AdministrationTabPanel.this.rulePanel)) {
                            AdministrationTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_GROUP_DELEGATION_MODEL_ADMIN_FORM:
                    if (event.getEvent() instanceof LoadGroupDelegationModelEvent) {
                        if (AdministrationTabPanel.this.newContent(AdministrationTabPanel.this.containerTabDelegation,
                                AdministrationTabPanel.this.groupDelegationModelPanel)) {
                            AdministrationTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_DELEGATION_MODEL_ADMIN_FORM:
                    if (event.getEvent() instanceof DelegationModelEvent) {
                        if (AdministrationTabPanel.this.newContent(AdministrationTabPanel.this.containerTabDelegation,
                                AdministrationTabPanel.this.delegationModelPanel)) {
                            AdministrationTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT:
                    if (AdministrationTabPanel.this.newContent(AdministrationTabPanel.this.containerTabDocument,
                            AdministrationTabPanel.this.documentListPanel)) {
                        if (event.getEvent() != null) {
                            AdministrationTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_CREATE_FORM:

                    if (AdministrationTabPanel.this.newContent(AdministrationTabPanel.this.containerTabDocument,
                            AdministrationTabPanel.this.documentFormPanel)) {
                        if (event.getEvent() != null) {
                            AdministrationTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_VIEW_DOCUMENT:
                    if (AdministrationTabPanel.this.newContent(AdministrationTabPanel.this.containerTabDocument,
                            AdministrationTabPanel.this.documentViewPanel)) {
                        if (event.getEvent() != null) {
                            AdministrationTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_PERIMETRE_FORM:
                    if (event.getEvent() instanceof PerimetreEvent) {
                        AdministrationTabPanel.this.tabSet.setSelection(AdministrationTabPanel.this.tabSet
                                .getItemByItemId(AdministrationTabPanel.this.ACTION_ADMIN_PEREMETRE));
                        AdministrationTabPanel.this.bus.fireEvent(event.getEvent());
                    }
                    break;

                case ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_CREATE_FORM:
                    if (AdministrationTabPanel.this.newContent(AdministrationTabPanel.this.containerTabCollaborature,
                            AdministrationTabPanel.this.collaboratureFormPanel)) {
                        if (event.getEvent() != null) {
                            AdministrationTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_LIST:
                    if (AdministrationTabPanel.this.newContent(AdministrationTabPanel.this.containerTabCollaborature,
                            AdministrationTabPanel.this.collaboratureListPanel)) {
                        if (event.getEvent() != null) {
                            AdministrationTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_USER_CREATE_FORM:
                    if (AdministrationTabPanel.this.newContent(AdministrationTabPanel.this.containerTabUser,
                            AdministrationTabPanel.this.userFormPanel)) {
                        if (event.getEvent() != null) {
                            AdministrationTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_USER_LIST:
                    if (AdministrationTabPanel.this.newContent(AdministrationTabPanel.this.containerTabUser,
                            AdministrationTabPanel.this.userListPanel)) {
                        if (event.getEvent() != null) {
                            AdministrationTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_DOC_LIST:
                    if (AdministrationTabPanel.this.newContent(AdministrationTabPanel.this.containerTabDoc, AdministrationTabPanel.this.docListPanel)) {
                        if (event.getEvent() != null) {
                            AdministrationTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_DOC_CREATE_FORM:
                    if (AdministrationTabPanel.this.newContent(AdministrationTabPanel.this.containerTabDoc, AdministrationTabPanel.this.docFormPanel)) {
                        if (event.getEvent() != null) {
                            AdministrationTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;

                }

                AdministrationTabPanel.this.disableEvents(false);
            }
        });

        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(final DelegationListProjectEvent event) {
                AdministrationTabPanel.this.disableEvents(true);
                AdministrationTabPanel.this.restoreUI(event);
                AdministrationTabPanel.this.disableEvents(false);
            }
        });
    }

    private void restoreUI(DelegationListProjectEvent pevent) {
        // update perimetre form
        PerimetreEvent event = new PerimetreEvent();
        PerimetreTreeModel perimetreTreeModel = new PerimetreTreeModel(pevent.getPerimetreModel(), SessionServiceImpl.getInstance().getUserContext()
                .getUserRoles());
        event.setMode(PerimetreEvent.MODE_IS_VIEW);
        event.setIsUoAdmin(perimetreTreeModel.getIsUoAdmin());
        event.setPerimetreId(pevent.getPerimetreModel().getPerId());
        event.setPath(pevent.getPerimetreModel().getName());
        this.bus.fireEvent(event);

        if (SessionServiceImpl.getInstance().getUserContext().isApplicationAdmin()) {
            this.tabSet.getItemByItemId(this.ACTION_ADMIN_RULE).setEnabled(true);
            this.tabSet.getItemByItemId(this.ACTION_ADMIN_DOCUMENT).setEnabled(true);
            this.tabSet.getItemByItemId(this.ACTION_ADMIN_REFERENTIELS).setEnabled(true);
            // add BYTP
            // if (ConstantClient.ENTITE_ID_IS_BYEFE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
            if (CommonUtils.belongsBYEFEGroup(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
                this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_FORMATION).setEnabled(true);
                this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_CONTROLE).setEnabled(true);
                this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_EXTERNAL_CONTROLLER).setEnabled(true);
            } else {
                this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_FORMATION).setEnabled(false);
                this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_CONTROLE).setEnabled(false);
                this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_EXTERNAL_CONTROLLER).setEnabled(false);
            }

        } else {
            this.tabSet.getItemByItemId(this.ACTION_ADMIN_RULE).setEnabled(false);
            this.tabSet.getItemByItemId(this.ACTION_ADMIN_DOCUMENT).setEnabled(false);
            if (SessionServiceImpl.getInstance().getUserContext().isUoAdmin()) {
                this.tabSet.getItemByItemId(this.ACTION_ADMIN_REFERENTIELS).setEnabled(true);
                if (CommonUtils.belongsBYEFEGroup(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_FORMATION).setEnabled(false);
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_CONTROLE).setEnabled(false);
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_EXTERNAL_CONTROLLER)
                            .setEnabled(false);
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_PERIMETRE).setEnabled(true);
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_CHANTIER).setEnabled(true);
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_DELEGANT_TYPE_GROUP)
                            .setEnabled(false);
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_DELEGANT_TYPE).setEnabled(false);

                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_NATURE).setEnabled(false);
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_DELEGATION).setEnabled(false);
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_STATUT_DELEGATION).setEnabled(false);
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_LANGUE).setEnabled(false);
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_DOCTYPE).setEnabled(false);
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_CATEGORY).setEnabled(false);
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_ENTITE_JURIDIQUE).setEnabled(false);

                } else {
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_FORMATION).setEnabled(false);
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_CONTROLE).setEnabled(false);
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_EXTERNAL_CONTROLLER)
                            .setEnabled(false);
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_PERIMETRE).setEnabled(false);
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_CHANTIER).setEnabled(false);
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_DELEGANT_TYPE_GROUP)
                            .setEnabled(false);
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_DELEGANT_TYPE).setEnabled(false);
                }
            } else {
                this.tabSet.getItemByItemId(this.ACTION_ADMIN_REFERENTIELS).setEnabled(false);
            }
        }

        this.tabSet.setSelection(this.tabSet.getItemByItemId(this.ACTION_ADMIN_PEREMETRE));
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
        if (this.ACTION_ADMIN_RULE.equals(tab)) {
            ContentEvent contentEvent = new ContentEvent();
            contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_GROUP_DELEGATION_MODEL_ADMIN_FORM);
            contentEvent.setEvent(new LoadGroupDelegationModelEvent());
            this.bus.fireEvent(contentEvent);
        } else if (this.ACTION_ADMIN_DOCUMENT.equals(tab)) {
            ContentEvent contentEvent = new ContentEvent();
            contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOCUMENT_LIST_DOCUMENT);
            contentEvent.setEvent(new ModifyDocumentEvent());
            this.bus.fireEvent(contentEvent);
        } else if (this.ACTION_ADMIN_COLLABORATURE.equals(tab)) {
            ContentEvent contentEvent = new ContentEvent();
            contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_COLLABORATURE_LIST);
            contentEvent.setEvent(new ModifyCollaboratureEvent());
            this.bus.fireEvent(contentEvent);
        } else if (this.ACTION_ADMIN_UTILISATEUR.equals(tab)) {
            ContentEvent contentEvent = new ContentEvent();
            contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_USER_LIST);
            contentEvent.setEvent(new LoadUserEvent());
            this.bus.fireEvent(contentEvent);
        } else if (this.ACTION_ADMIN_REFERENTIELS.equals(tab)) {
            this.containerTabReferentiel.getTabPanel().setSelection(
                    this.containerTabReferentiel.getTabPanel().getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_NATURE));
        } else if (this.ACTION_ADMIN_DOC.equals(tab)) {
            ContentEvent contentEvent = new ContentEvent();
            contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_DOC_LIST);
            contentEvent.setEvent(new LoadDocEvent());
            this.bus.fireEvent(contentEvent);
        }
    }

    public void initTab() {
        ArrayList<AdminTabAction> tabActionList = new ArrayList<AdminTabAction>();
        tabActionList.add(new AdminTabAction(this.ACTION_ADMIN_PEREMETRE, this.containerTabPerimetre));
        tabActionList.add(new AdminTabAction(this.ACTION_ADMIN_COLLABORATURE, this.containerTabCollaborature));
        tabActionList.add(new AdminTabAction(this.ACTION_ADMIN_UTILISATEUR, this.containerTabUser));
        tabActionList.add(new AdminTabAction(this.ACTION_ADMIN_DOCUMENT, this.containerTabDocument));
        tabActionList.add(new AdminTabAction(this.ACTION_ADMIN_RULE, this.containerTabDelegation));
        tabActionList.add(new AdminTabAction(this.ACTION_ADMIN_REFERENTIELS, this.containerTabReferentiel));
        tabActionList.add(new AdminTabAction(this.ACTION_ADMIN_DOC, this.containerTabDoc));

        this.tabSet = new TabPanel();
        this.tabSet.setTabScroll(true);

        TabItem item;
        for (final AdminTabAction tab : tabActionList) {
            item = new TabItem();
            item.setText(this.actionMessages.getString(tab.key));
            item.setClosable(false);
            item.setLayout(new FitLayout());
            item.setId(tab.key);
            this.tabSet.add(item);
            item.add(tab.content, new FitData(0));

            item.addListener(Events.BeforeSelect, new Listener<ComponentEvent>() {

                @Override
                public void handleEvent(ComponentEvent be) {
                    if (AppUtil.checkToShowWarningInAdminEditMode(false)) {
                        be.setCancelled(true);
                        be.cancelBubble();
                    }
                }
            });
            item.addListener(Events.Select, new Listener<ComponentEvent>() {

                @Override
                public void handleEvent(ComponentEvent be) {
                    AdministrationTabPanel.this.disableEvents(true);
                    AdministrationTabPanel.this.changeTab(AdministrationTabPanel.this.tabSet.getSelectedItem().getId());
                    AdministrationTabPanel.this.disableEvents(false);
                }
            });
        }

        Viewport viewport = new Viewport();
        viewport.setLayout(new BorderLayout());
        viewport.setBorders(true);
        viewport.setStyleAttribute("padding", "0px");
        viewport.setStyleAttribute("background", "white");
        viewport.add(this.tabSet, new BorderLayoutData(LayoutRegion.CENTER));
        viewport.add(new Label(""), new BorderLayoutData(LayoutRegion.SOUTH, 45));
        viewport.add(new Label(""), new BorderLayoutData(LayoutRegion.EAST, 184));
        this.add(viewport);
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
