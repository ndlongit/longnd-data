package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.DelegationNature;
import com.structis.vip.server.service.domain.core.GenericEntityService;
import com.structis.vip.shared.model.DelegationNatureModel;

public interface DomDelegationNatureService extends GenericEntityService<DelegationNature, Integer> {

    List<DelegationNatureModel> findNatureByEntite(String entiteId);

    List<DelegationNatureModel> findNatureByEntiteAndPerimetreType(String entiteId, String ptyId, Boolean isSub);

    DelegationNature insert(DelegationNature doc);

    DelegationNature update(DelegationNature doc);
}
