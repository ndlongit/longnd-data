package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.DelegationDocumentModel;

public interface ClientDelegationDocumentServiceAsync {
	public static class Util {
		private static ClientDelegationDocumentServiceAsync instance = GWT
				.create(ClientDelegationDocumentService.class);

		public static ClientDelegationDocumentServiceAsync getInstance() {
			return instance;
		}
	}

	void getDelegationDocuments(Integer delegationId, AsyncCallback<List<DelegationDocumentModel>> callback);

	void insert(DelegationDocumentModel delegationDocument, AsyncCallback<DelegationDocumentModel> callback);

	void delete(DelegationDocumentModel delegationModel, AsyncCallback<Boolean> callback);
}