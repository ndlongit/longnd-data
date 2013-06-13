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

    private static String runMode = config.runMode();

    public LoginPanel(SimpleEventBus bus) {
        this.bus = bus;
        createContent();

        // for Development mode only
        if (SharedConstant.RunMode.DEVELOPMENT.value().equalsIgnoreCase(runMode)) {
            txtName.setValue("philippe.nguyen");
        }
        ClientDomainServiceAsync.Util.getInstance().getDomains(new AsyncCallbackWithErrorResolution<List<DomainModel>>() {

            @Override
            public void onSuccess(List<DomainModel> result) {
                domainStore.removeAll();
                blankDomain = new DomainModel();
                blankDomain.setId(null);
                blankDomain.setName("");
                domainStore.add(blankDomain);
                domainStore.add(result);

                // for DEV only
                if (SharedConstant.RunMode.DEVELOPMENT.value().equalsIgnoreCase(runMode)) {
                    if (result != null && result.size() > 0) {
                        cboDomain.setValue(result.get(result.size() - 1));
                    }
                }
            }
        });
    }

    private void createContent() {
        setStyleAttribute("margin", "0px");
        setHeading(messages.userloginheader());
        setFrame(true);
        setButtonAlign(HorizontalAlignment.CENTER);
        setWidth(330);
        if (SharedConstant.RunMode.DEVELOPMENT.value().equalsIgnoreCase(runMode) || SharedConstant.RunMode.TESTING.value().equalsIgnoreCase(runMode)) {
            setHeight(165);
        } else {
            setHeight(100);
        }
        FormLayout formLayout = new FormLayout();
        formLayout.setLabelAlign(LabelAlign.RIGHT);
        formLayout.setLabelWidth(80);
        setLayout(formLayout);

        errorLayout = new LayoutContainer();
        errorLayout.setHeight(30);

        lblErrorMessage = new Label("");
        lblErrorMessage.setStyleName("errorMessage");
        errorLayout.add(lblErrorMessage);
        if (SharedConstant.RunMode.DEVELOPMENT.value().equalsIgnoreCase(runMode) || SharedConstant.RunMode.TESTING.value().equalsIgnoreCase(runMode)) {
            errorLayout.setVisible(false);
        } else {
            lblErrorMessage.setText(messages.commonnopermission());
        }
        add(errorLayout);
        txtName = new TextField<String>();
        txtName.setFieldLabel(messages.userloginname());
        txtName.setLabelSeparator("");
        if (SharedConstant.RunMode.DEVELOPMENT.value().equalsIgnoreCase(runMode) || SharedConstant.RunMode.TESTING.value().equalsIgnoreCase(runMode)) {
            add(txtName);
        }

        txtPassword = new TextField<String>();
        txtPassword.setFieldLabel(messages.userloginpasseword());
        txtPassword.setPassword(true);
        txtPassword.setLabelSeparator("");
        if (SharedConstant.RunMode.DEVELOPMENT.value().equalsIgnoreCase(runMode) || SharedConstant.RunMode.TESTING.value().equalsIgnoreCase(runMode)) {
            add(txtPassword);
        }

        cboDomain = new ComboBox<DomainModel>();
        cboDomain.setEditable(false);
        cboDomain.setTemplate(getTemplate());
        cboDomain.setLabelSeparator("");
        cboDomain.setFieldLabel(messages.userlogindomain());
        cboDomain.setDisplayField("name");
        cboDomain.setName("name");
        cboDomain.setValueField("symbol");
        cboDomain.setForceSelection(true);
        cboDomain.setStore(domainStore);
        cboDomain.setTriggerAction(TriggerAction.ALL);
        if (SharedConstant.RunMode.DEVELOPMENT.value().equalsIgnoreCase(runMode) || SharedConstant.RunMode.TESTING.value().equalsIgnoreCase(runMode)) {
            add(cboDomain);

            Button btnLogin = new Button(messages.userloginheader());
            btnLogin.addSelectionListener(new SelectionListener<ButtonEvent>() {

                @Override
                public void componentSelected(ButtonEvent ce) {
                    String name = txtName.getValue() == null ? "" : txtName.getValue();
                    String password = txtPassword.getValue() == null ? "" : txtPassword.getValue();
                    Integer domainId = cboDomain.getValue() == null ? null : cboDomain.getValue().getId();
                    login(name, domainId, password);
                }
            });
            addButton(btnLogin);
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
                    setHeight(195);
                    lblErrorMessage.setText(messages.usernotauthorized());
                    errorLayout.show();
                } else {
                    final SessionService sessionService = SessionServiceImpl.getInstance();
                    sessionService.setUserContext(result);
                    if (result.isAdministrateur() || (result.getPerimetre() == null)) {
                        navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
                    } else {
                        clientPerimetreService.findFirstLevelPerimetreByUserRoles(result.getEntite().getEntId(), false, result.getUserRoles(),
                                new AsyncCallbackWithErrorResolution<List<PerimetreModel>>() {

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
                                                navigation.goToEcran(Action.ACTION_DELEGATION, e);
                                                break;
                                            }
                                        }
                                        if (!isAuto) {
                                            navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Throwable arg0) {
                                        navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
                                    }
                                });
                    }
                }
            }
        });
    }
}
