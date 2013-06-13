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
public abstract class BasicServiceImpl<T extends BasicEntity<?>, ID extends Serializable, DAO extends BasicDao> implements BasicService<T, ID> {

    private static final String METHOD_BEGIN = " - Begin";

    private static final String METHOD_END = " - End";

    protected Logger logger = Logger.getLogger(this.getClass());

    protected DAO dao;

    public DAO getDao() {
        return dao;
    }

    // Do not use @Autowired on field directly, otherwise error appear
    @Autowired
    public void setDao(DAO dao) {
        this.dao = dao;
    }

    @Override
    public T find(ID id) {
        logger.debug("find()" + METHOD_BEGIN);
        T result = (T) dao.find(id);
        logger.debug("find()" + METHOD_END);
        return result;
    }

    @Override
    public List<T> findByIds(List<T> ids) {
        logger.debug("findByIds()" + METHOD_BEGIN);
        List results = dao.findByIds(ids);
        logger.debug("findByIds()" + METHOD_END);
        return results;
    }

    @Override
    public List<T> findAll() {
        logger.debug("findAll()" + METHOD_BEGIN);
        List<T> results = dao.findAll();
        logger.debug("findAll()" + METHOD_END);
        return results;
    }

    @Override
    public List<ID> findAllIds() {
        logger.debug("findAllIds()" + METHOD_BEGIN);
        List results = dao.findAllIds();
        logger.debug("findAllIds()" + METHOD_END);
        return results;
    }

    @Override
    public void delete(ID id) {
        logger.debug("delete(ID id)" + METHOD_BEGIN);
        T entity = (T) dao.find(id);
        delete(entity);
        logger.debug("delete(ID id)" + METHOD_END);
    }

    @Override
    public void delete(T entity) {
        logger.debug("delete(T entity)" + METHOD_BEGIN);
        dao.delete(entity);
        logger.debug("delete(T entity)" + METHOD_END);
    }

    @Override
    public T save(T entity) throws DataConstraintException, Exception {
        logger.debug("save()" + METHOD_BEGIN);
        try {
            dao.save(entity);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        logger.debug("save()" + METHOD_END);
        return entity;
    }

    @Override
    public T update(T entity) throws DataConstraintException, Exception {
        logger.debug("update()" + METHOD_BEGIN);
        T result = (T) dao.update(entity);
        logger.debug("update()" + METHOD_END);
        return result;
    }

    @Override
    public List<T> findByProperty(String propertyName, List propertyValues) {
        logger.debug("findByProperty()" + METHOD_BEGIN);
        List results = dao.findByProperty(propertyName, propertyValues);
        logger.debug("findByProperty()" + METHOD_END);
        return results;
    }

    @Override
    public T findUniqueByProperty(String propertyName, Object propertyValue) {
        logger.debug("findUniqueByProperty()" + METHOD_BEGIN);
        T result = (T) dao.findUniqueByProperty(propertyName, propertyValue);
        logger.debug("findUniqueByProperty()" + METHOD_END);
        return result;
    }
}
