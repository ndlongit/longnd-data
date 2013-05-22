package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.EntiteJuridique;
import com.structis.vip.server.dao.support.GenericDao;

public interface EntiteJuridiqueDao extends GenericDao<EntiteJuridique, Integer> {

    EntiteJuridique insert(EntiteJuridique doc);

    EntiteJuridique update(EntiteJuridique doc);

    List<EntiteJuridique> findByEntiteId(String entId);

    EntiteJuridique getDefaultByEntiteId(String entityId);

}
