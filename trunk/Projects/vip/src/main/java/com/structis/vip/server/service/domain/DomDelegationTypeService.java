package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.DelegationType;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomDelegationTypeService extends GenericEntityService<DelegationType, Integer> {

    List<DelegationType> getAllTypes();

    DelegationType getByType(String type);

    DelegationType getById(Integer id);

    DelegationType insert(DelegationType delegationType);

    DelegationType update(DelegationType delegationType);
}
