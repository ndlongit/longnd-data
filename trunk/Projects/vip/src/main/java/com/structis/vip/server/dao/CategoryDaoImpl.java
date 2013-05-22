package com.structis.vip.server.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.structis.vip.server.bean.domain.Category;
import com.structis.vip.server.dao.hibernate.HibernateGenericDao;
import com.structis.vip.server.util.DataCopier;

@Repository("categoryDao")
public class CategoryDaoImpl extends HibernateGenericDao<Category, Integer> implements CategoryDao {

    public CategoryDaoImpl() {
        super(Category.class);
    }

    @Override
    @Transactional
    public Category insert(Category doc) {
        this.save(doc);
        return doc;
    }

    @Override
    @Transactional
    public Category update(Category doc) {
        EntityManager em = this.getEntityManager();
        try {
            Category jpa = this.get(doc);
            if (jpa != null && jpa.getId() != null) {
                DataCopier.copyNotIdFields(doc, jpa);
                em.merge(jpa);
                return jpa;
            }
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
        return null;
    }

}
