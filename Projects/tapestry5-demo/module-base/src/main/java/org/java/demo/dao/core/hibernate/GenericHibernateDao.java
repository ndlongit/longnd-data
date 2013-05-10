package org.java.demo.dao.core.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.java.demo.model.core.BasicEntity;
import org.java.demo.util.HibernateUtil;

public class GenericHibernateDao<T extends BasicEntity<?>, ID extends Serializable> {

    private Class<T> clazz;

    @SuppressWarnings("unchecked")
    public GenericHibernateDao() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Session getSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public String getPersistentClassName() {
        return getClazz().getName();
    }

    // @Override
    public void save(T entity) {
        getSession().save(entity);
    }

    public void update(T entity) {
        getSession().update(entity);
    }

    public void save(T... entities) {
        for (T t : entities) {
            save(t);
        }
    }

    // @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    public void delete(ID id) throws UnsupportedOperationException {
        delete(get(id));
    }

    public void delete(ID... ids) throws UnsupportedOperationException {

        // TODO Optimize later
        for (ID id : ids) {
            delete(id);
        }
    }

    public void delete(T... entities) throws UnsupportedOperationException {
        for (T t : entities) {
            delete(t);
        }
    }

    public T get(ID id) {
        return findById(id);
    }

    public List<T> get(ID... ids) {

        // TODO Optimize later
        List<T> results = new ArrayList<T>();

        for (ID id : ids) {
            results.add(get(id));
        }
        return results;
    }

    public T findById(ID id) {
        return this.findById(id, false);
    }

    @SuppressWarnings("unchecked")
    public T findById(ID id, boolean lock) {
        T entity;
        if (lock)
            entity = (T) getSession().get(getClazz(), id, LockMode.UPGRADE);
        else
            entity = (T) getSession().get(getClazz(), id);

        return entity;
    }

    @SuppressWarnings("rawtypes")
    public List findAll() {
        String className = null;
        try {
            className = getPersistentClassName();
            String queryString = "from " + className;
            Query queryObject = getSession().createQuery(queryString);
            List list = queryObject.list();
            return list;
        } catch (RuntimeException re) {
            re.getLocalizedMessage();
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String... excludeProperty) {
        Criteria crit = getSession().createCriteria(getClazz());
        Example example = Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }

    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion) {
        Criteria crit = getSession().createCriteria(getClazz());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }

    // @Override
    public void beginTransaction() {
        getSession().beginTransaction();
    }

    // @Override
    public void commitTransaction() {
        getSession().getTransaction().commit();
    }

    // @Override
    public void rollbackTransaction() {
        getSession().getTransaction().rollback();
    }
}
