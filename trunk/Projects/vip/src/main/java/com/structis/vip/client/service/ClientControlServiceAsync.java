package com.structis.vip.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.ControlFilter;
import com.structis.vip.shared.model.ControlModel;

public interface ClientControlServiceAsync {
	
	public static class Util {
		private static ClientControlServiceAsync instance = GWT.create(ClientControlService.class);

		public static ClientControlServiceAsync getInstance() {
			return instance;
		}		
	}

	void delete(ControlModel model, AsyncCallback<Boolean> callback);

	void insert(ControlModel model,
			AsyncCallback<ControlModel> callback);

	void update(ControlModel model,
			AsyncCallback<ControlModel> callback);

	void findAll(AsyncCallback<List<ControlModel>> callback);

	void getControlsWithPaging(ControlFilter newFilter,
			AsyncCallback<PagingLoadResult<ControlModel>> asyncCallback);
	void getControls(ControlFilter config, AsyncCallback<List<ControlModel>> asyncCallback);

	void findByPerimetre(String perId,
			AsyncCallback<List<ControlModel>> asyncCallback);

}
