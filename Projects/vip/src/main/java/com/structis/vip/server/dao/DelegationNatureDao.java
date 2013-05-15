package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.DelegationNature;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.shared.model.DelegationNatureModel;

public interface DelegationNatureDao extends GenericDao<DelegationNature, Integer> {

	public List<DelegationNatureModel> findNatureByEntite(String entiteId);

	public DelegationNature insert(DelegationNature nature);

	public DelegationNature update(DelegationNature nature);

	public List<DelegationNatureModel> findNatureByEntiteAndPerimetreType(
			String entiteId, String ptyId,Boolean isSub);
}
