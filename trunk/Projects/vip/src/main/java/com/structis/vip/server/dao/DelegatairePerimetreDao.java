package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.DelegatairePerimetre;
import com.structis.vip.server.dao.support.GenericDao;

public interface DelegatairePerimetreDao extends GenericDao<DelegatairePerimetre, Integer> {

    List<DelegatairePerimetre> getByDelegataire(Integer colId);

    DelegatairePerimetre insert(DelegatairePerimetre rec);

    Boolean deleteByDelegataire(Integer colId);
}
