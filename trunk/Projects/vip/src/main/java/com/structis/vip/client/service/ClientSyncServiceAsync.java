package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.AddressModel;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.SynETDEModel;

public interface ClientSyncServiceAsync {

    public static class Util {

        private static ClientSyncServiceAsync instance = GWT.create(ClientSyncService.class);

        public static ClientSyncServiceAsync getInstance() {
            return instance;
        }
    }

    void syncRUBIS(String entityId, AsyncCallback<List<CollaborateurModel>> callback);

    void getAddress(String idbycn, AsyncCallback<AddressModel> callback);

    void getRubsiCodesNameEtde(AsyncCallback<List<SynETDEModel>> asyncCallback);

    void syncRUBISWithItems(String entId, List<SynETDEModel> models, AsyncCallback<List<CollaborateurModel>> asyncCallback);

    void getRubsiCodesName(String entId, String entName, AsyncCallback<List<SynETDEModel>> asyncCallback);

}
