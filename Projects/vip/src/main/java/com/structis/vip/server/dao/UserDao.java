package com.structis.vip.server.dao;

import java.util.List;

import com.structis.vip.server.bean.domain.User;
import com.structis.vip.server.dao.support.GenericDao;

public interface UserDao extends GenericDao<User, Integer> {
	
	List<User> findUsersByEntite(String entiteId);
	
	User findUserByUserName(String userName, String domain);
	
	User insert(User user);

	User update(User user);

	User getAuthorization(String name, Integer domainId, String password);
	
	List<User> findByPerimetre(String perimetreId);

	List<User> findUsersByEntiteAndUser(String entiteId, Integer userId);

	User findUserByNameDomainAndEntite(String userName, String domain,
			String entId);

	boolean checkBreakIntegrityData(User user);
}
