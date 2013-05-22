package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.DomDel;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomDomDelService extends GenericEntityService<DomDel, Integer> {

    List<DomDel> getByDelId(Integer group);

    DomDel insert(DomDel demDom);

    Boolean deleteByDelId(Integer group, String path);

    List<DomDel> findByDocumentModel(Integer dId);
}
