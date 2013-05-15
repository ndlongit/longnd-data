package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.Role;
import com.structis.vip.server.dao.support.GenericDao;

public interface RoleDao extends GenericDao<Role, Integer> {
	List<Role> getRoles();
}
