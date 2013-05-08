package com.structis.fichesst.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.fichesst.shared.dto.SimpleDto;

public interface ClientTypeServiceAsync {
	public static class Util {

		private static ClientTypeServiceAsync instance = GWT.create(ClientTypeService.class);

		public static ClientTypeServiceAsync getInstance() {
			return instance;
		}
	}

	void findAll(AsyncCallback<List<SimpleDto>> callback);
}
