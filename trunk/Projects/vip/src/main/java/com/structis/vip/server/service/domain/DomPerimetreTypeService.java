package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.PerimetreType;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomPerimetreTypeService extends GenericEntityService<PerimetreType, String> {

    List<PerimetreType> getPerimetreTypes(String entiteId);

    PerimetreType insert(PerimetreType doc);

    PerimetreType update(PerimetreType doc);

}
