package com.structis.vip.client.panel;

import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Label;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.exception.AsyncCallbackWithErrorResolution;
import com.structis.vip.client.message.ConstantMessages;
import com.structis.vip.client.message.Messages;
import com.structis.vip.client.navigation.Action;
import com.structis.vip.client.navigation.NavigationEvent;
import com.structis.vip.client.navigation.NavigationFactory;
import com.structis.vip.client.navigation.NavigationService;
import com.structis.vip.client.service.ClientDomainServiceAsync;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.service.ClientUserServiceAsync;
import com.structis.vip.client.session.SessionService;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.SharedConstant;
import com.structis.vip.shared.model.DomainModel;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.UserModel;

public class LoginPanel extends FormPanel {

    private NavigationService navigation = NavigationFactory.getNavigation();
    private final Messages messages = GWT.create(Messages.class);
    private static final ConstantMessages config = GWT.create(ConstantMessages.class);
    private SimpleEventBus bus;
    private ListStore<DomainModel> domainStore = new ListStore<DomainModel>();
    private TextField<String> txtName;
    private TextField<String> txtPassword;
    private ComboBox<DomainModel> cboDomain;
    private DomainModel blankDomain;
    private LayoutContainer errorLayout = new LayoutContainer();
    private Label lblErrorMessage;
    private ClientPerimetreServiceAsync clientPerimetreService = ClientPerimetreServiceAsync.Util.getInstance();

    private static String runningMode = config.runMode();

    public LoginPanel(SimpleEventBus bus) {
        this.bus = bus;
        this.createContent();

        // for Development mode only
        if (SharedConstant.RunMode.DEVELOPMENT.value().equalsIgnoreCase(runningMode)) {
            this.txtName.setValue("philippe.nguyen");
        }
        ClientDomainServiceAsync.Util.getInstance().getDomains(new AsyncCallbackWithErrorResolution<List<DomainModel>>() {

            @Override
            public void onSuccess(List<DomainModel> result) {
                LoginPanel.this.domainStore.removeAll();
                LoginPanel.this.blankDomain = new DomainModel();
                LoginPanel.this.blankDomain.setId(null);
                LoginPanel.this.blankDomain.setName("");
                LoginPanel.this.domainStore.add(LoginPanel.this.blankDomain);
                LoginPanel.this.domainStore.add(result);

                // for DEV only
                if (SharedConstant.RunMode.DEVELOPMENT.value().equalsIgnoreCase(runningMode)) {
                    if (result != null && result.size() > 0) {
                        LoginPanel.this.cboDomain.setValue(result.get(result.size() - 1));
                    }
                }
            }
        });
    }

