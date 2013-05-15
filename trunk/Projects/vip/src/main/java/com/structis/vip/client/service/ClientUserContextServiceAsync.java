package com.structis.vip.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.UserModel;

public interface ClientUserContextServiceAsync {

	public static class Util {

		private static ClientUserContextServiceAsync instance = GWT.create(ClientUserContextService.class);

		public static ClientUserContextServiceAsync getInstance() {
			return instance;
		}
	}
	
	void checkUserByKerberosSSO(AsyncCallback<UserModel> callback);

	void findUserOnDomainBC(final String login,
			AsyncCallback<UserModel> callback);

	void disconnectUser(UserModel userContext, AsyncCallback<Void> callback);

}
