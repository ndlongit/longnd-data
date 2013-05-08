package com.structis.fichesst.server.dao;

import java.util.List;

import com.structis.fichesst.server.bean.domain.FicheSt;

public interface FicheStDao extends BasicDao<FicheSt, Integer> {

	List<FicheSt> findByChantierId(Integer chantierId);
}
