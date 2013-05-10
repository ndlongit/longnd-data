package org.java.demo.service.core;

import java.io.Serializable;
import java.util.List;

import org.java.demo.exception.DataConstraintException;
import org.java.demo.model.core.BasicEntity;

public interface BasicService<T extends BasicEntity<?>, ID extends Serializable> {

    public T find(ID id);

    public List<T> findAll();

    public T save(T entity) throws DataConstraintException, Exception;

    public void delete(ID id);

    public void delete(T entity);

    T update(T entity) throws DataConstraintException, Exception;

    @SuppressWarnings("rawtypes")
    List<T> findByProperty(String propertyName, List propertyValues);

    T findUniqueByProperty(String propertyName, Object propertyValue);
}
