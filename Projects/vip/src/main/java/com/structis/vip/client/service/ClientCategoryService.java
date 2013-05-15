package com.structis.vip.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.structis.vip.shared.exception.LanguageException;
import com.structis.vip.shared.model.CategoryModel;

@RemoteServiceRelativePath("springGwtServices/clientCategoryService")
public interface ClientCategoryService extends RemoteService {
	List<CategoryModel> getCategories();
	CategoryModel findById(Integer id);
	
	Boolean delete(CategoryModel model) throws LanguageException;	
	CategoryModel insert(CategoryModel model);
	CategoryModel update(CategoryModel model);
}
