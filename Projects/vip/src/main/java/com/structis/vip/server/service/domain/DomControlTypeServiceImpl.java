package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.ControlType;
import com.structis.vip.server.dao.ControlTypeDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domControlTypeService")
public class DomControlTypeServiceImpl extends GenericEntityServiceImpl<ControlType, Integer> implements
		DomControlTypeService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(DomControlTypeServiceImpl.class);

	@Autowired
	@Qualifier("controlTypeDao")
	private ControlTypeDao controlTypeDao;

	@Override
	public GenericDao<ControlType, Integer> getDao() {
		return controlTypeDao;
	}

	@Override
	public ControlType getNew() {
		return new ControlType();
	}

	@Override
	public ControlType getNewWithDefaults() {
		return this.getNew();
	}

	@Override
	public List<ControlType> findByEntite(String entiteId) {
		return controlTypeDao.findByEntite(entiteId);
	}

	@Override
	public ControlType insert(ControlType nature) {
		return controlTypeDao.insert(nature);
	}

	@Override
	public ControlType update(ControlType nature) {
		return controlTypeDao.update(nature);
	}

	@Override
	public List<ControlType> findAll() {
		return this.find();
	}
}
