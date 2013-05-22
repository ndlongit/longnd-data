package com.structis.vip.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.UserModel;

public interface ClientAuthenticationServiceAsync {

    public static class Util {

        private static ClientAuthenticationServiceAsync instance = GWT.create(ClientAuthenticationService.class);

        public static ClientAuthenticationServiceAsync getInstance() {
            return instance;
        }
    }

    void login(String userName, String passWord, AsyncCallback<UserModel> callback);

    void ssoLogin(AsyncCallback<UserModel> callback);

    void ssoLogout(AsyncCallback<Void> callback);
}
