package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.DemDomModel;

public interface ClientDemDomServiceAsync {

    public static class Util {

        private static ClientDemDomServiceAsync instance = GWT.create(ClientDemDomService.class);

        public static ClientDemDomServiceAsync getInstance() {
            return instance;
        }

    }

    void getAllDemDomsByDemGroup(Integer group, AsyncCallback<List<DemDomModel>> callback);

    void insert(DemDomModel demDom, AsyncCallback<DemDomModel> callback);

    void insert(List<DemDomModel> demDoms, Integer group, AsyncCallback<Integer> callback);

    void deleteByGroup(Integer group, AsyncCallback<Boolean> callback);
}
