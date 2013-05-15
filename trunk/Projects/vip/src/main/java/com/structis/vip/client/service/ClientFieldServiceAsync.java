package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.FieFieldModel;

public interface ClientFieldServiceAsync {

	public static class Util {
		private static ClientFieldServiceAsync instance = GWT
				.create(ClientFieldService.class);

		public static ClientFieldServiceAsync getInstance() {
			return instance;
		}		
	}
	
	void getFieldsByEntiteId(String entiteId, AsyncCallback<List<FieFieldModel>> callback);
	void insert(FieFieldModel fieFieldModel, AsyncCallback<FieFieldModel> callback);
	void getFieldsByGroupName(String entId, String groupName,
			AsyncCallback<List<FieFieldModel>> callback);

}
