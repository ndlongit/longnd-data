package com.structis.fichesst.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.fichesst.shared.dto.SimpleDto;

public interface ClientRefModePaiementServiceAsync {
	public static class Util {

		private static ClientRefModePaiementServiceAsync instance = GWT.create(ClientRefModePaiementService.class);

		public static ClientRefModePaiementServiceAsync getInstance() {
			return instance;
		}
	}

	void findAll(AsyncCallback<List<SimpleDto>> callback);
}
