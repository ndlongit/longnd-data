package org.java.demo.dao.core.jpa;

import static org.java.demo.constant.AppConstants.METHOD_BEGIN;
import static org.java.demo.constant.AppConstants.METHOD_END;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.java.demo.constant.AppConstants;
import org.java.demo.model.core.BasicEntity;
import org.java.demo.model.core.NumericIdEntity;
import org.java.demo.model.core.Orderable;
import org.java.demo.model.core.Timestampable;

public abstract class GenericJpaDao<T extends BasicEntity<?>, ID extends Serializable> {

    protected Logger logger = Logger.getLogger(this.getClass());

    protected EntityManager entityManager;

    private final Class<T> clazz;

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

    public void save(final T entity) throws javax.validation.ValidationException, javax.persistence.PersistenceException,
            org.hibernate.exception.ConstraintViolationException, Exception {

        String method = "save()";

        // This object is gotten dynamically, so please do not create a class field for it.
        Level dynamicLogLevel = AppConstants.getDynaLogLevel();
        logger.log(dynamicLogLevel, startMethod(method));
        if (entity instanceof NumericIdEntity) { /* Auto-increment ID, so clear ID before saving */
            entity.setId(null);
        }
        this.entityManager.persist(entity);
        logger.log(dynamicLogLevel, endMethod(method));
    }

    public T update(T entity) throws javax.validation.ValidationException, javax.persistence.PersistenceException,
            org.hibernate.exception.ConstraintViolationException, Exception {

        String method = "save()";

        // This object is gotten dynamically, so please do not create a class field for it.
        Level dynamicLogLevel = AppConstants.getDynaLogLevel();
        logger.log(dynamicLogLevel, startMethod(method));
        T result = null;
        if (entity instanceof Timestampable) {
            ((Timestampable) entity).setModifiedDate(new Date());
        }
        result = this.entityManager.merge(entity);
        logger.log(AppConstants.getDynaLogLevel(), "update()" + METHOD_END);

        return result;
    }

    public void delete(T object) throws UnsupportedOperationException, IllegalArgumentException, javax.persistence.TransactionRequiredException {

        String method = "delete()";

        // This object is gotten dynamically, so please do not create a class field for it.
        Level dynamicLogLevel = AppConstants.getDynaLogLevel();
        logger.log(dynamicLogLevel, startMethod(method));
        this.entityManager.remove(object);
        logger.log(dynamicLogLevel, endMethod(method));
    }

    public T find(ID id) {

        String method = "save()";

        // This object is gotten dynamically, so please do not create a class field for it.
        Level dynamicLogLevel = AppConstants.getDynaLogLevel();
        logger.log(dynamicLogLevel, startMethod(method));
        T result = this.entityManager.find(this.clazz, id);
        logger.log(dynamicLogLevel, endMethod(method));
        return result;
    }

    public List<T> findByIds(List<T> ids) {

        String method = "findByIds()";

        // This object is gotten dynamically, so please do not create a class field for it.
        Level dynamicLogLevel = AppConstants.getDynaLogLevel();
        logger.log(dynamicLogLevel, startMethod(method));
        List<T> results = findByProperty(BasicEntity.PROP_ID, ids);
        logger.log(dynamicLogLevel, endMethod(method));
        return results;
    }

    @SuppressWarnings({ "unchecked" })
    public List<T> findAll() {

        String method = "findAll()";

        // This object is gotten dynamically, so please do not create a class field for it.
        Level dynamicLogLevel = AppConstants.getDynaLogLevel();
        logger.log(dynamicLogLevel, startMethod(method));
        String queryString = getFrom();
        queryString += buildOrderByClause();
        Query queryObject = this.entityManager.createQuery(queryString);
        List<T> results = queryObject.getResultList();
        logger.log(dynamicLogLevel, endMethod(method));
        return results;
    }

    @SuppressWarnings({ "unchecked" })
    public List<ID> findAllIds() {

        String method = "findAllIds()";

        // This object is gotten dynamically, so please do not create a class field for it.
        Level dynamicLogLevel = AppConstants.getDynaLogLevel();
        logger.log(dynamicLogLevel, startMethod(method));
        String queryString = "SELECT " + BasicEntity.PROP_ID + " " + getFrom();
        queryString += buildOrderByClause();
        Query queryObject = this.entityManager.createQuery(queryString);
        List<ID> results = queryObject.getResultList();
        logger.log(dynamicLogLevel, endMethod(method));
        return results;
    }

    @SuppressWarnings("unchecked")
    public T findUniqueByProperty(String propertyName, Object propertyValue) throws NoResultException, NonUniqueResultException {

        String method = "findUniqueByProperty()";

        // This object is gotten dynamically, so please do not create a class field for it.
        Level dynamicLogLevel = AppConstants.getDynaLogLevel();
        logger.log(dynamicLogLevel, startMethod(method));

        T result = null;
        if (propertyValue != null) {
            String queryString = getFrom() + " WHERE " + propertyName + " = ?1";
            Query queryObject = this.entityManager.createQuery(queryString);
            queryObject.setParameter(1, propertyValue);

            result = (T) queryObject.getSingleResult();
        }

        logger.log(dynamicLogLevel, endMethod(method));
        return result;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findByProperty(String propertyName, List<?> propertyValues) {

        String method = "findByProperty()";

        // This object is gotten dynamically, so please do not create a class field for it.
        Level dynamicLogLevel = AppConstants.getDynaLogLevel();
        logger.log(dynamicLogLevel, startMethod(method));
        String queryString = getFrom() + " WHERE " + propertyName + " IN (?1)";
        queryString += buildOrderByClause();
        Query queryObject = this.entityManager.createQuery(queryString);
        queryObject.setParameter(1, propertyValues);
        List results = queryObject.getResultList();
        logger.log(dynamicLogLevel, endMethod(method));
        return results;
    }

    protected String buildOrderByClause() {
        try {
            Object entity = Class.forName(getClazz().getName()).newInstance();
            if (entity instanceof Orderable) {
                return ((Orderable) entity).getOrderByClause();
            }
            
            return "";
        }

        catch (Exception e) {
            return "";
        }
    }

    protected String getFrom() {
        return "FROM " + getClazz().getName();
    }

    private static String startMethod(String method) {
        return method + METHOD_BEGIN;
    }

    private static String endMethod(String method) {
        return method + METHOD_END;
    }
}
