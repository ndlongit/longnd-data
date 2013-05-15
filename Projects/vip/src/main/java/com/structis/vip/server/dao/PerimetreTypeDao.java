package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.PerimetreType;
import com.structis.vip.server.dao.support.GenericDao;

public interface PerimetreTypeDao extends GenericDao<PerimetreType, String> {

	List<PerimetreType> getPerimetreTypes(String entiteId);

	PerimetreType insert(PerimetreType doc);

	PerimetreType update(PerimetreType doc);

}
