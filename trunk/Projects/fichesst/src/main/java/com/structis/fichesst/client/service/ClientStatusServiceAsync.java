package com.structis.fichesst.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.fichesst.shared.dto.SimpleDto;

public interface ClientStatusServiceAsync {
	public static class Util {

		private static ClientStatusServiceAsync instance = GWT.create(ClientStatusService.class);

		public static ClientStatusServiceAsync getInstance() {
			return instance;
		}
	}

	void findAll(AsyncCallback<List<SimpleDto>> callback);
}
