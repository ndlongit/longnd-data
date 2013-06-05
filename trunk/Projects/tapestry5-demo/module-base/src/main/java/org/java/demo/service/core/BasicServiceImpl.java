package org.java.demo.service.core;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.java.demo.dao.core.BasicDao;
import org.java.demo.exception.DataConstraintException;
import org.java.demo.model.core.BasicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BasicServiceImpl<T extends BasicEntity<?>, ID extends Serializable, DAO extends BasicDao> implements BasicService<T, ID> {

    private static final Logger logger = Logger.getLogger(BasicServiceImpl.class);

    protected DAO dao;
    
    public DAO getDao() {
        return dao;
    }

    //Do not use @Autowired on field directly, otherwise error appear 
    @Autowired
    public void setDao(DAO dao) {
        this.dao = dao;
    }

    @Override
    public T find(ID id) {
        T result = (T) dao.find(id);
        return result;
    }

    @Override
    public List<T> findByIds(List<T> ids) {
        return dao.findByIds(ids);
    }

    @Override
    public List<T> findAll() {
        List<T> results = dao.findAll();
        return results;
    }

    @Override
    public List<ID> findAllIds() {
        return dao.findAllIds();
    }

    @Override
    public void delete(ID id) {
        T entity = (T) dao.find(id);
        delete(entity);
    }

    @Override
    public void delete(T entity) {
        dao.delete(entity);
    }

    @Override
    public T save(T entity) throws DataConstraintException, Exception {
        try {
            dao.save(entity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        return entity;
    }

    @Override
    public T update(T entity) throws DataConstraintException, Exception {
        return (T) dao.update(entity);
    }

    @Override
    public List<T> findByProperty(String propertyName, List propertyValues) {
        return dao.findByProperty(propertyName, propertyValues);
    }

    @Override
    public T findUniqueByProperty(String propertyName, Object propertyValue) {
        return (T) dao.findUniqueByProperty(propertyName, propertyValue);
    }
}
