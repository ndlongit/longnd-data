package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.ExternControllerModel;

public interface ClientExternControllerServiceAsync {

    public static class Util {

        private static ClientExternControllerServiceAsync instance = GWT.create(ClientExternControllerService.class);

        public static ClientExternControllerServiceAsync getInstance() {
            return instance;
        }
    }

    void delete(ExternControllerModel model, AsyncCallback<Boolean> callback);

    void insert(ExternControllerModel model, AsyncCallback<ExternControllerModel> callback);

    void update(ExternControllerModel model, AsyncCallback<ExternControllerModel> callback);

    void findAll(AsyncCallback<List<ExternControllerModel>> callback);
}
