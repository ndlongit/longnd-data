package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.DelegationTypeModel;

public interface ClientDelegationTypeServiceAsync {

    public static class Util {

        private static ClientDelegationTypeServiceAsync instance = GWT.create(ClientDelegationTypeService.class);

        public static ClientDelegationTypeServiceAsync getInstance() {
            return instance;
        }
    }

    void getAllTypes(AsyncCallback<List<DelegationTypeModel>> callback);

    void getPrincipleType(AsyncCallback<DelegationTypeModel> callback);

    void getTemporaryType(AsyncCallback<DelegationTypeModel> callback);

    void getSubType(AsyncCallback<DelegationTypeModel> callback);

    void getDelegationTypeOf(String type, AsyncCallback<DelegationTypeModel> callback);

    void insert(DelegationTypeModel model, AsyncCallback<DelegationTypeModel> callback);

    void update(DelegationTypeModel model, AsyncCallback<DelegationTypeModel> callback);
}
