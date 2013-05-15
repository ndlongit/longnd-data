package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.ChantierTypeModel;
import com.structis.vip.shared.model.PerimetreTypeModel;

public interface ClientPerimetreTypeServiceAsync {
	public static class Util {

		private static ClientPerimetreTypeServiceAsync instance = GWT
				.create(ClientPerimetreTypeService.class);

		public static ClientPerimetreTypeServiceAsync getInstance() {
			return instance;
		}
	}
	void getPerimetreTypes(String entiteId, AsyncCallback<List<PerimetreTypeModel>> callback);
	void delete(PerimetreTypeModel model, AsyncCallback<Boolean> asyncCallback);
	void insert(PerimetreTypeModel model,
			AsyncCallback<PerimetreTypeModel> asyncCallback);
	void update(PerimetreTypeModel model,
			AsyncCallback<PerimetreTypeModel> asyncCallback);
}
