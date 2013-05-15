package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.DelegantTypeGroup;
import com.structis.vip.server.dao.DelegantTypeGroupDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domDelegantTypeGroupService")
public class DomDelegantTypeGroupServiceImpl extends GenericEntityServiceImpl<DelegantTypeGroup, Integer>
		implements DomDelegantTypeGroupService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger
			.getLogger(DomDelegantTypeGroupServiceImpl.class);

	@Autowired
	@Qualifier("delegantTypeGroupDao")
	private DelegantTypeGroupDao delegantTypeGroupDao;
	@Override
	public GenericDao<DelegantTypeGroup, Integer> getDao() {
		return delegantTypeGroupDao;
	}
	
	@Override
	public List<DelegantTypeGroup> getDelegantTypeGroups() {		
		return this.find();
	}

	@Override
	public DelegantTypeGroup findById(Integer typeId) {
		DelegantTypeGroup l = this.getByPrimaryKey(typeId);
		return l;
	}

	@Override
	public DelegantTypeGroup insert(DelegantTypeGroup doc) {
		return delegantTypeGroupDao.insert(doc);
	}

	@Override
	public DelegantTypeGroup update(DelegantTypeGroup doc) {
		return delegantTypeGroupDao.update(doc);
	}
	

	@Override
	public DelegantTypeGroup getNew() {
		return new DelegantTypeGroup();
	}

	@Override
	public DelegantTypeGroup getNewWithDefaults() {
		return new DelegantTypeGroup();
	}
	
	
}
