package com.sedex.appexch.dao;

import java.util.List;

import com.sedex.appexch.dao.base.BasicDao;
import com.sedex.appexch.model.User;
import com.sedex.appexch.search.UserSearch;

public interface UserDao extends BasicDao<User, Long> {

	User getByLoginName(final String loginName);

	List<User> search(UserSearch searchModel);

}
