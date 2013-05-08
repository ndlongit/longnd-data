package com.structis.fichesst.client.service;
import java.util.List;

import com.structis.fichesst.shared.dto.FicheTransfertppDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
public interface ClientFicheTransfertppServiceAsync {
	
	public static class Util {

		private static ClientFicheTransfertppServiceAsync instance = GWT.create(ClientFicheTransfertppService.class);

		public static ClientFicheTransfertppServiceAsync getInstance() {
			return instance;
		}
	}
	void findAll(AsyncCallback<List<FicheTransfertppDto>> callback);

	void findByChantierId(Integer chantierId, AsyncCallback<List<FicheTransfertppDto>> callback);
	
}
