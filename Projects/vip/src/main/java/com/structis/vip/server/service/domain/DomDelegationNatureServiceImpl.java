package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.DelegationNature;
import com.structis.vip.server.dao.DelegationNatureDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;
import com.structis.vip.shared.model.DelegationNatureModel;

@Service("domDelegationNatureService")
public class DomDelegationNatureServiceImpl extends GenericEntityServiceImpl<DelegationNature, Integer> implements
		DomDelegationNatureService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(DomDelegationNatureServiceImpl.class);

	@Autowired
	@Qualifier("delegationNatureDao")
	private DelegationNatureDao delegationNatureDao;

	@Override
	public GenericDao<DelegationNature, Integer> getDao() {
		return delegationNatureDao;
	}

	@Override
	public DelegationNature getNew() {
		return new DelegationNature();
	}

	@Override
	public DelegationNature getNewWithDefaults() {
		return this.getNew();
	}

	@Override
	public List<DelegationNatureModel> findNatureByEntite(String entiteId) {
		return delegationNatureDao.findNatureByEntite(entiteId);
	}

	@Override
	public DelegationNature insert(DelegationNature nature) {
		return delegationNatureDao.insert(nature);
	}

	@Override
	public DelegationNature update(DelegationNature nature) {
		return delegationNatureDao.update(nature);
	}

	@Override
	public List<DelegationNatureModel> findNatureByEntiteAndPerimetreType(
			String entiteId, String ptyId,Boolean isSub) {
		return delegationNatureDao.findNatureByEntiteAndPerimetreType(entiteId, ptyId, isSub);
	}
}
