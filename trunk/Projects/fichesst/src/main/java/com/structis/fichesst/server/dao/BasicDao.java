package com.structis.fichesst.server.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import com.structis.fichesst.server.bean.domain.core.BasicEntity;
import com.structis.fichesst.shared.exception.DataConstraintException;

@SuppressWarnings("rawtypes")
public interface BasicDao<T extends BasicEntity, ID extends Serializable> {

	/**
	 * Retrieve an persisted object using the given id as primary key. Returns null if not found.
	 * 
	 * @param id object's primary key
	 * @return object
	 */
	T find(ID id);

	List<T> findAll();

	/**
	 * Save all changes made to an object.
	 * 
	 * @param entity entity
	 */
	void save(T entity) throws DataConstraintException, Exception;

	T update(T entity) throws DataConstraintException, Exception;

	/**
	 * Remove an object. Check if object is not default one. If object implements <code>Undeletable</code> then it is hidden
	 * instead of deleted.
	 * 
	 * @param entity entity
	 * @throws UnsupportedOperationException - if entity is default one
	 * @see Undeletable
	 */
	void delete(T entity) throws UnsupportedOperationException;

	List<T> findByProperty(String propertyName, List propertyValues);

	T findUniqueByProperty(String propertyName, Object propertyValue) throws NoResultException, NonUniqueResultException;
}
