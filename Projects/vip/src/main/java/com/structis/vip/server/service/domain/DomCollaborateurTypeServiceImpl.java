package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.Category;
import com.structis.vip.server.bean.domain.CollaborateurType;
import com.structis.vip.server.dao.CollaborateurTypeDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domCollaborateurTypeService")
public class DomCollaborateurTypeServiceImpl extends GenericEntityServiceImpl<CollaborateurType, Integer> implements
		DomCollaborateurTypeService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(DomCollaborateurTypeServiceImpl.class);

	@Autowired
	@Qualifier("collaborateurTypeDao")
	private CollaborateurTypeDao collaborateurTypeDao;

	@Override
	public GenericDao<CollaborateurType, Integer> getDao() {
		return collaborateurTypeDao;
	}

	@Override
	public CollaborateurType getNew() {
		return new CollaborateurType();
	}

	@Override
	public CollaborateurType getNewWithDefaults() {
		return this.getNew();
	}

	/**
	 * Get all entites
	 */
	public List<CollaborateurType> getAllCollaborateurType() {
		return this.find();
	}

	@Override
	public List<CollaborateurType> getCollaborateurTypeByEntite(String entiteId) {
		return collaborateurTypeDao.getCollaborateurTypeByEntite(entiteId);
	}
	
	@Override
	public CollaborateurType insert(CollaborateurType doc) {
		return collaborateurTypeDao.insert(doc);
	}

	@Override
	public CollaborateurType update(CollaborateurType doc) {
		return collaborateurTypeDao.update(doc);
	}

}
