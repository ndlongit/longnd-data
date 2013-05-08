package com.structis.fichesst.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.fichesst.shared.dto.SimpleDto;

public interface ClientRefTypeMchAvServiceAsync {

	public static class Util {

		private static ClientRefTypeMchAvServiceAsync instance = GWT.create(ClientRefTypeMchAvService.class);

		public static ClientRefTypeMchAvServiceAsync getInstance() {
			return instance;
		}
	}

	void findAll(AsyncCallback<List<SimpleDto>> callback);
}
