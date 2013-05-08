package org.java.demo.dao;

import org.java.demo.dao.core.BasicDaoImpl;
import org.java.demo.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BasicDaoImpl<User, Long> implements UserDao {
}
