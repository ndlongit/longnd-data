package org.java.demo.service.core;

import static org.java.demo.constant.AppConstants.METHOD_BEGIN;
import static org.java.demo.constant.AppConstants.METHOD_END;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.java.demo.constant.AppConstants;
import org.java.demo.dao.core.BasicDao;
import org.java.demo.exception.DataConstraintException;
import org.java.demo.model.core.BasicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class AbstractService<T extends BasicEntity<?>, ID extends Serializable, DAO extends BasicDao> implements BasicService<T, ID> {

    protected Logger logger = Logger.getLogger(this.getClass());

    protected DAO dao;

    public DAO getDao() {
        return dao;
    }

    // Do not use @Autowired on field directly, otherwise error will appear
    @Autowired
    public void setDao(DAO dao) {
        this.dao = dao;
    }

    @Override
    public T find(ID id) {
        String method = "find()";

        // This object is gotten dynamically, so please do not create a class field for it.
        Level dynamicLogLevel = AppConstants.getDynaLogLevel();

        logger.log(dynamicLogLevel, startMethod(method));
        T result = (T) dao.find(id);
        logger.log(dynamicLogLevel, endMethod(method));
        return result;
    }

    @Override
    public List<T> findByIds(List<T> ids) {
        String method = "findByIds()";

        // This object is gotten dynamically, so please do not create a class field for it.
        Level dynamicLogLevel = AppConstants.getDynaLogLevel();

        logger.log(dynamicLogLevel, startMethod(method));
        List results = dao.findByIds(ids);
        logger.log(dynamicLogLevel, endMethod(method));
        return results;
    }

    @Override
    public List<T> findAll() {
        String method = "findAll()";

        // This object is gotten dynamically, so please do not create a class field for it.
        Level dynamicLogLevel = AppConstants.getDynaLogLevel();

        logger.log(dynamicLogLevel, startMethod(method));
        List<T> results = dao.findAll();
        logger.log(dynamicLogLevel, endMethod(method));
        return results;
    }

    @Override
    public List<ID> findAllIds() {
        String method = "findAllIds()";

        // This object is gotten dynamically, so please do not create a class field for it.
        Level dynamicLogLevel = AppConstants.getDynaLogLevel();

        logger.log(dynamicLogLevel, startMethod(method));
        List results = dao.findAllIds();
        logger.log(dynamicLogLevel, endMethod(method));
        return results;
    }

    @Override
    public void delete(ID id) {
        String method = "delete(ID id)";

        // This object is gotten dynamically, so please do not create a class field for it.
        Level dynamicLogLevel = AppConstants.getDynaLogLevel();

        logger.log(dynamicLogLevel, startMethod(method));
        T entity = (T) dao.find(id);
        delete(entity);
        logger.log(dynamicLogLevel, endMethod(method));
    }

    @Override
    public void delete(T entity) {
        String method = "delete(T entity)";

        // This object is gotten dynamically, so please do not create a class field for it.
        Level dynamicLogLevel = AppConstants.getDynaLogLevel();

        logger.log(dynamicLogLevel, startMethod(method));
        dao.delete(entity);
        logger.log(dynamicLogLevel, endMethod(method));
    }

    @Override
    public T save(T entity) throws DataConstraintException, Exception {
        String method = "save()";

        // This object is gotten dynamically, so please do not create a class field for it.
        Level dynamicLogLevel = AppConstants.getDynaLogLevel();

        logger.log(dynamicLogLevel, startMethod(method));
        try {
            dao.save(entity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        logger.log(dynamicLogLevel, endMethod(method));
        return entity;
    }

    @Override
    public T update(T entity) throws DataConstraintException, Exception {
        String method = "update()";

        // This object is gotten dynamically, so please do not create a class field for it.
        Level dynamicLogLevel = AppConstants.getDynaLogLevel();

        logger.log(dynamicLogLevel, startMethod(method));
        T result = (T) dao.update(entity);
        logger.log(dynamicLogLevel, endMethod(method));
        return result;
    }

    @Override
    public List<T> findByProperty(String propertyName, List propertyValues) {
        String method = "findByProperty()";

        // This object is gotten dynamically, so please do not create a class field for it.
        Level dynamicLogLevel = AppConstants.getDynaLogLevel();

        logger.log(dynamicLogLevel, startMethod(method));
        List results = null;
        try {
            results = dao.findByProperty(propertyName, propertyValues);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        logger.log(dynamicLogLevel, endMethod(method));
        return results;
    }

    @Override
    public T findUniqueByProperty(String propertyName, Object propertyValue) {
        String method = "findUniqueByProperty()";

        // This object is gotten dynamically, so please do not create a class field for it.
        Level dynamicLogLevel = AppConstants.getDynaLogLevel();

        logger.log(dynamicLogLevel, startMethod(method));
        T result = null;
        try {
            result = (T) dao.findUniqueByProperty(propertyName, propertyValue);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        logger.log(dynamicLogLevel, endMethod(method));
        return result;
    }

    private static String startMethod(String method) {
        return method + METHOD_BEGIN;
    }

    private static String endMethod(String method) {
        return method + METHOD_END;
    }
}
