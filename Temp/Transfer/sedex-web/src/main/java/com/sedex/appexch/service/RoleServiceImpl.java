package com.sedex.appexch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sedex.appexch.dao.RoleDao;
import com.sedex.appexch.model.Role;
import com.sedex.appexch.service.base.AbstractService;

@Service
public class RoleServiceImpl extends AbstractService<Role, Long, RoleDao>
		implements RoleService {

	@Override
	public List<Role> getByNames(List<String> roleNames) {
		return dao.getByNames(roleNames);
	}
}
