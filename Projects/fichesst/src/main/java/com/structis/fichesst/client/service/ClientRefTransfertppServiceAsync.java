package com.structis.fichesst.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.fichesst.shared.dto.RefTransfertPPDto;

public interface ClientRefTransfertppServiceAsync {

	public static class Util {

		private static ClientRefTransfertppServiceAsync instance = GWT.create(ClientRefTransfertppService.class);

		public static ClientRefTransfertppServiceAsync getInstance() {
			return instance;
		}
	}

	void findById(Integer id, AsyncCallback<RefTransfertPPDto> callback);

	void findAll(AsyncCallback<List<RefTransfertPPDto>> callback);
}
