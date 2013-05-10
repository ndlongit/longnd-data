package org.java.demo.dao.core.jpa;

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

import org.hibernate.exception.ConstraintViolationException;
import org.java.demo.exception.DataConstraintException;
import org.java.demo.model.core.BasicEntity;
import org.java.demo.model.core.NumericIdEntity;
import org.java.demo.model.core.Orderable;
import org.java.demo.model.core.Timestampable;
import org.java.demo.util.AppUtil;

public class GenericJpaDao<T extends BasicEntity<?>, ID extends Serializable> {

    protected EntityManager entityManager;

    private Class<T> clazz;

    @SuppressWarnings(value = "unchecked")
    public GenericJpaDao() {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public GenericJpaDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @PersistenceContext
    public final void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void save(final T entity) throws DataConstraintException, Exception {
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
    }

    public T update(T entity) throws DataConstraintException, Exception {
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

        return result;
    }

    public void delete(final T object) throws UnsupportedOperationException {
        this.entityManager.remove(object);
    }

    @SuppressWarnings("cast")
    public T find(ID id) {
        T result = (T) this.entityManager.find(this.clazz, id);
        return result;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<T> findAll() {
        String className = null;
        try {
            className = this.clazz.getName();
            String queryString = "FROM " + className;
            queryString += buildOrderByClause();
            Query queryObject = this.entityManager.createQuery(queryString);
            List list = queryObject.getResultList();
            return list;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    @SuppressWarnings("unchecked")
    public T findUniqueByProperty(String propertyName, Object propertyValue) throws NoResultException, NonUniqueResultException {
        if (propertyValue == null) {
            return null;
        }

        String value = "value";
        String queryString = "FROM " + getClazz().getName() + " WHERE " + propertyName + " = :" + value;
        Query queryObject = this.entityManager.createQuery(queryString);
        queryObject.setParameter(value, propertyValue);
        T result = null;
        result = (T) queryObject.getSingleResult();
        return result;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findByProperty(String propertyName, List propertyValues) {
        if (AppUtil.isNullOrEmpty(propertyValues)) {
            return new ArrayList<T>();
        }

        String values = "propertyValues";
        String queryString = "FROM " + getClazz().getName() + " WHERE " + propertyName + " IN (:" + values + ")";
        queryString += buildOrderByClause();
        Query queryObject = this.entityManager.createQuery(queryString);
        queryObject.setParameter(values, propertyValues);
        List list = queryObject.getResultList();
        return list;
    }

    @SuppressWarnings("rawtypes")
    protected String buildOrderByClause() {
        try {
            Object object = Class.forName(this.clazz.getName()).newInstance();
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
