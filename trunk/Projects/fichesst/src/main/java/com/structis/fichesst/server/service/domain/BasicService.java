package com.structis.fichesst.server.service.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.structis.fichesst.server.bean.domain.core.BasicEntity;
import com.structis.fichesst.shared.exception.DataConstraintException;

public interface BasicService<T extends BasicEntity<?>, ID extends Serializable> {

	public T find(ID id);

	public List<T> findAll();

	public T save(T entity) throws DataConstraintException, Exception;

	public void delete(ID id);

	public void delete(T entity);

	T update(T entity) throws DataConstraintException, Exception;

	List<T> findByProperty(String propertyName, Object... propertyValues);

	T findUniqueByProperty(String propertyName, Object propertyValue) throws EmptyResultDataAccessException,
			IncorrectResultSizeDataAccessException;
}
