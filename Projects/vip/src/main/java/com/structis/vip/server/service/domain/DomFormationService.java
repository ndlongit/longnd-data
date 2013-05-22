package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.Formation;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomFormationService extends GenericEntityService<Formation, Integer> {

    List<Formation> findByEntite(String entiteId);

    Formation insert(Formation doc);

    Formation update(Formation doc);

    List<Formation> findAll();
}
