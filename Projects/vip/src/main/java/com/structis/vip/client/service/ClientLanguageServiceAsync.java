package com.structis.vip.client.service;

import java.util.List;
import com.structis.vip.shared.model.LanguageModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClientLanguageServiceAsync {
	public static class Util {

		private static ClientLanguageServiceAsync instance = GWT
				.create(ClientLanguageService.class);

		public static ClientLanguageServiceAsync getInstance() {
			return instance;
		}
	}
	void getLanguages(AsyncCallback<List<LanguageModel>> callback);
	void findById(Integer languageId, AsyncCallback<LanguageModel> callback);
	void delete(LanguageModel model, AsyncCallback<Boolean> callback);
	void insert(LanguageModel model, AsyncCallback<LanguageModel> callback);
	void update(LanguageModel model, AsyncCallback<LanguageModel> callback);
}
