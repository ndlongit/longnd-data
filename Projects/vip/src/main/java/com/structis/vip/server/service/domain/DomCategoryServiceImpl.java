package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.Category;
import com.structis.vip.server.dao.CategoryDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domCategoryService")
public class DomCategoryServiceImpl extends GenericEntityServiceImpl<Category, Integer> implements DomCategoryService {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = Logger.getLogger(DomCategoryServiceImpl.class);

    @Autowired
    @Qualifier("categoryDao")
    private CategoryDao categoryDao;

    @Override
    public GenericDao<Category, Integer> getDao() {
        return this.categoryDao;
    }

    @Override
    public List<Category> getCategories() {
        return this.find();
    }

    @Override
    public Category findById(Integer catId) {
        Category l = this.getByPrimaryKey(catId);
        return l;
    }

    @Override
    public Category insert(Category doc) {
        return this.categoryDao.insert(doc);
    }

    @Override
    public Category update(Category doc) {
        return this.categoryDao.update(doc);
    }

    @Override
    public Category getNew() {
        return new Category();
    }

    @Override
    public Category getNewWithDefaults() {
        return new Category();
    }

}
