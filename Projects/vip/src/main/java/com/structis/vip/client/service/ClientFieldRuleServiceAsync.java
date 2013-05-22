package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.FieldRuleModel;

public interface ClientFieldRuleServiceAsync {

    public static class Util {

        private static ClientFieldRuleServiceAsync instance = GWT.create(ClientFieldRuleService.class);

        public static ClientFieldRuleServiceAsync getInstance() {
            return instance;
        }
    }

    void getRulesByDemGroup(Integer group, AsyncCallback<List<FieldRuleModel>> callback);

    void getRules(String entId, String ptyId, Integer dnaId, Integer langId, AsyncCallback<List<FieldRuleModel>> callback);

    void update(FieldRuleModel fieldRuleModel, AsyncCallback<Boolean> callback);

    void updateList(List<FieldRuleModel> list, AsyncCallback<List<FieldRuleModel>> callback);

    void insert(FieldRuleModel fieldRuleModel, AsyncCallback<FieldRuleModel> callback);

    void insertList(List<FieldRuleModel> list, AsyncCallback<List<FieldRuleModel>> callback);

    void deleteByGroup(Integer group, AsyncCallback<Boolean> callback);
}
