package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.UserRole;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomUserRoleService extends GenericEntityService<UserRole, Integer> {

    List<UserRole> findByPerimetre(String perimetreId);
}
