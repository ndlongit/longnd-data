package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.FormationModel;

public interface ClientFormationServiceAsync {
	
	public static class Util {
		private static ClientFormationServiceAsync instance = GWT.create(ClientFormationService.class);

		public static ClientFormationServiceAsync getInstance() {
			return instance;
		}		
	}

	void delete(FormationModel model, AsyncCallback<Boolean> callback);

	void findByEntite(String entiteId,
			AsyncCallback<List<FormationModel>> callback);

	void insert(FormationModel model,
			AsyncCallback<FormationModel> callback);

	void update(FormationModel model,
			AsyncCallback<FormationModel> callback);

	void findAll(AsyncCallback<List<FormationModel>> callback);

}
