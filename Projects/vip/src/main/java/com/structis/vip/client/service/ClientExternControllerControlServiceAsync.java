package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.ControlModel;
import com.structis.vip.shared.model.ExtControllerControlModel;
import com.structis.vip.shared.model.ExternControllerModel;

public interface ClientExternControllerControlServiceAsync {
	
	public static class Util {
		private static ClientExternControllerControlServiceAsync instance = GWT.create(ClientExternControllerControlService.class);

		public static ClientExternControllerControlServiceAsync getInstance() {
			return instance;
		}		
	}

	void delete(ExtControllerControlModel model, AsyncCallback<Boolean> callback);

	void insert(ExtControllerControlModel model,
			AsyncCallback<ExtControllerControlModel> callback);

	void update(ExtControllerControlModel model,
			AsyncCallback<ExtControllerControlModel> callback);

	void findAll(AsyncCallback<List<ExtControllerControlModel>> callback);
	void findByControl(Integer controlId, AsyncCallback<List<ExtControllerControlModel>> callback);

	void deleteByControl(Integer control,
			AsyncCallback<Boolean> asyncCallback);
	void insert(List<ExtControllerControlModel> eccs,
			AsyncCallback<Void> asyncCallback);
	
	void insert(Integer controlId, List<ExtControllerControlModel> eccs,
			AsyncCallback<Void> asyncCallback);
}
