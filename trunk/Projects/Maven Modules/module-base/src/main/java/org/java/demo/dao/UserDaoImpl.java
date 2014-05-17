package org.java.demo.dao;

import org.java.demo.dao.core.AbstractDao;
import org.java.demo.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {
}
