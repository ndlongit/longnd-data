package com.structis.vip.server.service.domain;

import java.util.List;


import com.structis.vip.server.bean.domain.Category;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomCategoryService extends GenericEntityService<Category, Integer> {
	List<Category> getCategories();
	Category findById(Integer catId);
	Category insert(Category doc);
	Category update(Category doc);	
	void delete(Category doc);
}
