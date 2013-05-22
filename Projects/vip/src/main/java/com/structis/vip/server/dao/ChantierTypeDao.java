package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.ChantierType;
import com.structis.vip.server.dao.support.GenericDao;

public interface ChantierTypeDao extends GenericDao<ChantierType, Integer> {

    public List<ChantierType> findChantierByEntite(String entiteId);

    public ChantierType insert(ChantierType nature);

    public ChantierType update(ChantierType nature);
}
