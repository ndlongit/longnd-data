package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.Formation;
import com.structis.vip.server.dao.support.GenericDao;

public interface FormationDao extends GenericDao<Formation, Integer> {

	public List<Formation> findByEntite(String entiteId);

	public Formation insert(Formation nature);

	public Formation update(Formation nature);
}
