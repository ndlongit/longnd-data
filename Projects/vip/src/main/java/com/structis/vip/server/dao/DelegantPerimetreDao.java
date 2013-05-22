package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.DelegantPerimetre;
import com.structis.vip.server.dao.support.GenericDao;

public interface DelegantPerimetreDao extends GenericDao<DelegantPerimetre, Integer> {

    List<DelegantPerimetre> getByDelegant(Integer colId);

    DelegantPerimetre insert(DelegantPerimetre rec);

    Boolean deleteByDelegant(Integer colId);
}
