package com.structis.vip.client.panel;

import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.event.LoadActionEvent;
import com.structis.vip.client.event.UserModeEvent;
import com.structis.vip.client.navigation.Action;
import com.structis.vip.client.navigation.NavigationEvent;
import com.structis.vip.client.navigation.NavigationFactory;
import com.structis.vip.client.navigation.NavigationService;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.LanguageModel;

public class HeaderPanel extends AbstractPanel {

    private NavigationService navigation = NavigationFactory.getNavigation();

    private ComboBox<LanguageModel> cbLanguage;
    private ListStore<LanguageModel> languages;
    private Label lblUserName;
    private Label lblDelegation;
    private Label lblAdministration;
    private UserModeEvent userModeEvent;

    public HeaderPanel(SimpleEventBus bus, UserModeEvent userModeEvent) {
        this.bus = bus;
        this.userModeEvent = userModeEvent;
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        initUI();

        if (currentUser != null) {
            lblUserName.setText(currentUser.getUserName());
            if (currentUser.isAdministrateur()) {
                lblAdministration.setVisible(true);

            } else {
                lblAdministration.setVisible(false);
            }
        }

    }

    private void initUI() {
        setLayout(new FlowLayout());

        VerticalPanel vp = new VerticalPanel();
        vp.setHorizontalAlign(HorizontalAlignment.RIGHT);

        HorizontalPanel hpTop = new HorizontalPanel();
        hpTop.setSpacing(4);

        lblDelegation = new Label(messages.headermenuswitchdelegation());
        lblDelegation.setStyleName("x-link-item");
        lblDelegation.setVisible(false);
        lblDelegation.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInEditMode() && !AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    NavigationEvent e = new NavigationEvent(new LoadActionEvent(null));
                    SessionServiceImpl.getInstance().setActionContext(Action.ACTION_DELEGATION);
                    lblDelegation.setVisible(false);
                    lblAdministration.setVisible(true);
                    navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE, e);
                    userModeEvent.setLblUserMode(HeaderPanel.messages.headermenuswitchdelegation());
                    bus.fireEvent(userModeEvent);
                }
            }
        });

        lblAdministration = new Label(messages.headermenuswitchadmin());
        lblAdministration.setStyleName("x-link-item");
        lblAdministration.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInEditMode() && !AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    NavigationEvent e = new NavigationEvent(new LoadActionEvent(null));
                    SessionServiceImpl.getInstance().setActionContext(Action.ACTION_ADMIN);
                    lblDelegation.setVisible(true);
                    lblAdministration.setVisible(false);
                    navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE, e);
                    userModeEvent.setLblUserMode(HeaderPanel.messages.headermenuswitchadmin());
                    bus.fireEvent(userModeEvent);
                }
            }
        });

        // init languages
        languages = new ListStore<LanguageModel>();
        cbLanguage = new ComboBox<LanguageModel>();
        cbLanguage.setFieldLabel(messages.commonlanguage());
        cbLanguage.setLabelSeparator("");
        cbLanguage.setStore(languages);
        cbLanguage.setDisplayField(LanguageModel.LAG_NAME);
        cbLanguage.setTriggerAction(TriggerAction.ALL);
        cbLanguage.setSize(150, -1);
        cbLanguage.addListener(Events.BeforeSelect, new Listener<ComponentEvent>() {

            @Override
            public void handleEvent(ComponentEvent be) {
                if (AppUtil.checkToShowWarningInEditMode() || AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    be.setCancelled(true);
                    be.cancelBubble();
                }
            }
        });
        cbLanguage.addListener(Events.Select, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                String path = Window.Location.getHref();

                if (path != null && path.length() > 0) {
                    int mchar = path.lastIndexOf("#");
                    String page = "vip.html";
                    String qchar = "?";

                    if (path.lastIndexOf(new String(page + qchar)) != -1) {
                        // IT'S OK
                    } else if (path.lastIndexOf(page) != -1) {
                        path = path.substring(0, mchar) + qchar + path.subSequence(mchar, path.length());
                    } else if (path.indexOf(page) == -1) {
                        path = path.substring(0, mchar) + page + qchar + path.subSequence(mchar, path.length());
                    }

                    mchar = path.lastIndexOf("#");
                    String paramName = "&locale=";
                    String param = paramName + cbLanguage.getValue().getCode().toLowerCase();
                    if (path.lastIndexOf(paramName) != -1) {
                        String cur = path.substring(path.lastIndexOf(paramName), path.lastIndexOf(paramName) + 10);
                        path = path.replace(cur, param);
                        Window.Location.replace(path);
                    } else {
                        Window.Location.replace(path.substring(0, mchar) + param + path.substring(mchar, path.length()));
                    }

                    LanguageModel lang = cbLanguage.getValue();
                    SessionServiceImpl.getInstance().setLanguageContext(lang);
                }
            }
        });

        List<LanguageModel> i18n = AppUtil.getLanguages();
        languages.add(i18n);
        if (cbLanguage.getValue() == null) {
            if (i18n != null && i18n.size() > 0) {
                if (SessionServiceImpl.getInstance().getLanguageContext() != null) {
                    cbLanguage.select(SessionServiceImpl.getInstance().getLanguageContext());
                    cbLanguage.setValue(SessionServiceImpl.getInstance().getLanguageContext());
                } else {
                    String path = Window.Location.getHref();
                    String paramName = "&locale=";

                    if (path.lastIndexOf(paramName) != -1) {
                        String currentLangCode = path.substring(path.lastIndexOf(paramName) + 8, path.lastIndexOf(paramName) + 10);
                        for (LanguageModel languageModel : i18n) {
                            if (languageModel.getCode().equalsIgnoreCase(currentLangCode)) {
                                cbLanguage.select(languageModel);
                                cbLanguage.setValue(languageModel);
                                break;
                            }
                        }
                    } else {
                        for (LanguageModel languageModel : i18n) {
                            if (ClientConstant.LANGUAGE_CODE_FRENCH.equalsIgnoreCase(languageModel.getCode())) {
                                cbLanguage.select(languageModel);
                                cbLanguage.setValue(languageModel);
                                break;
                            }
                        }
                    }
                }
            }
        }

        hpTop.add(lblDelegation);
        hpTop.add(lblAdministration);
        hpTop.add(cbLanguage);

        HorizontalPanel hpBottom = new HorizontalPanel();
        hpBottom.setStyleAttribute("margin-right", "5px");

        lblUserName = new Label();
        lblUserName.setStyleName("x-link-item");

        hpBottom.add(lblUserName);

        vp.add(hpTop);
        vp.add(hpBottom);

        add(vp);
    }
}
