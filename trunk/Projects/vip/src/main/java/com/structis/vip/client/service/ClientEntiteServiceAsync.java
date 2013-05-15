package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.PerimetreTreeModel;
import com.structis.vip.shared.model.UserModel;
import com.structis.vip.shared.model.UserRoleModel;

public interface ClientEntiteServiceAsync {
	public static class Util {
		private static ClientEntiteServiceAsync instance = GWT
				.create(ClientEntiteService.class);

		public static ClientEntiteServiceAsync getInstance() {
			return instance;
		}		
	}
	void getAllEntites(AsyncCallback<List<EntiteModel>> callback);
	void getEntiteByUser(UserModel user, AsyncCallback<EntiteModel> asyncCallback);
	void insert(EntiteModel entiteModel, AsyncCallback<Boolean> asyncCallback);
	void update(EntiteModel entiteModel, AsyncCallback<Boolean> asyncCallback);
	void findById(String id, AsyncCallback<EntiteModel> asyncCallback);
	void getTreeModelById(String id, List<UserRoleModel> userRoles, AsyncCallback<List<PerimetreTreeModel>> asyncCallback);
}
