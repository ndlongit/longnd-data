package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.DelegationStatusModel;

public interface ClientDelegationStatusServiceAsync {

    public static class Util {

        private static ClientDelegationStatusServiceAsync instance = GWT.create(ClientDelegationStatusService.class);

        public static ClientDelegationStatusServiceAsync getInstance() {
            return instance;
        }
    }

    void getAllDelegationStatuses(AsyncCallback<List<DelegationStatusModel>> callback);

    void findById(Integer id, AsyncCallback<DelegationStatusModel> callback);

    void insert(DelegationStatusModel model, AsyncCallback<DelegationStatusModel> asyncCallback);

    void update(DelegationStatusModel model, AsyncCallback<DelegationStatusModel> asyncCallback);

    void delete(DelegationStatusModel model, AsyncCallback<Boolean> callback);
}
