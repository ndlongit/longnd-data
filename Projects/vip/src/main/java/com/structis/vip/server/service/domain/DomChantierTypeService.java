package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.ChantierType;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomChantierTypeService extends GenericEntityService<ChantierType, Integer> {

    List<ChantierType> findChantierByEntite(String entiteId);

    ChantierType insert(ChantierType doc);

    ChantierType update(ChantierType doc);

    List<ChantierType> findAll();
}
