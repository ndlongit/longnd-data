package com.structis.vip.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.CollaborateurFormationModel;
import com.structis.vip.shared.model.CollaborateurModel;
import com.structis.vip.shared.model.KeyValueModel;

public interface ClientCollaborateurServiceAsync {

    public static class Util {

        private static ClientCollaborateurServiceAsync instance = GWT.create(ClientCollaborateurService.class);

        public static ClientCollaborateurServiceAsync getInstance() {
            return instance;
        }
    }

    void getAllCollaborateurs(AsyncCallback<List<CollaborateurModel>> callback);

    void getAllCollaborateursByEntiteId(String entiteId, Boolean sortie, AsyncCallback<List<CollaborateurModel>> callback);

    void getAllCollaborateursByEntiteId(String entiteId, Boolean sortie, String sortedField, Integer sortDir, Integer start, Integer pageSize,
            AsyncCallback<List<CollaborateurModel>> callback);

    void getAllDelegantsByEntiteId(String entiteId, AsyncCallback<List<CollaborateurModel>> callback);

    void getAllDelegatairesByEntiteId(String entiteId, AsyncCallback<List<CollaborateurModel>> callback);

    void update(CollaborateurModel model, AsyncCallback<CollaborateurModel> callback);

    void findByName(String name, String entityId, Boolean sortie, AsyncCallback<List<CollaborateurModel>> callback);

    void findByName(String name, String entityId, Boolean sortie, String sortedField, Integer sortDir, Integer start, Integer pageSize,
            AsyncCallback<List<CollaborateurModel>> callback);

    void delete(CollaborateurModel model, AsyncCallback<Boolean> callback);

    void insert(CollaborateurModel model, AsyncCallback<CollaborateurModel> callback);

    void findByCollaborateurId(Integer colId, AsyncCallback<List<CollaborateurFormationModel>> callback);

    void updateAndFormation(CollaborateurModel model, AsyncCallback<CollaborateurModel> callback);

    void updatePerimetreDelegant(Integer id, String perId, AsyncCallback<CollaborateurModel> asyncCallback);

    void updatePerimetreDelegataire(Integer id, String perId, AsyncCallback<CollaborateurModel> asyncCallback);

	void getAllDelegantsByPerimeter(String perId, String entiteId, Boolean recusion, AsyncCallback<List<CollaborateurModel>> asyncCallback);

    void getAllDelegatairesByPerimeter(String perId, String entiteId,
			Boolean level, AsyncCallback<List<CollaborateurModel>> asyncCallback);

    void findByFormationId(Integer forId, AsyncCallback<List<CollaborateurFormationModel>> callback);

    void getAllCollaborateursByEntiteIdPaging(PagingLoadConfig loadConfig, String entId, AsyncCallback<PagingLoadResult<CollaborateurModel>> callback);

    void getDelegantsByNatureAndPerimetre(String perId, String ptyId, String entId, Integer natureId,
            AsyncCallback<List<CollaborateurModel>> asyncCallback);

    void getAllControleursByEntiteId(String entiteIdIsByefe, AsyncCallback<List<KeyValueModel>> asyncCallback);

    void findById(Integer colId, AsyncCallback<CollaborateurModel> callback);

    void getAndUpdate(CollaborateurModel model, AsyncCallback<CollaborateurModel> asyncCallback);

    void findByPerimetre(String perId, AsyncCallback<List<CollaborateurModel>> asyncCallback);

}
