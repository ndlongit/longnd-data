package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.DelegationNatureModel;

public interface ClientDelegationNatureServiceAsync {
	public static class Util {
		private static ClientDelegationNatureServiceAsync instance = GWT.create(ClientDelegationNatureService.class);

		public static ClientDelegationNatureServiceAsync getInstance() {
			return instance;
		}
	}
	
	void findNatureByEntite(String entiteId, AsyncCallback<List<DelegationNatureModel>> callback);
	void findNatureByEntiteAndPerimetreType(String entiteId, String ptyId,Boolean isSub, AsyncCallback<List<DelegationNatureModel>> callback);

	void delete(DelegationNatureModel model, AsyncCallback<Boolean> callback);

	void insert(DelegationNatureModel model,
			AsyncCallback<DelegationNatureModel> callback);

	void update(DelegationNatureModel model,
			AsyncCallback<DelegationNatureModel> callback);

	void findNatureForNew(String perId, String entId,
			AsyncCallback<List<DelegationNatureModel>> callback);
	void findNatureForNew(String perId, String entId, String ptyId,Boolean isSub,
			AsyncCallback<List<DelegationNatureModel>> asyncCallback);

}
