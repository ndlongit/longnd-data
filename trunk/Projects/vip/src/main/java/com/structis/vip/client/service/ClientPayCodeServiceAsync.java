package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.PayCodeModel;

public interface ClientPayCodeServiceAsync {
	
	public static class Util {
		private static ClientPayCodeServiceAsync instance = GWT.create(ClientPayCodeService.class);

		public static ClientPayCodeServiceAsync getInstance() {
			return instance;
		}		
	}

	void findAll(AsyncCallback<List<PayCodeModel>> callback);

	void delete(PayCodeModel model, AsyncCallback<Boolean> callback);

	void findByCode(String code, AsyncCallback<PayCodeModel> callback);

	void insert(PayCodeModel model, AsyncCallback<PayCodeModel> callback);

	void update(PayCodeModel model, AsyncCallback<PayCodeModel> callback);

}