    private void createContent() {
        this.setStyleAttribute("margin", "0px");
        this.setHeading(this.messages.userloginheader());
        this.setFrame(true);
        this.setButtonAlign(HorizontalAlignment.CENTER);
        this.setWidth(330);
        if (SharedConstant.RunMode.DEVELOPMENT.value().equalsIgnoreCase(runningMode)) {
            this.setHeight(165);
        } else {
            this.setHeight(100);
        }
        FormLayout formLayout = new FormLayout();
        formLayout.setLabelAlign(LabelAlign.RIGHT);
        formLayout.setLabelWidth(80);
        this.setLayout(formLayout);

        this.errorLayout = new LayoutContainer();
        this.errorLayout.setHeight(30);

        this.lblErrorMessage = new Label("");
        this.lblErrorMessage.setStyleName("errorMessage");
        this.errorLayout.add(this.lblErrorMessage);
        if (SharedConstant.RunMode.DEVELOPMENT.value().equalsIgnoreCase(runningMode)) {
            this.errorLayout.setVisible(false);
        } else {
            this.lblErrorMessage.setText(this.messages.commonnopermission());
        }
        this.add(this.errorLayout);
        this.txtName = new TextField<String>();
        this.txtName.setFieldLabel(this.messages.userloginname());
        this.txtName.setLabelSeparator("");
        if (SharedConstant.RunMode.DEVELOPMENT.value().equalsIgnoreCase(runningMode)) {
            this.add(this.txtName);
        }

        this.txtPassword = new TextField<String>();
        this.txtPassword.setFieldLabel(this.messages.userloginpasseword());
        this.txtPassword.setPassword(true);
        this.txtPassword.setLabelSeparator("");
        if (SharedConstant.RunMode.DEVELOPMENT.value().equalsIgnoreCase(runningMode)) {
            this.add(this.txtPassword);
        }

        this.cboDomain = new ComboBox<DomainModel>();
        this.cboDomain.setEditable(false);
        this.cboDomain.setTemplate(this.getTemplate());
        this.cboDomain.setLabelSeparator("");
        this.cboDomain.setFieldLabel(this.messages.userlogindomain());
        this.cboDomain.setDisplayField("name");
        this.cboDomain.setName("name");
        this.cboDomain.setValueField("symbol");
        this.cboDomain.setForceSelection(true);
        this.cboDomain.setStore(this.domainStore);
        this.cboDomain.setTriggerAction(TriggerAction.ALL);
        if (SharedConstant.RunMode.DEVELOPMENT.value().equalsIgnoreCase(runningMode)) {
            this.add(this.cboDomain);

            Button btnLogin = new Button(this.messages.userloginheader());
            btnLogin.addSelectionListener(new SelectionListener<ButtonEvent>() {

                @Override
                public void componentSelected(ButtonEvent ce) {
                    String name = LoginPanel.this.txtName.getValue() == null ? "" : LoginPanel.this.txtName.getValue();
                    String password = LoginPanel.this.txtPassword.getValue() == null ? "" : LoginPanel.this.txtPassword.getValue();
                    Integer domainId = LoginPanel.this.cboDomain.getValue() == null ? null : LoginPanel.this.cboDomain.getValue().getId();
                    LoginPanel.this.login(name, domainId, password);
                }
            });
            this.addButton(btnLogin);
        }
    }

    private native String getTemplate() /*-{
                                        return [
                                        '<tpl for=".">',
                                        '<tpl if="name == \'\'">',
                                        '<div class="x-combo-list-item" qtip="N/A" qtitle=""></BR></div>',
                                        '</tpl>',
                                        '<tpl if="name != \'\'">',
                                        '<div class="x-combo-list-item" qtip="{name}" qtitle="">{name}</div>',
                                        '</tpl>', '</tpl>' ].join("");
                                        }-*/;

    protected void login(String name, Integer domainId, String password) {
        ClientUserServiceAsync.Util.getInstance().getAuthorization(name, domainId, password, new AsyncCallbackWithErrorResolution<UserModel>() {

            @Override
            public void onSuccess(final UserModel result) {
                if (result == null) {
                    LoginPanel.this.setHeight(195);
                    LoginPanel.this.lblErrorMessage.setText(LoginPanel.this.messages.usernotauthorized());
                    LoginPanel.this.errorLayout.show();
                } else {
                    final SessionService sessionService = SessionServiceImpl.getInstance();
                    sessionService.setUserContext(result);
                    if (result.isAdministrateur() || (result.getPerimetre() == null)) {
                        LoginPanel.this.navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
                    } else {
                        LoginPanel.this.clientPerimetreService.findFirstLevelPerimetreByUserRoles(result.getEntite().getEntId(), false,
                                result.getUserRoles(), new AsyncCallbackWithErrorResolution<List<PerimetreModel>>() {

                                    @Override
                                    public void onSuccess(List<PerimetreModel> arg0) {
                                        boolean isAuto = false;
                                        for (PerimetreModel perMdl : arg0) {
                                            if (perMdl.getPerId().equals(result.getPerimetre().getPerId())) {
                                                sessionService.setEntiteContext(result.getEntite());
                                                sessionService.setPerimetreContext(result.getPerimetre());
                                                NavigationEvent e = new NavigationEvent(new DelegationListProjectEvent(result.getEntite(), result
                                                        .getPerimetre()));
                                                isAuto = true;
                                                LoginPanel.this.navigation.goToEcran(Action.ACTION_DELEGATION, e);
                                                break;
                                            }
                                        }
                                        if (!isAuto) {
                                            LoginPanel.this.navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Throwable arg0) {
                                        LoginPanel.this.navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
                                    }
                                });
                    }
                }
            }
        });
    }
}
