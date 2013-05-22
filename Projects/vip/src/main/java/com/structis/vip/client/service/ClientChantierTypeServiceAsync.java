package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.ChantierTypeModel;

public interface ClientChantierTypeServiceAsync {

    public static class Util {

        private static ClientChantierTypeServiceAsync instance = GWT.create(ClientChantierTypeService.class);

        public static ClientChantierTypeServiceAsync getInstance() {
            return instance;
        }
    }

    void delete(ChantierTypeModel model, AsyncCallback<Boolean> callback);

    void findChantierByEntite(String entiteId, AsyncCallback<List<ChantierTypeModel>> callback);

    void insert(ChantierTypeModel model, AsyncCallback<ChantierTypeModel> callback);

    void update(ChantierTypeModel model, AsyncCallback<ChantierTypeModel> callback);

    void findAll(AsyncCallback<List<ChantierTypeModel>> callback);

}
