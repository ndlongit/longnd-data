package com.structis.fichesst.server.service.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.structis.fichesst.server.bean.domain.core.BasicEntity;
import com.structis.fichesst.server.dao.BasicDao;
import com.structis.fichesst.shared.exception.DataConstraintException;

import edu.emory.mathcs.backport.java.util.Arrays;

@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BasicServiceImpl<T extends BasicEntity<?>, ID extends Serializable, DAO extends BasicDao> implements
		BasicService<T, ID> {

	private static final Logger logger = Logger.getLogger(BasicServiceImpl.class);

	protected DAO dao;

	public DAO getDao() {
		return dao;
	}

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
	public List<T> findAll() {
		List<T> results = dao.findAll();
		return results;
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
		}
		catch( Exception e ) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return entity;
	}

	@Override
	public T update(T entity) throws DataConstraintException, Exception {
		T result = null;
		try {
			result = (T) dao.update(entity);
		}
		catch( Exception e ) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return result;
	}

	@Override
	public List<T> findByProperty(String propertyName, Object... propertyValues) {
		return dao.findByProperty(propertyName, Arrays.asList(propertyValues));
	}

	@Override
	public T findUniqueByProperty(String propertyName, Object propertyValue) throws EmptyResultDataAccessException,
			IncorrectResultSizeDataAccessException {
		return (T) dao.findUniqueByProperty(propertyName, propertyValue);
	}
}
