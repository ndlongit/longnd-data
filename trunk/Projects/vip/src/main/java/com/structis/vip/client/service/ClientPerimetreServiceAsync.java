package com.structis.vip.client.service;

import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.PerimetreModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.PerimetreTypeModel;
import com.structis.vip.shared.model.UserRoleModel;

public interface ClientPerimetreServiceAsync {

    public static class Util {

        private static ClientPerimetreServiceAsync instance = GWT.create(ClientPerimetreService.class);

        public static ClientPerimetreServiceAsync getInstance() {
            return instance;
        }
    }

    void findPerimetreByEntite(String entite, AsyncCallback<List<PerimetreModel>> callback);

    void findFirstLevelPerimetreByEntite(String emId, AsyncCallback<List<PerimetreModel>> callback);

    void findFirstLevelPerimetreByUserRoles(String emId, boolean isAdmin, List<UserRoleModel> userRoles, AsyncCallback<List<PerimetreModel>> callback);

    void getTreeModel(String entiteId, String perimetreId, AsyncCallback<List<PerimetreModel>> callback);

    void insert(PerimetreModel perimetreModel, AsyncCallback<Boolean> callback);

    void insert(String perimetreParentId, PerimetreModel perimetreModel, AsyncCallback<String> callback);

    void update(PerimetreModel perimetreModel, AsyncCallback<Boolean> callback);

    void findById(String id, AsyncCallback<PerimetreModel> callback);

    void deleteById(String id, AsyncCallback<Boolean> callback);

    void findFirstLevelPerimetre(AsyncCallback<List<PerimetreModel>> callback);

    void sync(String entiteId, String parentId, List<PerimetreTypeModel> types, AsyncCallback<Map<String, List<PerimetreModel>>> callback);

    void getTreeModelByParent(String entiteId, List<UserRoleModel> userRoles, PerimetreTreeModel perimetreTree,
            AsyncCallback<List<PerimetreTreeModel>> callback);

    void getTreeModelById(String perimetreId, List<UserRoleModel> userRoles, AsyncCallback<List<PerimetreTreeModel>> callback);

    void hasReferenceInDelegationOrControlOrPerimetre(String perId, AsyncCallback<Integer> asyncCallback);

    void hasReferenceInUserCollaborateur(String perId, AsyncCallback<Integer> asyncCallback);

    void clearReferenceToPerimetreInUserCollaborateur(String perId, AsyncCallback<Void> asyncCallback);

    void findByPerimetreParent(String perId, AsyncCallback<List<PerimetreModel>> asyncCallback);
}
