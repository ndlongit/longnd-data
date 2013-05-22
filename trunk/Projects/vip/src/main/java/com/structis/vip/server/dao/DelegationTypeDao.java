package com.structis.vip.server.dao;

import com.structis.vip.server.bean.domain.DelegationType;
import com.structis.vip.server.dao.support.GenericDao;

public interface DelegationTypeDao extends GenericDao<DelegationType, Integer> {

    DelegationType getByType(String type);

    DelegationType getById(Integer id);

    DelegationType insert(DelegationType delegationType);

    DelegationType update(DelegationType delegationType);

}
