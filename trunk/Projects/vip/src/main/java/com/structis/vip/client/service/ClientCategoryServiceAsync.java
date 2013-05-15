package com.structis.vip.client.service;

import java.util.List;
import com.structis.vip.shared.model.CategoryModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClientCategoryServiceAsync {
	public static class Util {

		private static ClientCategoryServiceAsync instance = GWT
				.create(ClientCategoryService.class);

		public static ClientCategoryServiceAsync getInstance() {
			return instance;
		}
	}
	void getCategories(AsyncCallback<List<CategoryModel>> callback);
	void findById(Integer CategoryId, AsyncCallback<CategoryModel> callback);
	void delete(CategoryModel model, AsyncCallback<Boolean> callback);
	void insert(CategoryModel model, AsyncCallback<CategoryModel> callback);
	void update(CategoryModel model, AsyncCallback<CategoryModel> callback);
}
