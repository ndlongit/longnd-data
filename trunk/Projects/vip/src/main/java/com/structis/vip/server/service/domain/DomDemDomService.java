package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.DemDom;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomDemDomService extends GenericEntityService<DemDom, Integer> {

    List<DemDom> getAllDemDomsByDemGroup(Integer group);

    DemDom insert(DemDom demDom);

    Boolean deleteByGroup(Integer group);

    List<DemDom> findByDocumentModel(Integer dId);
}
