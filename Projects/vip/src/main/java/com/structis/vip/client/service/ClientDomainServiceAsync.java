package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.DomainModel;

public interface ClientDomainServiceAsync {
	public static class Util {

		private static ClientDomainServiceAsync instance = GWT.create(ClientDomainService.class);

		public static ClientDomainServiceAsync getInstance() {
			return instance;
		}
	}
	void getDomains(AsyncCallback<List<DomainModel>> callback);
}
