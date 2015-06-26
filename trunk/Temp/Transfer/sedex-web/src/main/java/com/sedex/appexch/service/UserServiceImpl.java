package com.sedex.appexch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sedex.appexch.dao.UserDao;
import com.sedex.appexch.model.User;
import com.sedex.appexch.search.UserSearch;
import com.sedex.appexch.service.base.AbstractService;

@Service(UserService.SERVICE_ID)
public class UserServiceImpl extends AbstractService<User, Long, UserDao>
		implements UserService {

	@Override
	public List<User> search(UserSearch searchModel) {
		List<User> results = dao.search(searchModel);
		return results;
	}

	@Override
	public User getByLoginName(String loginName) {
		return dao.getByLoginName(loginName);
	}
}
