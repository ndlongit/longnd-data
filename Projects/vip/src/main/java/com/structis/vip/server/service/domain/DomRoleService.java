package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.Role;
import com.structis.vip.server.service.domain.core.GenericEntityService;

public interface DomRoleService extends GenericEntityService<Role, Integer> {

    List<Role> getRoles();
}
