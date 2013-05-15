package com.structis.vip.server.service.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.structis.vip.server.bean.domain.Role;
import com.structis.vip.server.dao.RoleDao;
import com.structis.vip.server.dao.support.GenericDao;
import com.structis.vip.server.service.domain.core.GenericEntityServiceImpl;

@Service("domRoleService")
public class DomRoleServiceImpl extends GenericEntityServiceImpl<Role, Integer> implements DomRoleService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(DomRoleServiceImpl.class);

	@Autowired
	@Qualifier("roleDao")
	private RoleDao roleDao;

	@Override
	public GenericDao<Role, Integer> getDao() {
		return roleDao;
	}

	@Override
	public Role getNew() {
		return new Role();
	}

	@Override
	public Role getNewWithDefaults() {
		return this.getNew();
	}

	@Override
	public List<Role> getRoles() {
		return roleDao.getRoles();
	}
}