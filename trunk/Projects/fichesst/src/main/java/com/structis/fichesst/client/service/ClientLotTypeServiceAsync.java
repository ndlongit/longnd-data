package com.structis.fichesst.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.fichesst.shared.dto.LotTypeDto;

public interface ClientLotTypeServiceAsync {
	public static class Util {
		private static ClientLotTypeServiceAsync instance = GWT.create(ClientLotTypeService.class);

		public static ClientLotTypeServiceAsync getInstance() {
			return instance;
		}
	}

	void findAll(AsyncCallback<List<LotTypeDto>> callback);
}
