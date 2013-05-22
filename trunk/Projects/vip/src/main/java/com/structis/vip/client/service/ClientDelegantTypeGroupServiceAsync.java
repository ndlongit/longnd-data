package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.DelegantTypeGroupModel;

public interface ClientDelegantTypeGroupServiceAsync {

    public static class Util {

        private static ClientDelegantTypeGroupServiceAsync instance = GWT.create(ClientDelegantTypeGroupService.class);

        public static ClientDelegantTypeGroupServiceAsync getInstance() {
            return instance;
        }
    }

    void getDelegantTypeGroups(AsyncCallback<List<DelegantTypeGroupModel>> callback);

    void findById(Integer DelegantTypeGroupId, AsyncCallback<DelegantTypeGroupModel> callback);

    void delete(DelegantTypeGroupModel model, AsyncCallback<Boolean> callback);

    void insert(DelegantTypeGroupModel model, AsyncCallback<DelegantTypeGroupModel> callback);

    void update(DelegantTypeGroupModel model, AsyncCallback<DelegantTypeGroupModel> callback);
}
