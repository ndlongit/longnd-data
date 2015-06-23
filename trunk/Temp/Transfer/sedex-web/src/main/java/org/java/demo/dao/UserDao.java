package org.java.demo.dao;

import java.util.List;

import org.java.demo.dao.core.BasicDao;
import org.java.demo.model.User;
import org.java.demo.search.UserSearch;

public interface UserDao extends BasicDao<User, Long> {

    User getByLoginName(final String loginName);

    List<User> search(UserSearch searchModel);

}
