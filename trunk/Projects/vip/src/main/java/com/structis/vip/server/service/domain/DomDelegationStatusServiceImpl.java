package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.DelegationStatus;
import com.structis.vip.server.dao.DelegationStatusDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domDelegationStatusService")
public class DomDelegationStatusServiceImpl extends GenericEntityServiceImpl<DelegationStatus, Integer> implements
		DomDelegationStatusService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(DomDelegationStatusServiceImpl.class);

	@Autowired
	@Qualifier("delegationStatusDao")
	private DelegationStatusDao delegationStatusDao;

	@Override
	public GenericDao<DelegationStatus, Integer> getDao() {
		return delegationStatusDao;
	}

	@Override
	public DelegationStatus getNew() {
		return new DelegationStatus();
	}

	@Override
	public DelegationStatus getNewWithDefaults() {
		return this.getNew();
	}

	public List<DelegationStatus> getAllDelegationStatuses() {
		return this.find();
	}

	@Override
	public DelegationStatus findById(Integer id) {
		return this.getByPrimaryKey(id);
	}

	@Override
	public DelegationStatus insert(DelegationStatus status) {
		return delegationStatusDao.insert(status);
	}

	@Override
	public DelegationStatus update(DelegationStatus status) {
		return delegationStatusDao.update(status);
	}
}
