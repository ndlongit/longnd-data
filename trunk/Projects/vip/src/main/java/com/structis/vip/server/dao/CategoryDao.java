package com.structis.vip.server.dao;

import com.structis.vip.server.bean.domain.Category;
import com.structis.vip.server.dao.support.GenericDao;

public interface CategoryDao extends GenericDao<Category, Integer> {

	Category insert(Category doc);

	Category update(Category doc);
	void delete(Category doc);

}
