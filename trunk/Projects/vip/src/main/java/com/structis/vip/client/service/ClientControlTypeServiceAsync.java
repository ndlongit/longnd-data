package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.ControlTypeModel;

public interface ClientControlTypeServiceAsync {

    public static class Util {

        private static ClientControlTypeServiceAsync instance = GWT.create(ClientControlTypeService.class);

        public static ClientControlTypeServiceAsync getInstance() {
            return instance;
        }
    }

    void delete(ControlTypeModel model, AsyncCallback<Boolean> callback);

    void findByEntite(String entiteId, AsyncCallback<List<ControlTypeModel>> callback);

    void insert(ControlTypeModel model, AsyncCallback<ControlTypeModel> callback);

    void update(ControlTypeModel model, AsyncCallback<ControlTypeModel> callback);

    void findAll(AsyncCallback<List<ControlTypeModel>> callback);

}
