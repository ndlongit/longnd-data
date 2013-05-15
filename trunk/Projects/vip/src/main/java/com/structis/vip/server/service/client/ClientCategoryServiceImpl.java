package com.structis.vip.server.service.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.structis.vip.client.service.ClientCategoryService;
import com.structis.vip.server.bean.domain.Category;
import com.structis.vip.server.core.DependencyInjectionRemoteServiceServlet;
import com.structis.vip.server.core.ManagerCallBack;
import com.structis.vip.server.mapper.ModelBeanMapperIfc;
import com.structis.vip.server.service.domain.DomCategoryService;
import com.structis.vip.shared.model.CategoryModel;

@Service("clientCategoryService")
public class ClientCategoryServiceImpl extends DependencyInjectionRemoteServiceServlet
		implements ClientCategoryService {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ClientCategoryServiceImpl.class);
	
	@Autowired
	private DomCategoryService domCategoryService; 
	
	@Autowired
	ModelBeanMapperIfc modelBeanMapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryModel> getCategories() {
		ManagerCallBack callBack = new ManagerCallBack() {
			public Object execute(Object... inputs) {
				return domCategoryService.find();
			}
		};
		return (List<CategoryModel>) callManager(callBack);
	}

	@Override
	public CategoryModel findById(Integer CategoryId) {
		Category lg = domCategoryService.getByPrimaryKey(CategoryId);
		return (CategoryModel) modelBeanMapper.map(lg);
	}

	@Override
	public Boolean delete(CategoryModel model) {	
		Category dm = (Category) modelBeanMapper.map(model);
		domCategoryService.delete(dm);
		return true;
	}

	@Override
	public CategoryModel insert(CategoryModel model) {
		Category doc = (Category) modelBeanMapper.map(model);
		doc = domCategoryService.insert(doc);
		return (CategoryModel) modelBeanMapper.map(doc);
	}

	@Override
	public CategoryModel update(CategoryModel model) {
		Category doc = (Category) modelBeanMapper.map(model);
		doc = domCategoryService.update(doc);
		return (CategoryModel) modelBeanMapper.map(doc);
	}
	
}
