package org.java.demo.dao.core.jpa;

import static org.java.demo.constant.AppConstants.METHOD_BEGIN;
import static org.java.demo.constant.AppConstants.METHOD_END;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.java.demo.constant.AppConstants;
import org.java.demo.exception.DataConstraintException;
import org.java.demo.model.core.BasicEntity;
import org.java.demo.model.core.NumericIdEntity;
import org.java.demo.model.core.Orderable;
import org.java.demo.model.core.Timestampable;
import org.java.demo.util.AppUtil;

public abstract class GenericJpaDao<T extends BasicEntity<?>, ID extends Serializable> {

    protected Logger logger = Logger.getLogger(this.getClass());

    protected EntityManager entityManager;

    private Class<T> clazz;

    @SuppressWarnings(value = "unchecked")
    protected GenericJpaDao() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @PersistenceContext
    public final void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void save(final T entity) throws DataConstraintException, Exception {
        logger.log(AppConstants.getDynaLogLevel(), "save()" + METHOD_BEGIN);
        try {
            if (entity instanceof NumericIdEntity) {
                entity.setId(null);
            }

            this.entityManager.persist(entity);
        } catch (ConstraintViolationException e) {
            throw new DataConstraintException(e);
        } catch (Exception e) {
            throw e;
        }
        logger.log(AppConstants.getDynaLogLevel(), "save()" + METHOD_END);
    }

    public T update(T entity) throws DataConstraintException, Exception {
        logger.log(AppConstants.getDynaLogLevel(), "update()" + METHOD_BEGIN);
        T result = null;
        try {
            if (entity instanceof Timestampable) {
                ((Timestampable) entity).setModifiedDate(new Date());
            }
            result = this.entityManager.merge(entity);
        } catch (ConstraintViolationException e) {
            throw new DataConstraintException(e);
        } catch (Exception e) {
            throw e;
        }
        logger.log(AppConstants.getDynaLogLevel(), "update()" + METHOD_END);

        return result;
    }

    public void delete(T object) throws UnsupportedOperationException {
        logger.log(AppConstants.getDynaLogLevel(), "delete()" + METHOD_BEGIN);
        this.entityManager.remove(object);
        logger.log(AppConstants.getDynaLogLevel(), "delete()" + METHOD_END);
    }

    @SuppressWarnings("cast")
    public T find(ID id) {
        logger.log(AppConstants.getDynaLogLevel(), "find()" + METHOD_BEGIN);
        T result = (T) this.entityManager.find(this.clazz, id);
        logger.log(AppConstants.getDynaLogLevel(), "find()" + METHOD_END);
        return result;
    }

    public List<T> findByIds(List<T> ids) {
        logger.log(AppConstants.getDynaLogLevel(), "findByIds()" + METHOD_BEGIN);
        List<T> results = findByProperty(BasicEntity.PROP_ID, ids);
        logger.log(AppConstants.getDynaLogLevel(), "findByIds()" + METHOD_END);
        return results;
    }

    @SuppressWarnings({ "unchecked" })
    public List<T> findAll() {
        logger.log(AppConstants.getDynaLogLevel(), "findAll()" + METHOD_BEGIN);
        try {
            String queryString = "FROM " + getClazz().getName();
            queryString += buildOrderByClause();
            Query queryObject = this.entityManager.createQuery(queryString);
            List<T> list = queryObject.getResultList();
            logger.log(AppConstants.getDynaLogLevel(), "findAll()" + METHOD_END);
            return list;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    @SuppressWarnings({ "unchecked" })
    public List<ID> findAllIds() {
        logger.log(AppConstants.getDynaLogLevel(), "findAllIds()" + METHOD_BEGIN);
        try {
            String queryString = "SELECT " + BasicEntity.PROP_ID + " FROM " + getClazz().getName();
            queryString += buildOrderByClause();
            Query queryObject = this.entityManager.createQuery(queryString);
            List<ID> list = queryObject.getResultList();
            logger.log(AppConstants.getDynaLogLevel(), "findAllIds()" + METHOD_END);
            return list;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public T findUniqueByProperty(String propertyName, Object propertyValue) throws NoResultException, NonUniqueResultException {

        logger.log(AppConstants.getDynaLogLevel(), "findUniqueByProperty()" + METHOD_BEGIN);
        if (propertyValue == null) {
            return null;
        }

        String value = "value";
        String queryString = "FROM " + getClazz().getName() + " WHERE " + propertyName + " = :" + value;
        Query queryObject = this.entityManager.createQuery(queryString);
        queryObject.setParameter(value, propertyValue);
        T result = null;
        result = (T) queryObject.getSingleResult();
        logger.log(AppConstants.getDynaLogLevel(), "findUniqueByProperty()" + METHOD_END);
        return result;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findByProperty(String propertyName, List<?> propertyValues) {
        logger.log(AppConstants.getDynaLogLevel(), "findByProperty()" + METHOD_BEGIN);
        if (AppUtil.isNullOrEmpty(propertyValues)) {
            return new ArrayList<T>();
        }

        String values = "propertyValues";
        String queryString = "FROM " + getClazz().getName() + " WHERE " + propertyName + " IN (:" + values + ")";
        queryString += buildOrderByClause();
        Query queryObject = this.entityManager.createQuery(queryString);
        queryObject.setParameter(values, propertyValues);
        List list = queryObject.getResultList();
        logger.log(AppConstants.getDynaLogLevel(), "findByProperty()" + METHOD_END);
        return list;
    }

    @SuppressWarnings("rawtypes")
    protected String buildOrderByClause() {
        try {
            Object object = Class.forName(getClazz().getName()).newInstance();
            if (object instanceof Orderable) {
                return " ORDER BY " + Orderable.PROP_ORDER;
            } else {
                if (object instanceof BasicEntity) {
                    return ((BasicEntity) object).getOrderByClause();
                } else {
                    return "";
                }
            }
        }

        catch (Exception e) {
            return "";
        }
    }
}
