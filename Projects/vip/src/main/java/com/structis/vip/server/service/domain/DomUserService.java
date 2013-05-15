package com.structis.vip.server.service.domain;

import java.util.List;

import com.structis.vip.server.bean.domain.User;
import com.structis.vip.server.service.domain.core.GenericEntityService;
import com.structis.vip.shared.exception.ExceptionType;
import com.structis.vip.shared.exception.UserException;

public interface DomUserService extends GenericEntityService<User, Integer> {

	public List<User> findUsers();

	public List<User> findUsersByEntite(String entiteId);

	public User findUserById(final int id);

	public User findUserByUserName(final String userName, final String domain);

	public User insert(User user) throws UserException;

	public User update(User user) throws UserException;
	
	public User updateNoRoles(User user);

	public Boolean deleteWithRoles(User user);

	public User getAuthorization(String name, Integer domainId,
			String password);

	public ExceptionType changePassword(Integer userId, String currentPassword, String newPassword);
	
	List<User> findByPerimetre(String perimetreId);

	public List<User> findUsersByEntiteAndUser(String entiteId, Integer id);
}
