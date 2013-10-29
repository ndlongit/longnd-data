package org.java.demo.service.core;

import java.io.Serializable;
import java.util.List;

import org.java.demo.exception.DataConstraintException;
import org.java.demo.model.core.BasicEntity;

public interface BasicService<T extends BasicEntity<?>, ID extends Serializable> {

    public T find(ID id);
    
    public List<T> findByIds(List<T> ids);

    public List<T> findAll();
    
    public List<ID> findAllIds();

    public T save(T entity) throws DataConstraintException, Exception;

    public void delete(ID id);

    public void delete(T entity);

    public T update(T entity) throws DataConstraintException, Exception;

    @SuppressWarnings("rawtypes")
    public List<T> findByProperty(String propertyName, List propertyValues);

    public T findUniqueByProperty(String propertyName, Object propertyValue);
}