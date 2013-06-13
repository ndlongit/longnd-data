package com.structis.vip.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.util.Theme;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.client.event.DelegationListProjectEvent;
import com.structis.vip.client.navigation.Action;
import com.structis.vip.client.navigation.ActionHelper;
import com.structis.vip.client.navigation.NavigationEvent;
import com.structis.vip.client.navigation.NavigationFactory;
import com.structis.vip.client.navigation.NavigationService;
import com.structis.vip.client.service.ClientAuthenticationServiceAsync;
import com.structis.vip.client.service.ClientPerimetreServiceAsync;
import com.structis.vip.client.session.SessionServiceImpl;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.UserModel;

public class vip implements EntryPoint, ValueChangeHandler<String> {

    private NavigationService navigation = NavigationFactory.getNavigation();

    private ClientPerimetreServiceAsync clientPerimetreService = ClientPerimetreServiceAsync.Util.getInstance();

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
        // Choose the theme
        GXT.setDefaultTheme(Theme.BLUE, true);

        // TODO do we add handle for history change?
        // History.addValueChangeHandler(this);
        if (SessionServiceImpl.getInstance().getUserContext() == null) {
            ClientAuthenticationServiceAsync.Util.getInstance().ssoLogin(new AsyncCallback<UserModel>() {

                @Override
                public void onSuccess(final UserModel userContext) {
                    if (userContext == null) {
                        vip.this.navigation.goToEcran(Action.ACTION_LOGIN);
                    } else {
                        SessionServiceImpl.getInstance().setUserContext(userContext);
                        if (userContext.isAdministrateur() || (userContext.getPerimetre() == null)) {
                            vip.this.navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
                        } else {
                            vip.this.clientPerimetreService.findFirstLevelPerimetreByUserRoles(userContext.getEntite().getEntId(), false,
                                    userContext.getUserRoles(), new AsyncCallback<List<PerimetreModel>>() {

                                        @Override
                                        public void onSuccess(List<PerimetreModel> arg0) {
                                            boolean isAuto = false;
                                            for (PerimetreModel perMdl : arg0) {
                                                if (perMdl.getPerId().equals(userContext.getPerimetre().getPerId())) {
                                                    SessionServiceImpl.getInstance().setEntiteContext(userContext.getEntite());
                                                    SessionServiceImpl.getInstance().setPerimetreContext(userContext.getPerimetre());
                                                    NavigationEvent e = new NavigationEvent(new DelegationListProjectEvent(userContext.getEntite(),
                                                            userContext.getPerimetre()));
                                                    isAuto = true;
                                                    vip.this.navigation.goToEcran(Action.ACTION_DELEGATION, e);
                                                    break;
                                                }
                                            }
                                            if (!isAuto) {
                                                vip.this.navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Throwable arg0) {
                                            vip.this.navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
                                        }
                                    });
                        }
                    }
                }

                @Override
                public void onFailure(Throwable caught) {
                    vip.this.navigation.goToEcran(Action.ACTION_LOGIN);
                }
            });
        } else {
            final UserModel userContext2 = SessionServiceImpl.getInstance().getUserContext();
            if (userContext2.isAdministrateur() || (userContext2.getPerimetre() == null)) {
                this.navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
            } else {
                this.clientPerimetreService.findFirstLevelPerimetreByUserRoles(userContext2.getEntite().getEntId(), false,
                        userContext2.getUserRoles(), new AsyncCallback<List<PerimetreModel>>() {

                            @Override
                            public void onSuccess(List<PerimetreModel> arg0) {
                                boolean isAuto = false;
                                for (PerimetreModel perMdl : arg0) {
                                    if (perMdl.getPerId().equals(userContext2.getPerimetre().getPerId())) {
                                        SessionServiceImpl.getInstance().setEntiteContext(userContext2.getEntite());
                                        SessionServiceImpl.getInstance().setPerimetreContext(userContext2.getPerimetre());
                                        NavigationEvent e = new NavigationEvent(new DelegationListProjectEvent(userContext2.getEntite(), userContext2
                                                .getPerimetre()));
                                        isAuto = true;
                                        vip.this.navigation.goToEcran(Action.ACTION_DELEGATION, e);
                                        break;
                                    }
                                }
                                if (!isAuto) {
                                    vip.this.navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
                                }
                            }

                            @Override
                            public void onFailure(Throwable arg0) {
                                vip.this.navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
                            }
                        });
            }
        }
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        // navigation.goToEcran(Action.ACTION_LOGIN);
        String rawEvent = event.getValue();
        String realEvent = rawEvent;
        Map<String, String> params = new HashMap<String, String>();

        if (realEvent.contains("&")) {
            // parse parameters
            realEvent = rawEvent.substring(0, rawEvent.indexOf("&"));
            String paramsString = rawEvent.substring(rawEvent.indexOf("&"));
            for (String string : paramsString.split("&")) {
                String[] text = string.split("=", 2);
                if (text.length == 2) {
                    params.put(text[0], text[1]);
                }
            }
        }

        Action dest = ActionHelper.getActionFromLabel(realEvent);

        if (null == dest) {
            this.navigation.goToEcran(Action.ACTION_CHOOSE_ENTITE);
        } else {
            if (this.navigation.getActionActuelle() != dest) {
                this.navigation.goToEcran(dest, new NavigationEvent(params));
            } else if (!params.isEmpty()) {
                this.navigation.goToEcran(dest, new NavigationEvent(params, false));
            }
        }
    }
}
