package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.DelegationStatus;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomDelegationStatusService extends GenericEntityService<DelegationStatus, Integer> {

    public List<DelegationStatus> getAllDelegationStatuses();

    public DelegationStatus findById(Integer id);

    public DelegationStatus insert(DelegationStatus doc);

    public DelegationStatus update(DelegationStatus doc);
}
