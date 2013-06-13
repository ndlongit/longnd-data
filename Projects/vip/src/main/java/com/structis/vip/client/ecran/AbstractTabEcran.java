package com.structis.vip.client.ecran;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
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
import com.google.gwt.user.client.ui.RootPanel;
import com.structis.vip.client.event.UserModeEvent;
import com.structis.vip.client.message.ActionMessages;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.navigation.Action;
import com.structis.vip.client.navigation.NavigationFactory;
import com.structis.vip.client.navigation.NavigationService;
import com.structis.vip.client.panel.HeaderPanel;
import com.structis.vip.client.panel.UserModePanel;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.client.util.CommonUtils;
import com.structis.vip.client.view.ViewState;
import com.structis.vip.shared.model.UserModel;
import com.structis.vip.shared.model.UserRoleModel;

public abstract class AbstractTabEcran extends LayoutContainer {

    protected SimpleEventBus bus = new SimpleEventBus();

    protected final Messages messages = GWT.create(Messages.class);
    protected ActionMessages actionMessages = GWT.create(ActionMessages.class);

    protected NavigationService navigation = NavigationFactory.getNavigation();
    private ViewState viewState = new ViewState();
    private TabPanel tabSet = new TabPanel();
    private HeaderPanel headerPanel;
    private ArrayList<Action> tabActionList = new ArrayList<Action>();
    private TabItem tabControle;
    private TabItem tabDocument;
    private UserModePanel userModePanel;
    private UserModeEvent userModeEvent = new UserModeEvent();
    protected Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        GWT.log(this.getClass().getName() + ": onRender");
        logger.info(this.getClass().getName() + ": onRender");

        this.headerPanel = new HeaderPanel(bus, userModeEvent);

        RootPanel.get("appHeaderRight").clear();
        RootPanel.get("appHeaderRight").add(this.headerPanel);

        this.userModePanel = new UserModePanel(bus);

        RootPanel.get("userMode").clear();
        RootPanel.get("userMode").add(this.userModePanel);
    }

    public void initTab(LayoutContainer content, Action action) {
        this.tabActionList.add(Action.ACTION_DELEGATION);
        this.tabActionList.add(Action.ACTION_CONTROL);
        this.tabActionList.add(Action.ACTION_DOCUMENT);
        this.tabActionList.add(Action.ACTION_PILOTAGE);
        this.addTabItemsToList(content, this.tabActionList, action);

        this.tabSet.setResizeTabs(true);
        this.tabSet.setMinTabWidth(115);
        this.tabSet.setResizeTabs(true);
        this.tabSet.setAnimScroll(true);
        this.tabSet.setTabScroll(true);

        Viewport viewport = new Viewport();
        final BorderLayout layout = new BorderLayout();
        viewport.setLayout(layout);
        viewport.setStyleAttribute("padding", "0px 0px 12px 2px");
        viewport.setBorders(true);
        viewport.add(new Label(""), new BorderLayoutData(LayoutRegion.SOUTH, 45));
        viewport.setStyleAttribute("background", "white");
        viewport.add(this.tabSet, new BorderLayoutData(LayoutRegion.CENTER));
        this.add(viewport);
    }

    private void addTabItemsToList(LayoutContainer content, ArrayList<Action> tabActionList, Action action) {
        for (final Action keyAction : tabActionList) {
            TabItem item = new TabItem();
            item.setId("tab_" + this.actionMessages.getString(keyAction.getLabel()));
            item.setText(this.actionMessages.getString(keyAction.getLabel()));
            item.setClosable(false);
            item.setLayout(new FitLayout());
            this.tabSet.add(item);
            if (action == keyAction) {
                item.add(content, new FitData(0));
                this.tabSet.setSelection(item);
            } else {
                item.addListener(Events.BeforeSelect, new Listener<ComponentEvent>() {

                    @Override
                    public void handleEvent(ComponentEvent be) {
                        if (!AppUtil.checkToShowWarningInEditMode()) {
                            AbstractTabEcran.this.disableEvents(true);

                            AbstractTabEcran.this.navigation.goToEcran(keyAction);
                            be.setCancelled(true);

                            AbstractTabEcran.this.disableEvents(false);
                        } else {
                            be.setCancelled(true);
                        }
                    }
                });
            }
            if (keyAction == Action.ACTION_CONTROL) {
                this.tabControle = item;
            }
            if (keyAction == Action.ACTION_DOCUMENT) {
                this.tabDocument = item;
            }
        }
    }

    protected UserModel getUserContext() {
        return SessionServiceImpl.getInstance().getUserContext();
    }

    protected void setUserContext(UserModel usercontext) {
        SessionServiceImpl.getInstance().setUserContext(usercontext);
    }

    public ViewState getViewState() {
        this.viewState.clear();
        return this.viewState;
    }

    public void setViewState(final ViewState viewState) {
        this.viewState.clear();
    }

    public void resetTab() {
        this.disableEvents(true);
        UserModel user = SessionServiceImpl.getInstance().getUserContext();

        // add BYTP
        // if (SessionServiceImpl.getInstance().getEntiteContext() != null &&
        // (ConstantClient.ENTITE_ID_IS_BYEFE.equals(SessionServiceImpl.getInstance().getEntiteContext().getEntId()))) {
        if (SessionServiceImpl.getInstance().getEntiteContext() != null
                && CommonUtils.belongsBYEFEGroup(SessionServiceImpl.getInstance().getEntiteContext().getEntId())) {
            TabItem item = this.tabSet.getItemByItemId("tab_" + this.actionMessages.getString(Action.ACTION_DOCUMENT.getLabel()));
            TabItem tabDelegation = this.tabSet.getItemByItemId("tab_" + this.actionMessages.getString(Action.ACTION_DELEGATION.getLabel()));
            boolean tabSelected = true;
            if (item != null) {
                this.hideTab(this.tabDocument);
            }
            if (!this.hasDelegationRole(user)) {
                // showTab(tabDelegation);
                // } else {
                this.hideTab(tabDelegation);
                tabSelected = false;
            }

            if (this.hasControlRole(user)) {
                // if (true) {
                this.showTab(this.tabControle);
                if (!tabSelected) {
                    this.tabSet.setSelection(this.tabControle);
                }
            } else {
                this.hideTab(this.tabControle);
            }
        } else {
            // TabItem item = tabSet.getItemByItemId("tab_" + actionMessages.getString(Action.ACTION_CONTROL.getLabel()) );
            if (this.tabControle != null) {
                this.hideTab(this.tabControle);
            }
            this.showTab(this.tabDocument);

        }
        this.tabSet.repaint();
        this.disableEvents(false);
    }

    private boolean hasDelegationRole(UserModel user) {
        List<UserRoleModel> roles = user.getUserRoles();
        for (UserRoleModel r : roles) {
            if (r.getRole().getId() < 7) {
                return true;
            }
        }
        return false;
    }

    private boolean hasControlRole(UserModel user) {
        List<UserRoleModel> roles = user.getUserRoles();
        for (UserRoleModel r : roles) {
            if (r.getRole().getId() == 1 || r.getRole().getId() == 2 || r.getRole().getId() == 3 || r.getRole().getId() == 7
                    || r.getRole().getId() == 8) {
                return true;
            }
        }
        return false;
    }

    private void hideTab(TabItem tab) {
        tab.getHeader().hide();
        tab.hide();
    }

    private void showTab(TabItem tab) {
        tab.getHeader().show();
        tab.show();
    }
}
