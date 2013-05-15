package com.structis.vip.client.service;

import java.util.List;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.model.DocumentModel;
import com.structis.vip.shared.model.UserModel;

public interface ClientDocumentServiceAsync {
	public static class Util {

		private static ClientDocumentServiceAsync instance = GWT.create(ClientDocumentService.class);

		public static ClientDocumentServiceAsync getInstance() {
			return instance;
		}
	}

	void findDocuments(AsyncCallback<List<DocumentModel>> callback);
	
	void findDocumentsByName(String name, AsyncCallback<List<DocumentModel>> callback);

	void findById(final Integer id, AsyncCallback<DocumentModel> callback);

	void insert(DocumentModel docModel, AsyncCallback<DocumentModel> callback);

	void update(DocumentModel docModel, AsyncCallback<DocumentModel> callback);
		
	
	void delete(DocumentModel docModel, AsyncCallback<Boolean> callback);
	
	void findDocumentsWithPaging(String name, PagingLoadConfig config, AsyncCallback<PagingLoadResult<DocumentModel>> callback);
		
}
