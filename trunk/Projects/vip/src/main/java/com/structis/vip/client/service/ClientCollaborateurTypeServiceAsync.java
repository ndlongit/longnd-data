package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.CollaborateurTypeModel;

public interface ClientCollaborateurTypeServiceAsync {
	public static class Util {
		private static ClientCollaborateurTypeServiceAsync instance = GWT.create(ClientCollaborateurTypeService.class);

		public static ClientCollaborateurTypeServiceAsync getInstance() {
			return instance;
		}
	}

	void getAllCollaborateurType(AsyncCallback<List<CollaborateurTypeModel>> callback);
	
	void getCollaborateurTypeByEntite(String entiteId, AsyncCallback<List<CollaborateurTypeModel>> callback);

	void delete(CollaborateurTypeModel model,
			AsyncCallback<Boolean> asyncCallback);

	void insert(CollaborateurTypeModel model,
			AsyncCallback<CollaborateurTypeModel> asyncCallback);

	void update(CollaborateurTypeModel model,
			AsyncCallback<CollaborateurTypeModel> asyncCallback);

}