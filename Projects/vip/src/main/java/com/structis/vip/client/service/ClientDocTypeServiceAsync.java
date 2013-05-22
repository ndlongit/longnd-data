package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.DocumentTypeModel;

public interface ClientDocTypeServiceAsync {

    public static class Util {

        private static ClientDocTypeServiceAsync instance = GWT.create(ClientDocTypeService.class);

        public static ClientDocTypeServiceAsync getInstance() {
            return instance;
        }
    }

    void getDocTypes(AsyncCallback<List<DocumentTypeModel>> callback);

    void findById(Integer DocTypeId, AsyncCallback<DocumentTypeModel> callback);

    void delete(DocumentTypeModel model, AsyncCallback<Boolean> callback);

    void insert(DocumentTypeModel model, AsyncCallback<DocumentTypeModel> callback);

    void update(DocumentTypeModel model, AsyncCallback<DocumentTypeModel> callback);
}
