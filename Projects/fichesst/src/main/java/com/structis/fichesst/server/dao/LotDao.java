package com.structis.fichesst.server.dao;

import java.util.List;

import com.structis.fichesst.server.bean.domain.Lot;

public interface LotDao extends BasicDao<Lot, Integer> {

	List<Lot> findByNameAndChantier(Integer chantierId, String name);
}
