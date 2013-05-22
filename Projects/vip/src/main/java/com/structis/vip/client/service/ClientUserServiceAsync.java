package com.structis.vip.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.model.UserModel;

public interface ClientUserServiceAsync {

    public static class Util {

        private static ClientUserServiceAsync instance = GWT.create(ClientUserService.class);

        public static ClientUserServiceAsync getInstance() {
            return instance;
        }
    }

    void findUsers(AsyncCallback<List<UserModel>> callback);

    void findUsersByEntite(String entiteId, UserModel userModel, AsyncCallback<List<UserModel>> callback);

    void findUserById(final int id, AsyncCallback<UserModel> callback);

    void findUserByUserName(final String userName, String domain, AsyncCallback<UserModel> callback);

    void insert(UserModel userModel, AsyncCallback<UserModel> callback);

    void update(UserModel userModel, AsyncCallback<UserModel> callback);

    void updateNoRoles(UserModel userModel, AsyncCallback<UserModel> callback);

    void delete(UserModel userModel, AsyncCallback<Boolean> callback);

    void getAuthorization(String name, Integer domainId, String password, AsyncCallback<UserModel> asyncCallback);

    void changePassword(Integer userId, String value, String newValue, AsyncCallback<ExceptionType> asyncCallback);

    void findUsersByEntiteRemote(PagingLoadConfig config, String entId, UserModel userModel, AsyncCallback<PagingLoadResult<UserModel>> callback);

    void findByPerimetre(String perId, AsyncCallback<List<UserModel>> asyncCallback);
}
