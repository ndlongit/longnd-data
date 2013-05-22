package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.RoleModel;
import com.structis.vip.shared.model.UserModel;

public interface ClientRoleServiceAsync {

    public static class Util {

        private static ClientRoleServiceAsync instance = GWT.create(ClientRoleService.class);

        public static ClientRoleServiceAsync getInstance() {
            return instance;
        }
    }

    void getRoles(UserModel userModel, AsyncCallback<List<RoleModel>> callback);
}
