package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.DelegationMdlModel;
import com.structis.vip.shared.model.DelegationNatureModel;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.LanguageModel;
import com.structis.vip.shared.model.PerimetreTypeModel;

public interface ClientDelegationModelServiceAsync {

    public static class Util {

        private static ClientDelegationModelServiceAsync instance = GWT.create(ClientDelegationModelService.class);

        public static ClientDelegationModelServiceAsync getInstance() {
            return instance;
        }
    }

    public void getDocumentModels(LanguageModel lang, PerimetreTypeModel perimetre, DelegationNatureModel nature, Integer collaboraterType,
            AsyncCallback<List<DocumentMdlModel>> callback);

    public void getDocumentModels(LanguageModel lang, PerimetreTypeModel perimetre, DelegationNatureModel nature, EntiteModel entite,
            Integer collaboraterType, AsyncCallback<List<DocumentMdlModel>> callback);

    public void getGroup(LanguageModel lang, PerimetreTypeModel perimetre, DelegationNatureModel nature, Integer collaboraterType,
            AsyncCallback<Integer> callback);

    public void getGroup(LanguageModel lang, PerimetreTypeModel perimetre, DelegationNatureModel nature, EntiteModel entite,
            Integer collaboraterType, AsyncCallback<Integer> callback);

    void getDelegationModelBy(EntiteModel entite, DelegationNatureModel nature, AsyncCallback<List<DelegationMdlModel>> callback);

    void getDelegationModelsByGroup(Integer group, AsyncCallback<List<DelegationMdlModel>> callback);

    void getAllDelegationModelsByEntite(EntiteModel entite, AsyncCallback<List<DelegationMdlModel>> callback);

    void update(DelegationMdlModel dem, AsyncCallback<DelegationMdlModel> callback);

    void insert(DelegationMdlModel dem, AsyncCallback<DelegationMdlModel> callback);

    void updateBatch(List<DelegationMdlModel> dems, AsyncCallback callback);

    void insertBatch(List<DelegationMdlModel> dems, AsyncCallback callback);

    void findById(Integer id, AsyncCallback<DelegationMdlModel> callback);

    void delete(DelegationMdlModel dem, AsyncCallback<Boolean> callback);

    void insert(DelegationMdlModel dem, Integer group, AsyncCallback<DelegationMdlModel> callback);

    void insert(List<DelegationMdlModel> dems, Integer group, AsyncCallback<Integer> callback);

    void deleteByGroup(Integer group, AsyncCallback<Boolean> callback);

    public void getHasMutiDelegataire(Integer group, AsyncCallback<Boolean> asyncCallback);
}
