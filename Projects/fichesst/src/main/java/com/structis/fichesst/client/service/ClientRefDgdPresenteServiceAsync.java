package com.structis.fichesst.client.service;

import java.util.List;

import com.structis.fichesst.shared.dto.SimpleDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClientRefDgdPresenteServiceAsync {

	public static class Util {

		private static ClientRefDgdPresenteServiceAsync instance = GWT.create(ClientRefDgdPresenteService.class);

		public static ClientRefDgdPresenteServiceAsync getInstance() {
			return instance;
		}
	}

	void findAll(AsyncCallback<List<SimpleDto>> callback);
}
