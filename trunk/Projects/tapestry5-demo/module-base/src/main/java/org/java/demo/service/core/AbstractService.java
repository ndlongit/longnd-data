package org.java.demo.service.core;

import static org.java.demo.constant.AppConstants.METHOD_BEGIN;
import static org.java.demo.constant.AppConstants.METHOD_END;

import java.io.Serializable;
import java.util.List;

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
        logger.log(AppConstants.getTrackLevel(), "find()" + METHOD_BEGIN);
        T result = (T) dao.find(id);
        logger.log(AppConstants.getTrackLevel(), "find()" + METHOD_END);
        return result;
    }

    @Override
    public List<T> findByIds(List<T> ids) {
        logger.log(AppConstants.getTrackLevel(), "findByIds()" + METHOD_BEGIN);
        List results = dao.findByIds(ids);
        logger.log(AppConstants.getTrackLevel(), "findByIds()" + METHOD_END);
        return results;
    }

    @Override
    public List<T> findAll() {
        logger.log(AppConstants.getTrackLevel(), "findAll()" + METHOD_BEGIN);
        List<T> results = dao.findAll();
        logger.log(AppConstants.getTrackLevel(), "findAll()" + METHOD_END);
        return results;
    }

    @Override
    public List<ID> findAllIds() {
        logger.log(AppConstants.getTrackLevel(), "findAllIds()" + METHOD_BEGIN);
        List results = dao.findAllIds();
        logger.log(AppConstants.getTrackLevel(), "findAllIds()" + METHOD_END);
        return results;
    }

    @Override
    public void delete(ID id) {
        logger.log(AppConstants.getTrackLevel(), "delete(ID id)" + METHOD_BEGIN);
        T entity = (T) dao.find(id);
        delete(entity);
        logger.log(AppConstants.getTrackLevel(), "delete(ID id)" + METHOD_END);
    }

    @Override
    public void delete(T entity) {
        logger.log(AppConstants.getTrackLevel(), "delete(T entity)" + METHOD_BEGIN);
        dao.delete(entity);
        logger.log(AppConstants.getTrackLevel(), "delete(T entity)" + METHOD_END);
    }

    @Override
    public T save(T entity) throws DataConstraintException, Exception {
        logger.log(AppConstants.getTrackLevel(), "save()" + METHOD_BEGIN);
        try {
            dao.save(entity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        logger.log(AppConstants.getTrackLevel(), "save()" + METHOD_END);
        return entity;
    }

    @Override
    public T update(T entity) throws DataConstraintException, Exception {
        logger.log(AppConstants.getTrackLevel(), "update()" + METHOD_BEGIN);
        T result = (T) dao.update(entity);
        logger.log(AppConstants.getTrackLevel(), "update()" + METHOD_END);
        return result;
    }

    @Override
    public List<T> findByProperty(String propertyName, List propertyValues) {
        logger.log(AppConstants.getTrackLevel(), "findByProperty()" + METHOD_BEGIN);
        List results = dao.findByProperty(propertyName, propertyValues);
        logger.log(AppConstants.getTrackLevel(), "findByProperty()" + METHOD_END);
        return results;
    }

    @Override
    public T findUniqueByProperty(String propertyName, Object propertyValue) {
        logger.log(AppConstants.getTrackLevel(), "findUniqueByProperty()" + METHOD_BEGIN);
        T result = (T) dao.findUniqueByProperty(propertyName, propertyValue);
        logger.log(AppConstants.getTrackLevel(), "findUniqueByProperty()" + METHOD_END);
        return result;
    }
}
