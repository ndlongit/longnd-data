package com.structis.fichesst.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.fichesst.shared.dto.GestionDto;

public interface ClientGestionServiceAsync {

	public static class Util {

		private static ClientGestionServiceAsync instance = GWT.create(ClientGestionService.class);

		public static ClientGestionServiceAsync getInstance() {
			return instance;
		}
	}

	void findGestionList(Integer ficheStId, AsyncCallback<List<GestionDto>> callback);
}
