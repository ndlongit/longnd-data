package com.structis.fichesst.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClientPropertiesServiceAsync {

	public static class Util {

		private static ClientPropertiesServiceAsync instance = GWT.create(ClientPropertiesService.class);

		public static ClientPropertiesServiceAsync getInstance() {
			return instance;
		}
	}

	void getVersionInfo(AsyncCallback<String> callback);
}
