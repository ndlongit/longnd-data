package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.UserRole;
import com.structis.vip.server.dao.support.GenericDao;

public interface UserRoleDao extends GenericDao<UserRole, Integer> {
	List<UserRole> findByUserId(Integer userId);

	List<UserRole> findBy(Integer userId, Integer roleId, String perimetreId);

	UserRole insert(UserRole userRole);

	List<UserRole> insert(List<UserRole> userRoles);
	
	List<UserRole> findByPerimetre(String perimetreId);

	Boolean deleteByUserId(Integer userId);
}
