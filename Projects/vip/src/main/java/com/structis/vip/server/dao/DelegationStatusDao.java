package com.structis.vip.server.dao;

import com.structis.vip.server.bean.domain.DelegationStatus;
import com.structis.vip.server.dao.support.GenericDao;

public interface DelegationStatusDao extends GenericDao<DelegationStatus, Integer> {

	DelegationStatus insert(DelegationStatus status);

	DelegationStatus update(DelegationStatus status);

}
