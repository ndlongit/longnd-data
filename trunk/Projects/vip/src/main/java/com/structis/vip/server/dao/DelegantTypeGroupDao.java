package com.structis.vip.server.dao;

import com.structis.vip.server.bean.domain.DelegantTypeGroup;
import com.structis.vip.server.dao.support.GenericDao;

public interface DelegantTypeGroupDao extends GenericDao<DelegantTypeGroup, Integer> {
	DelegantTypeGroup update(DelegantTypeGroup entity);
	DelegantTypeGroup insert(DelegantTypeGroup entity);	
}
