package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.EntiteJuridique;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomEntiteJuridiqueService extends GenericEntityService<EntiteJuridique, Integer> {

    public EntiteJuridique findById(Integer id);

    public EntiteJuridique insert(EntiteJuridique doc);

    public EntiteJuridique update(EntiteJuridique doc);

    public List<EntiteJuridique> findByEntiteId(String entId);

    public EntiteJuridique getDefaultByEntiteId(String entityId);
}
