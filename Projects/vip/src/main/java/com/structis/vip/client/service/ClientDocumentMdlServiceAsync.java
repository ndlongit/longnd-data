package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.structis.vip.shared.model.DocumentMdlModel;
import com.structis.vip.shared.model.DomDelModel;
import com.structis.vip.shared.model.EntiteModel;
import com.structis.vip.shared.model.LanguageModel;

public interface ClientDocumentMdlServiceAsync {
	public static class Util {

		private static ClientDocumentMdlServiceAsync instance = GWT
				.create(ClientDocumentMdlService.class);

		public static ClientDocumentMdlServiceAsync getInstance() {
			return instance;
		}
	}
	void findById(Integer docId, AsyncCallback<DocumentMdlModel> callback);
	void getAllDocumentModels(AsyncCallback<List<DocumentMdlModel>> callback);
	void getAllDocumentModelsByEntite(EntiteModel en, AsyncCallback<List<DocumentMdlModel>> callback);
	void getDocumentModelsByLanguage(LanguageModel lm, AsyncCallback<List<DocumentMdlModel>> callback);
	void getDocumentModelsByLanguageAndType(LanguageModel lm, String type, AsyncCallback<List<DocumentMdlModel>> callback);
	void getDocumentsByDelegation(Integer delegationId,
			AsyncCallback<List<DomDelModel>> callback);
	void getDocumentsByDelegationAndLanguage(Integer delegationId, Integer languageId, AsyncCallback<List<DomDelModel>> callback);
	void getDocumentsByDelegationLanguageAndType(Integer delegationId, Integer languageId, String type, AsyncCallback<List<DomDelModel>> callback);
	void createNewDocument(DomDelModel document, AsyncCallback<Boolean> callback);
	void createNewDocument(List<DomDelModel> documents, AsyncCallback<Boolean> callback);
	void deleteDocument(DomDelModel document, AsyncCallback<Boolean> callback);
	void delete(DocumentMdlModel model, AsyncCallback<Boolean> asyncCallback);
	void insert(DocumentMdlModel model, AsyncCallback<DocumentMdlModel> callback);
	void update(DocumentMdlModel model, AsyncCallback<DocumentMdlModel> callback);

	void deleteFile(DocumentMdlModel model, boolean isDeleteFile, boolean isDeleteTempFile, AsyncCallback<Boolean> callback);
}
