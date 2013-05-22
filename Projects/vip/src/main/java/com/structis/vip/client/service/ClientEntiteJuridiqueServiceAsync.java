package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.EntiteJuridiqueModel;

public interface ClientEntiteJuridiqueServiceAsync {

    public static class Util {

        private static ClientEntiteJuridiqueServiceAsync instance = GWT.create(ClientEntiteJuridiqueService.class);

        public static ClientEntiteJuridiqueServiceAsync getInstance() {
            return instance;
        }
    }

    void findById(Integer id, AsyncCallback<EntiteJuridiqueModel> asyncCallback);

    void insert(EntiteJuridiqueModel model, AsyncCallback<EntiteJuridiqueModel> asyncCallback);

    void update(EntiteJuridiqueModel model, AsyncCallback<EntiteJuridiqueModel> asyncCallback);

    void findByEntiteId(String entId, AsyncCallback<List<EntiteJuridiqueModel>> asyncCallback);

    void getDefaultByEntiteId(String entId, AsyncCallback<EntiteJuridiqueModel> asyncCallback);

    void delete(EntiteJuridiqueModel model, AsyncCallback<Boolean> asyncCallback);
}
