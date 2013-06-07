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
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.constant.ClientConstant;
import com.structis.vip.client.event.LoadActionEvent;
import com.structis.vip.client.event.UserModeEvent;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.navigation.Action;
import com.structis.vip.client.navigation.NavigationEvent;
import com.structis.vip.client.navigation.NavigationFactory;
import com.structis.vip.client.navigation.NavigationService;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.client.util.AppUtil;
import com.structis.vip.shared.model.LanguageModel;
import com.structis.vip.shared.model.UserModel;

public class HeaderPanel extends AbstractPanel {

    private final Messages messages = GWT.create(Messages.class);

    private NavigationService navigation = NavigationFactory.getNavigation();

    private ComboBox<LanguageModel> cbLanguage;
    private ListStore<LanguageModel> languages;
    private Label lblUserName;
    Label lblDelegation;
    Label lblAdministration;
    SimpleEventBus bus;
    private UserModel userModel;
    private UserModeEvent userModeEvent;

    public HeaderPanel(SimpleEventBus bus,UserModeEvent userModeEvent) {
    	this.bus = bus;
        this.userModel = SessionServiceImpl.getInstance().getUserContext();
        this.userModeEvent = userModeEvent;
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        this.initUI();

        if (this.userModel != null) {
            this.lblUserName.setText(this.userModel.getUserName());
            if (this.userModel.isAdministrateur()) {
                this.lblAdministration.setVisible(true);
              
            } else {
                this.lblAdministration.setVisible(false);
            }
        }

    }

    private void initUI() {
        this.setLayout(new FlowLayout());
       
        VerticalPanel vp = new VerticalPanel();
        vp.setHorizontalAlign(HorizontalAlignment.RIGHT);

        HorizontalPanel hpTop = new HorizontalPanel();
        hpTop.setSpacing(4);
        
        this.lblDelegation = new Label(this.messages.headermenuswitchdelegation());
        this.lblDelegation.setStyleName("x-link-item");
        this.lblDelegation.setVisible(false);
        this.lblDelegation.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInEditMode() && !AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    NavigationEvent e = new NavigationEvent(new LoadActionEvent(null));
                    SessionServiceImpl.getInstance().setActionContext(Action.ACTION_DELEGATION);
                    HeaderPanel.this.lblDelegation.setVisible(false);
                    HeaderPanel.this.lblAdministration.setVisible(true);
                    HeaderPanel.this.navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE, e);      
                    userModeEvent.setLblUserMode(HeaderPanel.this.messages.headermenuswitchdelegation());
                    HeaderPanel.this.bus.fireEvent(userModeEvent);
                }
            }
        });
        

        
        this.lblAdministration = new Label(this.messages.headermenuswitchadmin());
        this.lblAdministration.setStyleName("x-link-item");
        this.lblAdministration.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent arg0) {
                if (!AppUtil.checkToShowWarningInEditMode() && !AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    NavigationEvent e = new NavigationEvent(new LoadActionEvent(null));
                    SessionServiceImpl.getInstance().setActionContext(Action.ACTION_ADMIN);
                    HeaderPanel.this.lblDelegation.setVisible(true);
                    HeaderPanel.this.lblAdministration.setVisible(false);
                    HeaderPanel.this.navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE, e);
                    userModeEvent.setLblUserMode(HeaderPanel.this.messages.headermenuswitchadmin());
                    HeaderPanel.this.bus.fireEvent(userModeEvent);
                }
            }
        });

        // init languages
        this.languages = new ListStore<LanguageModel>();
        this.cbLanguage = new ComboBox<LanguageModel>();
        this.cbLanguage.setFieldLabel(this.messages.commonlanguage());
        this.cbLanguage.setLabelSeparator("");
        this.cbLanguage.setStore(this.languages);
        this.cbLanguage.setDisplayField(LanguageModel.LAG_NAME);
        this.cbLanguage.setTriggerAction(TriggerAction.ALL);
        this.cbLanguage.setSize(150, -1);
        this.cbLanguage.addListener(Events.BeforeSelect, new Listener<ComponentEvent>() {

            @Override
            public void handleEvent(ComponentEvent be) {
                if (AppUtil.checkToShowWarningInEditMode() || AppUtil.checkToShowWarningInAdminEditMode(false)) {
                    be.setCancelled(true);
                    be.cancelBubble();
                }
            }
        });
        this.cbLanguage.addListener(Events.Select, new Listener<BaseEvent>() {

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
                    String param = paramName + HeaderPanel.this.cbLanguage.getValue().getCode().toLowerCase();
                    if (path.lastIndexOf(paramName) != -1) {
                        String cur = path.substring(path.lastIndexOf(paramName), path.lastIndexOf(paramName) + 10);
                        path = path.replace(cur, param);
                        Window.Location.replace(path);
                    } else {
                        Window.Location.replace(path.substring(0, mchar) + param + path.substring(mchar, path.length()));
                    }

                    LanguageModel lang = HeaderPanel.this.cbLanguage.getValue();
                    SessionServiceImpl.getInstance().setLanguageContext(lang);
                }
            }
        });

        List<LanguageModel> i18n = AppUtil.getLanguages();
        this.languages.add(i18n);
        if (this.cbLanguage.getValue() == null) {
            if (i18n != null && i18n.size() > 0) {
                if (SessionServiceImpl.getInstance().getLanguageContext() != null) {
                    this.cbLanguage.select(SessionServiceImpl.getInstance().getLanguageContext());
                    this.cbLanguage.setValue(SessionServiceImpl.getInstance().getLanguageContext());
                } else {
                    String path = Window.Location.getHref();
                    String paramName = "&locale=";

                    if (path.lastIndexOf(paramName) != -1) {
                        String currentLangCode = path.substring(path.lastIndexOf(paramName) + 8, path.lastIndexOf(paramName) + 10);
                        for (LanguageModel languageModel : i18n) {
                            if (languageModel.getCode().equalsIgnoreCase(currentLangCode)) {
                                this.cbLanguage.select(languageModel);
                                this.cbLanguage.setValue(languageModel);
                                break;
                            }
                        }
                    } else {
                        for (LanguageModel languageModel : i18n) {
                            if (ClientConstant.LANGUAGE_CODE_FRENCH.equalsIgnoreCase(languageModel.getCode())) {
                                this.cbLanguage.select(languageModel);
                                this.cbLanguage.setValue(languageModel);
                                break;
                            }
                        }
                    }
                }
            }
        }

        hpTop.add(this.lblDelegation);
        hpTop.add(this.lblAdministration);
        hpTop.add(this.cbLanguage);
      
        HorizontalPanel hpBottom = new HorizontalPanel();
        hpBottom.setStyleAttribute("margin-right", "5px");
    
        this.lblUserName = new Label();
        this.lblUserName.setStyleName("x-link-item");

		hpBottom.add(this.lblUserName);
		
		vp.add(hpTop);
		vp.add(hpBottom);

        this.add(vp);
    }
}
