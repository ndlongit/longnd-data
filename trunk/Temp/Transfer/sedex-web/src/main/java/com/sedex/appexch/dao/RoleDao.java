package com.sedex.appexch.dao;

import java.util.List;

import com.sedex.appexch.dao.base.BasicDao;
import com.sedex.appexch.model.Role;

public interface RoleDao extends BasicDao<Role, Long> {

	List<Role> getByNames(List<String> roleNames);
}
