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
import com.structis.vip.client.event.ModifyEntiteJuridiqueEvent;
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
    static final String ACTION_ADMIN_DELEGANT_TYPE_GROUP = "actionAdminDelegantTypeGroup";
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

        this.natureListPanel = new NatureListPanel(bus);
        this.natureFormPanel = new NatureFormPanel(bus);

        this.languageListPanel = new LanguageListPanel(bus);
        this.languageFormPanel = new LanguageFormPanel(bus);

        this.statusListPanel = new StatusListPanel(bus);
        this.statusFormPanel = new StatusFormPanel(bus);

        this.chantierTypeListPanel = new ChantierTypeListPanel(bus);
        this.chantierTypeFormPanel = new ChantierTypeFormPanel(bus);

        this.perimetreTypeListPanel = new PerimetreTypeListPanel(bus);
        this.perimetreTypeFormPanel = new PerimetreTypeFormPanel(bus);

        this.delegationTypeListPanel = new DelegationTypeListPanel(bus);
        this.delegationTypeFormPanel = new DelegationTypeFormPanel(bus);

        this.formationListPanel = new FormationListPanel(bus);
        this.formationFormPanel = new FormationFormPanel(bus);

        this.controlTypeListPanel = new ControlTypeListPanel(bus);
        this.controlTypeFormPanel = new ControlTypeFormPanel(bus);

        this.externalControllerListPanel = new ExternalControllerListPanel(bus);
        this.externalControllerFormPanel = new ExternalControllerFormPanel(bus);

        this.entiteJuridiqueListPanel = new EntiteJuridiqueListPanel(bus);
        this.entiteJuridiqueViewPanel = new EntiteJuridiqueViewPanel(bus);
        this.entiteJuridiqueFormPanel = new EntiteJuridiqueFormPanel(bus);

        this.docTypeListPanel = new DocTypeListPanel(bus);
        this.docTypeFormPanel = new DocTypeFormPanel(bus);

        this.categoryListPanel = new CategoryListPanel(bus);
        this.categoryFormPanel = new CategoryFormPanel(bus);

        this.delegantTypeGroupListPanel = new DelegantTypeGroupListPanel(bus);
        this.delegantTypeGroupFormPanel = new DelegantTypeGroupFormPanel(bus);

        this.delegantTypeListPanel = new CollaborateurTypeListPanel(bus);
        this.delegantTypeFormPanel = new CollaborateurTypeFormPanel(bus);

        this.newContent(this.containerTabNature, this.natureListPanel);
        this.newContent(this.containerTabStatutDelegation, this.statusListPanel);
        this.newContent(this.containerTabTypeDelegation, this.delegationTypeListPanel);
        this.newContent(this.containerTabTypeChantier, this.chantierTypeListPanel);
        this.newContent(this.containerTabTypePerimetre, this.perimetreTypeListPanel);
        this.newContent(this.containerTabDelegantTypeGroup, this.delegantTypeGroupListPanel);
        this.newContent(this.containerTabDelegantType, this.delegantTypeListPanel);
        this.newContent(this.containerTabLangue, this.languageListPanel);
        this.newContent(this.containerTabDocType, this.docTypeListPanel);
        this.newContent(this.containerTabCategory, this.categoryListPanel);
        this.newContent(this.containerTabEntiteJuridique, this.entiteJuridiqueListPanel);
        this.newContent(this.containerTabFormation, this.formationListPanel);
        this.newContent(this.containerTabTypeControle, this.controlTypeListPanel);
        this.newContent(this.containerTabExternalController, this.externalControllerListPanel);

        this.initTab(ACTION_ADMIN_NATURE);

        this.addHandler();
    }

    private void addHandler() {
        this.bus.addHandler(ContentEvent.getType(), new ContentEventHandler() {

            @Override
            public void onLoadAction(ContentEvent event) {
                ReferentielTabPanel.this.disableEvents(true);

                switch (event.getMode()) {
                case ContentEvent.CHANGE_MODE_TO_ADMIN_NATURE_CREATE_FORM:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabNature, ReferentielTabPanel.this.natureFormPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;

                case ContentEvent.CHANGE_MODE_TO_ADMIN_NATURE_LIST:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabNature, ReferentielTabPanel.this.natureListPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_LANGUAGE_CREATE_FORM:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabLangue, ReferentielTabPanel.this.languageFormPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;

                case ContentEvent.CHANGE_MODE_TO_ADMIN_LANGUAGE_LIST:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabLangue, ReferentielTabPanel.this.languageListPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_STATUS_CREATE_FORM:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabStatutDelegation,
                            ReferentielTabPanel.this.statusFormPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_STATUS_LIST:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabStatutDelegation,
                            ReferentielTabPanel.this.statusListPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGATION_TYPE_CREATE_FORM:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabTypeDelegation,
                            ReferentielTabPanel.this.delegationTypeFormPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGATION_TYPE_LIST:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabTypeDelegation,
                            ReferentielTabPanel.this.delegationTypeListPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;

                case ContentEvent.CHANGE_MODE_TO_ADMIN_CHANTIER_TYPE_CREATE_FORM:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabTypeChantier,
                            ReferentielTabPanel.this.chantierTypeFormPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_CHANTIER_TYPE_LIST:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabTypeChantier,
                            ReferentielTabPanel.this.chantierTypeListPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;

                case ContentEvent.CHANGE_MODE_TO_ADMIN_PERIMETRE_TYPE_CREATE_FORM:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabTypePerimetre,
                            ReferentielTabPanel.this.perimetreTypeFormPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_PERIMETRE_TYPE_LIST:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabTypePerimetre,
                            ReferentielTabPanel.this.perimetreTypeListPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;

                case ContentEvent.CHANGE_MODE_TO_ADMIN_FORMATION_CREATE_FORM:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabFormation,
                            ReferentielTabPanel.this.formationFormPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_FORMATION_LIST:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabFormation,
                            ReferentielTabPanel.this.formationListPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;

                case ContentEvent.CHANGE_MODE_TO_ADMIN_CONTROL_TYPE_CREATE_FORM:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabTypeControle,
                            ReferentielTabPanel.this.controlTypeFormPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_CONTROL_TYPE_LIST:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabTypeControle,
                            ReferentielTabPanel.this.controlTypeListPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;

                case ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_VIEW_FORM:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabEntiteJuridique,
                            ReferentielTabPanel.this.entiteJuridiqueViewPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;

                case ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_EDIT_FORM:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabEntiteJuridique,
                            ReferentielTabPanel.this.entiteJuridiqueFormPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;

                case ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabEntiteJuridique,
                            ReferentielTabPanel.this.entiteJuridiqueListPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_EXTERN_CONTROLLER_LIST:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabExternalController,
                            ReferentielTabPanel.this.externalControllerListPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_EXTERN_CONTROLLER_CREATE_FORM:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabExternalController,
                            ReferentielTabPanel.this.externalControllerFormPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_DOCTYPE_CREATE_FORM:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabDocType, ReferentielTabPanel.this.docTypeFormPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;

                case ContentEvent.CHANGE_MODE_TO_ADMIN_DOCTYPE_LIST:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabDocType, ReferentielTabPanel.this.docTypeListPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_CATEGORY_CREATE_FORM:
                    if (ReferentielTabPanel.this
                            .newContent(ReferentielTabPanel.this.containerTabCategory, ReferentielTabPanel.this.categoryFormPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;

                case ContentEvent.CHANGE_MODE_TO_ADMIN_CATEGORY_LIST:
                    if (ReferentielTabPanel.this
                            .newContent(ReferentielTabPanel.this.containerTabCategory, ReferentielTabPanel.this.categoryListPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_GROUP_CREATE_FORM:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabDelegantTypeGroup,
                            ReferentielTabPanel.this.delegantTypeGroupFormPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_GROUP_LIST:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabDelegantTypeGroup,
                            ReferentielTabPanel.this.delegantTypeGroupListPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_CREATE_FORM:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabDelegantType,
                            ReferentielTabPanel.this.delegantTypeFormPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;
                case ContentEvent.CHANGE_MODE_TO_ADMIN_DELEGANT_TYPE_LIST:
                    if (ReferentielTabPanel.this.newContent(ReferentielTabPanel.this.containerTabDelegantType,
                            ReferentielTabPanel.this.delegantTypeListPanel)) {
                        if (event.getEvent() != null) {
                            ReferentielTabPanel.this.bus.fireEvent(event.getEvent());
                        }
                    }
                    break;

                }

                ReferentielTabPanel.this.disableEvents(false);
            }
        });

        this.bus.addHandler(DelegationListProjectEvent.getType(), new DelegationListProjectHandler() {

            @Override
            public void onLoadAction(final DelegationListProjectEvent event) {
                ReferentielTabPanel.this.disableEvents(true);
                // add BYTP
                if (CommonUtils.belongsBYEFEGroup(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
                    // if (ConstantClient.ENTITE_ID_IS_BYEFE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
                    ReferentielTabPanel.this.tabSet.getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_FORMATION).setEnabled(true);
                    ReferentielTabPanel.this.tabSet.getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_CONTROLE).setEnabled(true);
                    ReferentielTabPanel.this.tabSet.getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_EXTERNAL_CONTROLLER).setEnabled(true);
                } else {
                    ReferentielTabPanel.this.tabSet.getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_FORMATION).setEnabled(false);
                    ReferentielTabPanel.this.tabSet.getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_TYPE_CONTROLE).setEnabled(false);
                    ReferentielTabPanel.this.tabSet.getItemByItemId(ReferentielTabPanel.ACTION_ADMIN_EXTERNAL_CONTROLLER).setEnabled(false);
                }
                ReferentielTabPanel.this.disableEvents(false);
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

    public void initTab(String key) {
        ArrayList<AdminTabAction> tabActionList = new ArrayList<AdminTabAction>();
        tabActionList.add(new AdminTabAction(ACTION_ADMIN_NATURE, this.containerTabNature));
        tabActionList.add(new AdminTabAction(ACTION_ADMIN_TYPE_DELEGATION, this.containerTabTypeDelegation));
        tabActionList.add(new AdminTabAction(ACTION_ADMIN_STATUT_DELEGATION, this.containerTabStatutDelegation));
        tabActionList.add(new AdminTabAction(ACTION_ADMIN_TYPE_CHANTIER, this.containerTabTypeChantier));
        tabActionList.add(new AdminTabAction(ACTION_ADMIN_TYPE_PERIMETRE, this.containerTabTypePerimetre));
        tabActionList.add(new AdminTabAction(ACTION_ADMIN_DELEGANT_TYPE_GROUP, this.containerTabDelegantTypeGroup));
        tabActionList.add(new AdminTabAction(ACTION_ADMIN_DELEGANT_TYPE, this.containerTabDelegantType));
        tabActionList.add(new AdminTabAction(ACTION_ADMIN_LANGUE, this.containerTabLangue));
        tabActionList.add(new AdminTabAction(ACTION_ADMIN_DOCTYPE, this.containerTabDocType));
        tabActionList.add(new AdminTabAction(ACTION_ADMIN_CATEGORY, this.containerTabCategory));
        tabActionList.add(new AdminTabAction(ACTION_ADMIN_ENTITE_JURIDIQUE, this.containerTabEntiteJuridique));
        tabActionList.add(new AdminTabAction(ACTION_ADMIN_FORMATION, this.containerTabFormation));
        tabActionList.add(new AdminTabAction(ACTION_ADMIN_TYPE_CONTROLE, this.containerTabTypeControle));
        tabActionList.add(new AdminTabAction(ACTION_ADMIN_EXTERNAL_CONTROLLER, this.containerTabExternalController));

        this.tabSet = new TabPanel();
        this.tabSet.setTabScroll(true);

        TabItem item;
        for (final AdminTabAction tab : tabActionList) {
            item = new TabItem();
            item.setText(this.actionMessages.getString(tab.key));
            item.setClosable(false);
            item.setLayout(new FitLayout());
            item.setId(tab.key);
            item.setItemId(tab.key);
            this.tabSet.add(item);
            item.add(tab.content, new FitData(0));

            if (key == tab.key) {
                this.tabSet.setSelection(item);
            }
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
                    ReferentielTabPanel.this.disableEvents(true);
                    ReferentielTabPanel.this.restoreUI(ReferentielTabPanel.this.tabSet.getSelectedItem().getId());
                    ReferentielTabPanel.this.disableEvents(false);
                }
            });

        }

        // Viewport viewport = new Viewport();
        // viewport.setScrollMode(Scroll.AUTOX);
        // final BorderLayout layout = new BorderLayout();
        // viewport.setLayout(layout);
        // viewport.setStyleAttribute("padding", "0px");
        // viewport.setBorders(true);
        // viewport.add(new Label(""), new BorderLayoutData(LayoutRegion.SOUTH,45));
        // viewport.setStyleAttribute("background", "white");
        // viewport.add(tabSet, new BorderLayoutData(LayoutRegion.CENTER));

        Viewport viewport = new Viewport();
        viewport.setLayout(new BorderLayout());
        viewport.setBorders(true);
        viewport.setStyleAttribute("padding", "0px");
        viewport.setStyleAttribute("background", "white");
        viewport.add(this.tabSet, new BorderLayoutData(LayoutRegion.CENTER));
        viewport.add(new Label(""), new BorderLayoutData(LayoutRegion.SOUTH, 54));
        viewport.add(new Label(""), new BorderLayoutData(LayoutRegion.EAST, 184));
        this.add(viewport);
    }

    private void restoreUI(String tab) {
        if (ACTION_ADMIN_ENTITE_JURIDIQUE.equals(tab)) {
            ClientEntiteJuridiqueServiceAsync.Util.getInstance().findByEntiteId(SessionServiceImpl.getInstance().getEntiteContext().getEntId(),
                    new AsyncCallback<List<EntiteJuridiqueModel>>() {

                        @Override
                        public void onSuccess(List<EntiteJuridiqueModel> arg0) {
                            ContentEvent contentEvent = new ContentEvent();
                            contentEvent.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);

                            ModifyEntiteJuridiqueEvent event = new ModifyEntiteJuridiqueEvent();
                            event.setMode(ContentEvent.CHANGE_MODE_TO_ADMIN_ENTITE_JURIDIQUE_LIST_FORM);
                            event.setModel(null);
                            contentEvent.setEvent(event);
                            ReferentielTabPanel.this.bus.fireEvent(contentEvent);
                        }

                        @Override
                        public void onFailure(Throwable arg0) {
                        }
                    });
        }
    }

    public TabPanel getTabPanel() {
        return this.tabSet;
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
